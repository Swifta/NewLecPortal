package com.lonestarcell.mtn.controller.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class AllRowsActionsUIInfo extends AbstractAllRowsActionsUI<MTxn, OutTxn, TextChangeListenerTxn<OutTxn> >{

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( AllRowsActionsUIInfo.class.getName() );

	public AllRowsActionsUIInfo( MTxn mTxn, Grid grid, In in, boolean allowDateFilters,
			PaginationUIController pageC) {
		super(in, allowDateFilters, pageC);
		this.setModel( mTxn );
		this.setGrid( grid );
		this.init();
	}
	
	@Override
	protected void setModel( MTxn model ){
		this.model = model;
	}

	@Override
	protected void setOutTxnMeta() {
		
		outTxnMeta = new OutTxnMeta();
		outTxnMeta.setTotalRevenue( new ObjectProperty<String>( "0", String.class ) );
		outTxnMeta.setTotalRecord( new ObjectProperty<String>( "0", String.class ) );
		
		Out out = model.setTxnMeta( in, outTxnMeta );
		if( out.getStatusCode() != 1 )
			Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
		
	}

	@Override
	protected void setNewPage(int page) {
		container.removeAllItems();
		inTxn.setPage( page );
		// TODO validate input
		model.setInfoRetryToday(in, container );
		format();
		
	}

	@Override
	protected void refreshGridData() {
	
		container.removeAllItems();
		//TODO validate response
		Out out = model.setInfoRetryToday(in, container );
		model.setTxnMeta(in, outTxnMeta );
		
		if( out.getStatusCode() != 1 ) {
			Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
			return;
		}
		
		format();
		
	}

	@Override
	protected void addFilterField(BeanItemContainer<OutTxn> container,
			HeaderRow filterHeader, String itemId) {
		TextField tF = new TextField();
		tF.setStyleName("sn-tf-filter");
		tF.setDescription("Search");
		tF.setInputPrompt("Filter/Search");
		tF.setWidth("100%");
		HeaderCell cFilter = filterHeader.getCell(itemId);
		cFilter.setComponent(tF);
		
		TextChangeListenerTxn<OutTxn> tChangeListener = getTextChangeListner( container, itemId, tF );
		tF.addTextChangeListener( tChangeListener );
		tFSearchFields.add( tF );
		
		ShortcutListener enterListener = getSearchShortcutListener( tF, itemId, container );
		tF.addFocusListener( getSearchFocusListener( tF, enterListener ) );
		tF.addBlurListener( getSearchBlurListener( tF, enterListener ) );
		
		tF.setDescription( "Type to filter / Press Enter to search" );
		
		
	}

	@Override
	protected ShortcutListener getSearchShortcutListener(TextField tF,
			String itemId, BeanItemContainer<OutTxn> container) {
		
		return new ShortcutListener( "", KeyCode.ENTER, null){
			private static final long serialVersionUID = 1L;
			
			

			@Override
			public void handleAction(Object sender, Object target) {
				log.debug( "Enter search shortcut clicked." );
				String val = tF.getValue();
				if( val == null )
					return;
				val = val.trim();
				if( val.isEmpty() )
					return;
				
				
				if( (inTxn.getSearchSID() == null )
						&& ( inTxn.getSearchMoID() == null )
						&& ( inTxn.getSearchMeterNo() == null )
						&& ( inTxn.getSearchMSISDN() == null )
						&& ( inTxn.getSearchStatusDesc() == null ) ){
					
					log.debug( "No search required." );
					
					return;
					
				} 
				
				
				container.removeAllItems();
				
				log.debug( "Proceeding with search." );
				
				In in = new In();
				
				BData<InTxn> inBData = new BData<>();
				inTxn.setPage( 1 );
				inBData.setData( inTxn );
				in.setData( inBData );
				
				
				
				Out out = model.setInfoRetryToday(in, container );
				
				model.setTxnMeta( in, outTxnMeta );
				
				if( out.getStatusCode() != 1 ){
					Notification.show( out.getMsg(), Notification.Type.WARNING_MESSAGE );
					return;
				}
				format();
				
			}
		
		};

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setBeanItemContainer() {
		container = ((BeanItemContainer<OutTxn>) ((GeneratedPropertyContainer)  grid.getContainerDataSource())
				.getWrappedContainer());
		
	}

	@Override
	protected void setGrid( Grid grid ) {
		this.grid = grid;
		
	}

	@Override
	protected TextChangeListenerTxn<OutTxn> getTextChangeListner(
			BeanItemContainer<OutTxn> container, String itemId, TextField tF) {
			return new TextChangeListenerTxn<OutTxn>( container, inTxn, itemId, tF );
	}

}
