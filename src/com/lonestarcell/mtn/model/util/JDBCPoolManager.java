package com.lonestarcell.mtn.model.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/*
 * Handle JDBC pool creation and other utility functions.
 * @author Live
 * 
 */
public class JDBCPoolManager {

	private DataSource dataSource;

	public JDBCPoolManager() {
		this.initJDBCPool();
	}

	private void initJDBCPool() {

		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://localhost:3306/benin_portal_db");
		
		// Remote test db
		// p.setUsername("swifta");
		// p.setPassword("swift@123");
		
		// Local test db
		p.setUsername("ben-app");
		p.setPassword("b3n-a99@b3n-aPP");
		
		//p.setRemoveAbandoned( true );
		//p.setTestOnBorrow( true );
		//p.setValidationQuery( "SELECT 1" );
		//p.setValidationInterval( 3600000 );
		
		
		// Testing JDBC reconnection.
		p.setMinEvictableIdleTimeMillis( 5000 );
		p.setTimeBetweenEvictionRunsMillis( 5000 );
		p.setMinIdle( 0 );
		
		p.setDriverClassName("com.mysql.jdbc.Driver");

		dataSource = new DataSource(p);
		dataSource.setPoolProperties(p);
		//dataSource.

	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
