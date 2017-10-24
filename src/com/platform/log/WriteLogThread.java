package com.platform.log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WriteLogThread implements Runnable
{

	public String level = null;
	public String subject = null;
	public String message = null;
	
	public WriteLogThread(String level,String subject,String message)
	{
		this.level = level;
		this.subject = subject;
		this.message = message;
	}
	
	@Override
	public void run()
	{
		Logger logger = LogManager.getLogger();
		
		if("debug".equals(level))
		{
			logger.debug(subject+":"+message);
		}else if("info".equals(level))
		{
			logger.info(subject+":"+message);
		}else if("warn".equals(level))
		{
			logger.warn(subject+":"+message);
		}else
		{
			logger.error(subject+":"+message);
		}
	}

}
