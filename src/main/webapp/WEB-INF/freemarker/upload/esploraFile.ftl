<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
		
		<link rel="stylesheet"    href="/css/main.css" />
		<link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon" />
		
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
		<meta name="layout" content="main"/>
		
		<#if title?? ><title>${title}</title></#if>
		<script language="JavaScript" type="text/javascript" src="/js/bubbleSort.js"></script>
		
		<script>
		function openPath(path)
			{
			var w = 800;
			var h = 735;
			var pw = Math.floor((screen.width-w)/2);
			var ph = 0; //Math.floor((screen.height-h)/2);
			var opened = window.open(path,"mywin","toolbar=no,location=no,menubar=no,scrollbars=no,resizable=yes,width="+ w +", height="+ h +", top="+ ph +", left="+ pw);
			opened.focus();
			}
	
		function esegui(operaz, idFile)
			{
			if(operaz == "del")
				{
				if(confirm("Confermi Eliminazione?"))
					{
					document.location = "/edit/documento/delete/" + idFile;
					}
				}
			else if (operaz == "list")
				document.location = "/edit/documento/list/" + idFile;
			else if (operaz == "unzip")
			    document.location = "/edit/documento/unzip/" + idFile;
			else if (operaz == "get")  
			    openPath("/edit/documento/get/" + idFile);
			else if (operaz == "edit")
			    document.location = "/edit/documento/update/" + idFile;
			}
		
		function js_controllo_allegato()
			{
			var nome_file = document.UploadForm.file.value;
			//alert("Confermi caricamento di "+nome_file);
			if (nome_file == "")
				{
				alert("Scegli un file da trasferire!");
				return false;
				}
			return true;
			}
			
		function nuovaDir()
			{
			var idParent = document.formNewDir.idParent.value;
			var folderName = document.formNewDir.folderName.value;
			if(folderName == "")
				{
				alert("Immettere un nome per la nuova cartella");
				document.formNewDir.folderName.focus();
				return;
				}
			document.formNewDir.action = "/edit/documento/nuovaCartella/"+idParent+"/"+folderName;
			document.formNewDir.submit();
			}
			
		function  f_win_log(url)
			{

			//document.getElementById("logfile").innerHtml = url;
			
			var w;
			var h;
			var pw;
			var ph;


				w = 870;
				h = 600;
				pw = Math.floor((screen.width-w)/2);
				ph = 0;
				
				new_win=window.open(url,"Errori", "location=0,menubar=0,resizable=1,scrollbars, width="+ w +", height="+ h +", top="+ ph +", left="+ pw);

				new_win.focus();
			}		
						
		
		</script>
    </head>
    
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>GESTIONE FILE</h1>




<div id="container">
    <hr />
    <br />
    
<#include "errore.ftl">
   <br />

<form id="UploadForm" name="UploadForm" method="post" 
	action="/edit/documento/upload" 
	enctype="multipart/form-data" 
	onSubmit="javascript:return js_controllo_allegato()">

    <input type="hidden" id="parent_id" name="parent_id" value="${cartellaCorrente.getId() }" >
	<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right" valign="top">
				<input type="file"   id="file" name="file"         />
			</td>
		</tr>
		<tr class="prop">
			<td valign="top" class="name">
				<label for="title">Title</label>
			</td>
			<td valign="top" class="value ">
				<input type="text" id="title" name="title" size="80" maxlength="255" value="Title"  />
			</td>
		</tr>
		<tr class="prop">
			<td valign="top" class="name">
				<label for="description">Description</label>
			</td>
			<td valign="top" class="value ">
				<input type="text" id="description" name="description" size="80" maxlength="255" value="describe the object"  />
			</td>
		</tr>
		<tr class="prop">
			<td valign="top" class="name">
				<label for="autore">Author</label>
			</td>
			<td valign="top" class="value ">
				<input type="text" id="autore" name="autore" size="30" maxlength="45"  value="Author" />
			</td>
		</tr>
        <tr> 
          <td align="right" class="azzurro">State</td>
          <td class="azzurro"> 
          	<#assign state="WIP" />
          	<#include "/edit/states.ftl"  />
          </td>
        </tr>
		<tr> 
			<td valign="top" class="value ">
				<input type="submit"  value="Carica nuovo file >"          />
			</td>
		</tr>
	</table>
