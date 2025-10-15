// A simple function to be executed when the form is submitted.
function handleSignup(event) {
    // Prevent the default form submission (which is navigating to signin.html)
    // for a moment so we can show the alert.
    event.preventDefault(); 

    // A check to ensure all required fields are filled, 
    // although the 'required' attribute in HTML handles most of this.
    const form = event.target;
    if (form.checkValidity()) {
        // Show the success pop-up message
        alert("Account successfully created! Redirecting to the sign-in page.");

        // After the user clicks 'OK' on the alert, 
        // manually submit the form to proceed to the 'action' URL.
        // We use a slight delay just in case.
        setTimeout(() => {
            // Note: We need to use a non-default method to submit 
            // after the alert, or change the action to an actual server endpoint.
            // For this simple client-side example, we'll just redirect manually 
            // to mimic the form's action, as calling form.submit() might loop with the event listener.
            window.location.href = form.action;
        }, 100);
    } else {
        // If the form is somehow invalid despite 'required' attributes, let the browser handle it.
        // This 'else' block is usually not hit because of the 'required' attribute.
    }
}

// Wait for the entire HTML document to be loaded
document.addEventListener('DOMContentLoaded', () => {
    // Get the sign-up form element
    const signupForm = document.querySelector('form[action="signin.html"]');
    
    // Check if the form was found
    if (signupForm) {
        // Attach the handleSignup function to the form's 'submit' event
        signupForm.addEventListener('submit', handleSignup);
    } else {
        console.error("Signup form not found!");
    }
});