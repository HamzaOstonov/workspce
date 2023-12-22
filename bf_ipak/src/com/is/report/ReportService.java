package com.is.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;

public class ReportService {
    public static List<Report> getReport(String alias)  {

        List<Report> list = new ArrayList<Report>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias);
                Statement s = c.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM Reports");
                while (rs.next()) {
                        list.add(new Report(
                                        rs.getLong("id"),
                                        rs.getString("rclass"),
                                        rs.getString("rfname"),
                                        rs.getString("rname")));
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return list;
}
    public static  Report getReport(int reportId,String alias) throws Exception {

        Report report = new Report();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias);
                PreparedStatement ps = c.prepareStatement("SELECT * FROM reports WHERE id=?");
                ps.setInt(1, reportId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        report = new Report();

                        report.setId(rs.getLong("id"));
                        report.setRclass(rs.getString("rclass"));
                        report.setRfname(rs.getString("rfname"));
                        report.setRname(rs.getString("rname"));
                }
        } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e);
        } finally {
                ConnectionPool.close(c);
        }
        return report;
}
    public static List<RepPar> getRepPar(long rid,String alias)  {

        List<RepPar> list = new ArrayList<RepPar>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("SELECT * FROM repparams where repid=? order by parid");
                ps.setLong(1,rid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new RepPar(
                                        rs.getLong("repid"),
                                        rs.getLong("parid"),
                                        rs.getString("partype"),
                                        rs.getString("pname"),
                                        rs.getString("psel")));
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return list;

}

    public static String  getRepPar(long rid,long pid,String alias)  {

        String res="S";
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias);
                PreparedStatement ps = c.prepareStatement("SELECT * FROM repparams where repid=? and parid=? ");
                ps.setLong(1,rid);
                ps.setLong(2,pid);
                ResultSet rs = ps.executeQuery();
               if (rs.next()) {
                        
            	   res =rs.getString("partype");
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return res;

}

}