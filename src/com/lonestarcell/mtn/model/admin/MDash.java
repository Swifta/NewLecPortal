package com.lonestarcell.mtn.model.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.spring.fundamo.repo.CorporateAccountHolder001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.Subscriber001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.lonestarcell.mtn.spring.user.repo.UserRepo;
import com.vaadin.data.Item;

public class MDash extends MDAO {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LogManager.getLogger();

	public MDash(Long userAuthId, String userSession,
			ApplicationContext springAppContext) {
		super(userAuthId, userSession, springAppContext);
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Out setDashMetaSub( In in, Item record ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
				
		try {
			
			 
			 Subscriber001Repo repo = springAppContext
						.getBean(Subscriber001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}
			
		
			// String timeCorrection = " 23:13:59";
			// TODO Validate data call response
			// TODO Set datasource
			 
			record.getItemProperty( "totalSubSuccess" ).setValue( repo.countActive() );
		    record.getItemProperty( "totalSubOther" ).setValue( repo.countOther() );
		
			
			
			out.setStatusCode( 1 );
			out.setMsg( "Dash meta computed successfully." );
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Data engine in mute. " );
			out.setStatusCode( 100 );
			e.printStackTrace();
			
		} 
		
		return out;
	}
	
	
	@SuppressWarnings("unchecked")
	public Out setDashMetaMer( In in, Item record ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
				
		try {
			
			 CorporateAccountHolder001Repo repo = springAppContext
						.getBean(CorporateAccountHolder001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}
			
		
			// String timeCorrection = " 23:13:59";
			// TODO Validate data call response
			// TODO Set datasource
			 
			record.getItemProperty( "totalMerSuccess" ).setValue( repo.countActive() );
		    record.getItemProperty( "totalMerOther" ).setValue( repo.countOther() );
		
			
			
			out.setStatusCode( 1 );
			out.setMsg( "Dash meta computed successfully." );
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Data engine in mute. " );
			out.setStatusCode( 100 );
			e.printStackTrace();
			
		} 
		
		return out;
	}
	
	
	@SuppressWarnings("unchecked")
	public Out setDashMetaTxn( In in, Item record ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
				
		try {
			
			
			 
			 Transaction001Repo repo = springAppContext
						.getBean(Transaction001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}
			
		
			// String timeCorrection = " 23:13:59";
			// TODO Validate data call response
			// TODO Set datasource
			 
			record.getItemProperty( "totalTxnSuccess" ).setValue( repo.countSuccess() );
		    record.getItemProperty( "totalTxnOther" ).setValue( repo.countOther() );
		
			
			
			out.setStatusCode( 1 );
			out.setMsg( "Dash meta computed successfully." );
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Data engine in mute. " );
			out.setStatusCode( 100 );
			e.printStackTrace();
			
		} 
		
		return out;
	}
	
	
	@SuppressWarnings("unchecked")
	public Out setDashMetaUser( In in, Item record ) {
		
		Out out = this.checkAuthorization( );
		if( out.getStatusCode() != 1 ){
			out.setStatusCode( 100 );
			return out;
		}
		
				
		try {
			
			 
			 UserRepo repo = springAppContext
						.getBean(UserRepo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}
			
		
			// String timeCorrection = " 23:13:59";
			// TODO Validate data call response
			// TODO Set datasource
			 
			record.getItemProperty( "totalUserSuccess" ).setValue( repo.countActive() );
		    record.getItemProperty( "totalUserOther" ).setValue( repo.countOther() );
		
			
			
			out.setStatusCode( 1 );
			out.setMsg( "Dash meta computed successfully." );
			
			
			

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg( "Data engine in mute. " );
			out.setStatusCode( 100 );
			e.printStackTrace();
			
		} 
		
		return out;
	}
	
	
	
	
	
	

}
