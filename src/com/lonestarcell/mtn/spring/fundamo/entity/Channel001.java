package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CHANNEL001 database table.
 * 
 */
@Entity
@NamedQuery(name="Channel001.findAll", query="SELECT c FROM Channel001 c")
public class Channel001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="FIELD1_NAME")
	private String field1Name;

	@Column(name="FIELD1_REQUIRES_PIN_LINK")
	private String field1RequiresPinLink;

	@Column(name="FIELD2_NAME")
	private String field2Name;

	@Column(name="FIELD2_REQUIRES_PIN_LINK")
	private String field2RequiresPinLink;

	@Column(name="FIELD3_NAME")
	private String field3Name;

	@Column(name="FIELD3_REQUIRES_PIN_LINK")
	private String field3RequiresPinLink;

	@Column(name="FIELD4_NAME")
	private String field4Name;

	@Column(name="FIELD4_REQUIRES_PIN_LINK")
	private String field4RequiresPinLink;

	@Column(name="FIELD5_NAME")
	private String field5Name;

	@Column(name="FIELD5_REQUIRES_PIN_LINK")
	private String field5RequiresPinLink;

	@Column(name="GENERATE_ALIAS_MODULE")
	private String generateAliasModule;

	@Column(name="GENERATE_PASSWORD_KEY_MODULE")
	private String generatePasswordKeyModule;

	@Column(name="GENERATE_USERNAME_MODULE")
	private String generateUsernameModule;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String name;

	@Column(name="NOTIFICATION_ALLOWED")
	private String notificationAllowed;

	@Column(name="NOTIFICATION_END_TIME")
	private String notificationEndTime;

	@Column(name="NOTIFICATION_START_TIME")
	private String notificationStartTime;

	@Column(name="PERSIST_TOKEN_ALLOWED")
	private String persistTokenAllowed;

	@Column(name="REQUEST_ALLOWED")
	private String requestAllowed;

	@Column(name="VALIDATION_MODULE")
	private String validationModule;

	public Channel001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getField1Name() {
		return this.field1Name;
	}

	public void setField1Name(String field1Name) {
		this.field1Name = field1Name;
	}

	public String getField1RequiresPinLink() {
		return this.field1RequiresPinLink;
	}

	public void setField1RequiresPinLink(String field1RequiresPinLink) {
		this.field1RequiresPinLink = field1RequiresPinLink;
	}

	public String getField2Name() {
		return this.field2Name;
	}

	public void setField2Name(String field2Name) {
		this.field2Name = field2Name;
	}

	public String getField2RequiresPinLink() {
		return this.field2RequiresPinLink;
	}

	public void setField2RequiresPinLink(String field2RequiresPinLink) {
		this.field2RequiresPinLink = field2RequiresPinLink;
	}

	public String getField3Name() {
		return this.field3Name;
	}

	public void setField3Name(String field3Name) {
		this.field3Name = field3Name;
	}

	public String getField3RequiresPinLink() {
		return this.field3RequiresPinLink;
	}

	public void setField3RequiresPinLink(String field3RequiresPinLink) {
		this.field3RequiresPinLink = field3RequiresPinLink;
	}

	public String getField4Name() {
		return this.field4Name;
	}

	public void setField4Name(String field4Name) {
		this.field4Name = field4Name;
	}

	public String getField4RequiresPinLink() {
		return this.field4RequiresPinLink;
	}

	public void setField4RequiresPinLink(String field4RequiresPinLink) {
		this.field4RequiresPinLink = field4RequiresPinLink;
	}

	public String getField5Name() {
		return this.field5Name;
	}

	public void setField5Name(String field5Name) {
		this.field5Name = field5Name;
	}

	public String getField5RequiresPinLink() {
		return this.field5RequiresPinLink;
	}

	public void setField5RequiresPinLink(String field5RequiresPinLink) {
		this.field5RequiresPinLink = field5RequiresPinLink;
	}

	public String getGenerateAliasModule() {
		return this.generateAliasModule;
	}

	public void setGenerateAliasModule(String generateAliasModule) {
		this.generateAliasModule = generateAliasModule;
	}

	public String getGeneratePasswordKeyModule() {
		return this.generatePasswordKeyModule;
	}

	public void setGeneratePasswordKeyModule(String generatePasswordKeyModule) {
		this.generatePasswordKeyModule = generatePasswordKeyModule;
	}

	public String getGenerateUsernameModule() {
		return this.generateUsernameModule;
	}

	public void setGenerateUsernameModule(String generateUsernameModule) {
		this.generateUsernameModule = generateUsernameModule;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotificationAllowed() {
		return this.notificationAllowed;
	}

	public void setNotificationAllowed(String notificationAllowed) {
		this.notificationAllowed = notificationAllowed;
	}

	public String getNotificationEndTime() {
		return this.notificationEndTime;
	}

	public void setNotificationEndTime(String notificationEndTime) {
		this.notificationEndTime = notificationEndTime;
	}

	public String getNotificationStartTime() {
		return this.notificationStartTime;
	}

	public void setNotificationStartTime(String notificationStartTime) {
		this.notificationStartTime = notificationStartTime;
	}

	public String getPersistTokenAllowed() {
		return this.persistTokenAllowed;
	}

	public void setPersistTokenAllowed(String persistTokenAllowed) {
		this.persistTokenAllowed = persistTokenAllowed;
	}

	public String getRequestAllowed() {
		return this.requestAllowed;
	}

	public void setRequestAllowed(String requestAllowed) {
		this.requestAllowed = requestAllowed;
	}

	public String getValidationModule() {
		return this.validationModule;
	}

	public void setValidationModule(String validationModule) {
		this.validationModule = validationModule;
	}

}