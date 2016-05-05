<#if (pagina.getChildType("MenuTop"))?? >
	<!-- Navigation Level 2   -->
	<div class="nav2">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
		<#assign menuTop=pagina.getChildType("MenuTop") />
		<#assign oggetto=menuTop />
		<p><#include "/framework/links.ftl"  /></p>
	    <!-- Navigation item -->
	    <#list menuTop.getChildren() as menuItem >
	            <ul>
	                <li>
	                    <a href="${menuItem.url}" >${menuItem.title}</a>
	                    <#if menuItem.hasChildren() >
	                        <ul>
		                        <#list menuItem.getChildren() as subMenuItem >
		                            <li>
		                                <a href="${subMenuItem.url}" >${subMenuItem.title}</a> 
		                            </li>
		                        </#list>
	                        </ul>
	                    </#if>
	                </li>
	            </ul>
	    </#list>
	</div>
</#if>