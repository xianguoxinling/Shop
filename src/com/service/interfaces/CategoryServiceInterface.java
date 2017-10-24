package com.service.interfaces;

import java.util.List;

import com.model.category.Category;

public interface CategoryServiceInterface
{
    public int createCategory(Category category,String token);
    public int deleteCategory(String categoryID,String token,String magicKey);
    public List<Category> queryCategory(String magicKey);
}
