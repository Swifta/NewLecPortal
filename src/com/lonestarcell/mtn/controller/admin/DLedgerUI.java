package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.model.util.EnumPermission;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.VerticalLayout;

public class DLedgerUI extends DTxnUI implements DUserUIInitializable<DMainUI,ISubUI >, DUIControllable {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger();
	DLedgerUI(DMainUI a) {
		super(a.getSpringAppContext() );
		this.init(a);
		this.btnDay.setIcon( FontAwesome.BRIEFCASE );
		this.btnDay.setCaption( "Ledger" );
		this.btnArchive.setVisible( false );
		
		
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		// swap( new DTxnStateUI( getParentUI() ) ); 
		log.info( "Ledger, perm count: "+permSet.size() );
		if( permSet.contains( EnumPermission.REPORT_VIEW_LEDGER.val ) ){
			swap( new DTxnStateLedgerArchiveUI( getParentUI() ) );
			attachCommandListeners();
		} else {
			VerticalLayout c = new VerticalLayout();
			swap( c );
		}
	}

	
	

}
