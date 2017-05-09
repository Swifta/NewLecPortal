package com.lonestarcell.mtn.controller.admin;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Todayflightdata;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DDateFilterUIDesign;
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
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DTokenStateUI extends DTxnStateUIDesign implements DUserUIInitializable<DTokenUI, DTokenStateUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DTokenUI ancestor;
	private Logger log = LogManager.getLogger();
	private List<TextField> tFSearchFields = new ArrayList<>(3);
	private BeanItemContainer<OutTxn> beanItemContainer;
	
	private MTxn mTxn;
	private InTxn inTxn;
	
	
	DTokenStateUI( DTokenUI a){
		init( a );
	}

	@Override
	public void attachCommandListeners() {
		
	}

	@Override
	public void init(DManUIController duic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Today");
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
		this.vlTrxnTable.setWidth( "1150px");
		
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
	public void init(DTokenUI a) {
		mTxn = new MTxn(  getCurrentUserId(), getCurrentUserSession() );
		inTxn = new InTxn();
		setAncestorUI( a );
		setContent();
		
	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DTokenUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DTokenUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DTokenStateUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DTokenStateUI p) {
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
			
			mTxn.setTokenToday(in, beanItemContainer );

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

			grid.setColumnOrder( "swiftaId", "itronId", "meterNo", "amount", "tokenStatus", "txnType", "reqCount", "date", "actions" );

			
			grid.setFrozenColumnCount(2);
			
			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);
			
			// Header config
			HeaderCell dateFilterCellH = header.join( "swiftaId", "itronId", "meterNo", "amount", "tokenStatus", "txnType", "reqCount", "date", "actions");
			PaginationUIController pageC = new PaginationUIController();
			
			dateFilterCellH.setComponent( new AllRowsActionsUI( grid, in, true, pageC ) );
			
			header.setStyleName( "sn-date-filter-row" );
			dateFilterCellH.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			// Footer config
			FooterCell dateFilterCellF = footer.join( "swiftaId", "itronId", "meterNo", "amount", "tokenStatus", "txnType", "reqCount", "date", "actions");
		
			dateFilterCellF.setComponent( new AllRowsActionsUI( grid, in, false, pageC ) );
			
			//Init pagination controller after both header and footer have been set.
			pageC.init(null);

			
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
			grid.removeColumn( "token" );
			grid.removeColumn( "mmoId" );
			grid.removeColumn( "statusDesc" );
			grid.removeColumn( "reqCurrency" );
			grid.removeColumn( "msisdn" );
			grid.removeColumn( "rate" );
			

			// Add search field
			
			prepareGridHeader(grid, "itronId", "ITRON ID", true );
			prepareGridHeader(grid, "swiftaId", "SWIFTA ID", true );
			prepareGridHeader(grid, "meterNo", "Meter No.", true );
			prepareGridHeader(grid, "txnType", "ReQ. Type", true );
			prepareGridHeader(grid, "tokenStatus", "Token Status", true );
			prepareGridHeader(grid, "date", "Timestamp", false );
			prepareGridHeader(grid, "reqCount", "Count", false );
			prepareGridHeader(grid, "actions", "...", false );
			
			
			// Set column widths
			
			grid.getColumn( "swiftaId" ).setWidth( 135 );
			grid.getColumn( "itronId" ).setWidth( 135 );
			grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "txnType" ).setWidth( 125 );
			grid.getColumn( "tokenStatus" ).setWidth( 125 );
			grid.getColumn( "reqCount" ).setWidth( 80 );
			grid.getColumn( "amount" ).setWidth( 90 );
			//grid.getColumn( "date" ).setWidth( 170 );
			
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

		BeanItemContainer<Todayflightdata> container = (BeanItemContainer<Todayflightdata>) gpc
				.getWrappedContainer();
		
		Column col = grid.getColumn(itemId);
		col.setHeaderCaption(columnName);

		if (isSetFilter)
			addFilterField(container, grid.getHeaderRow(2), itemId);

	}
	
	
	private void addFilterField(BeanItemContainer<Todayflightdata> container,
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
			final BeanItemContainer<Todayflightdata> container,
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
			init( null );
		}
		
		@Override
		public void init(DManUIController duic) {
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
		private Item record;
		private Button btnSendTokenReq;
		
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
		
		
		private void attachBtnSendTokenReq() {
			
			this.btnSendTokenReq.addClickListener( new ClickListener(){
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					
					MMo m = new MMo( getCurrentUserId(), getCurrentUserSession() );
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
					Out out = m.tokenRetry( in );
					
					if( out.getStatusCode() == 1 ) {
						refresh();
					} else {
						Notification.show("Oops... error sending token req. Please try again later.",
								Notification.Type.ERROR_MESSAGE);
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

		@Override
		public void init(DManUIController duic) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class AllRowsActionsUI extends DDateFilterUIDesign implements DUIControllable {
	
		private static final long serialVersionUID = 1L;
		
		private Grid grid;
		private In in;
		private OutTxnMeta outTxnMeta;
		private boolean allowDateFilters;
		private PaginationUIController pageC;
		
		private AllRowsActionsUI( Grid grid, In in, boolean allowDateFilters, PaginationUIController pageC ){
			this.grid = grid;
			this.in = in;
			
			this.allowDateFilters = allowDateFilters;
			this.pageC = pageC;
			init( null );
			
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
			mTxn.setTokenToday(in, beanItemContainer );
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

		@Override
		public void init(DManUIController duic) {
			
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
	
	public class PaginationUIController implements DUIControllable, Serializable{
		
		private static final long serialVersionUID = 1L;

		private Map< String, Button > mapPageBtns;
			
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
		
		private In in;
		private OutTxnMeta outTxnMeta;
		
		PaginationUIController(){
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
			
			currentPage++;
			navigation( );
			
		}
		
		private void prev( ){
			
			currentPage--;
			navigation( );
			
		}
		
		private void beforeNext( ){
			
			   int beforeNextPage = Integer.valueOf( btnBeforeNextH.getData().toString() );
			   
			   log.debug( "Before Next Page: "+beforeNextPage+" Current Page: "+currentPage );
			
			   if( currentPage < beforeNextPage ){
				   
					currentPage++;
					btnBeforeNextH.addStyleName( "sn-cur-page" );
					btnBeforeNextF.addStyleName( "sn-cur-page" );
					
					btnBeforeNextH.setDescription( currentPage+"/"+pages );
					btnBeforeNextF.setDescription( currentPage+"/"+pages );
					
					btnAfterPrevH.removeStyleName( "sn-cur-page" );
					btnAfterPrevF.removeStyleName( "sn-cur-page" );
					
					log.debug( "Current page changed by Before next button" );
					
					this.setNewPage( currentPage );
					
					
				} else {
					log.debug( "Current page NOT changed by Before next button" );
				}
				
				
		}		
		
		private void afterPrev( ){
		   
		   int afterPrevPage = Integer.valueOf( btnAfterPrevH.getData().toString() );
		   log.debug( "After prev Page: "+afterPrevPage+" Current Page: "+currentPage );
		   
		   
		   if( currentPage > afterPrevPage ){
			   
				currentPage--;
				btnAfterPrevH.addStyleName( "sn-cur-page" );
				btnAfterPrevF.addStyleName( "sn-cur-page" );
				
				btnAfterPrevH.setDescription( currentPage+"/"+pages );
				btnAfterPrevF.setDescription( currentPage+"/"+pages );
				
				
				btnBeforeNextH.removeStyleName( "sn-cur-page" );
				btnBeforeNextF.removeStyleName( "sn-cur-page" );
				
				log.debug( "Current page changed by After prev button" );
				
				this.setNewPage( currentPage );
				
				
			} else {
				log.debug( "Current page NOT changed by After prev button" );
			}
			
			
		}		
		
		

		@Override
		public void init(DManUIController duic) {
			this.attachCommandListeners();
			
		}
		
		
		private int getTotalPages( Long total ){
			
			int pages = 0;
			Float pageLength = 15F;
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
			
			mTxn.setTokenToday(in, beanItemContainer );
			
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
				
				this.setNewPage( currentPage );
				
			} 
			
		}
		
		
		
	}
	

	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}

	
	


}