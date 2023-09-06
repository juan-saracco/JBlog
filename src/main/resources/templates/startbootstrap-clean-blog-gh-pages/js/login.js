const userNameInput = document.getElementById("userName");
const emailInput = document.getElementById("email");

document.getElementById('loginBtn').addEventListener("click", (e) => {
    e.preventDefault();


    if (userNameInput.value.length < 3 || emailInput.value.length < 5) {
        alert("usuario invalido")
    } else {
        const userName = userNameInput.value;
        const email = emailInput.value


        fetch("http://localhost:8000/usuarios", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                userName: userName,
                email: email
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.status = 200 && userName != "") {
                    window.location.href = "index.html";
                }
            }
            )
            .catch(error => {
                console.error(error);

            })
    }
})