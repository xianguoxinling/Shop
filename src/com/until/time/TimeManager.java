package com.until.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeManager
{
    public void test1()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        
        try{  
            Date d1 = df.parse("2004-03-26 13:31:40");  
            Date d2 = df.parse("2004-01-02 11:30:24");  
            long diff = d1.getTime() - d2.getTime();  
            long days = diff / (1000 * 60 * 60 * 24);  
            System.out.println(days);
        }catch (Exception e){  
        }  
    }
    public void test2() throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        java.util.Date now = df.parse("2004-03-26 13:31:40");  
        java.util.Date date=df.parse("2004-01-02 11:30:24");  
        long l=now.getTime()-date.getTime();  
        long day=l/(24*60*60*1000);  
        long hour=(l/(60*60*1000)-day*24);  
        long min=((l/(60*1000))-day*24*60-hour*60);  
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);  
        System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒"); 
    }
    
    public static void main(String args[])
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        try{  
            Date d1 = new Date();  
            Date d2 = df.parse("2017-04-01 11:30:24");  
            long diff = d1.getTime() - d2.getTime();  
            long days = diff / (1000 * 60 * 60 * 24);  
            System.out.println(days);
        }catch (Exception e){  
        }  
    }
}
