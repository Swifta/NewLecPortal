package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.design.admin.DTxnUIDesign;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class DTokenUI extends DTxnUIDesign implements DUserUIInitializable<DMainUI,DTokenUI>, DUIControllable {

	/**
	 * 
	 */
	
	private DMainUI ancestor;
	private Button btnActive;
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	
	DTokenUI(DMainUI a){
		init( a );
		
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void attachCommandListeners() {
		this.attachBtnArchive();
		this.attachBtnToday();
	}
	
	private void attachBtnToday(){
		btnActive = btnDay;
		this.btnDay.addClickListener( new ClickListener() {


			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if( isHMenuActiveBtn( btnDay ) ) 
					return;
				new DTokenStateUI( getParentUI() );
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
				new DTokenStateArchiveUI( getParentUI() );
				btnArchive.addStyleName( "sn-left-menu-active" );
				btnDay.removeStyleName( "sn-left-menu-active" );
				
			}
			
		} );
	}
	@Override
	public void setHeader() {
		this.lbFormHeader.setValue( "ITRON Token Requests" );
		
	}

	
	@Override
	public void setContent() {
		setHeader();
		setFooter();
		swap( new DTokenStateUI( getParentUI() ) ); 
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
	public DTokenUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DTokenUI p) {
		// TODO Auto-generated method stub
		
	}
	

	

}
