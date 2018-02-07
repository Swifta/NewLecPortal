package com.lonestarcell.mtn.controller.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DUserDetailsUIDesign;
import com.lonestarcell.mtn.model.admin.MUser;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class DUserDetailsUI extends DUserDetailsUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger( DUserDetailsUI.class.getName() );
	private Item record;
	private Collection<Item> records;
	private MUser mTxn = new MUser(  getCurrentUserId(), getCurrentUserSession()  );
	
	protected Set< Short > permSet;
	private ApplicationContext springAppContext;
	

	public DUserDetailsUI( Item record, ApplicationContext springAppContext ) {
		this.setSpringAppContext(springAppContext);
		this.setRecord( record );
		this.setPermSet( null );
		init();
	}
	
	
	
	

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}





	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}





	public Set<Short> getPermSet() {
		return permSet;
	}

	@SuppressWarnings("unchecked")
	public void setPermSet(Set<Short> permSet) {
		if( permSet == null )
			this.permSet = UI.getCurrent().getSession().getAttribute( Set.class );
		else
			this.permSet = permSet;
		
	}





	public Collection<Item> getRecords() {
		return records;
	}



	public void setRecords(Collection<Item> records) {
		this.records = records;
	}



	public Window getProcessingPopup() {
		return processingPopup;
	}



	public void setProcessingPopup(Window processingPopup) {
		this.processingPopup = processingPopup;
	}



	@Override
	public void attachCommandListeners() {
		this.attachBtnEditCreds();
		this.attachBtnUserEditPersonalInfo();
		this.attachOnClose();
		this.attachBtnUserBlock();
		this.attachBtnUserActivate();
		this.attachBtnUserExpirePassword();
		this.attachBtnUserExpireSession();
		this.attachBtnUserSetCreds();
		this.attachBtnUserChangeProfile();
		this.attachBtnUserRefresh();
	}
	
	
	private boolean isAllowedFeature( Button btn, Short permId ){
		boolean is = false;
		if( !permSet.contains( permId )){
			btn.setVisible( false );
			btn.setEnabled( false );
			
		} else {
			btn.setVisible( true );
			btn.setEnabled( true );
			is = true;
		}
		
		log.info( "Is permission "+btn.getCaption()+" of id "+permId+" on?: "+is );
		return is;
		
	}
	
	private void attachBtnUserSetCreds(){
		
		if( !isAllowedFeature( btnUserSetCreds, EnumPermission.USER_SET_RESET_PASSWORD.val) )
			return;
		
		this.btnUserSetCreds.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				processingPopup.close();
				new DUserSetCredsUI( record, springAppContext );
				
			}
			
		});
	}
	
	
	private void attachBtnUserChangeProfile(){
		
		if( !isAllowedFeature( btnUserChangeProfile, EnumPermission.USER_CHANGE_PROFILE.val) )
			return;
		
		this.btnUserChangeProfile.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				processingPopup.close();
				new DUserEditProfileUI( record, springAppContext );
				
			}
			
		});
	}



	
	
	private void attachBtnUserExpireSession(){
		if( !isAllowedFeature( btnUserExpireSession, EnumPermission.USER_CANCEL_LOGIN_SESSION.val) )
			return;
		
		this.btnUserExpireSession.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				expireSessionMultiHandler( records );
				format();
				
			}
			
		});
	}

	
	private void attachBtnUserExpirePassword(){
		
		if( !isAllowedFeature( btnUserExpirePassword, EnumPermission.USER_EXPIRE_PASSWORD.val) )
			return;
		this.btnUserExpirePassword.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				expirePassMultiHandler( records );
				format();

				
			}
			
		});
	}
	
	private void attachBtnUserActivate(){
		
		if( !isAllowedFeature( btnUserActivate, EnumPermission.USER_ACTIVATE_BLOCK.val) )
			return;
		
		this.btnUserActivate.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				activateMultiHandler( records );
				format();
				
			}
			
		});
	}
	
	
	private void attachBtnUserBlock(){
		
		if( !isAllowedFeature( btnUserBlock, EnumPermission.USER_ACTIVATE_BLOCK.val) )
			return;
		this.btnUserBlock.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				blockMultiHandler( records );
				format();
				
			}
			
		});
	}
	
	
	private void attachBtnUserRefresh(){
		this.btnUserRefresh.addClickListener( new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				refreshHandler();
				format();
				
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
	
	
	
	private boolean refreshRecord(){
		
		
	
		
		if( record != null ) {
			
			InUserDetails inData = new InUserDetails();
			setAuth( inData );
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
	
	private void setAuth( InUserDetails inData ){
		
		inData.setUsername( UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME ).toString() );
		inData.setUserSession(  UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString()  );

		
	}
	
	
	
	private void attachBtnEditCreds(){
		this.btnUserChangeCreds.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				processingPopup.close();
				new DUserEditCredsUI( record, springAppContext );
				
			}
			
		});
	}
	
	private void attachBtnUserEditPersonalInfo(){
		
		this.btnUserEditPersonalInfo.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				processingPopup.close();
				new DUserEditPersonalInfoUI( record, springAppContext );
				
			}
			
		});
	}
	
	public Item getRecord() {
		return record;
	}

	public void setRecord(Item record) {
		List<Item> r = new ArrayList<>();
		r.add( record );
		this.setRecords( r );
		this.record = record;
	}

	private void init() {
		this.setProcessingPopup( new Window("User Details") );
		attachCommandListeners();
		setPropertyDataSource();
		setContent();
		

	}
	


	private void setPropertyDataSource(){
		
		// Access Control
		this.lbUsername.setPropertyDataSource( record.getItemProperty( "username" ) );
		this.lbUserProfile.setPropertyDataSource( record.getItemProperty( "profile" ) );
		this.lbUserStatus.setPropertyDataSource( record.getItemProperty( "userStatus" ) );
		
		// Personal Info
		this.lbFirstName.setPropertyDataSource( record.getItemProperty( "firstName" ) );
		this.lbLastName.setPropertyDataSource( record.getItemProperty( "lastName" ) );
		this.lbSurname.setPropertyDataSource( record.getItemProperty( "surname" ) );
		this.lbEmail.setPropertyDataSource( record.getItemProperty( "email" ) );
		
		// Organization
		this.lbOrg.setPropertyDataSource( record.getItemProperty( "org" ) );
		this.lbOrgDomain.setPropertyDataSource( record.getItemProperty( "orgDomain" ) );
		this.lbOrgStatus.setPropertyDataSource( record.getItemProperty( "orgStatus" ) );
		
		// Log
		this.lbCreateDate.setPropertyDataSource( record.getItemProperty( "dateCreated" ) );
		this.lbLoginDate.setPropertyDataSource( record.getItemProperty( "lastLogin" ) );
	}
	
	@SuppressWarnings("unchecked")
	private void format(){
		
	
		// TODO Test after
		this.btnUserBlock.setVisible( false );
		this.btnUserActivate.setVisible( false );
		this.btnUserEditPersonalInfo.setVisible( false );
		this.btnUserExpirePassword.setVisible( false );
		this.btnUserExpireSession.setVisible( false );
		this.btnUserSetCreds.setVisible( false );
		this.btnUserChangeProfile.setVisible( false );
		this.btnUserChangeCreds.setVisible( false );
		
		String userStatus = record.getItemProperty( "userStatus" ).getValue().toString();
		log.debug( "User status: "+userStatus );
		
		if( getCurrentUserId() != (long) record.getItemProperty( "userId" ).getValue() ){
			
			this.btnUserChangeProfile.setVisible( true );
			
			if( record.getItemProperty( "userStatus" ).getValue().toString().equals( "2" ) ){
				
				btnUserActivate.setVisible( true );
				record.getItemProperty( "userStatus" ).setValue( "Blocked" );
				
			}else if ( record.getItemProperty( "userStatus" ).getValue().toString().equals( "1" ) ){
				
				btnUserBlock.setVisible( true );
				// btnUserExpireSession.setVisible( true );
				record.getItemProperty( "userStatus" ).setValue( "Active" );
			} else if(  record.getItemProperty( "userStatus" ).getValue().toString().equals( "0" ) ) {
				record.getItemProperty( "userStatus" ).setValue( "Registered" );
			} else {
				record.getItemProperty( "userStatus" ).setValue( "Unknown" );
			}
			
			if( record.getItemProperty( "userSession" ).getValue() == null || record.getItemProperty( "userSession" ).getValue().toString().trim().isEmpty() ){
				 btnUserExpireSession.setVisible( false );
			} else {
				
				
				// Only show this if change pass button is not on
				if( !record.getItemProperty( "changePass" ).getValue().toString().trim().equals( "1" ) ){
					btnUserExpireSession.setVisible( true );
				}
				
			}
			
			log.debug( "Change password state: "+record.getItemProperty( "changePass" ).getValue() );
			
			if( record.getItemProperty( "changePass" ).getValue().toString().trim().equals( "1" ) ){
				 btnUserExpirePassword.setVisible( false );
			} else {
				// Only show this if expire session button is not on [ This avoid account lockout due to password expiration & session expiration done to a single user
				// ... due to password reset timeout
				if( ! ( record.getItemProperty( "userSession" ).getValue() == null || record.getItemProperty( "userSession" ).getValue().toString().trim().isEmpty() ) ){
					btnUserExpirePassword.setVisible( true );
				}
			}
			
			

			log.debug( " Logged in profile id: "+getCurrentUserProfileId() );
			
			if ( permSet.contains( EnumPermission.USER_SET_RESET_PASSWORD.val ) ) {
				this.btnUserSetCreds.setVisible( true );
			}
			
			
			
		} else {
			
			// this.btnUserBlock.setVisible( false );
			// this.btnUserActivate.setVisible( false );
			// this.btnUserSetCreds.setVisible( false );
			this.btnUserEditPersonalInfo.setVisible( true );
			// this.btnUserExpirePassword.setVisible( false );
			// this.btnUserExpireSession.setVisible( false );
			// this.btnUserSetCreds.setVisible( false );
			// this.btnUserChangeProfile.setVisible( false );
			this.btnUserChangeCreds.setVisible( true );
			
			if( record.getItemProperty( "userStatus" ).getValue().toString().equals( "2" ) ){
				record.getItemProperty( "userStatus" ).setValue( "Blocked" );
			}else if ( record.getItemProperty( "userStatus" ).getValue().toString().equals( "1" ) ){
				record.getItemProperty( "userStatus" ).setValue( "Active" );
			} else if(  record.getItemProperty( "userStatus" ).getValue().toString().equals( "0" ) ) {
				record.getItemProperty( "userStatus" ).setValue( "Registered" );
			} else {
				record.getItemProperty( "userStatus" ).setValue( "Unknown" );
			}
			
		}
		
		
		
		
		
		
		String orgStatus = record.getItemProperty( "orgStatus" ).getValue().toString();
		if( orgStatus.equals( "1" ) ){
			record.getItemProperty( "orgStatus" ).setValue( "Active" );
		} else if( orgStatus.equals( "100" ) ){
			record.getItemProperty( "orgStatus" ).setValue( "Blocked" );
		} else {
			record.getItemProperty( "orgStatus" ).setValue( "Unknown" );
		}
		

	}
	
	
	protected Out expirePassMultiUserRecord ( Collection< Item > records ){
		return mTxn.expirePassMultiUserRecord( records );
		
	}
	
	
	protected Out expireSessionMultiUserRecord ( Collection< Item > records ){
		return mTxn.expireSessionMultiUserRecord( records );
		
	}
	
	
	protected Out activateMultiUserRecord ( Collection< Item > records ){
		return mTxn.activateMultiUserRecord( records );
		
	}
	
	protected Out blockMultiUserRecord ( Collection< Item > records ){
		
		return mTxn.blockMultiUserRecord( records );
		
	}
	

	
	protected void expireSessionMultiHandler( Collection< Item > records){
		
		Out out = expireSessionMultiUserRecord( records );
		

		if( out.getStatusCode() == 1 ){
			Notification.show( out.getMsg(),
					Notification.Type.HUMANIZED_MESSAGE );
			refreshRecord(  );
			
		} else {
			Notification.show( "Expiring some selected user(s) session(s) failed. Please try again / Contact support.",
					Notification.Type.ERROR_MESSAGE );
			
		}

	}
	
	protected void expirePassMultiHandler( Collection< Item > records){
		
		Out out = expirePassMultiUserRecord( records );
		

		if( out.getStatusCode() == 1 ){
			Notification.show( out.getMsg(),
					Notification.Type.HUMANIZED_MESSAGE );
			refreshRecord( );
			
		} else {
			Notification.show( "Expiring some selected user password(s) failed. Please try again / Contact support.",
					Notification.Type.ERROR_MESSAGE );
			
		}

	}

	protected void activateMultiHandler( Collection< Item > records){
		
		Out out = activateMultiUserRecord( records );
		
	
		if( out.getStatusCode() == 1 ){
			Notification.show( out.getMsg(),
					Notification.Type.HUMANIZED_MESSAGE );
			refreshRecord( );
			
		} else {
			Notification.show( "Activating some selected user(s) session(s) failed. Please try again / Contact support.",
					Notification.Type.ERROR_MESSAGE );
			
		}
	
	}
	
	protected void blockMultiHandler( Collection< Item > records){
		
		Out out = blockMultiUserRecord( records );
		
	
		if( out.getStatusCode() == 1 ){
			Notification.show( out.getMsg(),
					Notification.Type.HUMANIZED_MESSAGE );
			refreshRecord(  );
			
		} else {
			Notification.show( "Blocking some selected user(s) session(s) failed. Please try again / Contact support.",
					Notification.Type.ERROR_MESSAGE );
			
		}
	
	}
	
	
	protected void refreshHandler( ){
		
		boolean status = refreshRecord();
		
	
		if( status ){
			Notification.show( "Refresh completed.",
					Notification.Type.HUMANIZED_MESSAGE );
			log.debug( "Refresh completed. ");
			
		} else {
			Notification.show( "Refresh failed. Please try again / Contact support.",
					Notification.Type.ERROR_MESSAGE );
			log.debug( "Refresh failed. ");
			
		}
	
	}
	
	
	
	private void showPopup() {
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
		if(record != null  ) {
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
