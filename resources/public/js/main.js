// Задача:
// Есть форма регистрации:
//  - поле username
//  - поле email
//  - поле password
//
// username: латинские маленькие буковки, длина не меньше 3 и не больше 20,
//           уникален
// email: есть примитивная проверка в браузере, уникален
// password: хотя бы 1 цифра, хотя бы 2 больших буквы, все буквы латинские 

var $username = $('#username')
var $email = $('#email')
var $password = $('#password')

var reset = $field => {
    $field.parent().removeClass('has-danger')
    $field.parent().removeClass('has-success')
    $field.next('.form-control-feedback').html('')
}

var error = ($field, message) => {
    reset($field)
    $field.parent().addClass('has-danger')
    $field.next('.form-control-feedback').html(message)
}

var success = $field => {
    reset($field)
    $field.parent().addClass('has-success')
}

var usernameLocalVerifier = username => {
    if (username.length < 3 || username.length > 20) {
	throw new Error('Длина имени пользователя должна быть не меньше 3 и не больше 20 символов.')
    }

    if (!/^[a-z]*$/.test(username)) {
	throw new Error('Имя пользователя должно состоять из латинских букв.')
    }
	
    return username
}

var usernameApiVerifier = username => {
    return Rx.Observable.create(s => {
	$.ajax({
	    url: "/username",
	    method: "POST",
	    data: {username: username},
	    success: r => s.onNext(r)
	})
    })
	.map(res => {
	    console.log(res.result)
	    if (res.result) {
		return username
	    } else {
		throw new Error('Такое имя уже существует.')
	    }
	})
}

Rx.Observable.fromEvent($username, 'keyup')
    .pluck('target', 'value')
    .map(username => {
	reset($username)
	return username
    })
    .debounce(2000)
    .map(usernameLocalVerifier)
    .flatMap(usernameApiVerifier)
    .retryWhen(e => {
	return e.map(_e => {
	    error($username, _e.message)
	    return e
	})
    })
   .subscribe(data => {
	success($username)
   });
