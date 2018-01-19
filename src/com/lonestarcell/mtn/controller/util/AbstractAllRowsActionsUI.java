package com.lonestarcell.mtn.controller.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.design.admin.DDateFilterUIDesign;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.TextField;



public abstract class AbstractAllRowsActionsUI<M, O, T> extends DDateFilterUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;
	
	
	protected In in;
	protected M model;
	protected Grid grid;
	private boolean allowDateFilters;
	private boolean isHeader;
	protected PaginationUIController pageC;
	protected BeanItemContainer<O> container;
	protected InTxn inTxn;
	protected OutTxnMeta outTxnMeta;
	protected List<TextField> tFSearchFields = new ArrayList<>(4);
	
	private Logger log = LogManager.getLogger( AbstractAllRowsActionsUI.class.getName() );
	
	public AbstractAllRowsActionsUI( In in, boolean allowDateFilters, boolean isHeader, PaginationUIController pageC ){
		this.in = in;
		this.allowDateFilters = allowDateFilters;
		this.isHeader = isHeader;
		this.pageC = pageC;
		
		
	}
	
	protected abstract void setGrid(Grid grid );
	
	protected abstract void setBeanItemContainer();
	
	private void setInTxn( In in ){
		inTxn = ( InTxn )in.getData().getData();
	}
	
	protected abstract void setOutTxnMeta();

	@Override
	public void attachCommandListeners() {
		this.attachBtnFilter();
		this.attachBtnRefresh();
		this.attachBtnClearFilters();
		
		this.attachDFStartDate();
		this.attachDFLastDate();
		
		// Pagination 
		this.attachBtnNext();
		this.attachBtnPrev();
		
		this.attachBtnBeforeNext();
		this.attachBtnAfterPrev();
		
		// Data Export
		this.attachBtnExportOps();
		this.initDataExportUI();
		
	}
	
	
	protected  void setNewPage( int page ){
		log.debug( "All rows deselected" );
		grid.deselectAll();
	}
	
	protected void format(){
		
		if( outTxnMeta.getTotalRevenue().getValue() == null )
			outTxnMeta.getTotalRevenue().setValue( "0" );
		
		double revenue = Double.valueOf( outTxnMeta.getTotalRevenue().getValue().replaceAll(",", "") );
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		log.debug( "Formated revenue: "+nf.format( revenue ) );
		outTxnMeta.getTotalRevenue().setValue( nf.format( revenue ).replace( "$", "") );
		
		if( outTxnMeta.getTotalRecord().getValue() == null )
			outTxnMeta.getTotalRecord().setValue( "0" );
		
		long records = Long.valueOf( outTxnMeta.getTotalRecord().getValue().toString().replaceAll(",", "") );
		nf = NumberFormat.getNumberInstance( Locale.US );
		outTxnMeta.getTotalRecord().setValue( nf.format( records ));
	}
	
	
	private void attachBtnBeforeNext(){
		
		this.btnPageBeforeNext.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnBeforeNextH has been clicked" );
				pageC.beforeNext( );
				setNewPage( pageC.getNewPage() );
				
			}
			
		});
	}
	
	
	private void attachBtnAfterPrev(){
		
		this.btnPageAfterPrev.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnAfterPrevH has been clicked" );
				pageC.afterPrev( );
				setNewPage(  pageC.getNewPage()  );
				
			}
			
		});
	}
	
	
	private void attachBtnNext(){
		
		this.btnPageNext.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnNextH has been clicked" );
				pageC.next( );
				setNewPage(  pageC.getNewPage()  );
				
			}
			
		});
	}
	
	
	private void attachBtnPrev(){
		
		this.btnPagePrev.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				log.debug( "btnPrevH has been clicked" );
				pageC.prev( );
				setNewPage(  pageC.getNewPage()  );
				
			}
			
		});
	}
	
	private void attachDFStartDate(){
		this.dFStartDate.addValueChangeListener( new ValueChangeListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				dFStartDate.setComponentError(null);
				dFLastDate.setComponentError(null);
				
			}
			
		});
	}
	
	private void attachDFLastDate(){
		this.dFLastDate.addValueChangeListener( new ValueChangeListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				dFStartDate.setComponentError(null);
				dFLastDate.setComponentError(null);
				
			}
			
		});
	}
	
	private void attachBtnClearFilters(){
		this.btnClearFilters.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				clearAllFilters();
				
			}});
	}
	
	private void attachBtnFilter() {
		this.btnFilter.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				doFilterByDate( container, dFStartDate,
						dFLastDate);

			}

		});
	}
	
	
	protected abstract void attachBtnExportOps();
	
	protected abstract void initDataExportUI();
	
	private void attachBtnRefresh() {

		this.btnRefresh.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				refreshGridData();
			}

		});
	}
	
	
	protected abstract void refreshGridData();
	

	protected void init() {
		
		this.cLeftDateFilter.setVisible( this.isHeader );
		this.setBeanItemContainer();
		this.setInTxn( in );
		this.setOutTxnMeta();
		this.setContent();
		this.attachCommandListeners();
		
	}
	
	
	protected void setContent(){
		
		
		this.cDateFilters.setVisible( this.allowDateFilters );
		
		
		if( this.isHeader ) {
			
			
			// Txn meta
			
			this.lbTotalRevenue.setPropertyDataSource( outTxnMeta.getTotalRevenue() );
			this.lbTotalRecords.setPropertyDataSource( outTxnMeta.getTotalRecord() );
			
			
			
			// Paginations for header
			
			pageC.setLbTotalRecords( this.lbTotalRecords );
			pageC.getListPageBtns().put( "nextH", this.btnPageNext );
			pageC.getListPageBtns().put( "prevH", this.btnPagePrev );
			pageC.getListPageBtns().put( "afterPrevH", this.btnPageAfterPrev );
			pageC.getListPageBtns().put( "beforeNextH", this.btnPageBeforeNext );
			
			format();
		
		} else {
			
			// Paginations for footer
			pageC.getListPageBtns().put( "nextF", this.btnPageNext );
			pageC.getListPageBtns().put( "prevF", this.btnPagePrev );
			pageC.getListPageBtns().put( "afterPrevF", this.btnPageAfterPrev );
			pageC.getListPageBtns().put( "beforeNextF", this.btnPageBeforeNext );
			
		}
		
		
	}
	
	private void clearAllFilters(){
		container.removeAllContainerFilters();
		this.dFStartDate.clear();
		this.dFLastDate.clear();
		
		this.dFStartDate.setComponentError( null );
		this.dFLastDate.setComponentError( null );
		// TODO Vital part when doing search & filter.
		inTxn.setSearchMeterNo( null );
		inTxn.setSearchMoID( null );
		inTxn.setSearchMSISDN( null );
		inTxn.setSearchSID( null );
		inTxn.setSearchStatusDesc( null );
		
		inTxn.setfDate( null );
		inTxn.settDate( null );
		
		Iterator<TextField> itr = tFSearchFields.iterator();
		while( itr.hasNext() ) {
			itr.next().clear();

		}
		
		refreshGridData();
	}
	
	private void doFilterByDate(
			BeanItemContainer<O> container, DateField dFStart,
			DateField dFLast) {

		
		Date fDate = dFStart.getValue();
		Date tDate = dFLast.getValue();

		dFStart.setComponentError(null);
		dFLast.setComponentError(null);

		if (fDate == null) {
			dFStart.setComponentError(new UserError(
					"Please Select From: date"));
			return;
		}

		if (tDate == null) {
			dFLast.setComponentError(new UserError("Please Select To: date"));
			return;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(tDate);
		// Not sure about this.
		cal.add(Calendar.DAY_OF_MONTH, -1);
		tDate = cal.getTime();

		if (fDate.compareTo(tDate) > 0) {

			dFLast.setComponentError(new UserError(
					"Invalid dates! From: date should be earlier than To: date"));
			return;
		}
		
		cal.setTime(tDate);
		// Not sure about this.
		cal.add(Calendar.DAY_OF_MONTH, 1);
		tDate = cal.getTime();
		

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strSDate = sdf.format(fDate);
		log.debug( "Date: " + strSDate );

		String strTDate = sdf.format(tDate);
		
		inTxn.setfDate( strSDate );
		inTxn.settDate( strTDate );

		container.removeContainerFilters("date");

		Between fBtn = new Between("date", strSDate, strTDate);
		container.addContainerFilter(fBtn);

	}
	
	
	public void prepareGridHeader(Grid grid, String itemId, String columnName,
			boolean isSetFilter) {

		if (grid == null)
			throw new NullPointerException("Grid cannot be null");
		if (itemId == null)
			throw new NullPointerException("Item id is required.");
		if (columnName == null)
			columnName = itemId;
		
		Column col = grid.getColumn(itemId);
		col.setHeaderCaption(columnName);

		if (isSetFilter)
			addFilterField(container, grid.getHeaderRow(2), itemId);

	}
	
	
	
	protected abstract void addFilterField(BeanItemContainer<O> container,
			HeaderRow filterHeader, String itemId);
	
	
	protected BlurListener getSearchBlurListener( TextField tF, ShortcutListener listener ){
		return new BlurListenerCustom( listener, tF );
	}
	
	
	class FocusListenerCustom implements FocusListener{

		private static final long serialVersionUID = 1L;
		private ShortcutListener enterListener;
		private TextField tF;
		FocusListenerCustom( ShortcutListener enterListener, TextField tF ){
			this.enterListener = enterListener;
			this.tF = tF;
		}

		@Override
		public void focus(FocusEvent event) {
			
			tF.addShortcutListener( enterListener );
			tF.addBlurListener( getSearchBlurListener( tF, enterListener ) );
			log.debug( "Enter search shortcut listener attached." );
			
		}
		
	}
	
	
	class BlurListenerCustom implements BlurListener{

		private static final long serialVersionUID = 1L;
		private ShortcutListener enterListener;
		private TextField tF;
		BlurListenerCustom( ShortcutListener enterListener, TextField tF ){
			this.enterListener = enterListener;
			this.tF = tF;
		}

		@Override
		public void blur(BlurEvent event) {
			tF.removeShortcutListener( enterListener );
			log.debug( "Enter search shortcut listener DEttached." );
			
		}
		
	}
	
	
	protected FocusListener getSearchFocusListener( TextField tF, ShortcutListener enterListener){
		return new FocusListenerCustom( enterListener, tF );
	}
	
	
	
	protected abstract ShortcutListener getSearchShortcutListener( TextField tF, String itemId, BeanItemContainer<O> container );
	
	
	protected abstract T getTextChangeListner( BeanItemContainer<O> container,String itemId, TextField tF );
	

	protected void setModel(M m) {
		 this.model = m;
		
	}
	
	
	
	
}