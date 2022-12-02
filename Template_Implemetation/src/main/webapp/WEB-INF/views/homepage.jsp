<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored = "false" %>
     <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
</script>
<form action="uploadYourFile" method="post" enctype="multipart/form-data">
Select Region or Area<select class="txtbox col-md-12" name="regionareaname"  id="regionorarea">
<option selected disabled value="">Select Region or Area</option>
<c:forEach items="${regionAreaList}" var="regionArea">
	<option value="${regionArea.data}">${regionArea.data}</option>
</c:forEach>

</select>
<span id="error1"></span><br>
Tenant<select class="txtbox col-md-12" name="tenant"  id="tenant">
<option selected disabled value="">Tenant</option>

<c:forEach items="${tenantList}" var="tenant">
	<option value="${tenant.tenantName}">${tenant.tenantName}</option>
</c:forEach>
</select><br>
Select file<input type="file" id="submitbtn" class="submit-btn" name="fileToStore" value="File Upload">
<br>
<input type="submit" value="upload"  class="submit-btn" id="submitbtn" name="upload"><br>
</form>
</body>
 </html>





<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(window).on("load", function() {

	$("#latLong").on("click", function() {
	    var street = $("#street").val();
		var pincode = $("#pincode").val();
		var city = $("#city").val();
		var latLongData = {
				street: street,
				pincode: pincode,
				city:city
				}
		var url = "http://localhost:8080/FindLatLog/latLongAPi";
		$.ajax({
			type: 'POST',
			url: url,
			data: JSON.stringify(latLongData),
			contentType: 'application/json',
			success: function(data) {
			
				
			},
			error: function(message) {
			    $('#streetError').html("street error");
				$('#streetError').show(); 
				
		    	$('#pincodeError').html("pincode error");
				$('#pincodeError').show();
				
				$('#cityError').html("city error");
				$('#cityError').show();
			}
	
});
	});
	});
</script>
<h1>Find LAT & LONG</h1>

<label for="street">Street:</label>
<input type="text"  id="street">
 <span id="streetError"></span>

<label for="Pincode">Pincode:</label>
 <span id="pincodeError"></span>
<input type="text" id="pincode" >

<label for="City">City:</label>
<input type="text" id="city">
 <span id="cityError"></span>
<BR>

<label for="LAT">LAT</label>
<input type="text" id="lat">

<label for="LONG">LONG</label>
<input type="text" id="long">
<BR>
 <input  type="submit" value="GetLatLong" id="latLong"></br> -->
 
 