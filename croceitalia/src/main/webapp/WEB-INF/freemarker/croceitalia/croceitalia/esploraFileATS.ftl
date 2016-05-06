
<#assign sezione = "Generazione dataset XML">
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
<#assign quanti = 0 >

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
	
	<#assign classCSV = "" >
	<#assign classMPL = "" >
	<#assign classSSA = "last" >
	
	<#if tipoFile == "CSV" >
		<#assign classCSV = "selected" >
	</#if>
	<#if tipoFile == "ExcelMPL" >
		<#assign classMPL = "selected" >
	</#if>		
	<#if tipoFile == "ExcelSSA" >
		<#assign classSSA = "selected" >
	</#if>
	<center>
    <ul class="menu-in-page">
      <li class="${classCSV}"><a href="javascript:esegui('CSV')">Prestazioni ATS (csv)</a></li>
	  <li class="${classMPL}"><a href="javascript:esegui('ExcelMPL')">Prestazioni MMG/PDF</a></li>
	  <li class="${classSSA}"><a href="javascript:esegui('ExcelATS')">Prestazioni SSA/ATS (excel)</a></li>
    </ul>
	</center>
		
	<br>
    <fieldset style="border: 1px solid #ababab; border-radius: 3px;">
	<legend>Seleziona dal tuo computer il file da elaborare:</legend>
	
	<form id="UploadForm" name="UploadForm" method="post" style="display: block; padding: 0 8px; width: 51%; margin-bottom:15px" 
		action="/site/croceitalia/passo2/upload" 
		enctype="multipart/form-data" 
		onSubmit="javascript:return js_controllo_allegato()">
		<input type="hidden" id="tipoFile" name="tipoFile" value="${tipoFile}"/>
		<input type="hidden" id="autore" name="autore" value="${utente.getUserId()}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
		<input class="field" type="file" id="file" name="file" />
		<input type="submit" class="inviaXLS" value="Invia il file da elaborare"/>
	</form>	
	<form id="ZipUploadForm" name="ZipUploadForm" method="post" style="display: block; padding: 0 8px; width: 51%;"  
		action="/site/croceitalia/documento/zipUpload" 
		enctype="multipart/form-data" 
		onSubmit="javascript:return js_controllo_allegato_2()">
		<input type="hidden" id="tipoFile" name="tipoFile" value="${tipoFile}"/>		
		<input type="hidden" id="autore" name="autore" value="${utente.getUserId()}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
		<input class="field" type="file" id="file" name="file" />
		<input type="submit" class="inviaZip" value="Invia il file da elaborare"/>
	</form>		
    </fieldset>	
	<input type="button" class="inviaZip" value="Zippa e scarica i file elaborati" onClick="zippa()"/>	
	<input type="button" class="inviaZip" value="Cancella KO" onClick="cancellaKO()"/>	
	<input type="button" class="inviaZip" value="Cancella TUTTO" onClick="cancellaTutto()"/>		
	
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
			<strong>Nessun file elaborato<strong>
			<br>
		<#else>       
			<table border="0" cellpadding="0" cellspacing="0" style="margin-bottom:5px">
			<tr>
			<th title="Id Elaborazione">Id. El.</th>
			<th align=left width=45%>File elaborato</th>
			<th align=left width=45%>File generato</th>
			<th align=left width=5%>Stato</th>
			<th align=left width=5%>Operazioni</th>
			</tr>
			
			<#assign lastIdFile = "" >
			<#assign lastIdParent = "" >			
			<#assign fileStatus = "" >	
			<#assign lastFileStatus = "" >				
			<#assign primaRiga = true >
		

			   
	        <#list children as unFile >
			
			   <#assign nomeFile = unFile.getNomefile() >
			   <#assign ext = unFile.getExt() > 
			   <#assign urlGif = "/img/icons/" + unFile.getIco() >
			   <#assign alt = "clicca qui.." >
			   <#assign idFile = unFile.getId() >
			   <#assign idParent = unFile.getIdParent() >
			   <#assign urlv= "/site/documento/view/" + idFile />								
			   <#assign urlf= "/site/documento/get/" + idFile />
			   <#assign fileStatus = unFile.getState()>

			    <#if lastIdParent != idParent>
					<!-- chiusura della riga precedente -->
					<#if !primaRiga>
						<#if fileStatus == "KO">
							<#assign almenoUnKO = true >
						</#if>					
						<#if lastFileStatus == "WFB">
						   <td></td>
						</#if>
						   <td><strong>${lastFileStatus}</strong></td>	
						   <td width="20px"  >
							   <img  src="/img/icons/delete.png"  style="float:left;clear:none;border:0" title="Cancella Elaborazione" onclick="esegui('del' ,'${lastIdParent}', '${lastFileStatus}')" />
							   <#if lastFileStatus == "OK">
								  &nbsp;&nbsp;<a title="Scarica il file XML" href="/site/documento/get/${lastIdFile}" type="application/xml" ><img src="/img/icons/download.png" /></a>
							   </#if>
						   </td>
					   </tr>
					</#if>
					<#assign lastFileStatus = fileStatus>					
					<#assign lastIdParent = idParent >
					<#assign primaRiga = false >
					<#assign quanti = quanti + 1 >
					<tr><td>${idParent}</td>
				</#if>

				<#if fileStatus == "OK" && ext == "txt">
			    <#else>
					<td>
						<a title="Scarica il file" href="${urlf}" type="${unFile.getMimeType()}" ><img src="${ urlGif }"></a>
						<span title="${unFile.getDescription()}" onClick="javascript:f_win_log('${urlv}')">${ nomeFile }</span>
					</td>
					<#assign lastIdFile = idFile >	
			   </#if>
			</#list>
			<!-- chiusura ultima riga -->

			<#if lastFileStatus == "WFB">
			   <td></td>
			</#if>
			   <td><strong>${lastFileStatus}</strong></td>	
			   <td width="20px"  >
				   <img  src="/img/icons/delete.png"  style="float:left;clear:none;border:0" title="Cancella Elaborazione" onclick="esegui('del' ,'${lastIdParent}', '${lastFileStatus}')" />
				   <#if lastFileStatus == "OK">
					  &nbsp;&nbsp;<a title="Scarica il file XML" href="/site/documento/get/${lastIdFile}" type="application/xml" ><img src="/img/icons/download.png" /></a>
				   </#if>
			   </td>
		   </tr>
		</table>
        <hr />
		<strong>Totale file elaborati: ${quanti}<strong>
		</#if>
    </div>
	<#if utente.isProva()>	
	<div>
	<hr>
	<strong>Di seguito è possibile scaricare i modelli dei file excel da compilare e alcuni esempi precompilato</strong>
	<br><br>
	<em>
	<#if tipoFile == "CSV" >
		<a href="/modelliExcel/PropostaFlusso730CSVperAslLombardia.xls">Proposta tracciato flusso CSV per esportazioni dati da gestionali interni</a><br>
		<a href="/modelliExcel/Esempio_Flusso_730_CSV.csv">Esempio di flusso CSV precompilato</a><br>
	</#if>
	<#if tipoFile == "ExcelMPL" >
		<a href="/modelliExcel/Modello_MMG_PDF_Lombardia.xls">Modello MMG_PDF distribuito da regione Lombardia</a><br>
		<a href="/modelliExcel/Modello_MMG_PDF_Lombardia_Esempio.xls">Modello MMG_PDF distribuito da regione Lombardia (esempio precompilato)</a><br>
	</#if>		
	<#if tipoFile == "ExcelSSA" >
		<a href="/modelliExcel/Modello_STRUTTURE_SSA.xls">Modello excel predisposto per ATS e altre strutture sanitarie (es RSA)</a><br>
		<a href="/modelliExcel/Modello_STRUTTURE_SSA_Esempio.xls">Modello excel predisposto per ATS e altre strutture sanitarie (esempio precompilato)</a><br>
	</#if>
	
	</em>
	<br>
	</div>
	</#if>		
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
			//showLoader();
			document.location = "/site/croceitalia/documento/zippa/${utente.getUserId()}/${tipoFile}";
			}
			
		function cancellaKO()
			{
			<#if almenoUnKO>			
				if(!confirm("Confermi Eliminazione di tutte le elaborazioni KO?")) return
				showLoader();				
				document.location = "/site/croceitalia/documento/deleteAllKO/${utente.getUserId()}/${tipoFile}";
			<#else>
				alert("Nulla da cancellare");		
			</#if>
			}

		function cancellaTutto()
			{
			<#if quanti != 0>			
				if(!confirm("Confermi Eliminazione di tutte le elaborazioni?")) return
				if(!confirm("Ma sei proprio sicuro?")) return
				if(!confirm("Sicuro, sicuro?")) return
				showLoader();
				document.location = "/site/croceitalia/documento/deleteAll/${utente.getUserId()}/${tipoFile}";
			<#else>
				alert("Nulla da cancellare");		
			</#if>
			}				
			
		function esegui(operaz, idFile, stato)
			{
			if (operaz == "CSV")
				document.location = "/site/croceitalia/documento/list/CSV";					
			else if (operaz == "ExcelMPL")
				document.location = "/site/croceitalia/documento/list/ExcelMPL";				
			else if (operaz == "ExcelATS")
				document.location = "/site/croceitalia/documento/list/ExcelATS";
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
