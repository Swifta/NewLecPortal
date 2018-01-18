package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUISub;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUITxn;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.model.admin.MSub;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.ui.Grid;

public class DTxnStateArchiveUI extends DTxnStateUI {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( DTxnStateArchiveUI.class.getName() );
	
	
	DTxnStateArchiveUI( DTxnUI a){
		super( a.getSpringAppContext() );
		this.setInDate(inTxn, 300 );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	
	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Transaction Archive");
	}
	
	@Override
	protected AllRowsActionsUISub getHeaderController( MSub mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, true, true, pageC );
	}
	
	@Override
	protected AllRowsActionsUISub getFooterController( MSub mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, false, false, pageC );
	}


}
