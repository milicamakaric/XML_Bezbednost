$(document).ready(function()
{
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
	var tr = $('<tr><td>' + software.name +  '</td><td></td></tr>');
	
	$('#lista').append(tr);
}

function insertWithoutCertificate(software)
{
	var tr = $('<tr><td>' + software.name +  '</td><td><button id="add'+software.id+'" onclick=addCertificate('+software.id+')>Add certicicate</button></td></tr>');
	
	$('#lista').append(tr);
}

function addCertificate(id)
{
	window.location.href="certificate.html?id=" + id;
}