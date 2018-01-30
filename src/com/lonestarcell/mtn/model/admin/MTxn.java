package com.lonestarcell.mtn.model.admin;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MTxn extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(MTxn.class.getName());
	
	private OutTxn outTxn;
	private InTxn inTxn;
	
	public MTxn(){
		
	}
	public MTxn( Long d, String s, String t ) {
		super( d, s );
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}
		this.timeCorrection = " "+t;
		log.debug( " Model initialized successfully." );


	}

	public Out getPaymentToday( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "select distinct t.reference_no as swifta_id, t.fundamo_id as mmo_id, t.person_id as meter_no, t.payer_id as msisdn, m.token_generated as token, t.amount, t.status_code_id as pay_status, (CASE WHEN t.status_code_id = \"01\" THEN \"SMS SENT\" WHEN t.status_code_id = 04 THEN \"SMS FAILED\" WHEN t.status_code_id = 03 THEN \"SDP TIMEOUT\" WHEN t.status_code_id = 102 THEN \"SDP TIMEOUT\" ELSE \"UNKNOWN\" END ) as status_desc, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") as date  from transactions as t join msg_notification as m on m.transactionhistory_id = t.reference_no where (t.status_code_id = 01 or t.status_code_id = 04 or t.status_code_id = 03 or t.status_code_id = \"102\") and t.last_update > ? and t.last_update < ? order by t.last_update DESC limit 10";
		
		BeanItemContainer<OutTxn> txn = new BeanItemContainer<>(
				OutTxn.class);
		
		try {
			
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			// String timeCorrection = " 23:13:59";
			 
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
	
	
	
	public Out setPaymentToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		// String q = "select distinct t.reference_no as swifta_id, t.fundamo_id as mmo_id, t.person_id as meter_no, t.payer_id as msisdn, m.token_generated as token, t.amount, t.status_code_id as pay_status, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") as date  from transactions as t join msg_notification as m on m.transactionhistory_id = t.reference_no where (t.status_code_id = 01 or t.status_code_id = 04 or t.status_code_id = 03 or t.status_code_id = \"102\") and t.last_update > ? and t.last_update < ? order by t.last_update DESC LIMIT ?, ?";
		
		
		
		try {
			
			
			String q = "SELECT DISTINCT t.reference_no AS swifta_id, t.fundamo_id AS mmo_id, t.person_id AS meter_no, t.payer_id AS msisdn, m.token_generated AS token, t.amount, t.status_code_id AS pay_status, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") AS date  FROM transactions AS t JOIN msg_notification AS m on m.transactionhistory_id = t.reference_no";
				   q += " WHERE (t.status_code_id = 01 OR t.status_code_id = 04 OR t.status_code_id = 03 OR t.status_code_id = \"102\") AND ";
				   
				   
				   
				   q += " t.last_update > ? AND t.last_update < ? ORDER BY t.last_update DESC LIMIT ?, ?";

			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			 // String timeCorrection = " 23:13:59";
			 // inTxn.setfDate( "2017-01-01" );
			 // inTxn.settDate( "2017-01-14" );
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection );
			ps.setString( 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( 3, pageMin );
			ps.setInt(4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				outTxn = new OutTxn();
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				
				outTxn.setPayStatus( rs.getString( "pay_status" ) );
				//outTxn.setRate( rs.getDouble( "rate" ) );
				outTxn.setDate( rs.getString( "date" ) );
				
				String payStatus = rs.getString( "pay_status" );
				String statusDesc = "FAILED";
				if( payStatus.equals( "04" ) 
						||  payStatus.equals( "01" ) ) {
					statusDesc = "COMPLETE";
					
				} else if( payStatus.equals( "03" ) 
						|| payStatus.equals( "102" ) ) {
					statusDesc = "PENDING SDP";
				} else if(payStatus.equals( "0" ) 
						|| payStatus.equals( "02" )){
					statusDesc = "PENDING";
					
				}
				
				outTxn.setStatusDesc( statusDesc );
				
				container.addBean( outTxn );
				
			
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
	

	public Out searchPaymentToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			 
			BData< ? > bInData = in.getData();
			inTxn = (InTxn) bInData.getData();
			 
			Map<String, Integer > searchFieldMap = new HashMap<>();
			
			String q = "SELECT DISTINCT t.reference_no AS swifta_id, t.fundamo_id AS mmo_id, t.person_id AS meter_no, t.payer_id AS msisdn, m.token_generated AS token, t.amount, t.status_code_id AS pay_status, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") AS date  FROM transactions AS t JOIN msg_notification AS m on m.transactionhistory_id = t.reference_no";
				   q += " WHERE (t.status_code_id = 01 OR t.status_code_id = 04 OR t.status_code_id = 03 OR t.status_code_id = \"102\") AND ";
				   
				   
				   if( inTxn.getSearchSID() != null ) {
					   q += " t.reference_no LIKE ? AND";
					   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMoID() != null ) {
					   q += " t.fundamo_id LIKE ? AND";
					   searchFieldMap.put( "moID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMSISDN() != null ) {
					   q += " t.payer_id LIKE ? AND";
					   searchFieldMap.put( "msisdn",searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMeterNo() != null ) {
					   q += " t.person_id LIKE ? AND";
					   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchToken() != null ) {
					   q += " m.token_generated LIKE ? AND";
					   searchFieldMap.put( "token", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchReqCur() != null ) {
					   
					   if( "USD".contains( inTxn.getSearchReqCur().toUpperCase() ) ){
						   q += " t.rate_id = 0 AND";
					   } else if( "LRD".contains( inTxn.getSearchReqCur().toUpperCase() ) ){
						   q += " t.rate_id != 0 AND";
					   }else {
						   // Dummy rate id for no match
						   q += " ( t.rate_id = '-1' ) ";
						   q += " AND";
					   }
					   
				   }
				   
				   if( inTxn.getSearchStatusDesc() != null ) {
					   
					   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
					   
					   if( "COMPLETE".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
						   
						   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
						   q += " ( t.status_code_id = '04' ";
						   q += " OR  t.status_code_id = '01' ";  
						   q += " )";
						   q += " AND";
					   } else if(  "PENDING SDP".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
						   
						   q += " ( t.status_code_id = '03' ";
						   q += " OR  t.status_code_id = '102' ";  
						   q += " )";
						   q += " AND";
					   } else {
						   
						   // Dummy status for no match
						   q += " ( t.status_code_id = '404' ) ";
						   q += " AND";
					   }
					   
				   }
				   
				 q += " t.last_update > ? AND t.last_update < ? ORDER BY t.last_update DESC LIMIT ?, ?";

			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchMoID() != null ){
				ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchMSISDN() != null ){
				ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
			}
			
			if( inTxn.getSearchToken() != null ){
				ps.setString(  searchFieldMap.get( "token" ), "%"+inTxn.getSearchToken()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( paramIndexOffset + 3, pageMin );
			ps.setInt( paramIndexOffset + 4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No records found." );
				
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
				
				outTxn.setPayStatus( rs.getString( "pay_status" ) );
				//outTxn.setRate( rs.getDouble( "rate" ) );
				outTxn.setDate( rs.getString( "date" ) );
				
				String payStatus = rs.getString( "pay_status" );
				String statusDesc = "FAILED";
				if( payStatus.equals( "04" ) 
						||  payStatus.equals( "01" ) ) {
					statusDesc = "COMPLETE";
					
				} else if( payStatus.equals( "03" ) 
						|| payStatus.equals( "102" ) ) {
					statusDesc = "PENDING SDP";
				} else if(payStatus.equals( "0" ) 
						|| payStatus.equals( "02" )){
					statusDesc = "PENDING";
					
				}
				
				outTxn.setStatusDesc( statusDesc );
				
				container.addBean( outTxn );
				
			
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
	
	
	public Out setTxnToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		String q = "select DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, t.person_id as meter_no, t.amount, t.fundamo_id as mmo_id, t.payer_id as msisdn, status_code_id as pay_status, t.reference_no as swifta_id, fx.lsd_value as rate   from transactions as t left join exchange_rate as fx on fx.id = t.rate_id where t.last_update > ? and t.last_update < ? order by t.last_update DESC LIMIT ?, ?";

		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			//  String timeCorrection = " 23:13:59";
			 // inTxn.setfDate( "2017-01-01" );
			 // inTxn.settDate( "2017-01-14" );
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection );
			ps.setString( 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( 3, pageMin );
			ps.setInt(4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				
				String payStatus = rs.getString( "pay_status" );
				String statusDesc = "FAILED";
				if( payStatus.equals( "04" ) 
						||  payStatus.equals( "01" ) ) {
					statusDesc = "COMPLETE";
					
				} else if( payStatus.equals( "03" ) 
						|| payStatus.equals( "102" ) ) {
					statusDesc = "PENDING SDP";
				} else if(payStatus.equals( "0" ) 
						|| payStatus.equals( "02" )){
					statusDesc = "PENDING";
					
				}
				
				outTxn.setStatusDesc( statusDesc );
				
				container.addBean( outTxn );
				
			
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
	
	
	
	public Out searchTxnToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		out = new Out();
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 
		 
			String q = "SELECT DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) AS date, t.person_id AS meter_no,";
			   q += " t.amount, t.fundamo_id AS mmo_id, t.payer_id AS msisdn, status_code_id AS pay_status,";
			   q += " t.reference_no AS swifta_id, fx.lsd_value AS rate";   
			   q += " FROM transactions AS t LEFT JOIN exchange_rate AS fx on fx.id = t.rate_id";
			   q += " WHERE";
			   
			   if( inTxn.getSearchSID() != null ) {
				   q += " t.reference_no LIKE ? AND";
				   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchMoID() != null ) {
				   q += " t.fundamo_id LIKE ? AND";
				   searchFieldMap.put( "moID", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchMSISDN() != null ) {
				   q += " t.payer_id LIKE ? AND";
				   searchFieldMap.put( "msisdn",searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchMeterNo() != null ) {
				   q += " t.person_id LIKE ? AND";
				   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchStatusDesc() != null ) {
				   
				   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
				   
				   if(  "FAILED".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   q += " ( t.status_code_id = '403' ";
					   q += " OR  t.status_code_id = '100' ";  
					   q += " OR  t.status_code_id = '2100' ";  
					   q += " OR  t.status_code_id = '1100' ";  
					   q += " OR  t.status_code_id = '3100' ";  
					   q += " )";
					   q += " AND";
					   
				   } else if( "COMPLETE".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
					   q += " ( t.status_code_id = '04' ";
					   q += " OR  t.status_code_id = '01' ";  
					   q += " )";
					   q += " AND";
				   } else if(  "PENDING SDP".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   q += " ( t.status_code_id = '03' ";
					   q += " OR  t.status_code_id = '102' ";  
					   q += " )";
					   q += " AND";
				   } else if(  "PENDING".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   q += " ( t.status_code_id = '0' ";
					   q += " OR  t.status_code_id = '02' ";  
					   q += " )";
					   q += " AND";
				   } else {
					   
					   // Dummy status for no match
					   q += " ( t.status_code_id = '404' ) ";
					   q += " AND";
				   }
				   
			   }
			   
			 q += " t.last_update > ? AND t.last_update < ? ORDER BY t.last_update DESC LIMIT ?, ?";
			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			// TODO Delete Test Data
			// String timeCorrection = " 23:13:59";
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchMoID() != null ){
				ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchMSISDN() != null ){
				ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( paramIndexOffset + 3, pageMin );
			ps.setInt(paramIndexOffset + 4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No search result found." );
				
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				
				String payStatus = rs.getString( "pay_status" );
				String statusDesc = "FAILED";
				if( payStatus.equals( "04" ) 
						||  payStatus.equals( "01" ) ) {
					statusDesc = "COMPLETE";
					
				} else if( payStatus.equals( "03" ) 
						|| payStatus.equals( "102" ) ) {
					statusDesc = "PENDING SDP";
				} else if(payStatus.equals( "0" ) 
						|| payStatus.equals( "02" )){
					statusDesc = "PENDING";
					
				}
				
				outTxn.setStatusDesc( statusDesc );
				
				container.addBean( outTxn );
				
			
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
	
	public Out setExportDataMultiTxnToday( In in, BeanItemContainer<OutTxn> container, Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		out = new Out();
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 int max = records.size();
			 Iterator< Item > itr = records.iterator();
			 
			 StringBuilder sBuilder = new StringBuilder();
		 
			 sBuilder.append( "SELECT DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) AS date, t.person_id AS meter_no," );
			 sBuilder.append( " t.amount, t.fundamo_id AS mmo_id, t.payer_id AS msisdn, status_code_id AS pay_status," );
			 sBuilder.append( " t.reference_no AS swifta_id, fx.lsd_value AS rate" );   
			 sBuilder.append( " FROM transactions AS t LEFT JOIN exchange_rate AS fx on fx.id = t.rate_id" );
			
			 if( max != 0 ){
				 sBuilder.append( " WHERE t.reference_no IN( " );
			 } else {
				 sBuilder.append( " WHERE " );
			 }
			   
			   while( max != 0 ) {
					max--;
					if( max == 0 )
						sBuilder.append( "? " );
					else
						sBuilder.append( "?, " );
				}
				
			 if( records.size() != 0 && max == 0 ) {
				 sBuilder.append( " ) AND " );
			 }
			   max = records.size();
			   
			   if( inTxn.getSearchSID() != null ) {
				   sBuilder.append(  " t.reference_no LIKE ? AND" );
				   searchFieldMap.put( "sID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMoID() != null ) {
				   sBuilder.append( " t.fundamo_id LIKE ? AND" );
				   searchFieldMap.put( "moID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMSISDN() != null ) {
				   sBuilder.append( " t.payer_id LIKE ? AND" );
				   searchFieldMap.put( "msisdn",searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMeterNo() != null ) {
				   sBuilder.append( " t.person_id LIKE ? AND" );
				   searchFieldMap.put( "meterNo", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchStatusDesc() != null ) {
				   
				   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
				   
				   if(  "FAILED".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   sBuilder.append( " ( t.status_code_id = '403' " );
					   sBuilder.append( " OR  t.status_code_id = '100' " );  
					   sBuilder.append( " OR  t.status_code_id = '2100' " );  
					   sBuilder.append(  " OR  t.status_code_id = '1100' " );  
					   sBuilder.append(  " OR  t.status_code_id = '3100' " );  
					   sBuilder.append(  " )" );
					   if( max == 0 ){  
						  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
						   sBuilder.append( " AND " );
						}
					   
				   } else if( "COMPLETE".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
					   sBuilder.append(  " ( t.status_code_id = '04' " );
					   sBuilder.append( " OR  t.status_code_id = '01' " );  
					   sBuilder.append(  " )" );
					   if( max == 0 ){  
						  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
						   sBuilder.append( " AND " );
						}
				   } else if(  "PENDING SDP".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   sBuilder.append( " ( t.status_code_id = '03' " );
					   sBuilder.append(  " OR  t.status_code_id = '102' " );  
					   sBuilder.append(  " )" );
					   if( max == 0 ){  
						  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
						   sBuilder.append( " AND " );
						}
				   } else if(  "PENDING".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   sBuilder.append(  " ( t.status_code_id = '0' " );
					   sBuilder.append(  " OR  t.status_code_id = '02' " );  
					   sBuilder.append(  " )" );
					   
					   if( max == 0 ){  
						  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
						   sBuilder.append( " AND " );
					   }
					   
				   } else {
					   
					   // Dummy status for no match
					   sBuilder.append( " ( t.status_code_id = '404' ) " );
					   if( max == 0 ){  
						  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
						   sBuilder.append( " AND " );
						}
				   }
				   
			   } else {
				   
				   // Dummy status for any status
				   // [ this completes the query if no search term is set but some records contain fields ]
				   sBuilder.append( " ( t.status_code_id != '404' ) " );
				   if( max == 0 ){  
					  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
					   sBuilder.append( " AND " );
				   }
				   
			   }
			   
			   
				
			 if( max == 0 ) {
			   
				sBuilder.append(  " t.last_update > ? AND t.last_update < ? ORDER BY t.last_update DESC" );
			 } else {
				 sBuilder.append( " LIMIT "+records.size() );
			 }
			 
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( sBuilder.toString() );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchMoID() != null ){
				ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchMSISDN() != null ){
				ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
			}
			
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "swiftaId" ).getValue().toString() ) );
				max--;
			}
			
			max = records.size();
			
			if( max == 0 ) {
				int paramIndexOffset = searchFieldMap.size()+max;
				ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
				ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			}
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No search result found." );
				
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				
				String payStatus = rs.getString( "pay_status" );
				String statusDesc = "FAILED";
				if( payStatus.equals( "04" ) 
						||  payStatus.equals( "01" ) ) {
					statusDesc = "COMPLETE";
					
				} else if( payStatus.equals( "03" ) 
						|| payStatus.equals( "102" ) ) {
					statusDesc = "PENDING SDP";
				} else if(payStatus.equals( "0" ) 
						|| payStatus.equals( "02" )){
					statusDesc = "PENDING";
					
				}
				
				outTxn.setStatusDesc( statusDesc );
				
				container.addBean( outTxn );
				
			
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

	
	public Out setExportDataMultiPaymentToday( In in, BeanItemContainer<OutTxn> container, Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		out = new Out();
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 int max = records.size();
			 Iterator< Item > itr = records.iterator();
			 
			 StringBuilder sBuilder = new StringBuilder();
			 
			 
			 sBuilder.append( "SELECT DISTINCT t.reference_no AS swifta_id, t.fundamo_id AS mmo_id, t.person_id AS meter_no, t.payer_id AS msisdn, m.token_generated AS token, t.amount, t.status_code_id AS pay_status, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") AS date  FROM transactions AS t JOIN msg_notification AS m on m.transactionhistory_id = t.reference_no" );
			 sBuilder.append( " WHERE (t.status_code_id = 01 OR t.status_code_id = 04 OR t.status_code_id = 03 OR t.status_code_id = \"102\") " );

			 if( max != 0 ){
				 sBuilder.append( " AND t.reference_no IN( " );
			 }
			   
			   while( max != 0 ) {
					max--;
					if( max == 0 )
						sBuilder.append( "? " );
					else
						sBuilder.append( "?, " );
				}
				
			 if( records.size() != 0 && max == 0 ) {
				 sBuilder.append( " ) AND " );
			 } else {
				 sBuilder.append( " AND " );
			 }
			   max = records.size();
			   
			   
			   
			   
			   if( inTxn.getSearchSID() != null ) {
				   sBuilder.append(  " t.reference_no LIKE ? AND " );
				   searchFieldMap.put( "sID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMoID() != null ) {
				   sBuilder.append( " t.fundamo_id LIKE ? AND " );
				   searchFieldMap.put( "moID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMSISDN() != null ) {
				   sBuilder.append( " t.payer_id LIKE ? AND " );
				   searchFieldMap.put( "msisdn",searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMeterNo() != null ) {
				   sBuilder.append(  " t.person_id LIKE ? AND" );
				   searchFieldMap.put( "meterNo", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchToken() != null ) {
				   sBuilder.append( " m.token_generated LIKE ? AND" );
				   searchFieldMap.put( "token", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchReqCur() != null ) {
				   
				   if( "USD".contains( inTxn.getSearchReqCur().toUpperCase() ) ){
					   sBuilder.append(  " t.rate_id = 0 AND " );
				   } else if( "LRD".contains( inTxn.getSearchReqCur().toUpperCase() ) ){
					   sBuilder.append(" t.rate_id != 0 AND " );
				   }else {
					   // Dummy rate id for no match
					   sBuilder.append(  " ( t.rate_id = '-1' ) " );
					   sBuilder.append(  " AND" );
				   }
				   
			   } 
			   
			   if( inTxn.getSearchStatusDesc() != null ) {
				   
				   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
				   
				   if( "COMPLETE".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
					   sBuilder.append(  " ( t.status_code_id = '04' " );
					   sBuilder.append(  " OR  t.status_code_id = '01' " );  
					   sBuilder.append(  " )" );
					   if( max == 0 ){  
						  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
						  sBuilder.append( " AND " );
					   }
				   } else if(  "PENDING SDP".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   sBuilder.append(  " ( t.status_code_id = '03' " );
					   sBuilder.append(  " OR  t.status_code_id = '102' " );  
					   sBuilder.append(  " )" );
					   if( max == 0 ){  
						  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
						   sBuilder.append( " AND " );
					   }
				   } 
				   
			   } else {

				   // Dummy status for any status
				   // [ this completes the query if no search term is set but some records contain fields ]
				   sBuilder.append( " ( t.status_code_id != '404' ) " );
				   if( max == 0 ){  
					  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
					   sBuilder.append( " AND " );
				   }
			   }
				
			 if( max == 0 ) {
			   
				 sBuilder.append( " t.last_update > ? AND t.last_update < ? ORDER BY t.last_update DESC" );
			 } else {
				 sBuilder.append( " LIMIT "+records.size() );
			 }
			 
			ps = conn.prepareStatement( sBuilder.toString() );
			
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "swiftaId" ).getValue().toString() ) );
				max--;
			}
			
			max = records.size();
			
			if( max == 0 ) {
				int paramIndexOffset = searchFieldMap.size()+max;
				ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
				ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			}
			
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchMoID() != null ){
				ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchMSISDN() != null ){
				ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
			}
			
			if( inTxn.getSearchToken() != null ){
				log.debug( "IN TOKEN" );
				ps.setString(  searchFieldMap.get( "token" ), "%"+inTxn.getSearchToken()+"%" );
			}
			
			log.debug( "Query: "+ps.toString() );
			
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No search result found." );
				
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				
				outTxn.setPayStatus( rs.getString( "pay_status" ) );
				//outTxn.setRate( rs.getDouble( "rate" ) );
				outTxn.setDate( rs.getString( "date" ) );
				
				String payStatus = rs.getString( "pay_status" );
				String statusDesc = "FAILED";
				if( payStatus.equals( "04" ) 
						||  payStatus.equals( "01" ) ) {
					statusDesc = "COMPLETE";
					
				} else if( payStatus.equals( "03" ) 
						|| payStatus.equals( "102" ) ) {
					statusDesc = "PENDING SDP";
				} else if(payStatus.equals( "0" ) 
						|| payStatus.equals( "02" )){
					statusDesc = "PENDING";
					
				}
				
				outTxn.setStatusDesc( statusDesc );
				
				
				container.addBean( outTxn );
				
			
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
	
	
	public Out setExportDataMultiTokenToday( In in, BeanItemContainer<OutTxn> container, Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		out = new Out();
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 int max = records.size();
			 Iterator< Item > itr = records.iterator();
			 
			 StringBuilder sBuilder = new StringBuilder();
			 
			 sBuilder.append( "SELECT v.reference_no AS itron_id, v.meter_no, v.transaction_type," );
			 sBuilder.append( " v.status AS token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) AS date," );
			 sBuilder.append( " v.transaction_id AS swifta_id, ( v.repCount + 1 ) AS req_count, v.amount" );
			 sBuilder.append( " FROM  vend_req AS v" );
			 
					 
			 if( max != 0 ){
				 sBuilder.append( " WHERE v.reference_no IN( " );
			 } else {
				 sBuilder.append( " WHERE " );
			 }
			   
			 while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "? " );
				else
					sBuilder.append( "?, " );
			 }
				
			 if( records.size() != 0 && max == 0 ) {
				 sBuilder.append( " ) AND " );
			 }
			 
			 
			   max = records.size();
			   
			   
			   if( inTxn.getSearchSID() != null ) {
				   sBuilder.append( " v.transaction_id LIKE ? AND" );
				   searchFieldMap.put( "sID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchItronId() != null ) {
				   sBuilder.append(" v.reference_no LIKE ? AND" );
				   searchFieldMap.put( "ItronID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMeterNo() != null ) {
				   sBuilder.append(" v.meter_no LIKE ? AND" );
				   searchFieldMap.put( "meterNo", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchTxnType() != null ) {
				   sBuilder.append( " v.transaction_type LIKE ? AND" );
				   searchFieldMap.put( "txnType", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchTokenStatus() != null ) {
				   
				   log.debug( "Status: "+inTxn.getSearchTokenStatus() );
				   sBuilder.append( " v.status LIKE ? " );
				   
				   searchFieldMap.put( "tokenStatus", searchFieldMap.size()+max+1 );
				   

				   	if( max == 0 ){  
						//[ this completes the query if no records set.  ]
						sBuilder.append( " AND " );
					}
				   
				   
			   } else{
				   
				   // Dummy status for any status
				   // [ this completes the query if no search term is set but some records contain fields ]
				   sBuilder.append( " ( v.status != '404' ) " );
				   if( max == 0 ){  
					  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
					   sBuilder.append( " AND " );
				   }
				   
			   }
			   
			
				
			 if( max == 0 ) {
			   	sBuilder.append( "  v.last_update > ? AND v.last_update < ? ORDER BY v.last_update DESC" );

			 } else {
				sBuilder.append( " LIMIT "+records.size() );
			 }
			 
			 
			conn.setReadOnly( true );
			
			ps = conn.prepareStatement( sBuilder.toString() );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchItronId() != null ){
				ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchTxnType() != null ){
				ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
			}
			
			if( inTxn.getSearchTokenStatus() != null ){
				ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
			}
			
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "itronId" ).getValue().toString() ) );
				max--;
			}
			
			max = records.size();
			
			if( max == 0 ) {
				int paramIndexOffset = searchFieldMap.size()+max;
				ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
				ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			}
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No search result found." );
				
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				outTxn.setDate( rs.getString( "date" ) );
				
				container.addBean( outTxn );
				
			
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
	
	
	public Out setExportDataMultiInfoToday( In in, BeanItemContainer<OutTxn> container, Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		out = new Out();
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 int max = records.size();
			 Iterator< Item > itr = records.iterator();
			 
			 StringBuilder sBuilder = new StringBuilder();
			 
			 sBuilder.append( "SELECT v.reference_no AS itron_id, v.meter_no, v.transaction_type," );
			 sBuilder.append( " v.status AS token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) AS date," );
			 sBuilder.append( " v.transaction_id AS swifta_id, ( v.repCount + 1 ) AS req_count, v.amount" );
			 sBuilder.append( " FROM  transaction_history AS v" );
			 
					 
			 if( max != 0 ){
				 sBuilder.append( " WHERE v.reference_no IN( " );
			 } else {
				 sBuilder.append( " WHERE " );
			 }
			   
			 while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "? " );
				else
					sBuilder.append( "?, " );
			 }
				
			 if( records.size() != 0 && max == 0 ) {
				 sBuilder.append( " ) AND " );
			 }
			 
			 
			   max = records.size();
			   
			   
			   if( inTxn.getSearchSID() != null ) {
				   sBuilder.append( " v.transaction_id LIKE ? AND" );
				   searchFieldMap.put( "sID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchItronId() != null ) {
				   sBuilder.append(" v.reference_no LIKE ? AND" );
				   searchFieldMap.put( "ItronID", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchMeterNo() != null ) {
				   sBuilder.append(" v.meter_no LIKE ? AND" );
				   searchFieldMap.put( "meterNo", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchTxnType() != null ) {
				   sBuilder.append( " v.transaction_type LIKE ? AND" );
				   searchFieldMap.put( "txnType", searchFieldMap.size()+max+1 );
			   }
			   
			   if( inTxn.getSearchTokenStatus() != null ) {
				   
				   log.debug( "Status: "+inTxn.getSearchTokenStatus() );
				   sBuilder.append( " v.status LIKE ? " );
				   
				   searchFieldMap.put( "tokenStatus", searchFieldMap.size()+max+1 );
				   

				   	if( max == 0 ){  
						//[ this completes the query if no records set.  ]
						sBuilder.append( " AND " );
					}
				   
				   
			   } else{
				   
				   // Dummy status for any status
				   // [ this completes the query if no search term is set but some records contain fields ]
				   sBuilder.append( " ( v.status != '404' ) " );
				   if( max == 0 ){  
					  //  [ this completes the query if no records set. i.e. It rectifies the later correction if it's not necessary  ]
					   sBuilder.append( " AND " );
				   }
				   
			   }
			   
			
				
			 if( max == 0 ) {
			   	sBuilder.append( "  v.last_update > ? AND v.last_update < ? ORDER BY v.last_update DESC" );

			 } else {
				sBuilder.append( " LIMIT "+records.size() );
			 }
			 
			 
			conn.setReadOnly( true );
			
			ps = conn.prepareStatement( sBuilder.toString() );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchItronId() != null ){
				ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchTxnType() != null ){
				ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
			}
			
			if( inTxn.getSearchTokenStatus() != null ){
				ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
			}
			
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "itronId" ).getValue().toString() ) );
				max--;
			}
			
			max = records.size();
			
			if( max == 0 ) {
				int paramIndexOffset = searchFieldMap.size()+max;
				ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
				ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			}
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No search result found." );
				
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				outTxn.setDate( rs.getString( "date" ) );
				
				container.addBean( outTxn );
				
			
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
	
	

	@SuppressWarnings("unchecked")
	public Out refreshTxnRecord( Item record ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "select DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, status_code_id as pay_status from transactions as t where t.reference_no = ? LIMIT 1";
		
		try {
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, record.getItemProperty( "swiftaId" ).getValue().toString() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
			record.getItemProperty( "date" ).setValue( rs.getString( "date" ) );
			
			String payStatus = rs.getString( "pay_status" );
			String statusDesc = "FAILED";
			if( payStatus.equals( "04" ) 
					||  payStatus.equals( "01" ) ) {
				statusDesc = "COMPLETE";
				
			} else if( payStatus.equals( "03" ) 
					|| payStatus.equals( "102")
					|| payStatus.equals( "403") ) {
				statusDesc = "PENDING SDP";
			} else if(payStatus.equals( "0" ) 
					|| payStatus.equals( "02" )){
				statusDesc = "PENDING";
				
			}
			
			record.getItemProperty( "statusDesc" ).setValue( statusDesc );
			
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


	@SuppressWarnings("unchecked")
	public Out refreshMultiTxnRecord( Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			int max = records.size();
			Iterator< Item > itr = records.iterator();
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append( "select t.reference_no as swifta_id, DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, status_code_id as pay_status from transactions as t where t.reference_no IN( ");
			
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( ") LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "swiftaId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
			
			
			
			do {
			
				itr = records.iterator();
				max = records.size();
				Item record = null;
				
				log.debug( "Total records: "+max );
				
				while( itr.hasNext() ) {
					
					record = itr.next();
					String sid = record.getItemProperty( "swiftaId" ).getValue().toString();
					
					if( sid.equals( rs.getString( "swifta_id" ) ) ) {
						
						record.getItemProperty( "date" ).setValue( rs.getString( "date" ) );
						
						String payStatus = rs.getString( "pay_status" );
						String statusDesc = "FAILED";
						if( payStatus.equals( "04" ) 
								||  payStatus.equals( "01" ) ) {
							statusDesc = "COMPLETE";
							
						} else if( payStatus.equals( "03" ) 
								|| payStatus.equals( "102")
								|| payStatus.equals( "403") ) {
							statusDesc = "PENDING SDP";
						} else if(payStatus.equals( "0" ) 
								|| payStatus.equals( "02" )){
							statusDesc = "PENDING";
							
						}
						
						record.getItemProperty( "statusDesc" ).setValue( statusDesc );
						
						max--;
						
						if( record != null )
							records.remove( record );
					
						
						break;
					
					}
					
				}
				
				
			} while( rs.next() );
			
			log.debug( "Max after refresh: "+max );
			
			if( max == 0 ) {
				out.setStatusCode( 1 );
				out.setMsg( "Refresh successful for all selected records." );
			}else if( max > 0 ){
				out.setStatusCode( 2 );
				out.setMsg( "Some records were not refreshed. Please try again later." );
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
	
	
	@SuppressWarnings("unchecked")
	public Out refreshMultiPaymentRecord( Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			int max = records.size();
			Iterator< Item > itr = records.iterator();
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append( "select t.reference_no as swifta_id, DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, status_code_id as pay_status from transactions as t WHERE t.reference_no IN( ");
			
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( ") LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "swiftaId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
			
			
			
			do {
			
				itr = records.iterator();
				max = records.size();
				Item record = null;
				
				log.debug( "Total records: "+max );
				
				while( itr.hasNext() ) {
					
					record = itr.next();
					String sid = record.getItemProperty( "swiftaId" ).getValue().toString();
					
					if( sid.equals( rs.getString( "swifta_id" ) ) ) {
						
						record.getItemProperty( "date" ).setValue( rs.getString( "date" ) );
						
						
						String payStatus = rs.getString( "pay_status" );
						String statusDesc = "FAILED";
						if( payStatus.equals( "04" ) 
								||  payStatus.equals( "01" ) ) {
							statusDesc = "COMPLETE";
							
						} else if( payStatus.equals( "03" ) 
								|| payStatus.equals( "102" ) ) {
							statusDesc = "PENDING SDP";
						} else if(payStatus.equals( "0" ) 
								|| payStatus.equals( "02" )){
							statusDesc = "PENDING";
							
						}

						
						record.getItemProperty( "statusDesc" ).setValue( statusDesc );
						
						max--;
						
						if( record != null )
							records.remove( record );
					
						
						break;
					
					}
					
				}
				
				
			} while( rs.next() );
			
			if( max == 0 ) {
				out.setStatusCode( 1 );
				out.setMsg( "Refresh successful for all selected records." );
			}else if( max > 0 ){
				out.setStatusCode( 2 );
				out.setMsg( "Some records were not refreshed. Please try again later." );
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

	
	@SuppressWarnings("unchecked")
	public Out refreshMultiTokenRecord( Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			int max = records.size();
			Iterator< Item > itr = records.iterator();
			
			
			StringBuilder sBuilder = new StringBuilder();
			// sBuilder.append( "select t.reference_no as swifta_id, DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, status_code_id as pay_status from transactions as t where t.reference_no IN( ");
			
			sBuilder.append( "SELECT v.reference_no," );
			sBuilder.append( " v.status AS token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) AS date," );
			sBuilder.append( " v.transaction_id AS swifta_id, ( v.repCount + 1 ) AS req_count" );
			sBuilder.append(  " FROM  vend_req AS v" );
			sBuilder.append(  " WHERE v.reference_no IN ( " );
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "? " );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( ") LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "itronId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
			
			
			
			do {
			
				itr = records.iterator();
				max = records.size();
				Item record = null;
				
				log.debug( "Total records: "+max );
				
				while( itr.hasNext() ) {
					
					record = itr.next();
					String iid = record.getItemProperty( "itronId" ).getValue().toString();
					
					if( iid.equals( rs.getString( "reference_no" ) ) ) {
						
						record.getItemProperty( "reqCount" ).setValue( rs.getString( "req_count" ) );
						record.getItemProperty( "date" ).setValue( rs.getString( "date" ) );
						record.getItemProperty( "tokenStatus" ).setValue( rs.getString( "token_status" ) );
						
						max--;
						
						if( record != null )
							records.remove( record );
					
						
						break;
					
					}
					
				}
				
				
			} while( rs.next() );
			
			if( max == 0 ) {
				out.setStatusCode( 1 );
				out.setMsg( "Refresh successful for all selected records." );
			}else if( max > 0 ){
				out.setStatusCode( 2 );
				out.setMsg( "Some records were not refreshed. Please try again later." );
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
	
	
	
	@SuppressWarnings("unchecked")
	public Out refreshMultiInfoRecord( Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			int max = records.size();
			Iterator< Item > itr = records.iterator();
			
			
			StringBuilder sBuilder = new StringBuilder();
			// sBuilder.append( "select t.reference_no as swifta_id, DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, status_code_id as pay_status from transactions as t where t.reference_no IN( ");
			
			sBuilder.append( "SELECT v.reference_no, " );
			sBuilder.append( " v.status AS token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) AS date," );
			sBuilder.append( " v.transaction_id AS swifta_id, ( v.repCount + 1 ) AS req_count" );
			sBuilder.append(  " FROM  transaction_history AS v" );
			sBuilder.append(  " WHERE v.reference_no IN ( " );
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "? " );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( ") LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "itronId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
			
			
			
			do {
			
				itr = records.iterator();
				max = records.size();
				Item record = null;
				
				log.debug( "Total records: "+max );
				
				while( itr.hasNext() ) {
					
					record = itr.next();
					String iid = record.getItemProperty( "itronId" ).getValue().toString();
					
					if( iid.equals( rs.getString( "reference_no" ) ) ) {
						
						record.getItemProperty( "reqCount" ).setValue( rs.getString( "req_count" ) );
						record.getItemProperty( "date" ).setValue( rs.getString( "date" ) );
						record.getItemProperty( "tokenStatus" ).setValue( rs.getString( "token_status" ) );
						
						max--;
						
						if( record != null )
							records.remove( record );
					
						
						break;
					
					}
					
				}
				
				
			} while( rs.next() );
			
			log.debug( "Max after refresh: "+max );
			
			if( max == 0 ) {
				out.setStatusCode( 1 );
				out.setMsg( "Refresh successful for all selected records." );
			}else if( max > 0 ){
				
				out.setStatusCode( 2 );
				out.setMsg( "Some records were not refreshed. Please try again later." );
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
	@SuppressWarnings("unchecked")
	public Out searchMultiTxnRecord_1 ( Collection< Item > records ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			int max = records.size();
			Iterator< Item > itr = records.iterator();
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append( "select t.reference_no as swifta_id, DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, status_code_id as pay_status from transactions as t WHERE t.reference_no IN( ");
			
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( " LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "swiftaId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
			
			
			
			do {
			
				itr = records.iterator();
				max = records.size();
				Item record = null;
				
				log.debug( "Total records: "+max );
				
				while( itr.hasNext() ) {
					
					record = itr.next();
					String sid = record.getItemProperty( "swiftaId" ).getValue().toString();
					
					if( sid.equals( rs.getString( "swifta_id" ) ) ) {
						
						record.getItemProperty( "date" ).setValue( rs.getString( "date" ) );
						
						String payStatus = rs.getString( "pay_status" );
						String statusDesc = "FAILED";
						if( payStatus.equals( "04" ) 
								||  payStatus.equals( "01" ) ) {
							statusDesc = "COMPLETE";
							
						} else if( payStatus.equals( "03" ) 
								|| payStatus.equals( "102")
								|| payStatus.equals( "403") ) {
							statusDesc = "PENDING SDP";
						} else if(payStatus.equals( "0" ) 
								|| payStatus.equals( "02" )){
							statusDesc = "PENDING";
							
						}
						
						record.getItemProperty( "statusDesc" ).setValue( statusDesc );
						
						max--;
						
						if( record != null )
							records.remove( record );
					
						
						break;
					
					}
					
				}
				
				
			} while( rs.next() );
			
			if( max == 0 ) {
				out.setStatusCode( 1 );
				out.setMsg( "Refresh successful for all selected records." );
			}else if( max < records.size()){
				out.setStatusCode( 2 );
				out.setMsg( "Some records were not refreshed. Please try again later." );
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
	
	public Out getTokenToday( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "SELECT reference_no as itron_id, meter_no, transaction_type, status as token_status, DATE_FORMAT( last_update, '%Y-%m-%j %T' ) as date, transaction_id as swifta_id, ( repCount + 1 ) as req_count, amount FROM  vend_req as v where v.last_update > ? and v.last_update < ? order by v.last_update DESC;";
		
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
			 // String timeCorrection = " 23:13:59";
			//  inTxn.setfDate( "2017-01-18" );
			 // inTxn.settDate( "2017-01-19" );
			 
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
	

	public Out setTokenToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		//String q = "select DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, t.person_id as meter_no, t.amount, t.fundamo_id as mmo_id, t.payer_id as msisdn, status_code_id as pay_status, t.reference_no as swifta_id, fx.lsd_value as rate   from transactions as t left join exchange_rate as fx on fx.id = t.rate_id where t.last_update > ? and t.last_update < ? order by t.last_update DESC LIMIT ?, ?";
		
		String q = "SELECT v.reference_no as itron_id, v.meter_no, v.transaction_type, v.status as token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) as date, v.transaction_id as swifta_id, ( v.repCount + 1 ) as req_count, v.amount FROM  vend_req AS v WHERE v.last_update > ? AND v.last_update < ? order by v.last_update DESC LIMIT ?, ?;";

		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			 // String timeCorrection = " 23:13:59";
			 // inTxn.setfDate( "2017-01-01" );
			 // inTxn.settDate( "2017-01-14" );
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection );
			ps.setString( 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( 3, pageMin );
			ps.setInt(4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				outTxn = new OutTxn();
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				outTxn.setDate( rs.getString( "date" ) );
				
				container.addBean( outTxn );
				
			
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
	
	public Out searchTokenToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		
		// String q = "SELECT v.reference_no as itron_id, v.meter_no, v.transaction_type, v.status as token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) as date, v.transaction_id as swifta_id, ( v.repCount + 1 ) as req_count, v.amount FROM  vend_req AS v WHERE v.last_update > ? AND v.last_update < ? order by v.last_update DESC LIMIT ?, ?;";

		
		
		try {
			
			
			
			
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 
		 
			String q = "SELECT v.reference_no AS itron_id, v.meter_no, v.transaction_type,";
				   q += " v.status AS token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) AS date,";
				   q += " v.transaction_id AS swifta_id, ( v.repCount + 1 ) AS req_count, v.amount";
				   q += " FROM  vend_req AS v";
				   q += " WHERE";
			   
			   if( inTxn.getSearchSID() != null ) {
				   q += " v.transaction_id LIKE ? AND";
				   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchItronId() != null ) {
				   q += " v.reference_no LIKE ? AND";
				   searchFieldMap.put( "ItronID", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchMeterNo() != null ) {
				   q += " v.meter_no LIKE ? AND";
				   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchTxnType() != null ) {
				   q += " v.transaction_type LIKE ? AND";
				   searchFieldMap.put( "txnType", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchTokenStatus() != null ) {
				   
				   log.debug( "Status: "+inTxn.getSearchTokenStatus() );
				   q += " v.status LIKE ? AND";
				   searchFieldMap.put( "tokenStatus", searchFieldMap.size()+1 );
				   
			   }
			   
			 q += " v.last_update > ? AND v.last_update < ? ORDER BY v.last_update DESC LIMIT ?, ?";
			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchItronId() != null ){
				ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchTxnType() != null ){
				ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
			}
			
			if( inTxn.getSearchTokenStatus() != null ){
				ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( paramIndexOffset + 3, pageMin );
			ps.setInt(paramIndexOffset + 4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				out.setStatusCode( 100 );
				out.setMsg( "No records found." );
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
				outTxn.setDate( rs.getString( "date" ) );
				
				container.addBean( outTxn );
				
			
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
	

	public Out setInfoRetryToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		
		try {
			
			 String q = "SELECT v.reference_no as itron_id, v.meter_no, v.transaction_type, v.status as token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) as date, v.transaction_id as swifta_id, ( v.repCount + 1 ) as req_count, v.amount FROM transaction_history AS v ";
			 		q += " WHERE";
			 		
			 		
			 		q += " v.last_update > ? AND v.last_update < ? order by v.last_update DESC LIMIT ?, ?;";

			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			//  String timeCorrection = " 23:13:59";
			 // inTxn.setfDate( "2017-01-01" );
			 // inTxn.settDate( "2017-01-14" );
			 
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection );
			ps.setString( 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( 3, pageMin );
			ps.setInt(4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				outTxn = new OutTxn();
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
				bOutData.setData( container );
				
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
				outTxn.setDate( rs.getString( "date" ) );
				
				container.addBean( outTxn );
				
			
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
	
	
	public Out searchInfoRetryToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		
		try {
			
			
			
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 

			
			 String q = "SELECT v.reference_no as itron_id, v.meter_no, v.transaction_type, v.status as token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) as date, v.transaction_id as swifta_id, ( v.repCount + 1 ) as req_count, v.amount FROM transaction_history AS v ";
			 		q += " WHERE";
			 		
					   if( inTxn.getSearchSID() != null ) {
						   q += " v.transaction_id LIKE ? AND";
						   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
					   }
					   
					   if( inTxn.getSearchItronId() != null ) {
						   q += " v.reference_no LIKE ? AND";
						   searchFieldMap.put( "ItronID", searchFieldMap.size()+1 );
					   }
					   
					   if( inTxn.getSearchMeterNo() != null ) {
						   q += " v.meter_no LIKE ? AND";
						   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
					   }
					   
					   if( inTxn.getSearchTxnType() != null ) {
						   q += " v.transaction_type LIKE ? AND";
						   searchFieldMap.put( "txnType", searchFieldMap.size()+1 );
					   }
					   
					   if( inTxn.getSearchTokenStatus() != null ) {
						   
						   log.debug( "Status: "+inTxn.getSearchTokenStatus() );
						   q += " v.status LIKE ? AND";
						   searchFieldMap.put( "tokenStatus", searchFieldMap.size()+1 );
						   
					   }
			 		
			 		
			 		q += " v.last_update > ? AND v.last_update < ? order by v.last_update DESC LIMIT ?, ?;";

			 
			 
			 int page = inTxn.getPage();
			 int pageLength = 15;
			 int pageMin = 0;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			// TODO Delete Test Data
			// String timeCorrection = " 23:13:59";	
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchItronId() != null ){
				ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchTxnType() != null ){
				ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
			}
			
			if( inTxn.getSearchTokenStatus() != null ){
				ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			ps.setInt( paramIndexOffset + 3, pageMin );
			ps.setInt(paramIndexOffset + 4, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setStatusCode( 100 );
				out.setMsg( "No records found." );
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
				outTxn.setDate( rs.getString( "date" ) );
				
				container.addBean( outTxn );
				
			
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
	
	
	
	public Out setTxnMeta( In in, OutTxnMeta outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
	
		String q = "select COUNT( t.reference_no ) as total_records FROM transactions as t WHERE t.last_update > ? and t.last_update < ?";
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			// String timeCorrection = " 23:13:59";
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection ) ;
			ps.setString(2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );

				return out;
			}
			
			
			
			outTxn.getTotalRecord().setValue( rs.getLong( "total_records" )+"" );
			
			ps.close();
			rs.close();
			
			q = "select SUM( t.amount ) as total_revenue FROM transactions as t WHERE t.last_update > ? and t.last_update < ? and ( t.status_code_id = '04' OR t.status_code_id = '01')";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				return out;
			}
			
			outTxn.getTotalRevenue().setValue( rs.getString( "total_revenue" ) );
			
			
			
			out.setStatusCode( 1 );
			out.setMsg( "Txn meta computed successfully." );
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out searchTxnMeta( In in, OutTxnMeta outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 String qVariable = "";
		 
			String q = "SELECT  COUNT( t.reference_no ) AS total_records FROM transactions as t";
			     q += " WHERE";
			   
			   if( inTxn.getSearchSID() != null ) {
				   qVariable += " t.reference_no LIKE ? AND";
				   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchMoID() != null ) {
				   qVariable += " t.fundamo_id LIKE ? AND";
				   searchFieldMap.put( "moID", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchMSISDN() != null ) {
				   qVariable += " t.payer_id LIKE ? AND";
				   searchFieldMap.put( "msisdn",searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchMeterNo() != null ) {
				   qVariable += " t.person_id LIKE ? AND";
				   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
			   }
			   
			   if( inTxn.getSearchStatusDesc() != null ) {
				   
				   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
				   
				   if(  "FAILED".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   qVariable += " ( t.status_code_id = '403' ";
					   qVariable += " OR  t.status_code_id = '100' ";  
					   qVariable += " OR  t.status_code_id = '2100' ";  
					   qVariable += " OR  t.status_code_id = '1100' ";  
					   qVariable += " OR  t.status_code_id = '3100' ";  
					   qVariable += " )";
					   qVariable += " AND";
					   
				   } else if( "COMPLETE".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
					   qVariable += " ( t.status_code_id = '04' ";
					   qVariable += " OR  t.status_code_id = '01' ";  
					   qVariable += " )";
					   qVariable += " AND";
				   } else if(  "PENDING SDP".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   qVariable += " ( t.status_code_id = '03' ";
					   qVariable += " OR  t.status_code_id = '102' ";  
					   qVariable += " )";
					   qVariable += " AND";
				   } else if(  "PENDING".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
					   
					   qVariable += " ( t.status_code_id = '0' ";
					   qVariable += " OR  t.status_code_id = '02' ";  
					   qVariable += " )";
					   qVariable += " AND";
				   } else {
					   
					   // Dummy status for no match
					   qVariable += " ( t.status_code_id = '404' ) ";
					   qVariable += " AND";
				   }
				   
			   }
			   
			 q += qVariable;
			 q += " t.last_update > ? AND t.last_update < ? ORDER BY t.last_update DESC";
			 
			// String timeCorrection = " 23:13:59";
			conn = dataSource.getConnection();
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchMoID() != null ){
				ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchMSISDN() != null ){
				ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No search result found." );
				outTxn.getTotalRecord().setValue( "0" );
				return out;
			}
			
			outTxn.getTotalRecord().setValue( rs.getLong( "total_records" )+"" );
			
			ps.close();
			rs.close();
						
			q = "SELECT SUM( t.amount ) AS total_revenue FROM transactions as t";
		    q += " WHERE";
		    q += qVariable;
		    q += " t.last_update > ? AND t.last_update < ? AND ( t.status_code_id = '04' OR t.status_code_id = '01') ORDER BY t.last_update DESC";
		
		ps = conn.prepareStatement( q );
		if( inTxn.getSearchSID() != null ){
			ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
		}
		
		if( inTxn.getSearchMoID() != null ){
			ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
		}
		
		if( inTxn.getSearchMeterNo() != null ){
			ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
		}
		
		if( inTxn.getSearchMSISDN() != null ){
			ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
		}
		
		paramIndexOffset = searchFieldMap.size();
		ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
		ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
		
		log.debug( "Query: "+ps.toString() );
		
		rs = ps.executeQuery();
		if( !rs.next() ) {
			log.debug( "No result" );
			out.setMsg( "No search result found." );
			outTxn.getTotalRevenue().setValue( "0" );
			return out;
		}
		
		log.debug( "Next is not null." );
		if( rs.getString( "total_revenue" ) == null ){
			outTxn.getTotalRevenue().setValue( "0" );
		}else {
			outTxn.getTotalRevenue().setValue( rs.getString( "total_revenue" ) );
		}
		
		out.setStatusCode( 1 );
		out.setMsg( "Txn meta computed successfully." );
		
		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	
	public Out searchPaymentMeta( In in, OutTxnMeta outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 
			 String qVariable = "";
		 
			String q = "SELECT  COUNT( t.reference_no ) AS total_records FROM transactions AS t JOIN msg_notification AS m  ON m.transactionhistory_id = t.reference_no ";
			     q += " WHERE";
				   if( inTxn.getSearchSID() != null ) {
					   qVariable += " t.reference_no LIKE ? AND";
					   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMoID() != null ) {
					   qVariable += " t.fundamo_id LIKE ? AND";
					   searchFieldMap.put( "moID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMSISDN() != null ) {
					   qVariable += " t.payer_id LIKE ? AND";
					   searchFieldMap.put( "msisdn",searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMeterNo() != null ) {
					   qVariable += " t.person_id LIKE ? AND";
					   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchToken() != null ) {
					   qVariable += " m.token_generated LIKE ? AND";
					   searchFieldMap.put( "token", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchReqCur() != null ) {
					   
					   log.debug( "Status: "+inTxn.getSearchReqCur() );
					   
					   if( "USD".contains( inTxn.getSearchReqCur().toUpperCase() ) ){
						   qVariable += " t.rate_id = 0 AND";
					   } else if( "LRD".contains( inTxn.getSearchReqCur().toUpperCase() ) ){
						   qVariable += " t.rate_id != 0 AND";
					   }else {
						  
						   // Dummy rate id for no match
						   qVariable += " ( t.rate_id = '-1' ) ";
						   qVariable += " AND";
					   }
					   
				   }
				   
				   if( inTxn.getSearchStatusDesc() != null ) {
					   
					   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
					   
					   if( "COMPLETE".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
						   
						   log.debug( "Status: "+inTxn.getSearchStatusDesc() );
						   qVariable += " ( t.status_code_id = '04' ";
						   qVariable += " OR  t.status_code_id = '01' ";  
						   qVariable += " )";
						   qVariable += " AND";
					   } else if(  "PENDING SDP".contains( inTxn.getSearchStatusDesc().toUpperCase() ) ){
						   
						   qVariable += " ( t.status_code_id = '03' ";
						   qVariable += " OR  t.status_code_id = '102' ";  
						   qVariable += " )";
						   qVariable += " AND";
					   }else {
						   
						   // Dummy status for no match
						   qVariable += " ( t.status_code_id = '404' ) ";
						   qVariable += " AND";
					   }
					   
				   }
			 q += qVariable;
			 q += " t.last_update > ? AND t.last_update < ?";
			 
			// String timeCorrection = " 23:13:59";
			conn = dataSource.getConnection();
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchMoID() != null ){
				ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchMSISDN() != null ){
				ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
			}
			
			if( inTxn.getSearchToken() != null ){
				ps.setString(  searchFieldMap.get( "token" ), "%"+inTxn.getSearchToken()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No search result found." );
				outTxn.getTotalRecord().setValue( "0" );
				return out;
			}
			
			outTxn.getTotalRecord().setValue( rs.getLong( "total_records" )+"" );
			
			ps.close();
			rs.close();
						
			q = "SELECT SUM( t.amount ) AS total_revenue FROM transactions as t";
		    q += " WHERE ";
		    q += qVariable;
		    q += " t.last_update > ? AND t.last_update < ? AND ( t.status_code_id = '04' OR t.status_code_id = '01')";
		
		    
		ps = conn.prepareStatement( q );
		if( inTxn.getSearchSID() != null ){
			ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
		}
		
		if( inTxn.getSearchMoID() != null ){
			ps.setString(  searchFieldMap.get( "moID" ), "%"+inTxn.getSearchMoID()+"%" );
		}
		
		if( inTxn.getSearchMeterNo() != null ){
			ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
		}
		
		if( inTxn.getSearchMSISDN() != null ){
			ps.setString(  searchFieldMap.get( "msisdn" ), "%"+inTxn.getSearchMSISDN()+"%" );
		}
		
		if( inTxn.getSearchToken() != null ){
			ps.setString(  searchFieldMap.get( "token" ), "%"+inTxn.getSearchToken()+"%" );
		}
		
		paramIndexOffset = searchFieldMap.size();
		ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
		ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
		
		log.debug( "Query: "+ps.toString() );
		
		rs = ps.executeQuery();
		if( !rs.next() ) {
			log.debug( "No result" );
			out.setMsg( "No search result found." );
			outTxn.getTotalRevenue().setValue( "0" );
			return out;
		}
		
		log.debug( "Next is not null." );
		if( rs.getString( "total_revenue" ) == null ){
			outTxn.getTotalRevenue().setValue( "0" );
		}else {
			outTxn.getTotalRevenue().setValue( rs.getString( "total_revenue" ) );
		}
		
		out.setStatusCode( 1 );
		out.setMsg( "Txn meta computed successfully." );
		
		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out searchTokenMeta( In in, OutTxnMeta outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 
			 String qVariable = "";
		 
			String q = "SELECT  COUNT( v.reference_no ) AS total_records FROM vend_req as v";
			     q += " WHERE";
				   if( inTxn.getSearchSID() != null ) {
					   qVariable += " v.transaction_id LIKE ? AND";
					   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchItronId() != null ) {
					   qVariable += " v.reference_no LIKE ? AND";
					   searchFieldMap.put( "ItronID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMeterNo() != null ) {
					   qVariable += " v.meter_no LIKE ? AND";
					   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchTxnType() != null ) {
					   qVariable += " v.transaction_type LIKE ? AND";
					   searchFieldMap.put( "txnType", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchTokenStatus() != null ) {
					   
					   log.debug( "Status: "+inTxn.getSearchTokenStatus() );
					   qVariable += " v.status LIKE ? AND";
					   searchFieldMap.put( "tokenStatus", searchFieldMap.size()+1 );
					   
				   }
				   
			 q += qVariable;
			 q += " v.last_update > ? AND v.last_update < ?";
			 
			// String timeCorrection = " 23:13:59";
			conn = dataSource.getConnection();
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchItronId() != null ){
				ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchTxnType() != null ){
				ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
			}
			
			if( inTxn.getSearchTokenStatus() != null ){
				ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No result found." );
				outTxn.getTotalRecord().setValue( "0" );
				return out;
			}
			
			outTxn.getTotalRecord().setValue( rs.getLong( "total_records" )+"" );
			
			ps.close();
			rs.close();
						
			q = "SELECT SUM( v.amount ) AS total_revenue FROM vend_req AS v";
		    q += " WHERE ";
		    q += qVariable;
		    q += " v.last_update > ? AND v.last_update < ? AND ( v.status = 'COMPLETE' OR v.status = 'REVERSED') ";
		
		    
		ps = conn.prepareStatement( q );
		if( inTxn.getSearchSID() != null ){
			ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
		}
		
		if( inTxn.getSearchItronId() != null ){
			ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
		}
		
		if( inTxn.getSearchMeterNo() != null ){
			ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
		}
		
		if( inTxn.getSearchTxnType() != null ){
			ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
		}
		
		if( inTxn.getSearchTokenStatus() != null ){
			ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
		}
		
		paramIndexOffset = searchFieldMap.size();
		ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
		ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
		
		log.debug( "Query: "+ps.toString() );
		
		rs = ps.executeQuery();
		if( !rs.next() ) {
			log.debug( "No result" );
			out.setMsg( "No search result found." );
			outTxn.getTotalRevenue().setValue( "0" );
			return out;
		}
		
		log.debug( "Next is not null." );
		if( rs.getString( "total_revenue" ) == null ){
			outTxn.getTotalRevenue().setValue( "0" );
		}else {
			outTxn.getTotalRevenue().setValue( rs.getString( "total_revenue" ) );
		}
		
		out.setStatusCode( 1 );
		out.setMsg( "Txn meta computed successfully." );
		
		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out searchInfoRetryMeta( In in, OutTxnMeta outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			 
			 Map<String, Integer > searchFieldMap = new HashMap<>();
			 
			 String qVariable = "";
		 
			String q = "SELECT  COUNT( v.reference_no ) AS total_records FROM transaction_history AS v";
			     q += " WHERE";
				   if( inTxn.getSearchSID() != null ) {
					   qVariable += " v.transaction_id LIKE ? AND";
					   searchFieldMap.put( "sID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchItronId() != null ) {
					   qVariable += " v.reference_no LIKE ? AND";
					   searchFieldMap.put( "ItronID", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchMeterNo() != null ) {
					   qVariable += " v.meter_no LIKE ? AND";
					   searchFieldMap.put( "meterNo", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchTxnType() != null ) {
					   qVariable += " v.transaction_type LIKE ? AND";
					   searchFieldMap.put( "txnType", searchFieldMap.size()+1 );
				   }
				   
				   if( inTxn.getSearchTokenStatus() != null ) {
					   
					   log.debug( "Status: "+inTxn.getSearchTokenStatus() );
					   qVariable += " v.status LIKE ? AND";
					   searchFieldMap.put( "tokenStatus", searchFieldMap.size()+1 );
					   
				   }
				   
			 q += qVariable;
			 q += " v.last_update > ? AND v.last_update < ?";
			 
			// String timeCorrection = " 23:13:59";
			conn = dataSource.getConnection();
			conn.setReadOnly( true );
			ps = conn.prepareStatement( q );
			if( inTxn.getSearchSID() != null ){
				ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
			}
			
			if( inTxn.getSearchItronId() != null ){
				ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
			}
			
			if( inTxn.getSearchMeterNo() != null ){
				ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
			}
			
			if( inTxn.getSearchTxnType() != null ){
				ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
			}
			
			if( inTxn.getSearchTokenStatus() != null ){
				ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
			}
			
			int paramIndexOffset = searchFieldMap.size();
			
			ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
			ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				out.setMsg( "No result found." );
				outTxn.getTotalRecord().setValue( "0" );
				return out;
			}
			
			outTxn.getTotalRecord().setValue( rs.getLong( "total_records" )+"" );
			
			ps.close();
			rs.close();
						
			q = "SELECT SUM( v.amount ) AS total_revenue FROM vend_req AS v";
		    q += " WHERE ";
		    q += qVariable;
		    q += " v.last_update > ? AND v.last_update < ? AND ( v.status = 'COMPLETE' OR v.status = 'REVERSED') ";
		
		    
		ps = conn.prepareStatement( q );
		if( inTxn.getSearchSID() != null ){
			ps.setString( searchFieldMap.get( "sID" ), "%"+inTxn.getSearchSID()+"%" );
		}
		
		if( inTxn.getSearchItronId() != null ){
			ps.setString(  searchFieldMap.get( "ItronID" ), "%"+inTxn.getSearchItronId()+"%" );
		}
		
		if( inTxn.getSearchMeterNo() != null ){
			ps.setString(  searchFieldMap.get( "meterNo" ), "%"+inTxn.getSearchMeterNo()+"%" );
		}
		
		if( inTxn.getSearchTxnType() != null ){
			ps.setString(  searchFieldMap.get( "txnType" ), "%"+inTxn.getSearchTxnType()+"%" );
		}
		
		if( inTxn.getSearchTokenStatus() != null ){
			ps.setString(  searchFieldMap.get( "tokenStatus" ), "%"+inTxn.getSearchTokenStatus()+"%" );
		}
		
		paramIndexOffset = searchFieldMap.size();
		ps.setString( paramIndexOffset + 1, inTxn.getfDate()+timeCorrection );
		ps.setString( paramIndexOffset + 2, inTxn.gettDate()+timeCorrection );
		
		log.debug( "Query: "+ps.toString() );
		
		rs = ps.executeQuery();
		if( !rs.next() ) {
			log.debug( "No result" );
			out.setMsg( "No result found." );
			outTxn.getTotalRevenue().setValue( "0" );
			return out;
		}
		
		log.debug( "Next is not null." );
		if( rs.getString( "total_revenue" ) == null ){
			outTxn.getTotalRevenue().setValue( "0" );
		}else {
			outTxn.getTotalRevenue().setValue( rs.getString( "total_revenue" ) );
		}
		
		out.setStatusCode( 1 );
		out.setMsg( "Txn meta computed successfully." );
		
		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	@SuppressWarnings("unchecked")
	public Out setDashMeta( In in, Item record ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null; out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
	
		String q = "select COUNT( t.reference_no ) as total_txn_success FROM transactions as t WHERE ( t.status_code_id != '2100' OR  t.status_code_id != '1100' OR  t.status_code_id != '3100'  OR  t.status_code_id != '100'  ) AND t.last_update > ? and t.last_update < ?";
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			// String timeCorrection = " 23:13:59";
			 
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate()+timeCorrection ) ;
			ps.setString(2, inTxn.gettDate()+timeCorrection );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				// outTxn.setTotalTxnSuccess( rs.getLong( "total_txn_success" ) );
				record.getItemProperty( "totalTxnSuccess" ).setValue( rs.getLong( "total_txn_success" ) );
			}
			
			ps.close();
			rs.close();
			
			q = "select COUNT( t.reference_no ) as total_txn_fail FROM transactions as t WHERE t.last_update > ? and t.last_update < ? and ( t.status_code_id = '04' OR t.status_code_id = '01')";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				//outTxn.setTotalTxnFail( rs.getLong( "total_txn_fail" ) );
				record.getItemProperty( "totalTxnFail" ).setValue( rs.getLong( "total_txn_fail" ) );
			}
			
			ps.close();
			rs.close();
			
			
			// TOKEN
			
			q = "select COUNT( v.reference_no ) as total_token_success FROM vend_req as v WHERE v.last_update > ? and v.last_update < ? and ( v.status = 'COMPLETE' OR v.status = 'REVERSED')";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				// outTxn.setTotalTokenSuccess( rs.getLong( "total_token_success" ) );
				record.getItemProperty( "totalTokenSuccess" ).setValue( rs.getLong( "total_token_success" ) );
			}
			ps.close();
			rs.close();
			
			q = "select COUNT( v.reference_no ) as total_token_fail FROM vend_req as v WHERE v.last_update > ? and v.last_update < ? and ( v.status = 'FAILED' )";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				// outTxn.setTotalTokenFail( rs.getLong( "total_token_fail" ) );
				record.getItemProperty( "totalTokenFail" ).setValue( rs.getLong( "total_token_fail" ) );
			}
			
			
			ps.close();
			rs.close();
			
			
			
			// INFO & REVERSAL
			q = "select COUNT( h.reference_no ) as total_info_success FROM transaction_history as h WHERE h.last_update > ? and h.last_update < ? and ( h.status = 'COMPLETE')";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				// outTxn.setTotalInfoSuccess( rs.getLong( "total_info_success" ) );
				record.getItemProperty( "totalInfoSuccess" ).setValue(  rs.getLong( "total_info_success" ) );
			}
			ps.close();
			rs.close();
			
			
			q = "select COUNT( h.reference_no ) as total_info_fail FROM transaction_history as h WHERE h.last_update > ? and h.last_update < ? and ( h.status = 'FAILED' )";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				//outTxn.setTotalInfoFail( rs.getLong( "total_info_fail" ) );
				record.getItemProperty( "totalInfoFail" ).setValue( rs.getLong( "total_info_fail" ) );
			}
			
			ps.close();
			rs.close();
			
			
			// SMS 
			q = "select COUNT( m.id ) as total_sms_success FROM msg_notification as m WHERE m.datecreated > ? and m.datecreated < ? and ( m.status = 1 )";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				//outTxn.setTotalSMSSuccess( rs.getLong( "total_sms_success" ) );
				record.getItemProperty( "totalSMSSuccess" ).setValue( rs.getLong( "total_sms_success" ) );
			}
			
			ps.close();
			rs.close();
			
			
			q = "select COUNT( m.id ) as total_sms_fail FROM msg_notification as m WHERE m.datecreated > ? and m.datecreated < ? and ( m.status = 100 )";
			ps = conn.prepareStatement( q );
			ps.setString( 1, inTxn.getfDate() ) ;
			ps.setString(2, inTxn.gettDate() );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( rs.next() ) {
				record.getItemProperty( "totalSMSFail" ).setValue( rs.getLong( "total_sms_fail" ) );
			}
			
			out.setStatusCode( 1 );
			out.setMsg( "Dash meta computed successfully." );
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Data engine in mute. " );
			out.setStatusCode( 100 );
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
			
			if( rs.getShort( "profile_id" ) != 1 && rs.getShort( "profile_id" ) != 3  ){
				log.debug( "Not authorized" );
				out.setMsg( "Not authorized [ Insufficient profile permissions ]" );
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
