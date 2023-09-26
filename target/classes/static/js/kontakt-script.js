document.getElementById("calculateDistanceBtn").addEventListener("click", calculateDistance);

function calculateDistance() {
    // Replace these coordinates with the actual coordinates of your location and the branch in Lippstadt
    const myLocation = {lat: 51.5074, lng: -0.1278}; // Your location
    const branchLocation = {lat: 51.6777, lng: 8.3442}; // Branch location

    const earthRadiusKm = 6371;

    const lat1 = myLocation.lat * (Math.PI / 180);
    const lat2 = branchLocation.lat * (Math.PI / 180);
    const lng1 = myLocation.lng * (Math.PI / 180);
    const lng2 = branchLocation.lng * (Math.PI / 180);

    const dlat = lat2 - lat1;
    const dlng = lng2 - lng1;

    const a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
        Math.cos(lat1) * Math.cos(lat2) *
        Math.sin(dlng / 2) * Math.sin(dlng / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    const distance = earthRadiusKm * c;

    document.getElementById("distanceResult").textContent = `Entfernung: ${distance.toFixed(2)} km`;
}