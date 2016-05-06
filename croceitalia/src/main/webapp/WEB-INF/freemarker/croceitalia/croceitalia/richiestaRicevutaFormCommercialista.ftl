
<#assign sezione = "Richiesta ricevuta Sistema TS">
<#assign curr_gra = "">
<#assign curr_gen = "current">
<#assign curr_val = "">
<#assign curr_rev = "">
<#assign slide = "slide5">
<#include "/${dominio}/includes/header_new.ftl">

<script src="/js/cookie.js" type="text/javascript"></script>
<script src="/js/validation.js" type="text/javascript"></script>

<div id="wait">
  <div id="loading">
    Attendere prego...
  </div>
</div>

<!--#include "/${dominio}/includes/sidebar.ftl"-->

		<div id="content" class="admin">
		<#if utente.isLogged()>		
		
			<div class="loginform">
			<form method="post" action="/site/logout"> 
			<p style="margin:0"><input type="hidden" name="requestedUri" value="${requestedUri}" /></p>
			<fieldset style="border: 0; border-bottom: 1px solid #999; padding: 5px 0; margin-bottom: 30px; font-size: 16px;">
				<span style="display: inline-block;">
                Utente: <strong>${utente.cognome} ${utente.nome}</strong>
                <br />
				Profilo: <strong>${utente.getSoggettoUtente().getDescription()}</strong>
                </span>
				<input type="submit"  class="button" value="Esci" style="float: right;" />
			</fieldset>
			</form>
			</div>
			
			<#include "/${dominio}/includes/bottoniera.ftl">
			
		 	<div>
			
			<#include "/${dominio}/includes/messaggi.ftl">
			
			<#assign nomefile= outFile.nomefile/>					
			<#assign urlInvio= "/site/croceitalia/invia/xml/ricevuta" + outFile.id />								
			<#assign url= "/site/documento/get/" + outFile.id />			
			
			<form class="myform" id="formInvio" name="formInvio" action="/site/croceitalia/richiesta/ricevutacommercialista/execute" method="post">
			
				<!--input type="hidden" name="requestedUri" value="${requestedUri}" /-->			
				<input type="hidden" id="idFile" name="idFile" value="${outFile.id}" />
				<!--input type="text" id="parent_id" name="parent_id" value="${utente.getCodiceFiscale()}" /-->				
			
			  <p>
				<label for="user">User</label>
				<input type="text" class="input" id="user" name="user" value="" placeholder="${userInvio}"/>
			  </p>			  
			  <p>
				<label for="password">Password</label>
				<input type="password" class="input" id="password" name="password" value=""  placeholder="${passwordInvio}"/>
			  </p>
			  <p>
				<label for="pin">Pincode</label>
				<input type="text" class="input" id="pin" name="pin" value=""  placeholder="${pinCodeInvio}" />
			  </p>			  
			  <p>
				<label for="pin">Sede</label>
				<input type="text" class="input" id="sede" name="sede" value=""  placeholder="${sede}" />
			  </p>			  
			  <p>
				<label for="cf">Codice Fiscale medico</label>
				<input type="text" class="input" id="cf" name="cf" value=""  placeholder="${cfPropietario}" />
			  </p>
			  <p>
				<input type="button" class="button" value="RICHIESTA RICEVUTA" onclick="controlli()" />
			  </p>
			  <div class="clearfloat"></div>	
			</form>
			
			<br><br>
			
			</div>
		</#if>		
			
    	</div>

	<div class="clearfloat"></div>
 
<#include "/${dominio}/includes/footer_new.ftl">


<script>

function controlli()
	{
	if (!(ctrlRequired() && controlliFormali())) return false;

	showLoader();
	document.formInvio.submit();
	}
	
function showLoader()
	{
	document.getElementById("wait").style.display = "block";
	}

function hideLoader()
	{
	document.getElementById("wait").style.display = "none";
	}

	
function ctrlRequired()
	{
	var myform = document.getElementById("formInvio");
	var result = true;
	result = result && checkRequiredInput(myform.cf, "Codice Fiscale ");
	result = result && checkRequiredInput(myform.pin, "Pincode ");
	result = result && checkRequiredInput(myform.password, "Password ");
	result = result && checkRequiredInput(myform.sede, "Sede ");
	result = result && checkRequiredInput(myform.user, "User ");
	return result;
	}
	
function controlliFormali()
	{
	var myform = document.getElementById("formInvio");
	
	if(!isValidCF(myform.cf))
		return false;
		
	//if (myform.cf.value != myform.cfCheck.value)
	//{
	//	alert("Il codice fiscale digitato non coincide con quello inserito in fase di registrazione sul ns sito.\nImpossibile proseguire");
	//	return false;
	//}
	// definire controllo pin
	
	SetCookie("pin", myform.pin.value,null,"/");
	SetCookie("pwd", myform.password.value,null,"/");
	SetCookie("user", myform.user.value,null,"/");	
	SetCookie("sede", myform.sede.value,null,"/");	
	SetCookie("cf", myform.cf.value,null,"/");	
	return true;
	}

	myform = document.getElementById("formInvio");	
	myform.pin.value = GetCookie("pin");
	myform.password.value = GetCookie("pwd");
	myform.user.value = GetCookie("user");
	myform.sede.value = GetCookie("sede");
	myform.cf.value = GetCookie("cf");
</script>
	



   