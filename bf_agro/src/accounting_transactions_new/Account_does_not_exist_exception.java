package accounting_transactions_new;

public class Account_does_not_exist_exception extends Exception
{
	Account_does_not_exist_exception(String message)
	{
		super(message);
	}
}
