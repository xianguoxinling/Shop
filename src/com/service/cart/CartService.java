package com.service.cart;

import java.util.ArrayList;
import java.util.List;

import com.db.manager.CartDBManager;
import com.model.production.CartProduction;
import com.service.interfaces.CartServiceInterface;
import com.service.interfaces.ProductionServiceInterface;
import com.service.production.ProductionService;
import com.until.errorcode.MAGICCODE;

public class CartService implements CartServiceInterface
{

    private  CartDBManager dbManager = null;
    
    public CartService()
    {
        dbManager = new CartDBManager();
    }
    
    @Override
    public int addToCart(String productionID, String userID, String magicKey)
    {
        int result = MAGICCODE.OK;
        long num = dbManager.queryProductionNumFromCart(userID, productionID, magicKey);
        if(num > 0)
        {
            result = increaseProduction(productionID,userID,magicKey);
        }
        else
        {
            result = dbManager.addToCart(productionID, userID, magicKey);
        }
        
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int deleteFromCart(String productionID, String userID, String magicKey)
    {
        int result = dbManager.deleteFromCart(productionID, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int deleteFromCart(List<String> prodcutionIDList, String userID, String magicKey)
    {
        int result = dbManager.deleteFromCart(prodcutionIDList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int increaseProduction(String productionID, String userID, String magicKey)
    {
        int result = MAGICCODE.OK;
        long num = dbManager.queryProductionNumFromCart(userID, productionID, magicKey);
        if(num < 1)
        {
            return MAGICCODE.ERROR;
        }
        num = num +1;
        
        ProductionServiceInterface productionService = new ProductionService();
        long productionNum = productionService.queryProductionNum(productionID, magicKey);
        if(productionNum >= num)
        {
            result = updateProudctionNum(num,productionID,userID,magicKey);
            if(MAGICCODE.OK != result)
            {
                
            }
            return result;
        }else
        {
            return MAGICCODE.SHOP_PRODUCITON_NOT_ENOUGH;
        }
 
       
    }

    @Override
    public int decreaseProduction(String productionID, String userID, String magicKey)
    {
        long num = dbManager.queryProductionNumFromCart(userID, productionID, magicKey);
        num = num -1;
        if(num < 1)
        {
            return MAGICCODE.ERROR;
        }
        int result = updateProudctionNum(num,productionID,userID,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int updateProudctionNum(long number, String productionID, String userID, String magicKey)
    {
        if(number < 1)
        {
            return MAGICCODE.ERROR;
        }
        
        int result = dbManager.updateCartProductionNum(number, userID, productionID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int chooseProduction(String productionID, String userID, String magicKey)
    {
        int result = dbManager.chooseProoduction(productionID, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int unChooseProduction(String productionID, String userID, String magicKey)
    {
        int result = dbManager.unChooseProoduction(productionID, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public List<CartProduction> queryCart(String userID, String magicKey)
    {
        List<CartProduction> productionList = new ArrayList<CartProduction>();
        int result = dbManager.queryCart(productionList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return productionList;
    }

    @Override
    public int queryCartNum(String userID, String magicKey)
    {
        int result = dbManager.queryCartNum(userID, magicKey);
        if(MAGICCODE.DB_ERROR == result)
        {
            
        }
        return result;
    }

}
