<#assign testo=oggetto />
<div class="main-content" >
	<p><#include "/framework/links.ftl"  /></p>
	<#if testo.title??>
		<h1>${testo.title}</h1>
	</#if>
	<p>${testo.getTesto()}</p>
	<#if testo.autore??>
		<h2>${testo.autore}</h2>
	</#if>
</div>
