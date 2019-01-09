<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>我的消息</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div v-cloak id="app">
    <%--<i-table @on-row-dblclick="clickRow" stript :columns="columnMessage" :data="dataMessage"></i-table>--%>
        <table class="table table-hover">
            <tr>
                <th>序号</th>
                <th>消息内容</th>
            </tr>
            <tr v-for="(item, index) in dataMessage">
                <td v-if="item.messageType==1">{{index+1}}</td>
                <td v-if="item.messageType==1">管理员限制了您的权限</td>
                <td v-if="item.messageType==2">{{index+1}}</td>
                <td v-if="item.messageType==2">管理员恢复了您的权限</td>
                <td v-if="item.messageType==3">{{index+1}}</td>
                <td v-if="item.messageType==3">用户<a @click="turnToDetailUser(item)" style="">@{{item.senderName}}</a>在渲染任务<a @click="turnToDetail(item)" style="">@{{item.blogName}}</a>中@了您</td>
                <td v-if="item.messageType==4">{{index+1}}</td>
                <td v-if="item.messageType==4">管理员删除了您的渲染任务<a @click="turnToDetail(item)" style="">@{{item.blogName}}</a></td>
                <td v-if="item.messageType==5">{{index+1}}</td>
                <td v-if="item.messageType==5">管理员恢复了您的渲染任务<a @click="turnToDetail(item)" style="">@{{item.blogName}}</a></td>
            </tr>
            <%--<c:forEach var="msg" items="${app.}">--%>
                <%--<c:if test="${msg.message_type==1}">--%>
                    <%--<tr>--%>
                        <%--<th>您已被<a @click="turnToDetailUser(msg.senderId)" style="">@{{msg.senderName}}</a>限制权限</th>--%>
                    <%--</tr>--%>
                <%--</c:if>--%>

            <%--</c:forEach>--%>
        </table>
</div>
<script src="../js/ajax.js"></script>
<script src="../js/jquery-2.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/vue.min.js"></script>
<script src="../js/iview.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/ajax.js"></script>
<script src="../js/jquery-2.0.0.min.js"></script>
<script src="../js/homepage.js"></script>
<script type="text/javascript">
    var app = new Vue({
        el: "#app",
        data: {
            username:'',
            columnMessage:[
                {
                title:'发送者',
                key:'senderName'
                },
                {
                    title:'内容',
                    key:'messageContent'
                }
            ],
            dataMessage:[]
        },
        // methods: {
        //     clickRow: function(row) {
        //         console.log(row);
        //         turnToDetailUser(row);
        //     }
        // }
    })

    $(document).ready(function () {
        ajaxGet("/info_system/getMessageList",function(res) {
            if(res.code=="success") {
                app.dataMessage=res.data;
            }
            console.log(app.dataMessage);
        },null,false);
    })

    function turnToDetail(item) {
        parent.app.title="任务详细";
        parent.app.page="<%=basePath%>/info_system/blogDetail?blogId="+item.blogId;
    }

    function turnToDetailUser(item) {
        parent.app.title="他/她的主页";
        parent.app.page="<%=basePath%>/info_system/otherBlogs?userId="+item.senderId;
    }

</script>
</body>
</html>
