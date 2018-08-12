package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Create_table
 */
@MultipartConfig
@WebServlet("/Create_table")
public class Create_table extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Create_table() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String new_table_name = request.getParameter("new_table_name");
		System.out.println(new_table_name);

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
			String sql = " CREATE TABLE " + new_table_name + " (\r\n" + "Rent VARCHAR(50) DEFAULT '',\r\n"
					+ "Food VARCHAR(30) DEFAULT '',\r\n" + "Clothes VARCHAR(30) DEFAULT '',\r\n"
					+ "Stores VARCHAR(50) DEFAULT '',\r\n" + "Income VARCHAR(50) DEFAULT ''\r\n" + ") ";
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
