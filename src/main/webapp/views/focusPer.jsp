
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>我的关注</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/table.css" rel="stylesheet">

</head>
<body>
<div v-cloak id="app">
    <Row>
        <i-col span="12">
            <i-input search placeholder="输入用户名" v-model="username" />
        </i-col>
        <i-col span="11" offset="1">
            <i-button type="primary" icon="ios-search" @click="turnToAddPage()">添加关注</i-button>
        </i-col>
    </Row>
    <i-table @on-row-dblclick="clickRow" stript :columns="columnFollow" :data="dataFollow"></i-table>
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
            columnFollow:[
                {
                    title:'用户ID',
                    key:'userId'
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
                {
                    title: '删除',
                    key: 'deleteFollow',
                    width: 80,
                    align: 'center',
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'error',
                                    size: 'small'
                                },
                                on: {
                                    click: () => {
                                    var followId = app.dataFollow[params.index].followId;
                                        app.dataFollow.splice(params.index,1);
                                        console.log(followId);
                                        app.$Message.success("删除成功");
                                        ajaxGet("/info_system/deleteFollow?followId="+followId,function() {
                                        },null,false);
                                    }
                                }
                            }, '删除'),
                        ]);
                    }
                }
            ],
            dataFollow:[]
        },
        methods: {
            clickRow: function(row) {
                console.log(row);
                turnToDetailUser(row);
            }
        }
    })

    $(document).ready(function () {
        ajaxGet("/info_system/getFollowList",function(res) {
            if(res.code=="success") {
                app.dataFollow=res.data;
            }
            console.log(app.dataFollow);
        },null,false);
    })

    function turnToAddPage() {
        console.log("turnToAddPage");
        console.log(app.username);
        parent.app.page="<%=basePath%>/info_system/addFollow?username="+app.username;
    }

    function turnToDetail(item) {
        parent.app.title="任务详细";
        parent.app.page="<%=basePath%>/info_system/blogDetail?blogId="+item.blogId;
    }

    function turnToDetailUser(item) {
        parent.app.title="他/她的主页";
        parent.app.page="<%=basePath%>/info_system/otherBlogs?userId="+item.userId;
    }

</script>
</body>
</html>
