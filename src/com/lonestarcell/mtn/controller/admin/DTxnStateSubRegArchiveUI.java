package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.UI;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUILedger;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUISub;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUILedger;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUISub;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RowActionsUISub;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MSubReg;
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

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DTxnStateSubRegArchiveUI extends DTxnStateArchiveUI {

	private static final long serialVersionUID = 1L;
    private Logger log = LogManager.getLogger( DTxnStateSubRegArchiveUI.class.getName() );
	DTxnStateSubRegArchiveUI( ISubUI a){
		super( a.getSpringAppContext() );
		mSub = new MSubReg(getCurrentUserId(), getCurrentUserSession(),
				getCurrentTimeCorrection(), a.getSpringAppContext() );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	
	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Subscriber Registration Archive");
	}
	
	@Override
	protected AllRowsActionsUISub getHeaderController( IModel mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, true, true, pageC );
	}
	
	@Override
	protected AllRowsActionsUISub getFooterController( IModel mSub, Grid grid, In in, PaginationUIController pageC ){
		return new AllRowsActionsUISub( mSub, grid, in, false, false, pageC );
	}
	
	@Override
	protected Grid loadGridData(
			BeanItemContainer<AbstractDataBean> beanItemContainer) {
		
		Grid grid = new Grid();
		grid.addStyleName("sn-small-grid");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setHeight("600px");
		grid.setWidth("100%");
		
		if( log == null ){
			System.err.println( "log is really null huh!!!????" );
		}
		if( UI.getCurrent() == null ){
			log.error( "Current UI is null -." );
		} else {
			log.error( "Current UI is set -." );
		}
		log.debug("Locale: " + UI.getCurrent().getLocale());
		
		try {
			

			

			In in = new In();

			BData<InTxn> inBData = new BData<>();

			inTxn.setPage(1);
			// this.setInDate(inTxn, ( 365 * 3) );
			inBData.setData(inTxn);
			in.setData(inBData);

			// TODO validate response

			Out out = mSub.search(in, beanItemContainer);
			if (out.getStatusCode() != 1) {
				Notification.show(out.getMsg(),
						Notification.Type.WARNING_MESSAGE);
				return grid;
			} else {
				Notification.show(out.getMsg(),
						Notification.Type.HUMANIZED_MESSAGE);
			}

			// Add actions

			GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(
					beanItemContainer);

			gpc.addGeneratedProperty("actions",
					new PropertyValueGenerator<Component>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Component getValue(Item item, Object itemId,
								Object propertyId) {
							PopupView v = new PopupView("...",
									new RowActionsUISub(mSub, item));
							v.setWidth("100%");
							v.setHeight("100%");
							return v;
						}

						@Override
						public Class<Component> getType() {
							return Component.class;
						}

					});

			grid.setContainerDataSource(gpc);
			grid.getColumn("actions").setRenderer(new ComponentRenderer());
			
			grid.setColumnOrder("column1", "column2","column3","column4","column5","column6","column7","date", "actions");
			grid.setFrozenColumnCount(2);

			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);
			
			HeaderCell dateFilterCellH = header.join("column1", "column2","column3","column4","column5","column6","column7","date", "actions");
			
			PaginationUIController pageC = new PaginationUIController();
			AllRowsActionsUISub allRowsActionsUIH = new AllRowsActionsUILedger( mSub, grid, in, true, true, pageC );
			dateFilterCellH.setComponent(allRowsActionsUIH);

			header.setStyleName("sn-date-filter-row");
			dateFilterCellH
					.setStyleName("sn-no-border-right sn-no-border-left");
			
			// Preparing footer
			FooterCell dateFilterCellF = footer.join("column1", "column2","column3","column4","column5","column6","column7","date", "actions");
			
			dateFilterCellF.setComponent(new AllRowsActionsUILedger( mSub, grid, in, false, false, pageC ) );

			// Initialize pagination controller after both header and footer have been set.
			pageC.init();

			footer.setStyleName("sn-date-filter-row");
			dateFilterCellF
					.setStyleName("sn-no-border-right sn-no-border-left");

			PopupView v = new PopupView("HHHH", null);
			v.setContent(new MultiRowActionsUILedger(mSub, in, grid, v));
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
			

			//private String name, msisdn,  tno, type, amount,  status, channel, desc, payee, payer, entryDate ;
			
			// Add search field
			
			allRowsActionsUIH.prepareGridHeader(grid, "column1",
					"Name", true);
			allRowsActionsUIH.prepareGridHeader(grid, "column2", "MSISDN", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "column3", "ID No.", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "column4", "Type", false);
			allRowsActionsUIH
					.prepareGridHeader(grid, "column5", "Date of Birth", false);
			
			allRowsActionsUIH
			.prepareGridHeader(grid, "column6", "Status", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "date", "Time of Registration",
					false); 
			allRowsActionsUIH.prepareGridHeader(grid, "actions", "...", false);

			// Set column widths

			grid.getColumn("column1").setWidth(250);
			grid.getColumn("column2").setWidth( 150 ).setResizable(false);
			grid.getColumn("column3").setWidth( 150);
			grid.getColumn("column5").setWidth( 178 ).setResizable(false);
			grid.getColumn("column6").setWidth( 150 ).setResizable(false);
			grid.getColumn("date").setWidth(178).setResizable(false);
			
			// grid.addStyleName( "sn-small-grid" );

			allRowsActionsUIH.removeUnnecessaryColumns(grid);

			return grid;

		} catch (Exception e) {

			e.printStackTrace();
			Notification.show(
					e.getMessage()+" - Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}
		
		return grid;
	}


	
	


}
