package com.lonestarcell.mtn.controller.admin;

import com.vaadin.server.FontAwesome;

public class DSubRegUI extends DTxnUI implements DUserUIInitializable<DMainUI,ISubUI >, DUIControllable {

	private static final long serialVersionUID = 1L;
	DSubRegUI(DMainUI a) {
		super(a.getSpringAppContext() );
		this.init(a);
		this.btnDay.setIcon( FontAwesome.USER_SECRET );
		this.btnDay.setCaption( "Subscriber Reg." );
		this.btnArchive.setVisible( false );
	}
	
	@Override
	public void setContent() {
		setHeader();
		setFooter();
		// swap( new DTxnStateUI( getParentUI() ) ); 
		swap( new DTxnStateSubRegArchiveUI( getParentUI() ) );
		attachCommandListeners();
	}

	
	

}
