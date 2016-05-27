<#include "head.ftl"  />
        
        <div class="body">
            <h1>Create Patologia</h1>
            <form id="patologiaForm" name="patologiaForm" action="/edit/patologia/save" method="post" >
                   <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="descrizione">Descrizione</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="descrizione" name="descrizione" size="25" maxlength="40" value="" placeholder="Nome"  />                                                            
                           		</td>
                            </tr>
                        </tbody>                            
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><input type="button" name="create" class="save" value="Create" id="create" onClick="controlli()" /></span>
                </div>
            </form>
        </div>

    
    </body>

</html>