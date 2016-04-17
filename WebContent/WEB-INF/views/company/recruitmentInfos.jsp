<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="ecruitmentUnit" class="easyui-accordion" data-options="multiple:true,border:false" style="width:500px;height:600px;float:left;padding-left: 20px;padding-top: 20px">
    <div title="<font style='padding-left:30px'><s:property value='#request.unit.unitName'/>   基本信息   (右边为该公司所有的招聘信息)</font>" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
        <div class="easyui-panel" data-options='fit:true,border:false'>
        	<p>单位编号：   <s:property value="#request.unit.unitCode"/></p>
        	<p>招聘方式：   <s:property value="#request.recruitmentTypes"/></p>
        	<p>单位性质：   <s:property value="#request.unitTypes"/></p>
        	<p>单位联系人：   <s:property value="#request.unit.unitContantPerson"/></p>
        	<p>单位联系方式：   <s:property value="#request.unit.unitContantPhone"/></p>
        	<p>单位详细地址：   <s:property value="#request.unit.unitAddress"/></p>
        	<p>单位描述：   <s:property value="#request.unit.unitDescription"/></p>
    	</div>
    </div>
</div> 
<div style="padding-top: 20px;">
	<h1 style="color:#0099FF;font-size: 15px" align="center" >招聘信息</h1>
	<s:if test="#request.pageInfos.size()==0">
		<div class="easyui-accordion" data-options="multiple:true,border:false" style="width:500px;height:auto;padding-left: 30px;">
			<div title="<font style='padding-left:30px'>操作提醒</font>" data-options="iconCls:'icon-ok'" 
			style="overflow:auto;padding:10px;">
				<h1 style="color:#0099FF;padding-left: 30px;font-size: 15px" align="center" >暂无招聘信息</h1></div></div>
	</s:if>
	<s:else>
		<div id="recruitmentInfos" class="easyui-accordion" data-options="multiple:true,border:false"
		 style="width:500px;height:auto; padding-left: 30px">
		    <s:iterator value="#request.pageInfos">
		    	<div title="<font style='padding-left:30px'>[职位]<s:property value='position'/></font>  
		    	<font style='color:red;padding-left:80px'>[月薪]<s:property value='monthlySalary'/></font>" 
		    	style="overflow:auto;padding:10px;" data-options="iconCls:'icon-ok'">
					<div class="easyui-panel" data-options='fit:true,border:false'>
			    		<p style="float: left;padding-right: 50px">招聘职位：   <s:property value="position"/></p>
			    		<p>月薪： <s:property value="monthlySalary"/></p>
			    		<p>招聘人数：   <s:property value='hireCount'/></p>
			    		<p>工作性质： <s:property value="workType"/></p>
			    		<p>行业领域： <s:property value="industryType"/></p>
			    		<p>学历要求： <s:property value="educationType"/></p>
			    		<p>发布时间： <s:property value="releaseTime"/></p>
			    		<p>截止时间： <s:property value="endTime"/></p>
			    		<p>发布单位：<s:property value='#request.unit.unitName'/></p>
			    		<p>职位描述：   <s:property value="positionDescription"/></p>
			    	</div>
			    </div>
		    </s:iterator>
		</div>
	</s:else>
</div>

<script type="text/javascript">
</script>
