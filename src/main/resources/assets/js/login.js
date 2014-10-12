var currentUser;

$(function () {

    $('#loginBtn').on('click', function () {
        var $username = $('#loginUsername').val();
        var $password = $('#loginPassword').val();
        login($username, $password);
    });


    $('#registerBtn').on('click', function () {
        var $email = $('#registerEmail').val();
        var $username = $('#registerUsername').val();
        var $password = $('#registerPassword').val();
        $.ajax(
            {type: "POST", url: "/user", contentType: 'application/json',
                data: JSON.stringify({'email': $email, 'name': $username, 'password': $password})}
        ).
            done(function (data) {
                login($username, $password )
            }).fail(function (data) {
                $('#loginFail').fadeIn();
            })
    });


    var login = function ($username, $password) {
        $.ajax(
            {type: "POST", url: "/user/login", contentType: 'text/plain', username: $username, password: $password}
        ).done(function () {
                $('#loginModal').modal('hide');
                $('#loginLink').html("<span>Logged in as: <br/>" + $username + "!</span>")
                    .css('color', 'white').css('font-wight', 'bold');
//                var header = "Basic " + btoa($username + ":" + $password);
//                document.cookie = "Authorization=" + header;
                
                currentUser = $username;
                
            }).fail(function () {
                $('#loginFail').fadeIn();
            });
    }


});


