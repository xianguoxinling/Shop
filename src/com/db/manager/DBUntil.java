package com.db.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.address.Address;
import com.model.category.Category;
import com.model.order.Order;
import com.model.order.SaledHistory;
import com.model.production.Production;
import com.model.shop.Shop;
import com.model.shop.ShopPic;

public class DBUntil
{
    
    public Category getCategoryFromResultSet(ResultSet resultSet)
    {
        Category category = new Category();
        try
        {
            category.setId(resultSet.getString("id"));
            category.setName(resultSet.getString("name"));
            category.setPic(resultSet.getString("pic"));
            category.setIntroduction(resultSet.getString("introduction"));
            category.setMagicKey(resultSet.getString("magic_key"));
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return category;
    }
    
    public Production getProductionFromResultSet(ResultSet resultSet)
    {
        Production production = new Production();
        try
        {
            production.setId(resultSet.getString("id"));
            production.setName(resultSet.getString("name"));
            production.setBriefIntroduction(resultSet.getString("brief_introduction"));
            production.setDetailedIntroduction(resultSet.getString("detailed_introduction"));
            production.setCategory(resultSet.getString("category"));
            production.setMainPic(resultSet.getString("main_pic"));
            production.setNumber(resultSet.getLong("number"));
            production.setUpdateTime(resultSet.getString("update_time"));
            production.setMagicKey(resultSet.getString("magic_key"));
            production.setPrice(resultSet.getDouble("price"));
            production.setUuid(resultSet.getString("uuid"));
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return production;
    }
    
    public Production getProductionFromResultSetLeftJoinA(ResultSet resultSet)
    {
        Production production = new Production();
        try
        {
            production.setId(resultSet.getString("a.id"));
            production.setName(resultSet.getString("name"));
            production.setBriefIntroduction(resultSet.getString("a.brief_introduction"));
            production.setDetailedIntroduction(resultSet.getString("a.detailed_introduction"));
            production.setCategory(resultSet.getString("a.category"));
            production.setMainPic(resultSet.getString("a.main_pic"));
            production.setNumber(resultSet.getLong("a.number"));
            production.setUpdateTime(resultSet.getString("a.update_time"));
            production.setMagicKey(resultSet.getString("a.magic_key"));
            production.setPrice(resultSet.getDouble("a.price"));
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return production;
    }
    
    public SaledHistory getSaledHistoryFromResultSet(ResultSet resultSet)
    {
        SaledHistory saledHistory = new SaledHistory();
        try
        {
            saledHistory.setId(resultSet.getString("id"));
            saledHistory.setOrderID(resultSet.getString("order_id"));
            saledHistory.setState(resultSet.getString("state"));
            saledHistory.setUserID(resultSet.getString("customer"));
            saledHistory.setProductionID(resultSet.getString("production_id"));
            saledHistory.setProductionName(resultSet.getString("production_name"));
            saledHistory.setProductionBriftIntroduce(resultSet.getString("brief_introduction"));
            saledHistory.setNum(resultSet.getLong("number"));
            saledHistory.setSaledTime(resultSet.getString("update_time"));
            saledHistory.setMagicKey(resultSet.getString("magic_key"));
            saledHistory.setRealPrice(resultSet.getDouble("price"));
            saledHistory.setProductionPic(resultSet.getString("production_pic"));
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return saledHistory;
    }
    
    public Address queryAddressFromResultSet(ResultSet resultSet)
    {
        Address address = new Address();
        try
        {
            address.setId(resultSet.getString("id"));
            address.setAddress(resultSet.getString("address"));
            address.setCity(resultSet.getString("city"));
            address.setContactName(resultSet.getString("contact_name"));
            address.setIsCommonAddress(resultSet.getString("is_common"));
            address.setMagicKey(resultSet.getString("magic_key"));
            address.setMobile(resultSet.getString("mobile"));
            address.setUpdateTime(resultSet.getString("update_time"));
            address.setUserID(resultSet.getString("user_id"));
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return address;
    }
    
    public Order queryOrderFromResultSet(ResultSet resultSet)
    {
        Order order = new Order();
        try
        {
            order.setId(resultSet.getString("id"));
            Address address = new Address();
            address.setId(resultSet.getString("address"));
            order.setAddress(address);
            order.setCustomer(resultSet.getString("customer"));
            order.setDelivery(resultSet.getString("delivery"));
            order.setMagicKey(resultSet.getString("magic_key"));
            order.setState(resultSet.getString("state"));
            order.setTotalPrice(resultSet.getDouble("price"));
            order.setUuid(resultSet.getString("uuid"));
            order.setUpdateTime(resultSet.getString("update_time"));
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return order;
    }
    
    public ShopPic queryShopPicFromResultSet(ResultSet resultSet)
    {
        ShopPic pic = new ShopPic();
        try
        {
         
            pic.setId(resultSet.getString("id"));
            pic.setMagicKey(resultSet.getString("magic_key"));
            pic.setPic(resultSet.getString("pic"));
            pic.setType(resultSet.getString("pic_type"));
            pic.setType_value(resultSet.getString("pic_type_value"));
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return pic;
    }
    
    public Shop queryShopFromResultSet(ResultSet resultSet)
    {
        Shop shop = new Shop();
        try
        {
            shop.setId(resultSet.getString("id"));
            shop.setOwner(resultSet.getString("owner"));
            shop.setLogo(resultSet.getString("logo"));
            shop.setName(resultSet.getString("name"));
            shop.setMainPic(resultSet.getString("main_pic"));
            shop.setMagicKey(resultSet.getString("magic_key"));
            shop.setBriefIntroduction(resultSet.getString("briefIntroduction"));
            shop.setDetailedIntroduction(resultSet.getString("detailedIntroduction"));
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return shop;
    }
}
