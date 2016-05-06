<#assign tipoUtente = utente.getSoggettoUtente().getId()>			
<#if tipoUtente == "730ODCRC">
   <#include "inviaXmlParametriFormCommercialista.ftl">
   <#include "richiestaRicevutaFormCommercialista.ftl">
</#if>
<#if tipoUtente == "730UserMP">
   <#include "inviaXmlParametriFormMedico.ftl">
   <#include "richiestaRicevutaFormMedico.ftl">
</#if>
<#if tipoUtente == "730UserSSA">
   <#include "inviaXmlParametriFormStruttura.ftl">
   <#include "richiestaRicevutaFormStruttura.ftl">
</#if>