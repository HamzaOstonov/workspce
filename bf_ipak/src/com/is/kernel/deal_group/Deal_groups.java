package com.is.kernel.deal_group;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class Deal_groups
{
	private HashMap<Long, Deal_group> deal_groups;
	
	public Deal_groups(Connection c) throws SQLException
	{
		deal_groups = Service.get_deal_groups(c);
	}
	
	public Deal_group get(Long deal_group_id) throws DealGroupNotFoundException
	{
		Deal_group res = null;
		res = deal_groups.get(deal_group_id);
		if (res == null) throw new DealGroupNotFoundException(deal_group_id.toString());
		return res;
	}

	@Override
	public int hashCode()
	{
	    return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
	    return this.toString().equals(o);
	}
}
