package com.control.store;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.shop.ShopPic;
import com.platform.base.UserCookieManager;
import com.service.interfaces.ShopServiceInterface;
import com.service.shop.ShopService;
import com.until.errorcode.MAGICCODE;
import com.until.replace.ReplaceSrvToHttp;

public class QueryStorePicController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        JSONArray storePicJsonList = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
//        System.out.println("!!!!!!!:"+keyID);
        ShopServiceInterface storeService = new ShopService();
        List<ShopPic> shopPicList = storeService.queryProductionPic(keyID);
//        System.out.println("SIZE:"+shopPicList.size());
        Iterator<ShopPic> it = shopPicList.iterator();
        while(it.hasNext())
        {
            ShopPic shopPic = it.next();
            shopPic.setPic(ReplaceSrvToHttp.replace(shopPic.getPic()));
//            System.out.println(shopPic.getPic());
        }
        
        storePicJsonList = JSONArray.fromObject(shopPicList);
        stream.write(storePicJsonList.toString().getBytes("UTF-8"));
        return null;
    }
    
}
