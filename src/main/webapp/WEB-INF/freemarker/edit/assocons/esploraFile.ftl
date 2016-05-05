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
			if (operaz == "list")
				document.location = "/edit/assocons/esploraCartella//" + idFile;
			else if (operaz == "get")  
			    openPath("/edit/assocons/generaReportAvcp/" + idFile);
			}
		
		</script>
    </head>
    
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
        </div>
        <div class="body">
            <h1>SCEGLI UN FILE</h1>
			<div id="container">
			    <hr />
			    <br />
				<#include "errore.ftl">
				<br />
				<hr />
				<div id="esplora">
			        <br />
			        Cartella Attuale (${numFiles} files):<br />
			        <br />
			        <#if cartellaCorrente.isFirstLevel() == false >
			            <a href="javascript:void(0)" >
			                <img class="del" src="/img/icons/goUp.gif" alt="Livello Superiore"  onClick="esegui('list','${ cartellaCorrente.getIdParent() }')" />
			            </a>
			        </#if>
			        <img src="/img/icons/dir_opened_mini.gif" /><strong> ${ fullPath }</strong>
			        <br /><br />
			        <hr />
			        <br />
			        
			        <table border="0" >
				        <#list children as unFile >
				           <tr>
						   <#assign nomeFile = unFile.getNomefile() >
						   <#assign ext = unFile.getExt() > 
						   <#assign urlGif = "/img/icons/" + unFile.getIco() >
						   <#assign alt = "clicca qui.." >
						   <#assign idFile = unFile.getId() >
						   
					       <#if unFile.isFolder() >
					           <#assign metodo = "list" >
					       <#else>
					           <#assign metodo = "get" >
					       </#if>
								<a href="javascript:void(0)">
								   <td width="600px">
								   		<a>
									   <img src="${ urlGif }"  alt="esegui" onclick="esegui('${ metodo }','${ idFile }')" /> 
					                   <span onClick="esegui('${ metodo }','${ idFile }')">${ nomeFile }</span>
					                   </a> 
				                   </td>
							   </a>
							</tr>
						</#list>
					</table>
			    </div>
			</div>
		</body>
	</html>
