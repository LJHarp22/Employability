function old_Client() {
    change_Display("user_Old");
}

function new_Client() {
    change_Display("user_New");
}

function login() {
        var user_Account = new User(document.getElementById("user_Email").value,
                                    document.getElementById("user_Pass").value);
        $.get({
            url : "http://127.0.0.1:3000/account",
            data : {
                email       : user_Account.email,
                password    : user_Account.password
            },
            success : function (response) {

            }
    });
}

function new_Account() {
    var user_Account = new User(document.getElementById("new_Email").value,
                                document.getElementById("new_Pass").value);
    if(user_Account.password === document.getElementById("confirm_Pass").value) {
        $.post({
            url : "http://127.0.0.1:3000/account",
            data : {
                email       : user_Account.email,
                password    : user_Account.password
            },
            success : function (response) {

            }
    });
    } else {
        document.getElementById("new_Response").style.display = "block";
        document.getElementById("new_Response").style.color = "red";
        document.getElementById("new_Response").innerHTML = "Passwords do not match";
        document.getElementById("user_New").style.height = '360px';
    }
}

function change_Display(element_Id) {
    document.getElementById("user_Options").style.display = "none";
    var x = document.getElementById(element_Id);
    if(x.style.display === "none"){
        x.style.display = "flex";
    } else {
        x.style.display = "none";
    }
}

class User {
    constructor(email, password) {
        this.email = email;
        this.password = password;
    }
}