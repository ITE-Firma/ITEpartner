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

