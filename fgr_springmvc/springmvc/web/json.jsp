<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/12/8
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json提交</title>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#getJson").click(function (){
                var url = this.href;
                var args = {"time":new Date()};//这时要发送的数据,防止页面缓存
                $.post(
                    url,
                    args,
                    function (data){//返回的数据，是json格式
                        console.log("data=",data);
                        console.log("data.name=",data.name);
                        console.log("data.address=",data.address);
                    },
                    "json"
                );
                return false;
            })
            $("button[name='butt1']").click(function (){
                var url = "/springmvc/save2"
                var userName = $("#userName").val();
                var age = $("#age").val();
                var args = JSON.stringify({
                    "userName" : userName,
                    "age":age
                })
                //将json对象换成json字符串

                $.ajax({
                    url:url,
                    data:args,
                    type:"POST",
                    success:function (data){
                        console.log("返回的data =",data);
                    },
                    //指定发送数据时的编码和格式 后台会使用转换器处理json
                    contentType:"application/json;charset=utf-8"
                    })
            })
        })
    </script>
</head>
<body>
<%--当用户点击连接 发出ajax请求 接收请求后 查看数据--%>
<a href="<%=request.getContextPath()%>/json/dog" id="getJson">点击获取json数据</a>
<h1>发出一个接送数据</h1>
u:<input id="userName" type="text"><br/>
a:<input id="age" type="text"><br/>
<button name="butt1">添加数据</button>

<h1>下载文件的测试</h1>
<a href="<%=request.getContextPath()%>/downFile">点击下载文件</a>
</body>
</html>
