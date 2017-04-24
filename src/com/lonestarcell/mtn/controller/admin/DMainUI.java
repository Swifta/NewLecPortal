package com.lonestarcell.mtn.controller.admin;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DManagementUIDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DMainUI extends DManagementUIDesign implements View,
DUIControllable, DUserUIInitializable<DMainUI, DMainUI> {

	/**
	 **/
	private static final long serialVersionUID = 1L;
	private DMainUI ancestor;
		
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	private Button btnHMenuPrev;
	
	public DMainUI(){
		init( this );
	}

	@Override
	public void setHeader() {
		this.moreDropDown.addStyleName("sn-invisible");
		lbUsername.setValue((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		
	}
		
	

	@Override
	public void setContent() {
		
		setHeader();
		setFooter();
		swap( new DDashUI( getParentUI() ) );
		setHMenuState( btnHMenuDash );
		attachCommandListeners();
		
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
		setContent();
	}

	@Override
	public void setFooter() {
		
		String startYear = "2017";
		
		Calendar cal = Calendar.getInstance();
		String currentYear = cal.get( Calendar.YEAR )+"";
		
		
		this.lbClient.setValue( "&nbspMTN Liberia,&nbsp" );
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
		this.attachBtnHMenuUser();
		this.attachBtnHMenuDash();
		this.attachBtnHMenuTxn();
		this.attachBtnHMenuPayment();
		this.attachBtnHMenuToken();
		
		//Drop Down Menu
		this.attachBtnFx();
		this.attachBtnProfile();
		this.attachBtnUserOps();
		this.attachLogout();
		
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
	
	private void attachBtnHMenuPayment(){
		this.btnHMenuPayment.addClickListener(new ClickListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Payment menu clicked. " );
				
				if( setHMenuState( btnHMenuPayment ) )
					swap( new DPaymentUI( getParentUI() ) );
				
			}
			
		});
	}
	
	private void attachBtnHMenuUser(){
		this.btnHMenuUser.addClickListener(new ClickListener(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "User menu clicked. " );
				
				if( setHMenuState( btnHMenuUser ) )
					swap( new DUserUI( getParentUI() ) );
				
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

				new DProfileFormUI();

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
	public void init(DManUIController duic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
	
		if (UI.getCurrent().getSession().getAttribute("username") == null)
			UI.getCurrent().getNavigator().navigateTo("login");
		
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
	

}
