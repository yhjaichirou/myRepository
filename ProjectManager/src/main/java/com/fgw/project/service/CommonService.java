package com.fgw.project.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fgw.project.util.RetKit;
import com.fgw.project.util.UploadFile;

/**
 * 公共服务类
 * @author yhj
 * @date 2021年1月8日
 */
@Service
public class CommonService {
	private Log log = LogFactory.getLog(CommonService.class);
	
	@Value("${upload.path}")
	private String uploadPath;
	@Value("${getFileUrl}")
	private String getFileUrl;
	

	//上传文件
	public RetKit uploadFile(MultipartFile file) {
		String fileName = "";
		try {
			uploadPath = "D:\\textUpload\\";
			fileName = UploadFile.upload(uploadPath, file);
			return RetKit.okData(getFileUrl + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("文件上传失败！" + e.getMessage());
			return RetKit.fail("文件上传失败！" + e.getMessage());
		}
	}
}
