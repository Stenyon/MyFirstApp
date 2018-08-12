<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="pack1.database_connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.*"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"
	media="all">
<link
	href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Raleway+Dots'
	rel='stylesheet' type='text/css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<body>

	<div class="main-agileits">
		<h2 class="sub-head">Sign Up</h2>
		<div class="sub-main">

			<form name="myform" method="post" action="Registerindatabase">

				<input placeholder="First Name" name="first_name" class="name"
					type="text" required="required"> <span class="icon1"><i
					class="fa fa-user" aria-hidden="true"></i></span><br> <input
					placeholder="Last Name" name="last_name" class="name2" type="text"
					required="required"> <span class="icon2"><i
					class="fa fa-user" aria-hidden="true"></i></span><br> <input
					placeholder="Email" name="email" class="mail" type="text"
					title="Contact's email (format: xxx@xxx.xxx)"
					pattern="[a-zA-Z0-9!#$%&amp;'*+\/=?^_`{|}~.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*"
					id="email" required="required"> <span class="icon3"><i
					class="fa fa-envelope" aria-hidden="true"></i></span><br> <input
					placeholder="Password" name="password" class="pass" type="password"
					required="required" id="password"> <span class="icon4"><i
					class="fa fa-unlock" aria-hidden="true"></i></span><br> <input
					placeholder="Confirm Password" name="confirm_password" class="pass"
					type="password" required="required" id="confirm_password">
				<span class="icon5"><i class="fa fa-unlock"
					aria-hidden="true"></i></span><br> <span id='message'></span> <br />
				<input type="submit" value="Register" id="Register" disabled>

				<%
					Connection conn = database_connection.getCon();
					List<Object> email = new ArrayList<>();
					try {

						Statement myStmt = conn.createStatement();

						String use_schema = ("use test");
						myStmt.executeUpdate(use_schema);

						String SQL = "SELECT email FROM employees";
						ResultSet emails = myStmt.executeQuery(SQL);

						while (emails.next()) {
							email.add(emails.getString(1));
						}
						/* System.out.println(email.get(1)); */
					}

					catch (Exception exc) {
						System.out.println("Insert incomplete.");
						exc.printStackTrace();
					}
				%>
				<script>
$('#password, #confirm_password, #email').on('keyup', function () {
	var email_list='<%=email%>
					';
										/* 	console.log(($('#email').val()));	
										 console.log(email_list.includes('123@ya')); */
										var x = 0;
										if ($('#password').val() != $(
												'#confirm_password').val()) {
											document.getElementById("Register").disabled = true;
											$('#message').html(
													"Passwords dosen't match")
													.css('color', 'red');
											x++;
										}

										if (email_list.includes($('#email')
												.val())) {
											document.getElementById("Register").disabled = true;
											$('#message').html(
													"Email already exist!")
													.css('color', 'red');
											x++;
										}
										if (x == 0) {
											$('#message').html('').css('color',
													'green');
											document.getElementById("Register").disabled = false;
										}

									});
				</script>
			</form>
		</div>
	</div>
</body>
</html>
