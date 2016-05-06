 <!-- B.1 MAIN NAVIGATION -->
<div class="main-navigation">

	<#assign menuVertical=oggetto />
	<p><#include "/framework/links.ftl"  /></p>
		 
	<#list menuVertical.getChildren() as menuItem >
		<!-- Navigation Level 3 -->
		<h1 class="first">${menuItem.title}</h1>
		
		<#list menuItem.getChildren() as subMenuItem >
		    <!-- Navigation with grid style -->
		    <dl class="nav3-grid">
		        <dt>
		        	<a class="field"  href="${subMenuItem.url}" >${subMenuItem.title}</a>
		        </dt>
		    </dl>
		</#list>
	</#list>
	
	<#include "/${dominio}/login.ftl"  />
</div>