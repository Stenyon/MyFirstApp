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
@WebServlet("/Add_column")
public class Add_column extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String new_col_name = request.getParameter("new_column_name");
		String table_name = (String) request.getSession().getAttribute("table_name");

		/* System.out.println(new_col_name); */

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
			String sql = "ALTER TABLE `" + table_name + "` ADD COLUMN `" + new_col_name
					+ "` VARCHAR(45) DEFAULT \"\"; ";

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
