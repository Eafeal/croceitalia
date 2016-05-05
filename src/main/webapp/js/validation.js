// JavaScript Document


function checkRequiredInput(myInput, label)
	{
	if(myInput.value == "")
		{
		alert("campo "+label+" obbligatorio");
		myInput.focus();
		return false;
		}
	return true;
	}

function isValidName(name) {
	var reg = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?)+$/;
	return reg.test(name);
	}
	
function isValidNumber(value) {
	var reg = /^[0-9]+$/;
	return reg.test(value);
	}

function isValidAlphanumeric(value) {
	var reg = /^[a-zA-Z0-9]+$/;
	return reg.test(value);
	}

function isValidCod3(codice) {
	var reg = /^([0-9]{3})$/;
	if (!reg.test(codice))
		return false;
	else
		return true;
	}

function isValidCod10(codice) {
	var reg = /^([0-9]{10})$/;
	if (!reg.test(codice))
		return false;
	else
		return true;
	}

function isValidPassword(pw)
	{
	if(pw.length < 8)
		{
        alert("La password deve contenere minimo 8 caratteri");
        return false;
		}
	re = /[0-9]/;
	if(!re.test(pw))
		{
		alert("La password deve contenere almeno un numero (0-9)");
		return false;
		}
	re = /[a-z]/;
	if(!re.test(pw))
		{
		alert("La password deve contenere almeno una lettera minuscola (a-z)");
		return false;
		}
	re = /[A-Z]/;
	if(!re.test(pw))
		{
		alert("La password deve contenere almeno una lettera maiuscola (A-Z)");
		return false;
		}
	return true;
	}



// controllo standard sui campi numerici
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

function isValidEmail(email) {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	return reg.test(email);
	}
	
function isValidPhone(tel) {
	var reg = /^([0-9\+]{8,12})$/;
	return reg.test(tel);
	}
	
function isValidCF(cfinput)
	{
	var cf = cfinput.value;
	if(cf == "PROVAX00X00X000Y") return true; // CF di prova, salta controllo formale
	
    var validi, i, s, set1, set2, setpari, setdisp;
    if( cf == '' )  return true;
    cf = cf.toUpperCase();
    if( cf.length != 16 )
		{
		alert("il codice fiscale dovrebbe essere lungo\nesattamente 16 caratteri.");
		cfinput.focus();
		return false;
		}
    validi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    for( i = 0; i < 16; i++ )
		{
        if( validi.indexOf( cf.charAt(i) ) == -1 )
			{
            alert("Il codice fiscale contiene un carattere non valido '"+ cf.charAt(i) +"'.\nI caratteri validi sono le lettere e le cifre.\n");
			cfinput.focus();
			return false;
			}
		}
    set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
    setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
    s = 0;
    for( i = 1; i <= 13; i += 2 )
        s += setpari.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
    for( i = 0; i <= 14; i += 2 )
        s += setdisp.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
    if( s%26 != cf.charCodeAt(15)-'A'.charCodeAt(0) )
		{
		alert("Il Codice Fiscale non è formalmente corretto.");
		cfinput.focus();
		return false;
		}
    return true;
	}
	
function isValidPIVA(pivainput)
	{
	var piva = pivainput.value;
    if( piva == '' )  return true;
    if( piva.length != 11 )
		{
		alert("La partita IVA dovrebbe essere lunga\n esattamente 11 caratteri.");
		pivainput.focus();
		return false;
		}
    validi = "0123456789";
    for( i = 0; i < 11; i++ )
		{
        if( validi.indexOf( piva.charAt(i) ) == -1 )
			{
            alert("La partita IVA contiene un carattere non valido '"+ piva.charAt(i) +"'.\nI caratteri validi sono le lettere e le cifre.\n");
			pivainput.focus();
			return false;
			}
		}
    s = 0;
    for( i = 0; i <= 9; i += 2 )
        s += piva.charCodeAt(i) - '0'.charCodeAt(0);
    for( i = 1; i <= 9; i += 2 ){
        c = 2*( piva.charCodeAt(i) - '0'.charCodeAt(0) );
        if( c > 9 )  c = c - 9;
        s += c;
    }
    if( ( 10 - s%10 )%10 != piva.charCodeAt(10) - '0'.charCodeAt(0) )
		{
		alert("La partita IVA non è formalmente corretta.");
		pivainput.focus();
		return false;
		}
    return true;
	}
	
function checkPrivacy()
	{
	var flag = document.getElementById("privacy");
	if(!flag.checked)
		{
		alert("Consenso privacy obbligatorio");
		return false;
		}
	return true;
	}
