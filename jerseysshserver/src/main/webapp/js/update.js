$(function(){
            $("#uploadimg").change(function(){
                var option={
                    url:"${pageContext.request.contextPath}/jerseyssh/uploadImg.do",
                    type:"post",
                    dataType:"text",
                    data:{fileName:"uploadimg"},
                    success:function(data){
                    //把json格式的字符串转换成json对象
                        var dataObj=$.parseJSON(data);
                        //返回服务器的图片路径，并把图片的路径设置给img标签
                        $("#img").attr("src",dataObj.fullPath);
                    }
                };
                //上传表单
                $("#imgform").ajaxSubmit(option);
            });
        });