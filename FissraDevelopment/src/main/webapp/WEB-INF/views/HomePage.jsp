<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value="/static/css/FileUpload.css" />" />
</head>
<body>

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

	<form action="uploadYourFile" method="post"
		enctype="multipart/form-data">

		<div>
			<h1 style="text-align: center;">Upload Your File Here</h1>
		</div>
		<div class="container region">
				<div class="mb-3">
					Download Request For
					<select class="txtbox col-md-12" name="regionareaname" id="regionorarea">
						<option selected disabled value="">Select Option</option>
						<c:forEach items="${dataList}" var="dropdownDetails">
							<option value="${dropdownDetails.dataName}">${dropdownDetails.dataName}</option>
						</c:forEach>
					</select>
					<span id="dropdownError"></span>
				</div>
				
				<div class="mb-3">
					 Select File
							<input type="file" accept=".csv" id="submitbtn" class="form-control" name="fileToStore" value="File Upload">
							<span id="uploadError"></span>		
				</div>
				<div class="mb-3">
							<!-- <input type="submit" value="upload" class="form-control"
								id="submitbtn" name="upload"> -->
								
					<input type="submit" value="false" id="check" onclick="change()">Save</button>
				</div>
		</div>
	</form>
	<script type="text/javascript" src="<c:url value="/static/js/HomeValidation.js" />"></script>


</body>
</html>
