package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the NOTIFICATION_RULE001 database table.
 * 
 */
@Entity
@Table(name="NOTIFICATION_RULE001")
@NamedQuery(name="NotificationRule001.findAll", query="SELECT n FROM NotificationRule001 n")
public class NotificationRule001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CHANNEL_OID")
	private BigDecimal channelOid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="NOTIFY_PAYEE")
	private String notifyPayee;

	@Column(name="NOTIFY_PAYER")
	private String notifyPayer;

	@Column(name="TRANSACTION_TYPE_NAME")
	private String transactionTypeName;

	public NotificationRule001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public BigDecimal getChannelOid() {
		return this.channelOid;
	}

	public void setChannelOid(BigDecimal channelOid) {
		this.channelOid = channelOid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getNotifyPayee() {
		return this.notifyPayee;
	}

	public void setNotifyPayee(String notifyPayee) {
		this.notifyPayee = notifyPayee;
	}

	public String getNotifyPayer() {
		return this.notifyPayer;
	}

	public void setNotifyPayer(String notifyPayer) {
		this.notifyPayer = notifyPayer;
	}

	public String getTransactionTypeName() {
		return this.transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

}