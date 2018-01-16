package com.lonestarcell.mtn.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
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
public class DataAccessConfigUser {
	
	/*
		@Value( "${portal.db.url}" )
		private String dbUrl;
		
		@Value( "${portal.db.username}" )
		private String dbUsername;
		
		@Value( "${portal.db.password}" )
		private String dbPassword;
		
		@Value( "${portal.db.driver}" )
		private String dbDriver;
	
	*/
	
	private static Logger log = LogManager.getLogger();

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter adapter) {

		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

		emfb.setDataSource(ds);
		emfb.setPackagesToScan( "com.lonestarcell.mtn.spring.entity" );
		emfb.setJpaVendorAdapter( adapter );
		emfb.setJpaProperties( getAdditionalProperties() );
		return emfb;
	}

	@Bean
	@Primary
	public JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		return adapter;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager( EntityManagerFactory emf ) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( emf );
		return transactionManager;
	}

	@Bean
	@Primary
	public PersistenceAnnotationBeanPostProcessor getPersistenceAnnotationPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	@Primary
	public BeanPostProcessor getPersistenceExceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	@Primary
	public DataSource getDS() {

		PoolProperties p = new PoolProperties();
		
	    p.setUrl( "jdbc:mysql://localhost:3306/benin_portal_db" );
		p.setUsername( "root" );
		p.setPassword( "adm!n" );
		p.setDriverClassName( "com.mysql.jdbc.Driver" );
		
		p.setMinEvictableIdleTimeMillis(5000);
		p.setTimeBetweenEvictionRunsMillis(5000);
		p.setMinIdle(0);
		
		log.info( "user DB URL: "+p.getUrl() , this);
		DataSource ds = new DataSource(p);

		return ds;

	}

	private Properties getAdditionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return properties;
	}
}
