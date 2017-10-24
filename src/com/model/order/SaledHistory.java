package com.model.order;

public class SaledHistory
{
    private String id = null;
    private String orderID = null;
    private double realPrice = 0;
    private String magicKey = null;
    private String saledTime = null;
    private String state = null;
    private String userID = null;
    private String productionID = null;
    private String productionName = null;
    private String productionBriftIntroduce = null;
    private String productionPic = null;
    private long num = 0;
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public double getRealPrice()
    {
        return realPrice;
    }
    public void setRealPrice(double realPrice)
    {
        this.realPrice = realPrice;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    public String getSaledTime()
    {
        return saledTime;
    }
    public void setSaledTime(String saledTime)
    {
        this.saledTime = saledTime;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public long getNum()
    {
        return num;
    }
    public void setNum(long num)
    {
        this.num = num;
    }
    public String getProductionID()
    {
        return productionID;
    }
    public void setProductionID(String productionID)
    {
        this.productionID = productionID;
    }
    public String getProductionName()
    {
        return productionName;
    }
    public void setProductionName(String productionName)
    {
        this.productionName = productionName;
    }
    public String getProductionBriftIntroduce()
    {
        return productionBriftIntroduce;
    }
    public void setProductionBriftIntroduce(String productionBriftIntroduce)
    {
        this.productionBriftIntroduce = productionBriftIntroduce;
    }
    public String getProductionPic()
    {
        return productionPic;
    }
    public void setProductionPic(String productionPic)
    {
        this.productionPic = productionPic;
    }
    public String getOrderID()
    {
        return orderID;
    }
    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }
    
}
