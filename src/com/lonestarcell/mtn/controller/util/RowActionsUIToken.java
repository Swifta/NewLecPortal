package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.admin.DTxnDetailsUI;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.model.admin.MMo;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class RowActionsUIToken extends VerticalLayout implements DUIControllable{
	
	private static final long serialVersionUID = 1L;
	private Button btnDetails;
	private Button btnRefresh;
	private Button btnSendTokenReq;
	
	private Item record;
	private Logger log = LogManager.getLogger( RowActionsUIToken.class.getName() );
	private MTxn mTxn;
	
	public RowActionsUIToken( MTxn mTxn, Item record ){
		this.mTxn = mTxn;
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
		
		btnSendTokenReq = new Button( );
		btnSendTokenReq.setDescription( "Request token" );
		btnSendTokenReq.addStyleName( "borderless icon-align-top" );
		btnSendTokenReq.setIcon( FontAwesome.SEND_O );
		
	    this.addComponent( btnSendTokenReq );
		
		
		
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
		this.attachBtnSendTokenReq();
		
	}
	
	private void attachBtnRefresh(){
		this.btnRefresh.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				refresh();
				
			}
			
		});
	}
	
	
	private void refresh(){
		
		log.debug( "Refresh record button clicked." );
		if( record == null )
			Notification.show(
					"No record set for operaton.",
					Notification.Type.ERROR_MESSAGE);
		
		
		
		Collection<Item> records = new ArrayList<>();
		records.add( record );
		Out out = mTxn.refreshMultiTokenRecord( records );
		log.debug( "Row refresh status: "+out.getStatusCode() );
		if( out.getStatusCode() == 1 )
			Notification.show(
					"Record refreshed successfully." );
		else
			Notification.show(
					"Failed to refresh this record. Please try again.",
					Notification.Type.WARNING_MESSAGE );
		
	}
	
	
	private void attachBtnSendTokenReq() {
		
		this.btnSendTokenReq.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				MMo m = new MMo( getCurrentUserId(), getCurrentUserSession() );
				
				In in = new In();
				BData<InMo> inBData = new BData<>();
				InMo inMo = new InMo();
				
				inMo.setMmoId( ( String ) record.getItemProperty( "mmoId" ).getValue() );
				//inMo.setMmoId( "19876379" );
				//inMo.setAcctRef( "90099887766" );
				//inMo.setMsisdn( "231888210000" );
				//inMo.setAmount( ( amount*100 )+"" );
				//inMo.setCurrency( "LRD" );
				
				inBData.setData( inMo );
				in.setData( inBData );
				Out out = m.tokenRetry( in );
				
				if( out.getStatusCode() == 1 ) {
					refresh();
				} else {
					Notification.show("Oops... error sending token req. Please try again later.",
							Notification.Type.WARNING_MESSAGE );
				}
				
				
				
			}
			
		});
	}

	
	
	private void attachBtnDetails(){
		this.btnDetails.addClickListener( new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if( record == null )
					Notification.show(
							"No record set for operaton.",
							Notification.Type.ERROR_MESSAGE);
				
				new DTxnDetailsUI( record );
			}
			
		});
	}
	
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}

	
}