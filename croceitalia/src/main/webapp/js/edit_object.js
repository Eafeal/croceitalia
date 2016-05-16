function dettaglio(path,id)
	{
	var w = 800;
	var h = 735;
	var pw = Math.floor((screen.width-w)/2);
	var ph = 0; //Math.floor((screen.height-h)/2);
	var path = "/site/"+path+"/dettaglio/"+id;
	var opened = window.open(path,"mywin","toolbar=no,location=no,menubar=no,scrollbars=yes,resizable=yes,width="+ w +", height="+ h +", top="+ ph +", left="+ pw);
	opened.focus();
	}
	
function openPath(path)
	{
	var w = 800;
	var h = 735;
	var pw = Math.floor((screen.width-w)/2);
	var ph = 0; //Math.floor((screen.height-h)/2);
	var opened = window.open(path,"mywin","toolbar=no,location=no,menubar=no,scrollbars=no,resizable=yes,width="+ w +", height="+ h +", top="+ ph +", left="+ pw);
	opened.focus();
	}
	
function addObject(tipoOggetto_uid,uid)
	{
	var w = 800;
	var h = 735;
	var pw = Math.floor((screen.width-w)/2);
	var ph = 0; //Math.floor((screen.height-h)/2);
	var path = "/edit/newRel/step1/"+tipoOggetto_uid+"/"+uid;
	var opened = window.open(path,"mywin","toolbar=no,location=no,menubar=no,scrollbars=no,resizable=yes,width="+ w +", height="+ h +", top="+ ph +", left="+ pw);
	opened.focus();
	}
	
function modObject(path,id)
	{
	var w = 800;
	var h = 735;
	var pw = Math.floor((screen.width-w)/2);
	var ph = 0; //Math.floor((screen.height-h)/2);
	var path = "/edit/"+path+"/update/"+id;
	var opened = window.open(path,"mywin","toolbar=no,location=no,menubar=no,scrollbars=yes,resizable=yes,width="+ w +", height="+ h +", top="+ ph +", left="+ pw);
	opened.focus();
	}
	
function deleteObject(pageId,type,id)
	{
	if(confirm('Are you sure?'))
	   {
       	var w = 800;
		var h = 735;
		var pw = Math.floor((screen.width-w)/2);
		var ph = 0; //Math.floor((screen.height-h)/2);
		var path = "/edit/"+type+"/delete/"+id;
		window.document.location="/edit/"+type+"/delete/"+id+"/"+pageId;
	    }
	}

	