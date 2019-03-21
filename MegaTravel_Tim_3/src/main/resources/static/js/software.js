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
        
			console.log('There are ' + softwares.length + ' softwares in memory with certificate.');
			for (let software of softwares) 
			{
				insertWithCertificate(software);
				
			}
			
		}
    });
});

function insertWithCertificate(software)
{
	var tr = $('<tr><td>' + software.name +  '</td><td><button id="revoke'+software.id+'" class=\"btn btn-default	btn-md\" onclick=revokeCertificate('+software.id+')>Revoke</button></td></tr>');
	
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
	$("#btnRevoke").empty();
	$("#revokeDiv").hide();

	var reason=$("#reason").val();

	$.ajax({
		type : 'POST',
		url : "/api/certificates/revoke/"+id,
		success : function(data) {
				console.log('revoked');
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('greska');
		}
	});
}