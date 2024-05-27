<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<u:page title="Список задач">
	<ol>
		<%--@elvariable id="tasks" type="java.util.List"--%>
		<c:forEach var="task" items="${tasks}">
			<%--@elvariable id="task" type="by.vsu.twoa.domain.Task"--%>
			<c:url var="url_task_edit_html" value="/task/edit.html">
				<c:param name="id" value="${task.id}"/>
			</c:url>
			<li class="list-item"><a href="${url_task_edit_html}">${task.name} (${task.created})</a></li>
		</c:forEach>
	</ol>
	<c:url var="url_task_edit_html" value="/task/edit.html"/>
	<a href="${url_task_edit_html}" class="w3-button w3-blue w3-hover-indigo">Добавить</a>
</u:page>