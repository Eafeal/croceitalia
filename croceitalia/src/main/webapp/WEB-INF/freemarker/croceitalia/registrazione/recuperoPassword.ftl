
<#assign sezione = "Accedi al servizio<br/>per la generazione dataset XML">
<#assign curr_gra = "">
<#assign curr_gen = "">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">
<#include "/${dominio}/includes/sidebar.ftl">

		<div id="content">
		
			<h3>Recupero Password</h3></br>
			
			Se hai sottoscritto il nostro servizio e sei già in possosso di user e password di accesso, in questa sezione potrai recuperare le tue credenziali.</br>
			Inserisci l'email indicata in fase di registrazione e clicca sul bottone recupera password.
			<br><br>
			<form class="myform" id="formAccesso" name="formAccesso" action="/site/croceitalia/genera/login" method="post">
			  <p>
				<label for="user">E-mail (ID)</label>
				<input type="text" class="input" id="email" name="email" value="" />
			  </p>
			  <p>
				<input type="button" class="button" value="RECUPERA PASSWORD" onclick="recuperaPassword()"/>
			  </p>
			  <div class="clearfloat"></div>
			</form>
			<br/>Se non hai ancora sottoscritto il servizio, non esitare a <a href="/contatti.html"><b>contattarci</b></a> per chiedere ulteriori informazioni e/o a <a href="/prova-gratis.html"><b>registrarti</b></a> per richiedere la user di accesso. 
			
    	</div>

		<div class="clearfloat"></div>
 
<#include "/${dominio}/includes/footer_new.ftl">

<script src="/js/validation.js" type="text/javascript"></script>
<script>
function recuperaPassword()
	{	
		var myform = document.getElementById("formAccesso");
		if (myform.email.value=="")
		{
			alert("Inserire l'email di registrazione.");
			return false;
		}
		document.location = "/site/croceitalia/password-dimenticata/"+myform.email.value+".";
	}
</script>



   