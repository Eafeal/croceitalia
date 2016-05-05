<html>
<h1>LISTA PAZIENTI<h1>

<#if messaggio?? >
	<p>${messaggio}</p>
</#if>

<table>
	<#list ListaPazienti as paziente >
	    <tr>
			<td>Nome Paziente</td><td>${paziente}</td>
	    </tr>
	</#list>
</table>   
</html>              