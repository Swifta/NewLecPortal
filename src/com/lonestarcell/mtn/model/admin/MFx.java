package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnDetails;

public class MFx extends Model {

	private Logger log = LogManager.getLogger(MFx.class.getName());
	
	public MFx( Long d, String s ) {
		super( d,s );
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}

		log.debug( " Model initialized successfully." );


	}

	public Out setFxDetails( In in, OutTxnDetails outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		
		String q = "SELECT fx.id AS fx_id, fx.lsd_value AS fx_value, u.username AS fx_username, DATE_FORMAT( fx.timecreated, '%Y-%m-%j %T' ) AS fx_timestamp FROM exchange_rate as fx JOIN users as u ON u.user_id = fx.user_id ORDER BY fx.timecreated DESC LIMIT 1;";

		
		try {
			
	
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
	
			
			outTxn.getFxId().setValue( rs.getLong( "fx_id" )+"" );
			outTxn.getFxCreator().setValue( rs.getString( "fx_username" ) );
			outTxn.getFxValue().setValue( rs.getString( "fx_value" ) );
			outTxn.getFxTimestamp().setValue( rs.getString( "fx_timestamp" ) );
			
			
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
	
	
	public Out setNewFx ( In in, OutTxnDetails outTxn ) {

		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		out = new Out();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			 conn = dataSource.getConnection();
			// Fetch user id.
	 
			 String q = "SELECT u.user_id FROM users as u WHERE u.username = ? AND u.profile_id = 1 AND u.status = 1 LIMIT 1";
			
			ps = conn.prepareStatement( q );
			ps.setString( 1 , outTxn.getNewFxCreator().getValue() );
			
			log.debug( ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				out.setMsg( "Not authorised operation" );
				return out;
			}
			
			String userId = rs.getString( "user_id" );
			
			rs.close();
			ps.close();
			
			
			q =  "INSERT INTO exchange_rate( lsd_value, user_id ) VALUES (?,?)";
			
			ps = conn.prepareStatement( q );
			
			ps.setString( 1, outTxn.getNewFxValue().getValue().toString() );
			ps.setString( 2, userId );
			
			conn.setReadOnly( false );
			ps.execute();
			
			out = this.setFxDetails(in, outTxn);
			
			if( out.getStatusCode() == 1) {
				out.setMsg( "New rate set successfully." );
				outTxn.getNewFxValue().setValue( "" );
			}
			
				
			
				
		
		} catch (Exception e) {
				log.error(e.getMessage());
				out.setMsg( "Could not complete operation. " );
				e.printStackTrace();
				
		} finally{
				
				connCleanUp( conn, ps, rs );
		}
			
		
		return out;
	}
	
	
	
		
	
}
