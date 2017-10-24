package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.shop.Shop;
import com.model.shop.ShopPic;
import com.until.errorcode.MAGICCODE;

public class ShopDBManager
{

    protected DBManager dbManager = null;

    public int createShopPic(ShopPic shopPic)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "INSERT INTO shop_pic (pic,magic_key,pic_type,pic_type_value) VALUES (?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, shopPic.getPic());
            statement.setString(2, shopPic.getMagicKey());
            statement.setString(3, shopPic.getType());
            statement.setString(4, shopPic.getType_value());
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
    
    public int queryShopPic(List<ShopPic> picList,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from shop_pic where magic_key = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
//            System.out.println("QUERY MAGICKEY :" + magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                ShopPic shopPic = dbUntil.queryShopPicFromResultSet(resultSet); 
                picList.add(shopPic);
//                System.out.println("SHOP PIC :"+shopPic.getPic());
                
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
    
    public int createShop(String shopName,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "INSERT INTO ty_shop (name,owner,magic_key,update_time) VALUES (?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, shopName);
            statement.setString(2, userID);
            statement.setString(3, magicKey);
            statement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
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
    
    public int updateShopOwner(String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update ty_shop set owner = ? where magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
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
    
    public Shop queryShopByKey(String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from ty_shop where magic_key = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Shop shop = dbUntil.queryShopFromResultSet(resultSet); 
                return shop;
                
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
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
        return null;
    }
}
