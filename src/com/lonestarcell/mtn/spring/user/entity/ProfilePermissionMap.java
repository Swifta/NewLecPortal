package com.lonestarcell.mtn.spring.user.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Generated;


/**
 * The persistent class for the profile_permission_map database table.
 * 
 */
@Entity
@Table(name="profile_permission_map")
@NamedQuery(name="ProfilePermissionMap.findAll", query="SELECT p FROM ProfilePermissionMap p")
public class ProfilePermissionMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	//bi-directional many-to-one association to Permission
	@ManyToOne
	private Permission permission;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;

	public ProfilePermissionMap() {
	}
	
	

	public ProfilePermissionMap( Permission permission, Profile profile) {
		super();
		this.permission = permission;
		this.profile = profile;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}