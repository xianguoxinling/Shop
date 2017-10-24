package com.model.cart;
import java.util.List;

import com.model.production.Production;

public class Cart
{
    private String id ;
    private String userID;
    private List<Production> productionList;
    private String magicKey;
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public List<Production> getProductionList()
    {
        return productionList;
    }
    public void setProductionList(List<Production> productionList)
    {
        this.productionList = productionList;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    
    
}
