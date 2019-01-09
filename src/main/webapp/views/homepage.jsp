
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <title>云渲染平台</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/table.css" rel="stylesheet">

    <style>
        .iframeLayout{
            height:calc(100% - 92px);
        }
    </style>
</head>
<body>
<div id="app" style="height: 100%">
    <layout style="height: 100%">
        <i-header>
            <i-menu mode="horizontal" theme="dark" >
                <row>
                    <i-col span="10">
                        <div class="layout-logo">云 渲 染 平 台</div>
                    </i-col>
                    <i-col offset="12" span="2">
                        <div class="layout-nav">
                            <menu-item>
                                <a href="/logout"><i-button type="dashed"><icon type="md-log-out"></icon> 退出账号</i-button></a>
                            </menu-item>
                        </div>
                    </i-col>
                </row>
            </i-menu>
        </i-header>
        <layout class="iframeLayout">
            <sider hide-trigger style="background: #fff">
                <i-menu active-name="1-2" theme="light" width="auto" :open-names="['1']" @on-select="turnPage">
                    <div id="user" >
                        <row>
                            <i-col offset="3">
                                <div><p>Hello ${username} !</p></div>
                            </i-col>
                        </row>
                    </div>
                    <menu-item name="home">
                        <Icon type="md-home" ></Icon>
                        任务广场
                    </menu-item>
                    <menu-item name="sendBlog">
                        <Icon type="ios-create" ></Icon>
                        创建任务
                    </menu-item>
                    <menu-item name="myBlog">
                        <Icon type="ios-bookmarks" ></Icon>
                        我的任务
                    </menu-item>
                    <menu-item name="myComment">
                        <Icon type="ios-document" ></Icon>
                        我的评论
                    </menu-item>
                    <menu-item name="focusPer">
                        <Icon type="ios-disc" ></Icon>
                        关注的人
                    </menu-item>
                    <menu-item name="focusMe">
                        <Icon type="ios-contacts" ></Icon>
                        关注我的人
                    </menu-item>
                    <menu-item name="message">
                        <Icon type="md-mail" ></Icon>
                        消息
                    </menu-item>
                    <%--管理员的权限--%>
                    <menu-item name="blacklist" v-if="user.adminFlag==1">
                        <Icon type="ios-clipboard" ></Icon>
                        黑名单
                    </menu-item>
                    <menu-item name="blackBlog" v-if="user.adminFlag==1">
                        <Icon type="ios-trash" ></Icon>
                        管理渲染任务
                    </menu-item>
                </i-menu>
            </sider>
            <layout  style="height: 100%">
                <p style="font-size: 16pt; font-weight: bold; margin-top: 20px;margin-left:5%">{{title}}</p>

                <i-content style="height: calc(100% - 152px);width:90%;margin-left:5%">
                    <iframe :src="page" frameborder="no" width="100%" height="100%">

                    </iframe>
                </i-content>
                <i-footer style="height: 62px">
                    <p style="text-align: center">Copyright &copy; 2018-2019 CloudPlayer. All Rights Reserves</p>
                </i-footer>
            </layout>


        </layout>

        <Modal :title="importModel.title" width="600" :mask-closable="false" v-model="importModel.show"
               ok-text="ok" cancel-text="cancel" :loading="importModel.loading" @on-ok="edit_ok('importFrame')">
            <iframe id="importFrame" width="100%" frameborder="0" :src="importModel.url"></iframe>
        </Modal>

    </layout>

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
        data:{
            page:"<%=basePath%>/info_system/home",
            title:"我的任务",
            importModel:{
                title:"",
                url:"",
                loading:true,
                show:false
            },
            user:{
                userName:"",
                userPic:"",
                adminFlag:'${adminFlag}'
            }

        }


    });
    $(document).ready(function(){

        app.user.userPic='${userPic}';
        console.log(app.user.userPic);
    });



    function turnPage(name){
        if(name=="home"){
            //主页面
            app.title='任务广场';
            app.page="<%=basePath%>/info_system/home"
        }else if(name=="sendBlog"){
            //跳转到发任务页面
            app.title="创建任务";
            app.page="<%=basePath%>/info_system/sendBlog"
        }else if(name=="myBlog"){
            app.title="我的任务";
            app.page="<%=basePath%>/info_system/myBlogs"
        }else if(name=="myComment"){
            app.title="我的评论";
            app.page="<%=basePath%>/info_system/myComment"
        }else if(name=="focusPer") {
            //跳转到我的关注页面
            app.title="我的关注";
            app.page="<%=basePath%>/info_system/focusPer"
        }else if(name=="focusMe") {
            //跳转到关注我的页面
            app.title="关注我的人";
            app.page="<%=basePath%>/info_system/focusMe"
        }else if(name=="blacklist") {
            //跳转到拉黑用户页面
            app.title="黑名单";
            app.page="<%=basePath%>/info_system/banUser"
        }else if(name=="blackBlog") {
            //跳转到已删任务页面
            app.title="管理任务";
            app.page="<%=basePath%>/info_system/deletedBlog"
        }else if(name=="message") {
            //跳转到消息页面
            app.title="消息";
            app.page="<%=basePath%>/info_system/messageInfo"
        }

    }

    //跳转到用户详细页面
    function turnToDetailUser(){
        console.log("进入方法");
        parent.app.title="他/她的主页";
        app.page="<%=basePath%>/info_system/otherBlogs?userId="+'${userId}';
    }

    function changeImg(){
        app.importModel={
            title:"",
            url:"",
            loading:true,
            show:false
        };

        app.importModel.title="更改头像";
        app.importModel.url="/info_system/imgImport";

        app.importModel.show=true;
    }

    function edit_ok(id) {
        document.getElementById(id).contentWindow.submit();
    }
</script>

</body>
</html>