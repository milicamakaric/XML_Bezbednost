
$(document).on('submit','.logovanje',function(e){
	e.preventDefault();	
	
	var mail =  $('#mail').val();
	var password = $('#password').val();
				 
				$.ajax({
					type : 'GET',
					url : "/api/users/login?mail="+mail+"&password="+password,
					success : function(value) {
						if(value.email == 'error'){
							alert('Password is not valid.');
						}else{
							sessionStorage.setItem('logged',JSON.stringify(value));
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						alert('error');
					}
				});
	
	});



