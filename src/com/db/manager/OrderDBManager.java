package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.model.order.Order;
import com.model.order.OrderState;
import com.model.order.WxPayInfo;
import com.model.production.OrderProduction;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;
import com.until.replace.ReplaceSrvToHttp;

public class OrderDBManager
{
    protected DBManager dbManager = null;

    public int queryOrderProductionFromCart(List<OrderProduction> productionList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT a.id,a.name,a.main_pic,a.price,a.brief_introduction,b.num,b.ischoosed from production a left join cart b on a.id = b.production_id  WHERE b.person = ? and b.ischoosed = ? and b.magic_key = ?";
        PreparedStatement statement;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, "choosed");
            statement.setString(3, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                OrderProduction orderProduction = new OrderProduction();
                orderProduction.setId(resultSet.getString("a.id"));
                orderProduction.setName(resultSet.getString("a.name"));
                orderProduction.setBriefIntroduction(resultSet.getString("a.brief_introduction"));
                long num = resultSet.getLong("b.num");
                orderProduction.setNum(num);
                orderProduction.setMainPic(resultSet.getString("a.main_pic"));
                orderProduction.setPrice(resultSet.getDouble("a.price"));
                productionList.add(orderProduction);
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
    
    public int delChosenProduction(String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "delete from cart where ischoosed = ? and person = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "choosed");
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
    
    public int createOrderInfo(Order order)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO ty_order(price,customer,address,uuid,magic_key,update_time) VALUES (?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, order.getTotalPrice());
            statement.setString(2, order.getCustomer());
            statement.setString(3, order.getAddress().getId());
            statement.setString(4, order.getUuid());
            statement.setString(5, order.getMagicKey());
            statement.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
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
    
    public int createOrderProduction(String orderUuid,String productionID,long num,double price,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO order_production(production_id,order_uuid,number,price,magic_key,update_time) VALUES (?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(productionID));
            statement.setString(2, orderUuid);
            statement.setLong(3, num);
            statement.setDouble(4, price);
            statement.setString(5, magicKey);
            statement.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
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
    
    public int createOrderProduction(String orderUuid,List<OrderProduction> productionList,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO order_production(production_id,order_uuid,number,price,magic_key) VALUES (?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(false);
            Iterator<OrderProduction> it = productionList.iterator();
            while(it.hasNext())
            {
                OrderProduction production = it.next();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, Long.parseLong(production.getId()));
                statement.setString(2, orderUuid);
                statement.setLong(3, production.getNum());
                statement.setDouble(4, production.getPrice());
                statement.setString(5, magicKey);
                statement.executeUpdate();
            }
            
            connection.commit();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return RollBackManager.dealRollback(connection);
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
    
    public Order queryOrderInfoByID(String orderID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from ty_order where id = ? and magic_key = ?";
        Order order = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, orderID);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                order = dbUntil.queryOrderFromResultSet(resultSet);
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
        return order;
    }
    
    public Order queryOrderInfoByUUID(String orderUUID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from ty_order where uuid = ? and magic_key = ?";
        Order order = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, orderUUID);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                order = dbUntil.queryOrderFromResultSet(resultSet);
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
        return order;
    }
    
    public Order queryOrderInfoByUUID(String orderUUID)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from ty_order where uuid = ?";
        Order order = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, orderUUID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                order = dbUntil.queryOrderFromResultSet(resultSet);
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
        return order;
    }
    
    public int queryAllOrderInfo(List<Order> orderList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from ty_order where customer = ? and magic_key = ?";
        DBUntil dbUntil = new DBUntil();
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
                Order order = dbUntil.queryOrderFromResultSet(resultSet);
                orderList.add(order);
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
    
    public int queryUnPayOrderInfo(List<Order> orderList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null; 
        String sql = "select * from ty_order where customer = ? and magic_key = ? and state = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            statement.setString(3,OrderState.PR_PAY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Order order = dbUntil.queryOrderFromResultSet(resultSet);
                orderList.add(order);
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
    
    public int queryCancledOrderInfo(List<Order> orderList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null; 
        String sql = "select * from ty_order where customer = ? and magic_key = ? and state = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            statement.setString(3,OrderState.CANCLE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Order order = dbUntil.queryOrderFromResultSet(resultSet);
                orderList.add(order);
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
    
    public int queryPayedOrderInfo(List<Order> orderList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null; 
        String sql = "select * from ty_order where customer = ? and magic_key = ? and state = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            statement.setString(3,OrderState.PAYED);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Order order = dbUntil.queryOrderFromResultSet(resultSet);
                orderList.add(order);
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
    
    public int queryFinishedOrderInfo(List<Order> orderList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null; 
        String sql = "select * from ty_order where customer = ? and magic_key = ? and state = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            statement.setString(3,OrderState.FINISHED);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Order order = dbUntil.queryOrderFromResultSet(resultSet);
                orderList.add(order);
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
    
    public int queryOrderProduction(List<OrderProduction> productionList,String order_uuid,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null; 
        String sql = "select a.production_id,a.number,a.price,b.main_pic,b.brief_introduction,b.name from order_production a left join production b on a.production_id = b.id where a.order_uuid = ? and a.magic_key = ?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, order_uuid);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                OrderProduction production = new OrderProduction();
                production.setId(resultSet.getString("a.production_id"));
                production.setNum(resultSet.getLong("a.number"));
                production.setPrice(resultSet.getDouble("a.price"));
                String mainPic = resultSet.getString("b.main_pic");
                mainPic = ReplaceSrvToHttp.replace(mainPic);
                production.setMainPic(mainPic);
                production.setBriefIntroduction(resultSet.getString("b.brief_introduction"));
                production.setName(resultSet.getString("b.name"));
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
    
    public int updateOrderState(String orderID,String userID,String state,String magickey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update ty_order set state = ? where id = ? and customer = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.setLong(2, Long.parseLong(orderID));
            statement.setString(3, userID);
            statement.setString(4, magickey);
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
    
    public String queryOrderState(String orderID,String user,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null; 
        String sql = "select state from ty_order where id = ? and customer = ? and magic_key = ?";
        String state = null;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, orderID);
            statement.setString(2, user);
            statement.setString(3, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                state = resultSet.getString("state");
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
        return state;
    }
    
    public WxPayInfo queryWxPayInfo(String magicKey)
    {
        WxPayInfo wxPayInfo = new WxPayInfo();
        dbManager = DBManager.getInstance();
        wxPayInfo.setMagicKey(magicKey);
        Connection connection = null;
        String sql = "select * from wx_payinfo where magic_key = ?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                wxPayInfo.setAPI_KEY(resultSet.getString("api_key"));
                wxPayInfo.setAPP_ID(resultSet.getString("app_id"));
                wxPayInfo.setMCH_ID(resultSet.getString("mch_id"));
                wxPayInfo.setBody(resultSet.getString("body"));
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
        return wxPayInfo;
    }
}
