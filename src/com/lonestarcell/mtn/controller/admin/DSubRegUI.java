package com.lonestarcell.mtn.controller.admin;

import com.lonestarcell.mtn.model.util.EnumPermission;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.VerticalLayout;

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

	
	

}
