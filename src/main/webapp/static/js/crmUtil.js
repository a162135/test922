//标签类
FormClass = function (name,label){
    this.name = name;//名称
    this.label = label;//标签id
}

//非空判断
function notNull(formClass){
    for (var i = 0; i < formClass.length; i++) {
        var str = $("#"+formClass[i].label).val().trim();
        if (str === undefined || str === ""){
            alert(formClass[i].name + "不能为空");
            return false;
        }
    }
    return true;
}

function getAttribute(name){
    var json;
    $.ajax({
        url:"/crm/util/getAttribute.do",
        data:{
            "name":name
        },
        async:false,
        dataType:"json",
        success:function (data){
            json = data;
        }
    })
    return json;
}