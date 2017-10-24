package com.control.production;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.production.Production;
import com.service.interfaces.ProductionServiceInterface;
import com.service.production.ProductionService;
import com.until.errorcode.MAGICCODE;
import com.until.queryterm.QueryTerm;
import com.until.replace.ReplaceSrvToHttp;

public class QueryAllProductionController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        JSONArray productionJsonList = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }
        String begin = request.getParameter("begin");
        String num = request.getParameter("num");

        QueryTerm queryTerm = new QueryTerm();
        if (null != begin)
        {
            queryTerm.queryNum = Integer.parseInt(num);
            queryTerm.queryBegin = (Integer.parseInt(begin) - 1) * queryTerm.queryNum;
        }
        
        ProductionServiceInterface service = new ProductionService();
        List<Production> productionList = service.queryAllProduction(keyID, queryTerm);
        if(null == productionList)
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        Iterator<Production> it = productionList.iterator();
        while(it.hasNext())
        {
            Production production = it.next();
            String mainPic = production.getMainPic();
            if(null != mainPic)
            {
                String[] picFile = mainPic.split("/");
                StringBuffer newPic = new StringBuffer();
                for(int i =0;i<(picFile.length -1);i++)
                {
                    newPic.append(picFile[i]);
                    newPic.append("/");
                }
                newPic.append("midcompress/");
                newPic.append(picFile[picFile.length -1]);
                production.setMainPic(ReplaceSrvToHttp.replace(newPic.toString()));
            }
        }
        
        productionJsonList = JSONArray.fromObject(productionList);
        stream.write(productionJsonList.toString().getBytes("UTF-8"));
        return null;
    }

}
