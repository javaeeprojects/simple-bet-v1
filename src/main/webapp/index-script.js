
function showLoginForm(){
    $("#center-choice").css('display', 'none');
    $("#register-form").css('display', 'none');
    $("#login-form").css('display', 'block');
}

function showRegisterForm(){
    $("#center-choice").css('display', 'none');
    $("#login-form").css('display', 'none');
    $("#register-form").css('display', 'block');
}

function showStartMenu(){
    $("#center-choice").css('display', 'block');
    $("#login-form").css('display', 'none');
    $("#register-form").css('display', 'none');
}

$(document).ready(function() {

    $("#login-form").submit(function (event) {

        $("#login-error").text("");
        $(".loader").css("display","block");

        event.preventDefault();

        console.log("calling ajax");
        $.ajax({
            type: 'POST',
            cache: false,
            url: 'login',
            data: {
                'username' :  $("#username").val(),
                'password' : $("#password").val()
            },
            success : function (response) {
                $(".loader").css("display","none");
                $("#login-error").text(response.error);
                console.log(response.error);
            }
        });
    })
})


$(document).ready(function() {

    $("#register-form").submit(function (event) {

        $("#register-error").text("");
        $(".loader").css("display","block");

        event.preventDefault();

        var email = $('#register-username').val();
        var password = $('#register-password').val();
        var repeat = $('#repeat-password').val();

        var isEmailValid = validateEmail(email);
        var isPasswordValid = validatePassword(password);

        if(isEmailValid && isPasswordValid && !comparePasswords(password,repeat)) {

            console.log("calling ajax");
            $.ajax({
                type: 'POST',
                cache: false,
                url: 'login',
                data: {
                    'username': email,
                    'password': password
                },
                success: function (response) {
                    $(".loader").css("display", "none");
                    $("#register-error").text(response.error);
                    console.log(response.error);
                }
            });
        }
        else {

            $(".loader").css("display","none");

            if(!isEmailValid) {
                $("#register-error").text("Invalid e-mail address");
            }
            else if(!isPasswordValid){
                $("#register-error").text("Password must contain at least 5 characters");
            }
            else{
                $("#register-error").text("Passwords are not the same");
            }
        }
    })
})

function validateEmail(email) {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(email.toLowerCase());
}

function validatePassword(password){
    return password.length >=5;
}

function comparePasswords(password,repeat){
    return password.localeCompare(repeat);
}