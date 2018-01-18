package com.lonestarcell.mtn.controller;

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
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigFundamo;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.fundamo.entity.AccountIdentifier001;
import com.lonestarcell.mtn.spring.fundamo.entity.CorporateAccountHolder001;
import com.lonestarcell.mtn.spring.fundamo.entity.Entry001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;
import com.lonestarcell.mtn.spring.fundamo.entity.TransactionType001;
import com.lonestarcell.mtn.spring.fundamo.entity.UserAccount001;
import com.lonestarcell.mtn.spring.fundamo.repo.Entry001Repo;
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
					+ " for transaction " + transaction.getTransactionNumber()
					+ " and amount = " + transaction.getPayeeAmount()
					+ " and status = " + transaction.getStatus(), transaction);
		}

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
	public void testGetOneEntry() {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findAll(new PageRequest( 0, 1 ) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		
		Transaction001 transaction = pg.getContent().get( 0 ).getTransaction001();
		UserAccount001 userAccount = pg.getContent().get( 0 ).getUserAccount001();
		log.debug( "Entry transaction no: "+transaction.getTransactionNumber(), transaction );
		log.debug( "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount );
		

	}
	
	
	@Test
	// @Ignore
	@Transactional
	public void testGetOneEntryByOid() {
		Assert.assertNotNull("Fundamo entry repo is null.", entryRepo);
		Page<Entry001> pg = entryRepo.findAll(new PageRequest( 0, 100 ) );
		Assert.assertNotEquals("No entry record.", pg.getTotalPages(), 0);
		
		Transaction001 transaction = pg.getContent().get( 0 ).getTransaction001();
		UserAccount001 userAccount = pg.getContent().get( 0 ).getUserAccount001();
		CorporateAccountHolder001 corporateAccountHolder001 = userAccount.getCorporateAccountHolder001();
		AccountIdentifier001 AccountIdentifier001 = userAccount.getAccountIdentifier001();
		
		log.debug( "Entry transaction no: "+transaction.getTransactionNumber(), transaction );
		log.debug( "Entry userAccount no: "+userAccount.getUserAccountNumber(), userAccount );
		log.debug( "Entry corp acc. name : "+corporateAccountHolder001.getName(), corporateAccountHolder001 );
		

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
					+ " and status = " + transaction.getStatus(), transaction);
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
				+ " and status = " + transaction.getStatus(), transaction);
				

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
					+ " and status = " + transaction.getStatus(), transaction);
		}

	}

}
