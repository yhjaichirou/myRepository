package com.fgw.project.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.thread.ThreadUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 上传文件工具
 * @author admin
 *
 */
public class UploadFile {
	private static Log log = LogFactory.getLog(UploadFile.class);
	public static final String url = "D:\\image\\";
	public static final String[] videoType = {"VIDEO","3gp","MOV","AVI","avi","video"};
	
	/**
	 * 上传
	 * @param path 
	 * @param mulFile
	 * @return
	 * @throws Exception 
	 */
	public static String upload(String path,MultipartFile mulFile) throws Exception {
		String nativeName = mulFile.getOriginalFilename();
		//上传后文件做处理
		//int indexPoint = nativeName.indexOf(".");
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
		
//		if (StrKit.notBlank(suffix)) {
//			List<String> videoTpye = Arrays.asList(videoType);
//			if (videoTpye.contains(suffix)) {
//				fileName = System.currentTimeMillis() + proName + ".mp4";
////				getAynsConver( path+fileName ,dest);
//			}
//		}
		return fileName;
	}
	//异步
//	private static void getConver(String path_fileName,File dest) {
//		Runnable run = () -> {
//			String filename = convertToMp4(path_fileName,dest);
//			log.info("文件转化成功"+filename);
//		};
//		ThreadUtil.execute(run);
//	}
//	//同步
//	private static void getAynsConver(String path_fileName,File dest) throws Exception {
//		String filename = convertToMp4(path_fileName,dest);
//		log.info("文件转化成功"+filename);
//	}
	
	public static boolean deteleFile(String url) {
		File fileTemp = new File(url);
		if(fileTemp.exists()) {
			fileTemp.delete();
			return true;
		}
		return false;
	}
	
	
    
//	/**
//	 * 需要pom.xml 引入
//	 *  <dependency>
//            <groupId>org.bytedeco</groupId>
//            <artifactId>javacv</artifactId>
//            <version>1.5</version>
//        </dependency>
// 
//        <dependency>
//            <groupId>org.bytedeco</groupId>
//            <artifactId>javacv-platform</artifactId>
//            <version>1.5</version>
//        </dependency>
// 
//        <dependency>
//            <groupId>org.bytedeco</groupId>
//            <artifactId>javacpp</artifactId>
//            <version>1.5</version>
//        </dependency>
// 
//        <dependency>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>opencv-platform</artifactId>
//            <version>4.0.1-1.4.4</version>
//        </dependency>
// 
//        <dependency>
//            <groupId>org.bytedeco.javacpp-presets</groupId>
//            <artifactId>ffmpeg-platform</artifactId>
//            <version>4.1-1.4.4</version>
//        </dependency>
//	 */
//    /**
//     * 转换视频文件为mp4
//     * @param file
//     * @return
//     * @throws org.bytedeco.javacv.FrameRecorder.Exception 
//     * @throws org.bytedeco.javacv.FrameGrabber.Exception 
//     */
//	public static String convertToMp4(String path_fileName ,File file) throws Exception{
//        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);
//        String fileName = path_fileName;
//        Frame captured_frame = null;
//        FFmpegFrameRecorder recorder = null;
//        
//        // 错误 ： av_interleaved_write_frame() error -22 while writing interleaved video packet；
//    	// 
//    	//AVPacket pkt = frameGrabber.grabPacket();  
//    	
//        frameGrabber.start();
//        //fileName = file.getAbsolutePath() + "__.mp4";
//        recorder = new FFmpegFrameRecorder(fileName, frameGrabber.getImageWidth(), frameGrabber.getImageHeight(), frameGrabber.getAudioChannels());
//        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); //avcodec.AV_CODEC_ID_H264  //AV_CODEC_ID_MPEG4
//        recorder.setFormat("mp4");
//        recorder.setFrameRate(frameGrabber.getFrameRate());
//        //recorder.setSampleFormat(frameGrabber.getSampleFormat()); //
//        recorder.setSampleRate(frameGrabber.getSampleRate());
//
//        recorder.setAudioChannels(frameGrabber.getAudioChannels());
//        recorder.setFrameRate(frameGrabber.getFrameRate());
//        recorder.start();
//        
//        
//        while ((captured_frame = frameGrabber.grabFrame()) != null) {
//            try {
//            	
//                recorder.setTimestamp(frameGrabber.getTimestamp());
//                recorder.record(captured_frame);
//
//            } catch (Exception e) {
//            }
//        }
//        recorder.stop();
//        recorder.release();
//        frameGrabber.stop();
////        try {
////        	
////        } catch (Exception e) {
////        	log.error("文件转化错误："+e.getMessage());
////        	e.printStackTrace();
////        }
//        //file.delete();
//        return fileName;
//    }
//	/**
//     * 获取视频时长 单位/秒
//     * @param video
//     * @return
//     */
//    public static long getVideoDuration(File video) {
//        long duration = 0L;
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
//        try {
//            ff.start();
//            duration = ff.getLengthInTime() / (1000 * 1000);
//            ff.stop();
//        } catch (FrameGrabber.Exception e) {
//            e.printStackTrace();
//        }
//        return duration;
//    }
//    
	
}
