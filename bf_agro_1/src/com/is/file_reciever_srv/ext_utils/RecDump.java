package com.is.file_reciever_srv.ext_utils;

import java.util.Date;

public class RecDump {
	    private Long recid;
	    private Long file_in_id;
	    private Long file_type;
	    private String rec_dump;
	    private Long file_out_id;
	    private Long general_id;
	    private int error_id;
	    private int state;
	    private Date v_date;
	    private String err_message;
	    private Long file_type_id;

	    public RecDump() {
	    	super();
	    }

		public RecDump(Long recid, Long file_in_id, Long file_type,
				String rec_dump, Long file_out_id, Long general_id,
				int error_id, int state, Date v_date, 
				String err_message, Long file_type_id) {
			super();
			this.recid = recid;
			this.file_in_id = file_in_id;
			this.file_type = file_type;
			this.rec_dump = rec_dump;
			this.file_out_id = file_out_id;
			this.general_id = general_id;
			this.error_id = error_id;
			this.state = state;
			this.v_date = v_date;
			this.file_type_id = file_type_id;
		}

		public Long getRecid() {
			return recid;
		}

		public void setRecid(Long recid) {
			this.recid = recid;
		}

		public Long getFile_in_id() {
			return file_in_id;
		}

		public void setFile_in_id(Long file_in_id) {
			this.file_in_id = file_in_id;
		}

		public Long getFile_type() {
			return file_type;
		}

		public void setFile_type(Long file_type) {
			this.file_type = file_type;
		}

		public String getRec_dump() {
			return rec_dump;
		}

		public void setRec_dump(String rec_dump) {
			this.rec_dump = rec_dump;
		}

		public Long getFile_out_id() {
			return file_out_id;
		}

		public void setFile_out_id(Long file_out_id) {
			this.file_out_id = file_out_id;
		}

		public Long getGeneral_id() {
			return general_id;
		}

		public void setGeneral_id(Long general_id) {
			this.general_id = general_id;
		}

		public int getError_id()
		{
			return error_id;
		}

		public void setError_id(int error_id)
		{
			this.error_id = error_id;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public Date getV_date() {
			return v_date;
		}

		public void setV_date(Date v_date) {
			this.v_date = v_date;
		}

		public String getErr_message()
		{
			return err_message;
		}

		public void setErr_message(String err_message)
		{
			this.err_message = err_message;
		}

		public Long getFile_type_id()
		{
			return file_type_id;
		}

		public void setFile_type_id(Long file_type_id)
		{
			this.file_type_id = file_type_id;
		}		
}
