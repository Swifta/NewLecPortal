package com.lonestarcell.mtn.spring.user.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the organization database table.
 * 
 */
@Entity
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="domain_id")
	private String domainId;

	private String name;

	@Column(name="organization_status")
	private byte organizationStatus;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="organization")
	private List<User> users;

	public Organization() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomainId() {
		return this.domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getOrganizationStatus() {
		return this.organizationStatus;
	}

	public void setOrganizationStatus(byte organizationStatus) {
		this.organizationStatus = organizationStatus;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setOrganization(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setOrganization(null);

		return user;
	}

}