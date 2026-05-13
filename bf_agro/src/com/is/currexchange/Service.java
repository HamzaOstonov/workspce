package com.is.currexchange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.ConnectionPool;

public class Service {
	
    public CurrExchange getCurrExchange(int currexchangeId) {

        CurrExchange currexchange = new CurrExchange();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM currexchange WHERE currexchange_id=?");
                ps.setInt(1, currexchangeId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        currexchange = new CurrExchange();
                        
                        currexchange.setId(rs.getInt("id"));
                        currexchange.setBank_dt(rs.getString("bank_dt"));
                        currexchange.setAcc_dt(rs.getString("acc_dt"));
                        currexchange.setCard_dt(rs.getString("card_dt"));
                        currexchange.setBank_ct(rs.getString("bank_ct"));
                        currexchange.setAcc_ct(rs.getString("acc_ct"));
                        currexchange.setCard_ct(rs.getString("card_ct"));
                        currexchange.setAmount(rs.getInt("amount"));
                        currexchange.setDdate(rs.getDate("ddate"));
                        currexchange.setCourse(rs.getInt("course"));
                        currexchange.setState(rs.getInt("state"));
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return currexchange;
}

public static CurrExchange create(CurrExchange currexchange)  {

        Connection c = null;
        PreparedStatement ps = null;
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SEQ_currexchange.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        currexchange.setId(rs.getInt("id"));
                }
                ps = c.prepareStatement("INSERT INTO currexchange (id, bank_dt, acc_dt, card_dt, bank_ct, acc_ct, card_ct, amount, ddate, course, state ) VALUES (?,?,?,?,?,?,?,?,sysdate,?,?)");
                
                ps.setInt(1,currexchange.getId());
                ps.setString(2,currexchange.getBank_dt());
                ps.setString(3,currexchange.getAcc_dt());
                ps.setString(4,currexchange.getCard_dt());
                ps.setString(5,currexchange.getBank_ct());
                ps.setString(6,currexchange.getAcc_ct());
                ps.setString(7,currexchange.getCard_ct());
                ps.setLong(8,currexchange.getAmount());
               // ps.setDate(9,currexchange.getDdate());
                ps.setInt(9, currexchange.getCourse());
                ps.setInt(10,currexchange.getState());
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return currexchange;
}

public static void update(CurrExchange currexchange)  {

        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("UPDATE currexchange SET  bank_dt=?, acc_dt=?, card_dt=?, bank_ct=?, acc_ct=?, card_ct=?, amount=?, course=?, state=?  WHERE id=?");
                
                ps.setInt(1,currexchange.getId());
                ps.setString(2,currexchange.getBank_dt());
                ps.setString(3,currexchange.getAcc_dt());
                ps.setString(4,currexchange.getCard_dt());
                ps.setString(5,currexchange.getBank_ct());
                ps.setString(6,currexchange.getAcc_ct());
                ps.setString(7,currexchange.getCard_ct());
                ps.setLong(8,currexchange.getAmount());
               // ps.setDate(9,currexchange.getDdate());
                ps.setInt(9,currexchange.getCourse());
                ps.setInt(10,currexchange.getState());
                ps.executeUpdate();
                c.commit();
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }

}


}
