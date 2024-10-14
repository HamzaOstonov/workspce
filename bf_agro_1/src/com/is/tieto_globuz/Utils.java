// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz;

import com.is.utils.Res;
import com.is.LtLogger;
import java.util.LinkedList;
import com.is.utils.RefData;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.is.utils.CheckNull;
import com.is.ISLogger;
import com.is.ConnectionPool;
import java.util.HashMap;
import java.util.Map;

public class Utils
{
    private static final Map<String, String> letters;
    
    static {
        (letters = new HashMap<String, String>()).put("\u0410", "A");
        Utils.letters.put("\u0411", "B");
        Utils.letters.put("\u0412", "V");
        Utils.letters.put("\u0413", "G");
        Utils.letters.put("\u0414", "D");
        Utils.letters.put("\u0415", "E");
        Utils.letters.put("\u0401", "Yo");
        Utils.letters.put("\u0416", "Dj");
        Utils.letters.put("\u0417", "Z");
        Utils.letters.put("\u0418", "I");
        Utils.letters.put("\u0419", "I");
        Utils.letters.put("\u041a", "K");
        Utils.letters.put("\u041b", "L");
        Utils.letters.put("\u041c", "M");
        Utils.letters.put("\u041d", "N");
        Utils.letters.put("\u041e", "O");
        Utils.letters.put("\u041f", "P");
        Utils.letters.put("\u0420", "R");
        Utils.letters.put("\u0421", "S");
        Utils.letters.put("\u0422", "T");
        Utils.letters.put("\u0423", "U");
        Utils.letters.put("\u0424", "F");
        Utils.letters.put("\u0425", "Kh");
        Utils.letters.put("\u0426", "C");
        Utils.letters.put("\u0427", "Ch");
        Utils.letters.put("\u0428", "Sh");
        Utils.letters.put("\u0429", "Sch");
        Utils.letters.put("\u042a", "");
        Utils.letters.put("\u042b", "Y");
        Utils.letters.put("\u042c", "");
        Utils.letters.put("\u042d", "E");
        Utils.letters.put("\u042e", "Yu");
        Utils.letters.put("\u042f", "Ya");
        Utils.letters.put("\u0430", "a");
        Utils.letters.put("\u0431", "b");
        Utils.letters.put("\u0432", "v");
        Utils.letters.put("\u0433", "g");
        Utils.letters.put("\u0434", "d");
        Utils.letters.put("\u0435", "e");
        Utils.letters.put("\u0451", "e");
        Utils.letters.put("\u0436", "zh");
        Utils.letters.put("\u0437", "z");
        Utils.letters.put("\u0438", "i");
        Utils.letters.put("\u0439", "i");
        Utils.letters.put("\u043a", "k");
        Utils.letters.put("\u043b", "l");
        Utils.letters.put("\u043c", "m");
        Utils.letters.put("\u043d", "n");
        Utils.letters.put("\u043e", "o");
        Utils.letters.put("\u043f", "p");
        Utils.letters.put("\u0440", "r");
        Utils.letters.put("\u0441", "s");
        Utils.letters.put("\u0442", "t");
        Utils.letters.put("\u0443", "u");
        Utils.letters.put("\u0444", "f");
        Utils.letters.put("\u0445", "h");
        Utils.letters.put("\u0446", "c");
        Utils.letters.put("\u0447", "ch");
        Utils.letters.put("\u0448", "sh");
        Utils.letters.put("\u0449", "sch");
        Utils.letters.put("\u044c", "'");
        Utils.letters.put("\u044b", "y");
        Utils.letters.put("\u044a", "'");
        Utils.letters.put("\u044d", "e");
        Utils.letters.put("\u044e", "yu");
        Utils.letters.put("\u044f", "ya");
        Utils.letters.put("\\", "");
        Utils.letters.put("/", "");
        Utils.letters.put("\u2116", "");
        Utils.letters.put("#", "");
        Utils.letters.put("$", "");
        Utils.letters.put("^", "");
        Utils.letters.put("%", "");
        Utils.letters.put("&", "");
        Utils.letters.put("*", "");
        Utils.letters.put("|", "");
        Utils.letters.put("+", "");
        Utils.letters.put("-", "");
        Utils.letters.put("=", "");
        Utils.letters.put("'", "");
        Utils.letters.put(".", "");
        Utils.letters.put(",", "");
        Utils.letters.put("(", "");
        Utils.letters.put(")", "");
        Utils.letters.put("!", "");
        Utils.letters.put("№", "");
        Utils.letters.put(";", "");
        Utils.letters.put("%", "");
        Utils.letters.put(":", "");
        Utils.letters.put("?", "");
        Utils.letters.put("*", "");
        Utils.letters.put("\\", "");
        Utils.letters.put("\"", "");
        Utils.letters.put("@", "");
        Utils.letters.put("_", "");
        Utils.letters.put("«", "");
        Utils.letters.put("»", "");
        Utils.letters.put("�?", "");
        Utils.letters.put("ў", "u");
        Utils.letters.put("Ў", "u");
        Utils.letters.put("Ғ", "u");
        Utils.letters.put("ғ", "u");
        Utils.letters.put("Қ", "u");
        Utils.letters.put("қ", "u");
        Utils.letters.put("Ҳ", "u");
        Utils.letters.put("ҳ", "u");
    }
    
