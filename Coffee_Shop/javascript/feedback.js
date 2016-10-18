var nameRegEx = /^[a-zA-z]+$/;
var emailRegEx = /[a-z0-9]+[a-z0-9_\.-]*[a-z0-9]+\@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,10})/i;

function showError(id, error, message) {
    document.getElementById(id).style.border = "2px solid red";
    document.getElementById(error).innerHTML = message;
    return false;
}

function clearError(id, error) {
    document.getElementById(id).style.border = "";
    document.getElementById(error).innerHTML = "";
}
function validateName(){
    if( document.getElementById("name").value == ''){
            showError("name", "nameError", "Please enter your Name");
    }
    else{
        clearError("name", "nameError");
        if(nameRegEx.test(document.getElementById("name").value)){
            clearError("name", "nameError"); 
        }
        else{
            showError("name", "nameError", "Name can be only characters");

        }
    } 
}
function validateEmail(){
    if(emailRegEx.test(document.getElementById("email").value) || document.getElementById("email").value==''){
        clearError("email", "emailError");
    }
    else{
        showError("email", "emailError", "Invalid Email"); 
    }
}
function validateFields() {
    var flag = true;
    if( document.getElementById("name").value == ''){
            flag = showError("name", "nameError", "Please enter your Name");
    }
    else{
        clearError("name", "nameError");
        if(nameRegEx.test(document.getElementById("name").value)){
          clearError("name", "nameError"); 
        }
        else{
            flag = showError("name", "nameError", "Name can be only characters");

        }
    }

    if(emailRegEx.test(document.getElementById("email").value) || document.getElementById("email").value==''){
        clearError("email", "emailError");
    }
    else{
        flag = showError("email", "emailError", "Invalid Email"); 
    }
    if(!document.getElementById("rating1").checked && !document.getElementById("rating2").checked && !document.getElementById("rating3").checked && !document.getElementById("rating4").checked && !document.getElementById("rating5").checked){
        document.getElementById("ratingError").innerHTML = "Please give a rating and submit";
        document.getElementById("rating1").style.outline = "2px solid red";
        document.getElementById("rating2").style.outline = "2px solid red";
        document.getElementById("rating3").style.outline = "2px solid red";
        document.getElementById("rating4").style.outline = "2px solid red";
        document.getElementById("rating5").style.outline = "2px solid red";
        flag = false;

    }
    else{
        document.getElementById("ratingError").innerHTML = "";
        document.getElementById("rating1").style.outline = "";
        document.getElementById("rating2").style.outline = "";
        document.getElementById("rating3").style.outline = "";
        document.getElementById("rating4").style.outline = "";
        document.getElementById("rating5").style.outline = "";
    }
    if(flag == true){
        alert("Thanks for your feedback");
    }
    return flag;

}

