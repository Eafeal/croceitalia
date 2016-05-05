
<#assign sezione = "Prova gratuita">
<#assign curr_gra = "current">
<#assign curr_gen = "">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide4">
<#include "/${dominio}/includes/header_new.ftl">
<#include "/${dominio}/includes/sidebar.ftl">
			
		<div id="content">
				<h1>Esito elaborazione</h1>
		 		<div>		
				
				<#if UserMsg?? >
					<strong>
					${UserMsg}
					<br/>
					</strong>					
				</#if>
		
				<#if generatoReport?string="true" >
					<#assign description= outFile.description/>					
					<#assign urlv= "/site/documento/view/" + outFile.id />								
					<#assign url= "/site/documento/get/" + outFile.id />		
					
					Il documento XML &egrave; stato generato con il nome <strong>${description}</strong>;<br/>
					puoi visualizzare il risultato dell'elaborazione cliccando il seguente link:<br/>
					<strong><a href="${urlv}" target="Dataset730P" type="${outFile.getMimeType()}" > Visualizza ${description}</a></strong><br/><br/>
					Scaricare in locale il file XML generato cliccando il seguente link<br/>
					<strong><a href="${url}" type="${outFile.getMimeType()}" >Scarica ${description}</a></strong><br/><br/>
					Una volta scaricato il file zippalo e procedi al suo invio come previsto dalla normativa vigente.<br/><br/>
					
					<#if warnLog?? >
						<#assign url= "/site/documento/get/" + warnLog.id />
						<#assign urlv= "/site/documento/view/" + warnLog.id />										
						<#assign description="Log Elaborazione"/>
						(*) Clicca qui per <b><a href="javascript:f_win_log('${urlv}')" type="${warnLog.getMimeType()}" >visualizzare</a></b> o <b><a href="${url}" type="${warnLog.getMimeType()}" >scaricare</a></b> il
						log dell'elaborazione e verificare fino a che riga &egrave; stato letto ed elaborato il documento Excel che hai inviato.
						<br/><br/>
					</#if>
					Clicca qui per iniziare una <a href="/site/croceitalia/genera/xml"><b>nuova elaborazione</b>.</a><br/><br/>						
					Se la prova effettuata soddisfa le tue esigenze, non esitare a <a href="/contatti.html"><b>contattarci</b></a> per chiedere ulteriori informazioni e/o la user di accesso al servizio. 
						
				<#else> 
					<strong>				
					Siamo spiacenti ma il file excel inviato contiene degli errori<br/>				
					e non &egrave; stato possibile generare il file xml.<br/><br/>
					</strong>
					<#if warnLog?? >
						<#assign url= "/site/documento/get/" + warnLog.id />
						<#assign urlv= "/site/documento/view/" + warnLog.id />										
						<#assign description="log dell'elaborazione"/>
						<a href="javascript:f_win_log('${urlv}')" type="${warnLog.getMimeType()}" ><b>Visualizza qui gli errori</b></a> verificatisi o se preferisci scarica il 
						<a href="${url}" type="${warnLog.getMimeType()}" >${description}</a>.<br/><br/>
					
						Correggere gli errori segnalati direttamente nel file Excel, e inviare nuovamente il file per una nuova elaborazione.<br/><br/> 
						
						<form id="UploadForm" name="UploadForm" method="post" 
						action="/site/croceitalia/passo2/upload" 
							enctype="multipart/form-data" 
							onSubmit="javascript:return js_controllo_allegato()">
							<input type="hidden" id="parent_id" name="parent_id" value="${dominio}" >
							<label  class="field" for="file">File Excel</label>
							<input class="field" type="file" id="file" name="file" accept="application/vnd.ms-excel"/><br/><br/>
							<input type="submit" class="field" value="Invia il file excel corretto e genera il documento XML > "/>
						</form>						

						<br/>
						<br/><br/>
					Clicca qui per iniziare una <a href="/site/croceitalia/genera/xml"><b>nuova elaborazione</b></a>						
						
					<#else> 
						Assicuratevi che il file excel corrisponda al modello proposto scaricabile dal nostro sito.<br/><br/> 					
					<a href="/site/croceitalia/genera/xml"><b>Riprova a inviare un nuovo file excel</b></a>
					</#if>
				</#if> 
		 		</div>				
				<br/>
					<p>
	L'acquisto o il rinnovo della licenza &egrave; disponibile anche attraverso il <strong>MePA</strong>.
	<br/><br/>Per accedere all'offerte presenti sul catalogo <strong>MePA</strong>:
    <h4>
	<a target="XMLAVCPSMALL" href="https://www.acquistinretepa.it/catricerche/manageViewRic.do?adfgenDispatchAction=viewElement&org.apache.struts.taglib.html.CANCEL=true&idArticolo=56626164&idArticoloSelected=56626164|mepa|361680" title="Clicca qui per accedere all'offerta prevista per le scuole">
	Clicca qui se sei una scuola (codice Mepa XMLAVCPSMALL) 
    </a>
    </h4>	
	oppure
    <h4>	
	<a target="XMLAVCPBIG" href="https://www.acquistinretepa.it/catricerche/manageViewRic.do?adfgenDispatchAction=viewElement&org.apache.struts.taglib.html.CANCEL=true&idArticolo=56626165&idArticoloSelected=56626165|mepa|361680" title="Clicca qui per accedere all'offerta prevista per le ASL e gli ospedali">
	Clicca qui se sei un ASL o un Ospedale (codice Mepa XMLAVCPBIG) 
    </a>	
	</h4>	
	oppure
    <h4>
	<a target="XMLAVCPBASE" href="https://www.acquistinretepa.it/catricerche/manageViewRic.do?adfgenDispatchAction=viewElement&org.apache.struts.taglib.html.CANCEL=true&idArticolo=56626163&idArticoloSelected=56626163|mepa|361680" title="Clicca qui per accedere all'offerta prevista per i comuni e le altre PA">
	Clicca qui se sei un comune o altra PA (codice Mepa XMLAVCPBASE) 
    </a>	
	</h4>
	<br/>
	<strong>Per informazioni contattateci.</strong></p>

				<p>
				<br/><br/>
				<strong>		
				(*) ATTENZIONE!!!!!<br/>
				Questa &egrave; una versione dimostrativa del nostro applicativo.<br/>
				La generazione del file XML &egrave; limitata alle prime 15 righe del file Excel inviato,<br/>
				di conseguenza il file XML risultante, anche in caso di esito positivo, non pu� essere pubblicato sul vostro sito istituzionale.
				</strong>
				</p>	
							
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
		
		new_win=window.open(url,"errori", "location=0,menubar=0,resizable=1,scrollbars, width="+ w +", height="+ h +", top="+ ph +", left="+ pw);

		new_win.focus();
	
	
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

if (estensione.toUpperCase()=="XLS")
	{
	return true;
	}	

alert("Specificare un file in formato excel 2003.");	
return false;
}		
</script>
