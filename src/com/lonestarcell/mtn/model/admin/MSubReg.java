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
import com.lonestarcell.mtn.bean.OutSubReg;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutSubscriberTest;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.fundamo.entity.AccountIdentifier001;
import com.lonestarcell.mtn.spring.fundamo.entity.CorporateAccountHolder001;
import com.lonestarcell.mtn.spring.fundamo.entity.Entry001;
import com.lonestarcell.mtn.spring.fundamo.entity.Person001;
import com.lonestarcell.mtn.spring.fundamo.entity.RegistrationRequestData001;
import com.lonestarcell.mtn.spring.fundamo.entity.Subscriber001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;
import com.lonestarcell.mtn.spring.fundamo.entity.UserAccount001;
import com.lonestarcell.mtn.spring.fundamo.repo.Entry001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.UserAccount001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MSubReg extends MDAO implements IModel, Serializable {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(MSubReg.class.getName());

	

	public MSubReg(Long d, String s, String t, ApplicationContext cxt) {
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
			UserAccount001Repo repo = springAppContext
					.getBean(UserAccount001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Page<UserAccount001> pages = null;

			Pager pager = springAppContext.getBean(Pager.class);
			
			BData< ? > bInData = in.getData();
			InTxn inTxn = ( InTxn ) bInData.getData();

			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				
				pages = repo.findAll(pager.getPageRequest(inTxn.getPage()));
				
			} else if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
				log.debug( "In date filter: ", this );
				pages = repo.findPageByDateRange(
						pager.getPageRequest(inTxn.getPage() ),
						DateFormatFac.toDate( "2014-01-14"), 
						DateFormatFac.toDateUpperBound( "2014-01-14" ) );
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

			Iterator<UserAccount001> itr = pages.getContent().iterator();
			
			
			
			UserAccount001 ua = null;
			Subscriber001 sub = null;
			Person001 per = null;
			RegistrationRequestData001 regData = null;
			
			OutSubReg outSubReg = null;
			do {
				ua = itr.next();

				outSubReg = new OutSubReg();
				
				sub = ua.getSubscriber001();
				if( sub == null )
					sub = new Subscriber001();
				
				per = sub.getPerson001();
				if( per == null )
					per = new Person001();
				
				regData = per.getRegistrationRequestData001();
				if( regData == null )
					regData = new RegistrationRequestData001();
				
				outSubReg.setName( sub.getName() );
				outSubReg.setMsisdn( regData.getMsisdn() );
				outSubReg.setIdNo( regData.getIdNumber() );
				outSubReg.setIdType( regData.getIdType() );
				outSubReg.setDob( DateFormatFac.toString( per.getDateOfBirth() ) );
				outSubReg.setStatus( ua.getSystemCode().getValue() );
				outSubReg.setRegDate( DateFormatFac.toString( sub.getLastUpdate() ) );
				
				

				container.addBean(outSubReg);
				

			} while (itr.hasNext());
			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch ( Exception e ) {
			
			container.addBean( new OutSubReg() );
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);
			
			e.printStackTrace();
			out.setMsg( e.getMessage()+" - Data fetch error - " );
			log.error( e.getMessage(), this );
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
			out.setMsg( "Data fetch error - 1" );
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

	@Override
	public Out searchMeta(In in, OutTxnMeta outSubscriber) {

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
