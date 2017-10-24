package com.test.ut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.model.address.Address;
import com.service.address.AddressService;
import com.service.interfaces.AddressServiceInterface;
import com.test.ft.AddressFtTest;
import com.until.errorcode.MAGICCODE;

public class AddressUT
{

    public AddressServiceInterface service = null;
    @Before
    public void setUp() throws Exception
    {
        service = new AddressService();
    }

    @Test
    public void testAddAddress()
    {
        AddressFtTest ft = new AddressFtTest();
        Address address = new Address();
        address.setAddress("高新区健康东街");
        address.setCity("山东潍坊");
        address.setMagicKey(ft.magicKey);
        address.setMobile("135648123");
        address.setUserID("11");
        
        int result = service.addAddress(address);
        assertEquals(MAGICCODE.OK,result);
    }

    @Test
    public void testQueryAllUserAddress()
    {
    }

    @Test
    public void testDeleteAddress()
    {
    }

    @Test
    public void testCommonAddress()
    {
    }

    @Test
    public void testUnCommonAddress()
    {
    }

    @Test
    public void testQueryCommonAddress()
    {
    }

}
