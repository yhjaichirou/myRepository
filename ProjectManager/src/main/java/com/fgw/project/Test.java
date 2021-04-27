package com.fgw.project;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

public class Test {
	
	
	public static void main(String[] args) {
		String dateStr1 = "2021-01-01 22:33:23";
		Date date1 = DateUtil.parse(dateStr1);
		String dateStr2 = "2021-01-04 23:33:23";
		Date date2 = DateUtil.parse(dateStr2);
		System.out.println(date1.after(date2));
		
//		boolean s = date2.before(date1);
//		//相差一个月，31天
//		long betweenDay = DateUtil.between(date2,date1 , DateUnit.DAY);
//		System.out.println(s);
//		
//		
//		String cpcode = "AAAAAC";
//		String msg = "模板短信测试,0001";
//		String mobiles = "18607714703,18648807841";
//		String excode = "";
//		String templetid = "2";
//		String key = "f75b9467e1112239";
//		String md5source = cpcode+ msg+mobiles+excode+templetid+key;
//		md5source = "123456";
//		String md5str = "";
//		try {
//			md5str = makeMD5(new String(md5source.getBytes("UTF-8"), "UTF-8")).toLowerCase();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		System.out.println(md5str);
		
		
	}
	
	
	public static String makeMD5(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("UTF-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			re_md5 = buf.toString().toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re_md5;
	}
}
