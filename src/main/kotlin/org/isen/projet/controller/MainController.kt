package org.isen.projet.controller

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.VBox

class MainController {

    @FXML
    private lateinit var searchPane: VBox

    @FXML
    private lateinit var routePane: VBox

    @FXML
    private lateinit var mapPane: VBox

    @FXML
    private lateinit var searchField: TextField

    @FXML
    private lateinit var fuelTypeComboBox: ComboBox<String>

    @FXML
    private lateinit var checkToilets: CheckBox

    @FXML
    private lateinit var checkGonflage: CheckBox

    @FXML
    private lateinit var checkShop: CheckBox

    @FXML
    private lateinit var departureField: TextField

    @FXML
    private lateinit var destinationField: TextField

    fun showSearch() {
        searchPane.isVisible = true
        routePane.isVisible = false
        mapPane.isVisible = false
    }

    fun showRoute() {
        searchPane.isVisible = false
        routePane.isVisible = true
        mapPane.isVisible = false
    }

    fun showMap() {
        searchPane.isVisible = false
        routePane.isVisible = false
        mapPane.isVisible = true
    }

    fun handleSearch() {
        val query = searchField.text
        val fuelType = fuelTypeComboBox.value ?: "Tous"
        val hasToilets = checkToilets.isSelected
        val hasGonflage = checkGonflage.isSelected
        val hasShop = checkShop.isSelected

        println("Recherche : $query")
        println("Filtre carburant : $fuelType")
        println("Toilettes : $hasToilets, Gonflage : $hasGonflage, Boutique : $hasShop")

        // Ici, tu pourras ajouter une logique pour filtrer les stations-service selon les critères sélectionnés.
    }

    fun handleRoute() {
        val departure = departureField.text
        val destination = destinationField.text
        println("Calcul de l'itinéraire de $departure à $destination")
    }

    fun handleMap() {
        println("Affichage de la carte")
    }
}
