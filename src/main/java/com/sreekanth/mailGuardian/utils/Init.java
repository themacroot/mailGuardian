//package com.sreekanth.mailGuardian.utils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import org.apache.commons.lang3.StringUtils;
//import org.xbill.DNS.Lookup;
//
//import com.sreekanth.mailGuardian.Trie.Trie;
//import com.sreekanth.mailGuardian.enums.AddressStatus;
//import com.sreekanth.mailGuardian.validators.ServerValidator;
//import com.sreekanth.mailGuardian.validators.DomainValidation;
//import com.sreekanth.mailGuardian.utils.DLdistance;
//import com.sreekanth.mailGuardian.utils.HttpUtils;
//
//public class Init {
//	static List<String> domains;
//
//	public static void main(String args[]) throws IOException {
//
//		Trie trieSpam = new Trie();
//		Trie trieBurner = new Trie();
//		String txtUrl = "https://www.stopforumspam.com/downloads/toxic_domains_whole_filtered_250000.txt";
//
//		//trieSpam = Trie.loadTrie(trieSpam, txtUrl);
//
//		// Reading data using readLine
//		String searchWord = "youbid.team";
//		long startTime = System.nanoTime();
////		if (trieSpam.search(searchWord)) {
////			System.out.println("present");
////		} else {
////			System.out.println("Absent");
////		}
//		long endTime = System.nanoTime();
//		long totalTime = endTime - startTime;
//		System.out.println(totalTime);
//
//		// SpamDomainValidation.domainMatching("zwoho.com");
//
//		/**
//		 * A small list of well known email addresses.
//		 */
//
//		String jsonURL = "https://raw.githubusercontent.com/ivolo/disposable-email-domains/master/index.json";
//
//		try {
//			domains = HttpUtils.readJsonFromUrl(jsonURL);
//			domains = HttpUtils.readTextFromUrl(txtUrl);
//			// Print the domains or perform any other operations
//			for (String domain : domains) {
//				System.out.println(domain);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		String testData[] = { "adniriubwnjdnwidjw@yahooo.com",
//
//		};
//		String mail = "sreekanth@hotmale.co.is";
//
//		AddressStatus as = validate(mail);
//		System.out.println(as.toString());
//		//MailValidator validator = new MailValidator("hanu@hotmail.com");
////		for (int ctr = 0; ctr < testData.length; ctr++) {
////			System.out.println(testData[ctr] + " is valid? " + validator.mayMailboxExist(testData[ctr]));
////		}
//		return;
//
//	}
//
//	/**
//	 * Checks if a mail address is correct and tries to correct it if possible.
//	 *
//	 * @param mail the input mail address.
//	 * @return the status of the validation with a copy of the mail address on
//	 *         success.
//	 */
//	public static AddressStatus validate(String mail) {
//
//		String domain = StringUtils.substringAfterLast(mail, "@").trim();
//		if (domain == null) {
//			// System.err.println(mail + " is no valid email address");
//			return AddressStatus.wrongSchema;
//		}
//		try {
////			if (ServerValidator.doesMXRecordExist(domain)) {
////				// System.out.println(mail + " is ok");
////				return AddressStatus.valid.setMailAddress(mail);
////			} else {
////				for (String d : domains) {
////					if (DLdistance.damerauLevenshteinDistance(d, domain, 256) <= 2) {
////						System.out.println(mail + " did you mean " + d + "?");
////						return AddressStatus.typoDetected.setMailAddress(mail.substring(0, mail.indexOf("@") + 1) + d);
////					}
////				}
////				if (ServerValidator.doesDNSRecordExist(domain)) {
////					// System.err.println(mail + " has no mail servers");
////					return AddressStatus.noMxRecord;
////				} else {
////					// System.err.println("Domain \"" + domain + "\" does not exists");
////					return AddressStatus.notRegistered;
////				}
////			}
//		} catch (IllegalStateException e) {
//			return AddressStatus.unknown;
//		}
//	}
//
//}
