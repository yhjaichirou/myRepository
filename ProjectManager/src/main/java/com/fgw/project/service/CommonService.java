package com.fgw.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

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
import com.fgw.project.model.po.Industry;
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
	@Resource
	private AliyunSendMessageService aliService;

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
	
	/**
	 * 获取  Cascader 父类 数组格式   不包括自己  按父级顺序添加
	 * @param thisId
	 * @param industrys
	 * @return
	 */
	public List<Integer> getIndustryParent(Industry thisIndustry,List<Industry> industrys){
		List<Integer> ids = new ArrayList<>();
		if(thisIndustry.getPid()!=0) {
			for (Industry industry : industrys) {
				if(industry.getId().equals(thisIndustry.getPid())) {
					List<Integer> chi = getIndustryParent(industry,industrys);
					for (Integer chiid : chi) {
						ids.add(chiid);
					}
					ids.add(industry.getId());
					return ids;
				}
			}
		}
		return ids;
	}
	
	
	/**
	 * 	发送通知
	 */
	public void sendNotice(String phoneNumbers,String title,String content) {
        try {
        	//aliService.sendMessage(phoneNumbers,title, content);
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
}
