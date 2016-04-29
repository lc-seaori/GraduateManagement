window.infoPageList = function(pageNumber) {
	var $txtSearch = $('#txt-search'),
	searchkeywords = $.trim($txtSearch.val());
	if(searchkeywords == $txtSearch.data('prompt') && !$txtSearch.hasClass('black')) {
		searchkeywords = '';
	}
	
	var whatType = $('#whatType').val();
	
	var params = {
		'pageNumber': pageNumber,
		'pageSize': 15,
		'whatType' : whatType
	};

	$.ajax({
		type: 'POST',
		url: 'front_no_infolist',
        data: params,
        dataType: 'html',
        success: function(data) {
            $('#info_list').html(data);
        }
	});
};


