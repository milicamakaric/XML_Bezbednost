
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
							preusmeri();
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						alert('error');
					}
				});
	
	});

function preusmeri()
{
	$.ajax({
        type: 'GET',
        url: '/api/softwares/getSelfSigned',
        success: function (exists)
		{
        	if(exists)
        		window.location.href = "softwares.html";
        	else
        		{
        		alert("You need to create self signed certificate.");
        		window.location.href = "certificate.html?id=self";
        		}
			
			
			
		}
    });

}



