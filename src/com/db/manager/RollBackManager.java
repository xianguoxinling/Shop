package com.db.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.until.errorcode.MAGICCODE;

public class RollBackManager
{
	public static int dealRollback(Connection connection)
	{
		int result = MAGICCODE.PLATFORM_SUCCESS;
		try
		{
			connection.rollback();

		} catch (SQLException e1)
		{
			result = MAGICCODE.DB_ERROR;
			e1.printStackTrace();
		}

		return result;

	}
}
