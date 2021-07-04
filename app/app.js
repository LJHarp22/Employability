function oldClient() {
    change_Display("user_Old");
}


function newClient() {
    change_Display("user_New");
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