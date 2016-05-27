<#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Patologia</h1>
            
            <form id="patologiaForm" name="patologiaForm" action="/edit/patologia/doUpdate" method="post" >
                <input type="hidden" name="id_patologia" value="${patologia.getId_patologia()}" id="id_patologia" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id patologia</label></td>
                                <td align="top" class="value"><h2>${patologia.getId_patologia()}</h2></td>
                            </tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="descrizione">Descrizione</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="descrizione" name="descrizione" size="25" maxlength="40" value="${patologia.getDescrizione()}" placeholder="Descrizione"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlli()" /></span>
                </form>
                <form action="/edit/patologia/delete/${patologia.id_patologia}" method="get" >
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