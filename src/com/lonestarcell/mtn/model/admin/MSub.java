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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.ExportSubscriber;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutSubscriberTest;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.DateFormatFacRuntime;
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MSub extends MDAO implements IModel<Transaction001Repo>,
		Serializable {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(MSub.class.getName());

	public MSub(Long d, String s, String t, ApplicationContext cxt) {
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
			boolean isPgNav = inTxn.isPgNav();
			inTxn.setPgNav(false);

			// [ Initialize page & revenue on any db call??? ] Noooo... only on
			// some calls.
			if (!inTxn.isExportOp()) {
				if (!isPgNav) {
					OutTxnMeta meta = inTxn.getMeta();
					meta.getTotalRecord().setValue("0");
					meta.getTotalRevenue().setValue("0.00");
				}
			}

			Transaction001Repo repo = springAppContext
					.getBean(Transaction001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Page<Transaction001> pages = null;

			Pager pager = springAppContext.getBean(Pager.class);
			Map<String, Object> searchMap = inTxn.getSearchMap();
			Set<String> searchKeySet = searchMap.keySet();

			log.debug("MSub from date:" + inTxn.getfDate(), this);
			log.debug("MSub to date:" + inTxn.gettDate(), this);

			Pageable pgR = null;
			BeanItemContainer<OutSubscriber> exportRawData = null;
			double tAmount = 0D;
			long rowCount = 0L;

			// Date fall back

			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				inTxn.setfDate("2010-02-01");
				inTxn.settDate("2010-02-03");
			}

			Date fDate = DateFormatFacRuntime.toDate(inTxn.getfDate());

			if (inTxn.isExportOp()) {
				fDate = this.getExportFDate(inTxn, repo);
				pgR = pager.getPageRequest(0, inTxn.getExportPgLen());
				exportRawData = new BeanItemContainer<>(OutSubscriber.class);
			} else {
				pgR = pager.getPageRequest(inTxn.getPage());
			}

			boolean isSearch = false;

			if (searchKeySet.size() != 0) {
				if (searchKeySet.contains("column1")) {

					Object val = searchMap.get("column1");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						BigDecimal tNo = BigDecimal.valueOf(Long
								.valueOf((String) val));
						pages = repo.findPageByTransactionNumber(pgR, tNo);
						if (!inTxn.isExportOp()) {
							if (!isPgNav)
								tAmount = repo
										.findPageByTransactionNumberAmount(tNo);
						}

					}

				} else if (searchKeySet.contains("column5")) {

					Object val = searchMap.get("column5");

					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByPayerAccountNumber(pgR,
								(String) val, fDate, DateFormatFac
										.toDateUpperBound(inTxn.gettDate()));
						if (!inTxn.isExportOp()) {
							if (!isPgNav)
								tAmount = repo
										.findPageByPayerAccountNumberAmount(
												(String) val, fDate,
												DateFormatFac
														.toDateUpperBound(inTxn
																.gettDate()));
						}
					}

				} else if (searchKeySet.contains("column6")) {

					Object val = searchMap.get("column6");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByPayeeAccountNumber(pgR,
								(String) val, fDate, DateFormatFac
										.toDateUpperBound(inTxn.gettDate()));
						if (!inTxn.isExportOp()) {
							if (!isPgNav)
								tAmount = repo
										.findPageByPayeeAccountNumberAmount(
												(String) val, fDate,
												DateFormatFac
														.toDateUpperBound(inTxn
																.gettDate()));
						}
					}

				}

			}

			if (!isSearch) {
				if (inTxn.getfDate() != null && inTxn.gettDate() != null) {

					pages = repo.findPageByDateRange(pgR, fDate,
							DateFormatFac.toDateUpperBound(inTxn.gettDate()));

					// Amount should not be called in data export
					if ( !inTxn.isExportOp() ) {
						if (!isPgNav)
							tAmount = repo.findPageByDateRangeAmount(fDate,
									DateFormatFac.toDateUpperBound(inTxn
											.gettDate()));
					}
				}
			}

			if (pages == null) {
				log.info("Page object is null.");
				out.setMsg("DAO error occured.");
				return out;
			}

			if (pages.getNumberOfElements() == 0) {

				log.info("Record count is 0.");
				container.addBean(new OutSubscriberTest());
				BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
				bOutData.setData(container);
				out.setData(bOutData);
				out.setMsg("No records found.");

				return out;
			}

			rowCount = pages.getTotalElements();
			log.info("Fetched record count: " + rowCount);
			Iterator<Transaction001> itr = pages.getContent().iterator();
			do {
				Transaction001 transaction = itr.next();

				OutSubscriber outSubscriber = new OutSubscriber();

				double amount = (transaction.getPayeeAmount() / 100);

				outSubscriber.setTransactionNumber(transaction
						.getTransactionNumber() + "");
				outSubscriber.setType(transaction.getTransactionType001()
						.getSystemCode().getValue());

				if (inTxn.isExportOp())
					outSubscriber.setAmount(amount + "");
				else
					outSubscriber.setAmount(NumberFormatFac
							.toMoney(amount + ""));

				outSubscriber.setStatus(transaction.getSystemCode().getValue());
				outSubscriber.setPayer(transaction.getPayerAccountNumber());
				outSubscriber.setPayee(transaction.getPayeeAccountNumber());
				outSubscriber.setDate(DateFormatFac.toString(transaction
						.getLastUpdate()));

				container.addBean(outSubscriber);
				if (inTxn.isExportOp())
					exportRawData.addBean(outSubscriber);

			} while (itr.hasNext());

			if (inTxn.isExportOp()) {
				BData<BeanItemContainer<OutSubscriber>> bData = new BData<>();
				bData.setData(exportRawData);
				out.setData(bData);
			} else {

				if (!isPgNav) {
					OutTxnMeta meta = inTxn.getMeta();
					meta.getTotalRecord().setValue(rowCount + "");
					meta.getTotalRevenue().setValue((tAmount / 100) + "");
				}
			}

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
	public Date getExportFDate(InTxn inTxn, Transaction001Repo repo) {

		int fromPgNo = inTxn.getExportFPgNo();
		log.info("In export F-PgNo " + fromPgNo);

		int excludePgNo = fromPgNo - 1;
		if (fromPgNo <= 1) {
			excludePgNo = 1;
			fromPgNo = 1;
		}

		// - find max date in excludePgNo page of that.
		Page<Transaction001> expoExcludePage = repo.findPageByDateRange(
				new Pager().getPageRequest(excludePgNo),
				DateFormatFacRuntime.toDate(inTxn.getfDate()),
				DateFormatFacRuntime.toDateUpperBound(inTxn.gettDate()));
		Date expoFDate = null;
		int tElements = expoExcludePage.getNumberOfElements();

		// - Get fast date of 1st date if fromPgNo == 1, else, get last date of
		// current page
		if (fromPgNo == 1)
			expoFDate = expoExcludePage.getContent().get(0).getLastUpdate();
		else
			expoFDate = expoExcludePage.getContent().get(tElements - 1)
					.getLastUpdate();
		// - Probable latest date in exclude page [ still under testing ]
		log.info("Export F-Date?: " + expoFDate.toString());
		return expoFDate;
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

			log.info("Export pg len: " + exportPgLen);
			log.info("Export start page: " + inTxn.getPage());

			inTxn.setExportPgLen(exportPgLen);
			inTxn.setExportOp(true);

			out = this.search(in, container);
			inTxn.setExportOp(false);

			log.debug("Feeder function returned. ");
			if (out.getStatusCode() != 1)
				return out;

			log.debug("Proceeding to package for export. ");
			// TODO Repackage data for export

			ModelMapper packer = springAppContext.getBean(ModelMapper.class);

			BeanItemContainer<OutSubscriber> rawData = (BeanItemContainer<OutSubscriber>) out
					.getData().getData();
			Iterator<OutSubscriber> itrRaw = rawData.getItemIds().iterator();
			BeanItemContainer<ExportSubscriber> c = new BeanItemContainer<>(
					ExportSubscriber.class);
			while (itrRaw.hasNext()) {
				OutSubscriber tRaw = itrRaw.next();
				ExportSubscriber t = packer.map(tRaw, ExportSubscriber.class);
				c.addBean(t);
			}

			BData<BeanItemContainer<ExportSubscriber>> bData = new BData<>();
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
	public Out setMeta(In in, OutTxnMeta meta) {
		// TODO Double check with original setTxnMeta, check for any variance
		// TODO I did check, can't tell the difference at the moment.
		return this.searchMeta(in, meta);
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
			Transaction001Repo repo = springAppContext
					.getBean(Transaction001Repo.class);
			if (repo == null) {
				log.debug("Transaction001 repo is null");
				out.setMsg("DAO error occured - 1.");
				return out;
			}

			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();

			long rowCount = 0L;
			double amount = 0D;

			log.debug("MSub from date:" + inTxn.getfDate(), this);
			log.debug("MSub to date:" + inTxn.gettDate(), this);

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
			}

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
		// TODO Auto-generated method stub
		return null;
	}

}
