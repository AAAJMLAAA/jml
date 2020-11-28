package com.jml.mybatis.test.pojo;
//package text.pojo;
//
//import org.apache.ibatis.builder.MapperBuilderAssistant;
//import org.apache.ibatis.parsing.XNode;
//
//public class BuilderMapperStatement {
//	
//	public BuilderMapperStatement()
//	{
//		
//	}
//	  private final MapperBuilderAssistant builderAssistant;
//	  private final XNode context;
//	  private final String requiredDatabaseId;
//	  
//	 public void parseStatementNode() {
//		    String id = context.getStringAttribute("id");
//		    String databaseId = context.getStringAttribute("databaseId");
//		    Integer fetchSize = context.getIntAttribute("fetchSize");
//		    Integer timeout = context.getIntAttribute("timeout");
//		    String parameterMap = context.getStringAttribute("parameterMap");
//		    String parameterType = context.getStringAttribute("parameterType");
//		    Class<?> parameterTypeClass = resolveClass(parameterType);
//		    String resultMap = context.getStringAttribute("resultMap");
//		    String resultType = context.getStringAttribute("resultType");
//		    String lang = context.getStringAttribute("lang");
//		    LanguageDriver langDriver = getLanguageDriver(lang);
//
//		    Class<?> resultTypeClass = resolveClass(resultType);
//		    String resultSetType = context.getStringAttribute("resultSetType");
//		    StatementType statementType = StatementType.valueOf(context.getStringAttribute("statementType", StatementType.PREPARED.toString()));
//		    ResultSetType resultSetTypeEnum = resolveResultSetType(resultSetType);
//
//		    String nodeName = context.getNode().getNodeName();
//		    SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
//		    boolean isSelect = sqlCommandType == SqlCommandType.SELECT;
//		    boolean flushCache = context.getBooleanAttribute("flushCache", !isSelect);
//		    boolean useCache = context.getBooleanAttribute("useCache", isSelect);
//		    boolean resultOrdered = context.getBooleanAttribute("resultOrdered", false);
//
//		    // Include Fragments before parsing
//		    XMLIncludeTransformer includeParser = new XMLIncludeTransformer(configuration, builderAssistant);
//		    includeParser.applyIncludes(context.getNode());
//
//		    // Parse selectKey after includes and remove them.
//		    processSelectKeyNodes(id, parameterTypeClass, langDriver);
//		    
//		    // Parse the SQL (pre: <selectKey> and <include> were parsed and removed)
//		    SqlSource sqlSource = langDriver.createSqlSource(configuration, context, parameterTypeClass);
//		    String resultSets = context.getStringAttribute("resultSets");
//		    String keyProperty = context.getStringAttribute("keyProperty");
//		    String keyColumn = context.getStringAttribute("keyColumn");
//		    KeyGenerator keyGenerator;
//		    String keyStatementId = id + SelectKeyGenerator.SELECT_KEY_SUFFIX;
//		    keyStatementId = builderAssistant.applyCurrentNamespace(keyStatementId, true);
//		    if (configuration.hasKeyGenerator(keyStatementId)) {
//		      keyGenerator = configuration.getKeyGenerator(keyStatementId);
//		    } else {
//		      keyGenerator = context.getBooleanAttribute("useGeneratedKeys",
//		          configuration.isUseGeneratedKeys() && SqlCommandType.INSERT.equals(sqlCommandType))
//		          ? Jdbc3KeyGenerator.INSTANCE : NoKeyGenerator.INSTANCE;
//		    }
//
//		    builderAssistant.addMappedStatement(id, sqlSource, statementType, sqlCommandType,
//		        fetchSize, timeout, parameterMap, parameterTypeClass, resultMap, resultTypeClass,
//		        resultSetTypeEnum, flushCache, useCache, resultOrdered, 
//		        keyGenerator, keyProperty, keyColumn, databaseId, langDriver, resultSets);
//		  }
//}
