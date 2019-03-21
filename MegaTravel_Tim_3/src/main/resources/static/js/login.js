
$(document).on('submit','.loginForm',function(e){
	e.preventDefault();	
	
	var mail =  $('#mail').val();
	var password = $('#password').val();
				 
				$.ajax({
					type : 'POST',
					url : "/api/users/login?mail="+mail+"&password="+password,
					success : function(value) {
						if(value.email == 'error'){
							alert('Password is not valid.');
						}else{
							
							sessionStorage.setItem('logged',JSON.stringify(value));
							alert('You have been successfully logged in.');
							window.location.href = "softwares.html";
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						alert('error');
					}
				});
	
	});



