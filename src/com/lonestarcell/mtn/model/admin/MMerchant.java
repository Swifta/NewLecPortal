package com.lonestarcell.mtn.model.admin;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutMerchant;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutSubscriberTest;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.fundamo.entity.AccountIdentifier001;
import com.lonestarcell.mtn.spring.fundamo.entity.CorporateAccountHolder001;
import com.lonestarcell.mtn.spring.fundamo.entity.Entry001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;
import com.lonestarcell.mtn.spring.fundamo.entity.UserAccount001;
import com.lonestarcell.mtn.spring.fundamo.repo.Entry001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MMerchant extends MDAO implements IModel, Serializable {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(MMerchant.class.getName());

	

	public MMerchant(Long d, String s, String t, ApplicationContext cxt) {
		super(d, s, cxt);
		this.timeCorrection = " " + t;
		log.debug(" MDAO initialized successfully.");
	}

	@Override
	public Out set(In in, BeanItemContainer<AbstractDataBean> container) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		// TODO Check if user session is valid before operation.
		// TODO Check if user profile is authorized
		// TODO This should be implemented in one place, the mother class

		// Set relevant data;
		// From entity to UI component datasource convenience bean.

		OutSubscriber outSubscriber = new OutSubscriber();
		container.addBean(outSubscriber);

		out.setStatusCode(1);
		out.setMsg("Data fetch successful.");
		return out;
	}

	
	
	
	@Override
	public Out search(In in, BeanItemContainer<AbstractDataBean> container) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		try {
			Entry001Repo repo = springAppContext
					.getBean(Entry001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Page<Entry001> pages = null;

			Pager pager = springAppContext.getBean(Pager.class);
			
			BData< ? > bInData = in.getData();
			InTxn inTxn = ( InTxn ) bInData.getData();

			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				
				pages = repo.findAll(pager.getPageRequest(inTxn.getPage()));
				
			} else if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
				log.debug( "In date filter: ", this );
				pages = repo.findPageByDateRange(
						pager.getPageRequest(inTxn.getPage() ),
						DateFormatFac.toDate( inTxn.getfDate() ),
						DateFormatFac.toDateUpperBound( inTxn.gettDate() ) );
			}

			if (pages == null) {
				log.debug("Page object is null.");
				out.setMsg("DAO error occured.");
				return out;
			}

			if (pages.getNumberOfElements() == 0) {

				container.addBean( new OutMerchant());
				BData<BeanItemContainer< AbstractDataBean >> bOutData = new BData<>();
				bOutData.setData(container);
				out.setData(bOutData);
				out.setMsg("No records found.");

				return out;
			}

			Iterator<Entry001> itr = pages.getContent().iterator();
			
			Entry001 entry = null;
			Transaction001 t = null;
			UserAccount001 ua = null;
			
			OutMerchant outMerchant = null;
			do {
				entry = itr.next();

				outMerchant = new OutMerchant();
				t = entry.getTransaction001();
				ua = entry.getUserAccount001();
				
				if( ua == null ){
					ua = new UserAccount001();
				}
				CorporateAccountHolder001 corp = ua.getCorporateAccountHolder001();
				
				if( corp == null ){
					corp = new CorporateAccountHolder001();
				}
				
				double amount = ( entry.getAmount()/ 100 );
				outMerchant.setName( corp.getName() );
				outMerchant.setTno( entry.getTransactionNumber()+"" );
				
				List< AccountIdentifier001 > ais = ua.getAccountIdentifier001s();
				
				if( ais != null ){
					Iterator< AccountIdentifier001 > itrAi = ais.iterator();
					
					while( itrAi.hasNext() ){
						AccountIdentifier001 ai = itrAi.next();
						if( ai != null ){
							if( ai.getTypeName().equals( "ACI003" ) ){
								outMerchant.setMsisdn( ai.getName() );
								outMerchant.setType( "ACI003" );
							}
						}
					}
				}
				
				
				outMerchant.setAmount( NumberFormatFac.toMoney( amount + "" ) );
				outMerchant.setStatus( t.getSystemCode().getValue() );
				outMerchant.setChannel( t.getChannel() );
				outMerchant.setDesc( entry.getDescription() );
				outMerchant.setPayee( t.getPayeeAccountNumber() );
				outMerchant.setPayer( t.getPayerAccountNumber() );
				outMerchant.setEntryDate( DateFormatFac.toString( entry.getEntryDate() ) );

				container.addBean(outMerchant);
				

			} while (itr.hasNext());
			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch ( Exception e ) {
			
			container.addBean( new OutSubscriberTest() );
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);
			
			e.printStackTrace();
			out.setMsg( "Data fetch error." );
		}

		return out;
	}
	
	
	@Override
	public Out setExportData(In in, BeanItemContainer<AbstractDataBean> container) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		try {
			Transaction001Repo repo = springAppContext
					.getBean(Transaction001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			List<Transaction001> records = null;

			
			BData< ? > bInData = in.getData();
			InTxn inTxn = ( InTxn ) bInData.getData();
			
			log.debug( "MSub from date:"+inTxn.getfDate(), this );
			log.debug( "MSub to date:"+inTxn.gettDate(), this );

			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				
				records = repo.findAll();
				
			} else if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
				log.debug( "In date filter: ", this );
				records = repo.findAllByDateRange(
						DateFormatFac.toDate( inTxn.getfDate() ),
						DateFormatFac.toDate( inTxn.gettDate() ) );
			}

			if ( records == null ) {
				log.debug("Page object is null.");
				out.setMsg("DAO error occured.");
				return out;
			}

			if ( records.size() == 0) {

				container.addBean( new OutSubscriber() );
				BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
				bOutData.setData(container);
				out.setData(bOutData);
				out.setMsg("No records found.");

				return out;
			}

			Iterator<Transaction001> itr = records.iterator();
			do {
				Transaction001 transaction = itr.next();

				OutSubscriber  outSubscriber = new OutSubscriber();
				
				double amount = ( transaction.getPayeeAmount()/ 100 );
				
				outSubscriber.setAmount( NumberFormatFac.toMoney( amount + "" ) );
				outSubscriber.setPayee(transaction.getPayeeAccountNumber());
				outSubscriber.setPayer(transaction.getPayerAccountNumber());
				outSubscriber.setStatus(transaction.getSystemCode().getValue());
				outSubscriber.setDate( DateFormatFac.toString( transaction.getLastUpdate() ));
				outSubscriber.setTransactionNumber(transaction
						.getTransactionNumber() + "");
				outSubscriber.setType(transaction.getTransactionType001()
						.getSystemCode().getValue());

				container.addBean(outSubscriber);

			} while (itr.hasNext());
			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch ( Exception e ) {
			
			container.addBean(new OutSubscriber() );
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);
			
			e.printStackTrace();
			out.setMsg( "Data fetch error." );
		}

		return out;
	}


	
	
	@Override
	public Out setMeta(In in, OutTxnMeta outSubscriber) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		/*
		 * Perform the following ops. 1. Set total records 2. TODO Set Total
		 * revenue 3. TODO Filter by date:
		 */

		Transaction001Repo repo = (Transaction001Repo) springAppContext
				.getBean(Transaction001Repo.class);
		Page<Transaction001> pages = repo.findAll(new PageRequest(0, 1));
		outSubscriber.getTotalRecord().setValue(pages.getTotalPages() + "");

		out.setStatusCode(1);
		out.setMsg("Txn meta computed successfully.");

		return out;
	} 

	//@Override
	public Out searchMetax(In in, OutTxnMeta outSubscriber) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		BData<?> bInData = in.getData();
		InTxn inTxn = (InTxn) bInData.getData();

		/*
		 * Perform the following ops. 1. Set total records 2. TODO Set Total
		 * revenue 3. TODO Filter by date:
		 */

		Transaction001Repo repo = (Transaction001Repo) springAppContext
				.getBean(Transaction001Repo.class);
		Page<Transaction001> pages = repo.findAll(new PageRequest(0, 1));
		outSubscriber.getTotalRecord().setValue(pages.getTotalPages() + "");
		outSubscriber.getTotalRevenue().setValue(
				(repo.getTotalPayeeAmount() / 100) + "");

		out.setStatusCode(1);
		out.setMsg("Txn meta computed successfully.");

		return out;
	}
	
	
	@Override
	public Out searchMeta(In in, OutTxnMeta meta ) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();
		
		try {
			Entry001Repo repo = springAppContext
					.getBean(Entry001Repo.class);
			if (repo == null) {
				log.debug("Entry001 repo is null");
				out.setMsg("DAO error occured - 1.");
				return out;
			}
			
			BData< ? > bInData = in.getData();
			InTxn inTxn = ( InTxn ) bInData.getData();
			
			long rowCount = 0L;
			double amount = 0D;
			
			log.debug( "MSub from date:"+inTxn.getfDate(), this );
			log.debug( "MSub to date:"+inTxn.gettDate(), this );

			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				
				List< Object[] > lsObj = repo.getTotalAmountAndCountAll();
				if ( lsObj != null) {
					Object[] obj = lsObj.get( 0 );
					rowCount = Long.valueOf( obj[ 1 ].toString() );
					amount =  Double.valueOf( obj[ 0 ].toString() );
				} 
				
			} else if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
				log.debug( "In date filter: ", this );
				
				List< Object[] > lsObj = repo.findByDateRangeAmountAndCount( DateFormatFac.toDate( inTxn.getfDate() ),DateFormatFac.toDateUpperBound( inTxn.gettDate() ) );
				if ( lsObj != null) {
					Object[] obj = lsObj.get( 0 );
					rowCount = Long.valueOf( obj[ 1 ].toString() );
					amount =  Double.valueOf( obj[ 0 ].toString() );
				} 
			}

			
			
			log.info( "Amount: "+amount );
			log.info( "Total: "+rowCount );
			
			meta.getTotalRecord().setValue( rowCount + "");
			meta.getTotalRevenue().setValue(
					( amount / 100) + "");
			
			out.setStatusCode(1);
			out.setMsg("Txn meta computed successfully.");

		} catch ( Exception e ) {
			e.printStackTrace();
			out.setMsg( "Data fetch error." );
		}

		return out;
	}

	@Override
	public Out setExportDataMulti(In in,
			BeanItemContainer<AbstractDataBean> container, Collection<Item> records) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		out = new Out();

		// TODO Handle No record edge case
		/*
		 * rs = ps.executeQuery(); if( !rs.next() ) { log.debug( "No result" );
		 * out.setMsg( "No search result found." );
		 * 
		 * container.addBean( outTxn );
		 * 
		 * BData<BeanItemContainer<OutTxn>> bOutData = new BData<>();
		 * bOutData.setData( container );
		 * 
		 * out.setData( bOutData ); return out; }
		 */

		// TODO Loop things here

		OutSubscriber outSubscriber = new OutSubscriber();

		container.addBean(outSubscriber);

		out.setStatusCode(1);
		out.setMsg("Data fetch successful.");

		return out;
	}

}
