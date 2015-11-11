package unittests;

import com.tjazi.security.core.service.core.SecurityCoreImpl;
import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.RegisterNewUserCredentialsRequestMessage;
import com.tjazi.security.messages.RegisterNewUserCredentialsResponseMessage;
import com.tjazi.security.messages.RegisterNewUserCredentialsResponseStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 11/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class SecurityCore_RegisterNewProfile_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public SecurityCoreImpl securityCore;

    @Mock
    public UserSecurityDAO securityDAO;

    @Test
    public void registerNewProfileCredentials_ExceptionOnNullInput_Test() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage is null");

        securityCore.registerNewProfileCredentials(null);
    }

    @Test
    public void registerNewProfileCredentials_ExceptionOnNullProfileUuid_Test() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.ProfileUUID is null");

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();
        requestMessage.setPasswordHash("password hash");
        requestMessage.setProfileUuid(null);

        securityCore.registerNewProfileCredentials(requestMessage);
    }

    @Test
    public void registerNewProfileCredentials_ExceptionOnNullOrEmptyPasswordHash_Test() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.PasswordHash is null or empty");

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();
        requestMessage.setPasswordHash(null);
        requestMessage.setProfileUuid(UUID.randomUUID());

        securityCore.registerNewProfileCredentials(requestMessage);
    }

    @Test
    public void registerNewProfileCredentials_ExceptionWhenCallingDatabase_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "password hash";

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenThrow(Exception.class);

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();
        requestMessage.setPasswordHash(passwordHash);
        requestMessage.setProfileUuid(profileUuid);

        // main function call
        RegisterNewUserCredentialsResponseMessage responseMessage = securityCore.registerNewProfileCredentials(requestMessage);

        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);

        assertEquals(responseMessage.getRegistrationStatus(), RegisterNewUserCredentialsResponseStatus.GENERAL_ERROR);
    }

    @Test
    public void registerNewProfileCredentials_DuplicateRecordFound_Test() {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "password hash";

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.<UserSecurityDAODataModel>singletonList(new UserSecurityDAODataModel()));

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();
        requestMessage.setPasswordHash(passwordHash);
        requestMessage.setProfileUuid(profileUuid);

        // main function call
        RegisterNewUserCredentialsResponseMessage responseMessage = securityCore.registerNewProfileCredentials(requestMessage);

        // assertion / validation
        ArgumentCaptor<UserSecurityDAODataModel> daoRecordCaptor = ArgumentCaptor.forClass(UserSecurityDAODataModel.class);

        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);
        verify(securityDAO, times(0)).save(daoRecordCaptor.capture());

        assertEquals(responseMessage.getRegistrationStatus(), RegisterNewUserCredentialsResponseStatus.PROFILE_UUID_ALREADY_REGISTERED);
    }

    @Test
    public void registerNewProfileCredentials_RecordSavedSuccesfully_Test() {
        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "password hash";

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.<UserSecurityDAODataModel>emptyList());

        RegisterNewUserCredentialsRequestMessage requestMessage = new RegisterNewUserCredentialsRequestMessage();
        requestMessage.setPasswordHash(passwordHash);
        requestMessage.setProfileUuid(profileUuid);

        // main function call
        RegisterNewUserCredentialsResponseMessage responseMessage = securityCore.registerNewProfileCredentials(requestMessage);

        // assertion / validation
        ArgumentCaptor<UserSecurityDAODataModel> daoRecordCaptor = ArgumentCaptor.forClass(UserSecurityDAODataModel.class);

        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);
        verify(securityDAO, times(1)).save(daoRecordCaptor.capture());

        assertEquals(responseMessage.getRegistrationStatus(), RegisterNewUserCredentialsResponseStatus.OK);

        assertEquals(profileUuid, daoRecordCaptor.getValue().getProfileUuid());
        assertEquals(passwordHash, daoRecordCaptor.getValue().getPasswordHash());
    }
}
