package com.control.production;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.production.Production;
import com.service.interfaces.ProductionServiceInterface;
import com.service.production.ProductionService;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;
import com.until.replace.ReplaceSrvToHttp;

public class QueryProductionByIDController implements Controller
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
        }

        String producitonID = request.getParameter("producitonID");
        if(!UntilNum.isNumber(producitonID))
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        ProductionServiceInterface service = new ProductionService();
        Production production = service.queryProductionByID(producitonID, keyID);
        if(null == production)
        {
            map.put("code", MAGICCODE.MAGIC_PRODUCTION_NOT_FOUND);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        List<String> picList = new ArrayList<String>();
        List<String> oldList = production.getPicList();
        Iterator<String> it = oldList.iterator();
        while(it.hasNext())
        {
            String pic = it.next();
            picList.add(ReplaceSrvToHttp.replace(pic));
        }
        production.setPicList(picList);
        production.setMainPic(ReplaceSrvToHttp.replace(production.getMainPic()));
        json = JSONObject.fromObject(production);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
    }

}
