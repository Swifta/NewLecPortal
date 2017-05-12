package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.PaymentPaginationUIController;
import com.lonestarcell.mtn.design.admin.DDateFilterUIDesign;
import com.lonestarcell.mtn.design.admin.DPaginationUIDesign;
import com.lonestarcell.mtn.design.admin.DTxnStateUIDesign;
import com.lonestarcell.mtn.model.admin.MMo;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.FooterCell;
import com.vaadin.ui.Grid.FooterRow;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DPaymentArchiveUI extends DTxnStateUIDesign implements DUserUIInitializable<DPaymentUI, DPaymentArchiveUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DPaymentUI ancestor;
	private Logger log = LogManager.getLogger();
	private List<TextField> tFSearchFields = new ArrayList<>(3);
	private BeanItemContainer<OutTxn> beanItemContainer;
	
	private MTxn mTxn;
	private InTxn inTxn;
	
	
	DPaymentArchiveUI( DPaymentUI a){
		init( a );
	}

	@Override
	public void attachCommandListeners() {
		
	}
	
	
	

	@Override
	public void setHeader() {
		this.lbDataTitle.setValue( "Payment & SMS Info" );
		
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		setBeanItemContainer( new BeanItemContainer<OutTxn>(OutTxn.class) );
		
		swap(this);
		attachCommandListeners();
		this.vlTrxnTable.addComponent( loadGridData( beanItemContainer ) );
		this.vlTrxnTable.setHeightUndefined();
		this.vlTrxnTable.setWidth( "1255px");
		
	}
	
	

	public BeanItemContainer<OutTxn> getBeanItemContainer() {
		return beanItemContainer;
	}

	public void setBeanItemContainer(BeanItemContainer<OutTxn> beanItemContainer) {
		this.beanItemContainer = beanItemContainer;
	}

	@Override
	public void swap(Component cuid) {
		//ancestor.setHeight("100%");
		//cuid.setHeight("100%");

		
		//ancestor.addStyleName("sn-p");
		//cuid.addStyleName("sn-c");
		
		cuid.setHeight("100%");
		ancestor.getAncestorUI().getcMainContent().setHeight( "100%" );
		//ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
		ancestor.setHeight( "100%" );
		
		
		log.debug( "Users height: "+cuid.getHeight() );
		
		ancestor.swap( cuid );
		
	}

	@Override
	public void init( DPaymentUI a ) {
		mTxn = new MTxn( getCurrentUserId(), getCurrentUserSession()  );
		inTxn = new InTxn();
		setAncestorUI( a );
		setContent();
		
	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DPaymentUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DPaymentUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DPaymentArchiveUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DPaymentArchiveUI p) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Grid loadGridData( BeanItemContainer<OutTxn> beanItemContainer ) {
		try {

			
			
			
			In in = new In();
			
			BData<InTxn> inBData = new BData<>();
			
			DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
			Calendar cal = Calendar.getInstance();
			
			String tDate = sdf.format( cal.getTime() );
			log.debug( "To: "+tDate );
			
			inTxn.settDate(  tDate );
			inTxn.setPage( 1 );
			
			
			cal.add(Calendar.DAY_OF_MONTH, -200 );
			String fDate =  sdf.format( cal.getTime() );
			log.debug( "From: "+fDate );
			
			inTxn.setfDate( fDate );
			
			
			inBData.setData( inTxn );
			in.setData( inBData );
			
			//TODO validate response
			
			mTxn.setPaymentToday(in, beanItemContainer );

			Grid grid = new Grid();
			
			

			// Add actions
			
			GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(
					beanItemContainer);

			gpc.addGeneratedProperty("actions",
					new PropertyValueGenerator<Component>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Component getValue(Item item, Object itemId,
								Object propertyId) {
							PopupView v = new PopupView("...", new RowActionsUI( item ) );
							v.setWidth( "100%" );
							v.setHeight( "100%" );
							return v;
						}

						@Override
						public Class<Component> getType() {
							return Component.class;
						}

					}); 
			
			
			grid.setContainerDataSource(gpc);
			grid.getColumn("actions").setRenderer(new ComponentRenderer());
			
			//grid.setColumnOrder( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "rate", "statusDesc", "date", "actions" );

			grid.setColumnOrder( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "reqCurrency", "token", "statusDesc", "date" );

			
			
			
			grid.setFrozenColumnCount(2);
			
			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);
			
			// Header config
			//HeaderCell dateFilterCellH = header.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "rate", "statusDesc", "actions", "date" );
			HeaderCell dateFilterCellH = header.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "reqCurrency", "token", "statusDesc", "date", "actions" ); 
			
			PaymentPaginationUIController pageC = new PaymentPaginationUIController( beanItemContainer, mTxn, inTxn );
			
			dateFilterCellH.setComponent( new AllRowsActionsUI( grid, in, true, pageC ) );
			
			header.setStyleName( "sn-date-filter-row" );
			dateFilterCellH.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			// Footer config
			//FooterCell dateFilterCellF = footer.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "rate", "statusDesc", "actions", "date" );
			
			FooterCell dateFilterCellF = footer.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "reqCurrency", "token", "statusDesc", "date", "actions" ); 

			
			dateFilterCellF.setComponent( new AllRowsActionsUI( grid, in, false, pageC ) );
			
			//Init pagination controller after both header and footer have been set.
			pageC.init();

			
			footer.setStyleName( "sn-date-filter-row" );
			dateFilterCellF.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			PopupView v = new PopupView("...", new MultiRowActionsUI( grid ) );
				
			HeaderCell cellBulkActions = headerTextFilter.getCell( "actions" );
			v.setWidth( "100%" );
			v.setHeight( "100%" );
			
			cellBulkActions.setComponent( v );
			
			grid.getColumn( "actions" ).setWidth( 50 );
			HeaderRow headerColumnNames = grid.getHeaderRow( 1 );
			
			HeaderCell cellActions = headerColumnNames.getCell( "actions" );
			
			cellActions.setStyleName( "sn-cell-actions" );
			cellBulkActions.setStyleName( "sn-cell-actions" );
			
			// Hide unnecessary bean fields
			
			grid.removeColumn( "sessionVar" );
			grid.removeColumn( "profileId" );
			grid.removeColumn( "payStatus" );
			grid.removeColumn( "rate" );
			grid.removeColumn( "reqCount" );
			grid.removeColumn( "itronId" );
			grid.removeColumn( "tokenStatus" );
			grid.removeColumn( "txnType" );
			

			// Add search field
			
			prepareGridHeader(grid, "mmoId", "MoID", true );
			prepareGridHeader(grid, "msisdn", "MSISDN", true );
			prepareGridHeader(grid, "swiftaId", "SWIFTA ID", true );
			prepareGridHeader(grid, "statusDesc", "Status", true );
			prepareGridHeader(grid, "reqCurrency", "ReQ.Cur.", true );
			prepareGridHeader(grid, "meterNo", "Meter No.", true );
			prepareGridHeader(grid, "token", "Token", true );
			prepareGridHeader(grid, "date", "Timestamp", false );
			prepareGridHeader(grid, "actions", "...", false );
			
			
			// Set column widths
			
			/* grid.getColumn( "msisdn" ).setWidth( 135 ).setResizable(false);
			grid.getColumn( "swiftaId" ).setWidth( 125 );
			grid.getColumn( "mmoId" ).setWidth( 125 );
			grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "amount" ).setWidth( 100 );
			grid.getColumn( "statusDesc" ).setWidth( 125 );
			
			//grid.getColumn( "date" ).setWidth( 178 ).setResizable(false);
			grid.getColumn( "rate" ).setWidth( 70 ); */
			
			
			grid.getColumn( "msisdn" ).setWidth( 131 ).setResizable(false);
			grid.getColumn( "swiftaId" ).setWidth( 105 );
			grid.getColumn( "mmoId" ).setWidth( 105 );
			grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "amount" ).setWidth( 90 );
			grid.getColumn( "reqCurrency" ).setWidth( 90 );
			grid.getColumn( "token" ).setWidth( 195 );
			grid.getColumn( "date" ).setWidth( 170 );
			grid.getColumn( "statusDesc" ).setWidth( 107 );
			
			
			
			grid.addStyleName( "sn-small-grid" );

			grid.setSelectionMode(SelectionMode.MULTI);
			grid.setHeight( "500px" );
			grid.setWidth( "100%" );
			
			
			Notification.show(
					"Data loaded successfully.",
					Notification.Type.HUMANIZED_MESSAGE );
			
			return grid;

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}
		
		
		
		return new Grid();
	}
	
	@SuppressWarnings("unchecked")
	private void prepareGridHeader(Grid grid, String itemId, String columnName,
			boolean isSetFilter) {

		if (grid == null)
			throw new NullPointerException("Grid cannot be null");
		if (itemId == null)
			throw new NullPointerException("Item id is required.");
		if (columnName == null)
			columnName = itemId;

		GeneratedPropertyContainer gpc = (GeneratedPropertyContainer) grid
				.getContainerDataSource();

		BeanItemContainer<OutTxn> container = (BeanItemContainer<OutTxn>) gpc
				.getWrappedContainer();
		
		Column col = grid.getColumn(itemId);
		col.setHeaderCaption(columnName);

		if (isSetFilter)
			addFilterField(container, grid.getHeaderRow(2), itemId);

	}
	
	
	public class Pagination extends DPaginationUIDesign {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	private void addFilterField(BeanItemContainer<OutTxn> container,
			HeaderRow filterHeader, String itemId) {

		TextField tF = new TextField();
		tF.setStyleName("sn-tf-filter");
		tF.setDescription("Search");
		tF.setInputPrompt("Search...");
		tF.setWidth("100%");
		HeaderCell cFilter = filterHeader.getCell(itemId);
		cFilter.setComponent(tF);
		tF.addTextChangeListener(getTextChangeListner(container, itemId));
		
		tFSearchFields.add( tF );
		

	}
	
	
	private TextChangeListener getTextChangeListner(
			final BeanItemContainer<OutTxn> container,
			final String itemId) {
		return new TextChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {

				String val = event.getText();
				container.removeContainerFilters(itemId);
				if (val != null && !val.isEmpty()) {
					container.addContainerFilter(new SimpleStringFilter(itemId,
							val, true, false));
				}

			}

		};
	}
	
	private class MultiRowActionsUI extends VerticalLayout implements DUIControllable {
		
		private static final long serialVersionUID = 1L;
		private Button btnExport;
		private Button btnRefresh;
		private Grid grid;

		private MultiRowActionsUI( Grid grid ){
			
			this.grid = grid;
			init();
		}
		
		public void init() {
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
			
			this.addComponent( btnExport );
			this.addComponent( btnRefresh );
			
			
			
		}

		@Override
		public void attachCommandListeners() {
			
			this.attachBtnRefresh();
			
		}
		
		private void attachBtnRefresh(){
			this.btnRefresh.addClickListener( new ClickListener(){

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
					
					Out out = mTxn.refreshMultiTxnRecord( records );
					
					
					if( out.getStatusCode() == 1 )
						Notification.show(
								"All selected records have been refreshed.",
								Notification.Type.HUMANIZED_MESSAGE );
					else if( out.getStatusCode() == 2 )
						Notification.show(
								"Refresh operation failed on some records.",
								Notification.Type.WARNING_MESSAGE );
					else
						Notification.show(
								"Refresh operation failed.",
								Notification.Type.ERROR_MESSAGE );
					
				}
				
			});
		}

		
	}
	
	
	public class RowActionsUI extends VerticalLayout implements DUIControllable{
		
		private static final long serialVersionUID = 1L;
		private Button btnDetails;
		private Button btnRefresh;
		private Button btnSendSMS;
		private Item record;
		
		public RowActionsUI( Item record ){
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
			btnSendSMS = new Button( );
			
			btnDetails.setDescription( "More details" );
			btnRefresh.setDescription( "Refresh record" );
			btnSendSMS.setDescription( "Send sms" );
			
			btnDetails.addStyleName( "borderless icon-align-top" );
			btnRefresh.addStyleName( "borderless icon-align-top" );
			btnSendSMS.addStyleName( "borderless icon-align-top" );
			
			btnDetails.setIcon( FontAwesome.ALIGN_RIGHT );
			btnRefresh.setIcon( FontAwesome.REFRESH );
			btnSendSMS.setIcon( FontAwesome.ENVELOPE_O );
			
			this.addComponent( btnDetails );
			this.addComponent( btnRefresh );
			this.addComponent( btnSendSMS );
			
			
			
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
			this.attachBtnSendSMS();
			
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
		
		
		private void refresh(){
			
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
			
		}
		
		
		private void attachBtnSendSMS() {
			this.btnSendSMS.addClickListener( new ClickListener(){

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					
					MMo m = new MMo(  getCurrentUserId(), getCurrentUserSession()  );
					double amount = 4230;
					
					In in = new In();
					BData<InMo> inBData = new BData<>();
					InMo inMo = new InMo();
					inMo.setMmoId( "19876379" );
					inMo.setAcctRef( "90099887766" );
					inMo.setMsisdn( "231888210000" );
					inMo.setAmount( ( amount*100 )+"" );
					inMo.setCurrency( "LRD" );
					
					inBData.setData( inMo );
					in.setData( inBData );
					Out out = m.sendSMS( in );
					
					if( out.getStatusCode() == 1 ) {
						refresh();
					} else {
						Notification.show("Oops... error sending SMS. Please try again later.",
								Notification.Type.ERROR_MESSAGE);
					}
				}
				
			});
		}

		
		
	}
	
	private class AllRowsActionsUI extends DDateFilterUIDesign implements DUIControllable {
	
		private static final long serialVersionUID = 1L;
		
		private Grid grid;
		private In in;
		private OutTxnMeta outTxnMeta;
		private boolean allowDateFilters;
		private PaymentPaginationUIController pageC;
		
		private AllRowsActionsUI( Grid grid, In in, boolean allowDateFilters, PaymentPaginationUIController pageC ){
			this.grid = grid;
			this.in = in;
			
			this.allowDateFilters = allowDateFilters;
			this.pageC = pageC;
			init( );
			
		}

		@Override
		public void attachCommandListeners() {
			this.attachBtnFilter();
			this.attachBtnRefresh();
			this.attachBtnClearFilters();
			
			this.attachDFStartDate();
			this.attachDFLastDate();
			
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

				@SuppressWarnings("unchecked")
				@Override
				public void buttonClick(ClickEvent event) {
					doFilterByDate(
							((BeanItemContainer<OutTxn>) ((GeneratedPropertyContainer)  grid.getContainerDataSource())
									.getWrappedContainer()), dFStartDate,
							dFLastDate);

				}

			});
		}
		
		private void attachBtnRefresh() {

			this.btnRefresh.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					clearAllFilters();
					refreshGridData();
					

				}

			});
		}
		
		
		private void refreshGridData(){
			
			
			beanItemContainer.removeAllItems();
			
			//TODO validate response
			mTxn.setPaymentToday(in, beanItemContainer );
			mTxn.setTxnMeta(in, outTxnMeta );
			
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


		private void init() {
			
			if( !this.allowDateFilters )
				this.cLeftDateFilter.setVisible(false);
			this.setContent();
			this.attachCommandListeners();
			
		}
		
		private void setContent(){
			
			
			
			if( this.allowDateFilters ) {
				
				// Txn meta
				
				outTxnMeta = new OutTxnMeta();
				ObjectProperty<String> ds = new ObjectProperty<>( "0.00", String.class );
				outTxnMeta.setTotalRevenue( ds );
				this.lbTotalRevenue.setPropertyDataSource( ds );
			
				Property<String > dsTotalRecords = new ObjectProperty<>( "0", String.class );
				outTxnMeta.setTotalRecord( dsTotalRecords );
				this.lbTotalRecords.setPropertyDataSource( dsTotalRecords );
				
				mTxn.setTxnMeta( in, outTxnMeta );
				
				// Paginations for header
				
				pageC.setLbTotalRecords( this.lbTotalRecords );
				pageC.getListPageBtns().put( "nextH", this.btnPageNext );
				pageC.getListPageBtns().put( "prevH", this.btnPagePrev );
				pageC.getListPageBtns().put( "afterPrevH", this.btnPageAfterPrev );
				pageC.getListPageBtns().put( "beforeNextH", this.btnPageBeforeNext );
				
				pageC.setOutTxnMeta( outTxnMeta );
				pageC.setIn( in );
				
				format();
			
			} else {
				
				// Paginations for footer
				pageC.getListPageBtns().put( "nextF", this.btnPageNext );
				pageC.getListPageBtns().put( "prevF", this.btnPagePrev );
				pageC.getListPageBtns().put( "afterPrevF", this.btnPageAfterPrev );
				pageC.getListPageBtns().put( "beforeNextF", this.btnPageBeforeNext );
				
			}
			
			
		}
		
		@SuppressWarnings("unchecked")
		private void clearAllFilters(){
			((BeanItemContainer<OutTxn>) ((GeneratedPropertyContainer) grid.getContainerDataSource())
					.getWrappedContainer()).removeAllContainerFilters();
			this.dFStartDate.clear();
			this.dFLastDate.clear();
			
			this.dFStartDate.setComponentError( null );
			this.dFLastDate.setComponentError( null );
			
			Iterator<TextField> itr = tFSearchFields.iterator();
			while( itr.hasNext() ) {
				itr.next().clear();

			}
		}
		
		private void doFilterByDate(
				BeanItemContainer<OutTxn> container, DateField dFStart,
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
		
	}
	

	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}
	
	


}
