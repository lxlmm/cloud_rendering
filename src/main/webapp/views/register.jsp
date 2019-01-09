
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet"  href="../css/login.css">

    <script src="../js/jquery-2.1.1.min.js"></script>
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>云渲染平台·注册</h1>

            <form class="form" action="/register" method="post">
                <input type="text" placeholder="用户名" name="username">
                <input type="password" placeholder="密码(至少6位)" name="password">
                <input type="password" placeholder="验证密码" name="repassword">
                <input type="text" placeholder="姓名" name="name">
                <div id="choose-sex">
                    <input class="sel" type="radio" name="sex" value="男" checked><span>先生</span>
                    <input class="sel" type="radio" name="sex" value="女"><span>女士</span>
                </div>
                <button type="submit" id="confirm-button" onclick="">确定</button><br>
                <button id="return-button" style="padding: 0;"><a href="/login" style="display: block; width: 100%; padding: 10px 15px;">返回</a></button>

            </form>
            <div><br>${hint}</div>
        </div>

        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>

<script>
    function func(event) {
        event.preventDefault();
        document.getElementsByTagName('form').fadeOut(500);
        document.getElementsByClassName('.wrapper').addClass('form-success');
    }
</script>

</body>
</html>
