package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getplaces;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Placesdetails;
import com.lonestarcell.mtn.design.admin.DPlacesUIDesign;
import com.lonestarcell.mtn.design.agent.DTableCtrlsUIDesign;
import com.lonestarcell.mtn.model.agent.MBookingMan;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.converter.Converter;
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

public class DPlacesUI extends DPlacesUIDesign implements DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;

	public DPlacesUI() {
		init(null);
	}

	@Override
	public void attachCommandListeners() {

		// attachBtnChangeProfile();
		// attachChkUsername();
		attachBtncancel();
		// attachChkPass();
		// attachBtnAdd();

	}

	/*
	 * private void attachBtnAdd() {
	 * 
	 * this.btnAdd.addClickListener(new ClickListener() {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public void buttonClick(ClickEvent event) {
	 * 
	 * tFNewUsername.setComponentError(null);
	 * tFCurrentPass.setComponentError(null); tFNewPass.setComponentError(null);
	 * tFConfirmNewPass.setComponentError(null);
	 * 
	 * String newUsername = null; if (chkUsername.getValue()) { newUsername =
	 * tFNewUsername.getValue(); if (newUsername == null ||
	 * newUsername.trim().isEmpty()) { tFNewUsername .setComponentError(new
	 * UserError( "Username required. Or uncheck username checkbox")); return; }
	 * 
	 * if (newUsername.trim().length() < 4 || newUsername.trim().length() > 15)
	 * { tFNewUsername .setComponentError(new UserError(
	 * "Username should be between 5 and 15 characters.")); return; } }
	 * 
	 * String currentPass = null; String newPass = null; currentPass =
	 * tFCurrentPass.getValue(); if (currentPass == null ||
	 * currentPass.trim().isEmpty()) { tFCurrentPass.setComponentError(new
	 * UserError( "Current password required.")); return; }
	 * 
	 * if (chkPass.getValue()) {
	 * 
	 * newPass = tFNewPass.getValue();
	 * 
	 * if (newPass == null || newPass.trim().isEmpty()) { tFNewPass
	 * .setComponentError(new UserError(
	 * "New password required. Or uncheck password checkbox.")); return; }
	 * 
	 * if (newPass.trim().length() < 4 || newPass.trim().length() > 15) {
	 * tFNewPass .setComponentError(new UserError(
	 * "New password should be between 8 and 15 characters.")); return; }
	 * 
	 * String confirmNewPass = tFConfirmNewPass.getValue();
	 * 
	 * if (confirmNewPass == null || confirmNewPass.trim().isEmpty()) {
	 * tFConfirmNewPass .setComponentError(new UserError(
	 * "Confirm New password field value required. Or uncheck password checkbox."
	 * ));
	 * 
	 * return; }
	 * 
	 * if (!confirmNewPass.trim().equals(newPass.trim())) { tFConfirmNewPass
	 * .setComponentError(new UserError(
	 * "Confirm new password field value not matching new password.")); return;
	 * }
	 * 
	 * }
	 * 
	 * Updateusercreds user = new Updateusercreds(); try {
	 * 
	 * user.setPass(currentPass); user.setNewUsername(newUsername);
	 * user.setNewPass(newPass);
	 * 
	 * Updatedusercreds update = MUserMan .updateUserProfileDetails(user); if
	 * (update == null) { lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none"); lbErrorMsg
	 * .setValue("Oops... something is wrong. Please try again"); return; }
	 * 
	 * if (!update.getResponseCode().equals("01")) {
	 * 
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none");
	 * lbErrorMsg.setValue(update.getResponseMsg()); return; }
	 * 
	 * lbNormalMsg.removeStyleName("sn-display-none");
	 * lbErrorMsg.addStyleName("sn-display-none");
	 * lbNormalMsg.setValue(update.getResponseMsg());
	 * 
	 * if (chkUsername.getValue()) { UI.getCurrent() .getSession()
	 * .setAttribute(DLoginUIController.USERNAME, update.getNewUsername());
	 * lbUN.setValue(update.getNewUsername()); }
	 * 
	 * tFCurrentPass.clear(); tFNewUsername.clear(); tFNewPass.clear();
	 * tFConfirmNewPass.clear();
	 * 
	 * } catch (RemoteException | IllegalAccessException |
	 * DataServiceFaultException e) {
	 * lbErrorMsg.removeStyleName("sn-display-none");
	 * lbNormalMsg.addStyleName("sn-display-none"); lbErrorMsg
	 * .setValue("Oops... something is wrong. Please try again");
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * });
	 * 
	 * }
	 */

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
		processingPopup = new Window("Details of Places");
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

			Getplaces places = new HyperswiftStub.Getplaces();

			BeanItemContainer<Placesdetails> container = MBookingMan
					.getPlaces(places);
			Grid grid = new Grid();
			// grid.setFrozenColumnCount(3);
			grid.addStyleName("small-grid");
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

			grid.setColumnOrder("actions", "placeName", "placeAbbrv",
					"addedBy", "dateAdded", "lastUpdated");

			grid.getColumn("placeId").setHidden(true);
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

			this.cGridPlaces.addComponent(grid);
			this.cGridPlaces.setHeightUndefined();
			this.cGridPlaces
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
			this.btnEdit.setCaption("Edit Place");

		}

	}

}
