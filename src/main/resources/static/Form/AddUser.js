function saveUser() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let rol = document.getElementById("role").value;

    let params = {
        username,
        password,
        rol
    };

    $.ajax({
        url: 'http://localhost:8080/api/user',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function(data) {
            console.log(data);
            if (data.error) {
                console.log(data.error);
                alert(data.error);
            } else {
                console.log(data);
            }
        },
        error: function(xhr, status, error) {
            console.error("Error in response:", error);
            if (xhr.responseJSON && xhr.responseJSON.error) {
                alert("Se produjo un error: " + xhr.responseJSON.error);
            } else {
                alert("Se produjo un error: " + error);
            }
        }
    });
}
