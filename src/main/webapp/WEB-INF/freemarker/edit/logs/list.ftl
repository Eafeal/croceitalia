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
        </div>
        <div class="body">
            <h1>Ricerca Logs</h1>
            <form action="/edit/logs/list" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startTime">startTime</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="startTime" name="startTime"  />
                                </td>
                            </tr>
							<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endTime">endTime</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="endTime"  id="title" />
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
                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">insertTime</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">user_id</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">ipFromRequest</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">code</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">result</a></th>
                            <th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">message</a></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <#assign i=0 />
	                    <#list Lista as log >
	                        <#assign i=i+1 />
	                        <#if (i % 2) == 0 >
	                            <#assign classe="odd" />
	                        <#else>
	                            <#assign classe="even" />
	                        </#if>
	                        <tr class="${classe}">
	                            <td><a href="/edit/logs/update/${log.id}">${i}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${log.insertTime}</td>
	                            <td><a href="/edit/utenzahi/dettaglio/${log.user_id}">${log.user_id}<img src="/img/edit/mod.gif" alt="Modifica"  style="float:left;clear:none;border:0" /></a></td>
	                            <td>${log.ipFromRequest}</td>
	                            <td>${log.code}</td>
	                            <td>${log.result}</td>
	                            <td>${log.message}</td>
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