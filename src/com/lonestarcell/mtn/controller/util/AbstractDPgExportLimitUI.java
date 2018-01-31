package com.lonestarcell.mtn.controller.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.haijian.CSVExporter;
import org.vaadin.haijian.ExcelExporter;

import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.controller.admin.DUserRoleUI;
import com.lonestarcell.mtn.controller.admin.IExporter;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DPgExportLimitUIDesign;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class AbstractDPgExportLimitUI< T > extends DPgExportLimitUIDesign implements
		DUIControllable, IExporter< T > {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger(AbstractDPgExportLimitUI.class.getName());
	private Item record;
	protected IModel model;
	protected In in;
	private VerticalLayout cMoreOps;
	protected Collection<Item> records;
	private Accordion accoRoles;
	private ApplicationContext springAppContext;
	private ProfileRepo profileRepo;
	protected ExcelExporter xlsExporter;
	protected CSVExporter cSVExporter;

	private PaginationUIController pageC;
	private long rowCount;
	private InTxn inTxn;

	public AbstractDPgExportLimitUI(PaginationUIController pageC, IModel mSub, In in,
			Collection<Item> records, VerticalLayout cMoreOps) {
		this.records = records;
		this.pageC = pageC;
		this.setModel(mSub);
		this.setIn(in);
		this.setInTxn(in.getData().getData());
		this.setRecord(record);
		this.setcMoreOps(cMoreOps);
		init(null);
	}

	public AbstractDPgExportLimitUI(Collection<Item> records) {
		this.records = records;
		init(null);
	}

	public AbstractDPgExportLimitUI(DUserRoleUI a, Item record, Accordion accoRoles) {
		this.setRecord(record);
		this.setSpringAppContext(a.getSpringAppContext());
		this.setProfileRepo(this.springAppContext.getBean(ProfileRepo.class));
		init(accoRoles);
		log.debug("New role contructor run.");
	}

	public InTxn getInTxn() {
		return inTxn;
	}

	public void setInTxn(Object inTxn) {
		this.inTxn = (InTxn) inTxn;
	}

	public Collection<Item> getRecords() {
		return records;
	}

	public void setRecords(Collection<Item> records) {
		this.records = records;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	public In getIn() {
		return in;
	}

	public void setIn(In in) {
		this.in = in;
	}

	public VerticalLayout getcMoreOps() {
		return cMoreOps;
	}

	public void setcMoreOps(VerticalLayout cMoreOps) {
		this.cMoreOps = cMoreOps;
	}

	public long getRowCount() {
		Object obj = pageC.getLbTotalRecords().getValue(); 
		if (obj == null)
			obj = "0";
		else
			return pageC.getRowCount();
		return Long.valueOf(obj.toString().replace( ",", ""));
	}

	public ProfileRepo getProfileRepo() {
		return profileRepo;
	}

	public void setProfileRepo(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}

	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}

	public Window getProcessingPopup() {
		return processingPopup;
	}

	public void setProcessingPopup(Window processingPopup) {
		this.processingPopup = processingPopup;
	}

	@Override
	public void attachCommandListeners() {
		// this.attachBtnGenNewPass();
		if( !isMulti() )
			this.setComboContent();
		this.attachBtnXLS();
		this.attachBtnCSV();
	}

	protected boolean combosSet() {
		this.comboPgExportLimitFrom.setComponentError(null);
		this.comboPgExportLimitPgCount.setComponentError(null);

		return isComboSet(this.comboPgExportLimitFrom)
				&& isComboSet(this.comboPgExportLimitPgCount);
	}

	protected boolean isComboSet(ComboBox combo) {
		if (combo.getValue() == null) {
			combo.setComponentError(new UserError("Required."));
			return false;
		}
		return true;
	}

	private void setComboContent() {

		comboPgExportLimitFrom.removeAllItems();
		comboPgExportLimitPgCount.removeAllItems();
		int pages = pageC.getPages();
		int curPage = pageC.getCurrentPage();
		
		inTxn.setPageSize(pageC.getPageLength());

		for (int i = 1; i <= pages; i++)
			comboPgExportLimitFrom.addItems(i);
		
		// In initialization of pagination controller, current page value is 2.
		// Conditioning to fix this state affair.
		if( pageC.getNewPage() == 1 ){
			comboPgExportLimitFrom.setValue( 1);
			inTxn.setPage( 1 );
		} else {
			comboPgExportLimitFrom.setValue(curPage);
			inTxn.setPage(curPage);
		}

		setComboItems(comboPgExportLimitFrom.getValue());

		comboPgExportLimitFrom.addValueChangeListener(e -> {

			Object obj = e.getProperty().getValue();
			if (obj != null)
				inTxn.setPage(Integer.valueOf(obj.toString()));
			setComboItems(obj);
		});

		comboPgExportLimitPgCount.addValueChangeListener(e -> {
			Object obj = e.getProperty().getValue();
			if (obj != null)
				inTxn.setPageExportLimit(Integer.valueOf(obj.toString()));
		});
	}

	private void setComboItems(Object obj) {
		long rowCount = getRowCount();
		float pgLen = pageC.getPageLength();
		if (obj == null)
			obj = "0";

		int fromPg = Integer.valueOf(obj.toString());
		float exportPgCount = rowCount - ((fromPg - 1) * pgLen);
		int exportPgs = (int) Math.ceil(exportPgCount / pgLen);
		log.debug("Export pgs: " + exportPgs);

		comboPgExportLimitPgCount.removeAllItems();

		// Only next 10 pages can be exported.
		for (int i = 1; i <= 10; i++)
			if (i <= exportPgs)
				comboPgExportLimitPgCount.addItem(i);

		long defaultLimit = exportPgs - (exportPgs - 1);
		log.info("Default limit: " + defaultLimit);
		comboPgExportLimitPgCount.setValue(exportPgs - (exportPgs - 1));
		inTxn.setPageExportLimit((int) defaultLimit);

	}



	@Transactional
	private void addRoleToAccordion(Profile profile) {

		profile = profileRepo.findByProfileName(profile.getProfileName());

		Tree tL = new Tree();
		tL.addItem("Loading...");

		VerticalLayout vL = new VerticalLayout();
		vL.setCaption(profile.getProfileName());
		vL.setId(profile.getProfileId() + "");
		vL.setWidth("100%");
		vL.addComponent(tL);
		vL.setDescription(profile.getProfileDesc());
		this.accoRoles.addTab(vL, profile.getProfileName());

	}

	/*
	 * private void attachBtnGenNewPass(){ this.btnGenNewPass.addClickListener(
	 * new ClickListener(){
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public void buttonClick(ClickEvent event) {
	 * 
	 * tFNewPassword.setReadOnly( false ); record.getItemProperty( "newPassword"
	 * ).setValue( MUtil.genNewPass() ); tFNewPassword.setReadOnly( true );
	 * 
	 * 
	 * }
	 * 
	 * }); }
	 */
	
	
	

	public Item getRecord() {
		return record;
	}

	public void setRecord(Item record) {
		this.record = record;
	}

	private void init(Accordion accoRoles) {

		this.accoRoles = accoRoles;
		this.setProcessingPopup(new Window("Export records"));
		attachCommandListeners();
		setPropertyDataSource();
		setContent();

		xlsExporter = this.getExcelExporterCtrl();
		cSVExporter = this.getCSVExporterCtrl();

		xlsExporter.addStyleName("sn-display-none");
		cSVExporter.addStyleName("sn-display-none");
		this.cExportCtrls.replaceComponent(btnXLSDownloadPlaceholder,
				xlsExporter);
		this.cExportCtrls.replaceComponent(btnCSVDownloadPlaceholder,
				cSVExporter);

	}

	protected boolean isMulti() {
		boolean is = records != null && records.size() != 0;
		
		if( is ){
			this.comboPgExportLimitFrom.setEnabled( false );;
			this.comboPgExportLimitPgCount.setEnabled( false );
		}
		
		return is;
	}

	
	/*@SuppressWarnings("unchecked")
	@Override
	public Out getExportData() {
		BeanItemContainer<OutUser> c = new BeanItemContainer<>(OutUser.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();
				Property<?> column = record.getItemProperty("username");
				String val = column.getValue().toString();
				OutUser user = new OutUser();
				user.setUsername(val);
				c.addBean(user);
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;
			
			c = (BeanItemContainer<OutUser>) out.getData().getData();

		}
		
		Out out = new Out();
		BData< BeanItemContainer< OutUser > > bData = new BData<>();
		bData.setData( c );
		out.setData( bData );
		if( c != null && c.size() != 0 )
			out.setStatusCode( 1 );
		
		return out;
	} */

	@SuppressWarnings("unchecked")
	private void setPropertyDataSource() {

		// lbNormalMsg.removeStyleName("sn-display-none");
		// lbErrorMsg.addStyleName("sn-display-none");

		/*
		 * record.getItemProperty( "newUsername" ).setValue(
		 * record.getItemProperty( "username" ).getValue() );
		 * record.getItemProperty( "newPassword" ).setValue( MUtil.genNewPass()
		 * );
		 * 
		 * this.tFNewUsername.setPropertyDataSource( record.getItemProperty(
		 * "newUsername" ) ); this.tFNewPassword.setPropertyDataSource(
		 * record.getItemProperty( "newPassword" ) );
		 * 
		 * this.tFNewUsername.addValidator( new RequiredTFValidator( "" ) );
		 * this.tFNewPassword.addValidator( new RequiredTFValidator( "" ) );
		 * 
		 * 
		 * 
		 * this.tFNewUsername.setReadOnly( true );
		 * this.tFNewPassword.setReadOnly( true );
		 * 
		 * log.debug( "Field data sources have been initialized successfully."
		 * );
		 */

		RequiredTFValidator rTFValidator = new RequiredTFValidator(
				"Field required in valid format");
		// this.tFNewRoleName.addValidator(rTFValidator );
		// this.tFNewRoleName.setImmediate( true );
		// this.tFNewRoleName.setComponentError( null );
	}

	private void showPopup() {
		processingPopup.setContent(this);
		processingPopup.center();
		processingPopup.setClosable(true);
		processingPopup.setEnabled(true);
		processingPopup.setModal(true);
		processingPopup.setDraggable(false);
		processingPopup.setResizable(false);
		// processingPopup.setSizeFull();
		// processingPopup.setSizeUndefined();
		UI.getCurrent().addWindow(processingPopup);
	}

	private void setContent() {
		
		this.btnXLS.setDisableOnClick( true );
		this.btnCSV.setDisableOnClick( true );
		
		// if(record != null ) {
		showPopup();
		// format();
		// } else {

		// Notification.show("Oops... error loading data. Please  try again.",
		// Notification.Type.ERROR_MESSAGE);
		// }
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
	
	
	@SuppressWarnings("unchecked")
	public abstract BeanItemContainer< T > getExportData();

	/*
	@SuppressWarnings("unchecked")
	@Override
	public void attachBtnXLS() {
		this.btnXLS.addClickListener(e -> {
			if( !isMulti() )
				if (!combosSet())
					return;
			
				BeanItemContainer< OutUser > c = extractData();
				if ( c == null ) {
					showWarn("Failed to load export data. Please try again/contact support.");
					return;
				}
				
				xlsExporter.setContainerToBeExported(c);
				xlsExporter.removeStyleName("sn-display-none");
				btnXLS.setVisible(false);
				showSuccess("File ready. Click download icon");

			});
	} */

	/*
	@Override
	public void attachBtnCSV() {
		this.btnCSV
				.addClickListener(e -> {
					if( !isMulti() )
						if ( !combosSet() )
							return;
					// Load data
					BeanItemContainer< OutUser > c = extractData();
					if ( c == null ) {
						showWarn("Failed to load export data. Please try again/contact support.");
						return;
					}

					cSVExporter.setContainerToBeExported(c);
					cSVExporter.removeStyleName("sn-display-none");
					btnCSV.setVisible(false);
					showSuccess("File ready. Click download icon");

				});
	} */

	private ExcelExporter getExcelExporterCtrl() {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName = sdf.format(new Date()) + "_" + "_by_"
				+ this.getCurrentUsername();

		ExcelExporter excelExporter = new ExcelExporter();
		excelExporter.setDateFormat("yyyy-MM-dd");
		excelExporter.setContainerToBeExported(new BeanItemContainer<OutUser>(
				OutUser.class));
		excelExporter.setCaption("");
		excelExporter.setIcon(FontAwesome.DOWNLOAD);
		excelExporter.addStyleName("friendly icon-only link");
		excelExporter.setDescription("Download .xls");
		excelExporter.setDownloadFileName(fileName);
		excelExporter.setDisableOnClick(true);

		// excelExporter.setEnabled( false );

		excelExporter.setDisableOnClick(true);
		excelExporter.addClickListener(e -> {
			xlsExporter.addStyleName("sn-display-none");
			btnXLS.setVisible(true);
			xlsExporter.setEnabled(true);
			showSuccess("Now download... please wait.");
		});

		return excelExporter;

	}

	private CSVExporter getCSVExporterCtrl() {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName = sdf.format(new Date()) + "_" + "_by_"
				+ this.getCurrentUsername();

		CSVExporter cSVExporter = new CSVExporter();

		cSVExporter.setDateFormat("yyyy-MM-dd");
		// cSVExporter.setTableToBeExported( table );

		cSVExporter.setContainerToBeExported( getExportBean() );
		cSVExporter.setCaption("");
		cSVExporter.setIcon(FontAwesome.DOWNLOAD);
		cSVExporter.addStyleName("friendly icon-only link");
		cSVExporter.setDescription("Download .csv");
		cSVExporter.setDownloadFileName(fileName);
		cSVExporter.setDisableOnClick(true);

		cSVExporter.addClickListener(e -> {
			cSVExporter.addStyleName("sn-display-none");
			btnCSV.setVisible(true);
			cSVExporter.setEnabled(true);
			showSuccess("Now download... please wait.");
		});

		return cSVExporter;

	}
	
	protected abstract BeanItemContainer< T > getExportBean();

	protected void showError(String msg) {
		Notification.show(msg, Notification.Type.ERROR_MESSAGE);
	}

	protected void showWarn(String msg) {
		Notification.show(msg, Notification.Type.WARNING_MESSAGE);
	}

	protected void showSuccess(String msg) {
		Notification.show(msg, Notification.Type.HUMANIZED_MESSAGE);
	}

	protected String getCurrentUsername() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);
	}

}
