var BASE; // Context Path（在 script.jsp 中初始化）

var Reach = {
    /* -------------------------------------------------- 函数 -------------------------------------------------- */
	/* --  获取多选框的值 -- */
	getCheckBoxValues: function(selectE) {
		var ids = "";
		$(selectE + ":checked").each(function() {
            ids += this.value + ",";
        });
        if (ids.length > 0) {
            ids = ids.substring(0, ids.length - 1);
        }
        return ids;
	},
	/* --  获取多选框的值 -- */
	getCheckBoxQyOpenId: function(selectE) {
		var ids = "";
		$(selectE + ":checked").each(function() {
            ids += $(this).attr("qyOpenId") + ",";
        });
        if (ids.length > 0) {
            ids = ids.substring(0, ids.length - 1);
        }
        return ids;
	},
	/* -- 获取单选框的值 -- */
	getRadioValue: function(radioName) {
		return $("input[name='" + radioName + "']:checked").val();
	},
    Validator: {
        checkRequired: function(formId) {
            var result = true;
            $('#' + formId + ' .ext-required')
                .each(function() {
                    var value = $.trim($(this).val());
                    var tagName = this.tagName;
                    if (tagName == 'INPUT' || tagName == 'TEXTAREA') {
                        if (value == '') {
                            $(this).addClass('css-error');
                            result = false;
                        } else {
                            $(this).removeClass('css-error');
                        }
                    } else if (tagName == 'SELECT') {
                        if (value == '' || value == 0) {
                            $(this).addClass('css-error');
                            result = false;
                        } else {
                            $(this).removeClass('css-error');
                        }
                    }
                })
                .change(function() {
                    var value = $.trim($(this).val());
                    var tagName = this.tagName;
                    if (tagName == 'INPUT' || tagName == 'TEXTAREA') {
                        if (value != '') {
                            $(this).removeClass('css-error');
                        } else {
                            $(this).addClass('css-error');
                        }
                    } else if (tagName == 'SELECT') {
                        if (value != '' && value != 0) {
                            $(this).removeClass('css-error');
                        } else {
                            $(this).addClass('css-error');
                        }
                    }
                });
            return result;
        }
    },
    Cookie: {
        put: function(key, value) {
            $.cookie(key, value, {path: '/', expires: 365});
        },
        get: function(key) {
            return $.cookie(key);
        }
    },
    render: function(template, data) {
        return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
            var keys = key.split('.');
            var value = data[keys.shift()];
            for (var i = 0, l = keys.length; i < l; i++) {
                value = value[keys[i]];
            }
            return (typeof value !== 'undefined' && value !== null) ? value : '';
        });
    },
    i18n: function() {
        var args = arguments;
        var code = args[0];
        var text = window['I18N'][code];
        if (text) {
            if (args.length > 0) {
                text = text.replace(/\{(\d+)\}/g, function(m, i) {
                    return args[parseInt(i) + 1];
                });
            }
            return text;
        } else {
            return code;
        }
    },
    timestamp: function() {
        return new Date().getTime();
    },
    /* -------------------------------------------------- 组件 -------------------------------------------------- */
    Pager: function(pagerId, onChangePageNumber, onChangePageSize) {
        var $pager = $('#' + pagerId);
        // 初始化
        (function() {
            // 点击翻页按钮
            $(document).on('click', '#' + pagerId + ' .ext-pager-button button', function() {
                onChangePageNumber($(this).data('pn'));
            });
            // 点击并切换页面编号
            var pageNumberInput = '#' + pagerId + ' .ext-pager-pn';
            $(document)
                .on('click', pageNumberInput, function() {
                    $(this).select();
                })
                .on('keydown', pageNumberInput, function(event) {
                    if (event.keyCode == '13') {
                        var pageNumber = $(this).val();
                        var totalPage = parseInt($pager.find('.ext-pager-tp').text());
                        if (isNaN(pageNumber) || pageNumber <= 0 || pageNumber > totalPage) {
                            alert(Reach.i18n('common.pager.input_error'));
                            $(this).select();
                            return;
                        }
                        onChangePageNumber(pageNumber);
                    }
                });
            // 点击并切换每页条数
            var pageSizeInput = '#' + pagerId + ' .ext-pager-ps';
            $(document)
                .on('click', pageSizeInput, function() {
                    $(this).select();
                })
	            .on('change', pageSizeInput, function(event) {
                    var pageSize = $(this).val();
                    if (isNaN(pageSize) || pageSize <= 0) {
                        alert(Reach.i18n('common.pager.input_error'));
                        $(this).select();
                        return;
                    }
                    onChangePageSize(pageSize);
                    Reach.Cookie.put('cookie_ps_' + pagerId, pageSize);
	            });
        })();
        // 获取页面编号
        this.getPageNumber = function() {
            return $pager.find('.ext-pager-pn').val();
        };
        // 获取每页条数
        this.getPageSize = function() {
            return $pager.find('.ext-pager-ps').val();
        };
    }
};

