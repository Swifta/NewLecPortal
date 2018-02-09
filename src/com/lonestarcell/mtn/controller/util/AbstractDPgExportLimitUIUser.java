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
import org.vaadin.haijian.Exporter;
import org.vaadin.haijian.PdfExporter;

import com.lonestarcell.mtn.bean.ExportSubscriber;
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
import com.lonestarcell.mtn.model.admin.MUser;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.UserError;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class AbstractDPgExportLimitUIUser< T > extends DPgExportLimitUIDesign implements
		DUIControllable, IExporter< T > {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger(AbstractDPgExportLimitUIUser.class.getName());
	private Item record;
	protected MUser model;
	protected In in;
	private VerticalLayout cMoreOps;
	protected Collection<Item> records;
	private Accordion accoRoles;
	private ApplicationContext springAppContext;
	private ProfileRepo profileRepo;
	protected ExcelExporter xlsExporter;
	protected PdfExporter pdfExporter;
	protected CSVExporter cSVExporter;

	private PaginationUIController pageC;
	private long rowCount;
	private InTxn inTxn;

	public AbstractDPgExportLimitUIUser(PaginationUIController pageC, MUser mSub, In in,
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
	
	


	public AbstractDPgExportLimitUIUser(Collection<Item> records) {
		this.records = records;
		init(null);
	}

	public AbstractDPgExportLimitUIUser(DUserRoleUI a, Item record, Accordion accoRoles) {
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

	public MUser getModel() {
		return model;
	}

	public void setModel(MUser model) {
		
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
		this.attachBtnPDF();
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
			// inTxn.setPage( 1 );
			inTxn.setExportFPgNo( 1 );
		} else {
			comboPgExportLimitFrom.setValue(curPage);
			// inTxn.setPage(curPage);
			inTxn.setExportFPgNo( curPage );
		}

		setComboItems(comboPgExportLimitFrom.getValue());

		comboPgExportLimitFrom.addValueChangeListener(e -> {

			Object obj = e.getProperty().getValue();
			if (obj != null)
				//inTxn.setPage(Integer.valueOf(obj.toString()));
				inTxn.setExportFPgNo( Integer.valueOf(obj.toString()));
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
		setContent();

		xlsExporter = this.getExcelExporterCtrl();
		cSVExporter = this.getCSVExporterCtrl();
		pdfExporter = this.getPdfExporterCtrl();

		xlsExporter.addStyleName("sn-display-none");
		pdfExporter.addStyleName("sn-display-none");
		cSVExporter.addStyleName("sn-display-none");
		
		
		this.cExportCtrls.replaceComponent(btnXLSDownloadPlaceholder,
				xlsExporter);
		
		this.cExportCtrls.replaceComponent(btnCSVDownloadPlaceholder,
				cSVExporter);
		
		this.cExportCtrls.replaceComponent(btnPDFDownloadPlaceholder,
				pdfExporter);

	}

	protected boolean isMulti() {
		boolean is = records != null && records.size() != 0;
		
		if( is ){
			this.comboPgExportLimitFrom.setEnabled( false );;
			this.comboPgExportLimitPgCount.setEnabled( false );
		}
		return is;
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
	
	
	public abstract BeanItemContainer< T > getExportData();



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
	
	
	private PdfExporter getPdfExporterCtrl() {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName = sdf.format(new Date()) + "_" + "_by_"
				+ this.getCurrentUsername();

		PdfExporter pdfExporter = new PdfExporter();
		
		pdfExporter.setDateFormat("yyyy-MM-dd");
		pdfExporter.setContainerToBeExported(new BeanItemContainer<ExportSubscriber>(
				ExportSubscriber.class));
		
		
		pdfExporter.setDateFormat("yyyy-MM-dd");
		pdfExporter.setCaption("");
		pdfExporter.setIcon(FontAwesome.DOWNLOAD);
		pdfExporter.addStyleName("friendly icon-only link");
		pdfExporter.setDescription("Download .pdf");
		pdfExporter.setDownloadFileName(fileName);
		pdfExporter.setDisableOnClick(true);
		
		// excelExporter.setEnabled( false );
		pdfExporter.addClickListener(e -> {
			pdfExporter.addStyleName("sn-display-none");
			btnPDF.setVisible(true);
			pdfExporter.setEnabled(true);
			// showSuccess("Now download... please wait.");
		});

		return pdfExporter;

	}
	
	
	protected void exportHandler( Exporter exporter, Button btn, String reportTitle ){
		
		Resource icon = btn.getIcon();
		
		try {
			if (!isMulti())
				if (!combosSet())
					return;

			btn.setIcon(FontAwesome.SPINNER);
			btn.setImmediate(true);
			btn.setComponentError(null);
			btn.setEnabled( false );

			BeanItemContainer<T> c = this
					.getExportData();

			btn.setIcon( icon );
			btn.setEnabled(true);

			if (c == null) {
				showWarn("Failed to load export data. Please try again/contact support.");
				return;
			}

			Table table = new Table( reportTitle );
			table.setContainerDataSource( c );
			exporter.setTableToBeExported( table );
			renameColumns( exporter );
			//xlsExporter.setContainerToBeExported(c);
			exporter.removeStyleName("sn-display-none");
			btn.setVisible(false);
			// showSuccess("File ready. Click download icon");

		} catch (Exception ex) {
			ex.printStackTrace();
			btn.setComponentError(new UserError(
					"Data export failed. Please try again/contact support."));
			btn.setIcon( icon );
			btn.setEnabled(true);
		}


	}
	
	protected void renameColumns( Exporter exporter ){
		//transactionNumber, type, amount, status, payer, payee, date;
		
		/*
		exporter.setColumnHeader( "column1", "Transaction No." );
		exporter.setColumnHeader( "column2", "Type" );
		exporter.setColumnHeader( "column3", "Amount" );
		exporter.setColumnHeader( "column4", "Status" );
		exporter.setColumnHeader( "column5", "Payer" );
		exporter.setColumnHeader( "column6", "Payee" );
		exporter.setColumnHeader( "date", "Timestamp" ); */
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
