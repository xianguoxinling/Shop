package com.model.shop;

import java.util.List;

public class Shop
{
    private String id = null;
    private String name = null;
    private String owner = null;
    private String logo = null;
    private String mainPic = null;
    private List<String> allPic = null;
    private String briefIntroduction = null;
    private String detailedIntroduction = null;
    private String magicKey = null;
    private String platform = null;
    private String createTime = null;
    
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
    public String getOwner()
    {
        return owner;
    }
    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    public String getLogo()
    {
        return logo;
    }
    public void setLogo(String logo)
    {
        this.logo = logo;
    }
    public String getMainPic()
    {
        return mainPic;
    }
    public void setMainPic(String mainPic)
    {
        this.mainPic = mainPic;
    }
    public List<String> getAllPic()
    {
        return allPic;
    }
    public void setAllPic(List<String> allPic)
    {
        this.allPic = allPic;
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
    public String getPlatform()
    {
        return platform;
    }
    public void setPlatform(String platform)
    {
        this.platform = platform;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
}
