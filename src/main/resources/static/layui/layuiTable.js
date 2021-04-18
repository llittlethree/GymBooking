function loading(loginInfo) {
    var powerJSON ={}
    if (loginInfo != null && loginInfo != "") {
        var nullJson = {};
        var power = loginInfo.role.rolePower != null ? loginInfo.role.rolePower  : nullJson;
        var jsonObj = JSON.parse(power);
        powerJSON = jsonObj

    }

    /**
     * 根据角色id控制左侧导航的显示与隐藏
     * */
    var liRole = $("ul li[role]").length
    var roleId = loginInfo.roleId;
    for (var i = 0; i < liRole; i++) {
        $("ul li[role]").eq(i).hide();
        var arrStr = $("ul li[role]").eq(i).attr('role');
        var arr = arrStr.substring(arrStr.indexOf('[') + 1, arrStr.indexOf(']')).split(',');
        for (var j = 0; j < arr.length; j++) {
            if (arr[j] == roleId) {
                $("ul li[role]").eq(i).show();
                break;
            }
        }
    }
    for (var i = 0; i < liRole; i++) {
        if ($("ul li[role]").eq(i).is(":hidden") == true) {
            $("ul li[role]").eq(i).remove()
        }
    }
    /**
     * 根据角色权限控制显示隐藏
     * */
    var elePower = $("[power]").length;
    for (var i = 0; i < elePower; i++) {
        var arrStr = $("[power]").eq(i).attr('power');
        var arr = arrStr.substring(arrStr.indexOf('[') + 1, arrStr.indexOf(']')).split(',')
        var getPower = arr[0];
        var getTable = arr[1];
        console.log(getPower,getTable)
        $("[power]").eq(i).hide();
        for (var key in powerJSON) {
            if (key == getPower) {
                for (var j = 0; j < powerJSON[key].length; j++) {
                    if (powerJSON[key][j] == getTable) {
                        $("[power]").eq(i).show();
                        break;
                    }
                }
            }
        }
    }
    for (var i = 0; i < elePower; i++) {
        if ($("[power]").eq(i).is(":hidden") == true) {
            $("[power]").eq(i).remove()
        }
    }
}


/**数据表格*/
function Dbtable(tableId,DbUrl,toolId,TBtitle,data,cols,where={page:1,limit:10}) {
    //console.log(changeDate(1590940800));
    table.render({
        elem: tableId,
        url: DbUrl,
        toolbar: toolId,
        title: TBtitle,
        method: 'POST',
        //where:where,
        page: true,
        limit: 10,
        limits: [5, 7, 8, 10],
        groups: 5,
        defaultToolbar: ['print'],//删除工具栏默认工具'filter','print',
        data: data,
        cols: [cols],
        done: function (e) {
            console.log("table data",e);
            return e
        }
    });
}

/**
 * 模糊搜索查到的数据
 * */
function like(url,data) {
    var obj=null;
    $.ajax({
        url:url,
        type: 'POST',
        dataType: 'json',
        traditional:true,
        data:data,
        async:false,
    }).done(function(data) {
        if(data.code==0){
            obj=data;
        }else{
            layer.msg("搜索失败");
        }
    }).fail(function(data) {
        layer.msg("搜索异常");
    });
    return obj;
}

/*
* 批量删除
* */
function DELALl(selector,tablebar,url) {
    //批量删除
    var id_arr=[];//数组
    $(selector).click(function(){
        table.on(tablebar, function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            console.log(checkStatus);

            if(checkStatus.isAll===true){
                //console.log("全选");
                $(checkStatus.data).each(function(index,element){
                    id_arr[index]=element.id;
                });
                if(id_arr.length==0){
                    layer.msg("未选择");
                    id_arr=[];
                }else{
                    layer.confirm('确定批量删除吗？请谨慎操作！',{icon: 3, title:'警告'}, function(index){
                        deleteFlaseGroup(url,id_arr);
                        layer.close(index);
                        id_arr=[];
                    });
                }
            }

            if(checkStatus.isAll===false){
                //console.log("单选");
                $(checkStatus.data).each(function(index,element){
                    id_arr[index]=element.id;
                });
                if(id_arr.length==0){
                    layer.msg("未选择");
                    id_arr=[];
                }else{
                    layer.confirm(
                        '确定批量删除吗？请谨慎操作！',
                        {icon: 3, title:'警告'},
                        function(index){
                            deleteFlaseGroup(
                                url,
                                id_arr
                            );
                            layer.close(index);
                            id_arr=[];
                        });
                }
            }
        });
    });
}
//批量删除
function deleteFlaseGroup(url,id_arr){
    //console.log(id_arr);
    $.ajax({
        url:url,
        type: 'POST',
        dataType: 'json',
        traditional:true,
        data: {
            ids:id_arr//JSON.stringify(id_arr)
        },
    }).done(function(data) {
        //console.log("ajax返回:")
        //console.log(data);
        setToast(data.code,'delete');
    })
    .fail(function(data) {
        //console.log('error');
        //console.log(data);
        setToast(data.code,'delete');
    });
}

