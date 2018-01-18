package com.lonestarcell.mtn.model.admin;

import java.io.Serializable;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MSub extends MDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(MSub.class.getName());

	private OutSubscriber outSubscriber;
	private InTxn inTxn;

	
	public MSub(Long d, String s, String t, ApplicationContext cxt) {
		super(d, s, cxt);
		this.timeCorrection = " " + t;
		log.debug(" MDAO initialized successfully.");
	}

	public Out setTxnToday(In in, BeanItemContainer<OutSubscriber> container) {

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

		outSubscriber = new OutSubscriber();
		container.addBean(outSubscriber);

		out.setStatusCode(1);
		out.setMsg("Data fetch successful.");
		return out;
	}

	public Out searchTxnToday(In in, BeanItemContainer<OutSubscriber> container) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		try {
			Transaction001Repo repo = ( Transaction001Repo ) springAppContext
					.getBean( Transaction001Repo.class );
			if (repo == null) {
				log.debug("Transaction001 repo is null");
			} else {
				log.debug("Transaction001 repo is set");
			}

			// TODO No record conditioning

			/*
			 * if( 1 != 1 ) { log.debug( "No result" ); out.setMsg(
			 * "No search result found." );
			 * 
			 * container.addBean( outSubscriber );
			 * 
			 * BData<BeanItemContainer<OutSubscriber>> bOutData = new BData<>();
			 * bOutData.setData( container );
			 * 
			 * out.setData( bOutData ); return out; }
			 */

			// TODO loop through data here, running this in each loop.

			outSubscriber = new OutSubscriber();
			container.addBean(outSubscriber);

			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch (Exception e) {
			e.printStackTrace();
			out.setMsg( e.getMessage() );
		}

		return out;
	}

	public Out setTxnMeta(In in, OutTxnMeta outSubscriber) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		// String timeCorrection = " 23:13:59";

		// TODO Handle no record found.
		// TODO Set relevant data

		outSubscriber.getTotalRecord().setValue(100 + "");

		out.setStatusCode(1);
		out.setMsg("Txn meta computed successfully.");

		return out;
	}

	public Out searchTxnMeta(In in, OutTxnMeta outSubscriber) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		BData<?> bInData = in.getData();
		inTxn = (InTxn) bInData.getData();

		// TODO No record found.
		// TODO Set relevant data.
		outSubscriber.getTotalRecord().setValue(100 + "");

		out.setStatusCode(1);
		out.setMsg("Txn meta computed successfully.");

		return out;
	}

	public Out setExportDataMultiTxnToday(In in,
			BeanItemContainer<OutSubscriber> container, Collection<Item> records) {

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

		outSubscriber = new OutSubscriber();

		container.addBean(outSubscriber);

		out.setStatusCode(1);
		out.setMsg("Data fetch successful.");

		return out;
	}

}
