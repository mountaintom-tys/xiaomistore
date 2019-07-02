<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
        <meta name="author" content="order by dede58.com"/>
		<title>我的购物车-小米商城</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
	</head>
	<body>
	<!-- start header -->
	<!--end header -->

<!-- start banner_x -->
		<div class="banner_x center">
			<a href="xiaomishangcheng.action" target="_blank"><div class="logo fl"></div></a>
			
			<div class="wdgwc fl ml40">我的购物车</div>
			<div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
			<div class="dlzc fr">
				<ul>
					<li><a href="dingdanzhongxin.action" target="_blank">我的订单</a></li>
					<li>|</li>
					<li><a href="self_info.action" target="_blank">个人中心</a></li>	
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="xiantiao"></div>
		<div class="gwcxqbj">
			<div class="gwcxd center">
				 <div class="top2 center">
					<div class="sub_top fl">
						<input type="checkbox" value="quanxuan" id="allcheck" class="quanxuan" onclick="allchecked()" checked/>全选
					</div>
					<div class="sub_top fl">商品名称</div>
					<div class="sub_top fl">单价</div>
					<div class="sub_top fl">数量</div>
					<div class="sub_top fl">小计</div>
					<div class="sub_top fr">操作</div>
					<div class="clear"></div>
				 </div>
				 <c:forEach items="${shoppingcars}" var="row">
				 	<div class="content2 center">
						<div class="sub_content fl ">
							<input type="checkbox" name="xid" value="${row.sid}" id="xid${row.sid}"class="quanxuan" style="margin-top: 45px" checked  onclick="checkBox1(${row.sid},${row.subtotal })"/>
						</div>
						<input type="hidden" id="sid" value="${row.sid}" />
						<div class="sub_content fl" ><img src="${row.products.picture}" style="width:82px;height:82px;margin-top:15px" ></div>
						<div class="sub_content fl ft20">${row.products.pname}${row.products.version}&nbsp;${row.products.color}</div>
						<div class="sub_content fl ">${row.products.price}</div>
						<div class="sub_content fl">
							<input class="shuliang" type="number" value="${row.amount}" id="nid${row.sid}" step="1" min="1" style="margin-top: 40px" onclick="updateShoppingCarAmount(${row.sid})">
						</div>
						<div class="sub_content fl">${row.subtotal}</div>
						<%-- <div class="sub_content fl"><a href="deleteShoppingCar.action?sid=${row.sid}">×</a></div> --%>
						<div class="sub_content fl"><a href="#" onclick="deleteShoppingCar(${row.sid})">×</a></div>
						<div class="clear"></div>
					</div>
				</c:forEach>
			</div>
			<div class="jiesuandan mt20 center">
				<div class="tishi fl ml20">
					<ul>
						<li><a href="liebiao.action">继续购物</a></li>
						<li>|</li>
						<li>共<span>${shoppingcars.size()}</span>件商品，已选择<span id="choosed">${shoppingcars.size()}</span>件</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="jiesuan fr">
					<div class="jiesuanjiage fl">合计（不含运费）：<span id="total">${total}</span></div>
					<div class="jsanniu fr"><input class="jsan" type="submit" name="jiesuan"  value="去结算" onclick="addOrder()"/></div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
<script type="text/javascript" src="./js/jquery-1.11.3.js"></script>
<script type="text/javascript">
/*  function deleteShoppingCar(sid) {
	$.post("deleteShoppingCar.action",{"sid":sid},
			function(){
		window.location.reload();
	}); 
}  */ 
function deleteShoppingCar(sid) {
	$.ajax({
		type: "POST",
		url: "deleteShoppingCar.action",
		data:"sid="+sid,
	});
	window.location.reload();
} 
function updateShoppingCarAmount(sid) {
	var amount=document.getElementById("nid"+sid);
	$.ajax({
		type: "POST",
		url: "updateShoppingCarAmount.action",
		data:"sid="+sid+"&amount="+amount.value,
	});
	
	
	window.location.reload();
} 
 function checkBox1(sid,subtotal){
	var check=document.getElementById("xid"+sid);
	var total=document.getElementById("total");
	var choosed=document.getElementById("choosed");
	var allcheck=document.getElementById("allcheck");
	allcheck.checked=false;
	if(check.checked==false){
		total.innerText=total.innerText-subtotal;
		choosed.innerText=choosed.innerText-1;
		}
	else{
		total.innerText=parseInt(total.innerText)+parseInt(subtotal);
		choosed.innerText=parseInt(choosed.innerText)+1;
		}
}
 function allchecked(){
		var allcheck=document.getElementById("allcheck");
		if(allcheck.checked==true){
			window.location.reload();
		}
	}
 function addOrder(){
	 var xid=document.getElementsByName("xid");
	 for(var id=0;id<xid.length;id++){
		 var sid=xid[id].value;
		 var check=xid[id].checked;
		 if(check==true){
			 $.ajax({
					type: "POST",
					url: "addOrder.action",
					data:"sid="+sid,
				});
		 }	
	 }
	 alert("确认结算当前选中商品？");
	 window.location.reload();
 }

</script>
	<!-- footer -->
	<footer class="center">
			
			<div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
			<div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div> 
			<div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
		</footer>

	</body>
</html>