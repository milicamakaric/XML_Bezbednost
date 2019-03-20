$(document).on('submit','#formRegistration',function(e){
	e.preventDefault();	
	
		var newUser={
							name : $('#nameUser').val(),
							lastName : $('#surnameUser').val(),
							email : $('#mailUser').val(),
							password : $('#passwordUser').val(),
					}
				
		userData= JSON.stringify(newUser);			
				 
		$.ajax({
				type : 'POST',
				url : "/api/users/registration",
				contentType : "application/json",
					data: userData,
					dataType : 'json',
					success : function(value) {
						if(!value){	
							 alert("Mail is already taken.");
						}else{
							alert('You have been successfully registered.');
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						alert('greska');
					}
		});
		
	});
