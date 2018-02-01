package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUISub;
import com.lonestarcell.mtn.controller.util.MultiRowActionsUISub;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RowActionsUISub;
import com.lonestarcell.mtn.design.admin.DTxnStateUIDesign;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MSub;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DTxnStateUI extends DTxnStateUIDesign implements
		DUserUIInitializable<ISubUI, DTxnStateUI>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private ISubUI ancestor;
	protected Logger log = LogManager.getLogger(DTxnStateUI.class.getName());

	protected IModel mSub;
	protected InTxn inTxn;

	private ApplicationContext springAppContext;

	DTxnStateUI( ISubUI a) {
		this(a.getSpringAppContext());
		mSub = new MSub(getCurrentUserId(), getCurrentUserSession(),
				getCurrentTimeCorrection(), springAppContext );
		init(a);
	}

	/*
	 * Shared constructor by both DTxnStateUI [ Parent class ] &
	 * DTxnStateUIArchive [ Child class ]. Note init() is not called in this. It
	 * only set's up data objects
	 */
	protected DTxnStateUI(ApplicationContext cxt) {
		this.setSpringAppContext(cxt);
		inTxn = new InTxn();
		this.setInDate(inTxn, 1);
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
		this.lbDataTitle.setValue(" Transaction Records Today");
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();

		swap(this);
		attachCommandListeners();
		this.vlTrxnTable.addComponent(loadGridData(new BeanItemContainer<>(
				AbstractDataBean.class)));
		this.vlTrxnTable.setHeightUndefined();
		// this.vlTrxnTable.setWidth("1200px");
		this.vlTrxnTable.setWidth("100%");

	}

	@Override
	public void swap(Component cuid) {
		// ancestor.setHeight("100%");
		// cuid.setHeight("100%");

		// ancestor.addStyleName("sn-p");
		// cuid.addStyleName("sn-c");

		cuid.setHeight("100%");
		
		// TODO testing max content width
		cuid.setWidth( "100%" );
		((VerticalLayout)( (  Panel )cuid).getContent()).setWidth( "100%" );
		// VerticalLayout v = null;
		
		
		ancestor.getAncestorUI().getcMainContent().setHeight("100%");
		// ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
		ancestor.setHeight("100%");

		log.debug("Users height: " + cuid.getHeight());

		ancestor.swap(cuid);

	}

	@Override
	public void init(ISubUI a) {

		setAncestorUI(a);
		setContent();

	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub

	}

	@Override
	public ISubUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(ISubUI a) {
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

	protected Grid loadGridData(
			BeanItemContainer<AbstractDataBean> beanItemContainer) {
		
		Grid grid = new Grid();
		grid.addStyleName("sn-small-grid");
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.setHeight("600px");
		grid.setWidth("100%");
		// grid.setWidthUndefined();
		
		try {
			
			log.debug("Locale: " + UI.getCurrent().getLocale());

			In in = new In();

			BData<InTxn> inBData = new BData<>();

			inTxn.setPage(1);
			
			// Set OutTxnMeta
			OutTxnMeta outTxnMeta = new OutTxnMeta();
			outTxnMeta
					.setTotalRevenue(new ObjectProperty<String>("0", String.class));
			outTxnMeta
					.setTotalRecord(new ObjectProperty<String>("0", String.class));
			inTxn.setMeta( outTxnMeta );
			
			
			
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
			
			grid.setColumnOrder("column1", "column2","column3","column4","column5","column6","column7", "date", "actions");
			grid.setFrozenColumnCount(2);

			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);
			
			HeaderCell dateFilterCellH = header.join("column1", "column2","column3","column4","column5","column6","column7", "date", "actions");
			
			PaginationUIController pageC = new PaginationUIController();
			AllRowsActionsUISub allRowsActionsUIH = getHeaderController(mSub,
					grid, in, pageC);
			dateFilterCellH.setComponent(allRowsActionsUIH);

			header.setStyleName("sn-date-filter-row");
			dateFilterCellH
					.setStyleName("sn-no-border-right sn-no-border-left");
			
			// Preparing footer
			FooterCell dateFilterCellF = footer.join("column1", "column2","column3","column4","column5","column6","column7","date", "actions");
			
			dateFilterCellF.setComponent(getFooterController(mSub, grid, in,
					pageC));

			// Initialize pagination controller after both header and footer have been set.
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
			

			// Add search field
			
			allRowsActionsUIH.prepareGridHeader(grid, "column1",
					"Transaction Number", true);
			allRowsActionsUIH.prepareGridHeader(grid, "column2", "Type", false);
			allRowsActionsUIH
					.prepareGridHeader(grid, "column3", "Amount", false);
			allRowsActionsUIH
					.prepareGridHeader(grid, "column4", "Status", false);
			allRowsActionsUIH.prepareGridHeader(grid, "column5", "Payer", true);
			allRowsActionsUIH.prepareGridHeader(grid, "column6", "Payee", true);
			allRowsActionsUIH.prepareGridHeader(grid, "date", "Timestamp",
					false); 
			allRowsActionsUIH.prepareGridHeader(grid, "actions", "...", false);

			// Set column widths

			grid.getColumn("column1").setWidth(125);
			grid.getColumn("column2").setWidth( 200 ).setResizable(false);
			grid.getColumn("column3").setWidth(100);
			grid.getColumn("column5").setWidth( 200 ).setResizable(false);
			grid.getColumn("column6").setWidth( 200 ).setResizable(false);
			grid.getColumn("date").setWidth(178).setResizable(false);
			
			// grid.addStyleName( "sn-small-grid" );

			// DataExport dataExport = new DataExport();
			// dataExport.exportDataAsExcel( grid );
			
			// Hide unnecessary bean fields
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

	protected AllRowsActionsUISub getHeaderController(IModel mSub, Grid grid,
			In in, PaginationUIController pageC) {
		return new AllRowsActionsUISub(mSub, grid, in, false, true, pageC);
	}

	protected AllRowsActionsUISub getFooterController(IModel mSub, Grid grid,
			In in, PaginationUIController pageC) {
		return new AllRowsActionsUISub(mSub, grid, in, false, false, pageC);
	}

	protected long getCurrentUserId() {
		return (long) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USER_ID);
	}

	protected String getCurrentUserSession() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR);
	}

	protected String getCurrentTimeCorrection() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.TIME_CORRECTION);
	}

	protected void setInDate(InTxn inTxn, int dayOffSet) {

		inTxn.setfDate( "2010-02-01" );
		inTxn.settDate( "2010-02-03" );
		
		inTxn.setfDefaultDate( inTxn.getfDate() );
		inTxn.settDefaultDate( inTxn.gettDate() );

	}

}
