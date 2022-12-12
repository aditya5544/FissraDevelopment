$(window).on("load", function() {

});

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

	});
});


