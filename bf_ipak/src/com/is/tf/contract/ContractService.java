package com.is.tf.contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.tf.Accreditiv.Accreditiv;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.fund.Fund;
import com.is.tf.garant.Garant;
import com.is.tf.movefromex.MoveFromEx;
import com.is.tf.movefromim.Movefromim;
import com.is.tf.payment.Payment;
import com.is.tf.paymentref.Paymentref;
import com.is.tf.policy.Policy;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.RefObjDataService;

public class ContractService
{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from (SELECT * FROM TF_Contract order by p1t1)";
	private static HashMap<String, String> curr_ = null;
	
	
	public static List<RefData> getMyCurr(String key, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.kod_b label from tf_contract t, s_val v  where t.p1t1=?   and(t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)", key, alias);
	}
	
	public static List<RefData> getMyCurrency(String alias)
	{
		return RefDataService.getRefData("select kod data, kod_b||'  '||namev label from S_VAL order by data", alias);
	}
	
	public static List<RefData> getMyCurrency2(String kod, String alias)
	{
		return RefDataService.getRefData("select kod data, namev label from S_VAL where kod in (" + kod + ") order by data", alias);
	}
	
	public static List<RefData> getMyCurrG(String key, Long gid, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.kod_b label from tf_contract t, s_val v, tf_garant g where t.p1t1=? and t.p1t1=g.p1t18 and g.id=" + gid
				+ " and v.kod <> g.p5t18 and(t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)", key, alias);
	}
	
	public static List<RefData> getMyCurrAcr(String key, Long gid, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.kod_b label from tf_contract t, s_val v, tf_accreditiv g where t.p1t1=?  and  g.id=" + gid
				+ " and t.p1t1=g.p1t21 and v.kod <> g.p4t21 and(t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)", key, alias);
	}
	
	public static List<RefData> getMyCurrDeb(String key, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.kod_b label from tf_contract t, s_val v, tf_debet g where t.p1t1=?  and  t.p1t1=g.p1t24 and (t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)", key,
				alias);
	}
	
	public static List<RefData> getMyCurrPol(String key, Long gid, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.kod_b label from tf_contract t, s_val v, tf_policy g where t.p1t1=?  and  g.id=" + gid
				+ " and t.p1t1=g.p1t32 and g.id_contract=t.id and (t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)", key, alias);
	}
	
	public static HashMap<String, String> getHCurr(String branch)
	{
		curr_ = RefDataService.getHRefData("select kod data, namev label from S_VAL order by data", branch);
		return curr_;
	}
	
	public static List<RefData> getCurrOfAccreditiv(String idn, String aid, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.namev label from tf_accreditiv t, s_val v where t.p1t21='" + idn + "' and t.p2t21 = '" + aid + "' and v.kod = t.p4t21", alias);
	}
	
	public static List<RefCurrencyData> getCourseCurr_22t1_23t1(String idn, String date, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date('" + date + "','dd.mm.yyyy')) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v where t.p1t1='" + idn
				+ "' and(t.p22t1 = v.kod or t.p23t1 = v.kod)) cr order by course desc", alias);
	}
	
	public static List<RefData> getCurr_22t1_23t1(String idn, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.namev label from tf_contract t, s_val v where t.p1t1='" + idn + "' and(t.p22t1 = v.kod or t.p23t1 = v.kod)", alias);
	}
	
	public static List<RefData> getCurr_18t1_19t1(String idn, String alias)
	{
		return RefDataService.getRefData("select distinct v.kod data, v.namev label from tf_contract t, s_val v where t.p1t1='" + idn + "' and(t.p18t1 = v.kod or t.p19t1 = v.kod)", alias);
	}
	
	public static List<RefCurrencyData> getCourseCurr_18t1_19t1_withOther(String idn, String date, String curencies, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date('" + date + "','dd.mm.yyyy')) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v where (t.p1t1='"
				+ idn + "' and(t.p18t1 = v.kod or t.p19t1 = v.kod)) or v.kod in (" + curencies + ")) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getCourseCurr_18t1_19t1_withOther2(String idn, Long idc, String date, String curencies, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date('" + date + "','dd.mm.yyyy')) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v where (t.p1t1='"
				+ idn + "' and t.id='" + idc + "' and(t.p18t1 = v.kod or t.p19t1 = v.kod)) or v.kod in (" + curencies + ")) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getCourseCurr_18t1_19t1_withOther_sysdate(String idn, Long idc, String curencies, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,sysdate) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v where (t.p1t1='" + idn + "' and t.id='" + idc
				+ "' and (t.p18t1 = v.kod or t.p19t1 = v.kod)) or v.kod in (" + curencies + ")) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getMyCurr2only(String k1, String k2, String alias)
	{ 
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,sysdate) course from (select distinct v.kod kod, v.namev currency from s_val v where ('" + k1 + "' = v.kod or '" + k2
				+ "' = v.kod)) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getMyCurr_2_only(String date, String k1, String k2, String alias)
	{ 
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date('" + date + "','dd.mm.yyyy')) course from (select distinct v.kod kod, v.namev currency from s_val v where ('" + k1 + "' = v.kod or '"
				+ k2 + "' = v.kod)) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getMyCurrG(String cid, String date, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date('" + date
				+ "','dd.mm.yyyy')) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v, tf_garant g where t.p1t1='" + cid
				+ "' and t.p1t1=g.p1t18 and(t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getMyCurrAll(String cid, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date(sysdate)) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v where t.p1t1='" + cid
				+ "' and (t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getMyCurrA(String cid, String date, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date('" + date
				+ "','dd.mm.yyyy')) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v, tf_accreditiv g where t.p1t1='" + cid
				+ "' and t.p1t1=g.p1t21 and(t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getMyCurrP(String cid, String date, String alias)
	{
		return getRefCurrencyData("select cr.*, info.GETCOURSE(decode(cr.kod,'860','000',cr.kod),'000',1,to_date('" + date
				+ "','dd.mm.yyyy')) course from (select distinct v.kod kod, v.namev currency from tf_contract t, s_val v, tf_policy g where t.p1t1='" + cid
				+ "' and t.p1t1=g.p1t32 and(t.p18t1 = v.kod or t.p19t1 = v.kod or t.p22t1 = v.kod or t.p23t1 = v.kod)) cr order by course desc", alias);
	}
	
	public static List<RefCurrencyData> getRefCurrencyData(String sql, String branch)
	{
		List<RefCurrencyData> list = new LinkedList<RefCurrencyData>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(branch);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				list.add(
						new RefCurrencyData(rs.getString("kod"),
									rs.getString("currency"),
									rs.getDouble("course")));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
	}
	

	// ///////////////////////////////////
	public static List<RefData> getDebetmodes(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_debetmodes", alias);
	}
	
	public static List<RefData> getCountry(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_country", alias);
	}
	
	public static List<RefData> getAgreementState(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_agreementstate", alias);
	}
	
	public static List<RefData> getTransferType(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_transfertype", alias);
	}
	
	public static List<RefData> getIndastrZone(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_industrialzone order by data", alias);
	}
	
	public static List<RefData> getRegions(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_regions order by data", alias);
	}
	
	public static List<RefData> getContractType(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_contracttype order by data", alias);
	}
	
	public static List<RefData> getBenefitsinfo(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_benefitsinfo", alias);
	}
	
	public static List<RefData> getBenefitsinfo(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_benefitsinfo where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getPaymentSourse(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from Ss_Tf_Paymentsource where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getFundSourse(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_fundsource where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getFundtype(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from Ss_Tf_Fundtype where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getLoantype(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from Ss_Tf_Loantype where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getBenefitstype(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_benefitstype", alias);
	}
	
	public static List<RefData> getBasis(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_basis", alias);
	}
	
	public static List<RefData> getGnk(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_gnk", alias);
	}
	
	public static List<RefData> getSalereason(String alias)
	{
		return RefDataService.getRefData("select id data, name label from ss_tf_salereason", alias);
	}
	
	public static List<RefData> getReasons(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from Ss_Tf_Reasons where id in (" + id + ") ", alias);
	}
	
	public static List<RefData> getFundDest(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from SS_TF_FUNDDEST where id in (" + id + ") ", alias);
	}
	
	public static List<RefData> getFundDest(String alias)
	{
		return RefDataService.getRefData("select id data, name label from SS_TF_FUNDDEST ", alias);
	}
	
	public static List<RefData> getConditions(String alias)
	{
		return RefDataService.getRefData("select id data, name label from SS_TF_CONDITIONS ", alias);
	}
	
	public static List<RefData> getConditions(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from SS_TF_CONDITIONS where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getExporttype(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from SS_TF_EXPORTTYPE where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getSaletype(String id, String alias)
	{
		return RefDataService.getRefData("select id data, name label from SS_TF_SALETYPE where id in (" + id + ")", alias);
	}
	
	public static List<RefData> getPenalty_type(String alias)
	{
		return RefDataService.getRefData("select id data, name label from SS_TF_PENALTYTYPE ", alias);
	}
	
	public static List<RefData> getYesNo()
	{
		List<RefData> res = new ArrayList<RefData>();
		res.add(new RefData("1", "Да"));
		res.add(new RefData("0", "Нет"));
		res.add(new RefData("2", "Нет"));
		res.add(new RefData("3", "Частично"));
		return res;
	}
	
	public static List<RefData> getSrokIspolneniya()
	{
		List<RefData> res = new ArrayList<RefData>();
		res.add(new RefData("1", "до полного исполнения"));
		res.add(new RefData("2", "до конкретной даты"));
		return res;
	}
	
	public static List<RefData> getConfirm()
	{
		List<RefData> res = new ArrayList<RefData>();
		res.add(new RefData("0", "не подтверждено"));
		res.add(new RefData("1", "подтверждено"));
		return res;
	}
	
	public static List<RefData> getPrSourse()
	{
		List<RefData> res = new ArrayList<RefData>();
		res.add(new RefData("1", "Поступление средств от иностранного партнера"));
		res.add(new RefData("2", "Переброска средств с другого контракта"));
		res.add(new RefData("3", "Поступление страхового возмещения в иностранной валюте"));
		return res;
	}
	
	public static List<RefData> getStatusDoc()
	{
		List<RefData> res = new ArrayList<RefData>();
		res.add(new RefData("0", "Активный"));
		res.add(new RefData("1", "Удален"));
		res.add(new RefData("2", "В ожидании"));
		res.add(new RefData("3", "Скорректирован"));
		res.add(new RefData("4", "Отклонен"));
		res.add(new RefData("5", "Удален"));
		return res;
	}
	
	public static List<RefData> getProdaja()
	{
		List<RefData> res = new ArrayList<RefData>();
		res.add(new RefData("1", "Подлежит обязательной продаже"));
		res.add(new RefData("2", "Не подлежит обязательной продаже"));
		return res;
	}
	

	public static List<RefData> getIDN(String key, String alias)
	{ //
		return RefDataService.getRefData("select c.p1t1 data, c.p1t1 label from Tf_contract c where c.p61t1=? ", key, alias);
	}
	
	public static List<RefObjData> getIDNexpObj(String inn, String alias)
	{ //
		return RefObjDataService.getRefDataObj("select c.p1t1 refdata, c.p1t1 reflabel, c.* from tf_contract c where c.p61t1='" + inn + "' and c.p2t1 in (01,04,07,08,09,11) ", new Contract(), alias);
	}
	
	public static List<RefObjData> getFunds(String idn, String alias)
	{ //
		return RefObjDataService.getRefDataObj("select t.p2t35 refdata,'№:'||t.p3t35||'; Дата:'||to_char(t.p4t35,'yyyymmdd')||'; Сумма:'||t.p14t35||'; Валюта:'||t.p13t35 reflabel, t.* from tf_fund t where t.p1t35 = '" + idn + "' and t.p100t35='0'",
				new Fund(), alias);
	}
	
	public static List<RefObjData> getPolicy(String idn, String alias)
	{ // 
		return RefObjDataService.getRefDataObj("select t.p3t32 refdata,'№:'|| t.p3t32||'; Дата:'||to_char(t.p5t32,'yyyymmdd')||'; Сумма:'||t.p7t32||'; Валюта:'||t.p6t32 reflabel, t.* from tf_policy t where t.p1t32 = '" + idn + "' and t.p100t32='0'",
				new Policy(), alias);
	}
	
	public static List<RefObjData> getGarant(String idn, String alias)
	{ //
		return RefObjDataService.getRefDataObj("select t.p3t18 refdata,'№:'||t.p3t18||'; Дата:'||to_char(t.p4t18,'yyyymmdd')||'; Сумма:'||t.p6t18||'; Валюта:'||t.p5t18 reflabel, t.* from tf_garant t where t.p1t18 = '" + idn + "'", new Garant(),
				alias);
	}
	
	public static List<RefObjData> getAccredObj(String idn, String alias)
	{ // 
		return RefObjDataService.getRefDataObj("select t.p2t21 refdata, 'Аккредитив №:'||t.p2t21||'; Дата:'||to_char(t.p3t21,'dd-mm-yyyy')||';  Сумма:'||t.p5t21 ||';  Валюта:'||t.p4t21 reflabel, t.* from tf_accreditiv t where t.p1t21 = '" + idn
				+ "' and t.p100t21='0'", new Accreditiv(), alias);
	}
	
	public static List<RefObjData> getPaymentrefObj(String idn, String alias)
	{ //
		return RefObjDataService.getRefDataObj("select t.p2t37 refdata, '№:'||t.p2t37 reflabel, t.* from tf_paymentref t where t.p1t37 = '" + idn + "' and t.p100t37=0", new Paymentref(), alias);
	}
	
	public static List<RefObjData> getPaymentObj(String idn, String alias)
	{ //
		return RefObjDataService.getRefDataObj("select t.p22t44 refdata, '№:'||t.p23t44||'; Дата:'||t.p2t44||'; Сумма:'|| t.p16t44||'; Валюта:'|| t.p15t44 reflabel, t.* from tf_payment t where t.p1t44 = '" + idn + "' and p100t44='0'", new Payment(),
				alias);
	}
	
	public static List<RefObjData> getMoveFromExs(String idn, String alias)
	{
		return RefObjDataService.getRefDataObj(
				"select to_char(t.p2t52,'yyyymmdd')||'/'||t.p11t52||'/'||t.p9t52 refdata,'Дата:'||to_char(t.p2t52,'yyyymmdd')||'; сумма переброски:'||t.p11t52||'; Валюта расчета:'||t.p9t52 reflabel, t.* from tf_movefromex t where t.p1t52 = '" + idn
						+ "'", new MoveFromEx(), alias);
	}
	
	public static List<RefObjData> getMoveFromExs2(String idn, String alias)
	{
		return RefObjDataService.getRefDataObj("select to_char(t.p2t52,'yyyymmdd')||'/'||t.p13t52  refdata,'Дата:'||to_char(t.p2t52,'yyyymmdd')||'; Сумма оплаты за товар контр1:'||t.p13t52 reflabel, t.* from tf_movefromex t where t.p1t52 = '" + idn
				+ "' and t.p100t52='0'", new MoveFromEx(), alias);
	}
	
	public static List<RefObjData> getMoveFromIm(String idn, String alias)
	{
		return RefObjDataService.getRefDataObj("select to_char(t.p2t53,'yyyymmdd') refdata,'Дата:'||to_char(t.p2t53,'yyyymmdd')||'; Сумма:'||t.p18t53||'; Валюта:'||t.p16t53 reflabel, t.* from tf_movefromim t where t.p1t53 = '" + idn
				+ "' and t.p100t53='0'", new Movefromim(), alias);
	}
	
	public static List<RefData> getAgreement(String key, String alias)
	{ //
		return RefDataService.getRefData("select distinct a.p3t5 data, 'Доп.согл №:'||a.p3t5 label from tf_agreement a, tf_contract c where c.p1t1=? and c.id=a.id_contract", key, alias);
	}
	
	public static List<RefData> getPostup(String key, String alias)
	{
		return RefDataService.getRefData(
				"select distinct p3t35 data, 'Поступление №:'||f.p3t35||'; Дата:'||f.p4t35||';  Сумма:'||f.p14t35||';  Валюта:'||f.p13t35 label from tf_fund f , tf_contract c where c.p1t1=? and f.p1t35=c.p1t1 order by p3t35", key, alias);
	}
	
	public static List<RefData> getOplata(String key, String alias)
	{
		return RefDataService.getRefData("select p22t44 data, 'Платеж  №:'||f.p23t44||'; Дата:'||f.p2t44||';  Сумма:'||f.p16t44||';  Валюта:'||f.p15t44 label from tf_payment f , tf_contract c where c.p1t1=? and f.p1t44=c.p1t1 order by p23t44", key,
				alias);
	}
	
	public static List<RefObjData> getOplataRef(String idn, String alias)
	{
		return RefObjDataService.getRefDataObj(
					"select p22t44 refdata, 'Платеж  №:'||f.p23t44||'; Дата:'||f.p2t44||';  Сумма:'||f.p16t44||';  Валюта:'||f.p15t44 reflabel, f.* from tf_payment f , tf_contract c where c.p1t1= '"
							+ idn + "' and f.p1t44 = c.p1t1 order by p22t44", new Payment(), alias);
	}
	
	public static List<RefData> getAccredetivs(String idn, String alias)
	{ // 
		return RefDataService.getRefData("select t.p2t21 data, 'Аккредитив №'||t.p2t21 label from tf_accreditiv t where t.p1t21 = '" + idn + "'", alias);
	}
	
	public static List<RefData> getGarants(String idn, String alias)
	{ // 
		return RefDataService.getRefData("select t.p3t18 data, 'Гарантия №'||t.p3t18 label from tf_garant t where t.p1t18 = '" + idn + "'", alias);
	}
	
	public static List<RefData> getPolicies(String idn, String alias)
	{ // 
		return RefDataService.getRefData("select t.p3t32 data, t.p3t32 label from tf_policy t where t.p1t32 = '" + idn + "'", alias);
	}
	
	public static List<RefData> getYeisvoDocs(String alias)
	{
		return RefDataService.getRefData("select id data, upper(name) label from SS_TF_YEISVO_DOCS t order by id", alias);
	}
	
	// ////////////////////////////////////////////////////////////////////////////////
	
	public List<Contract> getContract()
	{
		
		List<Contract> list = new ArrayList<Contract>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TF_Contract");
			while (rs.next())
			{
				list.add(new Contract(
											rs.getLong("id"),
											rs.getString("p0t1"),
											rs.getString("p1t1"),
											rs.getString("p2t1"),
											rs.getString("p3t1"),
											rs.getString("p4t1"),
											rs.getString("p5t1"),
											rs.getDate("p6t1"),
											rs.getString("p7t1"),
											rs.getDate("p8t1"),
											rs.getString("p9t1"),
											rs.getString("p10t1"),
											rs.getString("p11t1"),
											rs.getString("p12t1"),
											rs.getString("p13t1"),
											rs.getString("p14t1"),
											rs.getString("p15t1"),
											rs.getString("p16t1"),
											rs.getString("p17t1"),
											rs.getString("p18t1"),
											rs.getString("p19t1"),
											rs.getDouble("p20t1"),
											rs.getDouble("p21t1"),
											rs.getString("p22t1"),
											rs.getString("p23t1"),
											rs.getString("p24t1"),
											rs.getString("p25t1"),
											rs.getString("p26t1"),
											rs.getString("p27t1"),
											rs.getString("p28t1"),
											rs.getString("p29t1"),
											rs.getString("p30t1"),
											rs.getString("p31t1"),
											rs.getDate("p32t1"),
											rs.getString("p33t1"),
											rs.getString("p34t1"),
											rs.getString("p35t1"),
											rs.getString("p36t1"),
											rs.getString("p37t1"),
											rs.getDate("p38t1"),
											rs.getString("p39t1"),
											rs.getDate("p40t1"),
											rs.getString("p41t1"),
											rs.getString("p42t1"),
											rs.getString("p43t1"),
											rs.getDate("p44t1"),
											rs.getString("p45t1"),
											rs.getString("p46t1"),
											rs.getString("p47t1"),
											rs.getDate("p48t1"),
											rs.getString("p49t1"),
											rs.getString("p50t1"),
											rs.getString("p51t1"),
											rs.getString("p52t1"),
											rs.getString("p53t1"),
											rs.getString("p54t1"),
											rs.getString("p55t1"),
											rs.getString("p56t1"),
											rs.getString("p57t1"),
											rs.getDate("p58t1"),
											rs.getString("p59t1"),
											rs.getString("p60t1"),
											rs.getString("p61t1"),
											rs.getString("p62t1"),
											rs.getDouble("p65t1"),
											rs.getDouble("p66t1"),
											rs.getDouble("p67t1"),
											rs.getDouble("p68t1"),
											rs.getDouble("p69t1"),
											rs.getDouble("p70t1"),
											rs.getDouble("p71t1"),
											rs.getDouble("p72t1"),
											rs.getDate("p73t1"),
											rs.getString("p100t1")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	private static String getCond(List<FilterField> flfields)
	{
		if (flfields.size() > 0)
		{
			return " and ";
		}
		else return " where ";
	}
	
	private static List<FilterField> getFilterFields(ContractFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getP0t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p0t1=?", filter.getP0t1()));
		}
		if (!CheckNull.isEmpty(filter.getP1t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p1t1=?", filter.getP1t1()));
		}
		if (!CheckNull.isEmpty(filter.getP2t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p2t1=?", filter.getP2t1()));
		}
		if (!CheckNull.isEmpty(filter.getP3t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p3t1=?", filter.getP3t1()));
		}
		if (!CheckNull.isEmpty(filter.getP4t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p4t1=?", filter.getP4t1()));
		}
		if (!CheckNull.isEmpty(filter.getP5t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p5t1=?", filter.getP5t1()));
		}
		if (!CheckNull.isEmpty(filter.getP6t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p6t1=?", filter.getP6t1()));
		}
		if (!CheckNull.isEmpty(filter.getP7t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p7t1=?", filter.getP7t1()));
		}
		if (!CheckNull.isEmpty(filter.getP8t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p8t1=?", filter.getP8t1()));
		}
		if (!CheckNull.isEmpty(filter.getP9t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p9t1=?", filter.getP9t1()));
		}
		if (!CheckNull.isEmpty(filter.getP10t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p10t1=?", filter.getP10t1()));
		}
		if (!CheckNull.isEmpty(filter.getP11t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p11t1=?", filter.getP11t1()));
		}
		if (!CheckNull.isEmpty(filter.getP12t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p12t1=?", filter.getP12t1()));
		}
		if (!CheckNull.isEmpty(filter.getP13t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p13t1=?", filter.getP13t1()));
		}
		if (!CheckNull.isEmpty(filter.getP14t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p14t1=?", filter.getP14t1()));
		}
		if (!CheckNull.isEmpty(filter.getP15t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p15t1=?", filter.getP15t1()));
		}
		if (!CheckNull.isEmpty(filter.getP16t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p16t1=?", filter.getP16t1()));
		}
		if (!CheckNull.isEmpty(filter.getP17t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p17t1=?", filter.getP17t1()));
		}
		if (!CheckNull.isEmpty(filter.getP18t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p18t1=?", filter.getP18t1()));
		}
		if (!CheckNull.isEmpty(filter.getP19t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p19t1=?", filter.getP19t1()));
		}
		if (!CheckNull.isEmpty(filter.getP20t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p20t1=?", filter.getP20t1()));
		}
		if (!CheckNull.isEmpty(filter.getP21t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p21t1=?", filter.getP21t1()));
		}
		if (!CheckNull.isEmpty(filter.getP22t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p22t1=?", filter.getP22t1()));
		}
		if (!CheckNull.isEmpty(filter.getP23t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p23t1=?", filter.getP23t1()));
		}
		if (!CheckNull.isEmpty(filter.getP24t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p24t1=?", filter.getP24t1()));
		}
		if (!CheckNull.isEmpty(filter.getP25t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p25t1=?", filter.getP25t1()));
		}
		if (!CheckNull.isEmpty(filter.getP26t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p26t1=?", filter.getP26t1()));
		}
		if (!CheckNull.isEmpty(filter.getP27t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p27t1=?", filter.getP27t1()));
		}
		if (!CheckNull.isEmpty(filter.getP28t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p28t1=?", filter.getP28t1()));
		}
		if (!CheckNull.isEmpty(filter.getP29t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p29t1=?", filter.getP29t1()));
		}
		if (!CheckNull.isEmpty(filter.getP30t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p30t1=?", filter.getP30t1()));
		}
		if (!CheckNull.isEmpty(filter.getP31t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p31t1=?", filter.getP31t1()));
		}
		if (!CheckNull.isEmpty(filter.getP32t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p32t1=?", filter.getP32t1()));
		}
		if (!CheckNull.isEmpty(filter.getP33t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p33t1=?", filter.getP33t1()));
		}
		if (!CheckNull.isEmpty(filter.getP34t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p34t1=?", filter.getP34t1()));
		}
		if (!CheckNull.isEmpty(filter.getP35t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p35t1=?", filter.getP35t1()));
		}
		if (!CheckNull.isEmpty(filter.getP36t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p36t1=?", filter.getP36t1()));
		}
		if (!CheckNull.isEmpty(filter.getP37t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p37t1=?", filter.getP37t1()));
		}
		if (!CheckNull.isEmpty(filter.getP38t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p38t1=?", filter.getP38t1()));
		}
		if (!CheckNull.isEmpty(filter.getP39t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p39t1=?", filter.getP39t1()));
		}
		if (!CheckNull.isEmpty(filter.getP40t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p40t1=?", filter.getP40t1()));
		}
		if (!CheckNull.isEmpty(filter.getP41t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p41t1=?", filter.getP41t1()));
		}
		if (!CheckNull.isEmpty(filter.getP42t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p42t1=?", filter.getP42t1()));
		}
		if (!CheckNull.isEmpty(filter.getP43t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p43t1=?", filter.getP43t1()));
		}
		if (!CheckNull.isEmpty(filter.getP44t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p44t1=?", filter.getP44t1()));
		}
		if (!CheckNull.isEmpty(filter.getP45t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p45t1=?", filter.getP45t1()));
		}
		if (!CheckNull.isEmpty(filter.getP46t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p46t1=?", filter.getP46t1()));
		}
		if (!CheckNull.isEmpty(filter.getP47t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p47t1=?", filter.getP47t1()));
		}
		if (!CheckNull.isEmpty(filter.getP48t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p48t1=?", filter.getP48t1()));
		}
		if (!CheckNull.isEmpty(filter.getP49t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p49t1=?", filter.getP49t1()));
		}
		if (!CheckNull.isEmpty(filter.getP50t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p50t1=?", filter.getP50t1()));
		}
		if (!CheckNull.isEmpty(filter.getP51t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p51t1=?", filter.getP51t1()));
		}
		if (!CheckNull.isEmpty(filter.getP52t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p52t1=?", filter.getP52t1()));
		}
		if (!CheckNull.isEmpty(filter.getP53t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p53t1=?", filter.getP53t1()));
		}
		if (!CheckNull.isEmpty(filter.getP54t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p54t1=?", filter.getP54t1()));
		}
		if (!CheckNull.isEmpty(filter.getP55t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p55t1=?", filter.getP55t1()));
		}
		if (!CheckNull.isEmpty(filter.getP56t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p56t1=?", filter.getP56t1()));
		}
		if (!CheckNull.isEmpty(filter.getP57t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p57t1=?", filter.getP57t1()));
		}
		if (!CheckNull.isEmpty(filter.getP58t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p58t1=?", filter.getP58t1()));
		}
		if (!CheckNull.isEmpty(filter.getP59t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p59t1=?", filter.getP59t1()));
		}
		if (!CheckNull.isEmpty(filter.getP60t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p60t1=?", filter.getP60t1()));
		}
		if (!CheckNull.isEmpty(filter.getP61t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p61t1=?", filter.getP61t1()));
		}
		if (!CheckNull.isEmpty(filter.getP62t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p62t1=?", filter.getP62t1()));
		}
		if (!CheckNull.isEmpty(filter.getP65t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p65t1=?", filter.getP65t1()));
		}
		if (!CheckNull.isEmpty(filter.getP66t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p66t1=?", filter.getP66t1()));
		}
		if (!CheckNull.isEmpty(filter.getP67t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p67t1=?", filter.getP67t1()));
		}
		if (!CheckNull.isEmpty(filter.getP68t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p68t1=?", filter.getP68t1()));
		}
		if (!CheckNull.isEmpty(filter.getP69t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p69t1=?", filter.getP69t1()));
		}
		if (!CheckNull.isEmpty(filter.getP70t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p70t1=?", filter.getP70t1()));
		}
		if (!CheckNull.isEmpty(filter.getP71t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p71t1=?", filter.getP71t1()));
		}
		if (!CheckNull.isEmpty(filter.getP72t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p72t1=?", filter.getP72t1()));
		}
		if (!CheckNull.isEmpty(filter.getP73t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p73t1=?", filter.getP73t1()));
		}
		if (!CheckNull.isEmpty(filter.getP100t1()))
		{
			flfields.add(new FilterField(getCond(flfields) + "p100t1=?", filter.getP100t1()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
	}
	
	public static int getCount(ContractFilter filter)
	{
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM TF_Contract ");
		if (flFields.size() > 0)
		{
			
			for (int i = 0; i < flFields.size(); i++)
			{
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			
			for (int k = 0; k < flFields.size(); k++)
			{
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				n = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return n;
		
	}
	
	public static List<Contract> getContractsFl(int pageIndex, int pageSize, ContractFilter filter)
	{
		
		List<Contract> list = new ArrayList<Contract>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0)
		{
			
			for (int i = 0; i < flFields.size(); i++)
			{
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++)
			{
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(new Contract(
											rs.getLong("id"),
											rs.getString("p0t1"),
											rs.getString("p1t1"),
											rs.getString("p2t1"),
											rs.getString("p3t1"),
											rs.getString("p4t1"),
											rs.getString("p5t1"),
											rs.getDate("p6t1"),
											rs.getString("p7t1"),
											rs.getDate("p8t1"),
											rs.getString("p9t1"),
											rs.getString("p10t1"),
											rs.getString("p11t1"),
											rs.getString("p12t1"),
											rs.getString("p13t1"),
											rs.getString("p14t1"),
											rs.getString("p15t1"),
											rs.getString("p16t1"),
											rs.getString("p17t1"),
											rs.getString("p18t1"),
											rs.getString("p19t1"),
											rs.getDouble("p20t1"),
											rs.getDouble("p21t1"),
											rs.getString("p22t1"),
											rs.getString("p23t1"),
											rs.getString("p24t1"),
											rs.getString("p25t1"),
											rs.getString("p26t1"),
											rs.getString("p27t1"),
											rs.getString("p28t1"),
											rs.getString("p29t1"),
											rs.getString("p30t1"),
											rs.getString("p31t1"),
											rs.getDate("p32t1"),
											rs.getString("p33t1"),
											rs.getString("p34t1"),
											rs.getString("p35t1"),
											rs.getString("p36t1"),
											rs.getString("p37t1"),
											rs.getDate("p38t1"),
											rs.getString("p39t1"),
											rs.getDate("p40t1"),
											rs.getString("p41t1"),
											rs.getString("p42t1"),
											rs.getString("p43t1"),
											rs.getDate("p44t1"),
											rs.getString("p45t1"),
											rs.getString("p46t1"),
											rs.getString("p47t1"),
											rs.getDate("p48t1"),
											rs.getString("p49t1"),
											rs.getString("p50t1"),
											rs.getString("p51t1"),
											rs.getString("p52t1"),
											rs.getString("p53t1"),
											rs.getString("p54t1"),
											rs.getString("p55t1"),
											rs.getString("p56t1"),
											rs.getString("p57t1"),
											rs.getDate("p58t1"),
											rs.getString("p59t1"),
											rs.getString("p60t1"),
											rs.getString("p61t1"),
											rs.getString("p62t1"),
											rs.getDouble("p65t1"),
											rs.getDouble("p66t1"),
											rs.getDouble("p67t1"),
											rs.getDouble("p68t1"),
											rs.getDouble("p69t1"),
											rs.getDouble("p70t1"),
											rs.getDouble("p71t1"),
											rs.getDouble("p72t1"),
											rs.getDate("p73t1"),
											rs.getString("p100t1")));
				
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public Contract getContract(int contractId)
	{
		
		Contract contract = new Contract();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_contract WHERE id=?");
			ps.setInt(1, contractId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				contract = new Contract();
				
				contract.setId(rs.getLong("id"));
				contract.setP0t1(rs.getString("p0t1"));
				contract.setP1t1(rs.getString("p1t1"));
				contract.setP2t1(rs.getString("p2t1"));
				contract.setP3t1(rs.getString("p3t1"));
				contract.setP4t1(rs.getString("p4t1"));
				contract.setP5t1(rs.getString("p5t1"));
				contract.setP6t1(rs.getDate("p6t1"));
				contract.setP7t1(rs.getString("p7t1"));
				contract.setP8t1(rs.getDate("p8t1"));
				contract.setP9t1(rs.getString("p9t1"));
				contract.setP10t1(rs.getString("p10t1"));
				contract.setP11t1(rs.getString("p11t1"));
				contract.setP12t1(rs.getString("p12t1"));
				contract.setP13t1(rs.getString("p13t1"));
				contract.setP14t1(rs.getString("p14t1"));
				contract.setP15t1(rs.getString("p15t1"));
				contract.setP16t1(rs.getString("p16t1"));
				contract.setP17t1(rs.getString("p17t1"));
				contract.setP18t1(rs.getString("p18t1"));
				contract.setP19t1(rs.getString("p19t1"));
				contract.setP20t1(rs.getDouble("p20t1"));
				contract.setP21t1(rs.getDouble("p21t1"));
				contract.setP22t1(rs.getString("p22t1"));
				contract.setP23t1(rs.getString("p23t1"));
				contract.setP24t1(rs.getString("p24t1"));
				contract.setP25t1(rs.getString("p25t1"));
				contract.setP26t1(rs.getString("p26t1"));
				contract.setP27t1(rs.getString("p27t1"));
				contract.setP28t1(rs.getString("p28t1"));
				contract.setP29t1(rs.getString("p29t1"));
				contract.setP30t1(rs.getString("p30t1"));
				contract.setP31t1(rs.getString("p31t1"));
				contract.setP32t1(rs.getDate("p32t1"));
				contract.setP33t1(rs.getString("p33t1"));
				contract.setP34t1(rs.getString("p34t1"));
				contract.setP35t1(rs.getString("p35t1"));
				contract.setP36t1(rs.getString("p36t1"));
				contract.setP37t1(rs.getString("p37t1"));
				contract.setP38t1(rs.getDate("p38t1"));
				contract.setP39t1(rs.getString("p39t1"));
				contract.setP40t1(rs.getDate("p40t1"));
				contract.setP41t1(rs.getString("p41t1"));
				contract.setP42t1(rs.getString("p42t1"));
				contract.setP43t1(rs.getString("p43t1"));
				contract.setP44t1(rs.getDate("p44t1"));
				contract.setP45t1(rs.getString("p45t1"));
				contract.setP46t1(rs.getString("p46t1"));
				contract.setP47t1(rs.getString("p47t1"));
				contract.setP48t1(rs.getDate("p48t1"));
				contract.setP49t1(rs.getString("p49t1"));
				contract.setP50t1(rs.getString("p50t1"));
				contract.setP51t1(rs.getString("p51t1"));
				contract.setP52t1(rs.getString("p52t1"));
				contract.setP53t1(rs.getString("p53t1"));
				contract.setP54t1(rs.getString("p54t1"));
				contract.setP55t1(rs.getString("p55t1"));
				contract.setP56t1(rs.getString("p56t1"));
				contract.setP57t1(rs.getString("p57t1"));
				contract.setP58t1(rs.getDate("p58t1"));
				contract.setP59t1(rs.getString("p59t1"));
				contract.setP60t1(rs.getString("p60t1"));
				contract.setP61t1(rs.getString("p61t1"));
				contract.setP62t1(rs.getString("p62t1"));
				contract.setP65t1(rs.getDouble("p65t1"));
				contract.setP66t1(rs.getDouble("p66t1"));
				contract.setP67t1(rs.getDouble("p67t1"));
				contract.setP68t1(rs.getDouble("p68t1"));
				contract.setP69t1(rs.getDouble("p69t1"));
				contract.setP70t1(rs.getDouble("p70t1"));
				contract.setP71t1(rs.getDouble("p71t1"));
				contract.setP72t1(rs.getDouble("p72t1"));
				contract.setP73t1(rs.getDate("p73t1"));
				contract.setP100t1(rs.getString("p100t1"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return contract;
	}
	
	public static Contract create(Contract contract)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_contract.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				contract.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO TF_contract (id, p0t1, p1t1, p2t1, p3t1, p4t1, p5t1, p6t1, p7t1, p8t1, p9t1, p10t1, p11t1, p12t1, p13t1, p14t1, p15t1, p16t1, p17t1, p18t1, p19t1, p20t1, p21t1, p22t1, p23t1, p24t1, p25t1, p26t1, p27t1, p28t1, p29t1, p30t1, p31t1, p32t1, p33t1, p34t1, p35t1, p36t1, p37t1, p38t1, p39t1, p40t1, p41t1, p42t1, p43t1, p44t1, p45t1, p46t1, p47t1, p48t1, p49t1, p50t1, p51t1, p52t1, p53t1, p54t1, p55t1, p56t1, p57t1, p58t1, p59t1, p60t1, p61t1, p62t1, p65t1, p66t1, p67t1, p68t1, p69t1, p70t1, p71t1, p72t1, p73t1, p100t1, ID_CONTRACT ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, contract.getId());
			ps.setString(2, contract.getP0t1());
			ps.setString(3, contract.getP1t1());
			ps.setString(4, contract.getP2t1());
			ps.setString(5, contract.getP3t1());
			ps.setString(6, contract.getP4t1());
			ps.setString(7, contract.getP5t1());
			ps.setDate(8, new java.sql.Date(contract.getP6t1().getTime()));
			ps.setString(9, contract.getP7t1());
			ps.setDate(10, new java.sql.Date(contract.getP8t1().getTime()));
			ps.setString(11, contract.getP9t1());
			ps.setString(12, contract.getP10t1());
			ps.setString(13, contract.getP11t1());
			ps.setString(14, contract.getP12t1());
			ps.setString(15, contract.getP13t1());
			ps.setString(16, contract.getP14t1());
			ps.setString(17, contract.getP15t1());
			ps.setString(18, contract.getP16t1());
			ps.setString(19, contract.getP17t1());
			ps.setString(20, contract.getP18t1());
			ps.setString(21, contract.getP19t1());
			ps.setDouble(22, contract.getP20t1());
			ps.setDouble(23, contract.getP21t1());
			ps.setString(24, contract.getP22t1());
			ps.setString(25, contract.getP23t1());
			ps.setString(26, contract.getP24t1());
			ps.setString(27, contract.getP25t1());
			ps.setString(28, contract.getP26t1());
			ps.setString(29, contract.getP27t1());
			ps.setString(30, contract.getP28t1());
			ps.setString(31, contract.getP29t1());
			ps.setString(32, contract.getP30t1());
			ps.setString(33, contract.getP31t1());
			ps.setDate(34, new java.sql.Date(contract.getP32t1().getTime()));
			ps.setString(35, contract.getP33t1());
			ps.setString(36, contract.getP34t1());
			ps.setString(37, contract.getP35t1());
			ps.setString(38, contract.getP36t1());
			ps.setString(39, contract.getP37t1());
			ps.setDate(40, new java.sql.Date(contract.getP38t1().getTime()));
			ps.setString(41, contract.getP39t1());
			ps.setDate(42, new java.sql.Date(contract.getP40t1().getTime()));
			ps.setString(43, contract.getP41t1());
			ps.setString(44, contract.getP42t1());
			ps.setString(45, contract.getP43t1());
			ps.setDate(46, new java.sql.Date(contract.getP44t1().getTime()));
			ps.setString(47, contract.getP45t1());
			ps.setString(48, contract.getP46t1());
			ps.setString(49, contract.getP47t1());
			ps.setDate(50, new java.sql.Date(contract.getP48t1().getTime()));
			ps.setString(51, contract.getP49t1());
			ps.setString(52, contract.getP50t1());
			ps.setString(53, contract.getP51t1());
			ps.setString(54, contract.getP52t1());
			ps.setString(55, contract.getP53t1());
			ps.setString(56, contract.getP54t1());
			ps.setString(57, contract.getP55t1());
			ps.setString(58, contract.getP56t1());
			ps.setString(59, contract.getP57t1());
			ps.setDate(60, new java.sql.Date(contract.getP58t1().getTime()));
			ps.setString(61, contract.getP59t1());
			ps.setString(62, contract.getP60t1());
			ps.setString(63, contract.getP61t1());
			ps.setString(64, contract.getP62t1());
			ps.setDouble(65, contract.getP65t1());
			ps.setDouble(66, contract.getP66t1());
			ps.setDouble(67, contract.getP67t1());
			ps.setDouble(68, contract.getP68t1());
			ps.setDouble(69, contract.getP69t1());
			ps.setDouble(70, contract.getP70t1());
			ps.setDouble(71, contract.getP71t1());
			ps.setDouble(72, contract.getP72t1());
			ps.setDate(73, new java.sql.Date(contract.getP73t1().getTime()));
			ps.setString(74, contract.getP100t1());
			
			ps.executeUpdate();
			c.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return contract;
	}
	
	public static void update(Contract contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE TF_contract SET id=?, p0t1=?, p1t1=?, p2t1=?, p3t1=?, p4t1=?, p5t1=?, p6t1=?, p7t1=?, p8t1=?, p9t1=?, p10t1=?, p11t1=?, p12t1=?, p13t1=?, p14t1=?, p15t1=?, p16t1=?, p17t1=?, p18t1=?, p19t1=?, p20t1=?, p21t1=?, p22t1=?, p23t1=?, p24t1=?, p25t1=?, p26t1=?, p27t1=?, p28t1=?, p29t1=?, p30t1=?, p31t1=?, p32t1=?, p33t1=?, p34t1=?, p35t1=?, p36t1=?, p37t1=?, p38t1=?, p39t1=?, p40t1=?, p41t1=?, p42t1=?, p43t1=?, p44t1=?, p45t1=?, p46t1=?, p47t1=?, p48t1=?, p49t1=?, p50t1=?, p51t1=?, p52t1=?, p53t1=?, p54t1=?, p55t1=?, p56t1=?, p57t1=?, p58t1=?, p59t1=?, p60t1=?, p61t1=?, p62t1=?, p65t1=?, p66t1=?, p67t1=?, p68t1=?, p69t1=?, p70t1=?, p71t1=?, p72t1=?, p73t1=?, p100t1=?  WHERE id=?");
			
			ps.setLong(1, contract.getId());
			ps.setString(2, contract.getP0t1());
			ps.setString(3, contract.getP1t1());
			ps.setString(4, contract.getP2t1());
			ps.setString(5, contract.getP3t1());
			ps.setString(6, contract.getP4t1());
			ps.setString(7, contract.getP5t1());
			ps.setDate(8, new java.sql.Date(contract.getP6t1().getTime()));
			ps.setString(9, contract.getP7t1());
			ps.setDate(10, new java.sql.Date(contract.getP8t1().getTime()));
			ps.setString(11, contract.getP9t1());
			ps.setString(12, contract.getP10t1());
			ps.setString(13, contract.getP11t1());
			ps.setString(14, contract.getP12t1());
			ps.setString(15, contract.getP13t1());
			ps.setString(16, contract.getP14t1());
			ps.setString(17, contract.getP15t1());
			ps.setString(18, contract.getP16t1());
			ps.setString(19, contract.getP17t1());
			ps.setString(20, contract.getP18t1());
			ps.setString(21, contract.getP19t1());
			ps.setDouble(22, contract.getP20t1());
			ps.setDouble(23, contract.getP21t1());
			ps.setString(24, contract.getP22t1());
			ps.setString(25, contract.getP23t1());
			ps.setString(26, contract.getP24t1());
			ps.setString(27, contract.getP25t1());
			ps.setString(28, contract.getP26t1());
			ps.setString(29, contract.getP27t1());
			ps.setString(30, contract.getP28t1());
			ps.setString(31, contract.getP29t1());
			ps.setString(32, contract.getP30t1());
			ps.setString(33, contract.getP31t1());
			ps.setDate(34, new java.sql.Date(contract.getP32t1().getTime()));
			ps.setString(35, contract.getP33t1());
			ps.setString(36, contract.getP34t1());
			ps.setString(37, contract.getP35t1());
			ps.setString(38, contract.getP36t1());
			ps.setString(39, contract.getP37t1());
			ps.setDate(40, new java.sql.Date(contract.getP38t1().getTime()));
			ps.setString(41, contract.getP39t1());
			ps.setDate(42, new java.sql.Date(contract.getP40t1().getTime()));
			ps.setString(43, contract.getP41t1());
			ps.setString(44, contract.getP42t1());
			ps.setString(45, contract.getP43t1());
			ps.setDate(46, new java.sql.Date(contract.getP44t1().getTime()));
			ps.setString(47, contract.getP45t1());
			ps.setString(48, contract.getP46t1());
			ps.setString(49, contract.getP47t1());
			ps.setDate(50, new java.sql.Date(contract.getP48t1().getTime()));
			ps.setString(51, contract.getP49t1());
			ps.setString(52, contract.getP50t1());
			ps.setString(53, contract.getP51t1());
			ps.setString(54, contract.getP52t1());
			ps.setString(55, contract.getP53t1());
			ps.setString(56, contract.getP54t1());
			ps.setString(57, contract.getP55t1());
			ps.setString(58, contract.getP56t1());
			ps.setString(59, contract.getP57t1());
			ps.setDate(60, new java.sql.Date(contract.getP58t1().getTime()));
			ps.setString(61, contract.getP59t1());
			ps.setString(62, contract.getP60t1());
			ps.setString(63, contract.getP61t1());
			ps.setString(64, contract.getP62t1());
			ps.setDouble(65, contract.getP65t1());
			ps.setDouble(66, contract.getP66t1());
			ps.setDouble(67, contract.getP67t1());
			ps.setDouble(68, contract.getP68t1());
			ps.setDouble(69, contract.getP69t1());
			ps.setDouble(70, contract.getP70t1());
			ps.setDouble(71, contract.getP71t1());
			ps.setDouble(72, contract.getP72t1());
			ps.setDate(73, new java.sql.Date(contract.getP73t1().getTime()));
			ps.setString(74, contract.getP100t1());
			ps.executeUpdate();
			c.commit();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
	}
	
	public static void remove(Contract contract)
	{
		
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM TF_contract WHERE id=?");
			ps.setLong(1, contract.getId());
			ps.executeUpdate();
			c.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
	}
	
	public static Long create(com.sbs.service.Contract contract, String P0T1)
	{
		
		Connection c = null;
		PreparedStatement ps = null;
		Long aid = new Long("0");
		try
		{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SQ_TF_contract.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				aid = rs.getLong("id");
			}
			ps = c.prepareStatement("INSERT INTO TF_contract (id, p0t1, p1t1, p2t1, p3t1, p4t1, p5t1, p6t1, p7t1, p8t1, p9t1, p10t1, p11t1, p12t1, p13t1, p14t1, p15t1, p16t1, p17t1, p18t1, p19t1, p20t1, p21t1, p22t1, p23t1, p24t1, p25t1, p26t1, p27t1, p28t1, p29t1, p30t1, p31t1, p32t1, p33t1, p34t1, p35t1, p37t1, p38t1, p39t1, p40t1, p41t1, p42t1, p43t1, p44t1, p48t1, p61t1, p65t1, p66t1, p67t1, p68t1, p69t1, p70t1, p71t1, p72t1, p73t1, p100t1) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			// ps =
			// c.prepareStatement("INSERT INTO TF_contract (id, p0t1, p1t1, p2t1, p3t1, p4t1, p5t1, p6t1, p7t1, p8t1, p9t1, p10t1, p11t1, p12t1, p13t1, p14t1, p15t1, p16t1, p17t1, p18t1, p19t1, p20t1, p21t1, p22t1, p23t1, p24t1, p25t1, p26t1, p27t1, p28t1, p29t1, p30t1, p31t1, p32t1, p33t1, p34t1, p35t1, p36t1, p37t1, p38t1, p39t1, p40t1, p41t1, p42t1, p43t1, p44t1, p45t1, p46t1, p47t1, p48t1, p49t1, p50t1, p51t1, p52t1, p53t1, p54t1, p55t1, p56t1, p57t1, p58t1, p59t1, p60t1, p61t1, p62t1) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, aid);
			ps.setString(2, P0T1);
			ps.setString(3, contract.getP1T1());
			ps.setString(4, contract.getP2T1());
			ps.setString(5, contract.getP3T1());
			ps.setString(6, contract.getP4T1());
			ps.setString(7, contract.getP5T1());
			ps.setDate(8, contract.getP6T1() != null ? new java.sql.Date(contract.getP6T1().getTimeInMillis()) : null);
			ps.setString(9, contract.getP7T1());
			ps.setDate(10, contract.getP8T1() != null ? new java.sql.Date(contract.getP8T1().getTimeInMillis()) : null);
			ps.setString(11, contract.getP9T1());
			ps.setString(12, contract.getP10T1());
			ps.setString(13, contract.getP11T1());
			ps.setString(14, contract.getP12T1());
			ps.setString(15, contract.getP13T1());
			ps.setString(16, String.valueOf(contract.getP14T1()));
			ps.setString(17, contract.getP15T1());
			ps.setString(18, contract.getP16T1());
			ps.setInt(19, contract.getP17T1() != null ? contract.getP17T1() : null);
			ps.setString(20, contract.getP18T1());
			ps.setString(21, contract.getP19T1());
			ps.setDouble(22, contract.getP20T1());
			ps.setDouble(23, contract.getP21T1());
			ps.setString(24, contract.getP22T1());
			ps.setString(25, contract.getP23T1());
			ps.setString(26, contract.getP24T1());
			ps.setString(27, contract.getP25T1());
			ps.setString(28, contract.getP26T1());
			ps.setString(29, contract.getP27T1());
			ps.setString(30, contract.getP28T1());
			ps.setString(31, contract.getP29T1());
			ps.setString(32, contract.getP30T1());
			ps.setString(33, contract.getP31T1());
			ps.setDate(34, contract.getP32T1() != null ? new java.sql.Date(contract.getP32T1().getTimeInMillis()) : null);
			ps.setDouble(35, contract.getP33T1());
			ps.setInt(36, contract.getP34T1() != null ? contract.getP34T1() : null);
			ps.setString(37, contract.getP35T1());
			// ps.setInt(38,contract.getP36T1()!=null? contract.getP36T1():0);
			ps.setString(38, contract.getP37T1());
			ps.setDate(39, contract.getP38T1() != null ? new java.sql.Date(contract.getP38T1().getTimeInMillis()) : null);
			ps.setInt(40, contract.getP39T1() != null ? contract.getP39T1() : null);
			ps.setDate(41, contract.getP40T1() != null ? new java.sql.Date(contract.getP40T1().getTimeInMillis()) : null);
			ps.setString(42, contract.getP41T1());
			ps.setString(43, contract.getP42T1());
			ps.setInt(44, contract.getP43T1() != null ? contract.getP43T1() : null);
			ps.setDate(45, contract.getP44T1() != null ? new java.sql.Date(contract.getP44T1().getTimeInMillis()) : null);
			// ps.setString(47,contract.getP45T1());
			// ps.setString(48,contract.getP46T1());
			// ps.setString(49,contract.getP47T1());
			ps.setDate(46, contract.getP48T1() != null ? new java.sql.Date(contract.getP48T1().getTimeInMillis()) : null);
			// ps.setString(51,contract.getP49T1());
			// ps.setString(52,contract.getP50T1());
			// ps.setString(53,contract.getP51T1());
			// ps.setString(54,contract.getP52T1());
			// ps.setString(55,contract.getP53T1());
			// ps.setInt(56,contract.getP54T1()!=null? contract.getP54T1():0);
			// ps.setString(57,contract.getP55T1());
			// ps.setString(58,contract.getP56T1());
			// ps.setString(59,P57T1);
			// ps.setDate(60,contract.getP58T1()!=null? new
			// java.sql.Date(contract.getP58T1().getTimeInMillis()):null);
			// ps.setInt(61,contract.getP59T1()!=null? contract.getP59T1():0);
			// ps.setInt(62,contract.getP60T1()!=null? contract.getP60T1():0);
			ps.setString(47, contract.getP61T1());
			// ps.setString(64,contract.getP62T1()!=null?
			// contract.getP62T1():null);
			ps.setDouble(48, contract.getP65T1() != null ? contract.getP65T1() : null);
			ps.setDouble(49, contract.getP66T1() != null ? contract.getP66T1() : null);
			ps.setDouble(50, contract.getP67T1() != null ? contract.getP67T1() : null);
			ps.setDouble(51, contract.getP68T1() != null ? contract.getP68T1() : null);
			ps.setDouble(52, contract.getP69T1() != null ? contract.getP69T1() : null);
			ps.setDouble(53, contract.getP70T1() != null ? contract.getP70T1() : null);
			ps.setDouble(54, contract.getP71T1() != null ? contract.getP71T1() : null);
			ps.setDouble(55, contract.getP72T1() != null ? contract.getP72T1() : null);
			ps.setDate(56, contract.getP73T1() != null ? new java.sql.Date(contract.getP73T1().getTimeInMillis()) : null);
			ps.setShort(57, contract.getP100T1() != null ? contract.getP100T1() : null);
			
			ps.executeUpdate();
			c.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error("Contract Service ERROR=" + CheckNull.getPstr(e));
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return aid;
	}
	
	public static String getBF_SETS(String id)
	{
		String res = "";
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM BF_SETS where id = '" + id + "'");
			if (rs.next())
			{
				res = rs.getString("value");
			}
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try
			{
				s.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				rs.close();
			}
			catch (Exception e)
			{
			}
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static String getBankINN(String id)
	{
		String res = "";
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			// rs =
			// s.executeQuery("select j.number_tax_registration from client_j j where branch = branch and id = '000'||branch");
			rs = s.executeQuery("SELECT * FROM BF_SETS where id = '" + id + "'");
			if (rs.next())
			{
				// res = rs.getString("number_tax_registration");
				res = rs.getString("value");
			}
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			try
			{
				s.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				rs.close();
			}
			catch (Exception e)
			{
			}
			ConnectionPool.close(c);
		}
		return res;
	}
	
}
