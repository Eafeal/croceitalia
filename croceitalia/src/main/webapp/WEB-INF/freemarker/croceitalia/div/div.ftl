<div class="main-content" >
	<#if ErrorMsg?? >
		<h1>Errore: ${ErrorMsg}</h1>
	</#if>
	<p><#include "/framework/links.ftl"  /></p>
	<#list oggetto.getChildren() as oggetto >
	    <#include "/${dominio}/${oggetto.getTemplate()}" />
	</#list>
</div>
