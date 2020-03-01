<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入jstl标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>"><!-- 项目根目录 -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>水果货物管理</title>
	<link rel="stylesheet" type="text/css" href="apiFiles/bootstrap/css/bootstrap-3.3.7.min.css">
	<link rel="stylesheet" type="text/css" href="apiFiles/bootstrap/css/bootstrap-datepicker3.css">
	<link rel="stylesheet" type="text/css" href="apiFiles/bootstrap/css/bootstrap-datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="pages/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="pages/css/commoditiesHome.css">
	<script type="text/javascript" src="apiFiles/jquery/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="apiFiles/bootstrap/js/bootstrap-3.3.7.min.js"></script>
	<script type="text/javascript" src="apiFiles/bootstrap/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="apiFiles/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="apiFiles/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="customPlugins/js/bootstrapUtilityFun.js"></script>
</head>
<body>
	<%@ include file="../menu.jsp" %><!-- 引用共用菜单元素 -->
	<div id="query_div">
		<form id="listForm" action="commodities/list.action" method="post">
			<!-- table布局 -->
			<table id="query_table">
				<tbody>
					<tr>
						<td class="td_name">
							名称：
						</td>
						<td class="td_value">
							<input type="text" name="name"/>
						</td>
						<td class="td_name">
							产地：
						</td>
						<td class="td_value">
							<input type="text" name="locality"/>
						</td>
						<td class="td_name">
							价格：
						</td>
						<td class="td_value"> 
							<input type="text" name="startPrice" value="0.0" style="width: 50px;"/>
							~
							<input type="text" name="endPrice" value="0.0" style="width: 50px;"/>
						</td>
					</tr>
					<tr>
						<td class="td_value" colspan="4">
							创建日期：
							<input class="timePicker" type="text" name="startTime"/>
							~
							<input class="timePicker" type="text" name="endTime"/>
						</td>
						<td class="td_name" colspan="2">
							<button id="search" style="padding: 3px 30px;margin-left: 39px;">搜索</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 隐藏元素 -->
			<div>
				<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
				<input type="hidden" id="startPage" name="startPage" value="${startPage}">
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}">
				<input type="hidden" id="countNumber" name="countNumber" value="${countNumber}">
				<input type="hidden" id="sumPageNumber" name="sumPageNumber" value="${sumPageNumber}">
			</div>
		</form>
		<div id="tipInfo">
			<!-- 显示错误信息 -->
			<c:if test="${errorMsg}">
				<font color="red">${errorMsg}</font>
			</c:if>
		</div>
	</div>
	<div id="showDataList">
		<div>
			<button type="button" class="btn btn-success btn-sm" id="addDate_btn">添加</button>
			<button type="button" class="btn btn-primary btn-sm" id="shanchu_btn">删除</button>
		</div>
		<!-- 通过c:if创建table表单 -->
		<c:if test="${list!=null}">
			<table border="1" style="margin-top:10px;width:700px;text-align: center;">
				<tbody>
					<tr>
						<td><input type="checkbox" id="checkAll"/></td>
						<td>序号</td>
						<td>名称</td>
						<td>价格</td>
						<td>创建日期</td>
					</tr>
					<c:forEach items="${list}" var="item" varStatus="status">
						<tr>
							<td><input type="checkbox" class="checkSelect" value="${item.accessoryid}"/></td>
							<td>${status.index+1}</td>
							<td>${item.name}</td>
							<td>${item.price}</td>
							<td>${item.createtime}</td>
						</tr>
					</c:forEach>
					<c:if test="${list==null}">
						<b>搜索结果为空！</b>
					</c:if>
				</tbody>
			</table>
		</c:if>
	</div>
	
	<!-- 删除数据弹框 -->
	<div class="modal fade" id="myModal_tipInfo" tabindex="-1" role="dialog" aria-hidden="true" >
		<div class="modal-dialog" style="width: 300px;margin-top: 10%;">
			<div class="modal-content">
				<div class="modal-header" style="background: #00699c;color: white;">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white;">
						&times;
					</button>
					<h4 class="modal-title">温馨提示:</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<span id="modal_tipInfo2" style="font-size: 16px;"></span>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
			</div>
		</div>
	</div>
	<!-- end -->
	
	<!-- 删除数据弹框 -->
	<div class="modal fade" id="myModal_delete" tabindex="-1" role="dialog" aria-hidden="true" >
		<div class="modal-dialog" style="width: 300px;margin-top: 10%;">
			<div class="modal-content">
				<div class="modal-header" style="background: #00699c;color: white;">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white;">
						&times;
					</button>
					<h4 class="modal-title">温馨提示:</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<span id="modal_tipInfo" style="font-size: 16px;"></span>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="modal_sc_ok">确定</button>
                </div>
			</div>
		</div>
	</div>
	<!-- end -->
	
	<!-- modal-添加新数据 -->
	<div class="modal fade" id="myModal_add" tabindex="-1" role="dialog" aria-hidden="true" >
		<div class="modal-dialog" style="width: 300px;margin-top: 10%;">
			<div class="modal-content">
				<div class="modal-header" style="background: #00699c;color: white;">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white;">
						&times;
					</button>
					<h4 class="modal-title">添加数据:</h4>
				</div>
				<div class="modal-body" style="text-align: center;vertical-align: middle;">
					<form id="addForm" action="accessory/addData.action" method="post" style="display: inline-block;">
						<table id="table_addData">
							<tbody>
								<tr>
									<td class="td_name">名称:</td>
									<td class="td_value">
										<input type="text" name="name"/>
										<input type="hidden" name="fruitid" id="newFruitid"/>
									</td>
								</tr>
								<tr>
									<td class="td_name">价格:</td>
									<td class="td_value">
										<input type="text" name="price"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button class="btn btn-warning btn-sm">添加</button>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- end -->
	<script>
	
	//全部选中，取消
	$("#checkAll").click(function(){
		var status=$($(this)).is(":checked");
		if(status){
			$(".checkSelect").prop("checked",true);
		}
		else{
			$(".checkSelect").prop("checked",false);
		}
		
	});
	
	//批量删除数据
	$("#modal_sc_ok").click(function(){
		var idArray=[];
		var url=window.location.href;
		var param=urlParamToObject(url)||{};
		var fruitid=param.fruitid||""; 
		$(".checkSelect").each(function(idx,item){
			var status=$(item).prop('checked');
			if(status){
				var value=$(item).val()||"";
				idArray.push(value);
			}
		});
		var url="http://localhost:8082/FruitSalesPlatform/accessory/deleteList.action";
		var param={
			fruitid:fruitid,
			arrays:idArray
		};
		ajaxRequest(url,param,function(result){
			if(result&&result.code=="0"){
				$("#myModal_delete").modal("hide");
				$("#myModal_tipInfo").modal("show");
				$("#modal_tipInfo2").text("删除数据成功!");
				window.location.reload();//页面重新刷新
			}
		})
		
	});
	
	//删除数据
	$("#shanchu_btn").click(function(){
		$("#myModal_delete").modal("show");
		$("#modal_tipInfo").text("您确定删除数据吗？");
	});
	
	
	//添加新数据
	$("#addDate_btn").click(function(){
		$("#myModal_add").modal("show");
		var url=window.location.href;
		var param=urlParamToObject(url)||{};
		var fruitid=param.fruitid||"";
		$("#newFruitid").val(fruitid);
	});
	
	
	
		//提交编辑数据
		$("#tj_btn").click(function(){
			var param={};
			$("#xg_info input").each(function(idx,item){
				var name=$(item).attr("name");
				var value=$(item).val()||"";
				if(name)param[name]=value;
				
			});
			var url="http://localhost:8082/FruitSalesPlatform/commodities/updateData.action";
			ajaxRequest(url,param,function(result){
				if(result&&result.code=="0"){
					$("#myModal").modal("hide");
					window.location.href="./commodities/list.action";
				}
			})
		});
	
		//编辑功能
		$(".editBtn").click(function(){
			var id=$(this).attr("id");
			$("#myModal").modal("show");
			var url="http://localhost:8082/FruitSalesPlatform/commodities/getDetailById.action"
			var param={
				id:id
			};
			ajaxRequest(url,param,function(json){
				if(json){
					$("#xg_info input").each(function(idx,item){
						var name=$(item).attr("name");
						if(name)$(item).val(json[name]||"");
					});
					$("#status").val(json.status||"");
				}
			})
		});
	
		
		/***************封装将路径参数解析为对象**************
		 *参数:url(string):带参数的请求地址
		 *返回值:paramObject(object):解析的参数对象
		 */
		function urlParamToObject(url=""){
			let paramObject={};
			if(/\?/.test(url)){
			let urlString = url.substring(url.indexOf("?")+1); 
			let urlArray=urlString.split("&"); 
			for (let i=0,len=urlArray.length;i<len;i++) { 
			let urlItem=urlArray[i]; 
			var idx=urlItem.indexOf("=");
			let name=urlItem.substring(0,idx);
			let value=urlItem.substring(idx+1);
			paramObject[name]=value; 
			} 
			}
			return paramObject;
		}//e
		
		
		/*
		**jquery ajax
		*1.
		*/
		
		function ajaxRequest(url,param,callback){
			var jsonStr=JSON.stringify(param);
			$.ajax({
				type:"post",
				url:url,
				contentType:"application/json;charset=utf-8",//提交参数的数据类型
				data:jsonStr,//数据参数
				success:function(result){
					callback(result);
				}
			});	
		}
	</script>
</body>
</html>