
<#assign sezione = "Validazione file XML">
<#assign curr_gra = "">
<#assign curr_gen = "">
<#assign curr_val = "current">
<#assign curr_rev = "">
<#assign slide = "slide7">
<#include "/${dominio}/includes/header_new.ftl">
<#include "/${dominio}/includes/sidebar.ftl">

<div id="content">

	  <div class="bodyResult ">

	    	<#if isValid?string="true" >
				<h1>Congratulazioni!<br/>Il documento XML &egrave; valido.</h1>
				Se vuoi validarne un altro <strong><a href="/validazione-xml.html">clicca qui</a>
			<#else> 
				<h1>Il documento inviato non &egrave; valido. Correggere gli errori e riprovare (<a href="/validazione-xml.html">clicca qui</a>)</h1>
				<#if UserMsg?? ><h3>${UserMsg}</h3></#if>
			</#if> 

	</div>
	<br/><br/>
	<div>
		<#if isValid?string="false" >
			<p><strong>Se il file &egrave; stato generato dal nostro programma ti preghiamo di contattare la nostra assistenza <a href="/contatti.html">"Contattaci"</a> per segnalare e verificare il problema. Grazie.</p>
		</#if>
	</div>
	<br/>
	<p><strong>Ti invitiamo a provare la nostra soluzione per <a href="/generazione-XML-AVCP-prova-gratis.html">la generazione del file XML Legge 190</a>.</strong></p>
	<br/>
	<p><strong><em>NB. Se il file XML &egrave; stato prodotto da te o da terzi parti, ASSOCONS SRL declina ogni responsabilit&agrave; in merito all'esito della sua validazione.</em></strong></p>
</div>

<div class="clearfloat"></div>
<#include "/${dominio}/includes/footer_new.ftl">
