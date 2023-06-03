// Initialize and add the map
function initMap() {
    // The location of Uluru
    const uluru = { lat: 49.81096280767046, lng: 23.98425754044042 };
    // The map, centered at Uluru
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 4,
        center: uluru,
    });
    // The marker, positioned at Uluru
    const marker = new google.maps.Marker({
        position: uluru,
        map: map,
    });
}

window.initMap = initMap;