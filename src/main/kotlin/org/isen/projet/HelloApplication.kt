package org.isen.projet

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        try {
            // Charger le fichier FXML
            val loader = FXMLLoader(javaClass.getResource("/fxml/main.fxml"))
            val root = loader.load<Any>()

            // Créer la scène
            val scene = Scene(root as javafx.scene.Parent)
            scene.stylesheets.add(javaClass.getResource("/css/styles.css").toExternalForm())

            // Configurer la fenêtre principale
            stage.title = "StationService"
            stage.scene = scene
            stage.show()
        } catch (e: Exception) {
            e.printStackTrace() // Afficher l'exception si une erreur se produit
        }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}
