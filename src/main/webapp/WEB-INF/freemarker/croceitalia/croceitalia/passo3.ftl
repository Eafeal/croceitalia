
<#assign sezione = "Generazione dataset XML">
<#assign curr_gra = "">
<#assign curr_gen = "current">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">
<#assign tipoUtente = utente.getSoggettoUtente().getId()>

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
			
		 	<div>

			<#if generatoReport?string="true" >
				
				<#include "/${dominio}/includes/messaggi.ftl">

				<#assign description= outFile.nomefile/>					
				<#assign urlv= "/site/documento/view/" + outFile.id />								
				<#assign url= "/site/documento/get/" + outFile.id />	
				<#assign esegui= "/site/croceitalia/invia/xml/form/" + outFile.id />						

				Il documento XML &egrave; stato generato con il nome<h3>${description}</h3>
				<br />
				<hr />

				<div class="icon-box">
				<a href="${urlv}" target="Dataset730P" type="application/text" ><img src="/images/icon-view.png" title="VISUALIZZA FILE" /></a>
				<p>
				Puoi visualizzare il risultato dell'elaborazione cliccando sull'icona qui a sinistra.
				</p>
				<div class="clearfloat"></div>
				</div>
				  
				<hr />
					<div class="icon-box">
					<a href="${url}" type="application/text"><img src="/images/icon-download.png" title="SCARICA ${description}" /></a>
					<p>
					Puoi scaricare sul tuo computer il file XML generato cliccando l'icona qui a sinistra. (il file verrà scaricato nella cartella dei download)<br/>
					Una volta scaricato il file, procedi al suo invio come previsto dalla normativa vigente,
					o dalle procedure previste dagli enti (ASL / Regione) di cui fai parte.
					<#if utente.isProva()>	
						<font color=red>
						<strong>		
						<br>ATTENZIONE!!!!!<br/>
						Questa &egrave; la versione dimostrativa del nostro applicativo.<br/>
						La generazione del file XML &egrave; incompleta, di conseguenza il file risultante, anche in caso di esito positivo, non può essere inviato.
						</strong>
						</font>
						<br>
					</#if>					
					</p>
					<div class="clearfloat"></div>
					</div>
					
					<#if tipoUtente != "730UserMPL">
						<hr />
						<div class="icon-box">
						<a href="${esegui}"><img src="/images/icon-send.png" title="INVIA ${description}" /></a>
						<p>
						Se vuoi inviare il file al sistema tessera sanitaria/SOGEI clicca sull'icona qui a sinistra.
						Per poter procedere all'invio del flusso devi essere già stato accreditato su sistema TS, ed avere a portata di mano il tuo codice fiscale, il pincode e la password di accesso che ti sono state rilasciate.<br>
						<#if utente.isProva()>	
							<font color=red>
							<strong>		
							Durante la prova gratuita potrai accedere alla funzione di invio, ma non potrai fisicamente inviare il tuo file.
							</strong>
							</font>
							<br>
						</#if>
						</p>
						<div class="clearfloat"></div>
						</div>
					</#if>
					<hr />
					<br>
					<a href="/site/croceitalia/genera/xml">Clicca qui per iniziare una <b>nuova elaborazione</b></a><br>

			<#else >
			
                <div class="boxed error-box">
                  <span class="boxed-title error-title">ERRORE</span>
                  Siamo spiacenti ma il file excel inviato contiene degli errori e non &egrave; stato possibile generare il file xml.
                </div>
				<#if warnLog?? >
					<#assign url= "/site/documento/get/" + warnLog.id />
					<#assign urlv= "/site/documento/view/" + warnLog.id />										
					<#assign description="log dell'elaborazione"/>
					
					<div class="icon-box">
					  <a href="javascript:f_win_log('${urlv}')" type="${warnLog.getMimeType()}"><img src="/images/icon-view.png" title="VISUALIZZA ERRORI"></a>
					  <p>
					  Visualizza gli errori cliccando sull'icona qui a sinistra, o se preferisci scarica il <a href="${url}" type="${warnLog.getMimeType()}" style="float: none; background: none; border: none; box-shadow: none;" >${description}</a>.<br>
					  Correggi gli errori segnalati direttamente sul file Excel.<br>
					  Terminate le correzioni invia nuovamente il file per una nuova elaborazione.<br>
					  </p>
					  <div class="clearfloat"></div>
					</div>
					
					<hr />
                
					<strong>NB</strong> Assicurati che il file excel corrisponda esattamente al modello scaricabile dal nostro sito; 
					eventuali modifiche apportate alla struttura del modello proposto potrebbero comprometerne l'utilizzo.<br/><br/>
					
					<!--form id="UploadForm" name="UploadForm" method="post" 
						action="/site/croceitalia/passo2/upload" 
						enctype="multipart/form-data" 
						onSubmit="javascript:return js_controllo_allegato()">
						<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
						<input type="hidden" id="autore" name="autore" value="${utente.getUserId()}"/>
						<label  class="field" for="file">File Excel</label>
						<input class="field" type="file" id="file" name="file"/><br/>
						<input type="submit" class="field" value="Invia il file excel corretto e genera il documento XML > "/>
					</form-->						
					<hr />
					<br>
					Clicca qui per iniziare una <a href="/site/croceitalia/genera/xml"><b>nuova elaborazione</b></a>
					<br><br>						
				<#else> 
					Assicuratevi che il file excel corrisponda al modello proposto scaricabile dal nostro sito.<br/><br/> 					
					<a href="/site/croceitalia/genera/xml"><b>Riprova a inviare un nuovo file excel</b></a>
				</#if>
	
			</#if>
			
		 	</div>

		<#else>
		
		</#if>
		
		<#if utente.isProva()>	
			<strong>Per informazioni relative all'acquisto della licenza d'uso <a href="/contatti.html">contattateci.</a></strong></p>
		</#if>
		
		
	    </div>
		<div class="clearfloat"></div>
		
		
 
<#include "/${dominio}/includes/footer_new.ftl">


<style>

#logfile {
	height:0;
	width:100%;
	border:0;
	overflow:hidden;
	-webkit-transition: all 500ms ease; 
	-moz-transition: all 500ms ease; 
	-ms-transition: all 500ms ease; 
	-o-transition: all 500ms ease; 
	transition: all 500ms ease;
	}
</style>

<script>

function scrivi(url)

                {
		alert(url);
                var mydiv = document.getElementById("logfile");
                mydiv.src = url;
                $("#logfile").width("100%");
                $("#logfile").height("200px");
                $("#logfile").css({
                               "overflow" : "auto",
                               "border" : "1px solid #DDD"
                               });
                }

</script>

<script>
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
