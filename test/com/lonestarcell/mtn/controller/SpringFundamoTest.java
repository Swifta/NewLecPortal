package com.lonestarcell.mtn.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.controller.main.Person;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.DateFormatFacRuntime;
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigFundamo;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.fundamo.entity.AccountIdentifier001;
import com.lonestarcell.mtn.spring.fundamo.entity.CorporateAccountHolder001;
import com.lonestarcell.mtn.spring.fundamo.entity.Entry001;
import com.lonestarcell.mtn.spring.fundamo.entity.LedgerAccount001;
import com.lonestarcell.mtn.spring.fundamo.entity.RegistrationRequestData001;
import com.lonestarcell.mtn.spring.fundamo.entity.Subscriber001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;
import com.lonestarcell.mtn.spring.fundamo.entity.TransactionType001;
import com.lonestarcell.mtn.spring.fundamo.entity.UserAccount001;
import com.lonestarcell.mtn.spring.fundamo.repo.Entry001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.LedgerAccount001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.RegistrationRequestData001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.Subscriber001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.UserAccount001Repo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class, DataAccessConfigFundamo.class,
		JpaConfig.class })
public class SpringFundamoTest {

	private static Logger log = LogManager.getLogger(SpringFundamoTest.class);
	@Autowired
	private Transaction001Repo repo;

	@Autowired
	private Entry001Repo entryRepo;

	@Autowired
	private UserAccount001Repo repoUserAccount;

	@Autowired
	private Subscriber001Repo subRepo;

	@Autowired
	private LedgerAccount001Repo ledgerRepo;
	
	@Autowired
	private RegistrationRequestData001Repo subRegRepo;
	
	@Autowired
	private Subscriber001Repo reg;

