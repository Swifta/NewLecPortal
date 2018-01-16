package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MESSAGE_CONTENT_VIEW database table.
 * 
 */
@Entity
@Table(name="MESSAGE_CONTENT_VIEW")
@NamedQuery(name="MessageContentView.findAll", query="SELECT m FROM MessageContentView m")
public class MessageContentView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private String content;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	private BigDecimal oid;

	public MessageContentView() {
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

}