package com.deepsingh44.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.deepsingh44.model.Product;

@Configuration
@ComponentScan(basePackages = { "com.deepsingh44" })
@EnableWebMvc
@EnableTransactionManagement
@PropertySource(value = { "classpath:config.properties" })
public class AppConfig {

	@Autowired
	private Environment environment;

	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.class"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.user"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.pass"));
		System.out.println(dataSource);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		// sessionFactory.setPackagesToScan(new String[] {"com.example.model"});
		sessionFactory.setAnnotatedClasses(Product.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		System.out.println("deep session " + sessionFactory);
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		System.out.println(properties);
		return properties;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		System.out.println("transaction " + "hello");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		System.out.println("transaction " + transactionManager);
		return transactionManager;
	}

}
