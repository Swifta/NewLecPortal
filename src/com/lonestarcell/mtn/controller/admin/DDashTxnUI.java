package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.design.admin.DDashTxnUIDesign;
import com.vaadin.ui.Component;

public class DDashTxnUI extends DDashTxnUIDesign implements DUserUIInitializable<DDashUI, DDashTxnUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DDashUI ancestor;
	private Logger log = LogManager.getLogger();
	
	DDashTxnUI( DDashUI a){
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
	public void init(DDashUI a) {
		setAncestorUI( a );
		setContent();
		
	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DDashUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DDashUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DDashTxnUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DDashTxnUI p) {
		// TODO Auto-generated method stub
		
	}
	
	


}
