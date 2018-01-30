package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.ExportUser;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.user.repo.UserRepo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MUser extends Model implements IModel {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger(MUser.class.getName());

	private OutUser outTxn;
	private InTxn inTxn;

	public MUser(Long userId, String userSession, ApplicationContext cxt ) {
		super(userId, userSession);
		this.springAppContext = cxt;
		
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException("DataSource cannot be null.");
		}

		log.debug(" Model initialized successfully.");

	}
	
	public MUser(Long userId, String userSession ) {
		super(userId, userSession);
		
		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException("DataSource cannot be null.");
		}

		log.debug(" Model initialized successfully.");

	}

	public Out setUsers(In in, BeanItemContainer<OutUser> container) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String q = "SELECT DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u";
		q += " JOIN organization AS o ON o.id = u.organization_id";
		q += " JOIN profile AS p ON p.profile_id = u.profile_id";
		q += " WHERE u.user_id != ?";
		q += " ORDER BY u.date_added, u.last_login DESC";
		q += " LIMIT ?, ?;";

		try {

			// TODO Check if user session is valid before operation.
			// TODO Check if user profile is authorized
			// TODO This should be implemented in one place, the mother class
			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			BData<?> bInData = in.getData();
			inTxn = (InTxn) bInData.getData();

			int page = inTxn.getPage();
			int pageLength = 15;
			int pageMin = 0;
			if (page > 1) {
				pageMin = (page - 1) * pageLength + 1;
			}

			// TODO Delete Test Data
			// String timeCorrection = " 23:13:59";
			// inTxn.setfDate( "2017-01-01" );
			// inTxn.settDate( "2017-01-14" );

			conn.setReadOnly(true);
			ps = conn.prepareStatement(q);
			// ps.setString( 1, inTxn.getfDate()+timeCorrection );
			// ps.setString( 2, inTxn.gettDate()+timeCorrection );
			ps.setLong(1, super.userAuthId);
			ps.setInt(2, pageMin);
			ps.setInt(3, pageLength);

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();
			if (!rs.next()) {
				log.debug("No result");

				outTxn = new OutUser();
				container.addBean(outTxn);

				BData<BeanItemContainer<OutUser>> bOutData = new BData<>();
				bOutData.setData(container);

				out.setData(bOutData);
				return out;
			}

			do {

				outTxn = new OutUser();

				outTxn.setUserId(rs.getLong("user_id"));
				outTxn.setUsername(rs.getString("username"));
				outTxn.setUserSession(rs.getString("user_session"));

				String userStatus = rs.getString("status");
				String userStatusDesc = null;
				if (userStatus.equals("0")) {
					userStatusDesc = "REGISTERED";
				} else if (userStatus.equals("1")) {
					userStatusDesc = "ACTIVE";
				} else if (userStatus.equals("2")) {
					userStatusDesc = "BLOCKED";
				} else {
					userStatusDesc = "N/A";
				}
				outTxn.setUserStatus(userStatusDesc);

				outTxn.setChangePass(rs.getString("change_password"));
				outTxn.setDate(rs.getString("date_added"));
				outTxn.setLastLogin(rs.getString("last_login"));
				outTxn.setOrg(rs.getString("org"));
				outTxn.setProfile(rs.getString("profile_name"));
				outTxn.setProfileId(rs.getInt("profile_id"));

				outTxn.setEmail(rs.getString("email"));

				container.addBean(outTxn);

			} while (rs.next());

			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	public Out searchUsers(In in, BeanItemContainer<OutUser> container) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			BData<?> bInData = in.getData();
			inTxn = (InTxn) bInData.getData();

			Map<String, Integer> searchFieldMap = new HashMap<>();
			String qVariable = "";

			String q = "SELECT DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u";
			q += " JOIN organization AS o ON o.id = u.organization_id";
			q += " JOIN profile AS p ON p.profile_id = u.profile_id";
			q += " WHERE ";

			if (inTxn.getSearchUsername() != null) {
				qVariable += " u.username LIKE ? AND";
				searchFieldMap.put("username", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchEmail() != null) {
				qVariable += " u.email LIKE ? AND";
				searchFieldMap.put("email", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchOrg() != null) {
				qVariable += " o.name LIKE ? AND";
				searchFieldMap.put("org", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchProfile() != null) {
				qVariable += " p.profile_name ? AND";
				searchFieldMap.put("profile", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchUserStatus() != null) {
				if ("REGISTERED".contains(inTxn.getSearchUserStatus()
						.toUpperCase())) {

					qVariable += " ( u.status = 0 ";
					qVariable += " )";
					qVariable += " AND";

				} else if ("ACTIVE".contains(inTxn.getSearchUserStatus()
						.toUpperCase())) {

					qVariable += " ( u.status = 1 ";
					qVariable += " )";
					qVariable += " AND";

				} else if ("BLOCKED".contains(inTxn.getSearchUserStatus()
						.toUpperCase())) {

					qVariable += " ( u.status = 2 ";
					qVariable += " )";
					qVariable += " AND";

				} else {

					// Dummy status for no match
					qVariable += " ( u.status = '404' ) ";
					qVariable += " AND";
				}

			}

			q += qVariable;

			q += " u.user_id != ?";
			q += " ORDER BY u.date_added, u.last_login DESC";
			q += " LIMIT ?, ?;";

			int page = inTxn.getPage();
			int pageLength = 15;
			int pageMin = 0;
			if (page > 1) {
				pageMin = (page - 1) * pageLength + 1;
			}
			
			
			BeanItemContainer< OutUser > exportRawData = null;

			if ( inTxn.isExportOp() ) {
				exportRawData = new BeanItemContainer<>( OutUser.class );
				page = inTxn.getPage();
				pageLength = inTxn.getExportPgLen();
				pageMin = 0;
				if (page > 1) {
					pageMin = (page - 1) * pageLength + 1;
				}
			}
			
			

			conn.setReadOnly(true);
			ps = conn.prepareStatement(q);
			if (inTxn.getSearchUsername() != null) {
				ps.setString(searchFieldMap.get("username"),
						"%" + inTxn.getSearchUsername() + "%");
			}

			if (inTxn.getSearchEmail() != null) {
				ps.setString(searchFieldMap.get("email"),
						"%" + inTxn.getSearchEmail() + "%");
			}

			if (inTxn.getSearchProfile() != null) {
				ps.setString(searchFieldMap.get("profile"),
						"%" + inTxn.getSearchProfile() + "%");
			}

			if (inTxn.getSearchOrg() != null) {
				ps.setString(searchFieldMap.get("org"),
						"%" + inTxn.getSearchOrg() + "%");
			}

			int paramIndexOffset = searchFieldMap.size();
			ps.setLong(paramIndexOffset + 1, super.userAuthId);
			ps.setInt(paramIndexOffset + 2, pageMin);
			ps.setInt(paramIndexOffset + 3, pageLength);

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();
			if (!rs.next()) {
				log.debug("No result");
				out.setMsg("No records found.");
				return out;
			}

			do {

				outTxn = new OutUser();

				outTxn.setUserId(rs.getLong("user_id"));
				outTxn.setUsername(rs.getString("username"));
				outTxn.setUserSession(rs.getString("user_session"));

				String userStatus = rs.getString("status");
				String userStatusDesc = null;
				if (userStatus.equals("0")) {
					userStatusDesc = "REGISTERED";
				} else if (userStatus.equals("1")) {
					userStatusDesc = "ACTIVE";
				} else if (userStatus.equals("2")) {
					userStatusDesc = "BLOCKED";
				} else {
					userStatusDesc = "N/A";
				}
				outTxn.setUserStatus(userStatusDesc);

				outTxn.setChangePass(rs.getString("change_password"));
				outTxn.setDate(rs.getString("date_added"));
				outTxn.setLastLogin(rs.getString("last_login"));
				outTxn.setOrg(rs.getString("org"));
				outTxn.setProfile(rs.getString("profile_name"));
				outTxn.setProfileId(rs.getInt("profile_id"));

				outTxn.setEmail(rs.getString("email"));

				container.addBean(outTxn);
				if( inTxn.isExportOp() )
					exportRawData.addBean( outTxn );

			} while (rs.next());
			
			if( inTxn.isExportOp() ){
				BData< BeanItemContainer< OutUser > > bData = new BData<>();
				bData.setData( exportRawData );
				out.setData( bData );
			}

			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

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
	public Out refreshMultiUserRecord(Collection<Item> records) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			int max = records.size();
			Iterator<Item> itr = records.iterator();

			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("SELECT DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u");
			sBuilder.append(" JOIN organization AS o ON o.id = u.organization_id");
			sBuilder.append(" JOIN profile AS p ON p.profile_id = u.profile_id");
			sBuilder.append(" WHERE u.user_id IN ( ");

			while (max != 0) {
				max--;
				if (max == 0)
					sBuilder.append("?");
				else
					sBuilder.append("?, ");
			}

			sBuilder.append(" ) LIMIT " + records.size());

			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			ps = conn.prepareStatement(sBuilder.toString());
			itr = records.iterator();
			max = records.size();
			while (itr.hasNext()) {
				ps.setLong(
						max,
						Long.valueOf(itr.next().getItemProperty("userId")
								.getValue().toString()));
				max--;
			}

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();
			if (!rs.next()) {
				out.setMsg("No user data.");
				out.setStatusCode(100);
				log.debug("No result");
				return out;
			}

			do {

				itr = records.iterator();
				max = records.size();
				Item record = null;

				log.debug("Total records: " + max);

				while (itr.hasNext()) {

					record = itr.next();

					record.getItemProperty("userId").setValue(
							rs.getLong("user_id"));
					record.getItemProperty("username").setValue(
							rs.getString("username"));
					record.getItemProperty("userSession").setValue(
							rs.getString("user_session"));

					String userStatus = rs.getString("status");
					String userStatusDesc = null;
					if (userStatus.equals("0")) {
						userStatusDesc = "REGISTERED";
					} else if (userStatus.equals("1")) {
						userStatusDesc = "ACTIVE";
					} else if (userStatus.equals("2")) {
						userStatusDesc = "BLOCKED";
					} else {
						userStatusDesc = "N/A";
					}
					// outTxn.setUserStatus( userStatusDesc );
					record.getItemProperty("userStatus").setValue(
							userStatusDesc);

					record.getItemProperty("changePass").setValue(
							rs.getString("change_password"));
					record.getItemProperty("dateAdded").setValue(
							rs.getString("date_added"));
					record.getItemProperty("lastLogin").setValue(
							rs.getString("last_login"));
					record.getItemProperty("org").setValue(rs.getString("org"));
					record.getItemProperty("profile").setValue(
							rs.getString("profile_name"));
					record.getItemProperty("profileId").setValue(
							rs.getInt("profile_id"));
					record.getItemProperty("email").setValue(
							rs.getString("email"));

					records.remove(record);
					log.debug("Max: " + max);
					max--;
					break;

				}

			} while (rs.next());

			log.debug("Max after operation: " + max);

			if (max == 0) {
				out.setStatusCode(1);
				out.setMsg("Refresh successful for selected record(s).");
			} else if (max > 0) {
				out.setStatusCode(2);
				out.setMsg("Some record(s) were not refreshed. Please try again later.");
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

	public Out blockMultiUserRecord(Collection<Item> records) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			int max = records.size();
			Iterator<Item> itr = records.iterator();

			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("UPDATE users AS u");
			sBuilder.append(" SET u.status = 2, u.user_session = NULL");
			sBuilder.append(" WHERE u.user_id IN ( ");

			while (max != 0) {
				max--;
				if (max == 0)
					sBuilder.append("?");
				else
					sBuilder.append("?, ");
			}

			sBuilder.append(" )  AND ( u.status = 1 )  LIMIT " + records.size());

			conn = dataSource.getConnection();
			conn.setReadOnly(false);

			ps = conn.prepareStatement(sBuilder.toString());
			itr = records.iterator();
			max = records.size();
			while (itr.hasNext()) {
				ps.setLong(
						max,
						Long.valueOf(itr.next().getItemProperty("userId")
								.getValue().toString()));
				max--;
			}

			log.debug("Query: " + ps.toString());

			ps.executeUpdate();

			out.setStatusCode(1);
			out.setMsg("Blocking selected user(s) was successful.");

		} catch (Exception e) {

			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	public Out activateMultiUserRecord(Collection<Item> records) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			int max = records.size();
			Iterator<Item> itr = records.iterator();

			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("UPDATE users AS u");
			sBuilder.append(" SET u.status = 1, u.user_session = NULL");
			sBuilder.append(" WHERE u.user_id IN ( ");

			while (max != 0) {
				max--;
				if (max == 0)
					sBuilder.append("?");
				else
					sBuilder.append("?, ");
			}

			sBuilder.append(" ) AND ( u.status = 2 )  LIMIT " + records.size());

			conn = dataSource.getConnection();
			conn.setReadOnly(false);

			ps = conn.prepareStatement(sBuilder.toString());
			itr = records.iterator();
			max = records.size();
			while (itr.hasNext()) {
				ps.setLong(
						max,
						Long.valueOf(itr.next().getItemProperty("userId")
								.getValue().toString()));
				max--;
			}

			log.debug("Query: " + ps.toString());

			ps.executeUpdate();

			out.setStatusCode(1);
			out.setMsg("Activating selected user(s) was successful.");

		} catch (Exception e) {

			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	public Out expireSessionMultiUserRecord(Collection<Item> records) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			int max = records.size();
			Iterator<Item> itr = records.iterator();

			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("UPDATE users AS u");
			sBuilder.append(" SET  u.user_session = NULL");
			sBuilder.append(" WHERE u.user_id IN ( ");

			while (max != 0) {
				max--;
				if (max == 0)
					sBuilder.append("?");
				else
					sBuilder.append("?, ");
			}

			sBuilder.append(" ) AND ( u.status = 1 ) LIMIT " + records.size());

			conn = dataSource.getConnection();
			conn.setReadOnly(false);

			ps = conn.prepareStatement(sBuilder.toString());
			itr = records.iterator();
			max = records.size();
			while (itr.hasNext()) {
				ps.setLong(
						max,
						Long.valueOf(itr.next().getItemProperty("userId")
								.getValue().toString()));
				max--;
			}

			log.debug("Query: " + ps.toString());

			ps.executeUpdate();

			out.setStatusCode(1);
			out.setMsg("Expiring selected user session(s) was successful.");

		} catch (Exception e) {

			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	public Out expirePassMultiUserRecord(Collection<Item> records) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			int max = records.size();
			Iterator<Item> itr = records.iterator();

			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("UPDATE users AS u");
			sBuilder.append(" SET  u.change_password = 1, u.user_session = NULL");
			sBuilder.append(" WHERE u.user_id IN ( ");

			while (max != 0) {
				max--;
				if (max == 0)
					sBuilder.append("?");
				else
					sBuilder.append("?, ");
			}

			sBuilder.append(" ) AND ( u.status = 1 OR u.status = 2 ) LIMIT "
					+ records.size());

			conn = dataSource.getConnection();
			conn.setReadOnly(false);

			ps = conn.prepareStatement(sBuilder.toString());
			itr = records.iterator();
			max = records.size();
			while (itr.hasNext()) {
				ps.setLong(
						max,
						Long.valueOf(itr.next().getItemProperty("userId")
								.getValue().toString()));
				max--;
			}

			log.debug("Query: " + ps.toString());

			ps.executeUpdate();

			out.setStatusCode(1);
			out.setMsg("Expiring selected user password(s) was successful.");

		} catch (Exception e) {

			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	public Out searchUserMeta(In in, OutTxnMeta outTxn) {

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			BData<?> bInData = in.getData();
			inTxn = (InTxn) bInData.getData();
			Map<String, Integer> searchFieldMap = new HashMap<>();
			String qVariable = "";

			String q = "SELECT COUNT( u.user_id ) AS total_records FROM users AS u ";
			q += " JOIN organization AS o ON o.id = u.organization_id";
			q += " JOIN profile AS p ON p.profile_id = u.profile_id";
			q += " WHERE";

			if (inTxn.getSearchUsername() != null) {
				qVariable += " u.username LIKE ? AND";
				searchFieldMap.put("username", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchEmail() != null) {
				qVariable += " u.email LIKE ? AND";
				searchFieldMap.put("email", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchOrg() != null) {
				qVariable += " o.name LIKE ? AND";
				searchFieldMap.put("org", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchProfile() != null) {
				qVariable += " p.profile_name ? AND";
				searchFieldMap.put("profile", searchFieldMap.size() + 1);
			}

			if (inTxn.getSearchUserStatus() != null) {
				if ("REGISTERED".contains(inTxn.getSearchUserStatus()
						.toUpperCase())) {

					qVariable += " ( u.status = 0 ";
					qVariable += " )";
					qVariable += " AND";

				} else if ("ACTIVE".contains(inTxn.getSearchUserStatus()
						.toUpperCase())) {

					qVariable += " ( u.status = 1 ";
					qVariable += " )";
					qVariable += " AND";

				} else if ("BLOCKED".contains(inTxn.getSearchUserStatus()
						.toUpperCase())) {

					qVariable += " ( u.status = 2 ";
					qVariable += " )";
					qVariable += " AND";

				} else {

					// Dummy status for no match
					qVariable += " ( u.status = '404' ) ";
					qVariable += " AND";
				}

			}

			q += qVariable;
			q += " u.user_id != ?";

			conn = dataSource.getConnection();
			conn.setReadOnly(true);
			ps = conn.prepareStatement(q);
			if (inTxn.getSearchUsername() != null) {
				ps.setString(searchFieldMap.get("username"),
						"%" + inTxn.getSearchUsername() + "%");
			}

			if (inTxn.getSearchEmail() != null) {
				ps.setString(searchFieldMap.get("email"),
						"%" + inTxn.getSearchEmail() + "%");
			}

			if (inTxn.getSearchProfile() != null) {
				ps.setString(searchFieldMap.get("profile"),
						"%" + inTxn.getSearchProfile() + "%");
			}

			if (inTxn.getSearchOrg() != null) {
				ps.setString(searchFieldMap.get("org"),
						"%" + inTxn.getSearchOrg() + "%");
			}

			int paramIndexOffset = searchFieldMap.size();
			ps.setLong(paramIndexOffset + 1, super.userAuthId);

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();
			if (!rs.next()) {
				log.debug("No result");

				return out;
			}

			outTxn.getTotalRecord().setValue(rs.getLong("total_records") + "");

			outTxn.getTotalRevenue().setValue("0");
			out.setStatusCode(1);
			out.setMsg("Txn meta computed successfully.");

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	@Override
	protected Out checkAuthorization() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		out = new Out();

		String q = "SELECT o.organization_status AS org_status, u.user_id, u.status, u.profile_id, u.user_session, u.change_password FROM users AS u";
		q += " JOIN organization AS o ON o.id = u.organization_id";
		q += " WHERE u.user_id = ?";
		q += " LIMIT 1;";

		try {

			conn = dataSource.getConnection();
			conn.setReadOnly(true);

			ps = conn.prepareStatement(q);
			ps.setLong(1, userAuthId);

			log.debug("Query: " + ps.toString());

			rs = ps.executeQuery();

			if (!rs.next()) {
				log.debug("No authorization data");
				out.setMsg("No authorization data");
				return out;
			}

			if (rs.getString("user_session") == null
					|| !rs.getString("user_session").equals(userAuthSession)) {
				// if( rs.getString( "user_session" ) == null || !rs.getString(
				// "user_session" ).equals( userSession ) ){

				log.debug("Login session expired");
				// log.debug(
				// "Admin username: "+username+" Session: "+userSession );
				out.setMsg("Not authorized [ Authorization session expired. ]");
				out.setStatusCode(403);
				return out;
			}

			if (rs.getShort("status") != 1) {
				log.debug("Not authorized");
				out.setMsg("Not authorized [ invalid account state ]");
				return out;
			}

			if (rs.getShort("change_password") == 1) {
				log.debug("Not authorized");
				out.setMsg("Not authorized [ Pending account password reset ]");
				return out;

			}

			if (rs.getShort("org_status") != 1) {
				log.debug("Not authorized");
				out.setMsg("Not authorized [ Invalid organization state ]");
				return out;
			}

			if (rs.getShort("profile_id") != 1) {
				log.debug("Not authorized");
				out.setMsg("Not authorized [ Insufficient profile permissions ]");
				return out;
			}

			BData<Long> bOutData = new BData<>();
			bOutData.setData(rs.getLong("user_id"));
			out.setData(bOutData);

			out.setStatusCode(1);
			out.setMsg("Account authorized for operation.");

		} catch (Exception e) {
			log.error(e.getMessage());
			out.setMsg("Could not complete operation. ");
			e.printStackTrace();

		} finally {
			connCleanUp(conn, ps, rs);
		}

		return out;
	}

	@Override
	public Out search(In in, BeanItemContainer<AbstractDataBean> container) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Out set(In in, BeanItemContainer<AbstractDataBean> container) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Out setMeta(In in, OutTxnMeta outSubscriber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Out searchMeta(In in, OutTxnMeta outSubscriber) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Out setExportData(In in,
			BeanItemContainer<AbstractDataBean> container) {
		BData<?> bInData = in.getData();
		inTxn = (InTxn) bInData.getData();

		log.debug("Page no: " + inTxn.getPage());
		log.debug("Page export limit: " + inTxn.getPageExportLimit());

		UserRepo repo = springAppContext.getBean(UserRepo.class);
		Pager pager = springAppContext.getBean(Pager.class);

		int exportPgLen = (int) Math.ceil(inTxn.getPageSize()
				* inTxn.getPageExportLimit());
		
		log.debug( "Export page length: "+exportPgLen );
		
		inTxn.setExportPgLen(exportPgLen);
		inTxn.setExportOp(true);
		out = this.searchUsers(in, new BeanItemContainer< OutUser > ( OutUser.class ) );
		inTxn.setExportOp(false);
		log.debug( "Feeder function returned. " );
		if (out.getStatusCode() != 1)
			return out;

		log.debug( "Proceeding to package for export. " );
		// TODO Repackage data for export
		
		ModelMapper packer = springAppContext.getBean( ModelMapper.class );

		
		BeanItemContainer< OutUser > rawData = (BeanItemContainer< OutUser >) out.getData().getData();
		Iterator< OutUser > itrRaw = rawData.getItemIds().iterator();
		BeanItemContainer<ExportUser> c = new BeanItemContainer<>( ExportUser.class );
		while (itrRaw.hasNext()) {
			OutUser tRaw = itrRaw.next();
			ExportUser t = packer.map( tRaw, ExportUser.class );
			c.addBean( t );
		}
		
		BData< BeanItemContainer< ExportUser > > bData = new BData<>();
		bData.setData( c );
		out.setData( bData );
		out.setStatusCode(1);
		out.setMsg("Export data set.");

		return out;
	}

	@Override
	public Out setExportDataMulti(In in,
			BeanItemContainer<AbstractDataBean> container,
			Collection<Item> records) {
		// TODO Auto-generated method stub
		return null;
	}

}
