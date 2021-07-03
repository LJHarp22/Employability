function oldClient() {
    change_Display("user_Options");
    change_Display("user_Old");
}


function newClient() {
    change_Display("user_Options");
    change_Display("user_New");
}


function change_Display(element_Id) {
    var x = document.getElementById(element_Id);
    if(x.style.display === "none"){
        x.style.display = "flex";
    } else {
        x.style.display = "none";
    }
}