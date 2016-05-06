<script type="text/javascript" src="/js/edit_object.js"></script>
<script language="javascript">
<!-- 
	
function delRelazione(padreId,tipoPadreId,figlioId,tipoFiglioId)
	{
	if(confirm('Are you sure?'))
	   {
       	var w = 800;
		var h = 735;
		var pw = Math.floor((screen.width-w)/2);
		var ph = 0; //Math.floor((screen.height-h)/2);
		var path = "/edit/rel/delete/"+padreId+"/"+tipoPadreId+"/"+figlioId+"/"+tipoFiglioId;
		window.document.location=path;
	    }
	}

function spostaSU(padreId,tipoPadreId,figlioId,tipoFiglioId)
	{
       	var w = 800;
		var h = 735;
		var pw = Math.floor((screen.width-w)/2);
		var ph = 0; //Math.floor((screen.height-h)/2);
		var path = "/edit/rel/su/"+padreId+"/"+tipoPadreId+"/"+figlioId+"/"+tipoFiglioId;
		window.document.location=path;
	}

function spostaGIU(padreId,tipoPadreId,figlioId,tipoFiglioId)
	{
       	var w = 800;
		var h = 735;
		var pw = Math.floor((screen.width-w)/2);
		var ph = 0; //Math.floor((screen.height-h)/2);
		var path = "/edit/rel/giu/"+padreId+"/"+tipoPadreId+"/"+figlioId+"/"+tipoFiglioId;
		window.document.location=path;
	}
--> 
</script>

<h1>Relations</h1>
<table>
	<thead>
		<tr> 
		    <th class="sortable" >#</th>
			<th class="sortable" >Edit</th>
			<th class="sortable" >Del</th>
			<th class="sortable" >Tipo</th>
			<th class="sortable" >Uid</th>
			<th class="sortable" >Lang</th>
            <th class="sortable" >Sposta</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
      		<td class="sortable" >&nbsp;</td>
		</tr>
	</tfoot>
	<tbody>
		<#assign i=0 />
			<#list padre.getChildren() as child>
				<#if (i % 2) == 0 >
					<#assign classe="odd" />
				<#else>
					<#assign classe="even" />
				</#if>
				
				<#assign i=i+1 />
				<tr class="${classe}" >
				    <td>${i}</td>
					<td>
						<a href="/edit/${child.tipoOggetto.editPath}/update/${child.id}">
							<img border="0" width="15" height="15" alt="Elimina" src="/img/edit/mod.gif" />
						</a>
					</td>
					<td>
						<a href="javascript:delRelazione('${padre.id}','${padre.tipoOggetto.uid}' , '${child.id}' , '${child.tipoOggetto.uid}' );" >
							<img border="0" width="15" height="15" alt="Elimina" src="/img/edit/icon_delete.gif" />
						</a>
					</td>
					<td>${child.tipoOggetto.uid}</td>
					<td>${child.uid}</td>
					<td>${child.lang}</td>
					<td>
						<a href="javascript:spostaSU('${padre.id}','${padre.tipoOggetto.uid}' , '${child.id}' , '${child.tipoOggetto.uid}' );" >
							<img border="0" width="15" height="15" alt="Sposta Su" src="/img/edit/icon_su.gif" />
						</a>
						<a href="javascript:spostaGIU('${padre.id}','${padre.tipoOggetto.uid}' , '${child.id}' , '${child.tipoOggetto.uid}' );" >
							<img border="0" width="15" height="15" alt="Sposta giu'" src="/img/edit/icon_giu.gif" />
						</a>
					</td>
				</tr>
			</#list>
	</tbody>
</table>

<table>
	<tr>
		<td class="sortable" >
			<a href="javascript:addObject( '${padre.tipoOggetto.tipo}' , '${padre.id}' )">
				<img src="/img/edit/ins.gif" alt=""  style="float:left;clear:none;border:0" />
				&nbsp;&nbsp;Crea Nuova Relazione
			</a> 
  		</td>
	</tr>
</table>

                
