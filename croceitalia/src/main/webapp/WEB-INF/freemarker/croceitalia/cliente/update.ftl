
        <#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Cliente</h1>
            
            <form id="clienteForm" name="clienteForm" action="/edit/cliente/doUpdate" method="post" >
                <input type="hidden" name="id_cliente" value="${cliente.getId_cliente()}" id="id_cliente" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id cliente</label></td>
                                <td align="top" class="value"><h2>${cliente.getId_cliente()}</h2></td>
                            </tr>
                            <tr class="prop">
                            	<td align="top" class="name"><label for="t">Tipologia cliente</label></td>
                            	<td align="top" class="value">
									<select id = "fk_tipo_cliente" name = "fk_tipo_cliente">
										<option value="0">Seleziona</option>
								    	<#list tipo_cliente as tipo>
								        	<option value = "${tipo.id_tipo_cliente}" <#if cliente.getFk_tipo_cliente() == tipo.id_tipo_cliente> selected </#if> >${tipo.descrizione}</option>
								    	</#list>
									</select>
							</tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="ragione_sociale">Ragione sociale</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="ragione_sociale" name="ragione_sociale" size="20" maxlength="20" value="${cliente.getRagione_sociale()}" placeholder="Ragione sociale"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td align="top" class="tipo_veicolo"><label for="p_iva">Partita IVA</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="p_iva" name="p_iva" size="20" maxlength="20" value="${cliente.getP_iva()}" placeholder="Partita IVA"/>
                                </td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono1">Recapiti</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="telefono1" name="telefono1" size="20" maxlength="60" value="${cliente.getTelefono1()}" placeholder="Telefono1" />
                                    <input type="text" id="telefono2" name="telefono2" size="10" maxlength="60" value="${cliente.getTelefono2()}" placeholder="Telefono2"  />
                                    <input type="text" id="email" name="email"  size="20" maxlength="60" value="${cliente.getEmail()}" placeholder="E-mail"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="via">Indirizzo</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="via" name="via" size="20" maxlength="20" value="${cliente.getVia()}" placeholder="Via"/>
                               		<input type="text" id="cap" name="cap" size="10" maxlength="20" value="${cliente.getCap()}" placeholder="CAP"/>
                               		<input type="text" id="comune" name="comune" size="20" maxlength="20" value="${cliente.getComune()}" placeholder="Comune"/>
                                	<input type="text" id="provincia" name="provincia" size="5" maxlength="2" value="${cliente.getProvincia()}" placeholder="Provincia"/>
                                	
                                </td>
                            </tr>
                           <!--tr class="prop">
                                <td valign="top" class="name"><label for="cf">Codice fiscale</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="cf" name="cf" size="20" maxlength="50" value="${cliente.getCf()}" placeholder="CF"/>
                                </td>
                           </tr-->
                           <tr class="prop">
                                <td valign="top" class="name"><label for="via">Quota fissa</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="qfs" name="qfs" size="10" maxlength="10" value="${cliente.getQf()}" placeholder="QF"/>                                </td>
                           </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onClick="controlli()" /></span>
                </form>
                <form action="/edit/cliente/delete/${cliente.id_cliente}" method="get" >
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

