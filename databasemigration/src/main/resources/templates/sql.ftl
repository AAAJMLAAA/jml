<#list columnDatas as columnData> 
<#list columnData.fildDatas as fildData>
INSERT INTO `${columnData.tableName}`(<#list columnData.fildNames as fildName>${fildName}<#if fildName_has_next>,</#if></#list>) VALUES(<#list fildData as data>'${data}'<#if data_has_next>,</#if></#list>);
</#list>
</#list>

