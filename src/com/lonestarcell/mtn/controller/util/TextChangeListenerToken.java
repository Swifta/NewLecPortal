package com.lonestarcell.mtn.controller.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.InTxn;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

public class TextChangeListenerToken<O> implements TextChangeListener{

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<O> container;
	private String itemId;
	private InTxn inTxn;
	
	private Logger log = LogManager.getLogger( AbstractAllRowsActionsUI.class.getName() );
	
	
	TextChangeListenerToken( BeanItemContainer<O> container, InTxn inTxn,
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
		
		if( itemId.equals( "swiftaId" ) ){
			inTxn.setSearchSID( val );
		} else if( itemId.equals( "itronId" ) ) {
			inTxn.setSearchItronId( val );
		}else if( itemId.equals( "meterNo" ) ) {
			inTxn.setSearchMeterNo( val );
		}else if( itemId.equals( "tokenStatus" ) ) {
			inTxn.setSearchTokenStatus( val );
		}else if( itemId.equals( "txnType" ) ) {
			inTxn.setSearchTxnType( val );
		}
		
		container.removeContainerFilters(itemId);
		if (val != null && !val.isEmpty()) {
			container.addContainerFilter(new SimpleStringFilter(itemId,
					val, true, false));
			
			
		}

	}
	
}