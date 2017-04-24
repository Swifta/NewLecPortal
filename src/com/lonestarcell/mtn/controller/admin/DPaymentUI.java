package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.design.admin.DTxnUIDesign;
import com.vaadin.ui.Component;

public class DPaymentUI extends DTxnUIDesign implements DUserUIInitializable<DMainUI,DPaymentUI>, DUIControllable {

	/**
	 * 
	 */
	
	private DMainUI ancestor;
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	
	DPaymentUI(DMainUI a){
		init( a );
		
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void attachCommandListeners() {
		//TODO
	}
	
	

	@Override
	public void init(DManUIController duic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		swap( new DPaymentStateUI( getParentUI() ) ); 
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
	public DPaymentUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DPaymentUI p) {
		// TODO Auto-generated method stub
		
	}
	

	

}
