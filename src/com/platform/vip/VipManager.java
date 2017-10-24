package com.platform.vip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.until.errorcode.MAGICCODE;

import net.sf.json.JSONObject;

public class VipManager
{
    public String server = "127.0.0.1";
    
    public double queryDiscount(String userID, String keyID)
    {
        JSONObject jsonObject = null;
        double discount = 1;
        String result = null;
        try
        {
            String strURL = "http://" + server + "/vip/member/querydiscount.action?userID=" + userID + "&keyID=" + keyID;
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
            
            jsonObject = JSONObject.fromObject(result);
//            System.out.println(result);
            String code = (String) jsonObject.get("code");
//            System.out.println("CODE:"+code);
//            System.out.println("XXXX:"+MAGICCODE.MAGIC_OK.equals(code));
            if(MAGICCODE.MAGIC_OK.equals(code))
            {
                String discountStr = (String) jsonObject.get("discount");
                discount = Double.parseDouble(discountStr);
//                System.out.println("DISCOUTN:"+discount);
                if(discount <=0 || discount > 1)
                {
                    return 1;
                }
            }

        } catch (Exception e)
        {
            
        } finally
        {
            
        }

        return discount;
    }
    
    public static void main(String args[])
    {
        VipManager test = new VipManager();
        double discount =  test.queryDiscount("25", "eb07c48a400a4288a0ee8322250cff04");
        System.out.println(discount);
        discount =  test.queryDiscount("26", "eb07c48a400a4288a0ee8322250cff04");
        System.out.println(discount);
        discount =  test.queryDiscount("25", "eb07c48a400a4288a0ee833522250cff04");
        System.out.println(discount);
                
    }
}
