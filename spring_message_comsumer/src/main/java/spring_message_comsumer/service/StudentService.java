package spring_message_comsumer.service;

import spring_message.common.dao.servcie.BaseService;

public interface StudentService extends BaseService{
	 public void handleMessage(String name);
}
