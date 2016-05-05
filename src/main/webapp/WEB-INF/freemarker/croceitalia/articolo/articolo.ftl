<li>
	<section class="block">
		<#if oggetto.image??  >
			<#assign image_id=oggetto.image.id />
			<a href="/site/page/articolo/${oggetto.uid}"><img src="/site/documento/get/${image_id}" alt="${oggetto.testo.title}"  /></a>
		<#else>
			<#assign image_id="NOIMAGE" />
		</#if>
		
		<h2><a href="/site/page/articolo/${oggetto.uid}">${oggetto.testo.title}</a></h2>
		${oggetto.testo.getPreviewTesto()} ... 
		 </span></p>
		 <a href="/site/page/articolo/${oggetto.uid}">Continua a leggere</a>
	</section>
</li>

