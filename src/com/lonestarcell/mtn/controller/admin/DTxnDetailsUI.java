package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.bean.InTxnDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DTxnDetailsUIDesign;
import com.lonestarcell.mtn.model.admin.MMo;
import com.lonestarcell.mtn.model.admin.MTxnDetails;
import com.lonestarcell.mtn.util.Money;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class DTxnDetailsUI extends DTxnDetailsUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger( DTxnDetailsUI.class.getName() );
	private OutTxnDetails data;
	private Item record;
	

	public DTxnDetailsUI( Item record) {
		this.setRecord( record );
		init();
	}

	@Override
	public void attachCommandListeners() {

		attachBtnTxnRefresh();
		attachBtnTokenRetry();
		attachBtnSendSMS();
		attachBtnUserDetailsClose();
	}
	
	
	
	public Item getRecord() {
		return record;
	}

	public void setRecord(Item record) {
		this.record = record;
	}
	
	
	private void attachBtnUserDetailsClose(){
		btnTxnDetailsClose.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				processingPopup.close();
				
			}
			
		} );
	}

	private void attachBtnTxnRefresh() {
		this.btnTxnRefresh.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				setUIData();
				
			}
			
		});
	}
	
	private void attachBtnSendSMS() {
		this.btnSMSRetry.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				MMo m = new MMo(  getCurrentUserId(), getCurrentUserSession()  );
				//double amount = 4230;
				
				In in = new In();
				BData<InMo> inBData = new BData<>();
				InMo inMo = new InMo();
				inMo.setMmoId( (String) record.getItemProperty(  "mmoId" ).getValue() );
				//inMo.setAcctRef( "90099887766" );
				//inMo.setMsisdn( "231888210000" );
				// inMo.setAmount( ( amount*100 )+"" );
				//inMo.setCurrency( "LRD" );
				
				inBData.setData( inMo );
				in.setData( inBData );
				Out out = m.sendSMS( in );
				
				if( out.getStatusCode() == 1 ) {
					setUIData();
				} else {
					Notification.show("Oops... error sending SMS. Please try again later.",
							Notification.Type.WARNING_MESSAGE);
				}
			}
			
		});
	}

	private void attachBtnTokenRetry() {
		
		log.debug( "Token retry attached." );
		
		this.btnTokenRetry.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				MMo m = new MMo(  getCurrentUserId(), getCurrentUserSession()   );
				// double amount = 4230;
				
				In in = new In();
				BData<InMo> inBData = new BData<>();
				InMo inMo = new InMo();
				// inMo.setMmoId( "19876379" );
				inMo.setMmoId( (String) record.getItemProperty(  "mmoId" ).getValue() );
				// inMo.setAcctRef( "90099887766" );
				// inMo.setMsisdn( "231888210000" );
				// inMo.setAmount( ( amount*100 )+"" );
				//inMo.setCurrency( "LRD" );
				
				inBData.setData( inMo );
				in.setData( inBData );
				
				log.debug( "Before calling token retry." );
				
				Out out = m.tokenRetry( in );
				
				if( out.getStatusCode() == 1 ) {
					log.debug( "After calling token retry." );
					setUIData();
					Notification.show( out.getMsg() ,
							Notification.Type.HUMANIZED_MESSAGE );
				} else {
					Notification.show( out.getMsg(),
							Notification.Type.WARNING_MESSAGE );
					log.error( "After calling token retry with error.." );
				}
				
				
				
			}
			
		});
	}

	private void init() {
		data = new OutTxnDetails();
		attachCommandListeners();
		setPropertyDataSource();
		setContent();
		

	}
	
	private void formatReqDataI(){
		
		if( data.getVerifDateCreate().getValue() != null 
				&& !data.getVerifDateCreate().getValue().equals( "-" ) ) {
			this.lbReqDateC.setPropertyDataSource( data.getVerifDateCreate() );
			log.debug( "Txn create date set." );
		} else {
			this.lbReqDateC.setPropertyDataSource( data.getTxnDateEnd() );
		}
		
		
		
		double amount = Double.valueOf( data.getAmount().getValue() );
		float rate =  Float.valueOf(data.getRate().getValue());
		if( data.getRateId().getValue().equals( "0" ) ) {
			
			data.getAmount().setValue( "USD "+ Money.format( amount ) );
			data.getRate().setValue( "N/A" );
			data.getRateId().setValue( "N/A" );
			data.getCurrency().setValue( "USD" );
			
		} else {
			
			data.getAmount().setValue( "LRD " + Money.format( amount*rate ) );
			data.getRate().setValue( "LRD "+Money.format( Double.valueOf( rate ) ) );
			data.getCurrency().setValue( "LRD" );
		}
		
		
		String payStatus =  data.getPayStatus().getValue();
		if( payStatus.equals( "01" ) ||  payStatus.equals( "04" ) ) {
			data.getPayStatus().setValue( "COMPLETED" );
			this.btnTxnRefresh.setEnabled( false );
			
		} else if( payStatus.equals( "100" ) 
				||  payStatus.equals( "1100" )  
				||  payStatus.equals( "2100" )
				||  payStatus.equals( "3100" ) ) {
			
			data.getPayStatus().setValue( "FAILED" );
			this.btnTxnRefresh.setEnabled( false );
		
		} else if( payStatus.equals( "4100" ) 
				||  payStatus.equals( "403" )  
				||  payStatus.equals( "02" )
				||  payStatus.equals( "0" ) ) {
			
			data.getPayStatus().setValue( "PENDING" );
			this.btnTxnRefresh.setEnabled( true );
		
		} else if( payStatus.equals( "03" ) 
				||  payStatus.equals( "102" ) ) {
			
			data.getPayStatus().setValue( "PENDING SDP" );
			this.btnTxnRefresh.setEnabled( true );
		}
		
		
	}
	
	private void formatItronData(){
		
		if( data.getVerifId().getValue() == null || data.getVerifId().getValue().equals( "-" )) {
			
			data.getVerifId().setValue( "-" );
			data.getVerifId().setValue( "-" );
			data.getVerifDateCreate().setValue( "-" );
			data.getVerifDateEnd().setValue( "-" );
			data.getVerifStatus().setValue( "-" );
			
		}
		
		if( data.getTokenId().getValue() == null || data.getTokenId().getValue().equals( "-" ) ) {
			
			data.getTokenId().setValue( "-" );
			data.getTokenDateCreate().setValue( "-" );
			data.getTokenDateEnd().setValue( "-" );
			data.getTokenStatus().setValue( "-" );
		}
		
		
		
		if( data.getPayStatus().getValue() != null 
				&& !data.getPayStatus().getValue().equals( "-" ) 
				&& !data.getPayStatus().getValue().equals( "FAILED" ) ){
			
			String status = data.getTokenStatus().getValue();
			if( status != null && ( status.equals( "COMPLETE" ) || status.equals( "REVERSED" ) )   ){
				this.btnTokenRetry.setEnabled( false );
			}else{
				this.btnTokenRetry.setEnabled( true );
			}
		} else {
			
			this.btnTokenRetry.setEnabled( false );
		}
		
		
		
			if( data.getItronAmount().getValue() != null 
					&& !data.getItronAmount().getValue().equals( "-" ) ) {
				
				data.getItronAmount().setValue( Money.format( (Double.valueOf(data.getItronAmount().getValue() )*100 ) ) );
			}
			
		
	}

	private void formatSMSData(){
		
		if( data.getSmsId().getValue() != null ) {
			
			data.getUnits().setValue( "kWH "+data.getUnits().getValue() );
			
			String status = data.getSmsStatus().getValue();
			
			if( status.equals( "1" ) ) {
				
				this.btnSMSRetry.setEnabled( false );
				data.getSmsStatus().setValue( "SMS SENT" );
				
			} else if( status.equals( "100" )){
				data.getSmsStatus().setValue( "SMS FAILED" );
			}
			
			
		} else {
			
			data.getSmsId().setValue( "-" );
			data.getSmsDateCreate().setValue( "-" );
			data.getSmsDateEnd().setValue( "-" );
			
			data.getToken().setValue( "-" );
			data.getUnits().setValue( "-" );
			
			data.getSmsStatus().setValue( "-" );
		
		}
		
		
		if( data.getPayStatus().getValue().equals( "COMPLETED" ) ){
			this.btnSMSRetry.setEnabled( true );
		} else {
			
			this.btnSMSRetry.setEnabled( false );
		}
		
		
	}
	
	private void setPropertyDataSource(){
		
		Property<String> ds = new ObjectProperty<String>( "-", String.class );
		
		// ITRON
		data.setItronAmount( ds );
		this.lbItronAmount.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setMeterNo( ds );
		this.lbItronMeterNo.setPropertyDataSource( ds );
		this.lbSmsMeterNo.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenDateCreate( ds );
		this.lbItronTokenDateC.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenDateEnd( ds );
		this.lbItronTokenDateE.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenId( ds );
		this.lbItronTokenId.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTokenStatus( ds );
		this.lbItronTokenStatus.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifDateCreate( ds );
		this.lbItronVerifDateC.setPropertyDataSource( ds );
		
		data.setTxnDateCreate( ds );
		this.lbReqDateC.setPropertyDataSource( ds );
		
		data.setSmsDateCreate( ds );
		this.lbSmsDateC.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifDateEnd( ds );
		this.lbItronVerifDateE.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifId( ds );
		this.lbItronVerifId.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setVerifStatus( ds );
		this.lbItronVerifStatus.setPropertyDataSource( ds );
		
		// Req.
		ds = new ObjectProperty<String>( "-", String.class );
		data.setAmount( ds );
		this.lbReqAmount.setPropertyDataSource( ds );
		this.lbSmsAmount.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setCurrency( ds );
		this.lbReqCur.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setTxnDateEnd( ds );
		this.lbReqDateE.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setMmoId( ds );
		this.lbReqMoId.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setMsisdn( ds );
		this.lbReqMSISDN.setPropertyDataSource( ds );
		this.lbSmsMSISDN.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setRate( ds );
		this.lbReqRate.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setRateId( ds );
		this.lbReqRateId.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSwiftaId( ds );
		this.lbReqSID.setPropertyDataSource( ds );
		this.lbSmsSID.setPropertyDataSource( ds );
		this.lbItronSID.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setPayStatus( ds );
		this.lbReqStatus.setPropertyDataSource( ds );
		
		
		
		
		
		
		// SMS
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSmsDateEnd( ds );
		this.lbSmsDateE.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSmsId( ds );
		this.lbSmsID.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setSmsStatus( ds );
		this.lbSmsStatus.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setToken( ds );
		this.lbSmsToken.setPropertyDataSource( ds );
		
		ds = new ObjectProperty<String>( "-", String.class );
		data.setUnits( ds );
		this.lbSmsUnits.setPropertyDataSource(ds );
		
		
		
		
		
		
		
	}
	
	private void format(){
		this.formatReqDataI();
		this.formatItronData();
		this.formatSMSData();
	}
	private void setUIData(){
		
		try {
			setTxnDetails( data );
			format();
		} catch ( Exception e ){
			
			e.printStackTrace();
			Notification.show("Oops... error setting data. Please  try again.",
					Notification.Type.ERROR_MESSAGE);
			
			
		}
		
		
	}

	private void setTxnDetails( OutTxnDetails data){
		
		try {
			
			MTxnDetails m = new MTxnDetails(  getCurrentUserId(), getCurrentUserSession()  );
			
			In in = new In();
			BData<InTxnDetails> inBData = new BData<>();
			InTxnDetails inTxn = new InTxnDetails();
			
			
			//inTxn.setSwiftaId( "125337" );
			
			String sid = (String) record.getItemProperty( "swiftaId" ).getValue();
			
			inTxn.setSwiftaId( sid );
			log.debug( "Swifta ID: "+inTxn.getSwiftaId() );
			
			
			
			
			inBData.setData( inTxn );
			in.setData( inBData );
			Out out = m.setTxnDetails( in, data );
			
			if( out.getStatusCode() != 1 ) {
				Notification.show("Oops... error updating data. Please  try again.",
						Notification.Type.ERROR_MESSAGE);
			} else {
				Notification.show( "Details re(loaded) successfully.",
						Notification.Type.HUMANIZED_MESSAGE);
			}
			
		
		} catch ( Exception e ){
			
			e.printStackTrace();
			Notification.show("Oops... error loading data. Please  try again.",
					Notification.Type.ERROR_MESSAGE);
		}
		
		
	}
	
	private void showPopup() {
		processingPopup = new Window("Transaction Details");
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable( true );
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		processingPopup.setSizeUndefined();
		UI.getCurrent().addWindow(processingPopup);
	}


	private void setContent() {
		if(record != null 
				&& data != null  ) {
			setUIData();
			showPopup();
		} else {
			
			Notification.show("Oops... error loading data. Please  try again.",
					Notification.Type.ERROR_MESSAGE);
		}
	}
	
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}


}
