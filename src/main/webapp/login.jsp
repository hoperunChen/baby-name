<%@ page import="cn.uctimes.luckyrui.babyname.dto.UserDTO" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>登录</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>


        .row > .col, .row > [class^=col-] {
            padding-top: .75rem;
            padding-bottom: .75rem;
            background-color: rgba(86, 61, 124, .15);
            border: 1px solid rgba(86, 61, 124, .2);
        }

        .login-text {
            text-align: center;
        }

        .content {
            margin-top: 60%;
        }

        .show-name {
            margin: 0 20px;
        }

        .show-name input, label {
            display: inline;
        }

        .show-name #phone {
            width: 80%;
        }

        .login-btn {
            text-align: center;
            margin-top: 46px;
        }

        .login-btn button {
            width: 80%;
            display: inline;
        }


    </style>
</head>
<body>
<div class="container">
    <!-- Content here -->
    <div class="row">
        <div class="col-xs-12 login-text">
            请先登录
        </div>
    </div>
    <% String errMsg = (String) request.getAttribute("errMsg"); %>
    <div class="row" style="height:45px">
        <div id="errorMsg" style="display: <%=null==errMsg || "".equals(errMsg) ? "none" : "block"%>;"
             class="alert alert-danger alert-dismissible" role="alert">
            <strong>Error!</strong>${errMsg}
        </div>
    </div>
    <div class="row content">
        <form id="loginForm" class="form-inline" action="do_login">
            <div class="col show-name">
                <div class="form-group">
                    <label for="phone">手机号:</label>
                    <input type="phone" class="form-control" id="phone" name="phone" placeholder="输入手机号">
                </div>
            </div>
            <div class="col login-btn">
                <button type="button" class="btn btn-success btn-block" onclick="doLogin()">登录</button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    function doLogin() {
        var form = $("#loginForm");
        form.submit();
    }
</script>
</body>
</html>