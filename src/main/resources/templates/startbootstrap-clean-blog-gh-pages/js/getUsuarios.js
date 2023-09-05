//const usuario = document.getElementById("usuario");
//const email = document.getElementById("email");

fetch("http://localhost:8000/usuarios/1", {
    method: "GET",
    headers: {
        "Content-Type": "application/json"
    }
})
.then(response => response.json())
.then(data => {
    // Aquí puedes procesar los datos recibidos del backend (por ejemplo, mostrarlos en tu página)
    console.log(data);
})
.catch(error => {
    console.error("Error:", error);
});