/**点击进入详情*/
function info(url){
    window.location.href=url;
}

/**点击表格中的修改按钮*/
function edit(url) {
    window.location.href=url;
}

/**点击表格中的修改按钮，弹出修改界面（弹窗）
 * elementIds:显示数据的控件id列表
 * files:显示到控件上的数据字段，与elementIds排列下标一致
 * */
function editOnThisPage(url,id,showId,elementIds,files,width="500px",height="300px",title="修改"){
    $("#updateBtn").css({'display':'inline-block'})
    $("#insertBtn").css({'display':'none'})
    $("#hident").val(id);
    var layerid;
    layerid = layer.open({
        title:title,
        type: 1,
        width:width,
        height:height,
        content: $(showId)
    });
    console.log("getid",layerid)
    $.ajax({
        url:url,
        type: 'POST',
        dataType: 'json',
        traditional:true,
        data: {
            id:id
        },
    }).done(function(data) {
        console.log("edit",data);
        if(data.code==0){
            for(var i=0;i<elementIds.length;i++){
                console.log(elementIds,files,data.data);
                for(var key in data.data){
                    console.log(data.data[key],key,files[i])
                    if(key==files[i]){
                        $(elementIds[i]).val(data.data[key])
                    }
                }
            }
        }
    }).fail(function(data) {

    });
    return layerid;
}
/**
 * 模糊搜索查到的数据
 * */
function like(url,data) {
    var obj=null;
    $.ajax({
        url:url,
        type: 'POST',
        dataType: 'json',
        traditional:true,
        data:data,
        async:false,
    }).done(function(data) {
        if(data.code==0){
            obj=data;
        }else{
            layer.msg("搜索失败");
        }
    }).fail(function(data) {
        layer.msg("搜索异常");
    });
    return obj;
}
/**点击表格中的删除按钮*/
function deleteOne(url,id) {
    layer.confirm('确定要删除吗？',{icon: 3, title:'警告'}, function(index){
        doSomethingOnTable(url,id,"delete")
        layer.close(index);
    });
}

/**表格中的事件*/
function doSomethingOnTable(url,id,sign) {
    $.ajax({
        url:url,
        type: 'POST',
        dataType: 'json',
        traditional:true,
        data: {
            id:id
        },
    }).done(function(data) {
        setToast(data.code,'delete');
    }).fail(function(data) {
        setToast(data.code,'delete');
    });
}
/**事件执行显示结果*/
function setToast(data,sign) {
    if(data==0){
        if(sign==="delete"){
            layer.msg("删除成功", {icon:6,time:1500},function(){
                window.location.reload();
            });
        }
    }else{
        if(sign==="delete"){
            layer.msg("删除失败", {icon:2,time:1500},function(){
                window.location.reload();
            });
        }
    }
}

function checkBoxRequest(url,data) {
    var obj=null;
    $.ajax({
        url:url,
        type: 'POST',
        dataType: 'json',
        traditional:true,
        data:data,
        async:false,
    }).done(function(data) {
        if(data.code==0){
            obj=data;
        }else{
            layer.msg("搜索失败");
        }
    }).fail(function(data) {
        layer.msg("搜索异常");
    });
    return obj;

}

/**
 * 之星添加事件 doAdd("#insertBtn")
 * validateFile ：验证字段
 * url：添加接口地址
 * data：添加的字段
 * tableId：重绘表格的id
 * layerId：弹窗的id
 * */
