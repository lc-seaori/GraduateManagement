function openTab(url, title) {
	if ($('#main').tabs('exists', title)) {
		$('#main').tabs('select', title);
	} else {
		$('#main').tabs('add', {
			title : title,
			href : url,
			closable : true,
			extractor : function(data) {
				return data;
			}
		});
	}
}

function getCurrentTab() {
	var tab = $('#main').tabs('getSelected');
	return tab;
}

function getTabIndex(tab) {
	var index = $('#main').tabs('getTabIndex', tab);
	return index;
}

function closeTab(tab) {
	$('#main').tabs('close', getTabIndex(tab));
}

function openWindow(id, url, title) {
	var tab = getCurrentTab();
	var div = "<div id='" + id + "'  class='easyui-window' title='" + title
			+ "' data-options='modal:true' >dsfdsf</div>";
	tab.append(div);
	$('#' + id).window('open');
}

function dealt(url, id) {
	var rows = $('#' + id).datagrid('getSelections');
	if (rows) {
		$.messager.confirm('Confirm',
				'Are you sure you had dealt this record?', function(r) {
					if (r) {
						var ids = "";
						for ( var index = 0; index < rows.length; index++) {
							ids += ids == "" ? rows[index].id : ","
									+ rows[index].id;
						}
						$.post(url, {
							"ids" : ids
						}, function(json) {
							if (json.status == "300") {
								$.messager.alert("fail!");
							} else {
								$('#' + id).datagrid('reload');
							}
						}, "json");
					}
				});
	}
}

function changeOption(form, select, input) {
	var value = $(form).find(select).val();
	$(form).find(input).val(value);
}

function setSelectedOption(form, select, value) {
	$(form).find(select).val(value);
}


//扩展easyui-combobox功能，能够自动判断是否已选择选择项，并提示信息
$.extend($.fn.validatebox.defaults.rules, {
    selectRequired: {
        validator: function (value, param) {
            var selectVal = $("input[name=" + param[0] + "]").val();
           /* $("#msg").html(selectVal);*/
            return parseInt(selectVal) > 0;
        },
        message:'该处未选择'
    }
});

//扩展easyui-combobox功能，能够自动判断是否已选择选择项，并提示信息,根据不同的form来识别相同id的easyui-combobox
$.extend($.fn.validatebox.defaults.rules, {
    selectRequiredForForm: {
        validator: function (value, param) {
            var selectVal = $('#'+param[0]).find("input[name=" + param[1] + "]").val();
           /* $("#msg").html(selectVal);*/
            return parseInt(selectVal) > 0;
        },
        message:'该处未选择'
    }
});

$.extend($.fn.validatebox.defaults.rules, {
    selectRequiredForFormZeReZhe: {
        validator: function (value, param) {
            var selectVal = $('#'+param[0]).find("input[name=" + param[1] + "]").val();
            /*$("#msg").html(selectVal);
            return parseInt(selectVal) > 0;*/
            
            if(value=="请选择"&&selectVal=="0")
            	return false;
            else
            	return true;
        },
        message:'该处未选择或未填写'
    }
});

//扩展easyui-validatebox功能，该规则起到比较日期大小的作用
$.extend($.fn.validatebox.defaults.rules,{
	   TimeCheck:{
	    validator:function(value,param){    
	     var s = $('#'+param[0]).find('#'+param[1]).val();
	     
	     var date1=s.split('年')[0]+"-"+s.split('年')[1].substring(0,s.split('年')[1].length-1);
	     
	     var date2=value.split('年')[0]+"-"+value.split('年')[1].substring(0,value.split('年')[1].length-1);
	     
	     //因为日期是统一格式的所以可以直接比较字符串 否则需要Date.parse(_date)转换
	     return date1<=date2;
	    },
	    message:'日期不正确'
	   }
	  }); 


//扩展easyui-datebox按钮功能，添加一个清空按钮
var buttons = $.extend([], $.fn.datebox.defaults.buttons);
buttons.splice(1, 0, {
	text: '清空',
	handler: function(target){
		
		$(target).datebox('setValue','');
		
	}
})

//扩展easyui-datetimebox按钮功能，添加一个清空按钮
var datetimeButtons = $.extend([], $.fn.datetimebox.defaults.buttons);
datetimeButtons.splice(1, 0, {
	text: '清空',
	handler: function(target){

		$(target).datetimebox('setValue','');

	}
})


