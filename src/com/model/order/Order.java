package com.model.order;
import java.util.List;
import java.util.UUID;

import com.model.address.Address;
import com.model.production.OrderProduction;

public class Order
{
    private String id = null;
    private List<OrderProduction> productionList = null;
    private double totalPrice=0;
    private String customer = null;
    private Address address = null;
//    //优惠券
//    private String coupon = null;
//    //折扣
//    private double discount = 1.0;
    private String state = null;
    private String uuid = null;
    private String delivery = null;
    private String magicKey = null;
    private String updateTime = null;
    
    public Order()
    {
        setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
    }
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public List<OrderProduction> getProductionList()
    {
        return productionList;
    }
    public void setProductionList(List<OrderProduction> productionList)
    {
        this.productionList = productionList;
    }
    public double getTotalPrice()
    {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
    
    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public String getDelivery()
    {
        return delivery;
    }
    public void setDelivery(String delivery)
    {
        this.delivery = delivery;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    public String getCustomer()
    {
        return customer;
    }
    public void setCustomer(String customer)
    {
        this.customer = customer;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
    
}
