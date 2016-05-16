<#include "head.ftl"  />
        
        <div class="body">
            <h1>Create Mezzo</h1>
            <form id="mezzoForm" name="mezzoForm" action="/edit/mezzo/save" method="post" >

                   <table>
                        <tbody>
                        	<tr class="prop">
                            	<td align="top" class="name"><label for="t">Tipologia mezzo</label></td>
                            	<td align="top" class="value">
									<select id ="fk_tipo_mezzo" name = "fk_tipo_mezzo">
										<option value="0">Seleziona</option>
								    	<#list tipo_mezzo as tipo>
								        	<option value = "${tipo.id_tipo_mezzo}">${tipo.descrizione}</option>
								    	</#list>
									</select>
							</tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="cognome">Targa</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="targa" name="targa"  size="12" maxlength="7" value="" placeholder="Targa"  />
                                </td> 
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono2">Descrizione</label></td>
                                <td align="top" class="value">                          
                                    <input type="text" id="descrizione" name="descrizione"  size="28" maxlength="60" value="" placeholder="Descrizione"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono2">Numero dei posti</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="num_posti" name="num_posti"  size="28" maxlength="60" value="" placeholder="Numero di posti"   />                                   
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono2">Costo per kilometro</label></td>
                                <td align="top" class="value">                                    
                                    <input type="text" id="costo_km" name="costo_km"  size="28" maxlength="60" value="" placeholder="Costo per kilometro"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono2">kilometri di franchigia</label></td>
                                <td align="top" class="value">                                    
                                    <input type="text" id="franchigia_km" name="franchigia_km"  size="28" maxlength="60" value="" placeholder="Kilometri di franchigia"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono2">Quota fissa</label></td>
                                <td align="top" class="value">                                    
                             		<input type="text" id="qf" name="qf"  size="28" maxlength="60" value="" placeholder="Quota fissa"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="telefono2">Distretto di appartenenza</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="distretto" name="distretto"  size="28" maxlength="60" value="" placeholder="Distretto di appartenenza"  />
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