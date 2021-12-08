package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * In aceasta clasa am creat metode care sa faca conexiunea dintre proiectul Java si baza de date
 * @author Tincu Diana
 *
 */
public class ConnectionFactory {

	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/temaa3";
	private static final String USER="root";
	private static final String PASS="1234";
	private static ConnectionFactory singleInstance = new ConnectionFactory();

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * metoda pentru crearea conexiunii cu baza de date
	 * @return Connection
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * Aceasta metoda returneaza conexiunea creata
	 * @return Connection
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}


	/**
	 * Aceasta metoda inchide un resultSet
	 * @param resultSet
	 * @return
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Aceasta metoda inchide un statement
	 * @param statement
	 * @return
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Aceasta metoda inchide conexiunea transmisa ca si parametru
	 * @param connection
	 * @return
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

