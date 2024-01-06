package com.sreekanth.mailGuardian.validators;

import javax.naming.CommunicationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.whois.WhoisClient;
import org.springframework.stereotype.Service;
import org.xbill.DNS.ARecord;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

import org.xbill.DNS.Resolver;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.TXTRecord;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.sreekanth.mailGuardian.utils.DateUtil;

/**
 * The ServerValidator class provides methods to validate and check various
 * aspects related to email servers and DNS records.
 */

@Service
public class ServerValidator {

	/**
	 * Checks if the host of the given email address exists.
	 *
	 * @param email The email address to check.
	 * @return {@code true} if the host exists, {@code false} otherwise.
	 */

	public boolean doesHostExist(String email) {
		String host = email.substring(email.indexOf("@") + 1);
		try {
			Inet4Address.getByName(host);
		} catch (UnknownHostException e) {
			System.out.println("[mail validation] host of mail does not exist email=" + email + " - " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Retrieves MX records for the given host name.
	 *
	 * @param hostName The host name to lookup MX records for.
	 * @return An ArrayList of MX records.
	 * @throws NamingException If a naming exception occurs during the process.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList getMX(String hostName) throws NamingException {
		// Perform a DNS lookup for MX records in the domain
		Hashtable env = new Hashtable();
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		DirContext ictx = new InitialDirContext(env);
		Attributes attrs = ictx.getAttributes(hostName, new String[] { "MX" });
		Attribute attr = attrs.get("MX");

		// if we don't have an MX record, try the machine itself
		if ((attr == null) || (attr.size() == 0)) {
			attrs = ictx.getAttributes(hostName, new String[] { "A" });
			attr = attrs.get("A");
			if (attr == null)
				throw new NamingException("No match for name '" + hostName + "'");
		}

		ArrayList res = new ArrayList();
		NamingEnumeration en = attr.getAll();

		while (en.hasMore()) {
			String mailhost;
			String x = (String) en.next();
			System.out.println(x);
			String f[] = x.split(" ");
			// THE fix *************
			if (f.length == 1)
				mailhost = f[0];
			else if (f[1].endsWith("."))
				mailhost = f[1].substring(0, (f[1].length() - 1));
			else
				mailhost = f[1];
			// THE fix *************
			res.add(mailhost);
		}
		return res;
	}

	/**
	 * Checks if a NS record exists for the given domain using xbill.
	 *
	 * @param domain the domain which should be checked.
	 * @return true if a NS record was found.
	 * @throws IllegalStateException on network errors.
	 * @throws UnknownHostException
	 */

	public boolean doesDNSRecordExist(String domain) throws IllegalStateException, UnknownHostException {
		try {
			Resolver resolver = new SimpleResolver();
			System.out.println("Domain Lookup");
			Lookup lookup = new Lookup(domain, Type.NS);
			lookup.setResolver(resolver);
			Record[] record = lookup.run();
			for (int i = 0; i < record.length; i++) {

				System.out.println(record[i].rdataToString() + "\n" + record.length);

			}
			if (lookup.getResult() == Lookup.TRY_AGAIN) {
				throw new IllegalStateException();
			}
			return record != null && record.length > 0;
		} catch (TextParseException e) {
		}
		return false;
	}

	/**
	 * Checks if a MX record exists for the given domain.
	 *
	 * @param domain the domain which should be checked.
	 * @return true if a MX record was found.
	 * @throws IllegalStateException on network errors.
	 * @throws UnknownHostException
	 */
	public boolean doesMXRecordExist(String domain) throws IllegalStateException, UnknownHostException {
		try {

			Resolver resolver = new SimpleResolver();
			Lookup lookup = new Lookup(domain, Type.MX);
			Record[] result = lookup.run();
			lookup.setResolver(resolver);
			if (lookup.getResult() == Lookup.TRY_AGAIN) {
				throw new IllegalStateException();
			}
			if (result != null && result.length > 0) {
				for (Record r : result) {
					MXRecord mx = (MXRecord) r;
					Record[] result2 = new Lookup(mx.getTarget(), Type.A).run();
					if (result2 != null && result2.length > 0) {
						System.out.println("OK! MX: " + mx.getTarget() + " -> "
								+ ((ARecord) result2[0]).getAddress().getHostAddress());
						return true;
					} else {
						System.err.println("Fail: could not resolv " + mx.getTarget());
					}
				}
			}
		} catch (TextParseException e) {
			e.printStackTrace();
		}
		// System.err.println("Fail: not mx record for " + domain + " found");
		return false;
	}

	public boolean getMXRecords(String domain) throws IllegalStateException, UnknownHostException {
		try {

			Resolver resolver = new SimpleResolver();
			Lookup lookup = new Lookup(domain, Type.MX);
			Record[] result = lookup.run();
			lookup.setResolver(resolver);
			if (lookup.getResult() == Lookup.TRY_AGAIN) {
				throw new IllegalStateException();
			}
			if (result != null && result.length > 0) {
				for (Record r : result) {
					System.out.println("Loop 1 : " + r.toString());
					MXRecord mx = (MXRecord) r;
					Record[] result2 = new Lookup(mx.getTarget(), Type.A).run();

					for (Record rec : result2) {
						System.out.println("Loop 2 : " + rec.toString());
					}
				}
			}
		} catch (TextParseException e) {
			e.printStackTrace();
		}
		// System.err.println("Fail: not mx record for " + domain + " found");
		return false;
	}

	/**
	 * Checks if an ANY record exists for the specified domain.
	 *
	 * @param domain The domain to check for ANY records.
	 * @return true if an ANY record exists, false otherwise.
	 * @throws IllegalStateException If there is an illegal state during DNS
	 *                               resolution.
	 * @throws UnknownHostException  If the IP address of a host could not be
	 *                               determined.
	 */

	public boolean doesRecordExist(String domain) throws IllegalStateException, UnknownHostException {
		try {

			Resolver resolver = new SimpleResolver();

			Lookup lookup = new Lookup(domain, Type.TXT);

			Record[] result = lookup.run();
			lookup.setResolver(resolver);
			if (lookup.getResult() == Lookup.TRY_AGAIN) {
				lookup.getResult();
			}
			if (result != null && result.length > 0) {
				for (Record r : result) {
					System.out.println(r.toString());

				}
			}

		} catch (TextParseException e) {
			e.printStackTrace();
		}
		// System.err.println("Fail: not mx record for " + domain + " found");
		return false;
	}

	/**
	 * Retrieves the DMARC record for a given domain.
	 *
	 * @param domain The domain for which to retrieve the DMARC record.
	 * @return The DMARC record or an error message if not found.
	 * @throws TextParseException   If there's an error parsing the DMARC record.
	 * @throws UnknownHostException If the host is unknown.
	 */
	public boolean doesDmarcRecordExist(String domain) throws TextParseException, UnknownHostException {
		String dmarcSubdomain = "_dmarc." + domain;

		Lookup lookup = new Lookup(dmarcSubdomain, Type.TXT);
		Record[] records = lookup.run();

		if (lookup.getResult() == Lookup.SUCCESSFUL && records != null) {
			for (Record record : records) {
				if (record instanceof TXTRecord) {
					TXTRecord txtRecord = (TXTRecord) record;
					System.out.println(txtRecord.getStrings().get(0));
					return true; // Assuming there's only one string in the TXT record
				}
			}
		}

		return false;
	}

	/**
	 * Checks if an SPF (Sender Policy Framework) record exists for the specified
	 * domain.
	 *
	 * @param domain The domain to check for SPF records.
	 * @return true if an SPF record exists, false otherwise.
	 * @throws TextParseException   If a parsing error occurs during the DNS query.
	 * @throws UnknownHostException If the IP address of a host could not be
	 *                              determined.
	 */

	public boolean doesSPFRecordExist(String domain) throws TextParseException, UnknownHostException {
		String spfRecord = null;

		// Perform the DNS query
		Record[] records = new Lookup(domain, Type.TXT).run();

		if (records != null) {
			for (Record record : records) {
				System.out.println(record);
				if (record.toString().contains("spf")) {
					return true;
				}
			}
		}

		return false;
	}

	public static final String WHOIS_SERVER_ROOT = "whois.iana.org";

	public static final int WHOIS_PORT = 43;

	public boolean creationbeforeXYears(String domain, int x) throws Exception {


		
		String referrer  = getReferrer(getWhoisData(domain,WHOIS_SERVER_ROOT));
		System.out.println("refferrer is  -->" + referrer);
		String creationDate = getCreationDate(getWhoisData(domain,referrer));
		System.out.println("credate is  -->" + creationDate);
		Date creationDateType= DateUtil.parseDateString(creationDate);
		return !DateUtil.isWithinXYears(creationDateType, x);
		
	}

	public String getWhoisData(String domain, String whoisHostName) throws SocketException, IOException {

		WhoisClient whoisClient = new WhoisClient();
		whoisClient.connect(whoisHostName, WHOIS_PORT);
		String results = whoisClient.query(domain);
		System.out.println(results);
		whoisClient.disconnect();
		return results;

	}
	
	public String getReferrer(String whoisResult) {

		String refererPattern = "refer:\\s+(\\S+)";
		Pattern pattern = Pattern.compile(refererPattern);
		Matcher matcher = pattern.matcher(whoisResult);

		if (matcher.find()) {
			String refererValue = matcher.group(1);
			System.out.println("Referer: --->" + refererValue);
			return refererValue;
		} else {
			System.out.println("Referer not found in IANA WHOIS information");
			return "Not Found";
		}

	}

	public String getCreationDate(String whoisResult) {

		String creationDatePattern = "Creation Date: (\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z)";
		Pattern pattern = Pattern.compile(creationDatePattern);
		Matcher matcher = pattern.matcher(whoisResult);

		if (matcher.find()) {
			String creationDateValue = matcher.group(1);
			System.out.println("Creation Date: " + creationDateValue);
			return creationDateValue;
		} else {
			System.out.println("Creation Date not found in WHOIS information");
			return "Not Found";
		}
		

	}



}
