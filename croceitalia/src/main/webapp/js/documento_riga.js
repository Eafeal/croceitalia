/* VISUALIZZA POPUP PER L'INSERIMENTO RIGA */

function resetRiga(){
	document.rigaForm.reset(); 
}
var indice = 0;

function mostra(ind) {
	indice = ind;
	document.location = '#popup1';
}

/* Non serve più
 * 
function percorso(ind) {
	indice = ind;
	
	if(trasportato1.value == "")
		alert("Non puoi inserire nulla!");
		else
		document.location = '#popup2';
} */
/* FINE --- VISUALIZZA POPUP PER L'INSERIMENTO RIGA */


//INIZIO AJAX ------------
$(document).ready(function(e){ 
	$("#creaPersona").click(function(){
	
	/* alert(indice); *///era 0
    var url = "/edit/paziente/save2"; // the script where you handle the form input.
    $.ajax({
           type: "POST",
           url: url,
           data: $("#insForm").serialize(), // serializes the form's elements.
           success: function(response){
        	   
		        	   alert(response);//Dalla chiave messaggio di Paziente Controller
		        	   var res = response.split(",");//funzione che prende una stringa e divide in base al parametro e li numera per posizione
		        	   if(res[0]=='KO')
		        		   	alert("Inserimento fallito!");
		        	   		
		        	   else
		        		   {
		        		  /*  alert(indice); *///sempre 1
		        		/* alert(res[0]);//Ok */
		        		 /*   alert(res[1]); *///Nome Cognome
		       	   		 /*   alert(res[2]); */// Id del paziente 
		       	   		   var yy = "#fk_paziente"+indice;//  equivale a fk_paziente1
		       	   		// var xx = "#trasportato"+indice;
		       	   		   var xx = "#trasportato";
		            	   $(yy).val(res[2]);
		            	   $(xx).val(res[1]);
		            	   document.location = '#gestioneRiga';
		            	   
		            	   //resetta tutto
		            	   document.insForm.reset(); 
		       	   		}
        	   		}
         		});
    	//e.preventDefault(); // avoid to execute the actual submit of the form.
		});
});

//--- PER CREARE LA RIGA
$(document).ready(function(e){ 
	$("#createRiga").click(function(){
//Contr. Obbligatorietà
		
		if(rigaForm.fk_id_paziente.value == ""){
			alert("Il paziente e' obbligatorio!");
			rigaForm.fk_id_paziente.focus();
			return false;
		}
		if(rigaForm.fk_id_struttura.value == ""){
			alert("La destinazione e' obbligatoria!");
			rigaForm.fk_id_struttura.focus();
			return false;
		}
		if(rigaForm.km_percorso.value == ""){
			alert("I kilometri del percorso e' obbligatorio!");
			rigaForm.km_percorso.focus();
			return false;
		}
		if(rigaForm.num_sedute.value == ""){
			alert("Il numero delle persone e' obbligatorio!");
			rigaForm.num_sedute.focus();
			return false;
		}
		if(rigaForm.mese.value == ""){
			alert("Il mese e' obbligatorio!");
			rigaForm.mese.focus();
			return false;
		}
		if(rigaForm.ora_sosta.value == ""){
			alert("Il numero delle ore di sosta e' obbligatorio!");
			rigaForm.ora_sosta.focus();
			return false;
		}
		if(rigaForm.quota_fissa_s.value == ""){
			alert("La quota fissa e' obbligatoria!");
			rigaForm.quota_fissa_s.focus();
			return false;
		}
//		if(rigaForm.diritto_uscita.value == ""){
//			alert("Il diritto di uscita e' obbligatorio!");
//			rigaForm.diritto_uscita.focus();
//			return false;
//		}
		
// Contr. Validità
		if (cf_contieneSoloCaratteriValidi(rigaForm.km_percorso, ".,1234567890") == 0) return false;
		if (cf_contieneSoloCaratteriValidi(rigaForm.num_sedute, "1234567890") == 0) return false;
		if (cf_contieneSoloCaratteriValidi(rigaForm.ora_sosta, "1234567890") == 0) return false;
		if (cf_contieneSoloCaratteriValidi(rigaForm.quota_fissa_s, ".,1234567890") == 0) return false;
		if (cf_contieneSoloCaratteriValidi(rigaForm.diritto_uscita, "1234567890") == 0) return false;
		
		if (!confirm('Sei sicuro? \nNon potrai piu\' modificare il mezzo!')){
			return false;
		}
		
		showLoader();
	   //insRigaForm.submit(); è già una submit appena entra qua
		});
});

