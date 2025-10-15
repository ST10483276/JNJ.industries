// --- Simulated User Data for Demonstration ---
// In a real application, you'd check a database, not localStorage.
const SIMULATED_EMAIL = "test@example.com";
const SIMULATED_PASSWORD = "password123";

// Store a simulated user account in localStorage for a better demo
localStorage.setItem("userEmail", SIMULATED_EMAIL);
localStorage.setItem("userPassword", SIMULATED_PASSWORD);

// --- Get HTML elements ---
// Note: These IDs must be present in the HTML file for this script to work.
const signinForm = document.getElementById("signinForm");
const emailInput = document.getElementById("emailInput");
const passwordInput = document.getElementById("passwordInput");
const toastMessage = document.getElementById("toastMessage");

// --- Toast Message Function ---
function showToast(message) {
  // Check if the toast element exists before trying to use it
  if (!toastMessage) return;
  
  toastMessage.textContent = message;
  toastMessage.className = "show"; // Add 'show' class to display
  // Remove 'show' class after 3 seconds (3000 milliseconds)
  setTimeout(function () {
    toastMessage.className = toastMessage.className.replace("show", "");
  }, 3000);
}

// --- Form Submission Handler ---
if (signinForm) { // Check if the form element was found
    signinForm.addEventListener("submit", function (event) {
        // Prevent the default form submission to the server
        event.preventDefault();

        const enteredEmail = emailInput.value.trim();
        const enteredPassword = passwordInput.value.trim();

        // Retrieve simulated user data from localStorage
        const storedEmail = localStorage.getItem("userEmail");
        const storedPassword = localStorage.getItem("userPassword");

        // 1. Check if a user with that email exists
        if (enteredEmail !== storedEmail) {
            // Toast message for non-existent account
            showToast("Account not found. Please sign up to continue! 🔒");
        } 
        // 2. Check password if email matches
        else if (enteredPassword === storedPassword) {
            // *** SIMULATED SUCCESSFUL LOGIN ***
            showToast("Sign in successful! Redirecting to home... ✅");
            // In a real app, you would redirect the user or set a session cookie here.
            // Example: setTimeout(() => window.location.href = 'Home.html', 1500);
        } 
        // 3. Invalid password
        else {
            showToast("Incorrect password. Please try again. 🚫");
        }
    });
}