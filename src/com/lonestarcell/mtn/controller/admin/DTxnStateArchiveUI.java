package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.controller.util.AbstractAllRowsActionsUI;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUISub;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.TextChangeListenerSub;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MSub;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.vaadin.ui.Grid;

public class DTxnStateArchiveUI extends DTxnStateUI<Transaction001Repo> {

	private static final long serialVersionUID = 1L;
	
	protected Logger log = LogManager.getLogger( DTxnStateArchiveUI.class.getName() );
	
	
	DTxnStateArchiveUI( ISubUI a ){
		super( a.getSpringAppContext() );
		this.setInDate( inTxn, 4*365 );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	

	@Override
	protected IModel<Transaction001Repo> getiModel( ApplicationContext cxt ) {
		return new MSub(getCurrentUserId(), getCurrentUserSession(),
				getCurrentTimeCorrection(), cxt );
	}
	
	
	/*
	 * This is for sub classes, not to call init.
	 */
	protected DTxnStateArchiveUI( ApplicationContext cxt ){
		super( cxt );
		this.setInDate( inTxn, 4*365 );
	}

	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Subscriber Transaction Archive");
	}
	
	@Override
	protected AbstractAllRowsActionsUI< Transaction001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean> > getHeaderController( IModel<Transaction001Repo> mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, true, true, pageC );
	}
	
	@Override
	protected AbstractAllRowsActionsUI< Transaction001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean> > getFooterController( IModel< Transaction001Repo > mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, false, false, pageC );
	}


}
