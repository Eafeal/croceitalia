
<#assign sezione = "Generazione dataset XML">
<#assign curr_gra = "">
<#assign curr_gen = "current">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">

<div id="wait">
  <div id="loading">
    Attendere prego<br>
	Elaborazione in corso....
  </div>
</div>

<#if utente.isLogged()>	
	<div class="loginform">
	<form method="post" action="/site/logout"> 
	<p><input type="hidden" name="requestedUri" value="${requestedUri}" /></p>
	<fieldset>
		Utente: ${utente.cognome} ${utente.nome}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Profilo: ${utente.getSoggettoUtente().getDescription()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  class="button" value="Esci" />
		<br>
	</fieldset>
	</form>
	</div>
	
	<#assign strong1a = "" >
	<#assign strong1c = "" >
	<#assign strong2a = "" >
	<#assign strong2c = "" >	
	<#assign strong3a = "" >
	<#assign strong3c = "" >
	<#assign strong4a = "" >
	<#assign strong4c = "" >
	
	<#if tipoFile == "CSV" >
		<#assign strong2a = "<strong>" >
		<#assign strong2c = "</strong>" >
	</#if>
	<#if tipoFile == "ExcelMPL" >
		<#assign strong3a = "<strong>" >
		<#assign strong3c = "</strong>" >
	</#if>		
	<#if tipoFile == "ExcelSSA" >
		<#assign strong4a = "<strong>" >
		<#assign strong4c = "</strong>" >
	</#if>
	
	${strong2a}<a href="javascript:esegui('CSV')">Prestazioni ATS (csv)</a>${strong2c}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
	${strong3a}<a href="javascript:esegui('ExcelMPL')">Prestazioni MMG/PDF</a>${strong3c}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
	${strong4a}<a href="javascript:esegui('ExcelSSA')">Prestazioni SSA/ATS (excel)</a>${strong4c}	
	
	<br><br>
	<strong>Seleziona dal tuo computer il file da elaborare</strong><br/>
	
	<form id="UploadForm" name="UploadForm" method="post" 
		action="/site/croceitalia/passo2/upload" 
		enctype="multipart/form-data" 
		onSubmit="javascript:return js_controllo_allegato()">
		<input type="hidden" id="tipoFile" name="tipoFile" value="${tipoFile}"/>
		<input type="hidden" id="autore" name="autore" value="${utente.getUserId()}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
		<input class="field" type="file" id="file" name="file" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" class="field" value="Invia il file da elaborare"/>
	</form>	
	
	<!--form id="ZipUploadForm" name="ZipUploadForm" method="post" 
		action="/site/croceitalia/documento/zipUpload" 
		enctype="multipart/form-data" 
		onSubmit="javascript:return js_controllo_allegato_2()">
		<input type="hidden" id="tipoFile" name="tipoFile" value="${tipoFile}"/>		
		<input type="hidden" id="autore" name="autore" value="${utente.getUserId()}"/>
		<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
		<input class="field" type="file" id="file" name="file" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" class="field" value="Invia il file da elaborare"/>
	</form-->		
	
	<div id="contentList">

        <#if firstLevel == false >
            <a href="javascript:void(0)" >
                <img class="del" src="/img/icons/goUp.gif" alt="Livello Superiore"  onClick="esegui('list','${ cartellaCorrente.getIdParent() }')" />
            </a>
        </#if>
        <img src="/img/icons/dir_opened_mini.gif" /><strong>&nbsp;&nbsp;${ fullPath }</strong>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Ordina per: <a href="javascript:esegui('nome')">Nome File Elaborato</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:esegui('data')">Data/ora Elaborazione Decr.</a>
		<br />
        <hr />
		<#if numFiles == "0" >
			<strong>Nessun file elaborato<strong>
		<#else>       
        <table border="0">
			<tr>
			<th title="Id Elaborazione">Id. El.</th>
			<th align=left width=45%>File elaborato</th>
			<th align=left width=45%>File generato</th>
			<th align=left width=5%>Stato</th>
			<th align=left width=5%>Operazioni</th>
			</tr>
			<tr><td colspan=5><hr></td></tr>
			<#assign lastIdFile = "" >
			<#assign lastIdParent = "" >			
			<#assign fileStatus = "" >	
			<#assign lastFileStatus = "" >				
			<#assign primaRiga = true >
			<#assign quanti = 0 >			
			
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
						<#if lastFileStatus == "WIP">
						   <td></td>
						</#if>
						   <td><strong>${lastFileStatus}</strong></td>	
						   <td width="20px"  >
							   <img  src="/img/skin/database_delete.png"  style="float:left;clear:none;border:0" title="Cancella Elaborazione" onclick="esegui('del' ,'${lastIdParent}', '${lastFileStatus}')" />
							   <#if lastFileStatus == "OK">
								  &nbsp;&nbsp;<a title="Scarica il file XML" href="/site/documento/get/${lastIdFile}" type="application/xml" >SC</a>					  
							   </#if>
						   </td>
					   </tr>
					</#if>
					<#assign lastFileStatus = fileStatus>					
					<#assign lastIdParent = idParent >
					<#assign primaRiga = false >
					<#assign quanti = quanti + 1 >
					<tr><td>${idParent}.</td>
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

			<#if lastFileStatus == "WIP">
			   <td></td>
			</#if>
				<td><strong>${lastFileStatus}</strong></td>	
			    <td width="20px"  >
				   <img  src="/img/skin/database_delete.png"  style="float:left;clear:none;border:0" title="Cancella elaborazione" onclick="esegui('del' ,'${lastIdParent}', '${lastFileStatus}')" />
				   <#if lastFileStatus == "OK">
					  &nbsp;&nbsp;<a title="Scarica il file XML" href="/site/documento/get/${lastIdFile}" type="application/xml" >SC</a>					  
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
	<br>
	<br>
	</div>			
	</#if>		
</#if>
	<div class="clearfloat"></div>
 
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
