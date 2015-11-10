package com.tjazi.security.messages;

import com.tjazi.security.messages.enums.RegisterNewUserCredentialsResponseStatus;

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
