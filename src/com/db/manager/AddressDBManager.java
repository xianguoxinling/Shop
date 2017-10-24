package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.address.Address;
import com.until.errorcode.MAGICCODE;

public class AddressDBManager
{
    protected DBManager dbManager = null;

    public int addAddress(Address address)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO address(user_id,city,address,magic_key,contact_name,mobile,is_common,update_time) VALUES (?,?,?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getUserID());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getAddress());
            statement.setString(4, address.getMagicKey());
            statement.setString(5, address.getContactName());
            statement.setString(6, address.getMobile());
            statement.setString(7, address.getIsCommonAddress());
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
    
    public Address queryAddressByID(String id,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from address where id = ? and magic_key = ?";
        Address address = null;
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
                address = dbUntil.queryAddressFromResultSet(resultSet);
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
        return address;
    }
    
    public Address queryCommonAddress(String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from address where is_common = ? and user_id = ? and magic_key = ? and state = ?";
        Address address = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "yes");
            statement.setLong(2, Long.parseLong(userID));
            statement.setString(3, magicKey);
            statement.setString(4, "OK");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                address = dbUntil.queryAddressFromResultSet(resultSet);
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
        return address;
    }
    
    public int queryAllUserAddress(List<Address> addressList,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from address where user_id = ? and magic_key = ? and state = ? ";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            statement.setString(3, "OK");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Address address = dbUntil.queryAddressFromResultSet(resultSet);
                addressList.add(address);
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
    
    public int deleteAddress(String id,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update address set state = ? where id = ? and user_id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "NOT_USED");
            statement.setLong(2, Long.parseLong(id));
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
    
    public int commonAddress(String id,String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "update address set is_common = ? where id = ? and user_id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "yes");
            statement.setLong(2, Long.parseLong(id));
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
    
    public int unCommonAddress(String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "update address set is_common = ? where is_common = ? and user_id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "no");
            statement.setString(2, "yes");
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
    
    public int queryAddressNum(String userID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select count(id) a from address where user_id = ? and magic_key = ? and state = ?";
        int num = 0;
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, magicKey);
            statement.setString(3, "OK");
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
