package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUISub;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUITxn;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MSub;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.ui.Grid;

public class DTxnStateArchiveUI extends DTxnStateUI {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( DTxnStateArchiveUI.class.getName() );
	
	
	DTxnStateArchiveUI( ISubUI a ){
		super( a.getSpringAppContext() );
		this.setInDate( inTxn, 4*365 );
		mSub = new MSub(getCurrentUserId(), getCurrentUserSession(),
				getCurrentTimeCorrection(), a.getSpringAppContext() );
		
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	
	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Subscriber Transaction Archive");
	}
	
	@Override
	protected AllRowsActionsUISub getHeaderController( IModel mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, true, true, pageC );
	}
	
	@Override
	protected AllRowsActionsUISub getFooterController( IModel mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, false, false, pageC );
	}


}
