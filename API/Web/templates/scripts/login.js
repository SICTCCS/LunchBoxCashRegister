const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;

    if (username === "admin" && password === "sictc!") {
        location.reload();
        window.location.href = "pages/data.html";
    } 
    if (username === "chef" && password === "sictc!") {
        location.reload();
        window.location.href = "pages/data.html";
    } 
    else {
        loginErrorMsg.style.opacity = 1;
    }
})
