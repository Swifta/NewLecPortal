package com.lonestarcell.mtn.spring.user.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.util.Date;


/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name="AuditLog.findAll", query="SELECT a FROM AuditLog a")
@Proxy(lazy = false)
@Table(name="audit_log")
public class AuditLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="al_id")
	private short alId;

	@ManyToOne
	@JoinColumn( name="al_user_id" )
	private User user;

	@ManyToOne
	@JoinColumn(name="al_perm_id")
	private Permission permission;
	
	@Column( name="al_op_status" )
	private short opStatus;
	
	@Column(name="al_create_date")
	private Date createDate;
	
	@Column(name="al_last_update")
	private Date lastUpdate;

	public AuditLog() {
	}

	public short getAlId() {
		return alId;
	}

	public void setAlId(short alId) {
		this.alId = alId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	

	public short getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(short opStatus) {
		this.opStatus = opStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	
	
}