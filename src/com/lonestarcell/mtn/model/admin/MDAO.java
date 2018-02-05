package com.lonestarcell.mtn.model.admin;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InSettings;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutConfig;

public class MDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long userAuthId;
	protected String userAuthSession, timeCorrection;
	public Out out;
	private Logger log = LogManager.getLogger();
	protected ApplicationContext springAppContext;

	public MDAO(Long userAuthId, String userSession,
			ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
		this.setUserAuthId(userAuthId);
		this.setUserAuthSession(userSession);
		out = new Out();
	}

	protected Long getUserAuthId() {
		return userAuthId;
	}

	protected void setUserAuthId(Long userAuthId) {
		this.userAuthId = userAuthId;
	}

	protected String getUserAuthSession() {
		return userAuthSession;
	}

	protected void setUserAuthSession(String userAuthSession) {
		this.userAuthSession = userAuthSession;
	}

	protected String sha256Hex(String str) {

		try {

			if (str == null)
				return new String();
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] raw = digest.digest(str.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder();
			for (byte b : raw) {
				sb.append(String.format("%02X", b));
			}
			return sb.toString();

		} catch (Exception e) {

			log.error(e.getMessage());
			return null;
		}
	}

	protected Out checkAuthorization() {
		out = new Out();

		BData<Long> bOutData = new BData<>();
		bOutData.setData(1L);
		out.setData(bOutData);

		out.setStatusCode(1);
		out.setMsg("Account authorized for operation.");

		// TODO Check authorization in mute. Check ancestor class [ Model ]

		log.debug("Check auth in mute.");
		

		return out;
	}

	public Out setAdminUserId(String username, String userSession) {

		// TODO
		out = new Out();
		BData<Long> bOutData = new BData<>();
		bOutData.setData(1L);
		out.setData(bOutData);
		out.setStatusCode(1);
		out.setMsg("Account authorized for operation.");

		return out;
	}

	protected OutConfig getConfig() {

		MSettings m = new MSettings(this.userAuthId, this.userAuthSession);
		InSettings inData = new InSettings();

		inData.setConfig(new OutConfig());

		BData<InSettings> bData = new BData<>();
		bData.setData(inData);

		In in = new In();
		in.setData(bData);

		Out out = m.setConfig(in);
		if (out.getStatusCode() == 1)
			return inData.getConfig();
		return null;

	}

}
