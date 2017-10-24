package com.service.address;

import java.util.ArrayList;
import java.util.List;

import com.db.manager.AddressDBManager;
import com.model.address.Address;
import com.service.interfaces.AddressServiceInterface;
import com.until.errorcode.MAGICCODE;

public class AddressService implements AddressServiceInterface
{

    protected AddressDBManager dbManager = null;
    
    public AddressService()
    {
        dbManager = new AddressDBManager();
    }
    
    @Override
    public int addAddress(Address address)
    {
        int result = MAGICCODE.OK;
        String isCommon = address.getIsCommonAddress();
        if("yes".equals(isCommon))
        {
            System.out.println("ISCOMMON:"+isCommon);
            
            result = unCommonAddress(address.getUserID(),address.getMagicKey());
            if(MAGICCODE.OK != result)
            {
                return result;
            }
        }else
        {
            System.out.println("ISCOMMON:"+isCommon);
            String userID = address.getUserID();
            String magicKey = address.getMagicKey();
            int count = dbManager.queryAddressNum(userID, magicKey);
            System.out.println("COUNT:"+count);
            if(0 == count)
            {
                isCommon = "yes";
                address.setIsCommonAddress(isCommon);
            }
        }
        result = dbManager.addAddress(address);
        if(result != MAGICCODE.OK)
        {
            return result;
        }
        
        return result;
    }

    @Override
    public Address queryAddressByID(String id, String magicKey)
    {
        Address address = dbManager.queryAddressByID(id, magicKey);
        return address;
    }

    @Override
    public List<Address> queryAllUserAddress(String userID, String magicKey)
    {
        List<Address> addressList = new ArrayList<Address>();
        int result = dbManager.queryAllUserAddress(addressList, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return addressList;
    }

    @Override
    public int deleteAddress(String id, String userID, String magicKey)
    {
        int result = dbManager.deleteAddress(id, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int commonAddress(String id, String userID, String magicKey)
    {
        int result = MAGICCODE.OK;
        
        result = unCommonAddress(userID,magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        result = dbManager.commonAddress(id, userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int unCommonAddress(String userID, String magicKey)
    {
        int result = dbManager.unCommonAddress(userID, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public Address queryCommonAddress(String userID, String magicKey)
    {
        Address address = dbManager.queryCommonAddress(userID, magicKey);
        return address;
    }

}
