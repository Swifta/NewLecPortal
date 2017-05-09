package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DDashTxnUIDesign;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.lonestarcell.mtn.model.admin.MUser;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class DDashTxnUI extends DDashTxnUIDesign implements DUserUIInitializable<DDashUI, DDashTxnUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DDashUI ancestor;
	private Logger log = LogManager.getLogger();
	private Item record;
	private OutTxnMeta data;

	
	private MTxn mTxn;
	
	DDashTxnUI( DDashUI a){
		init( a );
	}
	
	

	
	public OutTxnMeta getData() {
		return data;
	}




	public void setData(OutTxnMeta data) {
		this.data = data;
	}




	public MTxn getmTxn() {
		return mTxn;
	}




	public void setmTxn(MTxn mTxn) {
		this.mTxn = mTxn;
	}




	public Item getRecord() {
		return record;
	}


	public void setRecord(Item record) {
		this.record = record;
	}


	@Override
	public void attachCommandListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(DManUIController duic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent() {
		this.setData( new OutTxnMeta() );
		this.setRecord( new BeanItem<>( this.getData() ) );
		this.setmTxn( new MTxn( getCurrentUserId(), getCurrentUserSession() ) );
		setHeader();
		setFooter();
		swap(this);
		attachCommandListeners();
		
		this.setDashData();
	}

	@Override
	public void swap(Component cuid) {
		//ancestor.setHeight("100%");
		//cuid.setHeight("100%");

		
		//ancestor.addStyleName("sn-p");
		//cuid.addStyleName("sn-c");
		
		cuid.setHeight("100%");
		ancestor.getAncestorUI().getcMainContent().setHeight( "100%" );
		//ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
		ancestor.setHeight( "100%" );
		
		
		log.debug( "Users height: "+cuid.getHeight() );
		
		ancestor.swap( cuid );
		
	}

	@Override
	public void init(DDashUI a) {
		setAncestorUI( a );
		setContent();
		
	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DDashUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DDashUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DDashTxnUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DDashTxnUI p) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void setDashData(){
		
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		
		
		
		cal.add(Calendar.DAY_OF_MONTH, -300 );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		inBData.setData( inTxn );
		in.setData( inBData );
		
		
		Out out = mTxn.setDashMeta(in, this.getRecord() );
		if( out.getStatusCode() != 1 ) {
			Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
			return;
		}

		
		format();
		
		
	}
	
	private void format(){
		
	
		NumberFormat nf = NumberFormat.getNumberInstance( Locale.US );
		this.getData().setsTotalInfoFail( nf.format( (long) this.getData().getTotalInfoFail() ).replace( "$", "") );
		this.getData().setsTotalInfoSuccess( nf.format( (long) this.getData().getTotalInfoSuccess() ).replace( "$", "") );
		
		this.getData().setsTotalTokenFail( nf.format( (long) this.getData().getTotalTokenFail() ).replace( "$", "") );
		this.getData().setsTotalTokenSuccess( nf.format( (long)this.getData().getTotalTokenSuccess() ).replace( "$", ""));

		this.getData().setsTotalSMSFail( nf.format( ( long ) this.getData().getTotalSMSFail() ).replace( "$", "") );
		this.getData().setsTotalSMSSuccess( nf.format( (long) this.getData().getTotalSMSSuccess() ).replace( "$", "") );

		this.getData().setsTotalTxnFail( nf.format( ( long ) this.getData().getTotalTxnFail() ).replace( "$", "") );
		this.getData().setsTotalTxnSuccess( nf.format( ( long )this.getData().getTotalTxnSuccess() ).replace( "$", "") );

		// Totals
		
		this.getData().setsTotalTxn( nf.format( ( long )this.getData().getTotalTxn() ).replace( "$", "") );
		this.getData().setsTotalToken( nf.format( ( long ) this.getData().getTotalToken() ).replace( "$", "") );
		this.getData().setsTotalSMS( nf.format( ( long ) this.getData().getTotalSMS() ).replace( "$", "") );
		this.getData().setsTotalInfo( nf.format( ( long ) this.getData().getTotalInfo() ).replace( "$", "") );
		
		
		this.btnInfoFail.setCaption( this.getData().getsTotalInfoFail() );
		this.btnInfoSuccess.setCaption( this.getData().getsTotalInfoSuccess() );
		
		this.btnTokenFail.setCaption( this.getData().getsTotalTokenFail() );
		this.btnTokenSuccess.setCaption( this.getData().getsTotalTokenSuccess() );
		
		this.btnSMSFail.setCaption( this.getData().getsTotalSMSFail() );
		this.btnSMSSuccess.setCaption( this.getData().getsTotalSMSSuccess() );
		
		this.btnTxnFail.setCaption( this.getData().getsTotalTxnFail() );
		this.btnTxnSuccess.setCaption( this.getData().getsTotalTxnSuccess() );
		
		this.btnTxnTotal.setCaption( this.getData().getsTotalTxn() );
		this.btnTokenTotal.setCaption( this.getData().getsTotalToken() );
		this.btnSMSTotal.setCaption( this.getData().getsTotalSMS() );
		this.btnInfoTotal.setCaption( this.getData().getsTotalInfo() );
		
		Long per = this.getData().getPerTxn();
		this.lbTxn.setValue( per+"" );
		if(  per >= 80 ) {
			this.lbTxnPer.setStyleName( "sn-percentage-sign sn-green" );
		} else if( per >= 50 ){
			this.lbTxnPer.addStyleName( "sn-percentage-sign sn-orange" );
		} else {
			this.lbTxnPer.addStyleName( "sn-percentage-sign sn-red" );
		}
		
		per = this.getData().getPerSMS();
		this.lbSMS.setValue( per+"" );
		if(  per >= 80 ) {
			this.lbSMSPer.addStyleName( "sn-percentage-sign sn-green" );
		} else if( per >= 50 ){
			this.lbSMSPer.addStyleName( "sn-percentage-sign sn-orange" );
		} else {
			this.lbSMSPer.addStyleName( "sn-percentage-sign sn-red" );
		}
		
		per = this.getData().getPerToken();
		this.lbToken.setValue( per+"" );
		if(  per >= 80 ) {
			this.lbTokenPer.addStyleName( "sn-percentage-sign sn-green" );
		} else if( per >= 50 ){
			this.lbTokenPer.addStyleName( "sn-percentage-sign sn-orange" );
		} else {
			this.lbTokenPer.addStyleName( "sn-percentage-sign sn-red" );
		}
		
		per = this.getData().getPerInfo();
		log.debug( "Info per: "+per );
		this.lbInfo.setValue( per+"" );
		if(  per >= 80 ) {
			this.lbInfoPer.addStyleName( "sn-percentage-sign sn-green" );
		} else if( per >= 50 ){
			this.lbInfoPer.addStyleName( "sn-percentage-sign sn-orange" );
		} else {
			this.lbInfoPer.addStyleName( "sn-percentage-sign sn-red" );
		}
		
		
		
	
	}
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}
	
	
	


}
