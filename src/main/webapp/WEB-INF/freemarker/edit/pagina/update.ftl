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
            <h1>Edit Pagina</h1>
            
            
            <form action="/edit/pagina/doUpdate" method="post" >
                <input type="hidden" name="id" value="${pagina.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="id">Id</label>
                                </td>
                                <td valign="top" class="value ">
                                    ${pagina.id}
                                </td>
                            </tr>                   
                             <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="uid">uid</label>

                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="uid" value="${pagina.uid}" id="uid" size="45" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lang">lang</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="lang" value="${pagina.lang}" id="lang" size="4" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="title">title</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="title" value="${pagina.title}" id="title" size="120"  maxlength="120"  />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description">Descrizione</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="description" value="${pagina.description}" id="description" size="120"  maxlength="120"  />
                                </td>
                            </tr>
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="url">url</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="url" value="${pagina.url}" id="url" size="120"  maxlength="120" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="keywords">keywords</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="keywords"  name="keywords" value="${pagina.keywords}" size="120"  maxlength="120" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="renderPage">Render Page</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="renderPage"  name="renderPage" value="${pagina.renderPage}" size="50"  maxlength="55" />
                                </td>
                            </tr>
                            
                            <tr> 
                              <td align="right" class="azzurro">State</td>
                              <td class="azzurro"> 
                              	<#assign state=pagina.state   />
                              	<#include "/edit/states.ftl"  />
                              </td>
						    </tr>
						    
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
            			<span class="button"><input type="submit" value="Update" class="edit" /></span>
	            </form>
	            
	            <form action="/edit/pagina/duplicate/${pagina.id}" method="GET" >
	                    <span class="button"><input type="submit" value="Duplicate" class="duplicate"   /></span>
	            </form>
	             
	            <form action="/edit/pagina/delete/${pagina.id}" method="GET" >
	                    <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
	             </form>

	             <form action="#" method="GET" >
	                    <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica();" /></span>
	             </form>
             </div>

            <#assign padre=pagina />
            <#include "/edit/relazioni/rels.ftl"  />
            
		</div>
    </body>
</html>