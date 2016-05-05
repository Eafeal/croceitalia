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
           <span class="menuButton"><a href="/edit/photoGallery/list" class="list">Photo Gallery List</a></span>
           <span class="menuButton"><a href="/edit/photoGallery/create" class="create">New Photo Gallery</a></span>
           <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Edit Photo Gallery</h1>
            
            <form action="/edit/photoGallery/doUpdate" method="post" >
                <input type="hidden" id="tipoOggetto.uid" name="tipoOggetto.uid"  value="${photoGallery.tipoOggetto.uid}" readonly="readonly"   />
                <input type="hidden" name="id" value="${photoGallery.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="uid" name="title"  size="80" maxlength="255" value="${photoGallery.title}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="description" name="description" size="80" maxlength="255" value="${photoGallery.description}"  />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="autore">Author</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="autore" name="autore" size="30" maxlength="45" value="${photoGallery.autore}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lang">Language</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="lang" name="lang" size="4" maxlength="2" value="${photoGallery.lang}" />
                                </td>
                            </tr>
                            
                            <tr> 
                              <td align="right" class="azzurro">State</td>
                              <td class="azzurro"> 
                              	<#assign state=photoGallery.state   />
                              	<#include "/edit/states.ftl"        />
                              </td>
						    </tr>

                        </tbody>
                    </table>
            </div>
            <div class="buttons">
	            <span class="button"><input type="submit" value="Update" class="edit" /></span>
	            </form>
	            <form action="/edit/photoGallery/delete/${photoGallery.id}" method="get" >
	                    <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
	                    <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
	             </form>
             </div>
    
	        <#assign padre=photoGallery />
	        <#include "/edit/relazioni/rels.ftl"  />
                
        </div>
    </body>
</html>