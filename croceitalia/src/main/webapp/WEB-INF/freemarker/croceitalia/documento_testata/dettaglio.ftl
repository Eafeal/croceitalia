<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>Croce Bianca Italia</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/documento_riga.css" rel="stylesheet">
    
 <!--JQUERY TAB--> 
    <link href="/css/jquery-ui1.css" rel="stylesheet">
 <!--JQUERY TAB--> 
     
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
                                   <link rel="shortcut icon" href="/img/icons/favicon_croce.png">
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
          <a href="/edit/home" >
			<img class="brand" src="/img/edit/logo_croce1.png" style="width: 53px;" >
		  </a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li><a href="/edit/home"><b>Home</b></a></li>
              <li><a href="/edit/documento_testata/list"><b>Lista Documenti</b></a></li>
			
			<#if !documento.isChiuso()>
              <li class="active"><a href="#gestioneRiga" onclick="resetRiga()" style="cursor:cell;"><b>Nuova Riga</b></a></li>
            </#if>
                         
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
     
<div style="margin-top: 4%;">
<fieldset>
	<legend >Documento Testata
	 
	  				<#if !documento.isChiuso()>
	                <form name="chiusuraForm" action="/edit/documento_testata/chiudi/${documento.getId_documento_testata()}" method="get" class="formHead"  >
	                	<a title="Chiudi il documento" style="margin-top: -38px;margin-right: 150px; float:right;" id="chiusura" >
	                		<img src="/img/edit/chiudi_pdf.png" style="clear:none;border:0;width: 35px;float:right;cursor:pointer;" onclick="location.reload();">
	                	</a>               
	                </form>
	                </#if>
                
				<#if documento.isChiuso() >
					<#assign urlv= "/edit/documento_testata/pdfprint/${documento.getId_documento_testata()}" />
						<a title="Visualizza il documento di rimborso" onClick="javascript:f_win_log('${urlv}')" >
							<img src="/img/edit/pdf_riga.png" style="clear:none;border:0;width: 35px;float:right;cursor:pointer;" onclick="location.reload();">
						</a>
						
					<#if documento.isChiuso() && documento.isPdfGenerato()>
						<form name="inviaPDForm" action="/edit/documento_testata/pdfsend/${documento.getId_documento_testata()}" method="get" class="formHead" >  
							<a title="Invia la fattura pdf" id="inviaPDF"  >
								<img src="/img/edit/invia_pdf.png" style="margin-top: -36px;margin-right: 50px;float: right;width: 35px;cursor:pointer;"  onclick="location.reload();">
							</a>
	                 	</form>
					</#if>

				</#if>
		</legend>
		
<!---AGGIORNAMENTO DOCUMENTO-->			
 <form id="updateForm" name="updateForm" action="/edit/documento_testata/doUpdate" method="post">
 
 				<#if !documento.isChiuso()>
					<a title="Salva le modifiche del documento" style="text-decoration: none; float:right;" id="update" class="action-button shadow animate blue" >Salva</a>
				</#if>
				
                   <table class="aggiornaDocumento">
                    <input type="hidden" name="id_documento_testata" id="id_documento_testata" value="${documento.getId_documento_testata()}">
                        <tbody>
                        	<tr class="prop">
                                <td align="top" class="num"><label for="num"><h5>Numero Documento</h5></label></td>                             
                                <td align="top" class="value" style="float:right;">
                                    <input type="text" id="numAg" name="num_documento"  size="2" maxlength="11" value="${documento.getNum_documento()}" style="border:0px; text-align:right;" readonly/><b> - </b>
                                    <input type="text" id="anno_Ag" name="anno_documento"  size="4" maxlength="60" value="2016" style="border:0px; text-align:center;" readonly/> 
                                    del
                                     <input type="text" id="data_Ag" name="data_documento" value="${documento.getData_documento()?string["dd-MM-yyyy"]}" style="border:0px; background-color:#F2F2F2; text-align:right;">                                           	 
                                </td> 

                                <td align="top" class="num"><label for="num"><h5>Codice CIG</h5></label></td>                             
                                <td align="top" class="value" style="float:right;">
                                    <input type="text" size="25" maxlength="10"  id="CIG" name="CIG" value="${documento.getCIG()}" style="border:0px; text-align:center;">                                           	 
                                </td> 
                                      
                                <td align="top" class="num"><label for="anno_documento"><h5>Mese di Riferimento</h5></label></td>                             
                                <td align="top" class="value" style="float:right;"> 
                                <select class="grigio" id="mese" name="mese_documento" required>                      
								        	<#include "selectMese.ftl"  />
	                           </select>
	                              </td>
                            </tr>
                                   
                          <tr id="secondaRigaUpdate">
                                <td align="top" class="fk_id_mezzo"><label for="fk_id_mezzo"><h5>Mezzo</h5></label></td>                             
                                <td align="top" class="value" style="float:right;">
	                                <select class="grigio" id="mezzoAg" name="fk_id_mezzo" required>
	                                    <#list listaMezzo as mezzo>
	                                        <option value="${mezzo.getId_mezzo()}" <#if documento.getFk_id_mezzo() == mezzo.getId_mezzo()> selected </#if> >${mezzo.getTarga()} - ${mezzo.getDescrizione()}</option>
	                                    </#list>                   
	                                </select>
	                            </td>
	                     
                                <td align="top" class="fk_id_cliente"><label for="fk_id_cliente"><h5>Cliente</h5></label></td>                             
                                <td align="top" class="value" style="float:right;">
									<select class="grigio" id="clienteAg" name="fk_id_cliente" required>
	                                    <#list listaClienti as cliente>
	                                        <option value="${cliente.getId_cliente()}" <#if documento.getFk_id_cliente() == cliente.getId_cliente()> selected </#if>>${cliente.getRagione_sociale()}</option>
	                                    </#list>
	                                </select>
	                            </td>
                         
                                <td align="top" class="fk_id_banca"><label for="fk_id_banca"><h5>Banca di Appoggio</h5></label></td>                             
                                <td align="top" class="value" style="float:right;">
	                                <select class="grigio" id="bancaAg" name="fk_id_banca" required>
	                                    <#list listaBanca as banca>
	                                        <option value="${banca.getId_banca()}" <#if documento.getFk_id_banca() == banca.getId_banca()> selected </#if> >${banca.getNome()}</option>
	                                    </#list>
	                                </select>
                 				</td>
                            </tr>    
                        </tbody>                            
                    </table>
                    <hr>
			</form>

                            
		          
            
