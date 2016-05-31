<!DOCTYPE html>
<html lang="en"><head><meta charset="utf-8">
    <meta charset="utf-8">
    <title>Croce Bianca Italia</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/documento_riga.css" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/documento_riga.js"></script>

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
  <style type="text/css"></style><!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.--><script>var __adobewebfontsappname__="dreamweaver"</script><script src="http://use.edgefonts.net/arizonia:n4:default.js" type="text/javascript"></script>
</head>

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
              <li><a href="/edit/documento_testata/list">Documento</a></li>
              <li><a href="#gestioneRiga">Nuova Riga</a></li>              
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
     
<div>
<fieldset>
	<legend style="color:#5BC0DE;">Documento Testata</legend>
 <form id="updateForm" name="updateForm" action="/edit/documento_testata/doUpdate" method="post">
                   <table>
                    <input type="hidden" name="id_documento_testata" id="id_documento_testata" value="${documento.getId_documento_testata()}">
                        <tbody>
                        <tr class="prop">
                                <td align="top" class="num"><label for="num"><h4>Numero Documento</h4></label></td>                             
                                <td align="top" class="value" style="float:right;">
                                    <input type="text" id="num" name="num_documento"  size="3" maxlength="11" value="${documento.getNum_documento()}" style="border:0px; text-align:right;" readonly/><b> - </b><input type="text" id="anno_documento" name="anno_documento"  size="4" maxlength="60" value="2016" style="border:0px; text-align:center;" readonly/> del <input type="text" id="data_documento" name="data_documento" value="${documento.getData_documento()?string["dd-MM-yyyy"]}" style="border:0px; background-color:#F2F2F2; text-align:right;">                                           	 
                                </td> 
                               
                            </tr>
         
                             <tr class="prop">         
                                <td align="top" class="num"><label for="anno_documento"><h4>Mese di Riferimento</h4></label></td>                             
                                <td align="top" class="value" style="float:right;"> 
                                <select class="grigio" id="mese" name="mese_documento" required>                      
								        	<option value = "" >${documento.getMese_documento()}</option>
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
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_mezzo"><h4>Mezzo</h4></label></td>                             
                                <td align="top" class="value" style="float:right;">
	                                <select class="grigio" id="mezzo" name="fk_id_mezzo" required>
	                                    <#list listaMezzo as mezzo>
	                                        <option value="${mezzo.getId_mezzo()}" <#if documento.getFk_id_mezzo() == mezzo.getId_mezzo()> selected </#if> >${mezzo.getTarga()} - ${mezzo.getDescrizione()}</option>
	                                    </#list>                   
	                                </select>
	                            </td>
	                        </tr>
	                        
	                        <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_cliente"><h4>Cliente</h4></label></td>                             
                                <td align="top" class="value" style="float:right;">
									<select class="grigio" id="cliente" name="fk_id_cliente" required>
	                                    <#list listaClienti as cliente>
	                                        <option value="${cliente.getId_cliente()}" <#if documento.getFk_id_cliente() == cliente.getId_cliente()> selected </#if>>${cliente.getRagione_sociale()}</option>
	                                    </#list>
	                                </select>
	                            </td>
                            </tr>             
                             <tr class="prop">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_banca"><h4>Banca di Appoggio</h4></label></td>                             
                                <td align="top" class="value" style="float:right;">
	                                <select class="grigio" id="banca" name="fk_id_banca" required>
	                                    <#list listaBanca as banca>
	                                        <option value="${banca.getId_banca()}" <#if documento.getFk_id_banca() == banca.getId_banca()> selected </#if> >${banca.getNome()}</option>
	                                    </#list>
	                                </select>
                 				</td>
                            </tr>             
                       
                        </tbody>                            
                    </table>
                    <br/>
                <#if !documento.isChiuso()>
				<a style="text-decoration: none; float:right;" href="#" id="update" class="action-button shadow animate blue" onclick="return confirm('Sei sicuro?');">Aggiorna</a>
				</#if>
			</form>

                <div class="buttons">               
	               
	                
                 	<#if documento.isChiuso()>
                 	<a style="text-decoration: none;margin-top:10px; float:right;" href="#" id="chiudi" class="action-button shadow animate blue">Chiudi</a>
	                </#if>
	                
	                
	            <#if !documento.isChiuso()>
                <form name="chiusuraForm" action="/edit/documento_testata/chiudi/${documento.getId_documento_testata()}" method="get" >
                		<a style="text-decoration: none; margin-top:10px;" href="#" id="chiusura" class="action-button shadow animate blue">Chiusura</a>               
                </form>
                </#if>
					<#if documento.isChiuso()>
						<form name="stampaForm" action="/edit/documento_testata/pdfprint/${documento.getId_documento_testata()}" method="get" >
							<a style="text-decoration: none; margin-top:10px;" href="#" id="stampaPDF" class="action-button shadow animate blue">Stampa PDF</a> 
						<!--<span class="button"><input type="submit" value="Stampa PDF" class="delete" onclick="return confirm('Sei sicuro?');"/></span> -->
						</form>
					</#if>
                </div>
            
