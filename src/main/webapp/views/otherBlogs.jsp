
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>其他用户主页</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        .goods-item-img{
            width:85%;
            height:100px
        }

        a{text-decoration:none;color:#666;display:inline-block;}
        a:hover{color:#666;text-decoration:none;}
    </style>
</head>
<body>

<div id="app" v-cloak>
    <Row>
            <i-col span="20" offset="2">
                <Row>
                    <i-col span="2">
                        <span style="font-size: 17pt"> {{user.username}}</span>
                    </i-col>
                    <i-col span="2" offset="20" v-if="mainId!=userId&&hasFocus==false">
                        <i-button style="width:100%" @click="addFocus()">关注</i-button>
                    </i-col>
                    <i-col span="2" offset="20" v-if="hasFocus">
                        <i-button style="width:100%" disabled>已关注</i-button>
                    </i-col>
                </Row>
                <Row style="margin-top: 10px">
                    <i-col span="3">
                        <span style="font-size: 12pt">粉丝数：{{user.fansNum}}</span>
                    </i-col>
                    <i-col span="3">
                        <span style="font-size: 12pt">任务数：{{user.blogNum}}</span>
                    </i-col>
                </Row>
                <hr/>
                <Span style="font-size:13pt">全部任务</Span>
                <hr/>
                <div style="width:100%" v-for="item in blogList">
                    <div>
                        <Row>
                            <i-col span="4" v-if="item.blogPic!=null">
                                <div style="border:solid 0.1pt;padding:10px;border-color: #E5DDDB">
                                    <a  @click="turnToDetail(item)">
                                        <img :src="'<%=basePath%>'+item.blogPic" class="goods-item-img">
                                    </a>

                                </div>
                            </i-col>
                            <i-col span="17" offset="1">
                                <a @click="turnToDetail(item)">
                                    <div><span style="font-size: 15pt">{{item.blogTitle}}</span></div>
                                    <div style="height:80px">
                                        <p>{{item.blogContent}}</p>
                                    </div>
                                </a>
                                <div>
                                    <Row>
                                        <i-col span="1">
                                            <span style="font-size: 10pt">{{item.user.username}}</span>
                                        </i-col>
                                        <i-col span="5" offset="1">
                                            <span style="font-size: 10pt">{{item.blogTime}}</span>
                                        </i-col>
                                        <i-col span="1" offset="10" v-if="item.hasLike">
                                            <a @click="doLike(item)"><Icon type="md-thumbs-up" color="#2d8cf0"/></a>

                                        </i-col>
                                        <i-col span="1" offset="10" v-else>
                                            <a @click="doLike(item)"><Icon type="md-thumbs-up" color="#A3A2A1"/></a>
                                        </i-col>
                                        <i-col span="1">
                                            <span>{{item.likeCount}}&nbsp;&nbsp; |</span>
                                        </i-col>

                                        <i-col span="1" v-if="item.hasComment">
                                            &nbsp;
                                            <a><Icon type="md-chatboxes" color="#2d8cf0"/></a>
                                        </i-col>
                                        <i-col span="1" v-else>
                                            &nbsp;
                                            <a><Icon type="md-chatboxes" color="#A3A2A1"/></a>
                                        </i-col>

                                        <i-col span="1">
                                            <span>{{item.commentCount}}</span>
                                        </i-col>
                                    </Row>
                                </div>

                            </i-col>
                        </Row>
                        <hr/>
                    </div>
                </div>
            </i-col>
        </Row>
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
            mainId:'',
            userId:'${userId}',
            hasFocus:false,
            user:{
                id:"",
                username:"",
                fansNum:"",
                blogNum:"",
                userPic:""
            },
            blogList:[]
        }
    });

    $(document).ready(function(){
       var url="<%=basePath%>/info_system/getOtherBlogs?userId="+app.userId;
       console.log(url);
        ajaxGet(url,function(res){
            if(res.code=="success"){
                app.user=res.data.user;
                app.mainId=res.data.mainId;
                app.blogList=res.data.blogList;
                app.hasFocus=res.data.hasFocus;
                for(var i=0;i<app.blogList.length;i++){
                    //将时间戳转换为日期
                    app.blogList[i].blogTime=getTime(app.blogList[i].blogTime);
                }

                console.log(app.hasFocus);
                console.log(res.data.hasFocus);
            }
        },null,false)
    });

    //点赞
    function doLike(item){
        var data={
            blogId:item.blogId
        };

        console.log(item);
        if(item.hasLike==true){
            item.hasLike=false;
            item.likeCount-=1;
            ajaxPost("/info_system/deleteBlogLike",data,function(res){

            },null,false);
        }else{
            item.hasLike=true;
            item.likeCount+=1;
            ajaxPost("/info_system/addBlogLike",data,function(res){

            },null,false);
        }
    }


    //跳转到任务详细页面
    function turnToDetail(item){
        console.log("进入方法");
        parent.app.title="任务详细";
        parent.app.page="<%=basePath%>/info_system/blogDetail?blogId="+item.blogId;
    }

    //添加关注
    function addFocus(){
        var url="<%=basePath%>/info_system/addFocus";
        var data={
            followerId:app.mainId,
            mainId:app.userId
        };
        ajaxPost(url,data,function(res){
          if(res.code=="success"){
              app.hasFocus=true;
              app.user.fansNum+=1;
          }
        },null,false);
    }
</script>
</body>
</html>
