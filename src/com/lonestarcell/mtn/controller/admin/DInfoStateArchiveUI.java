package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUIInfo;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.ui.Grid;

public class DInfoStateArchiveUI extends DInfoStateUI {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( DInfoStateArchiveUI.class.getName() );
	
	
	DInfoStateArchiveUI( DInfoUI a){
		super(  );
		this.setInDate(inTxn, 300 );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	
	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("ITRON Customer Info & Vend Retry Archive");
	}
	
	@Override
	protected AllRowsActionsUIInfo getHeaderController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIInfo( mTxn, grid, in, true, true, pageC );
	}
	
	@Override
	protected AllRowsActionsUIInfo getFooterController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIInfo( mTxn, grid, in, false, false, pageC );
	}


}
