<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<u:page title="Регистрация">
	<c:url var="url_register_html" value="/register.html"/>
	<form action="${url_register_html}" method="post" id="register-form" class="w3-container form">
		<div class="form-element">
			<label for="last-name">Фамилия*:</label>
			<div class="w3-cell-row">
				<div class="w3-cell input">
					<input type="text" name="last-name" id="last-name" class="w3-input w3-border">
				</div>
				<div class="w3-container w3-cell w3-text-red error-message" id="last-name-error-message"></div>
			</div>
		</div>
		<div class="form-element">
			<label for="first-name">Имя*:</label>
			<div class="w3-cell-row">
				<div class="w3-cell input">
					<input type="text" name="first-name" id="first-name" class="w3-input w3-border">
				</div>
				<div class="w3-container w3-cell w3-text-red error-message" id="first-name-error-message"></div>
			</div>
		</div>
		<div class="form-element">
			<label for="middle-name">Отчество:</label>
			<div class="w3-cell-row">
				<div class="w3-cell input">
					<input type="text" name="middle-name" id="middle-name" class="w3-input w3-border">
				</div>
				<div class="w3-container w3-cell w3-text-red error-message" id="middle-name-error-message"></div>
			</div>
		</div>
		<div class="form-element">
			<label for="birthday">Дата рождения*:</label>
			<div class="w3-cell-row">
				<div class="w3-cell input">
					<input type="text" name="birthday" id="birthday" class="w3-input w3-border">
				</div>
				<div class="w3-container w3-cell w3-text-red error-message" id="birthday-error-message"></div>
			</div>
		</div>
		<div class="form-element">
			<label for="login">Имя пользователя*:</label>
			<div class="w3-cell-row">
				<div class="w3-cell input">
					<input type="text" name="login" id="login" class="w3-input w3-border">
				</div>
				<div class="w3-container w3-cell w3-text-red error-message" id="login-error-message"></div>
			</div>
		</div>
		<div class="form-element">
			<label for="password">Пароль*:</label>
			<div class="w3-cell-row">
				<div class="w3-cell input">
					<input type="password" name="password" id="password" class="w3-input w3-border">
				</div>
				<div class="w3-container w3-cell w3-text-red error-message" id="password-error-message"></div>
			</div>
		</div>
		<div class="form-element">
			<label for="password-repeat">Повтор пароля*:</label>
			<div class="w3-cell-row">
				<div class="w3-cell input">
					<input type="password" id="password-repeat" class="w3-input w3-border">
				</div>
				<div class="w3-container w3-cell w3-text-red error-message" id="password-repeat-error-message"></div>
			</div>
		</div>
		<button type="submit" class="w3-button w3-green w3-hover-teal">Зарегистрироваться</button>
	</form>
	<c:url var="url_register_js" value="/register.js"/>
	<script src="${url_register_js}" type="text/javascript"></script>
</u:page>