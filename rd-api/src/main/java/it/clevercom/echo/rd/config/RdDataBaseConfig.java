package it.clevercom.echo.rd.config;

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
 * @author luca
 * @since 16/01/2016
 * RD DB configuration
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "rdEntityManagerFactory",
					transactionManagerRef = "rdTransactionManager",
					basePackages = {"${rd.jpa.repository.package}"})
@PropertySource("classpath:rd-db.properties")
public class RdDataBaseConfig {

	@Value("${rd.jpa.entity.package}")
	private String rdJpaEntityPackage;

	@Autowired
	private Environment environment;

	@Bean
	public LocalContainerEntityManagerFactoryBean rdEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(rdDataSource());
		em.setPackagesToScan(new String[] { rdJpaEntityPackage });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(rdHibernateProperties());

		return em;
	}

	private Properties rdHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("rd.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("rd.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("rd.hibernate.format_sql"));
		return properties;        
	}

	@Bean
	public DataSource rdDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("rd.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("rd.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("rd.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("rd.jdbc.password"));
		return dataSource;
	}

	@Bean
	@Qualifier("rdTm")
	public JpaTransactionManager rdTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(rdEntityManagerFactory().getObject());
		return transactionManager;
	}

}
