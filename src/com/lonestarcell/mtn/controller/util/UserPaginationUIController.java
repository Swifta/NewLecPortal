package com.lonestarcell.mtn.controller.util;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.model.admin.MUser;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;

public class UserPaginationUIController implements DUIControllable, Serializable{
	
	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger();

	private Map< String, Button > mapPageBtns;
	private BeanItemContainer<OutUser> beanItemContainer;
		
	private Button btnNextH;
	private Button btnNextF;
	
	private Button btnPrevH;
	private Button btnPrevF;
	
	private Button btnAfterPrevH;
	private Button btnAfterPrevF;
	
	private Button btnBeforeNextH;
	private Button btnBeforeNextF;
	
	private int currentPage = 1;
	private int pages = 0;
	private Label lbTotalRecords;
	
	private MUser mTxn;
	private InTxn inTxn;
	
	private In in;
	private OutTxnMeta outTxnMeta;
	
	public UserPaginationUIController( BeanItemContainer<OutUser> container, MUser mTxn, InTxn inTxn ){
		this.beanItemContainer = container;
		this.mTxn = mTxn;
		this.inTxn = inTxn;
		
		mapPageBtns = new HashMap<>(8);
	}
	
	public Map<String, Button> getListPageBtns(){
		return mapPageBtns;
	}
	
	

	public Label getLbTotalRecords() {
		return lbTotalRecords;
	}

	public void setLbTotalRecords(Label lbTotalRecords) {
		this.lbTotalRecords = lbTotalRecords;
	}
	
	

	public In getIn() {
		return in;
	}

	public void setIn(In in) {
		this.in = in;
	}

	public OutTxnMeta getOutTxnMeta() {
		return outTxnMeta;
	}

	public void setOutTxnMeta(OutTxnMeta outTxnMeta) {
		this.outTxnMeta = outTxnMeta;
	}

	@Override
	public void attachCommandListeners() {
		
		this.attachOPTotalRecords();
		this.attachBtnNext();
		this.attachBtnPrev();
		this.attachBtnBeforeNext();
		this.attachBtnAfterPrev();
		
		
	}
	
