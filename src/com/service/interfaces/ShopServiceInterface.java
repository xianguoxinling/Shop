package com.service.interfaces;
import java.util.List;

import com.model.shop.Shop;
import com.model.shop.ShopPic;

public interface ShopServiceInterface
{
    public int createShop(String shopName,String phone,String password);
    public int updateShopOwner(String userID,String magicKey);
    public List<ShopPic> queryProductionPic(String magicKey);
    public int createShopPic(ShopPic shopPic);
    public Shop queryShopByKey(String magicKey);
}
