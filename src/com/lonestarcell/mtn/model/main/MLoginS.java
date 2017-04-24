package com.lonestarcell.mtn.model.main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.User;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Users2;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.UsersE;

public class MLoginS {
	private HyperswiftStub stub = null;

	public MLoginS() {
		stub = DSStubInitializer.getDSStub();
	}

	public User login(String username, String pass)
			throws NoSuchAlgorithmException {
		if (stub == null) {
			System.err.println("DS stub has not been initialized.");
			return null;
		}

		HyperswiftStub.LoginUser loginUser = new HyperswiftStub.LoginUser();
		loginUser.setUsername(username);
		loginUser.setPass(sha256Hex(pass));
		loginUser.setSessionVar(nextSessionId());

		// System.out.println("Hash: "+sha256Hex(pass));

		try {
			Users2 usersE = stub.loginUser(loginUser);
			UsersE users = usersE.getUsers();
			User[] userArr = users.getUser();

			if (userArr != null && userArr.length == 1)
				return userArr[0];
			else
				return null;

		} catch (RemoteException | DataServiceFaultException e) {

			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println("SID: " + nextSessionId());

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] raw = digest.digest("admin".getBytes());

		StringBuilder sb = new StringBuilder();
		for (byte b : raw) {
			sb.append(String.format("%02X", b));
		}
		System.out.println(sb.toString());

	}

	public static String sha256Hex(String str) throws NoSuchAlgorithmException {

		if (str == null)
			return new String();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] raw = digest.digest(str.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : raw) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}

	public static String nextSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