</fieldset>
</div>
			
			 <div class="dialog">  
   	  			<article class="tabellaDettaglio">
      			
      			
      			<table width="100%" border="0">
  					<tbody>
					    <tr>
					      <th scope="col">Num sedute</th>
					      <th scope="col">Mese sedute</th>
					      <th scope="col">Km Totali</th>
					      <th scope="col">Paziente</th>
					      <th scope="col">Destinazione</th>
					      <th scope="col">Percorso</th>
					      <th scope="col">N° ore sosta</th>
					      <th scope="col">Quota fissa</th>
					      <th scope="col">Diritto di uscita</th>
					      <th scope="col">IMPORTO</th>
					    </tr>
					    <#assign i=0 />
							<#list righe as righe>
						    	<#assign i=i+1 />
    					<tr onclick="javascript://document.location='/edit/documento_testata/update/${righe.getId_documento_righe()}'">					     
					     	<td><input value="${righe.getNum_sedute()}" type="text" name="num_sedute${i}" id="num_sedute${i}" class="num_sedute" size="5" maxlength="2" readonly></td>
					      	<td><input value="${righe.getMese()}" type="text" name="mese${i}" id="mese_sedute${i}" class="mese_sedute"  readonly></td>
					      	<td><input value="${righe.getKm_totali()}" type="text" name="km_totali${i}" id="km_percorsi${i}" class="km_percorsi" value="" readonly></td>
					      	<td><input value="${righe.getPaziente().getNominativo()}" type="text" name="_id_paziente${i}" id="trasportato${i}" class="trasportato" readonly></td>
					      	<td><input value="${righe.getStruttura().getNome()}" type="text" name="_id_struttura${i}" id="trasportato${i}" class="trasportato" readonly></td>
					      	<td><input value="${righe.getPercorsoAndata()} - A/R " type="text" name="_id_struttura${i}" id="percorso${i}" class="percorso"  readonly ></td>							
						 	<td><input value="${righe.getOra_sosta()}" type="text" name="ora_sosta${i}" id="num_ore${i}" class="num_ore"  readonly></td>
							<td><input value="${righe.getQuota_fissa()}" type="text" name="quota_fissa${i}" id="quota_fissa1" class="quota_fissa" readonly ></td>
						    <td><input value="${righe.getDiritto_uscita()}" type="text" name="diritto_uscita${i}" id="dirittto_uscita${i}" class="dirittto_uscita" readonly></td>
						    <td><input value="${righe.getImporto()}" type="text" name="importo${i}" id="importo${i}"  class="importo" readonly></td>
					    </tr>
					    
   						</#list>
    
  					</tbody>
				</table>
            
<!--- CREAZIONE RIGA --> 

<div id="gestioneRiga" class="overlay">
	<div class="popup">
		<h2 style="color:#5bc0de;">Nuova Riga</h2>
		<a class="close" href="#" id="finisci">&times;</a>
		<div class="content">
            <form id="rigaForm" name="rigaForm" action="/edit/documento_righe/save" method="post" >
                   <table>
                        <tbody>
