FormClass = function (name,label){
    this.name = name;
    this.label = label;
}

function notNull(formClass){
    for (var i = 0; i < formClass.length; i++) {
        var str = $("#"+formClass[i].label).val();
        if (str === undefined || str === ""){
            alert(formClass[i].name + "不能为空");
            return false;
        }
    }
    return true;
}