package com.lonestarcell.mtn.model.admin;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.util.JDBCPoolManager;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class ModelSelfCare {
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

	protected ModelSelfCare() {
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
	
	
	public Out setAuthUserId( InUserDetails inUser ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		
		String username = inUser.getUsername();
		String userSession = inUser.getUserSession();
		
		
		
		String q = "SELECT o.organization_status AS org_status, u.user_id, u.status, u.profile_id, u.user_session, u.change_password FROM users AS u";
		q += " JOIN organization AS o ON o.id = u.organization_id";
		q += " WHERE u.username = ?";
		q += " LIMIT 1;";
		
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1, username );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			
			
			if( !rs.next() ) {
				log.debug( "No authorization data" );
				out.setMsg( "No authorization data" );
				return out;
			}
			
			//if( rs.getString( "user_session" ) == null || !rs.getString( "user_session" ).equals( userSession ) || rs.getShort( "change_password" ) != 0 ){
			if( rs.getString( "user_session" ) == null || !rs.getString( "user_session" ).equals( userSession ) ){

				log.debug( "Login session expired" );
				// log.debug( "Admin username: "+username+" Session: "+userSession );
				out.setMsg( "Not authorized [ Authorization session expired. ]" );
				out.setStatusCode( 403 );
				
				if( UI.getCurrent() != null ){
					Notification.show( "Login session expired. Please login again.", Notification.Type.ERROR_MESSAGE );
					UI.getCurrent().getNavigator().navigateTo( "login" );
				}
				
				return out;
			}
					
			
			if( rs.getShort( "org_status" ) != 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ Invalid organization state ]" );
				return out;
			}
			
			
			BData<Long> bOutData = new BData<>();
			bOutData.setData(  rs.getLong( "user_id" ) );
			out.setData( bOutData );
			
			
			
			out.setStatusCode( 1 );
			out.setMsg( "Account authorized for operation." );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	

}
