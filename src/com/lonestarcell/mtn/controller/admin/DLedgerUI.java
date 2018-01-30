package com.lonestarcell.mtn.controller.admin;

import com.vaadin.server.FontAwesome;

public class DLedgerUI extends DTxnUI implements DUserUIInitializable<DMainUI,ISubUI >, DUIControllable {

	private static final long serialVersionUID = 1L;
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
		swap( new DTxnStateLedgerArchiveUI( getParentUI() ) );
		attachCommandListeners();
	}

	
	

}
