package edu.systemia.auditing_entities.infrastructure.config;

import edu.systemia.auditing_entities.infrastructure.persistence.interceptors.AppInterceptor;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

public class JPAConfig {

	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
		DataSource dataSource
	) {
		var em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(dataSource);
		em.setPackagesToScan("edu.systemia.auditing_entities.infrastructure");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.setProperty("hibernate.show_sql", "true");

		properties.setProperty("hibernate.ejb.interceptor", AppInterceptor.class.getName());

		em.setJpaProperties(properties);
		return em;
	}

	public JpaProperties jpaProperties() {
		JpaProperties properties = new JpaProperties();
		properties.getProperties().put("hibernate.session_factory.interceptor", AppInterceptor.class.getName());
		return properties;
	}

}
