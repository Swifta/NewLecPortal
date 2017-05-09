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
		p.setUrl("jdbc:mysql://localhost:3306/afrinnewdb");
		p.setUsername("root");
		p.setPassword("admin");
		p.setDriverClassName("com.mysql.jdbc.Driver");

		dataSource = new DataSource(p);
		dataSource.setPoolProperties(p);

	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}