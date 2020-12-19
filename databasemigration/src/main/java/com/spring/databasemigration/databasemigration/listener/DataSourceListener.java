package com.spring.databasemigration.databasemigration.listener;

import org.springframework.context.ApplicationListener;

import com.spring.databasemigration.databasemigration.event.DataSourceEvent;

public class DataSourceListener implements ApplicationListener<DataSourceEvent> {

	@Override
	public void onApplicationEvent(DataSourceEvent event) {
		System.out.println(event.getSource());
	}

}
