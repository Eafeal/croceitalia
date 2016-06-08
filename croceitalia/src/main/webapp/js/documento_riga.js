

/* VISUALIZZA POPUP PER L'INSERIMENTO RIGA */

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
	
	/* alert(indice); *///era 0
   insRigaForm.submit();
		});
});

// Lo stato del documento verrà chiuso
$(document).ready(function(e){ 
	$("#chiusura").click(function(){
		
		if (!confirm('Sei sicuro?')){
			return false;
		}
		
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
		
		if (!confirm('Sei sicuro?')){
			return false;
		}
		
	updateForm.submit();
		});
});

$(document).ready(function(e){ 
	$("#eliminaRiga").click(function(){
		if (!confirm('Confermi cancellazione?')){
			return false;
		}
		eliminaRigaForm.submit();
		});
});

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