<html>
    <head>
        <title>Passo 1: Choose Type List</title>
        <link rel="stylesheet" href="/css/main.css" />
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon" />
        
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <meta name="layout" content="main"/>

		<script type="text/javascript">
			function creaNuovo()
				{
				var addObjectForm=document.getElementById("addObjectForm");
				addObjectForm.action="/edit/newRel/newSon";
				addObjectForm.submit();
				}
		</script>

    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton">Creazione Relazione</span>
        </div>
        <div class="body">
            <h1>Passo 1: Choose Type List</h1>
            
            <form id="addObjectForm" name="addObjectForm" action="/edit/newRel/choose" method="post" >
                <input type="hidden" id="id" name="id" value="${padre.id}"  />
                <input type="hidden" id="tipoOggettoPadre.uid" name="tipoOggettoPadre.uid" value="${padre.tipoOggetto.tipo}"  />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="id">Uid</label>
                                </td>
                                <td valign="top" class="value ">
                                    ${padre.uid}
                                </td>
                            </tr>                   
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="id">Tipo Oggetto Padre</label>
                                </td>
                                <td valign="top" class="value ">
                                    ${padre.tipoOggetto.tipo}
                                </td>
                            </tr>       
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tipoOggetto.uid">Tipo Oggetto Figlio</label>
                                </td>
                                <td valign="top" class="value ">
                                    <select name="tipoOggetto.uid" id="tipoOggetto.uid" >
                                        <#list ListaTipiOggetti as unTipoOggetto >
										    <option value="${unTipoOggetto.uid}" >${unTipoOggetto.tipo}</option>
										</#list>
									</select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                	<span class="button"><input type="submit" value="Choose One" class="edit" /></span>
                	<span class="button"><input type="button" value="Create New" class="edit" onclick="javascript:creaNuovo()" /></span>
                </div>
            </form>
        </div>
    </body>
</html>