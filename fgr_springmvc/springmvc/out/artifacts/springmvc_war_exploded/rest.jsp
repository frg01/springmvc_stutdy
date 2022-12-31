<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/12/5
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rest</title>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function (){
            // alert("ok");
            $("#deleteBook").click(function (){
                $("#hiddenForm").attr("action",this.href);
                $(":hidden").val("DELETE");
                $("#hiddenForm").submit();
                return false;
            })
        })
    </script>
</head>
<body>
<h3>Rest风格的crud操作案例</h3><br/>

<h3>Rest查询书籍 [get]</h3><br/>
<a href="user/get/200">点击查询书籍</a>
<hr/>

<h3>Rest风格  添加书籍[post]</h3><br/>
<form action="user/add" method="post">
    name:<input name="book" type="text"><br/>
    <input type="submit" value="添加书籍">
</form>
<hr/>

<%--<a>标签默认情况下是GET方式
get请求转成springmvc 去识别delete  就要考虑HiddenHttpMethodFilter
HiddenHttpMethodFilter对以post提交的delete，put，patch进行转换成springmvc可以识别的RequestMethod。PUT...
需要将get请求转成post  我们可以通过jquery来处理
--%>
<h3>Rest风格  ,删除一本书</h3><br/>
<a href="user/delete/600" id="deleteBook">删除指定id的书</a>
<form action="" method="post" id="hiddenForm">
    <input type="hidden" name="_method"/>
</form>
<hr/>

<%--input _method 为指定method类型--%>
<h3>Rest风格  修改书籍[put]</h3><br/>
<form action="user/update/300" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="添加书籍~">
</form>

</body>
</html>
