package com.lonestarcell.mtn.model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.ExportUser;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.DateFormatFacRuntime;
import com.lonestarcell.mtn.model.util.Pager;
import com.lonestarcell.mtn.spring.user.entity.User;
import com.lonestarcell.mtn.spring.user.repo.UserRepo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public class MUser extends Model implements IModel<UserRepo> {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger(MUser.class.getName());

	private OutUser outTxn;
	private InTxn inTxn;

	public MUser(Long userId, String userSession, ApplicationContext cxt) {
		super(userId, userSession);
		this.springAppContext = cxt;

		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException("DataSource cannot be null.");
		}

		log.debug(" Model initialized successfully.");

	}

	public MUser(Long userId, String userSession) {
		super(userId, userSession);

		if (dataSource == null) {
			log.error("DataSource is null.");
			throw new IllegalStateException("DataSource cannot be null.");
		}

		log.debug(" Model initialized successfully.");

	}

	private Out setUsers(In in, BeanItemContainer<OutUser> container) {

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

				outTxn.setUserId(rs.getLong("user_id")+"");
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
				outTxn.setProfileId(rs.getInt("profile_id")+"");

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

	private Out searchUsers(In in, BeanItemContainer<OutUser> container) {

		/*
		 * Out out = this.checkAuthorization(); if (out.getStatusCode() != 1) {
		 * out.setStatusCode(100); return out; }
		 */

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

			BeanItemContainer<OutUser> exportRawData = null;

			if (inTxn.isExportOp()) {
				exportRawData = new BeanItemContainer<>(OutUser.class);
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

				outTxn.setUserId(rs.getLong("user_id")+"");
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
				outTxn.setProfileId(rs.getInt("profile_id")+"");

				outTxn.setEmail(rs.getString("email"));

				container.addBean(outTxn);
				if (inTxn.isExportOp())
					exportRawData.addBean(outTxn);

			} while (rs.next());

			if (inTxn.isExportOp()) {
				BData<BeanItemContainer<OutUser>> bData = new BData<>();
				bData.setData(exportRawData);
				out.setData(bData);
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

		/*
		 * Out out = this.checkAuthorization(); if (out.getStatusCode() != 1) {
		 * out.setStatusCode(100); return out; }
		 */

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
						Long.valueOf(itr.next().getItemProperty("column8")
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

					record.getItemProperty("column1").setReadOnly( false );
					record.getItemProperty("column2").setReadOnly( false );
					record.getItemProperty("column3").setReadOnly( false );
					
					record.getItemProperty("column4").setReadOnly( false );
					record.getItemProperty("column5").setReadOnly( false );
					record.getItemProperty("column6").setReadOnly( false );
					
					record.getItemProperty("column7").setReadOnly( false );
					record.getItemProperty("column8").setReadOnly( false );
					record.getItemProperty("column9").setReadOnly( false );
					
					record.getItemProperty("column10").setReadOnly( false );
					record.getItemProperty("date").setReadOnly( false );
					
					
					record.getItemProperty("column1").setValue(
							rs.getString("username"));
					
					record.getItemProperty("column10").setValue(
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
					record.getItemProperty("column4").setValue(
							userStatusDesc);

					record.getItemProperty("column7").setValue(
							rs.getString("change_password"));
					record.getItemProperty("date").setValue(
							rs.getString("date_added"));
					record.getItemProperty("column6").setValue(
							rs.getString("last_login"));
					record.getItemProperty("column3").setValue(rs.getString("org"));
					record.getItemProperty("column5").setValue(
							rs.getString("profile_name"));
					record.getItemProperty("column9").setValue(
							rs.getInt("profile_id"));
					record.getItemProperty("column2").setValue(
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

		/*
		 * Out out = this.checkAuthorization(); if (out.getStatusCode() != 1) {
		 * out.setStatusCode(100); return out; }
		 */

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
						Long.valueOf(itr.next().getItemProperty("column8")
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

		/*
		 * Out out = this.checkAuthorization(); if (out.getStatusCode() != 1) {
		 * out.setStatusCode(100); return out; }
		 */

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
						Long.valueOf(itr.next().getItemProperty("column8")
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

		/*
		 * Out out = this.checkAuthorization(); if (out.getStatusCode() != 1) {
		 * out.setStatusCode(100); return out; }
		 */

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
						Long.valueOf(itr.next().getItemProperty("column8")
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

		/*
		 * Out out = this.checkAuthorization(); if (out.getStatusCode() != 1) {
		 * out.setStatusCode(100); return out; }
		 */

		Connection conn = null;
		out = new Out();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			int max = records.size();
			Iterator<Item> itr = records.iterator();

			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("UPDATE users AS u");
			sBuilder.append(" SET  u.change_password = 1 ");
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
						Long.valueOf(itr.next().getItemProperty("column8")
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

		/*
		 * Out out = this.checkAuthorization(); if (out.getStatusCode() != 1) {
		 * out.setStatusCode(100); return out; }
		 */

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

			/*
			 * if (rs.getShort("profile_id") != 1) {
			 * log.debug("Not authorized");
			 * out.setMsg("Not authorized [ Insufficient profile permissions ]"
			 * ); return out; }
			 */

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

		Out out = this.checkAuthorization();
		if (out.getStatusCode() != 1) {
			out.setStatusCode(100);
			return out;
		}

		/*
		 * String q =
		 * "SELECT DATE_FORMAT( u.date_added, '%Y-%m-%e %T') AS date_added, DATE_FORMAT( u.last_login, '%Y-%m-%e %T') AS last_login, o.name AS org, u.username, u.email, u.status, u.profile_id, p.profile_name, u.change_password, u.user_session, user_id FROM users as u"
		 * ; q += " JOIN organization AS o ON o.id = u.organization_id"; q +=
		 * " JOIN profile AS p ON p.profile_id = u.profile_id"; q +=
		 * " WHERE u.user_id != ?"; q +=
		 * " ORDER BY u.date_added, u.last_login DESC"; q += " LIMIT ?, ?;";
		 */

		try {

			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();
			boolean isPgNav = inTxn.isPgNav();
			inTxn.setPgNav(false);

			// [ Initialize page & revenue on any db call??? ] Noooo... only on
			// some calls.
			if (!inTxn.isExportOp()) {
				if (!isPgNav) {
					OutTxnMeta meta = inTxn.getMeta();
					meta.getTotalRecord().setValue("0");
					meta.getTotalRevenue().setValue("0.00");
				}
			}

			UserRepo repo = springAppContext.getBean(UserRepo.class);
			if (repo == null) {
				log.error("User repo is null");
				out.setMsg("DAO error occured.");
				return out;
			}

			Page<User> pages = null;

			Pager pager = springAppContext.getBean(Pager.class);
			Map<String, Object> searchMap = inTxn.getSearchMap();
			Set<String> searchKeySet = searchMap.keySet();

			log.debug("MUser from date:" + inTxn.getfDate(), this);
			log.debug("MUser to date:" + inTxn.gettDate(), this);

			Pageable pgR = null;
			BeanItemContainer<OutUser> exportRawData = null;
			double tAmount = 0D;
			long rowCount = 0L;

			// Date fall back [ update fallback date as necessary for the user
			// data ]

			if (inTxn.getfDate() == null || inTxn.gettDate() == null) {
				inTxn.setfDate("2010-02-01");
				inTxn.settDate("2010-02-03");
			}

			Date fDate = DateFormatFacRuntime.toDate(inTxn.getfDate());

			if (inTxn.isExportOp()) {
				fDate = this.getExportFDate(inTxn, repo);
				pgR = pager.getPageRequest(0, inTxn.getExportPgLen());
				exportRawData = new BeanItemContainer<>(OutUser.class);
			} else {
				pgR = pager.getPageRequest(inTxn.getPage());
			}

			boolean isSearch = false;

			if (searchKeySet.size() != 0) {
				if (searchKeySet.contains("column1")) {

					Object val = searchMap.get("column1");

					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByUsername(pgR, userAuthId,
								(String) val, fDate, DateFormatFac
										.toDateUpperBound(inTxn.gettDate()));

					}

				} else if (searchKeySet.contains("column2")) {

					Object val = searchMap.get("column2");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByEmail(pgR, userAuthId,
								(String) val, fDate, DateFormatFac
										.toDateUpperBound(inTxn.gettDate()));

					}

					//Org
				} else if (searchKeySet.contains("column3")) {

					Object val = searchMap.get("column3");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByOrg(pgR, userAuthId,
								(String) val, fDate, DateFormatFac
										.toDateUpperBound(inTxn.gettDate()));

					}

					// Status
				} else if (searchKeySet.contains("column4")) {

					Object val = searchMap.get("column4");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						
						// Convert to db valid value.
						Short status = 404;
						if ("REGISTERED".contains(val.toString()
								.toUpperCase())) {
							status = 0; 

						} else if ("ACTIVE".contains(val.toString()
								.toUpperCase())) {
							status = 1;

						} else if ("BLOCKED".contains(val.toString()
								.toUpperCase())) {
							 status = 2;

						} 
						
						pages = repo.findPageByStatus(pgR, userAuthId,
								status, fDate, DateFormatFac
										.toDateUpperBound(inTxn.gettDate()));

					}

					// Profile
				} else if (searchKeySet.contains("column5")) {

					Object val = searchMap.get("column5");
					if (val != null && !val.toString().trim().isEmpty()) {
						isSearch = true;
						pages = repo.findPageByProfile(pgR, userAuthId,
								(String) val, fDate, DateFormatFac
										.toDateUpperBound(inTxn.gettDate()));

					}

				}

			}

			if (!isSearch) {
				if (inTxn.getfDate() != null && inTxn.gettDate() != null) {

					pages = repo.findPageByDateRange(pgR, userAuthId, fDate,
							DateFormatFac.toDateUpperBound(inTxn.gettDate()));
				}
			}

			if (pages == null) {
				log.info("Page object is null.");
				out.setMsg("DAO error occured.");
				return out;
			}

			if (pages.getNumberOfElements() == 0) {

				log.info("Record count is 0.");
				container.addBean(new OutUser());
				BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
				bOutData.setData(container);
				out.setData(bOutData);
				out.setMsg("No records found.");

				return out;
			}

			rowCount = pages.getTotalElements();
			log.info("Fetched record count: " + rowCount);
			Iterator<User> itr = pages.getContent().iterator();

			do {

				User user = itr.next();
				outTxn = new OutUser();

				outTxn.setUserId(user.getUserId()+"");
				outTxn.setUsername(user.getUsername());
				outTxn.setUserSession(user.getUserSession());

				Short userStatus = user.getStatus();
				String userStatusDesc = null;
				if (userStatus == 0) {
					userStatusDesc = "REGISTERED";
				} else if (userStatus == 1) {
					userStatusDesc = "ACTIVE";
				} else if (userStatus == 2) {
					userStatusDesc = "BLOCKED";
				} else {
					userStatusDesc = "N/A";
				}
				outTxn.setUserStatus(userStatusDesc);

				outTxn.setChangePass(user.getChangePassword() + "");
				outTxn.setDate(DateFormatFac.toString(user.getDateAdded()));
				outTxn.setLastLogin((user.getLastLogin() != null) ? DateFormatFac
						.toString(user.getLastLogin()) : "");
				outTxn.setOrg(user.getOrganization().getName());
				outTxn.setProfile(user.getProfile().getProfileName());
				outTxn.setProfileId(user.getProfile().getProfileId()+"");

				outTxn.setEmail(user.getEmail());

				container.addBean(outTxn);
				if (inTxn.isExportOp())
					exportRawData.addBean(outTxn);

			} while (itr.hasNext());

			if (inTxn.isExportOp()) {
				BData<BeanItemContainer<OutUser>> bData = new BData<>();
				bData.setData(exportRawData);
				out.setData(bData);
			} else {

				if (!isPgNav) {
					OutTxnMeta meta = inTxn.getMeta();
					meta.getTotalRecord().setValue(rowCount + "");
					meta.getTotalRevenue().setValue((tAmount / 100) + "");
				}
			}

			out.setStatusCode(1);
			out.setMsg("Data fetch successful.");

		} catch (Exception e) {
			container.addBean(new OutUser());
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);

			e.printStackTrace();
			out.setMsg("Data fetch error.");

		}

		return out;
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

		try {
			BData<?> bInData = in.getData();
			InTxn inTxn = (InTxn) bInData.getData();

			log.debug("Page no: " + inTxn.getPage());
			log.debug("Page export limit: " + inTxn.getPageExportLimit());
			int exportPgLen = (int) Math.ceil(inTxn.getPageSize()
					* inTxn.getPageExportLimit());

			log.info("Export pg len: " + exportPgLen);
			log.info("Export start page: " + inTxn.getPage());

			inTxn.setExportPgLen(exportPgLen);
			inTxn.setExportOp(true);

			out = this.search(in, container);
			inTxn.setExportOp(false);

			log.debug("Feeder function returned. ");
			if (out.getStatusCode() != 1)
				return out;

			log.debug("Proceeding to package for export. ");
			// TODO Repackage data for export

			ModelMapper packer = springAppContext.getBean(ModelMapper.class);

			BeanItemContainer<OutUser> rawData = (BeanItemContainer<OutUser>) out
					.getData().getData();
			Iterator<OutUser> itrRaw = rawData.getItemIds().iterator();
			BeanItemContainer<ExportUser> c = new BeanItemContainer<>(
					ExportUser.class);

			while (itrRaw.hasNext()) {
				OutUser tRaw = itrRaw.next();
				ExportUser t = packer.map(tRaw, ExportUser.class);
				c.addBean(t);
			}

			BData<BeanItemContainer<ExportUser>> bData = new BData<>();
			bData.setData(c);
			out.setData(bData);
			out.setStatusCode(1);
			out.setMsg("Export data set.");

		} catch (Exception ex) {

			container.addBean(new OutUser());
			BData<BeanItemContainer<AbstractDataBean>> bOutData = new BData<>();
			bOutData.setData(container);
			out.setData(bOutData);

			ex.printStackTrace();
			out.setMsg("Data fetch error.");
		}

		return out;
	}

	@Override
	public Out setExportDataMulti(In in,
			BeanItemContainer<AbstractDataBean> container,
			Collection<Item> records) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getExportFDate(InTxn inTxn, UserRepo repo) {

		int fromPgNo = inTxn.getExportFPgNo();
		log.info("In export F-PgNo " + fromPgNo);

		int excludePgNo = fromPgNo - 1;
		if (fromPgNo <= 1) {
			excludePgNo = 1;
			fromPgNo = 1;
		}

		// - find max date in excludePgNo page of that.
		Page<User> expoExcludePage = repo.findPageByDateRange(
				new Pager().getPageRequest(excludePgNo), userAuthId,
				DateFormatFacRuntime.toDate(inTxn.getfDate()),
				DateFormatFacRuntime.toDateUpperBound(inTxn.gettDate()));
		Date expoFDate = null;
		int tElements = expoExcludePage.getNumberOfElements();

		// - Get fast date of 1st page if fromPgNo == 1, else, get last date of
		// current page
		if (fromPgNo == 1)
			expoFDate = expoExcludePage.getContent().get(0).getDateAdded();
		else
			expoFDate = expoExcludePage.getContent().get(tElements - 1)
					.getDateAdded();
		// - Probable latest date in exclude page [ still under testing ]
		log.info("Export F-Date?: " + expoFDate.toString());
		return expoFDate;
	}

}
