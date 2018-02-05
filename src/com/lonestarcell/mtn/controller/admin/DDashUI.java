package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.design.admin.DDashUIDesign;
import com.vaadin.ui.Component;

public class DDashUI extends DDashUIDesign implements DUserUIInitializable<DMainUI,DDashUI>, DUIControllable {

	/**
	 * 
	 */
	
	private DMainUI ancestor;
	private Logger log = LogManager.getLogger( DDashUI.class.getName() );
	private ApplicationContext springAppContext;
	
	DDashUI(DMainUI a){
		this.setSpringAppContext( a.getSpringAppContext() );
		init( a );
		
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void attachCommandListeners() {
		//TODO
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
	public void setContent() {
		setHeader();
		setFooter();
		swap( new DDashTxnUI( getParentUI() ) ); 
		attachCommandListeners();
		this.setVMenu();
		
	}
	
	private void setVMenu( ){
		
		DateFormat sdf = new SimpleDateFormat( "MMM dd, yyyy" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		this.btnDash.setCaption( "[ "+tDate+ " ]");
		
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
	public DDashUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DDashUI p) {
		// TODO Auto-generated method stub
		
	}
	

	

}
