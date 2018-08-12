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
@WebServlet("/Save_data")
public class Save_data extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int NumberOfCol = Integer.parseInt(request.getParameter("NumberOfCol"));
		int NumberOfRow = Integer.parseInt(request.getParameter("NumberOfRow"));
		String table_name = (String) request.getSession().getAttribute("table_name");

		String x[] = new String[NumberOfCol];
		String y = "CREATE TABLE " + table_name + " (";
		String z = "";
		String table_value[] = new String[NumberOfRow * NumberOfCol + 1];
		String value_insert = "('";
		int counter = 0;

		for (int i = 0; i < NumberOfCol; i++) {

			x[i] = request.getParameter("col_" + (i + 1));
			y = y + "\r\n" + x[i] + " VARCHAR(50) DEFAULT '',";
			z = z + "," + x[i];
		}
		y = y.substring(0, y.length() - 1) + ")";
		z = z.substring(1);

		/* System.out.println(y); */
		for (int k = 1; k <= NumberOfRow; k++) {
			for (int j = 0; j < NumberOfCol; j++) {

				System.out.println(counter);
				table_value[counter] = request.getParameter("cell_" + (j + 1) + "_" + k);

				value_insert = value_insert + table_value[counter] + "','";
				counter++;
			}
			value_insert = value_insert.substring(0, value_insert.length() - 2) + "),('";
		}
		try {
			value_insert = value_insert.substring(0, value_insert.length() - 3);
		} catch (Exception e) {
			System.out.println("Insert rows!");
		}

		System.out.println(value_insert);

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
			String sql = " DROP TABLE " + table_name + "; ";

			String da = y;
			/*
			 * String nu= "insert into "+table_name+" ("+ "Rent,Food,Clothes,Stores,Income"
			 * +") values ('1','2','3','4','5'),('1','2','3','4','5');";
			 */
			String nu = "insert into " + table_name + " (" + z + ") values" + value_insert + ";";

			System.out.println(nu);

			myStmt.executeUpdate(sql);
			myStmt.executeUpdate(da);
			myStmt.executeUpdate(nu);
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
		System.out.println("error Save_data");
	}

}
