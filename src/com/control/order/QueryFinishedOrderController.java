package com.control.order;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.order.Order;
import com.platform.auth.TokenManager;
import com.platform.base.UserCookieManager;
import com.service.interfaces.OrderServiceInterface;
import com.service.order.OrderService;
import com.until.errorcode.MAGICCODE;

public class QueryFinishedOrderController implements Controller
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
        OrderServiceInterface orderService = new OrderService();
        List<Order> orderList = orderService.queryFinishedOrder(userID, keyID);
        JSONArray orderJsonList = null;
        if(null == orderList)
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        orderJsonList = JSONArray.fromObject(orderList);
        stream.write(orderJsonList.toString().getBytes("UTF-8"));
        return null;
    }

}
