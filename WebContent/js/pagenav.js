
/**
 * 新版分页控件，支持一个页面内置多个分页控件
 * author		ivan yuan
 *
 * isPages		开关值列表为：0，关；1，开
 * totalPage	总页数
 * pageNumber	当前页数
 * dealFun		翻页处理函数
 * pageFirst	[首页]显示文本
 * pagePre		[上一页]显示文本
 * pageLast		[下一页]显示文本
 * pageLast		[末页]显示文本
 * styleName	分页控件显示的样式
 */

(function ($) { 
	$.PageFunc = function (isPages, totalPage, totalRecord, pageNumber, pageSize, pageFirst, pagePre, pageNext, pageLast, styleName, dataTarget, dealFun) { 
		//alert("isPages==="+isPages+"；totalPage==="+totalPage+"；pageNumber==="+pageNumber+"；dealFun==="+dealFun+"；pageFirst==="+pageFirst +"；pagePre==="+pagePre+"；pageNext==="+pageNext+"；pageLast==="+pageLast+"；styleName==="+styleName);
		//参数初始化处理
		if (isPages == "" || isPages == null || isPages == undefined) { 
			isPages = 0; 
		}
		if (isPages=="0") {
			//如果分页控件开关值为0，则表示不显示分页；那么直接返回空。
			return "";
		}
		if (totalPage == "" || totalPage == null || totalPage == undefined) { 
			totalPage = 1; 
		}
		if (totalRecord == "" || totalRecord == null || totalRecord == undefined) { 
			totalRecord = 1; 
		}
		if (pageNumber == "" || pageNumber == null || pageNumber == undefined) { 
			pageNumber = 1; 
		}
		if (pageSize == "" || pageSize == null || pageSize == undefined) { 
			pageSize = 1; 
		}
		if (pageFirst == "" || pageFirst == null || pageFirst == undefined) { 
			pageFirst = "first"; 
		}
		if (pagePre == "" || pagePre == null || pagePre == undefined) { 
			pagePre = "pre"; 
		}
		if (pageNext == "" || pageNext == null || pageNext == undefined) { 
			pageNext = "next"; 
		}
		if (pageLast == "" || pageLast == null || pageLast == undefined) { 
			pageLast = "last"; 
		}
		if (styleName == "" || styleName == null || styleName == undefined) { 
			styleName = "001"; 
		}
		
		// 绑定数据
		$(dataTarget).data('totalPage', totalPage);
		$(dataTarget).data('totalRecord', totalRecord);
		$(dataTarget).data('pageNumber', pageNumber);
		$(dataTarget).data('pageSize', pageSize);
		
		totalPage = parseInt(totalPage);
		totalRecord = parseInt(totalRecord);
		pageNumber = parseInt(pageNumber);

		var navHtml = ""; 
	
		//分页参数处理
		if (pageNumber <= 1) pageNumber = 1;
		if (totalPage <= 1) {
			pageNumber = 1;
			totalPage = 1;
		}
		if (totalPage < pageNumber){
			pageNumber = totalPage;
		}

		//只有总页数大于1时，才显示分页控件
		if (totalPage > 1) {
			if (pageNumber != 1) {
				//处理首页连接 
				navHtml += "<a href=\"javascript:" + dealFun + "('1')\">"+pageFirst+"</a> "; 
			}
			if (pageNumber > 1) {
				//处理上一页的连接 
				navHtml += "<a href=\"javascript:" + dealFun + "('" + (pageNumber - 1) + "')\">"+pagePre+"</a>"; 
			}
		
			var currint = 3; 
			for (var i = 0; i <= 6; i++) {
				//显示前面...
				if (i==0 && (pageNumber + i - currint) > 1) navHtml += "...";
				
				//显示中间的页码【一共最多显示6个页码，前面3个，后面3个】
				if ((pageNumber + i - currint) >= 1 && (pageNumber + i - currint) <= totalPage){ 
					if (currint == i) {
						//当前页处理 
						navHtml += "<span class='current'>" + pageNumber + "</span> "; 
					} else {
						//一般页处理 
						var n = pageNumber + i - currint; 
						navHtml += "<a href=\"javascript:" + dealFun + "('" + (parseInt(n)) + "')\">" + n + "</a> "; 
					}
				}
				
				//显示后面...
				if (i==6 && (pageNumber + i - currint) < totalPage) navHtml += "...";
			}

			if (pageNumber < totalPage) {
				//处理下一页的链接 
				navHtml += "<a href=\"javascript:" + dealFun + "('" + (parseInt(pageNumber) + 1) + "')\">"+pageNext+"</a>"; 
			}
			if (pageNumber != totalPage) {
				//处理末页连接
				navHtml += "<a href=\"javascript:" + dealFun + "('" + totalPage + "')\">"+pageLast+"</a>"; 
			}
		}
		
		return navHtml;
	};

})(jQuery);

