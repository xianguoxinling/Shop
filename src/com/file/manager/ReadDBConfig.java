package com.file.manager;

import java.net.MalformedURLException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class ReadDBConfig
{
	private XMLManager xmlManager = null;
	private static ReadDBConfig readDBConfig = null;
	private String serverName = null;
	private String databaseName = null;
	private String port = null;
	private String user = null;
	private String password = null;
	
	
	private ReadDBConfig()
	{
		xmlManager = new XMLManager();
		int result = readConfig();
		if(0!=result)
		{
			//write error log
		}
	}
	
	public static ReadDBConfig getInstance()
	{
		if(null == readDBConfig)
		{
			readDBConfig = new ReadDBConfig();
		}
		return readDBConfig;
	}
	
	public int readConfig()
	{
		try
		{
			System.out.println("URL:"+this.getClass().getResource("/"));
			Document doc = xmlManager.read("/home/config/db.xml");
			Element root = xmlManager.getRootElement(doc);
			Iterator<Element> it = root.elementIterator();
			while (it.hasNext())
			{
				Element element = it.next();
				if (element.getName().equals("serverName"))
				{
					serverName = element.getText();
				} else if (element.getName().equals("port"))
				{
					port = element.getText();
				} else if (element.getName().equals("databaseName"))
				{
					databaseName = element.getText();
				} else if (element.getName().equals("user"))
				{
					user = element.getText();
				} else if (element.getName().equals("password"))
				{
					password = element.getText();
				} 
				else
				{

				}
			}

		} catch (MalformedURLException e)
		{
			e.printStackTrace();
			return 1;
		} catch (DocumentException e)
		{
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public XMLManager getXmlManager()
	{
		return xmlManager;
	}
	public void setXmlManager(XMLManager xmlManager)
	{
		this.xmlManager = xmlManager;
	}
	public String getServerName()
	{
		return serverName;
	}
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}
	public String getDatabaseName()
	{
		return databaseName;
	}
	public void setDatabaseName(String databaseName)
	{
		this.databaseName = databaseName;
	}
	public String getPort()
	{
		return port;
	}
	public void setPort(String port)
	{
		this.port = port;
	}
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public static void main(String args[])
	{
		ReadDBConfig test = ReadDBConfig.getInstance();
		System.out.println(test.getDatabaseName());
		System.out.println(test.getPassword());
		System.out.println(test.getPort());
		System.out.println(test.getServerName());
		System.out.println(test.getUser());
	}
}
