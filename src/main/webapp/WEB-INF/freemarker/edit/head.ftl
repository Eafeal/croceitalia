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
