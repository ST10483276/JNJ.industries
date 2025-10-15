// Empowering the Nation - Quote Calculator

// VAT and discount setup
const VAT = 0.15; // 15% VAT

// figure out discount based on how many courses
function discountRate(count) {
  if (count <= 1) return 0;
  if (count === 2) return 5;
  if (count === 3) return 10;
  return 15;
}

// format number as currency (Rands)
function money(n) {
  return "R" + Number(n).toLocaleString("en-ZA", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  });
}

// wait for page to load
document.addEventListener("DOMContentLoaded", () => {

  // grab elements from the page
  const form = document.querySelector("form");
  const calcBtn = document.getElementById("calcBtn");
  const clearBtn = document.getElementById("clearBtn");
  const error = document.getElementById("error");

  // Modal elements
  const modal = document.getElementById("quoteModal");
  const closeModalBtn = document.querySelector(".close-btn");
  const who = document.getElementById("modalWho"); // New ID for modal
  const listBox = document.getElementById("modalPickedList"); // New ID for modal
  const subtotalEl = document.getElementById("modalSubtotal");
  const discRateEl = document.getElementById("modalDiscRate");
  const discountEl = document.getElementById("modalDiscount");
  const vatEl = document.getElementById("modalVat");
  const totalEl = document.getElementById("modalTotal");


  // --- Helper Functions for Modal ---

  // Function to display the modal
  const showModal = () => {
    modal.style.display = "block";
  };

  // Function to hide the modal
  const hideModal = () => {
    modal.style.display = "none";
  };

  // Close the modal when the close button (x) is clicked
  closeModalBtn.addEventListener("click", hideModal);

  // Close the modal when clicking outside of it
  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      hideModal();
    }
  });

  // --- Core Functionality ---

  // get selected courses
  const getChecked = () =>
    Array.from(document.querySelectorAll('input[name="courses"]:checked')).map(el => ({
      name: el.value,
      fee: Number(el.dataset.fee)
    }));

  // build list of selected courses
  const renderList = items => {
    const ul = document.createElement("ul");
    ul.style.margin = "6px 0 10px";
    ul.style.paddingLeft = "18px";
    items.forEach(i => {
      const li = document.createElement("li");
      // FIX: Changed double quotes to backticks for the template literal (already done, but good to check)
      li.textContent = `${i.name} — ${money(i.fee)}`;
      ul.appendChild(li);
    });
    return ul;
  };

  // when "Calculate Fees" is clicked
  calcBtn.addEventListener("click", () => {
    const name = document.getElementById("name").value.trim();
    const surname = document.getElementById("Surname").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const email = document.getElementById("email").value.trim();

    // check if user info is filled
    if (!name || !surname || !phone || !email) {
      error.textContent = "⚠️ Please fill in your Name, Surname, Phone, and Email.";
      hideModal(); // Ensure modal is hidden if there's an error
      return;
    }

    // check if any course is picked
    const picks = getChecked();
    if (picks.length === 0) {
      error.textContent = "⚠️ Select at least one course.";
      hideModal(); // Ensure modal is hidden if there's an error
      return;
    }

    // do the math
    const subtotal = picks.reduce((s, i) => s + i.fee, 0);
    const dRate = discountRate(picks.length);
    const discount = subtotal * (dRate / 100);
    const afterDiscount = subtotal - discount;
    const vat = afterDiscount * VAT;
    const total = afterDiscount + vat;

    // populate modal with results
    // FIX: Changed single quotes to backticks for the template literal
    who.textContent = `${name} ${surname} · ${phone} · ${email}`;
    listBox.innerHTML = "";
    listBox.appendChild(renderList(picks));
    subtotalEl.textContent = money(subtotal);
    discRateEl.textContent = dRate;
    discountEl.textContent = "− " + money(discount);
    vatEl.textContent = money(vat);
    totalEl.textContent = money(total);

    // show results in the modal
    error.textContent = "";
    showModal();
  });

  // when "Clear" is clicked
  clearBtn.addEventListener("click", () => {
    form.reset();
    error.textContent = "";
    hideModal(); // Ensure modal is hidden when clearing
  });

});