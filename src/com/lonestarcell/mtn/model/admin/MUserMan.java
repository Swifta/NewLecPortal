package com.lonestarcell.mtn.model.admin;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Expirepass;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Expirepassresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.ExpirepassresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Expireuserpass;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getuserdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getusers;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Lockunlock;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Lockunlockresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.LockunlockresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Lockunlockuser;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Loginuserdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Loginuserresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.LoginuserresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Logout;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.LogoutResponsedetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Logoutresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.LogoutresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Registeruser;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Registeruserdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Registeruserresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.RegisteruserresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Resetpass;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Resetpassword;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.ResetpasswordResponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.ResetpasswordResponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Results;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.ResultsE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updatedusercreds;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updatedusercredsresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.UpdatedusercredsresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateuser;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateusercreds;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateuserresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.UpdateuserresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateuserstatus;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Users;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.model.main.MLoginS;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;

public class MUserMan extends MBackendClient {

	public static Registeruserdetails registerUser(Registeruser reguser)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!profile.equals("1"))
			throw new IllegalAccessException("Illigal access error");

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		reguser.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		reguser.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		reguser.setNewUserChangePass("1");
		reguser.setNewUserPassSalt(MLoginS.nextSessionId());
		reguser.setNewUserPass(MLoginS.nextSessionId());

		RegisteruserresponseE responseE = stub.registeruser(reguser);

		Registeruserresponse response = responseE.getRegisteruserresponse();
		Registeruserdetails[] details = response.getRegisteruserdetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Updatedusercreds updateUserProfileDetails(
			Updateusercreds updateUser) throws RemoteException,
			DataServiceFaultException, IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		updateUser.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		updateUser.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		try {
			updateUser.setPass(MLoginS.sha256Hex(updateUser.getPass()));
			updateUser.setNewPass(MLoginS.sha256Hex(updateUser.getNewPass()));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		UpdatedusercredsresponseE responseE = stub.updateusercreds(updateUser);

		Updatedusercredsresponse response = responseE
				.getUpdatedusercredsresponse();
		Updatedusercreds[] details = response.getUpdatedusercreds();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Resetpass resetPassword(Resetpassword user)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		user.setUsername((String) UI.getCurrent().getSession()
				.getAttribute("TMP_UN"));
		user.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute("TMP_SESSION"));

		try {
			user.setPass(MLoginS.sha256Hex(user.getPass()));
			user.setNewPass(MLoginS.sha256Hex(user.getNewPass()));
			user.setNewLoginSession(MLoginS.nextSessionId());
			user.setNewPassSalt(MLoginS.nextSessionId());
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			return null;
		}
		ResetpasswordResponseE responseE = stub.resetpassword(user);

		ResetpasswordResponse response = responseE.getResetpasswordResponse();
		Resetpass[] details = response.getResetpass();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Loginuserdetails getUserProfileDetails(Getuserdetails user)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		user.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		user.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		LoginuserresponseE responseE = stub.getuserdetails(user);

		Loginuserresponse response = responseE.getLoginuserresponse();
		Loginuserdetails[] details = response.getLoginuserdetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static BeanItemContainer<Users> getUsers(Getusers users)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!profile.equals("1"))
			throw new IllegalAccessException("Illigal access error");

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		users.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		users.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		ResultsE responseE = stub.getusers(users);

		Results response = responseE.getResults();
		Users[] details = response.getUsers();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);

		Collection<Users> list = Arrays.asList(details);

		BeanItemContainer<Users> bUsers = new BeanItemContainer<Users>(
				Users.class);
		bUsers.addAll(list);

		return bUsers;

	}

	public static LogoutResponsedetails logout(Logout user)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!profile.equals("1"))
			throw new IllegalAccessException("Illigal access error");

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		user.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		user.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		LogoutresponseE responseE = stub.logout(user);

		Logoutresponse response = responseE.getLogoutresponse();
		LogoutResponsedetails[] details = response.getLogoutResponsedetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Lockunlock lockunlockuser(Lockunlockuser user)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!profile.equals("1"))
			throw new IllegalAccessException("Illigal access error");

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		user.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		user.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		LockunlockresponseE responseE = stub.lockunlockuser(user);

		Lockunlockresponse response = responseE.getLockunlockresponse();
		Lockunlock[] details = response.getLockunlock();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Expireuserpass expirepass(Expirepass user)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!profile.equals("1"))
			throw new IllegalAccessException("Illigal access error");

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		user.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		user.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		ExpirepassresponseE responseE = stub.expirepass(user);

		Expirepassresponse response = responseE.getExpirepassresponse();
		Expireuserpass[] details = response.getExpireuserpass();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Updateuserstatus updateuser(Updateuser user)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!profile.equals("1"))
			throw new IllegalAccessException("Illigal access error");

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		user.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		user.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		UpdateuserresponseE responseE = stub.updateuser(user);

		Updateuserresponse response = responseE.getUpdateuserresponse();
		Updateuserstatus[] details = response.getUpdateuserstatus();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {

			Registeruser reguser = new HyperswiftStub.Registeruser();
			reguser.setUsername("admin");
			reguser.setLoginSession("sehnqtd3tuim06qblbd44ph8rb");

			reguser.setNewUsername("mikey2");
			reguser.setNewUserPass("Je=99ff0039@");
			reguser.setNewUserEmail("mikey2@fan.com");
			reguser.setNewUserProfileId("3");
			reguser.setNewUserPassSalt("lll");

			reguser.setNewUserFN("FN");
			reguser.setNewUserLN("LN");
			reguser.setNewUserSN("SN");
			reguser.setNewUserStatus("0");
			reguser.setNewUserChangePass("0");

			// MUserMan.registerUser(reguser);

			Getuserdetails user = new HyperswiftStub.Getuserdetails();
			user.setUsername("admin");
			user.setLoginSession("eotrur1qiii0hou384kvjvtncq");

			// MUserMan.getUserProfileDetails(user);

			Updateusercreds updateUser = new HyperswiftStub.Updateusercreds();
			updateUser.setUsername("");
			updateUser.setPass("");
			updateUser.setLoginSession("");
			updateUser.setNewUsername("");
			updateUser.setNewPass("");

			// MUserMan.updateUserProfileDetails(updateUser);

			Getusers users = new HyperswiftStub.Getusers();
			users.setUsername("admin");
			users.setLoginSession("");
			// MUserMan.getUsers(users);

			Logout logoutuser = new HyperswiftStub.Logout();
			logoutuser.setUsername("admin");
			logoutuser.setLoginSession("mfjarv4frobevmccic9lu3h9rl");
			logoutuser.setLogoutUsername("mtn");

			// MUserMan.logout(logoutuser);

			Lockunlockuser lockunlockuser = new HyperswiftStub.Lockunlockuser();
			lockunlockuser.setUsername("admin");
			lockunlockuser.setLoginSession("mfjarv4frobevmccic9lu3h9rl");
			lockunlockuser.setLockunlockUsername("mtn");
			lockunlockuser.setUStatus("1");
			// MUserMan.lockunlockuser(lockunlockuser);

			Expirepass expireuser = new HyperswiftStub.Expirepass();
			expireuser.setUsername("admin");
			expireuser.setLoginSession("mfjarv4frobevmccic9lu3h9rl");
			expireuser.setExpirepassUsername("mtn");
			// MUserMan.expirepass(expireuser);

			Updateuser uuser = new HyperswiftStub.Updateuser();
			uuser.setUsername("admin");
			uuser.setLoginSession("mfjarv4frobevmccic9lu3h9rl");
			uuser.setToUpdateUsername("mtn");
			uuser.setNewUserEmail("3pp@mtn.co.ug");
			uuser.setNewUserFN("MTN FN");
			uuser.setNewUserLN("MTN LN");
			uuser.setNewUserSN("MTN SN");
			uuser.setNewUserProfileId("4");

			// MUserMan.updateuser(uuser);

			Resetpassword resetuser = new HyperswiftStub.Resetpassword();
			resetuser.setUsername("");
			resetuser.setPass("");
			resetuser.setLoginSession("");
			resetuser.setNewPass("agent");

			// MUserMan.resetPassword(resetuser);

			System.out.println(MLoginS.sha256Hex("agentx"));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
