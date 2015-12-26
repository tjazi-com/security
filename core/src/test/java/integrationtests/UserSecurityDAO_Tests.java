package integrationtests;

import com.tjazi.security.core.service.dao.UserSecurityDAO;
import com.tjazi.security.core.service.dao.model.UserSecurityDAODataModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
/**
 * Created by Krzysztof Wasiak on 26/12/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class UserSecurityDAO_Tests {

    @Autowired
    private UserSecurityDAO userSecurityDAO;

    @Test
    public void saveRecordVerifySaved_Test() {
        String passwordHash = "sample password hash";
        UUID sampleProfileUuid = UUID.randomUUID();

        UserSecurityDAODataModel dataModel = new UserSecurityDAODataModel();
        dataModel.setPasswordHash(passwordHash);
        dataModel.setProfileUuid(sampleProfileUuid);

        userSecurityDAO.save(dataModel);

        List<UserSecurityDAODataModel> extractedRecords = userSecurityDAO.findByProfileUuid(sampleProfileUuid);

        assertEquals(1, extractedRecords.size());

        UserSecurityDAODataModel extractedRecord = extractedRecords.get(0);
        assertEquals(sampleProfileUuid, extractedRecord.getProfileUuid());
        assertEquals(passwordHash, extractedRecord.getPasswordHash());
    }

    @Test
    public void recordExistsCheck_Test() {
        String passwordHash = "sample password hash";
        UUID sampleProfileUuid = UUID.randomUUID();

        UserSecurityDAODataModel dataModel = new UserSecurityDAODataModel();
        dataModel.setPasswordHash(passwordHash);
        dataModel.setProfileUuid(sampleProfileUuid);

        userSecurityDAO.save(dataModel);

        assertFalse(userSecurityDAO.existsByProfileUuidPasswordHash(sampleProfileUuid, "false hash"));
        assertFalse(userSecurityDAO.existsByProfileUuidPasswordHash(UUID.randomUUID(), passwordHash));
        assertTrue(userSecurityDAO.existsByProfileUuidPasswordHash(sampleProfileUuid, passwordHash));
    }
}
