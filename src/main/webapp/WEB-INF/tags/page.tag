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
	</header>
	<article class="w3-container">
		<h2>${title}</h2>
		<jsp:doBody/>
	</article>
</body>
</html>