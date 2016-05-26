<#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Tipo Struttura</h1>
            
            <form id="tipo_strutturaForm" name="tipo_strutturaForm" action="/edit/tipo_struttura/doUpdate" method="post" >
                <input type="hidden" id="id_tipologia_struttura" name="id_tipologia_struttura" value="${tipo_struttura.getId_tipologia_struttura()}"  />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id tipo struttura</label></td>
                                <td align="top" class="value"><h2>${tipo_struttura.getId_tipologia_struttura()}</h2></td>
                            </tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="descrizione">Descrizione</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="descrizione" name="descrizione" size="25" maxlength="40" value="${tipo_struttura.getDescrizione()}" placeholder="Descrizione"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlli()" /></span>
                </form>
                <form action="/edit/tipo_struttura/delete/${tipo_struttura.id_tipologia_struttura}" method="get" >
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