const logoutButton = document.getElementById("logout-form-submit");

logoutButton.addEventListener("click", (e) => {
    location.reload();
    window.location.replace("index.html");
    }
)