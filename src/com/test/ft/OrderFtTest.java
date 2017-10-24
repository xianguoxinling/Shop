package com.test.ft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrderFtTest
{
    private String server = null;
    public String token = "v27mky8y1bimw9titjtdd180yyiludli10";
    public String magicKey = "ccc65483";
    public OrderFtTest()
    {
        server = "magic.puckart.com";
    }
    
    public String createOrderIM(String id,String num,String address)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/order/immediateyly_order.action?token=" + token + "&keyID=" + magicKey+"&id="+id
                    +"&num="+num+"&address="+address;
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
    
    public String createOrderByCart(String address)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/order/create_order.action?token=" + token + "&keyID=" + magicKey
                    +"&address="+address;
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
    
    public String queryOrder()
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/order/query_all.action?token=" + token + "&keyID=" + magicKey;
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
        OrderFtTest ft = new OrderFtTest();
        String result = ft.createOrderIM("1", "2", "18");
        System.out.println(result);
        result = ft.createOrderByCart("18");
        System.out.println(result);
        result = ft.queryOrder();
        System.out.println(result);
        
    }
}
