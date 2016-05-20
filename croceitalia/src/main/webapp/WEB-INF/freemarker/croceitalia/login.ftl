
<#assign sezione = "Accedi al servizio<br/>per la generazione dataset XML">
<#assign curr_gra = "">
<#assign curr_gen = "">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">

		<div id="content">
		<center>		
		<#if loginMessage="UserScaduta" >
		
			<fieldset>
			<h3>Siamo spiacenti ma la sua utenza &egrave; scaduta.</h3><br/>
			<p><a href="/contatti.html"><b>Contattare</b></a> Assocons per rinnovare il contratto e riattivare l'utenza</p>
			</fieldset>

		<#else>
		
			<fieldset>
			<br><h3>User e/o password errate</h3><br/>
			<p><a href="/index.html"><b>Riprova</b></a></p>
			</fieldset>
			<br>
  
		</#if>		
		</center>			
    	</div>

		<div class="clearfloat"></div>
 
<#include "/${dominio}/includes/footer_new.ftl">





   