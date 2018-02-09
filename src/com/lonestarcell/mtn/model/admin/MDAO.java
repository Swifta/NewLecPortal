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

public class MDAO extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	public MDAO(Long userAuthId, String userSession,
			ApplicationContext springAppContext) {
		super( userAuthId, userSession, springAppContext );
		
		
	}

	
}
