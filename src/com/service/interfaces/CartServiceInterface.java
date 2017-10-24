package com.service.interfaces;

import java.util.List;

import com.model.production.CartProduction;

public interface CartServiceInterface
{
    public int addToCart(String productionID,String userID,String magicKey);
    public int deleteFromCart(String productionID,String userID,String magicKey);
    public int deleteFromCart(List<String> prodcutionIDList,String userID,String magicKey);
    public int increaseProduction(String productionID,String userID,String magicKey);
    public int decreaseProduction(String productionID,String userID,String magicKey);
    public int updateProudctionNum(long number,String productionID,String userID,String magicKey);
    public int chooseProduction(String productionID,String userID,String magicKey);
    public int unChooseProduction(String productionID,String userID,String magicKey);
    public List<CartProduction> queryCart(String userID,String magickey);
    public int queryCartNum(String userID,String magicKey);
    
}
