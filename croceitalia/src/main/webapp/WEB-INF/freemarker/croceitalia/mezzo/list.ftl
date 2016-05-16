<#include "head.ftl"  />
        
        <div class="body">
            <h1>Ricerca Mezzi</h1>
            <form action="/edit/mezzo/list" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name">
                                    <label for="cerca">Cerca:</label>
                                </td>
                                <td align="top" class="value ">
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
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Targa</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Tipo veicolo</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Descrizione</a></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as mezzo >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/mezzo/update/${mezzo.getId_mezzo()}">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${mezzo.getTarga()}</td>
	                            <td>${mezzo.getTipo_mezzo().getDescrizione()}</td>
	                            <td>${mezzo.getDescrizione()}</td>  
	                            <td>
								    <form action="/edit/mezzo/delete/${mezzo.getId_mezzo()}" method="get" >
									<span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
									</form>
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