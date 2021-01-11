package com.fgw.project.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fgw.project.model.po.Config;
import com.fgw.project.repository.IConfigRepository;
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
	@Autowired
	private IConfigRepository configR;
	

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
	
	public Config getConfig() {
		Optional<Config> config_ = configR.findById(1);
		return config_.isPresent()?config_.get():null;
	}
	
	
	public void sendNotice() {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("AddSmsSign");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
	}
}
