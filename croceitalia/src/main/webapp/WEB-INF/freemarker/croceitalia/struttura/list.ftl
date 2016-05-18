 <#include "head.ftl"  />

        
        <div class="body">
            <h1>Ricerca Struttura</h1>
            <form action="/edit/struttura/list" method="post" >
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
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Nome Struttura</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Contatti </a></th>
                            <th>#</th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as struttura >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td>${struttura.getNome()}</td>
	                            <td>${struttura.getTelefono()}</td>
                             	 <td>
								    <a href="/edit/struttura/update/${struttura.getId_struttura()}"><img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a>
								    <a href="/edit/struttura/delete/${struttura.getId_struttura()}"><img src="/img/edit/cancella_on.gif" alt="Cancella"  style="float:left;clear:none;border:0" onclick="return confirm('Confermi cancellazione?');" /></a>
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