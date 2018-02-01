package com.lonestarcell.mtn.model.admin;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.ExportLedger;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutLedger;
import com.lonestarcell.mtn.bean.OutMerchant;
import com.lonestarcell.mtn.bean.OutSubReg;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;
import com.lonestarcell.mtn.spring.fundamo.repo.LedgerAccount001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MLedger extends MDAO implements IModel, Serializable {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(MLedger.class.getName());

	public MLedger(Long d, String s, String t, ApplicationContext cxt) {
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
			
			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();

			// Initialize page & revenue on any db call.
			if( !inTxn.isExportOp() ){
				OutTxnMeta meta = inTxn.getMeta();
				meta.getTotalRecord().setValue( "0" );
				meta.getTotalRevenue().setValue( "0.00" );
			}
			
			LedgerAccount001Repo repo = springAppContext
					.getBean(LedgerAccount001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Page<Object[]> pages = null;

			Pager pager = springAppContext.getBean(Pager.class);

			
			Map<String, Object> searchMap = inTxn.getSearchMap();
			Set<String> searchKeySet = searchMap.keySet();
			
			double tAmount = 0D;
			long rowCount = 0L;
			
			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				inTxn.setfDate("2010-02-01");
				inTxn.settDate("2010-02-03");
			}
			
			

			Pageable pgR = null;

			BeanItemContainer<OutLedger> exportRawData = null;

			if (inTxn.isExportOp()) {
				pgR = pager.getPageRequest(inTxn.getPage(),
						inTxn.getExportPgLen());
				exportRawData = new BeanItemContainer<>(OutLedger.class);
			} else {
				pgR = pager.getPageRequest(inTxn.getPage());
			}

			boolean isSearch = false;
			
			if (searchKeySet.size() != 0) {
				if (searchKeySet.contains("column1")) {

					Object val = searchMap.get("column1");

					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.getAllSumByAccNo(pgR,
								(String) val, DateFormatFac.toDate(inTxn.getfDate()),
								DateFormatFac.toDate(inTxn.gettDate()));
					}

				} else if (searchKeySet.contains("column2")) {

					Object val = searchMap.get("column2");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.getAllSumByName(pgR,
								(String) val, DateFormatFac.toDate(inTxn.getfDate()),
								DateFormatFac.toDate(inTxn.gettDate()));
					}

				}

			}

			if (!isSearch) {
				if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
					log.debug("In date filter: ", this);
					pages = repo.getAllSumByDateRange(pgR,
							DateFormatFac.toDate(inTxn.getfDate()),
							DateFormatFac.toDate(inTxn.gettDate()));
				}

			}

			if (pages == null) {
				log.debug("Page object is null.");
				out.setMsg("DAO error occured.");
				return out;
			}

			if (pages.getNumberOfElements() == 0) {

				container.addBean(new OutMerchant());
				BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
				bOutData.setData(container);
				out.setData(bOutData);
				out.setMsg("No records found.");

				return out;
			}

			rowCount = pages.getTotalElements();
			Iterator<Object[]> itr = pages.getContent().iterator();

			Object[] obj = null;

			OutLedger outLedger = null;
			do {
				obj = itr.next();

				outLedger = new OutLedger();

				outLedger.setAccNo(obj[0].toString());
				outLedger.setName(obj[1].toString());
				outLedger.setAmount(NumberFormatFac.toMoney(String
						.valueOf((Double.valueOf(obj[2].toString()) / 100))));
				outLedger.setDate(obj[3].toString());

				container.addBean(outLedger);
				if (inTxn.isExportOp())
					exportRawData.addBean(outLedger);

			} while (itr.hasNext());

			if (inTxn.isExportOp()) {
				BData<BeanItemContainer<OutLedger>> bData = new BData<>();
				bData.setData(exportRawData);
				out.setData(bData);
			} else {
				OutTxnMeta meta = inTxn.getMeta();
				meta.getTotalRecord().setValue(rowCount + "");
				meta.getTotalRevenue().setValue((tAmount / 100) + "");
			}


			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch (Exception e) {

			container.addBean(new OutSubReg());
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);

			e.printStackTrace();
			out.setMsg("Data fetch error");
			log.error(e.getMessage(), this);
		}

		return out;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Out setExportData(In in,
			BeanItemContainer<AbstractDataBean> container) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		try {

			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();

			log.debug("Page no: " + inTxn.getPage());
			log.debug("Page export limit: " + inTxn.getPageExportLimit());
			int exportPgLen = (int) Math.ceil(inTxn.getPageSize()
					* inTxn.getPageExportLimit());

			inTxn.setExportPgLen(exportPgLen);
			inTxn.setExportOp(true);

			out = this.search(in, container);
			inTxn.setExportOp(false);

			if (out.getStatusCode() != 1)
				return out;

			// TODO Repackage data for export

			ModelMapper packer = springAppContext.getBean(ModelMapper.class);

			BeanItemContainer<OutLedger> rawData = (BeanItemContainer<OutLedger>) out
					.getData().getData();
			Iterator<OutLedger> itrRaw = rawData.getItemIds().iterator();
			BeanItemContainer<ExportLedger> c = new BeanItemContainer<>(
					ExportLedger.class);
			while (itrRaw.hasNext()) {
				OutLedger tRaw = itrRaw.next();
				ExportLedger t = packer.map(tRaw, ExportLedger.class);
				c.addBean(t);
			}

			BData<BeanItemContainer<ExportLedger>> bData = new BData<>();
			bData.setData(c);
			out.setData(bData);
			out.setStatusCode(1);
			out.setMsg("Export data set.");

		} catch (Exception e) {

			container.addBean(new OutSubscriber());
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);

			e.printStackTrace();
			out.setMsg("Data fetch error - 1");
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

		try {

			out = this.checkAuthorization();
			if (out.getStatusCode() != 1) {
				out.setStatusCode(100);
				return out;
			}

			out = new Out();

			LedgerAccount001Repo repo = springAppContext
					.getBean(LedgerAccount001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Page<Object[]> pages = null;

			Pager pager = springAppContext.getBean(Pager.class);

			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();

			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {

				pages = repo.getAllSum(pager.getPageRequest(inTxn.getPage()));

			} else if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
				log.debug("In date filter: ", this);
				pages = repo.getAllSumByDateRange(
						pager.getPageRequest(inTxn.getPage()),
						DateFormatFac.toDate(inTxn.getfDate()),
						DateFormatFac.toDate(inTxn.gettDate()));
			}

			if (pages == null) {
				log.debug("Page object is null.");
				out.setMsg("DAO error occured.");
				return out;
			}

			outSubscriber.getTotalRecord().setValue(
					pages.getTotalElements() + "");

			out.setStatusCode(1);
			out.setMsg("Txn meta computed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			out.setStatusCode(100);
			out.setMsg("Error loading meta data..");

		}

		return out;
	}

	@Override
	public Out setExportDataMulti(In in,
			BeanItemContainer<AbstractDataBean> container,
			Collection<Item> records) {

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
