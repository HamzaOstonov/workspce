package com.is.tf.paymentref;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.tf.Accreditivsumchange.Accreditivsumchange;
import com.is.tf.paymentrefsumchange.Paymentrefsumchange;

public class Paymentref {

	 private Long id;
	    private String p1t37;
	    private Long p0t37;
	    private String p2t37;
	    private Date p3t37;
	    private Long p4t37;
	    private String p5t37;
	    private String p6t37;
	    private String p7t37;
	    private String p8t37;
	    private Double p9t37;
	    private String p10t37;
	    private Double p11t37;
	    private Date p12t37;
	    private Long p13t37;
	    private String p14t37;
	    private String p15t37;
	    private String p16t37;
	    private String p17t37;
	    private String p18t37;
	    private String p19t37;
	    private Long id_contract;
	    private String p21t37;
	    private String p24t37;
	    private Date p25t37;
	    private String p100t37;
	    private String p26t37;
	    private List<Paymentrefsumchange> sumchanges = new ArrayList<Paymentrefsumchange>();

    public Paymentref() {
    	super();
    }

	public Paymentref(Long id, String p1t37, Long p0t37, String p2t37,
			Date p3t37, Long p4t37, String p5t37, String p6t37, String p7t37,
			String p8t37, Double p9t37, String p10t37, Double p11t37,
			Date p12t37, Long p13t37, String p14t37, String p15t37,
			String p16t37, String p17t37, String p18t37, String p19t37,
			Long id_contract, String p21t37, String p24t37, Date p25t37,
			String p100t37,String p26t37) {
		super();
		this.id = id;
		this.p1t37 = p1t37;
		this.p0t37 = p0t37;
		this.p2t37 = p2t37;
		this.p3t37 = p3t37;
		this.p4t37 = p4t37;
		this.p5t37 = p5t37;
		this.p6t37 = p6t37;
		this.p7t37 = p7t37;
		this.p8t37 = p8t37;
		this.p9t37 = p9t37;
		this.p10t37 = p10t37;
		this.p11t37 = p11t37;
		this.p12t37 = p12t37;
		this.p13t37 = p13t37;
		this.p14t37 = p14t37;
		this.p15t37 = p15t37;
		this.p16t37 = p16t37;
		this.p17t37 = p17t37;
		this.p18t37 = p18t37;
		this.p19t37 = p19t37;
		this.id_contract = id_contract;
		this.p21t37 = p21t37;
		this.p24t37 = p24t37;
		this.p25t37 = p25t37;
		this.p100t37 = p100t37;
		this.p26t37 = p26t37;
		
	}
	
