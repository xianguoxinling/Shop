package com.until.replace;

import com.until.control.base.BaseString;

public class ReplaceHttpToSrv
{
	public static String replace(String base)
	{
		String result = base.replace(BaseString.baseURL2, "/srv/www/htdocs/");
		return result;
	}
}
