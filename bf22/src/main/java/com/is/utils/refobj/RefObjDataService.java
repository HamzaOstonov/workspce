package com.is.utils.refobj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.RefData;






public class RefObjDataService {
 	
	private static SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
	
	public static List<RefObjData> getRefDataObj(String sql, Object obj, String branch) {
		List<RefObjData> list = new LinkedList<RefObjData>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(branch);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				list.add(
						new RefObjData(
								rs.getString("refdata"), 
								rs.getString("reflabel"),
								getObject(rs, obj)
								));
		} catch (Exception e) {
			e.printStackTrace(); 
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	private static Object getObject(ResultSet rs, Object obj) throws Exception{
		Object res = null;/*
		if (obj instanceof com.is.tf.fund.Fund) {
			res = new com.is.tf.fund.Fund(
					 rs.getLong("id"),
                     rs.getString("p1t35"),
                     rs.getString("p0t35"),
                     rs.getString("p2t35"),
                     rs.getString("p3t35"),
                     rs.getDate("p4t35"),
                     rs.getString("p5t35"),
                     rs.getString("p6t35"),
                     rs.getDate("p7t35"),
                     rs.getString("p8t35"),
                     rs.getString("p9t35"),
                     rs.getString("p10t35"),
                     rs.getString("p11t35"),
                     rs.getString("p12t35"),
                     rs.getString("p13t35"),
                     rs.getDouble("p14t35"),
                     rs.getDouble("p15t35"),
                     rs.getDouble("p16t35"),
                     rs.getDouble("p17t35"),
                     rs.getDouble("p18t35"),
                     rs.getDouble("p19t35"),
                     rs.getString("p20t35"),
                     rs.getDouble("p21t35"),
                     rs.getLong("id_contract"),
                     rs.getString("p23t35"),
                     rs.getString("p24t35"),
                     rs.getString("p25t35"),
                     rs.getDate("p26t35"),
                     rs.getString("p100t35"));
		} else if (obj instanceof com.is.tf.movefromex.MoveFromEx) {
			res = new com.is.tf.movefromex.MoveFromEx(
                    rs.getLong("id"),
                    rs.getString("p0t52"),
                    rs.getString("p1t52"),
                    rs.getDate("p2t52"),
                    rs.getString("p3t52"),
                    rs.getString("p4t52"),
                    rs.getString("p5t52"),
                    rs.getString("p6t52"),
                    rs.getString("p7t52"),
                    rs.getString("p8t52"),
                    rs.getString("p9t52"),
                    rs.getDouble("p10t52"),
                    rs.getDouble("p11t52"),
                    rs.getDouble("p12t52"),
                    rs.getDouble("p13t52"),
                    rs.getDouble("p14t52"),
                    rs.getDouble("p15t52"),
                    rs.getDouble("p16t52"),
                    rs.getString("p17t52"),
                    rs.getString("p18t52"),
                    rs.getString("p19t52"),
                    rs.getString("p20t52"),
                    rs.getDate("p21t52"),
                    rs.getString("p22t52"),
                    rs.getString("p23t52"),
                    rs.getLong("id_contract"),
                    rs.getString("p24t52"),
                    rs.getDate("p25t52"),
                    rs.getString("p26t52"),
                    rs.getInt("p27t52"),
                    rs.getDate("p28t52"),
                    rs.getString("p100t52"),
                    rs.getString("p101t52"));        
			
		} else if (obj instanceof com.is.tf.payment.Payment) {
				res = new com.is.tf.payment.Payment(
						 rs.getLong("id"),
                         rs.getString("p1t44"),
                         rs.getString("p0t44"),
                         rs.getDate("p2t44"),
                         rs.getString("p3t44"),
                         rs.getString("p4t44"),
                         rs.getDate("p5t44"),
                         rs.getString("p6t44"),
                         rs.getString("p7t44"),
                         rs.getString("p8t44"),
                         rs.getString("p9t44"),
                         rs.getString("p10t44"),
                         rs.getString("p11t44"),
                         rs.getString("p12t44"),
                         rs.getString("p13t44"),
                         rs.getString("p14t44"),
                         rs.getString("p15t44"),
                         rs.getDouble("p16t44"),
                         rs.getDouble("p17t44"),
                         rs.getDouble("p18t44"),
                         rs.getDouble("p19t44"),
                         rs.getDouble("p20t44"),
                         rs.getDouble("p21t44"),
                         rs.getString("p22t44"),
                         rs.getString("p23t44"),
                         rs.getString("p24t44"),
                         rs.getLong("id_contract"),
                         rs.getString("p27t44"),
                         rs.getString("p28t44"),
                         rs.getString("p29t44"),
                         rs.getDate("p30t44"),
                         rs.getString("p100t44"));
			} else if (obj instanceof com.is.tf.Accreditiv.Accreditiv) {
				res = new com.is.tf.Accreditiv.Accreditiv(
						rs.getLong("id"),
                        rs.getString("p0t21"),
                        rs.getString("p1t21"),
                        rs.getString("p2t21"),
                        rs.getDate("p3t21"),
                        rs.getString("p4t21"),
                        rs.getDouble("p5t21"),
                        rs.getDouble("p6t21"),
                        rs.getDouble("p7t21"),
                        rs.getDouble("p8t21"),
                        rs.getString("p9t21"),
                        rs.getLong("id_contract"),
                        rs.getString("p10t21"),
                        rs.getString("p11t21"),
                        rs.getString("p12t21"),
                        rs.getString("p13t21"),
                        rs.getInt("p14t21"),
                        rs.getString("p15t21"),
                        rs.getString("p16t21"),
                        rs.getDate("p17t21"),
                        rs.getShort("p100t21"));
			}else if (obj instanceof com.is.tf.contract.Contract) {
				res = new com.is.tf.contract.Contract(
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
                        rs.getString("p100t1"));
			}else if (obj instanceof com.is.tf.garant.Garant) {
				res = new com.is.tf.garant.Garant(
						 rs.getLong("id"),
                         rs.getInt("p0t18"),
                         rs.getString("p1t18"),
                         rs.getString("p2t18"),
                         rs.getString("p3t18"),
                         rs.getDate("p4t18"),
                         rs.getString("p5t18"),
                         rs.getDouble("p6t18"),
                         rs.getDouble("p7t18"),
                         rs.getDouble("p8t18"),
                         rs.getDouble("p9t18"),
                         rs.getDate("p10t18"),
                         rs.getString("p11t18"),
                         rs.getLong("id_contract"),
                         rs.getString("p100t18"),
                         rs.getString("p12t18"),
                         rs.getString("p13t18"),
                         rs.getString("p14t18"),
                         rs.getString("p15t18"),
                         rs.getDate("p16t18"));
			}else if (obj instanceof com.is.tf.policy.Policy) {
				res = new com.is.tf.policy.Policy(
						  rs.getLong("id"),
                          rs.getString("p1t32"),
                          rs.getString("p0t32"),
                          rs.getString("p2t32"),
                          rs.getString("p3t32"),
                          rs.getString("p4t32"),
                          rs.getDate("p5t32"),
                          rs.getString("p6t32"),
                          rs.getDouble("p7t32"),
                          rs.getDouble("p8t32"),
                          rs.getDouble("p9t32"),
                          rs.getDouble("p10t32"),
                          rs.getDate("p11t32"),
                          rs.getString("p12t32"),
                          rs.getLong("id_contract"),
                          rs.getString("p13t32"),
                          rs.getString("p16t32"),
                          rs.getDate("p17t32"),
                          rs.getString("p100t32"));
			}else if (obj instanceof com.is.tf.paymentref.Paymentref) {
				res = new com.is.tf.paymentref.Paymentref(
						rs.getLong("id"),
                        rs.getString("p1t37"),
                        rs.getLong("p0t37"),
                        rs.getString("p2t37"),
                        rs.getDate("p3t37"),
                        rs.getLong("p4t37"),
                        rs.getString("p5t37"),
                        rs.getString("p6t37"),
                        rs.getString("p7t37"),
                        rs.getString("p8t37"),
                        rs.getDouble("p9t37"),
                        rs.getString("p10t37"),
                        rs.getDouble("p11t37"),
                        rs.getDate("p12t37"),
                        rs.getLong("p13t37"),
                        rs.getString("p14t37"),
                        rs.getString("p15t37"),
                        rs.getString("p16t37"),
                        rs.getString("p17t37"),
                        rs.getString("p18t37"),
                        rs.getString("p19t37"),
                        rs.getLong("id_contract"),
                        rs.getString("p21t37"),
                        rs.getString("p24t37"),
                        rs.getDate("p25t37"),
                        rs.getString("p100t37"),
                        rs.getString("p24t37")
				);
			}else if (obj instanceof com.is.tf.movefromim.Movefromim) {
				res = new com.is.tf.movefromim.Movefromim(
						 rs.getLong("id"),
                         rs.getString("p0t53"),
                         rs.getString("p1t53"),
                         rs.getDate("p2t53"),
                         rs.getString("p3t53"),
                         rs.getString("p4t53"),
                         rs.getString("p5t53"),
                         rs.getDate("p6t53"),
                         rs.getString("p7t53"),
                         rs.getString("p8t53"),
                         rs.getString("p9t53"),
                         rs.getString("p10t53"),
                         rs.getString("p11t53"),
                         rs.getString("p12t53"),
                         rs.getString("p13t53"),
                         rs.getString("p14t53"),
                         rs.getString("p15t53"),
                         rs.getString("p16t53"),
                         rs.getDouble("p17t53"),
                         rs.getDouble("p18t53"),
                         rs.getDouble("p19t53"),
                         rs.getDouble("p20t53"),
                         rs.getDouble("p21t53"),
                         rs.getDouble("p22t53"),
                         rs.getString("p23t53"),
                         rs.getString("p24t53"),
                         rs.getString("p25t53"),
                         rs.getString("p26t53"),
                         rs.getDate("p27t53"),
                         rs.getString("p28t53"),
                         rs.getString("p29t53"),
                         rs.getString("p30t53"),
                         rs.getDate("p31t53"),
                         rs.getDate("p33t53"));
						
			}*/
		return res;
	}
	
	

	
	/*
  public static List<RefDataObj> getRefData(String sql, String key, String branch)
  {
    List list = new LinkedList();
    Connection c = null;
    try
    {
      c = ConnectionPool.getConnection(branch);
      PreparedStatement ps = c.prepareStatement(sql);
      ps.setString(1, key);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
        list.add(
          new RefDataObj(rs.getString("data"), 
          rs.getString("label")));
    }
    catch (SQLException e) {
      com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    } finally {
      ConnectionPool.close(c);
    }
    return list;
  }

  public static HashMap<String, String> getHRefData(String sql, String branch)
  {
    HashMap list = new HashMap();
    Connection c = null;
    try {
      c = ConnectionPool.getConnection(branch);
      Statement s = c.createStatement();
      ResultSet rs = s.executeQuery(sql);
      while (rs.next())
        list.put(rs.getString("data"), rs.getString("label"));
    }
    catch (SQLException e) {
      com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    } finally {
      ConnectionPool.close(c);
    }
    return list;
  }

  public static List<RefDataObj> getRefTData(String sql)
  {
    List list = new LinkedList();
    Connection c = null;
    try {
      c = ConnectionPool.getTConnection();
      Statement s = c.createStatement();
      ResultSet rs = s.executeQuery(sql);
      while (rs.next())
        list.add(new RefDataObj(rs.getString("data"), rs.getString("label")));
    }
    catch (SQLException e) {
      com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    } finally {
      ConnectionPool.close(c);
    }
    return list;
  }

  public static HashMap<String, String> getHRefTData(String sql)
  {
    HashMap list = new HashMap();
    Connection c = null;
    try {
      c = ConnectionPool.getTConnection();
      Statement s = c.createStatement();
      ResultSet rs = s.executeQuery(sql);
      while (rs.next())
        list.put(rs.getString("data"), rs.getString("label"));
    }
    catch (SQLException e) {
      com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    } finally {
      ConnectionPool.close(c);
    }
    return list;
  }
  */
	
	public static String getObjectUID(Object obj, List<RefData> list) throws Exception{
		String res = "";/*
		if (obj instanceof com.is.tf.Accreditivsumchange.Accreditivsumchange) {
			res=getUID("AccreditivSumChange", list);
		} else if (obj instanceof com.is.tf.Accreditivtimechange.Accreditivtimechange) {
			res=getUID("AccreditivTimeChange", list);
		} else if (obj instanceof com.is.tf.fund.Fund) {
			res=getUID("Fund", list);
		} else if (obj instanceof com.is.tf.movefromex.MoveFromEx) {
			res=getUID("MoveFromEx", list);
		} else if (obj instanceof com.is.tf.Accreditiv.Accreditiv) {
			res = getUID("Accreditiv", list);
		} else if (obj instanceof com.is.tf.contract.Contract) {
			res=getUID("Contract", list);
		} else if (obj instanceof com.is.tf.garant.Garant) {
			res=getUID("Garant", list);	
		} else if (obj instanceof com.is.tf.garantsumchange.Garantsumchange) {
			res=getUID("GarantSumChange", list);
		} else if (obj instanceof com.is.tf.garanttimechange.garanttimechange) {
			res=getUID("GarantTimeChange", list);
		} else if (obj instanceof com.is.tf.policy.Policy) {
			res=getUID("Policy", list);	
		} else if (obj instanceof com.is.tf.policysumchange.Policysumchange) {
			res=getUID("PolicySumChange", list);	
		} else if (obj instanceof com.is.tf.policytimechange.Policytimechange) {
			res=getUID("PolicyTimeChange", list);	
		} else if (obj instanceof com.is.tf.paymentref.Paymentref) {
			res=getUID("Paymentref", list);	
		} else if (obj instanceof com.is.tf.movefromim.Movefromim) {
			res=getUID("MoveFromIm", list);	
		} else if (obj instanceof com.is.tf.payment.Payment) {
			res=getUID("Payment", list);	
		} else if (obj instanceof com.is.tf.Commissiongnk.Commissiongnk) {
		    res=getUID("CommissionGNK", list);	
	    }else if (obj instanceof com.is.tf.Commission.Commission) {
		    res=getUID("Commission", list);	
	    }else if (obj instanceof com.is.tf.compensation.Compensation) {
		    res=getUID("Compensation", list);	
	    }else if (obj instanceof com.is.tf.credit.Credit) {
		    res=getUID("Credit", list);	
	    }else if (obj instanceof com.is.tf.debet.Debet) {
		    res=getUID("Debet", list);	
	    }else if (obj instanceof com.is.tf.debetinfo.Debetinfo) {
		    res=getUID("DebetInfo", list);	
	    }else if (obj instanceof com.is.tf.lease.Lease) {
		    res=getUID("Lease", list);	
	    }else if (obj instanceof com.is.tf.movetoex.Movetoex) {
		    res=getUID("MoveToEx", list);	
	    }else if (obj instanceof com.is.tf.movetoim.Movetoim) {
		    res=getUID("MoveToIm", list);	
	    }else if (obj instanceof com.is.tf.penalty.Penalty) {
		    res=getUID("Penalty", list);	
	    }else if (obj instanceof com.is.tf.refundexp.Refundexp) {
		    res=getUID("RefundExp", list);	
	    }else if (obj instanceof com.is.tf.refundimp.Refundimp) {
		    res=getUID("RefundImp", list);	
	    }else if (obj instanceof com.is.tf.tax.Tax) {
		    res=getUID("Tax", list);	
	    }else if (obj instanceof com.is.tf.sale.Sale) {
		    res=getUID("Sale", list);	
	    }else if (obj instanceof com.is.tf.Act.Act) {
		    res=getUID("Act", list);	
	    }else if (obj instanceof com.is.tf.agreement.Agreement) {
		    res=getUID("Agreement", list);	
	    }else if (obj instanceof com.is.tf.Barterform.Barterform) {
		    res=getUID("BarterForm", list);	
	    }else if (obj instanceof com.is.tf.calcform.Calcform) {
		    res=getUID("CalcForm", list);	
	    }else if (obj instanceof com.is.tf.contract.Contract) {
		    res=getUID("Contract", list);	
	    }else if (obj instanceof com.is.tf.delegate.Delegate) {
		    res=getUID("Delegate", list);	
	    }else if (obj instanceof com.is.tf.endoperation.Endoperation) {
		    res=getUID("EndOperation", list);	
	    }else if (obj instanceof com.is.tf.expcondition.Expcondition) {
		    res=getUID("ExpCondition", list);	
	    }else if (obj instanceof com.is.tf.Goods.Goods) {
		    res=getUID("Goods", list);	
	    }else if (obj instanceof com.is.tf.Incoterms.Incoterms) {
		    res=getUID("Incoterms", list);	
	    }else if (obj instanceof com.is.tf.sender.Sender) {
		    res=getUID("Sender", list);	
	    }else if (obj instanceof com.is.tf.shipment.Shipment) {
		    res=getUID("Shipment", list);	
	    }else if (obj instanceof com.is.tf.specification.Specification) {
		    res=getUID("Specification", list);	
	    }else if (obj instanceof com.is.tf.tolling.Tolling) {
		    res=getUID("Tolling", list);	
	    }else if (obj instanceof com.is.tf.transcost.Transcost) {
		    res=getUID("TransCost", list);	
	    }else if (obj instanceof com.is.tf.work.Work) {
		    res=getUID("Work", list);	
	    }*/
		
		return res;
	}
	
	public static String getUID(String val, List<RefData> dp) throws Exception{
		String res="";
	    if ( dp != null ) {
	        for (int i = 0; i < dp.size(); i++) {
	            if (dp.get(i).getLabel().indexOf(("("+val+")").toUpperCase()) > -1) {
	                res = dp.get(i).getData();
	    }    }    }
	    return res;
	}
	
	public static String lvalue(String val, List<RefData> dp) throws Exception{
		String res="";
	    if ( dp != null ) {
	        for (int i = 0; i < dp.size(); i++) {
	            if ( val.equals(dp.get(i).getData())) {
	                res = dp.get(i).getLabel();
	    }    }    }
	    return res;
	}

	
	
}