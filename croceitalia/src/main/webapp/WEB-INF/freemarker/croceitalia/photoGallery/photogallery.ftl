<!DOCTYPE HTML>
<html>
	<head>
		
	    <link href="/css/photoGallery/base.css"  rel="stylesheet" type="text/css" media="screen" />
	
		<script type="text/javascript" src=" https://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.js"></script>
		<script type="text/javascript" src="/js/jquery/jquery.pikachoose.js"></script>
		
		<script type="text/javascript">
					$(document).ready(function() {
					$("#pikame").PikaChoose();	});
		</script>
	</head>
	<body>
		<h1 class="first">${photoGallery.title}</h1>
		<p>${photoGallery.description}</p>
		<p>${photoGallery.autore}</p>
		<div class="pikachoose">
			<ul id="pikame">
				<#list photoGallery.getChildren() as documento >
		            <li><a href=""><img src="/site/documento/get/${documento.id}" alt="${documento.description}" /></a></li>
		        </#list>
			</ul>
		</div>
	</body>
</html>





