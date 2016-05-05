
<#assign sezione = "Gestione invii Sogei XML">
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

			<strong>Seleziona dal tuo computer il file xml che hai generato con i tuoi programmi e che vuoi inviare a sistema TS</strong><br/>
			<form id="UploadForm" name="UploadForm" method="post" 
				action="/site/croceitalia/carica/xml" 
				enctype="multipart/form-data" 
				onSubmit="javascript:return js_controllo_allegato()">
				<input type="hidden" id="autore" name="autore" value="${utente.getUserId()}"/>
				<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
				<input class="field" type="file" id="file" name="file" />
				<input type="submit" class="field" style="float: right;" value="Carica il file XML da inviare a sistema TS > "/>
			</form>
		
            <div class="boxed alert-box" style="margin-top: 10px;">
          	  <span class="boxed-title alert-title">INFORMAZIONI</span>
              Se il file XML è stato generato con vostri programmi ASSOCONS non è in alcun modo responsabile per il contenuto del file stesso e dell'esito dell'invio dei dati a sistema TS.
       	    </div>    
			<br>
			<br>
		
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

if (estensione.toUpperCase()=="XML")
	{
	showLoader();
	return true;
	}	

alert("Specificare un file in formato XML.");	
return false;
}
</script>



   