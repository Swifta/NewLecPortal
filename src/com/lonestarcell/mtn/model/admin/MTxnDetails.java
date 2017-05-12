package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxnDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnDetails;

public class MTxnDetails extends Model {

	private Logger log = LogManager.getLogger(MTxnDetails.class.getName());
	
	private InTxnDetails inTxn;
	
	public MTxnDetails( Long d, String s) {
		super( d, s);
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}

		log.debug( " Model initialized successfully." );


	}

	public Out setTxnDetails( In in, OutTxnDetails outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		out = new Out();
		
		String q = "SELECT t.reference_no as swifta_id, t.fundamo_id as mo_id, v.reference_no as token_id,"
			+ " m.id as sms_id, DATE_FORMAT( t.last_update, '%Y-%m-%j %T' )  as date_end, DATE_FORMAT( v.date_created, '%Y-%m-%j %T' ) as token_date_create, "
			+ "  DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) as token_date_end, DATE_FORMAT( m.datecreated, '%Y-%m-%j %T' ) as sms_date_end, t.status_code_id as pay_status,"
			+ " v.status as token_status, m.status as sms_status, t.amount, v.amount as itron_amount, t.person_id as meter_no,"
			+ " t.payer_id as msisdn, t.rate_id, fx.lsd_value as rate, m.units, m.token_generated as token"
			+ " FROM transactions AS t "
			+ " LEFT JOIN msg_notification AS m on m.transactionhistory_id = t.reference_no "
			+ " LEFT JOIN vend_req AS v ON v.transaction_id = t.reference_no"
			+ " LEFT JOIN exchange_rate AS fx ON fx.id = t.rate_id"
			+ " WHERE"
			+ " t.reference_no = ? LIMIT 1";

		
		try {
			
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxnDetails) bInData.getData();
			 
			
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setLong( 1, Long.parseLong( inTxn.getSwiftaId() ) );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
	
			
			outTxn.getSwiftaId().setValue( rs.getLong( "swifta_id" )+"" );
			outTxn.getMmoId().setValue( rs.getString( "mo_id" ) );
			outTxn.getTokenId().setValue( rs.getString( "token_id" ) );
			outTxn.getSmsId().setValue( rs.getString( "sms_id" ) );
			
			outTxn.getMsisdn().setValue( rs.getString( "msisdn" ) );
			outTxn.getMeterNo().setValue( rs.getString( "meter_no" ) );
			outTxn.getAmount().setValue( rs.getDouble( "amount" )+"" );
			outTxn.getItronAmount().setValue( rs.getDouble( "itron_amount" )+"" );
			log.debug( outTxn.getAmount().getValue() );
			
			outTxn.getRateId().setValue( rs.getLong( "rate_id" )+"" );
			outTxn.getRate().setValue( rs.getFloat( "rate" )+"" );
		
			outTxn.getToken().setValue( rs.getString( "token" ) );
			outTxn.getUnits().setValue( rs.getString( "units" ) );
			
			outTxn.getPayStatus().setValue( rs.getString( "pay_status" ) );
			outTxn.getTokenStatus().setValue( rs.getString( "token_status" ) );
			outTxn.getSmsStatus().setValue( rs.getString( "sms_status" ) );
			
			outTxn.getTokenDateCreate().setValue( rs.getString( "token_date_create" ) );
			//outTxn.setTxnDateCreate( rs.getString( "date_end" ) );
			//outTxn.setSmsDateCreate( rs.getString( "sms_date_create" ) );
			
			outTxn.getTokenDateEnd().setValue( rs.getString( "token_date_end" ) );
			outTxn.getTxnDateEnd().setValue( rs.getString( "date_end" ) );
			outTxn.getSmsDateEnd().setValue( rs.getString( "sms_date_end" ) );
			
			outTxn = getVerifDetails( inTxn, outTxn );
			
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
	
	private OutTxnDetails getVerifDetails( InTxnDetails inTxn, OutTxnDetails outTxn ) {
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String q = "SELECT h.reference_no as verif_id, h.status as verif_status,"
				  + " DATE_FORMAT( h.date_created, '%Y-%m-%j %T' ) as verif_date_create,"
				  + " DATE_FORMAT( h.last_update, '%Y-%m-%j %T' ) as verif_date_end FROM transaction_history AS h"
				  + " WHERE h.transaction_id = ? AND h.transaction_type = 'CUSTINFOREQ' LIMIT 1;";

		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			ps = conn.prepareStatement( q );
			ps.setLong( 1, Long.parseLong( inTxn.getSwiftaId() ) );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				
				return outTxn;
			}
			
			outTxn.getVerifId().setValue( rs.getLong( "verif_id" )+"" );
			outTxn.getVerifStatus().setValue( rs.getString( "verif_status" ) );
			outTxn.getVerifDateCreate().setValue( rs.getString( "verif_date_create" ) );
			outTxn.getVerifDateEnd().setValue( rs.getString( "verif_date_end" ) );
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return outTxn;
	}
	
	
	
	
	
		
	
}
