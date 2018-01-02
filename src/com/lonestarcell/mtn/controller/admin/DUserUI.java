package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.design.admin.DUserUIDesign;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class DUserUI extends DUserUIDesign implements DUserUIInitializable<DMainUI,DUserUI>, DUIControllable {

	/**
	 * 
	 */
	
	private DMainUI ancestor;
	private Button btnActive;
	private Component rightContent;
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	
	DUserUI(DMainUI a){
		init( a );
		
	}
	
	

	public Component getRightContent() {
		return rightContent;
	}

	public void setRightContent(Component rightContent) {
		this.rightContent = rightContent;
	}



	private static final long serialVersionUID = 1L;

	@Override
	public void attachCommandListeners() {
		this.attachBtnNewUser();
		this.attachBtnUsers();
		this.attachBtnRolePerm();
	}
	
	private void attachBtnNewUser(){
		this.btnNewUser.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if( isHMenuActiveBtn( btnNewUser ) )
					return;
				
				new DNewUserUI( getParentUI() );
				// btnNewUser.addStyleName( "sn-left-menu-active" );
				// btnUsers.removeStyleName( "sn-left-menu-active" );
				
			}
			
		});
	}
	
	
	private void attachBtnUsers(){
		btnActive = btnUsers;
		this.btnUsers.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if( isHMenuActiveBtn( btnUsers ) )
					return;
				new DUserStateUI( getParentUI() );
				// btnUsers.addStyleName( "sn-left-menu-active" );
				// btnNewUser.removeStyleName( "sn-left-menu-active" );
				
			}
			
		});
	}
	
	
	private void attachBtnRolePerm(){
		// btnActive = btnUserRolePerm;
		this.btnUserRolePerm.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if( isHMenuActiveBtn( btnUserRolePerm ) )
					return;
				new DUserRolePermUI( getParentUI() );
				//btnUsers.addStyleName( "sn-left-menu-active" );
				//btnNewUser.removeStyleName( "sn-left-menu-active" );
				
			}
			
		});
	}
	
	
	private boolean isHMenuActiveBtn( Button btn ){
		if( btnActive.equals( btn ) ){
			return true;
		}
		
		
		btnActive.removeStyleName( "sn-left-menu-active" );
		btn.addStyleName( "sn-left-menu-active" );
		btnActive = btn;
		
		return false;
}



	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		swap( new DUserStateUI( getParentUI() ) ); 
		attachCommandListeners();
		
	}

	@Override
	public void swap(Component cuid) {
		this.cForms.replaceComponent(cForms.getComponent( 0 ), cuid);
		log.debug( "UI is swapped." );
		
	}

	@Override
	public void init(DMainUI a) {
		setRightContent( this.cForms );
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
	public DUserUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DUserUI p) {
		// TODO Auto-generated method stub
		
	}
	

	

}
