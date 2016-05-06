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
            <span class="menuButton"><a href="/edit/articolo/create" class="create">New Articolo</a></span>
        </div>
        <div class="body">
            <h1>Articolo List</h1>
            
            <div class="list">
                <table id="resultTable" >
                    <thead>
                        <tr>
                        	<th>#</th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Uid</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Lang</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Stato</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Title</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">Autore</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">Immagine</a></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as articolo >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/articolo/update/${articolo.id }">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${articolo.uid}</td>
	                            <td>${articolo.lang}</td>
	                            <td>${articolo.state}</td>
	                            <td>${articolo.testo.title}</td>
	                            <td>${articolo.testo.autore}</td>
	                            <#if articolo.image??  >
	                            	<td>${articolo.image.nomefile}</td>
	                            <#else>
	                            	<td>---</td>
	                            </#if>
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