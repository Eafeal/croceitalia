
<#assign sezione = "Gestione invii Sogei XML">
<#assign curr_gra = "">
<#assign curr_gen = "current">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">

<div id="wait">
  <div id="loading">
    Attendere prego
	Elaborazione in corso....
  </div>
</div>
<#assign almenoUnKO = false >
<#if utente.isLogged()>	
	<div class="loginform">
	<form method="post" action="/site/logout"> 
	<p style="margin:0"><input type="hidden" name="requestedUri" value="${requestedUri}" /></p>
	<fieldset style="border: 0; border-bottom: 1px solid #999; padding: 5px 0; margin-bottom: 30px; font-size: 16px; line-height: 50px;">
		Utente: <span style="display: inline-block; font-weight: bold; margin-right: 50px;">${utente.cognome} ${utente.nome}</span>
        Profilo: <span style="display: inline-block; font-weight: bold; margin-right: 50px;">${utente.getSoggettoUtente().getDescription()}</span>
        <input type="submit"  class="button" value="Esci" style="float: right;" />
	</fieldset>
	</form>
	</div>
	
	<#include "/${dominio}/includes/bottoniera.ftl">		
	
	<#include "/${dominio}/includes/messaggi.ftl">

	<div id="contentList">
			
        <div style="margin-bottom: 5px;">
          <img src="/img/icons/dir_opened_mini.gif" style="float:left; margin-right:10px; margin-top: 4px;" /><strong>${ fullPath }</strong>
		  <div style="float: right; font-size: 14px; border: 1px solid #e1e1e1; padding: 3px 10px; border-bottom: 0; border-radius: 5px 5px 0 0;">
            Ordina per: 
            <a href="javascript:esegui('nome')" style="padding: 0 20px;">Nome File Elaborato</a>
            <a href="javascript:esegui('data')">Data/ora Elaborazione Decr.</a>
          </div>
        </div>
		
		<#assign quanti = 0 >
		
		<#if numFiles == "0" >
		    <hr />
			<strong>Nessun file inviato<strong>
			<br>
		<#else>       
			<table border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px">
			<tr>
			<th title="Numuero Protocollo">Num Protocollo</th>
			<th align=left width=50%>Nome File xml generato o caricato</th>
			<th align=left width=25%>Ricevuta</th>
			<th align=left width=25%>Report errori</th>			
			<th align=left width=5%>Stato</th>
			<th align=left width=5%>Operazioni</th>
			</tr>
			
			<#assign lastIdFile = "" >
			<#assign lastIdParent = "" >
			<#assign IdProtocollo = "" >			
			<#assign fileStatus = "" >	
			<#assign lastFileStatus = "" >				
			<#assign primaRiga = true >
			<#assign trovatoPdf = false >			
			<#assign trovatoCsv = false >			
			<#assign nomeFileXLS = "XML Proprio">
			
	        <#list children as unFile >

			   <#assign idParent = unFile.getIdParent() >			

			    <#if lastIdParent != idParent>
					<!-- chiusura della riga precedente -->
					<#assign nomeFileXLS = "XML Proprio">
					<#if !primaRiga>
							<#if !trovatoPdf><td></td></#if>
							<#if !trovatoCsv><td></td></#if>
							<td title="${esito}"><strong><img src="/images/ico-${fileStatus}.png" /></strong></td>	
							<td width="20px"  >
							<#if fileStatus == "EL">
								<!--img  src="/img/icons/delete.png" style="float:left;clear:none;border:0" title="Cancella Elaborazione" onclick="esegui('del' ,'${lastIdParent}', '${fileStatus}')" />
								<a title="Scarica il file XML" href="/site/documento/get/${lastIdFile}" type="application/xml" ><img src="/img/icons/download.png" /></a-->
								<a title="Richiedi ricevuta" href="/site/croceitalia/richiesta/ricevuta/form/${lastIdFile}" type="application/xml" ><img src="/img/icons/icon-ricevuta.png" /></a>
							</#if>
						   </td>
						</tr>
						<#assign trovatoPdf = false >			
						<#assign trovatoCsv = false >	
					</#if>
					<#assign lastIdParent = idParent >
					<#assign esito = unFile.getEsito()>
					<#assign IdProtocollo = unFile.getIdProtocollo() >						
					<#assign primaRiga = false >
					<#assign quanti = quanti + 1 >
					<tr valign="top"><td title="${esito}">${IdProtocollo}</td>
				</#if>
		
			   <#assign nomeFile = unFile.getNomefile() >
			   <#assign ext = unFile.getExt() > 
			   <#assign urlGif = "/img/icons/" + unFile.getIco() >
			   <#assign alt = "clicca qui.." >
			   <#assign idFile = unFile.getId() >
			   <#assign urlv= "/site/documento/view/" + idFile />								
			   <#assign urlf= "/site/documento/get/" + idFile />
			   <#assign fileStatus = unFile.getState()>
			   

				<#if ext == "txt">
			    <#elseif ext == "xls">
					<#assign nomeFileXLS = nomeFile>
			    <#elseif ext == "xlsx">
					<#assign nomeFileXLS = nomeFile>
			    <#else>
					<#if ext == "pdf"><#assign trovatoPdf = true></#if>
					<#if ext == "csv"><#assign trovatoCsv = true></#if>					
					<td>
					    <#if ext == "xml">
							<a title="Scarica il file" href="${urlf}" type="${unFile.getMimeType()}" ><img src="/img/icons/xml.gif"></a>
							<span title="${unFile.getDescription()}" onClick="javascript:f_win_log('${urlv}')">${ nomeFile }</span><br>
							<em>(${nomeFileXLS})</em>
						</#if>
					    <#if ext == "pdf">
							<a title="Visualizza la ricevuta ${ nomeFile }" onClick="javascript:f_win_log('${urlv}')" ><img src="/img/icons/pdf.gif"></a>
							<a title="Scarica la ricevuta ${ nomeFile }" href="${urlf}" type="${unFile.getMimeType()}" ><img src="/img/icons/pdf.gif"></a><br>
							<em>(${ nomeFile })</em>
						</#if>
					    <#if ext == "csv">
							<a title="Visualizza il report degli errori" onClick="javascript:f_win_log('${urlv}')" ><img src="/img/icons/txt.gif"></a>
							<a title="Scarica il report degli errori ${ nomeFile }" href="${urlf}" type="${unFile.getMimeType()}" ><img src="/img/icons/txt.gif"></a><br>
							<em>(${ nomeFile })</em>
						</#if>						
					</td>
					<#assign lastIdFile = idFile >	
			    </#if>
			</#list>
			
			<!-- chiusura ultima riga -->
				<#if !trovatoPdf><td></td></#if>
				<#if !trovatoCsv><td></td></#if>
				<td title="${esito}"><strong><img src="/images/ico-${fileStatus}.png" /></strong></td>	
			    <td width="20px"  >
				   <!--img  src="/img/icons/delete.png"  style="float:left;clear:none;border:0" title="Cancella Elaborazione" onclick="esegui('del' ,'${lastIdParent}', '${fileStatus}')" /-->
				   <#if fileStatus == "EL">
					  <a title="Richiedi ricevuta" href="/site/croceitalia/richiesta/ricevuta/form/${lastIdFile}" type="application/xml" ><img src="/img/icons/icon-ricevuta.png" /></a>
					</#if>
			   </td>
		   </tr>
			</table>
			<hr />
			<strong>Totale file inviati: ${quanti}<strong
		
		</#if>

	</div>	
		
    </div>
	
	
