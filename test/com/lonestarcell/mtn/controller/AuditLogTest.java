package com.lonestarcell.mtn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.user.entity.AuditLog;
import com.lonestarcell.mtn.spring.user.entity.Permission;
import com.lonestarcell.mtn.spring.user.entity.User;
import com.lonestarcell.mtn.spring.user.repo.AuditLogRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { Config.class, DataAccessConfigUser.class, JpaConfig.class } )
public class AuditLogTest {
	
	private static Logger log = LogManager.getLogger();
	
	@Autowired
	private AuditLogRepo auditRepo;
	@Autowired
	private ApplicationContext springAppContext;
	
	
	
	@Test
	@Transactional
	// @Ignore
	public void testFindAll(){
		Assert.assertNotNull( "Audit repo is null.", auditRepo );
		Page< AuditLog > pg = auditRepo.findAll( new Pager().getPageRequest( 1 ) );
		Assert.assertNotNull( "Pg obj is null.", pg );
		Assert.assertNotEquals( "", 0, pg.getNumberOfElements() );
		AuditLog audit = pg.getContent().get( 0 );
		
		Permission permission = audit.getPermission();
		User user = audit.getUser();
		
		Assert.assertNotNull( "Perm obj is null", permission );
		Assert.assertNotNull( "User obj is null", user );
		
		log.info( "Audit log Count "+audit.getOpStatus(), this );
		
	}
	
	
	
	
	
	
}
