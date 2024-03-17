package parking

fun main() {
    var parkingLot: ParkingLot? = null
    do {
        val input = readln().split(" ")
        when (input[0]) {
            "create" -> parkingLot = ParkingLot(input[1].toInt())
            "park" -> parkingLot?.park(Car(input[1], input[2].lowercase())) ?: printNoParkingLotCreated()
            "leave" -> parkingLot?.leave(input[1].toInt()) ?: printNoParkingLotCreated()
            "status" -> parkingLot?.printStatus() ?: printNoParkingLotCreated()
            "reg_by_color" -> parkingLot?.printRegistrationNrsForColor(input[1].lowercase()) ?: printNoParkingLotCreated()
            "spot_by_color" -> parkingLot?.printSpotNrForColor(input[1].lowercase()) ?: printNoParkingLotCreated()
            "spot_by_reg" -> parkingLot?.printSpotNrForRegistrationNr(input[1]) ?: printNoParkingLotCreated()
        }
    } while (input[0] != "exit")
}

private fun printNoParkingLotCreated() = println("Sorry, a parking lot has not been created.")
