<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/icons-design.css">
</head>
<body>
	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('https://www.istockphoto.com/resources/images/PhotoFTLP/img_67920257.jpg');">

			<div class="wrap-login100">
				<form class="login100-form validate-form" action="Check"
					method="post">

					<img class="login100-form-logo"
						src="https://images-na.ssl-images-amazon.com/images/I/51zLZbEVSTL._SY355_.jpg"></img>
					<span class="login100-form-title p-b-34 p-t-27"> <br /> Log	in</span>

					<div class="wrap-input100 validate-input"
						data-validate="Enter email">
						<input class="input100" type="text" name="email"
							placeholder="Email"> <span class="focus-input100"
							data-placeholder="&#xf207;"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Enter password">
						<input class="input100" type="password" name="password"
							placeholder="Password"> <span class="focus-input100"
							data-placeholder="&#xf191;"></span>
					</div>

					<h3 align="center" style="color: red;">${errorMessage}</h3>
					<br />


					<div class="container-login100-form-btn">
						<button class="login100-form-btn">Login</button>

					</div>

<!-- 					<div class="left">
						<a class="txt1" href="#"> Forgot Password? </a>
					</div> -->

					<div class="right">
						<a class="txt1" href="Register.jsp"> Create new account </a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>