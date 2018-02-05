package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.bean.OutUserDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.AllRowsActionsUIUser;
import com.lonestarcell.mtn.controller.util.DPgExportLimitUIUser;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.design.admin.DUserStateUIDesign;
import com.lonestarcell.mtn.model.admin.MUser;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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

public class DUserStateUI extends DUserStateUIDesign implements
		DUserUIInitializable<DUserUI, DUserStateUI>, DUIControllable {

	private static final long serialVersionUID = 1L;

	private DUserUI ancestor;
	private Logger log = LogManager.getLogger();
	private BeanItemContainer<OutUser> beanItemContainer;

	private MUser mTxn;
	private InTxn inTxn;
	protected Set< Short > permSet;

	private ApplicationContext springAppContext;

	DUserStateUI(DUserUI a) {
		this.setSpringAppContext(a.getSpringAppContext());
		this.setPermSet( a.getPermSet() );
		init(a);
	}

	
	public Set<Short> getPermSet() {
		return permSet;
	}


	@SuppressWarnings("unchecked")
	public void setPermSet(Set<Short> permSet) {
		if( permSet == null )
			this.permSet = UI.getCurrent().getSession().getAttribute( Set.class );
		else
			this.permSet = permSet;
		
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
		// this.lbDataTitle.setValue("Today");
	}

	@Override
	public void setContent() {

		setHeader();
		setFooter();
		setBeanItemContainer(new BeanItemContainer<>(OutUser.class));

		swap(this);
		attachCommandListeners();
		this.vlTrxnTable.addComponent(loadGridData(beanItemContainer));
		this.vlTrxnTable.setHeightUndefined();
		// this.vlTrxnTable.setWidth( "1150px");
		this.vlTrxnTable.setWidth("100%");

	}

	public BeanItemContainer<OutUser> getBeanItemContainer() {
		return beanItemContainer;
	}

	public void setBeanItemContainer(
			BeanItemContainer<OutUser> beanItemContainer) {
		this.beanItemContainer = beanItemContainer;
	}

	/*
	 * @Override public void swap(Component cuid) {
	 * //ancestor.setHeight("100%"); //cuid.setHeight("100%");
	 * 
	 * 
	 * //ancestor.addStyleName("sn-p"); //cuid.addStyleName("sn-c");
	 * 
	 * cuid.setHeight("100%");
	 * ancestor.getAncestorUI().getcMainContent().setHeight( "100%" );
	 * //ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
	 * ancestor.setHeight( "100%" );
	 * 
	 * 
	 * log.debug( "Users height: "+cuid.getHeight() );
	 * 
	 * ancestor.swap( cuid );
	 * 
	 * }
	 */

	// New swap size config.
	@Override
	public void swap(Component cuid) {
		// ancestor.setHeight("100%");
		// cuid.setHeight("100%");

		// ancestor.addStyleName("sn-p");
		// cuid.addStyleName("sn-c");

		cuid.setHeight("100%");

		// TODO testing max content width
		cuid.setWidth("100%");
		((VerticalLayout) ((Panel) cuid).getContent()).setWidth("100%");
		// VerticalLayout v = null;

		ancestor.getAncestorUI().getcMainContent().setHeight("100%");
		// ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
		ancestor.setHeight("100%");

		log.debug("Users height: " + cuid.getHeight());

		ancestor.swap(cuid);

	}

	@Override
	public void init(DUserUI a) {
		mTxn = new MUser(getCurrentUserId(), getCurrentUserSession(),
				getSpringAppContext());
		inTxn = new InTxn();
		setInDate(inTxn, 1);

		// Scale left footer by user grid container height.
		a.getRightContent().setHeight("100%");

		setAncestorUI(a);
		setContent();

	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub

	}

	@Override
	public DUserUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DUserUI a) {
		this.ancestor = a;

	}

	@Override
	public DUserStateUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DUserStateUI p) {
		// TODO Auto-generated method stub

	}

	public Grid loadGridData(BeanItemContainer<OutUser> beanItemContainer) {
		try {

			Grid grid = new Grid();
			grid.addStyleName("sn-small-grid");
			grid.setSelectionMode(SelectionMode.MULTI);
			grid.setHeight("600px");
			grid.setWidth("100%");

			In in = new In();

			BData<InTxn> inBData = new BData<>();

			
			// Set OutTxnMeta
			OutTxnMeta outTxnMeta = new OutTxnMeta();
			outTxnMeta
					.setTotalRevenue(new ObjectProperty<String>("0", String.class));
			outTxnMeta
					.setTotalRecord(new ObjectProperty<String>("0", String.class));
			inTxn.setMeta( outTxnMeta );
			
			
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			String tDate = sdf.format(cal.getTime());
			log.debug("To: " + tDate);

			inTxn.settDate(tDate);
			inTxn.setPage(1);

			cal.add(Calendar.DAY_OF_MONTH, -200);
			String fDate = sdf.format(cal.getTime());
			log.debug("From: " + fDate);

			inTxn.setPermSet( this.getPermSet() );
			inTxn.setfDate(fDate);

			inBData.setData(inTxn);
			in.setData(inBData);

			// TODO validate response

			mTxn.setUsers(in, beanItemContainer);

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
									new RowActionsUI(mTxn, grid, item));
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

			// username, email, org, userStatus, profile, lastLogin, date,
			// actions

			// userId, profileId, changePass, userSession,

			grid.setColumnOrder("username", "email", "org", "userStatus",
					"profile", "lastLogin", "date", "actions");

			grid.setFrozenColumnCount(2);

			HeaderRow header = grid.prependHeaderRow();
			FooterRow footer = grid.prependFooterRow();
			HeaderRow headerTextFilter = grid.addHeaderRowAt(2);

			// Header config
			HeaderCell dateFilterCellH = header.join("username", "email",
					"org", "userStatus", "profile", "lastLogin", "date",
					"actions");
			PaginationUIController pageC = new PaginationUIController();

			AllRowsActionsUIUser allRowsActionsUIUse = new AllRowsActionsUIUser(
					mTxn, grid, in, true, true, pageC);
			dateFilterCellH.setComponent(allRowsActionsUIUse);

			header.setStyleName("sn-date-filter-row");
			dateFilterCellH
					.setStyleName("sn-no-border-right sn-no-border-left");

			// Footer config
			FooterCell dateFilterCellF = footer.join("username", "email",
					"org", "userStatus", "profile", "lastLogin", "date",
					"actions");

			dateFilterCellF.setComponent(new AllRowsActionsUIUser(mTxn, grid,
					in, false, false, pageC));

			// Init pagination controller after both header and footer have been
			// set.
			pageC.init();

			footer.setStyleName("sn-date-filter-row");
			dateFilterCellF
					.setStyleName("sn-no-border-right sn-no-border-left");

			PopupView v = new PopupView("...",
					new MultiRowActionsUI(mTxn, grid));

			HeaderCell cellBulkActions = headerTextFilter.getCell("actions");
			v.setWidth("100%");
			v.setHeight("100%");

			cellBulkActions.setComponent(v);

			grid.getColumn("actions").setWidth(50);
			HeaderRow headerColumnNames = grid.getHeaderRow(1);

			HeaderCell cellActions = headerColumnNames.getCell("actions");

			cellActions.setStyleName("sn-cell-actions");
			cellBulkActions.setStyleName("sn-cell-actions");

			// Hide unnecessary bean fields

			grid.removeColumn("userId");
			grid.removeColumn("profileId");
			grid.removeColumn("changePass");
			grid.removeColumn("userSession");

			// Add search field

			allRowsActionsUIUse.prepareGridHeader(grid, "username", "Username",
					true);
			allRowsActionsUIUse.prepareGridHeader(grid, "email", "Email", true);
			allRowsActionsUIUse.prepareGridHeader(grid, "org", "Organization",
					true);
			allRowsActionsUIUse.prepareGridHeader(grid, "userStatus", "Status",
					true);
			allRowsActionsUIUse.prepareGridHeader(grid, "profile", "Profile",
					true);
			allRowsActionsUIUse.prepareGridHeader(grid, "lastLogin",
					"Last Login", false);
			allRowsActionsUIUse.prepareGridHeader(grid, "date", "Added On",
					false);
			allRowsActionsUIUse
					.prepareGridHeader(grid, "actions", "...", false);

			// Set column widths

			grid.getColumn("userStatus").setWidth(120).setResizable(false);
			grid.getColumn("profile").setWidth(125);
			grid.getColumn("username").setWidth(125);
			grid.getColumn("org").setWidth(125);
			grid.getColumn("email").setWidth(215);

			// grid.addStyleName( "sn-small-grid" );

			// grid.setSelectionMode(SelectionMode.MULTI);
			// grid.setHeight( "500px" );
			// grid.setWidth( "100%" );

			Notification.show("Data loaded successfully.",
					Notification.Type.HUMANIZED_MESSAGE);

			return grid;

		} catch (Exception e) {

			Notification.show(
					"Error occured while loading data. Please try again!",
					Notification.Type.ERROR_MESSAGE);
			e.printStackTrace();

		}

		return new Grid();
	}

	public class RowActionsUI extends MultiRowActionsUI {

		private static final long serialVersionUID = 1L;
		private Button btnDetails;
		private Item recordDetails;

		RowActionsUI(MUser mTxn, Grid grid, Item record) {
			super(mTxn, grid, record);
			init();
		}
		
		private boolean isAllowedFeature( Button btn, Short permId ){
			
			if( !permSet.contains( permId )){
				btn.setVisible( false );
				btn.setEnabled( false );
				return false;
				
			} else {
				btn.setVisible( true );
				btn.setEnabled( true );
				return true;
			}
			
		}

		public Item getRecordDetails() {
			return recordDetails;
		}

		public void setRecordDetails(Item record) {

			String username = (String) record.getItemProperty("username")
					.getValue();
			log.debug(" In setRecordDetails username: " + username);
			this.recordDetails = record;

		}

		@Override
		protected void init() {
			setContent();
			attachCommandListeners();
		}

		@Override
		protected void setContent() {

			this.addStyleName("sn-more-drop-down");
			this.setSizeUndefined();
			this.setMargin(true);
			this.setSpacing(true);

			btnDetails = new Button();
			btnRefresh = new Button();

			btnDetails.setDescription("More details");
			btnRefresh.setDescription("Refresh record");

			btnDetails.addStyleName("borderless icon-align-top");
			btnRefresh.addStyleName("borderless icon-align-top");

			btnDetails.setIcon(FontAwesome.ALIGN_RIGHT);
			btnRefresh.setIcon(FontAwesome.REFRESH);

			btnEnable = new Button();
			btnEnable.setDescription("Activate");
			btnEnable.addStyleName("borderless icon-align-top");
			btnEnable.setIcon(FontAwesome.UNLOCK);

			btnDisable = new Button();
			btnDisable.setDescription("Block");
			btnDisable.addStyleName("borderless icon-align-top");
			btnDisable.setIcon(FontAwesome.LOCK);

			btnExpireSession = new Button();
			btnExpireSession.setDescription("Expire login session");
			btnExpireSession.addStyleName("borderless icon-align-top");
			btnExpireSession.setIcon(FontAwesome.UNLINK);

			btnExpirePass = new Button();
			btnExpirePass.setDescription("Expire password");
			btnExpirePass.addStyleName("borderless icon-align-top");
			btnExpirePass.setIcon(FontAwesome.USER_TIMES);

			this.addComponent(btnDetails);
			this.addComponent(btnDisable);
			this.addComponent(btnExpirePass);
			this.addComponent(btnExpireSession);
			this.addComponent(btnEnable);
			this.addComponent(btnRefresh);

			format();

		}

		@Override
		public void attachCommandListeners() {

			this.attachBtnDetails();
			this.attachBtnRefresh();
			this.attachBtnUserActivate();
			this.attachBtnUserBlock();
			this.attachBtnUserExpirePassword();
			this.attachBtnUserExpireSession();

		}

		protected void attachBtnUserExpireSession() {
			
			if( !isAllowedFeature( btnExpireSession, EnumPermission.USER_CANCEL_LOGIN_SESSION.val) )
				return;
			
			this.btnExpireSession.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					expireSessionMultiHandler();

				}

			});
		}

		protected void attachBtnUserExpirePassword() {
			
			if( !isAllowedFeature( btnExpirePass, EnumPermission.USER_EXPIRE_PASSWORD.val) )
				return;
			
			this.btnExpirePass.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					expirePassMultiHandler();
				}

			});
		}

		protected void attachBtnUserActivate() {
			
			if( !isAllowedFeature( btnEnable, EnumPermission.USER_ACTIVATE_BLOCK.val) )
				return;
			this.btnEnable.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					activateMultiHandler();
				}

			});
		}

		protected void attachBtnUserBlock() {
			
			if( !isAllowedFeature( btnDisable, EnumPermission.USER_ACTIVATE_BLOCK.val) )
				return;
			
			this.btnDisable.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					blockMultiHandler();

				}

			});
		}

		protected void attachBtnRefresh() {
			this.btnRefresh.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					Out out = refreshMultiUserRecord();

					if (out.getStatusCode() == 1)
						Notification.show(out.getMsg(),
								Notification.Type.HUMANIZED_MESSAGE);
					else if (out.getStatusCode() == 2)
						Notification.show(out.getMsg(),
								Notification.Type.WARNING_MESSAGE);
					else
						Notification.show("Refresh operation failed.",
								Notification.Type.ERROR_MESSAGE);

					format();

				}

			});
		}

		private void attachBtnDetails() {
			
			if( !isAllowedFeature( btnDetails, EnumPermission.USER_VIEW_DETAILS.val) )
				return;
			
			this.btnDetails.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					if (record == null) {
						Notification.show("No record set for operaton.",
								Notification.Type.ERROR_MESSAGE);
						return;
					}

					if (isUserDetailsSet() && recordDetails != null) {
						String username = (String) recordDetails
								.getItemProperty("username").getValue();
						log.debug("Details username: " + username);
						new DUserDetailsUI(recordDetails);

					} else {
						Notification
								.show("User data error. Please try again / Contact support.",
										Notification.Type.ERROR_MESSAGE);
					}
				}

			});
		}

		private void format() {

			log.debug("format called.");

			btnEnable.setVisible(false);
			btnDisable.setVisible(false);
			btnExpirePass.setVisible(false);
			btnExpireSession.setVisible(false);

			if (getCurrentUserId() != (long) record.getItemProperty("userId")
					.getValue()) {
				if (record.getItemProperty("userStatus").getValue().toString()
						.equals("BLOCKED")) {
					btnEnable.setVisible(true);
				} else if (record.getItemProperty("userStatus").getValue()
						.toString().equals("ACTIVE")) {
					btnDisable.setVisible(true);
				}

				String changePass = record.getItemProperty("changePass")
						.getValue().toString();
				if (changePass.equals("0")
						&& !record.getItemProperty("userStatus").getValue()
								.toString().equals("REGISTERED")) {
					btnExpirePass.setVisible(true);
				}

				if (record.getItemProperty("userSession").getValue() == null
						|| record.getItemProperty("userSession").getValue()
								.toString().trim().isEmpty()) {
				} else {
					btnExpireSession.setVisible(true);
				}

			}

		}

		private boolean isUserDetailsSet() {

			InUserDetails inData = new InUserDetails();
			setAuth(inData);

			String username = record.getItemProperty("username").getValue()
					.toString();
			log.debug("Username at setting user details: " + username);

			OutUserDetails user = new OutUserDetails();
			user.setUsername(username);
			recordDetails = new BeanItem<OutUserDetails>(user,
					OutUserDetails.class);
			inData.setRecord(recordDetails);

			username = recordDetails.getItemProperty("username").getValue()
					.toString();
			log.debug("Username before fetching user details: " + username);

			BData<InUserDetails> bData = new BData<>();
			bData.setData(inData);

			In in = new In();
			in.setData(bData);

			MUserDetails mUserDetails = new MUserDetails(getCurrentUserId(),
					getCurrentUserSession());
			username = recordDetails.getItemProperty("username").getValue()
					.toString();
			log.debug("Username after fetching user details: " + username);

			username = inData.getRecord().getItemProperty("username")
					.getValue().toString();
			log.debug("Username after fetching user details [ IN ]: "
					+ username);

			Out out = mUserDetails.setUserDetails(in);
			return out.getStatusCode() == 1;

		}

	}

	private void setAuth(InUserDetails inData) {

		inData.setUsername(UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME).toString());
		inData.setUserSession(UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR).toString());

	}

	private long getCurrentUserId() {
		return (long) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USER_ID);
	}

	private String getCurrentUserSession() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR);
	}

	public class MultiRowActionsUI extends VerticalLayout implements DUIControllable {

		private static final long serialVersionUID = 1L;
		protected MUser mTxn;
		protected Grid grid;
		protected Item record;
		protected Collection<Item> records;

		protected Button btnExport;
		protected Button btnRefresh;
		protected Button btnEnable;
		protected Button btnDisable;
		protected Button btnExpireSession;
		protected Button btnExpirePass;

		MultiRowActionsUI(MUser mTxn, Grid grid) {
			this(mTxn, grid, null);
			init();
		}

		MultiRowActionsUI(MUser mTxn, Grid grid, Item record) {
			this.mTxn = mTxn;
			this.grid = grid;
			this.setRecord(record);
		}

		protected Out expirePassMultiUserRecord(Collection<Item> records) {
			return mTxn.expirePassMultiUserRecord(records);

		}

		protected Item getRecord() {
			return record;
		}

		protected void setRecord(Item record) {
			this.record = record;
			List<Item> r = new ArrayList<>(1);
			r.add(record);
			setRecords(r);
		}

		protected Collection<Item> getRecords() {
			return records;
		}

		protected void setRecords(Collection<Item> records) {
			this.records = records;
		}

		protected Out expireSessionMultiUserRecord(Collection<Item> records) {
			MUser mUserDetails = new MUser(getCurrentUserId(),
					getCurrentUserSession(), getSpringAppContext());
			return mUserDetails.expireSessionMultiUserRecord(records);

		}

		protected Out activateMultiUserRecord(Collection<Item> records) {
			return mTxn.activateMultiUserRecord(records);

		}

		protected Out blockMultiUserRecord(Collection<Item> records) {

			return mTxn.blockMultiUserRecord(records);

		}

		protected void setContent() {

			this.addStyleName("sn-more-drop-down");
			this.setSizeUndefined();
			this.setMargin(true);
			this.setSpacing(true);

			btnExport = new Button();
			btnRefresh = new Button();

			btnExport.setDescription("Export selected records");
			btnRefresh.setDescription("Refresh selected records");

			btnExport.addStyleName("borderless icon-align-top");
			btnRefresh.addStyleName("borderless icon-align-top");

			btnExport.setIcon(FontAwesome.SHARE_SQUARE_O);
			btnRefresh.setIcon(FontAwesome.REFRESH);

			this.addComponent( btnExport );
			this.addComponent(btnRefresh);

			btnEnable = new Button();
			btnEnable.setDescription("Activate");
			btnEnable.addStyleName("borderless icon-align-top");
			btnEnable.setIcon(FontAwesome.UNLOCK);

			btnDisable = new Button();
			btnDisable.setDescription("Block");
			btnDisable.addStyleName("borderless icon-align-top");
			btnDisable.setIcon(FontAwesome.LOCK);

			btnExpireSession = new Button();
			btnExpireSession.setDescription("Expire login session");
			btnExpireSession.addStyleName("borderless icon-align-top");
			btnExpireSession.setIcon(FontAwesome.UNLINK);

			btnExpirePass = new Button();
			btnExpirePass.setDescription("Expire password");
			btnExpirePass.addStyleName("borderless icon-align-top");
			btnExpirePass.setIcon(FontAwesome.USER_TIMES);

			
			this.addComponent(btnEnable);
			this.addComponent(btnDisable);
			this.addComponent(btnExpireSession);
			this.addComponent(btnExpirePass);

		}

		@Override
		public void attachCommandListeners() {
			this.attachBtnRefresh();
			this.attachBtnUserActivate();
			this.attachBtnUserBlock();
			this.attachBtnUserExpirePassword();
			this.attachBtnUserExpireSession();
			this.attachBtnExport();

		}

		protected void init() {

			// Disable data export for users.

			this.setContent();
			this.attachCommandListeners();

		}
		
		private boolean isAllowedFeature( Button btn, Short permId ){
			if( !permSet.contains( permId )){
				btn.setVisible( false );
				btn.setEnabled( false );
				return false;
				
			} else {
				btn.setVisible( true );
				btn.setEnabled( true );
				return true;
			}
			
		}

		protected void attachBtnUserExpireSession() {
			
			if( !isAllowedFeature( btnExpireSession, EnumPermission.USER_CANCEL_LOGIN_SESSION.val) )
				return;
			
			this.btnExpireSession.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					Collection<?> itemIds = grid.getSelectedRows();

					if (itemIds == null || itemIds.size() == 0) {
						Notification.show(
								"Please select at least on record to refresh.",
								Notification.Type.WARNING_MESSAGE);
						return;
					}

					Iterator<?> itr = itemIds.iterator();
					List<Item> selectedRecords = new ArrayList<>(itemIds.size());
					while (itr.hasNext()) {
						Object itemId = itr.next();
						selectedRecords.add(grid.getContainerDataSource()
								.getItem(itemId));
					}

					setRecords(selectedRecords);
					expireSessionMultiHandler();

				}

			});
		}

		protected void attachBtnUserExpirePassword() {
			
			if( !isAllowedFeature( btnExpirePass, EnumPermission.USER_EXPIRE_PASSWORD.val) )
				return;
			
			this.btnExpirePass.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					Collection<?> itemIds = grid.getSelectedRows();

					if (itemIds == null || itemIds.size() == 0) {
						Notification.show(
								"Please select at least on record to refresh.",
								Notification.Type.WARNING_MESSAGE);
						return;
					}

					Iterator<?> itr = itemIds.iterator();
					Collection<Item> selectedRecords = new ArrayList<>(itemIds
							.size());

					while (itr.hasNext()) {
						Object itemId = itr.next();
						selectedRecords.add(grid.getContainerDataSource()
								.getItem(itemId));
					}

					setRecords(selectedRecords);
					expirePassMultiHandler();

				}

			});
		}

		protected void attachBtnUserActivate() {
			
			if( !isAllowedFeature( btnEnable, EnumPermission.USER_ACTIVATE_BLOCK.val) )
				return;
			
			this.btnEnable.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					Collection<?> itemIds = grid.getSelectedRows();

					if (itemIds == null || itemIds.size() == 0) {
						Notification.show(
								"Please select at least on record to refresh.",
								Notification.Type.WARNING_MESSAGE);
						return;
					}

					Iterator<?> itr = itemIds.iterator();
					List<Item> selectedRecords = new ArrayList<>(itemIds.size());
					while (itr.hasNext()) {
						Object itemId = itr.next();
						selectedRecords.add(grid.getContainerDataSource()
								.getItem(itemId));
					}

					setRecords(selectedRecords);
					activateMultiHandler();

				}

			});
		}

		protected void attachBtnUserBlock() {
			
			if( !isAllowedFeature( btnDisable, EnumPermission.USER_ACTIVATE_BLOCK.val) )
				return;
			
			this.btnDisable.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					Collection<?> itemIds = grid.getSelectedRows();

					if (itemIds == null || itemIds.size() == 0) {
						Notification.show(
								"Please select at least on record to refresh.",
								Notification.Type.WARNING_MESSAGE);
						return;
					}

					Iterator<?> itr = itemIds.iterator();
					List<Item> selectedRecords = new ArrayList<>(itemIds.size());
					while (itr.hasNext()) {
						Object itemId = itr.next();
						selectedRecords.add(grid.getContainerDataSource()
								.getItem(itemId));
					}

					setRecords(selectedRecords);
					blockMultiHandler();

				}

			});
		}
		
		
		private void attachBtnExport(){
			
			
			if( !isAllowedFeature( btnExport, EnumPermission.USER_EXPORT.val) )
				return;
			
			this.btnExport.addClickListener( new ClickListener(){

				
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					
					Collection<?> itemIds = grid.getSelectedRows();
					
					if( itemIds == null || itemIds.size() == 0 ) {
						Notification.show(
								"Please select at least on record to refresh.",
								Notification.Type.WARNING_MESSAGE );
						return;
					}
					
					
					Iterator< ? > itr = itemIds.iterator();
					
					Collection<Item> records = new ArrayList<>();
					
					
					
					
					while( itr.hasNext() ){
						Object itemId = itr.next();
						records.add( grid.getContainerDataSource().getItem( itemId ) );		
					}
					
					new DPgExportLimitUIUser( records );
				}
				
			});
		}

		protected void attachBtnRefresh() {
			this.btnRefresh.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {

					Collection<?> itemIds = grid.getSelectedRows();

					if (itemIds == null || itemIds.size() == 0) {
						Notification.show(
								"Please select at least on record to refresh.",
								Notification.Type.WARNING_MESSAGE);
						return;
					}

					Iterator<?> itr = itemIds.iterator();

					Collection<Item> selectedRecords = new ArrayList<>();

					while (itr.hasNext()) {
						Object itemId = itr.next();
						selectedRecords.add(grid.getContainerDataSource()
								.getItem(itemId));
					}

					setRecords(selectedRecords);
					Out out = refreshMultiUserRecord();

					if (out.getStatusCode() == 1)
						Notification.show(out.getMsg(),
								Notification.Type.HUMANIZED_MESSAGE);
					else
						Notification.show(out.getMsg(),
								Notification.Type.WARNING_MESSAGE);

				}

			});
		}

		protected void expireSessionMultiHandler() {

			Out out = expireSessionMultiUserRecord(records);

			if (out.getStatusCode() == 1) {
				Notification.show(out.getMsg(),
						Notification.Type.HUMANIZED_MESSAGE);
				refreshMultiUserRecord();

			} else {
				Notification
						.show("Expiring some selected user(s) session(s) failed. Please try again / Contact support.",
								Notification.Type.ERROR_MESSAGE);

			}

		}

		protected void expirePassMultiHandler() {

			Out out = expirePassMultiUserRecord(records);

			if (out.getStatusCode() == 1) {
				Notification.show(out.getMsg(),
						Notification.Type.HUMANIZED_MESSAGE);
				refreshMultiUserRecord();

			} else {
				Notification
						.show("Expiring some selected user password(s) failed. Please try again / Contact support.",
								Notification.Type.ERROR_MESSAGE);

			}

		}

		protected void activateMultiHandler() {

			Out out = activateMultiUserRecord(records);

			if (out.getStatusCode() == 1) {
				Notification.show(out.getMsg(),
						Notification.Type.HUMANIZED_MESSAGE);
				refreshMultiUserRecord();

			} else {
				Notification
						.show("Activating some selected user(s) session(s) failed. Please try again / Contact support.",
								Notification.Type.ERROR_MESSAGE);

			}

		}

		protected void blockMultiHandler() {

			Out out = blockMultiUserRecord(records);

			if (out.getStatusCode() == 1) {
				Notification.show(out.getMsg(),
						Notification.Type.HUMANIZED_MESSAGE);
				refreshMultiUserRecord();

			} else {
				Notification
						.show("Blocking some selected user(s) session(s) failed. Please try again / Contact support.",
								Notification.Type.ERROR_MESSAGE);

			}

		}

		protected Out refreshMultiUserRecord() {
			return mTxn.refreshMultiUserRecord(records);
		}

	}
	
	
	protected void setInDate(InTxn inTxn, int dayOffSet) {

		inTxn.setfDate( "2016-01-01" );
		inTxn.settDate( "2017-12-31" );
		
		inTxn.setfDefaultDate( inTxn.getfDate() );
		inTxn.settDefaultDate( inTxn.gettDate() );

	}

}
