<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
	integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"
	integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK"
	crossorigin="anonymous"></script>

<style>
img {
	max-width: 16%;
	height: auto;
	margin-left: 540px;
	margin-top: 118px;
}

body {
	margin: auto;
	background:  rgb(75, 72, 94);
}

.login {
	margin-top: 50px;
	max-width: 400px;
	padding: 20px;
}

.register {
	text-decoration: none;
}

.form-label {
	color: white;
}

/* #addData{
	background: white;
	border: solid 2px rgb(159, 168, 34);
	color: rgb(148, 132, 64);
	} */
</style>
</head>
<body>

		<div>
			<img src="<c:url value='/static/img/v2stech_logo.png' />" alt=user
				id="user-logo">
		</div>

		<div class="container login">
			<div id="error1" class="col-sm-12" style="display: none;">
				<div class="alert alert-danger">
					<strong><em class="fa fa-thumbs-down"></em> &nbsp;</strong> <span
						id="error1"></span>
				</div>
			</div>
			<div class="mb-3">
				<label for="username" class="form-label">Username</label> <input
					type="text" class="form-control" id="username"
					placeholder="Enter username">
			</div>
			<span id="usernameError" class="error"></span>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password"
					placeholder="Enter password" >
			</div>
			<span id="passwordError" class="error"></span> 
			<br> 
			<input class="btn btn-success" type="submit" value="Submit" id="submit">
		</div>

	
	<script type="text/javascript" src="<c:url value="/static/js/LoginValidation.js" />"></script>
</body>
</html>
