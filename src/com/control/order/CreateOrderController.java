package com.control.order;

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
import com.service.interfaces.OrderServiceInterface;
import com.service.order.OrderService;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;

public class CreateOrderController implements Controller
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
        
        String addressStr = request.getParameter("address");
        if(!UntilNum.isNumber(addressStr))   
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        OrderServiceInterface orderService = new OrderService();
        int result = orderService.createOrderByCart(userID, addressStr, keyID);
        if(MAGICCODE.OK == result)
        {
            String uuid = orderService.getUUID();
            map.put("uuid", uuid);
            map.put("code", MAGICCODE.MAGIC_OK);
        }else if(MAGICCODE.ADDRESS_NULL == result)
        {
            map.put("code", MAGICCODE.MAGIC_ADDRESS_NULL);
        }else if(MAGICCODE.SHOP_PRODUCTION_NULL == result)
        {
            map.put("code", MAGICCODE.MAGIC_PRODUCTION_NOT_FOUND);
        }else if(MAGICCODE.SHOP_PRODUCITON_NOT_ENOUGH == result)
        {
            map.put("code", MAGICCODE.MAGIC_SHOP_PRODUCITON_NOT_ENOUGH);
        }else if(MAGICCODE.SHOP_PRODUCTION_NUM_ERROR == result)
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
        }
        
        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
    }
}
