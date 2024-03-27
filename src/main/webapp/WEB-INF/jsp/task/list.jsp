<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Список задач</title>
</head>
<body>
	<h1>Список задач</h1>
	<ol>
		<%--@elvariable id="tasks" type="java.util.List"--%>
		<c:forEach var="task" items="${tasks}">
			<%--@elvariable id="task" type="by.vsu.twoa.domain.Task"--%>
			<li>${task.name} (${task.created})</li>
		</c:forEach>
	</ol>
</body>
</html>