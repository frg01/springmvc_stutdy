<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/12/5
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试 模型数据</title>
</head>
<body>
<h1>添加主人信息</h1>
<form action="vote/vote05" method="post">
    主人号：<input type="text" name="id"><br>
    主人名：<input type="text" name="name"><br>
    宠物号：<input type="text" name="pet.id"><br>
    宠物名：<input type="text" name="pet.name"><br>
    <input type="submit" value="添加主人和宠物">
</form>



<h1>添加主人信息Map</h1>
<form action="vote/vote06" method="post">
    主人号：<input type="text" name="id"><br>
    主人名：<input type="text" name="name"><br>
    宠物号：<input type="text" name="pet.id"><br>
    宠物名：<input type="text" name="pet.name"><br>
    <input type="submit" value="添加主人和宠物">
</form>

<h1>添加主人信息【测试ModelAndAndView】</h1>
<form action="vote/vote07" method="post">
    主人号：<input type="text" name="id"><br>
    主人名：<input type="text" name="name"><br>
    宠物号：<input type="text" name="pet.id"><br>
    宠物名：<input type="text" name="pet.name"><br>
    <input type="submit" value="添加主人和宠物">
</form>


<h1>添加主人信息【测试session】</h1>
<form action="vote/vote08" method="post">
    主人号：<input type="text" name="id"><br>
    主人名：<input type="text" name="name"><br>
    宠物号：<input type="text" name="pet.id"><br>
    宠物名：<input type="text" name="pet.name"><br>
    <input type="submit" value="添加主人和宠物">
</form>
</body>
</html>
