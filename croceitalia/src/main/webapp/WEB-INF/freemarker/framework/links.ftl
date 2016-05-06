<span style="float:none;display:inline" >
	<#if utente?? && utente.isAdmin() && oggetto?? >
    		Modifica ${oggetto.tipoOggetto.tipo}: ${oggetto.uid}
		<#if oggetto.inLavorazione()  >
			<a href="#"><img src="/img/edit/lav_in_corso.gif" alt="In Lavorazione" style="display:inline;float:left;clear:none;border:0"  /> </a>
		</#if>
		<a href="javascript:addObject('${oggetto.tipoOggetto.tipo}','${oggetto.id}' )">
			<img src="/img/edit/ins.gif" alt="Add Object" style="display:inline;float:left;clear:none;border:0"  />
		</a>
		<a href="javascript:modObject('${oggetto.tipoOggetto.editPath}','${oggetto.id}' )">
			<img src="/img/edit/mod.gif" alt="Edit" style="display:inline;float:left;clear:none;border:0"  />
		</a>
		<a href="javascript:deleteObject('${pagina.uid?html}', '${oggetto.tipoOggetto.editPath}','${oggetto.id}' )">
			<img src="/img/edit/cancella_on.gif" alt="Delete" style="display:inline;float:left;clear:none;border:0"  />
		</a>
	<#else>
		&nbsp;
	</#if>
</span>
