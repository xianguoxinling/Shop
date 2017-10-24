package com.service.interfaces;

import java.util.List;

import com.model.order.Order;

public interface OrderServiceInterface
{
    public int createOrderByCart(String userID,String addressID,String magicKey);
    public int createOrderImmediately(String userID,String productionID,long num,String addressID,String magicKey);
    public List<Order> queryAllOrder(String userID,String magicKey);
    public List<Order> queryFinishedOrder(String userID,String magicKey);
    public List<Order> queryCancledOrder(String userID,String magicKey);
    public List<Order> queryPrePayOrder(String userID,String magicKey);
    public List<Order> queryPayedOrder(String userID,String magicKey);
    public int payOrder(String orderID,String userID,String magicKey);
    public int cancledOrder(String orderID,String userID,String magicKey);
    public String getUUID();
    public Order queryOrderByID(String ID,String magicKey);
    public Order queryOrderByUUID(String uuid,String magicKey);
    public Order queryOrderInfoByUUID(String UUID);
}
