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

public class MSettings extends Model {

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
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
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
	


	
	
	
		
	
}
