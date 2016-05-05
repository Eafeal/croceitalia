<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <#include "/edit/head.ftl"  />
        
        <style type="text/css" media="screen">
			<!--
			@import url("/css/jquery/jquery-ui.css");
			-->
		</style>
		<script language="JavaScript" type="text/javascript" src="/js/jquery/jquery.min.js"></script>
		<script language="JavaScript" type="text/javascript" src="/js/jquery/jquery-ui.min.js"></script>
		<script language="JavaScript" type="text/javascript" src="/js/jquery/hoverIntent.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/jquery.bgiframe.min.js"></script>
		<script language="JavaScript" type="text/javascript" src="/js/jquery/superfish.js"></script>
		<script language="JavaScript" type="text/javascript" src="/js/jquery/jquery.autocomplete.js"></script>
		<script language="JavaScript" type="text/javascript" src="/js/jquery/date.js"></script>

<script type="text/javascript"> 
	
 $(document).ready(
            function(){

                $("#updForm").submit(function() {

                	errMsg = errMsgDefault = "Attenzione!!\n";
            		$(".errorTextField").removeClass('errorTextField');

            		if (errMsg == errMsgDefault){
                		return confirm("Confermi aggiornamento?");
            		} else {
            			alert(errMsg);
            			return false;
            		}
            		
                 });
                
    		//FINE (document).ready
            });
		            
       </script>

    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
            <span class="menuButton"><a href="/edit/documento/list" class="list">Documento List</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Edit</h1>
            <form id="updForm" name="updForm" action="/edit/documento/doUpdate" method="post" >
                <input type="hidden" name="id" value="${documento.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="uid" name="title"  size="80" maxlength="255" value="${documento.title}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="description" name="description" size="80" maxlength="255" value="${documento.description}"  />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name" >
                                    <label for="autore">Author</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="autore" name="autore" size="30" maxlength="45" value="${documento.autore}" />
                                </td>
                            </tr>


                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nomefile">Nome File</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="nomefile" name="nomefile" value="${documento.nomefile}"  size="64" maxlength="255" />
                                </td>
                            </tr>
							
                            <tr> 
                              <td align="right" class="azzurro">State</td>
                              <td class="azzurro"> 
                              	<#assign state=documento.state   />
                              	<#include "/edit/states.ftl"        />
                              </td>
						    </tr>
						    
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="submit" value="Update" class="edit" /></span>
                </form>
            </div>
        </div>
    </body>
</html>