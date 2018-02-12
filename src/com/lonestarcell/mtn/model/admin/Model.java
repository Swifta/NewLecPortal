package com.lonestarcell.mtn.model.admin;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutConfig;
import com.lonestarcell.mtn.model.util.JDBCPoolManager;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class Model  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected static DataSource dataSource;
	protected Long userAuthId;
	protected String userAuthSession, timeCorrection;
	protected Out out;
	private Logger log = LogManager.getLogger();
	protected ApplicationContext springAppContext;
	
	
	static {
		try {
			dataSource = new JDBCPoolManager().getDataSource();
			System.err.print( "Data source initialized successfully." );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Model( Long userAuthId, String userSession ) {
		this.setUserAuthId( userAuthId );
		this.setUserAuthSession( userSession );
		
		try {
			if( dataSource == null )
				throw new IllegalStateException( "Data source is null." );
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		out = new Out();
		

	}
	
	public Model( Long userAuthId, String userSession, ApplicationContext springAppContext ) {
		this.springAppContext = springAppContext;
		this.setUserAuthId( userAuthId );
		this.setUserAuthSession( userSession );
		
		try {
			if( dataSource == null )
				throw new IllegalStateException( "Data source is null." );
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		out = new Out();
		

	}
	
	
	

	protected Long getUserAuthId() {
		return userAuthId;
	}

	protected void setUserAuthId(Long userAuthId) {
		this.userAuthId = userAuthId;
	}

	protected String getUserAuthSession() {
		return userAuthSession;
	}

	protected void setUserAuthSession(String userAuthSession) {
		this.userAuthSession = userAuthSession;
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
	
	
	protected Out checkAuthorization( ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		
		
		
		String q = "SELECT o.organization_status AS org_status, u.user_id, u.status, u.profile_id, u.user_session, u.change_password FROM users AS u";
		q += " JOIN organization AS o ON o.id = u.organization_id";
		q += " WHERE u.user_id = ?";
		q += " LIMIT 1;";
		
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			ps = conn.prepareStatement( q );
			ps.setLong( 1, userAuthId );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			
			
			if( !rs.next() ) {
				log.debug( "No authorization data" );
				out.setMsg( "No authorization data" );
				return out;
			}
			
			if( rs.getString( "user_session" ) == null || !rs.getString( "user_session" ).equals( userAuthSession ) ){
			//if( rs.getString( "user_session" ) == null || !rs.getString( "user_session" ).equals( userSession ) ){

				log.debug( "Login session expired" );
				// log.debug( "Admin username: "+username+" Session: "+userSession );
				out.setMsg( "Not authorized [ Authorization session expired. ]" );
				out.setStatusCode( 403 );
				
				//Too bad, should be moved to controller.
				if( UI.getCurrent() != null ){
					Notification.show( "Login session expired. Please login again.", Notification.Type.ERROR_MESSAGE );
					UI.getCurrent().getNavigator().navigateTo( "login" );
				}
				return out;
			}
		
			
			if( rs.getShort( "status" ) != 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ invalid account state ]" );
				return out;
			}
			
			if( rs.getShort( "change_password" ) == 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ Pending account password reset ]" );
				return out;
				
			}
					
			
			if( rs.getShort( "org_status" ) != 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ Invalid organization state ]" );
				return out;
			}
			
			/*
			if( rs.getShort( "profile_id" ) != 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ Insufficient profile permissions ]" );
				return out;
			} */
			
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
	
	
	public Out setAdminUserId( String username, String userSession ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		
		
		
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
			
			if( rs.getString( "user_session" ) == null || !rs.getString( "user_session" ).equals( userSession ) || rs.getShort( "change_password" ) != 0 ){
			//if( rs.getString( "user_session" ) == null || !rs.getString( "user_session" ).equals( userSession ) ){

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
		
			
			if( rs.getShort( "status" ) != 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ invalid account state ]" );
				return out;
			}
					
			
			if( rs.getShort( "org_status" ) != 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ Invalid organization state ]" );
				return out;
			}
			
			/*
			if( rs.getShort( "profile_id" ) != 1 ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ Insufficient profile permissions ]" );
				return out;
			}*/
			
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
	

	protected OutConfig getConfig(){
		
		MSettings m = new MSettings( this.userAuthId, this.userAuthSession );
		InSettings inData = new InSettings();
		
		inData.setConfig( new OutConfig() );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.setConfig(in );
		if( out.getStatusCode() == 1 )
			return inData.getConfig();
		return null;
		
	}

}
