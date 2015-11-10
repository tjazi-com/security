package com.tjazi.security.messages;

/**
 * Created by Krzysztof Wasiak on 07/10/15.
 */
public class RegisterNewUserCredentialsResponseMessage {

    private RegisterNewUserCredentialsResponseStatus RegistrationStatus;

    public RegisterNewUserCredentialsResponseStatus getRegistrationStatus() {
        return RegistrationStatus;
    }

    public void setRegistrationStatus(RegisterNewUserCredentialsResponseStatus registrationStatus) {
        RegistrationStatus = registrationStatus;
    }
}
