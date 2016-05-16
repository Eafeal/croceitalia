<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
	<link rel="stylesheet"    href="/css/main.css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
	
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<meta name="layout" content="main"/>
	
	<#if title?? ><title>${title}</title></#if>
	
	<script type="text/javascript" src="/js/bubbleSort.js"></script>
	
	<script type="text/javascript">
		
		function controlli(){
		
			if(clienteForm.fk_tipo_cliente.value==0){
				alert("La tipologia cliente � obbligatoria");
				return false;
			}
			if(clienteForm.ragione_sociale.value==""){
				clienteForm.ragione_sociale.focus();
				alert("La ragione sociale � obbligatoria");
				return false;
			}
			if(clienteForm.p_iva.value==""){
				clienteForm.p_iva.focus();
				alert("La partita iva � obbligatoria");
				return false;
			}
			if (!controllo_p_iva(clienteForm.p_iva)) {
				return false;
			}
			if(clienteForm.telefono1.value==""){
				clienteForm.telefono1.focus();
				alert("Il numero di telefono � obbligatorio");
				return false;
			}
			if(clienteForm.email.value==""){
				clienteForm.email.focus();
				alert("L'e-mail � obbligatoria");
				return false;
			}
			if(clienteForm.via.value==""){
				clienteForm.via.focus();
				alert("La via � obbligatoria");
				return false;
			}
			if(clienteForm.cap.value==""){
				clienteForm.cap.focus();
				alert("Il cap � obbligatorio");
				return false;
			}
			if(clienteForm.comune.value==""){
				clienteForm.comune.focus();
				alert("Il comune � obbligatorio");
				return false;
			}
			if(clienteForm.provincia.value==""){
				clienteForm.provincia.focus();
				alert("La provincia � obbligatoria");
				return false;
			}
			//if(clienteForm.cf.value!=""){
			//	controllo_cf(clienteForm.cf);
			//	return false;
			//}
			clienteForm.submit();
		}
		
		function controllo_p_iva(p_iva) {
			if (p_iva.value.length != 11) {
				p_iva.focus();
				alert("La partita iva deve contenere 11 caratteri");
				return false;
			}
		
			return true;
		}
/*		
		function controllo_cf(cf) {
			if ((cf.value.length < 16) || (cf.value.length > 16)) {
				cf.focus();
				alert("Il codice fiscale deve contenere 16 caratteri");
				return false;
			}
			return true;
		}
*/	
	
		function ricarica(){
			
			if(window.opener!=null)
				{
				window.close();
				window.opener.location.reload(true);
				}
			}
	</script>
	
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

                    if (($("#data_nascita").val() !="")){
                        if (Date.isValid( $("#data_nascita").val(), "dd-MM-yyyy") == false){
                            errMsg = errMsg + "Data Scadenza NON valida\n";
                            $("#data_nascita").addClass('errorTextfield');
                        }
                    } else {
                        errMsg = errMsg + "Data Scadenza NON valida\n";
                        $("#data_nascita").addClass('errorTextField');
                    }

                    
                    if (errMsg == errMsgDefault){
                        return confirm("Confermi inserimento?");
                    } else {
                        alert(errMsg);
                        return false;
                    }
                    
                 });


                $("#data_nascita").datepicker({
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
                
                if ($("#data_nascita").val() == ""){
                    $("#data_nascita").val(validStartDate);
                }

            });

       </script>
       	
    </head>

    <body>
    
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        
        <#include "bottoni.ftl"  />    