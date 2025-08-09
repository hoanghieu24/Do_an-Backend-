// Example JavaScript for event handling
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("form").addEventListener("submit", function (e) {
        e.preventDefault();
        alert("Search submitted!");
    });
});
