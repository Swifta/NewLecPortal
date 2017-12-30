package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUIPayment;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.ui.Grid;

public class DPaymentStateArchiveUI extends DPaymentStateUI {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( DPaymentStateArchiveUI.class.getName() );
	
	
	DPaymentStateArchiveUI( DPaymentUI a){
		super(  );
		this.setInDate(inTxn, 300 );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	
	@Override
	public void setHeader() {
		this.lbDataTitle.setValue( "Payment & SMS Archive" );
	}
	
	@Override
	protected AllRowsActionsUIPayment getHeaderController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIPayment( mTxn, grid, in, true, true, pageC );
	}
	
	@Override
	protected AllRowsActionsUIPayment getFooterController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIPayment( mTxn, grid, in, false, false, pageC );
	}


}
