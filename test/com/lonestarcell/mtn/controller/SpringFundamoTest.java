package com.lonestarcell.mtn.controller;

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
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigFundamo;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.fundamo.entity.AccountIdentifier001;
import com.lonestarcell.mtn.spring.fundamo.entity.CorporateAccountHolder001;
import com.lonestarcell.mtn.spring.fundamo.entity.Entry001;
import com.lonestarcell.mtn.spring.fundamo.entity.LedgerAccount001;
import com.lonestarcell.mtn.spring.fundamo.entity.Subscriber001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;
import com.lonestarcell.mtn.spring.fundamo.entity.TransactionType001;
import com.lonestarcell.mtn.spring.fundamo.entity.UserAccount001;
import com.lonestarcell.mtn.spring.fundamo.repo.Entry001Repo;
import com.lonestarcell.mtn.spring.fundamo.repo.LedgerAccount001Repo;
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

	@Test
	@Ignore
	public void testSayHello() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo );
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
			log.debug("Transaction code " + type.getSystemCode().getCode()
					+ " for transaction no. " + transaction.getTransactionNumber()
					+ " and amount = " + transaction.getPayeeAmount()
					+ " and type = " + transaction.getTransactionType001().getSystemCode().getValue()
					+ " and payee = " + transaction.getPayeeAmount()
					+ " and payer = " + transaction.getPayerAccountNumber()
					+ " and status = " + transaction.getSystemCode().getValue(), transaction);
		}

	}
	
	
	@Test
	@Ignore
	public void testGetOnePageByDateRange() throws ParseException {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findPageByDateRange( new PageRequest(0, 1), DateFormatFac.toDate( "2014-01-19" ), DateFormatFac.toDate( "2018-01-18" ) );
		// Page<Transaction001> pg = repo.findAll(new PageRequest(0, 10));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);
		
		log.debug( "Total records: "+pg.getTotalPages(), this );
		if (pg.getContent().size() > 0) {
			Transaction001 transaction = pg.getContent().get(0);
			TransactionType001 type = transaction.getTransactionType001();
			log.debug("Transaction code " + type.getSystemCode().getCode()
					+ " for transaction no. " + transaction.getTransactionNumber()
					+ " and amount = " + NumberFormatFac.toMoney( ( transaction.getPayeeAmount()/100 ) + "" )
					+ " and type = " + transaction.getTransactionType001().getSystemCode().getValue()
					+ " and payee = " + transaction.getPayeeAmount()
					+ " and payer = " + transaction.getPayerAccountNumber()
					+ " and timestamp = " + DateFormatFac.toString( transaction.getLastUpdate() )
					+ " and status = " + transaction.getSystemCode().getValue(), transaction);
		}

	}
	
	@Test
	@Ignore
	public void testGetTotalPayeeAmount() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		long totalPayeeAmount = repo.getTotalPayeeAmount();
		
			log.debug("Total Payee Amount:  "+totalPayeeAmount, this );
		

	}
	
	@Test
	@Ignore
	public void testGetEntryCount() {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findAll(new PageRequest(0, 1) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		log.debug( "Entry total count: "+pg.getTotalPages(), pg );
		

	}
	
	@Test
	@Ignore
	public void testGetUserAccountCount() {
		Assert.assertNotNull("Fundamo userAccount repo is null.", repoUserAccount);
		Page<UserAccount001> pg = repoUserAccount.findAll(new PageRequest(0, 1) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		log.debug( "UserAccount total count: "+pg.getTotalPages(), pg );
		

	}
	
	@Test
	@Ignore
	@Transactional
	public void testGetOneUserAccount() {
		Assert.assertNotNull("Fundamo entry repo is null.", repoUserAccount);
		Page< UserAccount001 > pg = repoUserAccount.findAll(new PageRequest( 0, 1 ) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		
		List< Entry001 > entries = pg.getContent().get( 0 ).getEntries();
		log.debug( "User account entry count: "+entries.size(), entries  );
		//UserAccount001 userAccount = pg.getContent().get( 0 ).getUserAccount001();
		//log.debug( "Entry transaction no: "+transaction.getTransactionNumber(), transaction );
		//log.debug( "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount );
		

	}
	
	
	@Test
	@Ignore
	@Transactional
	public void testGetOneLedgerAccount() throws ParseException {
		Assert.assertNotNull("Fundamo ledger repo is null.", ledgerRepo );
		List< Object[] > records = ledgerRepo.getAllSum( DateFormatFac.toDate( "2011-06-10" ) );
		Assert.assertNotEquals("No ledger record.", records.size(), 0);
		
		log.debug( "Record of sum fetched successfully with count: "+records.size() );
		
		Iterator< Object[] > itr = records.iterator();
		while( itr.hasNext() ){
			Object[] record = itr.next();
			log.debug( "AccNo: "+record[ 0 ]
					+"Name: "+record[ 1 ]
					+"Amount: "+record[ 2 ],
					records );
		}		

	}
	
	
	@Test
	// @Ignore
	@Transactional
	public void testGetOneSubscriber() throws ParseException {

		
		Assert.assertNotNull("Fundamo user account. repo is null.", repoUserAccount );
		Page< UserAccount001 > pgu = repoUserAccount.findPageByDateRange(new PageRequest( 0, 15 ), DateFormatFac.toDate( "2014-01-14"), DateFormatFac.toDateUpperBound( "2014-01-14" ) );
		Assert.assertNotEquals("No entry record.", pgu.getTotalPages(), 0);
		
		int total = pgu.getContent().size();
		
		for( int x = total-1; x >= 0; x-- ) {
			
			UserAccount001 ua = pgu.getContent().get( x );
			log.debug( "User acount count: "+pgu.getSize()
			+" Name: "+ua.getSubscriber001().getName()
			+" MSISDN: "+ua.getSubscriber001().getPerson001().getRegistrationRequestData001().getMsisdn()
			+" ID NUmber: "+ua.getSubscriber001().getPerson001().getRegistrationRequestData001().getIdNumber()
			+" Name: "+ua.getSubscriber001().getName()
			+" ID TYPE: "+ua.getSystemCode().getValue()
			+" Date of birth: "+ua.getSubscriber001().getPerson001().getDateOfBirth()
			+" Date of Reg.: "+ua.getSubscriber001().getLastUpdate()
			+" Account Status: "+ua.getSystemCode().getValue(), ua  );
			//UserAccount001 userAccount = pg.getContent().get( 0 ).getUserAccount001();
			//log.debug( "Entry transaction no: "+transaction.getTransactionNumber(), transaction );
			//log.debug( "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount );
		
		}
		

	}
	

	/*
	@Test
	@Ignore
	public void testGetOneEntry() {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findAll(new PageRequest( 0, 1 ) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		
		Transaction001 transaction = pg.getContent().get( 0 ).getTransaction001();
		UserAccount001 userAccount = pg.getContent().get( 0 ).getUserAccount001();
		log.debug( "Entry transaction no: "+transaction.getTransactionNumber(), transaction );
		log.debug( "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount );
		

	} */
	
	
	
	// TODO Get one entry by OID
	
	@Test
	@Ignore
	@Transactional
	public void testGetOneEntryPageByDateRange() throws ParseException {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findPageByDateRange(new PageRequest( 0, 15 ), DateFormatFac.toDate( "2010-01-01" ), DateFormatFac.toDate( "2014-12-31" ) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		
		for( int x = pg.getContent().size() - 1; x >= 0; x-- ){
		Entry001 entry = pg.getContent().get( x );
		Transaction001 transaction = entry.getTransaction001();
		UserAccount001 userAccount = entry.getUserAccount001();
		CorporateAccountHolder001 corporateAccountHolder001 = null;
		if( userAccount != null ) {
			corporateAccountHolder001 = userAccount.getCorporateAccountHolder001();
		
		
		
		log.debug( "Entry transaction no: "+transaction.getTransactionNumber(), transaction );
		log.debug( "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount );
		
		if( corporateAccountHolder001 != null ){
			log.debug( "Entry corp acc. name : "+corporateAccountHolder001.getName(), corporateAccountHolder001 );
		}
		//log.debug( "User account MSISDN : "+userAccount.getAccountIdentifier001s().getName(), corporateAccountHolder001 );
		
		log.debug( "User account MSISDN count: "+userAccount.getAccountIdentifier001s().size(), userAccount.getAccountIdentifier001s() );
		
		userAccount.getAccountIdentifier001s().forEach( e->{ log.debug( "MSISDN: "+e.getName()+" of type: "+e.getTypeName(), e ); });
		
		log.debug( "Entry date: "+entry.getEntryDate(), entry );
		log.debug( "Entry Transaction number: "+entry.getTransactionNumber(), entry );
		log.debug( "TXN TYPE: "+entry.getEntryType001().getSystemcode().getValue(), entry );
		log.debug( "Description : "+entry.getDescription(), entry );
		log.debug( "Amount : "+entry.getAmount(), entry );
		log.debug( "Channel : "+entry.getTransaction001().getChannel(), entry.getTransaction001() );
		log.debug( "Transaction Status : "+entry.getTransaction001().getSystemCode().getValue(), entry.getTransaction001() );
		log.debug( "Payee Account number : "+entry.getTransaction001().getPayeeAccountNumber(), entry.getTransaction001() );
		log.debug( "Payer Account number : "+entry.getTransaction001().getPayerAccountNumber(), entry.getTransaction001() );
		
		} else {
			log.error( "UserAccount is null" );
		}
		
		}
		
		
		

	} 
	
	
	@Test
	@Ignore
	@Transactional
	public void testGetOneEntryPageByDate() throws ParseException {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findPageByDateRange(new PageRequest( 0, 15 ), DateFormatFac.toDate( "2011-01-14" ), DateFormatFac.toDateUpperBound( "2011-01-14" ) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		
		for( int x = pg.getContent().size() - 1; x >= 0; x-- ){
		Entry001 entry = pg.getContent().get( x );
		Transaction001 transaction = entry.getTransaction001();
		UserAccount001 userAccount = entry.getUserAccount001();
		CorporateAccountHolder001 corporateAccountHolder001 = null;
		if( userAccount != null ) {
			corporateAccountHolder001 = userAccount.getCorporateAccountHolder001();
		
		
		
		log.debug( "Entry transaction no: "+transaction.getTransactionNumber(), transaction );
		log.debug( "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount );
		
		if( corporateAccountHolder001 != null ){
			log.debug( "Entry corp acc. name : "+corporateAccountHolder001.getName(), corporateAccountHolder001 );
		} else {
			log.debug( "Entry corp acc. name is null", this );
			
		}
		//log.debug( "User account MSISDN : "+userAccount.getAccountIdentifier001s().getName(), corporateAccountHolder001 );
		
		log.debug( "User account MSISDN count: "+userAccount.getAccountIdentifier001s().size(), userAccount.getAccountIdentifier001s() );
		
		userAccount.getAccountIdentifier001s().forEach( e->{ log.debug( "MSISDN: "+e.getName()+" of type: "+e.getTypeName(), e ); });
		
		log.debug( "Entry date: "+entry.getEntryDate(), entry );
		log.debug( "Entry Transaction number: "+entry.getTransactionNumber(), entry );
		log.debug( "TXN TYPE: "+entry.getEntryType001().getSystemcode().getValue(), entry );
		log.debug( "Description : "+entry.getDescription(), entry );
		log.debug( "Amount : "+entry.getAmount(), entry );
		log.debug( "Channel : "+entry.getTransaction001().getChannel(), entry.getTransaction001() );
		log.debug( "Transaction Status : "+entry.getTransaction001().getSystemCode().getValue(), entry.getTransaction001() );
		log.debug( "Payee Account number : "+entry.getTransaction001().getPayeeAccountNumber(), entry.getTransaction001() );
		log.debug( "Payer Account number : "+entry.getTransaction001().getPayerAccountNumber(), entry.getTransaction001() );
		
		} else {
			log.error( "UserAccount is null" );
		}
		
		}
		
		
		

	} 
	
	
	@Test
	@Ignore
	@Transactional
	public void testGetEntryOnePage() {
		Assert.assertNotNull( "Fundamo entry repo is null.", entryRepo );
		Page< Entry001 > pg = entryRepo.findAll(new PageRequest(0, 10));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);
		if (pg.getContent().size() > 0) {
			Entry001 entry = pg.getContent().get(0);
			Transaction001 transaction = entry.getTransaction001();
			TransactionType001 type = transaction.getTransactionType001();
			log.debug("Transaction code " + type.getSystemCode().getCode()
					+ " for transaction " + transaction.getTransactionNumber()
					+ " and amount = " + transaction.getPayeeAmount()
					+ " and status = " + transaction.getSystemCode().getValue(), transaction);
		}

	}

	@Test
	@Transactional
	@Ignore
	public void testGetOne() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Transaction001 transaction = repo.findOne(482001L);
		Assert.assertNotNull("No transaction record.", transaction );

		TransactionType001 type = transaction.getTransactionType001();
		log.debug("Transaction code " + type.getSystemCode().getCode()
				+ " for transaction " + transaction.getTransactionNumber()
				+ " and amount = " + transaction.getPayeeAmount()
				+ " and status = " + transaction.getSystemCode().getValue(), transaction);
				

	}
	
	@Test
	@Ignore
	@Transactional
	public void testGetOnePageByPayerAccountNumber() {
		Assert.assertNotNull("Fundamo repo is null.", repo);
		Page<Transaction001> pg = repo.findByPayerAccountNumber( "413141000208116", new PageRequest(0, 10));

		Assert.assertNotEquals("No transaction record.", pg.getTotalPages(), 0);
		
		if (pg.getContent().size() > 0) {
			Transaction001 transaction = pg.getContent().get(0);
			TransactionType001 type = transaction.getTransactionType001();
			log.debug("Transaction code " + type.getSystemCode().getCode()
					+ " for transaction " + transaction.getTransactionNumber()
					+ " and amount = " + transaction.getPayeeAmount()
					+ " and status = " + transaction.getSystemCode().getValue(), transaction);
		}

	}

}
