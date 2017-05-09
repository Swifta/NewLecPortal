package com.lonestarcell.mtn.model.main;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.util.JDBCPoolManager;

public class Model {
	protected static DataSource dataSource;
	public Out out;
	private Logger log = LogManager.getLogger();

	static {
		try {
			dataSource = new JDBCPoolManager().getDataSource();
			System.err.print( "Data source initialized successfully." );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Model() {
		try {
			if( dataSource == null )
				throw new IllegalStateException( "Data source is null." );
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		out = new Out();

	}
	
	protected void connCleanUp( Connection conn, PreparedStatement ps, ResultSet rs ){
		
		try {
			if( rs != null )
				rs.close();
			if( ps != null )
				ps.close();
			conn.close();
		} catch ( Exception e ) {
			log.error( e.getMessage() );
		}
		
	}
	
	protected void rollBack( Connection conn ) {
		if( conn != null )
			try {
				conn.rollback();
			} catch (Exception e) {
				log.error( e.getMessage() );
			}
		
	}
	
	protected String sha256Hex(String str) {

		try {
			
			if (str == null)
				return new String();
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] raw = digest.digest(str.getBytes(StandardCharsets.UTF_8));
	
			StringBuilder sb = new StringBuilder();
			for (byte b : raw) {
				sb.append(String.format("%02X", b));
			}
			return sb.toString();
		
		} catch ( Exception e) {
			
			log.error( e.getMessage() );
			return null;
		}
	}

}