	private void attachBtnNext(){
		
		btnNextH.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnNextH has been clicked" );
				next( );
				
			}
			
		});
		
		
		btnNextF.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				next( );
				
			}
			
		});
	}
	
	private void attachBtnBeforeNext(){
		
		btnBeforeNextH.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnBeforeNextH has been clicked" );
				beforeNext( );
				
			}
			
		});
		
		
		btnBeforeNextF.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				beforeNext( );
				
			}
			
		});
	}
	
	private void attachBtnPrev(){
		
		this.btnPrevH.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnPrevH has been clicked" );
				prev( );
				
			}
			
		});
		
		
		this.btnPrevF.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				prev( );
				
			}
			
		});
	}
	

	
	private void attachBtnAfterPrev(){
		
		btnAfterPrevH.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnAfterPrevH has been clicked" );
				afterPrev( );
				
			}
			
		});
		
		
		btnAfterPrevF.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;
			
			

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "btnAfterPrevF has been clicked" );
				afterPrev( );
				
			}
			
		});
	}
	
	private void next( ){
		
		if( currentPage < pages ) {
			currentPage++;
		}
		navigation( );


		
	}
	
	private void prev( ){
		
		if( currentPage > 1) {
			currentPage--;
		}
		navigation( );
		
		
	}
	
	private void beforeNext( ){
		
		    int beforeNextPage = Integer.valueOf( btnBeforeNextH.getData().toString() );
		    
		    if( beforeNextPage == currentPage )
		    	return;
		   
		    log.debug( "Current Page: "+beforeNextPage );
		   
		    currentPage = beforeNextPage;
		    
			btnBeforeNextH.addStyleName( "sn-cur-page" );
			btnBeforeNextF.addStyleName( "sn-cur-page" );
			
			btnBeforeNextH.setDescription( currentPage+"/"+pages );
			btnBeforeNextF.setDescription( currentPage+"/"+pages );
			
			btnAfterPrevH.removeStyleName( "sn-cur-page" );
			btnAfterPrevF.removeStyleName( "sn-cur-page" );
			
			this.setNewPage( currentPage );
			
			
	}		
	
	private void afterPrev( ){
	   
		int afterPrevPage = Integer.valueOf( btnAfterPrevH.getData().toString() );
		
		if( afterPrevPage == currentPage )
	    	return;
		
		log.debug( "Current Page: "+afterPrevPage );
	   
		currentPage = afterPrevPage;
		btnAfterPrevH.addStyleName( "sn-cur-page" );
		btnAfterPrevF.addStyleName( "sn-cur-page" );
		
		btnAfterPrevH.setDescription( currentPage+"/"+pages );
		btnAfterPrevF.setDescription( currentPage+"/"+pages );
		
		btnBeforeNextH.removeStyleName( "sn-cur-page" );
		btnBeforeNextF.removeStyleName( "sn-cur-page" ); 
		
		this.setNewPage(  afterPrevPage );
		
	}		
	
	

	public void init() {
		this.attachCommandListeners();
	}
	
	
	private int getTotalPages( Long total ){
		
		int pages = 0;
		Float pageLength = 5F;
		pages = (int)Math.ceil( total/pageLength );
		
		return pages;
		
	}
	
	private void attachOPTotalRecords(){
		
		Long total = Long.valueOf( outTxnMeta.getTotalRecord().getValue().replaceAll(",", "") );
		this.initBtns( total );
		
		this.lbTotalRecords.addValueChangeListener( new ValueChangeListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				
					Long total = Long.valueOf( event.getProperty().getValue().toString().replaceAll(",", "") );
					initBtns( total );
					
				
			}
			
		});
	}
	
	private void initBtns( Long total ){
		
		btnNextH = mapPageBtns.get( "nextH" );
		btnNextF = mapPageBtns.get( "nextF" );
		
		btnPrevH = mapPageBtns.get( "prevH" );
		btnPrevF = mapPageBtns.get( "prevF" );
		
		btnAfterPrevH = mapPageBtns.get( "afterPrevH" );
		btnAfterPrevF = mapPageBtns.get( "afterPrevF" );
		
		btnAfterPrevH.setCaption( currentPage+"" );
		btnAfterPrevF.setCaption( currentPage+"" );
		
		btnAfterPrevH.setData( currentPage );
		btnAfterPrevF.setData( currentPage );
		
		btnBeforeNextH = mapPageBtns.get( "beforeNextH" );
		btnBeforeNextF = mapPageBtns.get( "beforeNextF" );
		
		btnBeforeNextH.setCaption( ( currentPage + 1)+"" );
		btnBeforeNextF.setCaption( ( currentPage + 1)+"" );
		
		btnBeforeNextH.setData( ( currentPage + 1) );
		btnBeforeNextF.setData( ( currentPage + 1) );
		
		
		pages = getTotalPages( total );
		if( pages <= 1 ){
			
			btnNextH.setVisible( false );
			btnNextF.setVisible( false );
			btnPrevH.setVisible( false );
			btnPrevF.setVisible( false );
			btnAfterPrevH.setVisible( false );
			btnAfterPrevF.setVisible( false );
			btnBeforeNextH.setVisible( false );
			btnBeforeNextF.setVisible( false );
			
		} else if( pages == 2){
			
			btnNextH.setVisible( false );
			btnNextF.setVisible( false );
			
			btnPrevH.setVisible( false );
			btnPrevF.setVisible( false );
			
			btnAfterPrevH.setVisible( true );
			btnAfterPrevF.setVisible( true );
			btnBeforeNextH.setVisible( true );
			btnBeforeNextF.setVisible( true );
			
		} else if( pages >=  3){
			
			btnPrevH.setVisible( false );
			btnPrevF.setVisible( false );
			
			btnNextH.setVisible( true );
			btnNextF.setVisible( true );
			
			btnAfterPrevH.setVisible( true );
			btnAfterPrevF.setVisible( true );
			btnBeforeNextH.setVisible( true );
			btnBeforeNextF.setVisible( true );
			
			pages = getTotalPages( total );
			this.navigation();
			
			
		} 
		
	}
	
	private void setNewPage( int page ){
		beanItemContainer.removeAllItems();
		inTxn.setPage( page );
		
		//TODO validate response
		
		mTxn.search(in, beanItemContainer );
		
		//mTxn.setTxnMeta(in, outTxnMeta );
		
		format();
		
	}
	
	private void format(){
		
		double revenue = Double.valueOf( outTxnMeta.getTotalRevenue().getValue().replaceAll(",", "") );
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		log.debug( "Formated revenue: "+nf.format( revenue ) );
		outTxnMeta.getTotalRevenue().setValue( nf.format( revenue ).replace( "$", "") );
		
		long records = Long.valueOf( outTxnMeta.getTotalRecord().getValue().toString().replaceAll(",", "") );
		nf = NumberFormat.getNumberInstance( Locale.US );
		outTxnMeta.getTotalRecord().setValue( nf.format( records ));
	}

	
	private void navigation(){
		
		
			if( pages >=  3){
			
				if( currentPage > 1 ){
					
					btnPrevH.setVisible( true );
					btnPrevF.setVisible( true );
				} else {
					btnPrevH.setVisible( false );
					btnPrevF.setVisible( false );
				}
			
				log.debug( "Current page: "+currentPage+" Total pages: "+pages );
			
				if( ( currentPage + 1 ) < pages ){
					
					btnNextH.setVisible( true );
					btnNextF.setVisible( true );
				} else {
					btnNextH.setVisible( false );
					btnNextF.setVisible( false );
				}
			
				if( currentPage <= pages ) {
					
					if( currentPage == pages){
						
						btnBeforeNextH.setCaption( currentPage+"" );
						btnBeforeNextF.setCaption( currentPage+"" );
						
						btnBeforeNextH.setDescription( currentPage+"/"+pages );
						btnBeforeNextF.setDescription( currentPage+"/"+pages );
						
						btnBeforeNextH.setData( currentPage );
						btnBeforeNextF.setData( currentPage );
						
						btnBeforeNextH.addStyleName( "sn-cur-page" );
						btnBeforeNextF.addStyleName( "sn-cur-page" );
						
						btnAfterPrevH.setCaption( ( currentPage - 1 )+"" );
						btnAfterPrevF.setCaption( ( currentPage - 1 )+"" );
						
						btnAfterPrevH.setData( ( currentPage - 1 ) );
						btnAfterPrevF.setData( ( currentPage - 1 ) );
						
						btnAfterPrevH.removeStyleName( "sn-cur-page" );
						btnAfterPrevF.removeStyleName( "sn-cur-page" );
						
					}else {
					
						btnAfterPrevH.setCaption( currentPage+"" );
						btnAfterPrevF.setCaption( currentPage+"" );
						
						btnAfterPrevH.setDescription( currentPage+"/"+pages );
						btnAfterPrevF.setDescription( currentPage+"/"+pages );
						
						
						btnAfterPrevH.setData( currentPage );
						btnAfterPrevF.setData( currentPage );
						
						btnAfterPrevH.addStyleName( "sn-cur-page" );
						btnAfterPrevF.addStyleName( "sn-cur-page" );
						
						btnBeforeNextH.setCaption( ( currentPage + 1 )+"" );
						btnBeforeNextF.setCaption( ( currentPage + 1 )+"" );
						
						btnBeforeNextH.setData( ( currentPage + 1 ) );
						btnBeforeNextF.setData( ( currentPage + 1 ) );
						
						btnBeforeNextH.removeStyleName( "sn-cur-page" );
						btnBeforeNextF.removeStyleName( "sn-cur-page" );
					}
			
					this.setNewPage( currentPage );
			}
			
		} 
		
	}
	
	
	
}

