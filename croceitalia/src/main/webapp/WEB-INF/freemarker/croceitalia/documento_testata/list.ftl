<!DOCTYPE html>
<html lang="en"><head><meta charset="utf-8">
    <meta charset="utf-8">
    <title>Croce Italia Bernate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6/html5shiv.min.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="ico/favicon.png">
  <style type="text/css"></style></head>
<style>
.tabella{
 	padding-top:5%;
 	padding-bottom:5%;
}
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}
td {
	padding-bottom: 0px;
}

td input{
	padding-bottom: 0px;
	text-align:center;
}
tr:nth-child(even){background-color: #f2f2f2}

th {
	text-align: center;
	background-color: #E9AC38;
	color: white;
}
.numero{
	width: 85px;
}
.anno{
	width: 50px;
}
.mese{
	width: 83px;
}
.data{
	width: 110px;
}
.cliente{
	width: 210px;
}
.mezzo{
	width: 205px;
}
.stato{
	width: 86px;
}
.totale{
	width: 40px;
}
.pdf{
	width: 40px;
}


.overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  visibility: hidden;
  opacity: 0;
}

.overlay:target {
  visibility: visible;
  opacity: 1;
}

.popup {
  margin: 70px auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 80%;
  position: relative;
  transition: all 1s ease-in-out;
}

