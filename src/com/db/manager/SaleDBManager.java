package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.order.SaledHistory;
import com.until.errorcode.MAGICCODE;

public class SaleDBManager
{
    protected DBManager dbManager = null;
    public int createSaleHistory(SaledHistory saleHistory)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO sale (order_id,production_id,number,price,customer,production_name,magic_key,production_pic,brief_introduction,update_time) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(saleHistory.getOrderID()));
            statement.setString(2, saleHistory.getProductionID());
            statement.setLong(3, saleHistory.getNum());
            statement.setDouble(4, saleHistory.getRealPrice());
            statement.setString(5, saleHistory.getUserID());
            statement.setString(6, saleHistory.getProductionName());
            statement.setString(7, saleHistory.getMagicKey());
            statement.setString(8, saleHistory.getProductionPic());
            statement.setString(9, saleHistory.getProductionBriftIntroduce());
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
    
    public SaledHistory querySaleHistory(String id,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from sale where id = ? and magic_key = ?";
        SaledHistory saledHistory = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(id));
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                saledHistory = dbUntil.getSaledHistoryFromResultSet(resultSet);
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
        return saledHistory;
    }
}
