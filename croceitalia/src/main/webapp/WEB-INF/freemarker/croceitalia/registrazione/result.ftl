
<#assign sezione = "Prova GRATUITA">
<#assign curr_gra = "">
<#assign curr_gen = "">
<#assign curr_val = "">
<#assign curr_rev = "current">
<#assign slide = "slide4">
<#include "/${dominio}/includes/header_new.ftl">
<#include "/${dominio}/includes/sidebar.ftl">

<div id="content">

  <div class="bodyResult ">

	<#if esito="1" >
		<h1>Congratulazioni!<br/>La registrazione è avvenuta con successo.</h1>
		<br/>
		<h3>Riceverai a breve sull'email indicata le istruzioni per attivare la tua utenza</h3> 
		<strong>Se non trovate l'email verificate anche nello spam e successivamente, se non la trovate, contattateci</strong> 		
		<br/>
	</#if>
	
	<#if esito="2" >
		<h1>Non è stato possibile completare la registrazione</h1>
		<br/>
		<h3>L'email indicata nel form di registrazione è già presente nei nostri archivi ed è in attesa di attivazione</h3> 
		<br/>
		<h3><a href="/site/croceitalia/recupero-istruzioni/${utente.getId()}">Clicca qui per recuperare le istruzioni per attivare la tua utenza</a>
		<br>... riceverai a breve sull'email indicata le istruzioni per attivare la tua utenza</h3> 
		<strong>Se non trovate l'email verificate anche nello spam e successivamente, se non la trovate, contattateci</strong> 			
		<br>
	</#if>	

	<#if esito="3" >
		<h1>Non è stato possibile completare la registrazione</h1>
		<br/>
		<h3>L'email indicata nel form di registrazione è già presente nei nostri archivi</h3> 
		<br/>
		<h3>Se hai dimenticato user e/o password <a href="/site/croceitalia/password-dimenticata/${utente.getId()}">Clicca qui per recuperare le tue credenziali di accesso</a></h3>
		<br>
		<br>
	</#if>	
	
	<#if esito="4" >
		<h3>Riceverai a breve sull'email indicata in fase di registrazione le istruzioni per attivare la tua utenza</h3>
		<strong>Se non trovate l'email verificate anche nello spam e successivamente, se non la trovate, contattateci</strong> 		
		<br/>
		<br>
	</#if>	
	
	<#if esito="5" >
		<h1>Congratulazioni!<br/>Attivazione avvenuta con successo.</h1>
		<br/>	
		<strong>Da questo momento hai a disposizione 7 giorni di tempo per provare gratuitamente, senza impegno, la nostra soluzione.</strong>
		<br/><br/>
		<h3>
		<a href="/login.html" title="Clicca qui per accedere al servizio">
		Clicca qui per accedere al servizio
		</a>
		</h3>
		<br/>
		<br>
	</#if>	
	
	<#if esito="6" >
		<br>
		<h3>Riceverai a breve sull'email indicata in fase di registrazione le tue credenziali di accesso</h3><br><br>
		<strong>Se non trovate l'email verificate anche nello spam e successivamente, se non la trovate, contattateci</strong> 		
		<br/>
		<br>
	</#if>	
	
	<#if esito="7" >
		<h3>Siamo spacenti ma non è stato possibile recuperare la tua password.<br>
		L'email indicata non è presente nei nostri archivi</h3> 
		<br/>
		<br/><a href="/site/croceitalia/password-dimenticata"><b>Riprova</b></a> o procedi con la <a href="/prova-gratis.html"><b>registrazione</b></a> per richiedere la user di accesso.
		<br>Se sei sicuro della mail indicata in fase di registrazione contattaci.
		<br>
	</#if>	
	
	<#if esito="99" >
		<h1>Siamo spiacenti ma si è verificato un errore in fase di registrazione</h1>
		<br/>
		<h3>Riprovare più tardi<br>Se l'errore persiste contattate la nostra assistenza</h3> 
		<br/>
	</#if>		
		
  </div>

</div>

<div class="clearfloat"></div>
<#include "/${dominio}/includes/footer_new.ftl">
