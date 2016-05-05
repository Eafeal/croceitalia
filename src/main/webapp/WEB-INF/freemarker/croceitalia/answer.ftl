<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">

<!--  Version: Multiflex-3 Update-4 / Overview             -->
<!--  Date:    December 11, 2006                           -->
<!--  Author:  Wolfgang                                    -->
<!--  License: Fully open source without restrictions.     -->
<!--           Please keep footer credits with a link to   -->
<!--           Wolfgang (www.1-2-3-4.info). Thank you!     -->

<head>

  <meta http-equiv="cache-control" content="no-cache"    />
  <meta http-equiv="expires" content="0"    />
  <meta name="revisit-after" content="2 days"    />
  <meta name="robots" content="index,follow"    />
  <meta name="publisher" content="Your publisher infos here ..."    />
  <meta name="copyright" content="Your copyright infos here ..."    />
  <meta name="author" content="Design: Wolfgang (www.1-2-3-4.info) / Modified: Paolo Donzelli (www.assocons.it)"    />
  <meta name="distribution" content="global"    />
  <meta name="description" content="Your page description here ..."    />
  <meta name="keywords" content="Your keywords, keywords, keywords, here ..."    />
  
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"   />
  <meta http-equiv="Pragma" content="no-cache"      />
  <meta http-equiv="Cache-Control" content="no-cache"      />
  <meta http-equiv="Expires" content="-1"      />


    <#include "/${dominio}/includes/header.ftl">
</head>

<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<body>
	<!-- Main Page Container -->
	<div class="page-container">
	
	    <!-- Page Header -->
	
	    <!-- B. MAIN -->
	    <div class="main">
	    	<h1>
				<#if ErrorMsg?? >
					<p>Messaggio di errore:<br/>${ErrorMsg}</p>
				<#else>
					<p>Operazione Terminata Correttamente</p>
				</#if>
			</h1>
	    </div>
	    
	    <#include "/${dominio}/includes/footer.ftl">
	    
	</div> 
</body>
</html>



   