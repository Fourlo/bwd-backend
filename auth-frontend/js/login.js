document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.querySelector("#login-form form");
    if (loginForm) {
        loginForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            const email = loginForm.querySelector("#login-email").value;
            const password = loginForm.querySelector("#login-password").value;
            try {
                const response = await fetch('http://localhost:8080/api/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        email: email,
                        password: password
                    })
                });

                if (response.ok) {
                    alert("Login successful");
                } else {
                    const result = await response.json();
                    alert("Login failed: " + result.message);
                }
            } catch (error) {
                console.error("Error during login:", error);
                alert("An error occurred during login.");
            }
        })
    }
});