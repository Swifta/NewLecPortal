package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Paymenttransaction;
import com.lonestarcell.mtn.design.admin.DReportsUIDesign;
import com.lonestarcell.mtn.model.admin.MPaymentReport;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class DPaymentReportsUI extends DManUI {

	DPaymentReportsUI(DManUIController duic) {
		System.out.println("DDashboardUI Contructor called.");
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

			this.btnReload.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				@Override
				public void buttonClick(ClickEvent event) {

					setContent();
					((BeanItemContainer<Paymenttransaction>) ((Grid) vlTrxnTable
							.getComponent(0)).getContainerDataSource())
							.removeAllContainerFilters();

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
							(BeanItemContainer<Paymenttransaction>) ((Grid) vlTrxnTable
									.getComponent(0)).getContainerDataSource(),
							dFStartDate, dFLastDate);

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

								((BeanItemContainer<Paymenttransaction>) ((Grid) vlTrxnTable
										.getComponent(0))
										.getContainerDataSource())
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
			setDUI(duic);
			attachCommandListeners();
			setContent();
		}

		private void setContent() {

			// DEFAULT UI STATE

			dFStartDate.addStyleName("sn-invisible");
			dFLastDate.addStyleName("sn-invisible");
			btnFilter.addStyleName("sn-invisible");
			chkDateFilter.setValue(false);

			try {

				BeanItemContainer<Paymenttransaction> container = MPaymentReport
						.getPaymentReport();
				Grid grid = new Grid(container);
				// grid.setFrozenColumnCount(3);
				grid.setSelectionMode(SelectionMode.MULTI);
				grid.setSizeUndefined();
				grid.setWidth("100%");

				grid.setColumnOrder("htrxnid", "ptrxnid", "booking_ref",
						"cost", "msisdn", "pstatus", "org", "date", "time");

				grid.getColumn("pstatus").setConverter(
						new Converter<String, String>() {

							private static final long serialVersionUID = 1L;

							@Override
							public String convertToModel(String value,
									Class<? extends String> targetType,
									Locale locale)
									throws com.vaadin.data.util.converter.Converter.ConversionException {
								if (value.equals("PAID"))
									return "01";
								else
									return "100";

							}

							@Override
							public String convertToPresentation(String value,
									Class<? extends String> targetType,
									Locale locale)
									throws com.vaadin.data.util.converter.Converter.ConversionException {
								if (value.equals("01") || value.equals("1"))
									return "PAID";
								else
									return "NOT PAID";

							}

							@Override
							public Class<String> getModelType() {

								return String.class;
							}

							@Override
							public Class<String> getPresentationType() {

								return String.class;
							}

						});

				grid.addHeaderRowAt(1);

				prepareGridHeader(grid, "htrxnid", "HyperSwift ID", true);
				prepareGridHeader(grid, "ptrxnid", "3PP ID", true);
				prepareGridHeader(grid, "booking_ref", "Booking Ref", true);
				prepareGridHeader(grid, "cost", "Amount [UGX]", false);
				prepareGridHeader(grid, "msisdn", "MSISDN", true);
				prepareGridHeader(grid, "msisdn", "MSISDN", true);
				prepareGridHeader(grid, "pstatus", "Status", true);
				prepareGridHeader(grid, "org", "3PP Name", true);
				prepareGridHeader(grid, "date", "Date", true);
				prepareGridHeader(grid, "time", "TimeStamp", true);

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

	public class DateConverter implements Converter<String, String> {

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

		BeanItemContainer<Paymenttransaction> container = (BeanItemContainer<Paymenttransaction>) grid
				.getContainerDataSource();
		Column col = grid.getColumn(itemId);
		col.setHeaderCaption(columnName);

		if (itemId.equals("date"))
			col.setConverter(new DateConverter());

		if (itemId.equals("time"))
			col.setConverter(new DateTimeConverter());

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

		container.removeContainerFilters("date");

		Between fBtn = new Between("date", strSDate, strTDate);
		container.addContainerFilter(fBtn);

	}

}
