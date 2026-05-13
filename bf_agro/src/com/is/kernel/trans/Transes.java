package com.is.kernel.trans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class Transes
{
	private HashMap<Trans_pk, Trans> transes;
	
	public Transes(Connection c) throws SQLException
	{
		transes = Service.get_transes(c);
	}
	
	private Trans get(Trans_pk trans_pk) throws TransNotFoundException
	{
		Trans res = transes.get(trans_pk);
		if (res == null) throw new TransNotFoundException(trans_pk.toString());
		return res;
	}
}
