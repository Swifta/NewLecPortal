package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Addnewroute;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getplaces;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getroutes;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newroutedata;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Placesdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Routesdetails;
import com.lonestarcell.mtn.design.admin.DRoutesUIDesign;
import com.lonestarcell.mtn.design.agent.DTableCtrlsUIDesign;
import com.lonestarcell.mtn.model.agent.MBookingMan;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.PopupVisibilityEvent;
import com.vaadin.ui.PopupView.PopupVisibilityListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DRoutesUI extends DRoutesUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;

	public DRoutesUI() {
		init(null);
	}

	@Override
	public void attachCommandListeners() {

		attachBtncancel();
		attachBtnAdd();
		attachComboOrigin();
		attachComboDest();

	}

	private void attachComboDest() {

		this.comboDestination.addFocusListener(new FocusListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				comboDestination.setComponentError(null);
			}

		});

	}

	@SuppressWarnings("unchecked")
	private void attachComboOrigin() {

		comboOrigin.removeAllItems();
		comboDestination.removeAllItems();
		comboOrigin.setImmediate(true);
		comboDestination.setImmediate(true);

		this.comboOrigin.addFocusListener(new FocusListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				comboOrigin.setComponentError(null);

				Collection<?> dest = comboDestination.getItemIds();
				Collection<?> origin = comboOrigin.getItemIds();

				if (origin.size() == 0) {

					System.err.println("Retrieving Origin places...");

					Getplaces places = new HyperswiftStub.Getplaces();

					try {
						BeanItemContainer<Placesdetails> container = MBookingMan
								.getPlaces(places);
						comboOrigin.setContainerDataSource(container);
						comboOrigin.setItemCaptionPropertyId("placeName");

					} catch (Exception e) {
						e.printStackTrace();
						comboOrigin.setComponentError(new UserError(
								"Oops... Error occured while loading places!"));
					}

					if (dest.size() == 0) {

						System.err.println("Retrieving Dest places...");

						Getplaces destPlaces = new HyperswiftStub.Getplaces();

						try {
							BeanItemContainer<Placesdetails> container = MBookingMan
									.getPlaces(destPlaces);
							comboDestination.setContainerDataSource(container);
							comboDestination
									.setItemCaptionPropertyId("placeName");

						} catch (Exception e) {
							e.printStackTrace();
							comboDestination
									.setComponentError(new UserError(
											"Oops... Error occured while loading places!"));
						}
					}

				}

			}

		});

		this.comboOrigin.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {

				Object itemId = event.getProperty().getValue();
				if (itemId == null) {
					if (comboDestination.getContainerDataSource().size() != 0) {

						BeanItemContainer<Placesdetails> c = (BeanItemContainer<Placesdetails>) comboDestination
								.getContainerDataSource();
						c.removeAllContainerFilters();
					}

				} else {

					Collection<?> dest = comboDestination.getItemIds();

					System.err.println("Data filter reset occuring...");
					String place = comboOrigin.getContainerDataSource()
							.getItem(itemId).getItemProperty("placeName")
							.getValue().toString();

					BeanItemContainer<Placesdetails> c = (BeanItemContainer<Placesdetails>) comboDestination
							.getContainerDataSource();

					c.removeContainerFilters("placeName");

					System.err.println("Place: " + place);
					System.out.println("Dest(s): " + c.size());

					c.addContainerFilter(new Not(new SimpleStringFilter(
							"placeName", place, true, false)));

					System.err.println("New c: " + c.size());

					comboDestination.setContainerDataSource(c);

					System.err.println("New Dest(s): "
							+ comboDestination.getContainerDataSource().size());

					return;

				}

			}

		});
	}

	private void attachBtnAdd() {

		this.btnAdd.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				comboOrigin.setComponentError(null);
				comboDestination.setComponentError(null);
				tFOWUSD.setComponentError(null);
				tFRTNUSD.setComponentError(null);

				String origin = null;
				String dest = null;

				String owUSD = null;
				String rtnUSD = null;

				Object obj = comboOrigin.getValue();

				if (obj == null) {
					comboOrigin.setComponentError(new UserError(
							"Area of Origin required."));
					return;
				}

				obj = comboDestination.getValue();

				if (obj == null) {
					comboDestination.setComponentError(new UserError(
							"Area of Destination required."));
					return;
				}

				obj = tFOWUSD.getValue();

				if (obj == null) {
					tFOWUSD.setComponentError(new UserError("Field required."));
					return;
				}

				owUSD = tFOWUSD.getValue();
				Double dOW = null;
				try {
					dOW = Double.parseDouble(owUSD);
				} catch (Exception e) {
					tFOWUSD.setComponentError(new UserError(
							"Invalid cost value. Remove any invalid money character"));
					return;
				}

				rtnUSD = tFRTNUSD.getValue();
				Double dRTN = null;
				if (rtnUSD != null)
					try {
						dRTN = Double.parseDouble(rtnUSD);
					} catch (Exception e) {
						tFRTNUSD.setComponentError(new UserError(
								"Invalid cost value. Remove any invalid money character"));
						return;
					}

				if (dOW > dRTN) {
					tFRTNUSD.setComponentError(new UserError(
							"Invalid Return cost value. Return cost should be greater than One Way trip cost values"));

					return;
				}

				origin = comboOrigin.getValue().toString().trim();
				dest = comboDestination.getValue().toString().trim();

				Addnewroute route = new HyperswiftStub.Addnewroute();
				try {

					route.setOriginId(origin);
					route.setDestId(dest);
					route.setCostOW(owUSD);
					route.setCostRTN(rtnUSD);

					Newroutedata rResponse = MBookingMan.addNewRoute(route);
					if (rResponse == null) {
						lbErrorMsg.removeStyleName("sn-display-none");
						lbNormalMsg.addStyleName("sn-display-none");
						lbErrorMsg
								.setValue("Oops... something is wrong. Please try again");
						return;
					}

					if (!rResponse.getResponseCode().equals("01")) {

						lbErrorMsg.removeStyleName("sn-display-none");
						lbNormalMsg.addStyleName("sn-display-none");
						lbErrorMsg.setValue(rResponse.getResponseMsg());
						return;
					}

					lbNormalMsg.removeStyleName("sn-display-none");
					lbErrorMsg.addStyleName("sn-display-none");
					lbNormalMsg.setValue(rResponse.getResponseMsg());

					comboOrigin.clear();
					comboDestination.clear();
					tFOWUSD.clear();
					tFRTNUSD.clear();

				} catch (Exception e) {
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					lbErrorMsg
							.setValue("Oops... something is wrong. Please try again");
					e.printStackTrace();
				}

			}

		});

	}

	private void attachBtncancel() {
		this.btnCancel.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				processingPopup.close();

			}

		});
	}

	@Override
	public void init(DManUIController duic) {

		attachCommandListeners();
		showPopup();
		setDefaultUIState();
		setContent();

	}

	private void showPopup() {
		processingPopup = new Window("Details of Routes");
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable(false);
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		processingPopup.setSizeUndefined();
		// processingPopup.setSizeFull();
		UI.getCurrent().addWindow(processingPopup);

	}

	public static void format() {
		String msisdn = "256778485869";
		String cCode = msisdn.substring(0, 3);

		String cProviderCode = msisdn.substring(3, 6);

		String no = msisdn.substring(6, msisdn.length());

		String fNo = "+" + cCode + " " + cProviderCode + " " + no;
		System.out.println("Fomarted no: " + fNo);

	}

	public static void main(String[] args) {
		/*
		 * new DPaymentStateUIController("", "").new
		 * TransactionStatusWorkerThread().start();
		 */
		format();

	}

	private void setDefaultUIState() {
		// this.cEditProfileForm.setVisible(false);
		// this.btnChangeProfile.setVisible(false);

		this.lbErrorMsg.addStyleName("sn-display-none");

		// chkUsername.setValue(true);
		// chkPass.setValue(true);

	}

	private void setContent() {
		loadGridData();

	}

	private void loadGridData() {
		try {

			Getroutes routes = new HyperswiftStub.Getroutes();

			BeanItemContainer<Routesdetails> container = MBookingMan
					.getRoutes(routes);

			Grid grid = new Grid();
			grid.setSelectionMode(SelectionMode.MULTI);
			grid.setHeightUndefined();
			grid.setWidth("830px");

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
							actions.setEditBtnState();
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

			grid.setColumnOrder("actions", "URoute", "originPlaceName",
					"destPlaceName", "addedBy", "dateAdded", "lastUpdated");

			grid.getColumn("URoute").setHeaderCaption("Route");
			grid.getColumn("originPlaceName").setHeaderCaption("Origin");
			grid.getColumn("destPlaceName").setHeaderCaption("Destination");

			grid.getColumn("routeId").setHidden(true);
			grid.getColumn("dateAdded").setConverter(new DateConverter());
			grid.getColumn("lastUpdated").setConverter(new DateConverter());

			grid.setFrozenColumnCount(2);
			grid.addHeaderRowAt(1);

			/*
			 * prepareGridHeader(grid, "bookingRef", "Booking Ref", true);
			 * prepareGridHeader(grid, "bookingAgent", "Booking Agent", true);
			 * prepareGridHeader(grid, "clientName", "Client Name", true);
			 * prepareGridHeader(grid, "cost", "Amount [USD]", false);
			 * prepareGridHeader(grid, "status", "Payment Status", true);
			 * prepareGridHeader(grid, "dateAdded", "Date Logged", true);
			 * prepareGridHeader(grid, "flightDate", "Date of Flight", true);
			 */

			this.cGridRoutes.addComponent(grid);
			this.cGridRoutes.setHeightUndefined();
			this.cGridRoutes
					.setMargin(new MarginInfo(false, true, false, true));
			// this.cGridPlaces.setWidth("100%");

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

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

		}

		public void setRowData(Item item) {
			this.rowData = item;
		}

		public Item getRowData() {
			return this.rowData;
		}

		private void attachBtnEdit() {
			this.btnEdit.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					// new DBookingFormUI(duic, rowData);

				}

			});
		}

		public void setGrid(Grid grid) {
			this.grid = grid;

		}

		public Grid getGrid() {
			return this.grid;
		}

		public void setEditBtnState() {
			this.btnCancel.setVisible(false);
			this.btnEdit.setVisible(true);
			this.btnEdit.setCaption("Edit Route");

		}

	}

}
