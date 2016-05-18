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
		
		function controlli(){
		
			if(clienteForm.fk_tipo_cliente.value==0){
				alert("La tipologia cliente è obbligatoria");
				return false;
			}
			if(clienteForm.ragione_sociale.value==""){
				clienteForm.ragione_sociale.focus();
				alert("La ragione sociale è obbligatoria");
				return false;
			}
			if(clienteForm.partitaIva.value==""){
				clienteForm.partitaIva.focus();
				alert("La partita iva è obbligatoria");
				return false;
			}
			if (!controllo_p_iva(clienteForm.partitaIva)) {
				return false;
			}
			if(clienteForm.telefono1.value==""){
				clienteForm.telefono1.focus();
				alert("Il numero di telefono è obbligatorio");
				return false;
			}
			if (!controllo_telefono(clienteForm.telefono1)) {
				return false;
			}
			if (!controllo_telefono1(clienteForm.telefono2)) {
				return false;
			}
			if(clienteForm.email.value==""){
				clienteForm.email.focus();
				alert("L'e-mail è obbligatoria");
				return false;
			}
			if (!controllo_email(clienteForm.email)) {
				return false;
			}
			if(clienteForm.via.value==""){
				clienteForm.via.focus();
				alert("La via è obbligatoria");
				return false;
			}
			if(clienteForm.cap.value==""){
				clienteForm.cap.focus();
				alert("Il cap è obbligatorio");
				return false;
			}
			if (!controllo_cap(clienteForm.cap)) {
				return false;
			}
			if(clienteForm.comune.value==""){
				clienteForm.comune.focus();
				alert("Il comune è obbligatorio");
				return false;
			}
			if (!controllo_comune(clienteForm.comune)) {
				return false;
			}
			if(clienteForm.provincia.value==""){
				clienteForm.provincia.focus();
				alert("La provincia è obbligatoria");
				return false;
			}
			if (!controllo_provincia(clienteForm.provincia)) {
				return false;
			}
			if (!controllo_qfs(clienteForm.qfs)) {
				return false;
			}
			clienteForm.submit();
		}
		
		
		function controllo_qfs(qfs) {
			if (!cf_contieneSoloCaratteriValidi(qfs,'0123456789,.')){
				return false;
			}
			return true;
		}
			
		function controllo_p_iva(p_iva) {
			if (p_iva.value.length != 11) {
				p_iva.focus();
				alert("La partita iva deve contenere 11 caratteri");
				return false;
			}
			if (!cf_contieneSoloCaratteriValidi(p_iva,'0123456789')){
				return false;
			}
		
			return true;
		}
		
		function controllo_telefono(telefono) {
			if (telefono.value.length < 10) { 
				telefono.focus();
				alert("Il numero di telefono deve essere composto da 10 numeri ");
				return false;
			}
			if (!cf_contieneSoloCaratteriValidi(telefono, '0123456789 +-/'))
				return false;
			return true;
		}
		
		function controllo_telefono1(telefono) {
			if (!cf_contieneSoloCaratteriValidi(telefono, '0123456789 +-/'))
				return false;
			return true;
		}
		
		function controllo_email(email) {
			if (email.value.length < 6) {
				email.focus();
				alert("l'email deve avere almeno 6 caratteri");
				return false;
			}
	
			if (!cf_contieneSoloCaratteriValidi(email,
					'qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM@._123456789')){
				return false;
			}
	
			if (email.value.indexOf(".") == -1 || email.value.indexOf("@") == -1) {
				alert("L'email deve contenere i caratteri: . @");
				email.focus();
				return false;
			}
			return true;
		}
		function controllo_cap(cap) {
			if (cap.value.length != 5) {
				cap.focus();
				alert("Il cap deve essere composto da 5 numeri");
				return false;
			}
			if (!cf_contieneSoloCaratteriValidi(cap, '0123456789'))
				return false;
			return true;
		}
		
		function controllo_comune(comune) {
			if (comune.value.length < 3) {
				comune.focus();
				alert("Il comune deve contenere almeno tre caratteri");
				return false;
			}
			if (!cf_contieneSoloCaratteriValidi(comune,'qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM ')){
				return false;
			}
		
			return true;
		}
		
		function controllo_provincia(provincia) {
			if (provincia.value.length != 2) {
				provincia.focus();
				alert("La provincia deve essere composta da due caratteri");
				return false;
			}
			if (!cf_contieneSoloCaratteriValidi(provincia,'qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM ')){
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

    </head>

    <body>
    
        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
        
        <#include "bottoni.ftl"  />    