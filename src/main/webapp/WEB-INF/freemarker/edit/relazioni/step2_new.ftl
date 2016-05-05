<html>
    <head>
        <title>Passo 2: Create new</title>
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
            <h1>Passo 1: Choose Type List</h1>
            
            <form action="/edit/pagina/addObject/step2" method="post" >
                <input type="text" name="uid" value="${oggetto.uid}" id="uid" />
                <input type="text" name="lang" value="${oggetto.lang}" id="lang" />
                <input type="text" name="tipo" value="${oggetto.getClass().getSimpleName()}" id="tipo" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="id">Id</label>
                                </td>
                                <td valign="top" class="value ">
                                    ${oggetto.uid}
                                </td>
                            </tr>                   
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tipoOggetto.uid">Tipooggetto</label>
                                </td>

                                <td valign="top" class="value ">
                                    <select name="tipoOggetto.uid" id="tipoOggetto.uid" >
                                        <option value="" >Seleziona..</option>
                                        <#list ListaTipiOggetti as tipoOggetto >
										    <option value="${tipoOggetto.uid}" >${tipoOggetto.tipo}</option>
										</#list>
									</select>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                    <span class="button"><input type="submit" value="Next" class="edit" /></span>
            </form>

             </div>
        </div>
    
    </body>
</html>