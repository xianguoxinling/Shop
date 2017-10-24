package com.control.zhaoshang;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.address.Address;
import com.model.zhaoshang.Zhaoshang;
import com.platform.auth.TokenManager;
import com.platform.base.UserCookieManager;
import com.service.address.AddressService;
import com.service.interfaces.AddressServiceInterface;
import com.service.zhaoshang.ZhaoshangService;
import com.until.errorcode.MAGICCODE;

public class CreateZhaoShangControler implements Controller
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
        
        String city = request.getParameter("city");
        String addressStr = request.getParameter("address");
        String contactName = request.getParameter("contack_name");
        String mobile = request.getParameter("mobile");
        
        Zhaoshang address = new Zhaoshang();
        address.setCity(city);
        address.setAddress(addressStr);
        address.setContactName(contactName);
        address.setMobile(mobile);
        address.setUserID(userID);
        address.setMagicKey(keyID);
        address.setState("招商");
        
        ZhaoshangService service = new ZhaoshangService();
        int result = service.create(address);
        if(MAGICCODE.OK == result)
        {
            map.put("code", MAGICCODE.MAGIC_OK);
        }
        else
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
        }
        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
        
    }
}
