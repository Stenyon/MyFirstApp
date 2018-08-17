<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.*"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="pack1.database_connection"%>
<head>
<link href="css/Userpage.css" rel="stylesheet" type="text/css"
	media="all">
</head>
<header>
	<h1>Hello ${name} !</h1>


	<%
		String user_id = (String) request.getSession().getAttribute("user_id");
		@SuppressWarnings("rawtypes")
		List tables = (List) request.getSession().getAttribute("tables");
		String dbName = (String) request.getSession().getAttribute("dbName");
		String table_name = (String) request.getSession().getAttribute("table_name");
		if (table_name == null){
			table_name = "Select table";
		}
	%>

	<div class="Toolbar">
		<button onclick="change_table()" style="float: left; margin-left: 5px"
			id="change_table">Change table</button>

		<select id="dropdown" class="drop">
			<%
				for (int u = 0; u < tables.size(); u++) {
			%>
			<option value="<%=u%>"><%=tables.get(u)%></option>
			<%
				}
			%>
		</select>
		<div class="mid_button">
		<button onclick="create_table()" >Create new table</button>
		<button onclick="delete_table()" >Delete table</button>
		<button onclick="add_row()" >Add row</button>
		<button onclick="add_column()" >Add column</button>
		<button onclick="save_data()" id="save_data">Save</button>
		</div>
		<a href="http://localhost:8080/JDBC_test"
			style="float: right; margin-right: 5px"><button>Log out</button>
		</a>
	</div>

	<h2 align="center">
		<font><strong><%=table_name%></strong></font>
	</h2>



	<%
/* 	System.out.println(table_name);
	System.out.println(table_name == "Select table"); */
	 if(!table_name.equals("Select table")){ 
		Connection conn = database_connection.getCon();
		Statement myStmt = null;
		try {
			myStmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int nr_column = 1;
		String columns_name = "";
		try {

			String column_name = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '"
					+ dbName + "' AND TABLE_NAME = '" + table_name + "';";

			ResultSet import_col_names = myStmt.executeQuery(column_name);

			while (import_col_names.next()) {
				nr_column++;
				columns_name = columns_name + import_col_names.getString(1) + ",";
				column_name = "col_" + (nr_column - 1);
	%>

	<input type="text" class="table_head" required="required"
		title="Don't use only digits!"
		value="<%=import_col_names.getString(1)%>" id="<%=column_name%>" />

	<%
		}	

			columns_name = columns_name.substring(0, columns_name.length() - 1);

			String old_columns_name = (String) request.getSession().getAttribute("columns_name");
			columns_name = old_columns_name + columns_name;
			request.getSession().setAttribute("columns_name", columns_name);
	%>
	<%
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</header>
<section>

	<%
		try {
			String sql2 = "SELECT * FROM " + table_name;

			int cell_row = 0;
			ResultSet import_cell = myStmt.executeQuery(sql2);
			while (import_cell.next()) {
				cell_row++;
	%>


	<%
		for (int i = 1; i < nr_column; i++) {
					String cell_id = "cell_" + i + "_" + cell_row;
	%>

	<input type="text" class="table_content"
		value="<%=import_cell.getString(i)%>" id="<%=cell_id%>"
		name="cell_of_col_<%=i%>" />

	<%
		;
				}
	%>
	<br>

	<%
		}
			/* connection.close(); */

		} catch (Exception e) {
			e.printStackTrace();
		}
	%>




	<br>

</section>
<footer>
	<%
		for (int i = 1; i < nr_column; i++) {
	%>
	<input type="text" class="table_sum" id="da" name="sum_of_cell_<%=i%>" />

	<%
		;
		} 
		 }
	
	%>

	<br>




	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</footer>
