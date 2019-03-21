$(document).ready(function()
{
	$("#revokeDiv").hide();
	
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
		tr = $('<tr><td>' + software.name +  '</td><td></td></tr>');
	}else{
		console.log('num je 2');
		tr = $('<tr><td>' + software.name +  '</td><td><button id="revoke'+software.id+'" class=\"btn btn-default	btn-md\" onclick=revokeCertificate('+software.id+')>Revoke</button></td></tr>');
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
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('greska');
		}
	});
}