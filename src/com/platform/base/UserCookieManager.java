package com.platform.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UserCookieManager
{

	public static String getUserID(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String userID = null;
		if (null == userID)
		{
			Cookie cookie = getCookieByName(request, "cantou");
			if (null != cookie)
			{
				userID = cookie.getValue();
//				userID = CookieEncrypt.AESDncode("magic", userID);
				if (null == userID || "".equals(userID))
				{
					return null;
				}
			}
			
//			Cookie[] cookies = request.getCookies();
//			for(Cookie cookie : cookies){
//			    String userName = cookie.getName();
//			    System.out.println("COOKIE NAME:"+userName);
//			    
//			    if("magicgraffiti".equals(userName))
//			    {
//			    	String value = cookie.getValue(); 
//			    	System.out.println("COOKIE VALUE:"+value);
//			    	userID = CookieEncrypt.AESDncode("magic", value);
////			    	userID = AES.Decrypt(value, "ILOVELIHANQINGLA");
//			    	System.out.println("COOKIE userID:"+userID);
//					if (null == userID || "".equals(userID))
//					{
//						return null;
//					}
//			    }
//			    
//			}
			
		}
		return userID;
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name)
	{
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name))
		{
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else
		{
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request)
	{
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies)
		{
			for (Cookie cookie : cookies)
			{
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

}
