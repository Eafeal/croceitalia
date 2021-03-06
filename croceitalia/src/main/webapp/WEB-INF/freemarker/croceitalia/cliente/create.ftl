<#include "head.ftl"  />
        
        <div class="body">
            <h1>Create Cliente</h1>
            <form id="clienteForm" name="clienteForm" action="/edit/cliente/save" method="post" >

                   <table>
                        <tbody>
                        	<tr class="prop">
                            	<td align="top" class="name"><label for="fk_tipo_clientet">Tipologia cliente</label></td>
                            	<td align="top" class="value">
									<select id = "fk_tipo_cliente" name = "fk_tipo_cliente">
										<option value="0">Seleziona</option>
								    	<#list tipo_cliente as tipo>
								        	<option value = "${tipo.id_tipo_cliente}">${tipo.descrizione}</option>
								    	</#list>
									</select>
							</tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="ragione_sociale">Ragione sociale</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="ragione_sociale" name="ragione_sociale"  size="25" maxlength="40" value="" placeholder="Ragione sociale"  />                                </td> 
                            </tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="partitaIva">Partita IVA / Cod. Fisc.</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="partitaIva" name="partitaIva"  size="25" maxlength="11" value="" placeholder="Partita IVA"  />
                                </td> 
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono1">Recapiti</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="telefono1" name="telefono1" size="18" maxlength="20" value="" placeholder="Telefono1" />
                                    <input type="text" id="telefono2" name="telefono2" size="18" maxlength="20" value="" placeholder="Telefono2"  />
                                    <input type="text" id="email" name="email"  size="33" maxlength="60" value="" placeholder="E-mail"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="via">Indirizzo</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="via" name="via" size="20" maxlength="40" value="" placeholder="Via" />
                                    <input type="text" id="cap" name="cap" size="7" maxlength="5" value="" placeholder="Cap"  />
                                    <input type="text" id="comune" name="comune"  size="20" maxlength="40" value="" placeholder="Comune"  />
                                    <input type="text" id="provincia" name="provincia"  size="5" maxlength="2" value=""  placeholder="Provincia" />
                             	</td>
                            </tr>
                             <!--tr class="prop">
                             	<td align="top" class="telefono1"><label for="codiceFiscale">Codice fiscale</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="codiceFiscale" name="codiceFiscale"  size="20" maxlength="16" value="" placeholder="CF"  />
                             	</td>
                            </tr-->
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="qfs">Quota fissa</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="qfs" name="qfs"  size="5" maxlength="9" value="0"  placeholder="QF" />
                             	</td>
                            </tr>
                        </tbody>                            
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><input type="button" name="create" class="save" value="Create" id="create" onClick="controlli()" /></span>
                </div>
            </form>
        </div>

    
    </body>
</html>