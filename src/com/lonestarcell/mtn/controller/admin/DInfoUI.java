package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.design.admin.DTxnUIDesign;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class DInfoUI extends DTxnUIDesign implements DUserUIInitializable<DMainUI,DInfoUI>, DUIControllable {

	/**
	 * 
	 */
	
	private DMainUI ancestor;
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	private Button btnActive;
	
	DInfoUI(DMainUI a){
		init( a );
		
	}
	private static final long serialVersionUID = 1L;

	

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		swap( new DInfoStateUI( getParentUI() ) ); 
		attachCommandListeners();
		
	}

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
				new DInfoStateUI( getParentUI() );
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
				new DInfoStateArchiveUI( getParentUI() );
				btnArchive.addStyleName( "sn-left-menu-active" );
				btnDay.removeStyleName( "sn-left-menu-active" );
				
			}
			
		} );
	}

	@Override
	public void setHeader() {
		this.lbFormHeader.setValue( "ITRON Customer Info & Token Retry" );
		
	}
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
	public DInfoUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DInfoUI p) {
		// TODO Auto-generated method stub
		
	}
	

	

}
