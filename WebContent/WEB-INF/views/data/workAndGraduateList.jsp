<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:hidden id="temp" name="graTime" value="%{#request.graTime}"></s:hidden>
<div style="padding:30px 60px 30px 60px;float: left;">
	<table id="graPivotGrid" style="width:442px;height:auto;"></table>
</div>
<div style="padding:30px 60px 30px 60px;">
	<table id="workPivotGrid" style="width:442px;height:auto;"></table>
</div>
<script type="text/javascript">
	$(function(){
		load1();
		load2();
		$.extend($.fn.pivotgrid.defaults.operators, {
			grarate: function(rows, field){
				var opts = $(this).pivotgrid('options');
				var rate;
				var graCount=0;
				var notGraCount=0;
				$.map(rows,function(row){
					graCount+=row['已毕业人数'];
					notGraCount+=row['未毕业人数'];
					var sum=graCount+notGraCount;
					rate=parseFloat(graCount/sum)||0;
				});
				return rate.toFixed(opts.valuePrecision);
			},
			sum: function(rows, field){
				var opts = $(this).pivotgrid('options');
				var v = 0;
				$.map(rows,function(row){
					v += parseFloat(row[field])||0;
				});
				return v.toFixed(0);
			},
			workrate: function(rows, field){
				var opts = $(this).pivotgrid('options');
				var rate;
				var workCount=0;
				var notWorkCount=0;
				$.map(rows,function(row){
					workCount+=row['已就业人数'];
					notWorkCount+=row['未就业人数'];
					var sum=workCount+notWorkCount;
					rate=parseFloat(workCount/sum)||0;
				});
				return rate.toFixed(opts.valuePrecision);
			}
		});
	});
	
	
	//加载毕业率数据
	function load1(){
		var graTime=$('#temp').val();
		//alert(graTime);
		$('#graPivotGrid').pivotgrid({
			url:'DataManagement_getGraduateRate.action?graTime='+graTime,
			method:'get',
			pivot:{
				rows:['department','majorField','clazz'],
				columns:['rate'],
				values:[
					{field:'未毕业人数',op:'sum'},
					{field:'已毕业人数',op:'sum'},
					{field:'毕业率',op:'grarate'}
				]
			},
			valuePrecision:2,
			valueStyler:function(value,row,index){
				if (/毕业率$/.test(this.field) && value>=0.0 && value<0.50){
					return 'background:#D8FFD8;color:#ff0000;'
				}
			}
		});
	}
	
	function load2(){
		var workTime=$('#temp').val();
		$('#workPivotGrid').pivotgrid({
			url:'DataManagement_getWorkRate.action?workTime='+workTime,
			method:'get',
			pivot:{
				rows:['department','majorField','clazz'],
				columns:['rate'],
				values:[
					{field:'未就业人数',op:'sum'},
					{field:'已就业人数',op:'sum'},
					{field:'就业率',op:'workrate'}
				]
			},
			valuePrecision:2,
			valueStyler:function(value,row,index){
				if (/就业率$/.test(this.field) && value>=0.0 && value<0.50){
					return 'background:#D8FFD8;color:#ff0000;'
				}
			}
		});
	}
</script>