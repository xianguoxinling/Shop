package com.control.cart;

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
import com.service.cart.CartService;
import com.service.interfaces.CartServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;

public class UpdateProductionCartNumController implements Controller
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

        if (null == token)
        {
            token = request.getParameter("token");
            if (null == token)
            {
                map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
                json = JSONObject.fromObject(map);
                stream.write(json.toString().getBytes("UTF-8"));
                return null;
            }
        }
        
        TokenManager tokenManager = new TokenManager();
        String userID = tokenManager.queryUser(token, keyID);
        if(null == userID)
        {
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        String id = request.getParameter("id");
        if(null == id || "".equals(id))
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        String num = request.getParameter("num");
        if(!UntilNum.isNumber(num))
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        CartServiceInterface cartService = new CartService();
        int result = cartService.updateProudctionNum(Long.parseLong(num), id, userID, keyID);
        if(MAGICCODE.OK != result)
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
        }else
        {
            int number = cartService.queryCartNum(userID, keyID);
            map.put("num",  new Integer(number).toString());
            map.put("code", MAGICCODE.MAGIC_OK);
        }
        
        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
        
    }

}