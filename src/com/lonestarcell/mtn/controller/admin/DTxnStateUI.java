package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUISub;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUITxn;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUISub;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUITxn;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RowActionsUISub;
import com.lonestarcell.mtn.controller.util.RowActionsUITxn;
import com.lonestarcell.mtn.design.admin.DTxnStateUIDesign;
import com.lonestarcell.mtn.model.admin.MSub;
import com.lonestarcell.mtn.model.admin.MTxn;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.FooterCell;
import com.vaadin.ui.Grid.FooterRow;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.UI;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DTxnStateUI extends DTxnStateUIDesign implements DUserUIInitializable<DTxnUI,DTxnStateUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DTxnUI ancestor;
	private Logger log = LogManager.getLogger( DTxnStateUI.class.getName() );
	
	
	private MSub mSub;
	protected InTxn inTxn;
	
	private ApplicationContext springAppContext;
	
	
	DTxnStateUI( DTxnUI a){
		
		this( a.getSpringAppContext());
		this.setInDate(inTxn, 1 );
		this.setSpringAppContext( a.getSpringAppContext() );
		
		init( a );
	}
	
	DTxnStateUI( ApplicationContext cxt ){
		inTxn = new InTxn();
		mSub = new MSub(  getCurrentUserId(), getCurrentUserSession(), getCurrentTimeCorrection(), cxt );

	}
	
	

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}

	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}

	@Override
	public void attachCommandListeners() {
		
	}


	@Override
	public void setHeader() {
		this.lbDataTitle.setValue( " Transaction Records Today" );
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		
		swap(this);
		attachCommandListeners();
		this.vlTrxnTable.addComponent( loadGridData( new BeanItemContainer<>( OutSubscriber.class ) ) );
		this.vlTrxnTable.setHeightUndefined();
		this.vlTrxnTable.setWidth( "1150px");
		
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
	public void init(DTxnUI a) {
		
		setAncestorUI( a );
		setContent();
		
	}
	
	

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DTxnUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DTxnUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DTxnStateUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DTxnStateUI p) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	protected Grid loadGridData( BeanItemContainer< OutSubscriber > beanItemContainer ) {
		try {

			
			log.debug( "Locale: "+UI.getCurrent().getLocale()  );
			
			In in = new In();
			
			BData<InTxn> inBData = new BData<>();
			
			
			inTxn.setPage( 1 );
			inBData.setData( inTxn );
			in.setData( inBData );
			
			//TODO validate response
			
			Out out = mSub.searchTxnToday(in, beanItemContainer );
			if( out.getStatusCode() != 1 ) {
				Notification.show( out.getMsg(), Notification.Type.WARNING_MESSAGE );
			} else {
				Notification.show(
						out.getMsg(),
						Notification.Type.HUMANIZED_MESSAGE );
			}

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
							PopupView v = new PopupView("...", new RowActionsUISub( mSub, item ) );
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
			
			//transactionNumber, type, amount, status, payer, payee, timestamp;
			//// "transactionNumber", "type", "amount", "status", "payer", "payee", "timestamp"
			//grid.setColumnOrder( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "rate", "statusDesc", "date", "actions" );
			
			grid.setColumnOrder( "transactionNumber", "type", "amount", "status", "payer", "payee", "timestamp", "actions" );

			grid.setFrozenColumnCount(2);
			
			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);
			
			
			// Header config
			// HeaderCell dateFilterCellH = header.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "rate", "statusDesc", "actions", "date" );
			HeaderCell dateFilterCellH = header.join( "transactionNumber", "type", "amount", "status", "payer", "payee", "timestamp", "actions" );
			PaginationUIController pageC = new PaginationUIController( );
			AllRowsActionsUISub allRowsActionsUIH = getHeaderController(  mSub, grid, in, pageC  );
			dateFilterCellH.setComponent( allRowsActionsUIH );
			
			header.setStyleName( "sn-date-filter-row" );
			dateFilterCellH.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			// Footer config
			// FooterCell dateFilterCellF = footer.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "rate", "statusDesc", "actions", "date" );
		
			FooterCell dateFilterCellF = footer.join( "transactionNumber", "type", "amount", "status", "payer", "payee", "timestamp", "actions" );
			dateFilterCellF.setComponent( getFooterController(  mSub, grid, in, pageC  ) );
			
			//Init pagination controller after both header and footer have been set.
			pageC.init( );

			
			footer.setStyleName( "sn-date-filter-row" );
			dateFilterCellF.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			PopupView v = new PopupView( "HHHH", null  );
			v.setContent( new MultiRowActionsUISub( mSub, in, grid, v ) );
			v.setHideOnMouseOut( true );
			v.setVisible( true );
			
				
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
			
			// grid.removeColumn( "sessionVar" );
			// grid.removeColumn( "profileId" );
			// grid.removeColumn( "payStatus" );
			// grid.removeColumn( "tokenStatus" );
			// grid.removeColumn( "itronId" );
			// grid.removeColumn( "reqCurrency" );
			// grid.removeColumn( "statusDesc" );
			// grid.removeColumn( "token" );
			// grid.removeColumn( "reqCount" );
			// grid.removeColumn( "txnType" );
			

			// Add search field
			
			// "transactionNumber", "type", "amount", "status", "payer", "payee", "timestamp", "actions"
			
			allRowsActionsUIH.prepareGridHeader( grid, "transactionNumber", "Transaction Number", true );
			allRowsActionsUIH.prepareGridHeader( grid, "type", "Type", false );
			allRowsActionsUIH.prepareGridHeader( grid, "amount", "Amount", false );
			allRowsActionsUIH.prepareGridHeader( grid, "status", "Status", false );
			allRowsActionsUIH.prepareGridHeader( grid, "payer", "Payer", true );
			allRowsActionsUIH.prepareGridHeader( grid, "payee", "Payee", true );
			allRowsActionsUIH.prepareGridHeader( grid, "timestamp", "Timestamp", false );
			allRowsActionsUIH.prepareGridHeader( grid, "actions", "...", false );
			
			
			// Set column widths
			
			grid.getColumn( "payer" ).setWidth( 135 ).setResizable(false);
			grid.getColumn( "payee" ).setWidth( 135 ).setResizable(false);
			grid.getColumn( "transactionNumber" ).setWidth( 125 );
			// grid.getColumn( "mmoId" ).setWidth( 125 );
			// grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "amount" ).setWidth( 100 );
			// grid.getColumn( "statusDesc" ).setWidth( 125 );
			
			grid.getColumn( "timestamp" ).setWidth( 178 ).setResizable(false);
			// grid.getColumn( "rate" ).setWidth( 70 );
			
			
			grid.addStyleName( "sn-small-grid" );

			grid.setSelectionMode(SelectionMode.MULTI);
			grid.setHeight( "500px" );
			grid.setWidth( "100%" );
			
			//DataExport dataExport = new DataExport();
			// dataExport.exportDataAsExcel( grid );
			
			return grid;

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}
		
		
		
		return new Grid();
	}
	
	protected AllRowsActionsUISub getHeaderController( MSub mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, false, true, pageC );
	}
	
	protected AllRowsActionsUISub getFooterController( MSub mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, false, false, pageC );
	}
	
	protected long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	protected String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}
	
	protected String getCurrentTimeCorrection(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.TIME_CORRECTION );
	}
	
	protected void setInDate( InTxn inTxn, int dayOffSet ){
		
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar cal = Calendar.getInstance();
		
		String tDate = sdf.format( cal.getTime() );
		log.debug( "To: "+tDate );
		
		inTxn.settDate(  tDate );
		
		cal.add(Calendar.DAY_OF_MONTH, -1*(dayOffSet) );
		String fDate =  sdf.format( cal.getTime() );
		log.debug( "From: "+fDate );
		
		inTxn.setfDate( fDate );
		
		
	}
	
	


	
	


}
