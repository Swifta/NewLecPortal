package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MSub;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.PopupVisibilityEvent;
import com.vaadin.ui.PopupView.PopupVisibilityListener;
import com.vaadin.ui.VerticalLayout;

public class MultiRowActionsUILedger extends MultiRowActionsUISub {
	
	private static final long serialVersionUID = 1L;
	public MultiRowActionsUILedger(){
	}
	public MultiRowActionsUILedger( IModel mSub, In in, Grid grid, PopupView popupView ){
		super( mSub,in,grid,popupView );
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
				
				new DPgExportLimitUILedger( records );
			}
			
		});
	}

	
}