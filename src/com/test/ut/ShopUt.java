package com.test.ut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.service.shop.ShopService;
import com.until.errorcode.MAGICCODE;
import com.until.random.RandomString;

public class ShopUt
{
    private ShopService shopService = null;
    
    @Before
    public void setUp() throws Exception
    {
        shopService = new ShopService();
    }

    @Test
    public void testCreateShop()
    {
//        String shopName = RandomString.getRandomString(11);
//        String phone = RandomString.getRandomString(11);
//        String password = RandomString.getRandomString(11);
        
      String shopName = "puckart";
      String phone = "15557193876";
      String password = "ccc65483";
        int result = shopService.createShop(shopName, phone,password);
        assertEquals(MAGICCODE.OK,result);
        
    }

}
