package com.control.store;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.shop.Shop;
import com.service.interfaces.ShopServiceInterface;
import com.service.shop.ShopService;
import com.until.errorcode.MAGICCODE;

public class QueryStroreDetailController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        ShopServiceInterface storeService = new ShopService();
        Shop shop = storeService.queryShopByKey(keyID);
        json = JSONObject.fromObject(shop);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
    }
}
