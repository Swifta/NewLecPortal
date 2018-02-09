package com.lonestarcell.mtn.controller.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.RequiredTFValidator;
import com.lonestarcell.mtn.design.admin.DUserEditRoleUIDesign;
import com.lonestarcell.mtn.design.admin.DUserNewRoleUIDesign;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.spring.user.entity.Permission;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.entity.ProfilePermissionMap;
import com.lonestarcell.mtn.spring.user.repo.PermissionRepo;
import com.lonestarcell.mtn.spring.user.repo.ProfilePermissionMapRepo;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class DUserEditRoleUI extends DUserEditRoleUIDesign implements
		DUIControllable {

	private static final long serialVersionUID = 1L;

	private Window processingPopup;
	private Logger log = LogManager.getLogger(DUserEditRoleUI.class.getName());
	private Item record;
	private Accordion accoRoles;
	private ApplicationContext springAppContext;
	private ProfileRepo profileRepo;
	private Set<String> oldPermNames;

	private Profile profileRecord;
	private List<ProfilePermissionMap> profilePermMapRecords;
	private DUserRolePermUI a;

	public DUserEditRoleUI(DUserRolePermUI a, Item record, Accordion accoRoles,
			Profile profileRecord,
			List<ProfilePermissionMap> profilePermMapRecords) {
		this.a = a;
		this.setProfilePermMapRecords(profilePermMapRecords);
		this.setProfileRecord(profileRecord);
		this.setRecord(record);
		this.setSpringAppContext(a.getSpringAppContext());
		this.setProfileRepo(this.springAppContext.getBean(ProfileRepo.class));
		init(accoRoles);
		log.debug("New role contructor run.");
	}

	public Set<String> getOldPermNames() {
		return oldPermNames;
	}

	public void setOldPermNames(Set<String> oldPermNames) {
		this.oldPermNames = oldPermNames;
	}

	public Profile getProfileRecord() {
		return profileRecord;
	}

	public void setProfileRecord(Profile profileRecord) {
		this.profileRecord = profileRecord;
	}

	public List<ProfilePermissionMap> getProfilePermMapRecords() {
		return profilePermMapRecords;
	}

	public void setProfilePermMapRecords(
			List<ProfilePermissionMap> profilePermMapRecords) {
		this.profilePermMapRecords = profilePermMapRecords;
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
		// this.btnSave.setEnabled(false);
	}

	@Transactional
	private void attachBtnSave() {
		this.btnSave.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				// TODO Validate if role name is unique
				// TODO Need transactions for ATOMICITY?
				// TODO Validate if atleast one permission is selected.
				
				save();


			}

		});
	}
	
	@Transactional
	private void save(){
		
		try {
			if (isFormValid()) {

				profileRecord.setProfileName(tFNewRoleName.getValue());
				profileRecord.setProfileDesc(tARoleDesc.getValue());

				Profile profile = profileRepo
						.saveAndFlush(profileRecord);
				if (profile != null) {

					Notification.show(
							"Role " + profile.getProfileName()
									+ " with id "
									+ profile.getProfileId()
									+ " added successfully.",
							Notification.Type.HUMANIZED_MESSAGE);

					PermissionRepo permRepo = springAppContext
							.getBean(PermissionRepo.class);

					Collection<?> newPermNames = (Collection<?>) twinColPermissions
							.getValue();

					// Need to remove all permissions if any existed
					// previously.
					if (newPermNames == null)
						newPermNames = new ArrayList<>();

					Iterator<?> itrNewPermNames = newPermNames
							.iterator();
					
					List<ProfilePermissionMap> newProfilePermMaps = new ArrayList<>();
					List<ProfilePermissionMap> discardProfilePermMaps = new ArrayList<>();
					while (itrNewPermNames.hasNext()) {
						String permName = itrNewPermNames.next()
								.toString();
						if (oldPermNames.contains(permName)) {

							oldPermNames.remove(permName);
						} else {
							Permission perm = permRepo
									.findByName(permName);
							newProfilePermMaps
									.add(new ProfilePermissionMap(perm,
											profileRecord));
						}

					}

					ProfilePermissionMapRepo profilePermRepo = springAppContext
							.getBean(ProfilePermissionMapRepo.class);

					// What remains of old permissions that is no longer
					// part of the map.

					log.debug("Size of discard set: "
							+ oldPermNames.size());

					Iterator<?> itrDiscardPermNames = oldPermNames
							.iterator();
					while (itrDiscardPermNames.hasNext()) {
						String permName = itrDiscardPermNames.next()
								.toString();
						Permission perm = permRepo.findByName(permName);

						discardProfilePermMaps.add(profilePermRepo
								.findByProfileProfileIdAndPermissionId(
										profileRecord.getProfileId(),
										perm.getId()));

					}

					profilePermRepo.delete(discardProfilePermMaps);
					profilePermRepo.save(newProfilePermMaps);

					showSuccess("Role permissions updated successfully.");

					// Setup permission tree with updated permission set
					a.populatePermissionTreeTestExceptionEffect(profilePermRepo
							.findByProfileProfileId(profileRecord
									.getProfileId()));
					
					
					Component comp = accoRoles.getSelectedTab();
					Tab tab = accoRoles.getTab( comp );
					tab.setCaption( profileRecord.getProfileName() );
					tab.setDescription( profileRecord.getProfileDesc() );
					
					
					processingPopup.close();
				} else {
					String msg = "Error adding role.";
					Notification.show(msg,
							Notification.Type.ERROR_MESSAGE);
					lbErrorMsg.removeStyleName("sn-display-none");
					lbNormalMsg.addStyleName("sn-display-none");
					lbErrorMsg.setValue(msg);
				}
			}
		} catch (Exception e) {

			Notification
					.show("Error occured. Please try again / Contact support.",
							Notification.Type.ERROR_MESSAGE);
			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");
			lbErrorMsg
					.setValue("Error occured. Please try again / Contact support.");

			e.printStackTrace();

		}

		
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

		if (!this.isTFValid(this.tFNewRoleName))
			return false;

		return true;
	}

	private boolean isTFValid(TextField tF) {

		if (!tF.isValid()) {

			lbErrorMsg.removeStyleName("sn-display-none");
			lbNormalMsg.addStyleName("sn-display-none");

			Iterator<Validator> itr = tF.getValidators().iterator();
			String msg = "";
			while (itr.hasNext()) {
				RequiredTFValidator v = (RequiredTFValidator) itr.next();
				msg += v.getErrorMessage();
			}

			lbErrorMsg.setValue(tF.getCaption() + " Error. " + msg);

			return false;

		} else {

			lbNormalMsg.removeStyleName("sn-display-none");
			lbErrorMsg.addStyleName("sn-display-none");

		}

		return true;

	}

	private void attachBtnCancel() {
		this.btnCancel.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				processingPopup.close();
			}

		});
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
		this.setProcessingPopup(new Window("New Role"));
		attachCommandListeners();
		setPropertyDataSource();
		setContent();

	}

	@SuppressWarnings("unchecked")
	private void setPropertyDataSource() {

		lbNormalMsg.removeStyleName("sn-display-none");
		lbErrorMsg.addStyleName("sn-display-none");

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
		this.tFNewRoleName.addValidator(rTFValidator);
		this.tFNewRoleName.setImmediate(true);
		this.tFNewRoleName.setComponentError(null);
	}

	private void setRoleData() {

		List<Permission> allPerm = springAppContext.getBean(
				PermissionRepo.class).findAll();

		this.tFNewRoleName.setValue(profileRecord.getProfileName());
		this.tARoleDesc.setValue(profileRecord.getProfileDesc());
		this.tFNewRoleName.setDescription(profileRecord.getProfileDesc());

		Iterator<Permission> itr = allPerm.iterator();
		this.twinColPermissions.removeAllItems();
		while (itr.hasNext())
			this.twinColPermissions.addItem(itr.next().getName());

		Iterator<ProfilePermissionMap> itrActivePerm = profilePermMapRecords
				.iterator();
		oldPermNames = new HashSet<>(profilePermMapRecords.size());
		while (itrActivePerm.hasNext())
			oldPermNames.add(itrActivePerm.next().getPermission().getName());

		this.twinColPermissions.setValue(oldPermNames);
		this.twinColPermissions.setRows(6);

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
		processingPopup.setSizeUndefined();
		UI.getCurrent().addWindow(processingPopup);
	}

	private void setContent() {
		try {
			if (profileRecord != null) {
				showPopup();
				setRoleData();
				// format();
			} else {

				throw new Exception("Error occured setting content.");
			}
		} catch (Exception e) {
			if (this.processingPopup != null)
				this.processingPopup.close();
			showError("Oops... something went wrong. Please try again.");
			e.printStackTrace();

		}
	}

	private void showError(String msg) {
		Notification.show(msg, Notification.Type.ERROR_MESSAGE);
	}

	private void showSuccess(String msg) {
		Notification.show(msg, Notification.Type.HUMANIZED_MESSAGE);
	}

	private void showWarn(String msg) {
		Notification.show(msg, Notification.Type.WARNING_MESSAGE);
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
