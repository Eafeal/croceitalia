
        <#include "head.ftl"  />

        <div class="body">
            <h1>Create Paziente</h1>
            <form id="insForm" name="insForm" action="/edit/paziente/save" method="post" >

                   <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="tipo"><label for="fk_id_patologia">Patologia</label></td>
	                            <td valign="top" class="value">
	                                <select id="fk_id_patologia" name="fk_id_patologia" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list patologia as pato>
	                                        <option value="${pato.id_patologia}">${pato.descrizione}</option>
	                                    </#list>
	                                </select>
	                            </td>
	                        </tr>
                            <tr class="prop">
                                <td align="top" class="name"><label for="cognome">Cognome/Nome</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="cognome" name="cognome"  size="25" maxlength="40" value="" placeholder="Cognome" />
                                    <input type="text" id="nome" name="nome"  size="25" maxlength="40" value="" placeholder="Nome"  />
                                </td> 
                            </tr>
                            <tr class="prop">
                             	<td align="top" class="telefono"><label for="telefono1">Telefono</label></td>
                                <td align="top" class="value">
                                    <input type="text" id="telefono1" name="telefono1"  size="18" maxlength="20" value=""  placeholder="Telefono principale"  />
                                    <input type="text" id="telefono2" name="telefono2"  size="18" maxlength="20" value=""  placeholder="Secondo telefono"  />
                             	</td>
                            </tr>
                            <tr class="prop" >
                             	<td align="top" class="telefono1"><label for="via"> Indirizzo</label></td>
                                <td align="top" class="value" >
                                    <input type="text" id="via" name="via" size="20" maxlength="40" value="" placeholder="Via"   />
                                    <input type="text" id="cap" name="cap"  size="7" maxlength="5" value="" placeholder="Cap"   />
                                    <input type="text" id="comune" name="comune"  size="20" maxlength="40" value="" placeholder="Comune"   />                               
                                    <input type="text" id="provincia" name="provincia"  size="5" maxlength="2" value="" placeholder="Provincia"   />
                             	</td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="sesso"><label for="sesso"> Sesso</label></td>
                                <td valign="top" class="value">
                                 	<ul style="list-style: none;padding-left: 0px;">
                  					<li>
                    					<input type="radio" id="f-option" name="sesso" value="M">
                    					<label for="f-option">Maschio</label>                  
                    					<input type="radio" id="s-option" name="sesso" value="F">
                    					<label for="s-option">Femmina</label>
                    				</li>
                    				</ul>
                                </td>
                            </tr>
     						<tr class="prop">
                                <td valign="top" class="data_nascita"><label for="data_nascita"> Data di nascita</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="data_nascita" name="data_nascita"  size="20" maxlength="20" />
                                </td>
                            </tr>
                        </tbody>                            
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><input type="button" name="create" class="save" value="Create" id="create" onclick="controlli()"/></span>
                </div>
            </form>
        </div>

    </body>  
    
</html>