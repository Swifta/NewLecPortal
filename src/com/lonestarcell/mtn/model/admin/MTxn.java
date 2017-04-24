package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.vaadin.data.util.BeanItemContainer;

public class MTxn extends Model {

	private Logger log = LogManager.getLogger(MTxn.class.getName());
	
	private OutTxn outTxn;
	private InTxn inTxn;
	
	public MTxn() {
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}

		log.debug( " Model initialized successfully." );


	}

	public Out getPaymentToday( In in ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "select distinct t.reference_no as swifta_id, t.fundamo_id as mmo_id, t.person_id as meter_no, t.payer_id as msisdn, m.token_generated as token, t.amount, t.status_code_id as pay_status, (CASE WHEN t.status_code_id = \"01\" THEN \"SMS SENT\" WHEN t.status_code_id = 04 THEN \"SMS FAILED\" WHEN t.status_code_id = 03 THEN \"SDP TIMEOUT\" WHEN t.status_code_id = 102 THEN \"SDP TIMEOUT\" ELSE \"UNKNOWN\" END ) as status_desc, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") as date  from transactions as t join msg_notification as m on m.transactionhistory_id = t.reference_no where (t.status_code_id = 01 or t.status_code_id = 04 or t.status_code_id = 03 or t.status_code_id = \"102\") and t.last_update > ? and t.last_update < ? order by t.last_update DESC limit 100";
		
		BeanItemContainer<OutTxn> txn = new BeanItemContainer<>(
				OutTxn.class);
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 String timeCorrection = " 23:13:59";
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection );
			ps.setString( 2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				outTxn = new OutTxn();
				txn.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( txn );
				
				out.setData( bOutData );
				return out;
			}
			
			
			

			do {
				
				outTxn = new OutTxn();
		
				
				outTxn.setSwiftaId( rs.getLong( "swifta_id" )+"" );
				outTxn.setMmoId( rs.getString( "mmo_id" ) );
				outTxn.setMsisdn( rs.getString( "msisdn" ) );
				outTxn.setMeterNo( rs.getString( "meter_no" ) );
				outTxn.setAmount( rs.getDouble( "amount" ) );
				outTxn.setReqCurrency( rs.getString( "req_currency" ) );
				outTxn.setToken( rs.getString( "token" ) );
				//outTxn.setPayStatus( rs.getString( "pay_status" ) );
				outTxn.setStatusDesc( rs.getString( "status_desc" ) );
				outTxn.setDate( rs.getString( "date" ) );
				
				txn.addBean( outTxn );
				
			
			} while( rs.next() );
			

			
			
			BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
			bOutData.setData( txn );
			
			out.setData( bOutData );
	
			
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
	
	
	public Out getTxnToday( In in ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "select DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, t.person_id as meter_no, t.amount, t.fundamo_id as mmo_id, t.payer_id as msisdn, status_code_id as pay_status, t.reference_no as swifta_id, fx.lsd_value as rate   from transactions as t left join exchange_rate as fx on fx.id = t.rate_id where t.last_update > ? and t.last_update < ? order by t.last_update DESC";

		BeanItemContainer<OutTxn> txn = new BeanItemContainer<>(
				OutTxn.class);
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 
			 // TODO Delete Test Data
			 String timeCorrection = " 23:13:59";
			 inTxn.setfDate( "2017-01-01" );
			 inTxn.settDate( "2017-01-14" );
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection );
			ps.setString( 2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				outTxn = new OutTxn();
				txn.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( txn );
				
				out.setData( bOutData );
				return out;
			}
			
			
			

			do {
				
				outTxn = new OutTxn();
		
				
				outTxn.setSwiftaId( rs.getLong( "swifta_id" )+"" );
				outTxn.setMmoId( rs.getString( "mmo_id" ) );
				outTxn.setMsisdn( rs.getString( "msisdn" ) );
				outTxn.setMeterNo( rs.getString( "meter_no" ) );
				outTxn.setAmount( rs.getDouble( "amount" ) );
				//outTxn.setReqCurrency( rs.getString( "req_currency" ) );
				//outTxn.setToken( rs.getString( "token" ) );
				outTxn.setPayStatus( rs.getString( "pay_status" ) );
				outTxn.setRate( rs.getDouble( "rate" ) );
			
				outTxn.setDate( rs.getString( "date" ) );
				
				txn.addBean( outTxn );
				
			
			} while( rs.next() );
			

			
			
			BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
			bOutData.setData( txn );
			
			out.setData( bOutData );
	
			
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
	
	
public Out getTokenToday( In in ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "SELECT reference_no as itron_id, meter_no, transaction_type, status as token_status, DATE_FORMAT( last_update, '%Y-%m-%j %T' ) as date, transaction_id as swifta_id, ( repCount + 1 ) as req_count, amount FROM afrinnewdb.vend_req where last_update > ? and last_update < ? order by last_update DESC;";
		
		BeanItemContainer<OutTxn> txn = new BeanItemContainer<>(
				OutTxn.class);
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 
			 // TODO Delete Test Data
			 String timeCorrection = " 23:13:59";
			 inTxn.setfDate( "2017-01-18" );
			 inTxn.settDate( "2017-01-19" );
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection );
			ps.setString( 2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				outTxn = new OutTxn();
				txn.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( txn );
				
				out.setData( bOutData );
				return out;
			}
			
			
			

			do {
				
				outTxn = new OutTxn();
		
				
				outTxn.setSwiftaId( rs.getLong( "swifta_id" )+"" );
				outTxn.setItronId( rs.getLong("itron_id")+"" );
				outTxn.setMeterNo( rs.getString( "meter_no" ) );
				outTxn.setAmount( rs.getDouble( "amount" ) );
				outTxn.setReqCount( rs.getString( "req_count" ) );
				outTxn.setTokenStatus( rs.getString( "token_status" ) );
				outTxn.setTxnType( rs.getString( "transaction_type" ) );
				//outTxn.setRate( rs.getDouble( "rate" ) );
			
				outTxn.setDate( rs.getString( "date" ) );
				
				txn.addBean( outTxn );
				
			
			} while( rs.next() );
			

			
			
			BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
			bOutData.setData( txn );
			
			out.setData( bOutData );
	
			
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
