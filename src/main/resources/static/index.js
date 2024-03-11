$(document).ready(function(){
    $('#loginButton').click(function(){
        var username = $('#username').val();
        var password = $('#password').val();

        let user = {
            username,
            password
        }
        console.log(user)
        $.ajax({
            type: 'POST',
            contentType : "application/json",
            dataType: "JSON",
            url: 'http://localhost:8080/api/login', // URL del endpoint de inicio de sesión en tu controlador REST
            data: JSON.stringify(user),
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            success: function(response){
                // Manejar la respuesta del servidor
                console.log(response);
                alert("Login exitoso!"); // Solo como ejemplo, puedes hacer lo que necesites con la respuesta del servidor
                debugger
                if (response.role === "admin"){
                    window.location.href = "Form/AddUser.html";
                }else{
                    alert("No eres Admin")

                }

            },
            error: function(xhr, status, error){
                // Manejar errores de la solicitud
                console.error(xhr.responseText);
                alert("Error en el inicio de sesión: " + xhr.responseText);
            }
        });
    });
});