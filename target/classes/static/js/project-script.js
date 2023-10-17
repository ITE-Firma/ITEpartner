const slides = document.querySelectorAll('.slide-project');
let currentSlide = 0;

function showSlide(index) {
    slides.forEach((slide, idx) => {
        slide.style.transform = `translateX(-${index * 100}%)`;
    });
}

showSlide(currentSlide);

document.querySelector('.first').addEventListener('click', () => {
    currentSlide = 0;
    showSlide(currentSlide);
});

document.querySelector('.last').addEventListener('click', () => {
    currentSlide = slides.length - 1;
    showSlide(currentSlide);
});

    function deleteProject(event) {
    event.preventDefault(); // Prevent the form submission

    if (confirm('Are you sure you want to delete this project?')) {
    var form = document.getElementById('deleteForm');
    var xhr = new XMLHttpRequest();

    xhr.open('POST', form.getAttribute('action'), true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
    if (xhr.status === 200) {
    // Handle success, e.g., update the UI
    alert('deleted successfully');
    // Optionally, you can redirect or update the page here
} else {
    // Handle errors, e.g., display an error message
    alert('An error occurred while deleting ');
}
};

    xhr.send(''); // Send the POST request
}
}
function deletePost(event ) {
    event.preventDefault(); // Prevent the form submission

    if (confirm('Are you sure you want to delete this project?')) {
        var form = document.getElementById('deleteFormFeedback');
        var xhr = new XMLHttpRequest();

        xhr.open('POST', form.getAttribute('action'), true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onload = function() {
            if (xhr.status === 200) {
                // Handle success, e.g., update the UI
                alert('deleted successfully');
                // Optionally, you can redirect or update the page here
            } else {
                // Handle errors, e.g., display an error message
                alert('An error occurred while deleting ');
            }
        };

        xhr.send(''); // Send the POST request
    }
}