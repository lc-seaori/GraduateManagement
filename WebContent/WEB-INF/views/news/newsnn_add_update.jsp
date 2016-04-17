<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String contextPath = request.getContextPath(); %>
<br/>
<br/>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="newsnnForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<input type="hidden" id="isAdd" name="isAdd" value="${isAdd}"/>
		<input type="hidden" id="platePid" name="platePid" value="${platePid}"/>
		<input type="hidden" id="id" name="id" value="${id}"/>
		<table cellpadding="7">
			<tr>
				<td>新闻标题：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="title" id="title"  
						data-options="required:true" missingMessage="必须填写新闻标题" 
						style="width: 150px; height: 15px; padding: 5px" value="%{#request.news.title}"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>创建人：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="createName" id="createName" 
						data-options="required:true" missingMessage="必须填写创建人名称" 
						style="width: 150px; height: 15px; padding: 5px" value="%{#request.news.createName}"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>新闻简介：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="summary" id="summary" 
						data-options="required:true" missingMessage="必须填写新闻简介" 
						style="width: 300px; height: 15px; padding: 5px" value="%{#request.news.summary}"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>新闻类型：</td>
				<td>
					<select class="easyui-combobox" editable="false" 
					id="newsType" name="newsType" style="width: 150px"
					data-options="panelWidth:'130px', panelHeight:'auto'">
						<option value="0">---请选择类型---</option>
						<c:forEach items="${newsType}" var="newsTe">
							<option <c:if test="${type == newsTe.id}">selected="selected"</c:if> value="${newsTe.id}">${newsTe.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>新闻关键字：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="keyWords" id="keyWords" 
						style="width: 150px; height: 15px; padding: 5px" value="%{#request.news.keyWords}"/>
				</td>
			</tr>
			
			<tr>
				<td>封面图片：</td>
				<td>
					<img id="cover" alt="image" 
					<c:if test="${news.coverImg == ''||isAdd == '1'}">src="<%=contextPath%>/uploadImg/default.png"</c:if> 
					<c:if test="${news.coverImg != ''}">src="<%=contextPath%>/uploadImg/${news.coverImg}"</c:if> 
					style="height: 90px;width: 160px;"/>
					<input id="uploadFile" name="upload" class="easyui-filebox" 
			   			style="width:300px" data-options="prompt:'Choose a file...',buttonText:'选择文件'"/>
			   		<input type="hidden" id="oldImgName" name="oldImgName" value="${news.coverImg}"/>
			   		<input type="hidden" id="coverImg" name="coverImg"/>
			   		<a href="javascript:void(0)" id="uploadSubmit" class="easyui-linkbutton" iconCls="icon-save">上传</a>
				</td>
			</tr>
			
			<tr>
				<td>所述一级新闻版块：</td>
				<td>
					<s:select list="#request.platepList" listKey="id" listValue="plateName" headerKey="---请选择版块---" headerValue="---请选择版块---"
					editable="false" id="pPlateSelected" name="plateParentId" style="width: 150px"
					data-options="panelWidth:'130px', panelHeight:'auto'"></s:select>
					<span>&nbsp;二级新闻版块：</span>
					<select editable="false" 
					id="pSelected" name="plateId" style="width: 150px"
					data-options="panelWidth:'130px', panelHeight:'auto'">
						<option value="0">---请选择版块---</option>
						<c:if test="${isAdd == '0'}">
							<c:forEach items="${plateList}" var="plate">
								<option <c:if test="${plateId == plate.id}">selected="selected"</c:if> value="${plate.id}">${plate.plateName}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>新闻内容：</td>
				<td><s:textarea cssClass="easyui-textbox" id="content" 
					name="content" data-options="multiline:true" 
					style="width: 500px; height: 350px; padding: 5px" value="%{#request.news.content}"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	<br/>
	<br/>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="saveNewsNnData('#newsnnForm')">保存</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitNewsPlatePage()">关闭</a>
	</div>
</div>
<script type="text/javascript">

	$(function(){
		//更新时初始化下拉框
		if($('#isAdd').val() == '0'){
			$('#pPlateSelected').find('option').each(function(){
				if($(this).val() == $('#platePid').val()){
					$(this).attr("selected",true);
				}
			}); 
		}
		
		var tcSelect = $('#pPlateSelected');
		tcSelect.combobox({
			onSelect : function() {
				var pSelected = tcSelect.combobox('getValue');
				if(pSelected!="---请选择版块---"&&pSelected!=null){
					/* $('#plateSelected').combobox({
						valueField : "id",
						textField : "plateName",
						url : "newsnn_getPlateList.action?plateParentId=" + pSelected,
						editable : false
					});		 */
					$.ajax({
					     type: 'get',
					     url: "newsnn_getPlateList.action?plateParentId=" + pSelected ,
					     dataType : 'json',
					     success: 
					    	function(data){
					    	 	var htmlVal = "";
					    		if(data!=null && data!=""){
					    			for(var i=0;i<data.length;i++){
					    				htmlVal = htmlVal+"<option value="+ data[i].id +">"+ data[i].plateName +"</option>"
					    			}
					    		}else{
					    			htmlVal = htmlVal+"<option value='0'>---请选择版块---</option>"
					    		}
					    		$('#pSelected').html(htmlVal);
					    		//alert(data[0].id);
							}
					}); 
				}else{
					//清空combobox
					/* $('#plateSelected').combobox('loadData', {});
					$('#plateSelected').combobox('setValue', '---请选择版块---'); */
				}
			}
		});
		
		//图片上传
		$('#uploadSubmit').click(function(){
			var fileName=$('#uploadFile').filebox('getValue');
			if(fileName==""){     
	            $.messager.alert('提示','请选择上传文件！','info');   
	        }else{  
	           	//Easy-UI的进度条提示
	           	$.messager.progress({
					title:'操作信息',
					text: '正在上传，等稍等...'
				}); 
	           $('#newsnnForm').ajaxSubmit({
			     type: 'post',
			     url: "newsnn_uploadImg.action" ,
			     dataType : 'json',
			     success: 
			    	function(data){
			    		if (data.success){
			    			$.messager.show({
			                    title:'操作提示',
			                    msg:  "   上传成功！",
			                    timeout:3000,
			                    showType:'slide'
			                });
			    			$("#coverImg").val(data.msg);
			    			$("#oldImgName").val(data.msg);
			    			$("#cover").attr("src",'<%=contextPath%>/uploadImg/'+data.msg);
						}else{
							$.messager.alert("操作提示","   "+data.msg,'error');
						} 
			    		$.messager.progress('close'); 
					}
				}); 
	           $('#uploadFile').filebox('setValue', '');
	        } 
		});
	});
  	
	function exitNewsNnPage() {
		closeTab(getCurrentTab());
	}
	
	//表单提交
	function saveNewsNnData(form){
    	//在提交数据之前执行表单验证
		$('#newsnnForm').form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var platePid = $(form).find('#pPlateSelected').combobox('getValue');
    	var newsType = $(form).find('#newsType').combobox('getValue');
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(newsType=="0"){
			$.messager.alert("错误提示", "请选择新闻类型","info"); 
			return false;
		}else if(platePid=="0" || platePid =="---请选择版块---"){
			$.messager.alert("错误提示", "至少选择一级新闻版块","info"); 
			return false;
		}
		
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "newsnn_editSubmit.action" ,
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
					   	$.messager.alert("操作提示", "操作成功",'info');   
					   	$('#newsNnList').datagrid('reload');
					   	closeTab(getCurrentTab());
					}else{
						$.messager.alert("操作提示",result.msg,'error');
					}
				}
			});   
	}
      
</script>
