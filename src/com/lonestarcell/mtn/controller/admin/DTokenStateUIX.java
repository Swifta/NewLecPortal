package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Cancelbookeddetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Cancelbookedflight;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Todayflightdata;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxn;
import com.lonestarcell.mtn.controller.agent.DBookingFormUI;
import com.lonestarcell.mtn.controller.agent.DTodayBookingReportsUI;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DTxnStateUIDesign;
import com.lonestarcell.mtn.design.agent.DTableCtrlsUIDesign;
import com.lonestarcell.mtn.model.admin.MTxn;
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
import com.vaadin.ui.UI;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DTokenStateUIX extends DTxnStateUIDesign implements DUserUIInitializable<DTokenUI, DTokenStateUIX>, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	private DTokenUI ancestor;
	private Logger log = LogManager.getLogger();
	private DManUIController duic;
	
	DTokenStateUIX( DTokenUI a){
		init( a );
	}

	@Override
	public void attachCommandListeners() {
		
		/*
		attachChkDateFilters();
		attachBtnReload();
		attachBtnFilter();
		
		*/
		
	}

	@Override
	public void init(DManUIController duic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader() {
		this.lbDataTitle.setValue("Today");
		
		/* dFStartDate.addStyleName("sn-invisible");
		dFLastDate.addStyleName("sn-invisible");
		btnFilter.addStyleName("sn-invisible");
		chkDateFilter.setValue(false);
		
		*/

		
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();
		swap(this);
		attachCommandListeners();
		loadGridData();
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
	public void init(DTokenUI a) {
		setAncestorUI( a );
		setContent();
		
	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DTokenUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DTokenUI a) {
		this.ancestor = a;
		
	}

	@Override
	public DTokenStateUIX getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DTokenStateUIX p) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void loadGridData() {
		try {

			
			MTxn mTxn = new MTxn(  getCurrentUserId(), getCurrentUserSession()  );
			
			In in = new In();
			BData<InTxn> inBData = new BData<>();
			InTxn inTxn = new InTxn();
			
			DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
			Calendar cal = Calendar.getInstance();
			
			String tDate = sdf.format( cal.getTime() );
			log.debug( "To: "+tDate );
			
			inTxn.settDate(  tDate );
			
			
			cal.add(Calendar.DAY_OF_MONTH, -100 );
			String fDate =  sdf.format( cal.getTime() );
			log.debug( "From: "+fDate );
			
			inTxn.setfDate( fDate );
			
			inBData.setData( inTxn );
			in.setData( inBData );
			Out out = mTxn.getTokenToday(in);
			
			
			BData<?> bOutData = out.getData();
			
			@SuppressWarnings("unchecked")
			BeanItemContainer<OutTxn> container = (BeanItemContainer<OutTxn>) bOutData.getData();
		

			Grid grid = new Grid();
			
			grid.setSelectionMode(SelectionMode.MULTI);
			grid.setSizeUndefined();
			grid.setWidth("100%");

			// Add actions
			
			GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(
					container);

			gpc.addGeneratedProperty("actions",
					new PropertyValueGenerator<Component>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Component getValue(Item item, Object itemId,
								Object propertyId) {
							Actions actions = new Actions();
							Property<?> p = item.getItemProperty("itronId");
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

			grid.setColumnOrder( "swiftaId", "itronId", "meterNo", "amount", "rate", "tokenStatus", "date" );

			grid.setFrozenColumnCount(2);
			grid.addHeaderRowAt(1);
			
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
			
			prepareGridHeader(grid, "itronId", "ITRON ID", true );
			prepareGridHeader(grid, "swiftaId", "SWIFTA ID", true );
			prepareGridHeader(grid, "meterNo", "Meter No.", true );
			prepareGridHeader(grid, "txnType", "ReQ. Type", true );
			prepareGridHeader(grid, "tokenStatus", "Token Status", true );
			prepareGridHeader(grid, "date", "Timestamp", false );
			prepareGridHeader(grid, "actions", "...", false );
			
			// Set column widths
			
			//grid.getColumn( "msisdn" ).setWidth( 131 ).setResizable(false);
			grid.getColumn( "swiftaId" ).setWidth( 105 );
			grid.getColumn( "itronId" ).setWidth( 105 );
			grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "meterNo" ).setWidth( 135 );
			grid.getColumn( "amount" ).setWidth( 90 );
			//grid.getColumn( "reqCurrency" ).setWidth( 90 );
			//grid.getColumn( "token" ).setWidth( 195 );
			grid.getColumn( "date" ).setWidth( 170 );
			//grid.getColumn( "statusDesc" ).setWidth( 107 );
			
			
			grid.addStyleName( "sn-small-grid" );

			this.vlTrxnTable.addComponent(grid);
			this.vlTrxnTable.setHeightUndefined();

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}
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

		BeanItemContainer<Todayflightdata> container = (BeanItemContainer<Todayflightdata>) gpc
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

						new DTodayBookingReportsUI(duic);

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

			if (!s.equals("0")) {
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
	
	
	private void addFilterField(BeanItemContainer<Todayflightdata> container,
			HeaderRow filterHeader, String itemId) {

		TextField tF = new TextField();
		tF.setStyleName("sn-tf-filter");
		tF.setDescription("Search");
		tF.setInputPrompt("Search...");
		tF.setWidth("100%");
		HeaderCell cFilter = filterHeader.getCell(itemId);
		cFilter.setComponent(tF);
		tF.addTextChangeListener(getTextChangeListner(container, itemId));

	}
	
	
	private TextChangeListener getTextChangeListner(
			final BeanItemContainer<Todayflightdata> container,
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
	
	
	/*private void attachBtnFilter() {
		this.btnFilter.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				doFilterByDate(
						((BeanItemContainer<OutTxn>) ((GeneratedPropertyContainer) ((Grid) vlTrxnTable
								.getComponent(0)).getContainerDataSource())
								.getWrappedContainer()), dFStartDate,
						dFLastDate);

			}

		});
	}*/
	
	/*
	private void attachChkDateFilters() {

		this.chkDateFilter
				.addValueChangeListener(new ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unchecked")
					@Override
					public void valueChange(ValueChangeEvent event) {

						if (!Boolean.parseBoolean(event.getProperty()
								.getValue().toString())) {
							dFStartDate.addStyleName("sn-invisible");
							dFLastDate.addStyleName("sn-invisible");
							btnFilter.addStyleName("sn-invisible");

							((BeanItemContainer<OutTxn>) ((GeneratedPropertyContainer) ((Grid) vlTrxnTable
									.getComponent(0))
									.getContainerDataSource())
									.getWrappedContainer())
									.removeContainerFilters("date");

						} else {

							dFStartDate.removeStyleName("sn-invisible");
							dFLastDate.removeStyleName("sn-invisible");
							btnFilter.removeStyleName("sn-invisible");
						}

					}

				});
	} */
	
	/*
	
	private void attachBtnReload() {

		this.btnReload.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {

				//setContent();
				((BeanItemContainer<OutTxn>) ((GeneratedPropertyContainer) ((Grid) vlTrxnTable
						.getComponent(0)).getContainerDataSource())
						.getWrappedContainer()).removeAllContainerFilters();

			}

		});
	} */
	
	
	private void doFilterByDate(
			BeanItemContainer<OutTxn> container, DateField dFStart,
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
		log.debug( "Date: " + strSDate );

		String strTDate = sdf.format(tDate);

		container.removeContainerFilters("date");

		Between fBtn = new Between("date", strSDate, strTDate);
		container.addContainerFilter(fBtn);

	}
	
	
	private long getCurrentUserId(){
		return ( long ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USER_ID );
	}
	
	private String getCurrentUserSession(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.SESSION_VAR );
	}


	
	


}
