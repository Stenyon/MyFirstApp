package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registerindatabase
 */
@WebServlet("/Registerindatabase")
public class Registerindatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname = request.getParameter("first_name");
		String lastname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

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
			String sql = "insert into employees " + " (last_name, first_name, email,password)" + " values ('"
					+ firstname + "', '" + lastname + "', '" + email + "', '" + password + "')";
			myStmt.executeUpdate(sql);
			System.out.println("Insert complete.");
			PrintWriter out = response.getWriter();
			out.println(
					"<div class=\"wrap-login100\"><link rel=\"stylesheet\" href=\"css/successfully-register.css\">");
			out.println("<div class=\"container-login100\"");
			out.println(
					"<br/><h2 style=\"color: red;\">You have been successfully registered!</h2><br/><h2 style=\"color: white;\">Welcome "
							+ firstname + " !</h2><br/>");
			out.println(
					"<div class=\"container-login100-form-btn\"><div class=\"login100-form-btn\"><a href='Login.jsp'> Login page </a></div></div></div>");

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
