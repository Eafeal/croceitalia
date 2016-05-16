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
		
			if(mezzoForm.fk_tipo_mezzo.value==0){
				alert("La tipologia del mezzo � obbligatoria");
				return null
			}

			if(mezzoForm.targa.value==""){
				mezzoForm.targa.focus();
				alert("La targa � obbligatoria");
				return null
			}
			if (!controllo_targa(mezzoForm.targa)) {
				return null;
			}
			if(mezzoForm.descrizione.value==""){
				mezzoForm.descrizione.focus();
				alert("La descrizione � obbligatoria");
				return null
			}
			if(mezzoForm.costo_km.value==""){
				mezzoForm.costo_km.focus();
				alert("Il costo kilometrico � obbligatorio");
				return null
			}
			if(mezzoForm.franchigia_km.value==""){
				mezzoForm.franchigia_km.focus();
				alert("I kilometri di franchigia sono obbligatori");
				return null
			}
			if(mezzoForm.qf.value==""){
				mezzoForm.qf.focus();
				alert("La quota fissa � obbligatoria");
				return null
			}
			if(mezzoForm.distretto.value==""){
				mezzoForm.distretto.focus();
				alert("Il distretto di appartenenza � obbligatorio");
				return null
			}
			mezzoForm.submit();
		}
		
		function controllo_targa(targa) {
			if ((targa.value.length != 7)) {
				targa.focus();
				alert("La targa deve contenere 7 caratteri");
				return false;
			}
		
			return true;
		}
	
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