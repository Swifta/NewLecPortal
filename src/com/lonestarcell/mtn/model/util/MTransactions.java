package com.lonestarcell.mtn.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MTransactions {
	private ResultSet transactions;
	private static Logger log = LogManager.getLogger();

	/**
	 * Get transactions
	 */
	public ResultSet getTransactions(Connection conn) {

		if (conn == null) {
			// log and return
			return null;
		}

		PreparedStatement ps = null;
		try {

			// 1. Get data source
			// 2. Get connection
			// 3. Set sql prepared statement

			conn.setReadOnly(true);
			ps = conn.prepareStatement("select * from transactions limit 10");

			// 4. Execute query
			// 5. Check result
			// 6. Return result set accordingly
			if (ps.execute()) {
				transactions = ps.getResultSet();
				transactions.next();
				log.info(transactions.getString("fundamo_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setReadOnly(false);
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return transactions;
	}

}
