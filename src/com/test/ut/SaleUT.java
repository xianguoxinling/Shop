package com.test.ut;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.model.order.Order;
import com.model.order.SaledHistory;
import com.model.production.OrderProduction;
import com.service.order.OrderService;
import com.service.sale.SaleService;
import com.until.errorcode.MAGICCODE;

public class SaleUT
{

    SaleService service = null;
    OrderService orderService = null;
    String magicKey = "ccc65483";
    
    @Before
    public void setUp() throws Exception
    {
        service = new SaleService();
        orderService = new OrderService();
    }

    @Test
    public void testCreateSaleHistory()
    {
        String orderID = "9539";
        Order order = orderService.queryOrderByID(orderID, magicKey);
        
        int result = MAGICCODE.OK;
        
        List<OrderProduction> productionList = order.getProductionList();
        Iterator<OrderProduction> it = productionList.iterator();
        while(it.hasNext())
        {
            OrderProduction production = it.next();
            SaledHistory saledHistory = new SaledHistory();
            saledHistory.setMagicKey(magicKey);
            saledHistory.setNum(production.getNum());
            saledHistory.setOrderID(orderID);
            saledHistory.setProductionBriftIntroduce(production.getBriefIntroduction());
            saledHistory.setProductionID(production.getId());
            saledHistory.setProductionName(production.getName());
            saledHistory.setProductionPic(production.getMainPic());
            saledHistory.setRealPrice(production.getNum()*production.getPrice());
            result = service.createSaleHistory(saledHistory);
            assertEquals(MAGICCODE.OK,result);
            
        }
        
    }

}
