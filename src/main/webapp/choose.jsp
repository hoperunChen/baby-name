<%@ page import="cn.uctimes.luckyrui.babyname.dto.UserDTO" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>宝宝选名</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/toastr.css">
    <style>


        .content > .container > .row > .col, .content > .container > .row > [class^=col-] {
            padding-top: .75rem;
            padding-bottom: .75rem;
        }

        .custom-header {
            margin: 13px 0 0 0;
        }

        .to-login-btn {
            padding: 0;
        }

        .header-title {
            /*display: inline;*/
            text-align: center;
        }

        .totle {
            text-align: right;
        }

        .show-name {
            text-align: center;
            font-size: -webkit-xxx-large;
            font-weight: bold;
            margin: 40% 0;
        }

        .descr {
            font-size: 45%;
        }

        .choose {
            text-align: center;
            bottom: 25%;
            margin-bottom: 0;
            border-width: 1px 0 0;
            position: fixed;
            right: 0;
            left: 0;
            z-index: 1030;
        }

        .footer [class^=col-] {
            padding-top: .75rem;
            padding-bottom: .75rem;
            text-align: center;
            background-color: rgba(86, 61, 124, .15);
            border-top: 1px solid rgba(86, 61, 124, .2);
        }

    </style>
</head>
<body>
<div class="page-header custom-header">
    <div class="container">
        <div class="row">
            <div class="col-xs-3">
                <button type="button" class="btn btn-link to-login-btn" onclick="window.location.href='login'">重新登录
                </button>
            </div>
            <div class="col-xs-6 header-title">
                <b>用户:${user.phone}</b>
            </div>
            <div class="col-xs-3">

            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="container">
        <!-- Content here -->
        <div class="row">
            <div class="col-xs-6">
                no:<span id="nameNo"></span>
            </div>
            <div class="col-xs-6 totle">
                totle:${maxId}
            </div>
        </div>
        <div class="row">
            <div class="col show-name">
                <span id="babyName"></span>
                <span id="descr" class="label label-info descr">Info</span>
            </div>
        </div>
        <div class="row choose">
            <div class="col-xs-6">
                <button type="button" class="btn btn-danger btn-lg" onclick="dislikeName()">不喜欢</button>
            </div>
            <div class="col-xs-6">
                <button type="button" class="btn btn-success btn-lg" onclick="likeName()">喜欢</button>
            </div>
        </div>
    </div>
</div>
<footer class="footer navbar-fixed-bottom ">
    <div class="container">
        <div class="row">
            <div class="col-xs-6" style="color: #00b5fe;">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                宝宝选名
            </div>
            <div class="col-xs-6" onclick="toastr.warning('开发中')">
                <span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
                我已选的
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="/toastr.min.js"></script>
<script>


    var currentNameId;

    $(document).ready(function () {
        var userId = '<%=((UserDTO)request.getAttribute("user")).getId()%>';
        if (userId == 'null' || userId == 'undefined' || userId == '') { // 入口是审核管理
            window.location = "/pages/login";
        }
        nextName();
    });

    function nextName() {

        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            data: {},
            url: '/choose/next_name',
            success: function (data) {
                if (data.success) {
                    var bizData = data.data;
                    $("#nameNo").text(bizData.id);
                    $("#babyName").text(bizData.name);
                    $("#descr").text(bizData.descr);
                    currentNameId = bizData.id;
                } else if (data.code == -1100) {
                    $("#nameNo").text("NAN");
                    $("#babyName").text("已经全部看完了!");
                    $(".choose button").hide();
                } else {
                    toastr.error(data.message);
                }
            }
        });
    }

    function likeName() {
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            data: {'nameId': currentNameId},
            url: '/choose/like',
            success: function (data) {
                if (data.success) {
                    nextName();
                } else if (data.code == -1200) {
                    toastr.warning(data.message);
                    nextName();
                } else {
                    toastr.error(data.message);
                }
            }
        });
    }

    function dislikeName() {
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            data: {'nameId': currentNameId},
            url: '/choose/dislike',
            success: function (data) {
                if (data.success) {
                    toastr.success("不喜欢成功,显示下一个!");
                    nextName();
                } else if (data.code == -1200) {
                    toastr.warning(data.message);
                    nextName();
                } else {
                    toastr.error(data.message);
                }
            }
        });
    }


</script>
</body>
</html>