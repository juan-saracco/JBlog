const createPostBtn = document.getElementById("new-post");
const postTitle = document.getElementById("post-title");
const postContent = document.getElementById("post-content");

createPostBtn.addEventListener('click', async () => {
    const titulo = postTitle.value;
    const contenido = postContent.value;
    const userId = document.getElementById('user').value;

    try {
        const usuariosResponse = await fetch("http://localhost:8000/usuarios", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!usuariosResponse.ok) {
            throw new Error("No se pudo obtener la lista de usuarios.");
        }

        const usuarios = await usuariosResponse.json();

        // Verificar si el usuario actual (por ID) está en la lista de usuarios
        const usuarioEncontrado = usuarios.find(usuario => usuario.userName === userId);

       

        // Crear la publicación si el usuario es válido
        const response = await fetch(`http://localhost:8000/usuarios/publicaciones/${usuarioEncontrado.id}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                titulo: titulo,
                contenido: contenido
            })
        });

        if (!response.ok) {
            throw new Error("No se pudo crear la publicación.");
        }

        const post = await response.json();

        const contenedor = document.getElementById("contenedor");

        const postContainer = document.createElement("div");
        postContainer.className = "post";

        const title = document.createElement("h2");
        title.textContent = `${post.titulo}`;

        const content = document.createElement("p");
        content.textContent = `${post.contenido}`;

        const autor = document.createElement("span");
        autor.textContent = `Creado por: ${post.autor.userName}`;

        postContainer.appendChild(title);
        postContainer.appendChild(content);
        postContainer.appendChild(autor);

        contenedor.appendChild(postContainer);
        postContent.textContent = "";
        postTitle.textContent = "";
        document.getElementById('user').textContent = "";


        
    } catch (error) {
        console.error("Error:", error);
        alert("Usuario invalido")
    }
});
