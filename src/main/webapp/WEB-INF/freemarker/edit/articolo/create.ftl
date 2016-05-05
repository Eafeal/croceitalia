<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="it">
<head>
	<#include "/edit/head.ftl"  />
	<#include "/editor/tinyMCE.ftl"  />

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
			loadImages('ALL');
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
            <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Create Articolo</h1>
            
            
            <form action="/edit/articolo/save" method="post" >
                <input type="hidden" name="tipoOggetto.uid"       id="tipoOggetto.uid"         value="Articolo"  readonly="readonly" />
                <input type="hidden" name="testo.tipoOggetto.uid" id="testo.tipoOggetto.uid"   value="Testo"     readonly="readonly" />
                
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="testo.title">Title</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="testo.title"  name="testo.title" value="title" />
                                </td>
                            </tr>

                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="testo.autore">Autore</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="testo.autore"  name="testo.autore" value="autore" />
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
                                <#assign state="WIP"          />
                                <#include "/edit/states.ftl"  />
                              </td>
                            </tr>

                            <tr class="prop">
						        <td valign="top" class="value ">
						            <label for="image_id">Immagine</label>
						        </td>
						        <td valign="top" class="value ">
						            <input type="text" id="image.id"  name="image.id" value=""   />
								    <select id="image_id" name="image_id"  onchange="javascript:imageChanged();" >
								        <option  value="" selected="selected" >Seleziona una Immagine</option>
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
                                    <label for="txt">Inserisci l'articolo </label>
                                </td>
                                <td valign="top" class="value">
                                    <textarea id="txt" name="txt" rows="20" cols="80" style="width:80%"></textarea>
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