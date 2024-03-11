$(document).ready(function(){
    $('#loginButton').click(function(){
        var username = $('#username').val();
        var password = $('#password').val();

        let user = {
            username,
            password
        }
        const SECRET_KEY = "my_super_secret_key";
        const SALT = "ssshhhhhhhhhhh!!!!";
        const iv = CryptoJS.enc.Hex.parse('0000000000000000'); // Mismo IV que en el servidor
                const key = CryptoJS.PBKDF2(SECRET_KEY, SALT, { keySize: 256 / 32, iterations: 65536 });

                const jsonString = JSON.stringify(user);

                const encrypted = CryptoJS.AES.encrypt(
                    jsonString,
                    key,
                    { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 }
                );

        userEncrypted = encrypted.toString();


        
        console.log(user)
        console.log("Encrypted: " + userEncrypted)
        $.ajax({
            type: 'POST',
            contentType : "application/json",
            dataType: "JSON",
            url: 'http://localhost:8080/api/login', // URL del endpoint de inicio de sesión en tu controlador REST
            data: JSON.stringify({ encrypt: userEncrypted }),
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            success: function(response){
                const decrypted = CryptoJS.AES.decrypt(
                    {
                        ciphertext: CryptoJS.enc.Base64.parse(response)
                    },
                    key,
                    { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 }
                );

                const jsonString = decrypted.toString(CryptoJS.enc.Utf8);
                JSON.parse(jsonString);
                // Manejar la respuesta del servidor
                console.log(response.toString());
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
