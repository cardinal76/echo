package it.clevercom.echo.tm.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 16/01/2016
 * Tele-health DB configuration
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "tmEntityManagerFactory",
					transactionManagerRef = "tmTransactionManager",
					basePackages = {"${tm.jpa.repository.package}"})
@PropertySource("classpath:tm-db.properties")
public class TmDataBaseConfig {

	@Value("${tm.jpa.entity.package}")
	private String tmJpaEntityPackage;

	@Autowired
	private Environment environment;

	@Bean
	public LocalContainerEntityManagerFactoryBean tmEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(tmDataSource());
		em.setPackagesToScan(new String[] { tmJpaEntityPackage });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(tmHibernateProperties());

		return em;
	}

	private Properties tmHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("tm.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("tm.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("tm.hibernate.format_sql"));
		return properties;        
	}

	@Bean
	public DataSource tmDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("tm.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("tm.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("tm.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("tm.jdbc.password"));
		return dataSource;
	}

	@Bean
	public JpaTransactionManager tmTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(tmEntityManagerFactory().getObject());
		return transactionManager;
	}

}
