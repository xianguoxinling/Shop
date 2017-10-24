package com.control.production;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.production.Production;
import com.platform.base.UserCookieManager;
import com.service.interfaces.ProductionServiceInterface;
import com.service.production.ProductionService;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;

public class CreateProductionController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        String token = UserCookieManager.getUserID(request, response);

        Map<String, String> map = new HashMap<String, String>();
//        JSONObject json = null;
//        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
//            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
//            json = JSONObject.fromObject(map);
//            stream.write(json.toString().getBytes("UTF-8"));
//            return null;
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
        
        String name = request.getParameter("name");
        if(null == name || "".equals(name))
        {
//            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
//            json = JSONObject.fromObject(map);
//            stream.write(json.toString().getBytes("UTF-8"));
//            return null;
        }
        
        String priceStr = request.getParameter("price");
        String numStr = request.getParameter("num");
        if((!UntilNum.isNumber(priceStr)) || (!UntilNum.isNumber(numStr)))
        {
//            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
//            json = JSONObject.fromObject(map);
//            stream.write(json.toString().getBytes("UTF-8"));
//            return null;
        }
        
        long num = Long.parseLong(numStr);
        double price = Double.parseDouble(priceStr);
        String category = request.getParameter("category");
        String briefIntroduction = request.getParameter("brief_introduction");
        String detailedIntroduction = request.getParameter("detailed_introduction");
        
        Production production = new Production();
        production.setBriefIntroduction(briefIntroduction);
        production.setCategory(category);
        production.setDetailedIntroduction(detailedIntroduction);
        production.setMagicKey(keyID);
        production.setName(name);
        production.setNumber(num);
        production.setPrice(price);
//        production.setMainPic("http://www.puckart.com/images/puck1.png");
        
        ProductionServiceInterface service = new ProductionService();
        int result = service.createProduction(production, token);
        if(MAGICCODE.OK == result)
        {
//            map.put("code", MAGICCODE.MAGIC_OK);
        }else
        {
//            map.put("code", MAGICCODE.MAGIC_ERROR);
        }
        
//        json = JSONObject.fromObject(map);
//        stream.write(json.toString().getBytes("UTF-8"));
//        return null;
        return new ModelAndView("/store/uploadpic.jsp", "p_uuid", production.getUuid());
    }
}
