package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
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

public class MTxn extends Model {

	private Logger log = LogManager.getLogger(MTxn.class.getName());
	
	private OutTxn outTxn;
	private InTxn inTxn;
	
	public MTxn( Long d, String s ) {
		super( d, s );
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}

		log.debug( " Model initialized successfully." );


	}

	public Out getPaymentToday( In in ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String q = "select distinct t.reference_no as swifta_id, t.fundamo_id as mmo_id, t.person_id as meter_no, t.payer_id as msisdn, m.token_generated as token, t.amount, t.status_code_id as pay_status, (CASE WHEN t.status_code_id = \"01\" THEN \"SMS SENT\" WHEN t.status_code_id = 04 THEN \"SMS FAILED\" WHEN t.status_code_id = 03 THEN \"SDP TIMEOUT\" WHEN t.status_code_id = 102 THEN \"SDP TIMEOUT\" ELSE \"UNKNOWN\" END ) as status_desc, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") as date  from transactions as t join msg_notification as m on m.transactionhistory_id = t.reference_no where (t.status_code_id = 01 or t.status_code_id = 04 or t.status_code_id = 03 or t.status_code_id = \"102\") and t.last_update > ? and t.last_update < ? order by t.last_update DESC limit 10";
		
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
	
	
	
	public Out setPaymentToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		//String q = "select DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, t.person_id as meter_no, t.amount, t.fundamo_id as mmo_id, t.payer_id as msisdn, status_code_id as pay_status, t.reference_no as swifta_id, fx.lsd_value as rate   from transactions as t left join exchange_rate as fx on fx.id = t.rate_id where t.last_update > ? and t.last_update < ? order by t.last_update DESC LIMIT ?, ?";
		String q = "select distinct t.reference_no as swifta_id, t.fundamo_id as mmo_id, t.person_id as meter_no, t.payer_id as msisdn, m.token_generated as token, t.amount, t.status_code_id as pay_status, IF(t.rate_id  = 0, \"USD\", \"LRD\") as req_currency, DATE_FORMAT(t.last_update, \"%Y-%m-%j %T\") as date  from transactions as t join msg_notification as m on m.transactionhistory_id = t.reference_no where (t.status_code_id = 01 or t.status_code_id = 04 or t.status_code_id = 03 or t.status_code_id = \"102\") and t.last_update > ? and t.last_update < ? order by t.last_update DESC LIMIT ?, ?";
		
		
		
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
			 int pageMin = 1;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			 String timeCorrection = " 23:13:59";
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
	

	
	
	public Out setTxnToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
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
			 int pageMin = 1;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			 String timeCorrection = " 23:13:59";
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
	

	@SuppressWarnings("unchecked")
	public Out refreshTxnRecord( Item record ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
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
		
		Connection conn = null;
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
			
			if( max == 0 ) {
				out.setStatusCode( 1 );
				out.setMsg( "Refresh successful for all selected records." );
			}else if( max < 4){
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
		
		Connection conn = null;
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
	

	public Out setTokenToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
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
			 int pageMin = 1;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			 String timeCorrection = " 23:13:59";
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
	

	public Out setInfoRetryToday( In in, BeanItemContainer<OutTxn> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		
		//String q = "select DATE_FORMAT( t.last_update, '%Y-%m-%j %T' ) as date, t.person_id as meter_no, t.amount, t.fundamo_id as mmo_id, t.payer_id as msisdn, status_code_id as pay_status, t.reference_no as swifta_id, fx.lsd_value as rate   from transactions as t left join exchange_rate as fx on fx.id = t.rate_id where t.last_update > ? and t.last_update < ? order by t.last_update DESC LIMIT ?, ?";
		
		String q = "SELECT v.reference_no as itron_id, v.meter_no, v.transaction_type, v.status as token_status, DATE_FORMAT( v.last_update, '%Y-%m-%j %T' ) as date, v.transaction_id as swifta_id, ( v.repCount + 1 ) as req_count, v.amount FROM transaction_history AS v WHERE v.last_update > ? AND v.last_update < ? order by v.last_update DESC LIMIT ?, ?;";

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
			 int pageMin = 1;
			 if( page > 1) {
				 pageMin =  ( page - 1 )*pageLength + 1;
			 }
			 
			 
			 // TODO Delete Test Data
			 String timeCorrection = " 23:13:59";
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
	
	
	
	public Out setTxnMeta( In in, OutTxnMeta outTxn ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
	
		String q = "select COUNT( t.reference_no ) as total_records FROM transactions as t WHERE t.last_update > ? and t.last_update < ?";
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			String timeCorrection = " 23:13:59";
			 
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
	
	
	
	
		
	
}
