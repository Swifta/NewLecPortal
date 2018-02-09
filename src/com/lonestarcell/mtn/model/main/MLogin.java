package com.lonestarcell.mtn.model.main;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InLogin;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutLogin;

public class MLogin extends Model {

	private Logger log = LogManager.getLogger(MLogin.class.getName());
	
	private OutLogin outLogin;
	private InLogin inLogin;
	public MLogin() {
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}

		log.debug( " Model initialized successfully." );


	}

	public Out login( In in ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "SELECT u.user_id, u.password, u.pass_salt, u.status, u.change_password,  u.profile_id, u.last_login, u.user_session  from users AS u WHERE u.username = ? LIMIT 1";
		
		try {
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			BData< ? > bInData = in.getData();
			inLogin = (InLogin) bInData.getData();
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inLogin.getUsername() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setStatusCode( 403 );
				return out;
			}
			
			String passHash = sha256Hex( inLogin.getPassword()+rs.getString( "pass_salt" ) );
		
			if( passHash.equals( rs.getString( "password" ) ) ){
				
				log.debug( "Valid user." );
				
				String session = nextSessionId();
				if( !setLoginSession( session, rs.getLong( "user_id" ) ))
					throw new Exception( "Failure to set login session." );
				
				
				log.debug( "Valid user and login session set." );
				
				
				outLogin = new OutLogin();
				outLogin.setSessionVar( session );
				outLogin.setProfileId( rs.getInt( "profile_id" ) );
				outLogin.setUserId(  rs.getLong( "user_id" ) );
				outLogin.setStatus(  rs.getInt( "status" )+"" );
				outLogin.setChangePassword( rs.getInt( "change_password" )+"");
				outLogin.setTimeCorrection( getTimeCorrection() );
				outLogin.setLastLogin( rs.getTimestamp( "last_login" ) );
				
				String userSession = rs.getString( "user_session" );
				if( userSession == null || userSession.trim().isEmpty()){
					userSession = null;
					log.info( "Reset login session is null." );
				}
				outLogin.setResetLoginSession( userSession );
				log.debug( "Last login timestamp "+outLogin.getLastLogin(), this );
				
				log.info( "user id: "+outLogin.getUserId() );
				log.info( "user id [ rs ]: "+ rs.getLong( "user_id" ) );
				
				
				
				
				BData<OutLogin> bOutData = new BData<>();
				bOutData.setData( outLogin );
	
				out.setData( bOutData );
				
				out.setStatusCode( 1 );
				
				
				
				out.setMsg( "Login successful, loading data." );
				
				
				
				
			} else {
				log.debug( "Invalid user!" );
				out.setStatusCode( 403 );
				out.setMsg( "Invalid credentials. Please verify and try again." );
			}
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	

	
	private String nextSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	private boolean setLoginSession( String session, Long userId ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "UPDATE users SET user_session = ? WHERE user_id = ? ";
		
		try {
			conn = dataSource.getConnection();
			conn.setReadOnly( false );
			conn.setAutoCommit( false );
			ps = conn.prepareStatement( q );
			ps.setString( 1, session );
			ps.setLong( 2, userId );
			
			int s = ps.executeUpdate();
			conn.commit();
			
			log.debug("User id: "+userId);
			log.debug( "Session set status: "+s );
			return  s == 1;
			
		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation." );
			rollBack( conn );
		}finally{
			connCleanUp( conn, ps, rs );
		}
		return false;
	}
	

	private String getTimeCorrection( ) {
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		String q = "SELECT s.setting_id, s.setting_m_epr, s.setting_core_epr, s.setting_time_correction FROM setting AS s";
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			
			ps = conn.prepareStatement( q );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				return null;
			}
				
			return rs.getString( "setting_time_correction" );
	
			

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return null;
	}

}
