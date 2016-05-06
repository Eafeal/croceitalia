
<#assign sezione = "Invio XML a Sistema TS">
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
			<#assign urlInvio= "/site/croceitalia/invia/xml/execute" + outFile.id />								
			<#assign url= "/site/documento/get/" + outFile.id />			
			
			Stai per inviare il file <strong>${outFile.nomefile}</strong> appena generato......<br>
			
			Per poter procedere all'invio del flusso devi essere già stato accreditato su sistema TS, ed avere a portata di mano il tuo codice fisclae, il pincode e la password di accesso che ti sono state rilasciate.<br>
			<br/>Se non ti sei ancora accreditato non è possibile procedere con l'invio del file; rivolgiti al tuo ordine o <strong><a href="#">clicca qui</a></strong> per accedere al servizio di accreditamento di tessera sanitaria.<br> 
			<br>
			<form class="myform" id="formInvio" name="formInvio" action="/site/croceitalia/invia/xmlstruttura/execute" method="post" onSubmit="javascript:controlli()">
				<!--input type="hidden" name="requestedUri" value="${requestedUri}" /-->			
				<input type="hidden" id="idFile" name="idFile" value="${outFile.id}" />
				<!--input type="text" id="parent_id" name="parent_id" value="${utente.getCodiceFiscale()}" /-->				
			  <p>
				<label for="regione">Codice regione struttura</label>
				<input type="text" class="input" id="regione" name="regione" value="" placeholder="${codiceRegioneInvio}" maxlength="3"/>
			  </p>
			  <p>
				<label for="asl">Codice ASL struttura</label>
				<input type="text" class="input" id="asl" name="asl" value="" placeholder="${codiceAslInvio}" maxlength="3"/>
			  </p>			  
			  <p>
				<label for="ssa">Codice struttura</label>
				<input type="text" class="input" id="ssa" name="ssa" value="" placeholder="${codiceSSAInvio}" maxlength="6"/>
			  </p>			  
			  <p>
				<label for="cf">Codice Fiscale propietario</label>
				<input type="text" class="input" id="cf" name="cf" value="${utente.getCodiceFiscale()}" maxlength="16"/ readonly>
				<input type="hidden" id="cfCheck" name="cfCheck" value="${utente.getCodiceFiscale()}" />				
			  </p>
			  <p>
				<label for="user">User</label>
				<input type="text" class="input" id="user" name="user" value="" placeholder="${userInvio}" maxlength="20"/>
			  </p>
			  <p>
				<label for="pin">Pincode Tessera sanitaria</label>
				<input type="text" class="input" id="pin" name="pin" value="" placeholder="${pinCodeInvio}" maxlength="10" />
			  </p>			  
			  <p>
				<label for="password">Password Tessera sanitaria</label>
				<input type="password" class="input" id="password" name="password" value="" placeholder="${passwordInvio}" maxlength="20"/>
			  </p>
			  <p>
				<input type="button" class="button" value="INVIA FILE" onclick="controlli()" />
			  </p>
			  <div class="clearfloat"></div>	
			</form>
			<#if utente.isProva()>
				<br>
				<font color=red>
				<strong>		
				Funzione disabilitata per la prova gratuita.
				</strong>
				</font>
				<br><br>
				<strong>Per informazioni relative all'acquisto della licenza d'uso <a href="/contatti.html">contattateci.</a></strong></p>
				<br><br>
			</#if>		
					
			<!--br/>Se hai dimenticato user e/o password <a href="/site/croceitalia/password-dimenticata">Clicca qui per recuperare le tue credenziali di accesso</a-->
			
			</div>
		</#if>		
			
    	</div>

	<div class="clearfloat"></div>
 
<#include "/${dominio}/includes/footer_new.ftl">


<script>

function controlli()
	{
	if (!(ctrlRequired() && controlliFormali())) return false;
	<#if utente.isProva()>
	alert("Funzione disabilitata");
	<#else>
	showLoader();
	document.formInvio.submit();
	</#if>	
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
	result = result && checkRequiredInput(myform.regione, "Codice regione struttura ");
	result = result && checkRequiredInput(myform.asl, "Codice ASL struttura ");
	result = result && checkRequiredInput(myform.ssa, "Codice struttura ");
	result = result && checkRequiredInput(myform.cf, "Codice Fiscale ");
	result = result && checkRequiredInput(myform.user, "User ");
	result = result && checkRequiredInput(myform.pin, "Pincode ");
	result = result && checkRequiredInput(myform.password, "Password ");
	return result;
	}
	
function controlliFormali()
	{
	var myform = document.getElementById("formInvio");
	var result = true;
	
	if (myform.regione.value=="" || !isValidCod3(myform.regione.value))
		{
		alert("Il Codice regione struttura è un codice numerico di 3 cifre");
		myform.regione.focus();
		return false;
		}
	if (myform.asl.value=="" || !isValidCod3(myform.asl.value))
		{
		alert("Il Codice ASL struttura è un codice numerico di 3 cifre");
		myform.asl.focus();
		return false;
		}
	if(myform.ssa.value=="" || !isValidNumber(myform.ssa.value) || myform.ssa.value.length < 5 )
		{
		alert("Il Codice struttura è un valore numerico di 5 o 6 cifre");
		myform.ssa.focus();
		return false;
		}
	if(!isValidCF(myform.cf))
		return false;
		
	//if (myform.cf.value != myform.cfCheck.value && myform.cf.value != "${cfInvio}")
	//{
	//	alert("Il codice fiscale digitato non coincide con quello inserito in fase di registrazione sul ns sito.\nImpossibile proseguire");
	//	return false;
	//}
	// definire controllo pin
	
	if (myform.pin.value=="" || !isValidCod10(myform.pin.value))
		{
		alert("Il Pincode è un codice numerico di 10 cifre");
		myform.pin.focus();
		return false;
		}
	
	if (myform.password.value=="" || !isValidAlphanumeric(myform.password.value))
		{
		alert("Il campo Password deve essere un valore alfa-numerico");
		myform.password.focus();
		return false;
		}
	if (myform.user.value=="" || !isValidAlphanumeric(myform.user.value))
		{
		alert("Il campo User deve essere un valore alfa-numerico");
		myform.user.focus();
		return false;
		}

	SetCookie("pin", myform.pin.value,null,"/");
	SetCookie("pwd", myform.password.value,null,"/");
	SetCookie("user", myform.user.value,null,"/");	
	SetCookie("regione", myform.regione.value,null,"/");	
	SetCookie("asl", myform.asl.value,null,"/");	
	SetCookie("ssa", myform.ssa.value,null,"/");				

		
	return true;
	}

	myform = document.getElementById("formInvio");	
	myform.pin.value = GetCookie("pin");
	myform.password.value = GetCookie("pwd");
	myform.user.value = GetCookie("user");
	myform.regione.value = GetCookie("regione");
	myform.asl.value = GetCookie("asl");
	myform.ssa.value = GetCookie("ssa");
	
</script>
	



   