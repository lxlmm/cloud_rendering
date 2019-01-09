
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>导入头像照片</title>
    <link rel="stylesheet" type="text/css" href="//unpkg.com/iview/dist/styles/iview.css">
    <link rel="stylesheet" type="text/css" href="../css/layout.css">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div v-cloak id="app">
    <i-form ref="form" :model="user"  :label-width="100">
        <div style="padding:20px;">
            <Form-item label="导入图片：" prop="userImg">
                <%--<i-Input v-model="goods.goodsPic" type="text" maxlength="255" ></i-Input>--%>
                <div style="width: 130px;height: 150px;">
                    <Upload style="width: 100%;height: 100%;"
                            accept="image/*"
                            :before-upload="handleBeforeUploadPic"
                            :show-upload-list="false">
                        <i-Button  style="width: 100%;height: 100%;" v-if="!hasPic"><Icon type="ios-add" size="100" ></Icon></i-Button>
                        <img :src="photo.src" style="width: 100%;height: 100%;" v-else/>
                    </Upload>
                </div>
            </Form-item>
        </div>
    </i-form>
</div>
<script src="../js/ajax.js"></script>
<script src="../js/jquery-2.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/vue.min.js"></script>
<script src="../js/iview.min.js"></script>
<script type="text/javascript">
    var app=new Vue({
        el:"#app",

        data:{
            user:{
                userImg:null
            },

            photo:{
                src:"",
                file:null,
            },
            hasPic:false
        },


    });
    //此函数放在app内部无作用，原因不明
    function handleBeforeUploadPic(file)
    {
        if (file.size > 10485760) {
            app.$Message.warning("照片大小超过限制");
            return false;
        }
        if (file.type.indexOf("image") != 0) {
            app.$Message.warning("文件类型不支持");
            return false;
        }
        var reader = new FileReader();
        reader.onload = function (e) {
            app.photo.src = e.target.result;
            app.photo.file = file;
        };
        reader.readAsDataURL(file);
        app.hasPic=true;
        return false;
    }


    //提交表单

    function submit(){
        console.log("进入提交方法");
        app.$refs["form"].validate(function(valid){
            if(valid){

                var formdata=new FormData();
                if(app.photo.file!=null)
                {
                    formdata.append('uploadFile', app.photo.file);
                }

                $.ajax({
                    type:'post',
                    url:"/info_system/setImgImport",
                    data:formdata,
                    cache:false,
                    processData:false,//不处理发送的数据，因为data的值是fromdata对象,不需要对数据进行处理
                    contentType:false ,
                    success:function(rep){
                        if(rep.code=="success"){
                            app.user.userImg=null;
                            parent.app.user=rep.data;
                            parent.app.$Message.success(rep.message);
                        }else{
                            parent.app.$Message.error(rep.message);
                        }
                        parent.app.importModel.show=false;
                    },
                    error:function(){
                        app.$Message.error("请求出错");
                    }
                })
            }
            else{
                parent.app.importModel.loading=false;
                setTimeout("parent.app.importModel.loading=true",100);
                app.$Message.error('请正确填写信息');
            }
            parent.app.importModel.loading=false;
        });
    }

</script>
</body>
</html>
