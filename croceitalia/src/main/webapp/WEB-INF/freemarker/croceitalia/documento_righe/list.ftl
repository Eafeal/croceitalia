

 <#include "head.ftl"  />

        
        <div class="body">
            <h1>Ricerca Documento Righe</h1>
            <form action="/edit/documento_righe/list" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cerca">Cerca:</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="cerca" name="cerca" />
                                </td>
                            </tr>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input type="submit" name="search" class="list" value="search" id="search" /></span>
                </div>
            </form>
            
            <#if Lista?? >
            <div class="list">
                <table id="resultTable" >
                    <thead>
                        <tr>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Nominativo</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Contatti</a></th>
                            <th>#</th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as documento >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td>${documento.getId_documento_righe()}</td>
	                            <td>${documento.getNum_sedute()}</td>
	                            <td>
								    <a href="/edit/documento_righe/update/${documento.getId_documento_righe()}"><img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a>
								    <a href="/edit/documento_righe/delete/${documento.getId_documento_righe()}"><img src="/img/edit/cancella_on.gif" alt="Cancella"  style="float:left;clear:none;border:0" onclick="return confirm('Confermi la cancellazione?');" /></a>
								</td>
	                        </tr>
	                    </#list>
                    </tbody>
                </table>
            </div>
			</#if>
			
            <div class="paginateButtons">
                
            </div>
        </div>
    
    </body>
</html>
<#if messaggio??>
<script>
alert("${messaggio}");
</script>
</#if>				