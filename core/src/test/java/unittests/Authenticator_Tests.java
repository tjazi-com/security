package unittests;

import com.tjazi.security.core.service.core.SecurityCoreImpl;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

/**
 * Created by Krzysztof Wasiak on 10/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class Authenticator_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public SecurityCoreImpl securityCore;

    @Test
    public void authenticateUser_ExceptionOnNullInput_Test() {

        thrown.expect(IllegalArgumentException.class);

        securityCore.authenticateUser(null);
    }

    @Test
    public void authenticateUser_ExceptionOnNullProfileUuid_Test() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.ProfileUUID is null");

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(null);
        requestMessage.setPasswordHash("sample password hash");

        securityCore.authenticateUser(requestMessage);
    }

    @Test
    public void authenticateUser_ExceptionOnNullPasswordHash_Test() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.PasswordHash is null or empty");

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(UUID.randomUUID());
        requestMessage.setPasswordHash("");

        securityCore.authenticateUser(requestMessage);
    }
}
