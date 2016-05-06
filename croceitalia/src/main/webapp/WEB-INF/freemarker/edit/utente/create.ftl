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


				$("#insForm").submit(function() {

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
                        return confirm("Confermi inserimento?");
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

                var today = new Date();
                var validStartDate = today.format("dd-MM-yyyy");
                
                if ($("#dataScadenza").val() == ""){
                    $("#dataScadenza").val(validStartDate);
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
            <h1>Create User</h1>
            <form id="insForm" name="insForm" action="/edit/utente/save" method="post" >
                <input type="hidden" id="stato" name="stato"  size="4" maxlength="4" value="00"   />
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Dominio</td>
                            <td valign="top" class="value">
                                <input type="text" readonly id="dominio.uid" name="dominio.uid"  size="20" maxlength="20" value="${dominio.uid}"   />
                            </td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Soggetto</td>
                            <td valign="top" class="value">
                                <select id="soggetto.id" name="soggetto.id">
                                    <option value=""  >Seleziona..</option>
                                    <#list soggetti as soggetto>
                                        <option value="${soggetto.id}"  >${soggetto.ruolo}</option>
                                    </#list>
                                </select>
                            </td>
                        </tr>     
                    </tbody>
                </table>
                <br/>
                    <!--input type="text" id="dominio.uid" name="dominio.uid" size="20" maxlength="20" value="${dominio.uid}"   />
                    <input type="text" id="soggetto.id" name="soggetto.id" value="730User"/-->
                    <input type="hidden" id="lingua" name="lingua"  size="3" maxlength="2" value="${lang.getLanguage()}"   />
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">User Id</td>
                                <td valign="top" class="value">
                                    <input type="text" id="userId" name="userId" size="22" maxlength="20"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="ragioneSociale">Ragione sociale Ente</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="ragioneSociale" name="ragioneSociale"  size="60" maxlength="60" value=""   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="partitaIva">Partita Iva</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="partitaIva" name="partitaIva"  size="15" maxlength="11" value=""   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="cognome">Rif. (cognome/nome)</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="cognome" name="cognome"  size="28" maxlength="60" value=""   />
                                    <input type="text" id="nome" name="nome"  size="28" maxlength="60" value=""   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="email">e-mail</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="email" name="email"  size="60" maxlength="80" value=""   />
                                </td>
                            </tr>
                            <!--tr class="prop">
                                <td valign="top" class="name"><label for="lingua">Lingua</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="lingua" name="lingua"  size="3" maxlength="2" value="${lang.getLanguage()}"   />
                                </td>
                            </tr-->
                            <tr class="prop">
                                <td valign="top" class="name"><label for="scadenza">Data scadenza</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="dataScadenza" name="dataScadenza"  size="36" maxlength="32" value="2015-12-31"   />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="password">Password</label></td>
                                <td valign="top" class="value">
                                    <input type="text" id="password" name="password"  size="36" maxlength="32" value="${password}"   />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><input type="submit" name="create" class="save" value="Create" id="create" /></span>
                </div>
            </form>
        </div>

    
    </body>
</html>