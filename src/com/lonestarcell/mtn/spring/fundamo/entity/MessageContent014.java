package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MESSAGE_CONTENT014 database table.
 * 
 */
@Entity
@Table(name="MESSAGE_CONTENT014")
@NamedQuery(name="MessageContent014.findAll", query="SELECT m FROM MessageContent014 m")
public class MessageContent014 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Lob
	private String content;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public MessageContent014() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
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

}