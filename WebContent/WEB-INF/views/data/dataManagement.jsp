<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="pp" style="width:1100px;position:relative">
    <div style="width:30%;"></div>
    <div style="width:40%;"></div>
    <div style="width:30%;"></div>
</div>
<div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mytitle'" style="width:350px;height:150px;border:1px solid #ccc">
    <div id="mytitle" style="background:#ddd;padding:5px;">温馨提示</div>
    <div style="padding-top:20px;padding-left:20px;">1.导入数据之前先下载表格模板，按照模板来填写数据;</div>
    <div style="padding-top:20px;padding-left:20px;">2.在导出数据的同时可以选择筛选条件;</div>
    <div style="padding-top:20px;padding-left:20px;">3.如果没有选择筛选条件，默认导出所有数据.</div>
</div>

<style type="text/css">
    .demo-rtl .portal-column-left{
        padding-left: 10px;
        padding-right: 10px;
    }
    .demo-rtl .portal-column-right{
        padding-left:10px;
        padding-right: 0;
    }
</style>
<script type="text/javascript">
	//定义panel个数
	var panels = [
        {id:'p1',title:'导入学生信息(包含生源信息)(excell)',height:200,collapsible:true,href:'DataManagement_Import_toImportStuInfoPage.action'},
        {id:'p2',title:'导出学生生源信息(excell)',height:250,href:'DataManagement_ExportStuInfos_toExportStuInfosPage.action'},
        {id:'p3',title:'导出学生就业信息(excell)',height:250,collapsible:true,closable:true,href:'DataManagement_ExportGraInfos_toExportGraInfosPage.action'},
        {id:'p4',title:'导出用人单位的招聘信息(word)',height:200,closable:true,href:'DataManagement_ExportRecInfos_toExportRecInfosPage.action'},
        {id:'p5',title:'导出学生信息(excell)',height:250,collapsible:true,closable:true,href:'DataManagement_ExportStus_toExportStusPage.action'},
        {id:'p6',title:'导入学生就业信息(excell)',height:250,collapsible:true,closable:true,href:'DataManagement_Import_toImportGraPage.action'}
        /* ,
        {id:'p5',title:'Searching',href:'portal_p5.html'},
        {id:'p6',title:'Graph',href:'portal_p6.html'} */
    ];
	//根据名字来获取浏览器cookie
    function getCookie(name){
        var cookies = document.cookie.split(';');
        if (!cookies.length) return '';
        for(var i=0; i<cookies.length; i++){
            var pair = cookies[i].split('=');
            if ($.trim(pair[0]) == name){
            	//返回状态
                return $.trim(pair[1]);
            }
        }
        return '';
    }
	
    //根据3个div的id来设置不同div里面的panel数
    function getPortalState(){
        var aa = [];
        //3为div个数
        for(var columnIndex=0; columnIndex<3; columnIndex++){
            var cc = [];
            //获取div里面的panel
            var panels = $('#pp').portal('getPanels', columnIndex);
            for(var i=0; i<panels.length; i++){
                cc.push(panels[i].attr('id'));
            }
            //同一个panel里面用","隔开
            aa.push(cc.join(','));
        }
        //不同Panel之间用":"隔开
        return aa.join(':');
    }
    
    //根据panel的id获取panel
    function getPanelOptions(id){
    	//panels为定义好的panel数据（开始定义的）
        for(var i=0; i<panels.length; i++){
            if (panels[i].id == id){
                return panels[i];
            }
        }
        return undefined;
    }
    
    function addPanels(portalState){
    	//根据状态来添加panel到div中
        var columns = portalState.split(':');
        for(var columnIndex=0; columnIndex<columns.length; columnIndex++){
        	//获取每个panel
            var cc = columns[columnIndex].split(',');
            for(var j=0; j<cc.length; j++){
                var options = getPanelOptions(cc[j]);
                if (options){
                    var p = $('<div/>').attr('id',options.id).appendTo('body');
                    p.panel(options);
                    $('#pp').portal('add',{
                        panel:p,
                        columnIndex:columnIndex
                    });
                }
            }
        }
        
    }
    
    $(function(){
    	//当portal插件的状态改变，就重新设置布局状态以及设置cookie的时间
        $('#pp').portal({
            onStateChange:function(){
                var state = getPortalState();
                var date = new Date();
                date.setTime(date.getTime() + 24*3600*1000);
                document.cookie = 'portal-state='+state+';expires='+date.toGMTString();
            }
        });
        var state = getCookie('portal-state');
        //默认布局
        if (!state){
            state = 'p1,p5,p6:p2,p3:p4';    // the default portal state
        }
        addPanels(state);
        $('#pp').portal('resize');
    });
</script>