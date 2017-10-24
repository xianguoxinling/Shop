package com.test.ft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.model.category.Category;

public class CategoryFtTest
{
    private String server = null;
    public CategoryFtTest()
    {
        server = "magic.puckart.com";
    }
    
    public String createCategory(Category category, String token)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/category/create.action?token=" + token + "&keyID=" + category.getMagicKey()+"&name="+category.getName()+"&introduction="+category.getIntroduction();
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
    
    public String queryCategory(String keyID)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/category/query.action?keyID=" +keyID ;
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
    
    public static void main(String[] args)
    {
        CategoryFtTest test = new CategoryFtTest();
        String keyID = "ccc65483";
        Category category = new Category();
        category.setName("测试");
        category.setIntroduction("测试");
        category.setMagicKey(keyID);
        String token = "test";
        
//        Category category2 = new Category();
//        category2.setName("纸艺");
//        category2.setIntroduction("测试");
//        category2.setMagicKey(keyID);
        
        String result = test.createCategory(category, token);
        System.out.println("CREATE RESULT:"+ result);
        
//        result = test.createCategory(category2, token);
//        System.out.println("CREATE RESULT2:"+ result);
//        
//        result = test.queryCategory(keyID);
//        System.out.println("QUERY RESULT:"+ result);
    }

}
