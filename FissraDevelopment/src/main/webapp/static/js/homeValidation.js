$(window).on("load", function() {

});

function check(field, message) {
	if (field == "" || field == null) {
		$("#" + message + "Error").html("Field is Required");
		$("#" + message + "Error").show();
		flag = false;
	} else {
		$("#" + message + "Error").hide();
		flag = true;
	}
	return flag;
}

 	$("#check").on("click", function() {
 	var select=$("#regionorarea").val();
 	var files = $('#submitbtn')[0].files;
    var fileData = files[0];
    console.log("my file data"+fileData);
    debugger;
/* var drop = $("#regionorarea").val();*/
	inputDetails={}
	inputDetails.dropdown = $("#regionorarea").val();
		inputDetails.upload = $("#submitbtn").val();
		var dropdwonValue = check(inputDetails.dropdown, "dropdown");
		var uploadValue = check(inputDetails.upload, "upload");
		if (!dropdwonValue || !uploadValue) {
			return;
		}
		
		  var   formData = new FormData(); 
  formData.append("fileToStore",fileData);
  formData.append("regionareaname",select);
  var url = "http://localhost:8080/FissraDevelopment/form-details";
 $.ajax({
		type: 'POST',
		 contentType: false,
		 cache: false,
		  processData: false,
		 data: formData,
		  enctype: 'multipart/form-data',
		  url: url,
		success: function(response) {
		 var html = "";
		html += "<h6> "+ response + "</h6>";
         $(messageid).append(html); 
		},
 	error: function(response) {
 	$('#uploadError').html(response.responseText)
				$('#uploadError').show();
 	 var html = "";
		html += "<h6> "+ response + "</h6>";
         $(messageid).append(html); 
 	
	}
	});
});



/*function change(event)
{
	var drop = $("#regionorarea").val();
	inputDetails={}
	inputDetails.dropdown = $("#regionorarea").val();
		inputDetails.upload = $("#submitbtn").val();
		var dropdwonValue = check(inputDetails.dropdown, "dropdown");
		var uploadValue = check(inputDetails.upload, "upload");
		if (!dropdwonValue || !uploadValue) {
			return;
		}
	var dropcreds = {
			dataName:drop
			}
	var url = "http://localhost:8080/FissraDevelopment/drop-data";
	$.ajax({
		type: 'POST',
		url: url,
		data: JSON.stringify(dropcreds),
		contentType: 'application/json',
		success: function(response) {	 
		}
	
	});
		
}*/