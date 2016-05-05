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

<meta name="google-site-verification" content="GYgAQkcCLry4lj0yolduWC4GMuauhGymVBX9WcfFR3o" />
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
		
		<div>
			<p align="center">
				<iframe marginwidth="0" marginheight="0" src="http://maps.google.it/maps?f=q&amp;source=s_q&amp;hl=it&amp;geocode=&amp;q=milano+Viale+Andrea+Doria+33&amp;aq=&amp;sll=41.442726,12.392578&amp;sspn=26.232085,67.543945&amp;ie=UTF8&amp;hq=&amp;hnear=Viale+Andrea+Doria,+33,+20124+Milano,+Lombardia&amp;ll=45.48896,9.214568&amp;spn=0.009928,0.017338&amp;z=15&amp;iwloc=A&amp;output=embed" frameborder="1" height="330" scrolling="no" width="405"></iframe><br/>
					<small>
						<a style="COLOR: #0000ff; TEXT-ALIGN: center" href="http://maps.google.it/maps?f=q&amp;source=embed&amp;hl=it&amp;geocode=&amp;q=milano+Viale+Andrea+Doria+33&amp;aq=&amp;sll=41.442726,12.392578&amp;sspn=26.232085,67.543945&amp;ie=UTF8&amp;hq=&amp;hnear=Viale+Andrea+Doria,+33,+20124+Milano,+Lombardia&amp;ll=45.48896,9.214568&amp;spn=0.009928,0.017338&amp;z=15&amp;iwloc=A" target="_blank">
							<font color="#003366" face="Tahoma, Arial, Helvetica, sans-serif">Visualizzazione ingrandita della mappa</font>
						</a>
					</small>
			</p>
			<p align="center">
				<br/><br/>
				ASSOCONS srl<br/>Viale Andrea Doria 33<br/>20124 Milano<br/>
				Tel.: 02 67020393 - 02 67382823<br/>Fax : 02 66719184<br/>
				E-mail:<a href="mailto:info@assocons.it">info@assocons.it</a><br/><br/><br/>
			</p>
		</div>
    
    <#include "/${dominio}/includes/footer.ftl">
    
</div> 
</body>
</html>



   