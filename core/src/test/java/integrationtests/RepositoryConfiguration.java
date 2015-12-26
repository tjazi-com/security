package integrationtests;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Krzysztof Wasiak on 26/12/2015.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.tjazi.security.core.service.dao.model"})
@EnableJpaRepositories(basePackages = {"com.tjazi.security.core.service.dao"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
