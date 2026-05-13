package properties;

import com.is.file_reciever_srv.exception.Reciever_exception;

public class Property_not_found_exception extends Reciever_exception
{

	public Property_not_found_exception(String message)
	{
		super(message);
	}
	
}