</form>
<hr />
<form id="formNewDir" name="formNewDir" action="" method="GET">
    <input type="hidden"    id="idParent"    name="idParent"    value="${cartellaCorrente.getId() }"   />
	<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr> 
			<td align="right" class="azzurro">    
				<label>Nome Cartella:</label>
				<input type="text"    id="folderName"  name="folderName"  value="Nome Nuova Cartella" onclick="this.value=''" />
			</td>
		</tr>
		<tr>
			<td>
				<input type="button"  value="Crea nuova cartella >" onclick="nuovaDir()" />
			</td>
		</tr>
	</table>
</form>
<hr />

<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right" valign="top">
  <tr>
    <td width="450" align="right">


    <div id="esplora">
        <br />
        Cartella Attuale (${numFiles} files):<br />
        <br />
        <#if cartellaCorrente.isFirstLevel() == false >
            <a href="javascript:void(0)" >
                <img class="del" src="/img/icons/goUp.gif" alt="Livello Superiore"  onClick="esegui('list','${ cartellaCorrente.getIdParent() }')" />
            </a>
        </#if>
        <img src="/img/icons/dir_opened_mini.gif" /><strong>${ fullPath }</strong>
        <br /><br />
        <hr />
        <br />
        
        <table border="1" >
	        <#list children as unFile >
	           <tr>
			   <#assign nomeFile = unFile.getNomefile() >
			   <#assign ext = unFile.getExt() > 
			   <#assign urlGif = "/img/icons/" + unFile.getIco() >
			   <#assign alt = "clicca qui.." >
			   <#assign idFile = unFile.getId() >
			   <#assign urlv= "/site/documento/view/" + idFile />								
			   <#assign urlf= "/site/documento/get/" + idFile />
			   
			   <td>${ idFile }</td>
			   
		       <#if unFile.isFolder() >
		           <#assign metodo = "list" >
		       <#else>
		           <#assign metodo = "get" >
		       </#if>
					<a href="javascript:void(0)">
					   <td width="100%">
					   		<!--a>
						   <img src="${ urlGif }"  alt="esegui" onclick="esegui('${ metodo }','${ idFile }')" /> 
		                   <span onClick="esegui('${ metodo }','${ idFile }')">${ nomeFile }</span>
		                   </a--> 

						   <#if unFile.isFolder() >
							   <img src="${ urlGif }"  alt="esegui" onclick="esegui('${ metodo }','${ idFile }')" /> 
							   <span onClick="esegui('${ metodo }','${ idFile }')">${ nomeFile }</span>
						   <#else>
								<a title="Scarica il file" href="${urlf}" type="${unFile.getMimeType()}" ><img src="${ urlGif }"></a>
								<span title="${unFile.getDescription()}" onClick="javascript:f_win_log('${urlv}')">${ nomeFile }</span>	
						   </#if>							
	                   </td>
					   <td width="20px"  >
					       <img  src="/img/edit/mod.gif"   alt="Modifica" onclick="esegui('edit','${ idFile }')" />
					   </td>
					   <td width="20px"  >
					       <img  src="/img/skin/database_delete.png"  style="float:left;clear:none;border:0" alt="Cancella" onclick="esegui('del' ,'${ idFile }')" />
					   </td>
					   <td width="20px"  >
						   <#if ext == "zip">
						       <img src="/img/icons/zip_mini.gif" style="float:left;clear:none;border:0" alt="unzip" onclick="esegui('unzip','${ idFile }')" />
						   <#else>
						   		&nbsp;
						   </#if>
					   	</td>
					   <td width="20px"  >
	                       &nbsp;
					   </td>
				   </a>
				</tr>
			</#list>
		</table>
    </div>
    
    
    </td>
  </tr>
</table>

</div>
	
</body>
</html>
