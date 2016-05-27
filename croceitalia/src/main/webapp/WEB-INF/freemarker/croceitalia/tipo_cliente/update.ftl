<#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Tipo Cliente</h1>
            
            <form id="tipo_clienteForm" name="tipo_clienteForm" action="/edit/tipo_cliente/doUpdate" method="post" >
                <input type="hidden" name="id_tipo_cliente" value="${tipo_cliente.getId_tipo_cliente()}" id="id_tipo_cliente" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id tipo cliente</label></td>
                                <td align="top" class="value"><h2>${tipo_cliente.getId_tipo_cliente()}</h2></td>
                            </tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="descrizione">Descrizione</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="descrizione" name="descrizione" size="25" maxlength="40" value="${tipo_cliente.getDescrizione()}" placeholder="Descrizione"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlli()" /></span>
                </form>
                <form action="/edit/tipo_cliente/delete/${tipo_cliente.id_tipo_cliente}" method="get" >
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