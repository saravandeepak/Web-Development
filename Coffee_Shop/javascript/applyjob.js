
var charRegEx = /^[a-zA-z]+$/;
var numRegEx = /^[0-9]+$/;
var phoneRegEx = /\d{3}[-.]?\d{3}[-.]?\d{4}/;
var emailRegEx = /[a-z0-9]+[a-z0-9_\.-]*[a-z0-9]+\@[a-z0-9-]+([\.a-z0-9-]+)*(\.[a-z]{2,10})/i;
function showError(id, error, message){
    document.getElementById(id).style.border = "2px solid red";
    document.getElementById(error).innerHTML = message;
    return false;
}

function clearError(id, error){
    document.getElementById(id).style.border = "";
    document.getElementById(error).innerHTML = "";
}

function validateName(id, error){
    var name = document.getElementById(id).value;
    var flag = true;
    if(!charRegEx.test(name)){
        flag = showError(id,error, "Only Characters");

    }
    else{
        clearError(id, error);
    }
    return flag;

}


function validateFieldsOnBlur(elem){

    var id = elem.id;


    switch(id){
        case 'fname':
            if(document.getElementById("fname").value==''){
                showError("fname", "fnameError", "Field can't be left blank");
            } 
            else{
                clearError("fname", "fnameError");
                validateName("fname", "fnameError");

            }
            break;

        case 'mname':
            if(document.getElementById("mname").value==''){

            } 
            else{
                validateName("mname", "mnameError");
            }
            break;

        case 'lname':
            if(document.getElementById("lname").value==''){
                showError("lname", "lnameError", "Field can't be left blank");
            } 
            else{
                clearError("lname", "lnameError");
                validateName("lname", "lnameError");
            }
            break;

        case 'age':
            if(document.getElementById("age").value==''){
                showError("age","ageError", "Field can't be left blank");
            }
            else{
                clearError("age", "ageError");

                if(!numRegEx.test(document.getElementById("age").value))
                {
                    showError("age","ageError", "Age can only be numbers")   
                }
                else{
                    clearError("age", "ageError");
                    var num = parseInt(document.getElementById("age").value); 
                    if( num >= 18 && num <= 50 ){
                        clearError("age", "ageError");
                    }
                    else{

                        showError("age","ageError", "Age can only be between 18 and 50")
                    }
                }

            }
            break;

        case 'email':
            if(document.getElementById("email").value==''){
                showError("email", "emailError", "Field can't be left blank");
            } 
            else{
                clearError("email", "emailError");
                if(!emailRegEx.test(document.getElementById("email").value)){
                   showError("email", "emailError", "Invalid Email"); 
                }
                else{
                    clearError("email", "emailError");
                }
            }
            break;

        case 'phoneno':
            if(document.getElementById("phoneno").value==''){
                showError("phoneno", "phoneError", "Field can't be left blank");

            } 
            else{
                clearError("phoneno", "phoneError");

                if(!phoneRegEx.test(document.getElementById("phoneno").value))
                {
                    showError("phoneno","phoneError", "Not a valid phone number");   
                }
                else{
                    clearError("phoneno", "phoneError");
                    
                }
            }
            break;

        case 'address1':
            if(document.getElementById("address1").value==''){
                showError("address1", "addressError", "Field can't be left blank");

            } 
            else{
                clearError("address1", "addressError");
            }
            break;

        case 'city':
            if(document.getElementById("city").value==''){
                showError("city", "cityError", "Field can't be left blank");

            } 
            else{
                clearError("city", "cityError");
                validateName("city", "cityError");
            }
            break;

        case 'country':
            if(document.getElementById("country").value==''){
                showError("country", "countryError", "Field can't be left blank");

            } 
            else{
                clearError("country", "countryError");
                validateName("country", "countryError");
            }
            break;

        case 'zipcode':
            if(document.getElementById("zipcode").value==''){
                showError("zipcode", "zipError", "Field can't be left blank");

            } 
            else{
                clearError("zipcode", "zipError");
                if(!numRegEx.test(document.getElementById("zipcode").value))
                {
                    showError("zipcode", "zipError", "ZipCode can only be numbers");
                }
                else{
                    clearError("zipcode", "zipError");
                    var num = document.getElementById("zipcode").value;
                    var zipRegEx = /^(\d{6})$/
                    if( zipRegEx.test(num)){
                        clearError("zipcode", "zipError");
                    }
                    else{

                        showError("zipcode", "zipError", "ZipCode must be 6 digits");
                    }
                }
            }
            break;
        case 'intro':
        if(document.getElementById("intro").value == ''){
            document.getElementById("intro").style.border = "2px solid red"; 
            document.getElementById("introError").style.display = 'block';
        }
        else{
            document.getElementById("intro").style.border = "";
            document.getElementById("introError").style.display = 'none';
        }

    }

}

