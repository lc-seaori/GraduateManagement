<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="studentInfoDetialsForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="id" name="id"/>
		<table cellpadding="7">
			<tr>
				<td>准考证号：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="examNum" name="examNum" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>学生姓名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="name" name="name" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>毕业学校：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="graduationSchool" name="graduationSchool" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>性别：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="sex" name="sex" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>专业：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="majorFieldName" name="majorFieldName" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>出生年月：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="birthday" name="birthday" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>身份证号：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="idCard" name="idCard"  
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>民族：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="nation" name="nation"  
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>政治面貌：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="politicalFeature" name="politicalFeature"  
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>籍	贯：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="nativePlace" name="nativePlace"  
						style="width: 200px; height: 15px; padding: 5px"  readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>户籍类型：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="household" name="household"  
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>学	制：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="schoolLength" name="schoolLength"  
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>学	历：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="education" name="education"  
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="phone" name="phone" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>入学年份：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="enterCollegeTime" name="enterCollegeTime" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>毕业日期：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="graduateTime" name="graduateTime" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>邮政编码：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="postEncoding" name="postEncoding" 
						style="width: 200px; height: 15px; padding: 5px" readonly="true"/>
				</td>
			</tr>
			
			<tr>
				<td>户口所在地:</td>
				<td><s:textarea cssClass="easyui-textbox" type="text" id="householdPlace" name="householdPlace" 
					style="width: 400px; height: 50px; padding: 5px" readonly="true"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitStudentInfoDetialsPage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
    function exitStudentInfoDetialsPage(){
    	closeTab(getCurrentTab());
    }  
</script>
	