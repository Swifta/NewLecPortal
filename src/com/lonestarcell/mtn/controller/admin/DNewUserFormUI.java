package com.lonestarcell.mtn.controller.admin;

import java.rmi.RemoteException;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Registeruser;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Registeruserdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateuser;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateuserstatus;
import com.lonestarcell.mtn.design.admin.DNewUserFormUIDesign;
import com.lonestarcell.mtn.model.admin.MUserMan;
import com.vaadin.data.Item;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class DNewUserFormUI extends DManUI {
	private Item data;

	public DNewUserFormUI(DManUIController duic) {

		init(duic);
	}

	public DNewUserFormUI(DManUIController duic, Item data) {
		setData(data);
		init(duic);
	}

	private class DBookingFormUIController extends DNewUserFormUIDesign
			implements DUIControllable {

		private static final long serialVersionUID = 1L;

		DBookingFormUIController(DManUIController duic) {
			init(duic);
		}

		@Override
		public void attachCommandListeners() {
			attachCancel();
			attachSave();

		}

		@Override
		public void init(DManUIController duic) {
			setDUI(duic);
			attachCommandListeners();
			setContent();
		}

		private void setContent() {

			this.comboProfile.removeAllItems();
			this.comboProfile.addItem("Administrator");
			this.comboProfile.addItem("Agent");
			this.comboProfile.addItem("3PP");
			this.tFEmail.addValidator(new EmailValidator(
					"Invalid email address. "));
			this.lbFormHeader.setValue("Edit user details below");

			setDefaultUIState();

		}

		private void setDefaultUIState() {
			if (data == null)
				return;

			this.btnSave.setCaption("Update");

			this.tFUN.setValue(data.getItemProperty("ousername").getValue()
					.toString());
			this.tFUN.setEnabled(false);
			this.tFUN.setReadOnly(false);

			this.tFEmail.setValue(data.getItemProperty("email").getValue()
					.toString());
			String[] names = data.getItemProperty("fullname").getValue()
					.toString().split(" ");
			this.tFFN.setValue(names[0]);
			this.tFLN.setValue(names[1]);
			this.tFSN.setValue(names[2]);

			this.chkStatus.setValue(data.getItemProperty("status").getValue()
					.toString().equals("1"));
			this.chkStatus.setReadOnly(true);
			this.chkStatus.setEnabled(false);

			String prof = data.getItemProperty("profile_name").getValue()
					.toString();
			if (prof.equals("Booking Agent"))
				prof = "Agent";
			else if (prof.equals("Admin"))
				prof = "Administrator";

			System.out.println("UType: " + prof);
			System.out.println("UStatus: "
					+ data.getItemProperty("status").getValue().toString()
							.equals("1"));
			System.out.println("Val: "
					+ data.getItemProperty("status").getValue().toString());
			this.comboProfile.setValue(prof);
		}

		private void attachCancel() {
			this.btnCancel.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 3061729744640691786L;

				@Override
				public void buttonClick(ClickEvent event) {
					if (data != null)
						new DUsersxUI(duic);
					else
						new DDashboardUI(duic);

				}

			});
		}

		private void attachSave() {

			this.btnSave.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1161782940713725548L;

				@Override
				public void buttonClick(ClickEvent event) {
					/*
					 * TODO Extra validation
					 * 
					 * @Live
					 */

					tFUN.setComponentError(null);
					tFFN.setComponentError(null);
					tFLN.setComponentError(null);
					tFSN.setComponentError(null);
					tFEmail.setComponentError(null);
					comboProfile.setComponentError(null);
					btnSave.setComponentError(null);

					String un = tFUN.getValue();

					if (un == null || un.trim().isEmpty()) {
						tFUN.setComponentError(new UserError(
								"Field value required."));
						return;
					}

					if (un.trim().length() < 3 || un.trim().length() > 15) {
						tFUN.setComponentError(new UserError(
								"Username should be between 6 and 15 characters"));
						return;
					}

					String fn = tFFN.getValue();

					if (fn == null || fn.trim().isEmpty()) {
						tFFN.setComponentError(new UserError(
								"Field value required."));
						return;
					}

					if (fn.trim().length() < 2 || fn.trim().length() > 15) {
						tFFN.setComponentError(new UserError(
								"Firstname should be between 5 and 15 characters"));
						return;
					}

					String ln = tFLN.getValue();
					if (ln == null || ln.trim().isEmpty()) {
						tFLN.setComponentError(new UserError(
								"Field value required."));
						return;
					}
					if (ln.trim().length() < 2 || ln.trim().length() > 15) {
						tFLN.setComponentError(new UserError(
								"Lastname should be between 3 and 15 Characters."));
						return;
					}

					String sn = tFSN.getValue();
					/*
					 * if(sn == null || sn.trim().isEmpty()){
					 * tFSN.setComponentError(new
					 * UserError("Field value required.")); return; }
					 * if(ln.trim().length() < 3 || ln.trim().length() > 15){
					 * tFSN.setComponentError(new
					 * UserError("Surname should be between 3 and 15 Characters."
					 * )); return; }
					 */

					if (sn.equals(null) || sn.trim().isEmpty())
						sn = "N/A";

					String email = tFEmail.getValue();
					if (email == null || email.trim().isEmpty()) {
						tFEmail.setComponentError(new UserError(
								"Field value required."));
						return;
					}

					tFEmail.validate();

					String profile = (String) comboProfile.getValue();
					System.out.println("Profile: " + profile);
					if (profile == null || profile.trim().isEmpty()) {
						comboProfile.setComponentError(new UserError(
								"Field value required."));
						return;
					}

					if (profile.equals("Administrator"))
						profile = "1";
					else if (profile.equals("Agent"))
						profile = "3";
					else if (profile.equals("3PP"))
						profile = "2";

					System.out.println("Profile to save: " + profile);

					String status = (chkStatus.getValue()) ? "1" : "0";

					if (data != null) {
						Updateuser user = new Updateuser();

						user.setToUpdateUsername(un);
						user.setNewUserFN(fn);
						user.setNewUserLN(ln);
						user.setNewUserSN(sn);
						user.setNewUserEmail(email);
						user.setNewUserProfileId(profile);

						try {
							Updateuserstatus response = MUserMan
									.updateuser(user);
							if (response == null) {
								Notification
										.show("Error occured while performing operation. Please try again",
												Notification.Type.ERROR_MESSAGE);
								return;
							}

							if (!response.getResponseCode().equals("01")) {
								Notification.show(response.getResponseMsg(),
										Notification.Type.ERROR_MESSAGE);
								return;
							}
						} catch (Exception e) {
							Notification
									.show("Error occured while performing operation. Please try again",
											Notification.Type.ERROR_MESSAGE);
							e.printStackTrace();
							return;
						}

						Notification.show("User details successfully updated!");
						new DUsersxUI(duic);
						return;
					}

					Registeruser reguser = new Registeruser();

					reguser.setNewUsername(un);
					reguser.setNewUserFN(fn);
					reguser.setNewUserLN(ln);
					reguser.setNewUserSN(sn);
					reguser.setNewUserEmail(email);
					reguser.setNewUserStatus(status);
					reguser.setNewUserProfileId(profile);

					try {

						Registeruserdetails response = MUserMan
								.registerUser(reguser);
						if (response != null
								&& !response.getResponseCode().equals("01")) {
							lbFormHeader.setValue(response.getResponseMsg());
							lbFormHeader.addStyleName("sn-error");
							return;
						}

						lbFormHeader.setValue(response.getResponseMsg()
								+ "! User registration completed. Enter new user details if required.");
						lbFormHeader.removeStyleName("sn-error");

						tFUN.clear();
						tFFN.clear();
						tFLN.clear();
						tFSN.clear();
						tFEmail.clear();
						comboProfile.clear();
						chkStatus.clear();

						tFUN.focus();

					} catch (RemoteException | IllegalAccessException
							| DataServiceFaultException e) {
						lbFormHeader
								.setValue("Oops... something is wrong. Please check your connection and try again.");
						lbFormHeader.addStyleName("sn-error");
						e.printStackTrace();
					}

				}

			});

		}

	}

	@Override
	public void setContent() {
		swap(duic, new DBookingFormUIController(duic));

	}

	@Override
	public void init(DManUIController duic) {
		setDUI(duic);
		setContent();

	}

	public Item getData() {
		return data;
	}

	public void setData(Item data) {
		this.data = data;
	}

}
