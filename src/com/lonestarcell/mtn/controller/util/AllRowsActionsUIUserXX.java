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

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.design.admin.DDateFilterUIDesign;
import com.lonestarcell.mtn.model.admin.MUser;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;



public class AllRowsActionsUIUserXX extends DDateFilterUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private Grid grid;
	private In in;
	private boolean allowDateFilters;
	private PaginationUIController pageC;
	private BeanItemContainer<OutUser> container;
	private InTxn inTxn;
	private OutTxnMeta outTxnMeta;
	private MUser mUser;
	private List<TextField> tFSearchFields = new ArrayList<>(4);
	
	private Logger log = LogManager.getLogger( AllRowsActionsUIUserXX.class.getName() );
	
	public AllRowsActionsUIUserXX( MUser mUser, Grid grid, In in, boolean allowDateFilters, PaginationUIController pageC ){
		this.grid = grid;
		this.in = in;
		this.mUser = mUser;
		this.allowDateFilters = allowDateFilters;
		this.pageC = pageC;
		init( );
		
	}
	
	@SuppressWarnings("unchecked")
	private void setBeanItemContainer(){
		container = ((BeanItemContainer<OutUser>) ((GeneratedPropertyContainer)  grid.getContainerDataSource())
				.getWrappedContainer());
	}
	
	private void setInTxn( In in ){
		inTxn = ( InTxn )in.getData().getData();
	}
	
	private void setOutTxnMeta(){
		outTxnMeta = new OutTxnMeta();
		outTxnMeta.setTotalRevenue( new ObjectProperty<String>( "0", String.class ) );
		outTxnMeta.setTotalRecord( new ObjectProperty<String>( "0", String.class ) );
	}

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
		
	}
	
	
	private void setNewPage( int page ){
		container.removeAllItems();
		inTxn.setPage( page );
		mUser.setUsers(in, container );
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
	
	
	private void attachBtnBeforeNext(){
		
		this.btnPageBeforeNext.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				pageC.beforeNext( );
				if( pageC.getNewPage() == inTxn.getPage() )
					return;
				setNewPage( pageC.getNewPage() );
				// pageC.setCurrentPage( pageC.getNewPage() );
				
			}
			
		});
	}
	
	
	private void attachBtnAfterPrev(){
		
		this.btnPageAfterPrev.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				
				
				pageC.afterPrev( );
				if( pageC.getNewPage() == inTxn.getPage() )
					return;
				setNewPage(  pageC.getNewPage()  );
				// pageC.setCurrentPage( pageC.getNewPage() );
				
			}
			
		});
	}
	
	
	private void attachBtnNext(){
		
		this.btnPageNext.addClickListener( new ClickListener(){
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				
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
	
	private void attachBtnRefresh() {

		this.btnRefresh.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				refreshGridData();
			}

		});
	}
	
	
	private void refreshGridData(){
		
		
		container.removeAllItems();
		
		//TODO validate response
		Out out = mUser.setUsers(in, container );
		mUser.setTxnMeta(in, outTxnMeta );
		
		if( out.getStatusCode() != 1 ) {
			Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
			return;
		}
		
		format();
		
		
	}
	

	private void init() {
		if( !this.allowDateFilters )
			this.cLeftDateFilter.setVisible(false);
		
		this.setBeanItemContainer();
		this.setInTxn( in );
		this.setOutTxnMeta();
		this.setContent();
		this.attachCommandListeners();
		
	}
	
	
	private void setContent(){
		
		
		this.cDateFilters.setVisible( false );
		if( this.allowDateFilters ) {
			
			// Txn meta
			
			this.lbTotalRevenue.setPropertyDataSource( outTxnMeta.getTotalRevenue() );
			this.lbTotalRecords.setPropertyDataSource( outTxnMeta.getTotalRecord() );
			
			Out out = mUser.setTxnMeta( in, outTxnMeta );
			if( out.getStatusCode() != 1 )
				Notification.show( out.getMsg(), Notification.Type.ERROR_MESSAGE );
			
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
		
		inTxn.setSearchMeterNo( null );
		inTxn.setSearchMoID( null );
		inTxn.setSearchMSISDN( null );
		inTxn.setSearchSID( null );
		inTxn.setSearchStatusDesc( null );
		
		Iterator<TextField> itr = tFSearchFields.iterator();
		while( itr.hasNext() ) {
			itr.next().clear();

		}
		
		refreshGridData();
	}
	
	private void doFilterByDate(
			BeanItemContainer<OutUser> container, DateField dFStart,
			DateField dFLast) {

		Date fDate = dFStart.getValue();
		Date tDate = dFLast.getValue();

		dFStart.setComponentError(null);
		dFLast.setComponentError(null);

		if (fDate == null) {
			dFStart.setComponentError(new UserError(
					"Please Select \"From\" date"));
			return;
		}

		if (tDate == null) {
			dFLast.setComponentError(new UserError("Please Select \"To\" date"));
			return;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(tDate);
		//cal.add(Calendar.DAY_OF_MONTH, 1);

		tDate = cal.getTime();

		if (fDate.compareTo(tDate) > 0) {

			dFLast.setComponentError(new UserError(
					"Invalid dates! \"From\" date should be earlier than \"To\" date"));
			return;
		}

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strSDate = sdf.format(fDate);
		log.debug( "Date: " + strSDate );

		String strTDate = sdf.format(tDate);

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
	
	
	
	private void addFilterField(BeanItemContainer<OutUser> container,
			HeaderRow filterHeader, String itemId) {

		TextField tF = new TextField();
		tF.setStyleName("sn-tf-filter");
		tF.setDescription("Search");
		tF.setInputPrompt("Filter/Search");
		tF.setWidth("100%");
		HeaderCell cFilter = filterHeader.getCell(itemId);
		cFilter.setComponent(tF);
		
		TextChangeListenerCustom tChangeListener = getTextChangeListner( container, itemId, tF );
		tF.addTextChangeListener( tChangeListener );
		tFSearchFields.add( tF );
		
		ShortcutListener enterListener = getSearchShortcutListener( tF, itemId, container );
		tF.addFocusListener( getSearchFocusListener( tF, enterListener ) );
		tF.addBlurListener( getSearchBlurListener( tF, enterListener ) );
		
		tF.setDescription( "Type to filter / Press Enter to search" );
		
		
		

	}
	
	
	private BlurListener getSearchBlurListener( TextField tF, ShortcutListener listener ){
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
	
	
	private FocusListener getSearchFocusListener( TextField tF, ShortcutListener enterListener){
		return new FocusListenerCustom( enterListener, tF );
	}
	
	
	
	private ShortcutListener getSearchShortcutListener( TextField tF, String itemId, BeanItemContainer<OutUser> container ){
		return new ShortcutListener( "", KeyCode.ENTER, null){
			private static final long serialVersionUID = 1L;
			
			

			@Override
			public void handleAction(Object sender, Object target) {
				log.debug( "Enter search shortcut clicked." );
				String val = tF.getValue();
				if( val == null )
					return;
				val = val.trim();
				if( val.isEmpty() )
					return;
				
				
				if( (inTxn.getSearchSID() == null )
						&& ( inTxn.getSearchMoID() == null )
						&& ( inTxn.getSearchMeterNo() == null )
						&& ( inTxn.getSearchMSISDN() == null )
						&& ( inTxn.getSearchStatusDesc() == null ) ){
					
					log.debug( "No search required." );
					
					return;
					
				} 
				
				
				container.removeAllItems();
				
				log.debug( "Proceeding with search." );
				
				In in = new In();
				
				BData<InTxn> inBData = new BData<>();
				inTxn.setPage( 1 );
				inBData.setData( inTxn );
				in.setData( inBData );
				
				
				
				Out out = mUser.setUsers( in, container );
				
				mUser.setTxnMeta( in, outTxnMeta );
				
				if( out.getStatusCode() != 1 ){
					Notification.show( out.getMsg(), Notification.Type.WARNING_MESSAGE );
				}
				
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
		
		};
	}
	
	
	
	private TextChangeListenerCustom getTextChangeListner(
			BeanItemContainer<OutUser> container,
			String itemId, TextField tF ) {
		return new TextChangeListenerCustom( container, itemId, tF );
	}
	
	
	public class TextChangeListenerCustom implements TextChangeListener{

		private static final long serialVersionUID = 1L;
		private BeanItemContainer<OutUser> container;
		private String itemId;
		
		
		TextChangeListenerCustom( BeanItemContainer<OutUser> container,
			String itemId, TextField tF ){
			this.container = container;
			this.itemId = itemId;
		}

		@Override
		public void textChange(TextChangeEvent event) {

			log.debug( "Item id: "+itemId );
			String val = event.getText();
			if( val != null && val.trim().isEmpty() )
				val = null;
			
			if( itemId.equals( "swiftaId" ) ){
				inTxn.setSearchSID( val );
			} else if( itemId.equals( "mmoId" ) ) {
				inTxn.setSearchMoID( val );
			}else if( itemId.equals( "meterNo" ) ) {
				inTxn.setSearchMeterNo( val );
			}else if( itemId.equals( "msisdn" ) ) {
				inTxn.setSearchMSISDN( val );
			}else if( itemId.equals( "statusDesc" ) ) {
				inTxn.setSearchStatusDesc( val );
			}
			
			container.removeContainerFilters(itemId);
			if (val != null && !val.isEmpty()) {
				container.addContainerFilter(new SimpleStringFilter(itemId,
						val, true, false));
				
				
			}

		}
		
	}
	
}