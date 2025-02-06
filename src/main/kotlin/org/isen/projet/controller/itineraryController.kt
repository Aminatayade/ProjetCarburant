package org.isen.projet.controller

import javafx.fxml.FXML
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView

class ItineraryController {

    @FXML private lateinit var itineraryWebView: WebView

    private val apiKey = "cbe88d48f284488bb67623ace0169588" // 🔑 Remplacez par votre clé API Geoapify

    @FXML
    fun initialize() {
        // Exemple de coordonnées utilisateur et station-service (Paris)
        val userLat = 48.8566
        val userLon = 2.3522
        val stationLat = 48.8625
        val stationLon = 2.2875

        loadItinerary(userLat, userLon, stationLat, stationLon)
    }

    fun loadItinerary(userLat: Double, userLon: Double, stationLat: Double, stationLon: Double) {
        val webEngine: WebEngine = itineraryWebView.engine

        val mapHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
                <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"/>
                <style>
                    #map { height: 100vh; width: 100vw; }
                </style>
            </head>
            <body>
                <div id="map"></div>
                <script>
                    var map = L.map('map').setView([$userLat, $userLon], 13);
                    L.tileLayer('https://maps.geoapify.com/v1/tile/osm-bright/{z}/{x}/{y}.png?apiKey=$apiKey', {
                        attribution: '© OpenStreetMap contributors'
                    }).addTo(map);

                    var userMarker = L.marker([$userLat, $userLon]).addTo(map).bindPopup("Vous êtes ici").openPopup();
                    var stationMarker = L.marker([$stationLat, $stationLon]).addTo(map).bindPopup("Station-service").openPopup();

                    // Requête Geoapify pour l'itinéraire
                    var routeUrl = `https://api.geoapify.com/v1/routing?waypoints=$userLat,$userLon|$stationLat,$stationLon&mode=drive&apiKey=$apiKey`;

                    fetch(routeUrl)
                        .then(response => response.json())
                        .then(result => {
                            var routeLayer = L.geoJSON(result, {
                                style: function () {
                                    return { color: "blue", weight: 5 };
                                }
                            }).addTo(map);
                        })
                        .catch(error => console.error("Erreur lors de la récupération de l'itinéraire:", error));
                </script>
            </body>
            </html>
        """.trimIndent()

        webEngine.loadContent(mapHtml)
    }
}
