<%--
  Created by IntelliJ IDEA.
  User: QQZ
  Date: 2020/2/17
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>//加上这一句下面的表达式才有作用
<html>
<head>
    <title>Title</title>
</head>
<body>
${requestScope.a1}
${sessionScope.a}
<h1>noapi</h1>
${requestScope.map}
${requestScope.modelmap}
${requestScope.model}
${sessionScope.map}
${sessionScope.modelmap}
<h1>mav</h1>
${requestScope.mav}
</body>
</html>
