package com.lonestarcell.mtn.controller.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.model.admin.MSub;
import com.vaadin.data.Item;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class RowActionsUISub extends VerticalLayout implements DUIControllable{
	
	private static final long serialVersionUID = 1L;
	private Button btnDetails;
	private Button btnRefresh;
	private Item record;
	private Logger log = LogManager.getLogger( RowActionsUISub.class.getName() );
	private MSub mSub;
	
	public RowActionsUISub( MSub mSub, Item record ){
		this.mSub = mSub;
		setRecord( record );
		
		init();
	}
	
	private void init(){
		setContent();
		attachCommandListeners();
	}
	
	private void setContent(){
		
		this.addStyleName( "sn-more-drop-down" );
		this.setSizeUndefined();
		this.setMargin( true );
		this.setSpacing( true );
		
		btnDetails = new Button( );
		btnRefresh = new Button( );
		
		
		btnDetails.setDescription( "More details" );
		btnRefresh.setDescription( "Refresh record" );
		
		
		btnDetails.addStyleName( "borderless icon-align-top" );
		btnRefresh.addStyleName( "borderless icon-align-top" );
		
		
		btnDetails.setIcon( FontAwesome.ALIGN_RIGHT );
		btnRefresh.setIcon( FontAwesome.REFRESH );
		
		
		this.addComponent( btnDetails );
		this.addComponent( btnRefresh );
		
		
		
		
	}

	public Item getRecord() {
		return record;
	}

	public void setRecord(Item record) {
		this.record = record;
		
	}

	@Override
	public void attachCommandListeners() {
		this.attachBtnDetails();
		this.attachBtnRefresh();
		
	}
	
	private void attachBtnRefresh(){
		this.btnRefresh.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Notification.show(
						"Actual behavior in mute.", Notification.Type.WARNING_MESSAGE );
				
				/*
				
				log.debug( "Refresh record button clicked." );
				if( record == null )
					Notification.show(
							"No record set for operaton.",
							Notification.Type.ERROR_MESSAGE);
				
				
				
				Collection<Item> records = new ArrayList<>();
				records.add( record );
				Out out = mTxn.refreshMultiTxnRecord( records );
				log.debug( "Row refresh status: "+out.getStatusCode() );
				if( out.getStatusCode() == 1 )
					Notification.show(
							"Record refreshed successfully." );
				else
					Notification.show(
							"Failed to refresh this record. Please try again.",
							Notification.Type.WARNING_MESSAGE );
				
				*/
				
			}
			
		});
	}
	
	
	private void attachBtnDetails(){
		this.btnDetails.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Notification.show(
						"Actual behavior in mute.", Notification.Type.WARNING_MESSAGE );
				
				log.debug( "Actual behavior in mute.", this );
				
				/*
				if( record == null )
					Notification.show(
							"No record set for operaton.",
							Notification.Type.ERROR_MESSAGE);
				
				new DTxnDetailsUI( record );
				*/
			}
			
		});
	}
	
	

	
}