<!-- Verticale --->  					
     					 <input type="hidden" name="fk_id_documento_testata" id="fk_id_documento_testata" value="${documento.getId_documento_testata()}">
					 		<tr class="prop">
	                            <td align="top" class="fk_id_paziente"><label for="fk_id_paziente">Paziente</label></td>
	                            <td valign="top" class="value">
	                                <select class="pazienteCrea" id="fk_id_paziente" name="fk_id_paziente" required>					      				
					      			   <option value="">Seleziona..</option>
	                                   <#list listaPaziente as paziente>
	                                        <option value="${paziente.getId_paziente()}">${paziente.getNome()} - ${paziente.getCognome()} </option><!-- puoi richiamare il nome anche in questo modo-->
	                                    </#list>
	                                    </select>
	                            </td>
	                             <td align="top" class="fk_id_struttura"><label for="fk_id_struttura">Destinazione</label></td>
	                            <td valign="top" class="value">
	                                <select class="strutturaCrea" id="fk_id_struttura" name="fk_id_struttura" required>
					      				<option value="">Seleziona..</option>
	                                   <#list listaStruttura as struttura>
	                                        <option value="${struttura.getId_struttura()}">${struttura.getNome()}</option>
	                                    </#list>
	                        		</select>
	                            </td>
	                       </tr>
					 		
	                       <tr class="prop">
	                            <td align="top" class="percorso"><label for="percorso">Percorso</label></td>
	                            <td valign="top" class="value">
	                                <input type="text" name="percorso" id="percorso" class="percorsoCrea"  readonly>
	                            </td>
	                            <td align="top" class="kmpercorso"><label for="km_percorso">Km Percorso</label></td>
	                            <td valign="top" class="value">
	                               	<input type="text" name="km_percorso" id="km_percorso" class="km_percorsiCrea" value="" onchange="javascript:totaleImporto();">
	                            </td>
	                       </tr>
	                       <tr class="prop">
	                            <td align="top" class="sedute"><label for="num_sedute">Num sedute</label></td>
	                            <td valign="top" class="value">
	                                <input type="text" name="num_sedute" id="num_sedute" class="num_seduteCrea"  onchange="totaleImporto()" value="">
							     </td>
							    <td align="top" class="mese"><label for="mese">Mese Sedute</label></td>
	                            <td valign="top" class="value">
							      	<select id="mese1" name="mese" class="mese_seduteCrea" required>
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
	                            <td align="top" class="ora_sosta"><label for="ora_sosta">N° ore sosta</label></td>
	                            <td align="top" class="value">
	                               	<input type="text" name="ora_sosta" id="ora_sosta" class="num_oreCrea"  value="0">
	                            </td>
	                       </tr>
	                        <tr class="prop">
	                            <td align="top" class="totali"><label for="km_totali">Km Totali</label></td>
	                            <td align="top" class="value">                        
					       			<input type="text" name="km_totali" id="km_totali" class="km_totaliCrea" value="0" readonly>
	                            </td>
	                            <td align="top" class="km_totali"><label for="km_totali"></label></td>
	                            <td align="top" class="value">                        
					       			<!--<input type="text" name="km_totali" id="km_totali" class="km_percorsi" value="0" readonly>-->
	                            </td>
	                       </tr>
	                       
	                       <tr class="prop">
	                            <td align="top" class="quota_fissa_s"><label for="quota_fissa_s">Quota fissa</label></td>
	                            <td valign="top" class="value">
	                               	<input type="text" name="quota_fissa_s" id="quota_fissa_s" class="quota_fissaCrea" value="${documento.getMezzo().getQf()}" onchange="totaleImporto()">
	                            </td>
	                            <td align="top" class="diritto_uscita"><label for="diritto_uscita">Diritto Uscita</label></td>
	                            <td valign="top" class="value">
	                               	<input type="text" name="diritto_uscita" id="diritto_uscita" class="dirittto_uscitaCrea" value="0" >		  
	                            </td>
	                       </tr>
	                       
	                       <tr class="prop">
	                            <td align="top" class="costokm"><label for="costokm">Costo Kilometrico</label></td>
	                            <td valign="top" class="value">
					      			<input type="text" name="costokm" id="costokm" class="costokmCrea" value="${documento.getMezzo().getCosto_km()}" onchange="totaleImporto()" readonly>
	                            </td>                          
	                            <td align="top" class="franchigia"><label for="franchigiakm">Franchigia</label></td>
	                            <td valign="top" class="value">
	                               	<input type="text" name="franchigiakm" id="franchigiakm" class="franchigiaCrea" value="${documento.getMezzo().getFranchigia_km()}" onchange="totaleImporto()" readonly>
	                            </td>
	                       </tr>
	                       <tr class="prop">
	                            <td align="top" class="importo_s"><label for="importo_s">IMPORTO TOTALE</label></td>
	                            <td valign="top" class="value">               
					      			<input type="text" name="importo_s" id="importo_s"  class="importoCrea" value="0" readonly>
	                            </td>
	                       </tr>
           				</tbody>                            
                    </table>
                    <br/>
                <div class="buttons">
                    <span class="button"><button name="create" class="save btn btn-default btn-vlarge" id="createRiga"/>Salva</button></span>
                </div>
                
            </form>


		</div>
	</div>
</div>
				</article>
			</div> <!-- /container -->
  </body>
</html>
	<script type="text/javascript" src="/js/asso_beans_client.js"></script>
	<script type="text/javascript" src="/js/controlli.js"></script>
	<script src='http://code.jquery.com/jquery-1.9.1.min.js'></script>
