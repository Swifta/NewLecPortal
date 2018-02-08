package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutProfile;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class MSettings extends Model {
	
	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(MSettings.class.getName());
	
	private InSettings inTxn;
	
	public MSettings( Long d, String s ) {
		super( d, s );
		
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}

		log.debug( " Model initialized successfully." );


	}

	
	public Out setProfiles( In in ) {
		
		/*
		Out out = super.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}*/
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		String q = "SELECT p.profile_id, p.profile_name, p.profile_desc FROM profile AS p ORDER BY p.profile_id DESC";
		try {
			
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InSettings) bInData.getData();
			 
			// Authorization
			if( setAdminUserId( inTxn.getUsername(), inTxn.getUserSession() ).getStatusCode() != 1 ) {
				return out;
			}
				
				out = new Out(); 
			
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				out.setMsg( "No profiles");
				return out;
			}
			
			
			

			do {
				
				OutProfile outProfile = new OutProfile();
				outProfile.setProfileId( rs.getInt( "profile_id" ) );
				outProfile.setProfileName( rs.getString( "profile_name" ) );
				outProfile.setProfileDesc(  rs.getString( "profile_desc" ) );
				
				inTxn.getProfileContainer().addBean( outProfile );
				
			
			} while( rs.next() );
	
			out.setStatusCode( 1 );
			out.setMsg( "Data fetch successful." );
				
				
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out setConfig( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		String q = "SELECT s.setting_id, s.setting_m_epr, s.setting_core_epr, s.setting_time_correction FROM setting AS s";
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InSettings) bInData.getData();
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				out.setMsg( "No Configuration set");
				return out;
			}
			

			do {
				
				
				inTxn.getConfig().setId( rs.getInt( "setting_id" ) );
				inTxn.getConfig().setCoreEPR( rs.getString( "setting_core_epr" ) );
				inTxn.getConfig().setMediatorEPR(  rs.getString( "setting_m_epr" ) );
				inTxn.getConfig().setTimeCorrection(  rs.getString( "setting_time_correction" ) );
				
			
			} while( rs.next() );
	
			out.setStatusCode( 1 );
			out.setMsg( "Config. fetch successful." );
				
				
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
public Out updateConfig( In in ) {
		
		Out out = super.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
				try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly( false );
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InSettings) bInData.getData();
			 
			 
			String q = "UPDATE setting AS s";
				   q += " SET s.setting_m_epr = ?,";
				   q += " s.setting_core_epr = ?, ";
				   q += " s.setting_time_correction = ? ";
				   q += " WHERE s.setting_id = ?";

			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getConfig().getMediatorEPR() );
			ps.setString( 2, inTxn.getConfig().getCoreEPR() );
			ps.setString( 3, inTxn.getConfig().getTimeCorrection() );
			ps.setInt( 4, inTxn.getConfig().getId() );
			
			ps.executeUpdate();
			
	
			out.setStatusCode( 1 );
			out.setMsg( "Config. update successful." );
				
				
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}


@Override
protected Out checkAuthorization() {
	
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
		if( rs.getShort( "profile_id" ) != 1 && rs.getShort( "profile_id" ) != 3  ){
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
	


	
	
	
		
	
}
