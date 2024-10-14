package com.is.dper_info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.dper_info.model.dper_info;
import com.is.dper_info.model.dper_infoFilter;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class DperInfoDao {
	private static DperInfoDao instance;
	private static String alias;
	private static dper_infoFilter filter;
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM dper_info ";
	
    private DperInfoDao(String alias){
    	this.alias = alias;
    }
    public static DperInfoDao getInstance(String alias){
    	if(instance == null) {
    		instance = new DperInfoDao(alias);
    	}
    	return instance;
    }
    public void setFilter(dper_infoFilter filter) {
		this.filter = filter;
	}
    
    private static String getCond(List<FilterField> flfields){
		if(flfields.size()>0){
			return " and ";
		}else
			return " where ";
	}

	
	public static List<FilterField> getFilterFields() {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getDate_begin())){
        	flfields.add(new FilterField(getCond(flfields)+ "v_date>=?",new java.sql.Date(filter.getDate_begin().getTime() )));
        }
        if(!CheckNull.isEmpty(filter.getDate_end())){
        	flfields.add(new FilterField(getCond(flfields)+ "v_date<=?",new java.sql.Date(filter.getDate_end().getTime() )));
        }
        if(!CheckNull.isEmpty(filter.getMbranch())){
                flfields.add(new FilterField(getCond(flfields)+ "mbranch=?",filter.getMbranch()));
        }
        if(!CheckNull.isEmpty(filter.getId())){
                flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
        }
        if(!CheckNull.isEmpty(filter.getVeoper())){
                flfields.add(new FilterField(getCond(flfields)+ "veoper=?",filter.getVeoper()));
        }
        if(!CheckNull.isEmpty(filter.getKind())){
                flfields.add(new FilterField(getCond(flfields)+ "kind=?",filter.getKind()));
        }
        if(!CheckNull.isEmpty(filter.getStrs())){
                flfields.add(new FilterField(getCond(flfields)+ "strs=?",filter.getStrs()));
        }
        if(!CheckNull.isEmpty(filter.getStrr())){
                flfields.add(new FilterField(getCond(flfields)+ "strr=?",filter.getStrr()));
        }
        if(!CheckNull.isEmpty(filter.getDistr())){
                flfields.add(new FilterField(getCond(flfields)+ "distr=?",filter.getDistr()));
        }
        if(filter.getSumma()!= null){
                flfields.add(new FilterField(getCond(flfields)+ "summa=?",filter.getSumma()));
        }
        if(!CheckNull.isEmpty(filter.getCurrency())){
                flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
        }
        if(!CheckNull.isEmpty(filter.getClient())){
                flfields.add(new FilterField(getCond(flfields)+ "client=?",filter.getClient()));
        }
        
        if(!CheckNull.isEmpty(filter.getRezident())){
                flfields.add(new FilterField(getCond(flfields)+ "rezident=?",filter.getRezident()));
        }
        if(!CheckNull.isEmpty(filter.getDoc_id())){
                flfields.add(new FilterField(getCond(flfields)+ "doc_id=?",filter.getDoc_id()));
        }
        if(!CheckNull.isEmpty(filter.getDoc_series())){
                flfields.add(new FilterField(getCond(flfields)+ "doc_series=?",filter.getDoc_series()));
        }
        if(!CheckNull.isEmpty(filter.getDoc_number())){
                flfields.add(new FilterField(getCond(flfields)+ "doc_number=?",filter.getDoc_number()));
        }
        if(!CheckNull.isEmpty(filter.getDoc_issue())){
                flfields.add(new FilterField(getCond(flfields)+ "doc_issue=?",filter.getDoc_issue()));
        }
        if(!CheckNull.isEmpty(filter.getDoc_date_issue())){
                flfields.add(new FilterField(getCond(flfields)+ "doc_date_issue=?",new java.sql.Date(filter.getDoc_date_issue().getTime())));
        }
        if(!CheckNull.isEmpty(filter.getState())){
                flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
        }
        if(!CheckNull.isEmpty(filter.getBirthday())){
                flfields.add(new FilterField(getCond(flfields)+ "birthday=?",new java.sql.Date(filter.getBirthday().getTime())));
        }
        if(filter.getProfit()!=null){
                flfields.add(new FilterField(getCond(flfields)+ "profit=?",filter.getProfit()));
        }
        if(!CheckNull.isEmpty(filter.getMtcn())){
                flfields.add(new FilterField(getCond(flfields)+ "mtcn=?",filter.getMtcn()));
        }
        if(!CheckNull.isEmpty(filter.getU1f2())){
                flfields.add(new FilterField(getCond(flfields)+ "u1f2=?",filter.getU1f2()));
        }
        if(filter.getCentsumma() != null){
                flfields.add(new FilterField(getCond(flfields)+ "centsumma=?",filter.getCentsumma()));
        }
	    if(!CheckNull.isEmpty(filter.getClient_i4())){
            flfields.add(new FilterField(getCond(flfields)+ "client_i4=?",filter.getClient_i4()));
	    }
        if(!CheckNull.isEmpty(filter.getClient_i5())){
                flfields.add(new FilterField(getCond(flfields)+ "client_i5=?",filter.getClient_i5()));
        }
        if(!CheckNull.isEmpty(filter.getClient_i6())){
                flfields.add(new FilterField(getCond(flfields)+ "client_i6=?",filter.getClient_i6()));
        }
        if(!CheckNull.isEmpty(filter.getClient_i7())){
                flfields.add(new FilterField(getCond(flfields)+ "client_i7=?",filter.getClient_i7()));
        }
        if(!CheckNull.isEmpty(filter.getClient_i8())){
                flfields.add(new FilterField(getCond(flfields)+ "client_i8=?",filter.getClient_i8()));
        }
        if(!CheckNull.isEmpty(filter.getClient_i9())){
                flfields.add(new FilterField(getCond(flfields)+ "client_i9=?",filter.getClient_i9()));
        }
        if(!CheckNull.isEmpty(filter.getClient_i11date())){
                flfields.add(new FilterField(getCond(flfields)+ "client_i11date=?",new java.sql.Date(filter.getClient_i11date().getTime())));
        }
        if(filter.getSumma2() != null){
                flfields.add(new FilterField(getCond(flfields)+ "summa2=?",filter.getSumma2()));
        }
        if(filter.getSumma3() != null){
                flfields.add(new FilterField(getCond(flfields)+ "summa3=?",filter.getSumma3()));
        }
        if(filter.getSumma4()!= null){
                flfields.add(new FilterField(getCond(flfields)+ "summa4=?",filter.getSumma4()));
        }
        if(filter.getSumma5() != null){
                flfields.add(new FilterField(getCond(flfields)+ "summa5=?",filter.getSumma5()));
        }
        if(!CheckNull.isEmpty(filter.getClient_i13code_str())){
                flfields.add(new FilterField(getCond(flfields)+ "client_i13code_str=?",filter.getClient_i13code_str()));
        }
        if(!CheckNull.isEmpty(filter.getRegion_offshor())){
                flfields.add(new FilterField(getCond(flfields)+ "region_offshor=?",filter.getRegion_offshor()));
        }
        if(!CheckNull.isEmpty(filter.getClient_grstr())){
                flfields.add(new FilterField(getCond(flfields)+ "client_grstr=?",filter.getClient_grstr()));
        }

        flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

          return flfields;
	}
	private static String getFiledsWithLike(dper_infoFilter filter){
    	StringBuffer sb = new StringBuffer(); 
    	if(!CheckNull.isEmpty(filter.getClient_name1())){
            sb.append(" and client_name1 like '%"+filter.getClient_name1().toUpperCase()+"%'");
	    }
	    if(!CheckNull.isEmpty(filter.getClient_name2())){
            sb.append(" and client_name2 like '%"+filter.getClient_name2().toUpperCase()+"%'");
	    }
	    if(!CheckNull.isEmpty(filter.getClient_name3())){
	    	sb.append(" and client_name3 like '%"+filter.getClient_name3().toUpperCase()+"%'");
	    }
	    if(!CheckNull.isEmpty(filter.getClient_i2())){
	    	sb.append(" and client_i2 like '%"+filter.getClient_i2().toUpperCase()+"%'");
	    }
	    if(!CheckNull.isEmpty(filter.getClient_i3())){
	    	sb.append(" and client_i3 like '%"+filter.getClient_i3().toUpperCase()+"%'");
	    }
        if(!CheckNull.isEmpty(filter.getPost_address())){
        	sb.append(" and post_address like '%"+filter.getPost_address().toUpperCase()+"%'");
        }
        if(!CheckNull.isEmpty(filter.getClient_i())){
        	sb.append(" and client_i like '%"+filter.getClient_i().toUpperCase()+"%'");
        }
        /*if(!CheckNull.isEmpty(filter.getClient_i9())){
        	sb.append(" and client_i9 like '%"+filter.getClient_i9().toUpperCase()+"%'");
        }*/
        if(!CheckNull.isEmpty(filter.getClient_i10())){
        	sb.append(" and client_i10 like '%"+filter.getClient_i10().toUpperCase()+"%'");
        }
        if(!CheckNull.isEmpty(filter.getClient_i12())){
        	sb.append(" and client_i12 like '%"+filter.getClient_i12().toUpperCase()+"%'");
        }
        return sb.toString();
    }
	
	public static int getCount() {
		Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM dper_info ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
                String filterWithLike = getFiledsWithLike(filter);
                sql.append(filterWithLike);
        }
        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement(sql.toString());

                    for(int k=0;k<flFields.size();k++){
                    ps.setObject(k+1, flFields.get(k).getColobject());
                    }
                    ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    n = rs.getInt(1);
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return n;
	}

	
	public static List<dper_info> getList(int pageIndex, int pageSize) {
		List<dper_info> list = new ArrayList<dper_info>();
        Connection c = null;
        int v_lowerbound = pageIndex + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        int params;
        List<FilterField> flFields =getFilterFields();

        StringBuffer sql = new StringBuffer();
        sql.append(psql1);
        sql.append(msql);
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
                String filterWithLike = getFiledsWithLike(filter);
                sql.append(filterWithLike);
        }
        sql.append(psql2);

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement(sql.toString());
                for(params=0;params<flFields.size();params++){
                ps.setObject(params+1, flFields.get(params).getColobject());
                }
                params++;
                ps.setInt(params++,v_upperbound);
                ps.setInt(params++,v_lowerbound);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new dper_info(
                                        rs.getString("branch"),
                                        rs.getString("mbranch"),
                                        rs.getLong("id"),
                                        rs.getString("veoper"),
                                        rs.getString("kind"),
                                        rs.getString("strs"),
                                        rs.getString("strr"),
                                        rs.getString("distr"),
                                        rs.getBigDecimal("summa"),
                                        rs.getString("currency"),
                                        rs.getDate("v_date"),
                                        rs.getString("client"),
                                        rs.getString("client_name1"),
                                        rs.getString("client_name2"),
                                        rs.getString("client_name3"),
                                        rs.getString("rezident"),
                                        rs.getString("doc_id"),
                                        rs.getString("doc_series"),
                                        rs.getString("doc_number"),
                                        rs.getString("doc_issue"),
                                        rs.getDate("doc_date_issue"),
                                        rs.getString("client_i"),
                                        rs.getLong("state"),
                                        rs.getString("post_address"),
                                        rs.getDate("birthday"),
                                        rs.getBigDecimal("profit"),
                                        rs.getString("mtcn"),
                                        rs.getInt("u1f2"),
                                        rs.getBigDecimal("centsumma"),
                                        rs.getString("client_i2"),
                                        rs.getString("client_i3"),
                                        rs.getString("client_i4"),
                                        rs.getString("client_i5"),
                                        rs.getString("client_i6"),
                                        rs.getString("client_i7"),
                                        rs.getString("client_i8"),
                                        rs.getString("client_i9"),
                                        rs.getString("client_i10"),
                                        rs.getDate("client_i11date"),
                                        rs.getString("client_i12"),
                                        rs.getBigDecimal("summa2"),
                                        rs.getBigDecimal("summa3"),
                                        rs.getBigDecimal("summa4"),
                                        rs.getBigDecimal("summa5"),
                                        rs.getString("client_i13code_str"),
                                        rs.getString("region_offshor"),
                                        rs.getString("client_grstr")));
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return list;
	}
	
	public static int getCount(dper_infoFilter filter, String alias) {
		Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM dper_info ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
                String filterWithLike = getFiledsWithLike(filter);
                sql.append(filterWithLike);
        }
        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement(sql.toString());

                    for(int k=0;k<flFields.size();k++){
                    ps.setObject(k+1, flFields.get(k).getColobject());
                    }
                    ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    n = rs.getInt(1);
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return n;
	}
	
	public static List<dper_info> getList(int pageIndex, int pageSize, dper_infoFilter filter, String alias) {
		List<dper_info> list = new ArrayList<dper_info>();
        Connection c = null;
        int v_lowerbound = pageIndex + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        int params;
        List<FilterField> flFields =getFilterFields();

        StringBuffer sql = new StringBuffer();
        sql.append(psql1);
        sql.append(msql);
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
                String filterWithLike = getFiledsWithLike(filter);
                sql.append(filterWithLike);
        }
        sql.append(psql2);

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement(sql.toString());
                for(params=0;params<flFields.size();params++){
                ps.setObject(params+1, flFields.get(params).getColobject());
                }
                params++;
                ps.setInt(params++,v_upperbound);
                ps.setInt(params++,v_lowerbound);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new dper_info(
                                        rs.getString("branch"),
                                        rs.getString("mbranch"),
                                        rs.getLong("id"),
                                        rs.getString("veoper"),
                                        rs.getString("kind"),
                                        rs.getString("strs"),
                                        rs.getString("strr"),
                                        rs.getString("distr"),
                                        rs.getBigDecimal("summa"),
                                        rs.getString("currency"),
                                        rs.getDate("v_date"),
                                        rs.getString("client"),
                                        rs.getString("client_name1"),
                                        rs.getString("client_name2"),
                                        rs.getString("client_name3"),
                                        rs.getString("rezident"),
                                        rs.getString("doc_id"),
                                        rs.getString("doc_series"),
                                        rs.getString("doc_number"),
                                        rs.getString("doc_issue"),
                                        rs.getDate("doc_date_issue"),
                                        rs.getString("client_i"),
                                        rs.getLong("state"),
                                        rs.getString("post_address"),
                                        rs.getDate("birthday"),
                                        rs.getBigDecimal("profit"),
                                        rs.getString("mtcn"),
                                        rs.getInt("u1f2"),
                                        rs.getBigDecimal("centsumma"),
                                        rs.getString("client_i2"),
                                        rs.getString("client_i3"),
                                        rs.getString("client_i4"),
                                        rs.getString("client_i5"),
                                        rs.getString("client_i6"),
                                        rs.getString("client_i7"),
                                        rs.getString("client_i8"),
                                        rs.getString("client_i9"),
                                        rs.getString("client_i10"),
                                        rs.getDate("client_i11date"),
                                        rs.getString("client_i12"),
                                        rs.getBigDecimal("summa2"),
                                        rs.getBigDecimal("summa3"),
                                        rs.getBigDecimal("summa4"),
                                        rs.getBigDecimal("summa5"),
                                        rs.getString("client_i13code_str"),
                                        rs.getString("region_offshor"),
                                        rs.getString("client_grstr")));
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return list;
	}



	
	public dper_info getItem(long itemId) {
		dper_info dper_info = new dper_info();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM dper_info WHERE id=?");
                ps.setLong(1, itemId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        dper_info = new dper_info();
                        
                        dper_info.setBranch(rs.getString("branch"));
                        dper_info.setMbranch(rs.getString("mbranch"));
                        dper_info.setId(rs.getLong("id"));
                        dper_info.setVeoper(rs.getString("veoper"));
                        dper_info.setKind(rs.getString("kind"));
                        dper_info.setStrs(rs.getString("strs"));
                        dper_info.setStrr(rs.getString("strr"));
                        dper_info.setDistr(rs.getString("distr"));
                        dper_info.setSumma(rs.getBigDecimal("summa"));
                        dper_info.setCurrency(rs.getString("currency"));
                        dper_info.setV_date(rs.getDate("v_date"));
                        dper_info.setClient(rs.getString("client"));
                        dper_info.setClient_name1(rs.getString("client_name1"));
                        dper_info.setClient_name2(rs.getString("client_name2"));
                        dper_info.setClient_name3(rs.getString("client_name3"));
                        dper_info.setRezident(rs.getString("rezident"));
                        dper_info.setDoc_id(rs.getString("doc_id"));
                        dper_info.setDoc_series(rs.getString("doc_series"));
                        dper_info.setDoc_number(rs.getString("doc_number"));
                        dper_info.setDoc_issue(rs.getString("doc_issue"));
                        dper_info.setDoc_date_issue(rs.getDate("doc_date_issue"));
                        dper_info.setClient_i(rs.getString("client_i"));
                        dper_info.setState(rs.getLong("state"));
                        dper_info.setPost_address(rs.getString("post_address"));
                        dper_info.setBirthday(rs.getDate("birthday"));
                        dper_info.setProfit(rs.getBigDecimal("profit"));
                        dper_info.setMtcn(rs.getString("mtcn"));
                        dper_info.setU1f2(rs.getInt("u1f2"));
                        dper_info.setCentsumma(rs.getBigDecimal("centsumma"));
                        dper_info.setClient_i2(rs.getString("client_i2"));
                        dper_info.setClient_i3(rs.getString("client_i3"));
                        dper_info.setClient_i4(rs.getString("client_i4"));
                        dper_info.setClient_i5(rs.getString("client_i5"));
                        dper_info.setClient_i6(rs.getString("client_i6"));
                        dper_info.setClient_i7(rs.getString("client_i7"));
                        dper_info.setClient_i8(rs.getString("client_i8"));
                        dper_info.setClient_i9(rs.getString("client_i9"));
                        dper_info.setClient_i10(rs.getString("client_i10"));
                        dper_info.setClient_i11date(rs.getDate("client_i11date"));
                        dper_info.setClient_i12(rs.getString("client_i12"));
                        dper_info.setSumma2(rs.getBigDecimal("summa2"));
                        dper_info.setSumma3(rs.getBigDecimal("summa3"));
                        dper_info.setSumma4(rs.getBigDecimal("summa4"));
                        dper_info.setSumma5(rs.getBigDecimal("summa5"));
                        dper_info.setClient_i13code_str(rs.getString("client_i13code_str"));
                        dper_info.setRegion_offshor(rs.getString("region_offshor"));
                        dper_info.setClient_grstr(rs.getString("client_grstr"));
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return dper_info;
	}
/*
	@Override
	public dper_info getItem(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public dper_info create(dper_info item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(dper_info item) {
		return 0;
	}

	@Override
	public int remove(dper_info item) {
		return 0;
	}
	@Override
	public <T1 extends dper_info> void setFilter(T1 filter) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public dper_info create(Connection c, dper_info item)
			throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int update(Connection c, dper_info item) throws IntegrationException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int remove(Connection c, dper_info item) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public dper_info getItem(Connection c, long itemId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public dper_info getItem(Connection c, String itemId) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
