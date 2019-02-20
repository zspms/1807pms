
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../res/lecheng/css/admin.css" rel="stylesheet"
		type="text/css" />
	<link href="../res/common/css/theme.css" rel="stylesheet"
		type="text/css" />
	<link href="../res/common/css/jquery.validate.css" rel="stylesheet"
		type="text/css" />
	<link href="../res/common/css/jquery.treeview.css" rel="stylesheet"
		type="text/css" />
	<link href="../res/common/css/jquery.ui.css" rel="stylesheet"
		type="text/css" />

	<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>

	<script src="../res/common/js/jquery.js" type="text/javascript"></script>

	<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
	<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
	<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
	<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

	<link rel="stylesheet" href="../res/css/style.css" />

	<script type="text/javascript">
		//文档就绪事件
		$(function() {
			//初始化二级部门
			$.post("getdeps.do",//url
			{
				pid : $("#udep1").val()
			},//json data
			function(data) {
				//返回json数据
				if (data != "") {
					$(data).each(
							function() {
								//用户所在部门id等于二级部门id
								if ($("#udep2").val() == this.id) {
									//设置下拉框属性
									$("#dep2").append(
											"<option value='"+this.id+"' selected='selected'>"
													+ this.name + "</option>");
								} else {
									//设置下拉框属性
									$("#dep2").append(
											"<option value='"+this.id+"'>"
													+ this.name + "</option>");
								}

							})
				}

			},//成功回调方法
			"json" //返回数据类型 dataType
			);
			//在一级部门下拉框的change时间
			$("#dep1").change(
					function() {
						//清空二级部门下拉框
						$("#dep2").empty();
						//发送ajax请求 
						$.post("getdeps.do",//url
						{
							pid : this.value
						},//json data
						function(data) { //返回json数据
							if (data != "") {
								$(data).each(
										function() {
											//设置下拉框属性
											$("#dep2").append(
													"<option value='"+this.id+"'>"
															+ this.name
															+ "</option>");
										})
							}

						},//成功回调方法
						"json" //返回数据类型 dataType

						)
					});
		});
	</script>
</head>
<body>
	<!--用户所在部门的上级部门id-->
	<input type="hidden" id="udep1" value="${USER.dept.pid}"> <!--用户所在部门的id-->
		<input type="hidden" id="udep2" value="${USER.dept.id}">
			${pageContext.request.contextPath} <!--获得应用的绝对路径--> <img
			src="${pageContext.request.contextPath}/images/logo4.png" /> <br />
			<!--绝对路径--> <img src="../images/logo4.png" /> <!--相对路径 ../ 表示上一级-->
			<div class="box-positon">
				<div class="rpos">
					当前位置: 用户管理 - 修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
						class="pn-frequired">${MSG}</span>
				</div>
				<form class="ropt">
					<input type="submit" onclick="this.form.action='userlist.do';"
						value="返回列表" class="return-button" />
				</form>
				<div class="clear"></div>
			</div>
			<div class="body-box" style="float: right">
				<form name="fm" id="jvForm" action="add.do" method="post">
					<table cellspacing="1" cellpadding="2" width="100%" border="0"
						class="pn-ftable">
						<tbody>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h"><span
									class="pn-frequired">*</span> 登录名:</td>
								<td width="80%" class="pn-fcontent"><input id="loginname"
									type="text" class="required" name="loginname" maxlength="100"
									value="${USER.loginname}" /> <span id="loginnamemsg" /></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h"><span
									class="pn-frequired">*</span> 密码:</td>
								<td width="80%" class="pn-fcontent"><input id="password"
									type="password" class="required" name="password"
									maxlength="100" value="${USER.password}" /> <span
									id="passwordmsg" /></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h"><span
									class="pn-frequired">*</span> 确认密码:</td>
								<td width="80%" class="pn-fcontent"><input id="conpwd"
									type="password" class="required" name="conpwd" maxlength="100"
									value="${USER.password}" /></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">真实姓名:</td>
								<td width="80%" class="pn-fcontent"><input id="realname"
									type="text" class="required" name="realname" maxlength="100"
									value="${USER.realname}" /> <span id="realnamemsg" /></td>
							</tr>

							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">性别:</td>
								<td width="80%" class="pn-fcontent"><c:if
										test="${USER.sex=='男'}">
										<input type="radio" name="sex" checked value="男" />男 
						<input type="radio" name="sex" value="女" />女
						</c:if> <c:if test="${USER.sex=='女'}">
										<input type="radio" name="sex" value="男" />男 
						<input type="radio" name="sex" checked value="女" />女
						</c:if></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">出生日期:</td>
								<td width="80%" class="pn-fcontent"><input type="text"
									name="birthday" maxlength="80" class="Wdate"
									value="${USER.birthdayText}" onclick="WdatePicker()"
									readonly="readonly" /></td>
							</tr>

							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">email:</td>
								<td width="80%" class="pn-fcontent"><input id="email"
									value="${USER.email}" type="text" name="email" maxlength="80" />
									<span id="emailmsg" /></td>
							</tr>

							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">部门:</td>
								<td width="80%" class="pn-fcontent"><select id="dep1"
									name="dep1">
									<!-- 用户的部门的上级部门不等于一级部门的id -->
										<c:if test="${USER.dept.pid==0}">
											<option value="0">无</option>
										</c:if>
										<c:forEach items="${DLIST}" var="d1">
											<!-- 用户的部门的上级部门等于一级部门的id -->
											<c:if test="${USER.dept.pid==d1.id}">
												<option value="${d1.id}" selected="selected">${d1.name}</option>
											</c:if>
											<!-- 用户的部门的上级部门不等于一级部门的id -->
											<c:if test="${USER.dept.pid!=d1.id}">
												<option value="${d1.id}">${d1.name}</option>
											</c:if>
										</c:forEach>
								</select> <select id="dep2" name="dept.id">

								</select></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">是否可用:</td>
								<td width="80%" class="pn-fcontent"><c:if
										test="${USER.isenabled==1}">
										<input type="radio" name="isenabled" checked value="1" />可用 
						<input type="radio" name="isenabled" value="-1" />不可用
						</c:if> <c:if test="${USER.isenabled==-1}">
										<input type="radio" name="isenabled" value="1 " />可用 
						<input type="radio" name="isenabled" checked value="-1" />不可用
						</c:if> <c:if test="${USER.isenabled!=-1 && USER.isenabled!=1}">
										<input type="radio" name="isenabled" value="1 " />可用 
						<input type="radio" name="isenabled" checked value="-1" />不可用
						</c:if></td>
							</tr>

						</tbody>
						<tbody>
							<tr>
								<td class="pn-fbutton" colspan="2"><input type="submit"
									class="submit" value="提交" /> &nbsp; <input type="reset"
									class="reset" value="重置" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
</body>
</html>