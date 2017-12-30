package com.lonestarcell.mtn.model.admin;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.vaadin.data.Item;

public class MUserDetails extends Model {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger(MUserDetails.class.getName());
	
	private InUserDetails inUser;
	
	public MUserDetails( Long id, String session ) {
		super( id, session );
		
		
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}

		log.debug( " Model initialized successfully." );


	}
	

	@SuppressWarnings("unchecked")
	public Out setUserDetails( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		out = new Out();
		
		String q = "SELECT o.domain_id AS org_domain, o.organization_status AS org_status, u.firstname, u.lastname, u.surname, DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u";
		q += " JOIN organization AS o ON o.id = u.organization_id";
		q += " JOIN profile AS p ON p.profile_id = u.profile_id";
		q += " WHERE u.username = ?";
		q += " ORDER BY u.date_added, u.last_login DESC";
		q += " LIMIT 1;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out(); 
			
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, ( String )inUser.getRecord().getItemProperty( "username" ).getValue() );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No user records." );
				return out;
			}
			
		
		
			inUser.getRecord().getItemProperty( "userId" ).setValue( rs.getLong( "user_id" ) );
			inUser.getRecord().getItemProperty( "username" ).setValue( rs.getString( "username" ) );
			inUser.getRecord().getItemProperty( "userSession" ).setValue( rs.getString( "user_session" ) );
			inUser.getRecord().getItemProperty( "userStatus" ).setValue( rs.getString( "status" ) );
			inUser.getRecord().getItemProperty( "changePass" ).setValue( rs.getShort( "change_password" ) );
			inUser.getRecord().getItemProperty( "dateAdded" ).setValue( rs.getString( "date_added" ) );
			inUser.getRecord().getItemProperty( "lastLogin" ).setValue( rs.getString( "last_login" ) );
			inUser.getRecord().getItemProperty( "org" ).setValue( rs.getString( "org" ) );
			inUser.getRecord().getItemProperty( "profile" ).setValue( rs.getString( "profile_name" ) );
			inUser.getRecord().getItemProperty( "profileId" ).setValue( rs.getInt( "profile_id" ) );
			inUser.getRecord().getItemProperty( "email" ).setValue( rs.getString( "email" )  );
			inUser.getRecord().getItemProperty( "firstName" ).setValue( rs.getString( "firstname" )  );
			inUser.getRecord().getItemProperty( "lastName" ).setValue( rs.getString( "lastname" )  );
			inUser.getRecord().getItemProperty( "surname" ).setValue( rs.getString( "surname" )  );
			
			inUser.getRecord().getItemProperty( "orgStatus" ).setValue( rs.getString( "org_status" )  );
			inUser.getRecord().getItemProperty( "orgDomain" ).setValue( rs.getString( "org_domain" )  );
			
	
			out.setStatusCode( 1 );
			out.setMsg( "User details set successfully." );
				
				
			
			
			

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out addNewUser( In in ) {
		
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		out = new Out();
		String q = "INSERT INTO users ";
			   q += "( organization_id, username, email, password, firstname, lastname, surname, ";
			   q += "added_by, change_password, profile_id, last_login, user_session, pass_salt ";
		   	   q += ") ";
		   	   q += "VALUES ";
		   	   q += "(?,?,?,?,?,?,?,";
		   	   q += "?,?,?,?,?,? ";
		   	   q += ");";
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly( false );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// TODO Check for authorization. This will be moved to Super class
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			Long authId = Long.valueOf( out.getData().getData().toString() );
			
			// Re-validate email
			if( this.checkEmailUnique( in ).getStatusCode( ) != 1 ) {
				return out;
			}
			
			// Re-validate username
			if( this.checkUsernameUnique( in ).getStatusCode( ) != 1 ) {
				return out;
			}
			
			out = new Out();
			
			ps = conn.prepareStatement( q );
			
			ps.setInt( 1, 1 );
			ps.setString( 2, inUser.getRecord().getItemProperty( "username" ).getValue().toString()  );
			ps.setString( 3, inUser.getRecord().getItemProperty( "email" ).getValue().toString()  );
			
			
			ps.setString( 5, inUser.getRecord().getItemProperty( "newFirstName" ).getValue().toString()  );
			ps.setString( 6, inUser.getRecord().getItemProperty( "newLastName" ).getValue().toString()  );
			ps.setString( 7, inUser.getRecord().getItemProperty( "newSurname" ).getValue().toString()  );
			ps.setLong( 8,  authId );
			ps.setShort( 9,  Short.valueOf( inUser.getRecord().getItemProperty( "changePass" ).getValue().toString() )  );
			ps.setShort( 10, Short.valueOf( inUser.getRecord().getItemProperty( "profileId" ).getValue().toString()  ) );
			
			// Last login as NULL
			ps.setString( 11, null  );
			// User session as NULL
			ps.setString( 12, null  );
			
			// Hash password
			ps.setString( 4, this.generatePassHash( inUser.getRecord() ) );
			
			// Set generated pass salt after generatePassHash call
			ps.setString( 13, inUser.getRecord().getItemProperty( "passSalt" ).getValue().toString()  );
			
			
			log.debug( "Query: "+ps.toString() );
			
			ps.execute();
	
			out.setStatusCode( 1 );
			out.setMsg( "User added successfully." );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out checkUsernameUnique( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.username = ? AND u.username != ?";
		q += " LIMIT 1;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out();
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			ps.setString( 2, inUser.getRecord().getItemProperty( "newUsername" ).getValue().toString() );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "Username is unique" );
				out.setStatusCode( 1 );
				
				return out;
			}
		
			log.debug( "Username already taken." );
			out.setMsg( "Username already taken." );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}


	public Out checkEmailUnique( In in ) {
		
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		
		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.email = ? AND u.email != ?";
		q += " LIMIT 1;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out();
			 
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getRecord().getItemProperty( "email" ).getValue().toString() );
			ps.setString( 2, inUser.getRecord().getItemProperty( "newEmail" ).getValue().toString() );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "Email is unique" );
				out.setStatusCode( 1 );
				
				return out;
			}
		
			out.setMsg( "Email already used." );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	

	
	
	
	
	@SuppressWarnings("unchecked")
	private String generatePassHash( Item record ){
		record.getItemProperty( "passSalt" ).setValue( sha256Hex( nextSessionId() ) );
		return sha256Hex( record.getItemProperty( "password" ).getValue().toString()+record.getItemProperty( "passSalt" ).getValue().toString() );
	}
	
	private String nextSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	

	
	public Out expireSession( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.username = ?";
		q += " LIMIT 1;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly( true );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out(); 
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No session data" );
				return out;
			}
			
			rs.close();
			ps.close();
			
			conn.setReadOnly( false );
			
			q = "UPDATE users AS u";
			q += " SET u.user_session = ?";
			q += " WHERE u.username = ?";
			
			ps = conn.prepareStatement( q );
			ps.setString( 1, null );
			ps.setString( 2, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();

		
			out.setMsg( "Session expired successfully." );
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out expirePass( In in ) {
		
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.username = ?";
		q += " LIMIT 1;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly( true );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out(); 
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No cred. data" );
				return out;
			}
			
			rs.close();
			ps.close();
			
			conn.setReadOnly( false );
			
			q = "UPDATE users AS u";
			q += " SET u.change_password = ?, u.user_session = ?";
			q += " WHERE u.username = ?";
			
			ps = conn.prepareStatement( q );
			ps.setInt( 1, 1 );
			ps.setString( 2, null );
			ps.setString( 3, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();

		
			out.setMsg( "Password expired successfully." );
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	public Out blockUser( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.username = ?";
		q += " LIMIT 1;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly( true );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out(); 
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No user data" );
				return out;
			}
			
			rs.close();
			ps.close();
			
			conn.setReadOnly( false );
			
			q = "UPDATE users AS u";
			q += " SET u.status = ?, u.user_session = ?";
			q += " WHERE u.username = ?";
			
			ps = conn.prepareStatement( q );
			ps.setInt( 1, 2 );
			ps.setString( 2, null );
			ps.setString( 3, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();

		
			out.setMsg( "User blocked successfully." );
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}

	
	public Out activateUser( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.username = ?";
		q += " LIMIT 1;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly( true );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out(); 
			
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No user data" );
				return out;
			}
			
			rs.close();
			ps.close();
			
			conn.setReadOnly( false );
			
			q = "UPDATE users AS u";
			q += " SET u.status = ?, u.user_session = ?";
			q += " WHERE u.username = ?";
			
			ps = conn.prepareStatement( q );
			ps.setInt( 1, 1 );
			ps.setString( 2, null );
			ps.setString( 3, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();

		
			out.setMsg( "User activated successfully." );
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public Out resetUserPassAdmin( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "UPDATE users AS u";
		q += " SET u.status = 1, u.change_password = ?, u.user_session = ?, u.password = ?, u.pass_salt = ?";
		q += " WHERE u.user_id = ?";
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly( false );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out(); 
			 
			 inUser.getRecord().getItemProperty( "username" ).setValue( inUser.getRecord().getItemProperty( "newUsername" ).getValue() );
			 Long userId = this.getUserId( in );
			 
			 if( userId == null || userId == 0 ) {
				 log.debug( "No user data" );
				 out.setMsg( "No user data." );
				 return out;
			 }
			 
			 String newPassword = inUser.getRecord().getItemProperty( "newPassword" ).getValue().toString();
				
			 // This is necessary for generatePassHash to base on newPassword other than current password
			 inUser.getRecord().getItemProperty( "password" ).setValue( newPassword );
			
			ps = conn.prepareStatement( q );
			ps.setInt( 1, 1 );
			ps.setString( 2, null );
			ps.setString( 3, this.generatePassHash( inUser.getRecord() ) );
			ps.setString( 4, inUser.getRecord().getItemProperty( "passSalt" ).getValue().toString() );
			ps.setLong( 5, userId );
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();
			
			inUser.getRecord().getItemProperty( "password" ).setValue( null );
			inUser.getRecord().getItemProperty( "newPassword" ).setValue( null );
			inUser.getRecord().getItemProperty( "passSalt" ).setValue( null );
			
			out.setMsg( "User password reset successfully." );
			
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	
	
	public Out resetUserProfileAdmin( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "UPDATE users AS u";
		q += " SET u.user_session = ?, u.profile_id = ?";
		q += " WHERE u.user_id = ?";
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly( false );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			out = new Out(); 
			 
			Long userId = this.getUserId( in );
			 
			 if( userId == null || userId == 0 ) {
				 log.debug( "No user data" );
				 out.setMsg( "No user data." );
				 return out;
			 }
			 
			
			ps = conn.prepareStatement( q );
			ps.setString( 1, null );
			ps.setInt( 2, (int)inUser.getRecord().getItemProperty( "profileId" ).getValue() );
			ps.setLong( 3, userId );
			
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();
			
			
			out.setMsg( "User profile reset successfully." );
			
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	@SuppressWarnings("unchecked")
	public Out resetUserCreds( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			
	

		
		String q = "UPDATE users AS u";
		q += " SET u.change_password = ?, u.user_session = ?, u.username = ?, u.password = ?, u.pass_salt = ?, u.profile_id = ?";
		q += " WHERE u.user_id = ?";
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly( false );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				log.debug( "Login session: "+inUser.getUserSession() );
				return out;
			}
			
			 Long authId = Long.valueOf( out.getData().getData().toString() );
			
			 if( authId == null || authId == 0 ) {
				 log.debug( "No user data - user id" );
				 out.setMsg( "No user data." );
				 return out;
			 }
			 
			// Validate current creds before reset
			if( validUserCurrentCreds( in ).getStatusCode() != 1 ){
				log.debug( "Returning from validUserCurrentCreds" );
				return out;
			} else {
				log.debug( "validUserCurrentCreds passed. Continuing to validate username." );
			}
			
			// Re-validate username
			if( this.checkUsernameUnique( in ).getStatusCode( ) != 1 ) {
				log.debug( "Returning from checkUsernameUnique" );
				return out;
			} else {
				log.debug( "checkUsernameUnique passed. Continuing to get user id" );
			}
			
			
			 
			 out = new Out();
				
			 
			 
			 
			 String session = nextSessionId();
			 String newUsername = inUser.getRecord().getItemProperty( "newUsername" ).getValue().toString();
			 String newPassword = inUser.getRecord().getItemProperty( "newPassword" ).getValue().toString();
			
			 // This is necessary for generatePassHash to base on newPassword other than current password
			 inUser.getRecord().getItemProperty( "password" ).setValue( newPassword );
			
			ps = conn.prepareStatement( q );
			ps.setInt( 1, 0 );
			ps.setString( 2, session );
			ps.setString( 3,  newUsername );
			ps.setString( 4, this.generatePassHash( inUser.getRecord() ) );
			ps.setString( 5, inUser.getRecord().getItemProperty( "passSalt" ).getValue().toString() );
			ps.setInt( 6, Integer.valueOf( inUser.getRecord().getItemProperty( "profileId" ).getValue().toString() ) );
			ps.setLong( 7, authId );
			log.debug( "Query: "+ps.toString() );
			ps.executeUpdate();
			
			// Set creds and form values to anew
			inUser.setUserSession( session );
			inUser.setUsername( newUsername );
			inUser.getRecord().getItemProperty( "username" ).setValue( newUsername );
			inUser.getRecord().getItemProperty( "userId" ).setValue( authId );
	
			inUser.getRecord().getItemProperty( "password" ).setValue( null );
			inUser.getRecord().getItemProperty( "newPassword" ).setValue( null );
			inUser.getRecord().getItemProperty( "passSalt" ).setValue( null );
			
			

		
			out.setMsg( "User creds reset successfully." );
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Out updateUserPersonalInfo( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		String q = "UPDATE users AS u";
		q += " SET u.firstname = ?, u.lastname = ?, u.surname = ?, u.email = ?";
		q += " WHERE u.user_id = ?";
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly( false );
			 
			 BData< ? > bInData = in.getData();
			 inUser = (InUserDetails ) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			// Re-validate email
			if( this.checkEmailUnique( in ).getStatusCode( ) != 1 ) {
				return out;
			}
			
			out = new Out(); 
			 
			 Long userId = this.getUserId( in );
			 
			 if( userId == null || userId == 0 ) {
				 log.debug( "No user data" );
				 out.setMsg( "No user data." );
				 return out;
			 }
			 
			 
			
			 String newFirstName = inUser.getRecord().getItemProperty( "newFirstName" ).getValue().toString();
			 String newLastName = inUser.getRecord().getItemProperty( "newLastName" ).getValue().toString();
			 String newSurname = inUser.getRecord().getItemProperty( "newSurname" ).getValue().toString();
			 String newEmail = inUser.getRecord().getItemProperty( "newEmail" ).getValue().toString();
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1,  newFirstName );
			ps.setString( 2, newLastName  );
			ps.setString( 3, newSurname );
			ps.setString( 4, newEmail );
			ps.setLong( 5, userId );
			
			ps.executeUpdate();
			
			// Set form values to anew
			
	
			inUser.getRecord().getItemProperty( "newFirstName" ).setValue( null );
			inUser.getRecord().getItemProperty( "newLastName" ).setValue( null );
			inUser.getRecord().getItemProperty( "newSurname" ).setValue( null );
			inUser.getRecord().getItemProperty( "newEmail" ).setValue( null );
			
			inUser.getRecord().getItemProperty( "firstName" ).setValue( newFirstName );
			inUser.getRecord().getItemProperty( "lastName" ).setValue( newLastName );
			inUser.getRecord().getItemProperty( "surname" ).setValue( newSurname );
			inUser.getRecord().getItemProperty( "email" ).setValue( newEmail );
			
			
			
			log.debug( "Query: "+ps.toString() );

		
			out.setMsg( "Personal info updated successfully." );
			out.setStatusCode( 1 );

		} catch (Exception e) {
			log.error( e.getMessage() );
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Out validUserCurrentCreds( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		
		out = new Out();
		
		String q = "SELECT user_id, password, pass_salt, status, change_password,  profile_id  from users WHERE username = ? LIMIT 1";
		
		try {
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			BData< ? > bInData = in.getData();
			inUser = ( InUserDetails ) bInData.getData();
			
			log.debug( "Authorization prior." );
			
			// Authorization
			if( setAdminUserId( inUser.getUsername(), inUser.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
			
			log.debug( "Authorization passed." );
			
			out = new Out(); 
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getUsername()  );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No user data." );
				return out;
			}
			
			String passHash = sha256Hex( inUser.getRecord().getItemProperty( "password" ).getValue().toString()+rs.getString( "pass_salt" ) );
		
			if( passHash.equals( rs.getString( "password" ) ) ){
				
				inUser.getRecord().getItemProperty( "password" ).setValue( null );
				log.debug( "Valid creds." );
				
				out.setStatusCode( 1 );
				out.setMsg( "Valid creds." );
				
			} else {
				out.setStatusCode( 100 );
				log.debug( "Invalid current password." );
				out.setMsg( "Invalid current password." );
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
	
	
	
	private Long getUserId ( In in ) {
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.username = ?";
		q += " LIMIT 1;";
		
		try {
			
			conn = dataSource.getConnection();
			conn.setReadOnly( true );
		 
			BData< ? > bInData = in.getData();
			inUser = (InUserDetails ) bInData.getData();
			
			ps = conn.prepareStatement( q );
			ps.setString( 1, inUser.getRecord().getItemProperty( "username" ).getValue().toString() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				return null;
			}
			
			
			return rs.getLong( "user_id" );
			
			

		} catch (Exception e) {
			log.error( e.getMessage() );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		
		
		return null;
	}
	


	
	
		
	
}
