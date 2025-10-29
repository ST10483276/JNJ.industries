// A simple function to be executed when the form is submitted.
function handleSignup(event) {
    // Prevent the default form submission (which is navigating to the form's action, signin.html)
    // for a moment so we can show the alert.
    event.preventDefault(); 

    // A check to ensure all required fields are filled, 
    // although the 'required' attribute in HTML handles most of this.
    const form = event.target;
    if (form.checkValidity()) {
        // Show the success pop-up message
        alert("Account successfully created Welcome to Empowering The Nation! Redirecting to the contact page.");

        // After the user clicks 'OK' on the alert, 
        // manually redirect the user to Contact.html.
        // We use a slight delay just in case.
        setTimeout(() => {
            // Instead of using form.action (which is "signin.html"), 
            // we redirect directly to "Contact.html"
            window.location.href = "Contact.html"; 
        }, 100);
    } else {
        // If the form is somehow invalid despite 'required' attributes, let the browser handle it.
        // This 'else' block is usually not hit because of the 'required' attribute.
    }
}

// Wait for the entire HTML document to be loaded
document.addEventListener('DOMContentLoaded', () => {
    // Get the sign-up form element
    // Note: The selector 'form[action="signin.html"]' is kept, 
    // but the destination is overridden in handleSignup.
    const signupForm = document.querySelector('form[action="signin.html"]'); 
    
    // Check if the form was found
    if (signupForm) {
        // Attach the handleSignup function to the form's 'submit' event
        signupForm.addEventListener('submit', handleSignup);
    } else {
        console.error("Signup form not found!");
    }
});