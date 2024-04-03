<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<u:page title="Вход в систему">
	<c:if test="${not empty param['error']}">
		<div class="w3-panel w3-pale-red w3-leftbar w3-border-red">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-right w3-hover-red">&times;</span>
			<h3>Ошибка</h3>
			<p>${param['error']}</p>
		</div>
	</c:if>
	<c:if test="${not empty param['warn']}">
		<div class="w3-panel w3-pale-yellow w3-leftbar w3-border-orange">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-right w3-hover-orange">&times;</span>
			<h3>Предупреждение</h3>
			<p>${param['warn']}</p>
		</div>
	</c:if>
	<c:url var="url_login_html" value="/login.html"/>
	<form action="${url_login_html}" method="post" class="w3-container" style="width: 30%; min-width: 500px">
		<div class="form-element">
			<label for="login">Имя пользователя:</label>
			<input type="text" name="login" id="login" class="w3-input w3-border">
		</div>
		<div class="form-element">
			<label for="password">Пароль:</label>
			<input type="password" name="password" id="password" class="w3-input w3-border">
		</div>
		<button type="submit" class="w3-button w3-green">Войти</button>
		<c:url var="url_register_html" value="/register.html"/>
		<a href="#" class="w3-button w3-blue">Зарегистрироваться</a>
	</form>
</u:page>