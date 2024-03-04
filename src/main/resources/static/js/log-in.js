var IP_SERVER = "/api";
var IP_SERVERS = {
    "Mario": "http://169.254.181.144:3000/usuarios/login",
    "Ricardo": "http://192.168.174.254:8080/api/login",
    "Jesus": "http://192.168.174.227:8080/api/login"
}
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    console.log("username: " + username + " password: " + password);
    $.ajax({
        type: "POST",
        // http://169.254.181.144:3000/usuarios/login
        // http://192.168.10.227:8080/api/login
        // http://192.168.10.59:8080/api/login
        // /api/login
        url: "/api/login",
        contentType: "application/json",  // Ajusta el tipo de contenido aquí
        data: JSON.stringify({ 
            username: username,
            password: password
        }),
        success: function (data) {
            console.log("Respuesta del servidor: " + data);
            $("#respuesta").text("¡Inicio de sesión exitoso! Respuesta del servidor: " + data);
        },
        error: function (error) {
            console.log("Error en el inicio de sesión: " + JSON.stringify(error));
            $("#respuesta").text("Error en el inicio de sesión: " + JSON.stringify(error));
        }
    });
  }