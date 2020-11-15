package spring_message.common.dao.servcie;

import java.util.List;

public interface BaseService {
	/**
	 * @Description: 添加值
	 * @date: 2020年10月4日 下午2:43:26
	 * @param stu
	 */
	public <T> void insert(T stu);

	/**
	 * @Description: 更新值
	 * @date: 2020年10月4日 下午2:43:37
	 * @param stu
	 */
	public <T> void update(T stu);

	/**
	 * @Description:条件查询
	 * @date: 2020年10月4日 下午2:44:00
	 * @return
	 */
	public <T> List<T> queryCondition(T stu);
	
	/**
	 * @Description: 条件查询总数
	 * @date: 2020年10月4日 下午2:45:04
	 * @param stu
	 * @return
	 */
	public <T> long queryConditionCount(T stu);
	
	/**
	 * @Description:  删除
	 * @date: 2020年10月4日 下午2:45:57
	 * @param id
	 */
	public void deleteById(String id);
	
}
