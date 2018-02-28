$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    // 图片上传
    oFileInput.Init("projectfile", "${pageContext.request.contextPath}/upload.do");
});

var FileInput = function () {
     var oFile = new Object();

     //初始化fileinput控件（第一次初始化）
     oFile.Init = function(ctrlName, uploadUrl) {
     var control = $('#' + ctrlName);

     //初始化上传控件的样式
     control.fileinput({
     language: 'zh', //设置语言
     uploadUrl: uploadUrl, //上传的地址
     allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
     showUpload: true, //是否显示上传按钮
     browseClass: "btn btn-info", //按钮样式 
     maxFileCount: 10, //表示允许同时上传的最大文件个数
     initialCaption: "请上传XXX图片...",//文本框初始话value
     validateInitialCount:true,
     previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
     msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
     });

     var html = "" ;
     //导入文件上传完成之后的事件
     $("#projectfile").on("fileuploaded", function (event, data, previewId, index) {
         if(data.response && data.response.status !==200) {
             layer.msg(data.response.message);
         } else {
             layer.alert(data.response.message, {
              skin: 'layui-layer-molv' //样式类名
              ,closeBtn: 0
            }, function(){
              html += '<img alt="" src="'+data.response.url+'">' ;
              layer.alert(html, {
                skin: 'layui-layer-lan'
                ,closeBtn: 0
                ,anim: 4 //动画类型
              });
            });
         }

     });
    }
     return oFile;
    };