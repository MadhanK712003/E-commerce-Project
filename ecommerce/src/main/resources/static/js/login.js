
document.getElementById("loginForm").addEventListener("submit", function (event) {
    let isValid = true;

    const username = document.getElementById("username");
    const password = document.getElementById("password");
    const usernameError = document.getElementById("usernameError");
    const passwordError = document.getElementById("passwordError");

    usernameError.textContent = "";
    passwordError.textContent = "";

    if (username.value.trim() === "") {
        usernameError.textContent = "Username is required";
        isValid = false;
    }

    if (password.value.trim() === "") {
        passwordError.textContent = "Password is required";
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
    }
});