<#assign tipoUtente = utente.getSoggettoUtente().getId()>			
<#if tipoUtente == "730ODCRC">
   <#include "inviaXmlParametriFormCommercialista.ftl">
   <#include "inviaXmlFormCommercialista.ftl">
</#if>
<#if tipoUtente == "730UserMP">
   <#include "inviaXmlParametriFormMedico.ftl">
   <#include "inviaXmlFormMedico.ftl">
</#if>
<#if tipoUtente == "730UserSSA">
   <#include "inviaXmlParametriFormStruttura.ftl">
   <#include "inviaXmlFormStruttura.ftl">
</#if>