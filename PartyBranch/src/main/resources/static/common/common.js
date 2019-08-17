/**
 * 
 */
requirejs.config({
	baseUrl:contextPath,
	urlArgs: 'v1.0.0',
    paths: {
    	ZeroClipboard: "ueditor/third-party/zeroclipboard/ZeroClipboard.min",//主要是加这句话
    	ueditor:"ueditor/ueditor.all.min",
    	ueditorConfig:"ueditor/ueditor.config",
    	ueditorzh:"ueditor/lang/zh-cn/zh-cn.min",
        jquery3: 'common/jquery-3.3.1-min',
        jquery: 'common/jquery.min',
        ueditorImgUp:"js/cms/ueditor",
        
        qrcode:'common/jquery.qrcode.min',
        bootstrap:'common/bootstrap/js/bootstrap.min',
        swiperAnimate:'common/swiper/js/swiper.animate1.0.3.min',
        swiper:'common/swiper/js/swiper.min',
        jqueryValidate: 'common/jquery.validate',
        validatMessage: 'common/messages_zh',
        upload2:"js/cms/upload2",
        csrf: 'common/csrf',
        layui:"common/frame/layui/layui",
        layuiCom:"common/frame/static/js/vip_comm",
        slide:"js/cms/slide",
        swiper:"js/web/swiper.min",
        map:'js/web/map/map',
        
      //threejs 依赖
        three:'js/web/three',
        OrbitControls:"js/web/OrbitControls",
        WebGL:"js/web/WebGL",
        LineSegmentsGeometry:"js/web/LineSegmentsGeometry",
        LineGeometry:'js/web/LineGeometry',
        WireframeGeometry2:'js/web/WireframeGeometry2',
        particles:'js/web/particles.min',
        LineMaterial:'js/web/LineMaterial',
        Wireframe:'js/web/Wireframe',
        LineGeometry:'js/web/LineGeometry',
        tween:'js/web/Tween',
    	animation:'js/web/Animation',
    	
    	base64:"common/base64",
    	
    },
	shim:{
		 csrf:{
			 deps:['jquery'],
			 exports:"Csrf"
		 },
		 loader:{
			 exports:"Loader"
		 },
		 loaderPage:{
			 deps:['jquery'],
			 exports:"LoaderPage"
		 },
		 menu:{
			 deps:['jquery','loaderPage'],
			 exports:"Menu"
		 },
		 BMap: {
		        deps: ['jquery'],
		        exports: 'BMap'
		    },
		    ueditor:{
		    	deps: ['ZeroClipboard','ueditorConfig'],
		    	exports: 'UE',
		    	init:function(ZeroClipboard,UE){
		    	//导出到全局变量，供ueditor使用
		    	window.ZeroClipboard = ZeroClipboard;
	    	  window.UE.Editor.prototype._bkGetActionUrl = window.UE.Editor.prototype.getActionUrl;
	    	  window.UE.Editor.prototype.getActionUrl = function(action){
	    	    	//调用自己写的Controller
	    	    	if(action == 'uploadimage' || action == 'uploadfile'){
	    	    		return  contextPath+"/ueditor/uploadimage"; //自己controller的action
	    	    	}else if(action == "uploadvideo"){
	    	    		return contextPath+"/ueditor/videoUp";//自己controller的action
	    	    	}else{
	    	    		return this._bkGetActionUrl.call(this,action);//百度编辑器默认的action
	    	    	}
	    	    }
		    	    

		    	}
		    },
		    layuiCom:{
		    	deps:['layui']
		    },
		    slide:{
		    	deps:['jquery']
		    },
		    qrcode:{
		    	deps:['jquery'],
		    	exports:'$'
		    },
		    animation:{
		    	deps:['tween']
		    },
		    map:{
		    	exports:'BMap'
		    }
	},
	waitSeconds:10
});

