package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Expirepass;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Expireuserpass;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getusers;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Lockunlock;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Lockunlockuser;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Logout;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.LogoutResponsedetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Paymenttransaction;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Users;
import com.lonestarcell.mtn.design.admin.DTableCtrlsUIDesign;
import com.lonestarcell.mtn.design.admin.DUsersUIDesign;
import com.lonestarcell.mtn.model.admin.MUserMan;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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

import de.datenhahn.vaadin.componentrenderer.ComponentCellKeyExtension;
import de.datenhahn.vaadin.componentrenderer.ComponentRenderer;

public class DUsersxUI extends DManUI {

	DUsersxUI(DManUIController duic) {
		System.out.println("DDashboardUI Contructor called.");
		init(duic);
	}

	private class DReportsUIController extends DUsersUIDesign implements
			DUIControllable {

		private static final long serialVersionUID = 1L;

		DReportsUIController() {
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
					((BeanItemContainer<Paymenttransaction>) ((GeneratedPropertyContainer) ((Grid) vlTrxnTable
							.getComponent(0)).getContainerDataSource())
							.getWrappedContainer()).removeAllContainerFilters();

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

							} else {

								dFStartDate.removeStyleName("sn-invisible");
								dFLastDate.removeStyleName("sn-invisible");
								btnFilter.removeStyleName("sn-invisible");
							}

						}

					});
		}

		private void setContent() {

			// DEFAULT UI STATE

			dFStartDate.addStyleName("sn-invisible");
			dFLastDate.addStyleName("sn-invisible");
			btnFilter.addStyleName("sn-invisible");
			chkDateFilter.setValue(false);

			this.btnReload.setIcon(FontAwesome.REFRESH);

			try {

				Getusers users = new Getusers();
				BeanItemContainer<Users> container = MUserMan.getUsers(users);

				GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(
						container);

				gpc.addGeneratedProperty("actions",
						new PropertyValueGenerator<Actions>() {

							/**
				 * 
				 */
							private static final long serialVersionUID = 1L;

							@Override
							public Actions getValue(Item item, Object itemId,
									Object propertyId) {

								Actions actions = new Actions();

								Property<?> p = item.getItemProperty("status");
								actions.setEnableButtonState(p.getValue()
										.toString());
								actions.setOpsBtns(item);
								actions.attachBtnListeners(item);

								return actions;

							}

							@Override
							public Class<Actions> getType() {
								return Actions.class;
							}

						});

				Grid grid = new Grid();
				ComponentCellKeyExtension.extend(grid);
				// grid.setFrozenColumnCount(3);
				grid.setSelectionMode(SelectionMode.MULTI);
				grid.setSizeUndefined();
				grid.setWidth("100%");

				grid.setContainerDataSource(gpc);

				grid.setColumnOrder("actions", "ousername", "email",
						"fullname", "status", "profile_name", "dateAdded",
						"lastLogin");
				grid.addHeaderRowAt(1);

				grid.getColumn("actions").setRenderer(new ComponentRenderer());

				prepareGridHeader(grid, "ousername", "Username", true);
				prepareGridHeader(grid, "email", "Email", true);
				prepareGridHeader(grid, "fullname", "Full Names", true);
				prepareGridHeader(grid, "status", "User Status", true);
				prepareGridHeader(grid, "profile_name", "Profile", true);
				prepareGridHeader(grid, "dateAdded", "Date Created", true);
				prepareGridHeader(grid, "lastLogin", "Last Login", true);

				grid.getColumn("changePassword").setHidden(true);
				grid.getColumn("userSession").setHidden(true);

				grid.getColumn("status").setConverter(
						new Converter<String, String>() {

							/**
				 * 
				 */
							private static final long serialVersionUID = 1L;

							@Override
							public String convertToModel(String value,
									Class<? extends String> targetType,
									Locale locale)
									throws com.vaadin.data.util.converter.Converter.ConversionException {
								if (value.equals("Enabled"))
									return "1";
								return "0";
							}

							@Override
							public String convertToPresentation(String value,
									Class<? extends String> targetType,
									Locale locale)
									throws com.vaadin.data.util.converter.Converter.ConversionException {
								if (value.equals("1"))
									return "Enabled";
								return "Locked";
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

				grid.getColumn("dateAdded").setConverter(new DateConverter());
				grid.getColumn("lastLogin").setConverter(
						new DateTimeConverter());

				grid.setFrozenColumnCount(1);
				grid.getColumn("actions").setRenderer(new ComponentRenderer());

				this.vlTrxnTable.addComponent(grid);
				this.vlTrxnTable.setHeightUndefined();

			} catch (Exception e) {
				Notification.show(
						"Error occured while loading data. Please try again!",
						Notification.Type.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		@Override
		public void init(DManUIController duic) {

			attachCommandListeners();
			setContent();

		}

	}

	class btnrenderer implements Converter<Button, String> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String convertToModel(Button value,
				Class<? extends String> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {

			return "none";
		}

		@Override
		public Button convertToPresentation(String value,
				Class<? extends Button> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {

			return new Button("xxx");
		}

		@Override
		public Class<String> getModelType() {

			return String.class;
		}

		@Override
		public Class<Button> getPresentationType() {

			return Button.class;
		}

	}

	@Override
	public void setContent() {
		swap(duic, new DReportsUIController());

	}

	@Override
	public void init(DManUIController duic) {
		// setDUI(duic);
		setContent();

	}

	public class Person {
		private String name;
		private Integer age;

		public void setName(String s) {
			this.name = s;
		}

		public void setAge(Integer s) {
			this.age = s;
		}

		public String getName() {
			return name;
		}

		public Integer getAge() {
			return age;
		}
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

		GeneratedPropertyContainer c = (GeneratedPropertyContainer) grid
				.getContainerDataSource();

		BeanItemContainer<Paymenttransaction> container = (BeanItemContainer<Paymenttransaction>) c
				.getWrappedContainer();
		Column col = grid.getColumn(itemId);
		col.setHeaderCaption(columnName);

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

		container.removeContainerFilters("dateAdded");

		Between fBtn = new Between("dateAdded", strSDate, strTDate);
		container.addContainerFilter(fBtn);

	}

	public class Actions extends DTableCtrlsUIDesign {

		private static final long serialVersionUID = 1L;

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

		}

		public void attachBtnListeners(final Item data) {
			if (this.btnExpirePass.isEnabled()) {
				this.btnExpirePass.addClickListener(new ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {

						if (data == null)
							throw new IllegalStateException("No data passed");

						Expirepass user = new Expirepass();
						user.setExpirepassUsername(data
								.getItemProperty("ousername").getValue()
								.toString());
						try {
							Expireuserpass response = MUserMan.expirepass(user);

							if (response == null) {
								Notification
										.show("Error occured during operation. Please try again",
												Notification.Type.ERROR_MESSAGE);
								return;
							}

							if (!response.getResponseCode().equals("01")) {
								Notification.show(response.getResponseMsg(),
										Notification.Type.ERROR_MESSAGE);
								return;
							}

							Notification
									.show("User password successfully expired!");
							new DUsersxUI(duic);

						} catch (Exception e) {
							Notification
									.show("Error occured during operation. Please try again",
											Notification.Type.ERROR_MESSAGE);
							e.printStackTrace();
						}

					}

				});
			}

			this.btnEdit.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					new DNewUserFormUI(duic, data);

				}

			});

			if (this.btnLogout.isVisible())
				this.btnLogout.addClickListener(new ClickListener() {

					/**
				 * 
				 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {

						if (data == null)
							throw new IllegalStateException("No data passed");

						Logout user = new Logout();
						user.setLogoutUsername(data
								.getItemProperty("ousername").getValue()
								.toString());
						try {
							LogoutResponsedetails response = MUserMan
									.logout(user);

							if (response == null) {
								Notification
										.show("Error occured during operation. Please try again",
												Notification.Type.ERROR_MESSAGE);
								return;
							}

							if (!response.getResponseCode().equals("01")) {
								Notification.show(response.getResponseMsg(),
										Notification.Type.ERROR_MESSAGE);
								return;
							}

							Notification.show("User successfully logged out!");
							new DUsersxUI(duic);

						} catch (Exception e) {
							Notification
									.show("Error occured during operation. Please try again",
											Notification.Type.ERROR_MESSAGE);
							e.printStackTrace();
						}

					}

				});

			this.btnEnable.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					if (data == null)
						throw new IllegalStateException("No data passed");

					Lockunlockuser user = new Lockunlockuser();
					user.setLockunlockUsername(data
							.getItemProperty("ousername").getValue().toString());

					String status = data.getItemProperty("status").getValue()
							.toString();
					if (status.equals("1"))
						user.setUStatus("0");
					else
						user.setUStatus("1");

					try {

						Lockunlock response = MUserMan.lockunlockuser(user);

						if (response == null) {
							Notification
									.show("Error occured during operation. Please try again",
											Notification.Type.ERROR_MESSAGE);
							return;
						}

						if (!response.getResponseCode().equals("01")) {
							Notification.show(response.getResponseMsg(),
									Notification.Type.ERROR_MESSAGE);
							return;
						}

						if (status.equals("1"))
							Notification
									.show("User Account successfully locked!");
						else
							Notification.show("User successfully unlocked!");

						new DUsersxUI(duic);

					} catch (Exception e) {
						Notification
								.show("Error occured during operation. Please try again",
										Notification.Type.ERROR_MESSAGE);
						e.printStackTrace();
					}

				}

			});
		}

		public void setOpsBtns(Item data) {
			String cpass = data.getItemProperty("changePassword").getValue()
					.toString();
			if (cpass.equals("1")) {
				this.btnExpirePass.setVisible(false);
			}

			String csession = data.getItemProperty("userSession").getValue()
					.toString();
			if (csession.equals("-1"))
				this.btnLogout.setVisible(false);
		}

		public void setEnableButtonState(String s) {

			if (s == null)
				throw new IllegalArgumentException("Status should not be null");

			if (s.equals("1")) {
				this.btnEnable.setCaption("Lock");
				this.btnEnable.setIcon(FontAwesome.LOCK);
				this.btnEnable.setDescription("Lock/Disable user");
				return;
			} else if (s.equals("0")) {
				this.btnEnable.setCaption("Unlock");
				this.btnEnable.setIcon(FontAwesome.UNLOCK);
				this.btnEnable.setDescription("Unlock/Enable user");
				return;
			} else {
				throw new IllegalArgumentException(
						"Status should be either 0 or 1. But value of: " + s
								+ " was seen");
			}
		}

	}

}
