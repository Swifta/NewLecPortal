package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;

public class MultiRowActionsUIInfo extends AbstractMultiRowActionsUI {
	private static final long serialVersionUID = 1L;
	public MultiRowActionsUIInfo(MTxn mTxn, In in, Grid grid,
			PopupView popupView) {
		super(mTxn, in, grid, popupView);
		
	}
	
	@Override
	protected void attachBtnExport(){
		this.btnExport.addClickListener( new ClickListener(){

			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Collection<?> itemIds = grid.getSelectedRows();
				
				if( itemIds == null || itemIds.size() == 0 ) {
					Notification.show(
							"Please select at least on record to refresh.",
							Notification.Type.WARNING_MESSAGE );
					return;
				}
				
				
				Iterator< ? > itr = itemIds.iterator();
				
				Collection<Item> records = new ArrayList<>();
				
				
				
				
				while( itr.hasNext() ){
					Object itemId = itr.next();
					records.add( grid.getContainerDataSource().getItem( itemId ) );		
				}
				
				VerticalLayout v = new DataExportUIInfo( mTxn, in, records, new VerticalLayout() ).getcMoreOps();
				v.setWidth( "37px" );
				getPopupViewContainer().replaceComponent(exportUI, v );
				exportUI = v;
				
				otherOpUI.setVisible( false );
				exportUI.setVisible( true );
			}
			
		});
	}
	
	
	protected void attachBtnRefresh(){
		this.btnRefresh.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Collection<?> itemIds = grid.getSelectedRows();
				
				if( itemIds == null || itemIds.size() == 0 ) {
					Notification.show(
							"Please select at least on record to refresh.",
							Notification.Type.WARNING_MESSAGE );
					return;
				}
				
				
				Iterator< ? > itr = itemIds.iterator();
				
				Collection<Item> records = new ArrayList<>();
				
				while( itr.hasNext() ){
					Object itemId = itr.next();
					records.add( grid.getContainerDataSource().getItem( itemId ) );		
				}
				
				Out out = mTxn.refreshMultiInfoRecord( records );
				
				
				if( out.getStatusCode() == 1 )
					Notification.show(
							"All selected records have been refreshed.",
							Notification.Type.HUMANIZED_MESSAGE );
				else if( out.getStatusCode() == 2 )
					Notification.show(
							out.getMsg(),
							Notification.Type.WARNING_MESSAGE );
				else
					Notification.show(
							"Refresh operation failed.",
							Notification.Type.ERROR_MESSAGE );
				
			}
			
		});
	}

	
	

	

	
}




