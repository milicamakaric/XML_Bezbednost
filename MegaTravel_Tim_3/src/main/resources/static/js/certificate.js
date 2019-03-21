$(document).ready(function(){
	var url = window.location.href;
	var splitted = url.split('?');
	
    var splitted2 = splitted[1].split('=');
    id_subject = splitted2[1];
	
    
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 

	today = yyyy+'-'+mm+'-'+dd;
	
	$('input[id=startCertificate]').attr("min", today);
	$('input[id=endCertificate]').attr("min", today);
	
	$('input[id=startCertificate]').on('change', function(){
		
		var datum = $('input[id=startCertificate]').val();
		$('input[id=endCertificate]').attr("min", datum);
		
		var date_start = new Date(datum);
		var date_end = new Date($('input[id=endCertificate]').val());
		
		if(date_end < date_start)
		{
			$('input[id=endCertificate]').val(datum);
		}
	});
	
	$.ajax({
		type : 'GET',
		url : "/api/users/user",
		success : function(user) {
			if(user==null || user=="")
				{
				alert('No user is logged in!');
				}
			else
				id_issuer = user.id;
		},
		error: function(data){
			alert('error');
		}
	});
	
	$('#formCertificate').submit(function(event) {
		event.preventDefault();
		start_date = $('input[id=startCertificate]').val();
		end_date = $('input[id=endCertificate]').val();
		
		if(id_subject != "self")
			{
				$.ajax({
					type : 'POST',
					url : "/api/certificates/create/" + id_subject + "/" + id_issuer + "/" + start_date + "/" + end_date,
					contentType: 'application/json',
					success : function(certificate) {
						alert("Successfully created certificate for software:" + id_subject + " !");
						window.location.href="softwares.html";
					},
					error: function(certificate){
						alert('error');
					}
				});
			}
		else
			{
			
				$.ajax({
					type : 'POST',
					url : "/api/certificates/createSelfSigned/" + id_issuer + "/" + start_date + "/" + end_date,
					contentType: 'application/json',
					success : function(certificate) {
						alert("Successfully created self signed certificate!");
						window.location.href="softwares.html";
					},
					error: function(certificate){
						alert('error');
					}
				});
			
			
			
			}
	});
	
});
