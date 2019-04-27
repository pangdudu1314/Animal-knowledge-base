package com.li.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class WriteToFileUtils {

	private static Logger logger = Logger.getLogger(WriteToFileUtils.class);

	public static void wirteToFile(String filePath, String filePathAndFileName, String content) {
		try {
			logger.info("filePath=" + filePath);
			logger.info("保存文件地址=" + filePathAndFileName);
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			RandomAccessFile randomFile = new RandomAccessFile(filePathAndFileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write(content.getBytes("UTF-8"));
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readTxtFile(String filePath) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			StringBuffer string = new StringBuffer();
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				Set<String> set = new HashSet<String>();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					string.append(lineTxt).append("\r\n");
				}
				read.close();
			} else {
			}
			return string.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String readTxtFile(String filePath, String encoding) {
		try {
			File file = new File(filePath);
			StringBuffer string = new StringBuffer();
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				Set<String> set = new HashSet<String>();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					string.append(lineTxt).append("\r\n");
				}
				read.close();
			} else {
			}
			return string.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
