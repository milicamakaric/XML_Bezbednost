$(document).on('submit','#formRegistration',function(e){
	e.preventDefault();	
		console.log('usao u reg');
		
		var newUser={
							name : $('#nameUser').val(),
							surname : $('#surnameUser').val(),
							email : $('#mailUser').val(),
							password : $('#passwordUser').val(),
					}
				
		userData= JSON.stringify(newUser);			
		console.log('user je ' + userData);

		$.ajax({
				type : 'POST',
				url : "/api/users/registration",
				contentType : "application/json",
				data: userData,
				dataType : 'json',
				success : function(value) {
						if(value.email=="error"){	
							 alert("Mail is already taken.");
						}else{
							alert('You have been successfully registered.');
							window.location.href="softwares.html";
						}
					},
				error: function(XMLHttpRequest, textStatus, errorThrown){
						alert('greska');
					}
		});
		
	});
