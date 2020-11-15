package springftl.quartz;

import java.io.File;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时清楚文件
 * @author jinmingliang
 *
 */
@Component
public class FileTimer {
	private final static String ZIP_PATH = "E:\\module\\zip";
	
	@Async
	@Scheduled(cron="59 59 23 * * ?")
	public void deleteFiles()
	{
		try {
			IteratorFile(new File(ZIP_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void IteratorFile(File file) throws Exception {
		if (file.exists()) {
			File[] listFiles = file.listFiles();
			for (File fileSon : listFiles) {
				if (fileSon.isDirectory()) {
					IteratorFile(fileSon);
					if (fileSon.exists()) {
						fileSon.delete();
					}
				} else {
					if (fileSon.exists()) {
						fileSon.delete();
					}
				}
			}
		}
	}
}
