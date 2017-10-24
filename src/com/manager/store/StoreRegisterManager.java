package com.manager.store;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import com.until.errorcode.MAGICCODE;

public class StoreRegisterManager
{
    private String server = null;
    private String code = null;
    private String token = null;
    private String magicKey = null;
    
    public StoreRegisterManager()
    {
//        server = "magic.puckart.com";
        server = "127.0.0.1";
    }
    
    public int storeRegister(String phone,String password)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/auth/store_register.action?phone=" + phone + "&password=" + password;
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
            
            JSONObject jsonObject = null;
            jsonObject = JSONObject.fromObject(result);
            code = (String) jsonObject.get("code");
            if(MAGICCODE.MAGIC_OK.equals(code))
            {
                token = (String) jsonObject.get("token");
                magicKey = (String) jsonObject.get("keyID");
            }
            
            
        } catch (Exception e)
        {
            return MAGICCODE.ERROR;
        } finally
        {
            
        }

        return MAGICCODE.OK;
    }
    
    public int storeLogin(String phone,String password)
    {
        String result = null;
        try
        {
            String strURL = "http://" + server + "/auth/store_login.action?phone=" + phone + "&password=" + password;
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
            
            JSONObject jsonObject = null;
            jsonObject = JSONObject.fromObject(result);
            code = (String) jsonObject.get("code");
            if(MAGICCODE.MAGIC_OK.equals(code))
            {
                token = (String) jsonObject.get("token");
                magicKey = (String)jsonObject.get("keyID");
            }
            
        } catch (Exception e)
        {
            return MAGICCODE.ERROR;
        } finally
        {
            
        }

        return MAGICCODE.OK;
    }

    public String getServer()
    {
        return server;
    }

    public void setServer(String server)
    {
        this.server = server;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getMagicKey()
    {
        return magicKey;
    }

    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    
}
