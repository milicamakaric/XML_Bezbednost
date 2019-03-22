$(document).ready(function()
{
	$("#revokeDiv").hide();
	$("#connectDiv").hide();
	
	$.ajax({
        type: 'GET',
        url: '/api/softwares/getNotCertificated',
        success: function (softwares)
		{
        
			console.log('There are ' + softwares.length + ' softwares in memory without certificate.');
			for (let software of softwares) 
			{
				insertWithoutCertificate(software);
				
			}
			
		}
    });
	
	$.ajax({
        type: 'GET',
        url: '/api/softwares/getCertificated',
        success: function (softwares)
		{
        
			console.log('There are ' + softwares.length + ' softwares in memory with revoked certificate.');
			for (let software of softwares) 
			{
				insertWithCertificate(software, 1);
				
			}
			
		}
    });

	$.ajax({
        type: 'GET',
        url: '/api/softwares/getCertificatedNR',
        success: function (softwares)
		{
        
        	console.log('There are ' + softwares.length + ' softwares in memory with notrevoked certificate.');
			for (let software of softwares) 
			{
				insertWithCertificate(software, 2);
				
			}
			
		}
    });
});

function insertWithCertificate(software, number)
{
	var tr="";
	console.log('Usao u insertWithCert '+ number)
	if(number == 1){
		console.log('num je 1');
		tr = $('<tr><td>' + software.name +  '</td><td></td><td><button id="validate'+software.id+'" class=\"btn btn-default	btn-md\" onclick=validateCertificate('+software.id+')>Validate</button></td></tr>');
	}else{
		console.log('num je 2');
		tr = $('<tr><td>' + software.name +  '</td><td><button id="revoke'+software.id+'" class=\"btn btn-default	btn-md\" onclick=revokeCertificate('+software.id+')>Revoke</button></td><td><button id="connect'+software.id+'" class=\"btn btn-default	btn-md\" onclick=connectCertificate('+software.id+')>Connect</button></td><td><button id="validate'+software.id+'" class=\"btn btn-default	btn-md\" onclick=validateCertificate('+software.id+')>Validate</button></td></tr>');
	}
	
	$('#lista').append(tr);
}

function insertWithoutCertificate(software)
{
	var tr = $('<tr><td>' + software.name +  '</td><td><button id="add'+software.id+'" class=\"btn btn-default	btn-md\" onclick=addCertificate('+software.id+')>Add certificate</button></td></tr>');
	
	$('#lista').append(tr);
}

function addCertificate(id)
{
	window.location.href="certificate.html?id=" + id;
}
function revokeCertificate(id)
{
	$("#btnRevoke").empty();
	$("#btnRevoke").append('<button type=\"button\" id=\"confirmReason\"onclick=revocation('+id+') class=\"btn btn-default btn-md\">Confirm</button>');
	
	$("#revokeDiv").show();
	$("#validateDiv").hide();
}
function revocation(id)
{
	console.log('revocation')
	$("#btnRevoke").empty();
	$("#revokeDiv").hide();
	var revokeButton = "revoke"+id;
	var reason=$("#reason").val();
	console.log(reason);
	console.log(id);
	console.log(revokeButton);
	
	
	$.ajax({
		type : 'POST',
		url : "/api/certificates/revoke/"+id+"/"+reason,
		success : function(data) {
				console.log('revoked');
				$("#"+revokeButton).hide();
				$("#connect"+id).hide();
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('greska');
		}
	});
}
function connectCertificate(id){
	$("#validateDiv").hide();
	$.ajax({
        type: 'GET',
        url: '/api/softwares/getNotConnected/'+id,
        success: function (softwares)
		{
        	$("#selectSoft").empty();
			for (let software of softwares) 
			{
				fillSelectNotConnected(software,id);
				
			}
			 $("#connectDiv").show();
			 $("#btnConnect").empty();
			 $("#btnConnect").append('<button type=\"button\" id=\"confirmCommunication\" onclick=confirmCommunication('+id+') class=\"btn btn-default btn-md\">Confirm communication</button>');
			
		}
    });

	
}
function fillSelectNotConnected(software,id){
	 console.log('usao ovdje');
	 $("#selectSoft").append("<option  value=\""+software.id+"\">"+software.name+"</option>");
	 		
}
function confirmCommunication(id){
	var idSoftware = $("#selectSoft").val();
	$.ajax({
		type : 'POST',
		url : "/api/softwares/confirmCommunication/"+id+"/idSoftware/"+idSoftware,
		success : function(data) {
				console.log('Communication has successfully changed');
				$("#connectDiv").hide();
				
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('greska');
		}
	});
	
}

function validateCertificate(id)
{
	$.ajax({
		type : 'GET',
		url : "/api/certificates/validate/"+id,
		success : function(data) {
			$("#validateDiv").show();
			$('#validationLabel').text('Validation - soft' + id + ':');
			$('#validation').val(data);
			if(data == "The certificate has been revoked.")
			{
				$.ajax({
					type : 'GET',
					url : "/api/certificates/revocationMessage/"+id,
					success : function(data) {

						$("#validationRevokedDiv").show();
						$("#validation_revoked").val(data);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						alert('greska kod validacije sertifikata');
					}
				});
			}
			else
			{
				$("#validationRevokedDiv").hide();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('greska kod validacije sertifikata');
		}
	});
}