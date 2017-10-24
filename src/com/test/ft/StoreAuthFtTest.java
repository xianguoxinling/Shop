package com.test.ft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.until.random.RandomString;

public class StoreAuthFtTest
{
    public String token = "v27mky8y1bimw9titjtdd180yyiludli10";
    public String magicKey = "ccc65483";
    private String server = null;
    
    public StoreAuthFtTest()
    {
        server = "magic.puckart.com";
    }
    
    public String storeRegister(String phone,String password)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/auth/store_register.action?phone=" + phone + "&password=" + password;
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
    
    public String storeLogin(String phone,String password)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/auth/store_login.action?phone=" + phone + "&password=" + password;
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
        String result = null;
        StoreAuthFtTest ft = new StoreAuthFtTest();
//        String phone = RandomString.getRandomString(11);
//        String password = RandomString.getRandomString(11);
//        String result = ft.storeRegister(phone, password);
//        System.out.println(result);
      String phone = "15557193876";
      String password = "ccc65483";
        result = ft.storeLogin(phone, password);
        System.out.println(result);
    }
}
