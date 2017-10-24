package com.service.pay;

import com.model.order.Order;
import com.service.interfaces.PayServiceInterface;
import com.until.errorcode.MAGICCODE;

public class PayService implements PayServiceInterface
{

	protected Order order;
	
	@Override
	public int pay()
	{
		return MAGICCODE.OK;
	}

	@Override
	public int detailPayResult()
	{
		return MAGICCODE.OK;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}
	
}