	@Test
	@Ignore
	public void testSayHello() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
	}

	@Test
	@Ignore
	public void testSearchByPayerAccountNumber() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findPageByPayerAccountNumber(
				new PageRequest(0, 10), "413141000000124");
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testSearchByPayeeAccountNumber() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findPageByPayeeAccountNumber(
				new PageRequest(0, 10), "413141000000124");
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testSearchByTransactionNumber() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findPageByTransactionNumber(
				new PageRequest(0, 10), BigDecimal.valueOf(1001));
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testSearchLedgerByAccNo() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", ledgerRepo);
		Page<Object[]> pg = ledgerRepo.getAllSumByAccNo(new PageRequest(0, 10),
				"");
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testSearchLedgerByName() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", ledgerRepo);
		Page<Object[]> pg = ledgerRepo.getAllSumByName(new PageRequest(0, 10),
				"");
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testFindPageByPayerAccountNumber() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findPageByPayerAccountNumber(
				new PageRequest(0, 10), "96");
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testFindPageByPayerAccountNumberAmount() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", entryRepo);
		double amount = entryRepo.findPageByPayerAccountNumberAmount("96");
		Assert.assertNotEquals("No transaction record.", amount, 0);

		log.info("Results Amount: " + amount);
	}

	@Test
	@Ignore
	public void testFindPageByPayeeAccountNumber() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findPageByPayeeAccountNumber(
				new PageRequest(0, 10), "413141000000124");
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testFindPageByTransactionNumber() throws ParseException {

		Assert.assertNotNull("Fundamo repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findPageByTransactionNumber(
				new PageRequest(0, 10), BigDecimal.valueOf(1017));
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.info("Results count: " + pg.getTotalElements());
	}

	@Test
	@Ignore
	public void testGetOnePage() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findAll(new PageRequest(0, 10));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);
		if (pg.getContent().size() > 0) {
			Transaction001 transaction = pg.getContent().get(0);
			TransactionType001 type = transaction.getTransactionType001();
			log.debug(
					"Transaction code "
							+ type.getSystemCode().getCode()
							+ " for transaction no. "
							+ transaction.getTransactionNumber()
							+ " and amount = "
							+ transaction.getPayeeAmount()
							+ " and type = "
							+ transaction.getTransactionType001()
									.getSystemCode().getValue()
							+ " and payee = "
							+ transaction.getPayeeAccountNumber()
							+ " and payer = "
							+ transaction.getPayerAccountNumber()
							+ " and status = "
							+ transaction.getSystemCode().getValue(),
					transaction);
		}

	}

	@Test
	@Ignore
	public void testGetFirstPage() throws ParseException {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findFirstPageByDate(new PageRequest(0,
				20), DateFormatFac.toDate("2010-02-01"));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		Iterator<Transaction001> itr = pg.getContent().iterator();

		while (itr.hasNext()) {

			Transaction001 transaction = itr.next();
			TransactionType001 type = transaction.getTransactionType001();
			log.debug(
					"Transaction code "
							+ type.getSystemCode().getCode()
							+ " for transaction no. "
							+ transaction.getTransactionNumber()
							+ " and amount = "
							+ transaction.getPayeeAmount()
							+ " and type = "
							+ transaction.getTransactionType001()
									.getSystemCode().getValue()
							+ " and payee = " + transaction.getPayeeAmount()
							+ " and payer = "
							+ transaction.getPayerAccountNumber()
							+ " and status = "
							+ transaction.getSystemCode().getValue()
							+ " and status = " + transaction.getLastUpdate(),
					transaction);

		}

	}

	@Test
	@Ignore
	public void testGetFirstPageMer() throws ParseException {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Entry001> pg = entryRepo.findFirstPageByDate(
				new PageRequest(0, 20), DateFormatFac.toDate("2010-02-01"));
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);
		Iterator<Entry001> itr = pg.getContent().iterator();

		log.info("Total elements: " + pg.getTotalElements());
		log.info("No. of elements: " + pg.getNumberOfElements());

		while (itr.hasNext()) {

			Entry001 entry = itr.next();
			log.info("Date: " + entry.getEntryDate());

		}

	}

	@Test
	@Ignore
	public void testGetFirstPageLedger() throws ParseException {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Object[]> pg = ledgerRepo.getFirstPageAllSumByDateRange(
				new PageRequest(0, 20), DateFormatFac.toDate("2010-02-01"));
		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);
		Iterator<Object[]> itr = pg.getContent().iterator();

		log.info("Total elements: " + pg.getTotalElements());
		log.info("No. of elements: " + pg.getNumberOfElements());

		while (itr.hasNext()) {
			Object[] obj = itr.next();
			log.info("Date: " + obj[obj.length - 1]);

		}

	}

	@Test
	@Ignore
	public void testGetByDateRangeAmountAndCount() throws ParseException {
		double amount = 0;
		long rowCount = 0;

		List<Object[]> lsObj = repo.findByDateRangeAmountAndCount(
				DateFormatFac.toDate("2010-02-01"),
				DateFormatFac.toDateUpperBound("2010-02-03"));
		Assert.assertNotNull("Amount - Count obj list is null.", lsObj);

		log.debug("Obj list size: " + lsObj.size());
		if (lsObj != null) {
			Object[] obj = lsObj.get(0);
			rowCount = Long.valueOf(obj[1].toString());
			amount = Double.valueOf(obj[0].toString());
		}

		log.info("Amount: " + amount, this);
		log.info("Count: " + rowCount, this);
	}

	@Test
	@Ignore
	public void testGetOnePageByDateRange() throws ParseException {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findPageByDateRange(
				new PageRequest(0, 1), DateFormatFac.toDate("2014-01-19"),
				DateFormatFac.toDate("2018-01-18"));
		// Page<Transaction001> pg = repo.findAll(new PageRequest(0, 10));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		log.debug("Total records: " + pg.getTotalPages(), this);
		if (pg.getContent().size() > 0) {
			Transaction001 transaction = pg.getContent().get(0);
			TransactionType001 type = transaction.getTransactionType001();
			log.debug(
					"Transaction code "
							+ type.getSystemCode().getCode()
							+ " for transaction no. "
							+ transaction.getTransactionNumber()
							+ " and amount = "
							+ NumberFormatFac.toMoney((transaction
									.getPayeeAmount() / 100) + "")
							+ " and type = "
							+ transaction.getTransactionType001()
									.getSystemCode().getValue()
							+ " and payee = "
							+ transaction.getPayeeAmount()
							+ " and payer = "
							+ transaction.getPayerAccountNumber()
							+ " and timestamp = "
							+ DateFormatFac.toString(transaction
									.getLastUpdate()) + " and status = "
							+ transaction.getSystemCode().getValue(),
					transaction);
		}

	}

	@Test
	@Ignore
	public void testGetTotalPayeeAmount() {

		Assert.assertNotNull("Fundamo repo is null.", repo);
		double totalPayeeAmount = repo.getTotalPayeeAmount();
		log.debug("Total Payee Amount:  " + totalPayeeAmount, this);

	}

	@Test
	@Ignore
	public void testGetTotalTransactionByDateRange() throws ParseException {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		long total = repo.findByDateRangeCount(
				DateFormatFac.toDate("2011-01-01"),
				DateFormatFac.toDate("2012-01-01"));
		log.debug("Total transaction count:  " + total, this);
	}

	@Test
	@Ignore
	public void testGetTotalAmountByDateRange() throws ParseException {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		double total = repo.findByDateRangeAmount(
				DateFormatFac.toDate("2011-01-01"),
				DateFormatFac.toDate("2012-01-01"));
		log.debug("Total transaction amount:  " + total, this);
	}

	@Test
	@Ignore
	public void testGetInitialDateSub() throws ParseException {

		long t1 = new Date().getTime();
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Date date = repo.getInitialDate();

		log.info("Very initial date:  " + DateFormatFacRuntime.toString(date),
				this);

		long tt = new Date().getTime() - t1;
		log.info("Time taken by query: " + (tt) + " ms(s)");
	}

	@Test
	@Ignore
	public void testGetInitialDateMer() throws ParseException {

		long t1 = new Date().getTime();
		Assert.assertNotNull("Fundamo repo is null.", entryRepo);
		Date date = entryRepo.findEarliestDate();

		log.info("Very initial date:  " + DateFormatFacRuntime.toString(date),
				this);

		long tt = new Date().getTime() - t1;
		log.info("Time taken by query: " + (tt) + " ms(s)");
	}

	@Test
	@Ignore
	public void testGetInitialDateLedger() throws ParseException {

		long t1 = new Date().getTime();
		Assert.assertNotNull("Fundamo repo is null.", entryRepo);
		Date date = ledgerRepo.findEarliestDate();
		log.info("Very initial date:  " + DateFormatFacRuntime.toString(date),
				this);

		long tt = new Date().getTime() - t1;
		log.info("Time taken by query: " + (tt) + " ms(s)");
	}

	@Test
	@Ignore
	public void testGetEntryCount() {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findAll(new PageRequest(0, 1));
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		log.debug("Entry total count: " + pg.getTotalPages(), pg);

	}

	@Test
	@Ignore
	public void testGetUserAccountCount() {
		Assert.assertNotNull("Fundamo userAccount repo is null.",
				repoUserAccount);
		Page<UserAccount001> pg = repoUserAccount
				.findAll(new PageRequest(0, 1));
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		log.debug("UserAccount total count: " + pg.getTotalPages(), pg);

	}

	@Test
	@Ignore
	@Transactional
	public void testGetOneUserAccount() {
		Assert.assertNotNull("Fundamo entry repo is null.", repoUserAccount);
		Page<UserAccount001> pg = repoUserAccount
				.findAll(new PageRequest(0, 1));
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);

		List<Entry001> entries = pg.getContent().get(0).getEntries();
		log.debug("User account entry count: " + entries.size(), entries);
		// UserAccount001 userAccount = pg.getContent().get( 0
		// ).getUserAccount001();
		// log.debug(
		// "Entry transaction no: "+transaction.getTransactionNumber(),
		// transaction );
		// log.debug(
		// "Entry userAccount no: "+userAccount.getUserAccountNumber(),
		// userAccount );

	}

	@Test
	@Ignore
	@Transactional
	public void testGetOneLedgerAccount() throws ParseException {
		Assert.assertNotNull("Fundamo ledger repo is null.", ledgerRepo);
		Page<Object[]> pages = ledgerRepo.getAllSumByDateRange(
				new Pager().getPageRequest(1),
				DateFormatFac.toDate("2011-06-10"),
				DateFormatFac.toDateUpperBound("2011-06-10"));
		Assert.assertNotEquals("No ledger record.", pages.getTotalPages(), 0);

		List<Object[]> records = pages.getContent();

		Iterator<Object[]> itr = records.iterator();
		while (itr.hasNext()) {
			Object[] record = itr.next();
			log.info("AccNo: " + record[0] + "Name: " + record[1] + "Amount: "
					+ record[2] + "Date: " + record[3], records);
		}

	}
	
	
	@Test
	@Ignore
	@Transactional
	public void testfindPageByDateRangeSubscriberReg() throws ParseException {

		/*Assert.assertNotNull("Fundamo user sub reg. repo is null.",
				subRegRepo);
		Page<RegistrationRequestData001> pg = subRegRepo.findPageByDateRange( new PageRequest(0, 50) );
		Assert.assertNotNull("Page is null.",
				pg);*/
	}
	
	
	@Test
	// @Ignore
	@Transactional
	public void testfindPageByDateRangeReg() throws ParseException {

		Assert.assertNotNull("Fundamo reg. repo is null.",
				reg);
		Page<Subscriber001> pg = reg.findPageByDateRange( new PageRequest(0, 15), DateFormatFac.toDate("2010-01-01"),
				DateFormatFac.toDate("2010-01-30" ) );
		Assert.assertNotNull("Page is null.",
				pg);
		
		Assert.assertNotEquals("Not records.",
				pg.getTotalElements(), 0);
		
		Iterator< Subscriber001 > itr = pg.getContent().iterator();
		while( itr.hasNext() ){
			Subscriber001 sub = itr.next();
			List< RegistrationRequestData001 > dataList = sub.getPerson001().getRegistrationRequestData001s();
			RegistrationRequestData001 data = dataList.get( 0 );
			log.info( "Registratin data count: "+dataList.size(), this );
			Assert.assertNotNull("Data instance is null.",data);
			List< UserAccount001 > uAccList = sub.getUserAccount001s();
			Assert.assertNotNull("User Account list is null.", uAccList );
			log.info( "UserAccount data count: "+uAccList.size(), this );
			
			
			
		}
	}

	@Test
	@Ignore
	@Transactional
	public void testfindPageByDateRangeSubscriber() throws ParseException {

		Assert.assertNotNull("Fundamo user account. repo is null.",
				repoUserAccount);
		Page<UserAccount001> pgu = repoUserAccount.findPageByDateRange(
				new PageRequest(0, 1), DateFormatFac.toDate("2011-01-14"),
				DateFormatFac.toDateUpperBound("2014-01-14"));
		Assert.assertNotEquals("No entry record.", pgu.getTotalPages(), 0);

		int total = pgu.getContent().size();

		for (int x = total - 1; x >= 0; x--) {

			UserAccount001 ua = pgu.getContent().get(x);
			log.debug("User acount count: "
					+ pgu.getSize()
					+ " Name: "
					+ ua.getSubscriber001().getName()
					/*
					+ " MSISDN: "
					+ ua.getSubscriber001().getPerson001()
							.getRegistrationRequestData001().getMsisdn()
					+ " ID NUmber: "
					+ ua.getSubscriber001().getPerson001()
							.getRegistrationRequestData001().getIdNumber() */
					+ " Name: " + ua.getSubscriber001().getName()
					+ " ID TYPE: " + ua.getSystemCode().getValue()
					+ " Date of birth: "
					+ ua.getSubscriber001().getPerson001().getDateOfBirth()
					+ " Date of Reg.: " + ua.getSubscriber001().getLastUpdate()
					+ " Account Status: " + ua.getSystemCode().getValue(), ua);
			// UserAccount001 userAccount = pg.getContent().get( 0
			// ).getUserAccount001();
			// log.debug(
			// "Entry transaction no: "+transaction.getTransactionNumber(),
			// transaction );
			// log.debug(
			// "Entry userAccount no: "+userAccount.getUserAccountNumber(),
			// userAccount );

		}

	}

	/*
	@Test
	@Ignore
	@Transactional
	public void testGetOneSubscriberGroupBy() throws ParseException {

		Assert.assertNotNull("Fundamo user account. repo is null.",
				repoUserAccount);
		Page<Object[]> pgu = repoUserAccount.findPageByDateRangeRaw(
				new PageRequest(0, 100), DateFormatFac.toDate("2011-01-14"),
				DateFormatFac.toDateUpperBound("2014-01-14"));
		Assert.assertNotEquals("No entry record.", pgu.getTotalPages(), 0);

		int total = pgu.getContent().size();

		for (int x = total - 1; x >= 0; x--) {

			Object[] ua = pgu.getContent().get(x);
			log.debug("User acount count: " + pgu.getSize()
					+ " Account Status: " + ua[0], ua);
			// UserAccount001 userAccount = pg.getContent().get( 0
			// ).getUserAccount001();
			// log.debug(
			// "Entry transaction no: "+transaction.getTransactionNumber(),
			// transaction );
			// log.debug(
			// "Entry userAccount no: "+userAccount.getUserAccountNumber(),
			// userAccount );

		}

	} */

	/*
	 * @Test
	 * 
	 * @Ignore public void testGetOneEntry() {
	 * Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
	 * Page<Entry001> pg = entryRepo.findAll(new PageRequest( 0, 1 ) );
	 * Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
	 * 
	 * Transaction001 transaction = pg.getContent().get( 0
	 * ).getTransaction001(); UserAccount001 userAccount = pg.getContent().get(
	 * 0 ).getUserAccount001(); log.debug(
	 * "Entry transaction no: "+transaction.getTransactionNumber(), transaction
	 * ); log.debug(
	 * "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount
	 * );
	 * 
	 * 
	 * }
	 */

	// TODO Get one entry by OID

	@Test
	@Ignore
	@Transactional
	public void testGetOneEntryPageByDateRangeMer() throws ParseException {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findPageByDateRange(
				new PageRequest(0, 15), DateFormatFac.toDate("2010-01-01"),
				DateFormatFac.toDate("2014-12-31"));
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);

		for (int x = pg.getContent().size() - 1; x >= 0; x--) {
			Entry001 entry = pg.getContent().get(x);
			Transaction001 transaction = entry.getTransaction001();
			UserAccount001 userAccount = entry.getUserAccount001();
			CorporateAccountHolder001 corporateAccountHolder001 = null;

			if (userAccount != null) {
				corporateAccountHolder001 = userAccount
						.getCorporateAccountHolder001();

				log.info(
						"Entry transaction no: "
								+ transaction.getTransactionNumber(),
						transaction);
				log.info(
						"Entry userAccount no: "
								+ userAccount.getUserAccountNumber(),
						userAccount);

				if (corporateAccountHolder001 != null) {
					log.info("Entry corp acc. name : "
							+ corporateAccountHolder001.getName(),
							corporateAccountHolder001);
				} else {
					log.info( "Corp is null." );
				}
				// log.debug(
				// "User account MSISDN : "+userAccount.getAccountIdentifier001s().getName(),
				// corporateAccountHolder001 );

				log.info("User account MSISDN count: "
						+ userAccount.getAccountIdentifier001s().size(),
						userAccount.getAccountIdentifier001s());

				userAccount.getAccountIdentifier001s().forEach(
						e -> {
							log.info("MSISDN: " + e.getName() + " of type: "
									+ e.getTypeName(), e);
						});

				log.info("Entry date: " + entry.getEntryDate(), entry);
				log.info(
						"Entry Transaction number: "
								+ entry.getTransactionNumber(), entry);
				log.info("TXN TYPE: "
						+ entry.getEntryType001().getSystemcode().getValue(),
						entry);
				log.debug("Description : " + entry.getDescription(), entry);
				log.debug("Amount : " + entry.getAmount(), entry);
				log.debug(
						"Channel : " + entry.getTransaction001().getChannel(),
						entry.getTransaction001());
				log.debug("Transaction Status : "
						+ entry.getTransaction001().getSystemCode().getValue(),
						entry.getTransaction001());
				log.debug("Payee Account number : "
						+ entry.getTransaction001().getPayeeAccountNumber(),
						entry.getTransaction001());
				log.debug("Payer Account number : "
						+ entry.getTransaction001().getPayerAccountNumber(),
						entry.getTransaction001());

			} else {
				log.error("UserAccount is null, for entry oid: "
						+ entry.getOid());
			}

		}

	}
	
	
	@Test
	@Ignore
	@Transactional
	public void testGetEntryPageByOid() throws ParseException {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		List< Entry001 > eList = entryRepo.findByOid( 312652743311870004L );
		Assert.assertNotNull( "Results list should not be null", eList );
		log.info( "Results count: "+eList.size(), this );
		
		Assert.assertNotEquals( "No transaction record", eList.size(), 0 );
		
		UserAccount001 uAcc = eList.get( 0 ).getUserAccount001();
		Assert.assertNotNull( "User account is null: ", uAcc );
		log.info( "User account OID: "+uAcc.getOid(), uAcc );
		
		
		
				
		
		
	}

	@Test
	@Ignore
	@Transactional
	public void testGetOneEntryPageByDate() throws ParseException {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findPageByDateRange(
				new PageRequest(0, 15), DateFormatFac.toDate("2011-01-14"),
				DateFormatFac.toDateUpperBound("2011-01-14"));
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);

		for (int x = pg.getContent().size() - 1; x >= 0; x--) {
			Entry001 entry = pg.getContent().get(x);
			Transaction001 transaction = entry.getTransaction001();
			UserAccount001 userAccount = entry.getUserAccount001();
			CorporateAccountHolder001 corporateAccountHolder001 = null;
			if (userAccount != null) {
				corporateAccountHolder001 = userAccount
						.getCorporateAccountHolder001();

				log.debug(
						"Entry transaction no: "
								+ transaction.getTransactionNumber(),
						transaction);
				log.debug(
						"Entry userAccount no: "
								+ userAccount.getUserAccountNumber(),
						userAccount);

				if (corporateAccountHolder001 != null) {
					log.debug("Entry corp acc. name : "
							+ corporateAccountHolder001.getName(),
							corporateAccountHolder001);
				} else {
					log.debug("Entry corp acc. name is null", this);

				}
				// log.debug(
				// "User account MSISDN : "+userAccount.getAccountIdentifier001s().getName(),
				// corporateAccountHolder001 );

				log.debug("User account MSISDN count: "
						+ userAccount.getAccountIdentifier001s().size(),
						userAccount.getAccountIdentifier001s());

				userAccount.getAccountIdentifier001s().forEach(
						e -> {
							log.debug("MSISDN: " + e.getName() + " of type: "
									+ e.getTypeName(), e);
						});

				log.debug("Entry date: " + entry.getEntryDate(), entry);
				log.debug(
						"Entry Transaction number: "
								+ entry.getTransactionNumber(), entry);
				log.debug("TXN TYPE: "
						+ entry.getEntryType001().getSystemcode().getValue(),
						entry);
				log.debug("Description : " + entry.getDescription(), entry);
				log.debug("Amount : " + entry.getAmount(), entry);
				log.debug(
						"Channel : " + entry.getTransaction001().getChannel(),
						entry.getTransaction001());
				log.debug("Transaction Status : "
						+ entry.getTransaction001().getSystemCode().getValue(),
						entry.getTransaction001());
				log.debug("Payee Account number : "
						+ entry.getTransaction001().getPayeeAccountNumber(),
						entry.getTransaction001());
				log.debug("Payer Account number : "
						+ entry.getTransaction001().getPayerAccountNumber(),
						entry.getTransaction001());

			} else {
				log.error("UserAccount is null, for entry oid: "
						+ entry.getOid());
			}

		}

	}

	@Test
	@Ignore
	@Transactional
	public void testGetEntryOnePage() {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findAll(new PageRequest(0, 10));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);
		if (pg.getContent().size() > 0) {
			Entry001 entry = pg.getContent().get(0);
			Transaction001 transaction = entry.getTransaction001();
			TransactionType001 type = transaction.getTransactionType001();
			log.debug(
					"Transaction code " + type.getSystemCode().getCode()
							+ " for transaction "
							+ transaction.getTransactionNumber()
							+ " and amount = " + transaction.getPayeeAmount()
							+ " and status = "
							+ transaction.getSystemCode().getValue(),
					transaction);
		}

	}

	@Test
	@Transactional
	@Ignore
	public void testGetOne() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Transaction001 transaction = repo.findOne(482001L);
		Assert.assertNotNull("No transaction record.", transaction);

		TransactionType001 type = transaction.getTransactionType001();
		log.debug("Transaction code " + type.getSystemCode().getCode()
				+ " for transaction " + transaction.getTransactionNumber()
				+ " and amount = " + transaction.getPayeeAmount()
				+ " and status = " + transaction.getSystemCode().getValue(),
				transaction);

	}

	@Test
	@Ignore
	@Transactional
	public void testGetOnePageByPayerAccountNumber() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findByPayerAccountNumber(
				"413141000208116", new PageRequest(0, 10));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);

		if (pg.getContent().size() > 0) {
			Transaction001 transaction = pg.getContent().get(0);
			TransactionType001 type = transaction.getTransactionType001();
			log.debug(
					"Transaction code " + type.getSystemCode().getCode()
							+ " for transaction "
							+ transaction.getTransactionNumber()
							+ " and amount = " + transaction.getPayeeAmount()
							+ " and status = "
							+ transaction.getSystemCode().getValue(),
					transaction);
		}

	}

}
