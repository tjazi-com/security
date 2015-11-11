package unittests;

import com.tjazi.security.core.service.core.SecurityCoreImpl;
import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import com.tjazi.security.messages.UserAuthenticationResponseMessage;
import com.tjazi.security.messages.UserAuthenticationResponseStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 10/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class SecurityCore_AuthenticateUser_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public SecurityCoreImpl securityCore;

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

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenThrow(Exception.class);

        // call main function
        UserAuthenticationResponseMessage responseMessage = securityCore.authenticateProfile(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);

        assertEquals(UserAuthenticationResponseStatus.GENERAL_ERROR, responseMessage.getAuthenticationResponseStatus());
    }


    @Test
    public void authenticateUser_SecurityRecordNotFound_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "Sample Password Hash";

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.<UserSecurityDAODataModel>emptyList());

        // call main function
        UserAuthenticationResponseMessage responseMessage = securityCore.authenticateProfile(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);

        assertEquals(UserAuthenticationResponseStatus.PROFILE_UUID_NOT_FOUND, responseMessage.getAuthenticationResponseStatus());
    }

    @Test
    public void authenticateUser_MoreThanOneRecordPerUuid_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "Sample Password Hash";

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash);

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.<UserSecurityDAODataModel>nCopies(3, new UserSecurityDAODataModel()));

        // call main function
        UserAuthenticationResponseMessage responseMessage = securityCore.authenticateProfile(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);

        assertEquals(UserAuthenticationResponseStatus.GENERAL_ERROR, responseMessage.getAuthenticationResponseStatus());
    }

    @Test
    public void authenticateUser_PasswordDontMatch_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "Sample Password Hash";

        UserAuthenticationRequestMessage requestMessage = new UserAuthenticationRequestMessage();
        requestMessage.setProfileUuid(profileUuid);
        requestMessage.setPasswordHash(passwordHash + "not_match");

        UserSecurityDAODataModel storedDaoModel = new UserSecurityDAODataModel();
        storedDaoModel.setProfileUuid(profileUuid);
        storedDaoModel.setPasswordHash(passwordHash);

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.singletonList(storedDaoModel));

        // call main function
        UserAuthenticationResponseMessage responseMessage = securityCore.authenticateProfile(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);

        assertEquals(UserAuthenticationResponseStatus.WRONG_PASSWORD, responseMessage.getAuthenticationResponseStatus());
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

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.singletonList(storedDaoModel));

        // call main function
        UserAuthenticationResponseMessage responseMessage = securityCore.authenticateProfile(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);

        assertEquals(UserAuthenticationResponseStatus.OK, responseMessage.getAuthenticationResponseStatus());
    }

}