	public Paymentref(Date p3t37, Long p4t37, String p5t37, String p6t37, String p7t37,
			String p8t37, Double p9t37, String p10t37, 
			Date p12t37, String p21t37) {
		super();
		
		this.p3t37 = p3t37;
		this.p4t37 = p4t37;
		this.p5t37 = p5t37;
		this.p6t37 = p6t37;
		this.p7t37 = p7t37;
		this.p8t37 = p8t37;
		this.p9t37 = p9t37;
		this.p10t37 = p10t37;
		this.p12t37 = p12t37;
		this.p21t37 = p21t37;
			
	}
	public Paymentref(Long id, String p1t37, Long p0t37, String p2t37,
			Date p3t37, Long p4t37, String p5t37, String p6t37, String p7t37,
			String p8t37, Double p9t37, String p10t37, Double p11t37,
			Date p12t37, Long p13t37, String p14t37, String p15t37,
			String p16t37, String p17t37, String p18t37, String p19t37,
			Long id_contract, String p21t37, String p24t37, Date p25t37,
			String p100t37, String p26t37, List<Paymentrefsumchange> sumchanges) {
		super();
		this.id = id;
		this.p1t37 = p1t37;
		this.p0t37 = p0t37;
		this.p2t37 = p2t37;
		this.p3t37 = p3t37;
		this.p4t37 = p4t37;
		this.p5t37 = p5t37;
		this.p6t37 = p6t37;
		this.p7t37 = p7t37;
		this.p8t37 = p8t37;
		this.p9t37 = p9t37;
		this.p10t37 = p10t37;
		this.p11t37 = p11t37;
		this.p12t37 = p12t37;
		this.p13t37 = p13t37;
		this.p14t37 = p14t37;
		this.p15t37 = p15t37;
		this.p16t37 = p16t37;
		this.p17t37 = p17t37;
		this.p18t37 = p18t37;
		this.p19t37 = p19t37;
		this.id_contract = id_contract;
		this.p21t37 = p21t37;
		this.p24t37 = p24t37;
		this.p25t37 = p25t37;
		this.p100t37 = p100t37;
		this.p26t37 = p26t37;
		this.sumchanges = sumchanges;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getP1t37() {
		return p1t37;
	}

	public void setP1t37(String p1t37) {
		this.p1t37 = p1t37;
	}

	public Long getP0t37() {
		return p0t37;
	}

	public void setP0t37(Long p0t37) {
		this.p0t37 = p0t37;
	}

	public String getP2t37() {
		return p2t37;
	}

	public void setP2t37(String p2t37) {
		this.p2t37 = p2t37;
	}

	public Date getP3t37() {
		return p3t37;
	}

	public void setP3t37(Date p3t37) {
		this.p3t37 = p3t37;
	}

	public Long getP4t37() {
		return p4t37;
	}

	public void setP4t37(Long p4t37) {
		this.p4t37 = p4t37;
	}

	public String getP5t37() {
		return p5t37;
	}

	public void setP5t37(String p5t37) {
		this.p5t37 = p5t37;
	}

	public String getP6t37() {
		return p6t37;
	}

	public void setP6t37(String p6t37) {
		this.p6t37 = p6t37;
	}

	public String getP7t37() {
		return p7t37;
	}

	public void setP7t37(String p7t37) {
		this.p7t37 = p7t37;
	}

	public String getP8t37() {
		return p8t37;
	}

	public void setP8t37(String p8t37) {
		this.p8t37 = p8t37;
	}

	public Double getP9t37() {
		return p9t37;
	}

	public void setP9t37(Double p9t37) {
		this.p9t37 = p9t37;
	}

	public String getP10t37() {
		return p10t37;
	}

	public void setP10t37(String p10t37) {
		this.p10t37 = p10t37;
	}

	public Double getP11t37() {
		return p11t37;
	}

	public void setP11t37(Double p11t37) {
		this.p11t37 = p11t37;
	}

	public Date getP12t37() {
		return p12t37;
	}

	public void setP12t37(Date p12t37) {
		this.p12t37 = p12t37;
	}

	public Long getP13t37() {
		return p13t37;
	}

	public void setP13t37(Long p13t37) {
		this.p13t37 = p13t37;
	}

	public String getP14t37() {
		return p14t37;
	}

	public void setP14t37(String p14t37) {
		this.p14t37 = p14t37;
	}

	public String getP15t37() {
		return p15t37;
	}

	public void setP15t37(String p15t37) {
		this.p15t37 = p15t37;
	}

	public String getP16t37() {
		return p16t37;
	}

	public void setP16t37(String p16t37) {
		this.p16t37 = p16t37;
	}

	public String getP17t37() {
		return p17t37;
	}

	public void setP17t37(String p17t37) {
		this.p17t37 = p17t37;
	}

	public String getP18t37() {
		return p18t37;
	}

	public void setP18t37(String p18t37) {
		this.p18t37 = p18t37;
	}

	public String getP19t37() {
		return p19t37;
	}

	public void setP19t37(String p19t37) {
		this.p19t37 = p19t37;
	}

	public Long getId_contract() {
		return id_contract;
	}

	public void setId_contract(Long id_contract) {
		this.id_contract = id_contract;
	}

	public String getP21t37() {
		return p21t37;
	}

	public void setP21t37(String p21t37) {
		this.p21t37 = p21t37;
	}

	public String getP24t37() {
		return p24t37;
	}

	public void setP24t37(String p24t37) {
		this.p24t37 = p24t37;
	}

	public Date getP25t37() {
		return p25t37;
	}

	public void setP25t37(Date p25t37) {
		this.p25t37 = p25t37;
	}

	public String getP100t37() {
		return p100t37;
	}

	public void setP100t37(String p100t37) {
		this.p100t37 = p100t37;
	}
	
	public String getP26t37() {
		return p26t37;
	}

	public void setP26t37(String p26t37) {
		this.p26t37 = p26t37;
	}
	public List<Paymentrefsumchange> getSumchanges() {
		return sumchanges;
	}



	public void setSumchanges(List<Paymentrefsumchange> sumchanges) {
		this.sumchanges = sumchanges;
	}


	
}
