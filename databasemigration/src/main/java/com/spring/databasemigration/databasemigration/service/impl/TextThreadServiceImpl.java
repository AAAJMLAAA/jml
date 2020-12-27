package com.spring.databasemigration.databasemigration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.spring.databasemigration.databasemigration.config.DataSourceContext;
import com.spring.databasemigration.databasemigration.dao.UserDao;
import com.spring.databasemigration.databasemigration.pojo.User;
import com.spring.databasemigration.databasemigration.service.TextThreadService;

@Service
public class TextThreadServiceImpl implements TextThreadService {

	@Autowired
	TaskExecutor taskExecutor;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DataSourceTransactionManager transactionManager;

	/**
	 * 线程中需要开启新的事务
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void dataTranslation() throws Exception {
		// 切换数据库
		DataSourceContext.setDataSource("oracleDataSource");
		System.out.println("dataTranslation-start");
		CountDownLatch countDownLatch = new CountDownLatch(10);
		AtomicInteger ai = new AtomicInteger(10);
		/**
		 * 线程单独的事务处理
		 */
		List<TransactionStatus> transactionStatuses = new ArrayList<>();
		for (int x = 0; x < 10; x++) {
			taskExecutor.execute(() -> {
				try {
					// 由于每个线程底层都是自己的事务操作
					// 现在为每个线程创建自己的事务
					DefaultTransactionDefinition def = new DefaultTransactionDefinition();
					def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
					TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
					userDao.insert(new User(UUID.randomUUID().toString(), new Date()));
					transactionStatuses.add(status);
					countDownLatch.countDown();
					/////////////////////////////////////////
					// System.out.println(countDownLatch.getCount());
					// countDownLatch.await();
					// System.out.println("OK");
					// if (incrementAndGet != 0) { // 如果数据没有减少0则 事务全部回滚
					// transactionManager.rollback(status);
					// } else {// 全部事务提交
					// transactionManager.commit(status);
					// }
				} catch (Exception e) {
					countDownLatch.countDown();
				}
			});

		}
		countDownLatch.await();
		dataTranslation2();
		System.out.println("dataTranslation-end");
	}

	@Override
	public void dataTranslation2() throws Exception {

		taskExecutor.execute(() -> {
			System.out.println("OK32");
			userDao.insert(new User("23", new Date()));
		});

	}

}
