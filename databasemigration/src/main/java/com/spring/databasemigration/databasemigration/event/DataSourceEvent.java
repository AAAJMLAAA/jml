package com.spring.databasemigration.databasemigration.event;

import org.springframework.context.ApplicationEvent;

public class DataSourceEvent extends ApplicationEvent{

	/**
	 * /**
	 * @Description: 
	 * @date: 2020年12月19日 下午8:37:04
	 */
	private static final long serialVersionUID = 1L;

	public DataSourceEvent(Object source) {
		super(source);
		System.out.println("信息： "+source);
	}

}
