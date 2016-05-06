	<#if ErrorMsg?? >
		<div class="boxed error-box">
		<span class="boxed-title error-title">ERRORE:</span>
		${ErrorMsg}
		</div>
	</#if>
	<#if UserMsg?? >	
		<div class="boxed alert-box" style="margin-top: 10px;">
		<span class="boxed-title alert-title">INFORMAZIONI</span>
		${UserMsg}
		</div>
	</#if>
	<#if OkMsg?? >		
		<div class="boxed success-box">
		<span class="boxed-title success-title">OPERAZIONE CONCLUSA CON SUCCESSO</span>
		${OkMsg}					
		</div>		
	</#if>