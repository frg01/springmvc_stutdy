<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/12/3
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
异常信息${requestScope.get("reason")}


<h1>登陆成功</h1>
欢迎您 ：${requestScope.user.name}


取出request域中的数据  通过el表达式来获取
address：${requestScope.address}<br>
主人名字=${requestScope.master.name}<br>
主人id=${requestScope.master.id}<br>
宠物名字=${requestScope.master.pet.name}<br>

取出session域的数据
address：${sessionScope.address}<br>
主人名字=${sessionScope.master}<br>
宠物名字=${sessionScope.master.pet.name}<br>
</body>
</html>