// Lo stato del documento verrà chiuso
$(document).ready(function(e){ 
	$("#chiusura").click(function(){
		
		if(updateForm.CIG.value==""){
			alert("Il CIG e' obbligatorio");
			return false;
		}
		updateForm.submit();
		
		if (!confirm('Sei sicuro? \nDopo la chiusura del documento, non potrai piu\' aggiungere nuove righe!')){
			return false;
		}
		showLoader();
		chiusuraForm.submit();
		});
});

//Puoi generare e stampare il PDF
$(document).ready(function(e){ 
	$("#stampaPDF").click(function(){
	stampaForm.submit();
		});
});

//Aggiorni il documento modificato 
$(document).ready(function(e){ 
	$("#update").click(function(){
		
		if (cf_contieneSoloCaratteriValidi(updateForm.data_documento, "\n1234567890-") == 0) return false;
		if (cf_contieneSoloCaratteriValidi(updateForm.CIG, "\n1234567890\nQWERTYUIOPASDFGHJKLZXCVBNM\nqwertyuiopasdfghjklzxcvbnm") == 0) return false;
		if (!confirm('Sei sicuro?')){
			return false;
		}
		
		showLoader();
		updateForm.submit();
		});
});

$(document).ready(function(e){ 
	$("#inviaPDF").click(function(){
		if (!confirm('Confermi invio?')){
			return false;
		}
		showLoader();
		inviaPDForm.submit();
		});
});


//$(document).ready(function(e){ 
//	$("#eliminaRiga").click(function(){
//		if (!confirm('Confermi cancellazione?')){
//			return false;
//		}
//		eliminaRigaForm.submit();
//		});
//});

// CALCOLO DELL'IMPORTO e dei KM TOTALI

function totaleImporto()
{

rigaForm.importo_s.value = 0;
rigaForm.km_totali.value = 0;
	
if (cf_contieneSoloCaratteriValidi(rigaForm.km_percorso, ".,1234567890") == 0) return false;
if (cf_contieneSoloCaratteriValidi(rigaForm.num_sedute, "1234567890") == 0) return false;

if (rigaForm.km_percorso.value == "")
	return false;

if (rigaForm.num_sedute.value == "")
	return false;
	
var numsedute = eval(pulisciNumero(rigaForm.num_sedute.value));
var kmpercorso = eval(pulisciNumero(rigaForm.km_percorso.value));

var km_totali = numsedute * kmpercorso;
km_totali = (Math.round(km_totali * 100) /100)+"";
rigaForm.km_totali.value = km_totali.replace(".",",");

//----------

if (cf_contieneSoloCaratteriValidi(rigaForm.quota_fissa_s, ".,1234567890") == 0) return false;
if (cf_contieneSoloCaratteriValidi(rigaForm.costokm, ".,1234567890") == 0) return false;
if (cf_contieneSoloCaratteriValidi(rigaForm.franchigiakm, ".,1234567890") == 0) return false;

if (rigaForm.quota_fissa_s.value == "")
	return false;
	
if (rigaForm.costokm.value == "")
	return false;
	
if (rigaForm.franchigiakm.value == "")
	return false;

var franchigia = eval(pulisciNumero(rigaForm.franchigiakm.value));

var kmDaConsiderare = kmpercorso - franchigia;

if(kmDaConsiderare < 0){
	kmDaConsiderare = 0;
}

var costoKm = eval(pulisciNumero(rigaForm.costokm.value));
var importoParziale = costoKm * kmDaConsiderare;
var quotaFissa = pulisciNumero(rigaForm.quota_fissa_s.value);

importoParziale = eval(importoParziale) + eval(quotaFissa);

//risPF *= pulisciNumero(rigaForm.num_sedute.value);

var importoTotale = importoParziale * numsedute;
importoTotale = (Math.round(importoTotale * 100) /100)+"";
rigaForm.importo_s.value = importoTotale.replace(".",",");

}


//
function pulisciNumero(numero)
{
var nuovoNumero = numero.replace(".","");
nuovoNumero = nuovoNumero.replace(",",".");
return nuovoNumero;
 }

function  f_win_log(url)
{
	   var w;
	   var h;
	   var pw;
	   var ph;

	   w = 870;
	   h = 600;
	   pw = Math.floor((screen.width-w)/2);
	   ph = 0;
	
		new_win=window.open(url,"Errori", "location=0,menubar=0,resizable=1,scrollbars, width="+ w +", height="+ h +", top="+ ph +", left="+ pw);
		new_win.focus();
} 

// JAVASCRIPT DAVIDE


function showLoader()
 {
 document.getElementById("wait").style.display = "block";
 }
 
function hideLoader()
 {
 document.getElementById("wait").style.display = "none";
 }

//-----------
function cancellaRiga(id)
{
	if (!confirm('Confermi cancellazione?')){
		return false;
	}
	eliminaRigaForm.id_riga.value = id;
	//eliminaRigaForm.action += "/"+ id;
	showLoader();
	eliminaRigaForm.submit();
}


