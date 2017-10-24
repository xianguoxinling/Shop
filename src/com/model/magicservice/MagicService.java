package com.model.magicservice;

import java.util.List;

public class MagicService
{
    private String id = null;
    private String name = null;
    private String briefIntroduction = null;
    private String detailedIntroduction = null;
    private String magicKey = null;
    private String mainPic = null;
    private List<String> picList = null;
    private double price = 0;
    private String updateTime = null;
    //初期为名字，后期可以设置为分类的ID
    private String category = null;
    private String serviceProvider = null;
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getBriefIntroduction()
    {
        return briefIntroduction;
    }
    public void setBriefIntroduction(String briefIntroduction)
    {
        this.briefIntroduction = briefIntroduction;
    }
    public String getDetailedIntroduction()
    {
        return detailedIntroduction;
    }
    public void setDetailedIntroduction(String detailedIntroduction)
    {
        this.detailedIntroduction = detailedIntroduction;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    public String getMainPic()
    {
        return mainPic;
    }
    public void setMainPic(String mainPic)
    {
        this.mainPic = mainPic;
    }
    public List<String> getPicList()
    {
        return picList;
    }
    public void setPicList(List<String> picList)
    {
        this.picList = picList;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public String getUpdateTime()
    {
        return updateTime;
    }
    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }
    public String getServiceProvider()
    {
        return serviceProvider;
    }
    public void setServiceProvider(String serviceProvider)
    {
        this.serviceProvider = serviceProvider;
    }
}
