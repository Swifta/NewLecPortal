package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUIPayment;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUIPayment;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RowActionsUIPayment;
import com.lonestarcell.mtn.design.admin.DTxnStateUIDesign;
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

public class DPaymentStateUI extends DTxnStateUIDesign implements DUserUIInitializable<DPaymentUI, DPaymentStateUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DPaymentUI ancestor;
	private Logger log = LogManager.getLogger();
	private BeanItemContainer<OutTxn> beanItemContainer;
	
	private MTxn mTxn;
	protected InTxn inTxn;
	
	DPaymentStateUI( DPaymentUI a){
		this();
		this.setInDate(inTxn, 1 );
		init( a );
	}
	
	DPaymentStateUI(){
		mTxn = new MTxn(  getCurrentUserId(), getCurrentUserSession(), getCurrentTimeCorrection() );
		inTxn = new InTxn();
	}

	@Override
	public void attachCommandListeners() {
		
	}
	
	
	

	@Override
	public void setHeader() {
		this.lbDataTitle.setValue( "Payment & SMS Records Today" );
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
	public DPaymentStateUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DPaymentStateUI p) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Grid loadGridData( BeanItemContainer<OutTxn> beanItemContainer ) {
		try {

			
			
			
			In in = new In();
			
			BData<InTxn> inBData = new BData<>();
			inTxn.setPage( 1 );
			inBData.setData( inTxn );
			in.setData( inBData );
			
			//TODO validate response
			
			Out out = mTxn.searchPaymentToday(in, beanItemContainer );
			
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
							PopupView v = new PopupView("...", new RowActionsUIPayment( mTxn, item ) );
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
			
			PaginationUIController pageC = new PaginationUIController();
			AllRowsActionsUIPayment allRowsActionsUIPayment = getHeaderController( mTxn, grid, in,pageC );
			dateFilterCellH.setComponent( allRowsActionsUIPayment );
			
			header.setStyleName( "sn-date-filter-row" );
			dateFilterCellH.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			// Footer config
			//FooterCell dateFilterCellF = footer.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "rate", "statusDesc", "actions", "date" );
			
			FooterCell dateFilterCellF = footer.join( "swiftaId", "mmoId", "msisdn", "meterNo", "amount", "reqCurrency", "token", "statusDesc", "date", "actions" ); 

			
			dateFilterCellF.setComponent( getFooterController(  mTxn, grid, in,pageC ) );
			
			//Init pagination controller after both header and footer have been set.
			pageC.init();

			
			footer.setStyleName( "sn-date-filter-row" );
			dateFilterCellF.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			PopupView v = new PopupView("...", null );
			
			MultiRowActionsUIPayment content = new MultiRowActionsUIPayment( mTxn, in, grid, v );
			v.setContent(content );
				
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
			
			allRowsActionsUIPayment.prepareGridHeader(grid, "mmoId", "MoID", true );
			allRowsActionsUIPayment.prepareGridHeader(grid, "msisdn", "MSISDN", true );
			allRowsActionsUIPayment.prepareGridHeader(grid, "swiftaId", "SWIFTA ID", true );
			allRowsActionsUIPayment.prepareGridHeader(grid, "statusDesc", "Status", true );
			allRowsActionsUIPayment.prepareGridHeader(grid, "reqCurrency", "ReQ.Cur.", true );
			allRowsActionsUIPayment.prepareGridHeader(grid, "meterNo", "Meter No.", true );
			allRowsActionsUIPayment.prepareGridHeader(grid, "token", "Token", true );
			allRowsActionsUIPayment.prepareGridHeader(grid, "date", "Timestamp", false );
			allRowsActionsUIPayment.prepareGridHeader(grid, "actions", "...", false );
			
			
			// Set column widths
			
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
			
			
			return grid;

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}
		
		
		
		return new Grid();
	}
	

	

	protected AllRowsActionsUIPayment getHeaderController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIPayment( mTxn, grid, in, false, true, pageC );
	}
	
	
	protected AllRowsActionsUIPayment getFooterController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIPayment( mTxn, grid, in, false, false, pageC );
	}


	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}
	
	private String getCurrentTimeCorrection(){
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
