package com.lonestarcell.mtn.spring.fundamo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the POSTILION_MESSAGE_CONTENT001 database table.
 * 
 */
@Entity
@Table(name="POSTILION_MESSAGE_CONTENT001")
@NamedQuery(name="PostilionMessageContent001.findAll", query="SELECT p FROM PostilionMessageContent001 p")
public class PostilionMessageContent001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long oid;

	@Lob
	private byte[] content;

	@Column(name="LAST_UPDATE")
	private Timestamp lastUpdate;

	public PostilionMessageContent001() {
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}