const form = document.getElementById('register-form');

form.addEventListener('submit', function (event) {
	let valid = true;

	const lastNameInput = document.getElementById('last-name');
	const lastNameErrorMessage = document.getElementById('last-name-error-message');
	let lastName = lastNameInput.value;
	if(lastName.length === 0) {
		valid = false;
		lastNameErrorMessage.innerHTML = 'Поле &laquo;Фамилия&raquo; не может быть пустым';
		lastNameInput.classList.add('w3-border-red');
	} else {
		lastNameErrorMessage.innerHTML = '';
		lastNameInput.classList.remove('w3-border-red');
	}

	const firstNameInput = document.getElementById('first-name');
	const firstNameErrorMessage = document.getElementById('first-name-error-message');
	let firstName = firstNameInput.value;
	if(firstName.length === 0) {
		valid = false;
		firstNameErrorMessage.innerHTML = 'Поле &laquo;Имя&raquo; не может быть пустым';
		firstNameInput.classList.add('w3-border-red');
	} else {
		firstNameErrorMessage.innerHTML = '';
		firstNameInput.classList.remove('w3-border-red');
	}

	const birthdayInput = document.getElementById('birthday');
	const birthdayErrorMessage = document.getElementById('birthday-error-message');
	let birthday = birthdayInput.value;
	if(birthday.length === 0) {
		valid = false;
		birthdayErrorMessage.innerHTML = 'Поле &laquo;Дата рождения&raquo; не может быть пустым';
		birthdayInput.classList.add('w3-border-red');
	} else if(!birthday.match(/\d{2}\.\d{2}.\d{4}/)) {
		valid = false;
		birthdayErrorMessage.innerHTML = 'Поле &laquo;Дата рождения&raquo; должно соответствовать шаблону <strong>dd.MM.yyyy</strong>';
		birthdayInput.classList.add('w3-border-red');
	} else {
		birthdayErrorMessage.innerHTML = '';
		birthdayInput.classList.remove('w3-border-red');
	}

	const loginInput = document.getElementById('login');
	const loginErrorMessage = document.getElementById('login-error-message');
	let login = loginInput.value;
	if(login.length === 0) {
		valid = false;
		loginErrorMessage.innerHTML = 'Поле &laquo;Имя пользователя&raquo; не может быть пустым';
		loginInput.classList.add('w3-border-red');
	} else {
		loginErrorMessage.innerHTML = '';
		loginInput.classList.remove('w3-border-red');
	}

	const passwordInput = document.getElementById('password');
	const passwordErrorMessage = document.getElementById('password-error-message');
	let password = passwordInput.value;
	if(password.length === 0) {
		valid = false;
		passwordErrorMessage.innerHTML = 'Поле &laquo;Пароль&raquo; не может быть пустым';
		passwordInput.classList.add('w3-border-red');
	} else {
		const passwordRepeatInput = document.getElementById('password-repeat');
		const passwordRepeatErrorMessage = document.getElementById('password-repeat-error-message');
		let passwordRepeat = passwordRepeatInput.value;
		if(password !== passwordRepeat) {
			valid = false;
			passwordErrorMessage.innerHTML = 'Значения в полях &laquo;Пароль&raquo; и &laquo;Повтор пароля&raquo; не совпадают';
			passwordInput.classList.add('w3-border-red');
			passwordRepeatErrorMessage.innerHTML = 'Значения в полях &laquo;Пароль&raquo; и &laquo;Повтор пароля&raquo; не совпадают';
			passwordRepeatInput.classList.add('w3-border-red');
		} else {
			passwordErrorMessage.innerHTML = '';
			passwordInput.classList.remove('w3-border-red');
			passwordRepeatErrorMessage.innerHTML = '';
			passwordRepeatInput.classList.remove('w3-border-red');
		}
	}

	if(!valid) {
		event.preventDefault();
	}
});