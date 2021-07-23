package com.fgw.project.controller.msg;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.fgw.project.service.msghttp.client.Request;
import com.fgw.project.service.msghttp.client.Response;
import com.fgw.project.service.msghttp.client.Request.ContentType;
import com.fgw.project.service.msghttp.demo.AppDemo;
import com.fgw.project.service.msghttp.demo.Constants;
import com.fgw.project.service.msghttp.demo.AppDemo.QueryEntity;
import com.fgw.project.service.msghttp.demo.AppDemo.SendSmsEntity;
import com.fgw.project.util.msg.AES;
import com.fgw.project.util.msg.MD5;
import com.fgw.project.util.msg.RSA;
import com.fgw.project.util.msg.RandomUtils;

import net.sf.json.JSONObject;

@Service
public class MsgApiService {

	Request request = null;
	public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC18dt2/XOHX7SoPf/SSBFU1DccRq5dAwOGFvamQkosogbwyU/cxrHGtVJw/q7mXUFY4TGMiYd/eB5AzHvUHZlZz1rymBzj98HPF3lJvCYl+PZrvYqzIw+2IJsK219VM6QVouWgTeUTe/j0cJhwpTE1ECfjJr6cQEYh563iJ1ONowIDAQAB";

	public void run() throws Exception {
		/**
		 * 初始化一次；必须预设服务端uri
		 */
		String url = Constants.SMS_SEND_URL;

		request = Request.newHttpRequestBuilder().uri(url).timeout(5000).charset("utf-8").method(Request.POST)
				.contentType(Request.ContentType.JSON).build();
		send(null);//发送短信
		//securitySend();
		//		queryBalance();
		//		queryReport();
//		queryMo();//获取上行信息

		request.release();
	}

	/**
	 * 协议实体
	 * @author pei
	 *
	 */
	public static class SendSmsEntity {
		String appKey = "";
		String timestamp = "";
		String mobile = "";
		String content = "";
		String sendTime = "";
		String spNumber = "";
		String sign = "";
		String reportUrl = "";
		String moUrl = "";
		String attach = "";

		public String getAppKey() {
			return appKey;
		}

		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getSendTime() {
			return sendTime;
		}

		public void setSendTime(String sendTime) {
			this.sendTime = sendTime;
		}

		public String getSpNumber() {
			return spNumber;
		}

		public void setSpNumber(String spNumber) {
			this.spNumber = spNumber;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		public String getReportUrl() {
			return reportUrl;
		}

		public void setReportUrl(String reportUrl) {
			this.reportUrl = reportUrl;
		}

		public String getMoUrl() {
			return moUrl;
		}

		public void setMoUrl(String moUrl) {
			this.moUrl = moUrl;
		}

		public String getAttach() {
			return attach;
		}

		public void setAttach(String attach) {
			this.attach = attach;
		}

		@Override
		public String toString() {
			return String.format(
					"SendSmsEntity [appKey=%s, timestamp=%s, mobile=%s, content=%s, sendTime=%s, spNumber=%s, sign=%s, reportUrl=%s, moUrl=%s, attach=%s]",
					appKey, timestamp, mobile, content, sendTime, spNumber, sign, reportUrl, moUrl, attach);
		}

	}

	/**
	 * 协议查询实体
	 * @author pei
	 *
	 */
	public static class QueryEntity {
		String appKey;
		String sign;

		public String getAppKey() {
			return appKey;
		}

		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}
	}

	/**
	 * 短信发送
	 * @throws UnsupportedEncodingException 
	 */
	public void send(SendSmsEntity se) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub		
		//1. 构建数据包对象
		SendSmsEntity sendEntity = buildSendEntity();
		if(se != null) {
			sendEntity = se;
		}
		//2. 转化对象为json字符串数据
		String data = JSONObject.fromObject(sendEntity).toString();
		long t1 = System.currentTimeMillis();
		//3. 字符串url编码，防止特殊字符被客户端强行改变，譬如+会被浏览器或者其他客户端变更为空格
		String encodeData = URLEncoder.encode(data, "UTF-8");
		//4. 发送
		Response resp = request.send(Constants.SMS_SEND_URL, encodeData);
		//5. 处理发送响应结果 resp.status==1时，		
		System.out.println("\n");
		System.out.println(">>> Request.url:             " + Constants.SMS_SEND_URL);
		System.out.println(">>> Request.data:            " + data);
		System.out.println(">>> ");
		System.out.println(">>> Response.contentLength:  " + resp.contentLength());
		System.out.println(">>> Response.contentType:    " + resp.contentType());
		System.out.println(">>> Response.status:         " + resp.status());
		System.out.println(">>> Response.charset:        " + resp.charset());
		System.out.println(">>> Response.content:        " + resp.content());
		System.out.println(">>> Response.error:          " + resp.error());
		System.out.println(">>> cost.time.ms:            " + (System.currentTimeMillis() - t1));

