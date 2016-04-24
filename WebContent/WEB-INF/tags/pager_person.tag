<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="dataTarget" required="true" type="java.lang.String" %>
<%@ attribute name="dealFun" required="true" type="java.lang.String" %>
<%@ attribute name="pager" required="true" type="com.benson.graduate.base.pagemodel.Pager" %>
<div class="${dataTarget} pageNav"></div>
<script type="text/javascript">
/*alert("${pager.totalPage}");
alert("${pager.totalRecord}");
alert("${pager.pageNumber}");
alert("${pager.pageSize}");
	alert(
		$.PageFunc(
				1,
				'${pager.totalPage}',
				'${pager.totalRecord}',
				'${pager.pageNumber}',
				'${pager.pageSize}',
				'首页',
				'&lt;&lt;上一页',
				'&gt;&gt;下一页',
				'尾页',
				'001',
				'.${dataTarget}',
				'${dealFun}'
			)		
	);*/

	$('.${dataTarget}').html($.PageFunc(
		1,
		'${pager.totalPage}',
		'${pager.totalRecord}',
		'${pager.pageNumber}',
		'${pager.pageSize}',
		'首页',
		'&lt;&lt;上一页',
		'&gt;&gt;下一页',
		'尾页',
		'001',
		'.${dataTarget}',
		'${dealFun}'
	));
</script>






