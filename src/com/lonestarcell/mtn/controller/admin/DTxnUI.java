package com.lonestarcell.mtn.controller.admin;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.design.admin.DTxnUIDesign;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DTxnUI extends DTxnUIDesign implements DUserUIInitializable<DMainUI,ISubUI>, ISubUI, DUIControllable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private DMainUI ancestor;
	private Button btnActive = new Button();
	private Logger log = LogManager.getLogger( DMainUI.class.getName() );
	protected Set< Short > permSet;
	
	private ApplicationContext springAppContext;
	
	DTxnUI(DMainUI a){
		this.setPermSet( a.getPermSet() );
		this.setSpringAppContext( a.getSpringAppContext() );
		init( a );
	}
	
	protected DTxnUI( ApplicationContext cxt ){
		this.setSpringAppContext( cxt );
		this.setPermSet( null );
	}
	
	
	

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}




	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}




	@Override
	public void attachCommandListeners() {
			
		Button btnDefault = null;
		if( permSet.contains( EnumPermission.REPORT_VIEW_MERCHANT.val ) ){
			this.attachBtnArchive();
			this.btnArchive.setVisible( true );
			this.btnArchive.setEnabled( true );
			btnDefault = btnArchive;
			log.info( "DTxnUI Perm count [ in btnArchive ]: "+permSet.size() );
		} else {
			this.btnArchive.setVisible( false );
			this.btnArchive.setEnabled( false );
		}
		
		log.info( "DTxnUI Perm count: "+permSet.size() );
		if( permSet.contains( EnumPermission.REPORT_VIEW_TRANSACTION.val ) ){
			this.attachBtnToday();
			this.btnDay.setVisible( true );
			this.btnDay.setEnabled( true );
			 btnDefault = btnDay;
		} else {
			this.btnDay.setVisible( false );
			this.btnDay.setEnabled( false );
		}
		
		if( btnDefault != null ) {
			 btnDefault.click();
		} else {
			log.info( "Default btn is null." );
			Iterator< Short > itr = permSet.iterator();
			while( itr.hasNext() ) {
				log.info( "Perm id: "+itr.next() );
			}
			
		}
				
		
		
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
				new DTxnStateMerchantArchiveUI( getParentUI() );
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
		
		// swap( new DTxnStateArchiveUI( getParentUI() ) );
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
	

	

}
