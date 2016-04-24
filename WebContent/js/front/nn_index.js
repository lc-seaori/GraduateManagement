var querySort = 0;
window.coursePageList = function(pageNumber , typeVal , sortVal) {
	var $txtSearch = $('#txt-search'),
	searchkeywords = $.trim($txtSearch.val());
	if(searchkeywords == $txtSearch.data('prompt') && !$txtSearch.hasClass('black')) {
		searchkeywords = '';
	}
	//解决选定条件之后，分页条件参数为undefined的问题
	if(typeVal==undefined&&sortVal==undefined){
		//获取类型值
		typeVal=$('#dl-query1 dd[class="cur"]').attr('id');
		typeVal = typeVal.replace('type-','');
		if(typeVal == '0'){
			typeVal = '';
		}
		//获取排序值
		sortVal=$('#dl-query2 dd[class="cur"]').attr('id');
		sortVal = sortVal.replace('sort-','');
	}
	
	var params = {
		'pageNumber': pageNumber,
		'pageSize': 5,
		'plateId' : typeVal,
		'sort' : sortVal,
		'whatType' : $('#whatType').val()
	};

	$.ajax({
		type: 'POST',
		url: 'front_no_nnlist',
        data: params,
        dataType: 'html',
        success: function(data) {
            $('#nn_list').html(data);
        }
	});
};

function selectquerytype(typeVal){
	$('#dl-query1 dd').removeClass('cur');
	$('#type-' + typeVal).addClass('cur');
	var sortVal='';
	//判断排序值
	if($('#sort-reg_count').hasClass('cur')){
		sortVal = 'reg_count';
	}else{
		sortVal = 'create_time';
	}
	if(typeVal == '0'){
		//全部
		window.coursePageList(1,'',sortVal);
	}else{
		window.coursePageList(1,typeVal,sortVal);
	}
}

function selectquerysort(sortVal){
	$('#dl-query2 dd').removeClass('cur');
	$('#sort-' + sortVal).addClass('cur');
	//获取类型值
	var typeVal = '';
	typeVal=$('#dl-query1 dd[class="cur"]').attr('id');
	typeVal = typeVal.replace('type-','');
	if(typeVal == '0'){
		typeVal = '';
	}
	window.coursePageList(1,typeVal,sortVal);
}


