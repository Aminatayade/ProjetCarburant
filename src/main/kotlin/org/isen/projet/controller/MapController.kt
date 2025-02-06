package org.isen.projet.controller

import javafx.fxml.FXML
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView

class MapController {

    @FXML private lateinit var mapWebView: WebView

    @FXML

    fun initialize() {
        val webEngine: WebEngine = mapWebView.engine
        val apiKey = "cbe88d48f284488bb67623ace0169588"  // Remplacez par votre vraie clé Geoapify

        val mapHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
                <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"/>
                <script src="https://api.geoapify.com/v1/leaflet/geocoding.js?apiKey=$apiKey"></script>
                <script src="https://api.geoapify.com/v1/leaflet/routing.js?apiKey=$apiKey"></script>
                <style>
                    #map { height: 100vh; width: 100vw; }
                </style>
            </head>
            <body>
                <div id="map"></div>
                <script>
                    var map = L.map('map').setView([48.8566, 2.3522], 12); // Paris par défaut
                    L.tileLayer('https://maps.geoapify.com/v1/tile/osm-bright/{z}/{x}/{y}.png?apiKey=$apiKey', {
                        attribution: '© OpenStreetMap contributors'
                    }).addTo(map);

                    function showUserLocation(lat, lon) {
                        map.setView([lat, lon], 14);
                        L.marker([lat, lon]).addTo(map).bindPopup("Vous êtes ici").openPopup();
                    }

                    navigator.geolocation.getCurrentPosition(function(position) {
                        showUserLocation(position.coords.latitude, position.coords.longitude);
                    }, function(error) {
                        console.log("Erreur de localisation: " + error.message);
                    });
                </script>
            </body>
            </html>
        """.trimIndent()

        webEngine.loadContent(mapHtml)
    }
}
