<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
function guid() {
	function S4() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	}
	return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}


	function submit() {
		var fileId = guid();
		var block = 1024*1024;
		$(function() {
			function sendFile(url,formData)
			{
				$.ajax({
					type : "post",
					url : url,
					cache : false, //上传文件无需缓存
					processData : false, // 告诉jQuery不要去处理发送的数据
					contentType : false, // false告诉jQuery不要去设置Content-Type请求头
					data : formData,
					//dataType: "JSON",
					beforeSend : function() {
						console.log("正在进行，请稍候");
					},
					success : function(responseStr) {
						console.log("成功" + responseStr);
					},
					error : function(responseStr) {
						console.log("error");
					}
				});
			}
			
			var formData = new FormData();
			var file = $("input[name=file]")[0].files[0];
			var length = file.size;
			var fileName = file.name;
			// 添加key
			formData.append("file", file);
			formData.append("id", fileId);
			formData.append("seek", 0);	
			formData.append("fileName",fileName);
			console.log(size);
			
			if (length > 2*block)
			{
				var size = parseInt(length / block);
				var s = parseInt(length % block);
				// 循环的次数
				var frequency = s > 0 ? size + 1 : size;
				// 更新key
				for (var i=0;i<frequency;i++)
				{
					if (i == (frequency -1))
					{
						var data = file.slice(i*block,length);//表示取文件的0到100k大小的数据
						formData.set("file", data); // append表示拼接这个字段的值
						formData.set("id", fileId);// 表示唯一一个文件
						formData.set("seek", i*block);	
						formData.set("fileName", fileName);
					}else
					{
						var data = file.slice(i*block,i*block+block);//表示取文件的0到100k大小的数据
						formData.set("file", data);
						formData.set("id", fileId);// 表示唯一一个文件
						formData.set("seek", i*block);	
						formData.set("fileName", fileName);
					}
					
					sendFile("FileServlet",formData);
				}
			}else
			{
				sendFile("FileServlet",formData);
			}
			
			
			
			// 删除key
			formData.delete("file");
			formData.delete("id");
			formData.delete("seek");	
			formData.delete("fileName");
		});
	}
</script>
</head>
<body>
	<input type="File" name="file">
	<br>
	<button onclick="submit()">按钮</button>
</body>
</html>