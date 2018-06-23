/**
 * 
 */
package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.luv2code.springsecurity.demo.genericsettings.UrlConstants;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Configuration class for the web app
 * @author Mihai-Tudor Popescu
 *
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableJpaRepositories("com.luv2code.springsecurity.demo.repositories")
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource({"classpath:security-persistence-mysql.properties", "classpath:crmapp-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer{

	private static final String HIBERNATE_PACKAGES_TO_SCAN_PROPERTY_KEY = "hibernate.packagesToScan";

	private static final String HIBERNATE_SHOW_SQL_PROPERTY_KEY = "hibernate.show_sql";

	private static final String HIBERNATE_DIALECT_PROPERTY_KEY = "hibernate.dialect";

	private static final String CRMAPP_CONNECTION_POOL_MAX_IDLE_TIME_PROPERTY_KEY = "crmapp.connection.pool.maxIdleTime";

	private static final String CRMAPP_CONNECTION_POOL_MAX_POOL_SIZE_PROPERTY_KEY = "crmapp.connection.pool.maxPoolSize";

	private static final String CRMAPP_CONNECTION_POOL_MIN_POOL_SIZE_PROPERTY_KEY = "crmapp.connection.pool.minPoolSize";

	private static final String CRMAPP_CONNECTION_POOL_INITIAL_POOL_SIZE_PROPERTY_KEY = "crmapp.connection.pool.initialPoolSize";

	private static final String CRMAPP_JDBC_PASSWORD_PROPERTY_KEY = "crmapp.jdbc.password";

	private static final String CRMAPP_JDBC_USER__PROPERTY_KEY = "crmapp.jdbc.user";

	private static final String CRMAPP_JDBC_URL_PROPERTY_KEY = "crmapp.jdbc.url";

	private static final String CRMAPP_JDBC_DRIVER_PROPERTY_KEY = "crmapp.jdbc.driver";

	private static final String SECURITY_CONNECTION_POOL_MAX_IDLE_TIME_PROPERTY_KEY = "security.connection.pool.maxIdleTime";

	private static final String SECURITY_CONNECTION_POOL_MAX_POOL_SIZE_PROPERTY_KEY = "security.connection.pool.maxPoolSize";

	private static final String SECURITY_CONNECTION_POOL_MIN_POOL_SIZE_PROPERTY_KEY = "security.connection.pool.minPoolSize";

	private static final String SECURITY_CONNECTION_POOL_INITIAL_POOL_SIZE_PROPERTY_KEY = "security.connection.pool.initialPoolSize";

	private static final String SECURITY_JDBC_PASSWORD_PROPERTY_KEY = "security.jdbc.password";

	private static final String SECURITY_JDBC_USER_PROPERTY_KEY = "security.jdbc.user";

	private static final String SECURITY_JDBC_URL_PROPERTY_KEY = "security.jdbc.url";

	private static final String SECURITY_JDBC_DRIVER_PROPERTY_KEY = "security.jdbc.driver";

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
		logger.info("Initializing security data source...");
		return getDataSourceFromProperties(SECURITY_JDBC_DRIVER_PROPERTY_KEY, SECURITY_JDBC_URL_PROPERTY_KEY, SECURITY_JDBC_USER_PROPERTY_KEY, SECURITY_JDBC_PASSWORD_PROPERTY_KEY, SECURITY_CONNECTION_POOL_INITIAL_POOL_SIZE_PROPERTY_KEY, SECURITY_CONNECTION_POOL_MIN_POOL_SIZE_PROPERTY_KEY, SECURITY_CONNECTION_POOL_MAX_POOL_SIZE_PROPERTY_KEY, SECURITY_CONNECTION_POOL_MAX_IDLE_TIME_PROPERTY_KEY);
	}

	/**
	 * Method for initializing a generic data source from properties
	 * @param securityConnectionPoolMaxIdleTimePropertyKey 
	 * @param connectionPoolMaxPoolSizePropertyKey 
	 * @param connectionPoolMinPoolSizePropertyKey 
	 * @param connectionPoolInitialPoolSizePropertyKey 
	 * @param jdbcPasswordPropertyKey 
	 * @param jdbcUserPropertyKey 
	 * @param jdbcUrlPropertyKey 
	 * @param jdbcDriverPropertyKey 
	 * @return a data source for the application to use
	 * @throws PropertyVetoException
	 * @throws NumberFormatException
	 */
	private ComboPooledDataSource getDataSourceFromProperties(String jdbcDriverPropertyKey, String jdbcUrlPropertyKey, String jdbcUserPropertyKey, String jdbcPasswordPropertyKey, String connectionPoolInitialPoolSizePropertyKey, String connectionPoolMinPoolSizePropertyKey, String connectionPoolMaxPoolSizePropertyKey, String connectionPoolMaxIdleTimePropertyKey) throws PropertyVetoException, NumberFormatException {
		// create connection pool
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// set the jdbc driver
		dataSource.setDriverClass(env.getProperty(jdbcDriverPropertyKey));

		// logging url and user
		logger.info("JDBC URL: " + env.getProperty(jdbcUrlPropertyKey));
		logger.info("JDBC User: " + env.getProperty(jdbcUserPropertyKey));

		// set the database connection properties
		dataSource.setJdbcUrl(env.getProperty(jdbcUrlPropertyKey));
		dataSource.setUser(env.getProperty(jdbcUserPropertyKey));
		dataSource.setPassword(env.getProperty(jdbcPasswordPropertyKey));

		// set the connection pool properties
		dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty(connectionPoolInitialPoolSizePropertyKey)));
		dataSource.setMinPoolSize(Integer.parseInt(env.getProperty(connectionPoolMinPoolSizePropertyKey)));
		dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty(connectionPoolMaxPoolSizePropertyKey)));
		dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty(connectionPoolMaxIdleTimePropertyKey)));
		logger.info("Data source successfully initialized!");
		return dataSource;
	}

	@Bean
	public DataSource crmDataSource() throws NumberFormatException, PropertyVetoException {
		logger.info("Initializing crm data source...");
		return getDataSourceFromProperties(CRMAPP_JDBC_DRIVER_PROPERTY_KEY, CRMAPP_JDBC_URL_PROPERTY_KEY, CRMAPP_JDBC_USER__PROPERTY_KEY, CRMAPP_JDBC_PASSWORD_PROPERTY_KEY, CRMAPP_CONNECTION_POOL_INITIAL_POOL_SIZE_PROPERTY_KEY, CRMAPP_CONNECTION_POOL_MIN_POOL_SIZE_PROPERTY_KEY, CRMAPP_CONNECTION_POOL_MAX_POOL_SIZE_PROPERTY_KEY, CRMAPP_CONNECTION_POOL_MAX_IDLE_TIME_PROPERTY_KEY);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NumberFormatException, PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(crmDataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(HIBERNATE_PACKAGES_TO_SCAN_PROPERTY_KEY));
		entityManagerFactoryBean.setJpaProperties(getHibernateProperties(HIBERNATE_DIALECT_PROPERTY_KEY, HIBERNATE_SHOW_SQL_PROPERTY_KEY, HIBERNATE_DIALECT_PROPERTY_KEY, HIBERNATE_SHOW_SQL_PROPERTY_KEY));
		return entityManagerFactoryBean;
	}
	
	private Properties getHibernateProperties(String hibernateDialectPropertyKey, String hibernateShowSQLPropertyKey, String customHibernateDialectPropertyKey, String customHibernateShowSQLPropertyKey) {
		Properties props = new Properties();
		logger.info("Getting Hibernate properties from the keys below.\n" +
				hibernateDialectPropertyKey + ": " + customHibernateDialectPropertyKey + "\n" +
				hibernateShowSQLPropertyKey + ": " + customHibernateShowSQLPropertyKey);
		props.setProperty(hibernateDialectPropertyKey, env.getProperty(customHibernateDialectPropertyKey));
		props.setProperty(hibernateShowSQLPropertyKey, env.getProperty(customHibernateShowSQLPropertyKey));
		return props;
	}
	
	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(sessionFactory);

		return txManager;
	}	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler(UrlConstants.RESOURCES_RECURSIVE_PATH)
          .addResourceLocations(UrlConstants.RESOURCES_DIR_PATH); 
    }	

}
