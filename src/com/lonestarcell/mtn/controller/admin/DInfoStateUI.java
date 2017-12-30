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
import com.lonestarcell.mtn.controller.util.AllRowsActionsUIInfo;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUIInfo;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RowActionsUIInfo;
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

public class DInfoStateUI extends DTxnStateUIDesign implements DUserUIInitializable<DInfoUI, DInfoStateUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DInfoUI ancestor;
	private Logger log = LogManager.getLogger();
	private BeanItemContainer<OutTxn> beanItemContainer;
	
	private MTxn mTxn;
	protected InTxn inTxn;
	
	
	DInfoStateUI( DInfoUI a){
		this();
		this.setInDate(inTxn, 1 );
		init( a );
	}
	
	DInfoStateUI(){
		mTxn = new MTxn(  getCurrentUserId(), getCurrentUserSession(), getCurrentTimeCorrection() );
		inTxn = new InTxn();
	}

	@Override
	public void attachCommandListeners() {
		
	}


	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("ITRON Customer Info & Vend Retry Records Today");
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
	public void init(DInfoUI a) {
		setAncestorUI( a );
		setContent();
		
	}

	@Override
	public void setFooter() {
		
	}

	@Override
	public DInfoUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DInfoUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DInfoStateUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DInfoStateUI p) {
		
	}
	
	
	public Grid loadGridData( BeanItemContainer<OutTxn> beanItemContainer ) {
		try {

			
			
			
			In in = new In();
			
			BData<InTxn> inBData = new BData<>();
			inTxn.setPage( 1 );
			inBData.setData( inTxn );
			in.setData( inBData );
			
			
			Out out = mTxn.searchInfoRetryToday(in, beanItemContainer );
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
							PopupView v = new PopupView("...", new RowActionsUIInfo( mTxn, item ) );
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
			PaginationUIController pageC = new PaginationUIController( );
			
			AllRowsActionsUIInfo allRowsActionsUIInfo = getHeaderController( mTxn, grid, in, pageC );
			dateFilterCellH.setComponent( allRowsActionsUIInfo );
			
			header.setStyleName( "sn-date-filter-row" );
			dateFilterCellH.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			// Footer config
			FooterCell dateFilterCellF = footer.join( "swiftaId", "itronId", "meterNo", "amount", "tokenStatus", "txnType", "reqCount", "date", "actions");
		
			dateFilterCellF.setComponent( getFooterController( mTxn, grid, in, pageC ) );
			
			//Init pagination controller after both header and footer have been set.
			pageC.init();

			
			footer.setStyleName( "sn-date-filter-row" );
			dateFilterCellF.setStyleName( "sn-no-border-right sn-no-border-left" );
			
			PopupView v = new PopupView("...", null );
			
			MultiRowActionsUIInfo content = new MultiRowActionsUIInfo( mTxn, in, grid, v );
			v.setContent( content );
				
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
			
			allRowsActionsUIInfo.prepareGridHeader(grid, "itronId", "ITRON ID", true );
			allRowsActionsUIInfo.prepareGridHeader(grid, "swiftaId", "SWIFTA ID", true );
			allRowsActionsUIInfo.prepareGridHeader(grid, "meterNo", "Meter No.", true );
			allRowsActionsUIInfo.prepareGridHeader(grid, "txnType", "ReQ. Type", true );
			allRowsActionsUIInfo.prepareGridHeader(grid, "tokenStatus", "Token Status", true );
			allRowsActionsUIInfo.prepareGridHeader(grid, "date", "Timestamp", false );
			allRowsActionsUIInfo.prepareGridHeader(grid, "reqCount", "Count", false );
			allRowsActionsUIInfo.prepareGridHeader(grid, "actions", "...", false );
			
			
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
			
			return grid;

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}
		
		
		
		return new Grid();
	}
	

	
	
	protected AllRowsActionsUIInfo getHeaderController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIInfo( mTxn, grid, in, false, true, pageC );
	}
	
	protected AllRowsActionsUIInfo getFooterController( MTxn mTxn, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUIInfo( mTxn, grid, in, false, false, pageC );
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
