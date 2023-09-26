const mainButton = document.getElementById('main-button');
const menu = document.getElementById('menu');
const subMenu = document.getElementById('sub-menu');
const menuOptions = document.querySelectorAll('.menu-option');
const subMenuOptions = document.querySelectorAll('.sub-menu-option');
const option1 = document.getElementById('option-1');

mainButton.addEventListener('click', () => {
    if (menu.classList.contains('active')) {
        closeMenu();
    } else {
        openMenu();
    }
});

document.addEventListener('click', (event) => {
    if (!event.target.closest('.button-container')) {
        closeMenu();
    }
});

option1.addEventListener('click', () => {
    if (!subMenu.classList.contains('active')) {
        openSubMenu();
    } else {
        closeSubMenu();
    }
});

function openMenu() {
    menu.classList.add('active');
    mainButton.classList.add('active');
    setTimeout(() => {
        menuOptions.forEach((option, index) => {
            setTimeout(() => {
                option.classList.add('active');
            }, index * 100);
        });
    }, 100);
}

function closeMenu() {
    menu.classList.remove('active');
    subMenu.classList.remove('active');
    mainButton.classList.remove('active');
    menuOptions.forEach(option => option.classList.remove('active'));
    subMenuOptions.forEach(option => option.classList.remove('active'));
}

function openSubMenu() {
    subMenu.classList.add('active');
    setTimeout(() => {
        subMenuOptions.forEach((option, index) => {
            setTimeout(() => {
                option.classList.add('active');
            }, index * 100);
        });
    }, 100);
}

function closeSubMenu() {
    subMenu.classList.remove('active');
    subMenuOptions.forEach(option => option.classList.remove('active'));
}
