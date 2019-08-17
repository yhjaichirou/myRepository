package com.dzb.partyBranch.kit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuKit {
	
//	@Value("access_key")
//	static String ACCESS_KEY;
//	
//	@Value("secret_key")
//	public
//    static String SECRET_KEY;
//	
//	@Value("bucketZone")
//    static String bucketZone;
//	
//	@Value("bucketname")
//    static String bucketname;
	
	
	//设置好账号的ACCESS_KEY和SECRET_KEY
		static String ACCESS_KEY = "DICBmZWLklcxycI_NXnhAp5Rq-uJHpOfH_f7j-Np";
		static String SECRET_KEY = "mbVczuIhqz0ketPM3Iz6twhDiqFB0HqiIQJv5jkS";
		// you bucket domain  eg: http://xxx.bkt.clouddn.com
		//域名
//	    static String domain = "p9zib6tkh.bkt.clouddn.com";//华北
//	    static String domain = "p616fj9tc.bkt.clouddn.com";//华南
		static String domain = "p3tbacis1.bkt.clouddn.com";//doortest华南
	    // 地区：http://up-z2.qiniu.com/
//	    static String bucketZone = "http://up-z1.qiniup.com";//华北
	    static String bucketZone = "http://up-z2.qiniup.com";//华南
	    
		// 要上传的空间
	    static String bucketName = "dchip-test";
	    
	    //访问地址
	    public static final String url = "doortest.z-chip.com";
	    //默认图片地址
	    public static final String def = "default.jpg";

		//密钥配置
		static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	    //创建上传对象
	    static UploadManager uploadManager = new UploadManager(new Configuration());
	    
	    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
	    public static String getUpToken(){
	        return auth.uploadToken(bucketName);
	    }
	    
	    // 获取domain
	    public static String getDomain() {
	        return domain;
	    }
	    
	    // 获取bucketZone
	    public static String getBucketZone() {
	        return bucketZone;
	    }
	    
	 // 删除：key为文件名
	    public static void delete(String key) throws QiniuException{
	        BucketManager bucketManager = new BucketManager(auth, new Configuration());
	        bucketManager.delete(bucketName, key);
	    }
	    
	    public static String upload(File fileName) throws IOException{
			try {
				//调用put方法上传
				String f = fileName.getName();
				String randomName = StrKit.getRandomUUID().toString().replace("-", "");
//						UUID.randomUUID().toString().replace("-", "");
				String name = randomName + "." + f.substring(f.lastIndexOf(".")+1);
				uploadManager.put(fileName, name, getUpToken());
				return name;
				
			} catch (QiniuException e) {
				Response r = e.response;
				// 请求失败时打印的异常的信息
				System.out.println(r.toString());
				try {
					//响应的文本信息
					System.out.println(r.bodyString());
				} catch (QiniuException e1) {
					//ignore
				}
			}     
			return null;  
		}
	    
	    public static String uploadStream(MultipartFile file) throws IOException {
	    	try {
				//调用put方法上传
	    		InputStream input = file.getInputStream();
				String f = file.getOriginalFilename();
				String randomName = StrKit.getRandomUUID().toString().replace("-", "");
//						UUID.randomUUID().toString().replace("-", "");
				String name = randomName + "." + f.substring(f.lastIndexOf(".")+1);
				uploadManager.put(input, name, getUpToken(),null,null);
				return name;
				
			} catch (QiniuException e) {
				Response r = e.response;
				// 请求失败时打印的异常的信息
				System.out.println(r.toString());
				try {
					//响应的文本信息
					System.out.println(r.bodyString());
				} catch (QiniuException e1) {
					//ignore
				}
			}     
			return null;  
	    }
	

}
