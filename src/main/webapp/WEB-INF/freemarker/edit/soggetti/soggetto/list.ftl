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
            <span class="menuButton"><a href="/edit/soggetti/soggetto/create" class="create">New Soggetto</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Soggetto List</h1>
            
            <div class="list">
                <table id="resultTable" >
                    <thead>
                        <tr>
                            <th>#</th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">Cognome</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Nome</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Sesso</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Data Nascita</a></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as soggetto >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/soggetti/soggetto/update/${soggetto.id  }">${i}</a></td>
	                            <td>${soggetto.cognome}</td>
	                            <td>${soggetto.nome}</td>
	                            <td>${soggetto.sesso}</td>
	                            <td>${soggetto.nascita?string("0") }</td>
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