package com.service.interfaces;

import java.util.List;

import com.model.address.Address;

public interface AddressServiceInterface
{
    public int addAddress(Address address);
    public Address queryAddressByID(String id,String magicKey);
    public List<Address> queryAllUserAddress(String userID,String magicKey);
    public int deleteAddress(String id,String userID,String magicKey);
    public int commonAddress(String id,String userID,String magicKey);
    public int unCommonAddress(String userID,String magicKey);
    public Address queryCommonAddress(String userID,String magicKey);
    
}
