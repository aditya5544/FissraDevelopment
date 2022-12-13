

function check(field, message) {
	if (field == "" || field == null) {
		$("#" + message + "Error").html(message + " Field is Required");
		$("#" + message + "Error").show();
		flag = false;
	} else {
		$("#" + message + "Error").hide();
		flag = true;
	}
	return flag;
}


$(window).on("load", function() {
	$("#submit").on("click", function() {
		var userdetails = {}
		userdetails.username = $("#username").val();
		userdetails.password = $("#password").val();
		var username = check(userdetails.username, "username");
		var password = check(userdetails.password, "password");
		if (!username || !password) {
			return;
		}
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


