// Prüfe, ob der Benutzer bereits den Banner geschlossen hat
if (!localStorage.getItem("cookieBannerClosed")) {
    // Zeige den Cookie-Banner an
    var banner = document.getElementById("cookie-banner");
    banner.style.display = "block"; // Stelle sicher, dass der Banner angezeigt wird
}

// Funktion zum Schließen des Banners und Speicherns des Zustands
function closeCookieBanner() {
    var banner = document.getElementById("cookie-banner");
    banner.style.display = "none"; // Verberge den Banner
    localStorage.setItem("cookieBannerClosed", "true");
}
