package dao.impl;

import java.io.IOException;
import java.sql.SQLException;

import dao.SourceInit;
import dao.connection.ConnectionPool;

public class DbInitImpl implements SourceInit {

	@Override
	public void init() {
		ConnectionPool pool = ConnectionPool.getInstance();
		try {
			pool.init();
		} catch (SQLException e) {
			e.printStackTrace();
			// logger
			throw new RuntimeException("ConnectionPool didn't initialized");
		}

	}

	@Override
	public void destroy() {
		ConnectionPool pool = ConnectionPool.getInstance();
		try {
			pool.close();
		} catch (IOException e) {
			// logger
		}
	}

}
