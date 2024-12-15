package edu.systemia.auditing_entities.infrastructure.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = "edu.systemia.auditing_entities.infrastructure.persistence",
	entityManagerFactoryRef = "oracleEntityManagerFactory",
	transactionManagerRef = "oracleTransactionManager"
)
@ConditionalOnProperty(prefix = "use-database", name = "datasource", havingValue = "ORACLE", matchIfMissing = true)
public class OracleDataSourceConfig {

    @Primary
    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

    @Bean(name = "oracleEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("oracleDataSource") DataSource dataSource
    ) {
		var properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", "none");
		return builder.dataSource(dataSource)
			.packages("edu.systemia.auditing_entities.infrastructure.persistence.*")
			.properties(properties)
			.persistenceUnit("oracle")
			.build();
	}

    @Bean(name = "oracleTransactionManager")
    PlatformTransactionManager transactionManager(
        @Qualifier("oracleEntityManagerFactory")
        EntityManagerFactory entityManager
    ) {
		return new JpaTransactionManager(entityManager);
	}

}
