<#include "head.ftl"/>

        <div class="body">
            <h1>Create Documento Testata</h1>
            <form id="TdocumentoForm" name="TdocumentoForm" action="/edit/documento_testata/save" method="post" >

                   <table>
                        <tbody>
	                   		 <tr class="prop">
	                            <td valign="top" class="tipo">Mezzo</td>
	                            <td valign="top" class="value">
	                                <select id="fk_id_mezzo" name="fk_id_mezzo" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list mezzo as mezzo>
	                                        <option value="${mezzo.id_mezzo}">${mezzo.id_mezzo}</option>
	                                    </#list>
	                                </select>
	                            </td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="tipo">Mezzo</td>
	                            <td valign="top" class="value">
	                                <select id="fk_tipo_mezzo" name="fk_tipo_mezzo" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list tipo_mezzo as tipo>
	                                        <option value="${tipo.id_tipo_mezzo}">${tipo.descrizione}</option>
	                                    </#list>
	                                </select>
	                            </td>
	                        </tr>
                            <tr class="prop">
                                <td align="top" class="num"><label for="num">Numero Documento</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="num" name="num"  size="28" maxlength="60" value="" placeholder="Numero documento"  />
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