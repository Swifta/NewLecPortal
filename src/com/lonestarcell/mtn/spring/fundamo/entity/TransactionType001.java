package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TRANSACTION_TYPE001 database table.
 * 
 */
@Entity
@Table(name="TRANSACTION_TYPE001")
@NamedQuery(name="TransactionType001.findAll", query="SELECT t FROM TransactionType001 t")
public class TransactionType001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;
	
	@OneToOne(fetch=FetchType.LAZY )
	@JoinColumn( name="name", referencedColumnName = "code" )
	private Systemcode systemCode;
	
	
	public TransactionType001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/*public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	public Systemcode getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(Systemcode systemCode) {
		this.systemCode = systemCode;
	}
	
	

}