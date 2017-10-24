package com.until.replace;

import com.until.control.base.BaseString;

public class ReplaceSrvToHttp
{
	public static String replace(String base)
	{
		if(null == base)
		{
			return "";
		}
		String result  = base.replace("/srv/www/htdocs/", BaseString.baseURL2);
		return result;
	}
	
	public static void main(String arts[])
	{
		String srv = "www.puckart.com/image/i.png";
		String result = ReplaceSrvToHttp.replace(srv);
		System.out.println(result);
	}
	
}
