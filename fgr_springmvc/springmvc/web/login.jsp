<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/12/3
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h3>登陆页面</h3>


作业登录页面
<form action="<%=request.getContextPath()%>/login/do" method="post">
    u:<input name="name" type="text"/><br/>
    p:<input name="pwd" type="password"/><br/>
    <input type="submit" value="登录">
</form>
<%--

--%>
<form action="user/buy" method="post">
    u:<input name="username" type="text"/><br/>
    p:<input name="password" type="password"/><br/>
    <input type="submit" value="登录">
</form>

<hr><h1>演示params的使用</h1>
<a herf="user/buy?bookId=100">查询书籍</a>

<h1>电脑信息</h1>
<form action="computer/show" method="post">
    品牌：<input type="text" name="brand"><br/>
    价格：<input type="text" name="price"><br/>
    数量：<input type="text" name="num"><br/>
    <input type="submit" value="提交按钮">
</form>
</body>
</html>
