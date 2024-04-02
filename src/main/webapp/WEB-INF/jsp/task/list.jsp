<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<u:page title="Список задач">
	<ol>
		<%--@elvariable id="tasks" type="java.util.List"--%>
		<c:forEach var="task" items="${tasks}">
			<%--@elvariable id="task" type="by.vsu.twoa.domain.Task"--%>
			<li class="list-item">${task.name} (${task.created})</li>
		</c:forEach>
	</ol>
</u:page>