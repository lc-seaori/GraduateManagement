<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="importGraDiv" style="position:relative;padding-top: 40px" align="center">
   	<s:form id="importGraForm" action="" method="post" namespace="/" enctype="multipart/form-data">
   		<input id="uploadFile" name="upload" class="easyui-filebox" 
   			style="width:300px" data-options="prompt:'Choose a file...',buttonText:'选择文件'"/>
   			<br/>
   			<br/>
   		<a href="DataManagement_DownloadGrasFile.action" id="downloadSubmit" class="easyui-linkbutton" iconCls="icon-print">表格下载</a>
   		&nbsp;&nbsp;&nbsp;
   		<a href="javascript:void(0)" id="uploadSubmit" class="easyui-linkbutton" iconCls="icon-save">上传</a>
   	</s:form>
</div>

<script type="text/javascript">
	$('#importGraForm').find('#uploadSubmit').click(function(){
		var fileName=$('#importGraForm').find('#uploadFile').filebox('getValue');
		//alert(fileName);
		if(fileName==""){     
            $.messager.alert('提示','请选择上传文件！','info');   
        }else{  
           	//Easy-UI的进度条提示
           	$.messager.progress({
				title:'操作信息',
				text: '正在上传，等稍等...'
			}); 
			
            //清空
            $('#importGraForm').ajaxSubmit({
		     type: 'post',
		     url: "DataManagement_Import_uploadGra.action" ,
		     dataType : 'json',
		     success: 
		    	function(data){
		    		//alert(data.success);
		    		var result=data.msg.split(",");
		    		if (data.success){
		    			$.messager.show({
		                    title:'操作提示',
		                    msg:  "   "+result[1],
		                    timeout:3000,
		                    showType:'slide'
		                });
					}else{
						$.messager.alert("操作提示","   "+result[1],'error');
					} 
		    		$.messager.progress('close');
				}
			}); 
            $('#importGraForm').find('#uploadFile').filebox('setValue', '');
        }
	});
</script>