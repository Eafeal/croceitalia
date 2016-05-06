<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <#include "/edit/head.ftl"  />

        <style type="text/css" media="screen">
        <!--
        @import url("/css/jquery/jquery-ui.css");
        -->
        </style>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/jquery.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/jquery-ui.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/hoverIntent.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/jquery.bgiframe.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/superfish.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/jquery.autocomplete.js"></script>
        <script language="JavaScript" type="text/javascript" src="/js/jquery/date.js"></script>

        <script type="text/javascript">

            $(document).ready(function() {

                $("#updForm").submit(function() {

                    errMsg = errMsgDefault = "Attenzione!!\n";
                    $(".errorTextField").removeClass('errorTextField');


                    if (($("#dataScadenza").val() !="")){
                        if (Date.isValid( $("#dataScadenza").val(), "dd-MM-yyyy") == false){
                            errMsg = errMsg + "Data Scadenza NON valida\n";
                            $("#dataScadenza").addClass('errorTextfield');
                        }
                    } else {
                        errMsg = errMsg + "Data Scadenza NON valida\n";
                        $("#dataScadenza").addClass('errorTextField');
                    }

                    if (errMsg == errMsgDefault){
                        return confirm("Confermi Aggiornamento?");
                    } else {
                        alert(errMsg);
                        return false;
                    }
                    
                 });

                
                $("#dataScadenza").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    bgiframe: true,
                    dateFormat: "dd-mm-yy",
                    constrainInput: false,
                    onSelect: function(dateText) {
                        $(this).val(dateText); 
                    }
                });    

                
                if ($("#dataScadenza").val() == ""){
                    $("#dataScadenza").val("${utente.dataScadenza?string("dd-MM-yyyy") }");
                }

            });

       </script>
       
    </head>

    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        <div class="nav">
           <span class="menuButton"><a class="home" href="/edit/home">Home</a></span>
           <span class="menuButton"><a href="/edit/utente/list" class="list">User List</a></span>
           <span class="menuButton"><a href="/edit/utente/create" class="create">New User</a></span>
           <span class="menuButton"><a href="javascript:void()" class="close" onclick="javascript:ricarica()">Close</a></span>
        </div>
        <div class="body">
            <h1>Edit Utente</h1>
            
            <form id="updForm" name="updForm" action="/edit/utente/doUpdate" method="post" >
                <input type="hidden" name="id" value="${utente.id}" id="id" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="userId">User Id</label></td>
                                <td valign="top" class="value"><h2>${utente.userId}</h2></td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="password">Password</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="password" name="password" size="20" maxlength="20" value="${utente.password}"   />
                                </td>
                            </tr>

                            <input type="hidden" id="dominio_uid" name="dominio_uid"  size="20" maxlength="20" value="${utente.dominio.uid}"   />
                            <!--input type="hidden" id="soggetto_id" name="soggetto_id"  size="20" maxlength="20" value="${utente.soggetto.id}"   />
                            <input type="hidden" id="stato"  name="stato"   size="4" maxlength="4" value="${utente.stato}"    /-->
                            <input type="hidden" id="lingua" name="lingua"  size="3" maxlength="2" value="${utente.lingua}"   />

                            <!--tr class="prop">
                                <td valign="top" class="name"><label for="dominio_uid">Dominio</label></td>
                                <td valign="top" class="value">
                                    <input type="text" readonly id="dominio_uid" name="dominio_uid"  size="20" maxlength="20" value="${utente.dominio.uid}"   />
                                </td>
                            </tr-->
                            <tr class="prop">
                                <td valign="top" class="name">Soggetto</td>
                                <td valign="top" class="value">
                                    <input type="text" id="soggetto_id" name="soggetto_id"  size="20" maxlength="20" value="${utente.soggetto.id}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="stato">Stato</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="stato" name="stato"  size="4" maxlength="4" value="${utente.stato}"   />
                                </td>
                            </tr>
                            <!--tr class="prop">
                                <td valign="top" class="name"><label for="lingua">Lingua</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="lingua" name="lingua"  size="3" maxlength="2" value="${utente.lingua}"   />
                                </td>
                            </tr-->   
                            <tr class="prop">
                                <td valign="top" class="name"><label for="partitaIva">Partita Iva</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="partitaIva" name="partitaIva"  size="15" maxlength="11" value="${utente.getPartitaIva()}"   />
									<label for="codiceFiscale">Codice Fiscale</label>
									<input type="text" id="codiceFiscale" name="codiceFiscale" size="15" maxlength="11" value="${utente.getCodiceFiscale()}"   />
                                </td>
                            </tr>							
                            <tr class="prop">
                                <td valign="top" class="name"><label for="ragioneSociale">Ragione sociale Ente</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="ragioneSociale" name="ragioneSociale"  size="60" maxlength="60" value="${utente.getRagioneSociale()}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="indirizzo">Indirizzo</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="indirizzo" name="indirizzo"  size="60" maxlength="100" value="${utente.getIndirizzo()}"   /><br>
                                    <input type="text" id="cap" name="cap"  size="5" maxlength="5" value="${utente.getCap()}"   />
                                    <input type="text" id="comune" name="comune" size="45" maxlength="100" value="${utente.getComune()}"   />
                                    <input type="text" id="prov" name="prov"  size="2" maxlength="2" value="${utente.getProv()}"   />
									
                                </td>
                            </tr>							
                            <tr class="prop">
                                <td valign="top" class="name"><label for="cognome">Riferimento</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="cognome" name="cognome"  size="28" maxlength="60" value="${utente.cognome}"   />
                                    <input type="text" id="nome" name="nome"  size="28" maxlength="60" value="${utente.nome}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="email">e-mail</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="email" name="email"  size="60" maxlength="80" value="${utente.email}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="telefono">telefono</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="telefono" name="telefono"  size="60" maxlength="80" value="${utente.getTelefono()}"   />
                                </td>
                            </tr>							
                            <tr class="prop">
                                <td valign="top" class="name"><label for="convenzione">Convenzione</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="convenzione" name="convenzione"  size="60" maxlength="80" value="${utente.getConvenzione()}"   />
                                </td>
                            </tr>							
                            <tr class="prop">
                                <td valign="top" class="name"><label for="dataScadenza">Data scadenza</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="dataScadenza" name="dataScadenza"  size="36" maxlength="32" value="${utente.dataScadenza?string["dd-MM-yyyy"]}"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label>Note</label></td>
                                <td valign="top" class="value"><textarea cols="300" id=note name=note>${utente.note}</textarea></td>
                            </tr>								
                            <tr class="prop">
                                <td valign="top" class="name"><label for="creata">Data creazione</label></td>
                                <td valign="top" class="value">${utente.insertTime?datetime}</td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label>Ultimo Accesso:</label></td>
                                <td valign="top" class="value">${eULTLOGIN?string.medium}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            <div class="buttons">
                <span class="button"><input type="submit" value="Update" class="edit" /></span>
                </form>
                <form action="/edit/utente/delete/${utente.id}" method="get" >
                        <span class="button"><input type="submit" value="Delete" class="delete" onclick="return confirm('Are you sure?');" /></span>
                        <span class="button"><input type="button" value="Close"  class="close"  onclick="ricarica()" /></span>
                 </form>
             </div>
               
        </div>
    
    </body>
</html>