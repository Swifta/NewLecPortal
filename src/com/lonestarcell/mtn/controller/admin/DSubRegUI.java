package com.lonestarcell.mtn.controller.admin;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.design.admin.DTxnUIDesign;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DSubRegUI extends DTxnUIDesign implements DUserUIInitializable<DMainUI,ISubUI>, ISubUI, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DMainUI ancestor;
	private Logger log = LogManager.getLogger();
	protected Set< Short > permSet;
	private ApplicationContext springAppContext;
	
	
	DSubRegUI(DMainUI a) { 
		this.setPermSet( a.getPermSet() );
		this.setSpringAppContext( a.getSpringAppContext() );
		this.init(a);
		this.btnDay.setIcon( FontAwesome.USER_SECRET );
		this.btnDay.setCaption( "Subscriber Reg." );
		this.btnArchive.setVisible( false );
		this.btnArchive.setEnabled( false );
	}
	
	


	@Override
	public void setContent() {
		setHeader();
		setFooter();
		if( permSet.contains( EnumPermission.REPORT_VIEW_SUBSCRIBER_REG.val )){
			swap( new DTxnStateSubRegArchiveUI( getParentUI() ) );
			attachCommandListeners();
		} else {
			VerticalLayout c = new VerticalLayout();
			c.setWidth( "100%" );
			c.setHeight( "100%" );
			swap( c );
		}
	}
	
	

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}




	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}


	

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
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
	public ISubUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(ISubUI p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VerticalLayout getcMainContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Short> getPermSet() {
		return permSet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPermSet(Set<Short> permSet) {
		if( permSet == null )
			this.permSet = UI.getCurrent().getSession().getAttribute( Set.class );
		else
			this.permSet = permSet;
		
	}


	@Override
	public void attachCommandListeners() {
		// TODO Auto-generated method stub
		
	}
	

}
