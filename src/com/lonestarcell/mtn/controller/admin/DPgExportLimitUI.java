package com.lonestarcell.mtn.controller.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.DataExportUISub;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.design.admin.DPgExportLimitUIDesign;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.UserError;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
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
	private Collection< Item > records;
	private Accordion accoRoles;
	private ApplicationContext springAppContext;
	private ProfileRepo profileRepo;
	
	private PaginationUIController pageC;
	private long rowCount;

	public DPgExportLimitUI(PaginationUIController pageC, IModel mSub, In in, Collection<Item> records, VerticalLayout cMoreOps) {
		this.pageC = pageC;
		this.setModel( mSub );
		this.setIn( in );
		this.setRecord(record);
		this.setcMoreOps( cMoreOps );
		init(null);
	}

	public DPgExportLimitUI(DUserRoleUI a, Item record, Accordion accoRoles) {
		this.setRecord(record);
		this.setSpringAppContext(a.getSpringAppContext());
		this.setProfileRepo(this.springAppContext.getBean(ProfileRepo.class));
		init(accoRoles);
		log.debug("New role contructor run.");
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
		this.attachBtnCancel();
		this.attachOnClose();
		// this.attachBtnGenNewPass();
		this.attachBtnSave();
		this.attachBtnCSV();
		this.setComboContent();
	}

	private void attachBtnCSV() {
		this.btnCSV.addClickListener( e->{
			if( !combosSet() )
				return;
			// TODO Handle export
			
			new DataExportUISub( model, in, new ArrayList<Item>(), cMoreOps );
	
		});


	}
	
	private boolean combosSet(){
		return isComboSet( this.comboPgExportLimitFrom) && isComboSet( this.comboPgExportLimitPgCount);
	}
	private boolean isComboSet( ComboBox combo ){
		if( combo.getValue() == null ){
			combo.setComponentError( new UserError( "Required." ) );
			return false;
		}
		return true;
	}
	
	private void setComboContent(){
		
		comboPgExportLimitFrom.removeAllItems();
		comboPgExportLimitPgCount.removeAllItems();
		int pages = pageC.getPages();
		int curPage = pageC.getCurrentPage();

		for (int i = 1; i <= pages; i++)
			comboPgExportLimitFrom.addItems(i);
		comboPgExportLimitFrom.setValue(curPage);

		setComboItems(comboPgExportLimitFrom.getValue());

		comboPgExportLimitFrom.addValueChangeListener(e -> {
			setComboItems(e.getProperty().getValue());
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
		log.info("Export pgs: " + exportPgs);
		
		comboPgExportLimitPgCount.removeAllItems();

		// Only next 10 pages can be exported.
		for (int i = 1; i <= 10; i++)
			if (i <= exportPgs)
				comboPgExportLimitPgCount.addItem(i);

		comboPgExportLimitPgCount.setValue(exportPgs - (exportPgs - 1));

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

	private Out resetUserPassAdmin() {
		MUserDetails mUserDetails = new MUserDetails(getCurrentUserId(),
				getCurrentUserSession());
		InUserDetails inData = new InUserDetails();
		setAuth(inData);
		inData.setRecord(record);

		BData<InUserDetails> bData = new BData<>();
		bData.setData(inData);

		In in = new In();
		in.setData(bData);

		Out out = mUserDetails.resetUserPassAdmin(in);

		return out;

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

	private boolean isFormValid() {

		// if( !this.isTFValid( this.tFNewRoleName ) )
		// return false;

		return true;
	}

	private boolean isTFValid(TextField tF) {

		if (!tF.isValid()) {

			// lbErrorMsg.removeStyleName("sn-display-none");
			// lbNormalMsg.addStyleName("sn-display-none");

			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while (itr.hasNext()) {
				RequiredTFValidator v = (RequiredTFValidator) itr.next();
				msg += v.getErrorMessage();
			}

			// lbErrorMsg.setValue( tF.getCaption()+" Error. "+msg );

			return false;

		} else {

			// lbNormalMsg.removeStyleName("sn-display-none");
			// lbErrorMsg.addStyleName("sn-display-none");

		}

		return true;

	}

	private void attachBtnCancel() {

	}

	private void attachOnClose() {
		processingPopup.addCloseListener(new CloseListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				// refreshRecord();
				// new DUserDetailsUI( record );

			}

		});
	}

	private boolean refreshRecord() {

		if (record != null) {

			InUserDetails inData = new InUserDetails();
			setAuth(inData);
			inData.setRecord(record);

			BData<InUserDetails> bData = new BData<>();
			bData.setData(inData);

			In in = new In();
			in.setData(bData);

			MUserDetails mUserDetails = new MUserDetails(getCurrentUserId(),
					getCurrentUserSession());
			Out out = mUserDetails.setUserDetails(in);
			return out.getStatusCode() == 1;

		}

		return false;

	}

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

	private void format() {

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
				//Notification.Type.ERROR_MESSAGE);
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

}
