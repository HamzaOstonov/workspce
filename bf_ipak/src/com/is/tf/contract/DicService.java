package com.is.tf.contract;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.is.ConnectionPool;
import com.is.login.SessionController;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.ContractResult;
import com.sbs.service.DicVersionResult;
import com.sbs.service.Record1Result;
import com.sbs.service.Record2Result;
import com.sbs.service.Record3Result;
import com.sbs.service.Record4Result;
import com.sbs.service.Record5Result;

public class DicService {
	
	public static void getDic() {
		SessionController sc = new SessionController();
		final BankServiceProxy ws = new BankServiceProxy((String)sc.getSessionObject("YESVO_URL"));
		
		try {
			//SessionController sc = new SessionController();
			Record2Result cr = ws.dicFundDest(((String)(sc.getSessionObject("BankINN"))));
			Connection c = null;
	        PreparedStatement ps = null;
	        Long aid=new Long("0");
	        try {
	        	c = ConnectionPool.getConnection();
	        	ps = c.prepareStatement("delete from ss_tf_funddest ");
	        	ps.executeUpdate();
	        	ps = c.prepareStatement("INSERT INTO ss_tf_funddest (ID,ISDELETED,NAME) values(?,?,?)");
	        	for (int i = 0; i < cr.getList().length; i++) {
		        	ps.setLong(1,cr.getList(i).getId());
		        	ps.setInt(2,cr.getList(i).getIsDeleted());
		        	ps.setString(3,cr.getList(i).getName());
		        	ps.executeUpdate();
		        	System.out.println("ss_tf_funddest "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
	        	}
	        	c.commit();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            try {
	            	c.rollback();
	            } catch (Exception ex) {
					ex.printStackTrace();
				}
	        } finally {
	        	ConnectionPool.close(c);
	        }
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//SessionController sc = new SessionController();
		Record1Result cr = ws.dicAgreementState(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_agreementstate ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_agreementstate (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setLong(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_agreementstate "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	try {
		Record2Result cr = ws.dicBasis(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_basis ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_basis (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setLong(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_basis "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicBenefitsInfo(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_benefitsinfo ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_benefitsinfo (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setLong(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_benefitsinfo "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicBenefitsType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_benefitstype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_benefitstype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setLong(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_benefitstype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicCarryType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_carrytype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_carrytype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setLong(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_carrytype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicConditions(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_conditions ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_conditions (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setLong(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_conditions "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicContractSubject(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_contractsubject ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_contractsubject (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setLong(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_contractsubject "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record5Result cr = ws.dicContractType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_contracttype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_contracttype (CODE,ID,ISDELETED,NAME) values(?,?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
        		ps.setString(1,cr.getList(i).getCode());
        		ps.setString(2,cr.getList(i).getId());
	        	ps.setInt(3,cr.getList(i).getIsDeleted());
	        	ps.setString(4,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_contracttype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicCountry(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_country ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_country (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_country "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicDealType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_dealtype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_dealtype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_dealtype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicDebetModes(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_debetmodes ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_debetmodes (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_debetmodes "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicEnterpriseType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_enterprisetype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_enterprisetype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_enterprisetype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record4Result cr = ws.dicErrors(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_errors ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_errors (ID,NAME) values(?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setString(2,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_errors "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicExportType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_exporttype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_exporttype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_enterprisetype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicFundSource(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_fundsource ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_fundsource (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_fundsource "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicFundType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_fundtype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_fundtype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_fundtype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicGoodsType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_goodstype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_goodstype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_goodstype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record5Result cr = ws.dicIncoterms(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_incoterms ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_incoterms (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_incoterms "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicIndustrialZone(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_industrialzone ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_industrialzone (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_industrialzone "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicLoanType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_loantype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_loantype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_loantype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicPaymentMethod(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_payment ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_payment (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_payment "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	try {
		Record2Result cr = ws.dicPaymentSource(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_paymentsource ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_paymentsource (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setInt(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_paymentsource "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	try {
		Record2Result cr = ws.dicPenaltyType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_penaltytype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_penaltytype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_penaltytype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicPost(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_post ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_post (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_post "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicProc(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_proc ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_proc (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_proc "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicReasons(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_reasons ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_reasons (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_reasons "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicRegions(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_regions ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_regions (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_regions "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicSaleReason(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_salereason ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_salereason (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_salereason "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicSaleSource(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_salesource ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_salesource (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_salesource "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicSaleType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_saletype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_saletype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_saletype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicService(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_service ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_service (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_service "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicServiceType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_servicetype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_servicetype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_servicetype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record3Result cr = ws.dicTnved(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_tnved ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_tnved (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_tnved "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicTransferType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_transfertype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_transfertype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_transfertype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record2Result cr = ws.dicTransportType(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_transporttype ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_transporttype (ID,ISDELETED,NAME) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setShort(1,cr.getList(i).getId());
	        	ps.setInt(2,cr.getList(i).getIsDeleted());
	        	ps.setString(3,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_transporttype "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		Record5Result cr = ws.dicUnit(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_unit ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_unit (CODE,ID,ISDELETED,NAME) values(?,?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
        		ps.setString(1,cr.getList(i).getCode());
        		ps.setString(2,cr.getList(i).getId());
	        	ps.setInt(3,cr.getList(i).getIsDeleted());
	        	ps.setString(4,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_unit "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		DicVersionResult cr = ws.dicVersions(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_versions ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_versions (DESCR,NAME,VERSION) values(?,?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
	        	ps.setString(1,cr.getList(i).getDesc());
	        	ps.setString(2,cr.getList(i).getName());
	        	ps.setInt(3,cr.getList(i).getVersion());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_versions "+cr.getList(i).getDesc()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}

	try {
		Record4Result cr = ws.dicGnk(((String)(sc.getSessionObject("BankINN"))));
		Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("delete from ss_tf_gnk ");
        	ps.executeUpdate();
        	ps = c.prepareStatement("INSERT INTO ss_tf_gnk (ID,NAME) values(?,?)");
        	for (int i = 0; i < cr.getList().length; i++) {
        		ps.setString(1,cr.getList(i).getId());
	        	ps.setString(2,cr.getList(i).getName());
	        	ps.executeUpdate();
	        	System.out.println("ss_tf_gnk "+cr.getList(i).getId()+" - " +cr.getList(i).getName());
        	}
        	c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
            	c.rollback();
            } catch (Exception ex) {
				ex.printStackTrace();
			}
        } finally {
        	ConnectionPool.close(c);
        }
			
	} catch (Exception e) {
		e.printStackTrace();
	}

	}
}
	

