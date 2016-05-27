<#include "head.ftl"/>

        <div class="body">
            <h1>Create Documento Testata</h1>
            <form id="TdocumentoForm" name="TdocumentoForm" action="/edit/documento_testata/save" method="post" >
                   <table>
                        <tbody>
                        <tr class="prop">
                                <td align="top" class="num"><label for="num">Numero Documento</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="num" name="num_documento"  size="25" maxlength="11" value="" placeholder="Numero documento"  /><b> - </b><input type="text" id="anno_documento" name="anno_documento"  size="4" maxlength="60" value="2016" readonly/>                                            	 
                                </td> 
                               
                            </tr>
                             <tr class="prop">
                                <td align="top" class="num"><label for="num">Data Documento</label></td>                             
                                <td align="top" class="value">                                   
                                	 <input type="text" id="data_documento" name="data_documento"  size="25" maxlength="60" value="" placeholder="Data documento"  />
                                </td>  
                            </tr> 
                             <tr class="prop">         
                                <td align="top" class="num"><label for="anno_documento">Mese di Riferimento</label></td>                             
                                <td align="top" class="value"> 
                                <select id="mese_documento" name="mese_documento" required>
	                                    <option value="">Seleziona..</option>
	                                        <option value="Gennaio">Gennaio</option>
	                                        <option value="Febbraio">Febbraio</option>
	                                        <option value="Marzo">Marzo</option>
	                                        <option value="Aprile">Aprile</option>
	                                        <option value="Maggio">Maggio</option>
	                                        <option value="Giugno">Giugno</option>
	                                        <option value="Luglio">Luglio</option>
	                                        <option value="Agosto">Agosto</option>
	                                        <option value="Settembre">Settembre</option>
	                                        <option value="Ottobre">Ottobre</option>
	                                        <option value="Novembre">Novembre</option>
	                                        <option value="Dicembre">Dicembre</option>
	                                        
	                                </select>
	                              </td>
                            </tr>       
                          <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_mezzo">Mezzo</label></td>                             
                                <td align="top" class="value">
	                                <select id="fk_id_mezzo" name="fk_id_mezzo" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list listaMezzo as mezzo>
	                                        <option value="${mezzo.getId_mezzo()}">${mezzo.getTarga()} - ${mezzo.getDescrizione()}</option>
	                                    </#list>                   
	                                </select>
	                            </td>
	                        </tr>
	                        
	                        <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_cliente">Cliente</label></td>                             
                                <td align="top" class="value">
									<select id="fk_id_cliente" name="fk_id_cliente" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list listaClienti as cliente>
	                                        <option value="${cliente.getId_cliente()}">${cliente.getRagione_sociale()}</option>
	                                    </#list>
	                                </select>
	                            </td>
                            </tr>             
                             <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_banca"> Banca di Appoggio</label></td>                             
                                <td align="top" class="value">
	                                <select id="fk_id_banca" name="fk_id_banca" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list listaBanca as banca>
	                                        <option value="${banca.getId_banca()}">${banca.getNome()}</option>
	                                    </#list>
	                                </select>
                 				</td>
                            </tr>             
                       
                        </tbody>                            
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><input type="button" name="create" class="save" value="Create" id="create"/></span>
                </div>
            </form>

        </div>

    
    </body>
</html>