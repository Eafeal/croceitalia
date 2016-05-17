
        <#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit struttura</h1>
            
            <form id="strutturaForm" name="strutturaForm" action="/edit/struttura/doUpdate" method="post" >
                <input type="hidden" name="id_struttura" value="${struttura.getId_struttura()}" id="id_struttura" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id Struttura</label></td>
                                <td align="top" class="value"><h2>${struttura.getId_struttura()}</h2></td>
                            </tr>
                            
                           
                         	<tr class="prop">
                            	<td align="top" class="name"><label for="t">Tipologia Struttura</label></td>
                            	<td align="top" class="value">
									<select id = "fk_id_tipologia_struttura" name = "fk_id_tipologia_struttura">
										<option >Seleziona</option>
								    	<#list tipo_struttura as tipo>
								        	<option value = "${tipo.id_tipologia_struttura}" <#if struttura.getFk_id_tipologia_struttura() == tipo.id_tipologia_struttura> selected </#if> >${tipo.descrizione}</option>
								    	</#list>
									</select>
								</td>
							</tr>
                            
                       
                            <tr class="prop">
                                <td valign="top" class="name"><label for="nome">Nome Struttura</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="nome" name="nome" size="28" maxlength="60" value="${struttura.getNome()}" placeholder="Descrizione"/>
                                </td>
                            </tr>
                             <tr class="prop" >
                             	<td align="top" class="descrizione"><label for="descrizione_breve"> Descrizione breve </label></td>
                                <td align="top" class="value" >
                                    <input type="text" id="descrizione_breve" name="descrizione_breve" size="28" maxlength="60" value="${struttura.getDescrizione_breve()}" placeholder="Scrivi"   />
                             	</td>
                            </tr>
                           <tr class="prop">
                                <td valign="top" class="name"><label for="via">Indirizzo</label></td>
                                <td valign="top" class="value">
                                
                                    <input type="text" id="via" name="via" size="50" maxlength="50" value="${struttura.getVia()}" placeholder="Via"/>
                                    <input type="text" id="cap" name="cap" size="7" maxlength="5" value="${struttura.getCap()}" placeholder="Cap"/>
                                    <input type="text" id="comune" name="comune" size="30" maxlength="30" value="${struttura.getComune()}" placeholder="Comune"/>
                                    <input type="text" id="provincia" name="provincia" size="5" maxlength="2" value="${struttura.getProvincia()}" placeholder="Provincia"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="telefono">Telefono </label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="telefono" name="telefono" size="28" maxlength="60" value="${struttura.getTelefono()}" placeholder="Telefono principale"/>
                                </td>
                            </tr>
                            <tr class="prop" >
                             	<td align="top" class="codice"><label for="email">E-mail</label></td>
                                <td align="top" class="value" >           
                                    <input type="text" id="email" name="email"  size="28" maxlength="60" value="${struttura.getEmail()}" placeholder="Indirizzo e-mail"   />
                             	</td>
                            </tr>
                            <tr class="prop" >
                             	<td align="top" class="codice"><label for="cod_regione"> Codice Struttura</label></td>
                                <td align="top" class="value" >
                                    <input type="text" id="cod_regione" name="cod_regione" size="6" maxlength="3" value="${struttura.getCod_regione()}" placeholder="Regione"   />
                                    <input type="text" id="cod_asl" name="cod_asl"  size="6" maxlength="3" value="${struttura.getCod_asl()}" placeholder="Asl"   />
                                    <input type="text" id="cod_struttura" name="cod_struttura"  size="7" maxlength="6" value="${struttura.getCod_struttura()}" placeholder="Struttura"   />
                             	</td>
                            </tr>             
                          
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlloStruttura()" /></span>
                </form>
                <form action="/edit/struttura/delete/${struttura.getId_struttura()}" method="get" >
                        <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Confermi cancellazione?');" /></span>
                        <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica()" /></span>
                 </form>
             </div>
               
        </div>
    
    </body>
    
</html>

<#if esito?? && esito=="ok">
<script>
alert("Aggiornamento effettuato correttamente");
</script>
</#if> 



