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
            <span class="menuButton"><a href="/edit/oggetto/list" class="list">Oggetto List</a></span>
            <span class="menuButton"><a href="/edit/oggetto/create" class="create">New Oggetto</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Edit Oggetto</h1>
            
            
            <form action="/edit/oggetto/doUpdate" method="post" >
                <input type="text" name="id" value="${oggetto.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
                        	
                        	<tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tipoOggetto.uid">Tipo Oggetto</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="hidden" id="tipoOggetto.uid" name="tipoOggetto.uid" value="${oggetto.tipoOggetto.uid}"  />
                                    <input type="hidden" id="tipoOggetto.tipo" name="tipoOggetto.tipo" value="${oggetto.tipoOggetto.tipo}"  />
                                    <input type="hidden" id="tipoOggetto.editPath" name="tipoOggetto.editPath" value="${oggetto.tipoOggetto.editPath}"  />
                                    ${oggetto.tipoOggetto.uid}
                                </td>
                            </tr>
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="uid">uid</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="uid"  name="uid" value="${oggetto.uid}" size="45" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="title">Title</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="title" name="title" value="${oggetto.title}" size="50" maxlength="45" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description">description</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="description" value="${oggetto.description}" id="description" size="45" />
                                </td>
                            </tr>
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lang">Language</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="lang" value="${oggetto.lang}" id="lang" />
                                </td>
                            </tr>

                            <tr> 
                              <td align="right" class="azzurro">State</td>
                              <td class="azzurro"> 
                                <#assign state=oggetto.state        />
                                <#include "/edit/states.ftl"  />
                              </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lang">Code</label>
                                </td>
                                <td valign="top" class="value ">
                                	<textarea id="code"  name="code" rows="6" cols="40">${oggetto.code}</textarea>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                    <span class="button"><input type="submit" value="Update" class="edit" /></span>
            </form>
            <form action="/edit/oggetto/delete/${oggetto.id}" method="get" >
                    <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
                    <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica();" /></span>
             </form>
             </div>
             
             
        <#assign padre=oggetto />
        <#include "/edit/relazioni/rels.ftl"  />
                
        </div>
    
    </body>
</html>