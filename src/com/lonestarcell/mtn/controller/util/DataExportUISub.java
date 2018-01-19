package com.lonestarcell.mtn.controller.util;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.controller.admin.DUIInitializable;
import com.lonestarcell.mtn.model.admin.MSub;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class DataExportUISub extends AbstractDDataExportUI<MSub, OutSubscriber, In > implements DUIInitializable {

	private Logger log = LogManager.getLogger();
	public DataExportUISub( MSub mSub, In in, Collection<Item> records, VerticalLayout cMoreOps ){
		super( mSub, in, records, cMoreOps );
		init();
	}
	
	
	@Override
	protected  boolean setAllExportRecords( MSub mSub, In in, Collection<Item>records ){
		
		container = new BeanItemContainer<>(OutSubscriber.class );
		
		Out out = new Out();
		
		log.debug( "Record is: "+records.size() );
		
		if( records.size() == 0 ){
			out = mSub.setTxnTodayExportData( in, container );
			
		} else {
			out = mSub.setExportDataMultiTxnToday(in, container, records );
		}
		
		boolean status = out.getStatusCode() == 1;
		if( status ){
			Notification.show( " Please select format to export", Notification.Type.TRAY_NOTIFICATION  );
		}else{
			Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
		}
		
		if( container == null ){
			log.debug( "Container is null." );
		} else {
			log.debug( "Export container of size: "+container.size(), this );
		} 
			
		return status;
		
	}


	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}
	
	


	@Override
	public void swap(Component cuid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void init() {
		super.init();
		
	}


	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}
	

}
