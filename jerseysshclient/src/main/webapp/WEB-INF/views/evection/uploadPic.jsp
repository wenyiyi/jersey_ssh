<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>
<h2>出差后台管理系统</h2>

<div>
    <form id="jvForm" method="post" enctype="multipart/form-data">
        <tr>
            <td width="20%" class="pn-flabel pn-flabel-h"></td>
                <td width="80%" class="pn-fcontent">
                <img width="800" height="550" id="allImgUrl"/>
                <input type="hidden" name="imgUrl" id="imgUrl"/>
                <input type="file" onchange="uploadPic()" name="pic" />
            </td>
        </tr>
    </form>
</div>

<script type="text/javascript">
    function uploadPic(){
        $("#jvForm").ajaxSubmit({
            url:"uploadPic.do",
            dataType:"json",
            type:"post",
            success:function(data){
                $("#allImgUrl").attr("src",data.url);
                $("#imgUrl").val(data.path);
            }
        });
    }
    
</script>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
</body>
</html>
