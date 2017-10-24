package com.model.category;

public class Category
{
    private String id = null;
    private String fatherID = null;
    private String name = null;
    private String introduction = null;
    private String magicKey = null;
    private String pic = null;
    
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
    public String getIntroduction()
    {
        return introduction;
    }
    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    public String getFatherID()
    {
        return fatherID;
    }
    public void setFatherID(String fatherID)
    {
        this.fatherID = fatherID;
    }
    public String getPic()
    {
        return pic;
    }
    public void setPic(String pic)
    {
        this.pic = pic;
    }
}
