package springftl.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	/**
	 * @Description:
	 * @date: 2020年10月24日 下午4:54:36
	 * @param inputFile   待压缩文件夹/文件名
	 * @param outputFile    生成的压缩包名字
	 * @throws Exception
	 */
	public static void ZipCompress(String inputFile, String outputFile) throws Exception {
		try(FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
				// 创建zip输出流
				ZipOutputStream out = new ZipOutputStream(fileOutputStream);	
				// 创建缓冲输出流
				BufferedOutputStream bos = new BufferedOutputStream(out);
				)
		{
			File input = new File(inputFile);
			compress(out, bos, input, null);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param name 压缩文件名，可以写为null保持默认
	 */
	private static void compress(ZipOutputStream out, BufferedOutputStream bos, File input, String name)
			throws IOException {
		if (name == null) {
			name = input.getName();
		}
		// 如果路径为目录（文件夹）
		if (input.isDirectory()) {
			// 取出文件夹中的文件（或子文件夹）
			File[] flist = input.listFiles();
			if (flist.length == 0)// 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
			{
				out.putNextEntry(new ZipEntry(name + "/"));
			} else// 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
			{
				for (int i = 0; i < flist.length; i++) {
					compress(out, bos, flist[i], name + "/" + flist[i].getName());
				}
			}
		} else// 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
		{
			out.putNextEntry(new ZipEntry(name));
			FileInputStream fos = new FileInputStream(input);
			BufferedInputStream bis = new BufferedInputStream(fos);
			int len = -1;
			// 将源文件写入到zip文件中
			byte[] buf = new byte[1024];
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			bis.close();
			fos.close();
			// 一定要刷一下内存
			bos.flush();
		}
	}

	/**
	 * @Description: 校验文件夹中的文件格式
	 * @date: 2020年10月24日 下午4:46:17
	 * @param srcPath
	 * @param suffixs
	 * @return
	 */
	public static boolean verifyFileName(String srcPath, List<String> suffixs) {
		{
			File file = new File(srcPath);
			if (!file.exists()) {
				return false;
			}
			try {
				InputStream fileInputStream = new FileInputStream(file);
				byte[] bendoce = new byte[3];
				fileInputStream.read(bendoce);
				fileInputStream.close();
				// 判断文件的编码
				if (bendoce[0] == -17 && bendoce[1] == -69 && bendoce[2] == -65) { // UTF-8的编码
					return verifyFileName(file, suffixs, "utf-8");
				} else {
					return verifyFileName(file, suffixs, "gbk");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static boolean verifyFileName(File file, List<String> suffixs, String encode) throws IOException {
		String regex = null;
		if (suffixs.size() > 0) {
			regex = "[\\s\\S]*.(" + String.join("|", suffixs) + ")";
		}
		FileInputStream fileInputStream = null;
		ZipInputStream zipInputStream = null;
		ZipEntry entry = null;
		try {
			fileInputStream = new FileInputStream(file);
			zipInputStream = new ZipInputStream(fileInputStream, Charset.forName(encode));
			while (((entry = zipInputStream.getNextEntry()) != null)) {
				// 如果该文件时目录
				if (entry.isDirectory()) {
					continue;
				}
				// 校验压缩包中文件后缀
				String pathName = entry.getName();
				if (regex != null) {
					boolean matches = Pattern.matches(regex, pathName);
					if (matches) {
						return false;
					}
				}
			}
			return true;
		} finally {
			if (zipInputStream != null) {
				try {
					zipInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description: 解压文件
	 * @date: 2020年10月24日 下午4:12:18
	 * @param srcPath
	 * @param dicPath
	 */
	public static void zipUncompress(String srcPath, String dicPath) {
		{
			File file = new File(srcPath);
			if (!file.exists()) {
				return;
			}
			try {
				InputStream fileInputStream = new FileInputStream(file);
				byte[] bendoce = new byte[3];
				fileInputStream.read(bendoce);
				fileInputStream.close();
				// 判断文件的编码
				if (bendoce[0] == -17 && bendoce[1] == -69 && bendoce[2] == -65) { // UTF-8的编码
					zipUncompress(file, dicPath, "utf-8");
				} else {
					zipUncompress(file, dicPath, "gbk");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description:
	 * @date: 2020年10月19日 下午8:17:51
	 * @param srcPath
	 *            文件路径
	 * @param dicPath
	 *            解压目录
	 * @throws IOException
	 */
	private static void zipUncompress(File file, String dicPath, String encode) throws IOException {

		FileInputStream fileInputStream = null;
		ZipInputStream zipInputStream = null;
		ZipEntry entry = null;
		try {
			fileInputStream = new FileInputStream(file);
			zipInputStream = new ZipInputStream(fileInputStream, Charset.forName(encode));
			File tmp = null;
			while (((entry = zipInputStream.getNextEntry()) != null)) {
				System.out.println(entry.getName());
				tmp = new File(dicPath + File.separator + entry.getName());
				// 如果该文件时目录
				if (entry.isDirectory()) {
					if (!tmp.exists()) {
						tmp.mkdirs();
					}
					continue;
				}

				try (OutputStream os = new FileOutputStream(tmp);
						BufferedOutputStream bos = new BufferedOutputStream(os);) {
					int len = 0;
					byte[] b = new byte[1024 * 512];
					while ((len = zipInputStream.read(b)) != -1) {
						bos.write(b, 0, len);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} finally {
			if (zipInputStream != null) {
				try {
					zipInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}