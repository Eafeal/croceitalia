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
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Create Pagina</h1>
            
            
            <form action="/edit/pagina/save" method="post" >
            
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="uid">uid</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="uid" value="uid" id="uid" />
                                </td>
                            </tr>
							<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Titolo</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="title" value="title" id="title" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Descrizione</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="description" value="description" id="description" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="url">Url</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="url" value="url" id="url" />
                                </td>
                            </tr>

                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="keywords">Keywords</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="keywords" value="key1..keyn" id="keywords" />
                                </td>
                            </tr>
                            <tr> 
                              <td align="right" class="azzurro">State</td>
                              <td class="azzurro"> 
                              	<#assign state="WIP" />
                              	<#include "/edit/states.ftl"  />
                              </td>
						    </tr>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input type="submit" name="create" class="save" value="Create" id="create" /></span>
                </div>
            </form>
        </div>

    
    </body>
</html>