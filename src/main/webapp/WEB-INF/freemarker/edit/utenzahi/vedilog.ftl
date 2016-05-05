<#include "/${dominio}/includes/doctype.ftl" />

	<link rel="stylesheet"    href="/css/main.css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
	
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<meta name="layout" content="main"/>
	
	<#if title?? ><title>${title}</title></#if>
	
	<script type="text/javascript" src="/js/bubbleSort.js"></script>
	
	<script type="text/javascript">
	
		function ricarica(){
			
			if(window.opener!=null)
				{
				window.close();
				window.opener.location.reload(true);
				}
			}
	</script>

    </head>

    <body>
	
	<#include "/${dominio}/includes/header_admin.ftl" />


        <div id="spinner" class="spinner" style="display:none;">
            <img src="/img/spinner.gif" alt="Loading..." />
        </div>
                
                <h1>Accessi al sistema</h1>

	            <div class="list">
	                <table id="resultTable" >
	                    <thead>
	                        <tr>
	                            <th>#</th>
	                            <th class="sortable" ><a href="javascript:ordina('resultTable',1,0);">User id</a></th>
	                            <th class="sortable" ><a href="javascript:ordina('resultTable',2,0);">Data / ora</a></th>
	                            <th class="sortable" ><a href="javascript:ordina('resultTable',3,0);">Cod.Operazione</a></th>
	                            <th class="sortable" ><a href="javascript:ordina('resultTable',4,0);">Esito</a></th>
	                            <th class="sortable" ><a href="javascript:ordina('resultTable',5,0);">Messaggio</a></th>
	                            <th class="sortable" ><a href="javascript:ordina('resultTable',6,0);">IP</a></th>								
	                        </tr>
	                    </thead>
	                    <tbody>
		                    <#assign i=0 />
		                    <#list Lista as log >
		                        <#assign i=i+1 />
		                        <#if (i % 2) == 0 >
		                            <#assign classe="odd" />
		                        <#else>
		                            <#assign classe="even" />
		                        </#if>
		                        <tr class="${classe}">
		                            <td>${i}</td>
		                            <td>${log.user_id}</td>									
		                            <td>${log.insertTime}</td>
		                            <td>${log.code}</td>
		                            <td>${log.result}</td>
		                            <td>${log.message}</td>
		                            <td>${log.ipFromRequest}</td>									
		                        </tr>
		                    </#list>
	                    </tbody>
	                </table>
				
				                                <br/><center><input type="button" name="Torna" value="Torna" id="Torna" onClick="history.back()"/>					
	            </div>

		</div>
    </body>
</html>