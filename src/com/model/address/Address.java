package com.model.address;

public class Address
{
    private String id = null;
    private String city = null;
    private String address = null;
    private String contactName = null;
    private String mobile = null;
    private String userID = null;
    private String updateTime = null;
    private String isCommonAddress = null;
    private String magicKey = null;
    private String state = "OK";
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public String getUpdateTime()
    {
        return updateTime;
    }
    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
    public String getIsCommonAddress()
    {
        return isCommonAddress;
    }
    public void setIsCommonAddress(String isCommonAddress)
    {
        this.isCommonAddress = isCommonAddress;
    }
    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public String getContactName()
    {
        return contactName;
    }
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
}
