package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MESSAGE_VIEW database table.
 * 
 */
@Entity
@Table(name="MESSAGE_VIEW")
@NamedQuery(name="MessageView.findAll", query="SELECT m FROM MessageView m")
public class MessageView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHANNEL_OID")
	private BigDecimal channelOid;

	@Column(name="CREATED_BY_OID")
	private BigDecimal createdByOid;

	@Column(name="CREATED_ON")
	private Timestamp createdOn;

	private String destination;

	@Column(name="ERROR_MESSAGE_TEXT")
	private String errorMessageText;

	@Column(name="EXTERNAL_ID")
	private String externalId;

	private String iccid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="MESSAGE_CONTENT_OID")
	private BigDecimal messageContentOid;

	@Column(name="MESSAGE_ID")
	private BigDecimal messageId;

	@Column(name="MESSAGE_TYPE")
	private String messageType;

	@Column(name="MPG_GROUP")
	private String mpgGroup;

	@Column(name="OBJECT_OID")
	private BigDecimal objectOid;

	private BigDecimal oid;

	@Column(name="ORIGINAL_MESSAGE_OID")
	private BigDecimal originalMessageOid;

	private String password;

	@Column(name="PROXIED_USER_ID")
	private String proxiedUserId;

	private String source;

	private String status;

	@Column(name="TIMEOUT_TIME")
	private Timestamp timeoutTime;

	@Column(name="USER_ID")
	private String userId;

	public MessageView() {
	}

	public BigDecimal getChannelOid() {
		return this.channelOid;
	}

	public void setChannelOid(BigDecimal channelOid) {
		this.channelOid = channelOid;
	}

	public BigDecimal getCreatedByOid() {
		return this.createdByOid;
	}

	public void setCreatedByOid(BigDecimal createdByOid) {
		this.createdByOid = createdByOid;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getErrorMessageText() {
		return this.errorMessageText;
	}

	public void setErrorMessageText(String errorMessageText) {
		this.errorMessageText = errorMessageText;
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getMessageContentOid() {
		return this.messageContentOid;
	}

	public void setMessageContentOid(BigDecimal messageContentOid) {
		this.messageContentOid = messageContentOid;
	}

	public BigDecimal getMessageId() {
		return this.messageId;
	}

	public void setMessageId(BigDecimal messageId) {
		this.messageId = messageId;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMpgGroup() {
		return this.mpgGroup;
	}

	public void setMpgGroup(String mpgGroup) {
		this.mpgGroup = mpgGroup;
	}

	public BigDecimal getObjectOid() {
		return this.objectOid;
	}

	public void setObjectOid(BigDecimal objectOid) {
		this.objectOid = objectOid;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	public BigDecimal getOriginalMessageOid() {
		return this.originalMessageOid;
	}

	public void setOriginalMessageOid(BigDecimal originalMessageOid) {
		this.originalMessageOid = originalMessageOid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProxiedUserId() {
		return this.proxiedUserId;
	}

	public void setProxiedUserId(String proxiedUserId) {
		this.proxiedUserId = proxiedUserId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimeoutTime() {
		return this.timeoutTime;
	}

	public void setTimeoutTime(Timestamp timeoutTime) {
		this.timeoutTime = timeoutTime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}