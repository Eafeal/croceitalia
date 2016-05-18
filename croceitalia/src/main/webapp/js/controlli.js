
	
	
	function controlli()
	{
		if (insForm.fk_id_patologia.value == ""){
			alert(" La patologia è obbligatoria!");
			insForm.fk_id_patologia.focus();
			return false;
		}
		if (insForm.cognome.value == "") {
			insForm.cognome.focus();
			alert(" Il cognome è obbligatorio!");
			return false;
		}
		
		if (insForm.nome.value == "") {
			insForm.nome.focus();
			alert("Il nome è obbligatorio!");
			return false;
		}
		
		if (insForm.comune.value == "") {
			insForm.comune.focus();
			alert(" Il comune è obbligatorio!");
			return false;
		}
		
		if((!document.insForm.sesso[0].checked) && (!document.insForm.sesso[1].checked)){
			alert("Il sesso è obbligatorio");
			document.insForm.sesso.focus();
			return false;
		}
		
		if (insForm.data_nascita.value == "") {
			insForm.data_nascita.focus();
			alert(" La data di nascita è obbligatoria!");
			return false;
		}
		
		//-----------------------------------
		if (insForm.via.value != "") {
			if (!controllo_via(insForm.via)) return false;
		}
		
		if (insForm.cap.value != "") {
			if (!controllo_cap(insForm.cap)) return false;
		}
		if (insForm.provincia.value != "") {
			if (!controllo_provincia(insForm.provincia)) return false;
		}
		
		if (insForm.telefono1.value != "") {		
			if (!controllo_telefono(insForm.telefono1)) return false;
		}
		
		if (insForm.telefono2.value != "") {		
			if (!controllo_telefono(insForm.telefono2)) return false;
		}
		//---------------------------
		if (!controllo_nome(insForm.nome)) {
			return false;
		}
		
		if(!controllo_cognome(insForm.cognome)){
			return false;
		}	
		
		if(!controllo_comune(insForm.comune)){
			return false;
		}
		

	insForm.submit();
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
				'qwertyuiopasdfghjklzxcvbnm QWERTYUIOPASDFGHJKLZXCVBNM'))
			return false;
		return true;
	}

	//Controllo cognome
	function controllo_cognome(cognome) {
		if ((cognome.value.length < 2) || (cognome.value.length > 30)) {
			cognome.focus();
			alert("Il Cognome deve essere minimo di 2 caratteri e  massimo 30 caratteri");
			return false;
		}
		if (!cf_contieneSoloCaratteriValidi(cognome,
				'qwertyuiopasdfghjklzxcvbnm QWERTYUIOPASDFGHJKLZXCVBNM'))
			return false;
		return true;
	}
	
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
