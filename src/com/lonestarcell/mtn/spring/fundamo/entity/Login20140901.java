package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the LOGIN_20140901 database table.
 * 
 */
//@Entity
@Table(name="LOGIN_20140901")
@NamedQuery(name="Login20140901.findAll", query="SELECT l FROM Login20140901 l")
public class Login20140901 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activated;

	@Column(name="CHANGE_LOGIN_NAME")
	private String changeLoginName;

	@Column(name="CHANGE_PASSWORD")
	private String changePassword;

	@Column(name="CHANNEL_OID")
	private BigDecimal channelOid;

	private String enabled;

	private String field1;

	private String field2;

	private String field3;

	private String field4;

	private String field5;

	@Column(name="LAST_LOGIN")
	private Timestamp lastLogin;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LOGGED_IN_CHANNELS")
	private BigDecimal loggedInChannels;

	@Column(name="LOGIN_ATTEMPTS")
	private BigDecimal loginAttempts;

	@Column(name="LOGIN_TIME_GROUP_OID")
	private BigDecimal loginTimeGroupOid;

	@Column(name="MOBILE_IDENTITY_OID")
	private BigDecimal mobileIdentityOid;

	private BigDecimal oid;

	@Column(name="PARTY_ROLE_OID")
	private BigDecimal partyRoleOid;

	@Column(name="PASSWORD_KEY")
	private String passwordKey;

	@Column(name="PREFERRED_NOTIFICATION_CHANNEL")
	private String preferredNotificationChannel;

	@Column(name="PREFERRED_REQUEST_CHANNEL")
	private String preferredRequestChannel;

	@Column(name="PREFERRED_TOKEN_CHANNEL")
	private String preferredTokenChannel;

	@Column(name="REFERENCE_NUMBER")
	private String referenceNumber;

	private String username;

	public Login20140901() {
	}

	public String getActivated() {
		return this.activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getChangeLoginName() {
		return this.changeLoginName;
	}

	public void setChangeLoginName(String changeLoginName) {
		this.changeLoginName = changeLoginName;
	}

	public String getChangePassword() {
		return this.changePassword;
	}

	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}

	public BigDecimal getChannelOid() {
		return this.channelOid;
	}

	public void setChannelOid(BigDecimal channelOid) {
		this.channelOid = channelOid;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return this.field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return this.field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return this.field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getLoggedInChannels() {
		return this.loggedInChannels;
	}

	public void setLoggedInChannels(BigDecimal loggedInChannels) {
		this.loggedInChannels = loggedInChannels;
	}

	public BigDecimal getLoginAttempts() {
		return this.loginAttempts;
	}

	public void setLoginAttempts(BigDecimal loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public BigDecimal getLoginTimeGroupOid() {
		return this.loginTimeGroupOid;
	}

	public void setLoginTimeGroupOid(BigDecimal loginTimeGroupOid) {
		this.loginTimeGroupOid = loginTimeGroupOid;
	}

	public BigDecimal getMobileIdentityOid() {
		return this.mobileIdentityOid;
	}

	public void setMobileIdentityOid(BigDecimal mobileIdentityOid) {
		this.mobileIdentityOid = mobileIdentityOid;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public BigDecimal getPartyRoleOid() {
		return this.partyRoleOid;
	}

	public void setPartyRoleOid(BigDecimal partyRoleOid) {
		this.partyRoleOid = partyRoleOid;
	}

	public String getPasswordKey() {
		return this.passwordKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}

	public String getPreferredNotificationChannel() {
		return this.preferredNotificationChannel;
	}

	public void setPreferredNotificationChannel(String preferredNotificationChannel) {
		this.preferredNotificationChannel = preferredNotificationChannel;
	}

	public String getPreferredRequestChannel() {
		return this.preferredRequestChannel;
	}

	public void setPreferredRequestChannel(String preferredRequestChannel) {
		this.preferredRequestChannel = preferredRequestChannel;
	}

	public String getPreferredTokenChannel() {
		return this.preferredTokenChannel;
	}

	public void setPreferredTokenChannel(String preferredTokenChannel) {
		this.preferredTokenChannel = preferredTokenChannel;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}