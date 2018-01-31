package com.lonestarcell.mtn.controller.util;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.InTxn;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

public class TextChangeListenerUser<O> implements TextChangeListener{

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<O> container;
	private String itemId;
	private InTxn inTxn;
	
	private Logger log = LogManager.getLogger( AbstractAllRowsActionsUI.class.getName() );
	
	
	TextChangeListenerUser( BeanItemContainer<O> container, InTxn inTxn,
		String itemId, TextField tF ){
		this.container = container;
		this.itemId = itemId;
		this.inTxn = inTxn;
	}

	@Override
	public void textChange(TextChangeEvent event) {

		log.debug( "Item id: "+itemId );
		String val = event.getText();
		if( val != null && val.trim().isEmpty() )
			val = null;
		

		
		
		if( itemId.equals( "email" ) ){
			inTxn.setSearchEmail( val );
		} else if( itemId.equals( "username" ) ) {
			inTxn.setSearchUsername( val );
		}else if( itemId.equals( "profile" ) ) {
			inTxn.setSearchProfile( val );
		}else if( itemId.equals( "org" ) ) {
			inTxn.setSearchOrg( val );
		}else if( itemId.equals( "userStatus" ) ) {
			inTxn.setSearchUserStatus( val );
		}
		
		container.removeContainerFilters(itemId);
		if (val != null && !val.isEmpty()) {
			container.addContainerFilter(new SimpleStringFilter(itemId,
					val, true, false));
			
			
		}

	}
	
}