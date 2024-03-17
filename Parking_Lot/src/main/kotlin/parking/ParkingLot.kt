package parking

class ParkingLot(spots: Int) {
    private val parkingSpots = MutableList(spots) { ParkingSpot(it + 1) }

    init {
        println("Created a parking lot with $spots spots.")
    }

    fun park(car: Car) {
        if (isLotFull().not()) {
            parkInFirstFreeSpot(car)
        } else {
            println("Sorry, the parking lot is full.")
        }
    }

    private fun parkInFirstFreeSpot(car: Car) {
        val freeSpot = getFirstFreeSpot()
        parkingSpots[freeSpot].park(car)
        println("${car.color} car parked in spot ${freeSpot + 1}.")
    }

    private fun getFirstFreeSpot() = parkingSpots.indexOfFirst { it.isFree() }

    fun leave(spot: Int) {
        val parkingSpot = parkingSpots[spot - 1]
        if (parkingSpot.isFree().not()) {
            parkingSpot.leave()
            println("Spot $spot is free.")
        } else {
            println("There is no car in spot $spot.")
        }
    }

    fun printStatus() {
        if (isLotEmpty().not()) {
            printSpotsInUse()
        } else {
            println("Parking lot is empty.")
        }
    }

    fun printRegistrationNrsForColor(color: String) =
        println(parkingSpots
            .mapNotNull { it.getCar() }
            .filter { it.color == color }
            .joinToString { it.registrationNr }
            .ifBlank {
                "No cars with color $color were found."
            })

    fun printSpotNrForColor(color: String) =
        println(parkingSpots
            .filter { it.getCar()?.color == color }
            .joinToString { it.spotNr.toString() }
            .ifBlank {
                "No cars with color $color were found."
            })

    fun printSpotNrForRegistrationNr(registrationNumber: String) {
        val parkingSpot = parkingSpots.find { it.getCar()?.registrationNr == registrationNumber }
        val spotNr = parkingSpot?.spotNr
        println(spotNr ?: "No cars with registration number $registrationNumber were found.")
    }

    private fun printSpotsInUse() {
        parkingSpots.filter { it.isFree().not() }.forEach { parkingSpot ->
            val parkedCar = parkingSpot.getCar() ?: throw IllegalStateException("Parking spot that is occupied has no car!")
            println("${parkingSpot.spotNr} ${parkedCar.registrationNr} ${parkedCar.color}")
        }
    }

    private fun isLotFull() = parkingSpots.all { it.isFree().not() }

    private fun isLotEmpty() = isLotFull().not()
}
