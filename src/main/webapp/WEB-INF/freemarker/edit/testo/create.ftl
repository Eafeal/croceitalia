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
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Create Testo</h1>
            
            <form action="/edit/testo/save" method="post" >
                <input type="hidden" name="tipoOggetto.uid" id="tipoOggetto.uid" value="Testo" readonly="readonly" />
                
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="title"  name="title" value="title" />
                                </td>
                            </tr>

                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="autore">Autore</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" name="autore" value="autore" id="autore" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lang">Language</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="lang" name="lang" size="4" maxlength="2" value="${lang.getLanguage()}" />
                                </td>
                            </tr>
                        
                            <tr> 
                              <td align="right" class="azzurro">State</td>
                              <td class="azzurro"> 
                              	<#assign state="WIP" />
                              	<#include "/edit/states.ftl"  />
                              </td>
                            </tr>
                            

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="testo">Inserisci il testo </label>
                                </td>
                                <td valign="top" class="value ">
                                    <textarea name="testo" id="testo" rows="20" cols="80" style="width: 80%"></textarea>
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