package com.lonestarcell.mtn.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InLogin;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.main.MLogin;

public class MLoginTest {

	private static MLogin m;
	
	Logger log = LogManager.getLogger();
	
	@BeforeClass
	public static void init(){
		m = new MLogin();
	}

	@Test
	//@Ignore
	public void testMLogin() {
		
		Assert.assertNotNull( "Model is null", m );

	}
	
	@Test
	//@Ignore
	public void loginTest(){
			
		InLogin inData = new InLogin();
		inData.setUsername( "admin" );
		inData.setPassword( "admin" );
		
		BData<InLogin> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = m.login( in );
		Assert.assertNotNull( out );
		Assert.assertNotNull( out.getData() );
		Assert.assertNotNull( out.getData().getData() );
		
		Assert.assertTrue( out.getStatusCode() == 1 );
	}
	
	

	
	@Test
	@Ignore( "Closed pool." )
	public void setLoginSessionTest(){
		//Assert.assertTrue( m.setLoginSession( "dummy_session_id xxxx ",1L ) );
	}

}
