package com.model.production;

public class OrderProduction
{
    private String id = null;
    private String name = null;
    private String briefIntroduction = null;
    private long num = 0;
    private String mainPic = null;
    private double price = 0;
    
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
    public long getNum()
    {
        return num;
    }
    public void setNum(long num)
    {
        this.num = num;
    }
    public String getMainPic()
    {
        return mainPic;
    }
    public void setMainPic(String mainPic)
    {
        this.mainPic = mainPic;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
}
