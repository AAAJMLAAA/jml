package springftl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springftl.dataSoure.DataSourceInfo;
import springftl.file.WriteFile;
import springftl.word.WordTemplate;
import springftl.zip.ZipUtil;

@RestController
@RequestMapping("/ftl")
public class FtlController {
	private final static String TARGET_PATH = "E:\\module\\text";
	private final static String ZIP_PATH = "E:\\module\\zip\\模板.zip";

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

	@PostMapping(value = "/loadTemplate")
	public void loadTemplate(DataSourceInfo dataSourceInfo, HttpServletResponse response) {
		FileInputStream fis = null;
		File file = null;
		try {
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("模板.zip", "UTF-8"));

			new WriteFile(dataSourceInfo);
			// 生成压缩包
			ZipUtil.ZipCompress(TARGET_PATH, ZIP_PATH);
			// 获取文件流操作
			ServletOutputStream outputStream = response.getOutputStream();
			file = new File(ZIP_PATH);
			fis = new FileInputStream(file);
			int len = 0;
			byte[] b = new byte[1024 * 512];
			while ((len = fis.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				IteratorFile(new File(TARGET_PATH));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@PostMapping(value = "/downDataSource")
	public void downDataSource(DataSourceInfo dataSourceInfo, HttpServletResponse response) throws IOException {
		try {
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode("数据库表信息.doc", "UTF-8"));
			WordTemplate.getWordByte(dataSourceInfo, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
