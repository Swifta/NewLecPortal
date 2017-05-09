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
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.bean.OutUser;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MUser extends Model {

	private Logger log = LogManager.getLogger(MUser.class.getName());
	
	private OutUser outTxn;
	private InTxn inTxn;
	
	public MUser( Long userId, String userSession ) {
		super( userId, userSession );
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException( "DataSource cannot be null." );
		}
		
		log.debug( " Model initialized successfully." );


	}
	
	
	




	public Out setUsers( In in, BeanItemContainer<OutUser> container ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String q = "SELECT DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u";
		q += " JOIN organization AS o ON o.id = u.organization_id";
		q += " JOIN profile AS p ON p.profile_id = u.profile_id";
		q += " WHERE u.user_id != ?";
		q += " ORDER BY u.date_added, u.last_login DESC";
		q += " LIMIT ?, ?;";
		
		
		try {
			
			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			 conn = dataSource.getConnection();
			 conn.setReadOnly(true);
			 
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			 
			 int page = inTxn.getPage();
			 int pageLength = 5;
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
			// ps.setString( 1, inTxn.getfDate()+timeCorrection );
			// ps.setString( 2, inTxn.gettDate()+timeCorrection );
			ps.setLong( 1, super.userAuthId );
			ps.setInt( 2, pageMin );
			ps.setInt(3, pageLength );
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );
				
				outTxn = new OutUser();
				container.addBean( outTxn );
				
				BData<BeanItemContainer<OutUser>> bOutData = new BData<>();
				bOutData.setData( container );
				
				out.setData( bOutData );
				return out;
			}
			
			
			

			do {
				
				outTxn = new OutUser();
				
				
		
				
				outTxn.setUserId( rs.getLong( "user_id" ) );
				outTxn.setUsername( rs.getString( "username" ) );
				outTxn.setUserSession( rs.getString( "user_session" ) );
				outTxn.setUserStatus( rs.getString( "status" ) );
				
				outTxn.setChangePass( rs.getString( "change_password" ) );
				outTxn.setDateAdded( rs.getString( "date_added" ) );
				outTxn.setLastLogin( rs.getString( "last_login" ) );
				outTxn.setOrg( rs.getString( "org" ) );
				outTxn.setProfile( rs.getString( "profile_name" ) );
				outTxn.setProfileId( rs.getInt( "profile_id" ) );
				
				outTxn.setEmail( rs.getString( "email" )  );
				
				
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
	public Out refreshMultiUserRecord( Collection<Item> records ) {
		
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
			

			sBuilder.append( "SELECT DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u" );
			sBuilder.append( " JOIN organization AS o ON o.id = u.organization_id" );
			sBuilder.append( " JOIN profile AS p ON p.profile_id = u.profile_id" );
			sBuilder.append( " WHERE u.user_id IN ( " );
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( " ) LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "userId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				out.setMsg( "No user data." );
				out.setStatusCode( 100 );
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
					
						record.getItemProperty( "userId" ).setValue( rs.getLong( "user_id" ) );
						record.getItemProperty( "username" ).setValue( rs.getString( "username" ) );
						record.getItemProperty( "userSession" ).setValue( rs.getString( "user_session" ) );
						record.getItemProperty( "userStatus" ).setValue( rs.getString( "status" ) );
						record.getItemProperty( "changePass" ).setValue( rs.getString( "change_password" ) );
						record.getItemProperty( "dateAdded" ).setValue( rs.getString( "date_added" ) );
						record.getItemProperty( "lastLogin" ).setValue( rs.getString( "last_login" ) );
						record.getItemProperty( "org" ).setValue( rs.getString( "org" ) );
						record.getItemProperty( "profile" ).setValue( rs.getString( "profile_name" ) );
						record.getItemProperty( "profileId" ).setValue( rs.getInt( "profile_id" ) );
						record.getItemProperty( "email" ).setValue( rs.getString( "email" ) );
						
						records.remove( record );
						log.debug( "Max: "+max );
						max--;
						break;
				
				}
				
			
			} while( rs.next() );
			
			log.debug( "Max after operation: " );
			
			if( max == 0 ) {
				out.setStatusCode( 1 );
				out.setMsg( "Refresh successful for selected record(s)." );
			}else if( max > 0 ){
				out.setStatusCode( 2 );
				out.setMsg( "Some record(s) were not refreshed. Please try again later." );
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
	
	

	public Out blockMultiUserRecord( Collection<Item> records ) {
		
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
			
			sBuilder.append(  "UPDATE users AS u" );
			sBuilder.append(  " SET u.status = 2, u.user_session = NULL" );
			sBuilder.append(  " WHERE u.user_id IN ( " );
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( " )  AND ( u.status = 1 )  LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly( false );
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "userId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();
			
			out.setStatusCode( 1 );
			out.setMsg( "Blocking selected user(s) was successful." );
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out activateMultiUserRecord( Collection<Item> records ) {
		
		
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
			
			sBuilder.append(  "UPDATE users AS u" );
			sBuilder.append(  " SET u.status = 1, u.user_session = NULL" );
			sBuilder.append(  " WHERE u.user_id IN ( " );
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( " ) AND ( u.status = 2 )  LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly( false );
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "userId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();
			
			out.setStatusCode( 1 );
			out.setMsg( "Activating selected user(s) was successful." );
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out expireSessionMultiUserRecord( Collection<Item> records ) {
		
		
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
			
			sBuilder.append(  "UPDATE users AS u" );
			sBuilder.append(  " SET  u.user_session = NULL" );
			sBuilder.append(  " WHERE u.user_id IN ( " );
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( " ) AND ( u.status = 1 ) LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly( false );
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "userId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();
			
			out.setStatusCode( 1 );
			out.setMsg( "Expiring selected user session(s) was successful." );
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
			out.setMsg( "Could not complete operation. " );
			e.printStackTrace();
			
		} finally {
			connCleanUp( conn, ps, rs );
		}
		
		return out;
	}
	
	
	public Out expirePassMultiUserRecord( Collection<Item> records ) {
		
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
			
			sBuilder.append(  "UPDATE users AS u" );
			sBuilder.append(  " SET  u.change_password = 1, u.user_session = NULL" );
			sBuilder.append(  " WHERE u.user_id IN ( " );
			
			while( max != 0 ) {
				max--;
				if( max == 0 )
					sBuilder.append( "?" );
				else
					sBuilder.append( "?, " );
			}
			
			sBuilder.append( " ) AND ( u.status = 1 OR u.status = 2 ) LIMIT "+records.size() );
			
			conn = dataSource.getConnection();
			conn.setReadOnly( false );
			
			ps = conn.prepareStatement( sBuilder.toString() );
			itr = records.iterator();
			max = records.size();
			while( itr.hasNext() ) {
				ps.setLong( max, Long.valueOf( itr.next().getItemProperty( "userId" ).getValue().toString() ) );
				max--;
			}
		
			
			log.debug( "Query: "+ps.toString() );
			
			ps.executeUpdate();
			
			out.setStatusCode( 1 );
			out.setMsg( "Expiring selected user password(s) was successful." );
			
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
		
		
		Out out = this.checkAuthorization(  );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
	
		String q = "select COUNT( u.user_id ) as total_records FROM users AS u WHERE u.user_id != ?";
				  
		
		try {
			
			 BData< ? > bInData = in.getData();
			 inTxn = (InTxn) bInData.getData();
			
			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			ps = conn.prepareStatement( q );
			ps.setLong( 1, super.userAuthId );
			
			
			log.debug( "Query: "+ps.toString() );
			
			rs = ps.executeQuery();
			if( !rs.next() ) {
				log.debug( "No result" );

				return out;
			}
			
			outTxn.getTotalRecord().setValue( rs.getLong( "total_records" )+"" );
			
			outTxn.getTotalRevenue().setValue( "0" );
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
