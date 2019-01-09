
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>我的粉丝</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/table.css" rel="stylesheet">

</head>
<body>
<div v-cloak id="app">
    <i-table @on-row-dblclick="clickRow" stript :columns="columnFans" :data="dataFans"></i-table>
</div>

<script src="../js/ajax.js"></script>
<script src="../js/jquery-2.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/vue.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/iview.min.js"></script>
<script type="text/javascript">
    var app = new Vue({
        el: "#app",
        data: {
            columnFans:[
                {
                    title:'用户ID',
                    key:'followerId'
                },
                {
                    title:'用户名',
                    key:'username'
                },
                {
                    title:'任务数',
                    key:'blogNum'
                },
                {
                    title:'ta的关注',
                    key:'followNum'
                },
                {
                    title:'ta的粉丝',
                    key:'fansNum'
                },
            ],
            dataFans:[]
        },
        methods: {
            clickRow: function(row) {
                console.log(row);
                turnToDetailUser(row);
            }
        }
    })

    $(document).ready(function () {
        ajaxGet("/info_system/getFansList",function(res) {
            if(res.code=="success") {
                app.dataFans=res.data;
            }
            console.log(app.dataFans);
        },null,false);
    })

    function turnToDetail(item) {
        parent.app.title="任务详细";
        parent.app.page="<%=basePath%>/info_system/blogDetail?blogId="+item.blogId;
    }

    function turnToDetailUser(item) {
        parent.app.title="他/她的主页";
        parent.app.page="<%=basePath%>/info_system/otherBlogs?userId="+item.followerId;
    }
</script>
</body>
</html>
