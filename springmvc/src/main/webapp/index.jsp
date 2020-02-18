<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>
<a href="/haha/hello">hello world</a>
<a href="/haha/user/nihao">测试pathvar</a>
<a href="/book/1">查询1号图书</a>
<form action="/book" method="post">
    <input type="submit" value="提交"/>
</form>

<form action="/book/1" method="post">
    <input type="submit" value="修改1号图书"/>
    <input name="_method" value="put"/>
</form>

<form action="/book/1" method="post">
    <input type="submit" value="删除一号图书"/>
    <input name="_method" value="delete"/>
</form>
<br>
<a href="/canshu?user=nihao">默认获取请求参数</a><br/>

<form action="/shu" method="post">
    书名：<input type="text" name="name">
    价格：<input type="text" name="price">
    作者名字：<input type="text" name="author.name">
    作者年龄：<input type="text" name="author.age">
    <input type="submit"/>
</form><br/>
<a href="/api">springmvc接受原生api</a><br/>
<a href="/noapi">springmvc不用原生api传参</a><br/>
<a href="/mav">方法返回参数是modelandview</a><br/>
<h1>测试modelattribute属性</h1>
<form action="/ma" method="post">
    书名：西游记
    价格：<input type="text" name="price">
    作者名字：<input type="text" name="author.name">
    作者年龄：<input type="text" name="author.age">
    <input type="submit" value="修改图书"/>
</form><br/>
<h1>测试前缀</h1>
<a href="/qian1">传统方法,还是拼串</a>
<a href="/qian2">forward</a>
<a href="/qian3">forward转发到请求</a>
<a href="/qian4">redirect重定向</a>
<a href="/qian5">redirect重定向到请求</a>
</body>
</body>
</html>
