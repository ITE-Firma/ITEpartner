// Prüfe, ob der Benutzer bereits den Banner geschlossen hat
if (!localStorage.getItem("cookieBannerClosed")) {
    // Zeige den Cookie-Banner an
    var banner = document.getElementById("cookie-banner");
    banner.classList.add("show");
}

// Funktion zum Schließen des Banners und Speicherns des Zustands
function closeCookieBanner() {
    var banner = document.getElementById("cookie-banner");
    banner.classList.remove("show");
    localStorage.setItem("cookieBannerClosed", "true");
}