    public static String getALias(final String branch) {
        String alias = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select user_name from ss_dblink_branch where branch = ?");
            ps.setString(1, branch);
            rs = ps.executeQuery();
            if (rs.next()) {
                alias = rs.getString(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return alias;
        }
        finally {
            close(rs);
            close(ps);
            ConnectionPool.close(c);
        }
        close(rs);
        close(ps);
        ConnectionPool.close(c);
        return alias;
    }
    
    public static String getExternalSession() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
        return dateFormat.format(new Date());
    }
    
    public static void close(final CallableStatement cs) {
        try {
            if (cs != null) {
                cs.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void rollback(final Connection c) {
        try {
            if (c != null) {
                c.rollback();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static List<RefData> getRefData(final String sql, final String branch) {
        final List<RefData> list = new LinkedList<RefData>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(branch);
            s = c.createStatement();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(new RefData(rs.getString("data"), rs.getString("label")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
            return list;
        }
        finally {
            close(rs);
            close(s);
            ConnectionPool.close(c);
        }
        close(rs);
        close(s);
        ConnectionPool.close(c);
        return list;
    }
    
    public static List<RefData> getRefData(final String sql, final String key, final String branch) {
        final List<RefData> list = new LinkedList<RefData>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(branch);
            ps = c.prepareStatement(sql);
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RefData(rs.getString("data"), rs.getString("label")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        finally {
            close(rs);
            close(ps);
            ConnectionPool.close(c);
        }
        close(rs);
        close(ps);
        ConnectionPool.close(c);
        return list;
    }
    
    public static List<RefData> getCurrAcc(final String key, final String branch) {
        return getRefData("select id data,id||' '||name label from account where acc_bal='20206'  and currency='840' and client=? and state=2", key, branch);
    }
    
    public static List<RefData> getDistr(final String key, final String branch) {
        return getRefData("select distr data, distr_name label, region_id from s_distr where act <> 'Z' and region_id=? order by distr", key, branch);
    }
    
    public static List<RefData> getMfo_name(final String mfo, final String branch) {
        return getRefData("select bank_id data, bank_name label from S_mfo where bank_id=?", mfo, branch);
    }
    
    public static List<RefData> getNation(final String branch) {
        return getRefData("select nat_id data, nat_name label from s_nation where act <> 'Z' order by nat_id", branch);
    }
    
    public static List<RefData> getRezCl(final String branch) {
        return getRefData("select kod_rez data, type_rez label from S_REZKL order by kod_rez", branch);
    }
    
    public static List<RefData> getProduct(final String branch) {
        return getRefData("select code data, name label from BF_globuz_CARD_SETTING where flag_user_view='1' and FLAG_CLIENT_TYPE='P' order by code", branch);
    }
    
    public static List<RefData> getGender(final String branch) {
        return getRefData("select CODE_SEX data, NAME_SEX label from S_SEX where act<>'Z' order by CODE_SEX", branch);
    }
    
    public static String getValue(final String key) {
        Connection c = null;
        String res = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT value FROM BF_SETS WHERE id=?");
            ps.setString(1, key);
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getString("value");
            }
        }
        catch (Exception e) {
            LtLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return res;
        }
        finally {
            close(rs);
            close(ps);
            ConnectionPool.close(c);
        }
        close(rs);
        close(ps);
        ConnectionPool.close(c);
        return res;
    }
    
    public static String getValueFromSql(final String sql, final String alias) {
        String value = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                value = rs.getString(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return value;
        }
        finally {
            close(rs);
            close(ps);
            ConnectionPool.close(c);
        }
        close(rs);
        close(ps);
        ConnectionPool.close(c);
        return value;
    }
    
    public static EmpcSettings getSettings(final String branch) {
        EmpcSettings settings = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from bf_empc_sets where branch = ?");
            ps.setString(1, branch);
            rs = ps.executeQuery();
            if (rs.next()) {
                settings = new EmpcSettings(rs.getString("bincod"), rs.getString("group_c"), rs.getString("bank_c"), rs.getString("range_id"), rs.getString("chip_app_id"), rs.getString("branch_id"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return settings;
        }
        finally {
            close(rs);
            close(ps);
            ConnectionPool.close(c);
        }
        close(rs);
        close(ps);
        ConnectionPool.close(c);
        return settings;
    }
    
    
    
    public static HashMap<String, EmpcSettings> getHSettings() {
        final HashMap<String, EmpcSettings> hsettings = new HashMap<String, EmpcSettings>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            //ps = c.prepareStatement("select * from SS_HUMO_TYPE_OF_CARD");
            ps = c.prepareStatement("select * from bf_empc_sets");
            rs = ps.executeQuery();
            while (rs.next()) {
                final EmpcSettings settings = new EmpcSettings(rs.getString("bincod"), rs.getString("group_c"), rs.getString("bank_c"), rs.getString("range_id"), rs.getString("chip_app_id"), rs.getString("branch_id"));
                hsettings.put(rs.getString("branch"), settings);
                //System.out.println(String.valueOf(rs.getString("branch")) + "----" + rs.getString("chip_app_id"));
            }
            //System.out.println("hsettings " + hsettings);
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return hsettings;
        }
        finally {
            close(rs);
            close(ps);
            ConnectionPool.close(c);
        }
        close(rs);
        close(ps);
        ConnectionPool.close(c);
        return hsettings;
    }
    
    public static void infoInit(final Connection c) throws Exception {
        CallableStatement cs = null;
        try {
            cs = c.prepareCall("{ call info.init() }");
            cs.execute();
        }
        finally {
            close(cs);
        }
        close(cs);
    }
    
    public static Date getInfoDate(final String alias, final Res res) {
        Date date = new Date();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            if (alias != null && !alias.equals("")) {
                c = ConnectionPool.getConnection(alias);
            }
            else {
                c = ConnectionPool.getConnection();
            }
            cs = c.prepareCall("{ call info.init() }");
            cs.execute();
            st = c.createStatement();
            rs = st.executeQuery("select info.GetDay from dual");
            if (rs.next()) {
                date = rs.getDate(1);
            }
        }
        catch (Exception e) {
            res.setCode(1);
            res.setName(CheckNull.getPstr(e));
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            e.printStackTrace();
            return date;
        }
        finally {
            close(rs);
            close(st);
            close(cs);
            ConnectionPool.close(c);
        }
        close(rs);
        close(st);
        close(cs);
        ConnectionPool.close(c);
        return date;
    }
    
    public static String toTranslit(final String text) {
        if (text == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); ++i) {
            final String l = text.substring(i, i + 1);
            if (Utils.letters.containsKey(l)) {
                sb.append(Utils.letters.get(l));
            }
            else {
                sb.append(l);
            }
        }
        return sb.toString();
    }
}
