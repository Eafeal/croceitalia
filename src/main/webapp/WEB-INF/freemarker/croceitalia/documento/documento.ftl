<div class="column1-unit">
<#assign resource = oggetto />
<#assign url= "/site/documento/get/" + resource.id />
<#assign description= resource.description         />

<#if resource.isImage() >
    <img border="0" align="center" src="${url}" alt="${description}"  />
<#else>
	<#if resource.isDocument() >
	    <#assign url= "/site/documento/get/" + resource.id />
	    <#assign description= resource.description         />
	    <a class="field" href="${url}" ><img border="0" align="center" src="/img/icons/${resource.getIco()}" alt="Scarica"  />${description}</a>
	</#if>
</#if>
</div>