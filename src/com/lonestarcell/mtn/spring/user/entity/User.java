package com.lonestarcell.mtn.spring.user.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="change_password")
	private byte changePassword;

	@Column(name="date_added")
	private Timestamp dateAdded;

	private String email;

	private String firstname;

	@Column(name="last_login")
	private Timestamp lastLogin;

	private String lastname;

	@Column(name="pass_salt")
	private String passSalt;

	private String password;

	private byte status;

	private String surname;

	@Column(name="user_session")
	private String userSession;

	private String username;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="added_by")
	private User user;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="user")
	private List<User> users;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	private Organization organization;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public byte getChangePassword() {
		return this.changePassword;
	}

	public void setChangePassword(byte changePassword) {
		this.changePassword = changePassword;
	}

	public Timestamp getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassSalt() {
		return this.passSalt;
	}

	public void setPassSalt(String passSalt) {
		this.passSalt = passSalt;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserSession() {
		return this.userSession;
	}

	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUser(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUser(null);

		return user;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}