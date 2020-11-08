package jml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FileUtils {

	private static void copyFile(String srcpath, String targepath) {
		File file = new File(srcpath);
		if (!file.exists()) {
			return;
		}

		File file2 = new File(targepath);
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
				RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
				FileChannel channel = randomAccessFile.getChannel();
				FileChannel channel2 = randomAccessFile2.getChannel();) {
			// 这个是直接丢到内存中区
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
			while (channel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				channel2.write(byteBuffer);
				byteBuffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void copyFile(File file, String targepath) {
		File file2 = new File(targepath);
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
				RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
				FileChannel channel = randomAccessFile.getChannel();
				FileChannel channel2 = randomAccessFile2.getChannel();) {
			// 这个是直接丢到内存中区
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
			while (channel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				channel2.write(byteBuffer);
				byteBuffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void copyFile(File srcFile, File targeFile, long seek) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(srcFile, "r");
				RandomAccessFile randomAccessFile2 = new RandomAccessFile(targeFile, "rw");
				FileChannel channel = randomAccessFile.getChannel();
				FileChannel channel2 = randomAccessFile2.getChannel();) {
			randomAccessFile.seek(seek);
			randomAccessFile2.seek(seek);
			// 这个是直接丢到内存中区
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
			while (channel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				channel2.write(byteBuffer);
				byteBuffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
public static void main(String[] args) throws Exception {
	fileBranch("C:\\Users\\19506\\Desktop\\test.txt", "C:\\Users\\19506\\Desktop\\test2.txt");
}
	public static void fileBranch(String srcpath, String targepath) throws Exception {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);

		File file = new File(srcpath);
		File file2 = new File(targepath);
		long length = file.length();
		if (length > 1024) {
			long size = length / 1024;
			long s = length % 1024;
			// 循环的次数
			long frequency = s > 0 ? size + 1 : size;
			CountDownLatch countDownLatch = new CountDownLatch((int) frequency);
			AtomicInteger ai = new AtomicInteger(0);
			for (int i = 0; i < frequency; i++) {
				int andIncrement = ai.getAndIncrement();
				System.out.println(andIncrement * 1024+"================");
				Runnable r = () -> {
					copyFile(file, file2, andIncrement * 1024);
					countDownLatch.countDown();
				};

				newFixedThreadPool.execute(r);
			}
			countDownLatch.await();
			newFixedThreadPool.shutdown();
		} else {
			copyFile(srcpath, targepath);
		}
	}

	public static void fileBranch(InputStream in, String targepath) throws Exception {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		File file = new File(UUID.randomUUID().toString());
		try (FileOutputStream fos = new FileOutputStream(file);
				BufferedInputStream bis = new BufferedInputStream(in);
				BufferedOutputStream bos = new BufferedOutputStream(fos);) {
			int len = 0;
			byte[] b = new byte[1024 * 512];
			while ((len = bis.read(b)) != -1) {
				bos.write(b, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		File file2 = new File(targepath);
		long length = file.length();
		if (length > 1024) {
			long size = length / 1024;
			long s = length % 1024;
			// 循环的次数
			long frequency = s > 0 ? size + 1 : size;
			CountDownLatch countDownLatch = new CountDownLatch((int) frequency);
			AtomicInteger ai = new AtomicInteger(0);
			for (int i = 0; i < frequency; i++) {
				int andIncrement = ai.getAndIncrement();
				Runnable r = () -> {
					copyFile(file, file2, andIncrement * 1024);
					countDownLatch.countDown();
				};

				newFixedThreadPool.execute(r);
			}
			countDownLatch.await();
			newFixedThreadPool.shutdown();
		} else {
			copyFile(file, targepath);
		}

		file.delete();
	}
	
	
	public static void fileBranch(InputStream in, String targepath,long seek) throws Exception {
		File file = new File(UUID.randomUUID().toString());
		try (FileOutputStream fos = new FileOutputStream(file);
				BufferedInputStream bis = new BufferedInputStream(in);
				BufferedOutputStream bos = new BufferedOutputStream(fos);) {
			int len = 0;
			byte[] b = new byte[1024 * 512];
			while ((len = bis.read(b)) != -1) {
				bos.write(b, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		File file2 = new File(targepath);
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
				RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
				FileChannel channel = randomAccessFile.getChannel();
				FileChannel channel2 = randomAccessFile2.getChannel();) {
			//randomAccessFile.seek(seek);
			randomAccessFile2.seek(seek);
			// 这个是直接丢到内存中区
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
			while (channel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				channel2.write(byteBuffer);
				byteBuffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		file.delete();
	}
	
	/**
	 * 零拷贝： 网络传输持久性日志块 java Nio channel.transforTo() linux sendFile 系统调用 概念：
	 * 操作系统数据从磁盘中读入内核空间 应用程序将内核空间读入到用户空间缓存中 应用程序将数据写回到内核空间到socket缓存中
	 * 操作系统将数据从socket缓冲区复制到网卡缓冲区，以便将数据经网路发出
	 * 
	 * 真正的零拷贝： 操作系统数据从磁盘中读入内核空间 将数据的位置和长度的信息的描述符增加至内核空间（socket缓存区）
	 * 操作系统将数据从socket缓冲区复制到网卡缓冲区，以便将数据经网路发出
	 * 
	 * @Description:
	 * @date: 2020年11月7日 上午9:05:10
	 * @param args
	 * @throws Exception
	 */
}
