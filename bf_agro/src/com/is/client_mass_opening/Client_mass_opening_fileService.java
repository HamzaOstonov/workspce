package com.is.client_mass_opening;

import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.media.AMedia;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import java.text.SimpleDateFormat;

public class Client_mass_opening_fileService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM Client_mass_opening_file ";

	public List<Client_mass_opening_file> getClient_mass_opening_file() {

		List<Client_mass_opening_file> list = new ArrayList<Client_mass_opening_file>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Client_mass_opening_file");
			while (rs.next()) {
				list.add(new Client_mass_opening_file(rs.getInt("id"), rs.getString("file_name"),
						rs.getString("sender"), rs.getString("v_date"), rs.getString("status")));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(Client_mass_opening_fileFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getFile_name())) {
			flfields.add(new FilterField(getCond(flfields) + "file_name=?", filter.getFile_name()));
		}
		if (!CheckNull.isEmpty(filter.getSender())) {
			flfields.add(new FilterField(getCond(flfields) + "sender=?", filter.getSender()));
		}
		if (!CheckNull.isEmpty(filter.getV_date())) {
			flfields.add(new FilterField(getCond(flfields) + "v_date=?", filter.getV_date()));
		}
		if (!CheckNull.isEmpty(filter.getStatus())) {
			flfields.add(new FilterField(getCond(flfields) + "status=?", filter.getStatus()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(Client_mass_opening_fileFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM Client_mass_opening_file");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<Client_mass_opening_file> getClient_mass_opening_filesFl(int pageIndex, int pageSize,
			Client_mass_opening_fileFilter filter) {

		List<Client_mass_opening_file> list = new ArrayList<Client_mass_opening_file>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Client_mass_opening_file(rs.getInt("id"), rs.getString("file_name"),
						rs.getString("sender"), rs.getString("v_date"), rs.getString("status")));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public Client_mass_opening_file getClient_mass_opening_file(int client_mass_opening_fileId) {

		Client_mass_opening_file client_mass_opening_file = new Client_mass_opening_file();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_file WHERE id=?");
			ps.setInt(1, client_mass_opening_fileId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				client_mass_opening_file = new Client_mass_opening_file();

				client_mass_opening_file.setId(rs.getInt("id"));
				client_mass_opening_file.setFile_name(rs.getString("file_name"));
				client_mass_opening_file.setSender(rs.getString("sender"));
				client_mass_opening_file.setV_date(rs.getString("v_date"));
				client_mass_opening_file.setStatus(rs.getString("status"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return client_mass_opening_file;
	}

	public static Client_mass_opening_file create(Client_mass_opening_file client_mass_opening_file) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_client_mass_opening_file.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				client_mass_opening_file.setId(rs.getInt("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO client_mass_opening_file (id, file_name, sender, v_date, status ) VALUES (?,?,?,?,?)");

			ps.setInt(1, client_mass_opening_file.getId());
			ps.setString(2, client_mass_opening_file.getFile_name());
			ps.setString(3, client_mass_opening_file.getSender());
			ps.setString(4, client_mass_opening_file.getV_date());
			ps.setString(5, client_mass_opening_file.getStatus());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return client_mass_opening_file;
	}

	public void update(int id_file, String status) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_file SET  status=?  WHERE id=?");
			ps.setString(1, status);
			ps.setInt(2, id_file);

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void remove(Client_mass_opening_file client_mass_opening_file) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM client_mass_opening_file WHERE id=?");
			ps.setLong(1, client_mass_opening_file.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public String insertFile(String file_name, String sender ) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Client_mass_opening_file client_mass_opening_file = new Client_mass_opening_file();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_client_mass_opening_file.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				client_mass_opening_file.setId(rs.getInt("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO client_mass_opening_file (id, file_name, sender, v_date, status ) VALUES (?,?,?,?,?)");

			ps.setInt(1, client_mass_opening_file.getId());
			ps.setString(2, file_name);
			ps.setString(3, sender );//означает что это 1резидент 2 нерезидент
			ps.setString(4, sdf.format(System.currentTimeMillis()));
			ps.setString(5, "1");
			ps.executeUpdate();
			c.commit();

		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
			

		} finally {
			ConnectionPool.close(c);

		}
		return client_mass_opening_file.getId().toString();

	}

	public static AMedia getExcel(String file) throws IOException {

		AMedia repmd = null;
		Workbook book = null;
		try {
			book = new XSSFWorkbook(new FileInputStream(file));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			book.write(out);
			out.close();
			byte[] arr = out.toByteArray();
			repmd = new AMedia("Example.xlsx", "xls", "application/vnd.ms-excel", arr);// Помнять название
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			book.close();
		}
		return repmd;
	}

	public void insertClientResident(List<ClientResident> client, String file_id) {// инсерт после всех проверок
		PreparedStatement ps = null;

		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"INSERT INTO client_mass_opening_resident (id, file_id, pinfl, lastname, date_birth,code_organization,card_type, phone,acc_group,passport_series,passport_number) VALUES (SEQ_client_mass_opening_resident.NEXTVAL,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, file_id);
			for (ClientResident clientResident : client) {
				ps.setString(2, clientResident.getPinfl());
				ps.setString(3, clientResident.getLastname());
				ps.setString(4, clientResident.getDate_birth());
				ps.setString(5, clientResident.getCode_organization());
				ps.setString(6, clientResident.getCard_type());
				ps.setString(7, clientResident.getPhone());
				ps.setString(8, clientResident.getAcc_group());
				ps.setString(9, clientResident.getPassport_series());
				ps.setString(10, clientResident.getPassport_number());
				ps.addBatch();
			}
			int[] results = ps.executeBatch();
			ISLogger.getLogger().info("Number of people in the insertClientResident: " + results.length);
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e);

		}
	}
	public void insertClientNotResident(List<ClientNotResident> client, String file_id) {// инсерт после всех проверок
		PreparedStatement ps = null;

		Connection c = null;
		try {
			c = ConnectionPool.getConnection();

			ps = c.prepareStatement(
					"INSERT INTO client_mass_opening_not_resident (id, file_id, pinfl,  firstname,  lastname,  patronymic,  nationality,citizenship, "
							+ " passport_serial,  passport_number,  date_birth,  code_gender,birth_place,  issued_by,  date_issue,  validity_expire,  organization_code, type_card,"
							+ "  phone,  region,  district,  address,acc_group) "
							+ "VALUES (SEQ_client_mass_opening_not_resident.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, file_id);
			for (ClientNotResident clientNotResident : client) {
				ps.setString(2, clientNotResident.getPinfl());
				ps.setString(3, clientNotResident.getFirstname());
				ps.setString(4, clientNotResident.getLastname());
				ps.setString(5, clientNotResident.getPatronymic());
				ps.setString(6, clientNotResident.getNationality());
				ps.setString(7, clientNotResident.getCitizenship());
				ps.setString(8, clientNotResident.getPassport_serial());
				ps.setString(9, clientNotResident.getPassport_number());
				ps.setString(10, clientNotResident.getDate_birth());
				ps.setString(11, clientNotResident.getCode_gender());
				ps.setString(12, clientNotResident.getBirth_place());
				ps.setString(13, clientNotResident.getIssued_by());
				ps.setString(14, clientNotResident.getDate_issue());
				ps.setString(15, clientNotResident.getValidity_expire());
				ps.setString(16, clientNotResident.getOrganization_code());
				ps.setString(17, clientNotResident.getType_card());
				ps.setString(18, clientNotResident.getPhone());
				ps.setString(19, clientNotResident.getRegion());
				ps.setString(20, clientNotResident.getDistrict());
				ps.setString(21, clientNotResident.getAddress());
				ps.setString(22, clientNotResident.getAcc_group());

				ps.addBatch();
			}
			int[] results = ps.executeBatch();
			ISLogger.getLogger().info("Number of people in the insertClientNotResident: " + results.length);
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e);
		}
	}
}