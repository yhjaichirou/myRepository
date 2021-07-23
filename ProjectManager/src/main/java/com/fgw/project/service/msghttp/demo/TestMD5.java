package com.fgw.project.service.msghttp.demo;

import java.io.Console;

import com.fgw.project.service.msghttp.demo.AppDemo.SendSmsEntity;
import com.fgw.project.util.msg.MD5;

import net.sf.json.JSONObject;
/**
 * {"sign":"33DC55F68E8310426DF88FE3B2DCA137","content":"123","timestamp":"1566875272752",
 * "reportUrl":"","attach":"","spNumber":"234","sendTime":"20190827110752","moUrl":"",
 * "mobile":"15852783736","appKey":"100957"}
 * @author pei
 *
 */
public class TestMD5 {
	public static void main(String[] args) {
		
//		System.out.println(MD5.getMD5("100838156586068718115354117186【水电八局工程采购平台】本次操作随机验证码为：390190，有效时间为1分钟，请及时使用。0f6741528278fbb5"));
//		System.out.println(MD5.getMD5("100838156586068718115354117186【水电八局工程采购平台】本次操作随机验证码为：390190，有效时间为1分钟，请及时使用。0f6741528278fbb5")
//				.equals("4330bd26a3b4c71c7a05125c2dd1d934"));
//		System.out.println(  );
		System.out.println(MD5.getMD5("100957156706321581815852783736短信测试234201908291520151fc582dd65403f6f"));

	}

	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		//		System.out.println( (System.currentTimeMillis()-1546589422955L)/(3600*1000) );
		//		
		//		SendSmsEntity e=buildSendEntity();
		//		System.out.println( JSONObject.fromObject(e).toString() );
		//appKey,timestamp,mobile,content,spNumber,sendTime,appSecret
		String s = "100819156533152345118695993557消息提醒201908091418440f6741528278fbb5";
		String a = "100819156533152345118695993557消息提醒201908091418440f6741528278fbb5";
		String b = "100801156549541840013590102061【沃蜗科技】您正在使用手机扫一扫进入停车场驰安科技园-大门入口,验证码473296,车牌:$粤B666663230178a7d437ef4";
		System.out.println(MD5.getMD5(b).equals("e5e8f2af69ceda1ec9eeba73d32d1d08"));

		//		String aa="【沃蜗科技】您正在使用手机扫一扫进入停车场驰安科技园-大门入口,验证码473296,车牌:$粤B66666";
		//		System.out.println(aa.indexOf("$"));

		String src = "?????";
		String url = new String(src);
		String[] v = new String[] { "100801", "1565495418400", "13590102061",
				"【沃蜗科技】您正在使用手机扫一扫进入停车场驰安科技园-大门入口,验证码473296,车牌:粤B66666", "3230178a7d437ef4" };
		for (String ss : v) {
			if (ss.indexOf("$") >= 0) {
				ss = ss.replace("$", "\\$");
			}
			System.out.println(ss);
			url = url.replaceFirst("\\?", ss);
		}
		System.out.println(url);
		System.out.println(MD5.getMD5(url));
		System.out.println(MD5.getMD5(url).equals("e5e8f2af69ceda1ec9eeba73d32d1d08"));
	}

	/**
	 * {"appKey":"100801","timestamp":"1564982486888","mobile":"13590102061",
	 * "content":"【沃蜗科技】请输入验证码：903371","spNumber":"","sendTime":"",
	 * "reportUrl":"","moUrl":"","attach":"","sign":"5e1ddce75d8ff1c262f63e34ec49de2a"} 
	 * 
	 * 测试校验结果：
	 * 
	 * SendSmsEntity [appKey=100801, timestamp=1564982486888, mobile=13590102061, 
	 * content=【沃蜗科技】请输入验证码：903371, sendTime=, spNumber=, sign=d40a67b799ae6884542a5b254a43a36e, 
	 * reportUrl=, moUrl=, attach=]
	
	
	 * {"appKey":"100819","timestamp":"1546589422955","mobile":"18695993557",
	 * "content":"汉中市政府","spNumber":"","sendTime":"20190809090057","reportUrl":"",
	 * "moUrl":"","attach":"","sign":"B65977B7BC6FF701A9B4395550CB3577"}
	 * @return
	
	
	 */

	public static SendSmsEntity buildSendEntity() {
		// TODO Auto-generated method stub
		SendSmsEntity entity = new SendSmsEntity();
		entity.setAppKey("100819");
		entity.setTimestamp(1546589422955L + "");
		entity.setMobile("18695993557");
		entity.setContent("汉中市政府");
		entity.setSpNumber("");
		entity.setSendTime("20190809090057");
		entity.setReportUrl("");
		//appKey,timestamp,mobile,content,spNumber,sendTime,appSecret
		entity.setSign(MD5.getMD5(entity.getAppKey() + entity.getTimestamp() + entity.getMobile()
				+ entity.getContent() + entity.getSpNumber() + entity.getSendTime() + "0f6741528278fbb5"));
		return entity;
	}
}
