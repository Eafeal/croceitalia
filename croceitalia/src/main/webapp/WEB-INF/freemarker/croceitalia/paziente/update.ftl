
        <#include "head.ftl"  />
 
      
        <div class="body">
            <h1>Edit Paziente</h1>
            
            <form id="insForm" name="insForm" action="/edit/paziente/doUpdate" method="post" >
                <input type="hidden" name="id_paziente" value="${paziente.getId_paziente()}" id="id_paziente" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td align="top" class="name"><label for="userId">User Id</label></td>
                                <td align="top" class="value"><h2>${paziente.getId_paziente()}</h2></td>
                            </tr>
                            
                           	<tr class="prop">
                            	<td align="top" class="name"><label for="t">Patologia</label></td>
                            	<td align="top" class="value">
									<select id = "fk_id_patologia" name = "fk_id_patologia">
										<option value=" " >Seleziona</option>
								    	<#list patologia as pato>
								        	<option value = "${pato.id_patologia}" <#if paziente.getFk_id_patologia() == pato.id_patologia> selected </#if> >${pato.descrizione}</option>
								    	</#list>
									</select>
								</td>
							</tr>
                            
							
                            <tr class="prop">
                                <td valign="top" class="name"><label for="nome">Nome</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="nome" name="nome" size="20" maxlength="20" value="${paziente.getNome()}" placeholder="Nome"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="cognome">Cognome</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="cognome" name="cognome" size="20" maxlength="20" value="${paziente.getCognome()}" placeholder="Cognome"/>
                                </td>
                            </tr>
  							 <tr class="prop">

                                <td valign="top" class="sesso"><label for="sesso"> Sesso</label></td>
                                <td valign="top" class="value">
                                 	<ul style="list-style: none;padding-left: 0px;">
                  					<li>
                    					<input type="radio" id="f-option" name="sesso" value="M" <#if paziente.getSesso()=="M"> checked </#if>>
                    					<label for="f-option">Maschio</label>                  
                    					<input type="radio" id="s-option" name="sesso" value="F" <#if paziente.getSesso()=="F"> checked </#if>>
                    					<label for="s-option">Femmina</label>
                    				</li>
                    				</ul>
                                </td>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="data">Data di nascita</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="data_nascita" name="data_nascita" size="20" maxlength="20" value="${paziente.getData_nascita()}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="telefono">Telefono </label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="telefon1" name="telefono1" size="20" maxlength="20" value="${paziente.getTelefono1()}" placeholder="Telefono principale"/>
                                    <input type="text" id="telefon2" name="telefono2" size="20" maxlength="20" value="${paziente.getTelefono2()}" placeholder="Secondo telefono"/>
                                </td>
                            </tr>
                           <tr class="prop">
                                <td valign="top" class="name"><label for="via">Indirizzo</label></td>
                                <td valign="top" class="value">
                                
                                    <input type="text" id="via" name="via" size="50" maxlength="40" value="${paziente.getVia()}" placeholder="Via"/>
                                    <input type="text" id="cap" name="cap" size="7" maxlength="5" value="${paziente.getCap()}" placeholder="Cap"/>
                                    <input type="text" id="comune" name="comune" size="30" maxlength="40" value="${paziente.getComune()}" placeholder="Comune"/>
         
                                    <input type="text" id="provincia" name="provincia" size="5" maxlength="2" value="${paziente.getProvincia()}" placeholder="Provincia"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="button" value="Update" class="edit" onclick="controlli()"/></span>
                </form>
                <form action="/edit/paziente/delete/${paziente.id_paziente}" method="get" >
                        <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
                        <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica()" /></span>
                 </form>
             </div>
               
        </div>
    
    </body>

</html>