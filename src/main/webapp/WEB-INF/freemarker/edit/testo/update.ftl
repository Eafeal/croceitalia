<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <#include "/edit/head.ftl"  />
        
        <#include "/editor/tinyMCE.ftl"  />
    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
            <span class="menuButton"><a href="/edit/testo/list" class="list">Testo List</a></span>
            <span class="menuButton"><a href="/edit/testo/create" class="create">New Testo</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Edit Testo</h1>
            
            <form action="/edit/testo/doUpdate" method="post" >
                <input type="hidden" name="id" value="${oggetto.id}" id="id" />
                <input type="hidden" name="tipoOggetto.uid" id="tipoOggetto.uid" value="Testo" readonly="readonly" />
                
                <div class="dialog">
                    <table>
                        <tbody>
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
                                  <label for="autore">autore</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" name="autore" value="${oggetto.autore}" id="autore" size="50" maxlength="45" />
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
                              	<#assign state=oggetto.state />
                              	<#include "/edit/states.ftl"  />
                              </td> 
                            </tr>
						    
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="testo">Inserisci il testo </label>
                                </td>
                                <td valign="top" class="value ">
                                    <textarea name="testo" id="testo" rows="40" cols="80" style="width: 80%">${oggetto.getTesto()}</textarea>
                                </td>
                            </tr>
                            
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                    <span class="button"><input type="submit" value="Update" class="edit" /></span>
            </form>
            <form action="/edit/testo/delete/${oggetto.id}" method="get" >
                    <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
                    <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica();" /></span>
             </form>
             </div>
    
        </div>
    
    </body>
</html>