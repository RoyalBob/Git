package com.mrlimrli.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadTools {
	
	public static String[] uploadFile(MultipartFile file, String uploadPath) {
		String fileName = file.getOriginalFilename();
		String uid = UUID.randomUUID().toString().replaceAll("-", "");// 生成随机文件名
		String realName = uid + "." + FileTools.getFileNameExt(fileName);// 使用旧的扩展名组装新的文件名
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String datePath="/"+sdf.format(new Date())+"/";//生成文件存放路径
		File targetFile = new File(uploadPath+datePath,realName);
		File targetFilePath = new File(uploadPath+datePath);
		if (!targetFilePath.exists()) {
			targetFilePath.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
			realName = "";
		}
		return new String[]{uploadPath+datePath,realName};
	}

	public static void deleteFile(String uploadPath,String filename){
        File file = new File(uploadPath+filename);
        if (file.exists()){
          file.delete();
        }
	}
	
}
