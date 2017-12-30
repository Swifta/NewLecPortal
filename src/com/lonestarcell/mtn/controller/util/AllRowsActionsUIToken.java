package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class AllRowsActionsUIToken extends AbstractAllRowsActionsUI<MTxn, OutTxn, TextChangeListenerToken<OutTxn> >{

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( AllRowsActionsUIToken.class.getName() );

	public AllRowsActionsUIToken( MTxn mTxn, Grid grid, In in, boolean allowDateFilters, boolean isHeader,
			PaginationUIController pageC) {
		super(in, allowDateFilters, isHeader, pageC);
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
			Notification.show( out.getMsg(), Notification.Type.WARNING_MESSAGE );
		
	}

	@Override
	protected void setNewPage(int page) {
		super.setNewPage( page );
		container.removeAllItems();
		inTxn.setPage( page );
		model.searchTokenToday(in, container );
		format();
		
	}

	@Override
	protected void refreshGridData() {
	
		container.removeAllItems();
		//TODO validate response
		Out out = model.searchTokenToday(in, container );
		model.searchTokenMeta(in, outTxnMeta );
		
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
		
		TextChangeListenerToken<OutTxn> tChangeListener = getTextChangeListner( container, itemId, tF );
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
				
				container.removeAllItems();
				
				log.debug( "Proceeding with search." );
				
				In in = new In();
				
				BData<InTxn> inBData = new BData<>();
				inTxn.setPage( 1 );
				inBData.setData( inTxn );
				in.setData( inBData );
				
				
				
				Out out = model.searchTokenToday(in, container );
				
				model.searchTokenMeta( in, outTxnMeta );
				
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
	protected TextChangeListenerToken<OutTxn> getTextChangeListner(
			BeanItemContainer<OutTxn> container, String itemId, TextField tF) {
			return new TextChangeListenerToken<OutTxn>( container, inTxn, itemId, tF );
	}

	@Override
	protected void initDataExportUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void attachBtnExportOps() {
			
			this.btnExportOps.addClickListener( new ClickListener(){

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					
					new DataExportUIToken( model, in, new ArrayList<Item>(), moreDropDown );

					
				}
				
			});
			
			
			this.btnExportOps.addBlurListener( new BlurListener(){

				private static final long serialVersionUID = 1L;

				@Override
				public void blur(BlurEvent event) {
					
					// btnExportOps.removeStyleName( "sn-btn-export-ops-active" );
					moreDropDown.removeStyleName( "sn-data-export-active" );
					log.debug( " Export menu blurred." );
					
				}
				
			});
			
			
		
	}

}
