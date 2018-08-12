package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database_connection {

	private static Connection conn;

	static {

		String url = "jdbc:mysql://localhost:3306/test	" + "?verifyServerCertificate=false" + "&useSSL=true"
				+ "&requireSSL=true";
		String user = "root";
		String password_server = "parola_admin";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password_server);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("connected");
		}
		/*
		 * finally { if (conn != null) { try { conn.close(); } catch (Exception e){} } }
		 */
	}

	public static Connection getCon() {
		return conn;
	}

}
