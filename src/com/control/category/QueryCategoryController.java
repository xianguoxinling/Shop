package com.control.category;

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

import com.model.category.Category;
import com.service.category.CategoryService;
import com.service.interfaces.CategoryServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.replace.ReplaceSrvToHttp;

public class QueryCategoryController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        JSONObject json = null;
        JSONArray categoryJsonList = null;
        Map<String, String> map = new HashMap<String, String>();
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
        }

        
        CategoryServiceInterface categorySevice = new CategoryService();
        List<Category> categoryList = categorySevice.queryCategory(keyID);
        if(null == categoryList)
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }else
        {
            Iterator<Category> it = categoryList.iterator();
            while(it.hasNext())
            {
                Category category = it.next();
                if(null != category)
                {
                    category.setPic(ReplaceSrvToHttp.replace(category.getPic()));
                }
            }
        }
        categoryJsonList = JSONArray.fromObject(categoryList);
        stream.write(categoryJsonList.toString().getBytes("UTF-8"));
        return null;
    }

}
