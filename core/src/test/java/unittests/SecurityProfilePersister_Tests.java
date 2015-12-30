package unittests;

import com.tjazi.security.core.service.core.SecurityProfilePersisterImpl;
import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.RegisterNewUserCredentialsRequestCommand;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 11/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class SecurityProfilePersister_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public SecurityProfilePersisterImpl securityProfilePersister;

    @Mock
    public UserSecurityDAO securityDAO;

    @Test
    public void registerNewProfileCredentials_ExceptionOnNullInput_Test() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage is null");

        securityProfilePersister.registerNewProfileCredentials(null);
    }

    @Test
    public void registerNewProfileCredentials_ExceptionOnNullProfileUuid_Test() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.ProfileUUID is null");

        RegisterNewUserCredentialsRequestCommand requestMessage = new RegisterNewUserCredentialsRequestCommand();
        requestMessage.setPasswordHash("password hash");
        requestMessage.setProfileUuid(null);

        securityProfilePersister.registerNewProfileCredentials(requestMessage);
    }

    @Test
    public void registerNewProfileCredentials_ExceptionOnNullOrEmptyPasswordHash_Test() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("requestMessage.PasswordHash is null or empty");

        RegisterNewUserCredentialsRequestCommand requestMessage = new RegisterNewUserCredentialsRequestCommand();
        requestMessage.setPasswordHash(null);
        requestMessage.setProfileUuid(UUID.randomUUID());

        securityProfilePersister.registerNewProfileCredentials(requestMessage);
    }

    @Test
    public void registerNewProfileCredentials_ExceptionWhenCallingDatabase_Test() throws Exception {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "password hash";

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenThrow(Exception.class);

        RegisterNewUserCredentialsRequestCommand requestMessage = new RegisterNewUserCredentialsRequestCommand();
        requestMessage.setPasswordHash(passwordHash);
        requestMessage.setProfileUuid(profileUuid);

        thrown.expect(Exception.class);

        // main function call
        boolean responseStatus = securityProfilePersister.registerNewProfileCredentials(requestMessage);

        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);
    }

    @Test
    public void registerNewProfileCredentials_DuplicateRecordFound_Test() throws Exception {

        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "password hash";

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.<UserSecurityDAODataModel>singletonList(new UserSecurityDAODataModel()));

        RegisterNewUserCredentialsRequestCommand requestMessage = new RegisterNewUserCredentialsRequestCommand();
        requestMessage.setPasswordHash(passwordHash);
        requestMessage.setProfileUuid(profileUuid);

        thrown.expect(IllegalArgumentException.class);

        // main function call
        securityProfilePersister.registerNewProfileCredentials(requestMessage);

        // assertion / validation
        ArgumentCaptor<UserSecurityDAODataModel> daoRecordCaptor = ArgumentCaptor.forClass(UserSecurityDAODataModel.class);

        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);
        verify(securityDAO, times(0)).save(daoRecordCaptor.capture());
    }

    @Test
    public void registerNewProfileCredentials_RecordSavedSuccesfully_Test() throws Exception {
        final UUID profileUuid = UUID.randomUUID();
        final String passwordHash = "password hash";

        when(securityDAO.findByProfileUuid(profileUuid))
                .thenReturn(Collections.<UserSecurityDAODataModel>emptyList());

        RegisterNewUserCredentialsRequestCommand requestMessage = new RegisterNewUserCredentialsRequestCommand();
        requestMessage.setPasswordHash(passwordHash);
        requestMessage.setProfileUuid(profileUuid);

        // main function call
        boolean responseStatus = securityProfilePersister.registerNewProfileCredentials(requestMessage);

        // assertion / validation
        ArgumentCaptor<UserSecurityDAODataModel> daoRecordCaptor = ArgumentCaptor.forClass(UserSecurityDAODataModel.class);

        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);
        verify(securityDAO, times(1)).save(daoRecordCaptor.capture());

        assertEquals(true, responseStatus);

        assertEquals(profileUuid, daoRecordCaptor.getValue().getProfileUuid());
        assertEquals(passwordHash, daoRecordCaptor.getValue().getPasswordHash());
    }
}
