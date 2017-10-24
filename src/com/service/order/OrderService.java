package com.service.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.db.manager.OrderDBManager;
import com.model.address.Address;
import com.model.order.Order;
import com.model.order.OrderState;
import com.model.order.SaledHistory;
import com.model.production.OrderProduction;
import com.model.production.Production;
import com.service.address.AddressService;
import com.service.interfaces.AddressServiceInterface;
import com.service.interfaces.OrderServiceInterface;
import com.service.interfaces.ProductionServiceInterface;
import com.service.interfaces.SaleServiceInterface;
import com.service.production.ProductionService;
import com.service.sale.SaleService;
import com.until.errorcode.MAGICCODE;

public class OrderService implements OrderServiceInterface
{
    protected OrderDBManager dbManager = null;
    protected AddressServiceInterface addressService = null;
    ProductionServiceInterface productionService = null;
    protected String uuid = null;
    
    public OrderService()
    {
        dbManager = new OrderDBManager();
        addressService =  new AddressService();
        productionService = new ProductionService();
    }
    
    @Override
    public int createOrderByCart(String userID, String addressID, String magicKey)
    {
        Address address = addressService.queryAddressByID(addressID, magicKey);
        if(null == address)
        {
            return MAGICCODE.ADDRESS_NULL;
        }
        
        List<OrderProduction> productionList = new ArrayList<OrderProduction>();
        int result = dbManager.queryOrderProductionFromCart(productionList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        
        double totalPrice = 0;
        Iterator<OrderProduction> it = productionList.iterator();
        while(it.hasNext())
        {
            OrderProduction production = it.next();
            long productionNum = production.getNum();
            double productionPrice = production.getPrice();
            double productionTotalPrice = productionNum * productionPrice;
            totalPrice += productionTotalPrice;
            production.setPrice(productionTotalPrice);
            System.out.println("******:"+productionTotalPrice);
        }
        
        Order order = new Order();
        order.setMagicKey(magicKey);
        order.setProductionList(productionList);
        order.setAddress(address);
        order.setCustomer(userID);
        order.setTotalPrice(totalPrice);
        uuid = order.getUuid();
        
        result = dbManager.createOrderInfo(order);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        
        result = dbManager.createOrderProduction(uuid, productionList, magicKey);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        
        result = delChosenProduction(userID,magicKey);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        return result;
    }
    
    public int delChosenProduction(String userID,String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.delChosenProduction(userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        return result;
    }
    
    public int queryOrderProduction(List<Order> orderList,String magicKey)
    {
        int result = MAGICCODE.OK;
        Iterator<Order> it = orderList.iterator();
        while(it.hasNext())
        {
            int queryNum = 0;
            Order order = it.next();
            List<OrderProduction> productionList = new ArrayList<OrderProduction>();
            result = dbManager.queryOrderProduction(productionList, order.getUuid(), magicKey);
            
            while(MAGICCODE.OK != result)
            {
                productionList = new ArrayList<OrderProduction>();
                result = dbManager.queryOrderProduction(productionList, order.getUuid(), magicKey);
                queryNum ++;
                if(queryNum>=3)
                {
                    break;
                }
            }
            order.setProductionList(productionList);
        }
        
        return result;
    }
    
    @Override
    public List<Order> queryAllOrder(String userID, String magicKey)
    {
        List<Order> orderList = new ArrayList<Order>();
        int result = dbManager.queryAllOrderInfo(orderList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            return null;
        }
        result = queryOrderProduction(orderList,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return orderList;
    }

    @Override
    public List<Order> queryFinishedOrder(String userID, String magicKey)
    {
        List<Order> orderList = new ArrayList<Order>();
        int result = dbManager.queryFinishedOrderInfo(orderList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            return null;
        }
        result = queryOrderProduction(orderList,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return orderList;
    }

    @Override
    public List<Order> queryCancledOrder(String userID, String magicKey)
    {
        List<Order> orderList = new ArrayList<Order>();
        int result = dbManager.queryCancledOrderInfo(orderList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            return null;
        }
        result = queryOrderProduction(orderList,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return orderList;
    }

    @Override
    public List<Order> queryPrePayOrder(String userID, String magicKey)
    {
        List<Order> orderList = new ArrayList<Order>();
        int result = dbManager.queryUnPayOrderInfo(orderList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            return null;
        }
        result = queryOrderProduction(orderList,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return orderList;
    }
    @Override
    public List<Order> queryPayedOrder(String userID,String magicKey)
    {
        List<Order> orderList = new ArrayList<Order>();
        int result = dbManager.queryPayedOrderInfo(orderList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            return null;
        }
        result = queryOrderProduction(orderList,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return orderList;
    }
    
    @Override
    public int payOrder(String orderID, String userID, String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateOrderState(orderID, userID, OrderState.PAYED, magicKey);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        
        Order order = queryOrderByID(orderID,magicKey);
        if(null == order)
        {
            return MAGICCODE.ERROR;
        }
        SaleServiceInterface saleService = new SaleService();
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
            result = saleService.createSaleHistory(saledHistory);
            if(MAGICCODE.OK != result)
            {
                
            }
            
        }
        
        return result;
    }

    @Override
    public int cancledOrder(String orderID, String userID, String magicKey)
    {
//        System.out.println("orderID:"+orderID+" userID:"+userID+" magicKey:"+magicKey);

        String state = dbManager.queryOrderState(orderID, userID, magicKey);
        if(null == state)
        {
            return MAGICCODE.ERROR;
        }
        if(!OrderState.PR_PAY.equals(state))
        {
            return MAGICCODE.SHOP_ORDER_CANOT_BE_CANCLE;
        }
        
        int result = updateOrderState(orderID,userID,OrderState.CANCLE,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    public int updateOrderState(String orderID,String userID,String state,String magickey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateOrderState(orderID, userID, state, magickey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }
    @Override
    public int createOrderImmediately(String userID, String productionID, long num, String addressID, String magicKey)
    {
        int result = MAGICCODE.OK;
        
        Address address = addressService.queryAddressByID(addressID, magicKey);
        if(null == address)
        {
            return MAGICCODE.ADDRESS_NULL;
        }
        
        Production production = productionService.queryProductionByID(productionID, magicKey);
        if(null == production)
        {
            return MAGICCODE.SHOP_PRODUCTION_NULL;
        }
        
        if(num > production.getNumber())
        {
            return MAGICCODE.SHOP_PRODUCITON_NOT_ENOUGH;
        }
        
        if(num < 1)
        {
            return MAGICCODE.SHOP_PRODUCTION_NUM_ERROR;
        }
        
        double realPrice = num * production.getPrice();
        
        Order order = new Order();
        order.setMagicKey(magicKey);
        order.setAddress(address);
        order.setCustomer(userID);
        order.setTotalPrice(realPrice);
        uuid = order.getUuid();
        
        result = dbManager.createOrderInfo(order);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        
        result = dbManager.createOrderProduction(uuid, productionID, num, production.getPrice(),magicKey);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        return 0;
    }
    
    public String getUUID()
    {
        return uuid;
    }

    @Override
    public Order queryOrderByID(String ID, String magicKey)
    {
        Order order = dbManager.queryOrderInfoByID(ID, magicKey);
        if(null != order)
        {
            Address address = addressService.queryAddressByID(order.getAddress().getId(), magicKey);
            order.setAddress(address);
            
            List<OrderProduction> productionList = new ArrayList<OrderProduction>();
            int result = dbManager.queryOrderProduction(productionList, order.getUuid(), magicKey);
            int queryNum = 0;
            while(MAGICCODE.OK != result)
            {
                productionList = new ArrayList<OrderProduction>();
                result = dbManager.queryOrderProduction(productionList, order.getUuid(), magicKey);
                queryNum ++;
                if(queryNum>=3)
                {
                    break;
                }
            }
            order.setProductionList(productionList);
        }
        return order;
    }

    @Override
    public Order queryOrderByUUID(String uuid, String magicKey)
    {
        Order order = dbManager.queryOrderInfoByUUID(uuid, magicKey);
        if(null != order)
        {
            Address address = addressService.queryAddressByID(order.getAddress().getId(), magicKey);
            order.setAddress(address);
            
            List<OrderProduction> productionList = new ArrayList<OrderProduction>();
            int result = dbManager.queryOrderProduction(productionList, order.getUuid(), magicKey);
            int queryNum = 0;
            while(MAGICCODE.OK != result)
            {
                productionList = new ArrayList<OrderProduction>();
                result = dbManager.queryOrderProduction(productionList, order.getUuid(), magicKey);
                queryNum ++;
                if(queryNum>=3)
                {
                    break;
                }
            }
            order.setProductionList(productionList);
        }
        return order;
    }
    
    public Order queryOrderInfoByUUID(String UUID)
    {
        Order order = dbManager.queryOrderInfoByUUID(UUID);
        return order;
    }

}
