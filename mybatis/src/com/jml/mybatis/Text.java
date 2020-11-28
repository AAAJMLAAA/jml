package com.jml.mybatis;

import java.util.List;

import com.jml.mybatis.presql.BaseDao;
import com.jml.mybatis.test.pojo.Flower;

/**
 * 要实现动态的往里面添加mapperstatement对象
 * 
 * @author jinmingliang
 *
 */
public class Text {
	
	public static void main(String[] args) throws Exception {
		
		Flower flower = new Flower();
		flower.setName("4");
		BaseDao baseDao = new BaseDao();
		baseDao.init(flower);
		List<Flower> selectList = baseDao.selectList(flower);
		for (Flower flower2 : selectList)
		{
			System.out.println(flower2);
		}
	}

	/**
	 * // .resource(resource) // .fetchSize(fetchSize) // .timeout(timeout) //
	 * .statementType(statementType) // .keyGenerator(keyGenerator) //
	 * .keyProperty(keyProperty) // .keyColumn(keyColumn)
	 * .databaseId(databaseId) .lang(languageDriver)
	 * //.resultOrdered(resultOrdered) //.resultSets(resultSets) //
	 * .resultMaps(getStatementResultMaps(resultMap, resultType, id))
	 * .resultSetType(ResultSetType.SCROLL_SENSITIVE);
	 * //.flushCacheRequired(valueOrDefault(flushCache, !isSelect)) //
	 * .useCache(valueOrDefault(useCache, isSelect)) // .cache(currentCache);
	 * 
	 */
}
