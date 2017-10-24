package com.model.production;

public class CartProduction
{
    private String id = null;
    private String name = null;
    private String briefIntroduction = null;
    private String ischoosed = null;
    private long cartNum = 0;
    private long productionNum = 0;
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
    public String getIschoosed()
    {
        return ischoosed;
    }
    public void setIschoosed(String ischoosed)
    {
        this.ischoosed = ischoosed;
    }
    public long getCartNum()
    {
        return cartNum;
    }
    public void setCartNum(long cartNum)
    {
        this.cartNum = cartNum;
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
    public long getProductionNum()
    {
        return productionNum;
    }
    public void setProductionNum(long productionNum)
    {
        this.productionNum = productionNum;
    }
    
}
