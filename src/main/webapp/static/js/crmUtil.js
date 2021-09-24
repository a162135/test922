//标签类
FormClass = function (name,label){
    this.name = name;//名称
    this.label = label;//标签id
}

//crm工具类
CRMUtil = function (){
    //非空判断
    this.notNull = function(formClass,displayName){
        for (var i = 0; i < formClass.length; i++) {
            var str = $("#"+formClass[i].label).val().trim();
            if (str === undefined || str === ""){
                if (formClass[i].name != undefined && formClass[i].name != ""){
                    if (displayName != undefined && displayName != ""){
                        $("#"+displayName).html(formClass[i].name + "不能为空");
                    } else {
                        alert(formClass[i].name + "不能为空");
                    }
                }
                return false;
            }
        }
        return true;
    }

    //从session作用域中取数据
    this.getAttribute = function(name){
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
}
var crmUtil = new CRMUtil();