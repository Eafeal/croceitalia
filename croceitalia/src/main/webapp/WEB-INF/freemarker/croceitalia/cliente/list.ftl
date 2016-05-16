<#include "head.ftl"  />
        
        <div class="body">
            <h1>Ricerca Clienti</h1>
            <form action="/edit/cliente/list" method="post" >
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
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Ragione sociale</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Partita IVA</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Comune</a></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as cliente >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/cliente/update/${cliente.getId_cliente()}">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${cliente.getRagione_sociale()}</td>
	                            <td>${cliente.getP_iva()}</td>
	                            <td>${cliente.getComune()}</td>
	                            <td>
								    <form action="/edit/cliente/delete/${cliente.getId_cliente()}" method="get" >
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