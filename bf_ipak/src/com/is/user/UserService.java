package com.is.user;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserService {
    private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql = "select * from (SELECT * FROM v_bf_bank_users order by user_name ) ";
    private final static int ITERATION_NUMBER = 1000;


    public List<User> getUser(String branch) {

        List<User> list = new ArrayList<User>();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM v_bf_bank_users");
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("id"),
                        rs.getString("branch"),
                        rs.getString("user_name"),
                        rs.getString("full_name"),
                        rs.getString("title"),
                        rs.getInt("not_chg_pas"),
                        rs.getInt("locked"),
                        rs.getDate("date_open"),
                        rs.getDate("pwd_expired")));
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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

    private static List<FilterField> getFilterFields(UserFilter filter) {
        List<FilterField> flfields = new ArrayList<FilterField>();


        if (!CheckNull.isEmpty(filter.getId())) {
            flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
        }
        if (!CheckNull.isEmpty(filter.getBranch())) {
            flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
        }
        if (!CheckNull.isEmpty(filter.getUser_name())) {
            flfields.add(new FilterField(getCond(flfields) + "upper(user_name) like ?", "%" + filter.getUser_name().toUpperCase() + "%"));
        }
        if (!CheckNull.isEmpty(filter.getFull_name())) {
            flfields.add(new FilterField(getCond(flfields) + "upper(full_name) like ?", "%" + filter.getFull_name().toUpperCase() + "%"));
        }
        if (!CheckNull.isEmpty(filter.getTitle())) {
            flfields.add(new FilterField(getCond(flfields) + "title=?", filter.getTitle()));
        }
        if (!CheckNull.isEmpty(filter.getNot_chg_pas())) {
            flfields.add(new FilterField(getCond(flfields) + "not_chg_pas=?", filter.getNot_chg_pas()));
        }
        if (!CheckNull.isEmpty(filter.getLocked())) {
            flfields.add(new FilterField(getCond(flfields) + "locked=?", filter.getLocked()));
        }
        if (!CheckNull.isEmpty(filter.getDate_open())) {
            flfields.add(new FilterField(getCond(flfields) + "date_open=?", filter.getDate_open()));
        }
        if (!CheckNull.isEmpty(filter.getPwd_expired())) {
            flfields.add(new FilterField(getCond(flfields) + "pwd_expired=?", filter.getPwd_expired()));
        }

        //flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

        return flfields;
    }


    public static int getCount(UserFilter filter, String branch) {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields = getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM v_bf_bank_users ");
        if (flFields.size() > 0) {

            for (int i = 0; i < flFields.size(); i++) {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement(sql.toString());

            for (int k = 0; k < flFields.size(); k++) {
                ps.setObject(k + 1, flFields.get(k).getColobject());
            }
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return n;

    }


    public static List<User> getUsersFl(int pageIndex, int pageSize, UserFilter filter, String branch) {

        List<User> list = new ArrayList<User>();
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
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement(sql.toString());
            for (params = 0; params < flFields.size(); params++) {
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
            params++;
            ps.setInt(params++, v_upperbound);
            ps.setInt(params++, v_lowerbound);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("id"),
                        rs.getString("branch"),
                        rs.getString("user_name"),
                        rs.getString("full_name"),
                        rs.getString("title"),
                        rs.getInt("not_chg_pas"),
                        rs.getInt("locked"),
                        rs.getDate("date_open"),
                        rs.getDate("pwd_expired")));
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return list;

    }


    public static User getUser(int userId, String branch) {

        User user = new User();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM v_bf_bank_users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();

                user.setId(rs.getInt("id"));
                user.setBranch(rs.getString("branch"));
                user.setUser_name(rs.getString("user_name"));
                user.setFull_name(rs.getString("full_name"));
                user.setTitle(rs.getString("title"));
                user.setNot_chg_pas(rs.getInt("not_chg_pas"));
                user.setLocked(rs.getInt("locked"));
                user.setDate_open(rs.getDate("date_open"));
                user.setPwd_expired(rs.getDate("pwd_expired"));
            }
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return user;
    }

    public static User create(User user) {

           /* Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    ps = c.prepareStatement("SELECT SEQ_user.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            user.setId(rs.getInt("id"));
                    }
                    //----------------------
                    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                    byte[] bSalt = new byte[8];
                    random.nextBytes(bSalt);
                    byte[] bDigest = getHash(ITERATION_NUMBER,user.getPassword(),bSalt);
                    String sDigest = byteToBase64(bDigest);
                    String sSalt = byteToBase64(bSalt);
                    user.setPassword(sDigest);
                    user.setSalt(sSalt);
                    
                    //------------------------
                    
                    ps = c.prepareStatement("INSERT INTO users (id, username, password, salt, branch, subbranch, firstname, secondname, surname, ptype, pseries, pnumber, pauthority, state ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setInt(1,user.getId());
                    ps.setString(2,user.getUsername());
                    ps.setString(3,user.getPassword());
                    ps.setString(4,user.getSalt());
                    ps.setString(5,user.getBranch());
                    ps.setString(6,user.getSubbranch());
                    ps.setString(7,user.getFirstname());
                    ps.setString(8,user.getSecondname());
                    ps.setString(9,user.getSurname());
                    ps.setInt(10,user.getPtype());
                    ps.setString(11,user.getPseries());
                    ps.setString(12,user.getPnumber());
                    ps.setString(13,user.getPauthority());
                    ps.setInt(14,user.getState());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

            } finally {
                    ConnectionPool.close(c);
            }*/
        return user;
    }

    private static Pwd getDigest(String password) {
        Pwd pwd = new Pwd();
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bSalt = new byte[8];
            random.nextBytes(bSalt);
            byte[] bDigest = getHash(ITERATION_NUMBER, password, bSalt);
            pwd.setDigest(byteToBase64(bDigest));
            pwd.setSalt(byteToBase64(bSalt));
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        }

        return pwd;
    }

    private static boolean checkPwd(String password, String digest, String salt) throws Exception {
        byte[] bDigest = base64ToByte(digest);
        byte[] bSalt = base64ToByte(salt);
        byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);
        return Arrays.equals(proposedDigest, bDigest);
    }

    public static Res chPwd(String user, String pwd, String npwd, String oldPwd, String branch) {

        Connection c = null;
        String digest = "";
        String salt = "";
        Res res = new Res(99, "Системная ошибка");
        if (!pwd.equals(npwd)) {
            res.setCode(2);
            res.setName("Не совпадают новые пароли");
        }

        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c
                    .prepareStatement("SELECT * FROM users WHERE user_name=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                digest = rs.getString("password");
                salt = rs.getString("salt");
            }
            if (!checkPwd(oldPwd, digest, salt)) {
                res.setCode(1);
                res.setName("Не совпадают старый и новый пароль");
                return res;
            }

            Pwd pwd1 = getDigest(pwd);
            ps = c.prepareStatement("UPDATE users SET  password=?,salt=?  WHERE user_name=?");

            ps.setString(1, pwd1.getDigest());
            ps.setString(2, pwd1.getSalt());
            ps.setString(3, user);
            int i = ps.executeUpdate();
            c.commit();
            if (i == 1) {
                res.setCode(0);
                res.setName("Пароль успешно изменён");
            }

        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return res;

    }

    public static Res chPwd(String user, String pwd, String npwd, String opwd) {

        Connection c = null;

        Res res = new Res(0, "Системная ошибка");
        if (!pwd.equals(npwd)) {
            res.setCode(2);
            res.setName("Не совпадают новые пароли");
        }

        try {
            c = ConnectionPool.getConnection(user, opwd);

            Pwd pwd1 = getDigest(pwd);
            String s = "alter user " + user + " identified by " + npwd;
            PreparedStatement ps = c.prepareStatement(s);
            ps.execute();

            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("select u.*,s.user_name scheme from v_bf_bank_users u, ss_dblink_branch s where u.user_name = '" + user + "' and s.branch = u.branch");
            rs.next();
            String schema = rs.getString("scheme");

            st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + schema);

            rs = c.prepareStatement("select t.value from ss_const t where t.id = 1723").executeQuery();
            rs.next();
            String nmb = rs.getString("value");

            ps = c.prepareStatement("update users u set u.pwd_expired = sysdate + interval '" + nmb + "' day where u.user_name = '" + user + "'");
            ps.execute();

            c.commit();

            //	if (i == 1) {
            res.setCode(0);
            res.setName("Пароль успешно изменён");
            //	}

        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            res.setName(e.getMessage());

        } finally {
            ConnectionPool.close(c);
        }
        return res;

    }

    public static Res chPwdToUser(String user, String pwd, String npwd, String alias) {
        Connection c = null;
        Res res = new Res(0, "Системная ошибка");
        if (!pwd.equals(npwd)) {
            res.setCode(2);
            res.setName("Не совпадают новые пароли");
        }
        try {
            c = ConnectionPool.getConnection(alias);
            Pwd pwd1 = getDigest(pwd);
            String s = "alter user " + user + " identified by " + npwd;
            System.out.println("alter user " + user + " identified by " + npwd);
            PreparedStatement ps = c.prepareStatement(s);
            ps.execute();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select u.*,s.user_name scheme from v_bf_bank_users u, ss_dblink_branch s where u.user_name = '" + user + "' and s.branch = u.branch");
            rs.next();
            String schema = rs.getString("scheme");
            st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + schema);
            rs = c.prepareStatement("select t.value from ss_const t where t.id = 1723").executeQuery();
            rs.next();
            String nmb = rs.getString("value");
            ps = c.prepareStatement("update users u set u.pwd_expired = sysdate + interval '" + nmb + "' day where u.user_name = '" + user + "'");
            ps.execute();
            c.commit();
            //if (i == 1) {
            res.setCode(0);
            res.setName("Пароль успешно изменён");
            //}
        } catch (Exception e) {
            e.printStackTrace();
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            res.setName(e.getMessage());
        } finally {
            ConnectionPool.close(c);
        }
        return res;
    }

    public static OBJ_Res lg(String login, String password) {
        //System.out.println(login+"|"+password);
        Connection c = null;
        PreparedStatement ps;
        ResultSet rs = null;
        OBJ_Res res1 = new OBJ_Res();
        User user = new User();
        res1.setCode(0);
        try {
            //System.out.println("l:"+login+"p:"+password+"|\nhhhjheuh wqehrkuewh qwkhfkjdbksauhk yu\n\n\n\n\n\n");
            c = ConnectionPool.getConnection(login, password);
            //ps = c.prepareStatement("select u.*,b.user_name alias from v_bf_bank_users u, ss_dblink_branch b where u.branch=b.branch and u.user_name = upper(?)");
            ps = c.prepareStatement("" +
                    "select u.*,b.user_name alias " +
                    "from v_bf_bank_users u, ss_dblink_branch b " +
                    "where u.branch=b.branch and upper(u.user_name) = upper(?)");
            //System.out.println("sql in user: select u.*,b.user_name alias from v_bf_bank_users u, ss_dblink_branch b where u.branch=b.branch and u.user_name = upper("+login+")");
            ps.setString(1, login);
            rs = ps.executeQuery();

            if (rs.next()) {
                //res=rs.getInt("id");
                user.setId(rs.getInt("id"));
                user.setBranch(rs.getString("branch"));
                user.setUser_name(rs.getString("user_name"));
                user.setFull_name(rs.getString("full_name"));
                user.setTitle(rs.getString("title"));
                user.setNot_chg_pas(rs.getInt("not_chg_pas"));
                user.setLocked(rs.getInt("locked"));
                user.setDate_open(rs.getDate("date_open"));
                user.setPwd_expired(rs.getDate("pwd_expired"));
                user.setAlias(rs.getString("alias"));

                //System.out.println(rs.getString("user_name")+" "+rs.getString("title"));
                //System.out.println(getVal("select info.getday from dual",login,password));
                //System.out.println(getVal("select info.getbranch from dual",login,password));
                if (rs.next()) {
                    res1.setCode(-2);
                    res1.setObj(user);
                    return res1;
                }


                PreparedStatement ps_pwd = c.prepareStatement("Select (case " +
                        "            when   (SYSDATE + 3 > U.PWD_EXPIRED) then 1 " +
                        "            else " +
                        "             0 " +
                        "        end) as res " +
                        "      " +
                        "From   V_BF_BANK_USERS  U, " +
                        "       SS_DBLINK_BRANCH B " +
                        "Where  U.BRANCH = B.BRANCH " +
                        "       and U.USER_NAME = UPPER(?)");
                ps_pwd.setString(1, login);
                ResultSet rs1 = ps_pwd.executeQuery();
                rs1.next();
                if (rs1.getInt("res") == 1) res1.setCode(2);
            }

        } catch (Exception ex) {
            com.is.LtLogger.getLogger().error(CheckNull.getPstr(ex));
            res1.setCode(-1);
        } finally {
            ConnectionPool.close(c);
        }

        res1.setObj(user);
        return res1;
    }

    public static OBJ_Res lg(String login, String password, String branch) {
        //System.out.println(login+"|"+password);
        Connection c = null;
        PreparedStatement ps;
        ResultSet rs = null;
        OBJ_Res res1 = new OBJ_Res();
        User user = new User();
        res1.setCode(0);
        try {
            //System.out.println("l:"+login+"p:"+password+"|\nhhhjheuh wqehrkuewh qwkhfkjdbksauhk yu\n\n\n\n\n\n");
            c = ConnectionPool.getConnection(login, password);
            //ps = c.prepareStatement("select u.*,b.user_name alias from v_bf_bank_users u, ss_dblink_branch b where u.branch=b.branch and u.user_name = upper(?)");
            ps = c.prepareStatement(
                    "select u.*,b.user_name alias from v_bf_bank_users u, " +
                            "ss_dblink_branch b where u.branch=b.branch and upper(u.user_name) = upper(?) " +
                            "and u.branch = ?");
            //System.out.println("sql in user: select u.*,b.user_name alias from v_bf_bank_users u, ss_dblink_branch b where u.branch=b.branch and u.user_name = upper("+login+")");
            ps.setString(1, login);
            ps.setString(2, branch);
            rs = ps.executeQuery();

            if (rs.next()) {
                //res=rs.getInt("id");
                user.setId(rs.getInt("id"));
                user.setBranch(rs.getString("branch"));
                user.setUser_name(rs.getString("user_name"));
                user.setFull_name(rs.getString("full_name"));
                user.setTitle(rs.getString("title"));
                user.setNot_chg_pas(rs.getInt("not_chg_pas"));
                user.setLocked(rs.getInt("locked"));
                user.setDate_open(rs.getDate("date_open"));
                user.setPwd_expired(rs.getDate("pwd_expired"));
                user.setAlias(rs.getString("alias"));

                //System.out.println(rs.getString("user_name")+" "+rs.getString("title"));
                //System.out.println(getVal("select info.getday from dual",login,password));
                //System.out.println(getVal("select info.getbranch from dual",login,password));
                if (rs.next()) {
                    res1.setCode(-2);
                    res1.setObj(user);
                    return res1;
                }


                PreparedStatement ps_pwd = c.prepareStatement("Select (case " +
                        "            when   (SYSDATE + 3 > U.PWD_EXPIRED) then 1 " +
                        "            else " +
                        "             0 " +
                        "        end) as res " +
                        "      " +
                        "From   V_BF_BANK_USERS  U, " +
                        "       SS_DBLINK_BRANCH B " +
                        "Where  U.BRANCH = B.BRANCH " +
                        "       and U.USER_NAME = UPPER(?)");
                ps_pwd.setString(1, login);
                ResultSet rs1 = ps_pwd.executeQuery();
                rs1.next();
                if (rs1.getInt("res") == 1) res1.setCode(2);
            }

        } catch (Exception ex) {
            com.is.LtLogger.getLogger().error(CheckNull.getPstr(ex));
            res1.setCode(-1);
        } finally {
            ConnectionPool.close(c);
        }

        res1.setObj(user);
        return res1;
    }

    public static List<RefData> getUser_branches(String username) {
        return getRefData(
                "select t.BRANCH data, t.BRANCH label " +
                        "from v_bf_bank_users t where upper(t.USER_NAME) = upper('" + username + "')");
    }

    public static List<RefData> getRefData(String sql) {
        List list = new LinkedList();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next())
                list.add(
                        new RefData(rs.getString("data"),
                                rs.getString("label")));
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return list;
    }

    public static String get_alias(String branch) {
        String alias = null;
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement(
                    "select t.user_name res from ss_dblink_branch t where t.branch = ?");
            ps.setString(1, branch);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) alias = rs.getString("res");
        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            if (c != null) try {
                c.close();
            } catch (Exception e) {
            }
            ;
        }
        return alias;
    }

    public static boolean user_is_system(String login, String password) {
        boolean res = false;

        Connection c = null;
        PreparedStatement ps;
        ResultSet rs = null;
        OBJ_Res res1 = new OBJ_Res();
        User user = new User();
        res1.setCode(0);
        try {
            //System.out.println("l:"+login+"p:"+password+"|\nhhhjheuh wqehrkuewh qwkhfkjdbksauhk yu\n\n\n\n\n\n");
            c = ConnectionPool.getConnection(login, password);
            //ps = c.prepareStatement("select u.*,b.user_name alias from v_bf_bank_users u, ss_dblink_branch b where u.branch=b.branch and u.user_name = upper(?)");
            ps = c.prepareStatement("select count(*) res from v_bf_bank_users u, ss_dblink_branch b where u.branch=b.branch and u.user_name = upper(?)");
            //System.out.println("sql in user: select u.*,b.user_name alias from v_bf_bank_users u, ss_dblink_branch b where u.branch=b.branch and u.user_name = upper("+login+")");
            ps.setString(1, login);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getInt("res") > 1)
                    res = true;
            }

        } catch (Exception ex) {
            com.is.LtLogger.getLogger().error(CheckNull.getPstr(ex));
            res1.setCode(-1);
        } finally {
            ConnectionPool.close(c);
        }

        return res;
    }

    public static String getVal(String in, String login, String password) {
        String out = "null";
        Connection c = null;
        try {
            c = ConnectionPool.getConnection(login, password);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(in);
            if (rs.next()) {
                out = rs.getString(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return out;
    }

    public static int lg1(String login, String password, String branch) {
        boolean authenticated = false;
        int res = -1;
        int uid = -1;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            boolean userExist = true;
            if (login == null || password == null) {
                userExist = false;
                login = "";
                password = "";
            }
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement("SELECT ID,PASSWORD, SALT FROM v_bf_bank_users WHERE user_name = ?");
            ps.setString(1, login);
            rs = ps.executeQuery();
            String digest, salt;
            if (rs.next()) {
                digest = rs.getString("PASSWORD");
                salt = rs.getString("SALT");
                uid = rs.getInt("ID");
                if (digest == null || salt == null) {
                    throw new SQLException(
                            "Database inconsistant Salt or Digested Password altered");
                }
                if (rs.next()) { // Should not append, because login is the
                    // primary key
                    throw new SQLException(
                            "Database inconsistent two CREDENTIALS with the same LOGIN");
                }
            } else {
                digest = "000000000000000000000000000=";
                salt = "00000000000=";
                userExist = false;
            }

            byte[] bDigest = base64ToByte(digest);
            byte[] bSalt = base64ToByte(salt);

            // Compute the new DIGEST
            byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);
            authenticated = Arrays.equals(proposedDigest, bDigest) && userExist;
            if (authenticated) {
                res = uid;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }
        //return authenticated;
        return res;
    }


    public static void update(User user) {
/*
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE users  set username=?, branch=?, subbranch=?, firstname=?, secondname=?, surname=?, ptype=?, pseries=?, pnumber=?, pauthority=?, state=?  WHERE id=?");
                    
                    
                    ps.setString(1,user.getUsername());
                   // ps.setString(2,user.getPassword());
                    //ps.setString(3,user.getSalt());
                    ps.setString(2,user.getBranch());
                    ps.setString(3,user.getSubbranch());
                    ps.setString(4,user.getFirstname());
                    ps.setString(5,user.getSecondname());
                    ps.setString(6,user.getSurname());
                    ps.setInt(7,user.getPtype());
                    ps.setString(8,user.getPseries());
                    ps.setString(9,user.getPnumber());
                    ps.setString(10,user.getPauthority());
                    ps.setInt(11,user.getState());
                    ps.setInt(12,user.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                   
            } finally {
                    ConnectionPool.close(c);
            }
*/
    }

    public static void remove(User user) {
/*
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM user WHERE user_id=?");
                    ps.setInt(1, user.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            } finally {
                    ConnectionPool.close(c);
            }
            */
    }

    public static byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }

    public static String byteToBase64(byte[] data) {
        BASE64Encoder endecoder = new BASE64Encoder();
        return endecoder.encode(data);
    }

    public static byte[] base64ToByte(String data) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(data);
    }

    public static List<Role> getRole(String branch) {

        List<Role> list = new ArrayList<Role>();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM bf_Roles");
            while (rs.next()) {
                list.add(new Role(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("dataaccess")));
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return list;

    }

    public static Role create(Role role, String branch) {

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement("SELECT SEQ_bf_role.NEXTVAL id FROM DUAL");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role.setId(rs.getInt("id"));
            }
            ps = c.prepareStatement("INSERT INTO bf_roles (id, name, dataaccess ) VALUES (?,?,?)");

            ps.setInt(1, role.getId());
            ps.setString(2, role.getName());
            ps.setInt(3, role.getDataaccess());
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return role;
    }

    public static void update(Role role, String branch) {

        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("UPDATE bf_roles SET  name=?, dataaccess=?  WHERE id=?");


            ps.setString(1, role.getName());
            ps.setDouble(2, role.getDataaccess());
            ps.setLong(3, role.getId());
            ps.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }

    }

    public static int remove(Role role, String branch) {

        Connection c = null;
        int fl_res = 0;
        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("select 'X' from bf_user_roles t where t.roleid=?");
            ps.setInt(1, role.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fl_res = -1;
            } else {

                ps = c.prepareStatement("DELETE FROM bf_roles WHERE id=?");
                ps.setInt(1, role.getId());
                ps.executeUpdate();
                c.commit();
            }
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return fl_res;
    }

    public static List<Module> getModuleInRole(int role, String branch) {

        List<Module> list = new ArrayList<Module>();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("select m.* from bf_modules m where   m.mtype<>0 and " +
                    " exists(select null from bf_role_modules r where r.moduleid=m.id and r.roleid=?) order by name_ru");
            ps.setInt(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Module(
                        rs.getInt("id"),
                        rs.getInt("parentid"),
                        rs.getString("mtype"),
                        rs.getString("name_ru"),
                        rs.getString("mname"),
                        rs.getString("icon"),
                        rs.getInt("group_id")));
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return list;
    }


    public static List<Module> getModuleNotInRole(int role, String branch) {

        List<Module> list = new ArrayList<Module>();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("select m.* from bf_modules m where   m.mtype<>0 and " +
                    "not exists(select null from bf_role_modules r where r.moduleid=m.id and r.roleid=?) order by name_ru");
            ps.setInt(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Module(
                        rs.getInt("id"),
                        rs.getInt("parentid"),
                        rs.getString("mtype"),
                        rs.getString("name_ru"),
                        rs.getString("mname"),
                        rs.getString("icon"),
                        rs.getInt("group_id")));
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return list;
    }

    public static void addModule(int role, int module, String branch) {

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement("INSERT INTO bf_role_modules (roleid,moduleid ) VALUES (?,?)");
            ps.setInt(1, role);
            ps.setInt(2, module);
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
    }

    public static void removeModule(int role, int module, String branch) {

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement("delete from bf_role_modules where roleid=? and moduleid=?");
            ps.setInt(1, role);
            ps.setInt(2, module);
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
    }


    public static int getUser_Access(String alias, int uid) {
        int res = 0;
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(alias);

            PreparedStatement ps = c.prepareStatement("select min(r.dataaccess) res from bf_roles r where exists (select 'x' from bf_user_roles u where u.roleid=r.id and u.userid=?)");
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) res = rs.getInt("res");


        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return res;

    }


    public static List<Action> getActionInRole(int role, int module, String branch, int deal_id) {

        List<Action> list = new ArrayList<Action>();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
           /* System.out.println("select a.* from bf_actions a where a.mid="+module+" and "+
                    "exists(select null from bf_role_actions r "+
                    "where r.mid=a.mid and r.actionid=a.id"+
                    " and r.roleid="+role+" and r.deal_id="+deal_id+")");*/
            PreparedStatement ps = c.prepareStatement("select a.* from bf_actions a where a.mid=? and a.deal_id=(case when ? = 0 then a.deal_id else ? end) and " +
                    "exists(select null from bf_role_actions r " +
                    "where r.mid=a.mid and r.actionid=a.id" +
                    " and r.roleid=? and r.deal_id=(case when ? = 0 then r.deal_id else ? end)) order by name ");
            ps.setInt(1, module);
            ps.setInt(2, deal_id);
            ps.setInt(3, deal_id);
            ps.setInt(4, role);
            ps.setInt(5, deal_id);
            ps.setInt(6, deal_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Action(
                        rs.getInt("id"),
                        rs.getInt("mid"),
                        rs.getString("name"),
                        rs.getString("icon"),
                        rs.getInt("deal_id")));

            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return list;

    }

    public static List<Action> getActionNotInRole(int role, int module, String branch, int deal_id) {

        List<Action> list = new ArrayList<Action>();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("select a.* from bf_actions a where a.mid=? and a.deal_id=(case when ? = 0 then a.deal_id else ? end) and " +
                    "not exists(select null from bf_role_actions r " +
                    "where r.mid=a.mid and r.actionid=a.id" +
                    " and r.roleid=? and r.deal_id=(case when ? = 0 then r.deal_id else ? end)) order by name ");
            ps.setInt(1, module);
            ps.setInt(2, deal_id);
            ps.setInt(3, deal_id);
            ps.setInt(4, role);
            ps.setInt(5, deal_id);
            ps.setInt(6, deal_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Action(
                        rs.getInt("id"),
                        rs.getInt("mid"),
                        rs.getString("name"),
                        rs.getString("icon"),
                        rs.getInt("deal_id")));

            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return list;

    }

    public static void addAction(int role, int module, Action action, String branch) {

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection(branch);

            System.out.println("INSERT INTO bf_role_actions (roleid,mid,actionid,deal_id ) VALUES (" + role + "," + module + "," + action.getId() + "," + action.getDeal_id() + ")");

            ps = c.prepareStatement("INSERT INTO bf_role_actions (roleid,mid,actionid,deal_id ) VALUES (?,?,?,?)");

            ps.setInt(1, role);
            ps.setInt(2, module);
            ps.setInt(3, action.getId());
            ps.setInt(4, action.getDeal_id());
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
    }

    public static void removeAction(int role, int module, Action action, String branch) {

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement("delete from bf_role_actions where roleid=? and mid=? and actionid=? and deal_id=?");

            ps.setInt(1, role);
            ps.setInt(2, module);
            ps.setInt(3, action.getId());
            ps.setInt(4, action.getDeal_id());
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));


        } finally {
            ConnectionPool.close(c);
        }
    }

    public static List<Role> getUserInRole(int user, String user_branch, String branch) {

        List<Role> list = new ArrayList<Role>();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("select r.* from bf_roles r where  exists(select null from bf_user_roles u where u.userid=? and u.roleid=r.id and u.branch=?)");
            ps.setInt(1, user);
            ps.setString(2, user_branch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Role(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("dataaccess")));

            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return list;

    }

    public static List<Role> getUserNotInRole(int user, String user_branch, String branch) {

        List<Role> list = new ArrayList<Role>();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("select r.* from bf_roles r where  not exists(select null from bf_user_roles u where u.userid=? and u.roleid=r.id and u.branch=?)");
            ps.setInt(1, user);
            ps.setString(2, user_branch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Role(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("dataaccess")));

            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return list;

    }

    public static void addRole(int user, int role, String user_branch, String branch) {

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement("INSERT INTO bf_user_roles (userid,roleid, branch ) VALUES (?,?,?)");

            ps.setInt(1, user);
            ps.setInt(2, role);
            ps.setString(3, user_branch);
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
    }

    public static void removeRole(int user, int role, String user_branch, String branch) {

        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement("delete from bf_user_roles where userid=? and branch=? and roleid=?");
            ps.setInt(1, user);
            ps.setString(2, user_branch);
            ps.setInt(3, role);
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
    }

    public static int getAccess(int user, String branch) {

        int res = 0;
        Connection c = null;
        try {
            c = ConnectionPool.getConnection(branch);
            PreparedStatement ps = c.prepareStatement("select min(r.dataaccess) dataaccess from bf_roles r where exists (select 'x' from bf_user_roles u where u.roleid=r.id and u.userid=?)");
            ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getInt("dataaccess");
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
            ConnectionPool.close(c);
        }
        return res;

    }

    public static String test_select() {
        Connection c = null;
        String res = "";

        try {
            c = ConnectionPool.getConnection();

            PreparedStatement ps = c.prepareStatement("select * from test_nvarchar");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res += rs.getString("t1") + "||";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return res;
    }

    public static String lvalue(String val, List<RefData> dp) {
        String res = "";
        if (dp != null) {
            for (int i = 0; i < dp.size(); i++) {
                if (val.equals(dp.get(i).getData())) {
                    res = dp.get(i).getLabel();
                }
            }
        }
        return res;
    }

    public static List<RefData> getSSDBLinkBranch(String alias) {
        return RefDataService.getRefData("select branch data, user_name label from ss_dblink_branch order by branch", alias);
    }

    public static List<RefData> getAccess(String branch) {
        return RefDataService.getRefData("select -(t.id-(select max(d.id) from ss_access d)) data, t.name label from ss_access t", branch);
    }

    public static List<RefData> get_deal_id(int group_id, int module_id, String alias) {
        if (CheckNull.isEmpty(group_id)) {
            group_id = 0;
        }
        return RefDataService.getRefData("select id data, id||'  '||name label from ss_deal s where group_id = " + group_id + " and exists (select 'x' from bf_actions a where a.mid = " + module_id + " and s.id = a.deal_id)", alias);
    }

}
    
    
    
    