</fieldset>
</div>


<div id="wait">
  <div id="loading">
    Attendere prego...
  </div>
</div>


<!-- TABELLA RIGHE -->			


			 <div class="dialog">  
   	  			<article class="tabellaDettaglio">
      			
			    <!--#if documento.isChiuso() &&  documento.isPdfGenerato() -->			
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
					      

					      	<th scope="col">Elimina</th>
					    
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
						    <td>
						  	<#if documento.getStato() == "A">
						    <!--form name="eliminaRigaForm" id="cestino" action="/edit/documento_righe/delete/${righe.getId_documento_righe()}" method="post"-->
						    	<input type="image" src="/img/edit/cestino.png" name="elimina${i}" id="eliminaRiga" class="elimina" onclick="cancellaRiga(${righe.getId_documento_righe()})" title="Elimina la riga">
						    
						    <!--/form-->
						    </#if>
						    
						    </td>
					    </tr>			    
   						</#list>
	   						<table>
		    					<tr class="prop">
		    					<hr>
			                                         
							      			<h4 style="color: #5BC0DE;margin-top: 13px; float: right;">IMPORTO TOTALE<input type="text" name="totale" id="importoAg" value="${documento.getTotale()}" style="float: right;text-align: right;margin: 11px;margin-top: 0px;width: 122px;border: 0px;" readonly></h4>
			                            
			                   
  					</tbody>
				</table>
			
			<!---RICHIAMI QUESTO FORM dopo aver cliccato il cestino e ad aver acconsentito--->
			    <form name="eliminaRigaForm" action="/edit/documento_righe/delete" method="post">
			    	<input type="hidden" name="id_riga" value="">
			    </form>				

<!--- CREAZIONE RIGA --> 

<div id="gestioneRiga" class="overlay">
	<div class="popup">
		<h2 style="color:#5bc0de;">Nuova Riga</h2>
		<a class="close" href="#" id="finisci" onclick="resetRiga()">&times;</a>
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
							      			<#include "selectMese.ftl"  />
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
	                               	<input type="text" name="diritto_uscita" id="diritto_uscita" class="dirittto_uscitaCrea" value="0"  readonly>		  
	                            </td>
	                       </tr>
	                       
	                       <tr class="prop">
	                            <td align="top" class="costokm"><label for="costokm">Costo Kilometrico</label></td>
	                            <td valign="top" class="value">
					      			<input type="text" name="costokm" id="costokm" class="costokmCrea" value="${documento.getMezzo().getCosto_km()}" onchange="totaleImporto()" readonly>
	                            </td>                          
	                            <td align="top" class="franchigia"><label for="franchigiakm">Franchigia Kilometrica</label></td>
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
                	<span class="button"><input type="reset" name="create" class="save btn btn-default btn-vlarge" value="Reset" id="create"/></span>
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


