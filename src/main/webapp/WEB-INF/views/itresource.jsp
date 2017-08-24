<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>System Administration - IT Resource Manager</title>
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
<h1>
	Add a new IT Resource
</h1>

<c:url var="addAction" value="/itresource/add" ></c:url>

<form:form action="${addAction}" commandName="itresource">
<table>
	<c:if test="${!empty itresource.name}">
	<tr>
		<td>
			<form:label path="key">
				<spring:message text="IT Resource Key"/>
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
				<spring:message text="IT Resource Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />&nbsp;<span><form:errors path="name" cssClass="error"/></span>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="itrTypeKey">
				<spring:message text="IT Resource Type Definition"/>
			</form:label>
		</td>
		<td>
			<form:select path="itrTypeKey" items="${resTypeComboList}"/>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty itresource.name}">
				<input type="submit"
					value="<spring:message text="Edit IT Resource"/>" />
			</c:if>
			<c:if test="${empty itresource.name}">
				<input type="submit"
					value="<spring:message text="Add IT Resource"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>IT Resources List</h3>
<c:if test="${!empty listITResources}">
	<table class="tg">
	<tr>
		<th width="80">IT Resource Key</th>
		<th width="80">IT Resource Name</th>
		<th width="80">IT Resource Type Definition</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listITResources}" var="itresource">
		<tr>
			<td>${itresource.key}</td>
			<td>${itresource.name}</td>
			<td>${itresource.itrTypeKey}</td>
			<td><a href="<c:url value='/itresource/edit/${itresource.key}' />" >Edit</a></td>
			<td><a href="<c:url value='/itresource/remove/${itresource.key}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>