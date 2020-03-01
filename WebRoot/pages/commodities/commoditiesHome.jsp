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
		</div>
		<!-- 通过c:if创建table表单 -->
		<c:if test="${list!=null}">
			<table border="1" style="margin-top:10px;width:700px;text-align: center;">
				<tbody>
					<tr>
						<td>序号</td>
						<td>名称</td>
						<td>价格</td>
						<td>产地</td>
						<td>创建日期</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${list}" var="item" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${item.name}</td>
							<td>${item.price}</td>
							<td>${item.locality}</td>
							<td>${item.createtime}</td>
							<td>
								<a href="javascript:void(0);" class="editBtn" id="${item.fruitid}">编辑</a>
								<a href="javascript:void(0);" class="deleteBtn" id="${item.fruitid}">删除</a>
								<a href="javascript:void(0);" class="fushupBtn" id="${item.fruitid}">附属品</a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${list==null}">
						<b>搜索结果为空！</b>
					</c:if>
				</tbody>
			</table>
		</c:if>
	</div>
	<div class="page_fy">
		<a href="javascript:void(0);" id="toPrePage">上一页</a>
		<a href="javascript:void(0);" id="toNextPage">下一页</a>
		<input type="text" id="tz_input" style="width: 43px;height: 18px;"/>
		<button style="padding: 2px 13px;" id="toPageIdx">跳转</button>
		<div>
			<span id="pageInfo"></span>
		</div>
	</div>
	
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		 aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog" style="width: 350px;">
			<div class="modal-content">
				<div class="modal-header" style="background: #00699c;color: white;">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white;">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						修改信息
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<table id="xg_info">
							<tbody>
								<tr>
									<td class="td_name">名称：</td>
									<td class="td_value">
										<Input type="text" name="name"/>
										<Input type="hidden" name="fruitid"/>
									</td>
								</tr>
								<tr>
									<td class="td_name">价格：</td>
									<td class="td_value">
										<Input type="text" name="price"/>
									</td>
								</tr>
								<tr>
									<td class="td_name">产地：</td>
									<td class="td_value">
										<Input type="text" name="locality"/>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center;">
										 <button type="button" class="btn btn-primary" id="tj_btn" style="margin: 5px 1px;">提交</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div><!-- /.modal-content -->
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
					<form id="deleteForm" action="commodities/delete.action" method="post">
						<input type="hidden" id="fruitid" name="fruitid"/>
						<input type="hidden" id="dstartPage" name="startPage"/>
						<input type="hidden" id="dcurrentPage" name="currentPage"/>
						<input type="hidden" id="dpageSize" name="pageSize"/>
					</form>
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
					<form id="addForm" action="commodities/addData.action" method="post" style="display: inline-block;">
						<table id="table_addData">
							<tbody>
								<tr>
									<td class="td_name">名称:</td>
									<td class="td_value">
										<input type="text" name="name"/>
									</td>
								</tr>
								<tr>
									<td class="td_name">价格:</td>
									<td class="td_value">
										<input type="text" name="price"/>
									</td>
								</tr>
								<tr>
									<td class="td_name">产地:</td>
									<td class="td_value">
										<input type="text" name="locality"/>
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
	
	//初始化时间插件
	bootstrapUtilityFun.initTimeFun("timePicker","");
	
	
	
	
	//打开货物附属品信息页面
	$(".fushupBtn").click(function(){
		var fruitid=$(this).attr("id");
		var url="./accessory/list.action?fruitid="+fruitid;
		window.open(url, "附属品", "height=400,width=700,scrollbars=yes");
	});
	
	//添加新数据
	$("#addDate_btn").click(function(){
		$("#myModal_add").modal("show");
	});
	
	//确认删除
	$("#modal_sc_ok").click(function(){
		$("#deleteForm").submit();//表单数据提交
	});
	
	//点击删除按钮
	$(".deleteBtn").click(function(){
		var id=$(this).attr("id");
		$("#myModal_delete").modal("show");
		$("#modal_tipInfo").text("您确定删除该条数吗？");
		$("#fruitid").val(id||"");
		$("#dstartPage").val($("#startPage").val()||"");
		$("#dcurrentPage").val($("#currentPage").val()||"");
		$("#dpageSize").val($("#pageSize").val()||"");
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
	
		//分页功能
	
		var currentPage=$("#currentPage").val();
		var startPage=$("#startPage").val();
		var pageSize=$("#pageSize").val();
		var countNumber=$("#countNumber").val();
		var sumPageNumber=$("#sumPageNumber").val();
		
		//分页信息
		var pageInfo="一共<font color='blue'>"+countNumber+"</font>条数据, "+
		"共<font color='blue'>"+sumPageNumber+"</font>页, "+
		"当前第<font color='blue'>"+currentPage+"</font>页";
		$("#pageInfo").html(pageInfo);
		
		//上一页
		$("#toPrePage").click(function(){
			var currentPage=$("#currentPage").val();
			var startPage=$("#startPage").val();
			var pageSize=$("#pageSize").val();
			if(eval(currentPage)==1){
				alert("数据已到顶！");
			}
			else{
				$("#currentPage").val(eval(currentPage)-1);
				$("#startPage").val(eval(pageSize)-eval(startPage));
				$("#listForm").submit();
			}
		});
		//下一页
		$("#toNextPage").click(function(){
			var currentPage=$("#currentPage").val();
			var sumPageNumber=$("#sumPageNumber").val();
			var startPage=$("#startPage").val();
			var pageSize=$("#pageSize").val();
			if(eval(currentPage)>=eval(sumPageNumber)){
				alert("数据已到底！");
			}
			else{
				$("#currentPage").val(eval(currentPage)+1);
				$("#startPage").val(eval(pageSize)+eval(startPage));
				$("#listForm").submit();
			}
		});
		//跳转
		$("#toPageIdx").click(function(){
			var currentPage=$("#currentPage").val();
			var startPage=$("#startPage").val();
			var pageSize=$("#pageSize").val();
			var countNumber=$("#countNumber").val();
			var sumPageNumber=$("#sumPageNumber").val();
			var tz_page=$("#tz_input").val();//跳转页数
			if(tz_page){
				if(eval(tz_page)<1){
					alert("数据已到顶！");
				}
				else if(eval(tz_page)>sumPageNumber){
					alert("数据已到底！");
				}
				else{
					$("#currentPage").val(tz_page);//跳转页面设置为当前页面
					if(eval(tz_page)>eval(currentPage)){//向前跳转
						//从下一页数据idx开始请求
						$("#startPage").val(eval(startPage)+eval(pageSize));
					}
					else if(eval(tz_page)<eval(currentPage)){//向后跳转
						//从上一页数据idx开始请求
						$("#startPage").val(eval(startPage)-eval(pageSize))
					}
					$("#listForm").submit();
				}
			}
			else{
				alert("请输入要跳转的页数！");
			}
		});
		
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