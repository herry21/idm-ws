<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>System Administration - IT Resource Parameter Definition</title>
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
	Add a new IT Resource Parameter Definition
</h1>

<c:url var="addAction" value="/itparamdefinition/add" ></c:url>

<form:form action="${addAction}" commandName="itparamdefinition">
<table>
	<c:if test="${!empty itparamdefinition.name}">
	<tr>
		<td>
			<form:label path="key">
				<spring:message text="ID"/>
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
				<spring:message text="Parameter Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />&nbsp;<span><form:errors path="name" cssClass="error"/></span>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="itdKey">
				<spring:message text="IT Resource Type Definition"/>
			</form:label>
		</td>
		<td>
			<!--form:select path="itdKey" items="${resTypeComboList}"/-->
			<form:select path="itdKey">
			    <c:forEach items="${resTypeComboList}" var="itd">
			        <c:choose>
			            <c:when test="${itd.key eq itparamdefinition.itdKey}">
			                <option value="${itd.key}" selected="true">${itd.value}</option>
			            </c:when>
			            <c:otherwise>
			                <option value="${itd.key}">${itd.value}</option>
			            </c:otherwise>
			        </c:choose> 
			    </c:forEach>
			</form:select>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty itparamdefinition.name}">
				<input type="submit"
					value="<spring:message text="Edit IT Parameter Definition"/>" />
			</c:if>
			<c:if test="${empty itparamdefinition.name}">
				<input type="submit"
					value="<spring:message text="Add IT Parameter Definition"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>IT Resources List</h3>
<c:if test="${!empty listITParamDefinitions}">
	<table class="tg">
	<tr>
		<th width="80">ID</th>
		<th width="80">Parameter Name</th>
		<th width="80">Resource Type</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listITParamDefinitions}" var="itparamdefinition">
		<tr>
			<td>${itparamdefinition.key}</td>
			<td>${itparamdefinition.name}</td>
			<td>${itparamdefinition.itdKey}</td>
			<td><a href="<c:url value='/itparamdefinition/edit/${itparamdefinition.key}' />" >Edit</a></td>
			<td><a href="<c:url value='/itparamdefinition/remove/${itparamdefinition.key}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>