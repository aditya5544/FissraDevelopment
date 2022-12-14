$(window).on("load", function() {
	$("#addData").on("click", function() {

		var username = $("#username").val();
		var password = $("#password").val();
		var logincreds = {
			username: username,
			password: password
		}
		var url = "http://localhost:8080/FissraDevelopment/login-data";
		$('#usernameError').html("");
		$('#passwordError').html("");
		$.ajax({
			type: 'POST',
			url: url,
			data: JSON.stringify(logincreds),
			contentType: 'application/json',
			success: function(data) {
				$("body").html(data)
			},
			error: function(message) {
				$('#error1').html(message.responseText)
				$('#error1').show();
				$("#error1").delay(8000).fadeOut("slow");
				$('#usernameError').html(message.responseJSON.username);
				$('#usernameError').show();
				$('#passwordError').html(message.responseJSON.password);
				$('#passwordError').show();

			}
		});
	});
});