package com.li.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ReadFileUtils {
    public static String readTxtFile(String filePath) {
        try {
            String encoding = "UTF-8";
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

    public static String readTxtFile(String filePath, String encoding) {
        try {
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
}