.popup h2 {
  margin-top: 0;
  color: #333;
  font-family: Tahoma, Arial, sans-serif;
}
.popup .close {
  position: absolute;
  top: 20px;
  right: 30px;
  transition: all 200ms;
  font-size: 30px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
.popup .close:hover {
  color: #06D85F;
}
.popup .content {
  max-height: 30%;
  overflow: auto;
}

@media screen and (max-width: 700px){
  .box{
    width: 70%;
  }
  .popup{
    width: 70%;
  }
}


button[class^="btn"] {
	display: inline-block;
	border-radius: 3px;
	cursor: pointer;
	font-weight: 500;
	font-family: 'Open sans', sans-serif;
	transition:0.14s;
}
.btn {
	padding:7px 13px;
	background: white;
	border-width: 2px;
	border-style: solid;
}

.btn-default {
	border-color: #7f8c8d;
	color: #7f8c8d;
}

.btn:hover {
	color:white;
}
.btn-default:hover {
	background: #7f8c8d;
}
.btn-vlarge {
	padding: 18px 24px;
	font-size: 20px;
}

</style>
  <body cz-shortcut-listen="true">

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Croce Italia</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/edit/home">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#popup1">Nuovo Documento</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
   	  <article class="tabella">
      <table width="100%" border="0">
  <tbody>
    <tr>
      	<th scope="col" style="font-size: 12px;">Nota di Rimborso spesa n</th>
      	<th scope="col">Anno</th>
      	<th scope="col">Mese</th>
      	<th scope="col" >Data</th>
      	<th scope="col" >Cliente</th>
      	<th scope="col">Mezzo</th>
	  	<th scope="col">Stato</th>
	  	<th scope="col">Importo Totale</th>
	 	<th scope="col">PDF</th>
    </tr>
	<#assign i=0 />
		<#list listaDocumenti as documento >
	    	<#assign i=i+1 />
    <tr onclick="javascript:document.location='/edit/documento_testata/update/${documento.getId_documento_testata()}'">
      	<td ><input type="text" nome="numero${i}" id="numero${i}" class="numero" value="${documento.getNum_documento()}" onclick="mostra(1)"  readonly></td>
      	<td><input type="text" nome="anno${i}" id="anno${i}" class="anno" value="${documento.getAnno_documento()}" onclick="mostra(1)" readonly></td>
      	<td><input type="text" nome="mese${i}" id="mese${i}" class="mese"  value="${documento.getMese_documento()}" onclick="mostra(1)"  readonly></td>
      	<td><input type="text" nome="data${i}" id="data${i}" class="data" value="${documento.getData_documento()?string["dd-MM-yyyy"]}" onclick="mostra(1)" readonly></td>
      	<td><input type="text" nome="cliente${i}" id="cliente${i}" class="cliente" value="${documento.getCliente().getRagione_sociale()}" onclick="mostra(1)" readonly></td>
      	<td><input type="text" nome="mezzo${i}" id="mezzo${i}" class="mezzo" value="${documento.getMezzo().getDescrizione()}" onclick="mostra(1)" readonly></td>
   		<td><input type="text" nome="stato${i}" id="stato${i}" class="stato" value="${documento.descrizioneStato()}" onclick="mostra(1)"  readonly></td>
    	<td><input type="text" nome="totale${i}" id="totale${i}" class="totale" value="${documento.getTotale()}" onclick="mostra(1)" readonly></td>
    	<td>
  			<#if documento.isChiuso()>
			<p class="pdf"><img name="pdf" src="/img/edit/pdf.png" alt="Modifica"  style="vertical-align:initial;clear:none;border:0"/></pdf>
			</#if>
		</td>
    </tr>
		</#list>
  </tbody>
</table>

<!-- POPUP -->
<div id="popup1" class="overlay">
	<div class="popup">
	<div class="scroll">
	
	
	
		<h3>Nuovo Documento</h3>
		<a class="close" href="#" id="finisci">&times;</a>
		<div class="content">
					
 <form id="TdocumentoForm" name="TdocumentoForm" action="/edit/documento_testata/save2" method="post" >
                   <table>
                        <tbody>
                        <tr class="prop">
                                <td align="top" class="num"><label for="num">Numero Documento</label></td>                             
                                <td align="top" class="value">
                                    <input type="text" id="num" name="num_documento"  size="25" maxlength="11" value="" placeholder="Numero documento"  /><b> - </b><input type="text" id="anno_documento" name="anno_documento"  size="4" maxlength="60" value="2016" readonly/>                                            	 
                                </td> 
                               
                            </tr>
                             <tr class="prop">
                                <td align="top" class="num"><label for="num">Data Documento</label></td>                             
                                <td align="top" class="value">                                   
                                	 <input type="text" id="data_documento" name="data_documento"  size="25" maxlength="60" value=""  />
                                </td>  
                            </tr> 
                             <tr class="prop">         
                                <td align="top" class="num"><label for="anno_documento">Mese di Riferimento</label></td>                             
                                <td align="top" class="value"> 
                                <select id="mese_documento" name="mese_documento" required>
	                                    <option value="">Seleziona..</option>
	                                        <option value="Gennaio">Gennaio</option>
	                                        <option value="Febbraio">Febbraio</option>
	                                        <option value="Marzo">Marzo</option>
	                                        <option value="Aprile">Aprile</option>
	                                        <option value="Maggio">Maggio</option>
	                                        <option value="Giugno">Giugno</option>
	                                        <option value="Luglio">Luglio</option>
	                                        <option value="Agosto">Agosto</option>
	                                        <option value="Settembre">Settembre</option>
	                                        <option value="Ottobre">Ottobre</option>
	                                        <option value="Novembre">Novembre</option>
	                                        <option value="Dicembre">Dicembre</option>
	                                        
	                                </select>
	                              </td>
                            </tr>       
                          <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_mezzo">Mezzo</label></td>                             
                                <td align="top" class="value">
	                                <select id="fk_id_mezzo" name="fk_id_mezzo" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list listaMezzo as mezzo>
	                                        <option value="${mezzo.getId_mezzo()}">${mezzo.getTarga()} - ${mezzo.getDescrizione()}</option>
	                                    </#list>                   
	                                </select>
	                            </td>
	                        </tr>
	                        
	                        <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_cliente">Cliente</label></td>                             
                                <td align="top" class="value">
									<select id="fk_id_cliente" name="fk_id_cliente" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list listaClienti as cliente>
	                                        <option value="${cliente.getId_cliente()}">${cliente.getRagione_sociale()}</option>
	                                    </#list>
	                                </select>
	                            </td>
                            </tr>             
                             <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_banca"> Banca di Appoggio</label></td>                             
                                <td align="top" class="value">
	                                <select id="fk_id_banca" name="fk_id_banca" required>
	                                    <option value="">Seleziona..</option>
	                                    <#list listaBanca as banca>
	                                        <option value="${banca.getId_banca()}">${banca.getNome()}</option>
	                                    </#list>
	                                </select>
                 				</td>
                            </tr>             
                       
                        </tbody>                            
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><button name="create" class="save btn btn-default btn-vlarge" value="Create" id="create"/>Inserisci</button></span>
                </div>
            </form>

		</div>
		
      </article>
    </div> <!-- /container -->
    

  </body>
</html>

<script src='http://code.jquery.com/jquery-1.9.1.min.js'></script>
<script type="text/javascript">

var indice = 0;

function mostra(ind) {
	indice = ind;
	alert(indice);
	document.location = '#miao1';
}

//INIZIO AJAX 
$(document).ready(function(e){ 
	$("#create").click(function(){
  	TdocumentoForm.submit();
		});
});

$(document).ready(function(e){ 
	$("#createggg").click(function(){
	

    var url = "/edit/documento_testata/save2"; // the script where you handle the form input.
    $.ajax({
           type: "POST",
           url: url,
           data: $("#TdocumentoForm").serialize(), // serializes the form's elements.
           success: function(response){
        	   
		        	   alert(response);//Dalla chiave messaggio di Paziente Controller
		        	   var res = response.split(",");//funzione che prende una stringa e divide in base al parametro e li numera per posizione
		        	   
		        	   if(res[0]=='KO')
		        		   	alert("Inserimento fallito!");
		        	   else
		        		   {
		        		  /*  alert(indice); *///sempre 1
		        		   alert(res[1]); 
		       	   		   alert(res[2]); 
		       	   		  /* var yy = "#fk_numero"+indice;//  equivale a fk_paziente1 */
		       	   		   var xx = "#trasportato"+indice;
		            	  // $(yy).val(res[2]);
		            	   $(xx).val(res[1]);
		            	   document.location = '/edit/documento_testata/list#';
		            	   location.reload();
		            	   //resetta tutto
		            	   document.TdocumentoForm.reset(); 
           	   
		       	   		}
        	   		}
         		});
    	//e.preventDefault(); // avoid to execute the actual submit of the form.
		});
});

</script>
