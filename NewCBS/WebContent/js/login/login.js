$('#logInButton').click(function(){
	var tips = $('#login_alert');
	var userId = $("#userId");
	var passwd = $("#passwd");
	var allFields = $( [] ).add( userId ).add( passwd );
	var bValid = true;
	
	allFields.removeClass( "ui-state-error" );
	
	bValid = bValid && checkLength( userId, "user ID", 3, 16, tips );
	bValid = bValid && checkLength( passwd, "Password", 3, 80, tips );
	
	bValid = bValid && checkRegexp( userId, /^[0-9]{3}-[0-9]{3}$/, "User ID must enter as 999-999.", tips );
	
	if(bValid){
		$('#loginFields').hide();
		$('#loginProgress').load('processing.html');
		$.get('login.action',
			{userId:userId.val(),passwd:passwd.val()},
			function(data){
				if(data.indexOf("Log in")==-1){
					$("#loginForm").submit();
				}
				else{
					updateTips('Invalid UserId or Password',tips);
				}
			}
		);
	}
	
});