package com.test.ft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.model.address.Address;

public class AddressFtTest
{
    
    public String token = "v27mky8y1bimw9titjtdd180yyiludli10";
    public String magicKey = "ccc65483";
    private String server = null;
    
    public AddressFtTest()
    {
        server = "magic.puckart.com";
    }
    
    public String createAddress(Address address)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/address/create.action?token=" + token + "&keyID=" + magicKey+"&city="+address.getCity()
                    +"&address="+address.getAddress() + "&contack_name=" + address.getContactName() + "&mobile=" + address.getMobile();
//                    + "&is_common=" + address.getIsCommonAddress();
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            reader.close();
            httpConn.disconnect();

            result = buffer.toString();

        } catch (Exception e)
        {
        } finally
        {
            
        }

        return result;
    }
    
    public String commonAddress(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/address/common.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            reader.close();
            httpConn.disconnect();

            result = buffer.toString();

        } catch (Exception e)
        {
        } finally
        {
            
        }

        return result;
    }
    
    public String delAddress(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/address/delete.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            reader.close();
            httpConn.disconnect();

            result = buffer.toString();

        } catch (Exception e)
        {
        } finally
        {
            
        }

        return result;
    }
    
    public String queryCommonAddress()
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/address/query_c.action?token=" + token + "&keyID=" + magicKey;
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            reader.close();
            httpConn.disconnect();

            result = buffer.toString();

        } catch (Exception e)
        {
        } finally
        {
            
        }

        return result;
    } 
    
    public String queryAddress()
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/address/query.action?token=" + token + "&keyID=" + magicKey;
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            reader.close();
            httpConn.disconnect();

            result = buffer.toString();

        } catch (Exception e)
        {
        } finally
        {
            
        }

        return result;
    } 
    
    public static void main(String args[])
    {
        AddressFtTest ft = new AddressFtTest();
        Address address = new Address();
        address.setAddress("高新区健康东街");
        address.setCity("山东潍坊");
        address.setMagicKey(ft.magicKey);
        address.setMobile("135648123");
        address.setContactName("mp");
        
        
        String result = null;
        
        result = ft.createAddress(address);
        System.out.println(result);
        result = ft.queryCommonAddress();
        System.out.println(result);
        
        
        Address address2 = new Address();
        address2.setAddress("滨江区江虹路410号");
        address2.setCity("浙江杭州");
        address2.setMagicKey(ft.magicKey);
        address2.setMobile("135648123");
        address2.setContactName("mp");
        
        result = ft.createAddress(address2);
        System.out.println(result);
        
        result = ft.queryAddress();
        System.out.println(result);
        
        result = ft.commonAddress("20");
        System.out.println(result);
        
        result = ft.queryAddress();
        System.out.println(result);
        
        result = ft.delAddress("21");
        System.out.println(result);
        
    }
}
