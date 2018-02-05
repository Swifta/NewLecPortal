package com.lonestarcell.mtn.controller.admin;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutUserDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DManagementUIDesign;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@UIScope
public class DMainUI extends DManagementUIDesign implements ISubUI, View,
DUIControllable, DUserUIInitializable<DMainUI, DMainUI> {

	/**
	 **/
	private static final long serialVersionUID = 1L;
	private DMainUI ancestor;
		
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	private Button btnHMenuPrev;
	
	private Item record;
	
	private ApplicationContext springAppContext;
	
	@Autowired
	private ProfileRepo person;
	
	private short profileId;
	private MUserSelfCare mUserDetails;
	private Set< Short > permSet;
	
	
	
	public DMainUI(){
		init( this );
	}
	
	
	
	

	public Set<Short> getPermSet() {
		return permSet;
	}





	public void setPermSet(Set<Short> permSet) {
		this.permSet = permSet;
	}





	public short getProfileId() {
		return profileId;
	}





	public void setProfileId(short profileId) {
		this.profileId = profileId;
	}





	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}
	
	@Autowired
	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}





	public Item getRecord() {
		return record;
	}



	public void setRecord(Item record) {
		this.record = record;
	}



	@Override
	public void setHeader() {
		this.moreDropDown.addStyleName("sn-invisible sn-main-ops");
		lbUsername.setPropertyDataSource( record.getItemProperty( "username" ));
		
	}
		
	

	@Override
	public void setContent() {
		
		
		setHeader();
		setFooter();
		// swap( new DDashUI( getParentUI() ) );
		// setHMenuState( btnHMenuDash );
		attachCommandListeners();
		
		
		
	}
	
	private boolean isUserDetailsSet(){
		
		String username = UI.getCurrent().getSession()
		.getAttribute(DLoginUIController.USERNAME).toString();
		
		String session = UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR ).toString();

		
		InUserDetails inData = new InUserDetails();
		inData.setUsername( username );
		inData.setUserSession( session );
		
		OutUserDetails user = new OutUserDetails();
		user.setUsername( username );
		Item r = new BeanItem<>( user, OutUserDetails.class );
		setRecord( r );
		inData.setRecord( r );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		if( record != null ) {
			mUserDetails = new MUserSelfCare();
			Out out = mUserDetails.setUserDetails( in );
			
			boolean is = out.getStatusCode() == 1;
			if( is ){
				
				Object obj = record.getItemProperty( "profileId" ).getValue();
				if( obj == null )
					is = false;
				this.setProfileId( Short.valueOf( obj.toString() ) );
			}
			return is;
		
		} 
		
		return false;
		
	}

	@Override
	public void swap(Component cuid) {
		//this.setHeight("100%");
		//cuid.setHeight("100%");
		//this.leftBar.setHeight("100%");
		//this.leftBar.addStyleName("sn-c");
		
		//this.addStyleName("sn-px");
		//cuid.addStyleName("sn-c");
		//this.content.setContent(cuid);
		
		this.cMainContent.replaceComponent( this.cMainContent.getComponent( 0 ), cuid );
		
		
		
	}

	@Override
	public void init(DMainUI a) {
		setAncestorUI( a );
	}

	@Override
	public void setFooter() {
		
		String startYear = "2017";
		
		Calendar cal = Calendar.getInstance();
		String currentYear = cal.get( Calendar.YEAR )+"";
		
		
		this.lbClient.setValue( "&nbspMTN Benin,&nbsp" );
		this.lbCRYearStart.setValue( startYear );
		this.lbCRYearCurrent.setValue( currentYear );
		
		if( startYear.equals( currentYear )) {
			
			this.lbCRYearCurrent.setVisible(false);
			this.lbYearSeparator.setVisible(false);
			
		}
		
	}

	@Override
	public DMainUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DMainUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DMainUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DMainUI p) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	public VerticalLayout getcMainContent() {
		return cMainContent;
	}

	public void setcMainContent(VerticalLayout cMainContent) {
		this.cMainContent = cMainContent;
	}

	@Override
	public void attachCommandListeners() {
		
		//H Menu
		// this.attachBtnHMenuDash();
		// this.attachBtnHMenuTxn();
		
		Button btnDefault = null;
		// User
		if( permSet.contains( EnumPermission.USER_MANAGE.val ) ){
			this.attachBtnHMenuUser();
			this.btnHMenuUser.setVisible( true );
			this.btnHMenuUser.setVisible( true );
			btnDefault = btnHMenuUser;
		} else {
			
			this.btnHMenuUser.setVisible( false );
			this.btnHMenuUser.setVisible( false );
			
		}
		
		// Ledger
		if( permSet.contains( EnumPermission.REPORT_VIEW_LEDGER.val )){
			this.attachBtnHMenuLedger();
			this.btnHMenuLedger.setEnabled( true );
			this.btnHMenuLedger.setVisible( true );
			btnDefault = btnHMenuLedger;
		} else {
			this.btnHMenuLedger.setEnabled( false );
			this.btnHMenuLedger.setVisible( false );
		}
		
		// Sub. Reg.
		if( permSet.contains( EnumPermission.REPORT_VIEW_SUBSCRIBER_REG.val )){
			this.attachBtnHMenuSubReg();
			this.btnHMenuSubReg.setEnabled( true );
			this.btnHMenuSubReg.setVisible( true );
			btnDefault = btnHMenuSubReg;
		} else {
			this.btnHMenuSubReg.setEnabled( false );
			this.btnHMenuSubReg.setVisible( false );
		}
		
		// Transaction [ Both subscriber & merchant ]
		
		if( permSet.contains( EnumPermission.REPORT_TRANSACTION.val ) ){
			this.attachBtnHMenuTxn();
			this.btnHMenuTxn.setEnabled( true );
			this.btnHMenuTxn.setVisible( true );
			btnDefault = btnHMenuTxn;
		} else {
			this.btnHMenuTxn.setEnabled( false );
			this.btnHMenuTxn.setVisible( false );
		}
		
		
		
		
		// Dash
		if( permSet.contains( EnumPermission.DASH_VIEW.val )){
			this.attachBtnHMenuDash();
			this.btnHMenuDash.setEnabled( true );
			this.btnHMenuDash.setVisible( true );
			btnDefault = btnHMenuDash;
		} else {
			this.btnHMenuDash.setEnabled( false );
			this.btnHMenuDash.setVisible( false );
		}
		

		if( btnDefault != null )
			btnDefault.click();
		

		
		

		
		

		
		
		
		
		
		// this.attachBtnHMenuToken();
		
		
		//Drop Down Menu
		this.attachBtnFx();
		this.attachBtnProfile();
		this.attachBtnUserOps();
		this.attachLogout();
		this.attachBtnSettings();
		
		
		// Disable Misc. for now
		
		// this.btnHMenuDash.setEnabled( true );
		// this.btnHMenuTxn.setEnabled( true );
		
		
		this.btnHMenuMisc.setEnabled( false );
		
		
		// this.btnHMenuToken.setEnabled( true );
		// this.btnHMenuPayment.setCaption( "x-subscriber 1" );
		// this.btnHMenuToken.setCaption( "x-subscriber 3" );
		
		
		
	}
	
	private void attachBtnHMenuLedger(){
		this.btnHMenuLedger.addClickListener(new ClickListener(){


			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Token menu clicked. " );
				
				if( setHMenuState( btnHMenuLedger ) )
					swap( new DLedgerUI( getParentUI() ) );
				
			}
			
		});
	}
	
	
	private void attachBtnHMenuToken(){
		this.btnHMenuToken.addClickListener(new ClickListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Token menu clicked. " );
				
				if( setHMenuState( btnHMenuToken ) )
					swap( new DTokenUI( getParentUI() ) );
				
			}
			
		});
	}
	
	private void attachBtnHMenuSubReg(){
		this.btnHMenuSubReg.addClickListener(new ClickListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Payment menu clicked. " );
				
				if( setHMenuState( btnHMenuSubReg ) )
					swap( new DSubRegUI( getParentUI() ) );
				
			}
			
		});
	}
	
	private void attachBtnHMenuUser(){
		
		/*
		if( this.getCurrentUserProfileId() != 1 ){
			btnHMenuUser.setVisible( false );
			btnHMenuUser.setEnabled( false );
		} */
		
		this.btnHMenuUser.addClickListener(new ClickListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "User menu clicked. " );
				
				if( setHMenuState( btnHMenuUser ) ){
					//swap( new DUserUI( getParentUI() ) );
					DUserUI dUserUI = new DUserUI( getParentUI() );
					swap( dUserUI );
				}
				
			}
			
		});
	}
	
	private void attachBtnHMenuDash(){
		this.btnHMenuDash.addClickListener(new ClickListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Dash menu clicked. " );
				
				if( setHMenuState( btnHMenuDash ) )
					swap( new DDashUI( getParentUI() ) );
				
			}
			
		});
	}
	
	private void attachBtnHMenuTxn(){
		this.btnHMenuTxn.addClickListener(new ClickListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Dash menu clicked. " );
				if( setHMenuState( btnHMenuTxn ) )
					swap( new DTxnUI( getParentUI() ) );
				
			}
			
		});
	}
	
	private void attachBtnUserOps() {
		System.out.println("Btn Ops attached.");
		this.btnUserOps.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				moreDropDown.removeStyleName("sn-invisible");

			}

		});

	}

	private void attachBtnFx() {
		this.btnFxRate.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DFxFormUI();

			}

		});
	}

	private void attachBtnProfile() {
		this.btnUserProfile.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				new DUserDetailsUI( record );
				
					

			}

		});
	}
	
	private void attachBtnSettings() {
		
		if( this.getCurrentUserProfileId() != 1 ){
			this.btnSettings.setVisible( false );
			this.btnSettings.setEnabled( false );
		}
		
		this.btnSettings.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				new DSettingsUI();
				
					

			}

		});
	}
	
	private void attachLogout() {
		this.btnLogout.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -7731164586803534336L;

			@Override
			public void buttonClick(ClickEvent event) {

				UI.getCurrent().getSession().close();
				UI.getCurrent().getPage().reload();

			}

		});
	}

	

	@Override
	public void enter(ViewChangeEvent event) {
	
		if (UI.getCurrent().getSession().getAttribute("username") == null) {
			
			log.debug( " No username set " );
			this.resetSessionData();
			UI.getCurrent().getNavigator().navigateTo("login");
			
		} else if( !this.isUserDetailsSet() ){
			logoutRedir();
			
		} else {
			
			// TODO load & set permissions
			if( isPermSet( profileId ) ) {
				setContent();
			} else {
				logoutRedir();
			}
			
		}
		
	}
	
	private boolean isPermSet( short profileId ){
		Set< Short > permSet = new HashSet< >();
		this.setPermSet( permSet );
		UI.getCurrent().getSession().setAttribute( Set.class, permSet );
		if( mUserDetails == null )
			return false;
		mUserDetails.setSpringAppContext(springAppContext);
		return mUserDetails.setProfilePermissionSet(profileId, permSet).getStatusCode() == 1;
	}
	
	
	private void logoutRedir(){
		Notification.show( "SET ERROR" , "Invalid user data", Notification.Type.ERROR_MESSAGE );
		log.debug( " No user data set " );
		this.resetSessionData();
		UI.getCurrent().getNavigator().navigateTo("login");
	}
	
	private void resetSessionData(){
		UI.getCurrent().getSession()
		.setAttribute(DLoginUIController.USERNAME, null);
		UI.getCurrent().getSession()
		.setAttribute(DLoginUIController.PROFILE_ID, null);
		UI.getCurrent().getSession()
		.setAttribute(DLoginUIController.SESSION_VAR, null);
	}
	
	private boolean setHMenuState( Button btnActive ) {
		
		if( btnHMenuPrev != null && btnHMenuPrev.equals( btnActive ))
			return false;
		
		if( btnHMenuPrev != null )
			btnHMenuPrev.removeStyleName( "sn-button-link-active" );
		
		btnActive.addStyleName( "sn-button-link-active" );
		btnHMenuPrev = btnActive;
		
		return true;
		
	}
	
	
	private int getCurrentUserProfileId(){
		return ( int ) UI.getCurrent().getSession().getAttribute( DLoginUIController.PROFILE_ID );
	}
	

}
