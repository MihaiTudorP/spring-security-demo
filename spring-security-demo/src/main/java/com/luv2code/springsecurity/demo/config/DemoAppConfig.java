/**
 * 
 */
package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Configuration class for the web app
 * @author Mihai-Tudor Popescu
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	private static final String CONNECTION_POOL_MAX_IDLE_TIME_PROPERTY_KEY = "connection.pool.maxIdleTime";

	private static final String CONNECTION_POOL_MAX_POOL_SIZE_PROPERTY_KEY = "connection.pool.maxPoolSize";

	private static final String CONNECTION_POOL_MIN_POOL_SIZE_PROPERTY_KEY = "connection.pool.minPoolSize";

	private static final String CONNECTION_POOL_INITIAL_POOL_SIZE_PROPERTY_KEY = "connection.pool.initialPoolSize";

	private static final String JDBC_PASSWORD_PROPERTY_KEY = "jdbc.password";

	private static final String JDBC_USER_PROPERTY_KEY = "jdbc.user";

	private static final String JDBC_URL_PROPERTY_KEY = "jdbc.url";

	private static final String JDBC_DRIVER_PROPERTY_KEY = "jdbc.driver";

	//holds data read from property files
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for the viewresolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DataSource securityDataSource() throws PropertyVetoException {
		
		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// set the jdbc driver
		securityDataSource.setDriverClass(env.getProperty(JDBC_DRIVER_PROPERTY_KEY));
		
		// logging url and user
		logger.info("JDBC URL: " + env.getProperty(JDBC_URL_PROPERTY_KEY));
		logger.info("JDBC USER: " + env.getProperty(JDBC_USER_PROPERTY_KEY));
		
		// set the database connection properties
		securityDataSource.setJdbcUrl(env.getProperty(JDBC_URL_PROPERTY_KEY));
		securityDataSource.setUser(env.getProperty(JDBC_USER_PROPERTY_KEY));
		securityDataSource.setPassword(env.getProperty(JDBC_PASSWORD_PROPERTY_KEY));
		
		// set the connection pool properties
		securityDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty(CONNECTION_POOL_INITIAL_POOL_SIZE_PROPERTY_KEY)));
		securityDataSource.setMinPoolSize(Integer.parseInt(env.getProperty(CONNECTION_POOL_MIN_POOL_SIZE_PROPERTY_KEY)));
		securityDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty(CONNECTION_POOL_MAX_POOL_SIZE_PROPERTY_KEY)));
		securityDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty(CONNECTION_POOL_MAX_IDLE_TIME_PROPERTY_KEY)));
		return securityDataSource;
	}
	
}
