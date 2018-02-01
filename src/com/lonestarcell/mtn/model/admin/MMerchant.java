package com.lonestarcell.mtn.model.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import com.lonestarcell.mtn.bean.ExportMerchant;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutMerchant;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutSubscriberTest;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.DateFormatFacRuntime;
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

public class MMerchant extends MDAO implements IModel< Entry001Repo >, Serializable {

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
			
			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();

			// Initialize page & revenue on any db call.
			if( !inTxn.isExportOp() ){
				OutTxnMeta meta = inTxn.getMeta();
				meta.getTotalRecord().setValue( "0" );
				meta.getTotalRevenue().setValue( "0.00" );
			}
			
			Entry001Repo repo = springAppContext.getBean(Entry001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Page<Entry001> pages = null;

			Pager pager = springAppContext.getBean(Pager.class);
			BeanItemContainer<OutMerchant> exportRawData = null;

			Map<String, Object> searchMap = inTxn.getSearchMap();
			Set<String> searchKeySet = searchMap.keySet();

			Pageable pgR = null;
			double tAmount = 0D;
			long rowCount = 0L;
			
			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				inTxn.setfDate("2010-02-01");
				inTxn.settDate("2010-02-03");
			}
			
			Date fDate = DateFormatFacRuntime.toDate( inTxn.getfDate() );

			if (inTxn.isExportOp()) {
				pgR = pager.getPageRequest(inTxn.getPage(),
						inTxn.getExportPgLen());
				fDate = this.getExportFDate(inTxn, repo );
				exportRawData = new BeanItemContainer<>(OutMerchant.class);
			} else {
				pgR = pager.getPageRequest(inTxn.getPage());
			}

			boolean isSearch = false;

