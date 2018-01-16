package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CARD_TRAN_CHAR_SET001 database table.
 * 
 */
@Entity
@Table(name="CARD_TRAN_CHAR_SET001")
@NamedQuery(name="CardTranCharSet001.findAll", query="SELECT c FROM CardTranCharSet001 c")
public class CardTranCharSet001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	@Column(name="LEDGER_ACCOUNT_NUMBER")
	private String ledgerAccountNumber;

	private String name;

	@Column(name="UNALLOCATED_LEDGER_ACC_NUM")
	private String unallocatedLedgerAccNum;

	public CardTranCharSet001() {
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

	public String getLedgerAccountNumber() {
		return this.ledgerAccountNumber;
	}

	public void setLedgerAccountNumber(String ledgerAccountNumber) {
		this.ledgerAccountNumber = ledgerAccountNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnallocatedLedgerAccNum() {
		return this.unallocatedLedgerAccNum;
	}

	public void setUnallocatedLedgerAccNum(String unallocatedLedgerAccNum) {
		this.unallocatedLedgerAccNum = unallocatedLedgerAccNum;
	}

}