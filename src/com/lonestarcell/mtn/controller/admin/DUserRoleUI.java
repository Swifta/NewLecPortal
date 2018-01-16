package com.lonestarcell.mtn.controller.admin;

import java.util.Iterator;




import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutProfile;
import com.lonestarcell.mtn.bean.OutUserDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.main.Person;
import com.lonestarcell.mtn.controller.util.EmailValidatorCustom;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.controller.util.TFValidator;
import com.lonestarcell.mtn.controller.util.UsernameTFValidator;
import com.lonestarcell.mtn.design.admin.DNewUserUIDesign;
import com.lonestarcell.mtn.model.admin.MSettings;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.model.admin.MUtil;
import com.lonestarcell.mtn.spring.entity.Profile;
import com.lonestarcell.mtn.spring.repo.ProfileRepo;
import com.lonestarcell.mtn.design.admin.DRoleUIDesign;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;import com.vaadin.ui.VerticalLayout;


@Component
@Scope( value = ConfigurableBeanFactory.SCOPE_PROTOTYPE )
public class DUserRoleUI extends DRoleUIDesign implements
		DUserUIInitializable<DUserUI, DUserRoleUI>, DUIControllable {

	private static final long serialVersionUID = 1L;

	private DUserUI ancestor;
	private Item record;
	private Logger log = LogManager.getLogger(DUserRoleUI.class.getName());
	
	@Autowired
	private ProfileRepo profileRepo;
	
	// @Autowired
	private DUserNewRoleUI dUserNewRoleUI;
	
	@Autowired
	private DUserRolePermUI dUserRolePermUI;
	
	@Autowired
	private Person person;
	
	private Thread t;

	DUserRoleUI(){
		
	}
	
	DUserRoleUI( DUserUI a ) {
		
	}
	
	

	public DUserNewRoleUI getdUserNewRoleUI() {
		return dUserNewRoleUI;
	}

	@Autowired
	public void setdUserNewRoleUI(DUserNewRoleUI dUserNewRoleUI) {
		this.dUserNewRoleUI = dUserNewRoleUI;
	}

	public Item getRecord() { 
		return record;
	}

	public void setRecord(Item record) {
		this.record = record;
	}

	@Override
	public void attachCommandListeners() {
		
		if( person != null ){
			log.debug( "Person bean is wired.", person );
		} else {
			log.debug( "Person bean is null.", this );
		}
		
		// this.attachBtnSave();
		this.attachBtnAddRole();
		this.attachAccoRole();
		
		

	}

	private void initComboProfile() {

		BeanItemContainer<OutProfile> profiles = getProfiles();

		/*
		 * comboProfile.setNullSelectionAllowed( false );
		 * comboProfile.setContainerDataSource( profiles );
		 * comboProfile.setItemCaptionMode( ItemCaptionMode.PROPERTY );
		 * comboProfile.setItemCaptionPropertyId( "profileName" );
		 */
		setDefaultProfile(profiles, 4);

	}

	//@Autowired
	// private DUserNewRoleUI dUserNewRoleUI;
	private void attachBtnAddRole( ) {
		this.btnAddNewRole.addClickListener(e -> {
			
			dUserNewRoleUI = getdUserNewRoleUI();
			dUserNewRoleUI.setRecord( record );
			dUserNewRoleUI.init( accoRoles );
			
			});
	}

	private void attachAccoRole() {

		
		// Initialize tabs with role [ Should be in a loop ]
		List< Profile > lsProfile = profileRepo.findAll();
		log.debug( "Total roles: "+lsProfile.size(), this );
		
		Iterator< Profile > itrProfile = lsProfile.iterator();
		
		this.accoRoles.removeAllComponents();
		
		
		while( itrProfile.hasNext() ){
			
			Profile profile = itrProfile.next();
			Tree tL = new Tree();
			tL.addItem("Loading...");
			
			VerticalLayout vL = new VerticalLayout( );
			vL.setCaption( profile.getProfileName() );
			vL.setId( profile.getProfileId()+"" );
			vL.setWidth( "100%" );
			vL.addComponent( tL );
			vL.setDescription( profile.getProfileDesc() );
			this.accoRoles.addTab(vL, profile.getProfileName() );
		}
		
		this.setImmediate( true );
		
		
		
		

		this.accoRoles.addSelectedTabChangeListener(e -> {

			// String tabId = e.getTabSheet().getId();
			
			VerticalLayout cRolePerm = ( VerticalLayout )e.getTabSheet().getSelectedTab();
			// String tabId = e.getTabSheet().getSelectedTab().getId();
			// String tabCap = e.getTabSheet().getSelectedTab().getCaption();

				try {

					Tree loadingT = new Tree();
					loadingT.addItem( "Loading..." );
					cRolePerm.removeAllComponents();
					cRolePerm.addComponent( loadingT );
					
					//DUserRolePermUI rolePerm = new DUserRolePermUI(  null );
					
					String sProfileTabId = cRolePerm.getId();
					if( sProfileTabId == null )
						sProfileTabId = "0";
					
					int profileId = Integer.parseInt( sProfileTabId );
	
					cRolePerm.removeAllComponents();
					dUserRolePermUI.init( ( short ) profileId, accoRoles );
					cRolePerm.addComponent( dUserRolePermUI );
					
					/*log.debug( "Loading..." );
					
					t = new Thread(new PassResetTimeoutThread( cRolePerm ));
					t.start();
					*/
					

				} catch (Exception ex) {
					
				
					
					/*Tree loadingT = new Tree();
					loadingT.addItem( "Error occured!" );
					cRolePerm.removeAllComponents();
					cRolePerm.replaceComponent( cRolePerm.getComponent( 0 ), loadingT );
					*/
					
					ex.printStackTrace();
				}

			});
	}

	private void setDefaultProfile(BeanItemContainer<OutProfile> profiles,
			int profileId) {

		Iterator<OutProfile> itr = profiles.getItemIds().iterator();
		while (itr.hasNext()) {

			OutProfile profile = itr.next();

			if (profile.getProfileId() == profileId) {
				// comboProfile.setValue( profile );
				break;
			}
		}
	}

	private BeanItemContainer<OutProfile> getProfiles() {

		MSettings mSettings = new MSettings(getCurrentUserId(),
				getCurrentUserSession());
		InSettings inData = new InSettings();
		setSettingsAuth(inData);

		inData.setProfileContainer(new BeanItemContainer<>(OutProfile.class));

		BData<InSettings> bData = new BData<>();
		bData.setData(inData);

		In in = new In();
		in.setData(bData);

		Out out = mSettings.setProfiles(in);
		if (out.getStatusCode() != 1) {
			Notification.show(out.getMsg(), Notification.Type.ERROR_MESSAGE);
		}

		log.debug("Set profiles msg: " + out.getMsg());
		log.debug("Set profiles status: " + out.getStatusCode());

		return inData.getProfileContainer();
	}

	/*
	 * @SuppressWarnings("unchecked") private void setProfileId(){ OutProfile
	 * profile = (OutProfile) comboProfile.getValue(); if( profile == null )
	 * throw new IllegalStateException( "Select profile." );
	 * record.getItemProperty( "profileId" ).setValue( profile.getProfileId() );
	 * }
	 */

	@SuppressWarnings("unchecked")
	private void setDummyPassword() {
		record.getItemProperty("password").setValue(MUtil.genNewPass());
	}

	/*
	 * private void attachBtnSave(){
	 * 
	 * this.btnSave.addClickListener( new ClickListener() {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public void buttonClick(ClickEvent event) {
	 * 
	 * try { if( isFormValid() ){
	 * 
	 * log.debug( "Form is valid." );
	 * 
	 * Out out = addNewUser();
	 * 
	 * if( out.getStatusCode() == 1 ){ resetFields(); Notification.show(
	 * out.getMsg(), Notification.Type.HUMANIZED_MESSAGE ); }else{
	 * Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none"); lbErrorMsg.setValue(
	 * out.getMsg() ); }
	 * 
	 * }else{ log.debug( "Form has errors." ); } } catch ( Exception e) {
	 * 
	 * String msg = e.getMessage(); if( msg == null || msg.trim().isEmpty() )
	 * msg = "Error occured. Please try again/Contact support.";
	 * 
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none"); lbErrorMsg.setValue( msg );
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * 
	 * }}); }
	 */

	/*
	 * private Out addNewUser(){
	 * 
	 * MUserDetails mUserDetails = new MUserDetails( getCurrentUserId(),
	 * getCurrentUserSession() ); InUserDetails inData = new InUserDetails();
	 * 
	 * setAuth( inData ); setProfileId(); setDummyPassword();
	 * 
	 * inData.setRecord( record );
	 * 
	 * BData<InUserDetails> bData = new BData<>(); bData.setData( inData );
	 * 
	 * In in = new In(); in.setData( bData );
	 * 
	 * Out out = mUserDetails.addNewUser( in ); return out;
	 * 
	 * 
	 * }
	 */

	/*
	 * @SuppressWarnings("unchecked") private void resetFields(){
	 * tFNewUsername.clear(); tFNewFirstName.clear(); tFNewLastName.clear();
	 * tFNewSurname.clear(); tFNewEmail.clear();
	 * setDefaultProfile((BeanItemContainer<OutProfile>)
	 * comboProfile.getContainerDataSource(), 4);
	 * 
	 * }
	 */

	/*
	 * private boolean isFormValid(){
	 * 
	 * if( !this.isUsernameTFValid( this.tFNewUsername ) ) return false; if(
	 * !this.isEmailTFValid( this.tFNewEmail ) ) return false;
	 * 
	 * if( !this.isRequiredTFValid( this.tFNewFirstName ) ) return false; if(
	 * !this.isRequiredTFValid( this.tFNewLastName ) ) return false; if(
	 * !this.isTFValid( this.tFNewSurname ) ) return false;
	 * 
	 * return true; }
	 */

	/*
	 * private boolean isTFValid (TextField tF){
	 * 
	 * if( !tF.isValid() ){
	 * 
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none");
	 * 
	 * Iterator<Validator> itr = tF.getValidators().iterator(); String msg = "";
	 * while( itr.hasNext() ){ TFValidator v = (TFValidator) itr.next(); msg +=
	 * v.getErrorMessage(); }
	 * 
	 * lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
	 * 
	 * return false;
	 * 
	 * } else {
	 * 
	 * lbNormalMsg.removeStyleName("sn-display-none");
	 * lbErrorMsg.addStyleName("sn-display-none");
	 * 
	 * 
	 * }
	 * 
	 * return true;
	 * 
	 * }
	 */

	/*
	 * private boolean isRequiredTFValid (TextField tF){
	 * 
	 * if( !tF.isValid() ){
	 * 
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none");
	 * 
	 * Iterator<Validator> itr = tF.getValidators().iterator(); String msg = "";
	 * while( itr.hasNext() ){ RequiredTFValidator v = (RequiredTFValidator)
	 * itr.next(); msg += v.getErrorMessage(); }
	 * 
	 * lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
	 * 
	 * return false;
	 * 
	 * } else {
	 * 
	 * lbNormalMsg.removeStyleName("sn-display-none");
	 * lbErrorMsg.addStyleName("sn-display-none");
	 * 
	 * 
	 * }
	 * 
	 * return true;
	 * 
	 * }
	 */

	/*
	 * private boolean isUsernameTFValid (TextField tF){
	 * 
	 * if( !tF.isValid() ){
	 * 
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none");
	 * 
	 * Iterator<Validator> itr = tF.getValidators().iterator(); String msg = "";
	 * while( itr.hasNext() ){ UsernameTFValidator v = (UsernameTFValidator)
	 * itr.next(); msg += v.getErrorMessage(); }
	 * 
	 * lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
	 * 
	 * return false;
	 * 
	 * } else {
	 * 
	 * lbNormalMsg.removeStyleName("sn-display-none");
	 * lbErrorMsg.addStyleName("sn-display-none");
	 * 
	 * 
	 * }
	 * 
	 * return true;
	 * 
	 * }
	 */

	/*
	 * private boolean isEmailTFValid (TextField tF){
	 * 
	 * if( !tF.isValid() ){
	 * 
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none");
	 * 
	 * Iterator<Validator> itr = tF.getValidators().iterator(); String msg = "";
	 * while( itr.hasNext() ){ EmailValidatorCustom v = (EmailValidatorCustom)
	 * itr.next(); msg += v.getErrorMessage(); }
	 * 
	 * lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );
	 * 
	 * return false;
	 * 
	 * } else {
	 * 
	 * lbNormalMsg.removeStyleName("sn-display-none");
	 * lbErrorMsg.addStyleName("sn-display-none");
	 * 
	 * 
	 * }
	 * 
	 * return true;
	 * 
	 * }
	 */

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContent() {

		OutUserDetails outUserDetails = new OutUserDetails();
		outUserDetails.setEmail("");
		Item item = new BeanItem<>(outUserDetails, OutUserDetails.class);

		this.setRecord(item);
		// this.setPropertyDataSource();
		log.debug("Content called.");
		setHeader();
		setFooter();
		swap(this);
		attachCommandListeners();
	}

	@Override
	public void swap(Component cuid) {

		cuid.setHeight("100%");
		ancestor.getAncestorUI().getcMainContent().setHeight("100%");
		ancestor.setHeight("100%");
		log.debug("Users height: " + cuid.getHeight());
		ancestor.swap(cuid);

	}

	@Override
	public void init(DUserUI a) {
		// Scale left footer by new user form container height.
		a.getRightContent().setHeightUndefined();
		setAncestorUI(a);
		this.initComboProfile();
		setContent();

	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub

	}

	@Override
	public DUserUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DUserUI a) {
		this.ancestor = a;

	}

	@Override
	public DUserRoleUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DUserRoleUI p) {
		// TODO Auto-generated method stub

	}

	/*
	 * @SuppressWarnings("unchecked") private void setPropertyDataSource(){
	 * 
	 * lbNormalMsg.removeStyleName("sn-display-none");
	 * lbErrorMsg.addStyleName("sn-display-none");
	 * 
	 * record.getItemProperty( "username" ).setValue( "" );
	 * record.getItemProperty( "newUsername" ).setValue( "" );
	 * record.getItemProperty( "email" ).setValue( "" ); record.getItemProperty(
	 * "newEmail" ).setValue( "" ); record.getItemProperty( "newFirstName"
	 * ).setValue( "" ); record.getItemProperty( "newLastName" ).setValue( "" );
	 * record.getItemProperty( "newSurname" ).setValue( "" );
	 * 
	 * 
	 * this.tFNewUsername.setPropertyDataSource( record.getItemProperty(
	 * "username" ) ); this.tFNewEmail.setPropertyDataSource(
	 * record.getItemProperty( "email" ) );
	 * this.tFNewFirstName.setPropertyDataSource( record.getItemProperty(
	 * "newFirstName" ) ); this.tFNewLastName.setPropertyDataSource(
	 * record.getItemProperty( "newLastName" ) );
	 * this.tFNewSurname.setPropertyDataSource( record.getItemProperty(
	 * "newSurname" ) );
	 * 
	 * EmailValidatorCustom emailValidator = new EmailValidatorCustom(
	 * "Field required in valid format" ); emailValidator.init(lbNormalMsg,
	 * lbErrorMsg, "" ); this.tFNewEmail.addValidator( emailValidator );
	 * 
	 * UsernameTFValidator usernameTFValidator = new UsernameTFValidator(
	 * "Field equired in valid format" ); usernameTFValidator.init(lbNormalMsg,
	 * lbErrorMsg, "" ); this.tFNewUsername.addValidator( usernameTFValidator );
	 * 
	 * 
	 * this.tFNewFirstName.addValidator( new RequiredTFValidator(
	 * "Field required in valid format" ) ); this.tFNewLastName.addValidator(
	 * new RequiredTFValidator( "Field required in valid format" ) );
	 * this.tFNewSurname.addValidator( new TFValidator(
	 * "Field not in valid format" ) );
	 * 
	 * this.tFNewUsername.setInvalidCommitted( true );
	 * this.tFNewEmail.setInvalidCommitted( true );
	 * 
	 * 
	 * }
	 */

	private void setAuth(InUserDetails inData) {

		inData.setUsername(UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME).toString());
		inData.setUserSession(UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR).toString());
	}

	private void setSettingsAuth(InSettings inData) {

		inData.setUsername(UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME).toString());
		inData.setUserSession(UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR).toString());

	}

	private long getCurrentUserId() {
		return (long) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USER_ID);
	}

	private String getCurrentUserSession() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR);
	}
	
	
	private class PassResetTimeoutThread implements Runnable {

		private VerticalLayout cRolePerm;
		PassResetTimeoutThread( VerticalLayout cRolePerm ){
			this.cRolePerm = cRolePerm;
		}
		@Override
		public void run() {

			try {



				log.debug( "Before the wait.", this );
				
				Thread.sleep(1000);
				
				log.debug( "The wait is over.", this );

				UI.getCurrent().access(new Runnable() {

					@Override
					public void run() {

						// DUserRolePermUI rolePerm = new DUserRolePermUI(  null );
						cRolePerm.removeAllComponents();
						cRolePerm.addComponent( dUserRolePermUI );
						
						log.debug( "UI updated.", this );
						

					}

				});

			} catch (InterruptedException e) {
				System.out.println("Timeout thread interrupted. Restarted.");
				e.printStackTrace();
				UI.getCurrent().access(new Runnable() {

					@Override
					public void run() {

						t = new Thread(new PassResetTimeoutThread( cRolePerm ));
						t.start();

					}

				});
			}

		}

	}

}
