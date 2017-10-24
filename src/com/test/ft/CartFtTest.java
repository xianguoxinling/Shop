package com.test.ft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CartFtTest
{
    public String token = "v27mky8y1bimw9titjtdd180yyiludli10";
    public String magicKey = "ccc65483";
    private String server = null;
    
    public CartFtTest()
    {
        server = "magic.puckart.com";
    }
    
    public String addProductionToCart(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/add.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
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
    
    public String queryCart()
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/query.action?token=" + token + "&keyID=" + magicKey;
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
    
    public String chooseProduction(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/choose.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
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
    
    public String unChooseProduction(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/unchoose.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
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
    
    public String increaseProduction(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/increase.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
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
    
    public String decreaseProduction(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/decrease.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
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
    
    public String deleteProduction(String id)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/delete.action?token=" + token + "&keyID=" + magicKey+"&id="+id;
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
    
    public String updateProductionNum(String id,String num)
    {
        String result = null;
        try
        {
            String strURL = "https://" + server + "/shop/cart/update_num.action?token=" + token + "&keyID=" + magicKey+"&id="+id+"&num="+num;
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
        String id = "2";
        CartFtTest ft = new CartFtTest();
        String result = null;
        result = ft.addProductionToCart(id);
        System.out.println("ADD CART:"+result);
        result = ft.queryCart();
        System.out.println("QUERY CART:"+result);
        
        result = ft.unChooseProduction(id);
        System.out.println("UNCHOOSE CART:"+result);
        result = ft.queryCart();
        System.out.println("QUERY CART:"+result);
        
        result = ft.chooseProduction(id);
        System.out.println("CHOOSE CART:"+result);
        result = ft.queryCart();
        System.out.println("QUERY CART:"+result);
        
        result = ft.increaseProduction(id);
        System.out.println("INCREASE CART:"+result);
        result = ft.queryCart();
        System.out.println("QUERY CART:"+result);
        
        result = ft.decreaseProduction(id);
        System.out.println("DECREASE CART:"+result);
        result = ft.queryCart();
        System.out.println("QUERY CART:"+result);
        
        result = ft.updateProductionNum(id, "10");
        System.out.println("UPDATE NUM CART:"+result);
        result = ft.queryCart();
        System.out.println("QUERY CART:"+result);
        
//        result = ft.deleteProduction(id);
//        System.out.println("DECREASE CART:"+result);
//        result = ft.queryCart();
//        System.out.println("QUERY CART:"+result);
    }

}
