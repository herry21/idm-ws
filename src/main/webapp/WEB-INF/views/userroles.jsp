<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>System Administration - User Role Membership</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
	<style>
	    .error {
	        color: red; font-weight: bold;
	    }
	</style>
</head>
<body>
<c:if test="${!empty user.userLogin}">
<h1>
	User Details - Role Membership for <c:out value='${user.userLogin}' />
</h1>
<br>
	<c:if test="${!empty listUserRoles}">
		<table class="tg">
		<tr>
			<th width="80">ID</th>
			<th width="80">Role Name</th>
		</tr>
		<c:forEach items="${listUserRoles}" var="role">
			<tr>
				<td>${role.key}</td>
				<td>${role.name}</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</c:if>
</body>
</html>