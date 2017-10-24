package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.category.Category;
import com.until.errorcode.MAGICCODE;

public class CategoryDBManaer
{
    protected DBManager dbManager = null;

    public int createCategory(Category category)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "INSERT INTO category (name,introduction,magic_key) VALUES (?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setString(2, category.getIntroduction());
            statement.setString(3, category.getMagicKey());
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }

    public int queryCategory(List<Category> categoryList,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql1 = "SELECT * from category WHERE magic_key = ?";
        PreparedStatement statement;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql1);
            statement.setString(1, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                    Category category = dbUntil.getCategoryFromResultSet(resultSet);
                    categoryList.add(category);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
         return MAGICCODE.OK;
    }
 
    public int checkCategoryNameExist(String name,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql1 = "SELECT id from category WHERE name=? and magic_key = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql1);
            statement.setString(1, name);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                return MAGICCODE.SHOP_CATEGORY_NAME_EXIST;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
         return MAGICCODE.SHOP_CATEGORY_NAME_NOT_EXIST;
    }
    
    public int delCategory(String categoryID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "delete from category where id = ? and magic_key = ?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(categoryID));
            statement.setString(2, magicKey);
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
}