$(function() {
    // 忽略空链接
    $('a[href="#"]').click(function() {
        return false;
    });

    // 设置 form 默认为 post 类型
    $('form').attr('method', 'post');

    // 全局 AJAX 设置
    $.ajaxSetup({
        cache: true,
        error: function(jqXHR, textStatus, errorThrown) {
            switch (jqXHR.status) {
                case 403:
                    document.write('');
                    location.href = BASE + '/';
                    break;
                case 500:
                    //alert(Reach.i18n('error.page.500'));
                    break;
                case 503:
                    alert(errorThrown);
                    break;
            }
        }
    });

    // 切换系统语言
    $('#language').find('a').click(function() {
        var language = $(this).data('value');
        Reach.Cookie.put('cookie_language', language);
        location.reload();
    });

    // 绑定注销事件
    $('#logout').click(function() {
    	var forword = $(this).attr('url') || (BASE + '/');
        if (confirm(Reach.i18n('common.logout_confirm'))) {
            $.ajax({
                type: 'post',
                url: '/ybolo/logout',
                data: {'ids': "ids"},
                dataType: 'json',
                success: function(result) {
                    if (result.success) {
//                    	if ($("#softWM").val()=='RMC-SN') {
//                    		location.href = "http://"+$("#topServer").val()
//                    	} else {
							if ($("#coIdLG").val() == '10000') {
								location.href = "/";
							} else {
                    			location.href = "/sys/enterprise/"+$("#coIdLG").val();
                    		}
//                    	}
                    }
                }
            });
        }
    });

    // 绑定验证码单击事件
    $('.ext-captcha').click(function() {
        var url = $(this).attr('src');
        var index = url.indexOf('?');
        if (index != -1) {
            url = url.substring(0, index);
        }
        url = url + '?_=' + Reach.timestamp();
        $(this).attr('src', url);
    });
    
    /**
     * 关联多选框，使用于全选多选框
     */
    $.relationCheckBox = function(allSelectE, selectE) {
        $(allSelectE).change(function() {
            $(selectE + ":not(:disabled)").attr("checked", this.checked);
        });
        $(selectE).change(function() {
            if ($(selectE + ":checked").length == $(selectE).length) {
                $(allSelectE).attr("checked", true);
            } else {
                $(allSelectE).attr("checked", false);
            }
        }).change();
    };
    
});


/**
 * 无重复数据的数组对象
 */
var UniqueArray = function() {
    
    this.datas = new Array();
    
    // 新增data
    this.add = function(data) {
        if (!this.contains(data)) {
            this.datas.push(data);
        }
    };
    
    // 是否包含data
    this.contains = function(data) {
        for(var i=0; i<this.datas.length; i++) {
            if (this.datas[i] == data) {
                return true;
            }
        }
        return false;
    };
    
    // 通过索引移除data
    this.remove = function(index) {
        this.datas.splice(index, 1);
    };
    
    // 移除data
    this.removeData = function(data) {
        for(var i=0; i<this.datas.length; i++) {
            if (this.datas[i] == data) {
                this.datas.splice(i, 1);
                return;
            }
        }
    };
    
    // 通过索引获取data
    this.get = function(index) {
        return this.datas[index];
    };
    
    // 数组长度
    this.size = function() {
        return this.datas.length;
    };
    
    // 转换为以,隔开的字符串
    this.toString = function() {
        var str = "";
        for(var i=0; i<this.datas.length; i++) {
            str += this.datas[i] + ",";
        }
        if (str.length > 0) {
            str = str.substring(0, str.length - 1);
        }
        return str;
    };
};