function validateFields(){

    var flag = true;



    if(document.getElementById("fname").value==''){
        flag = showError("fname", "fnameError", "Field can't be left blank");
    } 
    else{
        clearError("fname", "fnameError");
        flag = validateName("fname", "fnameError");

    }
    if(document.getElementById("mname").value==''){

    } 
    else{
        flag = validateName("mname", "mnameError");
    }
    if(document.getElementById("lname").value==''){
        flag = showError("lname", "lnameError", "Field can't be left blank");
    } 
    else{
        clearError("lname", "lnameError");
        flag = validateName("lname", "lnameError");
    }
    if(document.getElementById("age").value==''){
        flag = showError("age","ageError", "Field can't be left blank");
    } 
    else{
        clearError("age", "ageError");

        if(!numRegEx.test(document.getElementById("age").value))
        {
            flag = showError("age","ageError", "Age can only be numbers")   
        }
        else{
            clearError("age", "ageError");
            var num = parseInt(document.getElementById("age").value); 
            if( num >= 18 && num <= 50 ){
                clearError("age", "ageError");
            }
            else{

                flag = showError("age","ageError", "Age can only be between 18 and 50")
            }
        }

    }
    if(document.getElementById("email").value==''){
        flag = showError("email", "emailError", "Field can't be left blank");
    } 
    else{
        clearError("email", "emailError");
        if(!emailRegEx.test(document.getElementById("email").value)){
                   flag = showError("email", "emailError", "Invalid Email"); 
                }
                else{
                    clearError("email", "emailError");
                }
    }
     if(document.getElementById("phoneno").value==''){
                showError("phoneno", "phoneError", "Field can't be left blank");

            } 
    else{
        clearError("phoneno", "phoneError");

        if(!phoneRegEx.test(document.getElementById("phoneno").value))
        {
            showError("phoneno","phoneError", "Not a valid phone number");   
        }
        else{
            clearError("phoneno", "phoneError");

        }
    }

    if(document.getElementById("address1").value==''){
        flag = showError("address1", "addressError", "Field can't be left blank");

    } 
    else{
        clearError("address1", "addressError");
    }
    if(document.getElementById("city").value==''){
        flag = showError("city", "cityError", "Field can't be left blank");

    } 
    else{
        clearError("city", "cityError");
        flag = validateName("city", "cityError");
    }
    if(document.getElementById("country").value==''){
        flag = showError("country", "countryError", "Field can't be left blank");

    } 
    else{
        clearError("country", "countryError");
        flag = validateName("country", "countryError");
    }
    if(document.getElementById("zipcode").value==''){
        flag = showError("zipcode", "zipError", "Field can't be left blank");

    } 
    else{
        clearError("zipcode", "zipError");
        if(!numRegEx.test(document.getElementById("zipcode").value))
        {
            flag = showError("zipcode", "zipError", "ZipCode can only be numbers");
        }
        else{
            clearError("zipcode", "zipError");
            var num = document.getElementById("zipcode").value;
            var zipRegEx = /^(\d{6})$/
            if( zipRegEx.test(num)){
                clearError("zipcode", "zipError");
            }
            else{

                flag = showError("zipcode", "zipError", "ZipCode must be 6 digits");
            }
        }
    }
    if(document.getElementById("intro").value == ''){
        document.getElementById("intro").style.border = "2px solid red"; 
        document.getElementById("introError").style.display = 'block';
    }
    else{
        document.getElementById("intro").style.border = "";
        document.getElementById("introError").style.display = 'none';
    }
    if(!document.getElementById("fulltime").checked && !document.getElementById("parttime").checked)
    {
        document.getElementById("timeError").innerHTML = "Select either Full-Time or Part-Time";
        document.getElementById("fulltime").style.outline = "2px solid red";
        document.getElementById("parttime").style.outline = "2px solid red";
        flag = false;

    }
    else{
        document.getElementById("timeError").innerHTML = "";
        document.getElementById("fulltime").style.outline = "";
        document.getElementById("parttime").style.outline = "";

    }
    if(!document.getElementById("male").checked && !document.getElementById("female").checked && !document.getElementById("other").checked) {
        document.getElementById("sexError").innerHTML = "Please select a sex";
        document.getElementById("male").style.outline = "2px solid red";
        document.getElementById("female").style.outline = "2px solid red";
        document.getElementById("other").style.outline = "2px solid red";
        flag = false;

    }
    else{
        document.getElementById("sexError").innerHTML = "";
        document.getElementById("male").style.outline = "";
        document.getElementById("female").style.outline = "";
        document.getElementById("other").style.outline = "";

    }
    if(flag == true){
        alert("Your Details have been saved");
    }
    return flag;

}

