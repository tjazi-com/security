package unittests;

import com.tjazi.security.core.service.core.SecurityCoreImpl;
import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import com.tjazi.security.messages.UserAuthenticationRequestMessage;
import com.tjazi.security.messages.UserAuthenticationResponseMessage;
import com.tjazi.security.messages.enums.UserAuthenticationResponseStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Wasiak on 10/11/2015.
 */

@RunWith(MockitoJUnitRunner.class)
public class SecurityCore_Tests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    public SecurityCoreImpl securityCore;

    @Mock
    public UserSecurityDAO securityDAO;

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
        UserAuthenticationResponseMessage responseMessage = securityCore.authenticateUser(requestMessage);

        // assertion and verification
        verify(securityDAO, times(1)).findByProfileUuid(profileUuid);


    }

}
