package com.lonestarcell.mtn.controller.admin;

import com.lonestarcell.mtn.design.admin.DNewUserUIDesign;
import com.vaadin.ui.Component;

public class DNewUserUI extends DNewUserUIDesign implements DUserUIInitializable<DUserUI, DNewUserUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DUserUI ancestor;
	
	DNewUserUI( DUserUI a){
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
		
		ancestor.getAncestorUI().getcMainContent().setHeightUndefined();
		ancestor.setHeightUndefined();
		
		ancestor.swap(cuid);
		
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
	public DNewUserUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DNewUserUI p) {
		// TODO Auto-generated method stub
		
	}
	
	


}
