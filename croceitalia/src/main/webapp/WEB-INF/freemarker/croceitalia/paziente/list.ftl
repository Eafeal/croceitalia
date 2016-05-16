 <#include "head.ftl"  />

        
        <div class="body">
            <h1>Ricerca Pazienti</h1>
            <form action="/edit/paziente/list" method="post" >
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
                            <th>#</th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Nominativo</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Contatti</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Patologia</a></th>
                            <th class="sortable" ></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as paziente >
	                    <#list patologia as pato >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/paziente/update/${paziente.getId_paziente()}">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${paziente.getCognome()}&nbsp;${paziente.getNome()}</td>
	                            <td>${paziente.getTelefono1()} - ${paziente.getTelefono2()}</td>
	                            <td>${pato.descrizione} </td>
	                            <td>
								    <form action="/edit/paziente/delete/${paziente.id_paziente}" method="get" >
									<span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
									</form>
								</td>
	                        </tr>
	                    </#list>
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

<script>
function openDocument(id)
	{
	document.location = "/edit/documento/listForUser/"+id;
	}
</script>			