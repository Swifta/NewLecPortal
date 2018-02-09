package com.lonestarcell.mtn.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.util.AbstractAllRowsActionsUI;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUIMerchant;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUIMerchant;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RowActionsUISub;
import com.lonestarcell.mtn.controller.util.TextChangeListenerSub;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MMerchant;
import com.lonestarcell.mtn.spring.fundamo.repo.Entry001Repo;
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
import com.vaadin.ui.UI;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DTxnStateMerchantArchiveUI extends DTxnStateUI<Entry001Repo> {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger( DTxnStateMerchantArchiveUI.class.getName() );
	
	DTxnStateMerchantArchiveUI( ISubUI a){
		super( a.getSpringAppContext() );
		this.init(a);
		log.debug( "Archive UI loaded successfully." );
	}

	

	@Override
	protected IModel<Entry001Repo> getiModel(ApplicationContext cxt) {
		return new MMerchant(getCurrentUserId(), getCurrentUserSession(),
				getCurrentTimeCorrection(), cxt );
	}


	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Merchant Transaction Archive");
	}
	
	
	
	@Override
	protected Grid loadGridData(
			BeanItemContainer<AbstractDataBean> beanItemContainer) {
		
		Grid grid = new Grid();
		grid.addStyleName("sn-small-grid");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setHeight("600px");
		grid.setWidth("100%");
		
		try {
			

			log.debug("Locale: " + UI.getCurrent().getLocale());

			In in = new In();

			BData<InTxn> inBData = new BData<>();

			inTxn.setPage(1);
			// this.setInDate(inTxn, ( 365 * 3) );
			inTxn.setPermSet( this.getPermSet() );
			inBData.setData(inTxn);
			in.setData(inBData);
			
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
			
			grid.setColumnOrder("column1", "column2","column3","column4","column5","column6","column7","column8","column9","column10","column11","date", "actions");
			grid.setFrozenColumnCount(2);

			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);
			
			HeaderCell dateFilterCellH = header.join("column1", "column2","column3","column4","column5","column6","column7","column8","column9","column10","column11","date", "actions");
			
			PaginationUIController pageC = new PaginationUIController();
			AllRowsActionsUIMerchant allRowsActionsUIH = new AllRowsActionsUIMerchant( iModel, grid, in, true, true, pageC );
			allRowsActionsUIH.setWidth( "100%" );
			dateFilterCellH.setComponent(allRowsActionsUIH);

			header.setStyleName("sn-date-filter-row");
			
			
		
			dateFilterCellH
					.setStyleName("sn-no-border-right sn-no-border-left");
			
			// Preparing footer
			FooterCell dateFilterCellF = footer.join("column1", "column2","column3","column4","column5","column6","column7","column8","column9","column10","column11","date", "actions");
			
			dateFilterCellF.setComponent( new AllRowsActionsUIMerchant( iModel, grid, in, false, false, pageC ));

			// Initialize pagination controller after both header and footer have been set.
			pageC.init();

			footer.setStyleName("sn-date-filter-row");
			dateFilterCellF
					.setStyleName("sn-no-border-right sn-no-border-left");

			PopupView v = new PopupView("HHHH", null);
			v.setContent(new MultiRowActionsUIMerchant(iModel, in, grid, v));
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
					"Corp Name", false );
			allRowsActionsUIH.prepareGridHeader(grid, "column2", "MSISDN", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "column3", "T. Number", true );

			allRowsActionsUIH.prepareGridHeader(grid, "column4", "Type", false);
			allRowsActionsUIH
					.prepareGridHeader(grid, "column5", "Amount", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "column6", "Status", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "column7", "Channel", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "column8", "Description", false);
			// allRowsActionsUIH.prepareGridHeader(grid, "column4", "Description", false);
			
			allRowsActionsUIH.prepareGridHeader(grid, "column9", "Payer", true);
			allRowsActionsUIH.prepareGridHeader(grid, "column10", "Payee", true);
			allRowsActionsUIH.prepareGridHeader(grid, "date", "Timestamp",
					false); 
			allRowsActionsUIH.prepareGridHeader(grid, "actions", "...", false);

			// Set column widths

			grid.getColumn("column4").setWidth(100);
			grid.getColumn("column5").setWidth(100);
			grid.getColumn("column6").setWidth(100);
			grid.getColumn("column7").setWidth(100);
			// grid.getColumn("column2").setWidth( 150 ).setResizable(false);
			grid.getColumn("column3").setWidth( 120);
			grid.getColumn("column8").setWidth( 150 );
			grid.getColumn("column9").setWidth( 150 );
			grid.getColumn("column10").setWidth( 150 );
			//grid.getColumn("date").setWidth(178).setResizable(false);
			
			grid.addStyleName( "sn-small-grid" );

			allRowsActionsUIH.removeUnnecessaryColumns(grid);

			return grid;

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}
		
		return grid;
	}
	
	@Override
	protected void setInDate(InTxn inTxn, int dayOffSet) {
			inTxn.setfDate( "2010-02-01" );
			inTxn.settDate( "2010-02-03" );
			
			inTxn.setfDefaultDate( inTxn.getfDate() );
			inTxn.settDefaultDate( inTxn.gettDate() );
	}
	
	@Override
	protected AbstractAllRowsActionsUI<Entry001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean>> getHeaderController(
			IModel<Entry001Repo> mSub, Grid grid, In in,
			PaginationUIController pageC) {
		return new AllRowsActionsUIMerchant( mSub, grid, in, true, true, pageC );
	}



	@Override
	protected AbstractAllRowsActionsUI<Entry001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean>> getFooterController(
			IModel<Entry001Repo> mSub, Grid grid, In in,
			PaginationUIController pageC) {
		return new AllRowsActionsUIMerchant( mSub, grid, in, false, false, pageC );
	}




	
	


}
