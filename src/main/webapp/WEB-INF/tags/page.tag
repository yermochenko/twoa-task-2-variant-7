<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="UTF-8">
	<title>Вариант 7 :: ${title}</title>
	<c:url var="url_w3_css" value="/w3.css"/>
	<link rel="stylesheet" type="text/css" href="${url_w3_css}">
	<c:url var="url_custom_css" value="/custom.css"/>
	<link rel="stylesheet" type="text/css" href="${url_custom_css}">
</head>
<body>
	<header class="w3-container w3-purple">
		<h1>Вариант 7</h1>
		<%--@elvariable id="authenticatedUser" type="by.vsu.twoa.domain.User"--%>
		<c:if test="${not empty authenticatedUser}">
			<fmt:formatDate var="userBirthday" value="${authenticatedUser.birthday}" pattern="dd.MM.yyyy"/>
			<button class="w3-right w3-button w3-blue w3-hover-indigo" title="${authenticatedUser.firstName} ${authenticatedUser.middleName} ${authenticatedUser.lastName} (день рождения ${userBirthday})">${authenticatedUser.lastName}&nbsp;${fn:substring(authenticatedUser.firstName, 0, 1)}.&nbsp;${fn:substring(authenticatedUser.firstName, 0, 1)}.</button>
		</c:if>
	</header>
	<article class="w3-container">
		<h2>${title}</h2>
		<jsp:doBody/>
	</article>
</body>
</html>