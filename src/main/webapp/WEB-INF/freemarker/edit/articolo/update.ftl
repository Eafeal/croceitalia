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
        
        <#include "/editor/tinyMCE.ftl"  />
       
       <script type="text/javascript"> 

			function imageChanged() {
			
				var id=$("select#image_id").val();
				
				var resource = "<img src=\"/edit/documento/get/" + id  +"\" />" ;
				$("td#image_preview").html(resource);
				
				var elem=document.getElementById("image.id");
				elem.value=id;
				
				}
			
			function loadImages(nomeFile) {
			
				var url = "/edit/articolo/image/list/" + nomeFile; 
				
				$.ajax({
					type: "GET",
					url: url,
					data: "",
					dataType: "xml",
					success: function(xml){
							switch ($(xml).find("esito").text()){
								case "ok":
									var options = "<option value=''>Seleziona una Immagine</option>";
									$("#image_id").empty();
									$("#image_id").removeClass("muted");
			
									var selectedKey=$(xml).find("selectedKey").text();
									$(xml).find("option").each(function(){
									var value = $(this).find("value").text();
									var selected="";
									if(selectedKey==value)
										{selected="selected";}
									var desc = $(this).find("desc").text();
									options += '<option value="' + value + '" '+ selected + ' >' + desc + '</option>';
									});
			
									$("select#image_id").html(options);
			
								break;
								default:
									alert($(xml).find("messaggio").text());
								break;
							}
					   },
						error: function(){
							// KO per problemi ajax
							alert("Ajax Ko. Impossibile recuperare le immagini");
							}
						});
				}
				
				
				$(document).ready(
					function()
						{
						imageChanged();
						}
					);
		
			</script>


    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
            <span class="menuButton"><a href="/edit/articolo/list" class="list">Articolo List</a></span>
            <span class="menuButton"><a href="/edit/articolo/create" class="create">New Articolo</a></span>
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Edit Articolo</h1>
            
            <form action="/edit/articolo/doUpdate" method="post" >
                <input type="hidden" name="id" value="${oggetto.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
                       <tr class="prop">
                            <td valign="top" class="name" colspan="2" >
                              <label for="tipoOggetto.uid">Tipo Oggetto</label>
                              <input type="hidden"   id="tipoOggetto.uid"  name="tipoOggetto.uid" value="${oggetto.tipoOggetto.uid}"  />
                                ${oggetto.tipoOggetto.uid}
                            </td>
                       </tr>
                       <tr class="prop">
                            <td valign="top" class="name">
                              <label for="uid">Uid </label>
                            </td>
                            <td valign="top" class="value ">
                              <input type="text" id="uid" name="uid" value="${oggetto.uid}"  size="45" maxlength="40" />
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
                                <#assign state=oggetto.state            />
                                <#include "/edit/states.ftl"            />
                              </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="testo.title">Title</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="testo.title" name="testo.title" value="${oggetto.testo.title}" size="100" maxlength="95" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="testo.autore">autore</label>
                                </td>

                                <td valign="top" class="value ">
                                    <input type="text" id="testo.autore"  name="testo.autore" value="${oggetto.testo.autore}" size="50" maxlength="45" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
						        <td valign="top" class="value ">
						            <label for="image_id">Immagine</label>
						        </td>
						        <td valign="top" class="value ">
	                            <#if oggetto.image??  >
	                            	<#assign image_id=oggetto.image.id />
	                            <#else>
	                            	<#assign image_id="" />
	                            </#if>
						            <input type="hidden" id="image.id"  name="image.id" value="${image_id}"   />
								    <select id="image_id" name="image_id"  onchange="javascript:imageChanged();" >
								        <option  value="" >Seleziona una Immagine</option>
										<#list map?keys as key>
											<#if selectedKey==key?trim >
												<#assign selected="  selected=\"selected\"  " />
											<#else>
												<#assign selected="  " />
											</#if>
											<option value="${key?trim}" ${selected} >${map[key]?xml}</option>
										</#list>
								    </select>
						        </td>
                            </tr>

                            <tr class="prop">
								<td valign="top" class="value" id="image_preview" colspan="4" >
									IMAGE PREVIEW
								</td>
						    </tr>
						    
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="commento">Inserisci il testo</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="hidden" id="testo.id" name="testo.id" value="${oggetto.testo.id}"  size="20"  />
                                    <textarea id="txt" name="txt"  rows="800" cols="120" style="width: 100%">${oggetto.testo.getTesto()}</textarea>
                                </td>
                            </tr>
                            
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                    <span class="button"><input type="submit" value="Update" class="edit" /></span>
            </form>
            <form action="/edit/articolo/delete/${oggetto.id}" method="get" >
                    <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
                    <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica();" /></span>
             </form>
             </div>
    
        </div>
    
    </body>
</html>