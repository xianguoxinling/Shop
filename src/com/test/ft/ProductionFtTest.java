package com.test.ft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.model.production.Production;

public class ProductionFtTest
{
    private String server = null;
    public ProductionFtTest()
    {
        server = "magic.puckart.com";
    }
    
    public String createProduction(Production production, String token)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/production/create.action?token=" + token + "&keyID=" + production.getMagicKey()
                    +"&name="+production.getName() +"&price="+production.getPrice() +"&num="+production.getNumber()
                    +"&category="+production.getCategory()+"&brief_introduction="+production.getBriefIntroduction()
                    +"&detailed_introduction="+production.getDetailedIntroduction();
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
    
    public String queryCategory(String category,String magicKey)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/production/querybycategory.action?category=" + category + "&keyID=" + magicKey;
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
    
    public String query(String producitonID,String magicKey)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/production/query.action?producitonID=" + producitonID + "&keyID=" + magicKey;
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
        String token = "test";
        String magicKey = "ccc65483";
        ProductionFtTest ft = new ProductionFtTest();
        Production production = new Production();
        production.setName("小木船");
        production.setBriefIntroduction("这是一个测试的小木船");
        production.setCategory("雕塑");
        production.setDetailedIntroduction("This is just a test");
        production.setMagicKey(magicKey);
        production.setNumber(100);
        production.setPrice(0.01);
        String result = null;
//        String result = ft.createProduction(production, token);
//        System.out.println("CREATE RESULT:"+ result);
        
        result = ft.queryCategory("雕塑", magicKey);
        System.out.println("QUERY RESULT:"+ result);
        
//        result = ft.query("1", magicKey);
//        System.out.println("QUERY 1 RESULT:"+ result);
    }
}
