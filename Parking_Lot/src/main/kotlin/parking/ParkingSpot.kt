package parking

class ParkingSpot(val spotNr: Int) {
    private var parkedCar: Car? = null

    fun isFree() = parkedCar == null

    fun park(car: Car) {
        parkedCar = car
    }

    fun leave() {
        parkedCar = null
    }

    fun getCar() = parkedCar
}
