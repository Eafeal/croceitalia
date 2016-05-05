<!-- B.3 SUBCONTENT -->
<div class="main-subcontent">
    <#assign colBanner=oggetto />
    <p><#include "/framework/links.ftl"  /></p>
    	
    <div class="subcontent-unit-border-blue">
        <div class="round-border-topleft"></div>
        <div class="round-border-topright"></div>
        <h1 class="blue">MAIN SPONSOR</h1>
        
        <#list colBanner.getChildren() as banner >
            <#assign oggetto=banner />
           
            <p>
                <a target="_blank" href="${banner.url}">    
                    <#list banner.getChildren() as image >
                        <img class="center" alt="${banner.uid}" src="${image.url}" />
                    </#list>
                </a>
            </p>
        </#list>
    </div>
</div>


