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
           <span class="menuButton"><a href="/edit/anchor/list" class="list">Anchor List</a></span>
           <span class="menuButton"><a href="/edit/anchor/create" class="create">New Anchor</a></span>
           <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Edit Anchor</h1>
            
            <form action="/edit/anchor/doUpdate" method="post" >
                <input type="hidden" id="tipoOggetto.uid" name="tipoOggetto.uid"  value="${anchor.tipoOggetto.uid}" readonly="readonly"   />
                <input type="hidden" name="id" value="${anchor.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
               
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="title" name="title"  size="80" maxlength="255" value="${anchor.title}"   />
                                </td>
                            </tr>
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="uid">Codice</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="uid" name="uid" size="47" maxlength="45" value="${anchor.uid}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="description" name="description" size="47" maxlength="45" value="${anchor.description}" />
                                </td>
                            </tr>
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="url">Url</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="url" name="url" size="120" maxlength="255"  value="${anchor.url}"  />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lang">Language</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="lang" name="lang" size="4" maxlength="2" value="${anchor.lang}" />
                                </td>
                            </tr>
                            <tr> 
                              <td align="right" class="azzurro">State</td>
                              <td class="azzurro"> 
                                <#assign state=anchor.state   />
                                <#include "/edit/states.ftl"  />
                              </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="style">Style</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="style" name="style" size="120" maxlength="255"  value="${anchor.style}"  />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
	            <span class="button"><input type="submit" value="Update" class="edit" /></span>
	            </form>
	            <form action="/edit/anchor/delete/${anchor.id}" method="get" >
	                    <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
	                    <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica()" /></span>
	             </form>
             </div>
    
        <#assign padre=anchor />
        <#include "/edit/relazioni/rels.ftl"  />
                
        </div>
    
    </body>
</html>