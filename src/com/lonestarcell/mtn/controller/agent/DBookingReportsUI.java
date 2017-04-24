package com.lonestarcell.mtn.controller.agent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Bookingdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Cancelbookeddetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Cancelbookedflight;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getbookingreport;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Paymenttransaction;
import com.lonestarcell.mtn.controller.admin.DManUI;
import com.lonestarcell.mtn.controller.admin.DManUIController;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.design.agent.DReportsUIDesign;
import com.lonestarcell.mtn.design.agent.DTableCtrlsUIDesign;
import com.lonestarcell.mtn.model.agent.MBookingMan;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.PopupVisibilityEvent;
import com.vaadin.ui.PopupView.PopupVisibilityListener;
import com.vaadin.ui.TextField;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DBookingReportsUI extends DManUI {

	private DManUIController duic;

	public DBookingReportsUI(DManUIController duic) {
		System.out.println("DBookingReportUI Contructor called.");
		init(duic);
	}

	private class DReportsUIController extends DReportsUIDesign implements
			DUIControllable {

		private static final long serialVersionUID = 1L;

		DReportsUIController(DManUIController duic) {
			init(duic);

		}

		@Override
		public void attachCommandListeners() {
			System.out.println("Controls attached.");
			attachChkDateFilters();
			attachBtnFilter();
			attachBtnReload();

		}

		private void attachBtnReload() {

			this.btnReload.setIcon(FontAwesome.REFRESH);
			this.btnReload.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					setContent();

					new DBookingReportsUI(duic);

				}

			});
		}

		private void attachBtnFilter() {
			this.btnFilter.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				@Override
				public void buttonClick(ClickEvent event) {
					doFilterByDate(
							((BeanItemContainer<Paymenttransaction>) ((GeneratedPropertyContainer) ((Grid) vlTrxnTable
									.getComponent(0)).getContainerDataSource())
									.getWrappedContainer()), dFStartDate,
							dFLastDate);

				}

			});
		}

		private void attachChkDateFilters() {

			this.chkDateFilter
					.addValueChangeListener(new ValueChangeListener() {

						/**
				 * 
				 */
						private static final long serialVersionUID = 1L;

						@SuppressWarnings("unchecked")
						@Override
						public void valueChange(ValueChangeEvent event) {

							if (!Boolean.parseBoolean(event.getProperty()
									.getValue().toString())) {
								dFStartDate.addStyleName("sn-invisible");
								dFLastDate.addStyleName("sn-invisible");
								btnFilter.addStyleName("sn-invisible");

								((BeanItemContainer<Paymenttransaction>) ((GeneratedPropertyContainer) ((Grid) vlTrxnTable
										.getComponent(0))
										.getContainerDataSource())
										.getWrappedContainer())
										.removeContainerFilters("date");
								;

							} else {

								dFStartDate.removeStyleName("sn-invisible");
								dFLastDate.removeStyleName("sn-invisible");
								btnFilter.removeStyleName("sn-invisible");
							}

						}

					});
		}

		@Override
		public void init(DManUIController duic) {
			attachCommandListeners();
			setContent();
		}

		private void setContent() {

			// DEFAULT UI STATE

			dFStartDate.addStyleName("sn-invisible");
			dFLastDate.addStyleName("sn-invisible");
			btnFilter.addStyleName("sn-invisible");
			chkDateFilter.setValue(false);

			loadGridData();

		}

		private void loadGridData() {
			try {

				Getbookingreport report = new Getbookingreport();

				BeanItemContainer<Bookingdetails> container = MBookingMan
						.getBookingReport(report);
				Grid grid = new Grid();
				// grid.setFrozenColumnCount(3);
				grid.setSelectionMode(SelectionMode.MULTI);
				grid.setSizeUndefined();
				grid.setWidth("100%");

				GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(
						container);

				gpc.addGeneratedProperty("actions",
						new PropertyValueGenerator<Component>() {

							/**
					 * 
					 */
							private static final long serialVersionUID = 1L;

							@Override
							public Component getValue(Item item, Object itemId,
									Object propertyId) {
								Actions actions = new Actions();
								Property<?> p = item.getItemProperty("status");
								actions.setEditCancelButtonState(p.getValue()
										.toString());
								actions.setRowData(item);
								return actions;
							}

							@Override
							public Class<Component> getType() {

								return Component.class;
							}

						});

				grid.setContainerDataSource(gpc);
				grid.getColumn("actions").setRenderer(new ComponentRenderer());

				grid.setColumnOrder("actions", "bookingRef", "bookingAgent",
						"clientName", "cost", "status", "dateAdded",
						"flightDate");

				grid.setFrozenColumnCount(2);
				grid.addHeaderRowAt(1);

				prepareGridHeader(grid, "bookingRef", "Booking Ref", true);
				prepareGridHeader(grid, "bookingAgent", "Booking Agent", true);
				prepareGridHeader(grid, "clientName", "Client Name", true);
				prepareGridHeader(grid, "cost", "Amount [USD]", false);
				prepareGridHeader(grid, "status", "Payment Status", true);
				prepareGridHeader(grid, "dateAdded", "Date Logged", true);
				prepareGridHeader(grid, "flightDate", "Date of Flight", true);

				this.vlTrxnTable.addComponent(grid);
				this.vlTrxnTable.setHeightUndefined();

			} catch (Exception e) {

				Notification.show(
						"Error occured while loading data. Please try again!",
						Notification.Type.ERROR_MESSAGE);
				e.printStackTrace();

			}
		}

	}

	@Override
	public void setContent() {
		swap(duic, new DReportsUIController(duic));

	}

	@Override
	public void init(DManUIController duic) {
		setDUI(duic);
		setContent();

	}

	public static void main(String[] args) {

	}

	class StatusConverter implements Converter<String, String> {

		private static final long serialVersionUID = 1L;

		@Override
		public String convertToModel(String value,
				Class<? extends String> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {

			if (value.equals("PAID"))
				return "1";

			if (value.equals("CANCELED"))
				return "2";

			if (value.equals("NOT PAID"))
				return "0";

			return "-1";
		}

		@Override
		public String convertToPresentation(String value,
				Class<? extends String> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {
			if (value.equals("1"))
				return "PAID";

			if (value.equals("2"))
				return "CANCELED";

			if (value.equals("0"))
				return "NOT PAID";

			return "Other States";

		}

		@Override
		public Class<String> getModelType() {

			return String.class;
		}

		@Override
		public Class<String> getPresentationType() {

			return String.class;
		}

	}

	public class Actions extends DTableCtrlsUIDesign {

		private static final long serialVersionUID = 1L;

		private Item rowData;
		private Grid grid;

		Actions() {

			PopupView v = new PopupView("...", moreDropDown);
			v.addStyleName("sn-popup-view");
			this.cPopView.addComponent(v);
			v.addPopupVisibilityListener(new PopupVisibilityListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void popupVisibilityChange(PopupVisibilityEvent event) {
					if (event.getPopupView().isVisible())
						moreDropDown.setVisible(true);
					else
						moreDropDown.setVisible(false);
				}

			});

			attachBtnEdit();
			attachBtnCancel();

		}

		public void setRowData(Item item) {
			this.rowData = item;
		}

		public Item getRowData() {
			return this.rowData;
		}

		private void attachBtnCancel() {
			this.btnCancel.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Cancelbookedflight booking = new Cancelbookedflight();

					try {
						booking.setBookingRef(rowData
								.getItemProperty("bookingRef").getValue()
								.toString());

						Cancelbookeddetails response = MBookingMan
								.cancelBookedFlight(booking);
						if (response == null) {
							Notification.show(
									"Error occured during operation.",
									Notification.Type.ERROR_MESSAGE);
							return;
						}

						if (!response.getResponseCode().equals("01")) {
							Notification.show(response.getResponseMsg(),
									Notification.Type.ERROR_MESSAGE);
							return;
						}

						Notification
								.show("Booking details have been successfully cancelled.",
										Notification.Type.HUMANIZED_MESSAGE);

						System.out
								.println("Cancel booking returned successfull!");
						btnCancel.setVisible(false);

						new DBookingReportsUI(duic);

					} catch (Exception e) {
						Notification.show("Error occured during operation.",
								Notification.Type.ERROR_MESSAGE);
						e.printStackTrace();
					}

				}

			});
		}

		private void attachBtnEdit() {
			this.btnEdit.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					new DBookingFormUI(duic, rowData);

				}

			});
		}

		public void setGrid(Grid grid) {
			this.grid = grid;
		}

		public Grid getGrid() {
			return this.grid;
		}

		public void setEditCancelButtonState(String s) {

			if (s == null)
				throw new IllegalArgumentException("Status should not be null");

			if (s.equals("0")) {
				this.btnCancel.setVisible(true);
				this.btnEdit.setVisible(true);

			} else if (s.equals("2")) {
				this.btnCancel.setVisible(false);
			} else {
				this.btnCancel.setVisible(false);
				this.btnEdit.setCaption("No Actions");
				this.btnEdit.setIcon(FontAwesome.STOP);
				this.btnEdit.setDescription("No actions");
				this.btnEdit.setEnabled(false);
				this.btnEdit.setVisible(true);
				return;
			}
		}

	}

	class DateTimeConverter implements Converter<String, String> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String convertToModel(String value,
				Class<? extends String> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {

			return value;
		}

		@Override
		public String convertToPresentation(String value,
				Class<? extends String> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {

			try {
				String sDate = value;
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
				Date date;

				date = sdf.parse(sDate);

				sDate = sdf.format(date);

				return sDate;

			} catch (ParseException e) {
				e.printStackTrace();

				return new String("!#Invalid Time#!");
			}

		}

		@Override
		public Class<String> getModelType() {

			return String.class;
		}

		@Override
		public Class<String> getPresentationType() {

			return String.class;
		}

	}

	private class DateConverter implements Converter<String, String> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String convertToModel(String value,
				Class<? extends String> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {

			try {
				String sDate = value;
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date;

				date = sdf.parse(sDate);

				sDate = sdf.format(date);

				return sDate;

			} catch (ParseException e) {
				e.printStackTrace();

				return new String("!#Invalid Date#!");
			}

		}

		@Override
		public String convertToPresentation(String value,
				Class<? extends String> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {

			try {
				String sDate = value;
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date;

				date = sdf.parse(sDate);

				sDate = sdf.format(date);

				return sDate;

			} catch (ParseException e) {
				e.printStackTrace();

				return new String("!#Invalid Date#!");
			}

		}

		@Override
		public Class<String> getModelType() {

			return String.class;
		}

		@Override
		public Class<String> getPresentationType() {

			return String.class;
		}

	}

	private TextChangeListener getTextChangeListner(
			final BeanItemContainer<Paymenttransaction> container,
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

		BeanItemContainer<Paymenttransaction> container = (BeanItemContainer<Paymenttransaction>) gpc
				.getWrappedContainer();
		Column col = grid.getColumn(itemId);
		col.setHeaderCaption(columnName);

		if (itemId.equals("flightDate"))
			col.setConverter(new DateConverter());

		if (itemId.equals("dateAdded"))
			col.setConverter(new DateTimeConverter());

		if (itemId.equals("status"))
			col.setConverter(new StatusConverter());

		if (isSetFilter)
			addFilterField(container, grid.getHeaderRow(1), itemId);

	}

	private void addFilterField(
			BeanItemContainer<Paymenttransaction> container,
			HeaderRow filterHeader, String itemId) {

		TextField tF = new TextField();
		tF.setStyleName("sn-tf-filter");
		tF.setDescription("Search");
		tF.setInputPrompt("Search...");
		tF.setWidth("80px");
		HeaderCell cFilter = filterHeader.getCell(itemId);
		cFilter.setComponent(tF);
		tF.addTextChangeListener(getTextChangeListner(container, itemId));

	}

	private void doFilterByDate(
			BeanItemContainer<Paymenttransaction> container, DateField dFStart,
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
		cal.add(Calendar.DATE, 1);

		tDate = cal.getTime();

		if (fDate.compareTo(tDate) > 0) {

			dFLast.setComponentError(new UserError(
					"Invalid dates! \"From\" date should be earlier than \"To\" date"));
			return;
		}

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strSDate = sdf.format(fDate);
		System.out.println("Date: " + strSDate);

		String strTDate = sdf.format(tDate);
		System.out.println("l Date: " + strTDate);

		container.removeContainerFilters("dateAdded");

		Between fBtn = new Between("dateAdded", strSDate, strTDate);
		container.addContainerFilter(fBtn);

	}

	@Override
	public void setDUI(DManUIController duic) {
		this.duic = duic;
	}

}
