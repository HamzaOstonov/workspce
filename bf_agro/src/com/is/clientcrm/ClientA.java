package com.is.clientcrm;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;


public class ClientA  implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    protected Long id;
    protected String branch;
    protected String id_client;
    protected String id_client_in_sap;
    protected String name;
    protected String code_country;
    protected String code_type;
    protected String code_resident;
    protected String code_subject;
    protected String sign_registr;
    protected String code_form;
    protected Date date_open;
    protected Date date_close;
    protected String state;
    protected int kod_err;
    protected String file_name;    
    protected String sign_100;
    protected String subbranch;
    protected String p_drive_permit_ser  ;
    protected String p_drive_permit_num  ;
    protected Date p_drive_permit_reg_d; 
    protected Date p_drive_permit_exp_d; 
    protected String p_drive_permit_place; 

    
    public String getP_drive_permit_ser() {
		return p_drive_permit_ser;
	}

	public String getP_drive_permit_num() {
		return p_drive_permit_num;
	}

	public Date getP_drive_permit_reg_d() {
		return p_drive_permit_reg_d;
	}

	public Date getP_drive_permit_exp_d() {
		return p_drive_permit_exp_d;
	}

	public String getP_drive_permit_place() {
		return p_drive_permit_place;
	}

	public void setP_drive_permit_ser(String p_drive_permit_ser) {
		this.p_drive_permit_ser = p_drive_permit_ser;
	}

	public void setP_drive_permit_num(String p_drive_permit_num) {
		this.p_drive_permit_num = p_drive_permit_num;
	}

	public void setP_drive_permit_reg_d(Date p_drive_permit_reg_d) {
		this.p_drive_permit_reg_d = p_drive_permit_reg_d;
	}

	public void setP_drive_permit_exp_d(Date p_drive_permit_exp_d) {
		this.p_drive_permit_exp_d = p_drive_permit_exp_d;
	}

	public void setP_drive_permit_place(String p_drive_permit_place) {
		this.p_drive_permit_place = p_drive_permit_place;
	}

	public String getSubbranch() {
		return subbranch;
	}

	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}

	protected String j_short_name;
    /*public void setJ_short_name(String short_name){
        if (name != null)
            this.j_short_name = getJ_short_name();
    }
    */
    public String substringShortName(){
        if (name != null) {
            if (name.length() >= 25)
                return name.substring(0, 25);
            else
                return name.substring(0,name.length());
        }
        return null;
    }

    protected String j_place_regist_name;
    protected Date j_date_registration;
    protected String j_number_registration_doc;
    protected String j_code_tax_org;
    protected String j_number_tax_registration;
    protected String j_code_sector;
    protected String j_code_sector_old;
    protected String j_code_organ_direct;
    protected String j_code_head_organization;
    protected String j_code_class_credit;
    protected String j_director_name;
    protected String j_director_passport;
    protected String j_chief_accounter_name;
    protected String j_chief_accounter_passport;
    protected String j_code_bank;
    protected String j_account;
    protected String j_post_address;
    protected String j_phone;
    protected String j_fax;
    protected String j_email;
    protected String j_sign_trade;
    protected String j_opf;
    protected String j_soato;
    protected String j_okpo;
    protected String j_inn_head_organization;
    protected String j_region;
    protected String j_distr;
    protected String j_small_business;
    protected String j_file_name;
    protected String j_director_type_document;
    protected String j_director_passp_serial;
    protected String j_director_passp_number;
    protected Date j_director_passp_date_reg;
    protected String j_director_passp_place_reg;
    protected Date j_director_passp_date_end;
    protected String j_director_code_citizenship;
    protected Date j_director_birthday;
    protected String j_director_birth_place;
    protected String j_director_address;
    protected String j_director_family;           
    protected String j_director_first_name;       
    protected String j_director_patronymic;       
    protected String j_director_drv_permit_ser;   
    protected String j_director_drv_permit_num;   
    protected Date j_director_drv_permit_reg_d; 
    protected Date j_director_drv_permit_exp_d; 
    protected String j_director_drv_permit_place; 
    
    protected String j_accountant_type_document;
    protected String j_accountant_passp_serial;
    protected String j_accountant_passp_number;
    protected Date j_accountant_passp_date_reg;
    protected String j_accountant_passp_place_reg;
    protected Date j_accountant_passp_date_end;
    protected String j_accountant_code_citizenship;
    protected Date j_accountant_birthday;
    protected String j_accountant_birth_place;
    protected String j_accountant_address;
    protected String j_accountant_family;          
    protected String j_accountant_first_name;      
    protected String j_accountant_patronymic;      
    protected String j_accountant_drv_permit_ser;  
    protected String j_accountant_drv_permit_num;  
    protected Date j_accountant_drv_permit_reg_d;
    protected Date j_accountant_drv_permit_exp_d;
    protected String j_accountant_drv_permit_place;

    
    protected Long j_327;
    
    
    protected Date j_patent_expiration;
    
    
    protected String j_responsible_emp;
    
    protected String i_short_name;
    public String getI_short_name(){
        if (name != null) {
            if (name.length() >= 25)
                return name.substring(0, 25);
            else
                return name.substring(0,name.length());
        }
        return null;
    }
    
    
    protected Date i_date_registration;
    
    
    protected String i_number_registration_doc;
    
    
    protected String i_place_regist_name;
    
    
    protected String i_number_tax_registration;
    
    
    protected String i_opf;
    
    
    protected String i_form;
    
    
    protected String i_sector;
    
    
    protected String i_organ_direct;
    
    
    protected String i_account;
    
    
    protected String i_post_address;
    
    
    protected String i_director_name;
    
    
    protected String i_chief_accounter_name;
    
    
    protected String i_phone;
    
    
    protected String i_fax;
    
    
    protected String i_email;
    
    
    protected Date p_birthday;
    
    protected String  p_pinfl;
    
    protected String  p_Code_Nibbd;
    
    protected Date p_Date_ApprovedNibbd;
    
    protected Date p_Date_ClosedNibbd;
    
    protected String p_post_address;
    
    
    protected String p_passport_type;
    
    
    protected String p_passport_serial;
    
    
    protected String p_passport_number;
    
    
    protected String p_passport_place_registration;
    
    
    protected Date p_passport_date_registration;
    
    
    protected String p_code_tax_org;
    
    
    protected String p_number_tax_registration;
    
    
    protected String p_code_bank;
    
    
    protected String p_code_class_credit;
    
    
    protected String p_code_citizenship;
    
    
    protected String p_birth_place;
    
    
    protected String p_code_capacity;
    
    
    protected Date p_capacity_status_date;
    
    
    protected String p_capacity_status_place;
    
    
    protected String p_num_certif_capacity;
    
    
    protected String p_phone_home;
    
    
    protected String p_phone_mobile;
    
    
    protected String p_email_address;
    
    
    protected String p_pension_sertif_serial;
    
    
    protected String p_code_gender;
    
    
    protected String p_code_nation;
    
    
    protected String p_code_birth_region;
    
    
    protected String p_code_birth_distr;
    
    
    protected String p_type_document;
    
    
    protected Date p_passport_date_expiration;
    
    
    protected String p_code_adr_region;
    
    
    protected String p_code_adr_distr;
    
    
    protected String p_inps;
    
    protected String p_name;
    
    
    protected String p_family;
    
    
    protected String p_first_name;
    
    
    protected String p_patronymic;
    
    
    protected String p_last_name_cyr;
    
    
    protected String p_first_name_cyr;
    
    
    protected String p_patronymic_cyr;
    
    
    protected String p_pass_place_region;
    
    
    protected String p_pass_place_district;
    
    
    protected String j_nibbd_acc_bal;
    
    protected String j_nibbd_reason;    
    
    protected String p_post_address_street;
    
    
    protected String p_post_address_house;
    
    
    protected String p_post_address_flat;
    
    
    protected String swift_id;
    
    
    protected String type_non_resident;
    
    
    private String j_sign_dep_acc;
    
    
    protected String j_type_activity;
        
    
    //protected String id_sap;
    protected String j_nibbd_acc_id_order;
    
    private String capital_inform;
    
    
    private String capital_currency;

    
    
    private int nibbd_emp_id;

    
    
    protected String post_address_country;
    
    
    
    protected Date date_open1;

    
    
    protected Date date_close1;
    
    
    
    protected int sign_date_open;
    
    
    
    protected int sign_date_close;

    
    private String addressCountry;
	
    
    public ClientA() {
        super();
    }

    public ClientA(long id, String branch, String id_client, String name, String code_type, String sign_registr,
                   Date date_open, String state) {
        super();
        this.id = id;
        this.branch = branch;
        this.id_client = id_client;
        this.name = name;
        this.code_type = code_type;
        this.sign_registr = sign_registr;
        this.date_open = date_open;
        this.state = state;
        
    }

    public ClientA(Long id, String branch, String id_client, String name, String code_country, String code_type,
                   String code_resident, String code_subject, String sign_registr, String code_form, Date date_open,
                   Date date_close, String state, int kod_err, String file_name, String subbranch, String j_short_name,
                   String j_place_regist_name, Date j_date_registration, String j_number_registration_doc,
                   String j_code_tax_org, String j_number_tax_registration, String j_code_sector_old, String j_code_sector,
                   String j_code_organ_direct, String j_code_head_organization, String j_code_class_credit,
                   String j_director_name, String j_director_passport, String j_chief_accounter_name,
                   String j_chief_accounter_passport, String j_code_bank, String j_account, String j_post_address,
                   String j_phone, String j_fax, String j_email, String j_sign_trade, String j_opf, String j_soato,
                   String j_okpo, String j_inn_head_organization, String j_region, String j_distr, String j_small_business,
                   String j_director_type_document, String j_director_passp_serial, String j_director_passp_number,
                   Date j_director_passp_date_reg, String j_director_passp_place_reg, Date j_director_passp_date_end,
                   String j_director_code_citizenship, Date j_director_birthday, String j_director_birth_place,
                   String j_director_address,
                   String j_director_family,          
                   String j_director_first_name,      
                   String j_director_patronymic,      
                   String j_director_drv_permit_ser,  
                   String j_director_drv_permit_num,  
                   Date j_director_drv_permit_reg_d,
                   Date j_director_drv_permit_exp_d,
                   String j_director_drv_permit_place,
                   String j_accountant_type_document, String j_accountant_passp_serial,
                   String j_accountant_passp_number, Date j_accountant_passp_date_reg, String j_accountant_passp_place_reg,
                   Date j_accountant_passp_date_end, String j_accountant_code_citizenship, Date j_accountant_birthday,
                   String j_accountant_birth_place, String j_accountant_address, 
                   String j_accountant_family,          
                   String j_accountant_first_name,      
                   String j_accountant_patronymic,      
                   String j_accountant_drv_permit_ser,  
                   String j_accountant_drv_permit_num,  
                   Date j_accountant_drv_permit_reg_d,
                   Date j_accountant_drv_permit_exp_d,
                   String j_accountant_drv_permit_place,
                   Long j_327, Date j_patent_expiration,
                   String j_responsible_emp, String i_short_name, Date i_date_registration, String i_number_registration_doc,
                   String i_place_regist_name, String i_number_tax_registration, String i_opf, String i_form, String i_sector,
                   String i_organ_direct, String i_account, String i_post_address, String i_director_name,
                   String i_chief_accounter_name, String i_phone, String i_fax, String i_email, Date p_birthday,
                   String p_post_address, String p_passport_type, String p_passport_serial, String p_passport_number,
                   String p_passport_place_registration, Date p_passport_date_registration, String p_code_tax_org,
                   String p_number_tax_registration, String p_code_bank, String p_code_class_credit, String p_code_citizenship,
                   String p_birth_place, String p_code_capacity, Date p_capacity_status_date, String p_capacity_status_place,
                   String p_num_certif_capacity, String p_phone_home, String p_phone_mobile, String p_email_address,
                   String p_pension_sertif_serial, String p_code_gender, String p_code_nation, String p_code_birth_region,
                   String p_code_birth_distr, String p_type_document, Date p_passport_date_expiration,
                   String p_code_adr_region, String p_code_adr_distr, String p_inps, String p_family, String p_first_name,
                   String p_patronymic, String p_last_name_cyr, String p_first_name_cyr, String p_patronymic_cyr,
                   String p_pass_place_region, String p_pass_place_district, String p_post_address_street,
                   String p_post_address_house, String p_post_adress_flast, String sign_100,
                           String activity_type, String addressCountry, Date date_open1, Date date_close1, int sign_date_open, int sign_date_close, String post_address_country,String p_pinfl,
                           String  p_Code_Nibbd,Date p_Date_ApprovedNibbd,Date p_Date_ClosedNibbd, String p_drive_permit_ser,  
                           String p_drive_permit_num , 
                           Date p_drive_permit_reg_d , 
                           Date p_drive_permit_exp_d , 
                           String p_drive_permit_place
    ) {
        super();
        this.id = id;
		this.post_address_country = post_address_country;
		this.p_pinfl=p_pinfl;
		this.p_Code_Nibbd = p_Code_Nibbd;
		this.p_Date_ApprovedNibbd = p_Date_ApprovedNibbd;
		this.p_Date_ClosedNibbd = p_Date_ClosedNibbd;
        this.branch = branch;
        this.id_client = id_client;
        this.name = name;
        this.code_country = code_country;
        this.code_type = code_type;
        this.code_resident = code_resident;
        this.code_subject = code_subject;
        this.sign_registr = sign_registr;
        this.code_form = code_form;
        this.date_open = date_open;
        this.date_close = date_close;
        this.state = state;
        this.kod_err = kod_err;
        this.file_name = file_name;
        this.subbranch = subbranch;
        this.j_short_name = j_short_name;
        this.j_place_regist_name = j_place_regist_name;
        this.j_date_registration = j_date_registration;
        this.j_number_registration_doc = j_number_registration_doc;
        this.j_code_tax_org = j_code_tax_org;
        this.j_number_tax_registration = j_number_tax_registration;
        this.j_code_sector = j_code_sector;
        this.j_code_sector_old = j_code_sector_old;
        this.j_code_organ_direct = j_code_organ_direct;
        this.j_code_head_organization = j_code_head_organization;
        this.j_code_class_credit = j_code_class_credit;
        this.j_director_name = j_director_name;
        this.j_director_passport = j_director_passport;
        this.j_chief_accounter_name = j_chief_accounter_name;
        this.j_chief_accounter_passport = j_chief_accounter_passport;
        this.j_code_bank = j_code_bank;
        this.j_account = j_account;
        this.j_post_address = j_post_address;
        this.j_phone = j_phone;
        this.j_fax = j_fax;
        this.j_email = j_email;
        this.j_sign_trade = j_sign_trade;
        this.j_opf = j_opf;
        this.j_soato = j_soato;
        this.j_okpo = j_okpo;
        this.j_inn_head_organization = j_inn_head_organization;
        this.j_region = j_region;
        this.j_distr = j_distr;
        this.j_small_business = j_small_business;
        this.j_director_type_document = j_director_type_document;
        this.j_director_passp_serial = j_director_passp_serial;
        this.j_director_passp_number = j_director_passp_number;
        this.j_director_passp_date_reg = j_director_passp_date_reg;
        this.j_director_passp_place_reg = j_director_passp_place_reg;
        this.j_director_passp_date_end = j_director_passp_date_end;
        this.j_director_code_citizenship = j_director_code_citizenship;
        this.j_director_birthday = j_director_birthday;
        this.j_director_birth_place = j_director_birth_place;
        this.j_director_address = j_director_address;
        this.j_director_family           =j_director_family          ;
        this.j_director_first_name       =j_director_first_name      ;
        this.j_director_patronymic       =j_director_patronymic      ;
        this.j_director_drv_permit_ser   =j_director_drv_permit_ser  ;
        this.j_director_drv_permit_num   =j_director_drv_permit_num  ;
        this.j_director_drv_permit_reg_d =j_director_drv_permit_reg_d;
        this.j_director_drv_permit_exp_d =j_director_drv_permit_exp_d;
        this.j_director_drv_permit_place =j_director_drv_permit_place;        
        
        this.j_accountant_type_document = j_accountant_type_document;
        this.j_accountant_passp_serial = j_accountant_passp_serial;
        this.j_accountant_passp_number = j_accountant_passp_number;
        this.j_accountant_passp_date_reg = j_accountant_passp_date_reg;
        this.j_accountant_passp_place_reg = j_accountant_passp_place_reg;
        this.j_accountant_passp_date_end = j_accountant_passp_date_end;
        this.j_accountant_code_citizenship = j_accountant_code_citizenship;
        this.j_accountant_birthday = j_accountant_birthday;
        this.j_accountant_birth_place = j_accountant_birth_place;
        this.j_accountant_address = j_accountant_address;
        this.j_accountant_family           =j_accountant_family          ;
        this.j_accountant_first_name       =j_accountant_first_name      ;
        this.j_accountant_patronymic       =j_accountant_patronymic      ;
        this.j_accountant_drv_permit_ser   =j_accountant_drv_permit_ser  ;
        this.j_accountant_drv_permit_num   =j_accountant_drv_permit_num  ;
        this.j_accountant_drv_permit_reg_d =j_accountant_drv_permit_reg_d;
        this.j_accountant_drv_permit_exp_d =j_accountant_drv_permit_exp_d;
        this.j_accountant_drv_permit_place =j_accountant_drv_permit_place;
        
        this.j_327 = j_327;
        this.j_patent_expiration = j_patent_expiration;
        this.j_responsible_emp = j_responsible_emp;
        this.i_short_name = i_short_name;
        this.i_date_registration = i_date_registration;
        this.i_number_registration_doc = i_number_registration_doc;
        this.i_place_regist_name = i_place_regist_name;
        this.i_number_tax_registration = i_number_tax_registration;
        this.i_opf = i_opf;
        this.i_form = i_form;
        this.i_sector = i_sector;
        this.i_organ_direct = i_organ_direct;
        this.i_account = i_account;
        this.i_post_address = i_post_address;
        this.i_director_name = i_director_name;
        this.i_chief_accounter_name = i_chief_accounter_name;
        this.i_phone = i_phone;
        this.i_fax = i_fax;
        this.i_email = i_email;
        this.p_birthday = p_birthday;
        this.p_post_address = p_post_address;
        this.p_passport_type = p_passport_type;
        this.p_passport_serial = p_passport_serial;
        this.p_passport_number = p_passport_number;
        this.p_passport_place_registration = p_passport_place_registration;
        this.p_passport_date_registration = p_passport_date_registration;
        this.p_code_tax_org = p_code_tax_org;
        this.p_number_tax_registration = p_number_tax_registration;
        this.p_code_bank = p_code_bank;
        this.p_code_class_credit = p_code_class_credit;
        this.p_code_citizenship = p_code_citizenship;
        this.p_birth_place = p_birth_place;
        this.p_code_capacity = p_code_capacity;
        this.p_capacity_status_date = p_capacity_status_date;
        this.p_capacity_status_place = p_capacity_status_place;
        this.p_num_certif_capacity = p_num_certif_capacity;
        this.p_phone_home = p_phone_home;
        this.p_phone_mobile = p_phone_mobile;
        this.p_email_address = p_email_address;
        this.p_pension_sertif_serial = p_pension_sertif_serial;
        this.p_code_gender = p_code_gender;
        this.p_code_nation = p_code_nation;
        this.p_code_birth_region = p_code_birth_region;
        this.p_code_birth_distr = p_code_birth_distr;
        this.p_type_document = p_type_document;
        this.p_passport_date_expiration = p_passport_date_expiration;
        this.p_code_adr_region = p_code_adr_region;
        this.p_code_adr_distr = p_code_adr_distr;
        this.p_inps = p_inps;
        this.p_family = p_family;
        this.p_first_name = p_first_name;
        this.p_patronymic = p_patronymic;
        this.p_last_name_cyr = p_last_name_cyr;
        this.p_first_name_cyr = p_first_name_cyr;
        this.p_patronymic_cyr = p_patronymic_cyr;
        this.p_pass_place_region = p_pass_place_region;
        this.p_pass_place_district = p_pass_place_district;
        this.p_post_address_street = p_post_address_street;
        this.p_post_address_house = p_post_address_house;
        this.p_post_address_flat = p_post_adress_flast;
        this.p_drive_permit_ser   =p_drive_permit_ser  ;  
        this.p_drive_permit_num   =p_drive_permit_num  ; 
        this.p_drive_permit_reg_d =p_drive_permit_reg_d; 
        this.p_drive_permit_exp_d =p_drive_permit_exp_d; 
        this.p_drive_permit_place =p_drive_permit_place;

        this.sign_100 = sign_100;
        this.j_type_activity = activity_type;
        this.addressCountry = addressCountry;
        this.date_open1=date_open1;
        this.date_close1=date_close1;
        this.sign_date_open=sign_date_open;
        this.sign_date_close=sign_date_close;
        System.out.println(j_post_address);
    }

   
    @Override
    public ClientA clone() throws CloneNotSupportedException {
        return (ClientA) super.clone();
    }

    public void rollBackObjectiveChanges(final ClientA oldClient) {
            }

    public void setClientData(ClientA client) {
        /*if (id_client != null) {
            setNotObjectiveData(client);
        } else {
            setManualData(client);
        }*/
        setManualData(client);
    }

    public void setManualData(final ClientA other) {
        
    }

    public void setNotObjectiveData(final ClientA other) {
        
    }

    public boolean hasObjectiveChanges(ClientA old) {
    	 Field[] fields = ClientA.class.getDeclaredFields();

         for (Field f : fields) {
             try {
                 if (ClientFields.valueOf(f.getName().toUpperCase()) == null ||
                         !ClientFields.valueOf(f.getName().toUpperCase()).isObjectiveField()) {
                     //continue;
                 //}
                     throw new IllegalArgumentException();
                 }
             } catch (IllegalArgumentException e) {
                 continue;
             }
             try {
                 if (hasChanges(f.get(this), f.get(old))) {
                     //ISLogger.getLogger().error("Change " + f.getName());
                     return true;
                 }
             } catch (IllegalArgumentException e) {
                 e.printStackTrace();
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             }
         }
         return false;

    }

    public boolean hasAnyChanges(ClientA old) {
   	 Field[] fields = ClientA.class.getDeclaredFields();

        for (Field f : fields) {
            try {
                if (hasChanges(f.get(this), f.get(old))) {
                    //ISLogger.getLogger().error("Change " + f.getName());
                    return true;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
   }

    
    public static boolean hasChanges(Object obj1, Object obj2) {
		return (obj1 != null && obj2 != null && !obj1.equals(obj2)) 
			|| (obj1 != null && obj2 == null) 
			|| (obj1 == null && obj2 != null);
	}

    
    public boolean equalsWithoutInn(ClientA other) {
        if (other == null) {
            return false;
        }
        return this.j_number_registration_doc.equals(other.getJ_number_registration_doc())
                && this.j_date_registration.equals(other.getJ_date_registration())
                && this.j_opf.equals(other.getJ_opf()) && this.code_form.equals(other.getCode_form());
    }

    /*public void resolveSignRegistry(boolean isNibbd) {
        if (code_type.equalsIgnoreCase("08")) {
            this.sign_registr = "2";
            return;
        }
        if (code_type.equalsIgnoreCase("4")){
            this.sign_registr = "4";
            return;
        }
        if (isNibbd)
            this.sign_registr = "3";
        else
            this.sign_registr = "1";
    }*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public String getId_client_in_sap() {
		return id_client_in_sap;
	}

	public void setId_client_in_sap(String id_client_in_sap) {
		this.id_client_in_sap = id_client_in_sap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode_country() {
		return code_country;
	}

	public void setCode_country(String code_country) {
		this.code_country = code_country;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}

	public String getCode_resident() {
		return code_resident;
	}

	public void setCode_resident(String code_resident) {
		this.code_resident = code_resident;
	}

	public String getCode_subject() {
		return code_subject;
	}

	public void setCode_subject(String code_subject) {
		this.code_subject = code_subject;
	}

	public String getSign_registr() {
		return sign_registr;
	}

	public void setSign_registr(String sign_registr) {
		this.sign_registr = sign_registr;
	}

	public String getCode_form() {
		return code_form;
	}

	public void setCode_form(String code_form) {
		this.code_form = code_form;
	}

	public Date getDate_open() {
		return date_open;
	}

	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}

	public Date getDate_close() {
		return date_close;
	}

	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getKod_err() {
		return kod_err;
	}

	public void setKod_err(int kod_err) {
		this.kod_err = kod_err;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getSign_100() {
		return sign_100;
	}

	public void setSign_100(String sign_100) {
		this.sign_100 = sign_100;
	}

	public String getJ_short_name() {
		return j_short_name;
	}

	public void setJ_short_name(String j_short_name) {
		this.j_short_name = j_short_name;
	}

	public String getJ_place_regist_name() {
		return j_place_regist_name;
	}

	public void setJ_place_regist_name(String j_place_regist_name) {
		this.j_place_regist_name = j_place_regist_name;
	}

	public Date getJ_date_registration() {
		return j_date_registration;
	}

	public void setJ_date_registration(Date j_date_registration) {
		this.j_date_registration = j_date_registration;
	}

	public String getJ_number_registration_doc() {
		return j_number_registration_doc;
	}

	public void setJ_number_registration_doc(String j_number_registration_doc) {
		this.j_number_registration_doc = j_number_registration_doc;
	}

	public String getJ_code_tax_org() {
		return j_code_tax_org;
	}

	public void setJ_code_tax_org(String j_code_tax_org) {
		this.j_code_tax_org = j_code_tax_org;
	}

	public String getJ_number_tax_registration() {
		return j_number_tax_registration;
	}

	public void setJ_number_tax_registration(String j_number_tax_registration) {
		this.j_number_tax_registration = j_number_tax_registration;
	}

	public String getJ_code_sector() {
		return j_code_sector;
	}

	public void setJ_code_sector(String j_code_sector) {
		this.j_code_sector = j_code_sector;
	}

	public String getJ_code_sector_old() {
		return j_code_sector_old;
	}

	public void setJ_code_sector_old(String j_code_sector_old) {
		this.j_code_sector_old = j_code_sector_old;
	}

	public String getJ_code_organ_direct() {
		return j_code_organ_direct;
	}

	public void setJ_code_organ_direct(String j_code_organ_direct) {
		this.j_code_organ_direct = j_code_organ_direct;
	}

	public String getJ_code_head_organization() {
		return j_code_head_organization;
	}

	public void setJ_code_head_organization(String j_code_head_organization) {
		this.j_code_head_organization = j_code_head_organization;
	}

	public String getJ_code_class_credit() {
		return j_code_class_credit;
	}

	public void setJ_code_class_credit(String j_code_class_credit) {
		this.j_code_class_credit = j_code_class_credit;
	}

	public String getJ_director_name() {
		return j_director_name;
	}

	public void setJ_director_name(String j_director_name) {
		this.j_director_name = j_director_name;
	}

	public String getJ_director_passport() {
		return j_director_passport;
	}

	public void setJ_director_passport(String j_director_passport) {
		this.j_director_passport = j_director_passport;
	}

	public String getJ_chief_accounter_name() {
		return j_chief_accounter_name;
	}

	public void setJ_chief_accounter_name(String j_chief_accounter_name) {
		this.j_chief_accounter_name = j_chief_accounter_name;
	}

	public String getJ_chief_accounter_passport() {
		return j_chief_accounter_passport;
	}

	public void setJ_chief_accounter_passport(String j_chief_accounter_passport) {
		this.j_chief_accounter_passport = j_chief_accounter_passport;
	}

	public String getJ_code_bank() {
		return j_code_bank;
	}

	public void setJ_code_bank(String j_code_bank) {
		this.j_code_bank = j_code_bank;
	}

	public String getJ_account() {
		return j_account;
	}

	public void setJ_account(String j_account) {
		this.j_account = j_account;
	}

	public String getJ_post_address() {
		return j_post_address;
	}

	public void setJ_post_address(String j_post_address) {
		this.j_post_address = j_post_address;
	}

	public String getJ_phone() {
		return j_phone;
	}

	public void setJ_phone(String j_phone) {
		this.j_phone = j_phone;
	}

	public String getJ_fax() {
		return j_fax;
	}

	public void setJ_fax(String j_fax) {
		this.j_fax = j_fax;
	}

	public String getJ_email() {
		return j_email;
	}

	public void setJ_email(String j_email) {
		this.j_email = j_email;
	}

	public String getJ_sign_trade() {
		return j_sign_trade;
	}

	public void setJ_sign_trade(String j_sign_trade) {
		this.j_sign_trade = j_sign_trade;
	}

	public String getJ_opf() {
		return j_opf;
	}

	public void setJ_opf(String j_opf) {
		this.j_opf = j_opf;
	}

	public String getJ_soato() {
		return j_soato;
	}

	public void setJ_soato(String j_soato) {
		this.j_soato = j_soato;
	}

	public String getJ_okpo() {
		return j_okpo;
	}

	public void setJ_okpo(String j_okpo) {
		this.j_okpo = j_okpo;
	}

	public String getJ_inn_head_organization() {
		return j_inn_head_organization;
	}

	public void setJ_inn_head_organization(String j_inn_head_organization) {
		this.j_inn_head_organization = j_inn_head_organization;
	}

	public String getJ_region() {
		return j_region;
	}

	public void setJ_region(String j_region) {
		this.j_region = j_region;
	}

	public String getJ_distr() {
		return j_distr;
	}

	public void setJ_distr(String j_distr) {
		this.j_distr = j_distr;
	}

	public String getJ_small_business() {
		return j_small_business;
	}

	public void setJ_small_business(String j_small_business) {
		this.j_small_business = j_small_business;
	}

	public String getJ_director_type_document() {
		return j_director_type_document;
	}

	public void setJ_director_type_document(String j_director_type_document) {
		this.j_director_type_document = j_director_type_document;
	}

	public String getJ_director_passp_serial() {
		return j_director_passp_serial;
	}

	public void setJ_director_passp_serial(String j_director_passp_serial) {
		this.j_director_passp_serial = j_director_passp_serial;
	}

	public String getJ_director_passp_number() {
		return j_director_passp_number;
	}

	public void setJ_director_passp_number(String j_director_passp_number) {
		this.j_director_passp_number = j_director_passp_number;
	}

	public Date getJ_director_passp_date_reg() {
		return j_director_passp_date_reg;
	}

	public void setJ_director_passp_date_reg(Date j_director_passp_date_reg) {
		this.j_director_passp_date_reg = j_director_passp_date_reg;
	}

	public String getJ_director_passp_place_reg() {
		return j_director_passp_place_reg;
	}

	public void setJ_director_passp_place_reg(String j_director_passp_place_reg) {
		this.j_director_passp_place_reg = j_director_passp_place_reg;
	}

	public Date getJ_director_passp_date_end() {
		return j_director_passp_date_end;
	}

	public void setJ_director_passp_date_end(Date j_director_passp_date_end) {
		this.j_director_passp_date_end = j_director_passp_date_end;
	}

	public String getJ_director_code_citizenship() {
		return j_director_code_citizenship;
	}

	public void setJ_director_code_citizenship(String j_director_code_citizenship) {
		this.j_director_code_citizenship = j_director_code_citizenship;
	}

	public Date getJ_director_birthday() {
		return j_director_birthday;
	}

	public void setJ_director_birthday(Date j_director_birthday) {
		this.j_director_birthday = j_director_birthday;
	}

	public String getJ_director_birth_place() {
		return j_director_birth_place;
	}

	public void setJ_director_birth_place(String j_director_birth_place) {
		this.j_director_birth_place = j_director_birth_place;
	}

	public String getJ_director_address() {
		return j_director_address;
	}

	public void setJ_director_address(String j_director_address) {
		this.j_director_address = j_director_address;
	}

	public String getJ_accountant_type_document() {
		return j_accountant_type_document;
	}

	public void setJ_accountant_type_document(String j_accountant_type_document) {
		this.j_accountant_type_document = j_accountant_type_document;
	}

	public String getJ_accountant_passp_serial() {
		return j_accountant_passp_serial;
	}

	public void setJ_accountant_passp_serial(String j_accountant_passp_serial) {
		this.j_accountant_passp_serial = j_accountant_passp_serial;
	}

	public String getJ_accountant_passp_number() {
		return j_accountant_passp_number;
	}

	public void setJ_accountant_passp_number(String j_accountant_passp_number) {
		this.j_accountant_passp_number = j_accountant_passp_number;
	}

	public Date getJ_accountant_passp_date_reg() {
		return j_accountant_passp_date_reg;
	}

	public void setJ_accountant_passp_date_reg(Date j_accountant_passp_date_reg) {
		this.j_accountant_passp_date_reg = j_accountant_passp_date_reg;
	}

	public String getJ_accountant_passp_place_reg() {
		return j_accountant_passp_place_reg;
	}

	public void setJ_accountant_passp_place_reg(String j_accountant_passp_place_reg) {
		this.j_accountant_passp_place_reg = j_accountant_passp_place_reg;
	}

	public Date getJ_accountant_passp_date_end() {
		return j_accountant_passp_date_end;
	}

	public void setJ_accountant_passp_date_end(Date j_accountant_passp_date_end) {
		this.j_accountant_passp_date_end = j_accountant_passp_date_end;
	}

	public String getJ_accountant_code_citizenship() {
		return j_accountant_code_citizenship;
	}

	public void setJ_accountant_code_citizenship(String j_accountant_code_citizenship) {
		this.j_accountant_code_citizenship = j_accountant_code_citizenship;
	}

	public Date getJ_accountant_birthday() {
		return j_accountant_birthday;
	}

	public void setJ_accountant_birthday(Date j_accountant_birthday) {
		this.j_accountant_birthday = j_accountant_birthday;
	}

	public String getJ_accountant_birth_place() {
		return j_accountant_birth_place;
	}

	public void setJ_accountant_birth_place(String j_accountant_birth_place) {
		this.j_accountant_birth_place = j_accountant_birth_place;
	}

	public String getJ_accountant_address() {
		return j_accountant_address;
	}

	public void setJ_accountant_address(String j_accountant_address) {
		this.j_accountant_address = j_accountant_address;
	}

	public Long getJ_327() {
		return j_327;
	}

	public void setJ_327(Long j_327) {
		this.j_327 = j_327;
	}

	public Date getJ_patent_expiration() {
		return j_patent_expiration;
	}

	public void setJ_patent_expiration(Date j_patent_expiration) {
		this.j_patent_expiration = j_patent_expiration;
	}

	public String getJ_responsible_emp() {
		return j_responsible_emp;
	}

	public void setJ_responsible_emp(String j_responsible_emp) {
		this.j_responsible_emp = j_responsible_emp;
	}

	public Date getI_date_registration() {
		return i_date_registration;
	}

	public void setI_date_registration(Date i_date_registration) {
		this.i_date_registration = i_date_registration;
	}

	public String getI_number_registration_doc() {
		return i_number_registration_doc;
	}

	public void setI_number_registration_doc(String i_number_registration_doc) {
		this.i_number_registration_doc = i_number_registration_doc;
	}

	public String getI_place_regist_name() {
		return i_place_regist_name;
	}

	public void setI_place_regist_name(String i_place_regist_name) {
		this.i_place_regist_name = i_place_regist_name;
	}

	public String getI_number_tax_registration() {
		return i_number_tax_registration;
	}

	public void setI_number_tax_registration(String i_number_tax_registration) {
		this.i_number_tax_registration = i_number_tax_registration;
	}

	public String getI_opf() {
		return i_opf;
	}

	public void setI_opf(String i_opf) {
		this.i_opf = i_opf;
	}

	public String getI_form() {
		return i_form;
	}

	public void setI_form(String i_form) {
		this.i_form = i_form;
	}

	public String getI_sector() {
		return i_sector;
	}

	public void setI_sector(String i_sector) {
		this.i_sector = i_sector;
	}

	public String getI_organ_direct() {
		return i_organ_direct;
	}

	public void setI_organ_direct(String i_organ_direct) {
		this.i_organ_direct = i_organ_direct;
	}

	public String getI_account() {
		return i_account;
	}

	public void setI_account(String i_account) {
		this.i_account = i_account;
	}

	public String getI_post_address() {
		return i_post_address;
	}

	public void setI_post_address(String i_post_address) {
		this.i_post_address = i_post_address;
	}

	public String getI_director_name() {
		return i_director_name;
	}

	public void setI_director_name(String i_director_name) {
		this.i_director_name = i_director_name;
	}

	public String getI_chief_accounter_name() {
		return i_chief_accounter_name;
	}

	public void setI_chief_accounter_name(String i_chief_accounter_name) {
		this.i_chief_accounter_name = i_chief_accounter_name;
	}

	public String getI_phone() {
		return i_phone;
	}

	public void setI_phone(String i_phone) {
		this.i_phone = i_phone;
	}

	public String getI_fax() {
		return i_fax;
	}

	public void setI_fax(String i_fax) {
		this.i_fax = i_fax;
	}

	public String getI_email() {
		return i_email;
	}

	public void setI_email(String i_email) {
		this.i_email = i_email;
	}

	public Date getP_birthday() {
		return p_birthday;
	}

	public void setP_birthday(Date p_birthday) {
		this.p_birthday = p_birthday;
	}

	public String getP_post_address() {
		return p_post_address;
	}

	public void setP_post_address(String p_post_address) {
		this.p_post_address = p_post_address;
	}

	public String getP_passport_type() {
		return p_passport_type;
	}

	public void setP_passport_type(String p_passport_type) {
		this.p_passport_type = p_passport_type;
	}

	public String getP_passport_serial() {
		return p_passport_serial;
	}

	public void setP_passport_serial(String p_passport_serial) {
		this.p_passport_serial = p_passport_serial;
	}

	public String getP_passport_number() {
		return p_passport_number;
	}

	public void setP_passport_number(String p_passport_number) {
		this.p_passport_number = p_passport_number;
	}

	public String getP_passport_place_registration() {
		return p_passport_place_registration;
	}

	public void setP_passport_place_registration(String p_passport_place_registration) {
		this.p_passport_place_registration = p_passport_place_registration;
	}

	public Date getP_passport_date_registration() {
		return p_passport_date_registration;
	}

	public void setP_passport_date_registration(Date p_passport_date_registration) {
		this.p_passport_date_registration = p_passport_date_registration;
	}

	public String getP_code_tax_org() {
		return p_code_tax_org;
	}

	public void setP_code_tax_org(String p_code_tax_org) {
		this.p_code_tax_org = p_code_tax_org;
	}

	public String getP_number_tax_registration() {
		return p_number_tax_registration;
	}

	public void setP_number_tax_registration(String p_number_tax_registration) {
		this.p_number_tax_registration = p_number_tax_registration;
	}

	public String getP_code_bank() {
		return p_code_bank;
	}

	public void setP_code_bank(String p_code_bank) {
		this.p_code_bank = p_code_bank;
	}

	public String getP_code_class_credit() {
		return p_code_class_credit;
	}

	public void setP_code_class_credit(String p_code_class_credit) {
		this.p_code_class_credit = p_code_class_credit;
	}

	public String getP_code_citizenship() {
		return p_code_citizenship;
	}

	public void setP_code_citizenship(String p_code_citizenship) {
		this.p_code_citizenship = p_code_citizenship;
	}

	public String getP_birth_place() {
		return p_birth_place;
	}

	public void setP_birth_place(String p_birth_place) {
		this.p_birth_place = p_birth_place;
	}

	public String getP_code_capacity() {
		return p_code_capacity;
	}

	public void setP_code_capacity(String p_code_capacity) {
		this.p_code_capacity = p_code_capacity;
	}

	public Date getP_capacity_status_date() {
		return p_capacity_status_date;
	}

	public void setP_capacity_status_date(Date p_capacity_status_date) {
		this.p_capacity_status_date = p_capacity_status_date;
	}

	public String getP_capacity_status_place() {
		return p_capacity_status_place;
	}

	public void setP_capacity_status_place(String p_capacity_status_place) {
		this.p_capacity_status_place = p_capacity_status_place;
	}

	public String getP_num_certif_capacity() {
		return p_num_certif_capacity;
	}

	public void setP_num_certif_capacity(String p_num_certif_capacity) {
		this.p_num_certif_capacity = p_num_certif_capacity;
	}

	public String getP_phone_home() {
		return p_phone_home;
	}

	public void setP_phone_home(String p_phone_home) {
		this.p_phone_home = p_phone_home;
	}

	public String getP_phone_mobile() {
		return p_phone_mobile;
	}

	public void setP_phone_mobile(String p_phone_mobile) {
		this.p_phone_mobile = p_phone_mobile;
	}

	public String getP_email_address() {
		return p_email_address;
	}

	public void setP_email_address(String p_email_address) {
		this.p_email_address = p_email_address;
	}

	public String getP_pension_sertif_serial() {
		return p_pension_sertif_serial;
	}

	public void setP_pension_sertif_serial(String p_pension_sertif_serial) {
		this.p_pension_sertif_serial = p_pension_sertif_serial;
	}

	public String getP_code_gender() {
		return p_code_gender;
	}

	public void setP_code_gender(String p_code_gender) {
		this.p_code_gender = p_code_gender;
	}

	public String getP_code_nation() {
		return p_code_nation;
	}

	public void setP_code_nation(String p_code_nation) {
		this.p_code_nation = p_code_nation;
	}

	public String getP_code_birth_region() {
		return p_code_birth_region;
	}

	public void setP_code_birth_region(String p_code_birth_region) {
		this.p_code_birth_region = p_code_birth_region;
	}

	public String getP_code_birth_distr() {
		return p_code_birth_distr;
	}

	public void setP_code_birth_distr(String p_code_birth_distr) {
		this.p_code_birth_distr = p_code_birth_distr;
	}

	public String getP_type_document() {
		return p_type_document;
	}

	public void setP_type_document(String p_type_document) {
		this.p_type_document = p_type_document;
	}

	public Date getP_passport_date_expiration() {
		return p_passport_date_expiration;
	}

	public void setP_passport_date_expiration(Date p_passport_date_expiration) {
		this.p_passport_date_expiration = p_passport_date_expiration;
	}

	public String getP_code_adr_region() {
		return p_code_adr_region;
	}

	public void setP_code_adr_region(String p_code_adr_region) {
		this.p_code_adr_region = p_code_adr_region;
	}

	public String getP_code_adr_distr() {
		return p_code_adr_distr;
	}

	public void setP_code_adr_distr(String p_code_adr_distr) {
		this.p_code_adr_distr = p_code_adr_distr;
	}

	public String getP_inps() {
		return p_inps;
	}

	public void setP_inps(String p_inps) {
		this.p_inps = p_inps;
	}

	public String getP_family() {
		return p_family;
	}

	public void setP_family(String p_family) {
		this.p_family = p_family;
	}

	public String getP_first_name() {
		return p_first_name;
	}

	public void setP_first_name(String p_first_name) {
		this.p_first_name = p_first_name;
	}

	public String getP_patronymic() {
		return p_patronymic;
	}

	public void setP_patronymic(String p_patronymic) {
		this.p_patronymic = p_patronymic;
	}

	public String getP_last_name_cyr() {
		return p_last_name_cyr;
	}

	public void setP_last_name_cyr(String p_last_name_cyr) {
		this.p_last_name_cyr = p_last_name_cyr;
	}

	public String getP_first_name_cyr() {
		return p_first_name_cyr;
	}

	public void setP_first_name_cyr(String p_first_name_cyr) {
		this.p_first_name_cyr = p_first_name_cyr;
	}

	public String getP_patronymic_cyr() {
		return p_patronymic_cyr;
	}

	public void setP_patronymic_cyr(String p_patronymic_cyr) {
		this.p_patronymic_cyr = p_patronymic_cyr;
	}

	public String getP_pass_place_region() {
		return p_pass_place_region;
	}

	public void setP_pass_place_region(String p_pass_place_region) {
		this.p_pass_place_region = p_pass_place_region;
	}

	public String getP_pass_place_district() {
		return p_pass_place_district;
	}

	public void setP_pass_place_district(String p_pass_place_district) {
		this.p_pass_place_district = p_pass_place_district;
	}

	public String getJ_nibbd_acc_bal() {
		return j_nibbd_acc_bal;
	}

	public void setJ_nibbd_acc_bal(String j_nibbd_acc_bal) {
		this.j_nibbd_acc_bal = j_nibbd_acc_bal;
	}
	
	public String getJ_nibbd_reason() {
		return j_nibbd_reason;
	}

	public void setJ_nibbd_reason(String j_nibbd_reason) {
		this.j_nibbd_reason = j_nibbd_reason;
	}

	public String getP_post_address_street() {
		return p_post_address_street;
	}

	public void setP_post_address_street(String p_post_address_street) {
		this.p_post_address_street = p_post_address_street;
	}

	public String getP_post_address_house() {
		return p_post_address_house;
	}

	public void setP_post_address_house(String p_post_address_house) {
		this.p_post_address_house = p_post_address_house;
	}

	public String getP_post_address_flat() {
		return p_post_address_flat;
	}

	public void setP_post_address_flat(String p_post_address_flat) {
		this.p_post_address_flat = p_post_address_flat;
	}

	public String getSwift_id() {
		return swift_id;
	}

	public void setSwift_id(String swift_id) {
		this.swift_id = swift_id;
	}

	public String getType_non_resident() {
		return type_non_resident;
	}

	public void setType_non_resident(String type_non_resident) {
		this.type_non_resident = type_non_resident;
	}

	public String getJ_sign_dep_acc() {
		return j_sign_dep_acc;
	}

	public void setJ_sign_dep_acc(String j_sign_dep_acc) {
		this.j_sign_dep_acc = j_sign_dep_acc;
	}

	public String getJ_type_activity() {
		return j_type_activity;
	}

	public void setJ_type_activity(String j_type_activity) {
		this.j_type_activity = j_type_activity;
	}

	public String getJ_nibbd_acc_id_order() {
		return j_nibbd_acc_id_order;
	}

	public void setJ_nibbd_acc_id_order(String j_nibbd_acc_id_order) {
		this.j_nibbd_acc_id_order = j_nibbd_acc_id_order;
	}

	public String getCapital_inform() {
		return capital_inform;
	}

	public void setCapital_inform(String capital_inform) {
		this.capital_inform = capital_inform;
	}

	public String getCapital_currency() {
		return capital_currency;
	}

	public void setCapital_currency(String capital_currency) {
		this.capital_currency = capital_currency;
	}

	public int getNibbd_emp_id() {
		return nibbd_emp_id;
	}

	public void setNibbd_emp_id(int nibbd_emp_id) {
		this.nibbd_emp_id = nibbd_emp_id;
	}

	public String getPost_address_country() {
		return post_address_country;
	}

	public void setPost_address_country(String post_address_country) {
		this.post_address_country = post_address_country;
	}

	public Date getDate_open1() {
		return date_open1;
	}

	public void setDate_open1(Date date_open1) {
		this.date_open1 = date_open1;
	}

	public Date getDate_close1() {
		return date_close1;
	}

	public void setDate_close1(Date date_close1) {
		this.date_close1 = date_close1;
	}

	public int getSign_date_open() {
		return sign_date_open;
	}

	public void setSign_date_open(int sign_date_open) {
		this.sign_date_open = sign_date_open;
	}

	public int getSign_date_close() {
		return sign_date_close;
	}

	public void setSign_date_close(int sign_date_close) {
		this.sign_date_close = sign_date_close;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public void setI_short_name(String i_short_name) {
		this.i_short_name = i_short_name;
	}

	public String getP_pinfl() {
		return p_pinfl;
	}

	public void setP_pinfl(String p_pinfl) {
		this.p_pinfl = p_pinfl;
	}
	
	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	
	

	public String getP_Code_Nibbd() {
		return p_Code_Nibbd;
	}

	public void setP_Code_Nibbd(String p_Code_Nibbd) {
		this.p_Code_Nibbd = p_Code_Nibbd;
	}

	public Date getP_Date_ApprovedNibbd() {
		return p_Date_ApprovedNibbd;
	}

	public void setP_Date_ApprovedNibbd(Date p_Date_ApprovedNibbd) {
		this.p_Date_ApprovedNibbd = p_Date_ApprovedNibbd;
	}

	public Date getP_Date_ClosedNibbd() {
		return p_Date_ClosedNibbd;
	}

	public void setP_Date_ClosedNibbd(Date p_Date_ClosedNibbd) {
		this.p_Date_ClosedNibbd = p_Date_ClosedNibbd;
	}

	
	
	public String getJ_file_name() {
		return j_file_name;
	}

	public void setJ_file_name(String j_file_name) {
		this.j_file_name = j_file_name;
	}

	public String getJ_director_family() {
		return j_director_family;
	}

	public String getJ_director_first_name() {
		return j_director_first_name;
	}

	public String getJ_director_patronymic() {
		return j_director_patronymic;
	}

	public String getJ_director_drv_permit_ser() {
		return j_director_drv_permit_ser;
	}

	public String getJ_director_drv_permit_num() {
		return j_director_drv_permit_num;
	}

	public Date getJ_director_drv_permit_reg_d() {
		return j_director_drv_permit_reg_d;
	}

	public Date getJ_director_drv_permit_exp_d() {
		return j_director_drv_permit_exp_d;
	}

	public String getJ_director_drv_permit_place() {
		return j_director_drv_permit_place;
	}

	public String getJ_accountant_family() {
		return j_accountant_family;
	}

	public String getJ_accountant_first_name() {
		return j_accountant_first_name;
	}

	public String getJ_accountant_patronymic() {
		return j_accountant_patronymic;
	}

	public String getJ_accountant_drv_permit_ser() {
		return j_accountant_drv_permit_ser;
	}

	public String getJ_accountant_drv_permit_num() {
		return j_accountant_drv_permit_num;
	}

	public Date getJ_accountant_drv_permit_reg_d() {
		return j_accountant_drv_permit_reg_d;
	}

	public Date getJ_accountant_drv_permit_exp_d() {
		return j_accountant_drv_permit_exp_d;
	}

	public String getJ_accountant_drv_permit_place() {
		return j_accountant_drv_permit_place;
	}

	public void setJ_director_family(String j_director_family) {
		this.j_director_family = j_director_family;
	}

	public void setJ_director_first_name(String j_director_first_name) {
		this.j_director_first_name = j_director_first_name;
	}

	public void setJ_director_patronymic(String j_director_patronymic) {
		this.j_director_patronymic = j_director_patronymic;
	}

	public void setJ_director_drv_permit_ser(String j_director_drv_permit_ser) {
		this.j_director_drv_permit_ser = j_director_drv_permit_ser;
	}

	public void setJ_director_drv_permit_num(String j_director_drv_permit_num) {
		this.j_director_drv_permit_num = j_director_drv_permit_num;
	}

	public void setJ_director_drv_permit_reg_d(Date j_director_drv_permit_reg_d) {
		this.j_director_drv_permit_reg_d = j_director_drv_permit_reg_d;
	}

	public void setJ_director_drv_permit_exp_d(Date j_director_drv_permit_exp_d) {
		this.j_director_drv_permit_exp_d = j_director_drv_permit_exp_d;
	}

	public void setJ_director_drv_permit_place(String j_director_drv_permit_place) {
		this.j_director_drv_permit_place = j_director_drv_permit_place;
	}

	public void setJ_accountant_family(String j_accountant_family) {
		this.j_accountant_family = j_accountant_family;
	}

	public void setJ_accountant_first_name(String j_accountant_first_name) {
		this.j_accountant_first_name = j_accountant_first_name;
	}

	public void setJ_accountant_patronymic(String j_accountant_patronymic) {
		this.j_accountant_patronymic = j_accountant_patronymic;
	}

	public void setJ_accountant_drv_permit_ser(String j_accountant_drv_permit_ser) {
		this.j_accountant_drv_permit_ser = j_accountant_drv_permit_ser;
	}

	public void setJ_accountant_drv_permit_num(String j_accountant_drv_permit_num) {
		this.j_accountant_drv_permit_num = j_accountant_drv_permit_num;
	}

	public void setJ_accountant_drv_permit_reg_d(Date j_accountant_drv_permit_reg_d) {
		this.j_accountant_drv_permit_reg_d = j_accountant_drv_permit_reg_d;
	}

	public void setJ_accountant_drv_permit_exp_d(Date j_accountant_drv_permit_exp_d) {
		this.j_accountant_drv_permit_exp_d = j_accountant_drv_permit_exp_d;
	}

	public void setJ_accountant_drv_permit_place(
			String j_accountant_drv_permit_place) {
		this.j_accountant_drv_permit_place = j_accountant_drv_permit_place;
	}

	@Override
	public String toString() {
		return "ClientA [id=" + id + ", branch=" + branch + ", id_client=" + id_client + ", id_client_in_sap="
				+ id_client_in_sap + ", name=" + name + ", code_country=" + code_country + ", code_type=" + code_type
				+ ", code_resident=" + code_resident + ", code_subject=" + code_subject + ", sign_registr="
				+ sign_registr + ", code_form=" + code_form + ", date_open=" + date_open + ", date_close=" + date_close
				+ ", state=" + state + ", kod_err=" + kod_err + ", file_name=" + file_name + ", sign_100=" + sign_100
				+ ", j_short_name=" + j_short_name + ", j_place_regist_name=" + j_place_regist_name
				+ ", j_date_registration=" + j_date_registration + ", j_number_registration_doc="
				+ j_number_registration_doc + ", j_code_tax_org=" + j_code_tax_org + ", j_number_tax_registration="
				+ j_number_tax_registration + ", j_code_sector=" + j_code_sector + ", j_code_sector_old="
				+ j_code_sector_old + ", j_code_organ_direct=" + j_code_organ_direct + ", j_code_head_organization="
				+ j_code_head_organization + ", j_code_class_credit=" + j_code_class_credit + ", j_director_name="
				+ j_director_name + ", j_director_passport=" + j_director_passport + ", j_chief_accounter_name="
				+ j_chief_accounter_name + ", j_chief_accounter_passport=" + j_chief_accounter_passport
				+ ", j_code_bank=" + j_code_bank + ", j_account=" + j_account + ", j_post_address=" + j_post_address
				+ ", j_phone=" + j_phone + ", j_fax=" + j_fax + ", j_email=" + j_email + ", j_sign_trade="
				+ j_sign_trade + ", j_opf=" + j_opf + ", j_soato=" + j_soato + ", j_okpo=" + j_okpo
				+ ", j_inn_head_organization=" + j_inn_head_organization + ", j_region=" + j_region + ", j_distr="
				+ j_distr + ", j_small_business=" + j_small_business + ", j_director_type_document="
				+ j_director_type_document + ", j_director_passp_serial=" + j_director_passp_serial
				+ ", j_director_passp_number=" + j_director_passp_number + ", j_director_passp_date_reg="
				+ j_director_passp_date_reg + ", j_director_passp_place_reg=" + j_director_passp_place_reg
				+ ", j_director_passp_date_end=" + j_director_passp_date_end + ", j_director_code_citizenship="
				+ j_director_code_citizenship + ", j_director_birthday=" + j_director_birthday
				+ ", j_director_birth_place=" + j_director_birth_place + ", j_director_address=" + j_director_address
				+ ", j_accountant_type_document=" + j_accountant_type_document + ", j_accountant_passp_serial="
				+ j_accountant_passp_serial + ", j_accountant_passp_number=" + j_accountant_passp_number
				+ ", j_accountant_passp_date_reg=" + j_accountant_passp_date_reg + ", j_accountant_passp_place_reg="
				+ j_accountant_passp_place_reg + ", j_accountant_passp_date_end=" + j_accountant_passp_date_end
				+ ", j_accountant_code_citizenship=" + j_accountant_code_citizenship + ", j_accountant_birthday="
				+ j_accountant_birthday + ", j_accountant_birth_place=" + j_accountant_birth_place
				+ ", j_accountant_address=" + j_accountant_address + ", j_327=" + j_327 + ", j_patent_expiration="
				+ j_patent_expiration + ", j_responsible_emp=" + j_responsible_emp + ", i_short_name=" + i_short_name
				+ ", i_date_registration=" + i_date_registration + ", i_number_registration_doc="
				+ i_number_registration_doc + ", i_place_regist_name=" + i_place_regist_name
				+ ", i_number_tax_registration=" + i_number_tax_registration + ", i_opf=" + i_opf + ", i_form=" + i_form
				+ ", i_sector=" + i_sector + ", i_organ_direct=" + i_organ_direct + ", i_account=" + i_account
				+ ", i_post_address=" + i_post_address + ", i_director_name=" + i_director_name
				+ ", i_chief_accounter_name=" + i_chief_accounter_name + ", i_phone=" + i_phone + ", i_fax=" + i_fax
				+ ", i_email=" + i_email + ", p_birthday=" + p_birthday + ", p_pinfl=" + p_pinfl + ", p_Code_Nibbd="
				+ p_Code_Nibbd + ", p_Date_ApprovedNibbd=" + p_Date_ApprovedNibbd + ", p_Date_ClosedNibbd="
				+ p_Date_ClosedNibbd + ", p_post_address=" + p_post_address + ", p_passport_type=" + p_passport_type
				+ ", p_passport_serial=" + p_passport_serial + ", p_passport_number=" + p_passport_number
				+ ", p_passport_place_registration=" + p_passport_place_registration + ", p_passport_date_registration="
				+ p_passport_date_registration + ", p_code_tax_org=" + p_code_tax_org + ", p_number_tax_registration="
				+ p_number_tax_registration + ", p_code_bank=" + p_code_bank + ", p_code_class_credit="
				+ p_code_class_credit + ", p_code_citizenship=" + p_code_citizenship + ", p_birth_place="
				+ p_birth_place + ", p_code_capacity=" + p_code_capacity + ", p_capacity_status_date="
				+ p_capacity_status_date + ", p_capacity_status_place=" + p_capacity_status_place
				+ ", p_num_certif_capacity=" + p_num_certif_capacity + ", p_phone_home=" + p_phone_home
				+ ", p_phone_mobile=" + p_phone_mobile + ", p_email_address=" + p_email_address
				+ ", p_pension_sertif_serial=" + p_pension_sertif_serial + ", p_code_gender=" + p_code_gender
				+ ", p_code_nation=" + p_code_nation + ", p_code_birth_region=" + p_code_birth_region
				+ ", p_code_birth_distr=" + p_code_birth_distr + ", p_type_document=" + p_type_document
				+ ", p_passport_date_expiration=" + p_passport_date_expiration + ", p_code_adr_region="
				+ p_code_adr_region + ", p_code_adr_distr=" + p_code_adr_distr + ", p_inps=" + p_inps + ", p_name="
				+ p_name + ", p_family=" + p_family + ", p_first_name=" + p_first_name + ", p_patronymic="
				+ p_patronymic + ", p_last_name_cyr=" + p_last_name_cyr + ", p_first_name_cyr=" + p_first_name_cyr
				+ ", p_patronymic_cyr=" + p_patronymic_cyr + ", p_pass_place_region=" + p_pass_place_region
				+ ", p_pass_place_district=" + p_pass_place_district + ", j_nibbd_acc_bal="
				+ j_nibbd_acc_bal + ", p_post_address_street=" + p_post_address_street
				+ ", p_post_address_house=" + p_post_address_house + ", p_post_address_flat=" + p_post_address_flat
				+ ", swift_id=" + swift_id + ", type_non_resident=" + type_non_resident + ", j_sign_dep_acc="
				+ j_sign_dep_acc + ", j_type_activity=" + j_type_activity + ", j_nibbd_acc_id_order=" + j_nibbd_acc_id_order + ", capital_inform="
				+ capital_inform + ", capital_currency=" + capital_currency + ", nibbd_emp_id=" + nibbd_emp_id
				+ ", post_address_country=" + post_address_country + ", date_open1=" + date_open1 + ", date_close1="
				+ date_close1 + ", sign_date_open=" + sign_date_open + ", sign_date_close=" + sign_date_close
				+ ", addressCountry=" + addressCountry + "]";
	}
	

}
