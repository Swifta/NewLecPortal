package com.lonestarcell.mtn.model;

import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lonestarcell.mtn.model.util.JDBCPoolManager;

public class JDBCPoolManagerTest {

	private JDBCPoolManager poolMan;
	private DataSource dataSource;

	@Before
	public void initPoolMan() {
		poolMan = new JDBCPoolManager();
		dataSource = poolMan.getDataSource();
	}

	@Test
	// @Ignore("Ignore if necessary")
	public void testDataSource() {

		Assert.assertNotNull("Data source is null", dataSource);

	}

	@Test
	// @Ignore("Ignore if necessary")
	public void testConnection() throws SQLException {
		Assert.assertNotNull("Connection is null", dataSource.getConnection());
	}

}