function doAddOrEdit(validateFile,url,data,tourl,sign='add'){
    //添加按钮点击
    if(validateFile.length>0){
        for (var i=0;i<validateFile.length;i++){
            if(validateFile[i]=="" ||validateFile[i] == null || validateFile[i].length<=0) {
                layer.msg("数据未填写整");
                return false;
            }
        }
    }
    $.ajax({
        url:url,
        type: 'POST',
        dataType: 'json',
        traditional:true,
        data: data,
    }).done(function(e) {
        console.log(e)
        if(e.code==0){
            //数据表格重新刷新
            var msg = "";
            if(sign==='add'){
                msg="添加成功"
            }else if (sign==="edit"){
                msg="修改成功"
            }
            layer.msg(msg,{time:2000},function () {
                window.location.href=tourl;
            });

        }else{
            layer.msg(e.msg);
        }

    }).fail(function(e) {
        console.log("error"+e)
        layer.msg(e);
    }).always(function() {

    });
}



/**
 *  本页面中的添加窗口
 * */
function addLayerOnThisPage(showId,width,height,title="添加"){
    if(width==null || width=="") width="500px";
    if(height==null || height=="") height="300px";
    var layerid;
    $(showId+" "+"input").val("");
    layerid = layer.open({
        title:title,
        type: 1,
        width:width,
        height:height,
        content: $(showId)
    });
    console.log("getid",layerid)
    $(showId).css({'display':'block'})
    return layerid;
}


/**
 * 弹窗类型的 添加窗口
 * */
function addLayer(domId,url="",isScroll="no",width="500px",height="300px",title="添加"){
    $(domId).click(function () {
        layer.open({
            title:title,
            type: 2,
            width:width,
            height:height,
            content:[url,isScroll]
        })
    });
}


/**
 * select 二级联动
 * firstId :一级select控件id
 * url :二级联动请求的数据url
 *
 * secondDefaultOptionID:二级select控件默认未选择选项的id
 * secondId:二级select控件的id
 * type:联动类型 RegionToBuilding默认：宿舍区域联动宿舍楼  BuildingToDormitory:宿舍楼联动宿舍
 * */
function selectConnect(firstId,url,secondDefaultOptionID,secondId,type) {
    form.on('select('+firstId+')', function(data){
        console.log("联动：",data);
        if(data.value != null && data.value > 0){
            //发起联动请求
            var param={};
            if (type == "RegionToBuilding") param={regionId:data.value};
            if (type == "BuildingToDormitory") param={buildingId:data.value};
            console.log("param",param)
            $.ajax({
                url:url,
                type: 'POST',
                dataType: 'json',
                traditional:true,
                data:param,
                //async:false,
            }).done(function(e) {
                if (e.code==0){
                    if (e.data.length > 0){
                        console.log(e.data)
                        for (var i=0;i<e.data.length;i++){
                            if (type == "RegionToBuilding") {
                                $("#" + secondDefaultOptionID).after("<option value='" + e.data[i].id + "'>" + e.data[i].buildingName + "</option>");
                            }
                            if (type == "BuildingToDormitory"){
                                $("#" + secondDefaultOptionID).after("<option value='" + e.data[i].id + "'>" + e.data[i].dormitoryName + "</option>");
                                console.log("__1__",secondDefaultOptionID, e.data[i].id , e.data[i].dormitoryName,$("#" + secondDefaultOptionID))
                            }
                            form.render();
                        }
                    }else{
                        if(type == "RegionToBuilding"){
                            $("#connectBox select[connect='2']").html("<option id='buildingIdDefaultOption' value=''>请选择</option>");
                            $("#connectBox select[connect='3']").html("<option id='dormitoryIdDefaultOption' value=''>请选择</option>");
                        }else{
                            $(secondId).html("<option id='"+secondDefaultOptionID+"' value=''>请选择</option>");
                        }
                        //$(secondId).html("<option id='"+secondDefaultOptionID+"' value=''>请选择</option>");
                        form.render(); // buildingIdDefaultOption dormitoryIdDefaultOption
                    }
                }else{
                    layer.msg("联动失败");
                }
            }).fail(function(data) {
                layer.msg("异常");
            });
        }else{
            if(type == "RegionToBuilding"){
                $("#connectBox select[connect='2']").html("<option id='buildingIdDefaultOption' value=''>请选择</option>");
                $("#connectBox select[connect='3']").html("<option id='dormitoryIdDefaultOption' value=''>请选择</option>");
            }else{
                $(secondId).html("<option id='"+secondDefaultOptionID+"' value=''>请选择</option>");
            }
            //$(secondId).html("<option id='"+secondDefaultOptionID+"' value=''>请选择</option>");
            //$("#connectBox select[connect='2']").html("<option id='"+secondDefaultOptionID+"' value=''>请选择</option>");
            //$("#connectBox select[connect='3']").html("<option id='"+secondDefaultOptionID+"' value=''>请选择</option>");

            form.render();
        }
    });
}
