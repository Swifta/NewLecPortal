package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.design.admin.DUsersUIDesign;
import com.vaadin.ui.Component;

public class DUsersUI extends DUsersUIDesign implements DUserUIInitializable<DUserUI, DUsersUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DUserUI ancestor;
	private Logger log = LogManager.getLogger();
	
	DUsersUI( DUserUI a){
		init( a );
	}

	@Override
	public void attachCommandListeners() {
		// TODO Auto-generated method stub
		
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
		swap(this);
		attachCommandListeners();
	}

	@Override
	public void swap(Component cuid) {
		//ancestor.setHeight("100%");
		//cuid.setHeight("100%");

		
		//ancestor.addStyleName("sn-p");
		//cuid.addStyleName("sn-c");
		
		cuid.setHeight("100%");
		ancestor.getAncestorUI().getcMainContent().setHeight( "100%" );
		//ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
		ancestor.setHeight( "100%" );
		
		
		log.debug( "Users height: "+cuid.getHeight() );
		
		ancestor.swap( cuid );
		
	}

	@Override
	public void init(DUserUI a) {
		setAncestorUI( a );
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
	public DUsersUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DUsersUI p) {
		// TODO Auto-generated method stub
		
	}
	
	


}
