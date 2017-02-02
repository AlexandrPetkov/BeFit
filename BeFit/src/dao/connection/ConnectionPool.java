package dao.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import Constant.Constants;
import dao.exception.DAOException;

public class ConnectionPool implements Closeable {
	private static final ConnectionPool instance = new ConnectionPool();

	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;

	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;
	private ResourceBundle bundle;

	private ConnectionPool() {

		bundle = ResourceBundle.getBundle(Constants.DB_BUNDLE);
		this.driver = bundle.getString(Constants.DB_DRIVER);
		this.user = bundle.getString(Constants.DB_USER);
		this.password = bundle.getString(Constants.DB_PASSWORD);
		this.url = bundle.getString(Constants.DB_URL);

		try {
			this.poolsize = Integer.parseInt(bundle.getString(Constants.DB_POOLSIZE));
		} catch (NumberFormatException e) {
			this.poolsize = 10;
		}
	}

	public void init() throws SQLException {

		freeConnection = new ArrayBlockingQueue<>(poolsize);
		busyConnection = new ArrayBlockingQueue<>(poolsize);

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < poolsize; i++) {
			freeConnection.add(DriverManager.getConnection(url, user, password));
		}
	}

	public Connection take() throws InterruptedException { // запращиваем
															// соединение
		Connection con = freeConnection.take(); // извлекаем из
												// arrayBlockingQueue
		busyConnection.put(con); // помещаем в очередь занятых коннектионов
		return con;
	}

	public void free(Connection con) throws InterruptedException, DAOException { // возвращает
																					// коннекшн
																					// в
																					// очередь
		if (con == null) { // проврека если пользователь дурак, вдруг передат //
							// null или хакЭр
			throw new DAOException(Constants.CONNECTION_POOL_IS_NULL);
		}

		Connection tempConnection = con; // вариант безопасности кода
		con = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	@Override
	public void close() throws IOException {
		ArrayList<Connection> listConnection = new ArrayList<Connection>();

		for (int i = 0; i < poolsize; i++) { // перемещение из 2х колелекций в
												// 3ю
			listConnection.add(busyConnection.poll());
			listConnection.add(freeConnection.poll());
		}

		for (Connection connection : listConnection) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
