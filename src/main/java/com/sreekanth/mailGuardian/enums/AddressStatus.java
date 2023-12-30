package com.sreekanth.mailGuardian.enums;

/**
 * Enums representing different states for address verification.
 */
public enum AddressStatus {
    /**
     * The address is considered valid.
     */
    valid,

    /**
     * The address has schema errors, such as a missing at sign.
     */
    wrongSchema,

    /**
     * The domain is currently not registered.
     */
    notRegistered,

    /**
     * The domain has no MX record (cannot receive emails).
     */
    noMxRecord,

    /**
     * A typographic error was detected in the address.
     */
    typoDetected,

    /**
     * The status of the address is unknown.
     */
    unknown,

    /**
     * The mail address check is pending.
     */
    pending;

    // Additional information for typoDetected status
    private String mail;

    /**
     * Gets the suggested mail address in case of a detected typo.
     *
     * @return The suggested mail address.
     */
    public String getMailAddress() {
        return mail;
    }

    /**
     * Sets the email address in case of a detected typo.
     *
     * @param mail The mail address which is guessed.
     * @return This enum constant with the specified mail address set.
     */
    public AddressStatus setMailAddress(String mail) {
        this.mail = mail;
        return this;
    }

    /**
     * Checks if the email address is not valid or a typo was detected.
     *
     * @return {@code true} if the email address is not valid or a typo was detected.
     */
    public boolean wrong() {
        return this != valid && this != typoDetected;
    }

    /**
     * Validates an email address string.
     *
     * @param string The email address string to validate.
     */
    static void validate(String string) {
        // TODO: Implement email address validation logic
    }
}