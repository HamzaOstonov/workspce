package com.is.bpri;

public class LdProduct {
	
	private int bpr_id;
	private String currency;
    private String ld_num;
    private String crc_num;
    private String shifr_id;
    private String prod_name;
    private String sred_id;
    private String target;
    private String calc_id;
    private String term_type;
    //private int type_id;
    private String kred_id;
    private String klass_id;
    private String status;
    private String klassp_id;
    private String ld_amount;
    private String kred_id_cb;
    private String acceptance;
    private String cres;
    private String is_tax;
    private String use_branch;

    public LdProduct () {

    }

    public LdProduct(int bpr_id, String currency, String ld_num, String crc_num,
			String shifr_id, String prod_name, String sred_id, String target,
			String calc_id, String term_type,  String kred_id,
			String klass_id, String status, String klassp_id, String kred_id_cb, String acceptance,
			String cres, String is_tax, String use_branch,String ld_amount) {
		super();
		this.bpr_id = bpr_id;
		this.currency = currency;
		this.ld_num = ld_num;
		this.crc_num = crc_num;
		this.shifr_id = shifr_id;
		this.prod_name = prod_name;
		this.sred_id = sred_id;
		this.target = target;
		this.calc_id = calc_id;
		this.term_type = term_type;
		//this.type_id = type_id;
		this.kred_id = kred_id;
		this.klass_id = klass_id;
		this.status = status;
		this.klassp_id = klassp_id;
		this.ld_amount = ld_amount;
		this.kred_id_cb = kred_id_cb;
		this.acceptance = acceptance;
		this.cres = cres;
		this.is_tax = is_tax;
		this.use_branch = use_branch;
		//this. ld_amount = ld_amount;
	}



	public int getId(){
		return bpr_id;
	}



	public void setId(int bpr_id) {
		this.bpr_id = bpr_id;
	}



	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLd_num() {
		return ld_num;
	}

	public void setLd_num(String ld_num) {
		this.ld_num = ld_num;
	}

	public String getCrc_num() {
		return crc_num;
	}

	public void setCrc_num(String crc_num) {
		this.crc_num = crc_num;
	}

	public String getShifr_id() {
		return shifr_id;
	}

	public void setShifr_id(String shifr_id) {
		this.shifr_id = shifr_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getSred_id() {
		return sred_id;
	}

	public void setSred_id(String sred_id) {
		this.sred_id = sred_id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCalc_id() {
		return calc_id;
	}

	public void setCalc_id(String calc_id) {
		this.calc_id = calc_id;
	}

	public String getTerm_type() {
		return term_type;
	}

	public void setTerm_type(String term_type) {
		this.term_type = term_type;
	}

	/*public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}*/

	public String getKred_id() {
		return kred_id;
	}

	public void setKred_id(String kred_id) {
		this.kred_id = kred_id;
	}

	public String getKlass_id() {
		return klass_id;
	}

	public void setKlass_id(String klass_id) {
		this.klass_id = klass_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKlassp_id() {
		return klassp_id;
	}

	public void setKlassp_id(String klassp_id) {
		this.klassp_id = klassp_id;
	}
	
	public String getKred_id_cb(){
		return kred_id_cb;
	}
	
	public void setKred_id_cb(String kred_id_cb){
		this.kred_id_cb = kred_id_cb;
	}
	
	public String getAcceptance(){
		return acceptance;
	}
    
	public void setAcceptance(String acceptance){
		this.acceptance = acceptance;
	}
	
	public String getCres(){
		return cres;
	}
	
	public void setCres(String cres){
		this.cres = cres;
	}
	
	public String getIs_tax(){
		return is_tax;
	}
	
	public void setIs_tax(String is_tax){
		this.is_tax = is_tax;
	}
	
	public String getUse_branch(){
		return use_branch;
	}
	
	public void setUse_branch(String use_branch){
		this.use_branch = use_branch;
	}

	public String getLd_amount() {
		return ld_amount;
	}

	public void setLd_amount(String ld_amount) {
		this.ld_amount = ld_amount;
	}
	
	/*public Long getLd_amount()
	{
		return ld_amount;
	}
	
	public void setLd_amount(Long ld_amount)
	{
		this.ld_amount = ld_amount;
	}*/
	

}
