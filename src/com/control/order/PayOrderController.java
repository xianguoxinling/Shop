package com.control.order;

import java.io.IOException;
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
import com.service.pay.WxPayService;
import com.until.errorcode.MAGICCODE;

public class PayOrderController implements Controller
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

        String orderUUID = request.getParameter("uuid");
        {
            if(null == orderUUID)
            {
                map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
                json = JSONObject.fromObject(map);
                stream.write(json.toString().getBytes("UTF-8"));
                return null;
            }
        }
        
        TokenManager tokenManager = new TokenManager();
        String openID = tokenManager.queryOpenID(token, keyID);
        if (null == openID)
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

        System.out.println("OPENID:"+openID);
        
        String customerIP = getIpAddress(request);
        WxPayService wxPayService = new WxPayService(keyID);
        int result = wxPayService.pay(openID, orderUUID, customerIP);
        if(MAGICCODE.OK != result)
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        String prepayID = wxPayService.getPrepay_id();

        map.put("code", MAGICCODE.MAGIC_OK);
        map.put("prepay_id", prepayID);
        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;

    }

    public String getIpAddress(HttpServletRequest request) throws IOException
    {

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15)
        {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++)
            {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp)))
                {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

}
