<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <title>MICBLOG</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        .goods-item-img {
            width: 70%;
            height: 70%;
            margin-top: 10%
        }

        a {
            text-decoration: none;
            color: #666;
            display: inline-block;
        }

        a:hover {
            text-decoration: none;
        }
    </style>
</head>
<body>
<div v-cloak id="app" style="height:100%">

    <div style="padding-top:40px">
        <div style="width:100%;height:150px" v-for="item in allBlogList">
            <div>
                <Row>
                    <i-col span="4" v-if="item.blogPreviewPic!=null">
                        <div style="border:solid 0.1pt;border-color: #E5DDDB;text-align: center;height:70%">
                            <img :src="'<%=basePath%>'+item.blogPreviewPic" class="goods-item-img">

                        </div>
                    </i-col>
                    <i-col span="17" offset="1" style="margin-top:10px">
                        <div>
                            <Row>
                                <i-col span="5">
                                    <div><a @click="turnToDetail(item)"><span
                                            style="font-size: 15pt">{{item.blogTitle}}</span></a></div>
                                    <div><a @click="turnToDetail(item)"><span>{{item.blogContent}}</span></a></div>

                                </i-col>
                                <i-col span="1" offset="3">
                                    <form method="post">
                                        <input style="opacity: 0" type="text" v-model="item.blogId" name="blogId">
                                        <input type="submit" value="渲染" @click="render(item)">  
                                    </form>
                                      
                                </i-col>
                                <i-col span="1" offset="3">
                                    <form method="post">
                                        <input style="opacity: 0" type="text" v-model="item.blogId" name="blogId">
                                        <input type="submit" value="下载">  
                                    </form>
                                      
                                </i-col>
                            </Row>
                        </div>

                        <div>
                            <Row>
                                <%--<i-col span="1">--%>
                                <%--<a @click="turnToDetailUser(item.user.id)"><Avatar icon="ios-person" size="small" :src="'<%=basePath%>'+item.user.userPic" /></a>--%>
                                <%--</i-col>--%>
                                <i-col span="1">
                                    <span style="font-size: 10pt">{{item.user.username}}</span>
                                </i-col>
                                <i-col span="5" offset="1">
                                    <span style="font-size: 10pt">{{item.blogTime}}</span>
                                </i-col>

                            </Row>
                        </div>


                    </i-col>
                </Row>
                <hr/>
            </div>
        </div>
    </div>


</div>

<script src="../js/ajax.js"></script>
<script src="../js/jquery-2.1.1.min.js"></script>
<script src="../js/jquery-2.0.0.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/vue.min.js"></script>
<script src="../js/iview.min.js"></script>
<script src="../js/homepage.js"></script>
<script type="text/javascript">
    var app = new Vue({
        el: "#app",
        data: {
            value1: 0,
            allBlogList: [],
            sortWay: "time",
            timeBlogList: [],
            hotBlogList: [],
            hottestList: [],
        }
    });


    $(document).ready(function () {
        ajaxGet("/info_system/getMyBlogList", function (res) {
            if (res.code == "success") {
                app.timeBlogList = res.data.timeBlogList;
                app.hotBlogList = res.data.hotBlogList;
                for (var i = 0; i < app.timeBlogList.length; i++) {
                    //将时间戳转换为日期
                    app.timeBlogList[i].blogTime = getTime(app.timeBlogList[i].blogTime);
                    app.hotBlogList[i].blogTime = getTime(app.hotBlogList[i].blogTime);
                }
                if (app.hotBlogList.length > 3) {
                    app.hottestList.push(app.hotBlogList[0]);
                    app.hottestList.push(app.hotBlogList[1]);
                    app.hottestList.push(app.hotBlogList[2]);
                } else {
                    for (var i = 0; i < app.hotBlogList.length; i++) {
                        app.hottestList.push(app.hotBlogList[i]);
                    }
                }

                app.allBlogList = app.timeBlogList;
            }
        }, null, false);
    });

    // 提交表单
    function render(item) {
        console.log("进入方法");
        console.log(item);
        var formdata = new FormData();
        formdata.append("blogId", item.blogId);
        $.ajax({
            type: 'post',
            url: "/info_system/render",
            data: formdata,
            cache: false,
            processData: false, // 不处理发送的数据，因为data值是Formdata对象，不需要对数据做处理
            contentType: false, // 不设置Content-type请求头
            success: function (rep) {
                if (rep.code == "success") {
                    alert(rep.message);
                } else {
                    alert(rep.message);
                }
            },
            error: function () {
                alert("请求出错");
            }
        });


    }

    //点赞
    function doLike(item) {
        var data = {
            blogId: item.blogId
        };
        console.log(item);
        if (item.hasLike == true) {
            item.hasLike = false;
            item.likeCount -= 1;
            ajaxPost("/info_system/deleteBlogLike", data, function (res) {

            }, null, false);
        } else {
            item.hasLike = true;
            item.likeCount += 1;
            ajaxPost("/info_system/addBlogLike", data, function (res) {

            }, null, false);
        }
    }

    function downloadFile(item) {
        console.log("downloadFile");
        console.log(item.blogId);
        ajaxPost("/info_system/down?blogId=" + item.blogId, function (res) {
            window.open()
        }, null, false);
    }

    //跳转到任务详细页面
    function turnToDetail(item) {
        parent.app.title = "任务详细";
        parent.app.page = "<%=basePath%>/info_system/blogDetail?blogId=" + item.blogId;
    }

    //跳转到用户详细页面
    function turnToDetailUser(userId) {
        console.log("进入方法");
        parent.app.title = "他/她的主页";
        parent.app.page = "<%=basePath%>/info_system/otherBlogs?userId=" + userId;
    }

    function sortWayChange(way) {

        if (way == "time") {
            //按时间排序
            app.allBlogList = app.timeBlogList;
        } else {
            //按热度排序
            app.allBlogList = app.hotBlogList;

        }

    }

</script>
</body>
</html>
