<?xml version="1.0" encoding="ISO-8859-1"?>
<answer>
	<esito>${esito}</esito>
	<messaggio>
		<selectedKey>${selectedKey?trim}</selectedKey>
		<#list map?keys as key>
			<option>
				<value>${key?trim}</value>
				<desc>${map[key]?xml}</desc>
			</option>   
		</#list>
	</messaggio>
</answer>
