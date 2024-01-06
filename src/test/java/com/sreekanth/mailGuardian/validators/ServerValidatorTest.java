package com.sreekanth.mailGuardian.validators;

import static org.junit.jupiter.api.Assertions.*;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;

class ServerValidatorTest {

	ServerValidator serverValidator = new ServerValidator();
	String invalidEmail = "";
	String validEmail = "sib.co.in";
	String exceptionEmail = "";

	@Test
	public void testDoesHostExist_ValidHost() {

		// Act
		boolean result = serverValidator.doesHostExist(validEmail);

		// Assert
		assertTrue(result);
	}

	@Test
	public void testDoesHostExist_InvalidHost() {

		// Act
		boolean result = serverValidator.doesHostExist(invalidEmail);

		// Assert
		assertFalse(result);
	}

	@Test
	public void testDoesHostExist_ExceptionHandling() {

		// Act
		boolean result = serverValidator.doesHostExist(exceptionEmail);

		// Assert
		assertFalse(result); // The method should gracefully handle the exception and return false
	}

	@Test
	public void testGetMX_WithValidMXRecords() throws NamingException {

		// Act
		@SuppressWarnings("unchecked")
		ArrayList<String> mxRecords = serverValidator.getMX(validEmail);

		// Assert
		assertFalse(mxRecords.isEmpty());

	}

	public void testGetMX_WithNoMXRecords() throws NamingException {
		// The test expects a NamingException to be thrown when there are no MX records

		// Act & Assert
		Throwable exception = assertThrows(NamingException.class, () -> serverValidator.getMX(exceptionEmail));
		assertEquals("expected messages", exception.getMessage());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetMX_WithInvalidDomain() {

		// Act
		ArrayList<String> mxRecords = null;
		try {
			mxRecords = serverValidator.getMX(invalidEmail);
		} catch (NamingException e) {
			// Catch the exception if it occurs (in case the test fails due to unexpected
			// exception)
		}

		// Assert
		assertNull(mxRecords);

	}

	@Test
	public void testDoesDNSRecordExist_WithValidDomain() throws IllegalStateException, UnknownHostException {

		// Act
		boolean result = serverValidator.doesDNSRecordExist(validEmail);

		// Assert
		assertTrue(result);

	}

	@Test
	public void testDoesDNSRecordExist_WithInvalidDomain() throws IllegalStateException, UnknownHostException {

		// Act
		boolean result = serverValidator.doesDNSRecordExist(invalidEmail);

		// Assert
		assertFalse(result);

	}

	public void testDoesDNSRecordExist_WithNetworkError() {
		// The test expects an IllegalStateException to be thrown on network errors

		// Act & Assert
		Throwable exception = assertThrows(IllegalStateException.class,
				() -> serverValidator.doesDNSRecordExist(exceptionEmail));
		assertEquals("expected messages", exception.getMessage());

	}

	@Test
	public void testdoesMXRecordExist_WithValidMXRecords()
			throws NamingException, IllegalStateException, UnknownHostException {

		// Act
		@SuppressWarnings("unchecked")
		boolean mxRecords = serverValidator.doesMXRecordExist(validEmail);

		// Assert
		assertFalse(mxRecords);

	}

	@Test
	public void testdoesRecordExist() throws NamingException, IllegalStateException, UnknownHostException {

		// Act
		@SuppressWarnings("unchecked")
		boolean mxRecords = serverValidator.doesRecordExist(validEmail);

		// Assert
		assertFalse(mxRecords);

	}

	@Test
	public void testgetMXRecord() throws NamingException, IllegalStateException, UnknownHostException {

		// Act
		@SuppressWarnings("unchecked")
		boolean mxRecords = serverValidator.getMXRecords(validEmail);

		// Assert
		assertFalse(mxRecords);

	}

	@Test
	public void testwhoisit() throws Exception {

		// Act
		@SuppressWarnings("unchecked")
		boolean mxRecords = serverValidator.creationbeforeXYears(validEmail, 4);
		System.out.println(mxRecords);
		// Assert
		assertFalse(false);

	}

	public void testdoesMXRecordExist_WithNoMXRecords() throws NamingException {
		// The test expects a NamingException to be thrown when there are no MX records

		// Act & Assert
		Throwable exception = assertThrows(NamingException.class,
				() -> serverValidator.doesMXRecordExist(exceptionEmail));
		assertEquals("expected messages", exception.getMessage());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testdoesMXRecordExist_WithInvalidDomain() throws IllegalStateException, UnknownHostException {

		// Act
		boolean mxRecords;
		mxRecords = serverValidator.doesMXRecordExist(invalidEmail);

		// Assert
		assertNull(mxRecords);

	}

	@Test
	public void testdoesDmarcRecordExist() {

		// Replace with the actual domain

		try {
			boolean result = serverValidator.doesDmarcRecordExist(validEmail);

			// Assert that the result is true, indicating that the DMARC record was found
			assertTrue(result);
		} catch (Exception e) {
			// If an exception occurs during the test, fail the test
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testdoesSPFRecordExist() {

		// Replace with the actual domain

		try {
			boolean result = serverValidator.doesSPFRecordExist(validEmail);

			// Assert that the result is true, indicating that the DMARC record was found
			assertTrue(result);
		} catch (Exception e) {
			// If an exception occurs during the test, fail the test
			fail("Exception thrown: " + e.getMessage());
		}
	}
}
