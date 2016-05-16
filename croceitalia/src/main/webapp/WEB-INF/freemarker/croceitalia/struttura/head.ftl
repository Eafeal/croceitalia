<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
	<link rel="stylesheet"    href="/css/main.css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
	
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<meta name="layout" content="main"/>
	
	<#if title?? ><title>${title}</title></#if>
	
	<script type="text/javascript" src="/js/bubbleSort.js"></script>
	<script type="text/javascript" src="/js/asso_beans_client.js"></script>
	
	<script type="text/javascript">
	
	function controlloStruttura()
	{	
	//CONTROLLI DI OBBLIGATORIETA'
	
		if (strutturaForm.fk_id_tipologia_struttura.value == "") {
			strutturaForm.fk_id_tipologia_struttura.focus();
			alert(" La tipologia della struttura è obbligatoria!");
			return false;
		}
		if (strutturaForm.nome.value == "") {
			strutturaForm.nome.focus();
			alert(" La descrizione è obbligatoria!");
			return false;
		}
		if (strutturaForm.descrizione_breve.value == "") {
			strutturaForm.descrizione_breve.focus();
			alert(" Una descrizione breve è obbligatoria!");
			return false;
		}
		if (strutturaForm.comune.value == "") {
			strutturaForm.comune.focus();
			alert(" Il comune è obbligatorio!");
			return false;
		}
	//--------CONTROLLO validità ma non obbligatoria--------
	
		if (strutturaForm.via.value != "") {
			if (!controllo_via(strutturaForm.via)) return false;
		}
	
		if (strutturaForm.cap.value != "") {
			if (!controllo_cap(strutturaForm.cap)) return false;
		}
		if (strutturaForm.provincia.value != "") {
			if (!controllo_provincia(strutturaForm.provincia)) return false;
		}
		if (strutturaForm.telefono.value != "") {		
			if (!controllo_telefono(strutturaForm.telefono)) return false;
		}
		
		if (strutturaForm.email.value != "") {		
			if (!controllo_email(strutturaForm.email)) return false;
		}
		
		if (strutturaForm.cod_regione.value != "") {		
			if (!controllo_regione(strutturaForm.cod_regione)) return false;
		}
		
		if (strutturaForm.cod_asl.value != "") {		
			if (!controllo_asl(strutturaForm.cod_asl)) return false;
		}
		
		if (strutturaForm.cod_struttura.value != "") {		
			if (!controllo_CodStruttura(strutturaForm.cod_struttura)) return false;
		}
	//CONTROLLI DI VALIDITA'	
		
		if (!controllo_nome(strutturaForm.nome)) {
			return false;
		}

		if (!controllo_comune(strutturaForm.comune)) {
			return false;
		}
		
	strutturaForm.submit();
	}
	
	//Controllo Nome
	function controllo_nome(nome) {
		if ((nome.value.length < 2) || (nome.value.length > 30)) {
			nome.focus();
			alert("Il minimo è 2  Il massimo è: 30");
			return false;
		}

		//questa funziona cf_contiene... è stato preso dal file di Davide ( asso_beans_client.js)
		if (!cf_contieneSoloCaratteriValidi(nome,
				'qwertyuiopasdfghjklzxcvbnm QWERTYUIOPASDFGHJKLZXCVBNM1234567890.-/\()'))
			return false;
		return true;
	}

	//telefono
	function controllo_telefono(telefono) {

		if (telefono.value.length < 10) {//non deve essere minore 
			telefono.focus();// focus quando trova l'errore e ti posiziona dove c'è l'errore
			alert("ci sono meno di 10 numeri ");
			return false;
		}
		
		if (!cf_contieneSoloCaratteriValidi(telefono, '0123456789 +-/'))
			return false;
		return true;
	}
	
	//email 
	function controllo_email(email) {
		if (email.value.length < 6) {
			email.focus();
			alert("l'email non deve essere minore di 6 caratteri");
			return false;
		}

		if (!cf_contieneSoloCaratteriValidi(email,
				'qwertyuiopasdfghjklzxcvbnm QWERTYUIOPASDFGHJKLZXCVBNM@._123456789'))
			return false;

		if (email.value.indexOf(".") == -1 || email.value.indexOf("@") == -1) {
			alert("E-mail non corretta! Manca un punto o la @");
			email.focus();
			return false;
		}
		return true;
	}

	//------------------INDIRIZZO-----------
		function controllo_via(via) {
		if (via.value.length < 3) {
			via.focus();
			alert("La via deve essere minimo di 3 caratteri");
			return false;
			}
		return true;
		}
		
		function controllo_cap(cap) {
		if (cap.value.length != 5) {//non deve essere minore 
			cap.focus();// focus quando trova l'errore e ti posiziona dove c'è l'errore
			alert("Il cap deve essere di 5 caratteri ");
			return false;
		}
		if (!cf_contieneSoloCaratteriValidi(cap, '1234567890'))
			return false;
		return true;
		}
	
		function controllo_comune(comune) {
		if (comune.value.length < 3) {
			comune.focus();
			alert("Il comune deve avere essere minimo di 3 caratteri");
			return false;
			}
		return true;
		}
		
		function controllo_provincia(provincia) {
		if (provincia.value.length != 2) {
			provincia.focus();
			alert("La provincia deve avere solo 2 caratteri");
			return false;
		}
		if (!cf_contieneSoloCaratteriValidi(provincia,'qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM'))
			return false;
		return true;
		}
	//-------FINE validità INDIRIZZO---------------------

	//VALIDITA' CODICE
		function controllo_regione(regione) {
		if (regione.value.length != 3) {
			regione.focus();
			alert("Il codice della regione deve avere solo 3 caratteri");
			return false;
		}
		if (!cf_contieneSoloCaratteriValidi(regione,'1234567890'))
			return false;
		return true;
		}
		
		function controllo_asl(asl) {
		if (asl.value.length != 3) {
			asl.focus();
			alert("Il codice dell'ASL deve avere solo 3 caratteri");
			return false;
		}
		if (!cf_contieneSoloCaratteriValidi(asl,'1234567890'))
			return false;
		return true;
		}
	
		function controllo_CodStruttura(struttura) {
		if (struttura.value.length != 6) {
			struttura.focus();
			alert("Il codice della struttura deve avere solo 6 caratteri");
			return false;
		}
		if (!cf_contieneSoloCaratteriValidi(struttura,'1234567890'))
			return false;
		return true;
		}
	</script>
	
	
	<script type="text/javascript">
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


				$("#strutturaForm").submit(function() {

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