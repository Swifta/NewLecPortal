package com.lonestarcell.mtn.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories( basePackages = { "com.lonestarcell.mtn.spring.user.repo" },  entityManagerFactoryRef = "userEntityManagerFactory", 
transactionManagerRef = "userTransactionManager" )
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
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	/*
	@Bean
	public SelmaMapper selmaMapper(){
		return Selma.builder(SelmaMapper.class).build();
	}*/
	
	
	@Primary
	@Bean( name = "userEntityManagerFactory" )
	public LocalContainerEntityManagerFactoryBean userEntityManager( @Qualifier("dataSource") DataSource ds ) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource( ds );
		emfb.setPackagesToScan( "com.lonestarcell.mtn.spring.user.entity" );
		emfb.setJpaVendorAdapter( getJpaVendorAdapter() );
		emfb.setJpaProperties( getAdditionalProperties() );
		return emfb;
	}

	//@Bean
	//@Primary
	private  JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		return adapter;
	}

	
	@Primary
	@Bean
	public PlatformTransactionManager userTransactionManager( @Qualifier("userEntityManagerFactory")  EntityManagerFactory 
		    userEntityManagerFactory ) {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( userEntityManagerFactory );
		return transactionManager;
	}

	/*
	@Bean
	@Primary
	public PersistenceAnnotationBeanPostProcessor getPersistenceAnnotationPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	@Primary
	public BeanPostProcessor getPersistenceExceptionTranslator() {
		return new PersistenceExceptionTranslationPostProcessor();
	}*/

	
	@Primary
	@Bean( name = "dataSource" )
	public DataSource dataSource() {

		PoolProperties p = new PoolProperties();
		
	    p.setUrl( "jdbc:mysql://localhost:3306/benin_portal_db" );
		p.setUsername( "ben-app" );
		//p.setPassword( "adm!n" );
		p.setPassword( "b3n-a99@b3n-aPP" );
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
