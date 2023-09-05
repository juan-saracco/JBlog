//CORREGIR


const createPostBtn = document.getElementById("new-post");
const postTitle = document.getElementById("post-title");
const postContent = document.getElementById("post-content");

createPostBtn.addEventListener('click', () => {
    const titulo = postTitle.value;
    const contenido = postContent.value;

    //const id = recuperar id desde input.

    fetch("http://localhost:8000/usuarios/publicaciones/1", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            titulo: titulo,
            contenido: contenido
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        document.getElementById("titulo").innerText = data.titulo;
        document.getElementById("contenido").innerText = data.contenido;
        document.getElementById("autor").innerText =`Post creado por: ${data.autor.userName}`;

    })
    .catch(error => {
        console.error("Error:", error);
    });
});
