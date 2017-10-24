package com.platform.check;

import com.until.errorcode.MAGICCODE;

public class CheckParameter
{
    public static int checkParameter(String parmeter)
    {
        if(null == parmeter|| "".equals(parmeter))
        {
            return MAGICCODE.ERROR;
        }
        return MAGICCODE.OK;
    }
    
    public static int checkParameter(String parmeter,String parmeter2)
    {
        if(MAGICCODE.ERROR == checkParameter(parmeter) ||(MAGICCODE.ERROR == checkParameter(parmeter2)))
        {
            return MAGICCODE.ERROR;
        }
        
        return MAGICCODE.OK;
    }
    
    public static int checkParameter(String parmeter,String parmeter2,String parmeter3)
    {
        if(MAGICCODE.ERROR == checkParameter(parmeter) ||(MAGICCODE.ERROR == checkParameter(parmeter2)) ||(MAGICCODE.ERROR == checkParameter(parmeter3)))
        {
            return MAGICCODE.ERROR;
        }
        
        return MAGICCODE.OK;
    }
    
    public static int checkParameter(String parmeter,String parmeter2,String parmeter3,String parmeter4)
    {
        if(MAGICCODE.ERROR == checkParameter(parmeter,parmeter2) ||(MAGICCODE.ERROR == checkParameter(parmeter3,parmeter4)))
        {
            return MAGICCODE.ERROR;
        }
        
        return MAGICCODE.OK;
    }
}
