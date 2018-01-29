package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.haijian.CSVExporter;
import org.vaadin.haijian.ExcelExporter;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.DataExportUISub;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.design.admin.DPgExportLimitUIDesign;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.entity.User;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class DPgExportLimitUI extends DPgExportLimitUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger(DPgExportLimitUI.class.getName());
	private Item record;
	private IModel model;
	private In in;
	private VerticalLayout cMoreOps;
	private Collection<Item> records;
	private Accordion accoRoles;
	private ApplicationContext springAppContext;
	private ProfileRepo profileRepo;
	private ExcelExporter xlsExporter;
	private CSVExporter cSVExporter;

	private PaginationUIController pageC;
	private long rowCount;
	private InTxn inTxn;

	public DPgExportLimitUI(PaginationUIController pageC, IModel mSub, In in,
			Collection<Item> records, VerticalLayout cMoreOps) {
		this.pageC = pageC;
		this.setModel(mSub);
		this.setIn(in);
		this.setInTxn(in.getData().getData());
		this.setRecord(record);
		this.setcMoreOps(cMoreOps);
		init(null);
	}

	public DPgExportLimitUI(DUserRoleUI a, Item record, Accordion accoRoles) {
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
		return Long.valueOf(obj.toString());
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
		this.attachBtnSave();
		this.setComboContent();
		this.attachBtnXLS();
		this.attachBtnCSV();
	}

	private boolean combosSet() {
		this.comboPgExportLimitFrom.setComponentError( null );
		this.comboPgExportLimitPgCount.setComponentError( null );
		
		return isComboSet(this.comboPgExportLimitFrom)
				&& isComboSet(this.comboPgExportLimitPgCount);
	}

	private boolean isComboSet(ComboBox combo) {
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
		inTxn.setPageSize( pageC.getPageLength() );

		for (int i = 1; i <= pages; i++)
			comboPgExportLimitFrom.addItems(i);
		comboPgExportLimitFrom.setValue(curPage);
		inTxn.setPage( curPage );

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
		log.info( "Default limit: "+defaultLimit );
		comboPgExportLimitPgCount.setValue( exportPgs - (exportPgs - 1) );
		inTxn.setPageExportLimit( ( int ) defaultLimit );

	}

	private void attachBtnSave() {

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
		this.setProcessingPopup(new Window("Export Pages"));
		attachCommandListeners();
		setPropertyDataSource();
		setContent();
		
		xlsExporter = this.getExcelExporterCtrl();
		cSVExporter = this.getCSVExporterCtrl();
		
		xlsExporter.addStyleName( "sn-display-none" );
		cSVExporter.addStyleName( "sn-display-none" );
		this.cExportCtrls.replaceComponent( btnXLSDownloadPlaceholder, xlsExporter );
		this.cExportCtrls.replaceComponent( btnCSVDownloadPlaceholder, cSVExporter );
		

	}

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
	private void attachBtnXLS(){
		this.btnXLS.addClickListener( e->{
			if (!combosSet())
				return;
			// Load data
			BeanItemContainer<AbstractDataBean> container = new BeanItemContainer<AbstractDataBean>(
					AbstractDataBean.class);
			
			Out out = model.setExportData(in, container);
			if (out.getStatusCode() != 1) {
				showWarn("Failed to load export data. Please try again/contact support.");
				return;
			}
			
			BeanItemContainer<OutUser> c = ( BeanItemContainer< OutUser >)out.getData().getData();
			
			xlsExporter.setContainerToBeExported( c );
			xlsExporter.removeStyleName( "sn-display-none" );
			btnXLS.setVisible( false );
			showSuccess( "File ready. Click download icon" );
	
		});
	}
	
	
	@SuppressWarnings("unchecked")
	private void attachBtnCSV(){
		this.btnCSV.addClickListener( e->{
			if (!combosSet())
				return;
			// Load data
			BeanItemContainer<AbstractDataBean> container = new BeanItemContainer<AbstractDataBean>(
					AbstractDataBean.class);
			Out out = model.setExportData(in, container);
			if (out.getStatusCode() != 1) {
				showWarn("Failed to load export data. Please try again/contact support.");
				return;
			}
			
			BeanItemContainer<OutUser> c = ( BeanItemContainer< OutUser >)out.getData().getData();
			cSVExporter.setContainerToBeExported(c);
			cSVExporter.removeStyleName( "sn-display-none" );
			btnCSV.setVisible( false );
			showSuccess( "File ready. Click download icon" );
			
		});
	}
	
	
	
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
		excelExporter
				.addClickListener(e -> {
					xlsExporter.addStyleName( "sn-display-none" );
					btnXLS.setVisible( true );
					xlsExporter.setEnabled( true );
					showSuccess( "Now download... please wait." );
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

		cSVExporter.setContainerToBeExported(new BeanItemContainer<OutUser>(
				OutUser.class));
		cSVExporter.setCaption("");
		cSVExporter.setIcon(FontAwesome.DOWNLOAD);
		cSVExporter.addStyleName("friendly icon-only link");
		cSVExporter.setDescription("Download .csv");
		cSVExporter.setDownloadFileName(fileName);
		cSVExporter.setDisableOnClick(true);

		cSVExporter
				.addClickListener(e -> {
					cSVExporter.addStyleName( "sn-display-none" );
					btnCSV.setVisible( true );
					cSVExporter.setEnabled( true );
					showSuccess( "Now download... please wait." );
				});

		return cSVExporter;

	}

	private void showError(String msg) {
		Notification.show(msg, Notification.Type.ERROR_MESSAGE);
	}

	private void showWarn(String msg) {
		Notification.show(msg, Notification.Type.WARNING_MESSAGE);
	}

	private void showSuccess(String msg) {
		Notification.show(msg, Notification.Type.HUMANIZED_MESSAGE);
	}

	private String getCurrentUsername() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);
	}

}
