package com.service.shop;

import java.util.ArrayList;
import java.util.List;

import com.db.manager.ShopDBManager;
import com.manager.store.StoreRegisterManager;
import com.model.shop.Shop;
import com.model.shop.ShopPic;
import com.platform.auth.TokenManager;
import com.service.interfaces.ShopServiceInterface;
import com.until.errorcode.MAGICCODE;

public class ShopService implements ShopServiceInterface
{
    private ShopDBManager dbManager = null;
    
    public ShopService()
    {
        dbManager = new ShopDBManager();
    }
    
    @Override
    public List<ShopPic> queryProductionPic(String magicKey)
    {
        List<ShopPic> shopPicList = new ArrayList<ShopPic>();
        int result = dbManager.queryShopPic(shopPicList, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return shopPicList;
    }

    @Override
    public int createShopPic(ShopPic shopPic)
    {
        int result = MAGICCODE.OK;
        result = dbManager.createShopPic(shopPic);
        if(MAGICCODE.OK == result)
        {
            
        }
        
        return result;
    }
    
    public int createShop(String shopName,String phone,String password)
    {
        int result = MAGICCODE.OK;
        StoreRegisterManager storeManager = new StoreRegisterManager();
        result = storeManager.storeRegister(phone, password);
        if(MAGICCODE.OK != result)
        {
            System.out.println("STORE REGISTER ERROR!");
            return result;
        }
        System.out.println("STORE REGISTER SUCCESS!");
        String code = storeManager.getCode();
        System.out.println("code:"+code);
        if(MAGICCODE.MAGIC_OK.equals(code))
        {
            String token = storeManager.getToken();
            String magicKey = storeManager.getMagicKey();
            
            TokenManager tokenManager = new TokenManager();
            tokenManager.setServer("122.4.241.3");
            String userID = tokenManager.queryUser(token, magicKey);
            System.out.println("userID:"+userID);
            result = dbManager.createShop(shopName,userID, magicKey);
            if(MAGICCODE.OK == result)
            {
                
            }
            return result;
        }
        
        return MAGICCODE.ERROR;
    }
    
    public int updateShopOwner(String userID,String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateShopOwner(userID, magicKey);
        if(MAGICCODE.OK == result)
        {
            
        }
        return result;
    }
    
    public Shop queryShopByKey(String magicKey)
    {
        Shop shop = dbManager.queryShopByKey(magicKey);
        if(null == shop)
        {
            
        }
        return shop;
    }

}
