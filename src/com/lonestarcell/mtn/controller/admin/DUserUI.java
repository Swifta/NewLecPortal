package com.lonestarcell.mtn.controller.admin;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.design.admin.DUserUIDesign;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class DUserUI extends DUserUIDesign implements
		DUserUIInitializable<DMainUI, DUserUI>, DUIControllable {

	/**
	 * 
	 */

	private DMainUI ancestor;
	private Button btnActive = new Button();
	private Component rightContent;
	private Logger log = LogManager.getLogger(DMainUI.class.getName());
	protected Set< Short > permSet;

	private ApplicationContext springAppContext;

	public DUserUI(DMainUI a) {

		this.setSpringAppContext(a.getSpringAppContext());
		this.setPermSet( a.getPermSet() );
		init(a);
	}

	
	public Set<Short> getPermSet() {
		return permSet;
	}


	public void setPermSet(Set<Short> permSet) {
		this.permSet = permSet;
	}


	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}

	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
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
		
		Button btnDefault = null;
		
		if( permSet.contains( EnumPermission.USER_ROLE_MANAGE.val ) ){
			this.attachBtnRolePerm();
			this.btnUserRolePerm.setVisible( true );
			this.btnUserRolePerm.setEnabled( true );
			btnDefault = btnUserRolePerm;
		} else {
			this.btnUserRolePerm.setVisible( false );
			this.btnUserRolePerm.setEnabled( false );
		}
		
		if( permSet.contains( EnumPermission.USER_ADD.val ) ){
			this.attachBtnNewUser();
			this.btnNewUser.setVisible( true );
			this.btnNewUser.setEnabled( true );
			btnDefault = btnNewUser;
		} else {
			this.btnNewUser.setVisible( false );
			this.btnNewUser.setEnabled( false );
		}
		
		if( permSet.contains( EnumPermission.USER_VIEW.val ) ){
			this.attachBtnUsers();
			this.btnUsers.setVisible( true );
			this.btnUsers.setEnabled( true );
			btnDefault = btnUsers;
		} else {
			this.btnUsers.setVisible( false );
			this.btnUsers.setEnabled( false );
		}
		
		if( btnDefault != null )
			btnDefault.click();
		
		
		
	}

	private void attachBtnNewUser() {
		this.btnNewUser.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (isHMenuActiveBtn(btnNewUser))
					return;

				new DNewUserUI(getParentUI());
				// btnNewUser.addStyleName( "sn-left-menu-active" );
				// btnUsers.removeStyleName( "sn-left-menu-active" );

			}

		});
	}

	private void attachBtnUsers() {
		this.btnUsers.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (isHMenuActiveBtn(btnUsers))
					return;
				new DUserStateUI(getParentUI());
				// btnUsers.addStyleName( "sn-left-menu-active" );
				// btnNewUser.removeStyleName( "sn-left-menu-active" );

			}

		});
	}

	private void attachBtnRolePerm() {
		// btnActive = btnUserRolePerm;
		this.btnUserRolePerm.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (isHMenuActiveBtn(btnUserRolePerm))
					return;
				new DUserRoleUI(getParentUI());

			}

		});
	}

	private boolean isHMenuActiveBtn(Button btn) {
		if (btnActive.equals(btn)) {
			return true;
		}

		btnActive.removeStyleName("sn-left-menu-active");
		btn.addStyleName("sn-left-menu-active");
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
		// swap(new DUserStateUI(getParentUI()));
		attachCommandListeners();

	}

	@Override
	public void swap(Component cuid) {
		this.cForms.replaceComponent(cForms.getComponent(0), cuid);
		log.debug("UI is swapped.");

	}

	@Override
	public void init(DMainUI a) {
		setRightContent(this.cForms);
		setAncestorUI(a);
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
