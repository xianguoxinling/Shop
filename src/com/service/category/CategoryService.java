package com.service.category;

import java.util.ArrayList;
import java.util.List;

import com.db.manager.CategoryDBManaer;
import com.model.category.Category;
import com.service.interfaces.CategoryServiceInterface;
import com.until.errorcode.MAGICCODE;

public class CategoryService implements CategoryServiceInterface
{
    private CategoryDBManaer dbManager = null;
    
    public CategoryService()
    {
        dbManager = new CategoryDBManaer();
    }
    
    @Override
    public int createCategory(Category category, String token)
    {
        int result = MAGICCODE.OK;
        //鉴权
        
        result = dbManager.checkCategoryNameExist(category.getName(), category.getMagicKey());
        if(MAGICCODE.SHOP_CATEGORY_NAME_NOT_EXIST != result)
        {
            return result;
        }
        
        result = dbManager.createCategory(category);
        return result;
    }

    @Override
    public int deleteCategory(String categoryID, String token,String magicKey)
    {
        int result = MAGICCODE.OK;
        //鉴权
        
        result = dbManager.delCategory(categoryID,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public List<Category> queryCategory(String magicKey)
    {
        List<Category> categoryList = new ArrayList<Category>();
        int result = dbManager.queryCategory(categoryList, magicKey);
        
        if(MAGICCODE.OK != result)
        {
            
        }
        return categoryList;
    }

}
