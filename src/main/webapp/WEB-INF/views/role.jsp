<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Role Management</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a User
</h1>

<c:url var="addAction" value="/role/add" ></c:url>

<form:form action="${addAction}" commandName="role">
<table>
	<c:if test="${!empty role.name}">
	<tr>
		<td>
			<form:label path="key">
				<spring:message text="Role Key"/>
			</form:label>
		</td>
		<td>
			<form:input path="key" readonly="true" size="8"  disabled="true" />
			<form:hidden path="key" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Role Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td> 
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty role.name}">
				<input type="submit"
					value="<spring:message text="Edit Role"/>" />
			</c:if>
			<c:if test="${empty role.name}">
				<input type="submit"
					value="<spring:message text="Add Role"/>" />
			</c:if>
		</td>
	</tr>
</table>
</form:form>
<br>
<h3>Role List</h3>
<c:if test="${!empty listRoles}">
	<table class="tg">
	<tr>
		<th width="80">Role Key</th>
		<th width="80">Name</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listRoles}" var="role">
		<tr>
			<td>${role.key}</td>
			<td>${role.name}</td>
			<td><a href="<c:url value='/role/edit/${role.key}' />" >Edit</a></td>
			<td><a href="<c:url value='/role/remove/${role.key}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>