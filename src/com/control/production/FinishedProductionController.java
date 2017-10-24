package com.control.production;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.platform.base.UserCookieManager;

public class FinishedProductionController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        String token = UserCookieManager.getUserID(request, response);

        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
//        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
//            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
//            json = JSONObject.fromObject(map);
//            stream.write(json.toString().getBytes("UTF-8"));
//            return null;
        }
        
        return new ModelAndView("/store/addproduct.jsp");
    }

}
