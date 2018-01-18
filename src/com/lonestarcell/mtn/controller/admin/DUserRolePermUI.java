package com.lonestarcell.mtn.controller.admin;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutProfile;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DUserRolePermDesign;
import com.lonestarcell.mtn.model.admin.MSettings;
import com.lonestarcell.mtn.model.admin.MUtil;
import com.lonestarcell.mtn.spring.user.entity.ProfilePermissionMap;
import com.lonestarcell.mtn.spring.user.repo.ProfilePermissionMapRepo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class DUserRolePermUI extends DUserRolePermDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private Item record;
	private Logger log = LogManager.getLogger( DUserRolePermUI.class.getName() );
	
	private ApplicationContext springAppContext;
	private ProfilePermissionMapRepo profilePermMapRepo;
	
	DUserRolePermUI( DUserRoleUI a, short profileId ){
		this.setSpringAppContext( a.getSpringAppContext() );
		this.setProfilePermMapRepo( this.springAppContext.getBean( ProfilePermissionMapRepo.class ) );
		init( profileId );
	}
	
	
	
	public ProfilePermissionMapRepo getProfilePermMapRepo() {
		return profilePermMapRepo;
	}



	public void setProfilePermMapRepo(ProfilePermissionMapRepo profilePermMapRepo) {
		this.profilePermMapRepo = profilePermMapRepo;
	}



	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}


	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}


	private void init( short profileId ){
		setRecord( record );
		attachCommandListeners();
		loadPermissions( profileId );
	}
	
	public void loadPermissions( short profileId ){
		
		this.tPerm.removeAllItems();
		List< ProfilePermissionMap > lsProfilePermMap = profilePermMapRepo.findByProfileProfileId( profileId );
		int size = lsProfilePermMap.size();
		log.debug( "Profile Permission map Count: "+size, profilePermMapRepo );
		Iterator< ProfilePermissionMap > itrProfilePermMap = lsProfilePermMap.iterator();
		
		
		String permLabel = "Permission(s)";
		tPerm.addItem( permLabel );
		tPerm.setChildrenAllowed( permLabel, true );
		
		
		if( size == 0 ){
			
			tPerm.addItem( "No permission" );
			tPerm.setParent( "No permission", permLabel );
			tPerm.setChildrenAllowed( "No permission", false );
			
			return;
		}
		
		
		
		while( itrProfilePermMap.hasNext() ){
			
			ProfilePermissionMap profilePermMap = itrProfilePermMap.next();
			String permName = profilePermMap.getPermission().getName();
			String profile = profilePermMap.getProfile().getProfileName();
			log.debug( profile+" - "+permName, profilePermMap );
			
			// item = tPermChildren.addItem( perm );
			// tPermChildren.setChildrenAllowed( item, false );
			
			tPerm.addItem( permName );
			tPerm.setParent( permName, permLabel );
			tPerm.setChildrenAllowed( permName, false );
		}
		
	}
	public Item getRecord() {
		return record;
	}



	public void setRecord(Item record) {
		this.record = record;
	}



	@Override
	public void attachCommandListeners() {
	  //this.attachBtnSave();
	  //this.attachBtnAddRole();
		
		this.attachBtnDelete();
		
		
		
	}
	
	private void attachBtnDelete(){
		this.btnDelete.addClickListener( e->{ deleteRole();});
	}
	public void deleteRole(){
		
	}
	
	
	
	/*private void attachBtnAddRole(){
		this.btnAddNewRole.addClickListener(  e->{ 
			// processingPopup.close();
			new DUserNewRoleUI( record );
		} );
	}*/
	
	
	
	
	private BeanItemContainer< OutProfile > getProfiles(){
		

		MSettings mSettings = new MSettings(  getCurrentUserId(), getCurrentUserSession()  );
		InSettings inData = new InSettings();
		setSettingsAuth( inData );
		
		inData.setProfileContainer( new BeanItemContainer<>( OutProfile.class) );
		
		BData< InSettings > bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = mSettings.setProfiles(in );
		if( out.getStatusCode() != 1 ){
			Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
		}
		
		log.debug( "Set profiles msg: "+out.getMsg() );
		log.debug( "Set profiles status: "+out.getStatusCode() );
		
		return inData.getProfileContainer();
	}
	
	/* @SuppressWarnings("unchecked")
	private void setProfileId(){
		OutProfile profile = (OutProfile) comboProfile.getValue();
		if( profile == null )
			throw new IllegalStateException( "Select profile." );
		record.getItemProperty( "profileId" ).setValue( profile.getProfileId() );
	} */
	
	@SuppressWarnings("unchecked")
	private void setDummyPassword(){
		record.getItemProperty( "password" ).setValue( MUtil.genNewPass() );
	}
	
	
	/*
	private void attachBtnSave(){
		
		this.btnSave.addClickListener( new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				try {
					if( isFormValid() ){
						
						log.debug( "Form is valid." );
						
						Out out = addNewUser();
						
						if( out.getStatusCode() == 1 ){
							resetFields();
							Notification.show( out.getMsg(),
									Notification.Type.HUMANIZED_MESSAGE );
						}else{
							Notification.show( out.getMsg(),
									Notification.Type.ERROR_MESSAGE );
							lbErrorMsg.removeStyleName("sn-display-none");
							lbNormalMsg.addStyleName("sn-display-none");
							lbErrorMsg.setValue( out.getMsg() );
						}
						
					}else{
						log.debug( "Form has errors." );
					}
				} catch ( Exception e) {
					
					String msg = e.getMessage();
					if( msg == null || msg.trim().isEmpty() )
						msg = "Error occured. Please try again/Contact support.";
					
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					lbErrorMsg.setValue( msg );
					e.printStackTrace();
					
				}
				
				
			}});
	} */
	
	
	
	/* private Out addNewUser(){
		
		MUserDetails mUserDetails = new MUserDetails(  getCurrentUserId(), getCurrentUserSession()  );
		InUserDetails inData = new InUserDetails();
		
		setAuth( inData );
		setProfileId();
		setDummyPassword();
		
		inData.setRecord( record );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		Out out = mUserDetails.addNewUser( in );
		return out;
		
		
	} */
	
	/*@SuppressWarnings("unchecked")
	private void resetFields(){
		tFNewUsername.clear();
		tFNewFirstName.clear();
		tFNewLastName.clear();
		tFNewSurname.clear();
		tFNewEmail.clear();
		setDefaultProfile((BeanItemContainer<OutProfile>) comboProfile.getContainerDataSource(), 4);
		
	} */
	
	/* private boolean isFormValid(){
		
		if( !this.isUsernameTFValid( this.tFNewUsername ) )
			return false;
		if( !this.isEmailTFValid( this.tFNewEmail ) )
			return false;

		if( !this.isRequiredTFValid( this.tFNewFirstName ) )
			return false;
		if( !this.isRequiredTFValid( this.tFNewLastName ) )
			return false;
		if( !this.isTFValid( this.tFNewSurname ) )
			return false;
	
		return true;
	} */
	
	
	/*
	private boolean isTFValid (TextField tF){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				TFValidator v = (TFValidator) itr.next();
				msg += v.getErrorMessage();
			}
			
			lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
			
			return false;
			
		} else {
			
			lbNormalMsg.removeStyleName("sn-display-none");
			lbErrorMsg.addStyleName("sn-display-none");
			
			
		}
		
		return true;
		
	}
	
	*/

	
	/*
	private boolean isRequiredTFValid (TextField tF){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				RequiredTFValidator v = (RequiredTFValidator) itr.next();
				msg += v.getErrorMessage();
			}
			
			lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
			
			return false;
			
		} else {
			
			lbNormalMsg.removeStyleName("sn-display-none");
			lbErrorMsg.addStyleName("sn-display-none");
			
			
		}
		
		return true;
		
	} */
	
	
	
	
	
	/*
	private boolean isUsernameTFValid (TextField tF){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				UsernameTFValidator v = (UsernameTFValidator) itr.next();
				msg += v.getErrorMessage();
			}
			
			lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
			
			return false;
			
		} else {
			
			lbNormalMsg.removeStyleName("sn-display-none");
			lbErrorMsg.addStyleName("sn-display-none");
			
			
		}
		
		return true;
		
	} */
	
	
	
	/*
	private boolean isEmailTFValid (TextField tF){
		
		if( !tF.isValid() ){
			
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			
			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while( itr.hasNext() ){
				EmailValidatorCustom v = (EmailValidatorCustom) itr.next();
				msg += v.getErrorMessage();
			}
			
			lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
			
			return false;
			
		} else {
			
			lbNormalMsg.removeStyleName("sn-display-none");
			lbErrorMsg.addStyleName("sn-display-none");
			
			
		}
		
		return true;
		
	} */
	
	


	

	
	/*
	@SuppressWarnings("unchecked")
	private void setPropertyDataSource(){
		
		lbNormalMsg.removeStyleName("sn-display-none");
		lbErrorMsg.addStyleName("sn-display-none");
		
		record.getItemProperty( "username" ).setValue( "" );
		record.getItemProperty( "newUsername" ).setValue( "" );
		record.getItemProperty( "email" ).setValue( "" );
		record.getItemProperty( "newEmail" ).setValue( "" );
		record.getItemProperty( "newFirstName" ).setValue( "" );
		record.getItemProperty( "newLastName" ).setValue( "" );
		record.getItemProperty( "newSurname" ).setValue( "" );
		
		
		this.tFNewUsername.setPropertyDataSource( record.getItemProperty( "username" ) );
		this.tFNewEmail.setPropertyDataSource( record.getItemProperty( "email" ) );
		this.tFNewFirstName.setPropertyDataSource( record.getItemProperty( "newFirstName" ) );
		this.tFNewLastName.setPropertyDataSource( record.getItemProperty( "newLastName" ) );
		this.tFNewSurname.setPropertyDataSource( record.getItemProperty( "newSurname" ) );
		
		EmailValidatorCustom emailValidator = new EmailValidatorCustom( "Field required in valid format" );
		emailValidator.init(lbNormalMsg, lbErrorMsg, "" );
		this.tFNewEmail.addValidator( emailValidator );
		
		UsernameTFValidator usernameTFValidator = new UsernameTFValidator( "Field equired in valid format" );
		usernameTFValidator.init(lbNormalMsg, lbErrorMsg, "" );
		this.tFNewUsername.addValidator( usernameTFValidator );
		
		
		this.tFNewFirstName.addValidator( new RequiredTFValidator( "Field required in valid format" ) );
		this.tFNewLastName.addValidator( new RequiredTFValidator( "Field required in valid format" ) );
		this.tFNewSurname.addValidator( new TFValidator( "Field not in valid format" ) );
		
		this.tFNewUsername.setInvalidCommitted( true );
		this.tFNewEmail.setInvalidCommitted( true );
		
		
	} */
	
	
	


	
	private void setAuth( InUserDetails inData ){
		
		inData.setUsername( UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME ).toString() );
		inData.setUserSession(  UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString()  );	
	}
	
	private void setSettingsAuth( InSettings inData ){
		
		inData.setUsername( UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME ).toString() );
		inData.setUserSession(  UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString()  );

		
	}
	
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}
	
	


}
