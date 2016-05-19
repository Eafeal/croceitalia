
        <#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Banca</h1>
            
            <form id="bancaForm" name="bancaForm" action="/edit/banca/doUpdate" method="post" >
                <input type="hidden" name="id_banca" value="${banca.getId_banca()}" id="id_banca" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id banca</label></td>
                                <td align="top" class="value"><h2>${banca.getId_banca()}</h2></td>
                            </tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="nome">Istituto/Agenzia</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="nome" name="nome" size="25" maxlength="40" value="${banca.getNome()}" placeholder="Nome"/>
                                    <input type="text" id="agenzia" name="agenzia" size="25" maxlength="20" value="${banca.getAgenzia()}" placeholder="Agenzia"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="quota_fissa"><label for="via">Indirizzo</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="via" name="via" size="20" maxlength="40" value="${banca.getVia()}" placeholder="Via"/>
                               		<input type="text" id="cap" name="cap" size="7" maxlength="5" value="${banca.getCap()}" placeholder="CAP"/>
                               		<input type="text" id="comune" name="comune" size="40" maxlength="20" value="${banca.getComune()}" placeholder="Comune"/>
                                	<input type="text" id="provincia" name="provincia" size="5" maxlength="2" value="${banca.getProvincia()}" placeholder="Provincia"/>
                                	
                                </td>
                            </tr>
                           <tr class="prop">
                                <td valign="top" class="name"><label for="iban">Iban</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="bic_swift" name="bic_swift" size="50" maxlength="50" value="${banca.getBic_swift()}" hidden/>
                                    <input type="text" id="iban" name="iban" size="33" maxlength="27" value="${banca.getIban()}" placeholder="IBAN"/>
                                </td>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlli()" /></span>
                </form>
                <form action="/edit/banca/delete/${banca.id_banca}" method="get" >
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

