<#if errore?? && errore=="errore" >
	<script>
	function closeErr()
		{
		document.getElementById("errore").style.visibility = "hidden";
		}
	</script>
	<div id="errore">
		Messaggio di errore:<br/>
		${errorMsg}
		<br/>
		<br/>
		<input type="button" class="btnOrange" value="CHIUDI" onclick="closeErr()" />
	</div>
</#if>