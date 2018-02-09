package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.vaadin.ui.UI;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.util.AbstractAllRowsActionsUI;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUILedger;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUILedger;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RowActionsUISub;
import com.lonestarcell.mtn.controller.util.TextChangeListenerSub;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MLedger;
import com.lonestarcell.mtn.spring.fundamo.repo.LedgerAccount001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.ObjectProperty;
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

public class DTxnStateLedgerArchiveUI extends DTxnStateUI<LedgerAccount001Repo> {

	private static final long serialVersionUID = 1L;
    private Logger log = LogManager.getLogger( DTxnStateLedgerArchiveUI.class.getName() );
   
	DTxnStateLedgerArchiveUI( ISubUI a){
		super( a.getSpringAppContext() );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}
	

	@Override
	protected IModel<LedgerAccount001Repo> getiModel(ApplicationContext cxt) {
		return new MLedger(getCurrentUserId(), getCurrentUserSession(),
				getCurrentTimeCorrection(), cxt );
	}
	

	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Ledger Archive");
	}
	

	
	@Override
	protected Grid loadGridData(
			BeanItemContainer<AbstractDataBean> beanItemContainer) {
		
		Grid grid = new Grid();
		grid.addStyleName("sn-small-grid");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setHeight("600px");
		grid.setWidth("100%");
		
		log.debug("Locale: " + UI.getCurrent().getLocale());
		
		try {
			

			

			In in = new In();

			BData<InTxn> inBData = new BData<>();

			inTxn.setPage(1);
			// this.setInDate(inTxn, ( 365 * 3) );
			inBData.setData(inTxn);
			in.setData(inBData);
			inTxn.setPermSet( this.getPermSet() );
			
			// Set OutTxnMeta
			OutTxnMeta outTxnMeta = new OutTxnMeta();
			outTxnMeta
					.setTotalRevenue(new ObjectProperty<String>("0", String.class));
			outTxnMeta
					.setTotalRecord(new ObjectProperty<String>("0", String.class));
			inTxn.setMeta( outTxnMeta );
			

			// TODO validate response

			Out out = iModel.search(in, beanItemContainer);
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
									new RowActionsUISub(iModel, item));
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
			
			grid.setColumnOrder("column1", "column2","column3","column4", "actions");
			grid.setFrozenColumnCount(2);

			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);
			
			HeaderCell dateFilterCellH = header.join("column1", "column2","column3","column4", "actions");
			
			PaginationUIController pageC = new PaginationUIController();
			AbstractAllRowsActionsUI<LedgerAccount001Repo, AbstractDataBean, TextChangeListenerSub< AbstractDataBean> > allRowsActionsUIH = getHeaderController(iModel,
					grid, in, pageC);
			
			dateFilterCellH.setComponent(allRowsActionsUIH);

			header.setStyleName("sn-date-filter-row");
			dateFilterCellH
					.setStyleName("sn-no-border-right sn-no-border-left");
			
			// Preparing footer
			FooterCell dateFilterCellF = footer.join("column1", "column2","column3","column4", "actions");
			
			dateFilterCellF.setComponent( getFooterController(iModel, grid, in,
					pageC) );

			// Initialize pagination controller after both header and footer have been set.
			pageC.init();

			footer.setStyleName("sn-date-filter-row");
			dateFilterCellF
					.setStyleName("sn-no-border-right sn-no-border-left");

			PopupView v = new PopupView("HHHH", null);
			v.setContent(new MultiRowActionsUILedger(iModel, in, grid, v));
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
					"Account Number", true);
			allRowsActionsUIH.prepareGridHeader(grid, "column2", "Name", true );
			allRowsActionsUIH.prepareGridHeader(grid, "column3", "Amount", false);
			allRowsActionsUIH.prepareGridHeader(grid, "column4", "Latest Timestamp", false);
			allRowsActionsUIH.prepareGridHeader(grid, "actions", "...", false);
			
			// grid.addStyleName( "sn-small-grid" );

			// Set column widths

			// grid.getColumn("column1").setWidth(250);
			// grid.getColumn("column2").setWidth( 150 ).setResizable(false);
			// grid.getColumn("column3").setWidth( 150);
			
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
	
	@Override
	protected void setInDate( InTxn inTxn, int offSet ){
		inTxn.setfDate( "2010-02-01" );
		inTxn.settDate( "2014-10-27" );
		
		inTxn.setfDefaultDate( inTxn.getfDate() );
		inTxn.settDefaultDate( inTxn.gettDate() );
	}

	@Override
	protected AbstractAllRowsActionsUI<LedgerAccount001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean>> getHeaderController(
			IModel<LedgerAccount001Repo> mSub, Grid grid, In in,
			PaginationUIController pageC) {
		return new AllRowsActionsUILedger( mSub, grid, in, true, true, pageC );
	}



	@Override
	protected AbstractAllRowsActionsUI<LedgerAccount001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean>> getFooterController(
			IModel<LedgerAccount001Repo> mSub, Grid grid, In in,
			PaginationUIController pageC) {
		return new AllRowsActionsUILedger( mSub, grid, in, false, false, pageC );
	}

	
	


}
