<#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Tipo Mezzo</h1>
            
            <form id="tipo_mezzoForm" name="tipo_mezzoForm" action="/edit/tipo_mezzo/doUpdate" method="post" >
                <input type="hidden" name="id_tipo_mezzo" value="${tipo_mezzo.getId_tipo_mezzo()}" id="id_tipo_mezzo" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id tipo mezzo</label></td>
                                <td align="top" class="value"><h2>${tipo_mezzo.getId_tipo_mezzo()}</h2></td>
                            </tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="descrizione">Descrizione</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="descrizione" name="descrizione" size="25" maxlength="40" value="${tipo_mezzo.getDescrizione()}" placeholder="Descrizione"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlli()" /></span>
                </form>
                <form action="/edit/tipo_mezzo/delete/${tipo_mezzo.id_tipo_mezzo}" method="get" >
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