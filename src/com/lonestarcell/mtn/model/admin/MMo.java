package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutMo;
import com.lonestarcell.mtn.service.client.MTNMomoClient;
import com.lonestarcell.mtn.util.Money;

public class MMo extends Model{

	private Logger log = LogManager.getLogger(MMo.class.getName());
	
	private MTNMomoClient momo;
	
	public MMo( Long d, String s) {
		super( d, s );
		
		momo = new MTNMomoClient();
		
		log.debug( " MTN MOMO Client initialized successfully." );


	}

	public Out tokenRetry( In in ) {

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
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			
			BData< ? > bInData = in.getData();
			InMo inMo = (InMo) bInData.getData();
			conn = dataSource.getConnection();
			
			
			 
			//Reverse txn before triggering send sms request
	 
			 String q = "SELECT t.amount, t.rate_id, fx.lsd_value as rate, t.payer_id as msisdn, t.person_id as meter_no, v.status, t.status_code_id as pay_status FROM transactions as t ";
			   q += " JOIN vend_req as v on v.transaction_id = t.reference_no";
			   q += " JOIN exchange_rate as fx on fx.id = t.rate_id";
			   q += " WHERE t.fundamo_id = ? LIMIT 1";
			
			ps = conn.prepareStatement( q );
			ps.setString( 1 , inMo.getMmoId() );
			
			log.debug( ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				out.setMsg( "No such data" );
				return out;
			}
			
			String tokenReqStatus = rs.getString( "status" );
			log.debug( "Token status: "+tokenReqStatus );
			
			
			if( !tokenReqStatus.equals( "PENDING_REVERSAL" ) ) {
				out.setMsg( "Data in invalid state" );
				return out;
			}
			
			inMo.setAcctRef( rs.getString( "meter_no" ) );
			if( rs.getLong( "rate_id" ) == 0 ) {
				
				//Amount going thru ESB should be multiplied by 100.
				inMo.setAmount( Money.format( rs.getDouble( "amount" )*100 ) );
				inMo.setCurrency( "USD" );
				
			} else {
				
				//Amount going thru ESB should be multiplied by 100.
				inMo.setAmount( Money.format( rs.getDouble( "amount" )*rs.getFloat( "rate" )*100 ) );
				inMo.setCurrency( "LRD" );
			}
	
			inMo.setMsisdn( rs.getString( "msisdn" ) );
			
			OutMo outMo = new OutMo();
			 
			 boolean status = momo.setSendAXIOMAxis2Request( inMo );
			 
			 
			if( status ) {
				outMo.setStatus( "1" );
				out.setStatusCode( 1 );
				out.setMsg( "Operation successful." );
			} else {
				out.setMsg( "Token Req. failed" );
			}
			
			
			BData<OutMo> bOutData = new BData<>();
			bOutData.setData( outMo );
			out.setData( bOutData );
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally{
			
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	public Out sendSMS ( In in ) {

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
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			
			 
				
			 OutMo outMo = new OutMo();
			 conn = dataSource.getConnection();
			
			 BData< ? > bInData = in.getData();
			 InMo inMo = (InMo) bInData.getData();
			 
			// Fetch txn details and validate status before issuing send sms call.
	 
			 String q = "SELECT t.amount, t.rate_id, fx.lsd_value as rate, t.payer_id as msisdn, t.person_id as meter_no, v.status, t.status_code_id as pay_status FROM transactions as t ";
			   q += " JOIN vend_req as v on v.transaction_id = t.reference_no";
			   q += " JOIN exchange_rate as fx on fx.id = t.rate_id";
			   q += " WHERE t.fundamo_id = ? LIMIT 1";
			
			ps = conn.prepareStatement( q );
			ps.setString( 1 , inMo.getMmoId() );
			
			log.debug( ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				out.setMsg( "No such data" );
				return out;
			}
			
			String tokenReqStatus = rs.getString( "status" );
			String payStatus = rs.getString( "pay_status" );
			log.debug( "Token status: "+tokenReqStatus );
			
			
			if( !(tokenReqStatus.equals( "COMPLETE" ) 
					||tokenReqStatus.equals( "REVERSED" ) ) ) {
				out.setMsg( "Data in invalid state" );
				return out;
			}
			
			if( !( payStatus.equals( "04" )
					|| payStatus.equals( "03" )
					|| payStatus.equals( "102" )) ) {
				out.setMsg( "Data in invalid state" );
				return out;
			}
			
			inMo.setAcctRef( rs.getString( "meter_no" ) );
			if( rs.getLong( "rate_id" ) == 0 ) {
				
				inMo.setAmount( Money.format( rs.getDouble( "amount" ) ) );
				inMo.setCurrency( "USD" );
				
			} else {
				
				inMo.setAmount( Money.format( rs.getDouble( "amount" )*rs.getFloat( "rate" ) ) );
				inMo.setCurrency( "LRD" );
			}
	
			inMo.setMsisdn( rs.getString( "msisdn" ) );
			
			ps.close();
			
			String newTxnStatus = "03";
			if( tokenReqStatus.equals( "REVERSED" ) )
				newTxnStatus = "102";
			
			q =  "UPDATE transactions as t";
			q += " SET t.status_code_id = ?";
			q += " WHERE t.fundamo_id = ?";
			
			ps = conn.prepareStatement( q );
			ps.setString( 1, newTxnStatus );
			ps.setString( 2, inMo.getMmoId() );
			
			ps.executeUpdate() ;
			
			log.debug( "ReQ. Cur: "+inMo.getCurrency() );
			 
			 boolean status = momo.sendSMS( inMo );
			 log.debug( "SMS Req. status: "+status );
			 
			if( status ) {
				outMo.setStatus( "1" );
				out.setStatusCode( 1 );
				out.setMsg( "Operation successful." );
			} else {
				out.setMsg( "Send SMS failed." );
			}
			
			
			BData<OutMo> bOutData = new BData<>();
			bOutData.setData( outMo );
			out.setData( bOutData );
				
		
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
