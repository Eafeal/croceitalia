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
            <h1>Create Anchor</h1>
            <form action="/edit/anchor/save" method="post" >
                <input type="hidden" id="tipoOggetto.uid" name="tipoOggetto.uid"  value="Anchor"   readonly="readonly" />
                <div class="dialog">
                    <table>
                        <tbody>
                             <tr>
                                <td>
                                  <label for="title">Title</label>
                                  <input type="text" id="title" name="title" size="80" maxlength="255" value="Title"  />
                                </td>
                            </tr>
                             <tr>
                                <td>
                                  <label for="uid">Codice</label>
                                  <input type="text" id="uid" name="uid" size="47" maxlength="45" value="Unique Id"  />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="description">Description</label>
                                    <input type="text" id="description" name="description" size="47" maxlength="45" value="describe the object"  />
                                </td>
                            </tr>
                           <tr>
                                <td>
                                    <label for="url">Url</label>
                                    <input type="text" id="url" name="url" size="120" maxlength="255"  value="http://www.assocons.it"  />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                  <label for="lang">Language</label>
                                  <input type="text" id="lang" name="lang" size="4" maxlength="2" value="${lang.getLanguage()}" />
                                </td>
                            </tr>
                            <tr> 
                              <td>State
                                <#assign state="WIP" />
                                <#include "/edit/states.ftl"  />
                              </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input type="submit" name="create" class="save" value="Create" id="create" /></span>
                </div>
            </form>
        </div>

    
    </body>
</html>