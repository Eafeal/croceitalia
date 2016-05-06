<#if utente.isLogged() >
	<h1>Welcome</h1>
	<div class="loginform">
	    <form method="post" action="/site/logout"> 
	    <p><input type="hidden" name="requestedUri" value="${requestedUri}" /></p>
	    <fieldset>
	 		<p>${utente.nome}&nbsp;${utente.cognome} </p>
	 		<p><input type="submit"  class="button" value="LOGOUT"  /></p>
	    </fieldset>
	  </form>
	</div>
<#else>
	<h1>Login</h1>
	<div class="loginform">
	  <form method="post" action="/site/login"> 
	    <p><input type="hidden" name="rememberme"   value="0" /></p>
	    <p><input type="hidden"   name="requestedUri" value="${requestedUri}" /></p>
	    <fieldset>
	    	<#if loginMessage?? >
	    		<h1>Errore: ${loginMessage}</h1>
	    	</#if>
	      <p><label for="userId" class="top">User:</label><br />
	        <input type="text" name="userId" id="userId" tabindex="1" class="field" value="" />
	      </p>
	      <p><label for="password" class="top">Password:</label><br />
	        <input type="password" name="password" id="password" tabindex="2" class="field" value="" />
	      </p>
	      <p><input type="submit" class="button" value="LOGIN"  /></p>
	      <!-- p><a href="#" id="forgotpsswd_1">Password forgotten?</a></p -->
	    </fieldset>
	  </form>
	</div>
</#if>