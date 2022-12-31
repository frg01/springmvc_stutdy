<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/12/5
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试 request= parameter</title>
</head>
<body>
<h2>获取超链接参数值</h2>

<a href="vote/vote01?name=fgr">获取超链接的参数</a><br/>

<a href="vote/vote02">获取请求头信息</a><br/>

<h2>添加一个主人以bean的数据形式添加</h2>
<form action="vote/vote03" method="post">
 编号：<input type="text" name="id" ><br>
 名字：<input type="text" name="name" ><br>
 宠物号：<input type="text" name="pet.id" ><br>
 宠物名：<input type="text" name="pet.name" ><br>
<input type="submit" value="添加主人和宠物"><br>
</form>

<%--servlet API使用--%>
<form action="vote/vote04" method="post">
    编号：<input type="text" name="id" ><br>
    名字：<input type="text" name="name" ><br>
    宠物号：<input type="text" name="pet.id" ><br>
    宠物名：<input type="text" name="pet.name" ><br>
    <input type="submit" value="添加主人和宠物"><br>
</form>
</body>
</html>
