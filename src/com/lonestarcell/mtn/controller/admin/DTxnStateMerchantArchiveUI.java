package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutSubscriber;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUISub;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUITxn;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUISub;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.PopupViewPropertyValueGenerator;
import com.lonestarcell.mtn.controller.util.RowActionsUISub;
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

public class DTxnStateMerchantArchiveUI extends DTxnStateUI {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( DTxnStateMerchantArchiveUI.class.getName() );
	
	
	DTxnStateMerchantArchiveUI( DTxnUI a){
		super( a.getSpringAppContext() );
		this.setInDate( inTxn, 4*365 );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	
	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Merchant Transaction Archive");
	}
	
	@Override
	protected AllRowsActionsUISub getHeaderController( MSub mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, true, true, pageC );
	}
	
	@Override
	protected AllRowsActionsUISub getFooterController( MSub mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, false, false, pageC );
	}
	


	protected Grid loadGridData(
			BeanItemContainer<OutSubscriber> beanItemContainer) {
		try {

			log.debug("Locale: " + UI.getCurrent().getLocale());

			In in = new In();

			BData<InTxn> inBData = new BData<>();

			inTxn.setPage(1);
			// this.setInDate(inTxn, ( 365 * 3) );
			inBData.setData(inTxn);
			in.setData(inBData);

			// TODO validate response

			Out out = mSub.searchTxnToday(in, beanItemContainer);
			if (out.getStatusCode() != 1) {
				Notification.show(out.getMsg(),
						Notification.Type.WARNING_MESSAGE);
			} else {
				Notification.show(out.getMsg(),
						Notification.Type.HUMANIZED_MESSAGE);
			}

			Grid grid = new Grid();

			// Add actions

			GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(
					beanItemContainer);

			gpc.addGeneratedProperty("actions",
					new PopupViewPropertyValueGenerator( mSub ));

			grid.setContainerDataSource(gpc);
			grid.getColumn("actions").setRenderer(new ComponentRenderer() );
			grid.setColumnOrder("transactionNumber", "type", "amount",
					"status", "payer", "payee", "date", "actions");

			grid.setFrozenColumnCount(2);

			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);

			// Header config
			// HeaderCell dateFilterCellH = header.join( "swiftaId", "mmoId",
			// "msisdn", "meterNo", "amount", "rate", "statusDesc", "actions",
			// "date" );
			HeaderCell dateFilterCellH = header.join("transactionNumber",
					"type", "amount", "status", "payer", "payee", "date",
					"actions");
			PaginationUIController pageC = new PaginationUIController();
			AllRowsActionsUISub allRowsActionsUIH = getHeaderController(mSub,
					grid, in, pageC);
			dateFilterCellH.setComponent(allRowsActionsUIH);

			header.setStyleName("sn-date-filter-row");
			dateFilterCellH
					.setStyleName("sn-no-border-right sn-no-border-left");

			// Footer config
			// FooterCell dateFilterCellF = footer.join( "swiftaId", "mmoId",
			// "msisdn", "meterNo", "amount", "rate", "statusDesc", "actions",
			// "date" );

			FooterCell dateFilterCellF = footer.join("transactionNumber",
					"type", "amount", "status", "payer", "payee", "date",
					"actions");
			dateFilterCellF.setComponent(getFooterController(mSub, grid, in,
					pageC));

			// Init pagination controller after both header and footer have been
			// set.
			pageC.init();

			footer.setStyleName("sn-date-filter-row");
			dateFilterCellF
					.setStyleName("sn-no-border-right sn-no-border-left");

			PopupView v = new PopupView("HHHH", null);
			v.setContent(new MultiRowActionsUISub(mSub, in, grid, v));
			v.setHideOnMouseOut(true);
			v.setVisible(true);

			HeaderCell cellBulkActions = headerTextFilter.getCell("actions");
			v.setWidth("100%");
			v.setHeight("100%");

			cellBulkActions.setComponent(v);

			grid.getColumn("actions").setWidth(50);
			HeaderRow headerColumnNames = grid.getHeaderRow(1);

			HeaderCell cellActions = headerColumnNames.getCell("actions");

			cellActions.setStyleName("sn-cell-actions");
			cellBulkActions.setStyleName("sn-cell-actions");

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

			// "transactionNumber", "type", "amount", "status", "payer",
			// "payee", "date", "actions"

			allRowsActionsUIH.prepareGridHeader(grid, "transactionNumber",
					"Transaction Number", true);
			allRowsActionsUIH.prepareGridHeader(grid, "type", "Type", false);
			allRowsActionsUIH
					.prepareGridHeader(grid, "amount", "Amount", false);
			allRowsActionsUIH
					.prepareGridHeader(grid, "status", "Status", false);
			allRowsActionsUIH.prepareGridHeader(grid, "payer", "Payer", true);
			allRowsActionsUIH.prepareGridHeader(grid, "payee", "Payee", true);
			allRowsActionsUIH.prepareGridHeader(grid, "date", "Timestamp",
					false);
			allRowsActionsUIH.prepareGridHeader(grid, "actions", "...", false);

			// Set column widths

			grid.getColumn("payer").setWidth( 200 ).setResizable(false);
			grid.getColumn("payee").setWidth( 200 ).setResizable(false);
			grid.getColumn("transactionNumber").setWidth(125);
			// grid.getColumn( "mmoId" ).setWidth( 125 );
			// grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn("amount").setWidth(100);
			// grid.getColumn( "statusDesc" ).setWidth( 125 );

			grid.getColumn("date").setWidth(178).setResizable(false);
			// grid.getColumn( "rate" ).setWidth( 70 );

			grid.addStyleName("sn-small-grid");

			grid.setSelectionMode(SelectionMode.MULTI);
			grid.setHeight("600px");
			grid.setWidth("100%");

			// DataExport dataExport = new DataExport();
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
	
	


}
