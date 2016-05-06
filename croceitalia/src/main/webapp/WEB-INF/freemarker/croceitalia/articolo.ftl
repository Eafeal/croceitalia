<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">

<!--  Version: Multiflex-3 Update-4 / Overview             -->
<!--  Date:    December 11, 2006                           -->
<!--  Author:  Wolfgang                                    -->
<!--  License: Fully open source without restrictions.     -->
<!--           Please keep footer credits with a link to   -->
<!--           Wolfgang (www.1-2-3-4.info). Thank you!     -->

<head>

<#include "/${dominio}/includes/header.ftl">

</head>

<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<body>
<!-- Main Page Container -->
<div class="page-container">

    <!-- Page Header -->
    <#include "/${dominio}/includes/pageHeader.ftl">
    
    <!-- Oggetti Contenuti -->
	<#list pagina.getChildren() as oggetto>
		<#if oggetto.getTipoOggetto().getSimpleName()!="Articolo"  >
			<#include "/${dominio}/${oggetto.getTemplate()}" />
		</#if>
	</#list>
	
	<div class="main-content" >
		<article class="articlecontent">
			<header>
				<h2>${articolo.testo.title}</h2>
			</header>
				<#if articolo.image??  >
					<img src="/site/documento/get/${articolo.image.id}" alt="${articolo.testo.title}"  />
				</#if>
				<p>${articolo.testo.getTesto()}</p>
		</article>
	</div>
	
    <#include "/${dominio}/includes/footer.ftl">
    
</div> 
</body>
</html>

