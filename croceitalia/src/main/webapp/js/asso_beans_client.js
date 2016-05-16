//********************************************************************************************
// Rende maiuscolo il campo preso in esame
//********************************************************************************************	
function cf_toUpperCase(unCampoInput)
        {
        unCampoInput.value = unCampoInput.value.toUpperCase();
        }     
        
/********************************************************************************************
function cf_contieneSoloCaratteriValidi(unCampoInput, caratteriValidi)

Ritorna true se il valore di unCampoInput contiene solo caratteri inclusi nella stringa
caratteriValidi.
Altrimrnti torna false.
Esempi:
unCampoInput.value="jkgkjgjkg"
cf_contieneSoloCaratteriValidi(unCampoInput, "1234567890") ==> torna false (il campo deve essere numerico)

unCampoInput.value="AAAAABBBBBCCCCAAA"
cf_contieneSoloCaratteriValidi(unCampoInput, "ABCD") ==> torna TRUE

********************************************************************************************/
      

function getRadioValue(radio)
        {
        var value;
        for (var i=0; i<radio.length; i++)
                {
                if(radio[i].checked) { value = radio[i].value; }
                }
        return value;
        }
		
//---------------- ritorna l'estensione da sola di un file ------------------------------------------------

function fn_estensione(stringa)
        {
        var punto;
        var ext;
        punto = stringa.lastIndexOf(".");
        ext = stringa.substring(punto, stringa.length);
		return ext;
        } 
		
		
//---------------- dal percorso completo ritorna solamente il nome del file -------------------------------

function pulisci_file(path)
        {
        var ultimoSlash;
		var ultimoControSlash;
        var file;
		ultimoControSlash = path.lastIndexOf("\\") + 1;
        file = path.substring(ultimoControSlash, path.length);
		ultimoSlash = file.lastIndexOf("/") + 1;
        file = file.substring(ultimoSlash, file.length);
	    return file;
        }        
		
        
function cf_contieneSoloCaratteriValidi(unCampoInput, caratteriValidi)
        {        
        var valoreStringa = unCampoInput.value;
        var car;
        var cont;
        
        for (cont=0;cont<valoreStringa.length;cont++)
                {
                car=valoreStringa.charAt(cont);
                if (caratteriValidi.indexOf(car) < 0) 
                        {
                        alert("I caratteri validi sono:" + caratteriValidi);
                        unCampoInput.focus();
                        return 0;
                        }
                }
        return 1;
        }

        
/********************************************************************************************
function cf_campoInputNonContiene(unCampoInput, unSetDiCaratteri)

Serve per escludere dei caratteri.

Ritorna 0  se il value di  unCampoInput contiene ALMENO UN CARATTERE incluso nella stringa
unSetDiCaratteri.

Altrimenti torna 1.

********************************************************************************************/
        
function cf_campoInputNonContiene(unCampoInput, unSetDiCaratteri)
        {  
           
        var valoreStringa = unCampoInput.value;
        var contiene = cf_contiene(valoreStringa, unSetDiCaratteri);
        if (contiene) 
                {
                //unSetDiCaratteriValidi contiene car
                alert("Non e' possibile usare i caratteri:" + unSetDiCaratteri);
                unCampoInput.focus();
                return 0;
                }
        return 1;
        }
    
/********************************************************************************************
function cf_contiene(unCampoInput, unSetDiCaratteri)

Serve per sapere se dei caratteri sono contenuti in una stringa

Ritorna true se unaStringa contiene ALMENO UN CARATTERE incluso nella stringa
unSetDiCaratteri.

Altrimenti torna false.

Esempi:
unCampoInput.value="aaaa4444"
cf_contiene(unCampoInput, "1234567890") ==> torna true 

unCampoInput.value="AAAAABBBBBCCCCAAA"
cf_contieneSoloCaratteriValidi(unCampoInput, "XXXXXZZZZWWW") ==> torna false

********************************************************************************************/
function cf_contiene(unaStringa, unSetDiCaratteri)
        {        
        var valoreStringa = unaStringa;
        var car;
        var cont;
        for (cont=0;cont<valoreStringa.length;cont++)
                {
                car=valoreStringa.charAt(cont);
                if (unSetDiCaratteri.indexOf(car) >= 0) 
                        {
                        //unSetDiCaratteriValidi contiene car
                        return 1;
                        }
                }
        return 0;
        }


