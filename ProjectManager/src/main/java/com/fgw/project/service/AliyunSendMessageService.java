package com.fgw.project.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fgw.project.model.vo.SendMessageResult;
import com.fgw.project.util.RetKit;

/**
 * 短信发送
 * @author yhj   --- 废弃
 * @date 2021年1月11日
 */
@Service
public class AliyunSendMessageService {
	
	@Value("${aliyun.product}")
	private String alyProduct;
	@Value("${aliyun.domain}")
	private String alyDomain;
	@Value("${aliyun.accessKeyId}")
	private String alyAccessKeyId;
	@Value("${aliyun.accessKeySecret}")
	private String alyAccessKeySecret;

	public void addSmsSign() {
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

	public boolean sendMessage(String phoneNumbers,String title,String content) {

		try {

			// 设置超时时间-可自行调整
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");
			// 初始化ascClient需要的几个参数
			final String product = alyProduct;// 短信API产品名称（短信产品名固定，无需修改）
			final String domain = alyDomain;// 短信API产品域名（接口地址固定，无需修改）
			// 替换成你的AK
			final String accessKeyId = alyAccessKeyId;// 你的accessKeyId,参考本文档步骤2
			final String accessKeySecret = alyAccessKeySecret;// 你的accessKeySecret，参考本文档步骤2
			// 初始化ascClient,暂时不支持多region（请勿修改）
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象
			SendSmsRequest request = new SendSmsRequest();
			// 使用post提交
			request.setMethod(MethodType.POST);
			// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
			request.setPhoneNumbers("1500000000");
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("云通信");
			// 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
			request.setTemplateCode("SMS_1000000");
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			// 参考：request.setTemplateParam("{\"变量1\":\"值1\",\"变量2\":\"值2\",\"变量3\":\"值3\"}")
			request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
			// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");

			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			request.setOutId("yourOutId");

			// 请求失败这里会抛ClientException异常
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				// 请求成功
			}
			return true;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public RetKit querySendDetail(){
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String datas = response.getData();
            SendMessageResult obj = JSONObject.parseObject(datas, SendMessageResult.class);
            if(obj.getCode().equals("OK")) {
            	return RetKit.fail(obj.getMessage());
            }else {
            	return RetKit.okData(obj.getSmsSendDetailDTOs());
            }
        } catch (ServerException e) {
            e.printStackTrace();
            return RetKit.fail(e.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
            return RetKit.fail(e.getMessage());
        }
	}
}