			if (searchKeySet.size() != 0) {
				if (searchKeySet.contains("column3")) {

					Object val = searchMap.get("column3");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						BigDecimal tNo = BigDecimal.valueOf(Long
								.valueOf((String) val));
						pages = repo.findPageByTransactionNumber(pgR, tNo);
						
						if( !inTxn.isExportOp() )
						tAmount = repo.findPageByTransactionNumberAmount(tNo);

					}

				} else if (searchKeySet.contains("column9")) {

					Object val = searchMap.get("column9");

					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByPayerAccountNumber(pgR,
								(String) val, fDate,
								DateFormatFac.toDateUpperBound(inTxn.gettDate()));
						
						if( !inTxn.isExportOp() )
						tAmount = repo
								.findPageByPayerAccountNumberAmount((String) val, fDate,
										DateFormatFac.toDateUpperBound(inTxn.gettDate()));
					}

				} else if (searchKeySet.contains("column10")) {

					Object val = searchMap.get("column10");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByPayeeAccountNumber(pgR,
								(String) val, fDate,
								DateFormatFac.toDateUpperBound(inTxn.gettDate()));
						
						if( !inTxn.isExportOp() )
						tAmount = repo
								.findPageByPayeeAccountNumberAmount((String) val, fDate,
										DateFormatFac.toDateUpperBound(inTxn.gettDate()));
					}

				}

			}

			if (!isSearch) {

				if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
					log.debug("In date filter: ", this);
					pages = repo.findPageByDateRange(pgR,
							fDate,
							DateFormatFac.toDateUpperBound(inTxn.gettDate()));
					
					if( !inTxn.isExportOp() )
					tAmount = repo.findPageByDateRangeAmount(
							fDate,
							DateFormatFac.toDateUpperBound(inTxn.gettDate()));
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

			Iterator<Entry001> itr = pages.getContent().iterator();

			Entry001 entry = null;
			Transaction001 t = null;
			UserAccount001 ua = null;
			OutMerchant outMerchant = null;

			rowCount = pages.getTotalElements();
			log.debug( "row count: "+rowCount, this );

			do {
				entry = itr.next();

				outMerchant = new OutMerchant();
				t = entry.getTransaction001();
				ua = entry.getUserAccount001();

				if (ua == null) {
					ua = new UserAccount001();
				}
				CorporateAccountHolder001 corp = ua
						.getCorporateAccountHolder001();

				if (corp == null) {
					corp = new CorporateAccountHolder001();
				}

				double amount = (entry.getAmount() / 100);
				outMerchant.setName(corp.getName());
				outMerchant.setTno(entry.getTransactionNumber() + "");

				List<AccountIdentifier001> ais = ua.getAccountIdentifier001s();

				if (ais != null) {
					Iterator<AccountIdentifier001> itrAi = ais.iterator();

					while (itrAi.hasNext()) {
						AccountIdentifier001 ai = itrAi.next();
						if (ai != null) {
							if (ai.getTypeName().equals("ACI003")) {
								outMerchant.setMsisdn(ai.getName());
								outMerchant.setType("ACI003");
							}
						}
					}
				}

				if ( inTxn.isExportOp() )
					outMerchant.setAmount(amount + "");
				else
					outMerchant.setAmount(NumberFormatFac
							.toMoney(amount + ""));
				
				//outMerchant.setAmount(NumberFormatFac.toMoney(amount + ""));
				outMerchant.setStatus(t.getSystemCode().getValue());
				outMerchant.setChannel(t.getChannel());
				outMerchant.setDesc(entry.getDescription());
				outMerchant.setPayee(t.getPayeeAccountNumber());
				outMerchant.setPayer(t.getPayerAccountNumber());
				outMerchant.setEntryDate(DateFormatFac.toString(entry
						.getEntryDate()));

				container.addBean(outMerchant);
				if (inTxn.isExportOp())
					exportRawData.addBean(outMerchant);

			} while (itr.hasNext());

			if (inTxn.isExportOp()) {
				BData<BeanItemContainer<OutMerchant>> bData = new BData<>();
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

			container.addBean(new OutSubscriberTest());
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);

			e.printStackTrace();
			out.setMsg("Data fetch error.");
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

			BeanItemContainer<OutMerchant> rawData = (BeanItemContainer<OutMerchant>) out
					.getData().getData();
			Iterator<OutMerchant> itrRaw = rawData.getItemIds().iterator();
			BeanItemContainer<ExportMerchant> c = new BeanItemContainer<>(
					ExportMerchant.class);
			while (itrRaw.hasNext()) {
				OutMerchant tRaw = itrRaw.next();
				// Swap payer for description
				String payee = tRaw.getColumn10();
				ExportMerchant t = packer.map(tRaw, ExportMerchant.class);
				t.setColumn8( payee );
				
				c.addBean(t);
			}

			BData<BeanItemContainer<ExportMerchant>> bData = new BData<>();
			bData.setData(c);
			out.setData(bData);

			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch (Exception e) {

			container.addBean(new OutSubscriber());
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);

			e.printStackTrace();
			out.setMsg("Data fetch error.");
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

	// @Override
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
	public Out searchMeta(In in, OutTxnMeta meta) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}
		out = new Out();

		try {
			Entry001Repo repo = springAppContext.getBean(Entry001Repo.class);
			if (repo == null) {
				log.debug("Entry001 repo is null");
				out.setMsg("DAO error occured - 1.");
				return out;
			}

			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();

			long rowCount = 0L;
			double amount = 0D;

			log.debug("MSub from date:" + inTxn.getfDate(), this);
			log.debug("MSub to date:" + inTxn.gettDate(), this);

			/*
			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {

				List<Object[]> lsObj = repo.getTotalAmountAndCountAll();
				if (lsObj != null) {
					Object[] obj = lsObj.get(0);
					rowCount = Long.valueOf(obj[1].toString());
					amount = Double.valueOf(obj[0].toString());
				}

			} else if (inTxn.getfDate() != null && inTxn.gettDate() != null) {
				log.debug("In date filter: ", this);

				List<Object[]> lsObj = repo.findByDateRangeAmountAndCount(
						DateFormatFac.toDate(inTxn.getfDate()),
						DateFormatFac.toDateUpperBound(inTxn.gettDate()));
				if (lsObj != null) {
					Object[] obj = lsObj.get(0);
					rowCount = Long.valueOf(obj[1].toString());
					amount = Double.valueOf(obj[0].toString());
				}
			} */

			log.info("Amount: " + amount);
			log.info("Total: " + rowCount);

			meta.getTotalRecord().setValue(rowCount + "");
			meta.getTotalRevenue().setValue((amount / 100) + "");

			out.setStatusCode(1);
			out.setMsg("Txn meta computed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			out.setMsg("Data fetch error.");
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
	
	
	@Override
	public Date getExportFDate( InTxn inTxn, Entry001Repo repo ){
		
		int fromPgNo = inTxn.getExportFPgNo();
		log.info( "In export F-PgNo "+fromPgNo );
		int excludePgNo = fromPgNo - 1;
		if( fromPgNo <= 1 ) {
			excludePgNo = 1;
			fromPgNo = 1;
		}
		
		Page< Entry001 > expoExcludePage = repo.findPageByDateRange(
				new Pager().getPageRequest( excludePgNo ), DateFormatFacRuntime.toDate( inTxn.getfDate() ), DateFormatFacRuntime.toDateUpperBound(  inTxn.gettDate() 
						));
		Date expoFDate  = null;
		int tElements = expoExcludePage.getNumberOfElements();
		
		if( fromPgNo == 1 )
			expoFDate = expoExcludePage.getContent().get( 1 ).getLastUpdate();
		else
			expoFDate = expoExcludePage.getContent().get( tElements - 1 ).getLastUpdate();
		log.info( "Export F-Date?: "+expoFDate.toString() );
		
		return expoFDate;
	}

}
