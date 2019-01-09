
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
<head>
    <title>创建任务</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/table.css" rel="stylesheet">

</head>
<body>
<div v-cloak id="app">
    <Row style="margin-top:30px">

            <i-form :model="formContent" :rules="validate" ref="form">
                <form-item label="主题:" prop="blogTitle">
                    <i-input v-model="formContent.blogTitle" placeholder="输入主题" style="width:30%"></i-input>
                </form-item>
                <form-item label="描述:" prop="blogContent">
                    <i-input v-model="formContent.blogContent" type="textarea" :autosize="{minRows: 7,maxRows: 10}" placeholder="输入内容" style="width:80%"></i-input>
                </form-item>
                <%--<form-item label="要@的人：" prop="informPerson">--%>
                    <%--<i-select v-model="selectUser" multiple style="width:260px" placeholder="请选择">--%>
                        <%--<i-option v-for="item in userList" :value="item.id" :key="item.id">{{ item.username}}</i-option>--%>
                    <%--</i-select>--%>
                <%--</form-item>--%>
                <form-item label="模型：" prop="goodsPic">
                    <%--<i-Input v-model="goods.goodsPic" type="text" maxlength="255" ></i-Input>--%>
                    <div style="width: 350px;height:200px;">
                        <Upload
                                multiple="1"
                                type="drag"
                                :before-upload="handleBeforeUploadPic"
                                action="//jsonplaceholder.typicode.com/posts/">
                            <div style="padding: 20px 0">
                                <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                                <p>点击或将文件拖拽到这里上传</p>
                            </div>
                            <p style="color: red">PS:目前只支持.Giraffe文件的渲染</p>
                        </Upload>
                        <div v-if="hasPic">上传成功！</div>
                    </div>
                </form-item>
                <form-item>
                    <Row>
                        <i-col span="5" offset="14">
                            <i-button style="margin-left: 8px">Cancel</i-button>

                        </i-col>
                        <i-col span="5">
                            <i-button type="primary" @click="submit">Submit</i-button>
                        </i-col>
                    </Row>

                </form-item>

            </i-form>


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
            formContent:{
                blogTitle:"",
                blogContent:""
            },
            photo:{
                src:"",
                file:null,
            },
            hasPic:false,
            //所有的用户列表
            userList:[],
            //选择的要@的人
            selectUser:[],
            // 验证规则
            validate: {
                blogTitle:[{required: true, message: '标题不能为空', trigger: 'blur' }],
                blogContent:[{required: true, message: '内容不能为空', trigger: 'blur' }],
            }
        }
    });


    $(document).ready(function(){
        var url="<%=basePath%>/info_system/getAllUserList";
        ajaxGet(url,function(res){
            if(res.code=="success"){
                 app.userList=res.data;
            }
        },null,false);
    });

    //此函数放在app内部无作用，原因不明
    function handleBeforeUploadPic(file)
    {
        var reader = new FileReader();
        reader.onload = function (e) {
            app.photo.src = e.target.result;
            app.photo.file = file;
        };
        reader.readAsDataURL(file);
        app.hasPic=true;
        return true;
    }


    // 提交表单
    function submit() {
        console.log("进入方法");
        console.log(app.selectUser);
        app.$refs["form"].validate(function (valid) {
            if (valid)
            {
                var formdata=new FormData();
                if(app.photo.file!=null)
                {
                    formdata.append('uploadFile', app.photo.file);
                }
                formdata.append("blog",JSON.stringify(app.formContent));
                formdata.append("selectUser",JSON.stringify(app.selectUser));
                $.ajax({
                    type : 'post',
                    url : "/info_system/addBlog",
                    data : formdata,
                    cache : false,
                    processData : false, // 不处理发送的数据，因为data值是Formdata对象，不需要对数据做处理
                    contentType : false, // 不设置Content-type请求头
                    success : function(rep)
                    {
                        if(rep.code=="success")
                        {
                            app.formContent.blogTitle="";
                            app.formContent.blogContent="";
                           app.$Message.success(rep.message);
                        }
                        else //显示错误信息
                        {
                            app.$Message.error(rep.message);
                        }
                    },
                    error : function()
                    {
                        app.$Message.error("请求出错");
                    }
                });
            }
            else
            {

                app.$Message.error('请正确填写信息!');
            }
        });

    }
</script>
</body>

</html>
