<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">

<!--  Version: Multiflex-3 Update-4 / Overview             -->
<!--  Date:    December 11, 2006                           -->
<!--  Author:  Wolfgang                                    -->
<!--  License: Fully open source without restrictions.     -->
<!--           Please keep footer credits with a link to   -->
<!--           Wolfgang (www.1-2-3-4.info). Thank you!     -->

<head>
<meta name="view"       content="passo4-indiceRequest.ftl"      />

<#include "/${dominio}/includes/header.ftl">
<#include "/${dominio}/includes/sidebar.ftl">

</head>

<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<body>
	<!-- Main Page Container -->
	<div class="page-container">
	    <!-- Page Header -->
	    <#include "/${dominio}/includes/pageHeader.ftl">
	
	    <!-- B. MAIN -->
	    <div class="main">
	    
		<#list pagina.getChildren() as oggetto>
		    <#include "/${dominio}/${oggetto.getTemplate()}" />
		</#list>
	
	<div class="main-content" >
		<p><span style="float:none;display:inline" >&nbsp;</span></p>
		<h1>Primo Passo Costruzione File Indice</h1>
		<p><h2>Invia il file excel che hai compilato</h2></p>
		<h2></h2>
	</div>


		
		<div class="main-content" >		
			<form id="UploadForm" name="UploadForm" method="post" 
				action="/site/croceitalia/passo4/upload" 
				enctype="multipart/form-data" 
				onSubmit="javascript:return js_controllo_allegato()">
			    <input type="hidden" id="parent_id" name="parent_id" value="${cartellaCorrente.getId() }" >
		    	<#if ErrorMsg?? >
		    		<h1>Errore: ${ErrorMsg}</h1>
		    	</#if>
		    	<table>
		    		<!--tr><td colspan="2" >&nbsp;</td></tr>
					<tr><td colspan="2" >&nbsp;</td></tr>
		    		<tr>
		    			<td><label class="field" for="EntePubblicatore" >Ente Pubblicatore</label></td>
	        			<td><input class="field" type="text" id="EntePubblicatore" name="EntePubblicatore" size="80" maxlength="255" value="Ente Pubblicatore"  /></td>
	      			</tr>
	      			<tr>
	      				<td><label class="field" for="autore">Anno Riferimento</label></td>
	      				<td><input class="field" type="text" id="AnnoRiferimento" name="AnnoRiferimento" size="30" maxlength="45"  value="2013" /></td>
	      			</tr-->
	      			<tr>
	      				<td><label  class="field" for="file">File Excel</label></td>
	      				<td><input class="field" type="file" id="file" name="file" accept="application/vnd.ms-excel"/></td>
	      			</tr>
					<tr><td colspan="2" >&nbsp;</td></tr>
					<tr><td colspan="2" >&nbsp;</td></tr>
	      			<tr>
	      				<td>&nbsp;</td>
	      				<td><input type="submit" class="field" value="Invia il modello compilato > "  /></td>
	      			</tr>
					<tr><td colspan="2" >&nbsp;</td></tr>
					<tr><td colspan="2" >&nbsp;</td></tr>
	      		</table>
			</form>
    	</div>
    </div>
    
    <#include "/${dominio}/includes/footer.ftl">
    
</div> 
</body>
</html>
<script>
function js_controllo_allegato()
{

path=document.UploadForm.file.value;

if (path=="")
	{
	alert("Specificare il file da elaborare.");
	return false;
	}
	
posizione_punto=path.lastIndexOf(".");
lunghezza_stringa=path.length;
estensione=path.substring(posizione_punto+1,lunghezza_stringa);

if (estensione.toUpperCase()=="XLS")
	{
	return true;
	}	

alert("Specificare un file in formato excel 2003.");	
return false;
}
</script>



   