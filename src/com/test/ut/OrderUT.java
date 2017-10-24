package com.test.ut;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.model.order.Order;
import com.model.production.OrderProduction;
import com.service.order.OrderService;
import com.until.errorcode.MAGICCODE;

public class OrderUT
{

    public OrderService orderService;
    public String userID = "10";
    public String magicKey = "ccc65483";
    @Before
    public void setUp() throws Exception
    {
        orderService = new OrderService();
    }

    @Test
    public void testCreateOrder()
    {
//        String userID = "10";
        String address = "18";
//        String magicKey = "ccc65483";
        int result = orderService.createOrderByCart(userID, address, magicKey);
        assertEquals(MAGICCODE.OK,result);
    }
//    @Test
    public void testCreateOrderIM()
    {
//        String userID = "10";
        String address = "18";
//        String magicKey = "ccc65483";s
        long num = 3;
        String productionID = "1";
        
        int result = orderService.createOrderImmediately(userID, productionID, num, address, magicKey);
        assertEquals(MAGICCODE.OK,result);
    }
    
    @Test
    public void queryOrder()
    {
        List<Order> orderList = orderService.queryAllOrder(userID, magicKey);
        Iterator<Order> it = orderList.iterator();
        while(it.hasNext())
        {
            Order order = it.next();
            System.out.println("ADDRESS:"+order.getAddress());
            System.out.println("CUSTOMER:"+order.getCustomer());
            System.out.println("STATE:"+order.getState());
            System.out.println("TOTALPRICE:"+order.getTotalPrice());
            List<OrderProduction> productionList = order.getProductionList();
            Iterator<OrderProduction> pit = productionList.iterator();
            while(pit.hasNext())
            {
                OrderProduction production = pit.next();
                System.out.println("PRODUCTION:"+production.getName());
                System.out.println("PRODUCTION PRICE:"+production.getPrice());
                
            }
        }
    }

}
