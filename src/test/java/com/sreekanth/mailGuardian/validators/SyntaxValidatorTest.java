package com.sreekanth.mailGuardian.validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SyntaxValidatorTest {

    @Test
    public void testValidEmailSyntax() {
        // Valid email address
        String validEmail = "test@example.com";

        // Check if the syntax is valid
        assertTrue(SyntaxValidator.isEmailSyntaxValid(validEmail));
    }

    @Test
    public void testInvalidEmailSyntax() {
        // Invalid email address
        String invalidEmail = "invalid.email@";

        // Check if the syntax is invalid
        assertFalse(SyntaxValidator.isEmailSyntaxValid(invalidEmail));
    }

    @Test
    public void testNullEmailSyntax() {
        // Null email address
        String nullEmail = null;

        // Check if the syntax is invalid for null input
        assertFalse(SyntaxValidator.isEmailSyntaxValid(nullEmail));
    }

    @Test
    public void testEmptyEmailSyntax() {
        // Empty email address
        String emptyEmail = "";

        // Check if the syntax is invalid for empty input
        assertFalse(SyntaxValidator.isEmailSyntaxValid(emptyEmail));
    }
}

