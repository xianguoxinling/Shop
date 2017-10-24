package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.model.production.CartProduction;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;

public class CartDBManager
{
    protected DBManager dbManager = null;

    public int addToCart(String productionID,String userID, String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "INSERT INTO cart (production_id,person,magic_key) VALUES (?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(productionID));
            statement.setString(2, userID);
            statement.setString(3, magicKey);
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
    
    
    public int updateCartProductionNum(long num,String userID,String productionID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update cart set num = ? where production_id = ? and person = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, num);
            statement.setLong(2, Long.parseLong(productionID));
            statement.setString(3, userID);
            statement.setString(4, magicKey);
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
    
    public long queryProductionNumFromCart(String userID,String productionID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select num from cart where production_id = ? and person = ? and magic_key = ?";
        long num = 0;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(productionID));
            statement.setString(2, userID);
            statement.setString(3, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                num = resultSet.getLong("num");
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
        return num;
    }
    
    public int chooseProoduction(String productionID,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update cart set ischoosed = ? where production_id = ? and person = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "choosed");
            statement.setLong(2, Long.parseLong(productionID));
            statement.setString(3, userID);
            statement.setString(4, magicKey);
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
    
    public int unChooseProoduction(String productionID,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update cart set ischoosed = ? where production_id = ? and person = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "unchoosed");
            statement.setLong(2, Long.parseLong(productionID));
            statement.setString(3, userID);
            statement.setString(4, magicKey);
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
    
    
    public int deleteFromCart(String productionID,String userID, String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "delete from cart where production_id = ? and person = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(productionID));
            statement.setString(2, userID);
            statement.setString(3, magicKey);
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
    
    public int deleteFromCart(List<String> productionIDList,String userID, String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "delete from cart where production_id = ? and person = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            Iterator<String> it = productionIDList.iterator();
            {
                String productionID = it.next();
                if(UntilNum.isLong(productionID))
                {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setLong(1, Long.parseLong(productionID));
                    statement.setString(2, userID);
                    statement.setString(3, magicKey);
                    statement.executeUpdate();
                }
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
    
    public int queryCart(List<CartProduction> productionList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT a.id,a.name,a.main_pic,a.price,a.number,a.brief_introduction,b.num,b.ischoosed from production a left join cart b on a.id = b.production_id  WHERE b.person = ? and b.magic_key = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                CartProduction cartProduction = new CartProduction();
                cartProduction.setId(resultSet.getString("a.id"));
                cartProduction.setName(resultSet.getString("a.name"));
                cartProduction.setBriefIntroduction(resultSet.getString("a.brief_introduction"));
                long num = resultSet.getLong("b.num");
                cartProduction.setCartNum(num);
                long productionNum = resultSet.getLong("a.number");
                cartProduction.setProductionNum(productionNum);
                cartProduction.setIschoosed(resultSet.getString("b.ischoosed"));
                cartProduction.setMainPic(resultSet.getString("a.main_pic"));
                cartProduction.setPrice(resultSet.getDouble("a.price"));
                
                productionList.add(cartProduction);
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
    
    public int queryCartNum(String userID, String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select count(id) a from cart where person = ? and magic_key = ?";
        int num = 0;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                num = resultSet.getInt("a");
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
        return num;
    }
}
