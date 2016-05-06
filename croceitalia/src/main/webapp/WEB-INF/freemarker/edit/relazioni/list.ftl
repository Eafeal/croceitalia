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
            <span class="menuButton"><a href="/edit/relazioni/create" class="create">New Relazioni</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Relazioni List</h1>
            
            <div class="list">
                <table id="resultTable" >
                    <thead>
                        <tr>
                            <th>#</th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Dominio</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Uid</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Tipo Padre</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Padre</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">Tipo Figlio</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">Figlio</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',7,0);">Ordine</a></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Relazioni as relazione >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/relazioni/update/${relazione.uid }">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${relazione.dominio.uid}</td>
	                            <td>${relazione.uid}</td>
	                            <td>${relazione.tipopadre_id}</td>
	                            <td>${relazione.padre_id}</td>
	                            <td>${relazione.tipofiglio_id}</td>
	                            <td>${relazione.figlio_id}</td>
	                            <td>${relazione.ordine}</td>
	                        </tr>
	                    </#list>
                    </tbody>
                </table>
            </div>

            <div class="paginateButtons">
                
            </div>
        </div>
    
    </body>
</html>