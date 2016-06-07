function controlloCIG(){
	if (cf_contieneSoloCaratteriValidi(TdocumentoForm.CIG, "1234567890QWERTYUIOPASDFGHJKLZXCVBNM") == 0) return false;
}

//INIZIO AJAX 
$(document).ready(function(e){ 
	$("#create").click(function(){
		
		//-------------------------Controllo di obbligatorietà e validità CIG------------------------------------
		
			if(TdocumentoForm.CIG.value == ""){
				alert("IL CIG e' obbligatorio!");
				TdocumentoForm.CIG.focus();
				return false;
			}
		
			if(TdocumentoForm.mese_documento.value == ""){
				alert("Seleziona il mese di riferimento!");
				TdocumentoForm.mese_documento.focus();
				return false;
			}
			if(TdocumentoForm.fk_id_mezzo.value == ""){
				alert("Seleziona il mezzo!");
				TdocumentoForm.fk_id_mezzo.focus();
				return false;
			}
			if(TdocumentoForm.fk_id_cliente.value == ""){
				alert("Seleziona il cliente!");
				TdocumentoForm.fk_id_cliente.focus();
				return false;
			}
			if(TdocumentoForm.fk_id_banca.value == ""){
				alert("Seleziona la banca d'appoggio!");
				TdocumentoForm.fk_id_banca.focus();
				return false;
			}
			if (!controllo_cig(TdocumentoForm.CIG)) {
					return false;
				}
			alert("Faccio submit");
			TdocumentoForm.submit();
			//Controllo Numero CIG
			
			function controllo_cig(cig) {
		
				if (cig.value.length < 10) {//non deve essere minore 
					cig.focus();// focus quando trova l'errore e ti posiziona dove c'è l'errore
					alert("ci sono meno di 10 caratteri ");
					return false;
				}
				if (!cf_contieneSoloCaratteriValidi(cig,'1234567890QWERTYUIOPASDFGHJKLZXCVBNM'))
					return false;
				return true;
			}
		});
});

//$(document).ready(function(e){ 
//	$("#createggg").click(function(){
//	
//    var url = "/edit/documento_testata/save2"; // the script where you handle the form input.
//    $.ajax({
//           type: "POST",
//           url: url,
//           data: $("#TdocumentoForm").serialize(), // serializes the form's elements.
//           success: function(response){
//        	   
//		        	   alert(response);//Dalla chiave messaggio di Paziente Controller
//		        	   var res = response.split(",");//funzione che prende una stringa e divide in base al parametro e li numera per posizione
//		        	   
//		        	   if(res[0]=='KO')
//		        		   	alert("Inserimento fallito!");
//		        	   else
//		        		   {
//		        		  /*  alert(indice); *///sempre 1
//		        		   alert(res[1]); 
//		       	   		   alert(res[2]); 
//		       	   		  /* var yy = "#fk_numero"+indice;//  equivale a fk_paziente1 */
//		       	   		   var xx = "#trasportato"+indice;
//		            	  // $(yy).val(res[2]);
//		            	   $(xx).val(res[1]);
//		            	   document.location = '/edit/documento_testata/list#';
//		            	   location.reload();
//		            	   //resetta tutto
//		            	   document.TdocumentoForm.reset(); 
//           	   
//		       	   		}
//        	   		}
//         		});
//    	//e.preventDefault(); // avoid to execute the actual submit of the form.
//		});
//});

$(document).ready(function(e){ 
	$("#stampaPDF2").click(function(){
		alert("IO CI SONO");
		stampaForm1.submit();
	alert("Ciao SONO ancora io");
		});
});

// GENERAZIONE PDF 

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