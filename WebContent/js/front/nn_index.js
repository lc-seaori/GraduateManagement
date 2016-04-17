var querySort = 0;
window.nnPageList = function(pageNumber,platePid,plateId) {
	var $txtSearch = $('#txt-search'),
	searchkeywords = $.trim($txtSearch.val());
	if(searchkeywords == $txtSearch.data('prompt') && !$txtSearch.hasClass('black')) {
		searchkeywords = '';
	}
	var params = {
		'pageNumber': pageNumber,
		'pageSize': 10,
		'searchkeywords': searchkeywords,
		'orderField': querySort,
		'plate_pid':platePid,
		'plate_id':plateId,
		'mb_client':mb_client,
		'open_id':open_id,
		'open_orgs':open_orgs
	};

	$.ajax({
		type: 'POST',
		url: BASE + '/sys/enterprise/nn/page/' + coId,
        data: params,
        dataType: 'html',
        success: function(data) {
            $('#nn-list').html(data);
        }
	});
};

function selectquerytype(val){
	$('.f1 dd').removeClass('cur');
	$('#pp-' + val).addClass('cur');
	if(val == 0){
		window.nnPageList(1,'','');
		val='NO';
	}else{
		window.nnPageList(1,val,'');
	}
	var selectHtml='';
	$.ajax({
        type: 'POST',
        url: BASE + '/sys/news/search/plate',
        data: {'platePid': val},
        dataType: 'json',
        success: function(result) {
        	var obj = eval(result.data);
        	 $.each(obj, function (i, item) {
        		 selectHtml +='<dd id="sort-'+item.plateId+'" onclick=selectquerysort("'+item.plateId+'","'+item.platePid+'")>'+item.plateName+'</dd>';
             });
        	 $("#plateIdDt").empty();
        	 $("#plateIdDt").html(selectHtml);
        }
    });
}

function selectquerysort(val,pval){
	$('.video_sort dd').removeClass('cur');
	$('#sort-' + val).addClass('cur');
	window.nnPageList(1,pval,val);
}

function selectblock(val,pval){
	$('.nn_block dd').removeClass('cur');
	$('#pp-' + val).addClass('cur');
	if(val == 0){
		window.nnPageList(1,pval,'');
	}else{
		window.nnPageList(1,pval,val);
	}
}

