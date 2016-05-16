
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
                            	<td align="top" class="name"><label for="t">Tipologia mezzo</label></td>
                            	<td align="top" class="value">
									<select id = "fk_tipo_mezzo" name = "fk_tipo_mezzo">
										<option value="0">Seleziona</option>
								    	<#list tipo_mezzo as tipo>
								        	<option value = "${tipo.id_tipo_mezzo}" <#if mezzo.getFk_tipo_mezzo() == tipo.id_tipo_mezzo> selected </#if> >${tipo.descrizione}</option>
								    	</#list>
									</select>
							</tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="nome">Targa</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="targa" name="targa" size="12" maxlength="7" value="${mezzo.getTarga()}" placeholder="Targa"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="descrizione"><label for="telefono">Descrizione </label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="descrizione" name="descrizione" size="28" maxlength="20" value="${mezzo.getDescrizione()}" placeholder="Descrizione"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="num_posti"><label for="data">Numero di posti</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="num_posti" name="num_posti" size="28" maxlength="20" value="${mezzo.getNum_posti()}" placeholder="Numero di posti"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="telefono">Costo per kilometro </label></td>
                                <td valign="top" class="value">
                               		<input type="text" id="costo_km" name="costo_km" size="28" maxlength="20" value="${mezzo.getCosto_km()}" placeholder="Costo al kilometro"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="telefono">Kilometri di franchigia </label></td>
                                <td valign="top" class="value">
                               		<input type="text" id="franchigia_km" name="franchigia_km" size="28" maxlength="20" value="${mezzo.getFranchigia_km()}" placeholder="Franchigia al kilometro"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="telefono">Quota fissa</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="qf" name="qf" size="28" maxlength="20" value="${mezzo.getQf()}" placeholder="Quota fissa"/>
                                </td>
                            </tr>
                           <tr class="prop">
                                <td valign="top" class="name"><label for="via">Distretto di appartenenza</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="distretto" name="distretto" size="28" maxlength="50" value="${mezzo.getDistretto()}" placeholder="Distretto di appartenenza"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onClick="controlli()" /></span>
                </form>
                <form action="/edit/mezzo/delete/${mezzo.id_mezzo}" method="get" >
                        <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
                        <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica()" /></span>
                 </form>
             </div>
               
        </div>
    
    </body>
     <script type="text/javascript">

            $(document).ready(function() {

                $("#updForm").submit(function() {

                    errMsg = errMsgDefault = "Attenzione!!\n";
                    $(".errorTextField").removeClass('errorTextField');


                    if (($("#data_nascita").val() !="")){
                        if (Date.isValid( $("#data_nascita").val(), "dd-MM-yyyy") == false){
                            errMsg = errMsg + "Data Scadenza NON valida\n";
                            $("#data_nascita").addClass('errorTextfield');
                        }
                    } else {
                        errMsg = errMsg + "Data Scadenza NON valida\n";
                        $("#data_nascita").addClass('errorTextField');
                    }

                    if (errMsg == errMsgDefault){
                        return confirm("Confermi Aggiornamento?");
                    } else {
                        alert(errMsg);
                        return false;
                    }
                    
                 });

                
                $("#data_nascita").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    bgiframe: true,
                    dateFormat: "dd-mm-yy",
                    constrainInput: false,
                    onSelect: function(dateText) {
                        $(this).val(dateText); 
                    }
                });    

                
                if ($("#data_nascita").val() == ""){
                    $("#data_nascita").val("${utente.data_nascita?string("dd-MM-yyyy") }");
                }

            });

       </script>
</html>