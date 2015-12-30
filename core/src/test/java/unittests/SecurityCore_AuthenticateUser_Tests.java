package unittests;

import com.tjazi.security.core.service.core.SecurityProfileAuthenticatorImpl;
import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 10/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class SecurityCore_AuthenticateUser_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public SecurityProfileAuthenticatorImpl securityCore;

    @Mock
    public UserSecurityDAO securityDAO;

    @Test
    public void authenticateUser_ExceptionOnNullInput_Test() {

        thrown.expect(IllegalArgumentException.class);

        securityCore.authenticateProfile(null);
    }

    @Test
    public void authenticateUser_ExceptionOnNullProfileUuid_Test() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.ProfileUUID is null");

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(null);
        requestMessage.setPasswordHash("sample password hash");

        securityCore.authenticateProfile(requestMessage);
    }

    @Test
    public void authenticateUser_ExceptionOnNullPasswordHash_Test() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.PasswordHash is null or empty");

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(UUID.randomUUID());
        requestMessage.setPasswordHash("");

        securityCore.authenticateProfile(requestMessage);
    }

    @Test
    public void authenticateUser_ExceptionWhenCallingDao_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "Sample Password Hash";

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        // make sure exception will 'bubble-up to the external caller'
        thrown.expect(Exception.class);

        when(securityDAO.existsByProfileUuidPasswordHash(profileUuid, passwordHash))
                .thenThrow(Exception.class);

        // call main function
        boolean response = securityCore.authenticateProfile(requestMessage);
    }


    @Test
    public void authenticateUser_SecurityRecordNotFound_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "Sample Password Hash";

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        when(securityDAO.existsByProfileUuidPasswordHash(profileUuid, passwordHash))
                .thenReturn(false);

        // call main function
        boolean response = securityCore.authenticateProfile(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).existsByProfileUuidPasswordHash(profileUuid, passwordHash);

        assertEquals(false, response);
    }

    @Test
    public void authenticateUser_PasswordMatch_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "Sample Password Hash";

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        UserSecurityDAODataModel storedDaoModel = new UserSecurityDAODataModel();
        storedDaoModel.setProfileUuid(profileUuid);
        storedDaoModel.setPasswordHash(passwordHash);

        when(securityDAO.existsByProfileUuidPasswordHash(profileUuid, passwordHash))
                .thenReturn(true);

        // call main function
        boolean response = securityCore.authenticateProfile(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).existsByProfileUuidPasswordHash(profileUuid, passwordHash);

        assertEquals(true, response);
    }
}
