package com.model.production;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Production
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
    private String uuid = null;
    private long number = 0;
    
    public Production()
    {
        picList = new ArrayList<String>();
        uuid = UUID.randomUUID().toString();
    }
    
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
    public long getNumber()
    {
        return number;
    }
    public void setNumber(long number)
    {
        this.number = number;
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

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}
