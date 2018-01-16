package com.lonestarcell.mtn.spring.user.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permissions database table.
 * 
 */
@Entity
@Table(name="permissions")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String name;

	//bi-directional many-to-one association to ProfilePermissionMap
	@OneToMany(mappedBy="permission")
	private List<ProfilePermissionMap> profilePermissionMaps;

	public Permission() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProfilePermissionMap> getProfilePermissionMaps() {
		return this.profilePermissionMaps;
	}

	public void setProfilePermissionMaps(List<ProfilePermissionMap> profilePermissionMaps) {
		this.profilePermissionMaps = profilePermissionMaps;
	}

	public ProfilePermissionMap addProfilePermissionMap(ProfilePermissionMap profilePermissionMap) {
		getProfilePermissionMaps().add(profilePermissionMap);
		profilePermissionMap.setPermission(this);

		return profilePermissionMap;
	}

	public ProfilePermissionMap removeProfilePermissionMap(ProfilePermissionMap profilePermissionMap) {
		getProfilePermissionMaps().remove(profilePermissionMap);
		profilePermissionMap.setPermission(null);

		return profilePermissionMap;
	}

}