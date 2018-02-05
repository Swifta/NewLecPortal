package com.lonestarcell.mtn.controller.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.design.admin.DDateFilterUIDesign;
import com.lonestarcell.mtn.model.util.DateFormatFacRuntime;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.TextField;

public abstract class AbstractAllRowsActionsUI<M, O, T> extends
		DDateFilterUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;

	protected In in;
	protected M model;
	protected Grid grid;
	private boolean allowDateFilters;
	private boolean isHeader;
	protected PaginationUIController pageC;
	protected BeanItemContainer<O> container;
	protected InTxn inTxn;
	protected OutTxnMeta outTxnMeta;
	protected List<TextField> tFSearchFields = new ArrayList<>(4);
	protected int newPage;
	protected Set< Short > permSet;

	protected Set<String> gridColumnRemnantSet = new HashSet<>(10);

	private Logger log = LogManager.getLogger(AbstractAllRowsActionsUI.class
			.getName());

	public AbstractAllRowsActionsUI(In in, boolean allowDateFilters,
			boolean isHeader, PaginationUIController pageC) {

		
		this.in = in;
		
		this.allowDateFilters = allowDateFilters;
		this.isHeader = isHeader;
		this.pageC = pageC;

	}
	
	

	public Set<Short> getPermSet() {
		return permSet;
	}



	public void setPermSet( InTxn inTxn ) {
		Set< Short > pSet = new HashSet<>();
		if( inTxn == null )
			this.permSet = pSet;
		Set< Short > set = inTxn.getPermSet();
		if( set == null )
			this.permSet = pSet;
		this.permSet = set;
	}



	protected abstract void setGrid(Grid grid);

	protected abstract void setBeanItemContainer();

	private void setInTxn(In in) {
		inTxn = (InTxn) in.getData().getData();
		this.setPermSet( inTxn );
	}

	protected abstract void setOutTxnMeta();

	public void removeUnnecessaryColumns(Grid grid) {
		if (grid == null)
			return;
		if (gridColumnRemnantSet.size() == 0)
			return;

		Iterator<Column> itr = grid.getColumns().iterator();
		while (itr.hasNext()) {
			Column column = itr.next();
			String propertyId = column.getPropertyId().toString();
			log.debug("Dead column property id: " + propertyId);

			if (!gridColumnRemnantSet.contains(propertyId))
				grid.removeColumn(propertyId);
		}

	}

	@Override
	public void attachCommandListeners() {
		this.attachBtnFilter();
		this.attachBtnRefresh();
		this.attachBtnClearFilters();

		this.attachDFStartDate();
		this.attachDFLastDate();

		// Pagination
		this.attachBtnNext();
		this.attachBtnPrev();

		this.attachBtnBeforeNext();
		this.attachBtnAfterPrev();

		// Data Export
		
		
		this.attachBtnExportOps();
		this.initDataExportUI();

	}
	


	protected void setNewPage(int page) {
		log.info( "New page called." );
		this.newPage = page;
		grid.deselectAll();
	}
	
	

	public int getNewPage() {
		return newPage;
	}

	protected void format() {

		if (outTxnMeta.getTotalRevenue().getValue() == null)
			outTxnMeta.getTotalRevenue().setValue("0");

		String sMoney = outTxnMeta.getTotalRevenue().getValue()
		.replaceAll(",", "");
		
		String money = NumberFormatFac.toMoney( sMoney );
		outTxnMeta.getTotalRevenue().setValue( money );

		String tRecord = outTxnMeta.getTotalRecord().getValue();
		if (tRecord == null)
			tRecord = "0";

		String sThousands = tRecord.toString().replaceAll(",", "");
		String thousands = NumberFormatFac.toThousands( sThousands );
		outTxnMeta.getTotalRecord().setValue( thousands );
		
		log.info( "Format called." );
	}


	private void attachBtnBeforeNext() {

		this.btnPageBeforeNext.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				log.debug("btnBeforeNextH has been clicked");
				pageC.beforeNext();
				inTxn.setPgNav( true );
				setNewPage(pageC.getNewPage());

			}

		});
	}

	private void attachBtnAfterPrev() {

		this.btnPageAfterPrev.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				log.debug("btnAfterPrevH has been clicked");
				pageC.afterPrev();
				inTxn.setPgNav( true );
				setNewPage(pageC.getNewPage());

			}

		});
	}

	private void attachBtnNext() {

		this.btnPageNext.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				log.debug("btnNextH has been clicked");
				pageC.next();
				inTxn.setPgNav( true );
				setNewPage( pageC.getNewPage() );

			}

		});
	}

	private void attachBtnPrev() {

		this.btnPagePrev.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				log.debug("btnPrevH has been clicked");
				pageC.prev();
				inTxn.setPgNav( true );
				setNewPage(pageC.getNewPage());

			}

		});
	}

	private void attachDFStartDate() {
		this.dFStartDate.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				dFStartDate.setComponentError(null);
				dFLastDate.setComponentError(null);

			}

		});
	}

	private void attachDFLastDate() {
		this.dFLastDate.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				dFStartDate.setComponentError(null);
				dFLastDate.setComponentError(null);

			}

		});
	}

	private void attachBtnClearFilters() {
		this.btnClearFilters.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				clearAllFilters();

			}
		});
	}

	private void attachBtnFilter() {
		this.btnFilter.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				doFilterByDate(container, dFStartDate, dFLastDate);

			}

		});
	}

	protected abstract void attachBtnExportOps();

	protected abstract void initDataExportUI();

	private void attachBtnRefresh() {

		this.btnRefresh.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				refreshGridData();
			}

		});
	}

	protected abstract void refreshGridData();

	protected void init() {

		this.cLeftDateFilter.setVisible(this.isHeader);
		this.setBeanItemContainer();
		this.setInTxn(in);
		this.setOutTxnMeta();
		this.setContent();
		this.attachCommandListeners();

	}

	protected void setContent() {

		this.cDateFilters.setVisible(this.allowDateFilters);

		if (this.isHeader) {

			this.dFStartDate.setDateFormat("yyyy-MM-dd");
			this.dFLastDate.setDateFormat("yyyy-MM-dd");
			// Initialize start & end date
			if (inTxn.getfDate() != null)
				this.dFStartDate.setValue(DateFormatFacRuntime.toDate(inTxn
						.getfDate()));

			if (inTxn.gettDate() != null)
				this.dFLastDate.setValue(DateFormatFacRuntime.toDate(inTxn
						.gettDate()));

			// Txn meta

			this.lbTotalRevenue.setPropertyDataSource(outTxnMeta
					.getTotalRevenue());
			this.lbTotalRecords.setPropertyDataSource(outTxnMeta
					.getTotalRecord());

			// Paginations for header

			pageC.setLbTotalRecords(this.lbTotalRecords);
			pageC.getListPageBtns().put("nextH", this.btnPageNext);
			pageC.getListPageBtns().put("prevH", this.btnPagePrev);
			pageC.getListPageBtns().put("afterPrevH", this.btnPageAfterPrev);
			pageC.getListPageBtns().put("beforeNextH", this.btnPageBeforeNext);

			format();

		} else {

			// Paginations for footer
			pageC.getListPageBtns().put("nextF", this.btnPageNext);
			pageC.getListPageBtns().put("prevF", this.btnPagePrev);
			pageC.getListPageBtns().put("afterPrevF", this.btnPageAfterPrev);
			pageC.getListPageBtns().put("beforeNextF", this.btnPageBeforeNext);

		}

	}

	private void clearAllFilters() {
		container.removeAllContainerFilters();
		
		this.dFStartDate.setValue(DateFormatFacRuntime.toDate(inTxn
				.getfDefaultDate()));
		this.dFLastDate.setValue(DateFormatFacRuntime.toDate(inTxn
				.gettDefaultDate()));
		// Effect default filters
		doFilterByDate(container, dFStartDate, dFLastDate);

		this.dFStartDate.setComponentError(null);
		this.dFLastDate.setComponentError(null);

		inTxn.setfDate(inTxn.getfDefaultDate());
		inTxn.settDate(inTxn.gettDefaultDate());

		Iterator<TextField> itr = tFSearchFields.iterator();
		while (itr.hasNext()) {
			TextField tF = itr.next();
			tF.clear();
			tF.setComponentError( null );
		}
	}

	private void doFilterByDate(BeanItemContainer<O> container,
			DateField dFStart, DateField dFLast) {

		Date fDate = dFStart.getValue();
		Date tDate = dFLast.getValue();

		dFStart.setComponentError(null);
		dFLast.setComponentError(null);

		if (fDate == null) {
			dFStart.setComponentError(new UserError("Please Select From: date"));
			return;
		}

		if (tDate == null) {
			dFLast.setComponentError(new UserError("Please Select To: date"));
			return;
		}

		// Calendar cal = Calendar.getInstance();
		// cal.setTime(tDate);
		// Not sure about this.
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		// tDate = cal.getTime();

		if (fDate.compareTo(tDate) > 0) {

			dFLast.setComponentError(new UserError(
					"Invalid dates! From: date should be earlier than To: date"));
			return;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(tDate);
		// Not sure about this.
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date tFilterTDate = cal.getTime();

		// cal.setTime(tDate);
		// Not sure about this.
		// cal.add(Calendar.DAY_OF_MONTH, 1);
		// tDate = cal.getTime();

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strSDate = sdf.format(fDate);
		log.debug("Date: " + strSDate);

		String strTDate = sdf.format(tDate);
		String strFilterTDate = sdf.format(tFilterTDate);
		log.debug("From date: " + fDate);
		log.debug("To date: " + tDate);

		inTxn.setfDate(strSDate);
		inTxn.settDate(strTDate);

		container.removeContainerFilters("date");

		log.debug("Container size: " + container.size());

		Between fBtn = new Between("date", strSDate, strFilterTDate);
		container.addContainerFilter(fBtn);

	}

	public void prepareGridHeader(Grid grid, String itemId, String columnName,
			boolean isSetFilter) {

		if (grid == null)
			throw new NullPointerException("Grid cannot be null");
		if (itemId == null)
			throw new NullPointerException("Item id is required.");
		if (columnName == null)
			columnName = itemId;

		Column col = grid.getColumn(itemId);
		col.setHeaderCaption(columnName);

		gridColumnRemnantSet.add(itemId);

		if (isSetFilter)
			addFilterField(container, grid.getHeaderRow(2), itemId);

	}

	protected abstract void addFilterField(BeanItemContainer<O> container,
			HeaderRow filterHeader, String itemId);

	protected BlurListener getSearchBlurListener(TextField tF,
			ShortcutListener listener) {
		return new BlurListenerCustom(listener, tF);
	}

	class FocusListenerCustom implements FocusListener {

		private static final long serialVersionUID = 1L;
		private ShortcutListener enterListener;
		private TextField tF;

		FocusListenerCustom(ShortcutListener enterListener, TextField tF) {
			this.enterListener = enterListener;
			this.tF = tF;
		}

		@Override
		public void focus(FocusEvent event) {

			tF.addShortcutListener(enterListener);
			tF.addBlurListener(getSearchBlurListener(tF, enterListener));
			resetTF(tF);

		}

	}

	private void resetTF(TextField tF) {
		// Clear content of other text fields
		Iterator<TextField> itr = tFSearchFields.iterator();
		while (itr.hasNext()) {
			TextField f = itr.next();
			tF.setComponentError( null );
			if (!f.equals(tF)) {
				f.clear();
				// Remove all filters except date
				container.removeAllContainerFilters();
				
				if( dFStartDate.getValue() == null )
					dFStartDate.setValue(DateFormatFacRuntime.toDate(inTxn
						.getfDefaultDate()));
				
				if( dFLastDate.getValue() == null ) {
					
					dFStartDate.setValue(DateFormatFacRuntime.toDate(inTxn
							.getfDefaultDate()));
					
					dFLastDate.setValue(DateFormatFacRuntime.toDate(inTxn
						.gettDefaultDate()));
				}
				
				doFilterByDate(container, dFStartDate, dFLastDate);
			}

		}
	}

	class BlurListenerCustom implements BlurListener {

		private static final long serialVersionUID = 1L;
		private ShortcutListener enterListener;
		private TextField tF;

		BlurListenerCustom(ShortcutListener enterListener, TextField tF) {
			this.enterListener = enterListener;
			this.tF = tF;
		}

		@Override
		public void blur(BlurEvent event) {
			tF.removeShortcutListener(enterListener);
			log.debug("Enter search shortcut listener DEttached.");

		}

	}

	protected FocusListener getSearchFocusListener(TextField tF,
			ShortcutListener enterListener) {
		return new FocusListenerCustom(enterListener, tF);
	}

	protected abstract ShortcutListener getSearchShortcutListener(TextField tF,
			String itemId, BeanItemContainer<O> container);

	protected abstract T getTextChangeListner(BeanItemContainer<O> container,
			String itemId, TextField tF);

	protected void setModel(M m) {
		this.model = m;

	}

}