
        <#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Testata</h1>
            
            <form id="TdocumentoForm" name="TdocumentoForm" action="/edit/documento_testata/doUpdate" method="post" >
                <input type="hidden" name="id_documento_testata" value="${documento.getId_documento_testata()}" id="id_documento_testata" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">Id Struttura</label></td>
                                <td align="top" class="value"><h2>${documento.getId_documento_testata()}</h2></td>
                            </tr>
                                   
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlloStruttura()" /></span>
                </form>
                <form action="/edit/documento_testata/delete/${documento.getId_documento_testata()}" method="get" >
                        <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
                        <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica()" /></span>
                 </form>
             </div>
               
        </div>
    
    </body>
    
</html>