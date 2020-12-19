<#list tableVos as tableVo> 
DROP TABLE IF EXISTS `${tableVo.tableEntity.tableName}`;

CREATE TABLE `${tableVo.tableEntity.tableName}` (
<#list tableVo.columnEntitys as columnEntity>
	 `${columnEntity.columnName}` ${columnEntity.dataType}<#if columnEntity.dataType != "date">(${columnEntity.maxLength}) </#if><#if columnEntity.nullAble == "YES"> DEFAULT NULL <#else> NOT NULL DEFAULT '' </#if> <#if columnEntity.priKey == "PRI"> PRIMARY KEY</#if> COMMENT '${columnEntity.columnComment}'<#if columnEntity_has_next>,</#if>
</#list> 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='${tableVo.tableEntity.tableComment}';

</#list> 
