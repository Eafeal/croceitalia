
<#assign sezione = "Generazione dataset XML">
<#assign curr_gra = "">
<#assign curr_gen = "current">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">

<div id="wait">
  <div id="loading">
    Attendere prego...
  </div>
</div>

<!--#include "/${dominio}/includes/sidebar.ftl"-->
			
		<div id="content" class="admin">
	    	<#if ErrorMsg?? >
	    		<h1>cErrore: ${ErrorMsg}</h1>
	    	</#if>
		<#if utente.isLogged()>
		
			<div class="loginform">		
			<form method="post" action="/site/logout"> 
			<p style="margin:0"><input type="hidden" name="requestedUri" value="${requestedUri}" /></p>
			<fieldset style="border: 0; border-bottom: 1px solid #999; padding: 5px 0; margin-bottom: 30px; font-size: 16px;">
				<span style="display: inline-block;">
                Utente: <strong>${utente.cognome} ${utente.nome}</strong>
                <br />
				Profilo: <strong>${utente.getSoggettoUtente().getDescription()}</strong>
                </span>
				<input type="submit"  class="button" value="Esci" style="float: right;" />
			</fieldset>
			</form>
			</div>
			
			<#include "/${dominio}/includes/bottoniera.ftl">
				
			<strong>Seleziona dal tuo computer il file excel che hai compilato con le tue fatture e inviacelo per l'elaborazione</strong><br/>
			<form id="UploadForm" name="UploadForm" method="post" 
				action="/site/croceitalia/passo2/upload" 
				enctype="multipart/form-data" 
				onSubmit="javascript:return js_controllo_allegato()">
				<input type="hidden" id="autore" name="autore" value="${utente.getUserId()}"/>
				<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
				<input class="field" type="file" id="file" name="file" />
				<input type="submit" class="field" style="float: right;" value="Invia il modello compilato e genera il documento XML > "/>
			</form>
			<#if utente.soggetto.id == "730UserSSAL" || utente.soggetto.id == "730UserMon" || utente.soggetto.id == "730UserSSA">			
            <div class="boxed alert-box" style="margin-top: 10px;">
          	  <span class="boxed-title alert-title">INFORMAZIONI</span>
          	  <strong>Per le strutture sanitarie Lombarde:</strong><br />
              Se dovete inviare il file XML alla vostra ATS di competenza nel file excel NON INDICATE IL CF DEL PROPRIETARIO (colonna D)
       	    </div>            
			</#if>				
			<#assign modello = "/modelliExcel/Modello_STRUTTURE_SSA.xls">
			<#assign esempio = "/modelliExcel/Modello_STRUTTURE_SSA_Esempio.xls">
			<#if utente.soggetto.id == "730UserMP">
				<#assign modello = "/modelliExcel/Modello_MEDICI.xls">
				<#assign esempio = "/modelliExcel/Modello_MEDICI_Esempio.xls">			
			</#if>				
			<#if utente.soggetto.id == "730Latina">
				<#assign modello = "/modelliExcel/Modello_MEDICI.xls">
				<#assign esempio = "/modelliExcel/Modello_MEDICI_Esempio.xls">			
			</#if>
			<#if utente.soggetto.id == "730ODCRC">
				<#assign modello = "/modelliExcel/Modello_MEDICI.xls">
				<#assign esempio = "/modelliExcel/Modello_MEDICI_Esempio.xls">			
			</#if>				
			<#if utente.soggetto.id == "730UserMPL">
				<#assign modello = "/modelliExcel/Modello_MMG_PDF_Lombardia.xls">
				<#assign esempio = "/modelliExcel/Modello_MMG_PDF_Lombardia_Esempio.xls">			
			</#if>	
			
			<br/><strong>Se è la prima volta, segui attentamente le semplici istruzioni qui sotto per generare il tuo flusso XML:</strong>
			<ol style="padding-top: 10px;">
              <li style="padding: 10px 0;"><strong>Scarica il modello del file excel da compilare</strong><br>
			      <span style="font-size:13px">Clicca sulla prima icona qui sotto a sinistra per scaricare il file Excel; il file proposto fornisce il modello che consente di inserire tutti i dati previsti dalle specifiche tecniche organizzandoli in maniera semplice e lineare.(*)</span></li>
			  <li style="padding: 10px 0;"><strong>Modifica il file excel inserendo le fatture del tuo studio/struttura</strong></li>
			  <li style="padding: 10px 0;"><strong>Quando hai finito di compilare il foglio Excel, selezionalo dal tuo computer mediante <a href="#" onclick="document.getElementById('file').focus()">l'apposito campo</a> in cima alla pagina e inviacelo per l'elaborazione</strong></li>
			</ol>

            <ul class="hr-icons-link">
              <li><a href="${modello}" title="Clicca qui per scaricare il modello excel da compilare"><img src="/images/icon-excel-void.png" /><span>Scarica il modello Excel da compilare e inviare per la conversione</span></a></li>
              <li><a target = "istruzioni" href="/documenti/Istruzioni_per_la_compilazione_del_modello_excel_v1.pdf" title="Clicca qui per scaricare le istruzioni per la compilazione del file Excel"><img src="/images/icon-pdf.png" /><span>Scarica le istruzioni per la compilazione del file Excel<br><br></span></a></li>
              <li><a href="${esempio}" title="clicca qui per scaricare un esempio già compilato"><img src="/images/icon-excel.png" /><span>(*) Scarica un esempio già compilato per provare la conversione<br></span></a></li>
            </ul>		
			
			<br/>

			<#if utente.isProva()>	
				<font color=red>
				<strong>		
				Questa &egrave; una versione dimostrativa del nostro applicativo.<br/>
				L'eventuale flusso XML che verr&agrave; prodotto non potr&agrave; essere inviato.
				</strong>
				</font>
				<br>
			</#if>			
			
		<#else>
		
		</#if>

		
			
    	</div>

		<div class="clearfloat"></div>
 
		<#include "/${dominio}/includes/footer_new.ftl">

<script>
function showLoader()
	{
	document.getElementById("wait").style.display = "block";
	}

function hideLoader()
	{
	document.getElementById("wait").style.display = "none";
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

if (estensione.toUpperCase()=="XLS"||estensione.toUpperCase()=="XLSX"||estensione.toUpperCase()=="CSV")
	{
	showLoader();
	return true;
	}	

alert("Specificare un file in formato excel 2003 o CSV.");	
return false;
}
</script>



   