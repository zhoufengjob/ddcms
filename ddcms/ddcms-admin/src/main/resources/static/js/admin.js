function showPop(title, url) {
    var w;
    var h;

    w = ($(parent.window).width() * 0.9);
    h = ($(parent.window).height() - 50);

    var index = parent.layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fixed: true, //不固定
        maxmin: false,
        anim: 0,//0-6的动画形式，-1不开启
        shadeClose: true,
        scrollbar: true,
        shade: 0.4,
        title: title,
        content: url
    });
    // parent.layer.full(index);
}

//关闭窗体
function closePop() {
    var tab_iframe_id = $.cookie('tab_iframe_id');
    if (tab_iframe_id === null || tab_iframe_id === undefined || tab_iframe_id === "") {
        parent.$('#tableEvents').bootstrapTable("refresh");
    } else {
        parent.document.getElementById(tab_iframe_id).contentWindow.table.bootstrapTable("refresh");
    }

    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
//吐司弹框
function showToast(msg, fun) {
    swal(msg, {
        closeOnClickOutside: false,
        buttons: false,
        closeOnEsc: false,
        timer: 1000
    }).then(function () {
        fun();
    });
}

//对话框
function showDialog(icon, title, text, fun) {
    swal({
        icon: icon,
        title: title,
        text: text,
        dangerMode: true,
        closeOnConfirm: false,
        buttons: ["取消", "确定"]
    }).then(function (isConfirm) {
        if (isConfirm === true) {
            fun();
        } else {
            // Esc, close button or outside click
            // isConfirm is undefined
        }
    });
}

//将表单转换json对象
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//统一表格布局配置
function bootstrapTableByID(id, url, columns) {
    //region 数据表格渲染
    $(id).bootstrapTable({
        url: url,
        height: 640,
        // method: "post",
        locale: 'zh-CN',//中文支持
        search: true,
        clickToSelect: false,    //是否启用点击选中行
        pagination: true,     //是否显示分页（*）
        striped: true,      //是否显示行间隔色
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pageNumber: 1,      //初始化加载第一页，默认第一页
        pageSize: 10,      //每页的记录行数（*）
        pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
        queryParamsType: '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort  设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
        sortable: true,      //是否启用排序
        sortOrder: "asc",     //排序方式
        strictSearch: true, //设置为 true启用 全匹配搜索，否则为模糊搜索
        showToggle: false,   //是否显示 切换试图（table/card）按钮
        showPaginationSwitch: false,   //是否显示 数据条数选择框
        showRefresh: true,     //是否显示刷新按钮
        showColumns: true,
        minimumCountColumns: 2,    //最少允许的列数
        sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
        iconSize: 'outline',
        toolbar: id + 'Toolbar',//工具按钮用哪个容器
        icons: {
            refresh: 'glyphicon-repeat',
            toggle: 'glyphicon-list-alt',
            columns: 'glyphicon-list'
        },
        columns: [columns]
    });
    //endregion
}


//统一表格布局配置
function bootstrapTable(url, columns) {
    //region 数据表格渲染
    $("#tableEvents").bootstrapTable({
        url: url,
        height: 640,
        // method: "post",
        locale: 'zh-CN',//中文支持
        search: true,
        clickToSelect: false,    //是否启用点击选中行
        pagination: true,     //是否显示分页（*）
        striped: true,      //是否显示行间隔色
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pageNumber: 1,      //初始化加载第一页，默认第一页
        pageSize: 10,      //每页的记录行数（*）
        pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
        queryParamsType: '', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort  设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
        sortable: true,      //是否启用排序
        sortOrder: "asc",     //排序方式
        strictSearch: true, //设置为 true启用 全匹配搜索，否则为模糊搜索
        showToggle: false,   //是否显示 切换试图（table/card）按钮
        showPaginationSwitch: false,   //是否显示 数据条数选择框
        showRefresh: true,     //是否显示刷新按钮
        showColumns: true,
        minimumCountColumns: 2,    //最少允许的列数
        sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
        iconSize: 'outline',
        toolbar: '#tableEventsToolbar',//工具按钮用哪个容器
        icons: {
            refresh: 'glyphicon-repeat',
            toggle: 'glyphicon-list-alt',
            columns: 'glyphicon-list'
        },
        columns: [columns]
    });
    //endregion
}
