<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <div data-options="region:'west',title:'导航栏',split:true,iconCls:'icon-menu'" style="width: 180px;"> -->
<div data-options="region:'west',title:' -导航',split:true,iconCls:'icon-menu'" style="width: 200px;">
	<ul class="easyui-tree" data-options="animate:true,lines:true">
		<li data-options="iconCls:'icon-blank'"><span>新闻管理</span>
			<ul>
				<li data-options="state:'open',iconCls:'icon-panel'"><a plain="true" onclick="openTab('newsplate_index.action','新闻板块')">新闻板块管理</a></li>
				<li><a plain="true" onclick="openTab('newsnn_index.action','新闻资讯')">新闻资讯管理</a></li>
				<!-- <li data-options="iconCls:'icon-large-chart'"><span>报表统计</span>
					<ul>
						<li data-options="iconCls:'icon-large-smartart'"><a plain="true" onclick="openTab('DataManagement_toChooseTimePage.action','选择时间')">就业率毕业率</a></li>
						<li data-options="iconCls:'icon-large-picture'"><a plain="true" onclick="openTab('DataManagement_Graph_toShowGraphPage.action','报表查询')">报表查询</a></li>
					</ul>
				</li> -->
			</ul>
		</li>
		<li data-options="iconCls:'icon-group'"><span>毕业生信息管理</span>
			<ul>
				<li data-options="iconCls:'icon-man'"><span>生源信息管理</span>
					<ul>
						<li data-options="iconCls:'icon-sum'"><a plain="true" onclick="openTab('StudentInfo_List_toStudentInfoListPage.action','学生生源信息列表')">学生源信息</a></li>
						<li data-options="iconCls:'icon-edit'"><a plain="true" onclick="openTab('StudentInfo_List_toDepartmentAduitPage.action','源信息院系审核')">学院审核</a></li>
						<li data-options="iconCls:'icon-edit'"><a plain="true" onclick="openTab('StudentInfo_List_toSchoolAduitPage.action','源信息学校审核')">学校审核</a></li>
					</ul>
				</li>
				<li data-options="state:'closed',iconCls:'icon-man'"><span>学生信息</span>
					<ul>
						<li data-options="iconCls:'icon-sum'"><a plain="true" onclick="openTab('Student_toStudentListPage.action','学生信息列表')">学生基本信息</a></li>
					</ul>
				</li>
				<li data-options="iconCls:'icon-theme'"><span>就业信息管理</span>
					<ul>
						<li data-options="iconCls:'icon-theme'"><a plain="true" onclick="openTab('GraduateInfo_List_toGraduateInfoListPage.action','学生就业信息列表')">学生就业信息</a></li>
						<li data-options="iconCls:'icon-edit'"><a plain="true" onclick="openTab('GraduateInfo_List_toDepartmentAduitPage.action','就业信息院系审核')">学院审核</a></li>
						<li data-options="iconCls:'icon-edit'"><a plain="true" onclick="openTab('GraduateInfo_List_toSchoolAduitPage.action','就业信息校审核')">学校审核</a></li>
					</ul>
				</li>
			</ul>
		</li>
		
		<li data-options="iconCls:'icon-company'"><span>用人单位信息管理</span>
			<ul>
				<li data-options="iconCls:'icon-org'"><span>单位基本信息</span>
					<ul>
						<li data-options="iconCls:'icon-company'"><a plain="true" onclick="openTab('EmployingUnit_List_toEmpUnitListPage.action','用人单位信息列表')">单位信息列表</a></li>
						<li><a plain="true" onclick="openTab('RecruitmentInfo_List_toRecruitmentInfoListPage.action','招聘信息列表')">招聘信息列表</a></li>
					</ul>
				</li>
			</ul>
		</li>
		
		<li data-options="iconCls:'icon-blank'"><span>数据管理</span>
			<ul>
				<li data-options="state:'open',iconCls:'icon-panel'"><a plain="true" onclick="openTab('DataManagement_Show_toDataManagementPage.action','数据导入导出')">数据导入导出</a></li>
				<li data-options="iconCls:'icon-large-chart'"><span>报表统计</span>
					<ul>
						<li data-options="iconCls:'icon-large-smartart'"><a plain="true" onclick="openTab('DataManagement_toChooseTimePage.action','选择时间')">就业率毕业率</a></li>
						<li data-options="iconCls:'icon-large-picture'"><a plain="true" onclick="openTab('DataManagement_Graph_toShowGraphPage.action','报表查询')">报表查询</a></li>
					</ul>
				</li>
			</ul>
		</li>
		
		<li data-options="state:'closed',iconCls:'icon-sys'"><span>系统管理</span>
			<ul>
				<li data-options="state:'open',iconCls:'icon-user'">
					<a plain="true" onclick="openTab('User_toUserListPage.action','用户列表')"><span>用户管理</span></a>
				</li>
				<li data-options="state:'open',iconCls:'icon-resource'">
					<a plain="true" onclick="openTab('System_Auth_toAuthListPage.action','权限列表')"><span>权限管理</span></a>
				</li>
				<li data-options="state:'open',iconCls:'icon-role'">
					<a plain="true" onclick="openTab('System_Role_toRoleListPage.action','角色列表')"><span>角色管理</span></a>
				</li>
				<li data-options="state:'open',iconCls:'icon-org'">
					<a plain="true" onclick="openTab('Organization_toOrgListPage.action','组织部门列表')"><span>组织部门管理</span></a>
				</li>
				<li data-options="state:'open',iconCls:'icon-dic'">
					<a plain="true" onclick="openTab('System_Department_toDepListPage.action','学院列表')"><span>学院管理</span></a>
				</li>
				<li data-options="state:'open',iconCls:'icon-dic'">
					<a plain="true" onclick="openTab('System_Major_toMajorListPage.action','专业列表')"><span>专业管理</span></a>
				</li>
				<li data-options="state:'open',iconCls:'icon-dic'">
					<a plain="true" onclick="openTab('System_Clazz_toClazzListPage.action','班级列表')"><span>班级管理</span></a>
				</li>
				<li data-options="state:'open',iconCls:'icon-bug'">
					<a plain="true" onclick="openTab('System_Record_toOperRecordListPage.action','用户操作记录')"><span>用户操作记录</span></a>
				</li>
			</ul>
		</li>
	</ul>
</div>