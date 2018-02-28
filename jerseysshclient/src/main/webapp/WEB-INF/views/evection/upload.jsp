<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>
<h2>出差后台管理系统</h2>
<div>
    <form action="http://localhost:9090/jerseysshserver/file/upload" method="post" enctype="multipart/form-data">
 
	   <p>
		Select a file : <input type="file" name="file" size="45" />
	   </p>
	   <input type="submit" value="Upload" />
	</form>
</div>
</body>
</html>
