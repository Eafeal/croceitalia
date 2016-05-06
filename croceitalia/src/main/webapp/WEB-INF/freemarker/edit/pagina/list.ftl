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
            <span class="menuButton"><a href="/edit/pagina/list" class="list">Pagina List</a></span>
            <span class="menuButton"><a href="/edit/pagina/create" class="create">New Pagina</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Pagina List</h1>
            
            <div class="list">
                <table id="resultTable" >
                    <thead>
                        <tr>
                            <th>#</th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Dominio</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Uid</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Lang</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Title</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">Descrizione</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">Keywords</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',7,0);">Url</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',8,0);">State</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',9,0);">Render Page</a></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as pagina >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/pagina/update/${pagina.id }">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${pagina.dominio.uid}</td>
	                            <td>${pagina.uid}</td>
	                            <td>${pagina.lang}</td>
	                            <td>${pagina.title}</td>
	                            <td>${pagina.description}</td>
	                            <td>${pagina.keywords}</td>
	                            <td><a href="${pagina.url}" target="_new" >${pagina.url}</a></td>
	                            <td>${pagina.state}</td>
	                            <td>${pagina.renderPage}</td>
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