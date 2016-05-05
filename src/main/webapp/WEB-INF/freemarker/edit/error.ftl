<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">
    <head>
        <#include "/edit/head.ftl"  />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
        </div>
        
        <p>EDITING ERROR PAGE</p>

		<#if ErrorMsg?? >
			<p>Messaggio di errore:&nbsp;${ErrorMsg}</p>
		<#else>
			<p>Messaggio NON disponibile </p>
		</#if>
		
	</body>
</html>
