package it.clevercom.echo.sso.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author alx
 * @since 16/01/2017
 * SSO DB configuration
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "ssoEntityManagerFactory",
					transactionManagerRef = "ssoTransactionManager",
					basePackages = {"${sso.jpa.repository.package}"})
@PropertySource("classpath:sso-db.properties")
public class DataBase_sso_Config {

	@Value("${sso.jpa.entity.package}")
	private String ssoJpaEntityPackage;

	@Autowired
	private Environment environment;

	@Bean
	public LocalContainerEntityManagerFactoryBean ssoEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(ssoDataSource());
		em.setPackagesToScan(new String[] { ssoJpaEntityPackage });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(ssoHibernateProperties());

		return em;
	}

	private Properties ssoHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("sso.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("sso.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("sso.hibernate.format_sql"));
		return properties;        
	}

	@Bean
	public DataSource ssoDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("sso.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("sso.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("sso.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("sso.jdbc.password"));
		return dataSource;
	}

	@Bean
	@Qualifier("ssoTm")
	public JpaTransactionManager ssoTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(ssoEntityManagerFactory().getObject());
		return transactionManager;
	}

}
