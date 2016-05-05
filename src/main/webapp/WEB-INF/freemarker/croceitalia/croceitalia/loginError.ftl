
<#assign sezione = "Generazione xxxxxxxx">
<#assign curr_gen = "current">
<#assign curr_val = "">
<#assign slide = "slide4">
<#include "/${dominio}/includes/header_new.ftl">
<#include "/${dominio}/includes/sidebar.ftl">
			
		<div id="content">

			<h1>Login</h1>
			<div class="loginform">
			  <form method="post" action="/site/croceitalia/genera/login"> 
				<p><input type="hidden" name="rememberme"   value="0" /></p>
				<p><input type="hidden"   name="requestedUri" value="${requestedUri}" /></p>
				<fieldset>
				  <p><label for="userId" class="top">User:</label><br />
					<input type="text" name="userId" id="userId" tabindex="1" class="field" value="" />
				  </p>
				  <p><label for="password" class="top">Password:</label><br />
					<input type="password" name="password" id="password" tabindex="2" class="field" value="" />
				  </p>
				  <p><input type="submit" class="button" value="LOGIN"  /></p>
				  <!-- p><a href="#" id="forgotpsswd_1">Password forgotten?</a></p -->
					<#if loginMessage?? >
						<h3>Errore: ${loginMessage}</h3>
					</#if>				  
				</fieldset>
			  </form>
			</div>
			
    	</div>

		<div class="clearfloat"></div>
 
<#include "/${dominio}/includes/footer_new.ftl">

<script>
function js_controllo_allegato()
{

path=document.UploadForm.file.value;

if (path=="")
	{
	alert("Specificare il file da elaborare.");
	return false;
	}
	
posizione_punto=path.lastIndexOf(".");
lunghezza_stringa=path.length;
estensione=path.substring(posizione_punto+1,lunghezza_stringa);

if (estensione.toUpperCase()=="XLS")
	{
	return true;
	}	

alert("Specificare un file in formato excel 2003.");	
return false;
}
</script>



   