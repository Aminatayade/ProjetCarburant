package org.isen.projet.controller

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.Node
import javafx.collections.FXCollections
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainController {

    @FXML private lateinit var searchField: TextField
    @FXML private lateinit var startPointField: TextField  // Nouveau champ pour le point de départ
    @FXML private lateinit var endPointField: TextField    // Nouveau champ pour le point d'arrivée
    @FXML private lateinit var fuelTypeComboBox: ComboBox<String>
    @FXML private lateinit var checkToilets: CheckBox
    @FXML private lateinit var checkGonflage: CheckBox
    @FXML private lateinit var checkShop: CheckBox
    @FXML private lateinit var checkRestaurant: CheckBox
    @FXML private lateinit var stationListView: ListView<String>
    @FXML private lateinit var contentPane: BorderPane

    private val stationData = FXCollections.observableArrayList<String>()
    private var currentDataSource = "https://public.opendatasoft.com/explore/dataset/prix-des-carburants-j-1" // Source principale

    @FXML
    fun initialize() {
        // Afficher la carte par défaut
        loadView("/fxml/mapView.fxml")
        stationListView.items = stationData
        loadStations()
    }

    // Méthode pour utiliser la source principale
    fun usePrimarySource() {
        currentDataSource = "https://public.opendatasoft.com/explore/dataset/prix-des-carburants-j-1"
        loadStations() // Recharger les stations avec la nouvelle source
    }

    // Méthode pour utiliser la source secondaire
    fun useSecondarySource() {
        currentDataSource = "https://www.prix-carburants.gouv.fr/rubrique/opendata"
        loadStations() // Recharger les stations avec la nouvelle source
    }

    private fun loadStations() {
        stationData.clear() // Effacer les stations précédentes
        thread {
            try {
                val url = URL(currentDataSource) // Utilise la source actuelle
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val jsonText = connection.inputStream.bufferedReader().readText()
                val json = JSONObject(jsonText)

                val records = json.getJSONArray("records")
                for (i in 0 until records.length()) {
                    val record = records.getJSONObject(i)
                    val station = record.getJSONObject("fields")
                    val name = station.getString("nom")
                    val address = station.getString("adresse")
                    stationData.add("$name - $address") // Ajouter les stations à la liste
                }

            } catch (e: Exception) {
                stationData.add("Erreur de chargement des stations")
                println("Erreur : ${e.message}")
            }
        }
    }

    // Méthode de recherche
    fun handleSearch() {
        val startPoint = startPointField.text
        val endPoint = endPointField.text
        println("Recherche de stations entre $startPoint et $endPoint pour ${searchField.text}")
        // Ajouter la logique de recherche ici
    }

    // Affichage de la carte
    fun showMap() {
        loadView("/fxml/mapView.fxml")
    }

    // Affichage des résultats sous forme de liste
    fun showList() {
        println("Affichage des résultats sous forme de liste")
    }

    // Affichage de l'itinéraire
    fun showItinerary() {
        // Simuler la sélection d'une station avec des coordonnées fictives
        val stationLat = 48.8625
        val stationLon = 2.2875

        // Charger l'écran de l'itinéraire
        try {
            val loader = FXMLLoader(javaClass.getResource("/fxml/itineraryView.fxml"))
            val node = loader.load<Node>()
            val itineraryController = loader.getController<ItineraryController>()

            // Passer les coordonnées à l'itinéraire
            itineraryController.loadItinerary(48.8566, 2.3522, stationLat, stationLon)

            // Afficher la vue
            contentPane.center = node
        } catch (e: Exception) {
            e.printStackTrace()
            println("Erreur lors du chargement de l'itinéraire.")
        }
    }

    private fun loadView(fxmlPath: String) {
        try {
            val node: Node = FXMLLoader.load(javaClass.getResource(fxmlPath))
            contentPane.center = node
        } catch (e: Exception) {
            e.printStackTrace()
            println("Erreur de chargement du fichier FXML : $fxmlPath")
        }
    }
}