</#if>
	<div class="clearfloat"></div>
 <br>
	<#include "/${dominio}/includes/footer_new.ftl">


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

		function zippa()
			{	
			document.location = "/site/croceitalia/documento/zippa/${utente.getUserId()}/${tipoFile}";
			}
			

		function esegui(operaz, idFile, stato)
			{
			if (operaz == "CSV")
				document.location = "/site/croceitalia/documento/list/CSV";					
			else if (operaz == "ExcelMPL")
				document.location = "/site/croceitalia/documento/list/ExcelMPL";				
			else if (operaz == "ExcelSSA")
				document.location = "/site/croceitalia/documento/list/ExcelSSA";
			else if (operaz == "nome")
				document.location = "/site/croceitalia/documento/listper/nome/${tipoFile}";
			else if (operaz == "data")
				document.location = "/site/croceitalia/documento/listper/data/${tipoFile}";
			else if(operaz == "del")
				{
				if (stato == 'OK')
					{
					if(!confirm("Confermi Eliminazione elaborazione in stato OK?")) return
					}
				document.location = "/site/croceitalia/documento/delete/${tipoFile}/" + idFile;					
				}
			else if (operaz == "list")
				document.location = "/site/croceitalia/documento/list/${tipoFile}/" + idFile;
			else if (operaz == "unzip")
			    document.location = "/site/croceitalia/documento/unzip/${tipoFile}/" + idFile;
			else if (operaz == "get")  
			    openPath("/site/croceitalia/documento/get/" + idFile);
			else if (operaz == "edit")
			    document.location = "/site/croceitalia/documento/update/" + idFile;
			}
		
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

		if ("CSV"=="${tipoFile}" && estensione.toUpperCase() == "CSV")
			{
			showLoader();
			return true;
			}			
		if ("CSV"!="${tipoFile}" && (estensione.toUpperCase()=="XLS"||estensione.toUpperCase()=="XLSX"))
			{
			showLoader();
			return true;
			}	

		alert("Specificare un file in formato excel 2003, CSV o ZIP");	
		return false;
		}
			
		function js_controllo_allegato_2()
		{

		path=document.ZipUploadForm.file.value;

		if (path=="")
			{
			alert("Specificare il file da elaborare.");
			return false;
			}
			
		posizione_punto=path.lastIndexOf(".");
		lunghezza_stringa=path.length;
		estensione=path.substring(posizione_punto+1,lunghezza_stringa);

		if (estensione.toUpperCase()=="ZIP")
			{
			showLoader();
			return true;
			}	

		alert("Specificare un file in formato ZIP");	
		return false;
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
			document.formNewDir.action = "/site/croceitalia/documento/nuovaCartella/"+idParent+"/"+folderName;
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
			
		function showLoader()
			{
			document.getElementById("wait").style.display = "block";
			}

		function hideLoader()
			{
			document.getElementById("wait").style.display = "none";
			}
			
		</script>
