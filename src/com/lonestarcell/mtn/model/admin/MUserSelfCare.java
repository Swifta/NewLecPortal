package com.lonestarcell.mtn.model.admin;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.spring.user.entity.Permission;
import com.lonestarcell.mtn.spring.user.entity.Profile;
import com.lonestarcell.mtn.spring.user.entity.ProfilePermissionMap;
import com.lonestarcell.mtn.spring.user.repo.PermissionRepo;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.data.Item;

public class MUserSelfCare extends ModelSelfCare {

	private Logger log = LogManager.getLogger(MUserSelfCare.class.getName());

	private InUserDetails inUser;
	private ApplicationContext springAppContext;

	public MUserSelfCare() {
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException("DataSource cannot be null.");
		}

		log.debug(" Model initialized successfully.");

	}

	public MUserSelfCare(ApplicationContext springAppContext) {

		this.setSpringAppContext(springAppContext);

	}

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}

	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}

	public Out checkUsernameUnique(In in) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.username = ? AND u.username != ?";
		q += " LIMIT 1;";

		try {

			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			BData<?> bInData = in.getData();
			inUser = (InUserDetails) bInData.getData();

			// Authorization
			if (setAuthUserId( inUser )
					.getStatusCode() != 1) {
				return out;
			}

			out = new Out();

			conn.setReadOnly(true);
			ps = conn.prepareStatement(q);
			ps.setString(1, inUser.getRecord().getItemProperty("username")
					.getValue().toString());
			ps.setString(2, inUser.getRecord().getItemProperty("newUsername")
					.getValue().toString());

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();
			if (!rs.next()) {
				log.debug("No result");
				out.setMsg("Username is unique");
				out.setStatusCode(1);

				return out;
			}

			log.debug("Username already taken.");
			out.setMsg("Username already taken.");

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	@SuppressWarnings("unchecked")
	private String generatePassHash(Item record) {
		record.getItemProperty("passSalt").setValue(sha256Hex(nextSessionId()));
		return sha256Hex(record.getItemProperty("password").getValue()
				.toString()
				+ record.getItemProperty("passSalt").getValue().toString());
	}

	private String nextSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@SuppressWarnings("unchecked")
	public Out resetUserCreds(In in) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String q = "UPDATE users AS u";
		q += " SET u.change_password = ?, u.user_session = ?, u.username = ?, u.password = ?, u.pass_salt = ?, u.profile_id = ?";
		q += " WHERE u.user_id = ?";

		try {

			conn = dataSource.getConnection();
			conn.setReadOnly(false);

			BData<?> bInData = in.getData();
			inUser = (InUserDetails) bInData.getData();

			// Authorization
			if ( setAuthUserId( inUser )
					.getStatusCode() != 1) {
				log.debug("Login session: " + inUser.getUserSession());
				return out;
			}

			Long authId = Long.valueOf(out.getData().getData().toString());

			if (authId == null || authId == 0) {
				log.debug("No user data - user id");
				out.setMsg("No user data.");
				return out;
			}

			// Validate current creds before reset
			if (validUserCurrentCreds(in).getStatusCode() != 1) {
				log.debug("Returning from validUserCurrentCreds");
				return out;
			} else {
				log.debug("validUserCurrentCreds passed. Continuing to validate username.");
			}

			// Re-validate username
			if (this.checkUsernameUnique(in).getStatusCode() != 1) {
				log.debug("Returning from checkUsernameUnique");
				return out;
			} else {
				log.debug("checkUsernameUnique passed. Continuing to get user id");
			}

			out = new Out();

			String session = nextSessionId();
			String newUsername = inUser.getRecord()
					.getItemProperty("newUsername").getValue().toString();
			String newPassword = inUser.getRecord()
					.getItemProperty("newPassword").getValue().toString();

			// This is necessary for generatePassHash to base on newPassword
			// other than current password
			inUser.getRecord().getItemProperty("password")
					.setValue(newPassword);

			ps = conn.prepareStatement(q);
			ps.setInt(1, 0);
			ps.setString(2, session);
			ps.setString(3, newUsername);
			ps.setString(4, this.generatePassHash(inUser.getRecord()));
			ps.setString(5, inUser.getRecord().getItemProperty("passSalt")
					.getValue().toString());
			ps.setInt(6, Integer.valueOf(inUser.getRecord()
					.getItemProperty("profileId").getValue().toString()));
			ps.setLong(7, authId);
			log.debug("Query: " + ps.toString());
			ps.executeUpdate();

			// Set creds and form values to anew
			inUser.setUserSession(session);
			inUser.setUsername(newUsername);
			inUser.getRecord().getItemProperty("username")
					.setValue(newUsername);
			inUser.getRecord().getItemProperty("userId").setValue(authId);

			inUser.getRecord().getItemProperty("password").setValue(null);
			inUser.getRecord().getItemProperty("newPassword").setValue(null);
			inUser.getRecord().getItemProperty("passSalt").setValue(null);

			out.setMsg("User creds reset successfully.");
			out.setStatusCode(1);

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	@SuppressWarnings("unchecked")
	public Out validUserCurrentCreds(In in) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		out = new Out();

		String q = "SELECT user_id, password, pass_salt, status, change_password,  profile_id  from users WHERE username = ? LIMIT 1";

		try {
			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			BData<?> bInData = in.getData();
			inUser = (InUserDetails) bInData.getData();

			log.debug("Authorization prior.");

			// Authorization
			if ( setAuthUserId( inUser )
					.getStatusCode() != 1) {
				return out;
			}

			log.debug("Authorization passed.");

			out = new Out();

			conn.setReadOnly(true);
			ps = conn.prepareStatement(q);
			ps.setString(1, inUser.getUsername());

			rs = ps.executeQuery();
			if (!rs.next()) {
				log.debug("No result");
				out.setMsg("No user data.");
				return out;
			}

			String passHash = sha256Hex(inUser.getRecord()
					.getItemProperty("password").getValue().toString()
					+ rs.getString("pass_salt"));

			if (passHash.equals(rs.getString("password"))) {

				inUser.getRecord().getItemProperty("password").setValue(null);
				log.debug("Valid creds.");

				out.setStatusCode(1);
				out.setMsg("Valid creds.");

			} else {
				out.setStatusCode(100);
				log.debug("Invalid current password.");
				out.setMsg("Invalid current password.");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	@SuppressWarnings("unchecked")
	public Out setUserDetails(In in) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		out = new Out();

		String q = "SELECT o.domain_id AS org_domain, o.organization_status AS org_status, u.firstname, u.lastname, u.surname, DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u";
		q += " JOIN organization AS o ON o.id = u.organization_id";
		q += " JOIN profile AS p ON p.profile_id = u.profile_id";
		q += " WHERE u.username = ?";
		q += " ORDER BY u.date_added, u.last_login DESC";
		q += " LIMIT 1;";

		try {

			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			BData<?> bInData = in.getData();
			inUser = (InUserDetails) bInData.getData();

			// Authorization
			if (setAuthUserId( inUser )
					.getStatusCode() != 1) {
				return out;
			}

			out = new Out();

			conn.setReadOnly(true);
			ps = conn.prepareStatement(q);
			ps.setString(1,
					(String) inUser.getRecord().getItemProperty("username")
							.getValue());

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();
			if (!rs.next()) {
				log.debug("No result");
				out.setMsg("No user records.");
				return out;
			}

			inUser.getRecord().getItemProperty("userId")
					.setValue(rs.getLong("user_id"));
			inUser.getRecord().getItemProperty("username")
					.setValue(rs.getString("username"));
			inUser.getRecord().getItemProperty("userSession")
					.setValue(rs.getString("user_session"));
			inUser.getRecord().getItemProperty("userStatus")
					.setValue(rs.getString("status"));
			inUser.getRecord().getItemProperty("changePass")
					.setValue(rs.getShort("change_password"));
			inUser.getRecord().getItemProperty("dateAdded")
					.setValue(rs.getString("date_added"));
			inUser.getRecord().getItemProperty("lastLogin")
					.setValue(rs.getString("last_login"));
			inUser.getRecord().getItemProperty("org")
					.setValue(rs.getString("org"));
			inUser.getRecord().getItemProperty("profile")
					.setValue(rs.getString("profile_name"));
			inUser.getRecord().getItemProperty("profileId")
					.setValue(rs.getInt("profile_id"));
			inUser.getRecord().getItemProperty("email")
					.setValue(rs.getString("email"));
			inUser.getRecord().getItemProperty("firstName")
					.setValue(rs.getString("firstname"));
			inUser.getRecord().getItemProperty("lastName")
					.setValue(rs.getString("lastname"));
			inUser.getRecord().getItemProperty("surname")
					.setValue(rs.getString("surname"));

			inUser.getRecord().getItemProperty("orgStatus")
					.setValue(rs.getString("org_status"));
			inUser.getRecord().getItemProperty("orgDomain")
					.setValue(rs.getString("org_domain"));

			out.setStatusCode(1);
			out.setMsg("User details set successfully.");

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	@SuppressWarnings("unchecked")
	public Out updateUserPersonalInfo(In in) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		out = new Out();
		String q = "UPDATE users AS u";
		q += " SET u.firstname = ?, u.lastname = ?, u.surname = ?, u.email = ?";
		q += " WHERE u.user_id = ?";

		try {

			conn = dataSource.getConnection();
			conn.setReadOnly(false);

			BData<?> bInData = in.getData();
			inUser = (InUserDetails) bInData.getData();

			// Authorization
			if (setAuthUserId( inUser )
					.getStatusCode() != 1) {
				return out;
			}

			Long authId = Long.valueOf(out.getData().getData().toString());

			if (authId == null || authId == 0) {
				log.debug("No user data");
				out.setMsg("No user data.");
				return out;
			}

			// Re-validate email
			if (this.checkEmailUnique(in).getStatusCode() != 1) {
				return out;
			}

			out = new Out();

			String newFirstName = inUser.getRecord()
					.getItemProperty("newFirstName").getValue().toString();
			String newLastName = inUser.getRecord()
					.getItemProperty("newLastName").getValue().toString();
			String newSurname = inUser.getRecord()
					.getItemProperty("newSurname").getValue().toString();
			String newEmail = inUser.getRecord().getItemProperty("newEmail")
					.getValue().toString();

			ps = conn.prepareStatement(q);
			ps.setString(1, newFirstName);
			ps.setString(2, newLastName);
			ps.setString(3, newSurname);
			ps.setString(4, newEmail);
			ps.setLong(5, authId);

			ps.executeUpdate();

			// Set form values to anew

			inUser.getRecord().getItemProperty("newFirstName").setValue(null);
			inUser.getRecord().getItemProperty("newLastName").setValue(null);
			inUser.getRecord().getItemProperty("newSurname").setValue(null);
			inUser.getRecord().getItemProperty("newEmail").setValue(null);

			inUser.getRecord().getItemProperty("firstName")
					.setValue(newFirstName);
			inUser.getRecord().getItemProperty("lastName")
					.setValue(newLastName);
			inUser.getRecord().getItemProperty("surname").setValue(newSurname);
			inUser.getRecord().getItemProperty("email").setValue(newEmail);

			log.debug("Query: " + ps.toString());

			out.setMsg("Personal info updated successfully.");
			out.setStatusCode(1);

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	public Out checkEmailUnique(In in) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String q = "SELECT u.user_id FROM users AS u";
		q += " WHERE u.email = ? AND u.email != ?";
		q += " LIMIT 1;";

		try {

			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			BData<?> bInData = in.getData();
			inUser = (InUserDetails) bInData.getData();

			// Authorization
			if (setAuthUserId( inUser )
					.getStatusCode() != 1) {
				return out;
			}

			out = new Out();

			ps = conn.prepareStatement(q);
			ps.setString(1, inUser.getRecord().getItemProperty("email")
					.getValue().toString());
			ps.setString(2, inUser.getRecord().getItemProperty("newEmail")
					.getValue().toString());

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();
			if (!rs.next()) {
				log.debug("No result");
				out.setMsg("Email is unique");
				out.setStatusCode(1);

				return out;
			}

			out.setMsg("Email already used.");

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	public Out setProfilePermissionSet(short profileId, Set<Short> permSet) {

		Out out = new Out();

		try {

			ProfileRepo repo = springAppContext.getBean(ProfileRepo.class);
			if (repo == null) {
				log.debug("Repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Profile profile = repo.findOne(profileId);
			String msg = null;
			if (profile == null) {
				msg = "No such profile found with id " + profileId + ".";
				log.debug(msg);
				out.setMsg(msg);
			} else if (profile.getProfileStatus() != 1) {
				msg = "Profile with id " + profileId + " is not active.";
				log.debug(msg);
				out.setMsg(msg);
			} else {

				List<ProfilePermissionMap> permList = profile
						.getProfilePermissionMaps();
				if (permList != null && permList.size() != 0) {
					Iterator<ProfilePermissionMap> itr = permList.iterator();
					while (itr.hasNext()) {
						ProfilePermissionMap permMap = itr.next();
						Permission perm = permMap.getPermission();
						if (perm != null) {
							permSet.add(perm.getId());
							log.debug("Original Perm loaded: " + perm.getName());
							perm = this.getParentPerm(permSet,
									perm.getParentPermId());
							if (perm != null)
								addParentPermId(permSet, perm);
						}
					}
				}

			}

			out.setStatusCode(1);
			out.setMsg("Profile permission(s) successfully.");

		} catch (Exception ex) {

			ex.printStackTrace();
			out.setMsg("Data fetch error.");
		}

		return out;
	}

	private Permission addParentPermId(Set<Short> permSet, Permission perm) {
		if (perm != null && !permSet.contains(perm.getId())) {
			permSet.add(perm.getId());
			log.debug("Parent Perm loaded: " + perm.getName());
			perm = this.getParentPerm(permSet, perm.getParentPermId());
			if( perm != null ) {
				return addParentPermId(permSet, perm);
			}
		}

		return perm;
	}

	private Permission getParentPerm(Set<Short> permSet, short parentPermId) {
		PermissionRepo repo = springAppContext.getBean(PermissionRepo.class);
		Permission perm = repo.findOne(parentPermId);
		if (perm == null)
			return null;
		if (permSet.contains(perm.getId()))
			return null;
		return perm;
	}

}
