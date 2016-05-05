<h1>${pagina.title}</h1>
<#list pagina.getChildren() as child>
    <#include "/${dominio}/${child.getTemplate()}" />
</#list>
