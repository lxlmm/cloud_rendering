
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>添加关注</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/table.css" rel="stylesheet">

</head>
<body>
<div v-cloak id="app">

    <i-table @on-row-dblclick="clickRow" stript :columns="columnUnFollow" :data="dataUnFollow"></i-table>
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
            columnUnFollow:[
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
                    title: '关注',
                    key: 'addFollow',
                    width: 80,
                    align: 'center',
                    render: (h, params) => {
                    return h('div', [
                        h('Button', {
                            props: {
                                type: 'primary',
                                size: 'small'
                            },
                            on: {
                                click: () => {
                                    var followId = app.dataUnFollow[params.index].userId;
                                    app.dataUnFollow.splice(params.index,1);
                                    app.$Message.success("添加成功");
                                    console.log(followId);
                                    ajaxGet("/info_system/addFollowUser?followId="+followId,function(res) {
                                        if(res.code=="success") {
                                            app.dataUnFollow=res.data;
                                        }
                                        console.log(app.dataUnFollow);
                                    },null,false);
                                }
                            }
                    }, '关注'),
                ]);
            }
        }
    ],
    dataUnFollow:[]
    },
        methods: {
            clickRow: function(row) {
                console.log(row);
                turnToDetailUser(row);
            }
        }
    })

    var username = '${userName}';

    $(document).ready(function () {
        ajaxGet("/info_system/getUnFollowList?username="+username,function(res) {
            if(res.code=="success") {
                app.dataUnFollow=res.data;
            }
            console.log(app.dataUnFollow);
        },null,false);
    })

    function turnPage(item) {
        console.log("换页面");
        parent.app.page="<%=basePath%>/info_system/home"

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
