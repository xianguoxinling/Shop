package com.platform.log;

public class MagicLogManager
{
	
	public static void debug(String subject,String message)
	{
		WriteLogThread logThread = new WriteLogThread("debug",subject,message);
		Thread thread = new Thread(logThread);
		thread.start();
	}
	
	public static void info(String subject,String message)
	{
		WriteLogThread logThread = new WriteLogThread("info",subject,message);
		Thread thread = new Thread(logThread);
		thread.start();
	}
	
	public static void warn(String subject,String message)
	{
		WriteLogThread logThread = new WriteLogThread("warn",subject,message);
		Thread thread = new Thread(logThread);
		thread.start();
	}
	
	public static void error(String subject,String message)
	{
		WriteLogThread logThread = new WriteLogThread("error",subject,message);
		Thread thread = new Thread(logThread);
		thread.start();
	}

}
