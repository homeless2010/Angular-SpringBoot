<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
    src="<%=basePath%>/static/js/jquery.min.js"></script>
<title>登录</title>
</head>
<body>
    错误信息：
    <h4 id="erro"></h4>
    <form>
        <p>
            账号：<input type="text" name="username" id="username" value="admin" />
        </p>
        <p>
            密码：<input type="text" name="password" id="password" value="123" />
        </p>
        <p>
            <input type="button" id="ajaxLogin" value="登录" />
        </p>
    </form>
</body>
<script>
    var username = $("#username").val();
    var password = $("#password").val();
    $("#ajaxLogin").click(function() {
        $.post("/web/user/ajaxLogin", {
            "username" : username,
            "password" : password
        }, function(result) {
            if (result.status == 200) {
                location.href = "/index";
            } else {
                $("#erro").html(result.message);
            }
        });
    });
</script>
</html>