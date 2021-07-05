function old_Client() {
    change_Display("user_Old");
}

function new_Client() {
    change_Display("user_New");
}

function login() {
        var user_Account = new User(document.getElementById("user_Email").value,
                                    document.getElementById("user_Pass").value);
        //console.log(user_Account.email, user_Account.password);
        $.get({
            url : "http://127.0.0.1:3000/accounts",
            data : {
                email       : user_Account.email,
                password    : user_Account.password
            },
            //dataType : "json",
            success : function (response) {

            }
    });
}

function new_Account() {

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