package com.sreekanth.mailGuardian.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

/**
 * The SyntaxValidator class provides a method for checking the syntax validity of an email address.
 * It utilizes the EmailValidator from the Apache Commons Validator library.
 */

@Component
public class SyntaxValidator {

    /**
     * Checks the syntax validity of the given email address.
     *
     * @param emailAddress The email address to be validated for syntax.
     * @return {@code true} if the email syntax is valid, {@code false} otherwise.
     */
    public static boolean isEmailSyntaxValid(String emailAddress) {
        // Create an instance of EmailValidator from Apache Commons Validator
        EmailValidator validator = EmailValidator.getInstance();

        // Validate the syntax of the email address using the EmailValidator
        return validator.isValid(emailAddress);
    }
}