package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.zhaoshang.Zhaoshang;
import com.until.errorcode.MAGICCODE;

public class ZhaoshangDBManager
{
    protected DBManager dbManager = null;

    public int add(Zhaoshang zs)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO zhaoshang(user_id,city,address,magic_key,contact_name,mobile,is_common,update_time) VALUES (?,?,?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, zs.getUserID());
            statement.setString(2, zs.getCity());
            statement.setString(3, zs.getAddress());
            statement.setString(4, zs.getMagicKey());
            statement.setString(5, zs.getContactName());
            statement.setString(6, zs.getMobile());
            statement.setString(7, zs.getIsCommonAddress());
            statement.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
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