//********************************************************************************************
// controlla se il campo "numerico"  è compreso tra due valori:min,max(range)  
//********************************************************************************************
function cf_range( valoreInserito, min, max )
{
var  valoreInt = valoreInserito.value; 

if (valoreInt.length==0)
   return 1;

   if ( !cf_contieneSoloCaratteriValidi(valoreInserito, '0123456789' ) )
                return 0;
                
if((valoreInt < min) || (valoreInt > max))
     {
     valoreInserito.focus();
     alert("Il minimo è: " + min +"  Il massimo è: " +max);
     return 0;          
     }
     
return 1;
}

//********************************************************************************************
// controlla se il campo "numerico"  è compreso tra due valori:min,max(range) - torna un boolean solo per il test  
//********************************************************************************************
function cf_range_bool( valoreInserito, min, max )
{
var  valoreInt = valoreInserito.value; 
                
if((valoreInt < min) || (valoreInt > max))
     return 0;          
return 1;
}

/********************************************************************************************
function cf_contieneSoloCaratteriValidi(unCampoInput, caratteriValidi)

Ritorna true se il valore di unCampoInput contiene solo caratteri inclusi nella stringa
caratteriValidi.
Altrimrnti torna false.
Esempi:
unCampoInput.value="jkgkjgjkg"
cf_contieneSoloCaratteriValidi(unCampoInput, "1234567890") ==> torna false (il campo deve essere numerico)

unCampoInput.value="AAAAABBBBBCCCCAAA"
cf_contieneSoloCaratteriValidi(unCampoInput, "ABCD") ==> torna TRUE

********************************************************************************************/
function cf_contieneSoloCaratteriValidi(unCampoInput, caratteriValidi)
        {        
        var valoreStringa = unCampoInput.value;
        var car;
        var cont;
        
        for (cont=0;cont<valoreStringa.length;cont++)
                {
                car=valoreStringa.charAt(cont);
                if (caratteriValidi.indexOf(car) < 0) 
                        {
                        unCampoInput.focus();
                        alert("I caratteri validi sono:" + caratteriValidi);
                        return 0;
                        }
                }
        return 1;
        }
        
        

function cf_checkdate(campoData) 
	{
	
	if ( cf_contieneSoloCaratteriValidi(campoData, "0123456789 /-")== 0 )
		return false;
	
	if (chkdate(campoData) == false) 
		{
		campoData.select();
		campoData.focus();
		alert("Formato data non valido");
		return false;
		}
	return true;
	}	
	
function chkdate(campoData) 
        {
        if(campoData.value.length>0 && campoData.value.length<6)
                return false;
        //var strDatestyle = "US"; //United States date style
        var strDatestyle = "EU";  //European date style
        var strDate;
        var strDateArray;
        var strDay;
        var strMonth;
        var strYear;
        var intday;
        var intMonth;
        var intYear;
        var booFound = false;
        
        var strSeparatorArray = new Array("-"," ","/",".");
        var intElementNr;
        var err = 0;
        var strMonthArray = new Array(12);
        strMonthArray[0] = "01";
        strMonthArray[1] = "02";
        strMonthArray[2] = "03";
        strMonthArray[3] = "04";
        strMonthArray[4] = "05";
        strMonthArray[5] = "06";
        strMonthArray[6] = "07";
        strMonthArray[7] = "08";
        strMonthArray[8] = "09";
        strMonthArray[9] = "10";
        strMonthArray[10] = "11";
        strMonthArray[11] = "12";
        strDate = campoData.value;
        
        if (strDate.length < 1) {
        return true;
        }
        for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) 
                {
                if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) 
                        {
                        //la stringa contiene un separatore
                        strDateArray = strDate.split(strSeparatorArray[intElementNr]);
                        if (strDateArray.length != 3) 
                                {
                                err = 1;
                                return false;
                                }
                        else 
                                {
                                strDay   = strDateArray[0];
                                strMonth = strDateArray[1];
                                strYear  = strDateArray[2];
                                }
                        booFound = true;
                        }
                }
        if (booFound == false) 
                {
                if (strDate.length>5) 
                        {
                        strDay   = strDate.substr(0, 2);
                        strMonth = strDate.substr(2, 2);
                        strYear  = strDate.substr(4);
                        }
                }
        
        if (strYear.length == 2) 
                {
                strYear = '20' + strYear;
                }
        // US style
        if (strDatestyle == "US") 
                {
                strTemp  = strDay;
                strDay   = strMonth;
                strMonth = strTemp;
                }
        intday = parseInt(strDay, 10);
        if (isNaN(intday)) 
                {
                err = 2;
                return false;
                }
        intMonth = parseInt(strMonth, 10);
        if (isNaN(intMonth)) 
                {
                for (i = 0;i<12;i++) 
                        {
                        if (strMonth.toUpperCase() == strMonthArray[i].toUpperCase()) 
                                {
                                intMonth = i+1;
                                strMonth = strMonthArray[i];
                                i = 12;
                                }	
                        }
                if (isNaN(intMonth)) 
                        {
                        err = 3;
                        return false;
                        }
                }
                
        intYear = parseInt(strYear, 10);
        if (isNaN(intYear)) 
                {
                err = 4;
                return false;
                }
        if (intMonth>12 || intMonth<1) 
                {
                err = 5;
                return false;
                }
        if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) 
                {
                err = 6;
                return false;
                }
        if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) 
                {
                err = 7;
                return false;
                }
        if (intMonth == 2)	
                {
                if (intday < 1) 
                        {
                        err = 8;
                        return false;
                        }
                if (LeapYear(intYear) == true) 
                        {
                        if (intday > 29) 
                                {
                                err = 9;
                                return false;
                                }
                        }
                else 
                        {
                        if (intday > 28) 
                                {
                                err = 10;
                                return false;
                                }
                        }
                }
        var stringDay;
        if(intday<10)
                stringDay = "0" + intday;
        else
                stringDay = "" + intday;
                
        if (strDatestyle == "US") 
                {
                campoData.value = strMonthArray[intMonth-1] + "/" + stringDay + "/" + strYear;
                }
        else 
                {
                campoData.value = stringDay + "/" + strMonthArray[intMonth-1] + "/" + strYear;
                }
        return true;
        }

