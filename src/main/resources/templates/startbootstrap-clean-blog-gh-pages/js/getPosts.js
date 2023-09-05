window.onload = loadPosts();

function loadPosts(){
    fetch("http://localhost:8000/publicaciones", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(response => response.json())
    .then(publicaciones=>{

        const contendor = document.getElementById("contenedor");

        for (const post of publicaciones) {

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

            contendor.appendChild(postContainer);
            console.log(post);
        }
    })}