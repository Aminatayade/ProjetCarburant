package org.isen.projet

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("/fxml/main.fxml"))
        val scene = Scene(fxmlLoader.load())
        scene.stylesheets.add(HelloApplication::class.java.getResource("/css/styles.css").toExternalForm())

        stage.title = "Modern Fuel Station App"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}
