package com.is.bpri;

public class LdProductFilter {
   
	private int bpr_id;
	private String currency;
    private String ld_num;
    private String crc_num;
    private String shifr_id;
    private String prod_name;
    private String sred_id;
    private String target;
    private int calc_id;
    private int term_type;
    private int type_id;
    private String kred_id;
    private String klass_id;
    private int status;
    private String klassp_id;
    
    private String kred_id_cb;
    private int acceptance;
    private int cres;
    private int is_tax;
    private Long ld_amount;
    private String use_branch;

    public LdProductFilter () {

    }

	public LdProductFilter(int bpr_id,String currency, String ld_num, String crc_num,
			String shifr_id, String prod_name, String sred_id, String target,
			int calc_id, int term_type, int type_id, String kred_id,
			String klass_id, int status, String klassp_id, String kred_id_cb, int acceptance,
			int cres, int is_tax, Long ld_amount, String use_branch) {
		super();
		this.bpr_id=bpr_id;
		this.currency = currency;
		this.ld_num = ld_num;
		this.crc_num = crc_num;
		this.shifr_id = shifr_id;
		this.prod_name = prod_name;
		this.sred_id = sred_id;
		this.target = target;
		this.calc_id = calc_id;
		this.term_type = term_type;
		this.type_id = type_id;
		this.kred_id = kred_id;
		this.klass_id = klass_id;
		this.status = status;
		this.klassp_id = klassp_id;
		
		this.kred_id_cb = kred_id_cb;
		this.acceptance = acceptance;
		this.cres = cres;
		this.is_tax = is_tax;
		this. ld_amount = ld_amount;
		this.use_branch = use_branch;
	}
	

	public int getId() {
		return bpr_id;
	}



	public void setId(int id) {
		this.bpr_id = id;
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

	public int getCalc_id() {
		return calc_id;
	}

	public void setCalc_id(int calc_id) {
		this.calc_id = calc_id;
	}

	public int getTerm_type() {
		return term_type;
	}

	public void setTerm_type(int term_type) {
		this.term_type = term_type;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getKlassp_id() {
		return klassp_id;
	}

	public void setKlassp_id(String klassp_id) {
		this.klassp_id = klassp_id;
	}
	
	public String getKred_id_cb()
	{
		return kred_id_cb;
	}
	
	public void setKred_id_cb(String kred_id_cb)
	{
		this.kred_id_cb = kred_id_cb;
	}
	
	public int getAcceptance()
	{
		return acceptance;
	}
    
	public void setAcceptance(int acceptance)
	{
		this.acceptance = acceptance;
	}
	
	public int getCres()
	{
		return cres;
	}
	
	public void setCres(int cres)
	{
		this.cres = cres;
	}
	
	public int getIs_tax()
	{
		return is_tax;
	}
	
	public void setIs_tax(int is_tax)
	{
		this.is_tax = is_tax;
	}
	
	public Long getLd_amount()
	{
		return ld_amount;
	}
	
	public void setLd_amount(Long ld_amount)
	{
		this.ld_amount = ld_amount;
	}
	
	public String getUse_branch()
	{
		return use_branch;
	}
	
	public void setUse_branch(String use_branch)
	{
		this.use_branch = use_branch;
	}
}
