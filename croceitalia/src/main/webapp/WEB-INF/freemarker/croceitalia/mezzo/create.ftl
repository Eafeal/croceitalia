<#include "head.ftl"  />
        
        <div class="body">
            <h1>Create Mezzo</h1>
            <form id="mezzoForm" name="mezzoForm" action="/edit/mezzo/save" method="post" >

                   <table>
                        <tbody>
                        	<tr class="prop">
                            	<td align="top" class="name"><label for="fk_tipo_mezzo">Tipologia mezzo</label></td>
                            	<td align="top" class="value">
									<select id ="fk_tipo_mezzo" name = "fk_tipo_mezzo">
										<option value="0">Seleziona</option>
								    	<#list tipo_mezzo as tipo>
								        	<option value = "${tipo.id_tipo_mezzo}">${tipo.descrizione}</option>
								    	</#list>
									</select>
							</tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="targa">Targa</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="targa" name="targa"  size="9" maxlength="7" value="" placeholder="Targa"  />
                                </td> 
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="descrizione">Descrizione</label></td>
                                <td align="top" class="value">                          
                                    <input type="text" id="descrizione" name="descrizione"  size="28" maxlength="60" value="" placeholder="Descrizione"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="num_posti">Numero dei posti</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="num_posti" name="num_posti"  size="11" maxlength="2" value="" placeholder="Numero di posti"   />                                   
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="c_km">Costo per kilometro</label></td>
                                <td align="top" class="value">                                    
                                    <input type="text" id="c_km" name="c_km"  size="11" maxlength="10" value="" placeholder="Costo per km"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="f_km">kilometri di franchigia</label></td>
                                <td align="top" class="value">                                    
                                    <input type="text" id="f_km" name="f_km"  size="11" maxlength="10" value="" placeholder="Km di franchigia"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="qfs">Quota fissa</label></td>
                                <td align="top" class="value">                                    
                             		<input type="text" id="qfs" name="qfs"  size="11" maxlength="10" value="" placeholder="Quota fissa"  />
                             	</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="distretto">Distretto di appartenenza</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="distretto" name="distretto"  size="20" maxlength="40" value="" placeholder="Distretto di appartenenza"  />
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