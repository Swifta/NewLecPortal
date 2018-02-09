package com.lonestarcell.mtn.spring.user.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import java.util.List;


/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
@Proxy(lazy = false)
@Table(name="profile")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="profile_id")
	private short profileId;

	@Column(name="profile_desc")
	private String profileDesc;

	@Column(name="profile_name")
	private String profileName;
	
	@Column(name="profile_status")
	private short profileStatus;

	//bi-directional many-to-one association to ProfilePermissionMap
	
	// TODO Performance hit here [ look into if you get time ]
	@OneToMany(mappedBy="profile", fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE, CascadeType.MERGE } )
	private List<ProfilePermissionMap> profilePermissionMaps;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="profile")
	private List<User> users;

	public Profile() {
	}
	
	

	public Profile(String profileName, String profileDesc ) {
		super();
		this.profileName = profileName;
		this.profileDesc = profileDesc;
		
	}



	public short getProfileId() {
		return this.profileId;
	}

	public void setProfileId(short profileId) {
		this.profileId = profileId;
	}

	public String getProfileDesc() {
		return this.profileDesc;
	}

	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}

	public String getProfileName() {
		return this.profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public List<ProfilePermissionMap> getProfilePermissionMaps() {
		return this.profilePermissionMaps;
	}

	public void setProfilePermissionMaps(List<ProfilePermissionMap> profilePermissionMaps) {
		this.profilePermissionMaps = profilePermissionMaps;
	}

	public ProfilePermissionMap addProfilePermissionMap(ProfilePermissionMap profilePermissionMap) {
		getProfilePermissionMaps().add(profilePermissionMap);
		profilePermissionMap.setProfile(this);

		return profilePermissionMap;
	}

	public ProfilePermissionMap removeProfilePermissionMap(ProfilePermissionMap profilePermissionMap) {
		getProfilePermissionMaps().remove(profilePermissionMap);
		profilePermissionMap.setProfile(null);

		return profilePermissionMap;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setProfile(this);

		return user;
	}
	
	

	public short getProfileStatus() {
		return profileStatus;
	}



	public void setProfileStatus(short profileStatus) {
		this.profileStatus = profileStatus;
	}



	public User removeUser(User user) {
		getUsers().remove(user);
		user.setProfile(null);

		return user;
	}

}