function LeapYear(intYear) 
	{
	if (intYear % 100 == 0) 
		{
		if (intYear % 400 == 0) 
			{ return true; }
		}
	else 
		{
		if ((intYear % 4) == 0) 
			{ return true; }
		}
	return false;
	}
	
	
	
	
/*-------------------------------------------------------------------------------------------------
PASSANDOGLI UNA STRINGA CONTENENTE UNA DATA NEL FORMATO GG/MM/AAA RITORNA UN OGGETTO DATA 	
-------------------------------------------------------------------------------------------------*/
function getDateFromString(campoData) // formato gg/mm/aaaa
	{
	if(!cf_checkdate(campoData))
		return;
		
	stringDate = campoData.value;
	var separ = stringDate.split(/[\/\.\-]/);
	var data = new Date(separ[2], parseInt(separ[1],10)-1, separ[0]);
	return data;
	}
	


        
/*-------------------------------------------------------------------------------------------------
Passare alla funzione i seguenti parametri:
una stringa(composta da + stringhe divise da un carattere separatore) ed il carattere separatore;
divide la stringa in subString e crea un array di stringhe il cui ultimo elemento è una stringa vuota.
esempio.:
var valori = cf_arrayString('pippo£pollo£pluto','£');
valori[0] = 'pippo';
valori[1] = 'pollo';
valori[2] = 'pluto';
valori[3] = '';
-------------------------------------------------------------------------------------------------*/
function cf_arrayString(aString,aChar)
        {
        var ind = 0;       
        var valori = new Array();
        var trovato = true;
        var newString = aString;
        var nome ="";
        var cont = 0;
        while (trovato)        
                {                
                ind = newString.indexOf(aChar);
                if (ind >= 0)
                        {
                        nome = newString.substr(0,ind);
                        newString = newString.substr(ind+1);
                        valori[cont] = nome;                        
                        cont++;
                        }
                else
                        trovato = false;
                }
        valori[cont] = newString; 
        return valori;             
        }        
//  End


//********************************************************************************************
//  routine per il controllo dell'indirizzo email
//********************************************************************************************	
function controlla_email(campo)
        {
		if (campo.value!='')
			{			
			dato=campo.value;
			if (dato.indexOf(".")==-1 || dato.indexOf("@")==-1) 
				{
				alert("E-mail non corretta!");
				campo.focus();
				}
			}
        }
		
//********************************************************************************************
//  routine per il controllo del max num di caratteri contenibili in un input o textarea - 
//  parametri passati: campo input (o textarea), num max di caratteri
//********************************************************************************************
function max_caratteri_textarea(campo, maxcar)
	{
	if (campo.value.length > maxcar)
		{
		alert("Il campo " + campo.name + " non può contenere più di " + maxcar + " caratteri");
		var rimane = campo.value.substr(0,200);
		campo.value = rimane;
		campo.focus();
		}
	}

