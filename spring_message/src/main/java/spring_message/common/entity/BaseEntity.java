package spring_message.common.entity;

import java.io.Serializable;

public class BaseEntity  implements Serializable{

	/**
	 * /**
	 * @Description: 
	 * @date: 2020年10月4日 下午3:04:59
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer pageIndex;
	
	private Integer pageSize;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
