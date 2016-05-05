
<#assign sezione = "Accedi al servizio<br/>per la generazione dataset XML">
<#assign curr_gra = "">
<#assign curr_gen = "">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">
<#include "/${dominio}/includes/sidebar.ftl">

		<div id="content">
		<#if loginMessage="UserScaduta" >
		
			<fieldset>
			<h3>Siamo spiacenti ma la sua utenza &egrave; scaduta.</h3><br/>
			<p><a href="/contatti.html"><b>Contattare</b></a> Assocons per rinnovare il contratto e riattivare l'utenza</p>
			</fieldset>

		<#elseif loginMessage="UserErrata" >
		
			<fieldset>
			<br><h3>User e/o password errate</h3><br/>
			<p><a href="/site/croceitalia/genera/xml"><b>Riprova</b></a></p>
			</fieldset>
			<br>
			
		<#elseif loginMessage="UserNonAttiva" >
		
			<fieldset>
			<br><h3>Attenzione l'utenza non è stata ancora verificata e attivata</h3><br/>
			<p>Attiva la tua utenza seguendo le istruzioni della mail che hai ricevuto nella tua casella di posta.</p>		
			<p>Se non hai ricevuto la mail <a href="/site/croceitalia/recupero-istruzioni/${utente.getId()}"><b>clicca qui</b></a> per richiedere il reinvio delle istruzioni e controlla la tua casella di posta ed eventualmente anche lo SPAM.</p>		
			<p><a href="/contatti.html"><b>Contattare</b></a> l'assistenza Assocons nel caso in cui il problema persista.</p>
			</fieldset>
			<br>
			
		<#else>
		
			<h3>Accedi al servizio</h3></br>
			
			Se hai sottoscritto il nostro servizio e sei già in possosso di user e password di accesso, in questa sezione potrai procedere alla generazione del file XML.</br></br>

			<form class="myform" id="formAccesso" name="formAccesso" action="/site/croceitalia/genera/login" method="post">
			  <p>
				<label for="user">E-mail (ID)</label>
				<input type="text" class="input" id="userId" name="userId" value="" />
			  </p>
			  <p>
				<label for="password">Password</label>
				<input type="password" class="input" id="password" name="password" value="" />
			  </p>
			  <p>
				<input type="submit" class="button" value="ACCEDI" />
				<!-- p><a href="#" id="forgotpsswd_1">Password forgotten?</a></p -->
			  </p>
			  <div class="clearfloat"></div>
			</form>
			<br/>Se hai dimenticato user e/o password <a href="/site/croceitalia/password-dimenticata">Clicca qui per recuperare le tue credenziali di accesso</a>
			<br/>Se non hai ancora sottoscritto il servizio, non esitare a <a href="/contatti.html"><b>contattarci</b></a> per chiedere ulteriori informazioni e/o a <a href="/prova-gratis.html"><b>registrarti</b></a> per richiedere la user di accesso. 
			  
		</#if>		
			
    	</div>

		<div class="clearfloat"></div>
 
<#include "/${dominio}/includes/footer_new.ftl">





   