<%--
  Created by IntelliJ IDEA.
  User: MSI-NB
  Date: 2020/8/14
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="regiest">
        用户名: <input type="text" name="userName">
        验证码: <input type="text" name="code"> <img src="/kaptcha/kaptcha.jpg" style="width: 100px; height: 28px;"/><br/>
        <input type="submit" value="submit">
    </form>
</body>
</html>
