<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <#include "/edit/head.ftl"  />
    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
           <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
           <span class="menuButton"><a href="/edit/utente/list" class="list">User List</a></span>
           <span class="menuButton"><a href="/edit/utente/create" class="create">New User</a></span>
           <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Ricerca Utenze</h1>
            <form action="/edit/utente/list" method="post" >
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
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">User</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Ente/Riferimento</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Data creazione</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Data scadenza</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">Stato</a></th>
							<th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">Tipo</a></th>
							<th class="sortable" ></th>							
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as utente >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/utente/update/${utente.id }">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${utente.userId}</td>
	                            <td>${utente.ragioneSociale}<br/>Rif:${utente.nome}&nbsp;${utente.cognome}</td>
	                            <!--td>${utente.dominio.uid}</td>
	                            <td>${utente.soggetto.ruolo}</td-->
	                            <td>${utente.insertTime?string("dd-MM-yyyy hh:mm")}</td>
	                            <td>${utente.dataScadenza?string("dd-MM-yyyy")}</td>
	                            <td>${utente.stato}</td>
								<td>${utente.soggetto.id}</td>
								<td>
								    <form action="/edit/utente/delete/${utente.id}" method="get" >
									<span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
									</form>
									<span class="button"><input type="button" value="Documenti" class="delete" onclick="openDocument('${utente.userId}')" /></span>
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

<script>
function openDocument(id)
	{
	document.location = "/edit/documento/listForUser/"+id;
	}
</script>			