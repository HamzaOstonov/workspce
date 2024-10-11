package com.is.file_reciever.energo;

public class Ext_in_file_recordsFilter
{
	 private Long id;
	    private Long in_file_id;
	    private Long type_record_id;
	    private String rec_value;
	    private Long error_id;
	    private Long err_message;
	    private Long current_file_fr_id;

	    public Ext_in_file_recordsFilter()
	    {
	    }

	    public Ext_in_file_recordsFilter( Long id, Long in_file_id, 
	    		Long type_record_id, String rec_value, Long error_id, 
	    		Long err_message, Long current_file_fr_id)
	    {

	                this.id = id;
	                this.in_file_id = in_file_id;
	                this.type_record_id = type_record_id;
	                this.rec_value = rec_value;
	                this.error_id = error_id;
	                this.err_message = err_message;
	                this.current_file_fr_id = current_file_fr_id;
	        }

		public Long getId()
		{
			return id;
		}

		public void setId(Long id)
		{
			this.id = id;
		}

		public Long getIn_file_id()
		{
			return in_file_id;
		}

		public void setIn_file_id(Long in_file_id)
		{
			this.in_file_id = in_file_id;
		}

		public Long getType_record_id()
		{
			return type_record_id;
		}

		public void setType_record_id(Long type_record_id)
		{
			this.type_record_id = type_record_id;
		}

		public String getRec_value()
		{
			return rec_value;
		}

		public void setRec_value(String rec_value)
		{
			this.rec_value = rec_value;
		}

		public Long getError_id()
		{
			return error_id;
		}

		public void setError_id(Long error_id)
		{
			this.error_id = error_id;
		}

		public Long getErr_message()
		{
			return err_message;
		}

		public void setErr_message(Long err_message)
		{
			this.err_message = err_message;
		}

		public Long getCurrent_file_fr_id()
		{
			return current_file_fr_id;
		}

		public void setCurrent_file_fr_id(Long current_file_fr_id)
		{
			this.current_file_fr_id = current_file_fr_id;
		}
	    
}
