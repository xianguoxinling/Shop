package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.model.production.Production;
import com.until.errorcode.MAGICCODE;
import com.until.queryterm.QueryTerm;

public class ProductionDBManager
{
    protected DBManager dbManager = null;

    public int createProduction(Production production)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "INSERT INTO production (name,magic_key,main_pic,category,uuid,price,number,brief_introduction,detailed_introduction,update_time) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, production.getName());
            statement.setString(2, production.getMagicKey());
            statement.setString(3, production.getMainPic());
            statement.setString(4, production.getCategory());
            statement.setString(5, production.getUuid());
            statement.setDouble(6, production.getPrice());
            statement.setLong(7, production.getNumber());
            statement.setString(8, production.getBriefIntroduction());
            statement.setString(9, production.getDetailedIntroduction());
            statement.setTimestamp(10, new java.sql.Timestamp(new java.util.Date().getTime()));
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
    
    public int createProductionMainPic(String uuid,String mainPic,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update production set main_pic = ? where uuid = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mainPic);
            statement.setString(2, uuid);
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
    
    public int createProductionPic(String uuid,List<String> picList,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "INSERT INTO production_pic (production_uuid,path,magic_key,update_time) VALUES (?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            Iterator<String> it = picList.iterator();
            while(it.hasNext())
            {
                String path = it.next();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, uuid);
                statement.setString(2, path);
                statement.setString(3, magicKey);
                statement.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
                statement.executeUpdate();
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
    
    public Production queryProductionByID(String id ,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * from production WHERE id = ? and magic_key = ?";
        PreparedStatement statement;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                return dbUntil.getProductionFromResultSet(resultSet);
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
    
    public long queryProductionNum(String id ,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT number from production WHERE id = ? and magic_key = ?";
        PreparedStatement statement;
        long num = 0;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                num = resultSet.getLong("number");
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
    
    public int queryProductionByCategoryName(String categoryName,List<Production> productionList,QueryTerm queryTerm,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * from production WHERE category = ? and magic_key = ? and state = ? limit ?,?";
        PreparedStatement statement;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            statement.setString(2, magicKey);
            statement.setString(3, "online");
            statement.setInt(4, queryTerm.queryBegin);
            statement.setInt(5, queryTerm.queryNum);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Production production = dbUntil.getProductionFromResultSet(resultSet);
                productionList.add(production);
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
    
    public int queryAllProduction(List<Production> productionList,QueryTerm queryTerm,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * from production WHERE magic_key = ? and state = ? order by update_time limit ?,?";
        PreparedStatement statement;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(2, "online");
            statement.setString(1, magicKey);
            statement.setInt(3, queryTerm.queryBegin);
            statement.setInt(4, queryTerm.queryNum);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Production production = dbUntil.getProductionFromResultSet(resultSet);
                productionList.add(production);
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
    
    public int queryShopAllProduction(List<Production> productionList,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * from production WHERE magic_key = ? order by update_time";
        PreparedStatement statement;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Production production = dbUntil.getProductionFromResultSet(resultSet);
                productionList.add(production);
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
    
    public int queryProductionPic(Production production)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT path from production_pic WHERE production_uuid = ? and magic_key = ?";
        List<String> productionPic = production.getPicList();
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, production.getUuid());
            statement.setString(2, production.getMagicKey());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                productionPic.add(resultSet.getString("path"));
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
    
    public int deleteProduction(String productionID,String magic_key)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "delete from  production where id = ? and magic_key = ?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(productionID));
            statement.setString(2, magic_key);
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
