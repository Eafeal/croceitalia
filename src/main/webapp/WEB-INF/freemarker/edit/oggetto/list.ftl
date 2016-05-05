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
            <span class="menuButton"><a href="/edit/oggetto/create" class="create">New Oggetto</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Oggetto List</h1>
            
            <div class="list">
                <table id="resultTable" >
                    <thead>
                        <tr>
                        	<th>#</th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Id</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Uid</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Title</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Descrizione</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">Language</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">State</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',7,0);">Tipo</a></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list ListaOggetti as oggetto >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/oggetto/update/${oggetto.id  }">${i}</a></td>
	                            <td>${oggetto.id }</td>
	                            <td>${oggetto.uid}</td>
	                            <td>${oggetto.title}</td>
	                            <td>${oggetto.description}</td>
	                            <td>${oggetto.lang}</td>
	                            <td>${oggetto.state}</td>
	                            <td>${oggetto.tipoOggetto.tipo}</td>
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