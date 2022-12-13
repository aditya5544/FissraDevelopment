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



function change(event)
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
		
}