<html>
    <head>
        <title>Passo 2: Choose Object</title>
        
        <link rel="stylesheet" href="/css/main.css" />
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon" />
        
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
        <meta name="layout" content="main"/>

    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
            <span class="menuButton">Creazione Relazione</span>
        </div>
        <div class="body">
            <h1>Step 2: Choose Object</h1>
            
            <form id="addObjectForm" name="addObjectForm" action="/edit/newRel/save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="id">Padre</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="padre.id" name="padre.id" value="${padre.id}"  />
                                </td>
                            </tr>  
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="id">Padre</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="tipoPadre" name="tipoPadre" value="${tipoPadre.tipo}"  readonly />
                                </td>
                            </tr>                
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tipoFiglio">Tipo Figlio</label>
                                </td>
                                <td valign="top" class="value ">
                                    <input type="text" id="tipoFiglio" name="tipoFiglio" value="${tipoFiglio.tipo}"  readonly />
                                </td>
                            </tr>  
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="oggetto.uid">Oggetto</label>
                                </td>
                                <td valign="top" class="value ">
                                    <select name="oggetto.uid" id="oggetto.uid" >
                                        <option value="" >Seleziona..</option>
                                        <#list Lista as unOggetto >
										    <option value="${unOggetto.id}" >${unOggetto.uid}</option>
										</#list>
									</select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input type="submit" value="Choose One" class="edit" /></span>
                </div>
            </form>
        </div>
    </body>
</html>