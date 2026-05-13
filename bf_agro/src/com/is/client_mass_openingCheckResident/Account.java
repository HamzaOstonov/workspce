package  com.is.client_mass_openingCheckResident; 
import java.math.BigDecimal;
import java.util.Date;


public class Account {
	
	  private String branch;
	    private String id;
	     private String acc_bal;
	     private String currency;
	     private String client;
	     private String id_order;
	     private String name;
	     private String sgn;
	     private String bal;
	      private int sign_registr;
	      private BigDecimal s_in;
	      private BigDecimal s_out;
	      private BigDecimal dt;
	      private BigDecimal ct;
	      private BigDecimal s_in_tmp;
	      private BigDecimal s_out_tmp;
	      private BigDecimal dt_tmp;
	      private BigDecimal ct_tmp;
	      private Date l_date;
	      private Date date_open;
	      private Date date_close;
	      private String acc_group_id;
	      private int state;
	      private String state_desc;
	      private String client_name;

	    public Account() {
	    	super();
	    }
    
    private Account(String id) {
    	this.id = id;
    }
    
    public static Account instanceWithId(String id) {
    	return new Account(id);
    }
    
	public Account(String branch, String id, String acc_bal, String currency,
			String client, String id_order, String name, String sgn,
			String bal, int sign_registr, BigDecimal s_in, BigDecimal s_out, BigDecimal dt,
			BigDecimal ct, BigDecimal s_in_tmp, BigDecimal s_out_tmp, BigDecimal dt_tmp, BigDecimal ct_tmp,
			Date l_date, Date date_open, Date date_close, String acc_group_id,
			int state) {
		super();
		this.branch = branch;
		this.id = id;
		this.acc_bal = acc_bal;
		this.currency = currency;
		this.client = client;
		this.id_order = id_order;
		this.name = name;
		this.sgn = sgn;
		this.bal = bal;
		this.sign_registr = sign_registr;
		this.s_in = s_in;
		this.s_out = s_out;
		this.dt = dt;
		this.ct = ct;
		this.s_in_tmp = s_in_tmp;
		this.s_out_tmp = s_out_tmp;
		this.dt_tmp = dt_tmp;
		this.ct_tmp = ct_tmp;
		this.l_date = l_date;
		this.date_open = date_open;
		this.date_close = date_close;
		this.acc_group_id = acc_group_id;
		this.state = state;
	}
	
	public Account(String branch, String id, String acc_bal, String currency,
			String client, String id_order, String name, String sgn,
			String bal, int sign_registr, BigDecimal s_in, BigDecimal s_out, BigDecimal dt,
			BigDecimal ct, BigDecimal s_in_tmp, BigDecimal s_out_tmp, BigDecimal dt_tmp, BigDecimal ct_tmp,
			Date l_date, Date date_open, Date date_close, String acc_group_id,
			int state, String state_desc) {
		super();
		this.branch = branch;
		this.id = id;
		this.acc_bal = acc_bal;
		this.currency = currency;
		this.client = client;
		this.id_order = id_order;
		this.name = name;
		this.sgn = sgn;
		this.bal = bal;
		this.sign_registr = sign_registr;
		this.s_in = s_in;
		this.s_out = s_out;
		this.dt = dt;
		this.ct = ct;
		this.s_in_tmp = s_in_tmp;
		this.s_out_tmp = s_out_tmp;
		this.dt_tmp = dt_tmp;
		this.ct_tmp = ct_tmp;
		this.l_date = l_date;
		this.date_open = date_open;
		this.date_close = date_close;
		this.acc_group_id = acc_group_id;
		this.state = state;
		this.state_desc = state_desc;
	}

	public Account(String branch, String id, String client, String name, BigDecimal s_in, BigDecimal s_out,
			BigDecimal dt, BigDecimal ct, Date l_date, int state) {
		super();
		this.branch = branch;
		this.id = id;
		this.client = client;
		this.name = name;
		this.s_in = s_in;
		this.s_out = s_out;
		this.dt = dt;
		this.ct = ct;
		this.l_date = l_date;
		this.state = state;
	}
	
	
}
