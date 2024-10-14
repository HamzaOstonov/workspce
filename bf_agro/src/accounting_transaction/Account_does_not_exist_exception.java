package accounting_transaction;

public class Account_does_not_exist_exception extends Exception
{
	Account_does_not_exist_exception(String message)
	{
		super(message);
	}
}
