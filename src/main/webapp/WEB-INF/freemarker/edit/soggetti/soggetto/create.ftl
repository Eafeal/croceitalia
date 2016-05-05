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

                $("#insForm").submit(function() {

                	errMsg = errMsgDefault = "Attenzione!!\n";
            		$(".errorTextField").removeClass('errorTextField');


            	    if (($("#nascita").val() !="")){
            	        if (Date.isValid( $("#nascita").val(), "yyyy") == false){
            	            errMsg = errMsg + "Data di Nascita NON valida\n";
            	            $("#nascita").addClass('errorTextfield');
            	        }
            	    } else {
                        errMsg = errMsg + "Anno di Nascita NON valida\n";
            	        $("#nascita").addClass('errorTextField');
            	    }

                    
            		if (errMsg == errMsgDefault){
                		return confirm("Confermi inserimento?");
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
            <span class="menuButton"><a href="/edit/soggetti/soggetto/list" class="list">Soggetto List</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <div class="dialog">
            <form id="insForm" name="insForm" action="/edit/soggetti/soggetto/save" method="post" >
                <h1>Create Soggetto</h1>
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cognome">Cognome</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="cognome" name="cognome"  value="cognome" size="50" maxlength="40" />
                                </td>
                            </tr>
	                                
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome">Nome</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="nome" name="nome" value="nome"   size="50" maxlength="40" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sesso">Sesso</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="sesso"  name="sesso" value="F"  size="2" maxlength="1" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nascita">Anno di Nascita</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="nascita"   name="nascita"  maxlength="6" size="4"  />
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