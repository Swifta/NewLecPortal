package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.design.admin.DTxnUIDesign;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class DTxnUI extends DTxnUIDesign implements DUserUIInitializable<DMainUI,DTxnUI>, DUIControllable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private DMainUI ancestor;
	private Button btnActive;
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	
	private ApplicationContext springAppContext;
	
	DTxnUI(DMainUI a){
		this.setSpringAppContext( a.getSpringAppContext() );
		init( a );
	}
	
	
	

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}




	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}




	@Override
	public void attachCommandListeners() {
		
		// Default button 
		 //btnActive = btnArchive;
		// btnDay.setVisible( false );
		
		// Initial button state
		// btnArchive.addStyleName( "sn-left-menu-active" );
		// btnDay.removeStyleName( "sn-left-menu-active" );
		
		btnActive = btnDay;
		this.attachBtnArchive();
		this.attachBtnToday();
	}
	
	private void attachBtnToday(){
		this.btnDay.addClickListener( new ClickListener() {


			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if( isHMenuActiveBtn( btnDay ) ) 
					return;
				new DTxnStateArchiveUI( getParentUI() );
				btnDay.addStyleName( "sn-left-menu-active" );
				btnArchive.removeStyleName( "sn-left-menu-active" );
				
			}
			
		} );
	}
	
	private boolean isHMenuActiveBtn( Button btn ){
			if( btnActive.equals( btn ) ){
				return true;
			}
			btnActive = btn;
			return false;
	}
	
	
	private void attachBtnArchive(){
		this.btnArchive.addClickListener( new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if( isHMenuActiveBtn( btnArchive ) ) 
					return;
				new DTxnStateArchiveUI( getParentUI() );
				btnArchive.addStyleName( "sn-left-menu-active" );
				btnDay.removeStyleName( "sn-left-menu-active" );
				
			}
			
		} );
	}
	

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		// swap( new DTxnStateUI( getParentUI() ) ); 
		swap( new DTxnStateArchiveUI( getParentUI() ) );
		attachCommandListeners();
		
		
		
	}

	@Override
	public void swap(Component cuid) {
		this.cForms.replaceComponent(cForms.getComponent( 0 ), cuid);
		log.debug( "SUB-UI is swapped." );
		
	}

	@Override
	public void init(DMainUI a) {
		setAncestorUI( a );
		setContent();
		
	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
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
	public DTxnUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DTxnUI p) {
		// TODO Auto-generated method stub
		
	}
	

	

}
