package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutConfig;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.UrlValidatorCustom;
import com.lonestarcell.mtn.design.admin.DSettingsUIDesign;
import com.lonestarcell.mtn.model.admin.MSettings;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class DSettingsUI extends DSettingsUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger( DSettingsUI.class.getName() );
	private Item record;
	private OutConfig config;
	

	public DSettingsUI( ) {
		this.setRecord( new OutConfig() );
		init();
	}
	
	

	public Window getProcessingPopup() {
		return processingPopup;
	}



	public void setProcessingPopup(Window processingPopup) {
		this.processingPopup = processingPopup;
	}



	@Override
	public void attachCommandListeners() {
		
		this.attachBtnCoreCancel();
		this.attachBtnCoreEdit();
		this.attachBtnCoreSave();
		this.attachBtnMediatorCancel();
		this.attachBtnMediatorEdit();
		this.attachBtnMediatorSave();
		this.attachBtnTimeCancel();
		this.attachBtnTimeEdit();
		this.attachBtnTimeSave();
		this.attachBtnWindowClose();
		this.attachOnClose();
		
	}
	
	
	
	private void attachBtnMediatorEdit(){
		this.btnMediatorEdit.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				btnMediatorEdit.setVisible( false );
				btnMediatorSave.setVisible( true );
				btnMediatorCancel.setVisible( true );
				tFMediator.setVisible( true );
				lbMediator.setVisible( false );
			}
			
		});
	}
	
	
	private void attachBtnMediatorSave(){
		this.btnMediatorSave.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				try {
					if( isFormValid() )
						if( saveConfigHandler( ) ){
							
							btnMediatorEdit.setVisible( true );
							btnMediatorSave.setVisible( false );
							btnMediatorCancel.setVisible( false );
							tFMediator.setVisible( false );
							lbMediator.setVisible( true );
						}
				} catch ( Exception e ){
					e.printStackTrace();
					Notification.show( "Could not complete operation. Please try again / Contact support.", Notification.Type.ERROR_MESSAGE );
				}
				
				
			}
			
		});
	}



	
	
	private void attachBtnMediatorCancel(){
		this.btnMediatorCancel.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				btnMediatorEdit.setVisible( true );
				tFMediator.setVisible( false );
				lbMediator.setVisible( true );
				btnMediatorCancel.setVisible( false );
				btnMediatorSave.setVisible( false );
				
			}
			
		});
	}

	
	private void attachBtnCoreSave(){
		this.btnCoreSave.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				try {
					if( isFormValid() )
						if( saveConfigHandler( ) ){
							
							btnCoreEdit.setVisible( true );
							tFCore.setVisible( false );
							lbCore.setVisible( true );
							btnCoreCancel.setVisible( false );
							btnCoreSave.setVisible( false );
						}
				} catch ( Exception e ){
					e.printStackTrace();
					Notification.show( "Could not complete operation. Please try again / Contact support.", Notification.Type.ERROR_MESSAGE );
				}

				
			}
			
		});
	}
	
	private void attachBtnCoreEdit(){
		this.btnCoreEdit.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				btnCoreEdit.setVisible( false );
				btnCoreSave.setVisible( true );
				btnCoreCancel.setVisible( true );
				tFCore.setVisible( true );
				lbCore.setVisible( false );
				
			}
			
		});
	}
	
	
	private void attachBtnCoreCancel(){
		this.btnCoreCancel.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				btnCoreEdit.setVisible( true );
				tFCore.setVisible( false );
				lbCore.setVisible( true );
				btnCoreCancel.setVisible( false );
				btnCoreSave.setVisible( false );
				
			}
			
		});
	}
	
	
	private void attachBtnTimeSave(){
		this.btnTimeSave.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				try {
					if( isFormValid( ) )
						if( saveConfigHandler( ) ){
							
							btnTimeEdit.setVisible( true );
							lbTime.setVisible( true);
							
							btnTimeSave.setVisible( false );
							btnTimeCancel.setVisible( false );
							dFTime.setVisible( false );
						}
				} catch ( Exception e ){
					e.printStackTrace();
					Notification.show( "Could not complete operation. Please try again / Contact support.", Notification.Type.ERROR_MESSAGE  );
				}
				
			}
			
		});
	}
	
	
	
	private void attachBtnTimeEdit(){
		this.btnTimeEdit.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				btnTimeEdit.setVisible( false );
				lbTime.setVisible( false );
				
				btnTimeSave.setVisible( true );
				btnTimeCancel.setVisible( true );
				dFTime.setVisible( true );
				
			}
			
		});
	}
	
	private void attachBtnTimeCancel(){
		
		this.btnTimeCancel.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				btnTimeEdit.setVisible( true );
				lbTime.setVisible( true );
				
				dFTime.setVisible( false );
				btnTimeCancel.setVisible( false );
				btnTimeSave.setVisible( false );
			}
			
		});
	}
	
	private void attachOnClose(){
		processingPopup.addCloseListener( new CloseListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				refreshRecord();
				
			}
			
		});
	}
	
	private void attachBtnWindowClose(){
		btnWindowClose.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				processingPopup.close();
				
			}
			
		} );
	}
	
	
	private boolean refreshRecord(){
		
		
	
		
		if( record != null ) {
			
			InUserDetails inData = new InUserDetails();
			inData.setRecord( record );
			
			BData<InUserDetails> bData = new BData<>();
			bData.setData( inData );
			
			In in = new In();
			in.setData( bData );
			Out out = null;
			if( getCurrentUserProfileId() == 1 ) {
				out =  new MUserDetails(  getCurrentUserId(), getCurrentUserSession()  ).setUserDetails(in );
			} else {
				out =  new MUserSelfCare().setUserDetails(in );
			}
			return out.getStatusCode() == 1;
		
		} 
		
		return false;
		
		
	}
	
	

	
	public Item getRecord() {
		return record;
	}

	public void setRecord( OutConfig config ) {
		this.config = config;
		record = new BeanItem<>( config );
	}

	private void init() {
		this.setProcessingPopup( new Window("Platform Settings") );
		attachCommandListeners();
		setPropertyDataSource();
		setContent();
		

	}
	


	private void setPropertyDataSource(){
		
		config.setCoreEPR( "N/A" );
		config.setMediatorEPR( "N/A" );
		config.setTimeCorrection( "N/A" );
		
		
		
		// Core
		this.lbCore.setPropertyDataSource( record.getItemProperty( "coreEPR" ) );
		this.tFCore.setPropertyDataSource( record.getItemProperty( "coreEPR" ) );
		
		// Mediator
		this.lbMediator.setPropertyDataSource( record.getItemProperty( "mediatorEPR" ) );
		this.tFMediator.setPropertyDataSource( record.getItemProperty( "mediatorEPR" ) );
		
		// Time
		this.lbTime.setPropertyDataSource( record.getItemProperty( "timeCorrection" ) );
		//this.dFTime.setPropertyDataSource( record.getItemProperty( "timeCorrection" ) );
		
		// Validators
		this.tFMediator.addValidator( new UrlValidatorCustom( "Field required in valid format." ) );
		this.tFCore.addValidator( new UrlValidatorCustom( "Field required in valid format." ) );
		this.lbTime.setPropertyDataSource( record.getItemProperty( "timeCorrection" ) );
		
		
		
	}
	
	
	
	
	private boolean isFormValid( ){
		
		if ( !this.tFCore.isValid() )
			return false;
		if( !this.tFMediator.isValid() )
			return false;
		
		log.debug( "Form validation still pending." );
		if( !this.dFTime.isValid() )
			return false;
		
		log.debug( "Form validation passed." );
		return true;
	}
	

	
	private boolean saveConfigHandler( ){
		
		Date date = dFTime.getValue();
		DateFormat sdf = new SimpleDateFormat( "HH:mm:ss" );
		String sTime = sdf.format( date );
		config.setTimeCorrection( sTime );
		
		log.debug( "Time correction: "+config.getTimeCorrection( ) );
		String t = record.getItemProperty( "timeCorrection" ).getValue( ).toString();
		log.debug( "Time - Record correction: "+t );
		
		lbTime.setPropertyDataSource( record.getItemProperty( "timeCorrection" ) );
		
		
		Out out = updateConfig( );
		
		log.debug( "Config update msg: "+out.getMsg() );
	
		if( out.getStatusCode() == 1 ){
			Notification.show( out.getMsg(),
					Notification.Type.HUMANIZED_MESSAGE );
			format();
			return true;
			
		} else {
			Notification.show( out.getMsg(),
					Notification.Type.ERROR_MESSAGE );
			return false;
			
		}
	
	}
	
	
	
	
	
	private void showPopup() {
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable(false);
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		//processingPopup.setSizeUndefined();
		processingPopup.setHeight( "700px" );
		UI.getCurrent().addWindow(processingPopup);
		
	}
	
	private Out setConfig(){
		MSettings mSetting = new MSettings( this.getCurrentUserId(), this.getCurrentUserSession() );
		
		
		
		InSettings inSetting = new InSettings();
		inSetting.setConfig( config );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inSetting );
		
		In in = new In();
		in.setData( bData );
		
		Out out = mSetting.setConfig( in );
		
		
	
		
		return out;
		
		
	}
	
	private Out updateConfig(){
		
		MSettings mSetting = new MSettings( this.getCurrentUserId(), this.getCurrentUserSession() );
		
		InSettings inSetting = new InSettings();
		inSetting.setConfig( config );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inSetting );
		
		In in = new In();
		in.setData( bData );
		
		Out out = mSetting.updateConfig( in );
		
		return out;
		
		
	}
	
	private void format(){
		String sTime = config.getTimeCorrection();
		
		if( sTime == null ||  sTime.trim().isEmpty() ){
			config.setTimeCorrection( "N/A" );
		}else{
			
			String[] time = sTime.split( ":" );
			String sec = time[ 2 ];
			String min = time[ 1 ];
			String hour = time[ 0 ];
			
			Calendar cal = Calendar.getInstance();
			cal.set( Calendar.SECOND, Integer.valueOf( sec ) );
			cal.set( Calendar.MINUTE, Integer.valueOf( min ) );
			cal.set( Calendar.HOUR_OF_DAY, Integer.valueOf( hour ) );
			//cal.set( Calendar.AM_PM, Calendar.PM );
			
			
			
			
			
			// dFTime.setDateFormat( "yyyy-MM-dd H:m:s a" );
			
			
			
			dFTime.setValue( cal.getTime() );
			
			
		}
		
	}
	
	private boolean isConfigSet(){
		
		Out out = setConfig();
		boolean status = out.getStatusCode() == 1;
		if( !status ){
		Notification.show( out.getMsg(),
				Notification.Type.ERROR_MESSAGE);
		} else {
			Notification.show( out.getMsg(),
					Notification.Type.HUMANIZED_MESSAGE );
		}
		return status;
	}


	private void setContent() {
		
		this.btnCoreCancel.setVisible( false );
		this.btnMediatorCancel.setVisible( false );
		this.btnTimeCancel.setVisible( false );
		
		this.btnCoreSave.setVisible( false );
		this.btnMediatorSave.setVisible( false );
		this.btnTimeSave.setVisible( false );
		
		this.tFMediator.setVisible( false );
		this.tFCore.setVisible( false );
		this.dFTime.setVisible( false );
		
		if(record != null  && this.isConfigSet() ) {
			
			showPopup();
			format();
			
			
		} else {
			
			Notification.show("Oops... error loading data. Please  try again.",
					Notification.Type.ERROR_MESSAGE);
		}
	}
	

	private int getCurrentUserProfileId(){
		return ( int ) UI.getCurrent().getSession().getAttribute( DLoginUIController.PROFILE_ID );
	}
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}


}
