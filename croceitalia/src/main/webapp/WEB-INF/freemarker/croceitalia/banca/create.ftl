<#include "head.ftl"  />
        
        <div class="body">
            <h1>Create Banca</h1>
            <form id="bancaForm" name="bancaForm" action="/edit/banca/save" method="post" >
                   <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="nome">Nome</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="nome" name="nome" size="25" maxlength="40" value="" placeholder="Nome"  />                                
                                    <input type="text" id="agenzia" name="agenzia" size="25" maxlength="20" value="" placeholder="Agenzia"  />                                                            
                           		</td>
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono1"><label for="via">Indirizzo</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="via" name="via" size="20" maxlength="40" value="" placeholder="Via" />
                                    <input type="text" id="cap" name="cap" size="7" maxlength="5" value="" placeholder="Cap"  />                                
                                    <input type="text" id="comune" name="comune"  size="20" maxlength="40" value="" placeholder="Comune"  />
                                    <input type="text" id="provincia" name="provincia"  size="5" maxlength="2" value=""  placeholder="Provincia" />
                             	</td>
                            </tr>
                             <tr class="prop">
                             	<td align="top" class="telefono1"><label for="iban">Iban</label></td>
                                <td align="top" class="value">
                                    <input type="hidden" id="bic_swift" name="bic_swift"  size="28" maxlength="60" value=" " hidden  />
                                    <input type="text" id="iban" name="iban"  size="33" maxlength="27" value=""  placeholder="IBAN" />
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


