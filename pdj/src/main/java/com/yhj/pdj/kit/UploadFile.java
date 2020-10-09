package com.yhj.pdj.kit;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 上传文件工具
 * @author admin
 *
 */
public class UploadFile {
	private static Log log = LogFactory.getLog(UploadFile.class);
	
	/**
	 * 上传
	 * @param path 
	 * @param mulFile
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String uploadPic(String path,MultipartFile mulFile) throws IllegalStateException, IOException {
		String nativeName = mulFile.getOriginalFilename();
		//上传后文件做处理
		int indexPoint = nativeName.lastIndexOf(".");
		String proName = nativeName.substring(0,indexPoint);
		String suffix = nativeName.substring(indexPoint+1);
		//有的手机存在 特殊字符不识别 。
		nativeName = StrKit.getRandomString(5)+"."+suffix;
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		String fileName = System.currentTimeMillis() +nativeName;
		String url = path + fileName;
		File dest = new File(url);
		mulFile.transferTo(dest);
	
		return fileName;
	}
	
	public static boolean delFile(String path){
		if(StrKit.notBlank(path)) {
			File file = new File(path);
			if(file.exists()) {
				file.delete();
				return true;
			}
			return false;
		}
		return false;
	}
}
