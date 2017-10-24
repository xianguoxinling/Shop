package com.control.category;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.model.category.Category;
import com.platform.base.UserCookieManager;
import com.service.category.CategoryService;
import com.service.interfaces.CategoryServiceInterface;
import com.until.errorcode.MAGICCODE;

public class CreateCategoryController implements Controller
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
        String name = request.getParameter("name");
        if (null == name || "".equals(name))
        {
            map.put("code", MAGICCODE.MAGIC_SHOP_CATAGORY_NAME_NOT_EXIST);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;

        }
        String introduction = request.getParameter("introduction");
        Category category = new Category();
        category.setName(name);
        category.setIntroduction(introduction);
        category.setMagicKey(keyID);
        
        CategoryServiceInterface categorySevice = new CategoryService();
        int result = categorySevice.createCategory(category, token);
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
