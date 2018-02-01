package com.lonestarcell.mtn.spring.config;

import java.util.Properties;
import java.util.TimeZone;

import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.lonestarcell.mtn.spring.fundamo.repo" }, entityManagerFactoryRef = "fundamoEntityManagerFactory", transactionManagerRef = "fundamoTransactionManager")
public class DataAccessConfigFundamo {

	/*
	 * @Value( "${portal.db.url}" ) private String dbUrl;
	 * 
	 * @Value( "${portal.db.username}" ) private String dbUsername;
	 * 
	 * @Value( "${portal.db.password}" ) private String dbPassword;
	 * 
	 * @Value( "${portal.db.driver}" ) private String dbDriver;
	 */

	private static Logger log = LogManager.getLogger();

	@Bean( name = "fundamoEntityManagerFactory" )
	public LocalContainerEntityManagerFactoryBean fundamoEntityManagerFactory( @Qualifier("fundamoDataSource") DataSource dataSource ) {

		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

		emfb.setDataSource( dataSource );
		emfb.setPackagesToScan("com.lonestarcell.mtn.spring.fundamo.entity");
		emfb.setJpaVendorAdapter(getJpaVendorAdapter());
		emfb.setJpaProperties(getAdditionalProperties());
		return emfb;
	}

	// @Bean
	private JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		adapter.setDatabase(Database.ORACLE);
		// adapter.setDatabasePlatform( "MYSQL" );
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		return adapter;
	}

	@Bean
	public PlatformTransactionManager fundamoTransactionManager( @Qualifier("fundamoEntityManagerFactory") EntityManagerFactory
		    fundamoEntityManagerFactory ) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( fundamoEntityManagerFactory );
		return transactionManager;
	}

	/*
	 * @Bean public PersistenceAnnotationBeanPostProcessor
	 * getPersistenceAnnotationPostProcessor() { return new
	 * PersistenceAnnotationBeanPostProcessor(); }
	 * 
	 * @Bean public BeanPostProcessor getPersistenceExceptionTranslator() {
	 * return new PersistenceExceptionTranslationPostProcessor(); }
	 */

	@Bean( name="fundamoDataSource" )
	public DataSource dataSource() {

		// TimeZone tZone = TimeZone.getTimeZone( "Africa/Porto-Novo" );
		// TimeZone.setDefault( tZone );
		PoolProperties p = new PoolProperties();

		
		p.setUrl("jdbc:oracle:thin:@192.168.195.23:1521:FDCORE");
		// p.setUrl("jdbc:oracle:thin:@10.77.23.58:1521:FDCORE"); //192.168.195.23
		
		p.setUsername("fundamo");
		p.setPassword("MMfund29");
		p.setDriverClassName("oracle.jdbc.driver.OracleDriver");

		p.setMinEvictableIdleTimeMillis(5000);
		p.setTimeBetweenEvictionRunsMillis(5000);
		p.setMinIdle(0);
		
		

		log.info("fun DB URL: " + p.getUrl(), this);
		DataSource ds = new DataSource(p);

		return ds;

	}

	private Properties getAdditionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.Oracle10gDialect");
		return properties;
	}
}
