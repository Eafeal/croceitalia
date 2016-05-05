
<#assign sezione = "Reverse file XML">
<#assign curr_gra = "">
<#assign curr_gen = "">
<#assign curr_val = "">
<#assign curr_rev = "current">
<#assign slide = "slide8">
<#include "/${dominio}/includes/header_new.ftl">
<#include "/${dominio}/includes/sidebar.ftl">

<div id="content">

  <div class="bodyResult ">

    	<#if esito?string="true" >
			<h1>Congratulazioni!<br/>Il documento Excel &egrave; stato generato.</h1>
			<#if excelFile?? >
				<#assign url=  "/site/documento/get/"  + excelFile.id />
				<#assign urlv= "/site/documento/view/" + excelFile.id />
				<#assign description="File Excel Generato"/>
				<br/>
				<h3><a href="${url}" type="${excelFile.getMimeType()}" >Clicca qui</a> per scaricare il file Excel generato</h3> 
				<br/>
			</#if>
			
			<span>Se vuoi riprovare con un altro <strong><a href="/reverse-xml.html">clicca qui</a></span>
		<#else> 
			<h1>Non &egrave; stato possibile generare il file Excel. Correggere gli errori e riprovare <strong>(<a href="/reverse-xml.html">clicca qui</a>)</h1>
			<#if UserMsg?? ><br/><h3>Messaggio di errore:<br/>${UserMsg}</h3></#if>
		</#if> 
	</div>
	<br/><br/>
	<div>
		<p>
		<#if esito?string="false" >
			<p><strong>Se il file &egrave; stato generato dal nostro programma ti preghiamo di contattare la nostra assistenza <a href="/contatti.html">"Contattaci"</a> per segnalare e verificare il problema. Grazie.</p>
			<br/>
		</#if>
		</p> 
	</div>
	<br/>
</div>

<div class="clearfloat"></div>
<#include "/${dominio}/includes/footer_new.ftl">
