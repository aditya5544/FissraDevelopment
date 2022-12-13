function change(event)
{
	var drop = $("#regionorarea").val();
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