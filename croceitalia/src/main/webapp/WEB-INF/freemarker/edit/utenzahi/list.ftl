<#include "/${dominio}/includes/doctype.ftl" />

	<link rel="stylesheet"    href="/css/main.css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
	
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<meta name="layout" content="main"/>
	
	<#if title?? ><title>${title}</title></#if>
	
	<script type="text/javascript" src="/js/bubbleSort.js"></script>
	
	<script type="text/javascript">
	
		function ricarica(){
			
			if(window.opener!=null)
				{
				window.close();
				window.opener.location.reload(true);
				}
			}
	</script>

    </head>

    <body>
	
	<#include "/${dominio}/includes/header_admin.ftl" />


        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
		
        <!--div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
        </div-->
        <div>

            <form action="/edit/GestioneUtenti" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="cognome"><strong>Valore da ricercare:</strong></label>
                                    <input type="text" id="cognome" name="cognome" value="${cognome}" />
                                    <input type="submit" name="Trova" value="Trova" id="Trova" />
                                </td>								
                            </tr>
                    </table>
                </div>
            </form>
            
            <#if Lista?? >
            <div class="list">
                <table id="resultTable" width="95%">
                    <thead>
                        <tr>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Utente</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Nominativo</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">CF/P.Iva</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Mail</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">Telefono</a></th>
							<th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">Cellulare</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',7,0);">Stato</a></th>
                            <th>Blocca</th>
                            <th>Reset</th>
                            <th>Log</th>
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
	                            <td>${utente.userId}</td>
	                            <td>${utente.getNominativo()}</td>
	                            <td>${utente.getCdfiscPiva()}</td>
	                            <td>${utente.email}</td>
	                            <td>${utente.getTelefono()}</td>
								<td>${utente.getCellulare()}</td>
	                            <td>${utente.stato}</td>
	                        <#if utente.stato == "BLOCCATO" >
	                            <td><a href="#"><img src="/img/edit/blocca.gif" alt="Blocca" title="Blocca" style="float:left;clear:none;border:0" /></a></td>
	                        <#else>
	                            <td><a href="/edit/BloccaUtente/${utente.id}/${utente.userId}"><img src="/img/edit/blocca.gif" alt="Blocca" title="Blocca" style="float:left;clear:none;border:0" /></a></td>
	                        </#if>								
	                            <td><a href="/edit/ResetUtente/${utente.id}/${utente.userId}"><img src="/img/edit/reset.gif" alt="Sblocca" title="Sblocca" style="float:left;clear:none;border:0" /></a></td>
	                            <td><a href="/edit/VediLog/${utente.id}/${utente.userId}"><img src="/img/edit/lente.png" alt="Vedi Log" title="Vedi Log" style="float:left;clear:none;border:0" /></a></td>
	                        </tr>
	                    </#list>
                    </tbody>
                </table>
													
            </div>
			</#if>
			
            <div class="paginateButtons">
                
            </div>
        </div>
    
<div>
       
</body>
</html>