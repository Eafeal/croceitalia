<#include "head.ftl"/>

        <div class="body">
            <h1>Create Struttura</h1>
            <form id="strutturaForm" name="strutturaForm" action="/edit/struttura/save" method="post" >

                   <table>
                        <tbody>
                        <tr class="prop">
                            <td valign="top" class="tipo">Tipologia Struttura</td>
                            <td valign="top" class="value">
                                <select id="fk_id_tipologia_struttura" name="fk_id_tipologia_struttura">
                                    <option value="">Seleziona..</option>
                                    <#list tipologia as tipo>
                                        <option value="${tipo.id_tipologia_struttura}">${tipo.descrizione}</option>
                                    </#list>
                                </select>
                            </td>
                        </tr>     
                            <tr class="prop">
                                <td align="top" class="name"><label for="nome">Nome struttura</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="nome" name="nome"  size="28" maxlength="60" value="" placeholder="Nome struttura"  />
                                </td> 
                            </tr>
                            <tr class="prop" >
                             	<td align="top" class="descrizione"><label for="descrizione_breve">Descrizione breve</label></td>
                                <td align="top" class="value" >
                                    <input type="text" id="descrizione_breve" name="descrizione_breve" size="28" maxlength="60" value="" placeholder="" />
                             	</td>
                            </tr>
                            <tr class="prop" >
                             	<td align="top" class="indirizzo"><label for="via"> Indirizzo</label></td>
                                <td align="top" class="value" >
                                    <input type="text" id="via" name="via" size="50" maxlength="40" value="" placeholder="Via"   />
                                    <input type="text" id="cap" name="cap"  size="7" maxlength="5" value="" placeholder="Cap"   />
                                    <input type="text" id="comune" name="comune"  size="30" maxlength="40" value="" placeholder="Comune"   />
                                    
                                    <input type="text" id="provincia" name="provincia"  size="5" maxlength="2" value="" placeholder="Provincia"   />
                             	</td>
                            </tr>
     						<tr class="prop">
                             	<td align="top" class="telefono"><label for="telefono">Telefono</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="telefono" name="telefono"  size="28" maxlength="60" value=""  placeholder="Telefono principale"  />
                             	</td>
                            </tr>
                         
                            <tr class="prop" >
                             	<td align="top" class="codice"><label for="email">E-mail</label></td>
                                <td align="top" class="value" >           
                                    <input type="text" id="email" name="email"  size="28" maxlength="60" value="" placeholder="Indirizzo e-mail"   />
                             	</td>
                            </tr>
                            <tr class="prop" >
                             	<td align="top" class="codice"><label for="cod_regione"> Codice Struttura</label></td>
                                <td align="top" class="value" >
                                    <input type="text" id="cod_regione" name="cod_regione" size="6" maxlength="3" value="" placeholder="Regione"   />
                                    <input type="text" id="cod_asl" name="cod_asl"  size="7" maxlength="3" value="" placeholder="Asl"   />
                                    <input type="text" id="cod_struttura" name="cod_struttura"  size="8" maxlength="6" value="" placeholder="Struttura"   />
                             	</td>
                            </tr>
                        </tbody>                            
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><input type="button" name="create" class="save" value="Create" id="create" onclick="controlloStruttura()"/></span>
                </div>
            </form>
        </div>

    
    </body>
</html>