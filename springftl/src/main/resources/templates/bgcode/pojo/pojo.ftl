package ${packageName}.pojo;


@Table(name="${tableName}")
@Entity
public class ${tableName?cap_first} {

<#list sqlFields as field>  
	<#if field.priKey == 0>  
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
   	</#if>
   	/**${field.comment}*/
	@Column  
	private ${field.columnType} ${field.columnName};
	
</#list> 

<#list sqlFields as field>  
	public String get${field.columnName?cap_first}() 
	{
		return ${field.columnName};
	}

	public void set${field.columnName?cap_first}(${field.columnType}  ${field.columnName}) 
	{
		this.${field.columnName} = ${field.columnName};
	}
	
</#list> 
}
