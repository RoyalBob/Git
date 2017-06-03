package com.mrlimrli.utils;

import java.io.File;

public class FileTools {
	/**
	 * 得到文件的扩展名
	 * @param file
	 * @return
	 */
	public static String getFileExt(File file) {
        return getFileNameExt(file.getName());
    }
	/**
	 * 根据文件名截取扩展名
	 * @param fileName
	 * @return
	 */
	public static String getFileNameExt(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
