package org.isen.projet.model

data class StationService(
    val id: Int,
    val name: String,
    val city: String,
    val fuelType: String,
    val price: Double,
    val isOpen24h: Boolean
)
class StationServiceModel {
    fun getStations(): List<StationService> {
        return listOf(
            StationService(1, "Station 1", "Paris", "Diesel", 1.89, true),
            StationService(2, "Station 2", "Lyon", "Essence", 1.75, false),
        )
    }
}
