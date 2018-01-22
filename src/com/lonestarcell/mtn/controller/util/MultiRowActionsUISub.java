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

public class MultiRowActionsUISub extends VerticalLayout implements DUIControllable, PopupView.Content {
	
	private static final long serialVersionUID = 1L;
	private Button btnExport;
	private Button btnRefresh;
	private Grid grid;
	private IModel mSub;
	private In in;
	
	private Component exportUI;
	private VerticalLayout otherOpUI;
	
	private PopupView popupView;

	public MultiRowActionsUISub( IModel mSub, In in, Grid grid, PopupView popupView ){
		this.setPopupView( popupView);
		this.grid = grid;
		this.mSub = mSub;
		this.in = in;
		init( );
	}
	
	private void setPopupView( PopupView pop ){
		popupView = pop;
	}
	
	
	private void init() {
		setContent();
		this.attachCommandListeners();
	}
	
	private void setContent(){
		
		
		this.addStyleName( "sn-more-drop-down" );
		this.setSizeUndefined();
		this.setMargin( true );
		this.setSpacing( true );
		
		btnExport = new Button( );
		btnRefresh = new Button( );
		
		btnExport.setDescription( "Export selected records" );
		btnRefresh.setDescription( "Refresh selected records" );
		
		btnExport.addStyleName( "borderless icon-align-top" );
		btnRefresh.addStyleName( "borderless icon-align-top" );
		
		
		
		btnExport.setIcon( FontAwesome.SHARE_SQUARE_O );
		btnRefresh.setIcon( FontAwesome.REFRESH );
		
		otherOpUI = new VerticalLayout();
		otherOpUI.addComponent( btnExport );
		otherOpUI.addComponent( btnRefresh );
		
		exportUI = new VerticalLayout();
		exportUI.setWidth( "37px" );
		
		this.addComponent( otherOpUI );
		this.addComponent( exportUI );
		
		this.otherOpUI.setVisible( true );
		this.exportUI.setVisible( false );
		
		
		
		
		
		
	}


	@Override
	public void attachCommandListeners() {
		
		this.attachBtnRefresh();
		this.attachBtnExport();
		this.attachPopupView();
		
	}
	
	private void attachPopupView(){
		popupView.addPopupVisibilityListener( new PopupVisibilityListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void popupVisibilityChange(PopupVisibilityEvent event) {
				otherOpUI.setVisible( true );
				exportUI.setVisible( false );
			}
			
		});
	}
	
	private void attachBtnExport(){
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
				
				VerticalLayout v = new DataExportUISub( mSub, in, records, new VerticalLayout() ).getcMoreOps();
				v.setWidth( "37px" );
				getPopupViewContainer().replaceComponent(exportUI, v );
				exportUI = v;
				
				otherOpUI.setVisible( false );
				exportUI.setVisible( true );
			}
			
		});
	}
	
	private void attachBtnRefresh(){
		this.btnRefresh.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Notification.show(
						"Actual behavior muted.",
						Notification.Type.WARNING_MESSAGE );
				
				/*
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
				
				Out out = mSub.refreshMultiTxnRecord( records );
				
				
				if( out.getStatusCode() == 1 )
					Notification.show(
							"All selected records have been refreshed.",
							Notification.Type.HUMANIZED_MESSAGE );
				else if( out.getStatusCode() == 100 )
					Notification.show(
							out.getMsg(),
							Notification.Type.WARNING_MESSAGE );
				else
					Notification.show(
							"Refresh operation failed.",
							Notification.Type.ERROR_MESSAGE );
							
							*/
				
			} 
			
		});
	}

	@Override
	public String getMinimizedValueAsHTML() {
		return "<span>...</span>";
	}

	@Override
	public Component getPopupComponent() {
		return this;
	}
	
	private VerticalLayout getPopupViewContainer(){
		return this;
	}

	
}