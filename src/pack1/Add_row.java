package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/Add_row")
public class Add_row extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String table_name = (String) request.getSession().getAttribute("table_name");

		Connection conn = database_connection.getCon();
		Statement myStmt = null;
		try {
			myStmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			// 3. Execute SQL query
			String sql = " INSERT INTO " + table_name + " () VALUES();";
			myStmt.executeUpdate(sql);
			System.out.println("Insert complete.");

		} catch (Exception exc) {
			System.out.println("Insert incomplete.");
			exc.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
