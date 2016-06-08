<!DOCTYPE html>
<html lang="en"><head><meta charset="utf-8">
    <meta charset="utf-8">
    <title>Croce Italia Bernate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/documento_testata.css" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; 
      }
    </style>
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/documento_testata.js"></script>
    <script src="/js/asso_beans_client.js"></script>

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
      	<th scope="col" style="font-size: 12px;">Rimborso spesa</th>
      	<th scope="col">Anno</th>
      	<th scope="col">Mese</th>
      	<th scope="col" >Data</th>
      	<th scope="col" >N° CIG</th>
      	<th scope="col" >Cliente</th>
      	<th scope="col">Mezzo</th>
	  	<th scope="col">Importo Totale</th>
	  	<th scope="col">Stato</th>
	 	<th scope="col">PDF</th>
    </tr>
	<#assign i=0 />
	<#assign datadef="">
		<#list listaDocumenti as documento >
	    	<#assign i=i+1 />
	    	<#if datadef == "">
	    	<#assign datadef = documento.getData_documento()?string["dd-MM-yyyy"]>
	    	</#if>
    <tr >
      	<td onclick="javascript:document.location='/edit/documento_testata/update/${documento.getId_documento_testata()}'">
      		<input type="hidden" name="id_testata${i}" id="id_testata${i}" class="id" value="${documento.getId_documento_testata()}"  readonly>
      		<input type="text" name="numero${i}" id="numero${i}" class="numero" value="${documento.getNum_documento()}"  readonly>
      	</td>
      	<td><input type="text" name="anno${i}" id="anno${i}" class="anno" value="${documento.getAnno_documento()}" readonly></td>
      	<td><input type="text" name="mese${i}" id="mese${i}" class="mese"  value="${documento.getMese_documento()}"  readonly></td>
      	<td><input type="text" name="data${i}" id="data${i}" class="data" value="${documento.getData_documento()?string["dd-MM-yyyy"]}" readonly></td>
      	<td><input type="text" name="cig${i}" id="cig${i}" class="cig" value="${documento.getCIG()}" maxlength="10"  readonly></td>
      	<td><input type="text" name="cliente${i}" id="cliente${i}" class="cliente" value="${documento.getCliente().getRagione_sociale()}" readonly></td>
      	<td><input type="text" name="mezzo${i}" id="mezzo${i}" class="mezzo" value="${documento.getMezzo().getDescrizione()}" readonly></td>
    	<td><input type="text" name="totale${i}" id="totale${i}" class="totale" value="${documento.getTotale()}" readonly></td>
    	<td><input type="text" name="stato${i}" id="stato${i}" class="stato" value="${documento.descrizioneStato()}" readonly></td>
    	<td>
    		
			
			<#if documento.isChiuso()>
					<#assign urlv= "/edit/documento_testata/pdfprint/${documento.getId_documento_testata()}" />
						<a title="genera e visualizza il documento di rimborso" onClick="javascript:f_win_log('${urlv}')" >
							<img src="/img/edit/pdf.png" style="vertical-align:initial;clear:none;border:0">
						</a>
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
                                <td align="top" class="num"><label for="cig">Codice CIG</label></td>                             
                                <td align="top" class="numeroDocumento">
                                <input type="text" id="cig" name="CIG"  maxlength="10"  placeholder="Codice CIG" onchange="javascript:controlloCIG();"/> 
                                
                                <!--NUMERO DOCUMENTO e ANNO NASCOSTI-->
                                    <input type="hidden" id="numeroDocumento" name="num_documento"  maxlength="11" value="" placeholder="Numero documento" />
                                    <input type="hidden" id="anno_documento" name="anno_documento"  size="3" maxlength="60" value="2016" readonly/>                                            	 
                                </td> 
                            </tr>
                       
                            </tr>
                             <tr class="prop">
                                <td align="top" class="num"><label for="num">Data Documento</label></td>                             
                                <td align="top" class="value">                                   
                                	 <input type="text" id="data_documento" name="data_documento" value="${datadef}" placeholder="gg - mm - anno"  />
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
                                <td align="top" class="fk_id_cliente"><label for="fk_id_cliente">Cliente</label></td>                             
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
                                <td align="top" class="fk_id_banca"><label for="fk_id_banca"> Banca di Appoggio</label></td>                             
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
                    <span class="button"><button name="create" class="save btn btn-default btn-vlarge" value="Create" id="create" />Inserisci</button></span>
                    <span class="button"><input type="reset" name="create" class="save btn btn-default btn-vlarge" value="Reset" id="create"/></span>
                </div>
            </form>
		</div>	
      </article>
    </div> <!-- /container -->
  </body>
</html>
<script src='http://code.jquery.com/jquery-1.9.1.min.js'></script>

<script >
$(document).ready(function(e){ 
	$("#stampaPDF2").click(function(){
		alert("Sto per stampare il PDF");
		stampaForm1.submit();
	alert("Ciao SONO ancora io");
		});
});
</script>

