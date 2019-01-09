
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>我的评论</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/table.css" rel="stylesheet">

</head>
<body>
<div id="app" v-cloak>

    <table class="table table-hover table-bordered table-condensed" style="margin-top: 20px;" >
        <!--<table id="contentTable" class="table table-striped table-bordered table-condensed">-->
        <thead>
        <tr style="font-size: 15px;">
            <%--<th style="width: 50px;">--%>
            <%--<Checkbox @on-change="checkAll()"  v-model="viewModel.allChecked" style="margin-left: 8px;">--%>
            <%--</Checkbox>--%>
            <%--</th>--%>
                <th style="text-align: center">评论渲染主题</th>
            <th style="text-align: center">评论内容</th>
            <th style="text-align: center">时间</th>
            <th style="text-align: center">点赞数</th>
            <th style="text-align: center">显示状态</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in commentList">
            <%--<td>--%>
            <%--<Checkbox v-model="item.checked" style="margin-left: 8px;" :key="item.id"></Checkbox>--%>
            <%--</td>--%>
                <td style="text-align: center">{{item.blog.blogContent}}（<a @click="turnToDetail(item.blog)"><span>查看渲染任务详情</span></a>）</td>

            <td style="text-align: center">{{item.commentContent}}</td>
            <td style="text-align: center">{{item.commentTime}}</td>
            <td style="text-align: center">{{item.commentCount}}</td>
            <td style="text-align: center"><i-switch v-model="item.deleteFlag" @on-change="changeStatus(item.deleteFlag,item,index)"></i-switch></td>
        </tr>
        </tbody>
    </table>
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
            commentList:[]
        }
    });

    $(document).ready(function(){
       var url="<%=basePath%>/info_system/getMyComments";
       ajaxGet(url,function(res){
           if(res.code=="success"){
               app.commentList=res.data;
               for(var i=0;i<res.data.length;i++){
                   app.commentList[i].commentTime=getTime(app.commentList[i].commentTime);
                   if(app.commentList[i].deleteFlag==1){
                       app.commentList[i].deleteFlag=true;
                   }else{
                       app.commentList[i].deleteFlag=false;
                   }
               }
               console.log(app.commentList);
           }
       },null,false);
    });

    function changeStatus(deleteFlag,item,index){
        var data={
            deleteFlag:deleteFlag,
            commentId:item.commentId
        };
        var url="<%=basePath%>/info_system/updateCommentDeleteFlag";
        ajaxPost(url,data,function(res){
            if(res.code=="success"){
                app.commentList[index].deleteFlag=res.data;
            }
        },null,false)
    }

    function turnToDetail(item){
        console.log("进入方法");
        parent.app.title="任务详细";
        parent.app.page="<%=basePath%>/info_system/blogDetail?blogId="+item.blogId;
    }
</script>
</body>
</html>
