package com.control.store;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.platform.auth.TokenManager;
import com.platform.base.UserCookieManager;
import com.until.errorcode.MAGICCODE;

public class CreateShopPicController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        String token = UserCookieManager.getUserID(request, response);

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

//        if (null == token)
//        {
//            token = request.getParameter("token");
//            if (null == token)
//            {
//                map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
//                json = JSONObject.fromObject(map);
//                stream.write(json.toString().getBytes("UTF-8"));
//                return null;
//            }
//        }
//        
//        TokenManager tokenManager = new TokenManager();
//        String userID = tokenManager.queryUser(token, keyID);
//        if(null == userID)
//        {
//            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
//            json = JSONObject.fromObject(map);
//            stream.write(json.toString().getBytes("UTF-8"));
//            return null;
//        }
        String basePath = "/srv/www/htdocs/ty/shop/";
        basePath += keyID;
        File folder = new File(basePath);
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        String type = request.getParameter("type");
        String typeValue = request.getParameter("value");
        
        
        return null;
    }
}