		//解析发送结果
		if (resp.status() == 200 && resp.content() != null) {
			JSONObject respJson = JSONObject.fromObject(resp.content());
			System.out.println(
					">>> Send.result:             " + (respJson.getInt("status") == 1 ? "OK" : "FAIL"));
			if (respJson.getInt("status") == 1) {
				System.out.println(">>> Send.result.taskId:      " + respJson.getLong("taskId"));
			}
		}

	}

	/**
	 * {"appKey":"100819","timestamp":"1546589422955","mobile":"18695993557",
	 * "content":"汉中市政府","spNumber":"","sendTime":"20190809090057","reportUrl":"",
	 * "moUrl":"","attach":"","sign":"B65977B7BC6FF701A9B4395550CB3577"}
	 * @return
	 */
	public static SendSmsEntity buildSendEntity() {
		// TODO Auto-generated method stub
		SendSmsEntity entity = new SendSmsEntity();
		entity.setAppKey(Constants.APP_KEY);
		entity.setTimestamp(System.currentTimeMillis() + "");
		entity.setMobile("18647410031");
		entity.setContent("【申报】测试消息,MT-00001");
		entity.setReportUrl(Constants.CALL_BACK_URL); //"http://brinch.wlcbhc.com:8086/test/callback"
		entity.setSpNumber("");
		//定时发送
		//entity.setSendTime("20190809090057");
		//签名校验必填写字段
		//appKey,timestamp,mobile,content,spNumber,sendTime,appSecret
		String source = entity.getAppKey() + entity.getTimestamp() + entity.getMobile() + entity.getContent()
				+ entity.getSpNumber() + entity.getSendTime() + Constants.APP_SECRET;
		String sign = MD5.getMD5(source);
		entity.setSign(sign);
		return entity;
	}

	/**
	 * 加密发送 需额外申请
	 * @throws Exception
	 */
	public void securitySend() throws Exception {
		//1. 构建数据包对象
		SendSmsEntity sendEntity = buildSendEntity();
		//2. 转为json字符串数据
		String data = JSONObject.fromObject(sendEntity).toString();
		//3. 生成16随机aes密码；密码长度不能小于16位
		String aesKey = RandomUtils.randomString(16);
		System.out.println("AES.key=" + aesKey);
		//4. 对发送数据进行aes加密啊
		String encrptData = Base64.encodeBase64String(AES.encryptAES(data, aesKey));
		//5. 使用分配的公钥加密16位随机密码;publicKey 平台方提供
		String rsaKey = RSA.encodeBase64StringPublic(aesKey.getBytes(), RSA.getPublicKey(publicKey));
		System.out.println("token=" + aesKey);
		// url地址增加客户参数 /api/sms/air/encrptySend/:appKey
		String securityUrl = Constants.SMS_SECURITY_SEND_URL + "/" + sendEntity.getAppKey();
		//6. 随机密码存入http头字段token中
		request.builder().contentType(ContentType.TEXT).header("token", rsaKey).build();		
		long t1 = System.currentTimeMillis();
		//7. 发送加密数据
		Response resp = request.send(securityUrl, encrptData);
		System.out.println("\n");
		System.out.println(">>> Request.url:             " + securityUrl);
		System.out.println(">>> Request.data:            " + data);
		System.out.println(">>> Request.data.encrpt:     " + encrptData);
		System.out.println(">>> ");
		System.out.println(">>> Response.contentLength:  " + resp.contentLength());
		System.out.println(">>> Response.contentType:    " + resp.contentType());
		System.out.println(">>> Response.status:         " + resp.status());
		System.out.println(">>> Response.charset:        " + resp.charset());
		System.out.println(">>> Response.content:        " + resp.content());
		System.out.println(">>> Response.error:          " + resp.error());
		System.out.println(">>> cost.time.ms:            " + (System.currentTimeMillis() - t1));
	}

	/**
	 * 查询余额
	 */
	public void queryBalance() {

		QueryEntity entity = new QueryEntity();
		entity.setAppKey(Constants.APP_KEY);
		entity.setSign(MD5.getMD5(entity.getAppKey() + Constants.APP_SECRET));

		String data = JSONObject.fromObject(entity).toString();
		long t1 = System.currentTimeMillis();
		Response resp = request.send(Constants.SMS_QUERY_BALANCE_URL, data);
		System.out.println("\n");
		System.out.println(">>> Request.url:             " + Constants.SMS_QUERY_BALANCE_URL);
		System.out.println(">>> Request.data:            " + data);
		System.out.println(">>> ");
		System.out.println(">>> Response.contentLength:  " + resp.contentLength());
		System.out.println(">>> Response.contentType:    " + resp.contentType());
		System.out.println(">>> Response.status:         " + resp.status());
		System.out.println(">>> Response.charset:        " + resp.charset());
		System.out.println(">>> Response.content:        " + resp.content());
		System.out.println(">>> Response.error:          " + resp.error());
		System.out.println(">>> cost.time.ms:            " + (System.currentTimeMillis() - t1));
	}

	/**
	 * 查询状态报告
	 * @author pei
	 *
	 */
	public void queryReport() {
		// TODO Auto-generated method stub
		QueryEntity entity = new QueryEntity();
		entity.setAppKey("90001");
		entity.setSign(MD5.getMD5(entity.getAppKey() + Constants.APP_SECRET));

		String data = JSONObject.fromObject(entity).toString();
		long t1 = System.currentTimeMillis();
		Response resp = request.send(Constants.SMS_QUERY_REPORT_URL, data);
		System.out.println("\n");
		System.out.println(">>> Request.url:             " + Constants.SMS_QUERY_REPORT_URL);
		System.out.println(">>> Request.data:            " + data);
		System.out.println(">>> ");
		System.out.println(">>> Response.contentLength:  " + resp.contentLength());
		System.out.println(">>> Response.contentType:    " + resp.contentType());
		System.out.println(">>> Response.status:         " + resp.status());
		System.out.println(">>> Response.charset:        " + resp.charset());
		System.out.println(">>> Response.content:        " + resp.content());
		System.out.println(">>> Response.error:          " + resp.error());
		System.out.println(">>> cost.time.ms:            " + (System.currentTimeMillis() - t1));
	}

	/**
	 * 查询mo
	 * @author pei
	 *
	 */
	public void queryMo() {
		// TODO Auto-generated method stub
		QueryEntity entity = new QueryEntity();
		//entity.setAppKey("90001");
		entity.setAppKey(Constants.APP_KEY); //53d4cce170cdda7f  100806
		entity.setSign(MD5.getMD5(entity.getAppKey() + Constants.APP_SECRET));
//		entity.setSign(MD5.getMD5(entity.getAppKey() + "53d4cce170cdda7f"));

		String data = JSONObject.fromObject(entity).toString();
		long t1 = System.currentTimeMillis();
		String url = Constants.SMS_QUERY_MO_URL;
		url = Constants.HTTP_SERVER + "/api/sms/air/mo";
		Response resp = request.send(url, data);
		System.out.println("\n");
		System.out.println(">>> Request.url:             " + url);
		System.out.println(">>> Request.data:            " + data);
		System.out.println(">>> ");
		System.out.println(">>> Response.contentLength:  " + resp.contentLength());
		System.out.println(">>> Response.contentType:    " + resp.contentType());
		System.out.println(">>> Response.status:         " + resp.status());
		System.out.println(">>> Response.charset:        " + resp.charset());
		System.out.println(">>> Response.content:        " + resp.content());
		System.out.println(">>> Response.error:          " + resp.error());
		System.out.println(">>> cost.time.ms:            " + (System.currentTimeMillis() - t1));
	}
	
	
	public String msgFormate(String tittle, String content,String c) {
		String rt = new String();
		rt = Constants.C_HEAD + "";
		
		return rt;
	}
}
