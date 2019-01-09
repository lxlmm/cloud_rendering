
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/table.css" rel="stylesheet">

</head>
<body>
<div v-cloak id="app">
    <%--<Row>--%>
        <%--<i-col span="12">--%>
            <%--<i-input search placeholder="输入用户名" v-model="username" />--%>
        <%--</i-col>--%>
        <%--<i-col span="11" offset="1">--%>
            <%--<i-button type="primary" icon="ios-search" @click="turnToAddPage()">查找</i-button>--%>
        <%--</i-col>--%>
    <%--</Row>--%>
    <i-table @on-row-dblclick="clickRow" stript :columns="columnUser" :data="dataUser"></i-table>
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
            columnUser:[
                {
                    title:'用户ID',
                    key:'id'
                },
                {
                    title:'用户名',
                    key:'username'
                },
                {
                    title:'渲染数',
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
                    title: '操作权限',
                    key: 'deleteFlag',
                    align: 'center',
                    render: (h, params) => {
                        return h('div', [
                            h('i-switch', {
                                props: {
                                    size: 'large',
                                    value: params.row.deleteFlag,
                                    'true-value':1,
                                    'false-value':0
                                },
                                on: {
                                    'on-change': (value) => {
                                        params.row.deleteFlag=value;
                                        console.log(params.row.deleteFlag);
                                        if(params.row.deleteFlag==1)
                                            app.$Message.success("已恢复");
                                        else
                                            app.$Message.success("已删除");
                                        var userId = app.dataUser[params.index].id;
                                        console.log(userId);
                                        ajaxGet("/info_system/changeUser?userId="+userId+"&flag="+params.row.deleteFlag,function() {
                                        },null,false);
                                    }
                                }
                            }, [
                                h('span',{
                                    slot:'open',
                                    domProps: {
                                        innerHTML:'正常'
                                    }
                                }),
                                h('span',{
                                    slot:'close',
                                    domProps: {
                                        innerHTML:'拉黑'
                                    }
                                })
                            ]),
                        ]);
                    }
                }
            ],
            dataUser:[]
        },
        methods: {
            clickRow: function(row) {
                console.log(row);
                turnToDetailUser(row);
            }
        }
    })

    $(document).ready(function () {
        ajaxGet("/info_system/getUserList",function(res) {
            if(res.code=="success") {
                app.dataUser=res.data;
            }
            console.log(app.dataUser);
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
        parent.app.page="<%=basePath%>/info_system/otherBlogs?userId="+item.id;
    }

</script>
</body>
</html>
