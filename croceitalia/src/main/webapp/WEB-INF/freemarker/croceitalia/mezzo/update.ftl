
        <#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Mezzo</h1>
            
            <form id="mezzoForm" name="mezzoForm" action="/edit/mezzo/doUpdate" method="post" >
                <input type="hidden" name="id_mezzo" value="${mezzo.getId_mezzo()}" id="id_mezzo" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id mezzo</label></td>
                                <td align="top" class="value"><h2>${mezzo.getId_mezzo()}</h2></td>
                            </tr>
                            <tr class="prop">
                            	<td align="top" class="name"><label for="fk_tipo_mezzo">Tipologia mezzo</label></td>
                            	<td align="top" class="value">
									<select id = "fk_tipo_mezzo" name = "fk_tipo_mezzo">
										<option value="0">Seleziona</option>
								    	<#list tipo_mezzo as tipo>
								        	<option value = "${tipo.id_tipo_mezzo}" <#if mezzo.getFk_tipo_mezzo() == tipo.id_tipo_mezzo> selected </#if> >${tipo.descrizione}</option>
								    	</#list>
									</select>
							</tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="targa">Targa</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="targa" name="targa" size="9" maxlength="7" value="${mezzo.getTarga()}" placeholder="Targa"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="descrizione"><label for="descrizione">Descrizione </label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="descrizione" name="descrizione" size="28" maxlength="20" value="${mezzo.getDescrizione()}" placeholder="Descrizione"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="num_posti"><label for="num_posti">Numero di posti</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="num_posti" name="num_posti" size="11" maxlength="2" value="${mezzo.getNum_posti()}" placeholder="Numero di posti"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="c_km">Costo per kilometro </label></td>
                                <td valign="top" class="value">
                               		<input type="text" id="c_km" name="c_km" size="11" maxlength="10" value="${mezzo.getCosto_km()}" placeholder="Costo al km"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="f_km">Kilometri di franchigia </label></td>
                                <td valign="top" class="value">
                               		<input type="text" id="f_km" name="f_km" size="11" maxlength="10" value="${mezzo.getFranchigia_km()}" placeholder="Km di franchigia"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="qfs">Quota fissa</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="qfs" name="qfs" size="11" maxlength="10" value="${mezzo.getQf()}" placeholder="Quota fissa"/>
                                </td>
                            </tr>
                           <tr class="prop">
                                <td valign="top" class="name"><label for="distretto">Distretto di appartenenza</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="distretto" name="distretto" size="20" maxlength="40" value="${mezzo.getDistretto()}" placeholder="Distretto di appartenenza"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onClick="controlli()" /></span>
                </form>
                <form action="/edit/mezzo/delete/${mezzo.id_mezzo}" method="get" >
                        <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Confermi cancellazione?');" /></span>
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


