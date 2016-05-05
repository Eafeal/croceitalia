<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">

<!--  Version: Multiflex-3 Update-4 / Overview             -->
<!--  Date:    December 11, 2006                           -->
<!--  Author:  Wolfgang                                    -->
<!--  License: Fully open source without restrictions.     -->
<!--           Please keep footer credits with a link to   -->
<!--           Wolfgang (www.1-2-3-4.info). Thank you!     -->

<head>
<meta name="view"       content="passo2-compile.ftl"      />

<#include "/${dominio}/includes/header.ftl">
<#include "/${dominio}/includes/sidebar.ftl">

</head>

<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<body>
	<!-- Main Page Container -->
	<div class="page-container">
	    <!-- Page Header -->
	    <#include "/${dominio}/includes/pageHeader.ftl">
	
	    <!-- B. MAIN -->
	    <div class="main">
	    
		<#list pagina.getChildren() as oggetto>
		    <#include "/${dominio}/${oggetto.getTemplate()}" />
		</#list>
	    
		<div class="main-content" >
			<p><span style="float:none;display:inline" >&nbsp;</span></p>
			<div class="main-content" >
				<p><span style="float:none;display:inline" >&nbsp;</span></p>
				<h1>Primo Passo Costruzione File Indice</h1>
				<p><p style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 10pt;"><span style="color: #355f88;"><strong>Da un documento excel ad un documento xml</strong></span></p>
				<p><span style="font-size: medium; color: #355f88;">1-Scarica il modello del file excel</span></p>
				<p><span style="font-size: medium; color: #355f88;">2-Modificalo inserendo i tuoi dati</span></p>
				<p><span style="font-size: medium; color: #355f88;">3-Passa al passo successivo</span></p></p>
			</div>
			<div class="column1-unit">
	    		<a class="field" href="/site/documento/get/89252" ><img border="0" align="center" src="/img/icons/xls_mini.gif" alt="Scarica"  />Clicca qui per scaricare il modello excel vuoto da compilare</a>
	    	</div>
	    	<div class="column1-unit">
	    		<a class="field" href="/site/documento/get/91902" ><img border="0" align="center" src="/img/icons/xls_mini.gif" alt="Scarica"  />Clicca qui per scaricare un modello gi&agrave; compilato</a>
	    	</div>
	    </div>
    </div>
    <#include "/${dominio}/includes/footer.ftl">
</body>
</html>




   