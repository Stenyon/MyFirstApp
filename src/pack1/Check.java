package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check
 */
@MultipartConfig
@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		/* System.out.println(email); */

		Connection conn = database_connection.getCon();

		try {

			Statement myStmt = conn.createStatement();

			String use_schema = ("use test");
			myStmt.executeUpdate(use_schema);

			String SQL = "SELECT * FROM employees WHERE email='" + email + "'";
			ResultSet rs = myStmt.executeQuery(SQL);

			if ((rs.next())) {
				ResultSet database_password = myStmt
						.executeQuery("SELECT password FROM employees WHERE email='" + email + "'");
				if (database_password.next()) {
					String database_password1 = database_password.getString(1);

					if (password.equals(database_password1)) {
						ResultSet last_name = myStmt
								.executeQuery("SELECT last_name FROM employees WHERE email='" + email + "'");

						while (last_name.next()) {
							String name = last_name.getString(1);

							ResultSet user_id_brut = myStmt
									.executeQuery("SELECT id FROM employees WHERE email='" + email + "'");

							String user_id = null;

							if (user_id_brut.next()) {
								user_id = user_id_brut.getString(1);
							}

							String create_schema = "CREATE SCHEMA IF NOT EXISTS " + user_id + "_schema;";
							myStmt.executeUpdate(create_schema);

							use_schema = ("use " + user_id + "_schema;");
							myStmt.executeUpdate(use_schema);

							String dbName = user_id + "_schema";
							String columns_name = "";

							/*
							 * String s1 = "s1"; String s2 = "s2"; String s3 = "s3"; List<String> col_name =
							 * new ArrayList<String>(); col_name.add(s1); col_name.add(s3); col_name.add(1,
							 * s2); System.out.println(col_name); String csv = String.join(",", col_name);
							 * System.out.println(csv);
							 */

							List<Object> tables = new ArrayList<>();

							ResultSet get_tables = myStmt
									.executeQuery("SELECT TABLE_NAME \r\n" + "FROM INFORMATION_SCHEMA.TABLES\r\n"
											+ "WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='" + dbName + "' ");

							while (get_tables.next()) {

								tables.add(get_tables.getString(1));

							}

							request.getSession().setAttribute("name", name);
							request.getSession().setAttribute("email", email);
							request.getSession().setAttribute("user_id", user_id);
							request.getSession().setAttribute("dbName", dbName);
							request.getSession().setAttribute("columns_name", columns_name);
							request.getSession().setAttribute("tables", tables);
							/*
							 * try { get_tables.close(); user_id_brut.close(); last_name.close();
							 * database_password.close(); rs.close();
							 * 
							 * 
							 * } catch (SQLException e) {
							 * 
							 * e.printStackTrace();
							 * 
							 * }
							 */

							request.getRequestDispatcher("Userpage.jsp").forward(request, response);

						}

					} else {
						request.setAttribute("errorMessage", "Invalid password");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
						dispatcher.forward(request, response);
					}
				}

			} else {
				request.setAttribute("errorMessage", "Invalid email");
				String table_name = request.getParameter("table_name");
				if (table_name == null) {
					table_name = "Select table";
				}
				request.getSession().setAttribute("table_name", table_name);
				/*System.out.println(table_name);*/
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception exc) {
			System.out.println("Insert incomplete.");
			exc.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("not cool.");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h2 style= \"color:red \" >You can't acces this page!</h2>");
		out.println("<a href=\"http://localhost:8080/JDBC_test/ \" > Try this!</a>");
	}

}
