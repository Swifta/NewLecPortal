package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MTN_SOAP_REQUEST_DATA001 database table.
 * 
 */
@Entity
@Table(name="MTN_SOAP_REQUEST_DATA001")
@NamedQuery(name="MtnSoapRequestData001.findAll", query="SELECT m FROM MtnSoapRequestData001 m")
public class MtnSoapRequestData001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="CARD_BIN_NUMBER")
	private String cardBinNumber;

	@Column(name="\"CURRENT_DATE\"")
	private Timestamp currentDate;

	@Column(name="EASYPAY_NUMBER")
	private String easypayNumber;

	@Column(name="\"FIRST\"")
	private String first;

	private String iccid;

	@Column(name="INPUT_FILE_NAME")
	private String inputFileName;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private String msisdn;

	@Column(name="OLD_ICCID")
	private String oldIccid;

	@Column(name="OLD_MSISDN")
	private String oldMsisdn;

	@Column(name="QUARANTINE_DATE")
	private Timestamp quarantineDate;

	public MtnSoapRequestData001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getCardBinNumber() {
		return this.cardBinNumber;
	}

	public void setCardBinNumber(String cardBinNumber) {
		this.cardBinNumber = cardBinNumber;
	}

	public Timestamp getCurrentDate() {
		return this.currentDate;
	}

	public void setCurrentDate(Timestamp currentDate) {
		this.currentDate = currentDate;
	}

	public String getEasypayNumber() {
		return this.easypayNumber;
	}

	public void setEasypayNumber(String easypayNumber) {
		this.easypayNumber = easypayNumber;
	}

	public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getInputFileName() {
		return this.inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getOldIccid() {
		return this.oldIccid;
	}

	public void setOldIccid(String oldIccid) {
		this.oldIccid = oldIccid;
	}

	public String getOldMsisdn() {
		return this.oldMsisdn;
	}

	public void setOldMsisdn(String oldMsisdn) {
		this.oldMsisdn = oldMsisdn;
	}

	public Timestamp getQuarantineDate() {
		return this.quarantineDate;
	}

	public void setQuarantineDate(Timestamp quarantineDate) {
		this.quarantineDate = quarantineDate;
	}

}